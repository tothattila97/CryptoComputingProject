package Paillier;

import java.math.BigInteger;

public class KeyPair {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final BigInteger upperBound;

    KeyPair(PrivateKey privateKey, PublicKey publicKey, BigInteger upperBound) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.upperBound = upperBound;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public BigInteger getUpperBound() {
        return upperBound;
    }
}
