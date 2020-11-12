package ExtendedBedoza;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.security.SecureRandom;
import java.util.List;

public class Party {

    // Alternative of blood type
    private int bidPrice;
    int index;

    private int numberOfLayers;
    private int numberOfWires;
    int[][] circuit;
    private SecureRandom random = new SecureRandom();

    private int d;
    private int da;
    private int db;
    private int e;
    private int ea;
    private int eb;

    private int u;
    private int v;
    private int w;
    int[] xs;


    Party(int numberOfLayers, int numberOfWires, int bidPrice, int index) {
        this.numberOfLayers = numberOfLayers;
        this.numberOfWires = numberOfWires;
        this.bidPrice = bidPrice;
        this.index = index;
        this.circuit = new int[numberOfLayers][numberOfWires];
    }

    void initInputWires() {
        // TODO: Generalized solution for initialize input wires
        xs = new int[3];

        for (int i = 0; i < 3; i++) {
            xs[i] = random.nextInt(2);
        }

        System.out.println("Party " + index + ": " + xs[0]);
        circuit[0][index] = xs[0];
    }

    void setPartyIInputWires(int partyIInputWire, int index) {
        circuit[0][index] = partyIInputWire;
    }

    /*void setTripletFromDealer(boolean u, boolean v, boolean w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }*/

    void xor(int layer, int wire) {
        circuit[layer][wire] = circuit[layer - 1][wire] ^ circuit[layer - 1][wire + 1];
    }

    public void xorWithConstant(int layer, int wire, int c) {
        circuit[layer][wire] = circuit[layer - 1][wire] ^ c;
    }

    public void not(int layer, int wire) {
        circuit[layer][wire] = ~circuit[layer - 1][wire];
    }

    public void andWithConstant(int layer, int wire, int c) {
        circuit[layer][wire] = circuit[layer - 1][wire] & c;
    }


    // 1-out-of-4 OT protocol to remove the Dealer from BeDOZa
    public int i;
    public int[] messages;

    public void generateIndexForOT(){
        this.u = random.nextInt(2);
        this.v = random.nextInt(2);
        i = 2* u+v;
    }

    public int[] generateMessagesForOT(){
        this.u = random.nextInt(2);
        this.v = random.nextInt(2);
        this.w = random.nextInt(2);

        int M0 = (0 ^ this.u) & (0 ^ this.v) ^ this.w;
        int M1 = (0 ^ this.u) & (1 ^ this.v) ^ this.w;
        int M2 = (1 ^ this.u) & (0 ^ this.v) ^ this.w;
        int M3 = (1 ^ this.u) & (1 ^ this.v) ^ this.w;

        return new int[] {M0, M1, M2, M3};
    }

    public void setWFromMessagesForOT(){
        this.w = messages[i];
    }

    public int[] getMessages() {
        return messages;
    }

    public void setMessages(int[] messages) {
        this.messages = messages;
    }

    int[][] getCircuit() {
        return circuit;
    }

    public void setCircuit(int[][] circuit) {
        this.circuit = circuit;
    }

    int isD() {
        return d;
    }

    void setD(int d) {
        this.d = d;
    }

    int isDa() {
        return da;
    }

    void setDa(int da) {
        this.da = da;
    }

    int isDb() {
        return db;
    }

    void setDb(int db) {
        this.db = db;
    }

    int isE() {
        return e;
    }

    void setE(int e) {
        this.e = e;
    }

    int isEa() {
        return ea;
    }

    void setEa(int ea) {
        this.ea = ea;
    }

    int isEb() {
        return eb;
    }

    void setEb(int eb) {
        this.eb = eb;
    }

    public int getNumberOfLayers() {
        return numberOfLayers;
    }

    public int getNumberOfWires() {
        return numberOfWires;
    }

    int isU() {
        return u;
    }

    int isV() {
        return v;
    }

    int isW() {
        return w;
    }

}
