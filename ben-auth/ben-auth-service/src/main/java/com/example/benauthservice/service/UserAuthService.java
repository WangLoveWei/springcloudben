package com.example.benauthservice.service;

import com.example.benauthservice.dto.TokenInfo;
import com.example.benauthservice.param.UserParam;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author: wangben
 * @Description: 用户认证的相关服务的接口
 * @Date: 2020/8/7 10:10
 * @Version: 1.0
 */
public interface UserAuthService {
    /**
     * 通过username和password登陆
     * @param userParam
     * @return
     */
    TokenInfo loginByUsername(UserParam userParam);

    /**
     * 获取User
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

}
