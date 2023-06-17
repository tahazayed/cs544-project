package cs544;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostDao extends JpaRepository<Post, Long> {
}
