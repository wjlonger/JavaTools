package com.coder.util;

import java.util.*;
import java.util.regex.*;

public final class RegExUtils {

    public static final boolean ereg(String pattern, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.find();
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    public static final String eregReplace(String pattern, String newStr, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.replaceAll(newStr);
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    public static final Vector splitTags2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector vector = new Vector();
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                vector.add(eregReplace("(\\[\\#)|(\\#\\])", "", m.group()));
            }
            return vector;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    public static final String[] splitTags(String pattern, String str) {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            String[] array = new String[m.groupCount()];
            int i = 0;
            while (m.find()) {
                array[i] = eregReplace("(\\[\\#)|(\\#\\])", "", m.group());
                i++;
            }
            return array;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    public static final Vector regMatchAll2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector vector = new Vector();
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                vector.add(m.group());
            }
            return vector;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    public static final String[] regMatchAll2Array(String pattern, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            String[] array = new String[m.groupCount()];
            int i = 0;
            while (m.find()) {
                array[i] = m.group();
                i++;
            }
            return array;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    public static final String escapeDollarBackslash(String original) {
        StringBuffer buffer = new StringBuffer(original.length());
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if (c == '\\' || c == '$') {
                buffer.append("\\").append(c);
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    public static final String fetchStr(String pattern, String str) {
        String returnValue = null;
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                returnValue = m.group();
            }
            return returnValue;
        } catch (PatternSyntaxException e) {
            return returnValue;
        }
    }
}
