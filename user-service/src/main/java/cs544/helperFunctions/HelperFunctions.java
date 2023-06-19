package cs544.helperFunctions;

import java.util.UUID;

import cs544.models.User;

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
