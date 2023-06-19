package cs544.client;

import java.util.List;

import cs544.model.User;

public interface IUserServiceProxy {
    public List<User> getAll();
    public Long register(User user);
    public User get(Long id);
    public User login(User user);
}

