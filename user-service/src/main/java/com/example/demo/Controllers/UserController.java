package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.Exceptions.UserCreationException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Models.User;
import com.example.demo.Services.UserService;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    
    @Autowired
    UserService userServ;

    @GetMapping("")
    RedirectView redirecting() {
        return new RedirectView("/api/users");
    }

    @GetMapping("/")
    List<User> getAllUsers() {
        return userServ.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id) {
        try {
            Optional<User> user = userServ.getUserById(id);
            return user.get();

        } catch (Exception e) {
            // TODO: handle exception
            throw new UserNotFoundException("User not found");
        }
    }

    @PostMapping("/")
    ResponseEntity<User> registerUser(@RequestBody User newUser) {

        Optional<User> newUserAdded = userServ.saveUser(newUser);

        if(!newUserAdded.isPresent()) {
            throw new UserCreationException();
        }
        
        // HelperFunctions.attachJWT(newUserAdded.get());
        
        return ResponseEntity.ok(newUserAdded.get());

    }




    

}
