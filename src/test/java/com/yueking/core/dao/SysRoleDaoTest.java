package com.yueking.core.dao;

import com.yueking.BaseTest;
import com.yueking.core.entity.SysRolesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SysRoleDaoTest extends BaseTest {
    @Autowired
    SysRoleDao sysRoleDao;

    @Test
    public void testAddRole() {
        SysRolesEntity rolesEntity = new SysRolesEntity();
        rolesEntity.setAvailable(true);
        rolesEntity.setDescription("desc");
        rolesEntity.setRole("roleName");

        sysRoleDao.save(rolesEntity);
    }
}