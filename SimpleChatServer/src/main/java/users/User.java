package users;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class User {
    public static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 20;

    private UUID id;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String username, String password) {
        id = UUID.randomUUID();    // factory method
        this.username = username;
        this.password = password;
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    public void changeUsername(String username) {
        if(!isUsernameValid(username)) {
            throw new IllegalArgumentException("Valid username should have " +
                    "a length between " + MIN_LENGTH +
                    " and " + MAX_LENGTH);
        }
        this.username = username;
        update();
    }

    public static boolean isUsernameValid(String username) {
        return username != null && username.length() > MIN_LENGTH
                && username.length() < MAX_LENGTH;

    }

    public void update() {

        updatedAt = LocalDateTime.now();
    }
}
