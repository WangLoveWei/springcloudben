package com.example.ben.param.user;

import com.example.ben.group.Add;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: wangben
 * @Description: 注册需要的信息
 * @Date: 2020/8/10 14:09
 * @Version: 1.0
 */
@ApiModel("注册的传参")
public class RegisterParam {

    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空",groups ={Add.class} )
    private String username;//用户名

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空",groups ={Add.class} )
    private String password;//密码


    @ApiModelProperty("昵称")
    private String nickname;//昵称


    @ApiModelProperty("手机号")
    private String phoneNumber;//手机号


    @ApiModelProperty("邮箱")
    private String email;//邮箱


    @ApiModelProperty("性别F是nv  M是男 ")
    private String sex;//性别F是nv  M是男

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
