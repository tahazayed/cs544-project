package cs544.client;

import java.util.List;

import cs544.dto.UserLoginObject;
import cs544.dto.UserRegisterObject;
import cs544.model.User;

public interface IUserServiceProxy {
    public List<User> getAll();
    public User register(UserRegisterObject user);
    public User get(Long id);
    public String login(UserLoginObject user);
}

