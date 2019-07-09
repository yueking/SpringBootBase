package com.yueking.core.service.impl;

import com.yueking.core.dao.SysDictDao;
import com.yueking.core.entity.SysDict;
import com.yueking.core.entity.id.SysDictKey;
import com.yueking.core.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SysDictServiceImpl implements SysDictService {
    @Autowired
    SysDictDao dictDao;

    @Override
    public void addRootDict(String dictKey, String dictValue) {
        SysDict dictRoot = new SysDict(dictKey, dictValue);
        dictDao.save(dictRoot);
    }

    @Override
    public SysDict findDictById(String dictKey, String dictType) {
        SysDictKey key = new SysDictKey();
        key.setDictKey(dictKey);
        key.setDictType(dictType);
        return dictDao.getOne(key);
    }

    @Override
    public void addSubDict(String dictKey, String dictValue, SysDict parentDict) {
        SysDict dict = new SysDict(dictKey, dictValue, parentDict);
        dictDao.save(dict);
    }

    @Override
    public void addDict(SysDict dict) {
        dictDao.save(dict);
    }

    @Override
    public List<SysDict> getAllRootDict() {
        return dictDao.findAllByRoot(true);
    }

}
