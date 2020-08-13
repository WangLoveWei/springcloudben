package com.example.benuserclient.controller;

import com.example.ben.dto.UserPermissionUrlDto;
import com.example.benuserclient.service.UserPermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @Author: wangben
 * @Description: 用户权限的接口
 * @Date: 2020/8/11 22:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户权限模块")
public class UserPermissionController {

    @Autowired
    private UserPermissionService userPermissionService;

    @GetMapping("/getUserPermissionUrls")
    List<UserPermissionUrlDto> getUserPermissionUrls(@RequestParam("userId") Long userId) {
        List<UserPermissionUrlDto> permissionUrls = userPermissionService.getUserPermissionUrls(userId);
        return permissionUrls;
    }
}
