package com.coder.util;

import java.util.Collection;

public final class CollectionUtils {

    private CollectionUtils(){};

    public static boolean isNullOrEmpty(Collection collection){
        return collection == null || collection.size() == 0;
    }

    /**
     * 严格的空集合
     * 如果说collection不是null且size大于0，但是元素为null 则也为空集合
     * @param collection
     * @return
     */
    public static boolean isNullOrEmptyStrict(Collection collection){
        if(collection != null && collection.size() > 0){
            for(Object obj :collection ){
                if(obj != null){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(Object[] collection){
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmptyStrict(Object[] collection){
        if(collection != null && collection.length > 0){
            for(Object obj :collection ){
                if(obj != null){
                    return false;
                }
            }
        }
        return true;
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
     * @param objs
     * @param obj
     * @return
     */
    public static String join(Collection<Object> objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.size() - 1;
        int i = 0;
        for(Object o : objs){
            if(o != null){
                sb.append(String.valueOf(o));
                if(i != length){
                    sb.append(String.valueOf(obj));
                }
            }
            i++;
        }
        return sb.toString();
    }

    public static String join(Object[] objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.length - 1;
        int i = 0;
        for(Object o : objs){
            if(o != null){
                sb.append(String.valueOf(o));
                if(i != length){
                    sb.append(String.valueOf(obj));
                }
            }
            i++;
        }
        return sb.toString();
    }

    public static String join(int[] objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.length - 1;
        int i = 0;
        for(int o : objs){
            sb.append(o);
            if(i != length){
                sb.append(String.valueOf(obj));
            }
            i++;
        }
        return sb.toString();
    }

    public static String join(long[] objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.length - 1;
        int i = 0;
        for(long o : objs){
            sb.append(o);
            if(i != length){
                sb.append(String.valueOf(obj));
            }
            i++;
        }
        return sb.toString();
    }

    public static String join(double[] objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.length - 1;
        int i = 0;
        for(double o : objs){
            sb.append(o);
            if(i != length){
                sb.append(String.valueOf(obj));
            }
            i++;
        }
        return sb.toString();
    }

    public static String join(float[] objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.length - 1;
        int i = 0;
        for(float o : objs){
            sb.append(o);
            if(i != length){
                sb.append(String.valueOf(obj));
            }
            i++;
        }
        return sb.toString();
    }

    public static String join(byte[] objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.length - 1;
        int i = 0;
        for(float o : objs){
            sb.append(o);
            if(i != length){
                sb.append(String.valueOf(obj));
            }
            i++;
        }
        return sb.toString();
    }

    public static String join(short[] objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.length - 1;
        int i = 0;
        for(short o : objs){
            sb.append(o);
            if(i != length){
                sb.append(String.valueOf(obj));
            }
            i++;
        }
        return sb.toString();
    }

    public static String join(char[] objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.length - 1;
        int i = 0;
        for(char o : objs){
            sb.append(o);
            if(i != length){
                sb.append(String.valueOf(obj));
            }
            i++;
        }
        return sb.toString();
    }

    public static String join(boolean[] objs, Object obj){
        if(isNullOrEmpty(objs)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        int length = objs.length - 1;
        int i = 0;
        for(boolean o : objs){
            sb.append(o);
            if(i != length){
                sb.append(String.valueOf(obj));
            }
            i++;
        }
        return sb.toString();
    }

}
