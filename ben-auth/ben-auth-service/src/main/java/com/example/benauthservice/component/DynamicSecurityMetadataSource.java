package com.example.benauthservice.component;

import com.example.benauthservice.service.DynamicSecurityService;
import com.sun.xml.internal.ws.util.UtilException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 动态权限数据源，用于获取动态权限规则
 *
 * @author chuncheng
 * @date 2020/4/7
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static Map<String, ConfigAttribute> configAttributeMap = null;
    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (configAttributeMap == null) {
            this.loadDataSource();
        }
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        // 获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        // 获取当前请求方法
        String method = ((FilterInvocation) o).getRequest().getMethod();
        String path = getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = configAttributeMap.keySet().iterator();
        // 获取访问该路径所需资源
        while (iterator.hasNext()) {
            String pattern = iterator.next();
            ConfigAttribute configAttribute = configAttributeMap.get(pattern);
            String attribute = configAttribute.getAttribute();
            String[] split = attribute.split(":");
            String needMethod = split[split.length - 1];
            if (pathMatcher.match(pattern, path) && method.equalsIgnoreCase(needMethod)) {
                configAttributes.add(configAttribute);
            }
        }
        // 未设置操作请求权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        if (configAttributeMap == null) {
            this.loadDataSource();
        }
        return configAttributeMap == null ? null : configAttributeMap.values();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public static String getPath(String uriStr) {
        URI uri;
        try {
            uri = new URI(uriStr);
        } catch (URISyntaxException var3) {
            throw new UtilException(var3);
        }

        return uri.getPath();
    }
}
