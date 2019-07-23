package com.yueking.core.shiro.realm;

import com.yueking.core.shiro.entity.User;
import com.yueking.core.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService ;

    {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        this.setCredentialsMatcher(credentialsMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //1.获取登录名称
//        String username = (String) principals.getPrimaryPrincipal();
        //2.查找用户
//        User user = userService.findByUsername(username);
        User user = (User) principals.getPrimaryPrincipal();
        //3.建立授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //4.设置 角色信息和权限信息
        info.setRoles(userService.getRolesByUser(user));
        info.setStringPermissions(userService.getPermissionsByUser(user));

        System.out.println("###:"+user);


        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("==========="+token);
        if (userService == null) {
            System.out.println("========userService is null");
        }

        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {
            //找不到账号
            throw new UnknownAccountException();
        }

        if (user.isLocked()) {
            //账号被锁定
            throw new LockedAccountException();
        }

//        SimpleAuthenticationInfo authenticationInfo= new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
        //第一个参数传入用户信息 而不是用户名时 doGetAuthorizationInfo 可以获得用户信息 而不必再次读取数据库
        SimpleAuthenticationInfo authenticationInfo= new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getCredentialsSalt()));
        return authenticationInfo;
    }

}
