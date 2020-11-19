package Paillier;

import java.math.BigInteger;

public class BigIntegerHelper {

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        BigInteger result;
        BigInteger gcd = a.gcd(b);

        result = a.abs().divide(gcd);
        result = result.multiply(b.abs());

        return result;

    }
}
