package network;

import settings.ChannelSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatClientImpl implements ChatClient {
    private Socket clientSocket;
    private PrintWriter output; // send msg
    private BufferedReader input; // receive msg
    private Thread readerThread;

    private List<DisconnectObserver> disconnectObservers = new ArrayList<>();

    private String currentChannelName = ChannelSettings.DEFAULT_CHANNEL_NAME;
    private final Channel generalChannel = new InMemoryChannelRepository()
            .findByName(ChannelSettings.DEFAULT_CHANNEL_NAME).get();

    private ChannelRepository channelRepository;

    public ChatClientImpl(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())
            );
        } catch (IOException e) {
            throw new IllegalStateException("Error initializing client");
        }
        startReading();
    }

    private void startReading() {
        readerThread = new Thread(() -> {
            while (isOnline()) {
                readMessage();
            }
            notifyDisconnected(this); // if is not online
        });
        readerThread.start();
    }

    private void readMessage() {
        try {
            String msg = input.readLine();

            if (msg.contains("exit")) {
                System.out.println("Leo why?");
                disconnect();
            } else {
                System.out.println(msg);
                generalChannel.broadcast(this, msg);
            }
        } catch (IOException e) {
            disconnect();
        }
    }

    @Override
    public void sendMessage(String message) {
        if (isOnline()) {
            output.println(message);
        }
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
    public boolean isOnline() {

        return clientSocket != null && !clientSocket.isClosed(); // analogically to the ServerImpl
    }

    @Override
    public void changeCurrentChannel(String channelName) {
        if (!channelName.equals(currentChannelName)) {
            channelRepository.findByName(channelName)
                    .orElseThrow(() -> new IllegalStateException("Channel does not exist"));
            currentChannelName = channelName;
        }
    }

    @Override
    public String getCurrentChannelName() {
        return generalChannel.getName();
    }

    @Override
    public void subscribe(DisconnectObserver observer) {
        disconnectObservers.add(observer);
    }

    @Override
    public void unsubscribe(DisconnectObserver observer) {
        disconnectObservers.remove(observer); // opposite to the above
    }

    @Override
    public void notifyDisconnected(ChatClient client) {
        for (DisconnectObserver observer : disconnectObservers) {
            observer.clientDisconnected(client);
        }
    }
}
