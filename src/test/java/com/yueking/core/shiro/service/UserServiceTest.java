package com.yueking.core.shiro.service;

import com.yueking.BaseTest;
import com.yueking.core.shiro.entity.Permission;
import com.yueking.core.shiro.entity.Role;
import com.yueking.core.shiro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class UserServiceTest extends BaseTest {

    @Autowired
    UserService userService;

    @Test
    public void testCreateUser() {
    }

    @Test
    public void testChangePassword() {
    }

    @Test
    public void testCorrelationRoles() {
        userService.correlationRoles(8l,4l);
    }

    @Test
    public void testUnCorrelationRoles() {
    }

    @Test
    public void testFindByUsername() {
        User user = userService.findByUsername("yuekinger");
        System.out.println("user:"+user);
    }

    @Test
    public void testGetRolesByUser() {
    }

    @Test
    public void testGetPermissionsByUser() {
    }
}