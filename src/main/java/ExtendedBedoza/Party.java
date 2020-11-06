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
    boolean[][] circuit;
    private SecureRandom random = new SecureRandom();

    private boolean d;
    private boolean da;
    private boolean db;
    private boolean e;
    private boolean ea;
    private boolean eb;

    private boolean u;
    private boolean v;
    private boolean w;
    boolean[] xs;


    Party(int numberOfLayers, int numberOfWires, int bidPrice, int index) {
        this.numberOfLayers = numberOfLayers;
        this.numberOfWires = numberOfWires;
        this.bidPrice = bidPrice;
        this.index = index;
        this.circuit = new boolean[numberOfLayers][numberOfWires];
    }

    void initInputWires() {
        // TODO: Generalized solution for initialize input wires
        xs = new boolean[3];

        for (int i = 0; i < 3; i++) {
            xs[i] = random.nextBoolean();
        }

        System.out.println("Party " + index + ": " + xs[0]);
        circuit[0][index] = xs[0];
    }

    void setPartyIInputWires(boolean partyIInputWire, int index) {
        circuit[0][index] = partyIInputWire;
    }

    void setTripletFromDealer(boolean u, boolean v, boolean w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    void xor(int layer, int wire) {
        circuit[layer][wire] = circuit[layer - 1][wire] ^ circuit[layer - 1][wire + 1];
    }

    public void xorWithConstant(int layer, int wire, boolean c) {
        circuit[layer][wire] = circuit[layer - 1][wire] ^ c;
    }

    public void not(int layer, int wire) {
        circuit[layer][wire] = !circuit[layer - 1][wire];
    }

    public void andWithConstant(int layer, int wire, boolean c) {
        circuit[layer][wire] = circuit[layer - 1][wire] && c;
    }


    boolean[][] getCircuit() {
        return circuit;
    }

    public void setCircuit(boolean[][] circuit) {
        this.circuit = circuit;
    }

    boolean isD() {
        return d;
    }

    void setD(boolean d) {
        this.d = d;
    }

    boolean isDa() {
        return da;
    }

    void setDa(boolean da) {
        this.da = da;
    }

    boolean isDb() {
        return db;
    }

    void setDb(boolean db) {
        this.db = db;
    }

    boolean isE() {
        return e;
    }

    void setE(boolean e) {
        this.e = e;
    }

    boolean isEa() {
        return ea;
    }

    void setEa(boolean ea) {
        this.ea = ea;
    }

    boolean isEb() {
        return eb;
    }

    void setEb(boolean eb) {
        this.eb = eb;
    }

    public int getNumberOfLayers() {
        return numberOfLayers;
    }

    public int getNumberOfWires() {
        return numberOfWires;
    }

    boolean isU() {
        return u;
    }

    boolean isV() {
        return v;
    }

    boolean isW() {
        return w;
    }

}
