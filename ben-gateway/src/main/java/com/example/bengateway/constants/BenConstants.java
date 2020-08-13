package com.example.bengateway.constants;

/**
 * 框架通用常量
 *
 * @author wangben
 * @date
 */
public interface BenConstants {

    /**
     * 请求号header标识
     */
    String REQUEST_NO_HEADER_NAME = "Request-No";

    /**
     * header中的spanId，传递规则：request header中传递本服务的id
     */
    String SPAN_ID_HEADER_NAME = "Span-Id";
}
