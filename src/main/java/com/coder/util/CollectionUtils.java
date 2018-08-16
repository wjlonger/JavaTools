package com.coder.util;

import java.util.Collection;

public final class CollectionUtils {

    private CollectionUtils(){};

    public static boolean isNullOrEmpty(Collection collection){
        return collection == null || collection.size() == 0;
    }

    public static boolean isNullOrEmpty(Object[] collection){
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(int[] collection){
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(char[] collection){
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(byte[] collection){
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(double[] collection){
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(long[] collection){
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(float[] collection){
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(short[] collection){
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(boolean[] collection){
        return collection == null || collection.length == 0;
    }

    /**
     * 将字符串集合cs中的元素由连接符s拼接起来得到新的字符串
     * 集合中的元素类型是Object类型，因此需要重写toString方法
     * @param cs
     * @param s
     * @return
     */
    public static String join(Collection<Object> cs, String s){
        StringBuilder sb = new StringBuilder();
        int length = cs.size() - 1;
        int i = 0;
        for(Object st : cs){
            sb.append(st);
            if(i != length){
                sb.append(s);
                i++;
            }
        }
        return sb.toString();
    }
}
