package cs544.HelperFunctions;

import java.util.UUID;

import cs544.Models.User;

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
