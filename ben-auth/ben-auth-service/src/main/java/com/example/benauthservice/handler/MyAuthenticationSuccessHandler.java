package com.example.benauthservice.handler;

import com.alibaba.fastjson.JSON;
import com.example.bencommon.base.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author: wangben
 * @Description: 认证成功的处理器
 * @Date: 2020/7/29 11:05
 * @Version: 1.0
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

   /* @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;*/


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSON(CommonResult.ok("登录成功")).toString());
        response.getWriter().flush();
    }
/*
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decode;
        try {
            decode = Base64.getDecoder().decode(base64Token);

        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
        String token = new String(decode, StandardCharsets.UTF_8);

        int delim = token.indexOf(":");
        if (delim < 0) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }*/
}
