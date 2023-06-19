package cs544.service;

import cs544.model.User;

import java.util.List;

public interface IUserService {
    public List<User> getAll();
    public Long register(User post);
    public User get(Long id);
    public User login(User user);
}

