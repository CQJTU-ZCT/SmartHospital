package com.cqjtu.exception;

/**
 * @author zjhfyq
 * @Desc 自定义异常
 * @date 2017/12/8.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String msg){
        super(msg);
    }
}
