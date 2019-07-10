package com.yueking.core.service;

import com.yueking.core.entity.SysDict;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SysDictService {
    void addRootDict(String dictKey,String dictValue);

//    @Cacheable(value = "dict",key ="#dict.id")
    SysDict findDictById(String dictKey,String dictType);

    void addSubDict(String dictKey, String dictValue, SysDict parentDict);

//    @CacheEvict(cacheNames = "dictCache",key = "'sysDict'")
    void addDict(SysDict dict);

//    @Cacheable(cacheNames = "dictCache",key = "'sysDict'",sync = true)
    List<SysDict> getAllRootDict();

}
