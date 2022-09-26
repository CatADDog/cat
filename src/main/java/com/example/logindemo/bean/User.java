package com.example.logindemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Long id;
    String name;
    int age;
    String sex;
    String email;
    String state;

    public User(String name, int age, String sex, String email, String state) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.state = state;
    }
}
