package com.example.benauthservice.service;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @author chuncheng
 * @date 2020/4/7
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     *
     * @return 资源的 ANT 通配符 map
     */
    Map<String, ConfigAttribute> loadDataSource();
}
