package com.coder.util;

import java.util.Calendar;
import java.util.Date;

public final class ConstUtils {

    public static int KB = 1024;
    public static int MB = 1048576;
    public static int GB = 1073741824;

    public static long SEC = 1000;
    public static long MIN = 60000L;
    public static long HOUR = 3600000L;
    public static long DAY = 86400000L;

    public static String DOT = ".";

    public static String password = "52lixinling";

    /**
     * yyyy：年
     */
    public static String YEAR = "yyyy";
    /**
     * MM：月
     */
    public static String MONTH = "MM";
    /**
     * dd：日
     */
    public static String DAY_OF_MONTH = "dd";
    /**
     * hh：1~12小时制(1-12)
     */
    public static String HOUR_OF_DAY_12 = "hh";
    /**
     * HH：24小时制(0-23)
     */
    public static String HOUR_OF_DAY_24 = "HH";
    /**
     * mm：分
     */
    public static String MINUTE = "mm";
    /**
     * ss：秒
     */
    public static String SECOND = "ss";
    /**
     * S：毫秒
     */
    public static String MILLISECOND = "S";
    /**
     * E：星期几
     */
    public static String DAY_OF_WEEK = "E";
    /**
     * D：一年中的第几天
     */
    public static String DAY_OF_YEAR = "D";
    /**
     * F：一月中的第几个星期(会把这个月总共过的天数除以7)
     */
    public static String WEEK_OF_MONTH_BY_DAYS = "F";
    /**
     * w：一年中的第几个星期
     */
    public static String WEEK_OF_YEAR = "w";
    /**
     * W：一月中的第几星期(会根据实际情况来算)
     */
    public static String WEEK_OF_MONTH = "W";
    /**
     * a：上下午标识
     */
    public static String AM = "a";
    /**
     * k：和HH差不多，表示一天24小时制(1-24)。
     */
    public static String HOUR_OF_DAY_1_24 = "k";
    /**
     * K：和hh差不多，表示一天12小时制(0-11)。
     */
    public static String HOUR_OF_DAY_0_11 = "K";
    /**
     * z：表示时区
     */
    public static String TIMEZONE = "z";

    /**
     * 十六进制初值表
     */
    public static char HEXDIGITS[] = {
            '0', '1', '2', '3',
            '4', '5', '6', '7',
            '8', '9', 'a', 'b',
            'c', 'd',  'e', 'f'
    };

    /**
     * yyyy-MM-dd
     */
    public static String DATAPATTERN1 = "";

    /**
     * yyyy-MM-dd HH:mm
     */
    public static String DATAPATTERN2 = "yyyy-MM-dd HH:mm";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String DATAPATTERN3 = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy/MM/dd
     */
    public static String DATAPATTERN4 = "yyyy/MM/dd";

    /**
     * yyyy/MM/dd HH:mm
     */
    public static String DATAPATTERN5 = "yyyy/MM/dd HH:mm";

    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static String DATAPATTERN6 = "yyyy/MM/dd HH:mm:ss";

    public static String DATEREG = "^(\\d{1,4})(-|\\/)(\\d{1,2})\\2(\\d{1,2})$";

    /**
     * 密码的强度必须是包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间。
     */
    public static String PASSWORDREG = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$";

    /**
     * 中文
     */
    public static String CHINESEREG = "^[\\u4e00-\\u9fa5]{0,}$";

    /**
     * 由数字、26个英文字母或下划线组成的字符串
     */
    public static String WORDREG = "^\\w+$";

    /**
     * 校验E-Mail 地址
     */
    public static String EMAILREG = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

    /**
     * 校验身份证号码 15位
     */
    public static String IDNUMBER15REG = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";

    /**
     * 校验身份证号码 18位
     */
    public static String IDNUMBER18REG = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

    /**
     * 校验日期 "yyyy-mm-dd" 格式的日期校验，已考虑平闰年。
     */
    public static String DATEREGYYYYMMDDREG = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";

    /**
     * 校验金额
     */
    public static String MONEYREG = "^[0-9]+(.[0-9]{2})?$";

    /**
     * 校验手机号
     */
    public static String PHONEREG = "^((13|15|18)[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";

    /**
     * 判断IE的版本
     */
    public static String IEVERSIONREG = "^.*MSIE [5-8](?:\\.[0-9]+)?(?!.*Trident\\/[5-9]\\.0).*$";

    /**
     * 校验IP-v4地址
     */
    public static String IPV4REG = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";

    /**
     * 校验IP-v6地址
     */
    public static final String IPV6 = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";

    /**
     * 检查URL的前缀
     */
    public static String URLHEADREG = "^[a-zA-Z]+:\\/\\/";

    /**
     * 提取URL链接
     */
    public static String URLREG = "^(f|ht){1}(tp|tps):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?";

    /**
     * 文件路径及扩展名校验
     */
    public static String DIRREG = "^([a-zA-Z]\\:|\\\\)\\\\([^\\\\]+\\\\)*[^\\/:*?\"<>|]+\\.txt(l)?$";

    /**
     * 提取Color Hex Codes
     */
    public static String COLORHEXCODE = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

    /**
     * 提取网页图片
     */
    public static String IMGSRCREG = "(<a\\s*(?!.*\\brel=)[^>]*)(href=\"https?:\\/\\/)((?!(?:(?:www\\.)?'.implode('|(?:www\\.)?', $follow_list).'))[^\"]+)\"((?!.*\\brel=)[^>]*)(?:[^>]*)>";

    /**
     * 查找CSS属性
     */
    public static String CSSREG = "^\\s*[a-zA-Z\\-]+\\s*[:]{1}\\s[a-zA-Z0-9\\s.#]+[;]{1}";

    /**
     * 抽取注释
     */
    public static String ANNOTATIONREG = "<!--(.*?)-->";

    /**
     * 匹配HTML标签
     */
    public static String HTMLREG = "<\\/?\\w+((\\s+\\w+(\\s*=\\s*(?:\".*?\"|'.*?'|[\\^'\">\\s]+))?)+\\s*|\\s*)\\/?>";


    public static byte[] BASE64CHARS = {
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
