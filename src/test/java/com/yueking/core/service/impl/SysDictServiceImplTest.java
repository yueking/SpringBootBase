package com.yueking.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.yueking.BaseTest;
import com.yueking.core.entity.SysDict;
import com.yueking.core.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

public class SysDictServiceImplTest extends BaseTest {
    @Autowired
    SysDictService dictService;

    @Test
    public void testAddRootDict() throws Exception {
        dictService.addRootDict("XBDM", "性别代码");
//        dictService.addRootDict("SFDM","是否代码");
    }

    @Test
    public void testGetByID() {
        System.out.println("-------------");
        SysDict dict = dictService.findDictById("410100", "QHDM");
        dict = dictService.findDictById("410100", "QHDM");
        System.out.printf("dict:" + dict);
        dict.setDictValue("郑州市区1");
        dictService.addDict(dict);
        dict = dictService.findDictById("410100", "QHDM");
        System.out.printf("dict:" + dict);
        dict = dictService.findDictById("410100", "QHDM");
        System.out.printf("dict:" + dict);

    }

    @Test
    public void testFindDictByID() {
        SysDict dict = dictService.findDictById("410201", "QHDM");
        System.out.printf("dict:" + dict);

    }

    @Test
    public void testAddSubDict() {
        SysDict rootDict = dictService.findDictById("XBDM", "XBDM");
        dictService.addSubDict("1", "男", rootDict);
        dictService.addSubDict("2", "女", rootDict);

    }

    @Test
    public void testAddDictAll() {
        SysDict root = new SysDict("SFDM", "是否代码");


        SysDict s1 = new SysDict("1", "是", root);
        SysDict s2 = new SysDict("0", "否", root);


        System.out.println("=====:" + root.getSubDictList().size());

//        root.getSubDictList().add(s1);
//        root.getSubDictList().add(s2);
        dictService.addDict(root);
    }

    @Test
    public void testAddDictTree() {
        SysDict root = new SysDict("QHDM", "区划代码");
        SysDict d1 = new SysDict("410000", "河南省", root);
        SysDict d2 = new SysDict("410100", "郑州市", d1);
        SysDict d3 = new SysDict("410103", "郑州市二七区", d2);

        SysDict d4 = new SysDict("410200", "开封市", root);
        SysDict d5 = new SysDict("410201", "开封市老城", d4);

        dictService.addDict(root);
    }

    @Test
    public void testGetAllRootDict() {
        System.out.println("=====================");
        List<SysDict> rootDict = dictService.getAllRootDict();
        System.out.println("=====================");
        rootDict = dictService.getAllRootDict();
        rootDict = dictService.getAllRootDict();
        rootDict = dictService.getAllRootDict();
        System.out.println("=====================");
        for (SysDict dict : rootDict) {
            System.out.println("==rootDict==" + dict);
            System.out.println(JSON.toJSON(dict));
        }
    }
}