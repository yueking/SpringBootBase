package com.yueking.core.shiro.dao;

import com.yueking.BaseTest;
import com.yueking.core.shiro.entity.PermissionResource;
import com.yueking.core.shiro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class PermissionResourceDaoTest extends BaseTest {
    @Autowired
    PermissionResourceDao resourceDao;
    @Autowired
    UserDao userDao;

    @Test
    public void testFindAll() {
        PermissionResource resource = new PermissionResource();
        resource.setPermissionInit("init");
        resource.setSort(7);
        resource.setUrl("url name");

        resourceDao.save(resource);
        List<PermissionResource> list = resourceDao.findAll();
        System.out.println("resource size:"+list.size());
        for (PermissionResource r : list) {
            System.out.println(r);
        }
//        List<User> userList = userDao.findAll();
//        for (User user : userList) {
//            System.out.println(user);
//        }
    }
}