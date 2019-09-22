package users;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, String password) {
        Optional<User> foundUser = userRepository.find(username);
        if(foundUser.isPresent()) {
            throw new IllegalStateException("Users.User with " + username + " already exists!");
        }

        if(!User.isUsernameValid(username)) {
            throw new IllegalArgumentException("Valid username should have " +
                    "a length between " + User.MIN_LENGTH +
                    " and " + User.MAX_LENGTH);
        }

        User user = new User(username, password);
        userRepository.add(user);
    }
}