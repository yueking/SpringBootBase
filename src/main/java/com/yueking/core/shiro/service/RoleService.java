package com.yueking.core.shiro.service;

import com.yueking.core.shiro.entity.Role;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface RoleService {
     Role createRole(Role role);
     void deleteRole(Long roleId);
    /**
     * 添加角色-权限之间关系
     */
     void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
     void unCorrelationPermissions(Long roleId, Long... permissionIds);//
}
