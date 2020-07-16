package com.dubbo.consumer.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {


    //如果需要使用shiro长期登陆，设置subject的rememberMe属性并且设置允许的范围为user。authc不允许被rememberMe用户访问。
    @PostMapping(value = "/doLogin")
    public void doLogin(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password){
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);

            System.out.println("登陆成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("登陆失败");
        }
    }

    @RequestMapping(value = "/index")
    public String index(){
        System.out.println("欢迎来到主页");
        return "欢迎来到主页";
    }

    @GetMapping(value = "/hello")
    public String hello(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies[0].getValue());
        return "hello";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "Please Login !";
    }

    @GetMapping(value = "/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("成功退出");
        return "success to logout";
    }









}
