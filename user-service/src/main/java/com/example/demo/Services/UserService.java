package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Daos.IUserDao;
import com.example.demo.Models.User;
import com.example.demo.Models.Roles;

@Service
public class UserService {
    @Autowired
    private IUserDao ud;
    @Autowired
    private RolesService roleServ;

    public List < User > getAllUsers() {
        return ud.findAll();
    }

    public Optional < User > getUserById(Long id) {
        return ud.findById(id);
    }

    public Optional < User > saveUser(User user) {

        List < Roles > roles = new ArrayList < > ();
        for (Roles userRole: user.getRoles()) {
            Roles existingRole = roleServ.findRoleByERole(userRole.getRole());
            if (existingRole != null) {
                roles.add(existingRole);
            }
        }
        user.setRoles(roles);
        return Optional.of(ud.save(user));
    }

    public Optional < User > getUserByUsernameAndPassword(String username, String password) {
        return ud.findAllByUsernameAndPassword(username, password);
    }
}