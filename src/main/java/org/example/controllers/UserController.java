package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
// Handling login and logout and signup processes, authorization, profile management, activity

@RestController
@RequestMapping(value ="/api/users")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")

public  class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        return userService.findByEmail(loginRequest.getEmail())
                .map(user -> {
                    if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                        return ResponseEntity.ok(user);
                    }
                    return ResponseEntity.badRequest().body("Invalid credentials");
                })
                .orElse(ResponseEntity.badRequest().body("User not found"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
/*
 Mapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return userService.saveAndFlush(user);

    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return userService.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.getUserById(id).map(user -> {
            userService.deleteUser(id);  // Deletes user based on ID
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }).orElse(ResponseEntity.notFound().build());  // HTTP 404 Not Found if user doesn't exist
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email){
        return userService.findByEmail(email).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

*/