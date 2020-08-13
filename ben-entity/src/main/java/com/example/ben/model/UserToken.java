package com.example.ben.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wangben
 * @Description: token的表
 * @Date: 2020/8/11 11:58
 * @Version: 1.0
 */
@TableName("ben_user_token")
public class UserToken implements Serializable {

    private static final long serialVersionUID = 3021111197272961934L;

    public UserToken(String username, String token, Long userId, Integer status) {
        this.username = username;
        this.token = token;
        this.userId = userId;
        this.status = status;
    }

    public UserToken() {
    }

    public UserToken(String username, String token, Long userId) {
        this.username = username;
        this.token = token;
        this.userId = userId;
        this.status=1;
    }

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;


    @TableField("token")
    private String token;


    @TableField("user_id")
    private Long userId;

    @TableField("status")
    private Integer status;


    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private  Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
