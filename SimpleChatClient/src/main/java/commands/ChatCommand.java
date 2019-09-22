package commands;

import lombok.Getter;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Getter
public abstract class ChatCommand {
    protected String sender;

    protected ChatCommand() {
        try {
            sender = Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new IllegalStateException("Could not create a command. ", e);
        }
    }
}
