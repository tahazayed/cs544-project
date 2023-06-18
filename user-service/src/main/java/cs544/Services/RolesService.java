package cs544.Services;

import cs544.Daos.IRoleDao;
import cs544.Models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.Models.enums.ERoles;

@Service
public class RolesService {
    
    @Autowired
    IRoleDao roleDao;

    public Roles findRoleByERole(ERoles nameOfRole) {
        return roleDao.findOneByRole(nameOfRole);
    }
}
