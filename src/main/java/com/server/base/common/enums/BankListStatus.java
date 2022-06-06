package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum BankListStatus {
    ACTIVE(0),
    DEACTIVE(-1);

    BankListStatus(Integer code) {
        this.code = code;
    }

    Integer code;
}
