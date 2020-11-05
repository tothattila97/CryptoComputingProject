package ExtendedBedoza;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args){

        System.out.println("Hello");

        int numberOfParties = new Random().nextInt(10) +2;
        System.out.println("BeDOZa for " + numberOfParties + " parties");

        Dealer dealer = new Dealer();
        dealer.generateTriplets();

        List<Party> partyList = new ArrayList<>();
        setup(numberOfParties, partyList);

        boolean xorResult = partyList.get(0).xs[0];
        for (int i = 1; i < partyList.size(); i++){
            xorResult = xorResult ^ partyList.get(i).xs[0];
        }
        System.out.println("XOR result of parties (counted by sequentially): " + xorResult);

        int layer = 1;
        // Count XOR for layer 1
        for (int i = 0; i < partyList.size(); i+=2){
            Party party = partyList.get(i);
            party.xor(layer, party.index);
            partyList.get(i+1).circuit = party.circuit;
        }

        // Move values to the upper wires
        int wireId = 0;
        for (int i = 0; i < partyList.size(); i+=2){
            Party party = partyList.get(i);
            party.circuit[layer][wireId] = party.circuit[layer][i];
            wireId++;
        }
        layer++;





        System.out.println("End...");
    }

    private static void setup(int numberOfParties, List<Party> partyList) {
        int numberOfLayers = numberOfParties;// % 2 == 0 ? : ;
        for (int i = 0; i < numberOfParties; i++){
            Party tempParty = new Party(numberOfLayers+1, numberOfParties, 99, i);
            partyList.add(tempParty);
            tempParty.initInputWires();
        }

        for (Party temp:
                partyList) {
            for (int i = 0; i < partyList.size(); i++){
                if (temp.index != i)
                    partyList.get(i).setPartyIInputWires(temp.xs[0], temp.index);
            }
        }
    }


    static void andOfTwoWires(Party party1, Party party2, Dealer dealer, int layer, int wire) {
        dealer.generateTriplets();
        party1.setTripletFromDealer(dealer.ua, dealer.va, dealer.wa);
        party2.setTripletFromDealer(dealer.ub, dealer.vb, dealer.wb);

        // Run sub protocol: compute d = x XOR u and e = y XOR v
        party1.setDa(party1.getCircuit()[layer - 1][wire] ^ party1.isU());
        party1.setEa(party1.getCircuit()[layer - 1][wire + 1] ^ party1.isV());
        party2.setDb(party2.getCircuit()[layer - 1][wire] ^ party2.isU());
        party2.setEb(party2.getCircuit()[layer - 1][wire + 1] ^ party2.isV());

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
        party1.circuit[layer][wire] = party1.isW() ^ (party1.isE() & party1.circuit[layer - 1][wire]) ^ (party1.isD() & party1.circuit[layer - 1][wire + 1]) ^ (party1.isE() & party1.isD());
        party2.circuit[layer][wire] = party2.isW() ^ (party2.isE() & party2.circuit[layer - 1][wire]) ^ (party2.isD() & party2.circuit[layer - 1][wire + 1]);  //^ (bob.isE() & bob.isD())
    }
}
