package org.example.controllers;

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
// Handling login and logout and signup processes, authorization, profile management, activity

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;
    private UserLoginRequest loginRequest;



    @PostMapping
    public registerUser<String> registerEntity(@ResponseBody UserService userService){

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
