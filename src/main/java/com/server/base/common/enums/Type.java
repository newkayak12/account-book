package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum Type {
    MONTH(1),
    WEEK(2),
    DAY(3);
    Integer code;

    Type(Integer code) {
        this.code = code;
    }
}
