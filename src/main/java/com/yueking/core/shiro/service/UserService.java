package com.yueking.core.shiro.service;

import com.yueking.core.shiro.entity.Permission;
import com.yueking.core.shiro.entity.Role;
import com.yueking.core.shiro.entity.User;

import java.util.Set;

public interface UserService {
    User createUser(User user); //创建账户

    void changePassword(Long userId, String newPassword);//修改密码

    void correlationRoles(Long userId, Long... roleIds); //添加用户-角色关系

    void unCorrelationRoles(Long userId, Long... roleIds);// 移除用户-角色关系

    User findByUsername(String username);// 根据用户名查找用户

    Set<Role> findRoles(String username);// 根据用户名查找其角色

    Set<Permission> findPermissions(String username); //根据用户名查找其权限
}
