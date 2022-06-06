package com.server.base.common.enums;

public enum RefWeekday {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    RefWeekday(Integer code) {
        this.code = code;
    }

    Integer code;

}
