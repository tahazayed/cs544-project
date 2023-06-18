package cs544.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import cs544.Models.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.Daos.IUserDao;
import cs544.Exceptions.UserNotFoundException;
import cs544.HelperFunctions.JwtUtils;

@Service
public class AuthService {
    
    @Autowired
    private IUserDao ud;

    public String authenticateUser(User user) {
        Optional<User> userFound = ud.findAllByUsername(user.getUsername());

        
        if(!userFound.isPresent()) {
            throw new UserNotFoundException("Invalid username or password");
        }
        User userFoundObj = userFound.get();

        if(!BCrypt.checkpw(user.getPassword(), userFoundObj.getPassword())) {
            throw new UserNotFoundException("Invalid username or password");   
        }

        Map<String, Object> claim1 = new HashMap<>();

        claim1.put("user", userFoundObj);

        String token = JwtUtils.generateToken(userFound.get(),claim1);

        return token;

    }

}
