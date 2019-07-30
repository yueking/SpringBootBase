package com.yueking.core.shiro.service.impl;

import com.yueking.core.shiro.dao.PermissionResourceDao;
import com.yueking.core.shiro.entity.PermissionResource;
import com.yueking.core.shiro.service.PermissionResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PermissionResourceServiceImpl implements PermissionResourceService {
    @Autowired
    PermissionResourceDao permissionResourceDao;

    @Override
    public List<PermissionResource> allPermissionResources() {
        Sort sort = new Sort(Sort.Direction.DESC, "sort");
        return permissionResourceDao.findAll(sort);
    }
}
