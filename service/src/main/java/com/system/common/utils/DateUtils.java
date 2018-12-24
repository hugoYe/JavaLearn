package com.system.common.utils;

import com.system.exception.BizException;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_CERTIFICAT_FORMAT = "yyyy-MM-dd HH:mm";

    public static String formatDate(final Date date) {
        return formatDate(date, DATETIME_CERTIFICAT_FORMAT);
    }

    public static String getFormatDate(final Date date) {
        return formatDate(date, DATETIME_FORMAT);

    }

    public static String formatDate(final Date date, final String pattern) {
        Assert.notNull(date, "Date");
        Assert.notNull(pattern, "Pattern");
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     *  
     *      * 获取随机日期 
     *      * @param beginDate 起始日期，格式为：yyyy-MM-dd 
     *      * @param endDate 结束日期，格式为：yyyy-MM-dd 
     *      * @return 
     *      
     */
    public static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT);
            Date start = format.parse(beginDate);  // 构造开始日期
            Date end = format.parse(endDate);  // 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }

            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    /**
     * 解析
     *
     * @param dateStr
     * @return
     */
    public static Date parse(String dateStr) {
        try {
            return new SimpleDateFormat(DATETIME_CERTIFICAT_FORMAT).parse(dateStr);
        } catch (ParseException e) {
            throw new BizException(e);
        }
    }

    /**
     * 解析
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parse(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            throw new BizException(e);
        }
    }

    public static Date parseDate(Date date) {
        return parse(formatDate(date), DATETIME_FORMAT);
    }
}
