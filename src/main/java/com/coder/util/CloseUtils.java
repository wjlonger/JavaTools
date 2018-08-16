package com.coder.util;

import java.io.*;

/**
 * @author WJL
 */
public final class CloseUtils {

    private CloseUtils(){}

    public static void closeIO(InputStream is){
        if(is != null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                is = null;
            }
        }
    }

    public static void closeIO(OutputStream os){
        if(os != null){
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                os = null;
            }
        }
    }

    public static void closeIO(Writer writer){
        if(writer != null){
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                writer = null;
            }
        }
    }

    public static void closeIO(Reader reader){
        if(reader != null){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                reader = null;
            }
        }
    }
}
