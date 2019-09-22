package network;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryChannelRepository implements ChannelRepository {
    private static Set<Channel> channels = new HashSet<>();

    @Override
    public void add(Channel channel) {
        channels.add(channel);
    }

    @Override
    public Optional<Channel> findByName(String name) {
        return channels.stream()
                .filter(channel -> channel.getName().equals(name))
                .findAny();
    }

    @Override
    public Collection<Channel> getAll() {
        return channels;
    }
}
