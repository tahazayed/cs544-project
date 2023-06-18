package cs544.controller;

import cs544.client.IVoteServiceProxy;
import cs544.dto.VoteCreationObject;
import cs544.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VoteRestController {

    private final IVoteServiceProxy voteServiceProxy;

    public VoteRestController(@Autowired IVoteServiceProxy voteServiceProxy) {
        this.voteServiceProxy = voteServiceProxy;
    }

    @GetMapping(value = "/votes/", produces = "application/json")
    public List<Vote> getAll() {
        return voteServiceProxy.getAll();
    }

    @GetMapping(value = "/votes/post/{id}", produces = "application/json")
    public List<Vote> getAllByPostId(@PathVariable Long id) {
        return voteServiceProxy.getAllByPostId(id);
    }

    @GetMapping(value = "/votes/comment/{id}", produces = "application/json")
    public List<Vote> getAllByCommentId(@PathVariable Long id) {
        return voteServiceProxy.getAllByCommentId(id);
    }

    @GetMapping(value = "/votes/{id}", produces = "application/json")
    public Vote get(@PathVariable Long id) {
        return voteServiceProxy.get(id);
    }

    @PostMapping(value = "/votes/", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody VoteCreationObject voteCreationObject) {

        //return new RedirectView("/api/vote/" + voteServiceProxy.add(voteCreationObject));
        Long response = voteServiceProxy.add(voteCreationObject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/votes/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        voteServiceProxy.delete(id);
        return new ResponseEntity<>("Vote entity deleted successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/votes/post/{id}")
    public ResponseEntity<String> deleteAllByPostId(@PathVariable Long id) {
        voteServiceProxy.deleteAllByPostId(id);
        return new ResponseEntity<>("Vote entity deleted successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/votes/comment/{id}")
    public ResponseEntity<String> deleteAllByCommentId(@PathVariable Long id) {
        voteServiceProxy.deleteAllByCommentId(id);
        return new ResponseEntity<>("Vote entity deleted successfully.", HttpStatus.OK);
    }

}
