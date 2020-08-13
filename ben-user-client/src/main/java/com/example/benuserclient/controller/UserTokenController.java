package com.example.benuserclient.controller;


import com.example.bencommon.base.CommonResult;
import com.example.benuserclient.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangben
 * @Description: 用户token的相关接口
 * @Date: 2020/8/11 11:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户token模块")
public class UserTokenController {

    @Autowired
    private UserTokenService userTokenService;

    @ApiOperation("保存token的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", paramType = "String", value = "sdaffdadf.asfssasf.dasfasd", required = true),
            @ApiImplicitParam(name = "username", paramType = "String", value = "wangben", required = true),
            @ApiImplicitParam(name = "userId", paramType = "int", value = "1234", required = true),
    })
    @GetMapping("/saveToken")
    CommonResult<Long> saveToken(@RequestParam("token") String token, @RequestParam("username") String username, @RequestParam("userId") Long userId) {
        Long tokenId = userTokenService.saveToken(token, username, userId);
        return CommonResult.ok(tokenId);
    }
}
