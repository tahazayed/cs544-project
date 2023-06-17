package cs544;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public List<Comment> getAll(){return commentDao.findAll();}
    public Comment add(Comment comment){
        comment.setDateTime(LocalDateTime.now());
        //comment.setPostId(UUID.randomUUID().toString());
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
