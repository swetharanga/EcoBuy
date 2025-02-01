package org.example.dto;


public class LoginRequest {

    private String email;
    private String username;
    private  Long Id;
    private String password;

    //Getters and Setters

    public void setPassword(String password) {
        this.password = password;
    }

    public CharSequence getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
