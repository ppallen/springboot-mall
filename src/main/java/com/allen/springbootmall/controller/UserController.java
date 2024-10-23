package com.allen.springbootmall.controller;

import com.allen.springbootmall.dto.UserLoginRequest;
import com.allen.springbootmall.dto.UserRegisterRequest;
import com.allen.springbootmall.model.User;
import com.allen.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //request body
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        //創建
        Integer userId =  userService.register(userRegisterRequest);
        //取得
        User user  = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        //User user = userService.login(userLoginRequest);
        String token = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }


}
