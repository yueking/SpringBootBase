package com.yueking.core.dao;

import com.yueking.BaseTest;
import com.yueking.core.entity.SysUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class SysUserDaoTest extends BaseTest {
    @Autowired
    SysUserDao sysUserDao;

    @Test
    public void testAddSysUser() {
        SysUsersEntity usersEntity = new SysUsersEntity();
        usersEntity.setLocked(false);
        usersEntity.setPassword("12354");
        usersEntity.setUsername("yuewu");
        usersEntity.setSalt("salt");

        sysUserDao.save(usersEntity);
    }
}