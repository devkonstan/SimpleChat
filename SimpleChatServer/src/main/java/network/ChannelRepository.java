package network;

import java.util.Collection;
import java.util.Optional;

public interface ChannelRepository {
    void add(Channel channel);
    Optional<Channel> findByName(String name);
    Collection<Channel> getAll();
}
