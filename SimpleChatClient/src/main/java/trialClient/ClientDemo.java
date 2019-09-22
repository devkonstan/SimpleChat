package trialClient;

import java.io.IOException;
import java.net.ConnectException;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        SampleClient client = new SampleClient();

        try {
            client.connectToServer("127.0.0.1", 50000); //mozna wpisac "localhost"
        } catch (ConnectException e) {
            System.out.println("Cannot connect :(");
        }
    }
}
