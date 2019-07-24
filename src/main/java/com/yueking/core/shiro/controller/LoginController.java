package com.yueking.core.shiro.controller;

import com.yueking.core.ret.RetResponse;
import com.yueking.core.ret.RetResult;
import com.yueking.core.shiro.entity.User;
import com.yueking.core.shiro.model.LoginResult;
import com.yueking.core.shiro.service.LoginService;
import com.yueking.core.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @RequestMapping("subLogin")
    public String subLogin(User user, ModelMap model) {
        LoginResult loginResult = loginService.login(user.getUsername(), user.getPassword());
        model.addAttribute("message", loginResult.getResult());
        if (loginResult.isLogin()) {
            return "home";
        } else {
            return "login";
        }

    }

    @ResponseBody
    @RequestMapping("subLoginREST")
    public RetResult<User> subLogin2(User user) {
        Subject currentUser = SecurityUtils.getSubject();
        //登录
        try {
            currentUser.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
        } catch (IncorrectCredentialsException i) {
            throw new ServiceException("密码输入错误");
        }
        //从session取出用户信息
        user = (User) currentUser.getPrincipal();
        return RetResponse.makeOKRsp(user);

    }

    @RequestMapping("logout")
    public String logout() {
        loginService.logout();
        return "login";
    }

    @RequestMapping("intoLogin")
    public String intoLogin() {
        return "login";
    }

    @RequestMapping("home")
    public String home() {
        return "home";
    }

    @RequestMapping("unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @ResponseBody
    @RequestMapping("userInfo")
    public RetResult<User> userInfo() {
        User user = userService.findByUsername("yuekinger");
        return RetResponse.makeOKRsp(user);
    }

    @ResponseBody
    @RequestMapping("findUserByUserName")
    public RetResult<User> findUserByUserName(String username) {
        User user = userService.findByUsername(username);
        return RetResponse.makeOKRsp(user);
    }
    @ResponseBody
    @RequestMapping("findUserByUserNameExc")
    public RetResult<User> findUserByUserNameExc(String username) {
        List a = null;
        a.size();
        User user = userService.findByUsername(username);
        return RetResponse.makeOKRsp(user);
    }
}
