package ExtendedBedoza;

import java.util.Random;

public class Main {

    public static void main(String[] args){

        System.out.println("Hello");

        int numberOfParties = new Random().nextInt(10) +2;
        System.out.println("BeDOZa for " + numberOfParties + " parties");

        Dealer dealer = new Dealer();
        dealer.generateTriplets();



    }


    /*static void andOfTwoWires(Party party1, Party party2, Dealer dealer, int layer, int wire) {
        dealer.generateTriplets();
        party1.setTripletFromDealer(dealer.ua, dealer.va, dealer.wa);
        party2.setTripletFromDealer(dealer.ub, dealer.vb, dealer.wb);

        // Run sub protocol: compute d = x XOR u and e = y XOR v
        party1.setDa(party1.getCircuit()[layer - 1][wire] ^ party1.isUa());
        party1.setEa(party1.getCircuit()[layer - 1][wire + 1] ^ party1.isVa());
        party2.setDb(party2.getCircuit()[layer - 1][wire] ^ party2.isUb());
        party2.setEb(party2.getCircuit()[layer - 1][wire + 1] ^ party2.isVb());

        // Run sub protocol: open d and e values
        party1.setDb(party2.isDb());
        party1.setEb(party2.isEb());
        party2.setDa(party1.isDa());
        party2.setEa(party1.isEa());

        party1.setD(party1.isDa() ^ party1.isDb());
        party1.setE(party1.isEa() ^ party1.isEb());
        party2.setD(party2.isDa() ^ party2.isDb());
        party2.setE(party2.isEa() ^ party2.isEb());

        //Run sub protocol: calculate z value : z = [w] XOR e AND [x] XOR d AND [y] XOR e and d
        party1.circuit[layer][wire] = party1.isWa() ^ (party1.isE() & party1.circuit[layer - 1][wire]) ^ (party1.isD() & party1.circuit[layer - 1][wire + 1]) ^ (party1.isE() & party1.isD());
        party2.circuit[layer][wire] = party2.isWb() ^ (party2.isE() & party2.circuit[layer - 1][wire]) ^ (party2.isD() & party2.circuit[layer - 1][wire + 1]);  //^ (bob.isE() & bob.isD())
    }*/
}
