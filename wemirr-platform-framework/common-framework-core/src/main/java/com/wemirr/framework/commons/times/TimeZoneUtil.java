package com.wemirr.framework.commons.times;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 美国	Locale("en", "US")	ZoneId.of("America/New_York") （纽约时区）
 * 中国	Locale("zh", "CN")	ZoneId.of("Asia/Shanghai") （上海时区）
 * 印度	Locale("en", "IN")	ZoneId.of("Asia/Kolkata") （加尔各答时区）
 * 英国	Locale("en", "GB")	ZoneId.of("Europe/London") （伦敦时区）
 * 日本	Locale("ja", "JP")	ZoneId.of("Asia/Tokyo") （东京时区）
 * 澳大利亚	Locale("en", "AU")	ZoneId.of("Australia/Sydney") （悉尼时区）
 * 加拿大	Locale("en", "CA")	ZoneId.of("America/Toronto") （多伦多时区）
 * 德国	Locale("de", "DE")	ZoneId.of("Europe/Berlin") （柏林时区）
 * 法国	Locale("fr", "FR")	ZoneId.of("Europe/Paris") （巴黎时区）
 * 韩国	Locale("ko", "KR")	ZoneId.of("Asia/Seoul") （首尔时区）
 * 俄罗斯	Locale("ru", "RU")	ZoneId.of("Europe/Moscow") （莫斯科时区）
 * 巴西	Locale("pt", "BR")	ZoneId.of("America/Sao_Paulo") （圣保罗时区）
 * 南非	Locale("en", "ZA")	ZoneId.of("Africa/Johannesburg") （约翰内斯堡时区
 *
 * @author Levin
 */
public class TimeZoneUtil {
    public static TimeZone toTimeZone(Locale locale) {
        // 获取 locale 对应的国家代码
        String country = locale.getCountry();

        // 根据国家代码获取对应的时区 ID
        if (country != null && !country.isEmpty()) {
            return switch (country) {
                case "US" -> TimeZone.getTimeZone("America/New_York");
                case "CN" -> TimeZone.getTimeZone("Asia/Shanghai");
                case "IN" -> TimeZone.getTimeZone("Asia/Kolkata");
                case "GB" -> TimeZone.getTimeZone("Europe/London");
                case "JP" -> TimeZone.getTimeZone("Asia/Tokyo");
                case "AU" -> TimeZone.getTimeZone("Australia/Sydney");
                case "CA" -> TimeZone.getTimeZone("America/Toronto");
                case "DE" -> TimeZone.getTimeZone("Europe/Berlin");
                case "FR" -> TimeZone.getTimeZone("Europe/Paris");
                case "KR" -> TimeZone.getTimeZone("Asia/Seoul");
                case "RU" -> TimeZone.getTimeZone("Europe/Moscow");
                case "BR" -> TimeZone.getTimeZone("America/Sao_Paulo");
                case "ZA" -> TimeZone.getTimeZone("Africa/Johannesburg");
                default -> TimeZone.getDefault();
            };
        }
        // 如果没有找到合适的国家代码，返回系统默认时区
        return TimeZone.getDefault();
    }

    public static ZoneId toZoneId(Locale locale) {
        // 获取 locale 对应的国家代码
        String country = locale.getCountry();
        if (country == null || country.isEmpty()) {
            return ZoneId.systemDefault();
        }
        // 根据国家代码获取对应的时区 ID
        return switch (country) {
            case "US" -> ZoneId.of("America/New_York");
            case "CN" -> ZoneId.of("Asia/Shanghai");
            case "IN" -> ZoneId.of("Asia/Kolkata");
            case "GB" -> ZoneId.of("Europe/London");
            case "JP" -> ZoneId.of("Asia/Tokyo");
            case "AU" -> ZoneId.of("Australia/Sydney");
            case "CA" -> ZoneId.of("America/Toronto");
            case "DE" -> ZoneId.of("Europe/Berlin");
            case "FR" -> ZoneId.of("Europe/Paris");
            case "KR" -> ZoneId.of("Asia/Seoul");
            case "RU" -> ZoneId.of("Europe/Moscow");
            case "BR" -> ZoneId.of("America/Sao_Paulo");
            case "ZA" -> ZoneId.of("Africa/Johannesburg");
            default -> ZoneId.systemDefault();
        };
    }
}
