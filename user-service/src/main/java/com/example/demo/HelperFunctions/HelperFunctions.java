package com.example.demo.HelperFunctions;

import java.util.UUID;

import com.example.demo.Models.User;

public class HelperFunctions {
    
    public static String generateRandomToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static User attachJWT(User user) {
        // String token = JwtUtils.generateToken(user);
        // user.setJwtToken(token);
        return user;
    }
}
