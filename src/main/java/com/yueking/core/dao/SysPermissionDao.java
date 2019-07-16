package com.yueking.core.dao;

import com.yueking.core.entity.SysPermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionDao extends JpaRepository<SysPermissionsEntity,Long> {
}
