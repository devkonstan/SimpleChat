package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientImpl implements ChatClient {
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;
    private Thread readerThread;


    private void readMessage() {
        try {
            String msg = input.readLine();

            if (msg == null) {
                disconnect();
                return;
            }

            System.out.println(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReading() {
        readerThread = new Thread(() -> {
            while (isOnline()) {
                readMessage();
            }
            System.out.println("Lost connection with the server.");
            disconnect();
            System.exit(0);
        });
        readerThread.start();
    }

    @Override
    public void connect(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException("Could not connect to the server at: " + ip + ":" + port);
        }
        startReading();
    }

    @Override
    public void disconnect() {
        if (isOnline()) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clientSocket = null; // gc
        }
    }

    @Override
    public void sendMessage(String message) {
        if (isOnline()) {
            output.println(message);
        }
    }

    @Override
    public boolean isOnline() {
        return clientSocket != null && !clientSocket.isClosed(); // analogically to the ServerImpl
    }
}
