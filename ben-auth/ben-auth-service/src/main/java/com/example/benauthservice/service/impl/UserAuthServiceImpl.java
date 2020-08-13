package com.example.benauthservice.service.impl;

import com.example.ben.model.Permission;
import com.example.ben.model.User;
import com.example.benauthservice.dto.TokenInfo;
import com.example.benauthservice.feign.UserConsumer;
import com.example.benauthservice.model.BenUserDetails;
import com.example.benauthservice.param.UserParam;
import com.example.benauthservice.service.UserAuthService;
import com.example.bencommon.common.exception.Asserts;
import com.example.bencommon.config.properties.JwtProperties;
import com.example.bencommon.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangben
 * @Description:
 * @Date: 2020/8/7 10:10
 * @Version: 1.0
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserConsumer userConsumer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public TokenInfo loginByUsername(UserParam userParam) {


        UserDetails userDetails = loadUserByUsername(userParam.getUsername());

        if (!passwordEncoder.matches(userParam.getPassword(), userDetails.getPassword())) {
            Asserts.fail("密码不正确");
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        BenUserDetails user = (BenUserDetails) userDetails;
        String token = jwtTokenUtil.generateToken(userDetails.getUsername(),user.getUser().getId());
        if (token == null) {
            Asserts.fail("用户名或密码错误");
        }
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setToken(token);
        tokenInfo.setTokenHead(jwtProperties.getHeader());
//        tokenInfo.setRole(user.getUser().getRole());
        tokenInfo.setUserId(user.getUser().getId());
        tokenInfo.setUsername(userParam.getUsername());
        userConsumer.saveToken(tokenInfo.getToken(),tokenInfo.getUsername(),tokenInfo.getUserId());
        return tokenInfo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
       User user = userConsumer.findUserByUsername(username);
        if (user == null) {
            Asserts.fail("用户名或密码错误");
        }

        List<Permission> permissionList = userConsumer.findResourceByUserId(user.getId());
       /* User user =new User();
        user.setUsername("wangben");
        user.setPassword("$2a$10$MSNW3daQEuvrbeVHLUJc.uQCZVKV6ot3mcxhDxANsYIUDVnApZnoC");
        List<Permission> permissionList =new ArrayList<>();*/
        return new BenUserDetails(user, permissionList);
    }
}
