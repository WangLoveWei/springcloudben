package com.example.bencommon.constants;

/**
 * 响应接口
 *
 * @author chuncheng
 * @date 2020/3/6
 */
public interface IErrorCode {


    /**
     * 是否成功
     *
     * @return 是否成功
     */
    boolean isSuccess();

    /**
     * 响应码
     *
     * @return 响应码
     */
    int getCode();

    /**
     * 响应消息
     *
     * @return 响应消息
     */
    String getMessage();
}
