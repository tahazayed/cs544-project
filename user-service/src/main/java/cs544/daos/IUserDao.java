package cs544.daos;

import java.util.Optional;

import cs544.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserDao extends JpaRepository<User,Long>{

    Optional<User> findAllByUsernameAndPassword(String username, String password);
    Optional<User> findAllByUsername(String username);
}
