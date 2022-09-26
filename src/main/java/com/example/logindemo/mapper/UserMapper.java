package com.example.logindemo.mapper;

import com.example.logindemo.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Long id);
    @Insert("insert into user(name,age,sex,email,state) values( #{name}, #{age} ,#{sex},#{email},#{state} )")
    void addUser(User user);
    @Select("select * from user")
    public List<User> getUserList();
    @Select("select name,age,sex,email,state from user")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "email", column = "email"),
            @Result(property = "state", column = "state")
    })
    public List<User> getAllWithoutId();
    @Update("update user set age=#{age},name=#{name},sex=#{sex},email=#email,state=#{state} where id =#{id}")
    void updateUser(User user);
    @Delete("delete from user where id =#{id}")
    void deleteUserById(@Param("id") Long id);
}
