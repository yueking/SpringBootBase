package com.yueking.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.yueking.BaseTest;
import com.yueking.core.entity.SysDict;
import com.yueking.core.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.*;

public class SysDictServiceImplTest extends BaseTest {
    @Autowired
    SysDictService dictService;

    @Test
    public void testAddRootDict() throws Exception {
        dictService.addRootDict("XBDM","性别代码");
//        dictService.addRootDict("SFDM","是否代码");
    }

    @Test
    public void testGetByID() {
        SysDict dict = dictService.findDictById("XBDM", "XBDM");
        System.out.printf("dict:"+dict);

    }

    @Test
    public void testAddDict() {
        SysDict rootDict = dictService.findDictById("XBDM", "XBDM");
        dictService.addDict("1","男",rootDict);
        dictService.addDict("2","女",rootDict);

    }

    @Test
    public void testAddDictAll(){
//        SysDict root = new SysDict("SFDM","是否代码","SFDM");
//        root.setRoot(true);
//        root.setLevel(0);

//        SysDict d1 = new SysDict("1","是",root.getId().getDictType());
//        SysDict d2 = new SysDict("2","否",root.getId().getDictType());


//        if (root.getSubDictList() == null) {
//            root.setSubDictList(new HashSet<>());
//            System.out.println("===========null");
//        }

//        root.getSubDictList().add(d1);
//        root.getSubDictList().add(d2);

//        System.out.println("===json:"+root);

//        dictService.addDict(root);
    }

    @Test
    public void testGetAllRootDict() {
        List<SysDict> rootDict = dictService.getAllRootDict();
        for (SysDict dict : rootDict) {
            System.out.println("==rootDict=="+dict);
            System.out.println(JSON.toJSON(dict));
        }
    }
}