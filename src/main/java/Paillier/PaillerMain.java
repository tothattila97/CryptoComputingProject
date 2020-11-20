package Paillier;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PaillerMain {

    public static void main(String[] args) {

        if (args.length != 0 && args[0].equals("-test")) {
            JUnitCore junit = new JUnitCore();
            junit.addListener(new TextListener(System.out));
            Result run = junit.run(PaillierTest.class);
            resultReport(run);
            return;
        }

        System.out.println("Paillier encryption algorithm... \n");

        // Preprocessing with the Trusted Dealer
        Dealer dealer = new Dealer();
        KeyPair generatedKeyPair = dealer.getKeyPair();

        int numberOfParties = new Random().nextInt(10) + 2;
        List<Party> parties = new LinkedList<>();
        for (int i = 0; i < numberOfParties; i++) {
            Party party = new Party(new KeyPair(null, generatedKeyPair.getPublicKey(), generatedKeyPair.getUpperBound()));
            party.computeEncryptedVote();
            System.out.println("\nParty " + (i + 1) + ":");
            System.out.println("Vote:" + party.getVote() + " \nCorresponding encrypted vote: " + party.getEncryptedVote());
            parties.add(party);
        }

        // Online phase
        for (int i = 0; i < parties.size(); i++) {
            for (Party party : parties) {
                if (parties.get(i) != party)
                    party.addToEncryptedVotes(parties.get(i).getEncryptedVote());
            }
        }

        System.out.println("\nEncrypted products:");
        for (Party party : parties) {
            party.computeEncryptedProduct();
            dealer.addToProductOfEncryptedVotes(party.getEncryptedProduct());
            System.out.println(party.getEncryptedProduct());
        }

        dealer.decryptCipheredVotes();
    }

    private static void resultReport(Result result) {
        System.out.println("Finished. Result: Failures: " +
                result.getFailureCount() + ". Ignored: " +
                result.getIgnoreCount() + ". Tests run: " +
                result.getRunCount() + ". Time: " +
                result.getRunTime() + "ms.");
    }
}
