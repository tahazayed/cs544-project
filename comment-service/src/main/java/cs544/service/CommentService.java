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
    private final ICommentDao ICommentDao;

    public CommentService(@Autowired ICommentDao ICommentDao) {
        this.ICommentDao = ICommentDao;
    }

    @Override
    public List<Comment> getAll(){return ICommentDao.findAll();}
    @Override
    public Comment add(Comment comment){
        comment.setDateTime(LocalDateTime.now());

        return ICommentDao.save(comment);}
    @Override
    public void update(Comment comment){
        ICommentDao.save(comment);}
    @Override
    public void delete(Long userId){
        ICommentDao.deleteById(userId);}
    @Override
    public Comment get(Long id){
       try {return ICommentDao.findById(id).get();}
           catch (Exception e){
           e.printStackTrace();
           return null;
           }
       }


}
