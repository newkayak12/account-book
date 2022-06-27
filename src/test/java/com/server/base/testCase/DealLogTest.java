package com.server.base.testCase;

import com.server.base.baseTest.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;

public class DealLogTest extends BaseTest {
    @BeforeEach
    void settings(){
        super.prefix += "/dealLog";
    }

    @Test
    void test(){
        System.out.println(LocalDate.parse("2022-06-26").getDayOfMonth());
        System.out.println(LocalDate.parse("2022-06-26").getDayOfWeek());
        System.out.println(LocalDate.parse("2022-06-26").getDayOfWeek().getValue());
        Integer year = LocalDate.parse("2022-06-26").getYear();
        Integer month = LocalDate.parse("2022-06-26").getMonth().getValue();
        Integer day = LocalDate.parse("2022-06-26").getDayOfMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        System.out.println(calendar);
        System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));


    }
}
