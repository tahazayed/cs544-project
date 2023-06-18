package cs544.service;

import cs544.model.Comment;
import cs544.repository.CommentDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentServiceProxy {
    @Autowired
    private CommentDao commentDao;

    public List<Comment> getAll(){return commentDao.findAll();}
    public Comment add(Comment comment){
        comment.setDateTime(LocalDateTime.now());

        return commentDao.save(comment);}
    public void update(Comment comment){
        commentDao.save(comment);}
    public void delete(int userId){
        commentDao.deleteById(userId);}
    public Comment get(int id){
       try {return commentDao.findById(id).get();}
           catch (Exception e){
           e.printStackTrace();
           return null;
           }
       }


}
