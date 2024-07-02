package com.wemirr.framework.excel.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

/**
 * @author levin
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
    public Instant convertToJavaData(ReadCellData cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
        return Instant.parse(cellData.getStringValue());
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
        String pattern = Optional.ofNullable(format).map(JsonFormat::pattern).orElse("yyyy-MM-dd HH:MM:ss");
        ZoneId zoneId = ZoneId.systemDefault();
        log.debug("Instant 日期时区 - {}", zoneId);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(value, zoneId);
        return new WriteCellData<>(localDateTime.format(DateTimeFormatter.ofPattern(pattern)));
    }
}
