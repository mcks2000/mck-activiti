package com.mck.activiti.common.util;

import cn.hutool.core.date.DateUtil;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * Java8 Date与LocalDate互转
 * 新增：1.LocalDate等相关时间格式 获取
 * 2.常见时间格式做格式化处理
 * 3.自定义时间时间格式化
 * 4.LocalDate与String之间格式互换
 * 5.时间间隔计算
 * 6.自定义时间，自定义格式化
 *
 * @author: mck
 * @Date: 2022/06/08 10:17
 */
public class DateFormatUtil {

    public static final DateTimeFormatter TIME_SECOND_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter SHORT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter LONG_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
    public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    //------------LocalDate等相关时间格式 获取

    /**
     * 返回当前的日期
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回当前时间
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 返回当前日期时间
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    //------------常见时间格式做格式化处理

    /**
     * yyyy-MM-dd
     */
    public static String getCurrentDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * yyMMdd
     */
    public static String getCurrentShortDateStr() {
        return LocalDate.now().format(SHORT_DATE_FORMATTER);
    }

    public static String getCurrentMonthStr() {
        return LocalDate.now().format(YEAR_MONTH_FORMATTER);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    public static LocalDateTime getCurrentDateTime() {
        return parseLocalDateTime(new Date().getTime());
    }

    /**
     * yyyy-MM-dd HH:mm:ss SSS
     *
     * @return
     */
    public static String getCurrentLongDateTimeStr() {
        return LocalDateTime.now().format(LONG_DATETIME_FORMATTER);
    }

    /**
     * yyMMddHHmmss
     */
    public static String getCurrentShortDateTimeStr() {
        return LocalDateTime.now().format(SHORT_DATETIME_FORMATTER);
    }

    /**
     * HH:mm:ss
     */
    public static String getCurrentTimeSecondStr() {
        return LocalTime.now().format(TIME_SECOND_FORMATTER);
    }

    /**
     * HH:mm
     */
    public static String getCurrentTimeStr() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    /**
     * HHmmss
     */
    public static String getCurrentShortTimeStr() {
        return LocalTime.now().format(SHORT_TIME_FORMATTER);
    }

    //------------自定义时间时间格式化

    public static String getCurrentDateStr(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentDateTimeStr(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentTimeStr(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }
    //------------自定义时间，自定义格式化

    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime parseLocalTime(String timeStr, String pattern) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDate2(LocalDate date, DateTimeFormatter pattern) {
        return date.format(pattern);
    }

    public static String formatLocalDateShort(LocalDate date) {
        return formatLocalDate2(date, SHORT_DATE_FORMATTER);
    }

    public static String formatLocalDateTime(LocalDateTime datetime, String pattern) {
        return datetime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalTime(LocalTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    //------------LocalDate与String之间格式互换

    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    public static LocalDateTime parseLocalDateTime(Long currentTimeMillis) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = dateformat.format(currentTimeMillis);
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    /**
     * 2021-04-27T12:56:45+08:00 转 localDateTime
     *
     * @param oldDateStr
     * @return
     */
    public static LocalDateTime parseLocalDateTimeEN(String oldDateStr) {
        if (ObjectUtils.isEmpty(oldDateStr)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        try {
            Date date = df.parse(oldDateStr);
            return parseLocalDateTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static LocalDateTime parseLocalDateTime(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        return localDateTime;
    }

    public static LocalDateTime parseLongLocalDateTime(String longDateTimeStr) {
        return LocalDateTime.parse(longDateTimeStr, LONG_DATETIME_FORMATTER);
    }

    public static LocalTime parseLocalTime(String timeStr) {
        return LocalTime.parse(timeStr, TIME_FORMATTER);
    }

    public static LocalTime parseLocalTimeSecond(String timeStr) {
        return LocalTime.parse(timeStr, TIME_SECOND_FORMATTER);
    }

    public static LocalTime parseLocalTimeShort(String timeStr) {
        return LocalTime.parse(timeStr, SHORT_TIME_FORMATTER);
    }

    public static String formatLocalDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public static String formatLocalDateTime(LocalDateTime datetime) {
        return datetime.format(DATETIME_FORMATTER);
    }

    public static String formatLocalDateTimeTry(LocalDateTime datetime) {
        if (ObjectUtils.isEmpty(datetime)) {
            return "";
        }
        return datetime.format(DATETIME_FORMATTER);
    }

    public static String formatLocalTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    public static String formatLocalTimeSecond(LocalTime time) {
        return time.format(TIME_SECOND_FORMATTER);
    }

    public static String formatLocalTimeShort(LocalTime time) {
        return time.format(SHORT_TIME_FORMATTER);
    }

    //------------时间间隔计算

    /**
     * 日期相隔秒
     */
    public static long periodHours(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Duration.between(startDateTime, endDateTime).get(ChronoUnit.SECONDS);
    }

    /**
     * 日期相隔天数
     */
    public static long periodDays(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 日期相隔周数
     */
    public static long periodWeeks(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.WEEKS);
    }

    /**
     * 日期相隔月数
     */
    public static long periodMonths(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.MONTHS);
    }

    /**
     * 日期相隔年数
     */
    public static long periodYears(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.YEARS);
    }

    /**
     * 是否当天
     */
    public static boolean isToday(LocalDate date) {
        return getCurrentLocalDate().equals(date);
    }

    /**
     * 获取当前毫秒数
     */
    public static Long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 判断是否为闰年
     */
    public static boolean isLeapYear(LocalDate localDate) {
        return localDate.isLeapYear();
    }

    //------------Date 和 LocalDate LocalTime 之间的转换

    /**
     * Date转换成LocalDate
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static LocalDate date2LocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date转换成LocalTime
     *
     * @param date
     * @return HH:mm:ss.SSS
     */
    public static LocalTime date2LocalTime(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * Date转换成LocalTime
     *
     * @param date
     * @return HH:mm
     */
    public static LocalTime date2LocalTimeHHmm(Date date) {
        if (null == date) {
            return null;
        }
        return date2LocalTimeFormat(date, "HH:mm");
    }


    /**
     * Date转换成date2LocalTimeFormat
     *
     * @param date
     * @return format格式时间
     */
    public static LocalTime date2LocalTimeFormat(Date date, String format) {
        if (null == date) {
            return null;
        }
        LocalTime localTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        String formatTime = localTime.format(DateTimeFormatter.ofPattern(format));
        return LocalTime.parse(formatTime);
    }


    /**
     * localTime 转date
     *
     * @param localTime
     * @return
     */
    public static Date localTime2Date(LocalTime localTime) {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    /**
     * LocalDate转换成Date
     *
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime转换成Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转成对应格式的时间格式
     *
     * @param date
     * @param format
     * @return String
     */
    public static String dateFormat(Date date, String format) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.now().format(formatter);
    }

    /**
     * DateDateTime转成对应格式的时间格式
     *
     * @param localDateTime
     * @param format
     * @return String
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dtf);
    }

    public static Date StringToDateFormat(String str) {
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 前一天
     *
     * @return
     */
    public static String preDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        String billDate = DateUtil.format(calendar.getTime(), "YYYY-MM-dd");
        return billDate;
    }


//    /**
//     * 原方法名 localTime2Date，先已修改
//     * 原方法体改成 localTime2Date2
//     *
//     * @ TODO 保留一个月时间，1个月后删除（2020-01-10 可删除）
//     */
//    @Deprecated
//    public static Date localTime2Date2(LocalTime localTime) throws ParseException {
//        LocalDate localDate = LocalDate.now();
//        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
//        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
//        String timeString = format.format(date);
//        return format.parse(timeString);
//    }

    public static List<LocalDate> listLocalDate(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> list = new ArrayList<>();
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(list::add);
        return list;
    }

    public static Date dateAddHour(Date startDate, Integer timeoutHour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.HOUR, timeoutHour);// 24小时制

        return cal.getTime();
    }

    public static void main(String[] args) throws ParseException {


        Date date = new Date();
        System.out.println(date);
        System.out.println("Date-->LocalDate:     " + date2LocalDate(date));
        System.out.println("Date-->LocalTime:     " + date2LocalTime(date));
        System.out.println("Date-->LocalTimeHHmm: " + date2LocalTimeHHmm(date));

        System.out.println("LocalTime-->Date:     " + localTime2Date(LocalTime.now()));
        System.out.println("LocalDate-->Date:     " + localDate2Date(LocalDate.now()));
        System.out.println("LocalDateTime-->Date: " + localDateTime2Date(LocalDateTime.now()));

        String date2String = dateFormat(date, "yyyy-MM-dd");
        LocalTime localTime = date2LocalTimeFormat(date, "HH:mm");
        System.out.println(date2String);
        System.out.println(localTime);

        System.out.println(LocalDate.parse("2020-12-12"));
        System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("2020-11-11")));
        System.out.println(localDateTimeFormat(LocalDateTime.now(), "HH:mm"));

//        Date date1 = localTime2Date2(LocalTime.parse("12:11"));
//        Date date2 = localTime2Date2(LocalTime.parse("12:12"));

        Date date3 = localTime2Date(LocalTime.parse("12:11"));
        Date date4 = localTime2Date(LocalTime.parse("12:12"));

//        System.out.println(date1);
//        System.out.println(date2);

        System.out.println(date3);
        System.out.println(date4);


    }
}

