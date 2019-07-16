package com.yueking.core.dao;

import com.yueking.core.entity.SysUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao extends JpaRepository<SysUsersEntity,Long> {
}
