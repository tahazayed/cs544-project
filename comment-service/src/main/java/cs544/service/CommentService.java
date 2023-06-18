package cs544.service;

import cs544.model.Comment;
import cs544.repository.ICommentDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentService implements ICommentService {
    private final ICommentDao commentDao;

    public CommentService(@Autowired ICommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> getAll() {
        return commentDao.findAll();
    }

    @Override
    public Long add(Comment comment) {
        comment.setDateTime(LocalDateTime.now());
        comment = commentDao.save(comment);
        return comment.getId();
    }

    @Override
    public void update(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void delete(Long userId) {
        commentDao.deleteById(userId);
    }

    @Override
    public Comment get(Long id) {
        try {
            return commentDao.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteAllByPostId(Long postId) {
        commentDao.deleteAllByPostId(postId);
    }


}
