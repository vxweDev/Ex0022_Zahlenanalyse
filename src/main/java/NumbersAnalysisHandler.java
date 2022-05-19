import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class NumbersAnalysisHandler extends AbstractHandler{

    @Override
    public void runTask() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line = bufferedReader.readLine();

            System.out.println(line);

            String[] tokens = line.split(";");
            int[] numbers = new int[tokens.length];

            for(int i=0; i<tokens.length; i++){
                numbers[i] = Integer.parseInt(tokens[i]);
            }
            // Arithmetic Mean
            double sum=0;
            for (int number : numbers)
                sum += number;
            double avg = sum/numbers.length;

            // Standard Deviation
            sum = 0;
            for (int number : numbers)
                sum += Math.pow((number - avg), 2);
            double std = Math.sqrt(sum / (numbers.length - 1));

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            bufferedWriter.write(avg + ";" + std);
            bufferedWriter.flush();

            bufferedWriter.close();
            bufferedReader.close();
        }catch (IOException e){
            System.err.println(e);
        }
    }
}
