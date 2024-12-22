package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecobuy.model.UserLoginRequest;
import com.example.ecobuy.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// Handling login and logout and signup processes, authorization, profile management, activity

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public saveUser(){

    }

    @GetMapping
    public ResponseEntity<Optional> findUserById(@PathVariable Long Id){
        return userService.getUserById(Id).map()
    }
    @DeleteMapping
    public  ResponseEntity<Void> deleteUser(@PathVariable Long Id){
        userService.getUserById(Id)
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);

    }

    public findByEmail(){

    }

    @PostMapping
    public ResponseEntity<String> responseEntity(@ResponseBody UserLoginRequest loginRequest) // login request will contain the login credentials entered by the user in json format
    {
        this.loginRequest = loginRequest;
        String token  = authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (token!= null) {
            return ResponseEntity.ok(new LoginResponse("Login successful", token));
        }
        else{
            return ResponseEntity.status(401).body("Invalid login credentials, try again");
        }

    }

}
