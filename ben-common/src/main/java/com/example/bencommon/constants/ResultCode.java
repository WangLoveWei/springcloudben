package com.example.bencommon.constants;

import org.springframework.http.HttpStatus;

/**
 * 常用的响应结果
 *
 * @author chuncheng
 * @date 2020/3/6
 */
public enum ResultCode implements IErrorCode {
    /**
     * 请求成功
     * <p>用于 GET</p>
     */
    SUCCESS(true, HttpStatus.OK.value(), "操作成功"),
    /**
     * 请求成功
     * <p>用于 GET</p>
     */
    FAILED(false, HttpStatus.OK.value(), "操作失败"),
    /**
     * 创建成功
     * <p>用于 POST 成功</p>
     */
    CREATED(true, HttpStatus.CREATED.value(), "创建成功"),
    /**
     * 所请求的内容为空
     */
    NO_CONTENT(true, HttpStatus.NO_CONTENT.value(), "所请求的内容为空"),
    /**
     * 删除成功
     */
    DELETE_SUCCESS(true, HttpStatus.NO_CONTENT.value(), "删除成功"),
    /**
     * 资源已重置
     */
    RESET_CONTENT(true, HttpStatus.RESET_CONTENT.value(), "资源已重置"),
    /**
     * 资源已永久更改路径
     */
    MOVED_PERMANENTLY(true, HttpStatus.MOVED_PERMANENTLY.value(), "资源已永久更改路径"),
    /**
     * 资源临时更改路径
     */
    FOUND(true, HttpStatus.FOUND.value(), "资源临时更改路径"),
    /**
     * 参数校验失败
     */
    VALIDATE_FAILED(false, HttpStatus.BAD_REQUEST.value(), "参数校验失败"),
    /**
     * 暂未登录或token已经过期
     */
    UNAUTHORIZED(false, HttpStatus.UNAUTHORIZED.value(), "暂未登录或token已经过期"),
    /**
     * 没有相关权限
     */
    FORBIDDEN(false, HttpStatus.FORBIDDEN.value(), "没有相关权限"),
    /**
     * 没有该资源
     */
    NOT_FOUND(false, HttpStatus.NOT_FOUND.value(), "没有该资源"),

    /**
     * 服务器异常返回繁忙
     */
    SERVER_BUSY(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器繁忙"),
    ;

    private final boolean success;
    private final int code;
    private final String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


    @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
