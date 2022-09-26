package com.example.logindemo.annotation;


import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Target(ElementType.METHOD)  // 让该注解可以注解在方法和类上
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyAdmin {

}
