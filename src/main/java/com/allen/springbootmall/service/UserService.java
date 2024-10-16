package com.allen.springbootmall.service;

import com.allen.springbootmall.dto.UserLoginRequest;
import com.allen.springbootmall.dto.UserRegisterRequest;
import com.allen.springbootmall.model.User;

public interface UserService {
    User login(UserLoginRequest userLoginRequest);

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

}
