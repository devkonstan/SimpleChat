package network;

import settings.ChannelSettings;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl implements ChatServer {
    private ServerSocket serverSocket;
    private List<ChatClient> onlineUsers = new ArrayList<>();
    private Thread acceptingThread;

    private final ChannelRepository channelRepository;

    public ChatServerImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public void start(int port) {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Server is running on port %d...\n", port);

        acceptingThread = new Thread(this::startAcceptingClients);
        acceptingThread.start();
    }

    private void startAcceptingClients() {
        while (isOnline()) {
            try {
                Socket clientSocket = serverSocket.accept();
                ChatClient client = new ChatClientImpl(clientSocket);
                client.subscribe(this);
                onlineUsers.add(client);
                channelRepository.findByName(ChannelSettings.DEFAULT_CHANNEL_NAME)
                        .ifPresent(channel -> channel.join(client));
                System.out.println("New client has joined. Online users: " + onlineUsers.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutdown() {
        if (isOnline()) {

            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isOnline() {
        return serverSocket != null && !serverSocket.isClosed(); //is not null && is open
    }

    @Override
    public void clientDisconnected(ChatClient client) {
        onlineUsers.remove(client);
        System.out.println("Client left the channel. Clients online: " + onlineUsers.size());
        channelRepository.findByName(ChannelSettings.DEFAULT_CHANNEL_NAME)
                .ifPresent(channel -> channel.leave(client));
    }


}
