package com.coder.util;

import java.util.Collection;

public final class CollectionUtils {

    private CollectionUtils(){};

    public static boolean isNullOrEmpty(Collection collection){
        return collection == null || collection.size() == 0;
    }

}
