package com.example.demo.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Daos.IUserDao;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.HelperFunctions.JwtUtils;
import com.example.demo.Models.User;

@Service
public class AuthService {
    
    @Autowired
    private IUserDao ud;

    public String authenticateUser(User user) {
        Optional<User> userFound = ud.findAllByUsernameAndPassword(user.getUsername(), user.getPassword());
        
        if(!userFound.isPresent()) {
            throw new UserNotFoundException("Invalid username or password");
        }

        Map<String, Object> claim1 = new HashMap<>();

        User userFoundObj = userFound.get();
        claim1.put("user", userFoundObj);

        String token = JwtUtils.generateToken(userFound.get(),claim1);

        return token;

    }

}
