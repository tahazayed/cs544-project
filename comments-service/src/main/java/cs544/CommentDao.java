package cs544;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CommentDao extends JpaRepository<Comment, Integer> {



}
