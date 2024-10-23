package com.allen.springbootmall.service.impl;

import com.allen.springbootmall.dao.UserDao;
import com.allen.springbootmall.dto.UserLoginRequest;
import com.allen.springbootmall.dto.UserRegisterRequest;
import com.allen.springbootmall.model.User;
import com.allen.springbootmall.service.UserService;
import com.allen.springbootmall.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public String login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //檢查 user是否存在
        if(user ==null){
            log.warn("該 email {} 並未註冊",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用MD5生成密碼的雜湊值
        String hashPassword = DigestUtils.md5DigestAsHex(
                userLoginRequest.getPassword().getBytes());

        //比較密碼
//        if(user.getPassword().equals(hashPassword)){
//            return ResponseEntity.ok().body(user).getBody();
//        }else{
//            log.warn("email {} 的密碼錯誤",user.getEmail());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
        try {
            // 使用 AuthenticationManager 進行身份驗證
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userLoginRequest.getEmail(), userLoginRequest.getPassword());

            // 認證過程將使用 UserDetailsService 加載用戶信息，並使用配置的密碼編碼器進行密碼驗證
            authentication = authenticationManager.authenticate(authentication);

            // 認證成功後生成 JWT token
            String token = jwtUtil.createToken(authentication);

            // 返回 JWT token
            return token;

        }
//        catch (UsernameNotFoundException e) {
//            // 用戶名不存在，捕獲該異常並返回錯誤
//            log.warn("此 Email {} 尚未註冊", userLoginRequest.getEmail());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "此 Email 尚未註冊");
//
//        }
        catch (BadCredentialsException e) {
            // 密碼不正確，捕獲該異常
            log.warn("密碼不正確: {}", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密碼不正確");

        } catch (Exception e) {
            // 捕捉其他未預期的異常
            log.error("登入失敗: {}", userLoginRequest.getEmail(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "登入失敗，請稍後再試");
        }



    }


    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //Check 註冊的Email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if(user != null){
            log.warn("該 email {} 已被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用MD5生成密碼的雜湊表
        String hashPassword = DigestUtils.md5DigestAsHex(
                userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashPassword);

        //Create account
        return userDao.createUser(userRegisterRequest);
    }

}
