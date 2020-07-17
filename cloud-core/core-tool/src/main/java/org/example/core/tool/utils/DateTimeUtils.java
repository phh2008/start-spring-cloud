package org.example.core.tool.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * java 8 datetime
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/23
 */
public class DateTimeUtils {

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * HH:mm:ss
     */
    public static final String HH_MM_SS = "HH:mm:ss";

    /**
     * yyyyMMddHHmmss
     */
    public static final DateTimeFormatter FMT_YYYYMMDD24HHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    /**
     * yyMMddHHmmss
     */
    public static final DateTimeFormatter FMT_YYMMDD24HHMMSS = DateTimeFormatter.ofPattern("yyMMddHHmmss");
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter FMT_YYYY_MM_DD_24HH_MM_SS = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
    /**
     * yyyyMMdd
     */
    public static final DateTimeFormatter FMT_YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
    /**
     * yyyy-MM-dd
     */
    public static final DateTimeFormatter FMT_YYYY_MM_DD = DateTimeFormatter.ofPattern(YYYY_MM_DD);

    /**
     * yyyy-MM
     */
    public static final DateTimeFormatter FMT_YYYY_MM = DateTimeFormatter.ofPattern("yyyy-MM");

    /**
     * yy/MM/dd
     */
    public static final DateTimeFormatter FMT_YY_LMM_LDD = DateTimeFormatter.ofPattern("yy/MM/dd");

    /**
     * HHmmss
     */
    public static final DateTimeFormatter FMT_24HHMMSS = DateTimeFormatter.ofPattern("HHmmss");
    /**
     * HH:mm:ss
     */
    public static final DateTimeFormatter FMT_24HH_MM_SS = DateTimeFormatter.ofPattern(HH_MM_SS);
    /**
     * HH:mm
     */
    public static final DateTimeFormatter FMT_24HH_MM = DateTimeFormatter.ofPattern("HH:mm");


    /**
     * Date to LocalDate
     *
     * @param date 日期时间
     * @return LocalDate
     */
    public static LocalDate toLocalDate(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date to LocalDateTime
     *
     * @param date 日期时间
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDate to Date
     *
     * @param localDate 日期
     * @return Date
     */
    public static Date toDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime to Date
     *
     * @param localDateTime 日期时间
     * @return Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 格式为人性化字符串 :
     * 如：刚刚，x分钟前，x小时前，x天前，x月前，yyyy-MM
     *
     * @param dateTime 日期时间
     * @return string
     */
    public static String fmtHuman(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        final LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);
        long seconds = duration.getSeconds();
        if (seconds <= 60) {
            return "刚刚";
        } else if (seconds < 3600) {
            return seconds / 60 + "分钟前";
        } else if (seconds < 86400) {
            return seconds / 3600 + "小时前";
        } else if (seconds < 2592000) {
            return seconds / 86400 + "天前";
        } else if (seconds < 31104000) {
            return seconds / 2592000 + "个月前";
        }
        return dateTime.format(FMT_YYYY_MM);
    }


}
