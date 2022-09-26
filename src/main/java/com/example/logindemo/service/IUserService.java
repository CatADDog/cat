package com.example.logindemo.service;

import com.example.logindemo.bean.User;

import java.util.List;
public interface IUserService {
    public User getUserById(Long id);
    public List<User> getUsers();
    public List<User> getUsersWithoutId();
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUserById(Long id);
}

