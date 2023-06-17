package cs544.repository;

import cs544.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentDao extends JpaRepository<Comment, Integer> {



}
