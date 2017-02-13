package com.npu.library.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZhangRenyu
 * 
 * 日期转换工具
 *
 */
public class DateUtils {


    public static final long MILLISECOND_OF_DAY = 1000 * 60 * 60 * 24;

    /**
     * 添加或减少天数
     */
    public static Date addDay(Date date, int days) {
        if (date != null) {
            return new Date(date.getTime() + days * MILLISECOND_OF_DAY);
        }
        return date;
    }

    public static Date parseDate(String strDate) {
        if (strDate == null || strDate.equals("")) {
            return null;
        }
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
}
