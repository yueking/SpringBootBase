package com.yueking.core.shiro.dao;

import com.yueking.BaseTest;
import com.yueking.core.shiro.entity.Module;
import com.yueking.core.shiro.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Optional;

public class ModuleDaoTest extends BaseTest {
    @Autowired
    ModuleDao moduleDao;

    @Test
    public void testAdd() {
        //todo module 自动关联级别
        Module module = new Module("module1");
        Resource resource = new Resource();
        resource.setName("resource1");
        resource.setType("type");
        module.setResource(resource);

        moduleDao.saveAndFlush(module);

        Module module2 = new Module("module2",module);
        Resource resource1 = new Resource();
        resource1.setName("resource2");
        resource1.setType("type2");

        module2.setResource(resource1);

        moduleDao.saveAndFlush(module2);


    }

    @Test
    public void testGetByID() {
        Optional<Module> module = moduleDao.findById("402881026c478963016c478972460000");
        Module module1 = module.get();
        System.out.println(module1);

        Optional<Module> optional = moduleDao.findById("402881026c478963016c478972710001");
        Module module2 = optional.get();
        System.out.println(module2);

//        module1.setName("moduleOne");
//        moduleDao.saveAndFlush(module1);
        module2.setName("moduleTwo");

        moduleDao.saveAndFlush(module2);
    }

    @Test
    public void testAddNew() {
        Module module = new Module("moduleRoot");
        Resource  resource =new Resource();
        resource.setName("r1");

        module.setResource(resource);

        moduleDao.saveAndFlush(module);
    }
}