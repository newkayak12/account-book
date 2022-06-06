package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum BankListStatus {
//    은행 리스트 상태
    ACTIVE(0),
    DEACTIVE(-1);

    BankListStatus(Integer code) {
        this.code = code;
    }

    Integer code;
}
