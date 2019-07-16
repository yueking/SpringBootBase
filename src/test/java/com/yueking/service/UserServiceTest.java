package com.yueking.service;

import com.yueking.BaseTest;
import com.yueking.core.entity.SysPermissionsEntity;
import com.yueking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserServiceTest extends BaseTest {
    @Autowired
    UserService userService;

    @Test
    public void testDeleteAll(){
        userService.deleteAll();
    }

    @Test(dependsOnMethods = "testDeleteAll")
    public void testAddUser() {
        User user = new User();
        user.setName("yw");
        user.setUsername("yueking");
        user.setEmail("yueking@gmail.com");

        Integer id = userService.addUser(user);
        System.out.println("=====insert user:"+id);
    }

    @Test(dependsOnMethods = "testAddUser")
    public void testFindAll(){
        List<User> userList = userService.findAll();
        System.out.println("size:"+userList.size());
        for (User user : userList) {
            System.out.println("id:"+user.getId()+"\t"+user.getName());
        }
        Assert.assertEquals(userList.size(),1);
    }

    @Test
    public void testFindAllSort(){
        List<User> userList = userService.findAll("name");
        System.out.println("size:"+userList.size());
        Assert.assertEquals(userList.size(),0);
        for (User user : userList) {
            System.out.println("id:"+user.getId()+"\t"+user.getName());
        }
    }

    @Test
    public void testGetAllUser() {
        List<User> userList = userService.getAllUser(1, 2);
        System.out.println("size:"+userList.size());
        for (User user : userList) {
            System.out.println("id:"+user.getId()+"\t"+user.getName());
        }
    }

    @Test(dependsOnMethods = "testAddUser")
    public void testFindByName() {
        String name = "yw";
        User user = userService.findByName(name);
        Assert.assertEquals(user.getName(),"yw");
        System.out.println("=====findByName:"+user.getName());
    }

    @Test(dependsOnMethods = "testAddUser")
    public void testFindByUsername() {
        String username = "yueking";
        User user = userService.findByUsername(username);
        Assert.assertEquals(user.getName(),"yw");
        System.out.println("=====findByUsername:"+user.getName());
    }

    @Test(dependsOnMethods = "testAddUser")
    public void testExistsByUsername() {
        String username = "yueking";
        Boolean exists = userService.existsByUsername(username);
        Assert.assertTrue(exists);
        System.out.println("=====exists username:"+exists);
    }

    @Test
    public void testGetByExample() {
        List<User> list = userService.getByExample("yw");
        for (User user : list) {
            System.out.println("userID:"+user.getId()+"\t"+user.getName()+"\t"+user.getUsername());
        }
        System.out.println("==============size:"+list.size());
    }

}