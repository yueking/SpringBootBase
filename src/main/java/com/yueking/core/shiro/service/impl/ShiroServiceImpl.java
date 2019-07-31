package com.yueking.core.shiro.service.impl;

import com.yueking.core.shiro.entity.PermissionResource;
import com.yueking.core.shiro.service.PermissionResourceService;
import com.yueking.core.shiro.service.ShiroService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    PermissionResourceService permissionResourceService;
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        List<PermissionResource> resources = permissionResourceService.allPermissionResources();
        for (PermissionResource resource : resources) {
            filterChainDefinitionMap.put(resource.getUrl(),resource.getPermissionInit());
        }
        return filterChainDefinitionMap;
    }

    @Override
    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            defaultFilterChainManager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());

            Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : filterChainDefinitionMap.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                defaultFilterChainManager.createChain(url,chainDefinition);
            }
            System.out.println("权限更新成功!");


        }

    }
}
