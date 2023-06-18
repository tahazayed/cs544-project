package cs544.Daos;

import java.util.Optional;

import cs544.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserDao extends JpaRepository<User,Long>{

    Optional<User> findAllByUsernameAndPassword(String username, String password);
}
