package network;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public abstract class Channel {
    protected String name;

    public Channel(String name) {
        this.name = name;
    }

    public abstract void join(ChatClient client);
    public abstract void leave(ChatClient client);
    public abstract void broadcast(ChatClient sender, String message);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;
        Channel channel = (Channel) o;
        return name.equals(channel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
