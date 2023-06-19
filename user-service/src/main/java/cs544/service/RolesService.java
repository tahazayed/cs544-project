package cs544.service;

import cs544.daos.IRoleDao;
import cs544.models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.models.enums.ERoles;

@Service
public class RolesService {
    
    @Autowired
    IRoleDao roleDao;

    public Roles findRoleByERole(ERoles nameOfRole) {
        return roleDao.findOneByRole(nameOfRole);
    }
}
