import commands.ChatCommand;
import exceptions.EmptyMessageException;
import network.ChatClient;
import network.ChatClientImpl;
import network.ConsoleInputProvider;
import network.UserInputProvider;

public class ClientApp {
    public static void main(String[] args) {

        ChatClient client = new ChatClientImpl();
        client.connect("127.0.0.1", 50000);

        UserInputProvider inputProvider = new ConsoleInputProvider();

        while (client.isOnline()) {
            try {
                ChatCommand userInput = inputProvider.getUserInput();
                client.sendMessage(String.valueOf(userInput));
            } catch (EmptyMessageException ex) {
                System.out.println(ex.getMessage());
            }
        }
//        System.out.println("My time is over here!");
    }
}
