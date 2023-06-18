package com.example.demo.Daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.User;


public interface IUserDao extends JpaRepository<User,Long>{

    Optional<User> findAllByUsernameAndPassword(String username, String password);
}
