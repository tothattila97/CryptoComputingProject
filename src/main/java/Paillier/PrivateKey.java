package Paillier;

import java.math.BigInteger;

public class PrivateKey {

    private final BigInteger lambda;
    private final BigInteger mu;

    PrivateKey(BigInteger lambda, BigInteger mu) {
        this.lambda = lambda;
        this.mu = mu;
    }

    public BigInteger getLambda() {
        return lambda;
    }

    public BigInteger getMu() {
        return mu;
    }
}
