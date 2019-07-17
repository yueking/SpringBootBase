package com.yueking.core.dao;

import com.yueking.BaseTest;
import com.yueking.core.shiro.dao.RoleDao;
import com.yueking.core.shiro.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class RoleDaoTest extends BaseTest {
    @Autowired
    RoleDao roleDao;

    @Test
    public void testAddRole() {
        Role rolesEntity = new Role();
        rolesEntity.setAvailable(true);
        rolesEntity.setDescription("desc");
        rolesEntity.setRole("roleName");

        roleDao.save(rolesEntity);
    }
}