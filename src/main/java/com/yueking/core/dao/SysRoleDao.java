package com.yueking.core.dao;

import com.yueking.core.entity.shrio.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleDao extends JpaRepository<Role,Long> {
}
