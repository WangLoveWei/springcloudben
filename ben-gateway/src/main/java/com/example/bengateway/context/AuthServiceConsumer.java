package com.example.bengateway.context;

import com.example.benauthinterface.api.AuthServiceApi;
import com.example.bengateway.constants.Constant;
import com.example.bengateway.fallback.AuthFailService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: wangben
 * @Description: 认证中心的feign
 * @Date: 2020/8/5 17:07
 * @Version: 1.0
 */
@FeignClient(value = Constant.AUTH_MODULAR_NAME )
public interface AuthServiceConsumer extends AuthServiceApi {
}
