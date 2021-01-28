package com.wemirr.framework.commons;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * @author Levin
 */
public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    private static final String STR = "1234567890AaSsDdFfGgHhJjKkLlQqWwEeRrTtYyUuIiOoPpZzXxCcVvBbNnMm";

    /**
     * 随机生成指定位数的字符串数字
     *
     * @param size 位数
     * @return 生成结果
     */
    public static String nextInt(int size) {
        return StringUtils.leftPad(org.apache.commons.lang3.RandomUtils.nextInt(0, 999999) + "", size, '0');
    }

    /**
     * length为产生的位数
     *
     * @param length 长度
     * @return 随机结果
     */
    public static String nextString(int length) {
        return nextString(length, false);
    }


    public static String nextString(int length, boolean onlyNumber) {
        //由Random生成随机数
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        //长度为几就循环几次
        // //产生0-61的数字
        for (int i = 0; i < length; ++i) {
            // //将产生的数字通过length次承载到sb中
            int number = random.nextInt(onlyNumber ? 10 : 62);
            sb.append(STR.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

}
