package com.yueking.core.dao;

import com.yueking.BaseTest;
import com.yueking.core.entity.SysDict;
import com.yueking.core.entity.id.SysDictKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SysDictDaoTest extends BaseTest {
    @Autowired
    SysDictDao dictDao;

    @Test
    public void testAddDict() {
        SysDictKey key = new SysDictKey();
        key.setDictType("XB");
        key.setDictKey("XB");
        SysDict dict = new SysDict();
        dict.setId(key);
        dict.setDictValue("value");
        dict.setRoot(true);
        dict.setLevel(0);

        dictDao.save(dict);
    }
}