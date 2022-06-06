package com.server.base.common.enums;

public enum RefBankCode {
    TOSS(1, "토스 증권")
    ;

    RefBankCode(Integer bcode, String bName) {
        this.bcode = bcode;
        this.bName = bName;
    }

    Integer bcode;
     String bName;
}
