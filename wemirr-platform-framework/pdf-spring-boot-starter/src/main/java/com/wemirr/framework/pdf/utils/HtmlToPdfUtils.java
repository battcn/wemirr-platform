package com.wemirr.framework.pdf.utils;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;
import com.wemirr.framework.pdf.handler.PageEventHandler;
import com.wemirr.framework.pdf.handler.WaterMarkEventHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
public class HtmlToPdfUtils {

    private static void addEventHandler(PdfDocument pdfDocument, Map<String, List<IEventHandler>> eventHandlers) {
        if (eventHandlers == null || eventHandlers.isEmpty()) {
            return;
        }
        for (Map.Entry<String, List<IEventHandler>> entry : eventHandlers.entrySet()) {
            for (IEventHandler handler : entry.getValue()) {
                pdfDocument.addEventHandler(entry.getKey(), handler);
            }
        }
    }


    /**
     * HTML 转 PDF 带水印
     *
     * @param eventHandlers 事件处理
     * @param inputStream   输入流
     * @param outputStream  输出流
     */
    public static void html2Pdf(Map<String, List<IEventHandler>> eventHandlers, InputStream inputStream, OutputStream outputStream) {
        try (PdfWriter pdfWriter = new PdfWriter(outputStream); PdfDocument pdfDocument = new PdfDocument(pdfWriter)) {
            addEventHandler(pdfDocument, eventHandlers);
            ConverterProperties properties = new ConverterProperties();
            //添加中文字体支持
            FontProvider fontProvider = new FontProvider();
            PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H");
            fontProvider.addFont(sysFont.getFontProgram(), "UniGB-UCS2-H");
            properties.setFontProvider(fontProvider);
            HtmlConverter.convertToPdf(inputStream, pdfDocument, properties);
        } catch (Exception e) {
            log.error("pdf 处理异常 - {}", e.getLocalizedMessage());
        }
    }

    /**
     * HTML 转 PDF 带水印
     *
     * @param inputStream  输入流
     * @param waterMark    水印
     * @param outputStream 输出流
     */
    public static void html2WaterMarkPdf(InputStream inputStream, String waterMark, OutputStream outputStream) {
        html2Pdf(Map.of(PdfDocumentEvent.END_PAGE, List.of(new WaterMarkEventHandler(waterMark), new PageEventHandler())), inputStream, outputStream);
    }
}
