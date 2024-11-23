package org.example.models;
import org.springframework.security.crypto.bcrypt.BCrypt;
// Manage relationship between Java and database
// Getting all the user data from the mysql database

//import org.springframework.lang.NonNull;
import jakarta.persistence.*;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public String setFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() {
        return lastName;
    }
    public String getUsername(){
        return  username;
    }

    public String getPassword() {

        return password;
    }

    public String getEmail() {
        return email;
    }
}

