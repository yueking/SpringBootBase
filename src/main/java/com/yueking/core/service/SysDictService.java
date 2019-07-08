package com.yueking.core.service;

import com.yueking.core.entity.SysDict;

import java.util.List;

public interface SysDictService {
    void addRootDict(String dictKey,String dictValue);

    SysDict findDictById(String dictKey,String dictType);

    void addDict(String dictKey, String dictValue, SysDict parentDict);

    void addDict(SysDict dict);

    List<SysDict> getAllRootDict();

}
