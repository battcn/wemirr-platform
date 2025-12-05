package com.wemirr.framework.commons.util;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * 摘要算法工具类 (JDK 21+)
 * <p>
 * 替代 Hutool 的 SecureUtil/MD5
 *
 * @author Gemini
 */
public class DigestUtil {

    private DigestUtil() {
    }

    /**
     * 计算 MD5 摘要并转为 16 进制字符串
     *
     * @param data 被摘要的数据
     * @return 32位 Hex 字符串
     */
    public static String md5Hex(String data) {
        if (data == null) {
            return null;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(data.getBytes(StandardCharsets.UTF_8));
            // JDK 17+ 新特性：HexFormat，性能极高
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 算法不存在", e);
        }
    }
}