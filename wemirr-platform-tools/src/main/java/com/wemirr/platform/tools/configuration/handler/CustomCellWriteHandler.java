//package com.wemirr.platform.tools.configuration.handler;
//
//
//import com.alibaba.excel.metadata.CellData;
//import com.alibaba.excel.metadata.Head;
//import com.alibaba.excel.write.handler.CellWriteHandler;
//import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
//import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//
//import java.util.List;
//
///**
// * @author Levin
// */
//@Slf4j
//public class CustomCellWriteHandler implements CellWriteHandler {
//
//
//    @Override
//    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
//
//    }
//
//    @Override
//    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
//        // 这里可以对cell进行任何操作
////        Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
////        CellStyle cellStyle = workbook.createCellStyle();
////        Font cellFont = workbook.createFont();
////        cellFont.setBold(true);
////        cellStyle.setFont(cellFont);
////        cell.setCellStyle(cellStyle);
//    }
//
//    @Override
//    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
//
//    }
//
//    @Override
//    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
//        boolean needSetWidth = relativeRowIndex != null && (isHead || relativeRowIndex == 0);
//        if (!needSetWidth) {
//            return;
//        }
//        writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), 25 * 256);
//    }
//
//}