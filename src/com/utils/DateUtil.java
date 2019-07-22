package com.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * <p>
 * Title:DateUtil
 * </p>
 * <p>
 * Description:日期常量类
 * </p>
 * 
 * @author chenzhenjiang
 * @date 2016-6-7 下午1:40:09
 */
public class DateUtil {

    public static final String C_YYYYyMMmDDd = "yyyy年MM月dd日";
    
    public static final String C_MMmDDd = "MM月dd日";

    public static final String C_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String C_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String C_YYYY_MM_DD_00_00_00 = "yyyy-MM-dd 00:00:00";

    public static final String C_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String C_YYYY_MM = "yyyy-MM";

    public static final String C_YYYYMM = "yyyyMM";

    public static final String C_YYYY = "yyyy";

    public static final String C_MM = "MM";

    public static final String C_DD = "dd";

    public static final String C_YYYYMMDD = "yyyyMMdd";

    public static final String C_HHMMSS = "HH:mm:ss";

    public static final String C_HHMM = "HH:mm";

    public static final String C_HHMMSSSSS = "HHmmssSSS";

    public static final String C_YYYYMMDDHHMM = "yyyyMMddHHmm";

    public static final String C_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String C_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    public static final String C_YYYY_MM_DD2 = "yyyy/MM/dd";

    public static final String[] C_WEAK = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /*************************************************
     * 获得date类型的当前日期
     * 
     * @return Date类型的当前日期
     **************************************************/
    public static Date getCurrentDate() {
        long now = System.currentTimeMillis();
        Date vDate = new Date(now);
        return vDate;
    }

    /*************************************************
     * 根据时间值获得时间对象
     * 
     * @return Date类型的日期对象
     **************************************************/
    public static Date getLongToDate(Long value) {
        java.sql.Date vDate = new java.sql.Date(value);
        return vDate;
    }

    /***************************************************
     * 获得字符串类型的当前日期，默认格式yyyy-MM-dd,该方法不要修改
     * 
     * @return String类型的当前日期
     **************************************************/
    public static String getCurrentDateStr() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return parseDateToString(currDate, C_YYYY_MM_DD);
    }

    /***************************************************
     * 获得字符类型的当前日期，日期格式为strFormat
     * 
     * @param strFormat 格式日期字符串
     * @return String类型的当前日期
     **************************************************/
    public static String getCurrentDateStr(String strFormat) {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();

        return parseDateToString(currDate, strFormat);
    }

    /***************************************************
     * 转换String类型的日期到Date类型，格式yyyy-mm-dd
     * 
     * @param dateValue 字符类型日期
     * @return date类型日期
     **************************************************/
    public static Date parseStringToDate(String dateValue) {
        return parseStringToDate(dateValue, C_YYYY_MM_DD);
    }

    /***************************************************
     * 转换String类型的日期到Date类型，格式为strFormat，默认格式yyyy-mm-dd
     * 
     * @param strFormat 格式字符串
     * @param dateValue String类型的日期
     * @return Date 获得指定格式的日期对象，如果出现异常返回null
     **************************************************/
    public static Date parseStringToDate(String dateValue, String strFormat) {
        if (dateValue == null) {
            return null;
        }
        if (strFormat == null) {
            strFormat = C_YYYY_MM_DD;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
        Date newDate = null;

        try {
            newDate = dateFormat.parse(dateValue);
        } catch (ParseException pe) {
            newDate = null;
        }
        return newDate;
    }

    /*****************************************************
     * 将date类型的日期转换为系统参数定义的格式的字符串格式yyyy-mm-dd
     * 
     * @param dateValue 需要转换的日期
     * @return 转换后符合给定格式的日期字符串
     ****************************************************/
    public static String parseDateToString(Date dateValue) {
        return parseDateToString(dateValue, C_YYYY_MM_DD);
    }

    /*****************************************************
     * 将Date类型的日期转换为系统参数定义的格式的字符串格式strFormat
     * 
     * @param dateValue 需要转换的日期
     * @param strFormat 格式字符串
     * @return 指定字符格式的日期字符串
     ****************************************************/

    public static String parseDateToString(Date dateValue, String strFormat) {
        if (dateValue == null || strFormat == null) {
            return null;
        }

        SimpleDateFormat dateFromat = new SimpleDateFormat();
        dateFromat.applyPattern(strFormat);

        return dateFromat.format(dateValue);
    }

    /*****************************************************
     * 转换时间戳到字符串，格式strFormat
     * 
     * @param datetime 需要转换的时间戳
     * @param strFormat 指定的转换格式
     * @return 时间戳字符串
     ****************************************************/
    public static String parseTimestampToString(Timestamp datetime, String strFormat) {
        if (datetime == null || strFormat == null) {
            return null;
        }

        SimpleDateFormat dateFromat = new SimpleDateFormat();
        dateFromat.applyPattern(strFormat);
        return dateFromat.format(datetime);
    }

    /*****************************************************
     * 取得指定日期N天后的日期
     * 
     * @param date 指定时间
     * @param days 天数
     * @return 指定时间days天后的日期对象
     ****************************************************/
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 取得指定日期N天后的日期 字符串
     * 
     * @param dateStr 指定时间
     * @param formatStr 日期格式
     * @param days 天数
     * @return 指定时间days天后的日期对象
     */
    public static String addDaysStr(String dateStr, String formatStr, int days) {
        Date oldDate = parseStringToDate(dateStr, formatStr);
        Date newDate = addDays(oldDate, days);
        return parseDateToString(newDate, formatStr);
    }

    /**
     * 获取指定日期N小时后的日期
     * 
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, Double hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.MINUTE, new Double(hour * 60).intValue());
        return cal.getTime();
    }

    /**
     * 获取指定日期N分钟后的日期
     * 
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * 获取指定日期N分钟后的日期 字符串
     * 
     * @param dateStr
     * @param formatStr
     * @param minute
     * @return
     */
    public static String addMinuteStr(String dateStr, String formatStr, int minute) {
        Date oldDate = parseStringToDate(dateStr, formatStr);
        Date newDate = addMinute(oldDate, minute);
        return parseDateToString(newDate, formatStr);
    }

    /**
     * 取得指定日期N月后的日期
     * 
     * @param date
     * @param days
     * @return
     */
    public static String addMonths(String dateValue, int months, String strFormat) {
        Date date = parseStringToDate(dateValue, strFormat);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return parseDateToString(cal.getTime(), strFormat);
    }

    /**
     * Descreiption:取得指定日期N月后的日期
     *
     * @param date
     * @param months
     * @Author: moshengwei
     * @Date: 2018年08月22日 17:34:59
     * @Return
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * Descreiption:取得指定日期N年后的日期
     *
     * @param date
     * @param years
     * @Author: moshengwei
     * @Date: 2018年08月22日 17:41:55
     * @Return
     */
    public static Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /*****************************************************
     * 计算两个日期之间相差的天数
     * 
     * @param date1
     * @param date2
     * @return 两个日期相差的天数
     ****************************************************/
    public static int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000;// (1000 * 3600 * 24 =
        // 86400000);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * Descreiption:计算两个日期之间相差的月数
     *
     * @param date1
     * @param date2
     * @Author: moshengwei
     * @Date: 2018年09月17日 14:49:42
     * @Return 两个日期相差的月数(四舍五入)
     */
    public static int monthsBetween(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        int yearMonth1 = c1.get(Calendar.YEAR) * 12;
        int yearMonth2 = c2.get(Calendar.YEAR) * 12;
        int months1 = c1.get(Calendar.MONTH) + 1;
        int months2 = c2.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DATE);
        int day2 = c2.get(Calendar.DATE);
        // 获取日期自然月最后一天
        c1.add(Calendar.MONTH, 1);
        c1.set(Calendar.DATE, 0);
        c2.add(Calendar.MONTH, 1);
        c2.set(Calendar.DATE, 0);
        // 将日换算为月
        BiFunction<Integer, Integer, Float> computeMonths = (day, monthDay) ->{
            BigDecimal dayBig = new BigDecimal(day);
            BigDecimal monthDayBig = new BigDecimal(monthDay);
            return dayBig.divide(monthDayBig, 5, BigDecimal.ROUND_HALF_UP).floatValue();
        };
        float dayMonths1 = computeMonths.apply(day1, c1.get(Calendar.DATE));
        float dayMonths2 = computeMonths.apply(day2, c2.get(Calendar.DATE));

        float date1Months = yearMonth1 + months1 + dayMonths1;
        float date2Months = yearMonth2 + months2 + dayMonths2;

        float dateMonths = date2Months - date1Months;
        // 四舍五入
        dateMonths = dateMonths > 0 ? dateMonths + 0.5f : dateMonths - 0.5f;

        return (int)(dateMonths);
    }

    /*****************************************************
     * 根据指定的日期获得对应的星期字符串
     * 
     * @param vdate 指定的日期字符，格式yyyy-MM-dd或yyyy-MM-dd hh:mm:ss
     * @return 日期字符串，如果给定的日期字符串不符合要求返回null
     ****************************************************/
    public static String getWeek(String vdate) {
        Date date = null;
        try {
            date = parseStringToDate(vdate, C_YYYY_MM_DD);
            if (date == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return C_WEAK[i];
    }

    /*****************************************************
     * 
     * 
     * @return 日期字符串及星期字符串
     ****************************************************/
    public static String getDateAndWeekString(String dateFormat) {
        String rtn = "";
        if (dateFormat == null || dateFormat.equals("")) {
            dateFormat = C_YYYY_MM_DD;
        }

        Calendar calendar = Calendar.getInstance();
        Date currDate = calendar.getTime();
        int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        try {
            SimpleDateFormat dateFromat = new SimpleDateFormat();
            dateFromat.applyPattern(dateFormat);
            rtn = dateFromat.format(currDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn + " " + C_WEAK[i];

    }

    /*****************************************************
     * 比较当前日期和指定日期，如果指定日期晚于当前日期返回true
     * 
     * @param vDate 指定的日期
     * @return 如果指定日期晚于当前日期返回true，指定日期早于当前日期返回false，指定日期为null返回false
     ****************************************************/
    public static boolean compareDate(Date vDate) {
        if (vDate == null) {
            return false;
        }
        Date cDate = getCurrentDate();
        return cDate.before(vDate);
    }

    /*******************************************************
     * 比较当前日期是否在指定的起始日期和截止日期内（如果在指定的日期之内返回true，如果不在指定的日期内返回false
     * 
     * @param beginDateValue 起始日期，长格式日期
     * @param endDateValue 截止日期 长格式日期
     ********************************************************/
    public static boolean compareDate(String beginDateValue, String endDateValue) {
        boolean retn;
        Date beginDate = parseStringToDate(beginDateValue, DateUtil.C_YYYY_MM_DD_HH_MM_SS);
        Date endDate = parseStringToDate(endDateValue, DateUtil.C_YYYY_MM_DD_HH_MM_SS);
        Date cDate = getCurrentDate();
        if (cDate.after(beginDate) && cDate.before(endDate)) {
            retn = true;
        } else {
            retn = false;
        }
        return retn;
    }

    /*******************************************************
     * 比较当前日期是否在指定的起始日期和截止日期内（如果在指定的日期之内返回true，如果不在指定的日期内返回false
     * 
     * @param beginDate 起始日期，短格式日期
     * @param endDate 截止日期 短格式日期
     ********************************************************/
    public static boolean compareShortDate(Date beginDate, Date endDate) {
        boolean retn;
        if (beginDate == null || endDate == null) {
            retn = false;
        } else {
            Date cDate = getCurrentDate();
            if (cDate.after(beginDate) && cDate.before(addDays(endDate, 1))) {
                retn = true;
            } else {
                retn = false;
            }
        }
        return retn;
    }

    /********************************************************
     * 
     * @param date ：java.lang.Date
     * @return java.lang.String
     ********************************************************/
    public static final String nullToKong(Date date) {
        if (date == null || date.toString().equals("null")) {
            return "";
        } else {
            return parseDateToString(date, C_YYYY_MM_DD_HH_MM_SS);
        }
    }

    /**
     * 功能：格式化日期)
     * 
     * @param vDate :(日期对象java.util.Date)
     * @param i : 1 则返回YYYY-MM-DD格式的日期字符串 i = 2 则返回YYYY-MM-DD HH:MM:SS格式的日期字符串 i = 3 则返回YYYY格式的日期字符串
     *        否则返回YYYY-MM-DD格式的日期字符串
     * @return java.lang.String
     */
    public static final String formatDateTime(Date vDate, int i) {
        if (vDate == null) {
            return null;
        }
        SimpleDateFormat formatter;
        if (i == 1) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        } else if (i == 2) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (i == 3) {
            formatter = new SimpleDateFormat("yyyy");
        } else if (i == 4) {
            formatter = new SimpleDateFormat("yyyy年MM月dd日");
        } else {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        }

        String dateString = formatter.format(vDate);
        return dateString;
    }

    /**
     * 格式化字符串为指定的字符串
     * 
     * @param dateString
     * @param format
     * @return
     */
    public static String getDateStrFormat(String dateString, String format) {
        return getDateStrFormat(dateString, C_YYYY_MM_DD, format);
    }

    /**
     * 格式化字符串为指定的字符串
     * 
     * @param dateString
     * @param sourceFormat
     * @param format
     * @return
     */
    public static String getDateStrFormat(String dateString, String sourceFormat, String format) {
        if (null == dateString || dateString.trim().length() < 1) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(sourceFormat);
        Date newDate = null;
        String returnDataStr = null;
        try {
            newDate = dateFormat.parse(dateString);
            dateFormat.applyPattern(format);
            returnDataStr = dateFormat.format(newDate);
        } catch (ParseException pe) {
            newDate = null;
        }
        return returnDataStr;
    }

    /**
     * 获取指定日期第一天
     * 
     * @param date
     * @return
     */
    public static String getFirstDay(String dateValue) {
        Date date = parseStringToDate(dateValue, C_YYYY_MM);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        return day_first;
    }

    /**
     * 获取指定日期最后一天
     * 
     * @param date
     * @return
     */
    public static String getLastDay(String dateValue) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = new GregorianCalendar();
        Date daystart = parseStringToDate(dateValue, C_YYYY_MM);;
        c.setTime(daystart);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(c.getTime());
    }

    /**
     * 获取下一个月第一天
     * 
     * @param dateValue
     * @return
     */
    public static String getNextMonthFirstDay(String dateValue) {
        // 获取下个月
        Date date = parseStringToDate(dateValue, C_YYYY_MM);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        // 获取下个月第一天
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(cal.getTime());
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        return day_first;
    }

    /**
     * 获取下一个月最后一天
     * 
     * @param dateValue
     * @return
     */
    public static String getNextMonthLastDay(String dateValue) {
        Date date = parseStringToDate(dateValue, C_YYYY_MM);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(c.getTime());
    }

    /**
     * 返回指定长度的日期域
     * 
     * @param date 日期域，例如年、月、日
     * @param length 长度
     * @return
     */
    public static String getDateBylength(int date, int length) {
        long l = (long) Math.pow(10D, length);
        return String.valueOf(l + date).substring(1);
    }

    /**
     * 获取两个时间直接的差值
     * 
     * @param receivedate
     * @param opdate
     * @param format
     * @return
     */
    public static long getTimeConsumeByLong(String receivedate, String opdate, String format) {
    	if(null==receivedate||null==opdate) {
    		return 0L;
    	}
        long time = DateUtil.parseStringToDate(opdate, format).getTime() - DateUtil.parseStringToDate(receivedate, format).getTime();
        return time;
    }

    /**
     * 计算两个字符串时间之间的差值（x天x小时x分钟x秒）, 默认格式：C_YYYY_MM_DD_HH_MM_SS
     * 
     * @param receivedate
     * @param opdate
     * @return
     */
    public static String getTimeConsume(String receivedate, String opdate,Locale locale) {
        return getTimeConsume(receivedate, opdate, C_YYYY_MM_DD_HH_MM_SS,locale);
    }

    /**
     * 计算两个字符串时间之间的差值（x天x小时x分钟x秒）
     * 
     * @param receivedate
     * @param opdate
     * @param format
     * @return
     */
    public static String getTimeConsume(String receivedate, String opdate, String format,Locale locale) {
    	return getTimeConsume(DateUtil.parseStringToDate(receivedate, format),DateUtil.parseStringToDate(opdate, format),locale);
    }
    
    /**
     * 计算两个字符串时间之间的差值（x天x小时x分钟x秒）
     * @param receivedate
     * @param opdate
     * @return
     */
    public static String getTimeConsume(Date receivedate, Date opdate,Locale locale) {
    	String[] tips=new String[] {" 秒 "," 分钟 "," 小时 "," 天 "};
    	if(Locale.SIMPLIFIED_CHINESE!=locale) {
    		tips=new String[] {" sec "," min "," hr "," d "};
    	}
    	try {
            String timeconsume = "";
            long time =opdate.getTime() - receivedate.getTime();
            if (0 == time) {
                timeconsume = "0"+tips[0];
            } else {
                long day = time / (24 * 60 * 60 * 1000);
                time = time % (24 * 60 * 60 * 1000);
                long hour = time / (60 * 60 * 1000);
                time = time % (60 * 60 * 1000);
                long min = time / (60 * 1000);
                time = time % (60 * 1000);
                long sec = time / 1000;
                if (day > 0) {
                    timeconsume += (day + tips[3]);
                }
                if (hour > 0) {
                    timeconsume += (hour + tips[2]);
                }
                if (min > 0) {
                    timeconsume += (min + tips[1]);
                }
                if (sec > 0) {
                    timeconsume += (sec + tips[0]);
                }
            }
            return timeconsume;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回指定日期之前的上一个周六所在日期，若指定日期本身是周六时，返回指定日期
     * 
     * @param date 指定日期，为null时，默认当前日期
     * @return
     */
    public static Date getLastSaturday(Date date) {
        if (null == date) {
            date = new Date();
        }
        if (C_WEAK[6].equals(getWeek(formatDateTime(date, 1)))) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return calendar.getTime();
    }

    /**
     * 返回指定日期之前的上一个周一所在日期，若指定日期本身是周一时，返回指定日期
     * 
     * @param date 指定日期，为null时，默认当前日期
     * @return
     */
    public static Date getLastMonday(Date date) {
        if (null == date) {
            date = new Date();
        }
        if (C_WEAK[1].equals(getWeek(formatDateTime(date, 1)))) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date tDate = calendar.getTime();
        if (tDate.after(date)) {
            calendar.add(Calendar.DATE, -7);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            tDate = calendar.getTime();
        }
        return tDate;
    }

    /**
     * 将日期转为其最小
     * 
     * @param date
     * @return
     */
    public static Date getMin(Date date) {
        String d = DateUtil.parseDateToString(date, DateUtil.C_YYYY_MM_DD);
        return parseStringToDate(d + " 00:00:00", DateUtil.C_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前月的第一天
     * 
     * @return
     */
    public static Date getFirstDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return c.getTime();
    }

    /**
     * 获取往前（-）或往后（+）n个月的第一天
     * 
     * @return
     */
    public static Date getFirstDayOfMonths(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, n);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return cal.getTime();
    }

    /**
     * 获取往前（-）或往后（+）n个月的最后一天
     * 
     * @return
     */
    public static Date getLastDayOfMonths(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, n + 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return cal.getTime();
    }

    /**
     * 获取当前年的第一天
     * 
     * @return
     */
    public static Date getFirstDayOfYear() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 判断日期格式:yyyy-mm-dd
     * 
     * @param sDate
     * @return
     */
    public static boolean isValidDate(String sDate) {
        String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
        String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))" + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        if ((sDate != null)) {
            Pattern pattern = Pattern.compile(datePattern1);
            Matcher match = pattern.matcher(sDate);
            if (match.matches()) {
                pattern = Pattern.compile(datePattern2);
                match = pattern.matcher(sDate);
                return match.matches();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 获取下days天，以当前日期为基准
     * 
     * @param days
     * @return
     */
    public static Date getNextDay(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 获取指定日期下days天，以指定的日期为基准
     * 
     * @param days
     * @param date 指定日期，格式（yyyy-mm-dd）
     * @return
     */
    public static Date getNextDay(String date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.parseStringToDate(date));
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }


    /**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {// 可以用new
        // Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     * 
     * @param specifiedDay 格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayAfter;
    }


    /**
     * 获取两个日期区间所有的天,起始时间格式yyyyMMdd
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<String> getAllDays(String startDate, String endDate) {
        List<String> listString = new ArrayList<String>();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        Date dateStart = parseStringToDate(startDate, C_YYYYMMDD);
        Date dateEnd = parseStringToDate(endDate, C_YYYYMMDD);
        startCalendar.setTime(dateStart);
        endCalendar.setTime(dateEnd);
        while (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {
            String datetemp = parseDateToString(startCalendar.getTime(), C_YYYYMMDD);
            listString.add(datetemp);
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return listString;
    }

    /**
     * 获取两个日期间的所有的天按照月分类,起始时间格式yyyyMMdd
     * 
     * @param startDate
     * @param endDate
     * @param weekMonth
     * @return
     */
    public static List<Map<String, List<String>>> getAllDaysByMonth(String startDate, String endDate) {

        List<Map<String, List<String>>> listAll = new ArrayList<Map<String, List<String>>>();
        List<String> listString = getAllDays(startDate, endDate);

        String[] str = new String[listString.size() + 1];
        int k = 0;
        for (String i : listString) {
            str[k] = i;
            k++;
        }
        String monthsub = str[0].trim().substring(0, 6);
        int kk = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != null) {
                if (!monthsub.equals(str[i].trim().substring(0, 6))) {
                    String[] in = new String[32];
                    System.arraycopy(str, kk, in, 0, i - kk);
                    int kki = 0;
                    for (int ini = 0; ini < 32; ini++) {
                        if (in[ini] != null) {
                            kki = ini;
                        }
                    }
                    String[] mmi = new String[kki + 1];
                    System.arraycopy(in, 0, mmi, 0, kki + 1);
                    Map<String, List<String>> map = new HashMap<String, List<String>>();
                    map.put(monthsub, Arrays.asList(mmi));
                    listAll.add(map);
                    kk = i;
                    monthsub = str[i].trim().substring(0, 6);
                }
                if (monthsub.equals(str[i].trim().substring(0, 6)) && str[i + 1] == null) {
                    String[] in = new String[32];
                    System.arraycopy(str, kk, in, 0, i - kk + 1);
                    int kki = 0;
                    for (int ini = 0; ini < 32; ini++) {
                        if (in[ini] != null) {
                            kki = ini;
                        }
                    }
                    String[] mmi = new String[kki + 1];
                    System.arraycopy(in, 0, mmi, 0, kki + 1);
                    Map<String, List<String>> map = new HashMap<String, List<String>>();
                    map.put(monthsub, Arrays.asList(mmi));
                    listAll.add(map);
                }
            }
        }
        return listAll;
    }

    /**
     * 获取两个日期间的所有的天按照月中每周分类，每周第一天是星期一,起始时间格式yyyyMMdd
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<Map<String, List<String>>> getAllDaysByWeek(String startDate, String endDate) {

        List<Map<String, List<String>>> listAll = new ArrayList<Map<String, List<String>>>();

        List<Map<String, List<String>>> monthList = new ArrayList<Map<String, List<String>>>();
        monthList = getAllDaysByMonth(startDate, endDate);

        Date date = parseStringToDate(startDate, C_YYYYMMDD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Date datee = parseStringToDate(endDate, C_YYYYMMDD);
        Calendar calendare = Calendar.getInstance();
        calendare.setTime(datee);
        calendare.add(Calendar.MONTH, 1);
        String subStarte = parseDateToString(calendare.getTime(), C_YYYYMMDD).trim().substring(0, 6);
        while (true) {
            String subStart = parseDateToString(calendar.getTime(), C_YYYYMMDD).trim().substring(0, 6);
            if (subStarte.equals(subStart)) {
                break;
            }

            for (Map<String, List<String>> mapList : monthList) {
                int k = 0;
                List<String> list = mapList.get(subStart);
                if (list == null) {
                    continue;
                }
                String[] arr = new String[32];
                for (String i : list) {
                    arr[k] = i;
                    k++;
                }

                Date datef = parseStringToDate(arr[0], C_YYYYMMDD);
                Calendar calendarf = Calendar.getInstance();
                calendarf.setFirstDayOfWeek(Calendar.MONDAY);
                calendarf.setTime(datef);
                int weekNum = calendarf.get(Calendar.WEEK_OF_MONTH);

                int kk = 0;
                for (int i = 0; i < 32; i++) {
                    if (arr[i] != null) {
                        Date datei = parseStringToDate(arr[i], C_YYYYMMDD);
                        Calendar calendari = Calendar.getInstance();
                        calendari.setFirstDayOfWeek(Calendar.MONDAY);
                        calendari.setTime(datei);
                        int weekNumi = calendari.get(Calendar.WEEK_OF_MONTH);
                        if (weekNumi != weekNum) {
                            String[] in = new String[7];
                            System.arraycopy(arr, kk, in, 0, i - kk);
                            kk = i;
                            int kki = 0;
                            for (int ini = 0; ini < 7; ini++) {
                                if (in[ini] != null) {
                                    kki = ini;
                                }
                            }
                            String[] mmi = new String[kki + 1];
                            System.arraycopy(in, 0, mmi, 0, kki + 1);
                            Map<String, List<String>> map = new HashMap<String, List<String>>();
                            map.put(arr[i].trim().substring(0, 6) + weekNum, Arrays.asList(mmi));
                            listAll.add(map);
                            weekNum = weekNumi;
                        }
                        if (weekNumi == weekNum && arr[i + 1] == null) {
                            String[] in = new String[7];
                            System.arraycopy(arr, kk, in, 0, i - kk + 1);
                            int kki = 0;
                            for (int ini = 0; ini < 7; ini++) {
                                if (in[ini] != null) {
                                    kki = ini;
                                }
                            }
                            String[] mmi = new String[kki + 1];
                            System.arraycopy(in, 0, mmi, 0, kki + 1);
                            Map<String, List<String>> map = new HashMap<String, List<String>>();
                            map.put(arr[i].trim().substring(0, 6) + weekNum, Arrays.asList(mmi));
                            listAll.add(map);
                        }
                    }
                }
            }
            calendar.add(Calendar.MONTH, 1);
        }
        return listAll;

    }
    
    /** 
     * 判断给定日期是否为月末的一天 
     * 
     * @param date 
     * @return true:是|false:不是 
     */ 
     public static boolean isLastDayOfMonth(Date date) { 
         Calendar calendar = Calendar.getInstance(); 
         calendar.setTime(date); 
         calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1)); 
         if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
             return true; 
         } 
         return false; 
     } 

    public static void main(String[] args) {
        String rentStartTime = "2018-08-21";
        String rentEndTime = "2020-09-20";
        System.out.println(compareDate(rentStartTime, rentEndTime));
    }
}
