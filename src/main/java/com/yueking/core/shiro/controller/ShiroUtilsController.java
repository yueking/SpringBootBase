package com.yueking.core.shiro.controller;

import com.yueking.core.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shiroUtils")
public class ShiroUtilsController {
    @RequestMapping("/noLogin")
    public void noLogin(){
        System.out.println("=====noLogin");
        throw new UnauthenticatedException();
    }
    @RequestMapping("/noAuthorize")
    public void noAuthorize() {
        System.out.println("=====noAuthorize");
        throw new UnauthorizedException();
    }


    @RequestMapping("/currentUser")
    public User currentUser() {
        User u = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println("=====currentUser"+u);
        return u;
    }
}
