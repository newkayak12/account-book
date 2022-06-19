package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum IsMain {
    MAIN(1),
    SUB(0);

    Integer code;

    IsMain(Integer code) {
        this.code = code;
    }
}
