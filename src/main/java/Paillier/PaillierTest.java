package Paillier;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class PaillierTest {

    @Test
    public void encryptionDecryptionTest() {
        System.out.println("\nCIPHER TEST:");

        KeyPair keyPair = new KeyGenerator().generateKeyPair();
        HomomorphicCipher homomorphicCipher = new HomomorphicCipher(keyPair);

        int plain = 12345678;
        BigInteger cipherText = homomorphicCipher.encrypt(BigInteger.valueOf(plain));
        System.out.println("Encryption started...");
        System.out.println("Plaintext:" + plain + " \nCorresponding ciphertext: " + cipherText);

        BigInteger plaintext = homomorphicCipher.decrypt(cipherText);
        System.out.println("\nDecryption started...");
        System.out.println("Ciphertext: " + cipherText + "\nCorresponding plaintext: " + plaintext);

        Assert.assertEquals(BigInteger.valueOf(plain), plaintext);
    }

    @Test
    public void homomorphicPropertyTest() {
        System.out.println("\nHOMOMORPHIC PROPERTY TEST:");

        KeyPair keyPair = new KeyGenerator().generateKeyPair();
        HomomorphicCipher homomorphicCipher = new HomomorphicCipher(keyPair);

        int plaintext1 = 130;
        int plaintext2 = 170;

        BigInteger cipherText1 = homomorphicCipher.encrypt(BigInteger.valueOf(plaintext1));
        BigInteger cipherText2 = homomorphicCipher.encrypt(BigInteger.valueOf(plaintext2));

        BigInteger ciphertextProduct = cipherText1.multiply(cipherText2).mod(keyPair.getPublicKey().getNSquared());

        BigInteger addedPlaintext = homomorphicCipher.decrypt(ciphertextProduct);

        System.out.println(addedPlaintext);

        Assert.assertEquals(BigInteger.valueOf(plaintext1 + plaintext2), addedPlaintext);
    }
}
