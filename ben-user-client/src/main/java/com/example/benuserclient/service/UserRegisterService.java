package com.example.benuserclient.service;

import com.example.ben.param.user.RegisterParam;

/**
 * @Author: wangben
 * @Description: 用户注册的服务层
 * @Date: 2020/8/10 14:23
 * @Version: 1.0
 */
public interface UserRegisterService {


    /**
     * 用户注册的方法
     * @param param
     * @return
     */
    Long register(RegisterParam param);
}
