package cs544.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import cs544.models.Roles;
import cs544.models.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.daos.IUserDao;

@Service
public class UserService {
    @Autowired
    private IUserDao ud;
    @Autowired
    private RolesService roleServ;

    public List <User> getAllUsers() {
        return ud.findAll();
    }

    public Optional < User > getUserById(Long id) {
        return ud.findById(id);
    }

    public Optional < User > saveUser(User user) {

        List <Roles> roles = new ArrayList < > ();
        for (Roles userRole: user.getRoles()) {
            Roles existingRole = roleServ.findRoleByERole(userRole.getRole());
            if (existingRole != null) {
                roles.add(existingRole);
            }
        }
        user.setRoles(roles);
        String salt = BCrypt.gensalt();

        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(BCrypt.hashpw(user.getPassword(), salt));
        return Optional.of(ud.save(user));
    }

    public Optional < User > getUserByUsernameAndPassword(String username, String password) {
        return ud.findAllByUsernameAndPassword(username, password);
    }
}