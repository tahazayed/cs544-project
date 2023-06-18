package cs544.client;

import cs544.dto.VoteCreationObject;
import cs544.model.Vote;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IVoteServiceProxy {
    List<Vote> getAll();
    List<Vote> getAllByPostId(@PathVariable Long id);
    List<Vote> getAllByCommentId(@PathVariable Long id);
    Vote get(@PathVariable Long id);
    Long add(@RequestBody VoteCreationObject voteCreationObject);
    void delete(@PathVariable Long id);
    void deleteAllByPostId(@PathVariable Long id);
    void deleteAllByCommentId(@PathVariable Long id);
}
