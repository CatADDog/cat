package com.example.logindemo.controller;

import com.example.logindemo.annotation.OnlyAdmin;
import com.example.logindemo.bean.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    private String returnUrl = "/";

    private static Map<String, SysUser> userdb = new HashMap<String, SysUser>();

    /**
     * 模拟数据库 管理员账号admin
     */
    static {
        userdb.put("admin", new SysUser("admin", "admin"));
        userdb.put("guest", new SysUser("guest", "guest"));
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    //产品信息
    @GetMapping("/product")
    public String product() {
        return "product";
    }

    //登录
    @GetMapping("/login")
    public String login(@RequestParam(value = "returnUrl", required = false) String url) {
        if (url != null){
            returnUrl = url;
        }else{
            returnUrl="/";
        }
        return "login";
    }

    //登录检查
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("name") String username, @RequestParam("pwd") String password, HttpSession session) {

        session.setAttribute("login_status", false);  //登录状态初始为false

        if (userdb.get(username) == null) {
            return "0";  //用户名不存在
        } else {
            SysUser user = (SysUser) userdb.get(username);
            if (!password.equals(user.getPassword())) {
                return "1";  //密码错误
            } else {
                //将用户信息写入session
                session.setAttribute("loginUser", user);
                session.setAttribute("login_status", true);
                return returnUrl;
            }
        }
    }

    //注销用户
    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");  //移除session值（实现注销）
        session.setAttribute("login_status", false);  //设置登录状态false
        return "redirect:/";
    }


}
