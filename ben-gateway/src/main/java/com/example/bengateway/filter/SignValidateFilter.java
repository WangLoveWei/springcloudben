package com.example.bengateway.filter;

import cn.hutool.core.io.IoUtil;
import com.example.bencommon.common.exception.ServiceException;
import com.example.bencommon.utils.ToolUtil;
import com.example.bengateway.constants.ZuulConstants;
import com.example.bengateway.exception.SignExceptionEnum;
import com.example.bengateway.sign.SignService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.catalina.startup.Tool;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author: wangben
 * @Description: 认证应用签名的拦截器
 * @Date: 2020/8/5 17:12
 * @Version: 1.0
 */
public class SignValidateFilter extends ZuulFilter {

    @Autowired
    private SignService signService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return ZuulConstants.SIGN_VALIDATE_FILTER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String requestBody = IoUtil.read(inputStream, Charset.forName("UTF-8"));

        //requestBody为空则不校验签名
        if (ToolUtil.isOneEmpty(requestBody)) {
            return null;
        }
        String appId = request.getParameter("appId");
        String timestamp = request.getParameter("timestamp");
        String token = request.getParameter("token");//这是为了验证访问接口的数据是不是非法访问，并不是验证token

        if (ToolUtil.isOneEmpty(appId, timestamp, token)) {
            throw new ServiceException(SignExceptionEnum.SIGN_ERROR_WITH_EMPTY_DATA);
        }

        boolean validateSign = signService.validateSign(appId, timestamp, token, requestBody);

        if (validateSign) {
            return null;
        } else {
            throw new ServiceException(SignExceptionEnum.SIGN_ERROR);
        }
    }
}
