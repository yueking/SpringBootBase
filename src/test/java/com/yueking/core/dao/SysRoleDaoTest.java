package com.yueking.core.dao;

import com.yueking.BaseTest;
import com.yueking.core.entity.shrio.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class SysRoleDaoTest extends BaseTest {
    @Autowired
    SysRoleDao sysRoleDao;

    @Test
    public void testAddRole() {
        Role rolesEntity = new Role();
        rolesEntity.setAvailable(true);
        rolesEntity.setDescription("desc");
        rolesEntity.setRole("roleName");

        sysRoleDao.save(rolesEntity);
    }
}