package com.example.benauthservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: wangben
 * @Description:
 * @Date: 2020/7/29 14:20
 * @Version: 1.0
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserAuthService userAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userAuthService.loadUserByUsername(username);
    }
}
