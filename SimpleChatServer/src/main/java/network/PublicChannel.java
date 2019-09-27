package network;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicChannel extends Channel {
    private List<ChatClient> channelClients = new ArrayList<>();

    public PublicChannel(String name) {
        super(name);
    }

    @Override
    public void join(ChatClient client) {
        channelClients.add(client);
        System.out.println("New client has joined " + name + " channel");
        System.out.println("Clients in channel " + name + ": " + channelClients.size());
    }

    @Override
    public void leave(ChatClient client) {
        channelClients.remove(client);
        System.out.println("Client has left " + name + " channel");
        System.out.println("Clients in channel " + name + ": " + channelClients.size());
    }

    @Override
    public void broadcast(ChatClient sender, String message) {
        List<ChatClient> recipients = channelClients.stream()
                .filter(chatClient -> chatClient != sender) // to all but sender
                .collect(Collectors.toList());

        recipients.forEach(recipient -> recipient.sendMessage(message));
    }
}
