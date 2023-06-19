package cs544.controller;

import cs544.client.IVoteServiceProxy;
import cs544.dto.VoteCreationObject;
import cs544.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VoteRestController {

    private final IVoteServiceProxy voteServiceProxy;

    public VoteRestController(@Autowired IVoteServiceProxy voteServiceProxy) {
        this.voteServiceProxy = voteServiceProxy;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/votes/", produces = "application/json")
    public List<Vote> getAll() {

        return voteServiceProxy.getAll();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/votes/post/{id}", produces = "application/json")
    public List<Vote> getAllByPostId(@PathVariable Long id) {

        return voteServiceProxy.getAllByPostId(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/votes/comment/{id}", produces = "application/json")
    public List<Vote> getAllByCommentId(@PathVariable Long id) {

        return voteServiceProxy.getAllByCommentId(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/votes/{id}", produces = "application/json")
    public Vote get(@PathVariable Long id) {

        return voteServiceProxy.get(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/votes/", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody VoteCreationObject voteCreationObject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String userId = authentication.getName();
            voteCreationObject.setUserId(Long.parseLong(userId));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Access Denied"
            );
        }
        Long response = voteServiceProxy.add(voteCreationObject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('USER')")
//    @DeleteMapping("/votes/{id}")
//    public ResponseEntity<String> delete(@PathVariable Long id) {
//        voteServiceProxy.delete(id);
//        return new ResponseEntity<>("Vote entity deleted successfully.", HttpStatus.OK);
//    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @DeleteMapping("/votes/post/{id}")
//    public ResponseEntity<String> deleteAllByPostId(@PathVariable Long id) {
//        voteServiceProxy.deleteAllByPostId(id);
//        return new ResponseEntity<>("Vote entity deleted successfully.", HttpStatus.OK);
//    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @DeleteMapping("/votes/comment/{id}")
//    public ResponseEntity<String> deleteAllByCommentId(@PathVariable Long id) {
//        voteServiceProxy.deleteAllByCommentId(id);
//        return new ResponseEntity<>("Vote entity deleted successfully.", HttpStatus.OK);
//    }

}
