package ExtendedBedoza;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.security.SecureRandom;
import java.util.List;

public class Party {

    // Alternative of blood type
    int bidPrice;
    int index;

    int numberOfLayers;
    int numberOfWires;
    public boolean[][] circuit;
    SecureRandom random = new SecureRandom();

    private boolean d;
    private boolean da;
    private boolean db;
    private boolean e;
    private boolean ea;
    private boolean eb;

    private boolean u;
    private boolean v;
    private boolean w;
    public boolean[] xs;


    Party(int numberOfLayers, int numberOfWires, int bidPrice, int index) {
        this.numberOfLayers = numberOfLayers;
        this.numberOfWires = numberOfWires;
        this.bidPrice = bidPrice;
        this.index = index;
        this.circuit = new boolean[numberOfLayers][numberOfWires];
    }

    public void initInputWires(){
        // TODO: Generalized solution for initialize input wires
        xs = new boolean[3];

        for (int i = 0; i < 3; i++){
            xs[i] = random.nextBoolean();
        }

        System.out.println("Party " + index + ": " + xs[0]);
        circuit[0][index] = xs[0];
    }

    public void setPartyIInputWires(boolean partyIInputWire, int index){
        circuit[0][index] = partyIInputWire;
    }

    public void setTripletFromDealer(boolean u, boolean v, boolean w){
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public void xor(int layer, int wire) {
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


    public boolean[][] getCircuit() {
        return circuit;
    }

    public void setCircuit(boolean[][] circuit) {
        this.circuit = circuit;
    }

    public boolean isD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public boolean isDa() {
        return da;
    }

    public void setDa(boolean da) {
        this.da = da;
    }

    public boolean isDb() {
        return db;
    }

    public void setDb(boolean db) {
        this.db = db;
    }

    public boolean isE() {
        return e;
    }

    public void setE(boolean e) {
        this.e = e;
    }

    public boolean isEa() {
        return ea;
    }

    public void setEa(boolean ea) {
        this.ea = ea;
    }

    public boolean isEb() {
        return eb;
    }

    public void setEb(boolean eb) {
        this.eb = eb;
    }
    public int getNumberOfLayers() {
        return numberOfLayers;
    }

    public int getNumberOfWires() {
        return numberOfWires;
    }

    public boolean isU() {
        return u;
    }

    public boolean isV() {
        return v;
    }

    public boolean isW() {
        return w;
    }

}
