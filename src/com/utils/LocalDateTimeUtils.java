package com.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author zidong.luo
 */
public class LocalDateTimeUtils {

    /**
     * 日期格式yyyy-MM-dd
     */
    public static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     */
    public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 构造函数
     */
    private LocalDateTimeUtils() {
        super();
    }


    /**
     * Date转LocalDateTime
     *
     * @param date Date对象
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param dateTime LocalDateTime对象
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 格式化时间-默认yyyy-MM-dd HH:mm:ss格式
     *
     * @param dateTime LocalDateTime对象
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DATE_TIME_PATTERN);
    }

    /**
     * 按pattern格式化时间-默认yyyy-MM-dd HH:mm:ss格式
     *
     * @param dateTime LocalDateTime对象
     * @param pattern  要格式化的字符串
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        if (pattern == null || pattern.isEmpty()) {
            pattern = DATE_TIME_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * 获取今天的00:00:00
     *
     * @return
     */
    public static Date getDayStart() {
        return getDayStart(LocalDateTime.now());
    }

    /**
     * 获取今天的23:59:59
     *
     * @return
     */
    public static Date getDayEnd() {
        return getDayEnd(LocalDateTime.now());
    }

    /**
     * 获取昨天的起始时间00:00:00
     *
     * @return
     */
    public static Date getYesterdayStart() {
        return getDayStart(LocalDateTime.now().minusDays(1));
    }

    /**
     * 获取昨天的结束时间23:59:59
     *
     * @return
     */
    public static Date getYesterdayEnd() {
        return getDayEnd(LocalDateTime.now().minusDays(1));
    }

    /**
     * 获取某天的00:00:00
     *
     * @param dateTime
     * @return
     */
    public static Date getDayStart(LocalDateTime dateTime) {
        return localDateTimeToDate(dateTime.with(LocalTime.MIN));
    }

    /**
     * 获取某天的23:59:59
     *
     * @param dateTime
     * @return
     */
    public static Date getDayEnd(LocalDateTime dateTime) {
        return localDateTimeToDate(dateTime.with(LocalTime.MAX));
    }

    /**
     * 获取本月第一天的00:00:00
     *
     * @return
     */
    public static Date getFirstDayOfMonth() {
        return getFirstDayOfMonth(LocalDateTime.now());
    }

    /**
     * 获取本月最后一天的23:59:59
     *
     * @return
     */
    public static Date getLastDayOfMonth() {
        return getLastDayOfMonth(LocalDateTime.now());
    }

    /**
     * 获取某月第一天的00:00:00
     *
     * @param dateTime LocalDateTime对象
     * @return
     */
    public static Date getFirstDayOfMonth(LocalDateTime dateTime) {
        return localDateTimeToDate(dateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
    }

    /**
     * 获取某月最后一天的23:59:59
     *
     * @param dateTime LocalDateTime对象
     * @return
     */
    public static Date getLastDayOfMonth(LocalDateTime dateTime) {
        return localDateTimeToDate(dateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX));
    }

    /**
     * 获取当年开始时间00:00:00
     *
     * @return
     */
    public static Date getFirstDayOfYear() {
        return getFirstDayOfYear(LocalDateTime.now());
    }

    public static Date getLastDayOfYear() {
        return getLastDayOfYear(LocalDateTime.now());
    }

    public static Date getLastDayOfYear(LocalDateTime dateTime) {
        return localDateTimeToDate(dateTime.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX));
    }

    /**
     * 获取指定日期所在年的起始时间
     *
     * @param dateTime
     * @return
     */
    public static Date getFirstDayOfYear(LocalDateTime dateTime) {
        return localDateTimeToDate(dateTime.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN));
    }

    /**
     * 获取本周的开始时间00:00:00
     *
     * @return
     */
    public static Date getFirstDayOfCurWeek() {
        return getDayStart(LocalDateTime.now().with(DayOfWeek.MONDAY));
    }

    /**
     * 获取本周的结束时间00:00:00
     *
     * @return
     */
    public static Date getLastDayOfCurWeek() {
        return getDayEnd(LocalDateTime.now().with(DayOfWeek.SUNDAY));
    }

    public static void main(String[] a) {
        System.out.println(getLastDayOfCurWeek());

        System.out.println(getLastDayOfYear());

        System.out.println(formatDateTime(LocalDateTime.now(),"yyyyMMddHHmmss"));
    }

}
