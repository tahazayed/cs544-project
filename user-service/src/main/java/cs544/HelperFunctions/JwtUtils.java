package cs544.HelperFunctions;

import java.util.Date;
import java.util.Map;

import cs544.Models.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    private static final String SECRET_KEY = "enterprise";
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour

    public static String generateToken(User user, Map < String, Object > claims) {
        Claims jwtClaims = Jwts.claims();

        // Add additional claims dynamically from the claims map
        jwtClaims.putAll(claims);

        return Jwts.builder()
            .setClaims(jwtClaims)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    public static User parseToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();

        User user = new User();
        user.setId((Long) claims.get("userId"));
        user.setUsername((String) claims.get("username"));
        // user.setRoles((List<String>) claims.get("roles"));

        return user;
    }
}