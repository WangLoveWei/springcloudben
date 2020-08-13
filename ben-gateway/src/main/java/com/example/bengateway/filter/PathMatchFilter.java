package com.example.bengateway.filter;

import com.example.ben.dto.UserPermissionUrlDto;
import com.example.bencommon.common.enums.CoreExceptionEnum;
import com.example.bencommon.common.exception.ServiceException;
import com.example.bencommon.config.properties.JwtProperties;
import com.example.bengateway.config.IgnoreUrlsConfig;
import com.example.bengateway.constants.Constant;
import com.example.bengateway.constants.ZuulConstants;
import com.example.bengateway.context.AuthServiceConsumer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 请求路径权限过滤器
 *
 * @author fengshuonan
 * @date 2017-11-14-上午10:43
 */
public class PathMatchFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return ZuulConstants.PATH_MATCH_FILTER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        boolean matches = false;
        List<String> urls = ignoreUrlsConfig.getUrls();
        if (urls != null && urls.size() > 0) {
            for (String url : urls) {
                matches = request.getServletPath().matches(url);
                if (matches) break;
            }
        }
        //是否是登录页面
        if (matches || request.getServletPath().equals(jwtProperties.getAuthPath())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        Object userId = currentContext.get(Constant.IDENTITY_HEADER);

        if (userId == null) {
            throw new ServiceException(CoreExceptionEnum.PERMISSION_ERROR);
        }
        //现在是走的认证中心，其实可以直接走user
        List<UserPermissionUrlDto> permissionUrls = authServiceConsumer.getUserPermissionUrls(Long.valueOf(userId.toString()));
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean hasPermission = false;
        for (UserPermissionUrlDto permission : permissionUrls) {
            hasPermission = antPathMatcher.match(permission.getUrl(), request.getServletPath());
            if (hasPermission) {
                hasPermission = permission.getMethod().equalsIgnoreCase(request.getMethod());
            }
            if (hasPermission) {
                break;
            }
        }

        if (hasPermission) {
            return null;
        } else {
            throw new ServiceException(CoreExceptionEnum.PERMISSION_ERROR);
        }
    }
}
