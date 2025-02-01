package org.example.config;

import jakarta.annotation.PostConstruct;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
//@Configuration
public class InitialDataConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User user = new User();
            user.setName("Admin");
            user.setUsername("admin");
            user.setEmail("admin@example.com");
            user.setPassword(passwordEncoder.encode("password"));
            userRepository.save(user);
        }
    }

}

   /*
    private static final Logger logger = LoggerFactory.getLogger(InitialDataConfig.class);

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.name}")
    private  String adminName;
    @Value("${admin.username}")
    private  String adminUsername;
    @Value("${admin.password}")
    private  String adminPassword;

    //Initializing initial credentials to login - This method checks if an admin user exists in the system (by email) and,
    // if not, creates one with the given details.
    @Autowired
    public InitialDataConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
        public void InitializeData () {
        try {
            logger.info("Checking for existing admin user...");
            User existingUser = userService.findByEmail(adminEmail).orElse(null);

            if (existingUser == null) {
                logger.info("Creating new admin user...");
                String encodedPassword = passwordEncoder.encode(adminPassword);
                User admin = new User(adminName, adminEmail, encodedPassword, adminUsername);
                logger.info("Encoded password: {}", encodedPassword);
                User savedUser = userService.saveUser(admin);
                if (savedUser != null && savedUser.getId() != null) {
                    logger.info("Admin user created successfully with ID: {}", savedUser.getId());
                } else {
                    logger.error("Failed to create admin user");
                }
            } else {
                logger.info("Admin user already exists with ID: {}", existingUser.getId());
            }

        } catch (Exception e) {
            System.err.println("Error during data initialization: " + e.getMessage());
            e.printStackTrace();

        }
    }


}
*/
