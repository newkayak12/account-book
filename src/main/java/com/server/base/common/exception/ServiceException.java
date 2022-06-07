package com.server.base.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends Exception{
    private Integer code;
    private String msg;
    public ServiceException(Exceptions exceptions, String message){
        super(message);
        this.code = exceptions.getCode();
        this.msg = String.format(exceptions.getMsg(), message);
    }
    public ServiceException(Exceptions exceptions) {
        super(exceptions.getMsg());
        this.code = exceptions.getCode();
        this.msg = exceptions.getMsg();
    }
}
