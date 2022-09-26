package com.example.logindemo.controller;

import com.example.logindemo.annotation.OnlyAdmin;
import com.example.logindemo.bean.User;
import com.example.logindemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userSerive;
    @GetMapping("")
    @OnlyAdmin   //自定义注解
    public String user() {
        return "user";
    }
    @GetMapping("/")
    public List<User> getUserList(){
        List<User> list=userSerive.getUserList();
        return list;
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userSerive.getUserById(id);
    }
    @PostMapping("/")
    public User addUser(@RequestBody User user) throws Exception {
        Long id = user.getId();
        if(id==null){
            throw new Exception("id为空,添加失败"); //抛出异常
        }
        if(userSerive.getUserById(id)!=null){
            throw new Exception("id重复,添加失败"); //抛出异常
        }
        userSerive.addUser(user);
        return user;
    }
    @PutMapping("/")
    public boolean updateuser(@RequestBody User user) throws Exception {
        Long id = user.getId();
        if(id==null){
            throw new Exception("id不存在,更新失败");
        }
        userSerive.updateUser(user);
        return true;
    }
    @DeleteMapping("/{id}")
    public boolean deldeteUser(@RequestBody Long id)throws Exception{
        if(userSerive.getUserById(id)==null){
            throw new Exception("id不存在,删除失败");
        }
        userSerive.deleteUserById(id);
        return true;
    }

}
