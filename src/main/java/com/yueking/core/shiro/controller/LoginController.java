package com.yueking.core.shiro.controller;

import com.yueking.core.shiro.entity.User;
import com.yueking.core.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("subLogin")
    public String subLogin(User user) {
        String message;
        System.out.println("========subLogin=======");
        System.out.println("user:"+user);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());

        token.setRememberMe(true);

        try {
            subject.login(token);
            System.out.println("---isAuthenticated:"+subject.isAuthenticated());

            if (subject.hasRole("admin")) {
                System.out.println("--admin Account");
            } else {
                System.out.println("--user Account");
            }

            if (subject.isPermitted("sys")) {
                System.out.println("-- sys permission");
            }

            if (subject.isPermitted("pub")) {
                System.out.println("-- pub permission");
            }
        } catch (UnknownAccountException e) {
            System.out.println("=====UnknownAccountException");
            message = "UnknownAccountException";
//            throw new UnknownAccountException();
        }catch (IncorrectCredentialsException ex) {
//            return "用户不存在或者密码错误！";
            message = "password error";
        } catch (AuthenticationException ex) {
            message = "AuthenticationException";
//            return ex.getMessage(); // 自定义报错信息
        } catch (Exception ex) {
            ex.printStackTrace();
//            return "内部错误，请重试！";
            message = "error";
        }
        return "home";

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
