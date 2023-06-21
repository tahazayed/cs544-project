package cs544.service;

import cs544.client.ICommentServiceProxy;
import cs544.client.IVoteServiceProxy;
import cs544.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentService implements ICommentService {
    private final ICommentServiceProxy commentServiceProxy;

    private final IVoteServiceProxy voteServiceProxy;

    @Autowired
    public CommentService(ICommentServiceProxy commentServiceProxy,
                          IVoteServiceProxy voteServiceProxy) {
        this.commentServiceProxy = commentServiceProxy;
        this.voteServiceProxy = voteServiceProxy;
    }

    @Override
    public List<Comment> getAll() {
        return commentServiceProxy.getAll();
    }

    @Override
    public Long add(Comment comment) {
        return commentServiceProxy.add(comment);

    }

    @Override
    public void update(Comment comment) {
        commentServiceProxy.update(comment);
    }

    @Override
    public void delete(Long id) {
        commentServiceProxy.delete(id);
        voteServiceProxy.deleteAllByCommentId(id);
    }

    @Override
    public Comment get(Long id) {
        return commentServiceProxy.get(id);
    }

    @Override
    public void deleteAllByPostId(Long id) {

        commentServiceProxy.deleteAllByPostId(id);
    }
}
