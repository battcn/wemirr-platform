package com.wemirr.framework.pdf.handler;

import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class WaterMarkEventHandler implements IEventHandler {

    /**
     * 水印内容
     */
    private final String context;

    /**
     * 一页中有几列水印
     */
    private final int x;

    /**
     * 一页中每列有多少水印
     */
    private final int y;

    public WaterMarkEventHandler(String context) {
        this(context, 5, 5);
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        PdfDocument document = documentEvent.getDocument();
        PdfPage page = documentEvent.getPage();
        Rectangle pageSize = page.getPageSize();
        PdfFont pdfFont = null;
        try {
            pdfFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H");
        } catch (IOException e) {
            log.error("PDF 字体创建异常 - {}", e.getLocalizedMessage());
        }

        PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), document);

        Paragraph waterMark = new Paragraph(context).setOpacity(0.5f);
        Canvas canvas = new Canvas(pdfCanvas, pageSize)
                .setFontColor(WebColors.getRGBColor("lightgray"))
                .setFontSize(16)
                .setFont(pdfFont);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                canvas.showTextAligned(waterMark, (150 + i * 300), (160 + j * 150), document.getNumberOfPages(), TextAlignment.CENTER, VerticalAlignment.BOTTOM, 120);
            }
        }
        canvas.close();
    }
}
