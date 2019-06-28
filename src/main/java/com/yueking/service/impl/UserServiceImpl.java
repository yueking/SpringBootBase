package com.yueking.service.impl;

import com.yueking.entity.User;
import com.yueking.repository.UserRepository;
import com.yueking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userDao;


    @Override
    public Integer addUser(User user) {
        userDao.save(user);
        Integer id = user.getId();
        return id;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAll(String sort) {
        return userDao.findAll(new Sort(Sort.Direction.DESC,sort));
    }

    @Override
    public List<User> getAllUser(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> userPage = userDao.findAll(pageRequest);

        int totalPages = userPage.getTotalPages();
        long count = userPage.getTotalElements();
        System.out.println("=====totalPages:"+ totalPages);
        System.out.println("=====count:"+ count);
        return userPage.getContent();
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public List<User> getByExample(String name) {
        User user = new User();
        user.setName(name);
//        user.setId(6);
        Example<User> example = Example.of(user);
        List<User> userList = userDao.findAll(example);
        return userList;
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }
}
