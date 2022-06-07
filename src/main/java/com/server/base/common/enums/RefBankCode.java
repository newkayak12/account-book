package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum RefBankCode {
//    은행 코드
    TOSS(1, "토스 증권"),
    HANA(2, "하나 은행"),
    JAIL(3, "제일 은행"),
    KUKMIN(4, "국민 은행"),
    SINHAN(5, "신한 은행"),
    WOORI(6, "우리 은행"),
    KIUP(7, "기업 은행"),
    NONGHYUP(8, "농협"),
    SOOHYUP(9, "수협"),
    POST(10, "우체국"),
    BC(11, "BC 카드"),
    SAMSUNG(12, "삼성 카드"),
    LOTTE(13, "롯데 카드"),
    HYUNDAI(14, "현대 카드")
    ;

    RefBankCode(Integer bcode, String bName) {
        this.bcode = bcode;
        this.bName = bName;
    }

    Integer bcode;
     String bName;
}
