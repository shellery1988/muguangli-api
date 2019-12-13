package com.muguangli.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author NUC7IBNK
 */
public final class DateFormatUtil {

    private static final Logger log = Logger.getLogger(DateFormatUtil.class);

    /** 日期格式yyyy-MM-dd HH:mm:ss字符串常量 */
    public static final String FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /** 日期格式yyyyMMddHHmmss字符串常量 */
    private static final String FULL_PATTERN_SHORT = "yyyyMMddHHmmss";
    
    /** 日期格式yyyy-MM-dd字符串常量 */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    
    /** 日期格式yyyyMMdd字符串常量 */
    public static final String DATE_PATTERN_SHORT = "yyyyMMdd";
    
    /** 日期格式HH:mm:ss字符串常量 */
    private static final String TIME_FORMAT = "HH:mm:ss";
    
    /** 日期格式HHmmss字符串常量 */
    private static final String TIME_FORMAT_SHORT = "HHmmss";
    
    private DateFormatUtil() {
    	
    }

    public static String date2StringFull(Date date) {
        return date2StringPattern(date, FULL_PATTERN);
    }
    
    public static String date2StirngFullShort(Date date) {
        return date2StringPattern(date, FULL_PATTERN_SHORT);
    }
    
    public static String date2StringDate(Date date) {
        return date2StringPattern(date, DATE_PATTERN);
    }
    
    public static String date2StringDateShort(Date date) {
        return date2StringPattern(date, DATE_PATTERN_SHORT);
    }
    
    public static String date2StringTime(Date date) {
        return date2StringPattern(date, TIME_FORMAT);
    }
    
    public static String date2StringTimeShort(Date date) {
        return date2StringPattern(date, TIME_FORMAT_SHORT);
    }
    
    public static String date2StringPattern(Date date, String pattern) {
    	try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			if(null == date) {
				date = new Date();
			}
			return formatter.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("+8")));
		} catch (Exception e) {
			log.error("DateFormatUtil dateToStringPattern error:"+e);
			throw e;
		}
    }
    
    public static LocalDate str2LocalDate(String dateStr) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    	LocalDate date = LocalDate.parse(dateStr, formatter);
    	return date;
    }
    
    public static LocalDateTime str2LocalDateTime(String dateStr) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FULL_PATTERN);
        LocalDateTime time = LocalDateTime.parse(dateStr, formatter);
        return time;
    }
    
    public static Date localDate2Date(LocalDate localDate) {
    	ZoneId zoneId = ZoneId.systemDefault();
    	ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }
    
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
    	ZoneId zoneId = ZoneId.systemDefault();
    	ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
    
    public static LocalDate date2LocalDate(Date date){
    	Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }
    
    public static LocalDateTime date2LocalDateTime(Date date){
    	Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }
    
}
