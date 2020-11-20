package Paillier;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Paillier encryption algorithm... \n");

        KeyPair generatedKeyPair = new KeyGenerator().generateKeyPair();
        HomomorphicCipher homomorphicCipher = new HomomorphicCipher(generatedKeyPair);

        int numberOfParties = new Random().nextInt(10) + 2;
        List<Party> parties = new LinkedList<>();
        for (int i = 0; i < numberOfParties; i++) {
            Party party = new Party(new KeyPair(null, generatedKeyPair.getPublicKey(), generatedKeyPair.getUpperBound()));
            party.computeEncryptedVote();
            System.out.println("\nParty " + (i + 1) + ":");
            System.out.println("Vote:" + party.getVote() + " \nCorresponding encrypted vote: " + party.getEncryptedVote());
            parties.add(party);
        }

        for (int i = 0; i < parties.size(); i++) {
            for (Party party : parties) {
                if (parties.get(i) != party)
                    party.addToEncryptedVotes(parties.get(i).getEncryptedVote());
            }
        }

        System.out.println("\nEncrypted products:");
        for (Party party : parties) {
            party.computeEncryptedProduct();
            System.out.println(party.getEncryptedProduct());
        }

        System.out.println("\nDecrypted products:");
        for (Party party : parties) {
            System.out.println(homomorphicCipher.decrypt(party.getEncryptedProduct()));
        }

        /*System.out.println("Public key: \n\t" + "g: " + generatedKeyPair.getPublicKey().getG() + "\n\t"
                + "n: " + generatedKeyPair.getPublicKey().getN() + "\n");
        System.out.println("Private key: \n\t" + "λ: " + generatedKeyPair.getPrivateKey().getLambda() + "\n\t"
                + "μ: " + generatedKeyPair.getPrivateKey().getMu() + "\n");

        int plain = 12345678;
        BigInteger cipherText = homomorphicCipher.encrypt(BigInteger.valueOf(plain));
        System.out.println("Encryption started...");
        System.out.println("Plaintext:" + plain + " \nCorresponding ciphertext: " + cipherText);

        BigInteger plaintext = homomorphicCipher.decrypt(cipherText);
        System.out.println("\nDecryption started...");
        System.out.println("Ciphertext: " + cipherText + "\nCorresponding plaintext: " + plaintext);*/


    }
}
