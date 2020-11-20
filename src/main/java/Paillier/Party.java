package Paillier;

import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Party {

    private final int vote;
    private BigInteger encryptedVote;
    private final KeyPair keyPair;
    private List<BigInteger> othersEncryptedVote;
    private BigInteger encryptedProduct;


    Party(KeyPair keyPair) {
        vote = new Random().nextInt(2);
        this.keyPair = keyPair;
        this.othersEncryptedVote = new LinkedList<>();
    }

    public void computeEncryptedVote() {
        HomomorphicCipher cipher = new HomomorphicCipher(this.keyPair);
        this.encryptedVote = cipher.encrypt(BigInteger.valueOf(vote));
    }

    public void addToEncryptedVotes(BigInteger encryptedVote) {
        othersEncryptedVote.add(encryptedVote);
    }

    public void computeEncryptedProduct() {
        encryptedProduct = encryptedVote;
        for (BigInteger bigInteger : othersEncryptedVote) {
            encryptedProduct = encryptedProduct.multiply(bigInteger).mod(keyPair.getPublicKey().getNSquared());
        }
    }

    public BigInteger getEncryptedProduct() {
        return encryptedProduct;
    }

    public BigInteger getEncryptedVote() {
        return encryptedVote;
    }

    public int getVote() {
        return vote;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

}
