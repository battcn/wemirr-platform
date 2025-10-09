package com.wemirr.framework.excel.handler.head;


import cn.idev.excel.metadata.Head;
import cn.idev.excel.write.handler.CellWriteHandler;
import cn.idev.excel.write.metadata.holder.WriteSheetHolder;
import cn.idev.excel.write.metadata.holder.WriteTableHolder;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * I18N 附议处理
 *
 * @author Levin
 */
@RequiredArgsConstructor
public class I18nHeaderCellWriteHandler implements CellWriteHandler {

    /**
     * SPEL 语法处理
     */
    private final PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("${", "}");


    private final MessageSource messageSource;
    private final PropertyPlaceholderHelper.PlaceholderResolver placeholderResolver;

    public I18nHeaderCellWriteHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.placeholderResolver = placeholderName -> this.messageSource.getMessage(placeholderName, null,
                LocaleContextHolder.getLocale());
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        if (isHead == null || !isHead) {
            return;
        }
        var headNameList = head.getHeadNameList();
        if (headNameList == null || headNameList.isEmpty()) {
            return;
        }
        // I18N 处理
        var i18nHeadNames = headNameList.stream()
                .map(headName -> propertyPlaceholderHelper.replacePlaceholders(headName, placeholderResolver))
                .toList();
        head.setHeadNameList(i18nHeadNames);
    }

}