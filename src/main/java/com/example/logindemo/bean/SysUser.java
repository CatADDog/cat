package com.example.logindemo.bean;

public class SysUser {
    private String name;
    private String password;

    public SysUser() {

    }

    public SysUser(String name, String pwd) {
        this.name = name;
        this.password = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
