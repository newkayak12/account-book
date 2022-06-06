package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum RefBankCode {
//    은행 코드
    TOSS(1, "토스 증권")
    ;

    RefBankCode(Integer bcode, String bName) {
        this.bcode = bcode;
        this.bName = bName;
    }

    Integer bcode;
     String bName;
}
