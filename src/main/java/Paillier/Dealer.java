package Paillier;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Dealer {

    private final KeyPair keyPair;
    private final HomomorphicCipher homomorphicCipher;
    private final List<BigInteger> productOfEncryptedVotes;
    private BigInteger result;

    Dealer() {
        keyPair = new KeyGenerator().generateKeyPair();
        homomorphicCipher = new HomomorphicCipher(keyPair);
        productOfEncryptedVotes = new LinkedList<>();
    }

    public void decryptCipheredVotes() {
        System.out.println("\nDecrypted products:");
        result = homomorphicCipher.decrypt(productOfEncryptedVotes.get(0));
        System.out.println(result);
        for (int i = 1; i < productOfEncryptedVotes.size(); i++) {
            BigInteger temp = homomorphicCipher.decrypt(productOfEncryptedVotes.get(i));
            if (result.equals(temp)) {
                System.out.println(result);
            }
            else{
                result = null;
                System.out.println("Abort... decryption is unsuccessful!");
            }
        }
    }

    public void addToProductOfEncryptedVotes(BigInteger productOfEncryptedVote) {
        this.productOfEncryptedVotes.add(productOfEncryptedVote);
    }

    public BigInteger getResult() {
        return result;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public List<BigInteger> getProductOfEncryptedVotes() {
        return productOfEncryptedVotes;
    }
}
