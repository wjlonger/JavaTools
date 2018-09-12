package com.coder.util;

import org.safehaus.uuid.UUIDGenerator;
import java.util.UUID;

public final class UUIDUtils {

    public static String getUUIDByJUG() {
        UUIDGenerator generator = UUIDGenerator.getInstance();
        return generator.generateTimeBasedUUID().toString();
    }

    public static String getUUID32ByJUG() {
        UUIDGenerator generator = UUIDGenerator.getInstance();
        String uid = generator.generateTimeBasedUUID().toString();
        return StringUtils.replaceStringFast(uid, "-", "");
    }

    public static String getUUIDByJDK(){
        return UUID.randomUUID().toString();
    }

    public static String getUUID32ByJDK(){
        String uuid = UUID.randomUUID().toString();
        return StringUtils.replaceStringFast(uuid, "-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUIDByJUG());
        System.out.println(getUUID32ByJUG());

        System.out.println(getUUIDByJDK());
        System.out.println(getUUID32ByJDK());
    }
}
