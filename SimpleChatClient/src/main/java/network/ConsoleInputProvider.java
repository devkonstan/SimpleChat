package network;

import com.beust.jcommander.JCommander;
import commands.ChatCommand;
import commands.ChatCommandFactory;
import commands.Message;
import commands.SupportedCommands;
import exceptions.EmptyMessageException;
import lombok.Getter;
import lombok.Setter;
import settings.UserSettings;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

@Getter @Setter
public class ConsoleInputProvider implements UserInputProvider {
    private Scanner scanner = new Scanner(System.in);
    private String username;
    private LocalDateTime updatedAt;
    private ChatCommandFactory chatCommandFactory = new ChatCommandFactory();

    public String getUsername() {
        return username;
    }

    public ConsoleInputProvider() {
        String tempUsername;
        // 1st step of validation
        while (true) {
            System.out.print("Enter username: ");
            tempUsername = scanner.nextLine();

            if (isUsernameValid(tempUsername)) {
                break;
            }
        }
        username = tempUsername;
    }

//    @Override
//    public String getUserInput() {
//        String input = scanner.nextLine();
//
//        if (input.trim().isEmpty()) {
//            throw new EmptyMessageException();
//        } else
//            return String.format("%s: %s", username, input);
//
//    }

    @Override
    public ChatCommand getUserInput() {
        String input = scanner.nextLine();

        if (input.trim().isEmpty()) {
            throw new EmptyMessageException();
        } else
            System.out.printf("%s: %s\n", username, input);

        if(isCommand(input)) {
            String commandString = input.contains(" ")
                    ? input.split(" ")[0] : input;

            SupportedCommands commandType = SupportedCommands.fromString(commandString);
            ChatCommand command =  chatCommandFactory.create(commandType);

            if(input.contains(" ")) {
                String[] arguments = input.split(" ");
                JCommander.newBuilder()
                        .addObject(command)
                        .build()
                        .parse(Arrays.copyOfRange(arguments, 1, arguments.length));
            }

            return command;
        }
        return Message.createNew(input.toString());
    }

    // validation method
    private static boolean isUsernameValid(String username) {
        return !username.isEmpty() &&
                !username.equals("exit") &&
                username.length() >= UserSettings.MIN_USERNAME_LENGTH &&
                username.length() < UserSettings.MAX_USERNAME_LENGTH &&
                username.matches(UserSettings.USERNAME_PATTERN);
    }

    public void changeUsername(String username) {
        if(!isUsernameValid(username)) {
            throw new IllegalArgumentException("Valid username should have " +
                    "a length between " + UserSettings.MIN_USERNAME_LENGTH +
                    " and " + UserSettings.MAX_USERNAME_LENGTH );
        }
        this.username = username;
        update();
    }

    public void update() {

        updatedAt = LocalDateTime.now();
    }

    private static boolean isCommand(String input) {
        String trimmedInput = input.trim();
        String regex = !trimmedInput.contains(" ") ? "^\\/[a-z]+$" : "^\\/[a-z]+\\s+\\S+";
        return trimmedInput.matches(regex);
    }

}
