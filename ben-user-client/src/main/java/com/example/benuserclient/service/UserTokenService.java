package com.example.benuserclient.service;

/**
 * @Author: wangben
 * @Description: 用户token的相关接口
 * @Date: 2020/8/11 11:54
 * @Version: 1.0
 */
public interface UserTokenService {

    /**
     * 保存token的接口
     * @param token
     * @param username
     * @param userId
     * @return
     */
    Long saveToken(String token, String username, Long userId);
}
