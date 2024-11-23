package org.example.services;
import org.example.models.User;

import java.util.List;

//Abstract the operations related to user authentication, registration, and profile management.

public interface UserService {
    boolean authenticateUser(String email, String password);

    User saveUser(User user);

    User deleteUser(Long Id);

    List<User> getAllUsers();

    Optional<User> findByEmail(String email);

}
