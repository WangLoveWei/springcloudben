package com.example.bengateway.constants;

public interface Constant {

    /**
     * 身份标识的header名称,用于传给下游服务获取用户id
     */
    public static final String IDENTITY_HEADER = "USER-ID";

    /**
     * roses-auth模块名称
     */
    public static final String AUTH_MODULAR_NAME = "ben-auth";

    /**
     * roses-gateway模块名称
     */
    public static final String GATEWAY_MODULAR_NAME = "roses-gateway";


}