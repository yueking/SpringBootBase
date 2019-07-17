package com.yueking.core.dao;

import com.yueking.core.entity.shrio.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionDao extends JpaRepository<Permission,Long> {
}
