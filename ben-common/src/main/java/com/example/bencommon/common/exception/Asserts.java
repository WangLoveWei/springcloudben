package com.example.bencommon.common.exception;

import com.example.bencommon.constants.IErrorCode;
import com.example.bencommon.constants.ResultCode;

public class Asserts {

    public static void fail(IErrorCode iErrorCode){
        throw new ServiceException(iErrorCode);
    }

    public static void fail(IErrorCode iErrorCode, String message){
        throw new ServiceException(iErrorCode, message);
    }

    public static void fail(String message) {
        throw new ServiceException(ResultCode.FAILED, message);
    }

}