package com.coder.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class CloseUtils {

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

}
