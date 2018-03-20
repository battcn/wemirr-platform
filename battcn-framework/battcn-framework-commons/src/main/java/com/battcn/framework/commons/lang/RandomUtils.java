package com.battcn.framework.commons.lang;

import java.util.Random;
import java.util.UUID;

/**
 * @author Levin
 * @since 2018/3/20 0020
 */
public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    private static short counter = (short) 0;

    /**
     * 随机生成整数ID
     *
     * @return ID
     */
    public static int getIntId() {
        Random rnd = new Random();
        return rnd.nextInt(89999999) + 10000000;
    }

    /**
     * 生成UUID
     *
     * @return 生成纯大写的UUID并且去掉 '-'
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static String generate() {
        String sep = "";
        return (format(getHiTime()) + sep + format(getLoTime()) + sep + format(getCount())).toUpperCase();
    }

    /**
     * Unique in a millisecond for this JVM instance (unless there
     * are > Short.MAX_VALUE instances created in a millisecond)
     */
    private static short getCount() {
        synchronized (RandomUtils.class) {
            if (counter < 0) {
                counter = 0;
            }
            return counter++;
        }
    }

    /**
     * 唯一的毫秒时间戳
     */
    private static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    private static int getLoTime() {
        return (int) System.currentTimeMillis();
    }


    private static String format(int intVal) {
        String formatted = Integer.toHexString(intVal);
        StringBuilder buf = new StringBuilder("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    private static String format(short shortVal) {
        String formatted = Integer.toHexString(shortVal);
        StringBuilder buf = new StringBuilder("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }


}
