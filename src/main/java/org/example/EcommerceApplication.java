package org.example;  // Ensure this matches your project's package structure

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Marks this as the main Spring Boot application class
public class EcommerceApplication {

    public static void main(String[] args) {
        // Start the Spring Boot application
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
