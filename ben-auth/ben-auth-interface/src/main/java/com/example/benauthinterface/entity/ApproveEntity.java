package com.example.benauthinterface.entity;

/**
 * @Author: wangben
 * @Description: 认证的实体需要传递的数据
 * @Date: 2020/8/7 14:44
 * @Version: 1.0
 */
public class ApproveEntity {

    private String token;

    private String username;

    private Long userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
