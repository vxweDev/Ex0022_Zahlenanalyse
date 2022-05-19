import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public abstract class AbstractHandler implements Runnable{
    protected Socket clientSocket;

    public void setClientSocket(Socket client) throws SocketException {
        this.clientSocket = client;
        clientSocket.setSoTimeout(20000);
    }

    @Override
    public void run() {
        System.out.println("Starte Client/Server Kommunikation");
        runTask();

    }

    public abstract void runTask();

}
