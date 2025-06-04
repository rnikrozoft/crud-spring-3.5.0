package com.example.demo.service;

import com.example.demo.model.entity.UserEntity;

import java.util.List;

public interface UserIF {
    UserEntity newUser(UserEntity user);
    UserEntity findByEmail(String email);
    UserEntity updateUser(UserEntity user);
    void deleteUser(Integer id);

    List<UserEntity> findAllUsers();
}
