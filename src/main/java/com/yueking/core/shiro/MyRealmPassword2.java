package com.yueking.core.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealmPassword2 extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = "yuekinger";
        String password = "e4c58578be098a7832835680e1d2756a";
        String salt2 = "823ce226d1e86706fde24b73dab17097";

        SimpleAuthenticationInfo authenticationInfo= new SimpleAuthenticationInfo(username,password,getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username+salt2));
        return authenticationInfo;
    }
}
