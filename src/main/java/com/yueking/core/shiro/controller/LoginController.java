package com.yueking.core.shiro.controller;

import com.yueking.core.shiro.entity.User;
import com.yueking.core.shiro.model.LoginResult;
import com.yueking.core.shiro.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("subLogin")
    public String subLogin(User user, ModelMap model ) {
        LoginResult loginResult = loginService.login(user.getUsername(), user.getPassword());
        model.addAttribute("message",loginResult.getResult());
        if (loginResult.isLogin()) {
            return "home";
        } else {
            return "login";
        }

    }

    @RequestMapping("logout")
    public String logout(){
        loginService.logout();
        return "login";
    }

    @RequestMapping("intoLogin")
    public String intoLogin(){
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
