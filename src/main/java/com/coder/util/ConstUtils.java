package com.coder.util;

public class ConstUtils {

    public static final int KB = 1024;
    public static final int MB = 1048576;
    public static final int GB = 1073741824;

    public static final long SEC = 1000;
    public static final long MIN = 60000L;
    public static final long HOUR = 3600000L;
    public static final long DAY = 86400000L;

    public static final char HEXDIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'};

    public static final String DATAPATTERN1 = "yyyy-MM-dd";
    public static final String DATAPATTERN2 = DATAPATTERN1 + " HH:mm";
    public static final String DATAPATTERN3 = DATAPATTERN2 + ":ss";

    public static final String DATAPATTERN4 = "yyyy/MM/dd";
    public static final String DATAPATTERN5 = DATAPATTERN4 + " HH:mm";
    public static final String DATAPATTERN6 = DATAPATTERN5 + ":ss";

    public static final byte[] BASE64CHARS = {
            'A','B','C','D','E','F','G','H',
            'I','J','K','L','M','N','O','P',
            'Q','R','S','T','U','V','W','X',
            'Y','Z','a','b','c','d','e','f',
            'g','h','i','j','k','l','m','n',
            'o','p','q','r','s','t','u','v',
            'w','x','y','z','0','1','2','3',
            '4','5','6','7','8','9','+','/',
    };


    enum MemoryUnit{
        BYTE,
        KB,
        MB,
        GB,
    }

    enum TimeUnit{
        MSEC,
        SEC,
        MIN,
        HOUR,
        DAY,
    }


}
