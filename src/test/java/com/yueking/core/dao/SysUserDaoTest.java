package com.yueking.core.dao;

import com.yueking.BaseTest;
import com.yueking.core.entity.SysPermissionsEntity;
import com.yueking.core.entity.SysRolesEntity;
import com.yueking.core.entity.SysUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SysUserDaoTest extends BaseTest {
    @Autowired
    SysUserDao userDao;

    @Autowired
    SysRoleDao roleDao;

    @Autowired
    SysPermissionDao permissionDao;

    //    @Test(dependsOnMethods = "testDeleteUser")
    @Test
    public void testAddSysUserAndRoles() {
        SysUsersEntity usersEntity = new SysUsersEntity();
        usersEntity.setLocked(false);
        usersEntity.setPassword("12354");
        usersEntity.setUsername("yuewu1");
        usersEntity.setSalt("salt");

        SysRolesEntity rolesEntity = new SysRolesEntity();
        rolesEntity.setAvailable(true);
        rolesEntity.setDescription("desc");
        rolesEntity.setRole("roleName");

        Set<SysRolesEntity> rolesEntitySet = new HashSet<>();
        rolesEntitySet.add(rolesEntity);

        usersEntity.setRoles(rolesEntitySet);

        userDao.save(usersEntity);
    }

    @Test
    public void testFindById() {
        Optional<SysUsersEntity> result = userDao.findById(43l);
        SysUsersEntity user = result.get();
        System.out.println("user:" + user);
    }

    @Test(dependsOnMethods = "testFindById")
    public void testUpdateUser() {
        Optional<SysUsersEntity> result = userDao.findById(43l);
        SysUsersEntity user = result.get();
        System.out.println("user:" + user);

        SysRolesEntity rolesEntity = new SysRolesEntity();
        rolesEntity.setAvailable(true);
        rolesEntity.setDescription("desc");
        rolesEntity.setRole("role1");

        Set<SysRolesEntity> roles = user.getRoles();
        roles.add(rolesEntity);

        user.setRoles(roles);

        userDao.save(user);
    }

    @Test
    public void testDeleteUser() {
        userDao.deleteAll();
    }

    @Test
    public void testUpdateUserAddRolesAll() {
        List<SysRolesEntity> rolesEntityList = roleDao.findAll();
        Optional<SysUsersEntity> result = userDao.findById(52l);
        SysUsersEntity user = result.get();

        user.setRoles(new HashSet<>(rolesEntityList));

        userDao.save(user);
    }

    @Test
    public void testAddRoles() {
        SysRolesEntity rolesEntity = new SysRolesEntity();
        rolesEntity.setAvailable(true);
        rolesEntity.setDescription("desc");
        rolesEntity.setRole("role1");

        roleDao.save(rolesEntity);
    }

    @Test
    public void testAddUser() {
        SysUsersEntity user = new SysUsersEntity();
        user.setLocked(false);
        user.setPassword("123");
        user.setUsername("admin");
        user.setSalt("salt");

        userDao.save(user);
    }

    @Test
    public void testAddPermissions() {
        SysPermissionsEntity entity = new SysPermissionsEntity();
        entity.setAvailable(true);
        entity.setDescription("P_Desc");
        entity.setPermission("permission2");

        permissionDao.save(entity);

    }

    @Test
    public void testUpdateAddRoleAllPermissions() {
        List<SysPermissionsEntity> permissionsEntityList = permissionDao.findAll();

        Optional<SysRolesEntity> result = roleDao.findById(50l);
        SysRolesEntity roles = result.get();

        System.out.println("role:"+roles);
        System.out.println("permissions:"+permissionsEntityList);

        roles.setPermissions(new HashSet<>(permissionsEntityList));

        roleDao.save(roles);

    }
}