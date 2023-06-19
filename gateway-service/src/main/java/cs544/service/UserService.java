package cs544.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.client.ICommentServiceProxy;
import cs544.client.IPostServiceProxy;
import cs544.client.IUserServiceProxy;
import cs544.client.IVoteServiceProxy;
import cs544.model.User;

@Service
public class UserService implements IUserService {


    private final IUserServiceProxy userServiceProxy;

    public UserService(@Autowired IUserServiceProxy userServiceProxy) {
        this.userServiceProxy = userServiceProxy;
    }

    @Override
    public List < User > getAll() {
        return userServiceProxy.getAll();
    }

    @Override
    public Long register(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public User get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public User login(User user) {
        return userServiceProxy.login(user);       
    }

}