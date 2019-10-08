package com.example.sdllabproject1;

public class usercreation {
    String username;
    String role;

    public usercreation(String username) {
        this.username = username;
    }
    public usercreation(String username,String role)
    {
        this.username=username;
        this.role=role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
