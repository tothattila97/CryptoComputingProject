package Paillier;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        System.out.println("Paillier encryption algorithm... \n");

        KeyPair generatedKeyPair = new KeyGenerator().generateKeyPair();
        HomomorphicCipher homomorphicCipher = new HomomorphicCipher(generatedKeyPair);

        System.out.println("Public key: \n\t" + "g: " + generatedKeyPair.getPublicKey().getG() + "\n\t"
                + "n: " + generatedKeyPair.getPublicKey().getN() + "\n");
        System.out.println("Private key: \n\t" + "λ: " + generatedKeyPair.getPrivateKey().getLambda() + "\n\t"
                + "μ: " + generatedKeyPair.getPrivateKey().getMu() + "\n");

        int plain = 12345678;
        BigInteger cipherText = homomorphicCipher.encrypt(BigInteger.valueOf(plain));
        System.out.println("Encryption started...");
        System.out.println("Plaintext:"+ plain +" \nCorresponding ciphertext: " + cipherText);

        BigInteger plaintext = homomorphicCipher.decrypt(cipherText);
        System.out.println("\nDecryption started...");
        System.out.println("Ciphertext: " + cipherText + "\nCorresponding plaintext: " + plaintext);


    }
}
