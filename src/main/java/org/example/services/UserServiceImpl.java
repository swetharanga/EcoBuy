package org.example.services;

import org.example.models.User;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // Need to make sure the password is encrypted so we use BCrypt
    @Override
    public boolean authenticateUser(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return true;
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new IllegalArgumentException("Email not found");
        }
    }
    @Override
    public User saveUser(User user) {
        // Hash the password only if it's not already hashed
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (!BCrypt.checkpw("test", user.getPassword())) { // Arbitrary test to see if password is already hashed
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        }
        return userRepository.save(user);
    }


    @Override
    public User deleteUser(Long Id) {
        if (!userRepository.existsById(Id)) {
            throw new IllegalArgumentException("User with ID " + Id + " does not exist");
        }
         userRepository.deleteById(Id);
        return null;
    }
    @Override
    public ResponseEntity<User> saveAndFlush(User user) {
        userRepository.saveAndFlush(user);
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long Id){
        return userRepository.findAllById(Id);
    }
    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (IncorrectResultSizeDataAccessException e) {
            // Log the exception and handle it appropriately
            // For example, return Optional.empty() or rethrow a custom exception
            return Optional.empty();
        }
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();  // Implementing findAll here
    }

    @Override
    public ResponseEntity<User> save(User user) {
        return null;
    }


}
