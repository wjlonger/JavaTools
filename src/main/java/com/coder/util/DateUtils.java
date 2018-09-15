package com.coder.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;


public final class DateUtils {

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
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.HOUR, hours);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }


    public static String format(Date date,String formatStr){
        if(date == null) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    public static Date parse(String dateStr,String formatStr){
        if(StringUtils.isNullOrEmpty(dateStr) || StringUtils.isNullOrEmpty(formatStr)){
            return null;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(format(now,ConstUtils.DATAPATTERN3));
        System.out.println(format(getNewDate(now,0,0,1,24,0,0),ConstUtils.DATAPATTERN3));
    }
}
