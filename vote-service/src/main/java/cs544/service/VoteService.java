package cs544.service;

import cs544.model.Vote;
import cs544.repository.IVoteDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        var existingVote = voteDao.findFirstByUserIdAndCommentIdAndVote(vote.getUserId(),
                vote.getCommentId(), vote.getVote());
        if (existingVote == null) {
            voteDao.save(vote);
        } else {
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
        return voteDao.findById(id).get();
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
