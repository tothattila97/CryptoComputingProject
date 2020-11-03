import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class implements java socket client
 * 
 * @author pankaj
 *
 */
public class Client {
    static int id = 1;
    static int secret = 548509543;

    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        // get the localhost IP address, if server is running on some other IP, you need
        // to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        for (int i = 0; i < 5; i++) {
            // establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            // write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if (i == 4)
                oos.writeObject("exit");
            else if (i == 0) {
                oos.writeObject("Hello My id is" + Client.id);
            } else if (i == 1) {
                oos.writeObject("Exchange codes?" + Client.secret);
            } else if (i == 2) {
                oos.writeObject("Anything Else? Coffee?");
            } else {
                oos.writeObject("cool man" + i);
            }
            // read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            // close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}
