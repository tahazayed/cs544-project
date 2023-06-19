package cs544.controller;

import cs544.model.Vote;
import cs544.dto.VoteCreationObject;
import cs544.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VoteRestController {
    @Autowired
    private IVoteService voteService;

    @GetMapping(value = "/vote/", produces = "application/json")
    public List<Vote> getAll() {

        return voteService.getAll();
    }

    @GetMapping(value = "/vote/post/{id}", produces = "application/json")
    public List<Vote> getAllByPostId(@PathVariable Long id) {

        return voteService.getAllByPostId(id);
    }

    @GetMapping(value = "/vote/comment/{id}", produces = "application/json")
    public List<Vote> getAllByCommentId(@PathVariable Long id) {

        return voteService.getAllByCommentId(id);
    }

    @GetMapping(value = "/vote/{id}", produces = "application/json")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Vote vote = voteService.get(id);
        if (vote == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @PostMapping(value = "/vote/", consumes = "application/json")
    public RedirectView add(@RequestBody VoteCreationObject voteCreationObject) {
        Vote vote = new Vote();
        vote.setUserId(voteCreationObject.getUserId();
        vote.setCommentId(voteCreationObject.getCommentId());
        vote.setVote(voteCreationObject.getVote());
        vote.setPostId(voteCreationObject.getPostId());
        voteService.add(vote);
        return new RedirectView("/api/vote/" + vote.getId());
    }

// not needed as user will always add a vote
//    @PostMapping(value = "/vote/{id}", consumes = "application/json")
//    public void update(@RequestBody Vote vote) {
//        voteService.update(vote);
//    }

//    @PutMapping(value= "/vote/{id}", consumes = "application/json")
//    public void put(@PathVariable long id, @RequestBody Vote vote) {
//        if (id != vote.getId()) { throw new IllegalArgumentException(); }
//        voteService.update(vote);
//    }

    @DeleteMapping("/vote/{id}")
    public void delete(@PathVariable Long id) {

        voteService.delete(id);
    }

    @DeleteMapping("/vote/post/{id}")
    public void deleteAllByPostId(@PathVariable Long id) {

        voteService.deleteAllByPostId(id);
    }

    @DeleteMapping("/vote/comment/{id}")
    public void deleteAllByCommentId(@PathVariable Long id) {

        voteService.deleteAllByCommentId(id);
    }
}
