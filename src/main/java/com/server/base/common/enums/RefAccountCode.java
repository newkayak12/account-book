package com.server.base.common.enums;

public enum RefAccountCode {
    INCOME("a1", "입금");

    RefAccountCode(String cCode, String cName) {
        this.cCode = cCode;
        this.cName = cName;
    }

    String cCode;
    String cName;
}
