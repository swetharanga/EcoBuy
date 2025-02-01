package org.example.controllers;

import org.example.security.JwtUtil;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
public class AuthController {

/*
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    
@PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
    // Check if email already exists
    if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
        return ResponseEntity.badRequest().body("Email already registered");
    }
    User user = new User();
    user.setName(registerRequest.getName());
    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    user.setEmail(registerRequest.getEmail());
    userService.saveUser(user);

    //Generate token
    String token = jwtUtil.generateToken(user.getEmail());

    // Map the token and secret key generated
    Map<String, Object> response = new HashMap<>();

    response.put("token", token);
    response.put("user", user);

    return ResponseEntity.ok(response);
}

@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
    Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());

    // Check if user exists
    if (userOptional.isEmpty()) {
        return ResponseEntity.badRequest().body("Invalid email or password");
    }

    // Extract the user object from Optional
    User user = userOptional.get();

    // Validate the password
    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        return ResponseEntity.badRequest().body("Invalid email or password");
    }

    // Generate a JWT token for the user
    String token = jwtUtil.generateToken(user.getEmail());

    // Create a response with token and user details
    Map<String, Object> response = new HashMap<>();
    response.put("token", token);
    response.put("user", user);

    return ResponseEntity.ok(response);
}

*/


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected JwtUtil jwtUtil;

    @GetMapping("/login")
    public String login() {
        return "login"; // Returns the login.html template
    }
}

/*
        @PostMapping("/register")
        public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
            if (userService.findByEmail(request.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email already registered");
            }

            User user = new User();
            user.setName(request.getName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            userService.saveUser(user);

            return ResponseEntity.ok(Map.of(
                    "token", jwtUtil.generateToken(user.getEmail()),
                    "user", user
            ));
        }

        @PostMapping("/login")
        public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
            Optional<org.example.models.User> userOptional = userService.findByEmail(request.getEmail());

            if (userOptional.isEmpty() ||
                    !passwordEncoder.matches(request.getPassword(), userOptional.get().getPassword())) {
                return ResponseEntity.badRequest().body("Invalid email or password");
            }

            org.example.models.User user = userOptional.get();
            return ResponseEntity.ok(Map.of(
                    "token", jwtUtil.generateToken(user.getEmail()),
                    "user", user
            ));
        }
    }



*/
