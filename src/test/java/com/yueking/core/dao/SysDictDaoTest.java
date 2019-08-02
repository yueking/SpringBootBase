package com.yueking.core.dao;

import com.yueking.BaseTest;
import com.yueking.core.entity.SysDict;
import com.yueking.core.entity.id.SysDictKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class SysDictDaoTest extends BaseTest {
    @Autowired
    SysDictDao dictDao;

    @Test(dependsOnMethods = "testRemove")
    public void testAddDict() {
        SysDictKey key = new SysDictKey();
        key.setDictType("XB");
        key.setDictKey("XB");
        SysDict dictRoot = new SysDict();
        dictRoot.setId(key);
        dictRoot.setDictValue("value");
        dictRoot.setRoot(true);
        dictRoot.setLevel(0);

        SysDict d1 = new SysDict();
        d1.setId(new SysDictKey("1","XB"));
        d1.setDictValue("男");

        SysDict d2 = new SysDict();
        d2.setId(new SysDictKey("2","XB"));
        d2.setDictValue("女");

        Set list = new HashSet();
        list.add(d1);
        list.add(d2);

        dictRoot.setSubDictList(list);




        dictDao.save(dictRoot);
    }

    @Test
    public void testRemove() {
        dictDao.deleteAll();
    }

    @Test
    public void testOneDict() {
        SysDict dict = new SysDict("SF","是否");
        dictDao.save(dict);

        SysDict d1 = new SysDict("1","是",dict);
        dictDao.save(d1);

    }
}