package com.allen.springbootmall.dao;

import com.allen.springbootmall.dto.UserRegisterRequest;
import com.allen.springbootmall.model.User;

public interface UserDao {

    User getUserByEmail(String email);

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);

}
