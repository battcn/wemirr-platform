package com.wemirr.framework.security.utils;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 兼容 spring-security PasswordEncoder 的增强实现
 *
 * @author Levin
 */
public final class PasswordEncoderHelper {

    private static final String DEFAULT_ENCODING_ID = "bcrypt";
    private static final String DEFAULT_ID_PREFIX = "{";

    private static final String DEFAULT_ID_SUFFIX = "}";

    public static String encode(String encodingId, String rawPassword) {
        String password = switch (encodingId) {
            case "bcrypt" -> BCrypt.hashpw(rawPassword);
            case "md5" -> SaSecureUtil.md5(rawPassword);
            case "sha256" -> SaSecureUtil.sha256(rawPassword);
            default -> rawPassword;
        };
        return DEFAULT_ID_PREFIX + encodingId + DEFAULT_ID_SUFFIX + password;
    }

    public static String encode(String rawPassword) {
        return encode(DEFAULT_ENCODING_ID, rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        String encodingId = StrUtil.subBetween(encodedPassword, DEFAULT_ID_PREFIX, DEFAULT_ID_SUFFIX);
        String actualPassword = StrUtil.removePrefix(encodedPassword, DEFAULT_ID_PREFIX + encodingId + DEFAULT_ID_SUFFIX);
        return switch (encodingId) {
            case "bcrypt" -> BCrypt.checkpw(rawPassword, actualPassword);
            case "md5" -> SaSecureUtil.md5(rawPassword).equals(actualPassword);
            case "sha256" -> SaSecureUtil.sha256(rawPassword).equals(actualPassword);
            default -> rawPassword.equals(actualPassword);
        };
    }
}
