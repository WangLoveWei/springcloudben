package com.example.benuserclient.service.impl;

import com.example.ben.model.UserToken;
import com.example.benuserclient.dao.UserTokenDao;
import com.example.benuserclient.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangben
 * @Description:
 * @Date: 2020/8/11 11:55
 * @Version: 1.0
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public Long saveToken(String token, String username, Long userId) {
        UserToken userToken = new UserToken(username, token, userId);
        userTokenDao.insert(userToken);
        return userToken.getId();
    }
}
