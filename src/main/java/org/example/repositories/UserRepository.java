package org.example.repositories;

import org.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//The JpaRepository interface provides built-in methods like save, find and delete a product or also find a product by its username
// UserRepository checks if a username is already taken, for user authentication, user management


public interface UserRepository extends JpaRepository<User, Long> {
   // List<User> findByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<User> findAllById(Long id);
}
