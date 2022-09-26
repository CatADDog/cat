package com.example.logindemo.service;

import com.example.logindemo.bean.User;
import com.example.logindemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }
    @Override
    public List<User> getUsers() {
        return userMapper.getUserList();
    }
    @Override
    public List<User> getUsersWithoutId() {
        return userMapper.getAllWithoutId();
    }
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteUserById(id);
    }
}
