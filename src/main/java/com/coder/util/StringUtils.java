package com.coder.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.regex.Pattern;

import static com.coder.util.ConvertUtils.specialCharacter2String;

/**
 * @author WJL
 */
public final class StringUtils {

    public static String EMPTY = "";
    public static String GB2312 = "GB2312";
    public static String ISO88591 = "ISO-8859-1";
    public static String UTF8 = "UTF-8";
    public static String UTF16 = "UTF16";
    public static String UTF32 = "UTF32";
    public static String GBK = "GBK";
    public static String PERCENT_E = "%e";
    public static char CHAR_PERCENT = '%';
    public static String STRING_PERCENT = "%";


    private static final String[] hex = { "00", "01", "02", "03", "04", "05",
            "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B",
            "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26",
            "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31",
            "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C",
            "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47",
            "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52",
            "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D",
            "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68",
            "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73",
            "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E",
            "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
            "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94",
            "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
            "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA",
            "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5",
            "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0",
            "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB",
            "CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6",
            "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1",
            "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC",
            "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7",
            "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };

    private static final byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01,
            0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };
    private static String[] SENSITIVE_WORDS= {
            "and"   ,"exec"    ,"execute" ,"insert"  ,"select","delete",
            "update","count"   ,"drop"    ,"chr"     ,"mid"   ,"master", "truncate",
            "char"  ,"declare" ,"sitename","net user","xp_cmdshell",
            "like"  ,"and"     ,"exec"    ,"execute" ,"insert","create","drop",
            "table" ,"from"    ,"grant"   ,"union"   ,"where",
            "select","delete"  ,"update"  ,"order"   ,"by","count","chr","mid",
            "master","truncate","char"    ,"declare" ,"or","use","group_concat","column_name",
            "information_schema.columns","table_schema" };
    private static String[] SENSITIVE_CHARS = {"*","'",";","or","-","--","+","//","/","%","#","="};

    private static HashMap<String, Integer> htmlEntities = new HashMap<String, Integer>();

    static {
        htmlEntities.put("nbsp", new Integer(160));
        htmlEntities.put("iexcl", new Integer(161));
        htmlEntities.put("cent", new Integer(162));
        htmlEntities.put("pound", new Integer(163));
        htmlEntities.put("curren", new Integer(164));
        htmlEntities.put("yen", new Integer(165));
        htmlEntities.put("brvbar", new Integer(166));
        htmlEntities.put("sect", new Integer(167));
        htmlEntities.put("uml", new Integer(168));
        htmlEntities.put("copy", new Integer(169));
        htmlEntities.put("ordf", new Integer(170));
        htmlEntities.put("laquo", new Integer(171));
        htmlEntities.put("not", new Integer(172));
        htmlEntities.put("shy", new Integer(173));
        htmlEntities.put("reg", new Integer(174));
        htmlEntities.put("macr", new Integer(175));
        htmlEntities.put("deg", new Integer(176));
        htmlEntities.put("plusmn", new Integer(177));
        htmlEntities.put("sup2", new Integer(178));
        htmlEntities.put("sup3", new Integer(179));
        htmlEntities.put("acute", new Integer(180));
        htmlEntities.put("micro", new Integer(181));
        htmlEntities.put("para", new Integer(182));
        htmlEntities.put("middot", new Integer(183));
        htmlEntities.put("cedil", new Integer(184));
        htmlEntities.put("sup1", new Integer(185));
        htmlEntities.put("ordm", new Integer(186));
        htmlEntities.put("raquo", new Integer(187));
        htmlEntities.put("frac14", new Integer(188));
        htmlEntities.put("frac12", new Integer(189));
        htmlEntities.put("frac34", new Integer(190));
        htmlEntities.put("iquest", new Integer(191));
        htmlEntities.put("Agrave", new Integer(192));
        htmlEntities.put("Aacute", new Integer(193));
        htmlEntities.put("Acirc", new Integer(194));
        htmlEntities.put("Atilde", new Integer(195));
        htmlEntities.put("Auml", new Integer(196));
        htmlEntities.put("Aring", new Integer(197));
        htmlEntities.put("AElig", new Integer(198));
        htmlEntities.put("Ccedil", new Integer(199));
        htmlEntities.put("Egrave", new Integer(200));
        htmlEntities.put("Eacute", new Integer(201));
        htmlEntities.put("Ecirc", new Integer(202));
        htmlEntities.put("Euml", new Integer(203));
        htmlEntities.put("Igrave", new Integer(204));
        htmlEntities.put("Iacute", new Integer(205));
        htmlEntities.put("Icirc", new Integer(206));
        htmlEntities.put("Iuml", new Integer(207));
        htmlEntities.put("ETH", new Integer(208));
        htmlEntities.put("Ntilde", new Integer(209));
        htmlEntities.put("Ograve", new Integer(210));
        htmlEntities.put("Oacute", new Integer(211));
        htmlEntities.put("Ocirc", new Integer(212));
        htmlEntities.put("Otilde", new Integer(213));
        htmlEntities.put("Ouml", new Integer(214));
        htmlEntities.put("times", new Integer(215));
        htmlEntities.put("Oslash", new Integer(216));
        htmlEntities.put("Ugrave", new Integer(217));
        htmlEntities.put("Uacute", new Integer(218));
        htmlEntities.put("Ucirc", new Integer(219));
        htmlEntities.put("Uuml", new Integer(220));
        htmlEntities.put("Yacute", new Integer(221));
        htmlEntities.put("THORN", new Integer(222));
        htmlEntities.put("szlig", new Integer(223));
        htmlEntities.put("agrave", new Integer(224));
        htmlEntities.put("aacute", new Integer(225));
        htmlEntities.put("acirc", new Integer(226));
        htmlEntities.put("atilde", new Integer(227));
        htmlEntities.put("auml", new Integer(228));
        htmlEntities.put("aring", new Integer(229));
        htmlEntities.put("aelig", new Integer(230));
        htmlEntities.put("ccedil", new Integer(231));
        htmlEntities.put("egrave", new Integer(232));
        htmlEntities.put("eacute", new Integer(233));
        htmlEntities.put("ecirc", new Integer(234));
        htmlEntities.put("euml", new Integer(235));
        htmlEntities.put("igrave", new Integer(236));
        htmlEntities.put("iacute", new Integer(237));
        htmlEntities.put("icirc", new Integer(238));
        htmlEntities.put("iuml", new Integer(239));
        htmlEntities.put("eth", new Integer(240));
        htmlEntities.put("ntilde", new Integer(241));
        htmlEntities.put("ograve", new Integer(242));
        htmlEntities.put("oacute", new Integer(243));
        htmlEntities.put("ocirc", new Integer(244));
        htmlEntities.put("otilde", new Integer(245));
        htmlEntities.put("ouml", new Integer(246));
        htmlEntities.put("divide", new Integer(247));
        htmlEntities.put("oslash", new Integer(248));
        htmlEntities.put("ugrave", new Integer(249));
        htmlEntities.put("uacute", new Integer(250));
        htmlEntities.put("ucirc", new Integer(251));
        htmlEntities.put("uuml", new Integer(252));
        htmlEntities.put("yacute", new Integer(253));
        htmlEntities.put("thorn", new Integer(254));
        htmlEntities.put("yuml", new Integer(255));
        htmlEntities.put("fnof", new Integer(402));
        htmlEntities.put("Alpha", new Integer(913));
        htmlEntities.put("Beta", new Integer(914));
        htmlEntities.put("Gamma", new Integer(915));
        htmlEntities.put("Delta", new Integer(916));
        htmlEntities.put("Epsilon", new Integer(917));
        htmlEntities.put("Zeta", new Integer(918));
        htmlEntities.put("Eta", new Integer(919));
        htmlEntities.put("Theta", new Integer(920));
        htmlEntities.put("Iota", new Integer(921));
        htmlEntities.put("Kappa", new Integer(922));
        htmlEntities.put("Lambda", new Integer(923));
        htmlEntities.put("Mu", new Integer(924));
        htmlEntities.put("Nu", new Integer(925));
        htmlEntities.put("Xi", new Integer(926));
        htmlEntities.put("Omicron", new Integer(927));
        htmlEntities.put("Pi", new Integer(928));
        htmlEntities.put("Rho", new Integer(929));
        htmlEntities.put("Sigma", new Integer(931));
        htmlEntities.put("Tau", new Integer(932));
        htmlEntities.put("Upsilon", new Integer(933));
        htmlEntities.put("Phi", new Integer(934));
        htmlEntities.put("Chi", new Integer(935));
        htmlEntities.put("Psi", new Integer(936));
        htmlEntities.put("Omega", new Integer(937));
        htmlEntities.put("alpha", new Integer(945));
        htmlEntities.put("beta", new Integer(946));
        htmlEntities.put("gamma", new Integer(947));
        htmlEntities.put("delta", new Integer(948));
        htmlEntities.put("epsilon", new Integer(949));
        htmlEntities.put("zeta", new Integer(950));
        htmlEntities.put("eta", new Integer(951));
        htmlEntities.put("theta", new Integer(952));
        htmlEntities.put("iota", new Integer(953));
        htmlEntities.put("kappa", new Integer(954));
        htmlEntities.put("lambda", new Integer(955));
        htmlEntities.put("mu", new Integer(956));
        htmlEntities.put("nu", new Integer(957));
        htmlEntities.put("xi", new Integer(958));
        htmlEntities.put("omicron", new Integer(959));
        htmlEntities.put("pi", new Integer(960));
        htmlEntities.put("rho", new Integer(961));
        htmlEntities.put("sigmaf", new Integer(962));
        htmlEntities.put("sigma", new Integer(963));
        htmlEntities.put("tau", new Integer(964));
        htmlEntities.put("upsilon", new Integer(965));
        htmlEntities.put("phi", new Integer(966));
        htmlEntities.put("chi", new Integer(967));
        htmlEntities.put("psi", new Integer(968));
        htmlEntities.put("omega", new Integer(969));
        htmlEntities.put("thetasym", new Integer(977));
        htmlEntities.put("upsih", new Integer(978));
        htmlEntities.put("piv", new Integer(982));
        htmlEntities.put("bull", new Integer(8226));
        htmlEntities.put("hellip", new Integer(8230));
        htmlEntities.put("prime", new Integer(8242));
        htmlEntities.put("Prime", new Integer(8243));
        htmlEntities.put("oline", new Integer(8254));
        htmlEntities.put("frasl", new Integer(8260));
        htmlEntities.put("weierp", new Integer(8472));
        htmlEntities.put("image", new Integer(8465));
        htmlEntities.put("real", new Integer(8476));
        htmlEntities.put("trade", new Integer(8482));
        htmlEntities.put("alefsym", new Integer(8501));
        htmlEntities.put("larr", new Integer(8592));
        htmlEntities.put("uarr", new Integer(8593));
        htmlEntities.put("rarr", new Integer(8594));
        htmlEntities.put("darr", new Integer(8595));
        htmlEntities.put("harr", new Integer(8596));
        htmlEntities.put("crarr", new Integer(8629));
        htmlEntities.put("lArr", new Integer(8656));
        htmlEntities.put("uArr", new Integer(8657));
        htmlEntities.put("rArr", new Integer(8658));
        htmlEntities.put("dArr", new Integer(8659));
        htmlEntities.put("hArr", new Integer(8660));
        htmlEntities.put("forall", new Integer(8704));
        htmlEntities.put("part", new Integer(8706));
        htmlEntities.put("exist", new Integer(8707));
        htmlEntities.put("empty", new Integer(8709));
        htmlEntities.put("nabla", new Integer(8711));
        htmlEntities.put("isin", new Integer(8712));
        htmlEntities.put("notin", new Integer(8713));
        htmlEntities.put("ni", new Integer(8715));
        htmlEntities.put("prod", new Integer(8719));
        htmlEntities.put("sum", new Integer(8721));
        htmlEntities.put("minus", new Integer(8722));
        htmlEntities.put("lowast", new Integer(8727));
        htmlEntities.put("radic", new Integer(8730));
        htmlEntities.put("prop", new Integer(8733));
        htmlEntities.put("infin", new Integer(8734));
        htmlEntities.put("ang", new Integer(8736));
        htmlEntities.put("and", new Integer(8743));
        htmlEntities.put("or", new Integer(8744));
        htmlEntities.put("cap", new Integer(8745));
        htmlEntities.put("cup", new Integer(8746));
        htmlEntities.put("int", new Integer(8747));
        htmlEntities.put("there4", new Integer(8756));
        htmlEntities.put("sim", new Integer(8764));
        htmlEntities.put("cong", new Integer(8773));
        htmlEntities.put("asymp", new Integer(8776));
        htmlEntities.put("ne", new Integer(8800));
        htmlEntities.put("equiv", new Integer(8801));
        htmlEntities.put("le", new Integer(8804));
        htmlEntities.put("ge", new Integer(8805));
        htmlEntities.put("sub", new Integer(8834));
        htmlEntities.put("sup", new Integer(8835));
        htmlEntities.put("nsub", new Integer(8836));
        htmlEntities.put("sube", new Integer(8838));
        htmlEntities.put("supe", new Integer(8839));
        htmlEntities.put("oplus", new Integer(8853));
        htmlEntities.put("otimes", new Integer(8855));
        htmlEntities.put("perp", new Integer(8869));
        htmlEntities.put("sdot", new Integer(8901));
        htmlEntities.put("lceil", new Integer(8968));
        htmlEntities.put("rceil", new Integer(8969));
        htmlEntities.put("lfloor", new Integer(8970));
        htmlEntities.put("rfloor", new Integer(8971));
        htmlEntities.put("lang", new Integer(9001));
        htmlEntities.put("rang", new Integer(9002));
        htmlEntities.put("loz", new Integer(9674));
        htmlEntities.put("spades", new Integer(9824));
        htmlEntities.put("clubs", new Integer(9827));
        htmlEntities.put("hearts", new Integer(9829));
        htmlEntities.put("diams", new Integer(9830));
        htmlEntities.put("quot", new Integer(34));
        htmlEntities.put("amp", new Integer(38));
        htmlEntities.put("lt", new Integer(60));
        htmlEntities.put("gt", new Integer(62));
        htmlEntities.put("OElig", new Integer(338));
        htmlEntities.put("oelig", new Integer(339));
        htmlEntities.put("Scaron", new Integer(352));
        htmlEntities.put("scaron", new Integer(353));
        htmlEntities.put("Yuml", new Integer(376));
        htmlEntities.put("circ", new Integer(710));
        htmlEntities.put("tilde", new Integer(732));
        htmlEntities.put("ensp", new Integer(8194));
        htmlEntities.put("emsp", new Integer(8195));
        htmlEntities.put("thinsp", new Integer(8201));
        htmlEntities.put("zwnj", new Integer(8204));
        htmlEntities.put("zwj", new Integer(8205));
        htmlEntities.put("lrm", new Integer(8206));
        htmlEntities.put("rlm", new Integer(8207));
        htmlEntities.put("ndash", new Integer(8211));
        htmlEntities.put("mdash", new Integer(8212));
        htmlEntities.put("lsquo", new Integer(8216));
        htmlEntities.put("rsquo", new Integer(8217));
        htmlEntities.put("sbquo", new Integer(8218));
        htmlEntities.put("ldquo", new Integer(8220));
        htmlEntities.put("rdquo", new Integer(8221));
        htmlEntities.put("bdquo", new Integer(8222));
        htmlEntities.put("dagger", new Integer(8224));
        htmlEntities.put("Dagger", new Integer(8225));
        htmlEntities.put("permil", new Integer(8240));
        htmlEntities.put("lsaquo", new Integer(8249));
        htmlEntities.put("rsaquo", new Integer(8250));
        htmlEntities.put("euro", new Integer(8364));
    }

    /**
     * 过滤字符串中敏感字符得到可安全用于执行SQL的字符串
     * @param sql
     * @return
     */
    public static String toSafeDataBaseString(String sql){
        if(!isNullOrEmpty(sql)){
            sql = sql.toLowerCase();
            for (int i = 0; i < SENSITIVE_WORDS.length; i++) {
                if (sql.indexOf(SENSITIVE_WORDS[i]) != -1) {
                    //正则替换词语，无视大小写
                    sql = sql.replaceAll("(?i)"+SENSITIVE_WORDS[i],EMPTY);
                }
            }
            for (int i = 0; i < SENSITIVE_CHARS.length; i++) {
                if (sql.indexOf(SENSITIVE_CHARS[i]) >= 0) {
                    sql = sql.replaceAll(SENSITIVE_CHARS[i],EMPTY);
                }
            }
            return isNullOrEmpty(sql) || isNullOrSpace(sql) ? null : sql;
        }
        return null;
    }

    /**
     * null 转换为 empty
     * @param str
     * @return
     */
    public static String null2Empty(String str){
        return str == null ? EMPTY : str;
    }

    /**
     * empty 转换为 null
     * @param str
     * @return
     */
    public static String empty2Null(String str){
        return EMPTY.equals(str) ? null : str;
    }
    /**
     *
     * escape() 函数可对字符串进行编码，这样就可以在所有的计算机上读取该字符串。
     * @param s
     * @return
     */
    public static String escape(String s) {
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int ch = s.charAt(i);
            if ('A' <= ch && ch <= 'Z') {
                sb.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') {
                sb.append((char) ch);
            } else if ('0' <= ch && ch <= '9') {
                sb.append((char) ch);
            } else if (ch == '-' || ch == '_' || ch == '.' || ch == '!'
                    || ch == '~' || ch == '*' || ch == '\'' || ch == '('
                    || ch == ')') {
                sb.append((char) ch);
            } else if (ch <= 0x007F) {
                sb.append(CHAR_PERCENT);
                sb.append(hex[ch]);
            } else {
                sb.append(CHAR_PERCENT);
                sb.append('u');
                sb.append(hex[(ch >>> 8)]);
                sb.append(hex[(0x00FF & ch)]);
            }
        }
        return sb.toString();
    }

    /**
     *
     * unescape() 函数可对通过 escape() 编码的字符串进行解码。
     * @param s
     * @return
     */
    public static String unescape(String s) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        int len = s.length();
        while (i < len) {
            int ch = s.charAt(i);
            if ('A' <= ch && ch <= 'Z') {
                sb.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') {
                sb.append((char) ch);
            } else if ('0' <= ch && ch <= '9') {
                sb.append((char) ch);
            } else if (ch == '-' || ch == '_' || ch == '.' || ch == '!'
                    || ch == '~' || ch == '*' || ch == '\'' || ch == '('
                    || ch == ')') {
                sb.append((char) ch);
            } else if (ch == '%') {
                int ci = 0;
                if ('u' != s.charAt(i + 1)) {
                    ci = (ci << 4) | val[s.charAt(i + 1)];
                    ci = (ci << 4) | val[s.charAt(i + 2)];
                    i += 2;
                } else {
                    ci = (ci << 4) | val[s.charAt(i + 2)];
                    ci = (ci << 4) | val[s.charAt(i + 3)];
                    ci = (ci << 4) | val[s.charAt(i + 4)];
                    ci = (ci << 4) | val[s.charAt(i + 5)];
                    i += 5;
                }
                sb.append((char) ci);
            } else {
                sb.append((char) ch);
            }
            i++;
        }
        return sb.toString();
    }

    /**
     *
     * 判断字符串是否是null或者是空格
     * @param s
     * @return
     */
    public static boolean isNullOrSpace(String s){
        return (s == null || EMPTY.equals(s.trim()));
    }

    /**
     * 判断字符串是否是null或者是空字符串（“”）
     * @param s
     * @return
     */
    public static boolean isNullOrEmpty(String s){
        return (s == null || EMPTY.equals(s));
    }

    /**
     * 字符集转换
     * @param text
     * @param oldCharSetName
     * @param newCharSetName
     * @return
     */
    public static String convertCharSet(String text,String oldCharSetName,String newCharSetName){
        if(isNullOrEmpty(text)){
            return text;
        }
        String result;
        try {
            result = new String(text.getBytes(oldCharSetName), newCharSetName);
        } catch (Exception e) {
            result = EMPTY;
        }
        return result;
    }

    /**
     * url编码
     * @param text
     * @return
     */
    public static String utf8UrlEncode(String text) {
        if(isNullOrEmpty(text)){
            return text;
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                result.append(c);
            } else {
                byte[] b = new byte[0];
                try {
                    b = Character.toString(c).getBytes(UTF8);
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

    /**
     * url解码
     * @param text
     * @return
     */
    public static String utf8UrlDecode(String text) {
        String result = EMPTY;
        int p;
        if (!isNullOrEmpty(text)) {
            text = text.toLowerCase();
            p = text.indexOf(PERCENT_E);
            if (p == -1) {
                return text;
            }
            while (p != -1) {
                result += text.substring(0, p);
                text = text.substring(p, text.length());
                if (EMPTY.equals(text) || text.length() < 9) {
                    return result;
                }
                result += codeToWord(text.substring(0, 9));
                text = text.substring(9, text.length());
                p = text.indexOf(PERCENT_E);
            }
        }
        return result + text;
    }

    /**
     * Unicode字符转文字
     * @param text
     * @return
     */
    private static String codeToWord(String text) {
        String result;
        if (utf8CodeCheck(text)) {
            byte[] code = new byte[3];
            code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
            code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
            code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
            try {
                result = new String(code, UTF8);
            } catch (UnsupportedEncodingException ex) {
                result = null;
            }
        } else {
            result = text;
        }
        return result;
    }

    /**
     * 检测是否是Unicode字符集
     * @param text
     * @return
     */
    private static boolean utf8CodeCheck(String text) {
        String sign = "";
        if (text.startsWith(PERCENT_E)) {
            for (int p = 0; p != -1;) {
                p = text.indexOf(STRING_PERCENT, p);
                if (p != -1) {
                    p++;
                }
                sign += p;
            }
        }
        return "147-1".equals(sign);
    }

    /**
     * 是否是utf-8的url
     * @param text
     * @return
     */
    public static boolean isUtf8Url(String text) {
        text = text.toLowerCase();
        int p = text.indexOf(STRING_PERCENT);
        if (p != -1 && text.length() - p > 9) {
            text = text.substring(p, p + 9);
        }
        return utf8CodeCheck(text);
    }

    /**
     * 在字符串s中将find替换为replace（性能较高，待测试）
     * @param s
     * @param find
     * @param replace
     * @return
     */
    public static String replaceStringFast(String s, String find, String replace) {
        if (isNullOrEmpty(find)) {
            return s;
        }
        if (isNullOrEmpty(replace)) {
            replace = EMPTY;
        }
        int findLength = find.length();
        int replaceLength = replace.length();
        int length;
        int stringLength = s.length();
        if (findLength == replaceLength) {
            length = stringLength;
        } else {
            int count;
            int start;
            int end;
            count = 0;
            start = 0;
            while ((end = s.indexOf(find, start)) != -1) {
                count++;
                start = end + findLength;
            }
            if (count == 0) {
                return s;
            }
            length = stringLength - (count * (findLength - replaceLength));
        }
        int start = 0;
        int end = s.indexOf(find, start);
        if (end == -1) {
            return s;
        }
        StringBuffer sb = new StringBuffer(length);
        while (end != -1) {
            sb.append(s.substring(start, end));
            sb.append(replace);
            start = end + findLength;
            end = s.indexOf(find, start);
        }
        end = stringLength;
        sb.append(s.substring(start, end));
        return (sb.toString());
    }

    /**
     * 在字符串strSrc中将strOld替换为strNew（性能略低，待测试）
     * @param strSrc
     * @param strOld
     * @param strNew
     * @return
     */
    public static String replaceStringSlow(String strSrc, String strOld, String strNew) {
        if (strSrc == null || strOld == null || strNew == null) {
            return EMPTY;
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

    /**
     * Html格式字符串转换
     * @param strSrc
     * @return
     */
    public static String htmlEncode(String strSrc) {
        if (isNullOrEmpty(strSrc)) {
            return EMPTY;
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

    /**
     * Html格式字符串转换
     * quotes == 1 将 " 转换为安全字符
     * quotes == 2 将 ' 转换为安全字符
     * @param strSrc
     * @param quotes
     * @return
     */
    public static String htmlEncode(String strSrc, int quotes) {
        if (isNullOrEmpty(strSrc)) {
            return EMPTY;
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

    /**
     * Html字符串解码为HTMl
     * @param strSrc
     * @return
     */
    public static String htmlDecode(String strSrc) {
        if (isNullOrEmpty(strSrc)) {
            return EMPTY;
        }
        return (strSrc.replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&quot;", "\"")
                .replaceAll("&#039;", "'")
                .replaceAll("&amp;", "&"));
    }

    /**
     * md5 加密
     * @param s
     * @return
     */
    public static String md5(String s) {
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] strs = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                strs[k++] = ConstUtils.HEXDIGITS[byte0 >>> 4 & 0xf];
                strs[k++] = ConstUtils.HEXDIGITS[byte0 & 0xf];
            }
            return new String(strs);
        } catch (Exception e) {
            return EMPTY;
        }
    }

    /**
     * 字符串转为Unicode编码
     * @param text
     * @return
     */
    public static String stringToUnicode(String text) {
        StringBuilder sb = new StringBuilder();
        int input;
        StringReader isr = null;
        try {
            isr = new StringReader(new String(text.getBytes(), UTF8));
            while ((input = isr.read()) != -1) {
                sb.append("&#x" + Integer.toHexString(input) + ";");
            }
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        } catch (IOException e) {
            return EMPTY;
        } finally {
            CloseUtils.closeIO(isr);
        }

        return sb.toString();

    }

    public static String gb2utf(String inStr) {
        if (isNullOrEmpty(inStr)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        char[] cs = inStr.toCharArray();
        for(char c : cs){
            String s = Integer.toHexString(c);
            if (s.length() > 2) {
                sb.append("&#x");
                sb.append(s);
                sb.append(";");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String gbEncoding(final String gbString) {
        char[] cs = gbString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cs){
            String hexB = Integer.toHexString(c);
            sb.append("\\u");
            if (hexB.length() <= 2) {
                sb.append("00");
            }
            sb.append(hexB);
        }
        return sb.toString();
    }

    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end ;
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
        return buffer.toString();
    }

    public static String prepad(String s, int length) {
        return prepad(s, length, ' ');
    }

    public static String prepad(String s, int length, char c) {
        int needed = length - s.length();
        if (needed <= 0) {
            return s;
        }
        char padding[] = new char[needed];
        java.util.Arrays.fill(padding, c);
        StringBuffer sb = new StringBuffer(length);
        sb.append(padding);
        sb.append(s);
        return sb.toString();
    }

    public static String postpad(String s, int length) {
        return postpad(s, length, ' ');
    }

    public static String postpad(String s, int length, char c) {
        int needed = length - s.length();
        if (needed <= 0) {
            return s;
        }
        char padding[] = new char[needed];
        java.util.Arrays.fill(padding, c);
        StringBuffer sb = new StringBuffer(length);
        sb.append(s);
        sb.append(padding);
        return sb.toString();
    }

    public static String midpad(String s, int length) {
        return midpad(s, length, ' ');
    }

    public static String midpad(String s, int length, char c) {
        int needed = length - s.length();
        if (needed <= 0) {
            return s;
        }
        int beginning = needed / 2;
        int end = beginning + needed % 2;
        char prepadding[] = new char[beginning];
        java.util.Arrays.fill(prepadding, c);
        char postpadding[] = new char[end];
        java.util.Arrays.fill(postpadding, c);
        StringBuffer sb = new StringBuffer(length);
        sb.append(prepadding);
        sb.append(s);
        sb.append(postpadding);
        return sb.toString();
    }

    public static String[] split(String s, String delimiter) {
        if (isNullOrEmpty(delimiter)) {
            return new String[]{s};
        }
        int stringLength = s.length();
        int delimiterLength = delimiter.length();
        int count = 0;
        int start = 0;
        int end;
        while ((end = s.indexOf(delimiter, start)) != -1) {
            count++;
            start = end + delimiterLength;
        }
        count++;
        String[] result = new String[count];
        count = 0;
        start = 0;
        while ((end = s.indexOf(delimiter, start)) != -1) {
            result[count] = (s.substring(start, end));
            count++;
            start = end + delimiterLength;
        }
        end = stringLength;
        result[count] = s.substring(start, end);
        return (result);
    }

    public static String[] splitIncludeDelimiters(String s, String delimiter) {
        if (isNullOrEmpty(delimiter)) {
            return new String[]{s};
        }
        int stringLength = s.length();
        int delimiterLength = delimiter.length();
        int count = 0;
        int start = 0;
        int end;
        while ((end = s.indexOf(delimiter, start)) != -1) {
            count += 2;
            start = end + delimiterLength;
        }
        count++;
        String[] result = new String[count];
        count = 0;
        start = 0;
        while ((end = s.indexOf(delimiter, start)) != -1) {
            result[count] = (s.substring(start, end));
            count++;
            result[count] = delimiter;
            count++;
            start = end + delimiterLength;
        }
        end = stringLength;
        result[count] = s.substring(start, end);
        return (result);
    }

    public static String join(String[] array) {
        return join(array, "");
    }

    public static String join(String[] array, String delimiter) {
        if (CollectionUtils.isNullOrEmpty(array)) {
            return EMPTY;
        }
        if (array.length == 1) {
            if (isNullOrEmpty(array[0])) {
                return EMPTY;
            }
            return array[0];
        }
        int length = 0;
        int delimiterLength = delimiter.length();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                length += array[i].length();
            }
            if (i < array.length - 1) {
                length += delimiterLength;
            }
        }
        StringBuffer result = new StringBuffer(length);
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                result.append(array[i]);
            }
            if (i < array.length - 1) {
                result.append(delimiter);
            }
        }
        return result.toString();
    }

    public static String escapeHTML(String s) {
        int length = s.length();
        int newLength = length;
        boolean someCharacterEscaped = false;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int cint = 0xffff & c;
            if (cint < 32) {
                switch (c) {
                    case '\r':
                    case '\n':
                    case '\t':
                    case '\f': break;
                    default:
                        newLength -= 1;
                        someCharacterEscaped = true;
                        break;
                }
            } else {
                switch (c) {
                    case '\"': {
                        newLength += 5;
                        someCharacterEscaped = true;
                    }
                    break;
                    case '&':
                    case '\'': {
                        newLength += 4;
                        someCharacterEscaped = true;
                    }
                    break;
                    case '<':
                    case '>': {
                        newLength += 3;
                        someCharacterEscaped = true;
                    }
                    break;
                    default:
                        break;
                }
            }
        }
        if (!someCharacterEscaped) {
            return s;
        }
        StringBuffer sb = new StringBuffer(newLength);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int cint = 0xffff & c;
            if (cint < 32) {
                switch (c) {
                    case '\r':
                    case '\n':
                    case '\t':
                    case '\f': {
                        sb.append(c);
                    }
                    break;
                    default:
                        break;
                }
            } else {
                switch (c) {
                    case '\"':
                        sb.append("&quot;");
                        break;
                    case '\'':
                        sb.append("&#39;");
                        break;
                    case '&':
                        sb.append("&amp;");
                        break;
                    case '<':
                        sb.append("&lt;");
                        break;
                    case '>':
                        sb.append("&gt;");
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }
        }
        return sb.toString();
    }

    public static String escapeSQL(String s) {
        int length = s.length();
        int newLength = length;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\':
                case '\"':
                case '\'':
                case '\0': newLength += 1;break;
                default:break;
            }
        }
        if (length == newLength) {
            return s;
        }
        StringBuffer sb = new StringBuffer(newLength);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\': sb.append("\\\\");break;
                case '\"': sb.append("\\\"");break;
                case '\'': sb.append("\\\'");break;
                case '\0': sb.append("\\0");break;
                default: sb.append(c);break;
            }
        }
        return sb.toString();
    }

    public static String escapeJavaLiteral(String s) {
        int length = s.length();
        int newLength = length;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"':
                case '\'':
                case '\n':
                case '\r':
                case '\t':
                case '\\':
                    newLength += 1;
                    break;
                default:
                    break;
            }
        }
        if (length == newLength) {
            // nothing to escape in the string
            return s;
        }
        StringBuffer sb = new StringBuffer(newLength);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"': {
                    sb.append("\\\"");
                }
                break;
                case '\'': {
                    sb.append("\\\'");
                }
                break;
                case '\n': {
                    sb.append("\\n");
                }
                break;
                case '\r': {
                    sb.append("\\r");
                }
                break;
                case '\t': {
                    sb.append("\\t");
                }
                break;
                case '\\': {
                    sb.append("\\\\");
                }
                break;
                default: {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static String trim(String s, String c) {
        int length = s.length();
        if (c == null) {
            return s;
        }
        int cLength = c.length();
        if (c.length() == 0) {
            return s;
        }
        int start = 0;
        int end = length;
        boolean found;
        int i;
        found = false;
        for (i = 0; !found && i < length; i++) {
            char ch = s.charAt(i);
            found = true;
            for (int j = 0; found && j < cLength; j++) {
                if (c.charAt(j) == ch) {
                    found = false;
                }
            }
        }
        if (!found) {
            return "";
        }
        start = i - 1;
        found = false;
        for (i = length - 1; !found && i >= 0; i--) {
            char ch = s.charAt(i);
            found = true;
            for (int j = 0; found && j < cLength; j++) {
                if (c.charAt(j) == ch) {
                    found = false;
                }
            }
        }
        end = i + 2;
        return s.substring(start, end);
    }

    public static String unescapeHTML(String s) {
        StringBuffer result = new StringBuffer(s.length());
        int ampInd = s.indexOf("&");
        int lastEnd = 0;
        while (ampInd >= 0) {
            int nextAmp = s.indexOf("&", ampInd + 1);
            int nextSemi = s.indexOf(";", ampInd + 1);
            if (nextSemi != -1 && (nextAmp == -1 || nextSemi < nextAmp)) {
                int value = -1;
                String escape = s.substring(ampInd + 1, nextSemi);
                try {
                    if (escape.startsWith("#")) {
                        value = Integer.parseInt(escape.substring(1), 10);
                    } else {
                        if (htmlEntities.containsKey(escape)) {
                            value = ((Integer) (htmlEntities.get(escape))).intValue();
                        }
                    }
                } catch (NumberFormatException x) {
                }
                result.append(s.substring(lastEnd, ampInd));
                lastEnd = nextSemi + 1;
                if (value >= 0 && value <= 0xffff) {
                    result.append((char) value);
                } else {
                    result.append("&").append(escape).append(";");
                }
            }
            ampInd = nextAmp;
        }
        result.append(s.substring(lastEnd));
        return result.toString();
    }

    public static String escapeRegularExpressionLiteral(String s) {

        int length = s.length();
        int newLength = length;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                newLength += 1;
            }
        }
        if (length == newLength) {
            return s;
        }
        StringBuffer sb = new StringBuffer(newLength);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static void buildFindAnyPattern(String[] terms, StringBuffer sb) {
        if (terms.length == 0) {
            throw new IllegalArgumentException("There must be at least one term to find.");
        }
        sb.append("(?:");
        for (int i = 0; i < terms.length; i++) {
            if (i > 0) {
                sb.append("|");
            }
            sb.append("(?:");
            sb.append(escapeRegularExpressionLiteral(terms[i]));
            sb.append(")");
        }
        sb.append(")");
    }

    public static Pattern getContainsAnyPattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?s).*");
        buildFindAnyPattern(terms, sb);
        sb.append(".*");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getEqualsAnyPattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?s)\\A");
        buildFindAnyPattern(terms, sb);
        sb.append("\\z");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getStartsWithAnyPattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?s)\\A");
        buildFindAnyPattern(terms, sb);
        sb.append(".*");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getEndsWithAnyPattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?s).*");
        buildFindAnyPattern(terms, sb);
        sb.append("\\z");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getContainsAnyIgnoreCasePattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?i)(?u)(?s).*");
        buildFindAnyPattern(terms, sb);
        sb.append(".*");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getEqualsAnyIgnoreCasePattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?i)(?u)(?s)\\A");
        buildFindAnyPattern(terms, sb);
        sb.append("\\z");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getStartsWithAnyIgnoreCasePattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?i)(?u)(?s)\\A");
        buildFindAnyPattern(terms, sb);
        sb.append(".*");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getEndsWithAnyIgnoreCasePattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?i)(?u)(?s).*");
        buildFindAnyPattern(terms, sb);
        sb.append("\\z");
        return Pattern.compile(sb.toString());
    }

    public static boolean containsAny(String s, String[] terms) {
        return getContainsAnyPattern(terms).matcher(s).matches();
    }

    public static boolean equalsAny(String s, String[] terms) {
        return getEqualsAnyPattern(terms).matcher(s).matches();
    }

    public static boolean startsWithAny(String s, String[] terms) {
        return getStartsWithAnyPattern(terms).matcher(s).matches();
    }

    public static boolean endsWithAny(String s, String[] terms) {
        return getEndsWithAnyPattern(terms).matcher(s).matches();
    }

    public static boolean containsAnyIgnoreCase(String s, String[] terms) {
        return getContainsAnyIgnoreCasePattern(terms).matcher(s).matches();
    }

    public static boolean equalsAnyIgnoreCase(String s, String[] terms) {
        return getEqualsAnyIgnoreCasePattern(terms).matcher(s).matches();
    }

    public static boolean startsWithAnyIgnoreCase(String s, String[] terms) {
        return getStartsWithAnyIgnoreCasePattern(terms).matcher(s).matches();
    }

    public static boolean endsWithAnyIgnoreCase(String s, String[] terms) {
        return getEndsWithAnyIgnoreCasePattern(terms).matcher(s).matches();
    }
}
