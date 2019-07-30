package com.yueking.core.shiro.service;

import com.yueking.BaseTest;
import com.yueking.core.shiro.entity.PermissionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

public class PermissionResourceServiceTest extends BaseTest {
    @Autowired
    PermissionResourceService permissionResourceService;

    @Test
    public void testFindAllDesc() {
        List<PermissionResource> list = permissionResourceService.allPermissionResources();
        System.out.println("======="+list.size());
        for (PermissionResource resource : list) {
            System.out.println(resource);
        }

    }
}