package com.example.benauthservice.controller;

import com.example.ben.param.user.RegisterParam;
import com.example.benauthservice.dto.TokenInfo;
import com.example.benauthservice.feign.UserConsumer;
import com.example.benauthservice.param.UserParam;
import com.example.benauthservice.service.UserAuthService;
import com.example.bencommon.base.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangben
 * @Description: 用户注册的相关接口
 * @Date: 2020/8/7 10:05
 * @Version: 1.0
 */
@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserConsumer userConsumer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public CommonResult<TokenInfo> login(@RequestBody UserParam userParam) {
        TokenInfo tokenInfo = userAuthService.loginByUsername(userParam);
        return CommonResult.ok(tokenInfo);
    }

    @PostMapping("/register")
    public CommonResult<Long> register(@RequestBody RegisterParam param){
        String password = param.getPassword();
        String passwordEncode = passwordEncoder.encode(password);
        param.setPassword(passwordEncode);
        return userConsumer.register(param);
    }
}
