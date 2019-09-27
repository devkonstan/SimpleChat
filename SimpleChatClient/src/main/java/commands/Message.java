package commands;

import lombok.Getter;
import lombok.Setter;
import network.ConsoleInputProvider;

import java.time.LocalTime;

@Getter @Setter
public class Message extends ChatCommand {
    private LocalTime timestamp;
    private String content;
    public static Message createNew(String content) {
        return load(LocalTime.now(), content);
    }

    public static Message load(LocalTime timestamp, String content) {
        Message message = new Message();
        message.timestamp = timestamp;
        message.content = content;
        return message;
    }

    @Override
    public String toString() {
        return "["+timestamp+"]" + " " + content;
    }
}
