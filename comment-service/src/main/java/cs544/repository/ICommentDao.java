package cs544.repository;

import cs544.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICommentDao extends JpaRepository<Comment, Long> {

    void deleteAllByPostId(Long id);
}
