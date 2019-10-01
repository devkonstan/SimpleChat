package network;

import commands.ChatCommand;
import commands.Message;
import exceptions.EmptyMessageException;
import lombok.Getter;
import lombok.Setter;
import settings.UserSettings;

import java.time.LocalDateTime;
import java.util.Scanner;

@Getter
@Setter
public class ConsoleInputProvider implements UserInputProvider {
    private Scanner scanner = new Scanner(System.in);
    private String username;
    private LocalDateTime updatedAt;


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

    @Override
    public ChatCommand getUserInput() {
        String input = scanner.nextLine();

        if (input.trim().isEmpty()) {
            throw new EmptyMessageException();
        } else
            System.out.println(username + ": " + input);

        return Message.createNew(username + ": " + input);
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
        if (!isUsernameValid(username)) {
            throw new IllegalArgumentException("Valid username should have " +
                    "a length between " + UserSettings.MIN_USERNAME_LENGTH +
                    " and " + UserSettings.MAX_USERNAME_LENGTH);
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
