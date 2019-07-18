package com.yueking.core.shiro;

import com.yueking.BaseTest;
import com.yueking.core.shiro.realm.UserRealm;
import com.yueking.core.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class ShirtStartTest extends BaseTest {
    @Autowired
    UserService userService;

    @Test
    public void testHello() {
        System.out.println("=====hello");

        UserRealm userRealm = new UserRealm();

        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        userRealm.setCredentialsMatcher(credentialsMatcher);

        userRealm.setUserService(userService);

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(userRealm);

        SecurityUtils.setSecurityManager(securityManager);

        //3.获取 subject 及 创建 用户名 密码Token 身份/凭证
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("yuekinger", "123");

        subject.login(token);
    }

}
