import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private final ServerSocket serverSocket;
    private static volatile boolean stopServer;
    private final AbstractHandler handler;

    static {
        stopServer = false;
    }

    public Server(int port, AbstractHandler handler) throws IOException {
        this.serverSocket = new ServerSocket(6000);
        this.handler = handler;
        serverSocket.setSoTimeout(10000);
    }

    public void stopServer() {
        stopServer = true;
    }

    @Override
    public void run() {
        while (!stopServer) {
            try {
                System.out.println("Warten auf Anfrage...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Anfrage erhalten!");

                handler.setClientSocket(clientSocket);

                //Handler handler = new Handler(clientSocket);
                Thread thread = new Thread(handler);
                thread.start();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}