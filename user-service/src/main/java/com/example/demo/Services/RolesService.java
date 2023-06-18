package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Daos.IRoleDao;
import com.example.demo.Models.Roles;
import com.example.demo.Models.enums.ERoles;

@Service
public class RolesService {
    
    @Autowired
    IRoleDao roleDao;

    public Roles findRoleByERole(ERoles nameOfRole) {
        return roleDao.findOneByRole(nameOfRole);
    }
}
