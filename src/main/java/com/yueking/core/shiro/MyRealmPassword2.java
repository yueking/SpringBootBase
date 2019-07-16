package com.yueking.core.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealmPassword2 extends AuthorizingRealm {
    private PasswordService passwordService;

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //进入用户登录
        if (!token.getPrincipal().equals("yuekinger")) {
            System.out.println("不存在："+token.getPrincipal());
            return null;
        }
        String username = "yuekinger";
        String password = "e4c58578be098a7832835680e1d2756a";
        String salt2 = "823ce226d1e86706fde24b73dab17097";

        SimpleAuthenticationInfo authenticationInfo= new SimpleAuthenticationInfo(username,password,getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username+salt2));
        return authenticationInfo;
    }
}
