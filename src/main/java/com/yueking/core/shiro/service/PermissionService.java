package com.yueking.core.shiro.service;


import com.yueking.core.shiro.entity.Permission;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface PermissionService {
    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
