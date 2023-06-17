package cs544.repository;

import cs544.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostDao extends JpaRepository<Post, Long> {
}
