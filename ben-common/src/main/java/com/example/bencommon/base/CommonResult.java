package com.example.bencommon.base;

import com.example.bencommon.constants.IErrorCode;
import com.example.bencommon.constants.ResultCode;


/**
 * 通用响应结果封装
 *
 * @author chuncheng
 * @date 2020/3/6
 */

public class CommonResult<T> {

    private boolean success;

    private int code;

    private String message;

    private T data;

    public CommonResult() {
    }

    public CommonResult(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult(IErrorCode iErrorCode, T data) {
        this.success = iErrorCode.isSuccess();
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
        this.data = data;
    }

    public CommonResult(IErrorCode iErrorCode, String message, T data) {
        this.success = iErrorCode.isSuccess();
        this.code = iErrorCode.getCode();
        this.message = message;
        this.data = data;
    }
    public static <T>  CommonResult<T> ok() {
        return new  CommonResult<>(ResultCode.SUCCESS, null);
    }

    public static <T>  CommonResult<T> ok(String message) {
        return new  CommonResult<>(ResultCode.SUCCESS, message, null);
    }

    public static <T>  CommonResult<T> ok(T data) {
        return new  CommonResult<>(ResultCode.SUCCESS, data);
    }

    public static <T>  CommonResult<T> ok(String message, T data) {
        return new  CommonResult<>(ResultCode.SUCCESS, message, data);
    }

    public static <T>  CommonResult<T> created() {
        return new  CommonResult<>(ResultCode.CREATED, null);
    }

    public static <T>  CommonResult<T> created(String message) {
        return new  CommonResult<>(ResultCode.CREATED, message, null);
    }

    public static <T>  CommonResult<T> created(T data) {
        return new  CommonResult<>(ResultCode.CREATED, data);
    }

    public static <T>  CommonResult<T> created(String message, T data) {
        return new  CommonResult<>(ResultCode.CREATED, message, data);
    }

    public static <T>  CommonResult<T> failed() {
        return new  CommonResult<>(ResultCode.FAILED, null);
    }

    public static <T>  CommonResult<T> failed(String message) {
        return new  CommonResult<>(ResultCode.FAILED, message, null);
    }

    public static <T>  CommonResult<T> failed(IErrorCode iErrorCode) {
        return new  CommonResult<>(iErrorCode, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
