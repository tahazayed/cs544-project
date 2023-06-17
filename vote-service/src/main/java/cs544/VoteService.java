package cs544;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VoteService {
    @Autowired
    private IVoteDao voteDao;

    public List<Vote> getAll() {
        return voteDao.findAll();
    }

    public void add(Vote vote) {
        var existingVote = voteDao.findFirstByUserIdAndCommentIdAndVote(vote.getUserId(),
                vote.getCommentId(), vote.getVote());
        if (existingVote == null)
        {
            voteDao.save(vote);
        }
        else {
            vote.setId(existingVote.getId());
            vote.setVersion(existingVote.getVersion());
        }
    }

    public void update(Vote vote) {
        voteDao.save(vote);
    }

    public void delete(Long voteId) {
        voteDao.deleteById(voteId);
    }

    public Vote get(Long id) {
        return voteDao.findById(id).get();
    }

    public void deleteAllByPostId(Long postId)
    {
        voteDao.deleteAllByPostId(postId);
    }

    public void deleteAllByCommentId(Long commentId) {
        voteDao.deleteAllByCommentId(commentId);
    }
    public List<Vote> getAllByPostId(Long postId){
        return voteDao.findAllByPostId(postId);
    }
    List<Vote> getAllByCommentId(Long commentId){
        return voteDao.findAllByCommentId(commentId);
    }
}
