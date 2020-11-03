import java.io.IOException;

public class TestNetworking {

	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
		int port = 9876;
		Party malaka[] = new Party[11];
		for (int i = 0; i < 11; i++) {
			malaka[i] = new Party(9876 + i, 0);
			malaka[i].start();
		}
		int counter = 0;
		for (int i = 10; i > 0; i--) {
			malaka[i].Send(9876);
			counter++;
		}
		for (int i = 1; i < 11; i++) {
			malaka[0].Send(9876 + i);
		}

	}
}
