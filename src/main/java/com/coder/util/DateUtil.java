package com.coder.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;


public final class DateUtil {

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    public static SimpleDateFormat ymdhms = new SimpleDateFormat(ConstUtils.DATAPATTERN3);

    /**
     * 对时间加减得到指定时间
     * date可以不传，不穿默认相对于今天计算
     * @param date
     * @param year
     * @param month
     * @param day
     * @param hours
     * @param minute
     * @param second
     * @return
     */
    public static Date getNewDate(Date date,int year,int month,int day,int hours,int minute,int second){
        if(date == null){
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, month);
        calendar.add(Calendar.HOUR, hours);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }


    /**
     * 比较两个时间大小
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1,Date date2) {
        if(date1 == null || date2 == null){
            return false;
        }
        return date1.before(date2);
    }
}
