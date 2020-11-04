import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Party extends Thread {
    private static int port = 9876;
    private static int clients = 10;
    private ServerSocket server;
    private Socket ssIn[] = new Socket[clients];
    private Socket ssOut[] = new Socket[clients];
    private ObjectInputStream ins[] = new ObjectInputStream[clients];
    private ObjectOutputStream outs[] = new ObjectOutputStream[clients];

    private int id;
    private Object value;

    Party(int id) throws IOException {
        this.server = new ServerSocket(port + id);
        this.id = id;
        this.value = "malaka" + id;
    }

    @Override
    public void run() {
        try {
            init_ssIn();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void init_ssIn() throws IOException {
        for (int i = 0; i < clients; i++) {
            if (i == id)
                continue;
            System.out.println("#" + this.id + " waiting for connection");
            ssIn[i] = this.server.accept();
            ins[i] = new ObjectInputStream(ssIn[i].getInputStream());
        }
    }

    public void init_ssOut() throws UnknownHostException, IOException {
        for (int i = 0; i < clients; i++) {
            if (i == id)
                continue;
            System.out.println("#" + this.id + " connecting to " + i);
            ssOut[i] = new Socket(InetAddress.getLocalHost().getHostName(), port + i);
            outs[i] = new ObjectOutputStream(ssOut[i].getOutputStream());
        }
    }

    public void broadcast(Object obj) throws IOException {
        for (int i = 0; i < clients; i++) {
            if (i == id)
                continue;
            outs[i].writeObject(obj);
        }
    }

    public Object[] gather() throws ClassNotFoundException, IOException {
        Object[] objs = new Object[clients];
        objs[id] = this.value;

        for (int i = 0; i < clients; i++) {
            if (i == id)
                continue;
            objs[i] = ins[i].readObject();
        }

        return objs;
    }

    // void Listen() throws IOException, ClassNotFoundException,
    // InterruptedException {
    // System.out.println("#" + this.id + ": awake");

    // boolean alive = true;
    // while (alive) {
    // System.out.println("#" + this.id + "waiting for connection");
    // Socket socket = this.server.accept();
    // ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    // ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

    // String code = (String) ois.readObject();
    // System.out.println("#" + this.id + ":{" + code + "}");
    // // Thread.sleep(100);
    // switch (code) {
    // case "00":
    // break;
    // case "99":
    // alive = false;
    // break;
    // case "01":
    // System.out.println("#" + this.id + ":{" + code + "}");
    // break;
    // default:

    // }
    // // Thread.sleep(100);
    // System.out.println("listen-write-before");
    // // oos.writeBoolean(alive);
    // System.out.println("listen-write");

    // // oos.close();
    // // ois.close();
    // // socket.close();
    // }
    // System.out.println("#" + this.id + ": dead");
    // this.server.close();
    // }

    // boolean Send(Object obj, int targetId) throws IOException,
    // InterruptedException, ClassNotFoundException {
    // Socket socket = new Socket(InetAddress.getLocalHost().getHostName(),
    // targetId);
    // ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    // ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

    // System.out.println("send-write-before");
    // oos.writeObject(obj);
    // // Thread.sleep(100);
    // System.out.println("send-read-before");
    // // boolean response = ois.readBoolean();
    // System.out.println("send-read");

    // oos.close();
    // ois.close();
    // socket.close();
    // return true;
    // // return response;
    // }
}
