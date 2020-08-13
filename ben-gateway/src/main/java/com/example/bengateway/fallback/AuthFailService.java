package com.example.bengateway.fallback;

import com.example.ben.dto.UserPermissionUrlDto;
import com.example.bencommon.base.CommonResult;
import com.example.bengateway.context.AuthServiceConsumer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: wangben
 * @Description: 服务降级
 * @Date: 2020/8/7 15:42
 * @Version: 1.0
 */
@Component
@RequestMapping("/authFailService")
public class AuthFailService implements AuthServiceConsumer {

    @Override
    public String getSecretByAppId(String appId) {
        return null;
    }

    @Override
    public List<UserPermissionUrlDto> getUserPermissionUrls(Long userId) {
        return null;
    }

    @Override
    public CommonResult<Boolean> approve() {
        return CommonResult.failed("false");
    }
}
