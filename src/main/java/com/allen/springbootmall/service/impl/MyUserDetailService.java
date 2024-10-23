package com.allen.springbootmall.service.impl;

import com.allen.springbootmall.dao.UserDao;
import com.allen.springbootmall.model.MyUserDetails;
import com.allen.springbootmall.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(MyUserDetailService.class);

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.getUserByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);


    }
}
