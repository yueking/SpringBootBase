package com.yueking.core.shiro;

import com.yueking.BaseTest;
import com.yueking.core.dao.SysPermissionDao;
import com.yueking.core.dao.SysRoleDao;
import com.yueking.core.dao.SysUserDao;
import com.yueking.core.entity.SysPermissionsEntity;
import com.yueking.core.entity.SysRolesEntity;
import com.yueking.core.entity.SysUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class ShiroDataTest extends BaseTest {
    @Autowired
    SysPermissionDao permissionDao;

    @Autowired
    SysRoleDao roleDao;

    @Autowired
    SysUserDao userDao;

    @Test
    public void testPermissionAdd() {
        for (int i = 1; i < 4; i++) {
            SysPermissionsEntity entity = new SysPermissionsEntity();
            entity.setPermission("permission" + i);
            entity.setDescription("desc");
            entity.setAvailable(true);

            permissionDao.save(entity);
        }

    }

    @Test(dependsOnMethods = "testPermissionAdd")
    public void testRoleAdd() {
        List<SysPermissionsEntity> list = permissionDao.findAll();

        for (int i = 1; i < 3; i++) {
            SysRolesEntity entity = new SysRolesEntity();
            entity.setRole("role"+i);
            entity.setName("role"+i);
            entity.setDescription("desc");
            entity.setAvailable(true);

            entity.setPermissions(new HashSet<>(list));

            roleDao.save(entity);
        }

    }

    @Test(dependsOnMethods = {"testPermissionAdd","testRoleAdd"})
    public void testUserAdd() {
        List<SysRolesEntity> list = roleDao.findAll();
        for (int i = 1; i < 3; i++) {
            SysUsersEntity entity = new SysUsersEntity();
            entity.setUsername("user"+i);
            entity.setPassword("user"+i);
            entity.setSalt("salt");

            entity.setRoles(new HashSet<>(list));

            userDao.save(entity);
        }
    }



    @Test
    public void testRoleUpdatePermission() {
        List<SysPermissionsEntity> list = permissionDao.findAll();
        Optional<SysRolesEntity> result = roleDao.findById(67l);
        SysRolesEntity roles = result.get();
        System.out.println("roles:" + roles);

        roles.setPermissions(new HashSet<>(list));

        roleDao.saveAndFlush(roles);
    }

    @Test
    public void testUserUpdateRoles() {
        List<SysRolesEntity> list = roleDao.findAll();
        Optional<SysUsersEntity> result = userDao.findById(69l);
        SysUsersEntity user = result.get();

        user.setRoles(new HashSet<>(list));
        System.out.println("user:" + user);
        userDao.saveAndFlush(user);
    }

    @Test
    public void testUserDelete() {
        userDao.deleteById(103l);
    }

    @Test
    public void testRoleDelete(){
        roleDao.deleteById(101l);
    }

    @Test
    public void testUserFindById() {
        Optional<SysUsersEntity> result = userDao.findById(102l);
        SysUsersEntity user = result.get();
        System.out.println("user:"+user);
    }
}
