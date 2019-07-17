package com.yueking.core.dao;

import com.yueking.BaseTest;
import com.yueking.core.entity.shrio.Permission;
import com.yueking.core.entity.shrio.Role;
import com.yueking.core.entity.shrio.User;
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
        User usersEntity = new User();
        usersEntity.setLocked(false);
        usersEntity.setPassword("12354");
        usersEntity.setUsername("yuewu1");
        usersEntity.setSalt("salt");

        Role rolesEntity = new Role();
        rolesEntity.setAvailable(true);
        rolesEntity.setDescription("desc");
        rolesEntity.setRole("roleName");

        Set<Role> rolesEntitySet = new HashSet<>();
        rolesEntitySet.add(rolesEntity);

        usersEntity.setRoles(rolesEntitySet);

        userDao.save(usersEntity);
    }

    @Test
    public void testFindById() {
        Optional<User> result = userDao.findById(43l);
        User user = result.get();
        System.out.println("user:" + user);
    }

    @Test(dependsOnMethods = "testFindById")
    public void testUpdateUser() {
        Optional<User> result = userDao.findById(43l);
        User user = result.get();
        System.out.println("user:" + user);

        Role rolesEntity = new Role();
        rolesEntity.setAvailable(true);
        rolesEntity.setDescription("desc");
        rolesEntity.setRole("role1");

        Set<Role> roles = user.getRoles();
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
        List<Role> rolesEntityList = roleDao.findAll();
        Optional<User> result = userDao.findById(52l);
        User user = result.get();

        user.setRoles(new HashSet<>(rolesEntityList));

        userDao.save(user);
    }

    @Test
    public void testAddRoles() {
        Role rolesEntity = new Role();
        rolesEntity.setAvailable(true);
        rolesEntity.setDescription("desc");
        rolesEntity.setRole("role1");

        roleDao.save(rolesEntity);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setLocked(false);
        user.setPassword("123");
        user.setUsername("admin");
        user.setSalt("salt");

        userDao.save(user);
    }

    @Test
    public void testAddPermissions() {
        Permission entity = new Permission();
        entity.setAvailable(true);
        entity.setDescription("P_Desc");
        entity.setPermission("permission2");

        permissionDao.save(entity);

    }

    @Test
    public void testUpdateAddRoleAllPermissions() {
        List<Permission> permissionsEntityList = permissionDao.findAll();

        Optional<Role> result = roleDao.findById(50l);
        Role roles = result.get();

        System.out.println("role:"+roles);
        System.out.println("permissions:"+permissionsEntityList);

        roles.setPermissions(new HashSet<>(permissionsEntityList));

        roleDao.save(roles);

    }
}