package com.wemirr.framework.excel.handler.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wemirr.framework.excel.domain.ExcelWriteFile;
import com.wemirr.framework.excel.domain.WriterType;
import com.wemirr.framework.excel.handler.ISheetWriteHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.util.CollectionUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public class SingleSheetWriteHandler implements ISheetWriteHandler {


    private final ApplicationContext context;

    @Override
    public void export(HttpServletResponse response, ExcelWriteFile file) {
        validate(context, file);
        String name = Optional.ofNullable(file.getFileName()).orElse(String.valueOf(System.currentTimeMillis()));
        String fileName = String.format("%s%s", URLEncoder.encode(name, StandardCharsets.UTF_8), file.getExcelType().getValue());
        String contentType = MediaTypeFactory.getMediaType(fileName).map(MediaType::toString).orElse("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentType(contentType);
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        response.setCharacterEncoding("utf-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename*=utf-8''" + fileName);
        write(response, file);
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

