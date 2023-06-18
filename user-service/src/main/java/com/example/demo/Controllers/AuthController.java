package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.User;
import com.example.demo.Services.AuthService;
import com.example.demo.Services.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/oauthtoken")
public class AuthController {

    @Autowired
    UserService userServ;
    @Autowired
    AuthService authServ;

    @PostMapping(value="")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        String jwtToken = authServ.authenticateUser(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("jwtToken", jwtToken);
        return ResponseEntity.ok(response);
    }
    
    
}
