package com.coder.util;

public class StringUtils {

    public static boolean isNullOrSpace(String s){

        if(s == null || "".equals(s.trim())){
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(String s){

        if(s == null || s.length() == 0){
            return true;
        }
        return false;
    }

}
