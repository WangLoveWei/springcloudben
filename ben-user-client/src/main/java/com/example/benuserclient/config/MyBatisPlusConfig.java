package com.example.benuserclient.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis plus 配置
 *
 * @author chuncheng
 * @date 2020/3/17
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.example.benuserclient.dao")
public class MyBatisPlusConfig {

    /**
     * SQL执行效率插件 设置 dev test 环境开启
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceMonitorInterceptor performanceInterceptor() {
        return new PerformanceMonitorInterceptor();
    }


}
