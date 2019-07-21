package com.yueking.core.shiro.controller;

import com.yueking.core.shiro.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("subLogin")
    public void subLogin(User user) {
        System.out.println("========subLogin=======");
        System.out.println("user:"+user);
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("home")
    public String home(){
        return "home";
    }

    @RequestMapping("unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
}
