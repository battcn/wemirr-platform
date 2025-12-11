package com.wemirr.framework.commons;

/**
 * 原生 User-Agent 解析器
 * @author Levin
 */
public class NativeUserAgent {

    public record Info(String engine, String os, String platform, String browser) {
        // 空对象的静态工厂方法
        public static Info unknown() {
            return new Info("Unknown", "Unknown", "Unknown", "Unknown");
        }
    }

    public static Info parse(String ua) {
        if (ua == null || ua.isBlank()) {
            return Info.unknown();
        }
        // 解析 OS & Platform
        var osMeta = switch (ua) {
            case String s when s.contains("Windows") -> new String[]{"Windows", "Desktop"};
            case String s when s.contains("Mac OS X") && (s.contains("iPhone") || s.contains("iPad")) -> new String[]{"iOS", "Mobile"};
            case String s when s.contains("Mac OS X") -> new String[]{"Mac OS X", "Desktop"};
            case String s when s.contains("Android") -> new String[]{"Android", "Mobile"};
            case String s when s.contains("Linux") -> new String[]{"Linux", "Desktop"};
            default -> new String[]{"Unknown", "Unknown"};
        };

        var browserMeta = switch (ua) {
            case String s when s.contains("Edg") -> new String[]{"Edge", "Blink"};
            case String s when s.contains("Chrome") -> new String[]{"Chrome", "Webkit"};
            case String s when s.contains("Firefox") -> new String[]{"Firefox", "Gecko"};
            case String s when s.contains("Safari") && !s.contains("Chrome") -> new String[]{"Safari", "Webkit"};
            case String s when s.contains("MSIE") || s.contains("Trident") -> new String[]{"IE", "Trident"};
            default -> new String[]{"Unknown", "Unknown"};
        };

        return new Info(browserMeta[1], osMeta[0], osMeta[1], browserMeta[0]);
    }
}