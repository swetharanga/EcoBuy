package org.example.repositories;
//The JpaRepository interface provides built-in methods like save, find and delete a product or also find a product by its username
// UserRepository checks if a username is already taken, for user authentication, user management
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);

}
