package com.example.benauthservice.provider;


import com.example.ben.dto.UserPermissionUrlDto;
import com.example.benauthinterface.api.AuthServiceApi;
import com.example.benauthservice.feign.UserConsumer;
import com.example.bencommon.base.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * auth接口实现
 *
 * @author fengshuonan
 * @date 2017-11-09-下午7:47
 */
@RestController
public class AuthServiceProvider implements AuthServiceApi {

    @Autowired
    private UserConsumer userConsumer;

    @Override
    public String getSecretByAppId(String appId) {
        return null;
    }

    @Override
    public List<UserPermissionUrlDto> getUserPermissionUrls(Long userId) {
        return userConsumer.getUserPermissionUrls(userId);
    }

    /*@Override
    public LoginUser getUserById(Long userId) {
        return null;
    }*/

    @Override
    public CommonResult<Boolean> approve() {
        return CommonResult.ok(true);
    }

}
