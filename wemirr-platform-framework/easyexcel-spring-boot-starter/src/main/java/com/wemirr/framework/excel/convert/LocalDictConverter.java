package com.wemirr.framework.excel.convert;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;
import com.wemirr.framework.commons.entity.DictEnum;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * @author Levin
 */
@SuppressWarnings("unchecked")
public class LocalDictConverter implements Converter<DictEnum<?>> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return DictEnum.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override

    public DictEnum<?> convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                         GlobalConfiguration globalConfiguration) {
        Field field = contentProperty.getField();
        Class<DictEnum<?>> type = (Class<DictEnum<?>>) field.getType();
        return DictEnum.of(type, cellData.getStringValue());
    }

    @Override
    public WriteCellData<String> convertToExcelData(DictEnum<?> value, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        return new WriteCellData<>(Optional.ofNullable(value).map(DictEnum::getLabel).orElse(""));
    }

}