package com.yueking.core.shiro.service;

import com.yueking.BaseTest;
import com.yueking.core.shiro.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PermissionServiceTest extends BaseTest {
    @Autowired
    PermissionService permissionService;

    @Test
    public void testCreatePermission() {
        Permission p1 = new Permission();
        p1.setPermission("p2");
        p1.setDescription("desc");
        p1.setAvailable(true);

        permissionService.createPermission(p1);
    }

    @Test
    public void testDeletePermission() {
        permissionService.deletePermission(8l);
    }
}