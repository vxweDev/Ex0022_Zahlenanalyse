import java.io.IOException;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) throws IOException {
        NumbersAnalysisHandler handler = new NumbersAnalysisHandler();
        Server server = new Server(6000, handler);


        Thread thread = new Thread(server);
        thread.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter stoppt den Server!");
        scanner.next();

        server.stopServer();
    }
}
