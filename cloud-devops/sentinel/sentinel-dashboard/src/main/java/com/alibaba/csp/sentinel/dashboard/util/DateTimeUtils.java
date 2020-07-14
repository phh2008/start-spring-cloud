package com.alibaba.csp.sentinel.dashboard.util;

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
     * <p> Date to LocalDate </p>
     *
     * @param date
     * @return LocalDate
     * @author jacky
     * @date 2018/7/26
     * @version V1.0
     */
    public static LocalDate toLocalDate(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * <p> Date to LocalDateTime </p>
     *
     * @param date
     * @return LocalDateTime
     * @author jacky
     * @date 2018/7/26
     * @version V1.0
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * <p> LocalDate to Date </p>
     *
     * @param localDate
     * @return Date
     * @author jacky
     * @date 2018/7/26
     * @version V1.0
     */
    public static Date toDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * <p> LocalDateTime to Date </p>
     *
     * @param localDateTime
     * @return Date
     * @author jacky
     * @date 2018/7/26
     * @version V1.0
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * <p>
     * 格式为人性化字符串 :
     * 如：刚刚，x分钟前，x小时前，x天前，x月前，yyyy-MM
     * </p>
     *
     * @param
     * @return
     * @throws
     * @author jacky
     * @date 2019/4/2
     * @version V1.0
     */
    public static String fmtHuman(LocalDateTime before) {
        if (before == null) {
            return "";
        }
        final LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(before, now);
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
        return before.format(FMT_YYYY_MM);
    }


}
