package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VoteRestController {
    @Autowired
    private VoteService voteService;

    @GetMapping(value = "/votes/", produces = "application/json")
    public List<Vote> getAll() {

        return voteService.getAll();
    }

    @GetMapping(value = "/votes/post/{id}", produces = "application/json")
    public List<Vote> getAllByPostId(@PathVariable Long id) {

        return voteService.getAllByPostId(id);
    }

    @GetMapping(value = "/votes/comment/{id}", produces = "application/json")
    public List<Vote> getAllByCommentId(@PathVariable Long id) {

        return voteService.getAllByCommentId(id);
    }

    @GetMapping(value = "/votes/{id}", produces = "application/json")
    public Vote get(@PathVariable Long id) {

        return voteService.get(id);
    }

    @PostMapping(value = "/votes/", consumes = "application/json")
    public RedirectView add(@RequestBody VoteCreationObject voteCreationObject) {
        Vote vote = new Vote(voteCreationObject.getUserId(),
                voteCreationObject.getCommentId(),
                voteCreationObject.getVote(),
                voteCreationObject.getPostId());
        voteService.add(vote);
        return new RedirectView("/api/votes/" + vote.getId());
    }

// not needed as user will always add a vote
//    @PostMapping(value = "/votes/{id}", consumes = "application/json")
//    public void update(@RequestBody Vote vote) {
//        voteService.update(vote);
//    }

//    @PutMapping(value= "/votes/{id}", consumes = "application/json")
//    public void put(@PathVariable long id, @RequestBody Vote vote) {
//        if (id != vote.getId()) { throw new IllegalArgumentException(); }
//        voteService.update(vote);
//    }

    @DeleteMapping("/votes/{id}")
    public void delete(@PathVariable Long id) {

        voteService.delete(id);
    }

    @DeleteMapping("/votes/post/{id}")
    public void deleteAllByPostId(@PathVariable Long id) {

        voteService.deleteAllByPostId(id);
    }

    @DeleteMapping("/votes/comment/{id}")
    public void deleteAllByCommentId(@PathVariable Long id) {

        voteService.deleteAllByCommentId(id);
    }
}
