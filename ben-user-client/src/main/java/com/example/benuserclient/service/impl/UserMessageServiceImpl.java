package com.example.benuserclient.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.ben.model.User;
import com.example.benuserclient.dao.UserRegisterDao;
import com.example.benuserclient.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangben
 * @Description: 用户信息接口
 * @Date: 2020/8/11 0:19
 * @Version: 1.0
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserRegisterDao userRegisterDao;

    @Override
    public User findUserByUsername(String username) {
        return userRegisterDao.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username).last(" limit 1"));
    }
}
