import java.net.*;

public class playwith {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost(); // returns the system details i.e. Inet Address
		System.out.println(address);
		address = InetAddress.getByName("www.facebook.com"); // returns the address of the website
		System.out.println(address);
		address = InetAddress.getByName("www.blackboard.au.dk");
		System.out.println(address);
		InetAddress ia[] = InetAddress.getAllByName("www.google.com");
		for (int i = 0; i < ia.length; i++) {
			System.out.println(ia[i]);

		}
	}
}
