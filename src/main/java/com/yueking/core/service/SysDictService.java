package com.yueking.core.service;

import com.yueking.core.entity.SysDict;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SysDictService {
    @CacheEvict(cacheNames = "dictCache",key = "#dict.id")
    void addDict(SysDict dict);

    @CacheEvict(cacheNames = "dictCache",key = "#dictKey")
    void addRootDict(String dictKey,String dictValue);

    @CacheEvict(cacheNames = "dictCache",key = "#dictKey")
    void addSubDict(String dictKey, String dictValue, SysDict parentDict);

//    @Cacheable(value = "dict",key ="#dict.id",unless="#result == null")
    @Cacheable(cacheNames = "dictCache",key ="#dictKey+#dictType")
    SysDict findDictById(String dictKey,String dictType);

    @Cacheable(cacheNames = "dictCache",key = "'sysDict'")
    List<SysDict> getAllRootDict();

}
