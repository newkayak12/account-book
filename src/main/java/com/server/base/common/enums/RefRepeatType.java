package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum RefRepeatType {
//    반복 타입
    LOAN(-1),//할부
    NONE(0), //반복 없음
    DAILY(1), // 매일
    WEEKDAY(2), // 평일마다
    WEEKEND(3), // 주말마다
    WEEKLY(4), // 매주
    PER_TWO_WEEKS(5), //2주마다
    PER_FOUR_WEEKS(6), //4주마다
    MONTHLY(7), //매달
    END_OF_MONTH(8),//달 말
    PER_TWO_MONTH(9), // 2달 마다
    PER_THREE_MONTH(10), //3달 마다
    PER_FOUR_MONTH(11), //4달 마다
    PER_SIX_MONTH(12),//6달 마다
    PER_A_YEAR(13); //1년 마다

    RefRepeatType(Integer code) {
        this.code = code;
    }

    Integer code;
}
