package com.coder.util;

import java.util.*;
import java.io.*;

public final class PropsUtil {

    public PropsUtil() {
    }

    public String getPropsFilePath() {
        String filePath = this.getClass().getResource("/").getPath();
        filePath = filePath.substring(0, filePath.indexOf("classes") - 1) + "/destinations.properties";
        return filePath;
    }

    public InputStream getPropsIS() {
        InputStream ins = this.getClass().getResourceAsStream("/destinations.properties");
        return ins;
    }

    public String readSingleProps(String attr) {
        String retValue = "";
        Properties props = new Properties();
        try {
//            if (!FileUtil.isFileExist(getPropsFilePath())) {
//                return "";
//            }
//            FileInputStream fi = new FileInputStream(getPropsFilePath());
            InputStream fi = getPropsIS();
            props.load(fi);
            fi.close();

            retValue = props.getProperty(attr);
        } catch (Exception e) {
            return "";
        }
        return retValue;
    }

    public HashMap readAllProps() {
        HashMap h = new HashMap();
        Properties props = new Properties();

        try {
//            if (!FileUtil.isFileExist(getPropsFilePath()))
//                return new HashMap();
//            FileInputStream fi = new FileInputStream(getPropsFilePath());
            InputStream fi = getPropsIS();
            props.load(fi);
            fi.close();
            Enumeration er = props.propertyNames();
            while (er.hasMoreElements()) {
                String paramName = (String) er.nextElement();
                h.put(paramName, props.getProperty(paramName));
            }
        } catch (Exception e) {
            return new HashMap(0);
        }
        return h;
    }
}
