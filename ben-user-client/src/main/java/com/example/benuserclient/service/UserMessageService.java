package com.example.benuserclient.service;

import com.example.ben.model.User;

/**
 * @Author: wangben
 * @Description: 用户信息接口
 * @Date: 2020/8/11 0:19
 * @Version: 1.0
 */
public interface UserMessageService {

    /**
     * 根据用户名查询详细信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
