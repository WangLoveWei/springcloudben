package com.example.bencommon.common.exception;


import com.example.bencommon.common.AbstractServiceException;
import com.example.bencommon.constants.IErrorCode;

/**
 * @Author: wangben
 * @Description: 业务服务异常封装
 * @Date: 2020/8/5 15:18
 * @Version: 1.0
 */
public class ServiceException extends RuntimeException{
    private Integer code;

    private String errorMessage;

    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(AbstractServiceException exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

    public ServiceException(IErrorCode iErrorCode) {
        super(iErrorCode.getMessage());
        this.code = iErrorCode.getCode();
        this.errorMessage=iErrorCode.getMessage();
    }

    public ServiceException(IErrorCode iErrorCode, String errorMessage) {
        super(errorMessage);
        this.code = iErrorCode.getCode();
        this.errorMessage = errorMessage;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
