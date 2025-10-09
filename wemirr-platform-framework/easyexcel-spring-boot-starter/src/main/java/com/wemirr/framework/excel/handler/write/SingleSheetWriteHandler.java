package com.wemirr.framework.excel.handler.write;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.ExcelWriter;
import cn.idev.excel.write.metadata.WriteSheet;
import com.wemirr.framework.excel.domain.ExcelWriteFile;
import com.wemirr.framework.excel.domain.SheetInfo;
import com.wemirr.framework.excel.domain.WriterType;
import com.wemirr.framework.excel.handler.ISheetWriteHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class SingleSheetWriteHandler implements ISheetWriteHandler {

    private final ApplicationContext context;

    @Override
    public boolean support(List<SheetInfo> sheets) {
        return sheets != null && sheets.size() == 1;
    }

    @Override
    public void write(HttpServletResponse response, ExcelWriteFile file) {
        List<?> dataList = (List<?>) file.getData();
        ExcelWriter excelWriter = getExcelWriter(context, response, file);
        WriteSheet sheet;
        if (CollectionUtils.isEmpty(dataList)) {
            sheet = EasyExcel.writerSheet(file.getSheetList().get(0).getName()).build();
        } else {
            Class<?> dataClass = dataList.get(0).getClass();
            sheet = this.sheet(context, file.getSheetList().get(0), dataClass, file.getTemplate(), file.getHeadGenerator());
        }
        if (file.getWriterType() != null && file.getWriterType() == WriterType.FILL) {
            excelWriter.fill(dataList, sheet);
        } else {
            excelWriter.write(dataList, sheet);
        }
        excelWriter.finish();
    }
}

