package com.example.bengateway.filter;

import com.example.bencommon.base.CommonResult;
import com.example.bencommon.common.enums.CoreExceptionEnum;
import com.example.bencommon.common.exception.ServiceException;
import com.example.bencommon.config.properties.JwtProperties;
import com.example.bengateway.config.IgnoreUrlsConfig;
import com.example.bengateway.constants.Constant;
import com.example.bengateway.constants.ZuulConstants;
import com.example.bencommon.utils.JwtTokenUtil;
import com.example.bengateway.context.AuthServiceConsumer;
import com.netflix.discovery.converters.Auto;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangben
 * @Description: jwtToken的权限校验的过滤器
 * @Date: 2020/8/5 15:04
 * @Version: 1.0
 */
public class JwtTokenFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


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
        return ZuulConstants.JWT_TOKEN_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        boolean matches=false;
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
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        //获取头文件中包含的验证信息
        String authHeader = request.getHeader(jwtProperties.getHeader());
        String authToken;

        if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith(JwtProperties.AUTH_HEADER_PREFIX)) {
            authToken = authHeader.substring(JwtProperties.AUTH_HEADER_PREFIX.length());
            try {
                Boolean tokenExpired = jwtTokenUtil.isTokenExpired(authToken);
                if (tokenExpired) {
                    throw new ServiceException(CoreExceptionEnum.TOKEN_EXPIRED);
                } else {
                    //到认证服务中心去认证jwtToken是否有效
                    CommonResult<Boolean> approve = authServiceConsumer.approve();
                    if (approve.isSuccess()) {
                        String userId = jwtTokenUtil.getUserIdFromToken(authToken);
                        currentContext.addZuulRequestHeader(Constant.IDENTITY_HEADER, userId);
                        currentContext.set(Constant.IDENTITY_HEADER, userId);
                        return null;
                    } else {
                        throw new ServiceException(approve.getCode(), approve.getMessage());
                    }
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                throw new ServiceException(CoreExceptionEnum.TOKEN_ERROR);
            }
        } else {
            //header authorization为空，或者没有带Bearer字段
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }
    }
}
