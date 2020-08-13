package com.example.benauthservice.param;


import javax.validation.constraints.Pattern;

public class UserParam {

    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$", message = "用户名校验失败")
    private String username;


    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[1-9])(?=.*[-_!@#$%^&*~]).{8,12}$", message = "密码校验失败")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}