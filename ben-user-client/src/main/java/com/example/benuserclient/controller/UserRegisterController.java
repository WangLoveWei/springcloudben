package com.example.benuserclient.controller;

import com.example.ben.group.Add;
import com.example.ben.model.Permission;
import com.example.ben.model.User;
import com.example.ben.param.user.RegisterParam;
import com.example.bencommon.base.CommonResult;
import com.example.benuserclient.service.UserMessageService;
import com.example.benuserclient.service.UserRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangben
 * @Description: 用户注册的相关接口
 * @Date: 2020/8/10 12:01
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户注册模块")
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private UserMessageService userMessageService;

    @ApiOperation("用户注册的接口")
    @PostMapping("/register")
    public CommonResult<Long> register(@RequestBody @Validated({Add.class}) RegisterParam param) {
        Long userId = userRegisterService.register(param);
        return CommonResult.ok(userId);
    }

/*

    @GetMapping("/register")
    UserDetails register(String username,String password);
*/

    @GetMapping("/findUserByUsername")
    User findUserByUsername(@RequestParam("username") String username) {
        return userMessageService.findUserByUsername(username);

    }

    @GetMapping("findResourceByUserId")
    List<Permission> findResourceByUserId(@RequestParam("userId") Long userId) {
        return new ArrayList<>();
    }

}
