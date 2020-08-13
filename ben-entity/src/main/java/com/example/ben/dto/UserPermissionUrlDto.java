package com.example.ben.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangben
 * @Description: 用户的接口权限返回值
 * @Date: 2020/8/11 23:48
 * @Version: 1.0
 */
@ApiModel("用户的接口权限返回值")
public class UserPermissionUrlDto {

    @ApiModelProperty("资源的id")
    private Long permissionId;

    @ApiModelProperty("资源的访问方法")
    private String method;

    @ApiModelProperty("资源的链接")
    private String url;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("资源的名字")
    private String name;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
