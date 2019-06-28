package com.yueking.core.dao;

import com.yueking.BaseTest;
import com.yueking.core.entity.Person;
import com.yueking.core.entity.id.PersonID;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class PersonDaoTest extends BaseTest {
    @Autowired
    PersonDao personDao;
    @Test
    public void testAdd() {
        System.out.println("======");
        PersonID id = new PersonID();
        id.setPersionId("pid");
        id.setGroupId("gid");
        Person person = new Person();
        person.setId(id);
        person.setName("yueking");

        personDao.save(person);
    }
}