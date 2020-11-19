package Paillier;

import java.math.BigInteger;
import java.util.Random;

public class HomomorphicCipher {

    private final KeyPair keyPair;

    public HomomorphicCipher(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public final BigInteger encrypt(BigInteger m) {

        PublicKey publicKey = keyPair.getPublicKey();
        int bits = publicKey.getBits();
        BigInteger n = publicKey.getN();
        BigInteger nSquared = publicKey.getNSquared();
        BigInteger g = publicKey.getG();

        BigInteger r;
        r = new BigInteger(bits, new Random());
        while (r.compareTo(n) >= 0) {
            r = new BigInteger(bits, new Random());
        }

        BigInteger result = g.modPow(m, nSquared);
        BigInteger x = r.modPow(n, nSquared);

        result = result.multiply(x);
        result = result.mod(nSquared);

        return result;
    }

    public final BigInteger decrypt(BigInteger c) {

        PublicKey publicKey = keyPair.getPublicKey();
        PrivateKey privateKey = keyPair.getPrivateKey();
        BigInteger upperBound = keyPair.getUpperBound();

        BigInteger n = publicKey.getN();
        BigInteger nSquare = publicKey.getNSquared();
        BigInteger lambda = privateKey.getLambda();

        BigInteger u = privateKey.getMu();

        BigInteger p = c.modPow(lambda, nSquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);

        if (upperBound != null && p.compareTo(upperBound) > 0) {
            p = p.subtract(n);
        }

        return p;
    }
}
