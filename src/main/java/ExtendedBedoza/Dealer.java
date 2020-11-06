package ExtendedBedoza;

import java.security.SecureRandom;

class Dealer {

    // Alice's and Bob's uniformly random triplets
    boolean ua;
    boolean va;
    boolean wa;
    boolean ub;
    boolean vb;
    boolean wb;
    private SecureRandom random = new SecureRandom();

    Dealer() {
    }

    /**
     * Create some uniformly random value but the following is true: wa XOR wb = (ua XOR ub) * (va XOR vb)
     */
    void generateTriplets() {
        ua = random.nextInt(2) != 0;
        va = random.nextInt(2) != 0;
        wa = random.nextInt(2) != 0;
        ub = random.nextInt(2) != 0;
        vb = random.nextInt(2) != 0;
        wb = ((ua ^ ub) & (va ^ vb)) ^ wa;
    }

}
