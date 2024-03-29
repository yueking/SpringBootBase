package com.yueking.core.service;

import com.yueking.core.entity.SysDict;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SysDictService {
//    @CacheEvict(cacheNames = "dictCache",key = "#dict.id")
    @CacheEvict(cacheNames = "dictCache",key = "#dict.id.dictKey+#dict.id.dictType")
    void addDict(SysDict dict);

    @CacheEvict(cacheNames = "dictCache",key = "#dictType+#dictType")
    void addRootDict(String dictType,String dictValue);

    @CacheEvict(cacheNames = "dictCache",key = "#dictKey+#parentDict.dictType")
    void addSubDict(String dictKey, String dictValue, SysDict parentDict);

    @Cacheable(cacheNames = "dictCache",key ="#dictKey+#dictType",unless="#result == null")
    SysDict findDictById(String dictKey,String dictType);

    @Cacheable(cacheNames = "dictCache",key = "'sysDict'")
    List<SysDict> getAllRootDict();

    @CacheEvict(cacheNames = "dictCache",key ="#dictKey+#dictType")
    void deleteById(String dictKey, String dictType);

}
