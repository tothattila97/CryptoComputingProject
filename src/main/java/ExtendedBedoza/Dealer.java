package ExtendedBedoza;

import java.security.SecureRandom;

public class Dealer {

    // Alice's and Bob's uniformly random triplets
    public boolean ua;
    public boolean va;
    public boolean wa;
    public boolean ub;
    public boolean vb;
    public boolean wb;
    private SecureRandom random = new SecureRandom();

    public Dealer(){

    }

    /**
     * Create some uniformly random value but the following is true: wa XOR wb = (ua XOR ub) * (va XOR vb)
     */
    public void generateTriplets() {
        ua = random.nextInt(2) != 0;
        va = random.nextInt(2) != 0;
        wa = random.nextInt(2) != 0;
        ub = random.nextInt(2) != 0;
        vb = random.nextInt(2) != 0;
        wb = ((ua ^ ub) & (va ^ vb)) ^ wa;
    }

}
