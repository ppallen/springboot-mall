package com.allen.springbootmall.service.impl;

import com.allen.springbootmall.dao.UserDao;
import com.allen.springbootmall.dto.UserRegisterRequest;
import com.allen.springbootmall.model.User;
import com.allen.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
