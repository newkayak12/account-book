package com.server.base.common.responseContainer;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Response implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public Response(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
