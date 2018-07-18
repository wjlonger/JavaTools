package com.coder.util;

import java.util.*;
import java.text.*;

public class DateHandler {

    public static int openDay = 5;
    private String iDate = "";
    private int iYear;
    private int iMonth;
    private int iDay;

    //  iDateTime = 2002-01-01 23:23:23
    public void setDate(String iDateTime) {
        this.iDate = iDateTime.substring(0, 10);
    }

    public String getDate() {
        return this.iDate;
    }

    public int getYear() {
        iYear = Integer.parseInt(iDate.substring(0, 4));
        return iYear;
    }

    public int getMonth() {
        iMonth = Integer.parseInt(iDate.substring(5, 7));
        return iMonth;
    }

    public int getDay() {
        iDay = Integer.parseInt(iDate.substring(8, 10));
        return iDay;
    }

    public static String subDate(String date) {
        return date.substring(0, 10);
    }

    public static boolean isSeason(String date) {
        int month = Integer.parseInt(date.substring(5, 7));
        switch (month){
            case 3: case 6: case 9: case 12:return true;
            default:return false;
        }
    }

    public static String getDateFromNow(int afterDay) {
        GregorianCalendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        date = calendar.getTime();
        return df.format(date);
    }

    public static String getDateFromNow(int afterDay, String format_string) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        DateFormat df = new SimpleDateFormat(format_string);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        date = calendar.getTime();
        return df.format(date);
    }

    public static String getNowForFileName(int afterDay) {
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        Date date = calendar.getTime();
        return df.format(date);
    }

    public int getDateCompare(String limitDate, int afterDay) {
        GregorianCalendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + afterDay);
        date = calendar.getTime();
        iDate = limitDate;
        calendar.set(getYear(), getMonth() - 1, getDay());
        Date dateLimit = calendar.getTime();
        return dateLimit.compareTo(date);
    }

    public int getDateCompare(String limitDate) {
        iDate = limitDate;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(getYear(), getMonth() - 1, getDay());
        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        return date.compareTo(nowDate);
    }

    public long getLongCompare(String limitDate) {
        iDate = limitDate;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(getYear(), getMonth() - 1, getDay());
        Date date = calendar.getTime();
        long datePP = date.getTime();
        Date nowDate = new Date();
        long dateNow = nowDate.getTime();
        return ((dateNow - datePP) / (24 * 60 * 60 * 1000));
    }

    public String getStringCompare(String limitDate, int openDay) {
        iDate = limitDate;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(getYear(), getMonth() - 1, getDay());
        Date date = calendar.getTime();
        long datePP = date.getTime();
        Date nowDate = new Date();
        long dateNow = nowDate.getTime();
        long overDay = ((dateNow - datePP) / (24 * 60 * 60 * 1000));
        String info = "";
        return info;

    }

    public static int diffDate(String beforeDate, String afterDate) {
        String[] tt = beforeDate.split("-");
        Date firstDate = new Date(Integer.parseInt(tt[0]), Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
        tt = afterDate.split("-");
        Date nextDate = new Date(Integer.parseInt(tt[0]), Integer.parseInt(tt[1]) - 1, Integer.parseInt(tt[2]));
        return (int) (nextDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000);
    }

    public static String getToday() {
        Calendar cld = Calendar.getInstance();
        Date date = new Date();
        cld.setTime(date);
        int intMon = cld.get(Calendar.MONTH) + 1;
        int intDay = cld.get(Calendar.DAY_OF_MONTH);
        String mons = String.valueOf(intMon);
        String days = String.valueOf(intDay);
        if (intMon < 10) {
            mons = "0" + String.valueOf(intMon);
        }
        if (intDay < 10) {
            days = "0" + String.valueOf(intDay);
        }
        return String.valueOf(cld.get(Calendar.YEAR)) + "-" + mons + "-" + days;
    }

    public static String getCurrentMonth() {
        String strmonth = null;
        Calendar cld = Calendar.getInstance();
        Date date = new Date();
        cld.setTime(date);
        int intMon = cld.get(Calendar.MONTH) + 1;
        if (intMon < 10) {
            strmonth = "0" + String.valueOf(intMon);
        } else {
            strmonth = String.valueOf(intMon);
        }
        date = null;
        return strmonth;
    }

    public static String getYestoday() {
        Calendar cld = Calendar.getInstance();
        Date date = new Date();
        cld.setTime(date);
        cld.add(Calendar.DATE, -1);
        int intMon = cld.get(Calendar.MONTH) + 1;
        int intDay = cld.get(Calendar.DAY_OF_MONTH);
        String mons = String.valueOf(intMon);
        String days = String.valueOf(intDay);
        if (intMon < 10) {
            mons = "0" + String.valueOf(intMon);
        }
        if (intDay < 10) {
            days = "0" + String.valueOf(intDay);
        }
        return String.valueOf(cld.get(Calendar.YEAR)) + "-" + mons + "-" + days;
    }

    public static int getWorkDay(String date, int sign) {
        int month = 0;
        int week = 0;
        int workDay = 0;
        Calendar rightNow = Calendar.getInstance();
        DateHandler dateOver = new DateHandler();
        dateOver.setDate(date);
        rightNow.set(Calendar.YEAR, dateOver.getYear());
        rightNow.set(Calendar.MONTH, dateOver.getMonth() - 1);
        rightNow.set(Calendar.DATE, dateOver.getDay());
        month = rightNow.get(Calendar.MONTH);
        while (rightNow.get(Calendar.MONTH) == month) {
            week = rightNow.get(Calendar.DAY_OF_WEEK);
            if (week == 1 || week == 7) {
            } else {
                workDay++;
            }
            rightNow.add(Calendar.DATE, sign);
        }
        return workDay;
    }
}
