package com.example.logindemo.interceptor;

import com.example.logindemo.annotation.OnlyAdmin;
import com.example.logindemo.bean.SysUser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 自定义请求拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /*
     * 这里可以对请求之前进行拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session中的用户信息(loginUser)和登录状态(login_status)
        HttpSession session = request.getSession();  //手工创建session方式
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");  //获取登录用户信息

        //如果没有 转发到“/login”登录
        if (loginUser == null) {
            System.out.println("returnUrl=" + request.getRequestURL()); //测试时显示
            //转向到 /login 登录请求,  returnUrl保存当前的请求url
            response.sendRedirect("/login?returnUrl=" + URLEncoder.encode(request.getRequestURL().toString(), "UTF-8"));
            //URLEncoder.encode()方法解决中文乱码问题（本例可以不用）
            return false;  //拦截
            //请求转发用下面这个：
            //request.getRequestDispatcher("/login?returnUrl="+request.getServletPath()).forward(request, response);
        }


        // 检测是否有 @OnlyAdmin 注解    把handler强转为HandlerMethod
        OnlyAdmin oa = ((HandlerMethod) handler).getMethodAnnotation(OnlyAdmin.class);
        if (oa != null) {
            // 如果有 @OnlyAdmin则表明该方法只允许管理员操作
            if (!hasPermission(loginUser)) {
                response.setContentType("text/json;charset=utf-8");
                response.getWriter().println("{\"msg\":\"你未有该权限\"}");  //输出json
                return false;
            }
        }


        return true;  //放行

    }

    // 用户鉴权具体方法
    private boolean hasPermission(SysUser loginUser) {
        if (loginUser.getName().equals("admin"))
            return true;
        else
            return false;
    }
}
