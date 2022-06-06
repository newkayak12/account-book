package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum RefPaymentType {
    CASH(0),CHECK(1), CREDIT(2) ;

    RefPaymentType(Integer code) {
        this.code = code;
    }

    Integer code;

}
