package com.example.bengateway.filter;

import com.example.bengateway.constants.BenConstants;
import com.example.bengateway.constants.ZuulConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.catalina.users.Constants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: wangben
 * @Description: 请求号码requestNo生成过滤器
 * @Date: 2020/8/5 14:56
 * @Version: 1.0
 */
public class RequestNoGenerateFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return ZuulConstants.REQUEST_NO_GENERATE_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 添加一个请求号
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();

        String requestNo = UUID.randomUUID().toString().replace("-", "");

        requestContext.addZuulRequestHeader(BenConstants.REQUEST_NO_HEADER_NAME,requestNo);

        response.addHeader(BenConstants.REQUEST_NO_HEADER_NAME, requestNo);

        return null;
    }
}
