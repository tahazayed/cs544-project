package cs544.controller;

import cs544.model.Comment;
import cs544.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    private final ICommentService commentService;

    public CommentRestController(@Autowired ICommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/comment/", produces = "application/json")
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/comment/{id}", produces = "application/json")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Comment comment = commentService.get(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/comment/", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Comment comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String userId = authentication.getName();
            comment.setUserId(Long.parseLong(userId));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Access Denied"
            );
        }
        Long response = commentService.add(comment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/comment/{id}", consumes = "application/json")
    public Comment update(@RequestBody Comment comment, @PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String userId = authentication.getName();
            Long loggedInUserId = Long.parseLong(userId);

            Comment existingComment = commentService.get(id);
            if (existingComment != null) {
                if (existingComment.getUserId() != loggedInUserId) {
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
        comment.setId(id);
        commentService.update(comment);
        return comment;
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/comment/{id}")
    public void delete(@PathVariable Long id) {

        commentService.delete(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/comment/post/{id}")
    public void deleteByPostId(@PathVariable Long id) {

        commentService.deleteAllByPostId(id);
    }
}
