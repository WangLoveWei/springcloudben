package com.example.benuserclient.service;

import com.example.ben.dto.UserPermissionUrlDto;

import java.util.List;

/**
 * @Author: wangben
 * @Description: 用户资源权限的接口方法
 * @Date: 2020/8/11 23:31
 * @Version: 1.0
 */
public interface UserPermissionService {

    /**
     * 查询用户的权限列表
     * @param userId
     * @return
     */
    List<UserPermissionUrlDto> getUserPermissionUrls(Long userId);
}
