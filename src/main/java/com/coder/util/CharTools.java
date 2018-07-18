package com.coder.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import static com.coder.util.ConvertUtils.specialCharacter2String;

public class CharTools {

    public static final String ISO885912GB2312(String text) {
        String result = "";
        try {
            result = new String(text.getBytes("ISO-8859-1"), "GB2312");
        } catch (UnsupportedEncodingException ex) {
            result = ex.toString();
        }
        return result;
    }

    public static final String ISO885912GBK(String text) {
        try {
            if (text == null) {
                return "";
            } else {
                text = text.trim();
                text = new String(text.getBytes("ISO8859_1"), "GBK");
                return text;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    public static final String ISO885912UTF8(String text) {
        try {
            if (text == null) {
                return "";
            } else {
                text = new String(text.getBytes("ISO-8859-1"), "UTF-8");
                return text;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    public static final String UTF82GBK(String text) {
        try {
            if (text == null) {
                return "";
            } else {
                text = text.trim();
                text = new String(text.getBytes("UTF-8"), "GBK");
                return text;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    public static final String UTF82ISO88591(String text) {
        try {
            if (text == null) {
                return "";
            } else {
                text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
                return text;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    public static final String GBK2ISO88591(String text) {
        try {
            if (text == null) {
                return "";
            } else {
                text = new String(text.getBytes("GBK"), "ISO8859_1");
                return text;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    public static final String GBK2UTF8(String text) {
        try {
            if (text == null) {
                return "";
            } else {
                text = new String(text.getBytes("GBK"), "UTF-8");
                return text;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    public static final String GB23122ISO88591(String text) {
        String result = "";
        try {
            result = new String(text.getBytes("GB2312"), "ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static final String Utf8URLencode(String text) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                result.append(c);
            } else {
                byte[] b = new byte[0];
                try {
                    b = Character.toString(c).getBytes("UTF-8");
                } catch (Exception ex) {
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    result.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return result.toString();
    }

    public static final String Utf8URLdecode(String text) {
        String result = "";
        int p = 0;
        if (text != null && text.length() > 0) {
            text = text.toLowerCase();
            p = text.indexOf("%e");
            if (p == -1) {
                return text;
            }
            while (p != -1) {
                result += text.substring(0, p);
                text = text.substring(p, text.length());
                if (text == "" || text.length() < 9) {
                    return result;
                }
                result += CodeToWord(text.substring(0, 9));
                text = text.substring(9, text.length());
                p = text.indexOf("%e");
            }
        }
        return result + text;
    }

    private static final String CodeToWord(String text) {
        String result;

        if (Utf8codeCheck(text)) {
            byte[] code = new byte[3];
            code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
            code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
            code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
            try {
                result = new String(code, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                result = null;
            }
        } else {
            result = text;
        }
        return result;
    }

    private static final boolean Utf8codeCheck(String text) {
        String sign = "";
        if (text.startsWith("%e")) {
            for (int i = 0, p = 0; p != -1; i++) {
                p = text.indexOf("%", p);
                if (p != -1) {
                    p++;
                }
                sign += p;
            }
        }
        return sign.equals("147-1");
    }

    public static final boolean ISUTF8URL(String text) {
        text = text.toLowerCase();
        int p = text.indexOf("%");
        if (p != -1 && text.length() - p > 9) {
            text = text.substring(p, p + 9);
        }
        return Utf8codeCheck(text);
    }

    public static final String replace(String strSrc, String strOld, String strNew) {
        if (strSrc == null || strOld == null || strNew == null) {
            return "";
        }
        int i = 0;
        if (strOld.equals(strNew)) {
            return strSrc;
        }
        if ((i = strSrc.indexOf(strOld, i)) >= 0) {
            char[] arr_cSrc = strSrc.toCharArray();
            char[] arr_cNew = strNew.toCharArray();
            int intOldLen = strOld.length();
            StringBuffer buf = new StringBuffer(arr_cSrc.length);
            buf.append(arr_cSrc, 0, i).append(arr_cNew);
            i += intOldLen;
            int j = i;
            while ((i = strSrc.indexOf(strOld, i)) > 0) {
                buf.append(arr_cSrc, j, i - j).append(arr_cNew);
                i += intOldLen;
                j = i;
            }
            buf.append(arr_cSrc, j, arr_cSrc.length - j);
            return buf.toString();
        }
        return strSrc;
    }

    public static final String htmlEncode(String strSrc) {
        if (strSrc == null) {
            return "";
        }
        char[] arr_cSrc = strSrc.toCharArray();
        StringBuffer buf = new StringBuffer(arr_cSrc.length);
        char ch;
        for (int i = 0; i < arr_cSrc.length; i++) {
            ch = arr_cSrc[i];
            buf.append(specialCharacter2String(ch));
        }
        return buf.toString();
    }

    public static final String htmlEncode(String strSrc, int quotes) {
        if (strSrc == null) {
            return "";
        }
        if (quotes == 0) {
            return htmlEncode(strSrc);
        }
        char[] arr_cSrc = strSrc.toCharArray();
        StringBuffer buf = new StringBuffer(arr_cSrc.length);
        char ch;
        for (int i = 0; i < arr_cSrc.length; i++) {
            ch = arr_cSrc[i];
            buf.append(specialCharacter2String(ch, quotes));
        }
        return buf.toString();
    }

    public static final String htmlDecode(String strSrc) {
        if (strSrc == null) {
            return "";
        }
        strSrc = strSrc.replaceAll("&lt;", "<");
        strSrc = strSrc.replaceAll("&gt;", ">");
        strSrc = strSrc.replaceAll("&quot;", "\"");
        strSrc = strSrc.replaceAll("&#039;", "'");
        strSrc = strSrc.replaceAll("&amp;", "&");
        return strSrc;
    }

    public final static String MD5(String s) {
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = ConstUtils.HEXDIGITS[byte0 >>> 4 & 0xf];
                str[k++] = ConstUtils.HEXDIGITS[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String StringToUnicode(String text) {
        String result = "";
        int input;
        StringReader isr;
        try {
            isr = new StringReader(new String(text.getBytes(), "GBK"));
        } catch (UnsupportedEncodingException e) {
            return "-1";
        }
        try {
            while ((input = isr.read()) != -1) {
                result = result + "&#x" + Integer.toHexString(input) + ";";

            }
        } catch (IOException e) {
            return "-2";
        }
        isr.close();
        return result;

    }

    public static String gb2utf(String inStr) {
        char temChr;
        int ascInt;
        int i;
        String result = "";
        if (inStr == null) {
            inStr = "";
        }
        for (i = 0; i < inStr.length(); i++) {
            temChr = inStr.charAt(i);
            ascInt = temChr + 0;
            if (Integer.toHexString(ascInt).length() > 2) {
                result = result + "&#x" + Integer.toHexString(ascInt) + ";";
            } else {
                result = result + temChr;
            }
        }
        return result;
    }

    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    public static StringBuffer decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr;
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer;
    }

}
