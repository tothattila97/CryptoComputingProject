import java.io.IOException;
import java.util.Arrays;

public class TestNetworking {

	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {

		Party malaka[] = new Party[10];

		for (int i = 0; i < 10; i++) {
			malaka[i] = new Party(i);
			malaka[i].start();
		}

		for (int i = 0; i < 10; i++) {
			malaka[i].init_ssOut();
		}

		for (int i = 0; i < 10; i++) {
			malaka[i].broadcast(String.valueOf(i));
		}

		for (int i = 0; i < 10; i++) {
			Object[] objectArray = malaka[i].gather();
			System.out.println(
					"$" + i + "$ " + Arrays.toString(Arrays.copyOf(objectArray, objectArray.length, String[].class)));
		}

		System.out.println("ending malaka");
	}
}
