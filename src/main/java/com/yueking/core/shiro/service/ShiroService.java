package com.yueking.core.shiro.service;

import java.util.Map;

public interface ShiroService {
    /**
     * 权限配置读取
     * @return
     */
    Map<String,String>loadFilterChainDefinitions();

    /**
     * 动态更新权限
     */
    void updatePermission();

}
