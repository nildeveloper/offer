package com.test;

import org.junit.Test;

import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 * User: haolu.liu
 * Date: 2019-08-26 9:47
 * Description:
 */
public class Test1 {

    @Test
    public void test() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        System.out.println(year + " " + month + " " + day);
    }
}
