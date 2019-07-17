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
        SysPermissionsEntity entity = new SysPermissionsEntity();
        entity.setPermission("permission3");
        entity.setDescription("desc");
        entity.setAvailable(true);

        permissionDao.save(entity);
    }

    @Test
    public void testRoleAdd() {
        SysRolesEntity entity = new SysRolesEntity();
        entity.setRole("role2");
        entity.setName("role2");
        entity.setDescription("desc");
        entity.setAvailable(true);

        roleDao.save(entity);
    }

    @Test
    public void testUserAdd() {
        SysUsersEntity entity = new SysUsersEntity();
        entity.setUsername("admin");
        entity.setPassword("admin");
        entity.setSalt("salt");

        userDao.save(entity);
    }

    @Test
    public void testPermissionsFindAll() {
        List<SysPermissionsEntity> list = permissionDao.findAll();
        System.out.println("permission:"+list);
    }

    @Test
    public void testRoleFindAll() {
        List<SysRolesEntity> list = roleDao.findAll();
        System.out.println("Roles:"+list);
    }

    @Test
    public void testUserFindAll() {
        List<SysUsersEntity> list = userDao.findAll();
        System.out.println("users:"+list);
    }

    @Test
    public void testRoleUpdatePermission() {
        List<SysPermissionsEntity> list = permissionDao.findAll();
        Optional<SysRolesEntity> result = roleDao.findById(60l);
        SysRolesEntity roles = result.get();
        System.out.println("roles:"+roles);

        roles.setPermissions(new HashSet<>(list));

        roleDao.saveAndFlush(roles);
    }

    @Test
    public void testUserUpdateRoles() {
        List<SysRolesEntity> list = roleDao.findAll();
        Optional<SysUsersEntity> result = userDao.findById(62l);
        SysUsersEntity user = result.get();

        user.setRoles(new HashSet<>(list));
        System.out.println("user:"+user);
        userDao.saveAndFlush(user);
    }
}
