package Paillier;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class KeyGenerator {

    private int bits = 1024;
    private int certainty = 0;
    private Random rng;
    private BigInteger upperBound;

    public KeyGenerator bits(int bits) {
        this.bits = bits;
        return this;
    }

    public KeyGenerator certainty(int certainty) {
        this.certainty = certainty;
        return this;
    }

    public KeyGenerator randomNumberGenerator(Random rng) {
        this.rng = rng;
        return this;
    }

    public KeyGenerator upperBound(BigInteger b) {
        this.upperBound = b;
        return this;
    }

    public KeyPair generateKeyPair() {
        if (rng == null) {
            rng = new SecureRandom();
        }

        BigInteger p, q;
        int length = bits / 2;
        if (certainty > 0) {
            p = new BigInteger(length, certainty, rng);
            q = new BigInteger(length, certainty, rng);
        } else {
            p = BigInteger.probablePrime(length, rng);
            q = BigInteger.probablePrime(length, rng);
        }

        BigInteger n = p.multiply(q);
        BigInteger nSquared = n.multiply(n);

        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        BigInteger qMinusOne = q.subtract(BigInteger.ONE);

        BigInteger lambda = BigIntegerHelper.lcm(pMinusOne, qMinusOne);

        BigInteger g;
        BigInteger helper;

        g = new BigInteger(bits, rng);
        helper = calculateL(g.modPow(lambda, nSquared), n);

        while (!helper.gcd(n).equals(BigInteger.ONE)) {
            g = new BigInteger(bits, rng);
            helper = calculateL(g.modPow(lambda, nSquared), n);
        }

        PublicKey publicKey = new PublicKey(n, nSquared, g, bits);
        PrivateKey privateKey = new PrivateKey(lambda, helper.modInverse(n));

        return new KeyPair(privateKey, publicKey, upperBound);

    }

    private BigInteger calculateL(BigInteger u, BigInteger n) {
        BigInteger result = u.subtract(BigInteger.ONE);
        result = result.divide(n);
        return result;
    }

}
