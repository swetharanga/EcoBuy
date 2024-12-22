package org.example.services;

import org.example.models.User;
import org.example.repositories.UserRepository;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    // Need to make sure the password is encrypted so we use BCrypt
    @Override
    public boolean authenticateUser(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));

        if(BCrypt.checkpw(password, userRepository.getPassword()))
        {
            return true;
        }
        else{
            throw new IllegalArgumentException("Invalid password");
        }

    }
    @Override
    public User saveUser(User user) {
        // Hash the password only if it's not already hashed
        if (!BCrypt.checkpw("test", user.getPassword())) { // Arbitrary test to see if password is already hashed
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        }
        return userRepository.save(user);
    }


    @Override
    public User deleteUser(Long Id) {
         userRepository.deleteById(Id);
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
        return userRepository.findByEmail(email);
    }


}
