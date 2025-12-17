package com.wemirr.framework.excel.convert;

import cn.hutool.core.util.StrUtil;
import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wemirr.framework.commons.threadlocal.ThreadLocalHolder;
import com.wemirr.framework.commons.times.TimeZoneUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

/**
 * Excel Instant类型转换器
 * <p>支持Instant与Excel字符串互转，根据用户时区自动转换</p>
 * <p>支持 {@link JsonFormat} 注解配置日期格式</p>
 *
 * @author Levin
 * @since 1.0.0
 */
@Slf4j
public class InstantConverter implements Converter<Instant> {

    @Override
    public Class<Instant> supportJavaTypeKey() {
        return Instant.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Instant convertToJavaData(ReadCellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String value = cellData.getStringValue();
        if (StrUtil.isBlank(value)) {
            return null;
        }
        return TimeZoneUtil.toOffsetUtcTime(value, ThreadLocalHolder.getLocale());
    }

    @Override
    public WriteCellData<String> convertToExcelData(Instant value, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        if (Objects.isNull(value)) {
            return new WriteCellData<>();
        }
        Field field = contentProperty.getField();
        // 暂时只读取 JsonFormat.format
        JsonFormat format = field.getAnnotation(JsonFormat.class);
        String pattern = Optional.ofNullable(format).map(JsonFormat::pattern).orElse("yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId = TimeZoneUtil.toZoneId(ThreadLocalHolder.getLocale());
        log.debug("Instant 日期时区 - {}", zoneId);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(value, zoneId);
        return new WriteCellData<>(localDateTime.format(DateTimeFormatter.ofPattern(pattern)));
    }
}
