package com.example.benauthservice.feign;

import com.example.ben.dto.UserPermissionUrlDto;
import com.example.ben.model.Permission;
import com.example.ben.model.User;
import com.example.ben.param.user.RegisterParam;
import com.example.bencommon.base.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @Author: wangben
 * @Description: user认证的接口
 * @Date: 2020/8/6 22:36
 * @Version: 1.0
 */
@FeignClient("user-service")
@RequestMapping("/user")
public interface UserConsumer {

    @GetMapping("/loadUserByUsername")
    UserDetails loadUserByUsername(String username);
/*

    @GetMapping("/register")
    UserDetails register(String username,String password);
*/

    @GetMapping("/findUserByUsername")
    User findUserByUsername(@RequestParam("username") String username);

    @GetMapping("findResourceByUserId")
    List<Permission> findResourceByUserId(@RequestParam("userId") Long userId);

    @PostMapping("/register")
    public CommonResult<Long> register(RegisterParam param);


    @GetMapping("/saveToken")
    CommonResult<Long> saveToken(@RequestParam("token") String token,@RequestParam("username")String username,@RequestParam("userId")Long userId);

    @GetMapping("/getUserPermissionUrls")
    List<UserPermissionUrlDto> getUserPermissionUrls(@RequestParam("userId") Long userId);
}
