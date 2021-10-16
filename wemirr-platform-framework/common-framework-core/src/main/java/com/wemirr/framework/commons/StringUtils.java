package com.wemirr.framework.commons;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Levin
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    /**
     * 金额为分的格式
     */
    private static final String REGEX_CURRENCY_FEN = "-?[0-9]+";

    private static final String REGEX_MOBILE_PHONE = "^[1][0-9]{10}$";


    /**
     * 检查字符串("") or null
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isUndefined(String str) {
        return isEmpty(str) || "undefined".equals(str.trim());
    }

    /**
     * 判断是否是手机号码
     */
    public static boolean isMobile(String mobile) {
        if (isEmpty(mobile)) {
            return false;
        }
        return mobile.matches(REGEX_MOBILE_PHONE);
    }

    /**
     * 检查字符串是否是字母， null/空字符串 皆为false
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if only contains digits, and is non-null
     */
    public static boolean isLetter(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String autoFillDid(String did) {
        return autoFillDid(Long.parseLong(did));
    }

    public static String autoFillDid(long did) {
        return autoFillDid(did, DID_LENGTH);
    }

    public static String autoFillDid(long did, int length) {
        return String.format("%0" + length + "d", did);
    }


    /**
     * 采用";"形式拼接
     */
    public static String toLink(Object... args) {
        return join(SIGN_FEN_HAO, args);
    }

    /**
     * 采用"&"形式拼接
     */
    public static String toLinkByAnd(Object... args) {
        return join(SIGN_AND, args);
    }


    /**
     * 采用","形式拼接
     */
    public static String toLinkByDouHao(Object... args) {
        return join(SIGN_DOU_HAO, args);
    }

    /**
     * 把格式 1_2_3 换成 1,2,3
     *
     * @param key 1_2_3
     * @return 1, 2, 3
     */
    public static String formatIds(String key) {
        // 把格式 1_2_3 换成 1,2,3
        if (key != null && key.contains(SIGN_LINE)) {
            key = key.replaceAll(SIGN_LINE, SIGN_DOU_HAO);
        }
        return key;
    }

    /**
     * 计算百分数
     *
     * @param count count
     * @param total total
     * @return 结果
     */
    public static String getPercent(Double count, Double total) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        // //控制保留小数点后几位，2：表示保留2位小数点
        nf.setMinimumFractionDigits(2);
        return nf.format(count / total);
    }

    /**
     * 计算百分数，不保留两位小数
     *
     * @param count count
     * @param total total
     * @return 结果
     */
    public static String percent(Double count, Double total) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        //控制保留小数点后几位，0:不保留两位小数 2：表示保留2位小数点
        nf.setMinimumFractionDigits(0);
        return nf.format(count / total);
    }


    /**
     * 把 emoji 表情截掉
     *
     * @param content content
     * @return 处理后的
     */
    public static String transEmojiName(String content) {
        Pattern emo = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]" +
                "|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emoMatcher = emo.matcher(content);
        if (emoMatcher.find()) {
            //把表情截掉
            return emoMatcher.replaceAll("*");
        }
        return content;
    }

    /**
     * Json字符串中获取ID拼接字符串
     */
    public static String getIdsByJson(String json) {
        //使用非贪婪模式
        String regex = getMatching(json, "\"id\":(\\d+)");
        return StringUtils.isEmpty(regex) ? getMatching(json, "\"id\":\"(\\d+)\"") : regex;
    }

    private static String getMatching(String source, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(source);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group(1)).append(SIGN_DOU_HAO);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 符号--与符号（&）
     */
    public static final String SIGN_AND = "&";
    /**
     * 符号--下划线（_）
     */
    public static final String SIGN_LINE = "_";
    /**
     * 符号--百分号（%）
     */
    public static final String SIGN_PERCENT = "%";

    /**
     * 符号--逗号（,）
     */
    public static final String SIGN_DOU_HAO = ",";
    /**
     * 符号--冒号（:）
     */
    public static final String SIGN_MAO_HAO = ":";
    /**
     * 符号--分号（;）
     */
    public static final String SIGN_FEN_HAO = ";";
    /**
     * 符号--井号（#）
     */
    public static final String SIGN_NUMBER = "#";
    /**
     * 符号--加号（+）
     */
    public static final String SIGN_PLUS = "+";
    /**
     * 符号--减号（-）
     */
    public static final String SIGN_MINUS = "-";

    /**
     * 数字--0
     */
    public static final String DIGIT_ZERO = "0";
    /**
     * 数字--1
     */
    public static final String DIGIT_ONE = "1";
    /**
     * 数字--6
     */
    public static final String DIGIT_SIX = "6";
    /**
     * 数字--7
     */
    public static final String DIGIT_SEVEN = "7";

    /**
     * 字符串--未知
     */
    public static final String STRING_UNKNOWN = "未知";

    /**
     * 时间戳-- 0.5 hour
     */
    public static final long TIMESTAMP_HOUR_HALF = 30 * 60;

    /**
     * 时间戳-- 3 hour
     */
    public static final long TIMESTAMP_HOUR_3 = 3 * 60 * 60;

    /**
     * 时间戳-- 8 hour
     */
    public static final long TIMESTAMP_HOUR_8 = 8 * 60 * 60;

    /**
     * 时间戳-- 1 天 （24 hour）
     */
    public static final long TIMESTAMP_DAYS_1 = 24 * 60 * 60;

    /**
     * 时间戳-- 7 天
     */
    public static final long TIMESTAMP_DAYS_7 = 7 * 24 * 60 * 60;

    /**
     * 时间戳-- 1 月 (31 dat)
     */
    public static final long TIMESTAMP_MOON_1 = 31 * 24 * 60 * 60;

    /**
     * 时间戳-- 1 年 （365 day）
     */
    public static final long TIMESTAMP_YEAR_1 = 356 * 24 * 60 * 60;

    /**
     * 时间戳-- 4:50
     */
    public static final long TIMESTAMP_DELAY = 4 * 60 * 60 + 50 * 60;

    /**
     * JSON 数组字符串
     */
    public static final String JSON_ARRAY = "[]";

    /**
     * 开发模式（dev）
     */
    public static final String MODEL_DEV = "dev";

    /**
     * 正式模式（prod）
     */
    public static final String MODEL_PROD = "prod";

    /**
     * DID长度 -- 10位
     */
    public static final int DID_LENGTH = 10;

    /**
     * 标志-最小数据
     **/
    public static final int DATA_FLAG_MIN = 1;
    /**
     * 标志-中等数据
     **/
    public static final int DATA_FLAG_MID = 2;
    /**
     * 标志-最大数据
     **/
    public static final int DATA_FLAG_MAX = 4;
    /**
     * 标志-扩展数据
     **/
    public static final int DATA_FLAG_EXT = 8;


}
