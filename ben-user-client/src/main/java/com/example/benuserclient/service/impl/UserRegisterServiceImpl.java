package com.example.benuserclient.service.impl;

import com.example.ben.convert.UserConvert;
import com.example.ben.model.User;
import com.example.ben.param.user.RegisterParam;
import com.example.benuserclient.dao.UserRegisterDao;
import com.example.benuserclient.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangben
 * @Description: 用户注册的实现
 * @Date: 2020/8/10 14:24
 * @Version: 1.0
 */
@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserRegisterDao userRegisterDao;

    @Override
    public Long register(RegisterParam param) {
        User user = UserConvert.INSTANCE.convertRegister(param);
        userRegisterDao.insert(user);
        return user.getId();
    }
}
