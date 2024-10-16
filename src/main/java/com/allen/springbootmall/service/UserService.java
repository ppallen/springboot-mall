package com.allen.springbootmall.service;

import com.allen.springbootmall.dto.UserRegisterRequest;
import com.allen.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

}
