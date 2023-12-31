package cs544.service;

import cs544.model.Vote;
import cs544.repository.IVoteDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@Transactional
public class VoteService implements IVoteService {
    @Autowired
    private IVoteDao voteDao;

    @Override
    public List<Vote> getAll() {
        return voteDao.findAll();
    }

    @Override
    public void add(Vote vote) {
        var existingVote = voteDao.findFirstByUserIdAndCommentId(vote.getUserId(),
                vote.getCommentId());
        if (existingVote == null) {
            voteDao.save(vote);
        } else {
            existingVote.setVote(vote.getVote());
            voteDao.save(existingVote);
            vote.setId(existingVote.getId());
            vote.setVersion(existingVote.getVersion());
        }
    }

    @Override
    public void update(Vote vote) {
        voteDao.save(vote);
    }

    @Override
    public void delete(Long voteId) {
        voteDao.deleteById(voteId);
    }

    @Override
    public Vote get(Long id) {
        Vote vote = null;
        try {
            vote = voteDao.findById(id).get();
        } catch (Exception var4) {
            //Ignore
        }
        return vote;
    }

    @Override
    public void deleteAllByPostId(Long postId) {
        voteDao.deleteAllByPostId(postId);
    }

    @Override
    public void deleteAllByCommentId(Long commentId) {
        voteDao.deleteAllByCommentId(commentId);
    }

    @Override
    public List<Vote> getAllByPostId(Long postId) {
        return voteDao.findAllByPostId(postId);
    }

    @Override
    public List<Vote> getAllByCommentId(Long commentId) {
        return voteDao.findAllByCommentId(commentId);
    }
}
