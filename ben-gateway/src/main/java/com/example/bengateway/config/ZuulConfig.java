package com.example.bengateway.config;

import com.example.bencommon.config.properties.JwtProperties;
import com.example.bencommon.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangben
 * @Description: zuul的配置信息
 * @Date: 2020/8/7 14:14
 * @Version: 1.0
 */
@Configuration
public class ZuulConfig {

    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }
    @Bean
    public JwtProperties jwtProperties(){
        return new JwtProperties();
    }

}
