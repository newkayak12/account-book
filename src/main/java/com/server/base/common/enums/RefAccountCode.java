package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum RefAccountCode {
//    입출금 코드
    INCOME("a1", "급여 지급"),
    OUTCOME("n2","은행 자동 납부");

    RefAccountCode(String cCode, String cName) {
        this.cCode = cCode;
        this.cName = cName;
    }

    String cCode;
    String cName;
}
