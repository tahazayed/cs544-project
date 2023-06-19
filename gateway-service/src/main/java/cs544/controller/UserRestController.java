package cs544.controller;

import cs544.dto.UserLoginObject;
import cs544.model.User;
import cs544.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final IUserService userService;

    public UserRestController(@Autowired IUserService userService) {

        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/user/", produces = "application/json")
    public List<User> getAll() {
        return userService.getAll();
    }

    // @RolesAllowed("USER")
    @PostMapping(value = "/user/login/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody UserLoginObject user) {
         Map<String, Object> response = new HashMap<>();
         String token = userService.login(user);
         response.put("jwtToken", token);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    // @GetMapping(value = "/user/{id}",produces = "application/json")
    // public Post get(@PathVariable long id){

    //     return postService.get(id);
    // }


    // @PutMapping(value = "/user/{id}", consumes = "application/json")
    // public ResponseEntity<?> update(@RequestBody Post post, @PathVariable Long id) {
    //     post.setId(id);
    //     post.generateDate();
    //     postService.update(post);
    //     return new ResponseEntity<>("Updated Successful", HttpStatus.OK);
    // }

    // @DeleteMapping(value = "/post/{id}", produces = "application/json")
    // public ResponseEntity<String> delete(@PathVariable long id){
    //     postService.delete(id);
    //     return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    // }

}

