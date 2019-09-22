package trialClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SampleClient {
    private Socket clientSocket;
    private PrintWriter output;
    private static final String EXIT_KEYWORD = "exit";

    public void connectToServer(String ipAddress, int port) throws IOException {
        clientSocket = new Socket(ipAddress, port);
        output = new PrintWriter(clientSocket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String userInput = scanner.next();
            if(userInput.toLowerCase().equals(EXIT_KEYWORD)) {
                break;
            }
            output.println(userInput);
        }
    }
}
