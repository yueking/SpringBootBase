package com.yueking.core.shiro.yw.demo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealmPassword extends AuthorizingRealm {
    private PasswordService passwordService;

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //进入用户登录
        if (!token.getPrincipal().equals("yuewu")) {
            System.out.println("不存在："+token.getPrincipal());
            return null;
        }
        //实际项目中需要使用 passwordService 加密后保存到数据库中
        System.out.println("---:encryptPassword:"+passwordService.encryptPassword("123"));
        return new SimpleAuthenticationInfo("yuewu",passwordService.encryptPassword("123"),getName());
    }
}
