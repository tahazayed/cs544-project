package cs544.controller;

import cs544.dto.PostCreationObject;
import cs544.model.Post;
import cs544.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostRestController {

    private final IPostService postService;

    @Autowired
    public PostRestController(IPostService postService) {

        this.postService = postService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/posts/", produces = "application/json")
    public List<Post> getAll() {

        return postService.getAll();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/posts/{id}", produces = "application/json")
    public Post get(@PathVariable long id) {

        return postService.get(id);
    }

    //TODO: Use the userId from logged in user
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/posts/", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody PostCreationObject postCreationObject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String userId = authentication.getName();
            postCreationObject.setUserId(Long.parseLong(userId));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Access Denied"
            );
        }
        Long response = postService.add(postCreationObject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping(value = "/posts/{id}", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody Post post, @PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String userId = authentication.getName();
            Long loggedInUserId = Long.parseLong(userId);

            Post existingPost = postService.get(id);
            if (existingPost != null) {
                if (existingPost.getUserId() != loggedInUserId) {
                    throw new ResponseStatusException(
                            HttpStatus.FORBIDDEN, "Access Denied"
                    );
                }

            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                );
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Access Denied"
            );
        }
        post.setId(id);
        postService.update(post);
        return new ResponseEntity<>("Updated Successful", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/posts/{id}", produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable long id) {
        postService.delete(id);
        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

}
