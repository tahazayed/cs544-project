package com.example.demo.Daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Roles;
import com.example.demo.Models.enums.ERoles;

public interface IRoleDao extends JpaRepository<Roles,Long> {
    
    public Roles findOneByRole(ERoles nameOfRole);
}
