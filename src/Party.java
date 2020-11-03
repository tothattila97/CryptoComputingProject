import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Party extends Thread {
    private static ServerSocket server;
    int id;
    int first;
    int otherbits[] = new int[10];
    int value = 200;

    // socket server port on which it will listen
    Party(int id, int first) throws IOException, ClassNotFoundException {
        server = new ServerSocket(id);
        this.id = id;
        this.first = first;
    }

    @Override
    public void run() {
        try {
            Listen();
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void Listen() throws IOException, ClassNotFoundException {
        System.out.println("I am awake and " + this.id);
        while (true) {
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received: I am server" + this.id + " " + message);
            ois.close();
            socket.close();

            if (message.equalsIgnoreCase("exit"))
                break;
        }
        System.out.println("Shutting down Socket server!!");
        // close the ServerSocket object
        server.close();
    }

    void Send(int id) throws IOException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        socket = new Socket(host.getHostName(), id);
        oos = new ObjectOutputStream(socket.getOutputStream());
        // System.out.println("I will send to "+ id +" and i am "+ this.id);
        oos.writeObject("Malaka hi, I am " + this.id + " i am sending to " + id);
        if (this.id == 9877)
            oos.writeObject("exit");
        oos.close();
        Thread.sleep(100);
    }
}
