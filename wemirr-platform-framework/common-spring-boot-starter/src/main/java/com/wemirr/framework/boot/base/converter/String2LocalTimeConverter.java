package com.wemirr.framework.boot.base.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.wemirr.framework.commons.times.TimeConstants.DEFAULT_TIME_FORMAT;


/**
 * 解决入参为 Date类型
 *
 * @author Levin
 * @since 2019-04-30
 */
public class String2LocalTimeConverter extends BaseDateConverter<LocalTime> implements Converter<String, LocalTime> {

    private static final Map<String, String> FORMAT = new LinkedHashMap<>(1);

    static {
        FORMAT.put(DEFAULT_TIME_FORMAT, "^\\d{1,2}:\\d{1,2}:\\d{1,2}$");
    }

    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }

    @Override
    public LocalTime convert(String source) {
        return super.convert(source, (key) -> LocalTime.parse(source, DateTimeFormatter.ofPattern(key)));
    }
}
