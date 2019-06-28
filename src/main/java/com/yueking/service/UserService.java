package com.yueking.service;

import com.yueking.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 增加 user
     *
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 查询所有对象
     *
     * @return
     */
    List<User> findAll();

    /**
     * 查询所有对象 并进行排序
     *
     * @return
     */
    List<User> findAll(String sort);

    List<User> getAllUser(int page, int size);

    User findByName(String name);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    List<User> getByExample(String name);

    void deleteAll();

    void deleteById(Integer id);

}