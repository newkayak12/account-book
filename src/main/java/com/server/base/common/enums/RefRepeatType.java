package com.server.base.common.enums;

import lombok.Getter;

@Getter
public enum RefRepeatType {
    NONE(0),
    DAILY(1),
    WEEKDAY(2),
    WEEKEND(3),
    WEEKLY(4),
    PER_TWO_WEEKS(5),
    PER_FOUR_WEEKS(6),
    MONTHLY(7),
    END_OF_MONTH(8),
    PER_TWO_MONTH(9),
    PER_THREE_MONTH(10),
    PER_FOUR_MONTH(11),
    PER_SIX_MONTH(12),
    PER_A_YEAR(13);

    RefRepeatType(Integer code) {
        this.code = code;
    }

    Integer code;
}
