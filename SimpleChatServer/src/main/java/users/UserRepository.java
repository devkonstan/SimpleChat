package users;

import java.util.Optional;

public interface UserRepository {
    void add(User user);
    Optional<User> find(String username); // hides null
}