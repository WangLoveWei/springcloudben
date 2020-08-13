package com.example.benauthinterface.api;

import com.example.ben.dto.UserPermissionUrlDto;
import com.example.bencommon.base.CommonResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @Author: wangben
 * @Description: 验证身份的api
 * @Date: 2020/8/5 16:18
 * @Version: 1.0
 */
@RequestMapping("/api/auth")
public interface AuthServiceApi {


    /**
     * 获取secret通过appId
     *
     * @param appId
     * @return
     */
    @RequestMapping("/getUserById")
    String getSecretByAppId(@RequestParam(name = "appId") String appId);

    /**
     * 获取you权限的请求路径
     *
     * @param userId
     * @return
     */
    @RequestMapping("/getUserPermissionUrls")
    List<UserPermissionUrlDto> getUserPermissionUrls(@RequestParam(name = "userId") Long userId);


    /* */

    /**
     * 认证访问token
     *
     * @author stylefeng
     */
    @GetMapping("/approve")
    CommonResult approve();
}
