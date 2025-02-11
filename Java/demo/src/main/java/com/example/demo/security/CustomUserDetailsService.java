package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    // Constructor to inject the InMemoryUserDetailsManager
    @Autowired
    public CustomUserDetailsService(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        initialize();
    }

    // Method to load a user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return inMemoryUserDetailsManager.loadUserByUsername(username);
    }

    // Initialize the in-memory users
    private void initialize() {
        // Check if the user "user" already exists
        if (!inMemoryUserDetailsManager.userExists("user")) {
            // Add an example user with username 'user' and password 'password'
            User.UserBuilder users = User.builder();
            inMemoryUserDetailsManager.createUser(
                users.username("user")
                    .password("{bcrypt}$2a$10$KlBl2dZWuPgeJ6Gdbq6UruvRIEcF4MiLD2G8Gb2p.Cwr1hDZBw9tS") // pre-encoded password
                    .roles("USER")
                    .build());
        }
    }
}

