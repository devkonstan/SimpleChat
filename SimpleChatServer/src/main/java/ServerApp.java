import network.*;
import utils.ChatInitializer;
import utils.DefaultInitializer;

import java.util.Collection;

public class ServerApp {
    public static void main(String[] args) {
        ChannelRepository channelRepository = new InMemoryChannelRepository();
        ChatInitializer initializer = new DefaultInitializer(channelRepository);
        initializer.initialize();
        Collection<Channel> channel = channelRepository.getAll();
        ChatServer chatServer = new ChatServerImpl(channelRepository);
        chatServer.start(50000);
    }
}
