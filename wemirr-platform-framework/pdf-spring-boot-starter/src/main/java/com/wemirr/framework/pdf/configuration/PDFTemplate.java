package com.wemirr.framework.pdf.configuration;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.wemirr.framework.pdf.domain.PDFDomain;
import com.wemirr.framework.pdf.handler.PageEventHandler;
import com.wemirr.framework.pdf.handler.WaterMarkEventHandler;
import com.wemirr.framework.pdf.utils.HtmlToPdfUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
public class PDFTemplate {

    private Configuration cfg = null;

    public void init() {
        if (cfg != null) {
            return;
        }
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        // 设置模板文件所在的目录
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
    }

    /**
     * PDF 导出
     *
     * @param domain   pdf 对象
     * @param response 输出流
     */
    @SneakyThrows
    public void export(PDFDomain domain, HttpServletResponse response) {
        export(Map.of(PdfDocumentEvent.END_PAGE, List.of(new WaterMarkEventHandler(domain.getWatermark()), new PageEventHandler())), domain, response);
    }

    @SneakyThrows
    public void export(Map<String, List<IEventHandler>> eventHandlers, PDFDomain domain, HttpServletResponse response) {
        // 加载模板
        Template template = cfg.getTemplate(domain.getTemplateName());
        // 渲染模板到字符串
        StringWriter out = new StringWriter();
        template.process(domain.getModel(), out);
        // 生成 PDF 文件
        try (InputStream is = new ByteArrayInputStream(out.toString().getBytes());
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            HtmlToPdfUtils.html2Pdf(eventHandlers, is, bos);
            byte[] pdfBytes = bos.toByteArray();
            // 设置响应头
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + domain.getFileName() + ".pdf\"");
            response.setContentLength(pdfBytes.length);
            // 将 PDF 写入响应输出流
            try (OutputStream os = response.getOutputStream()) {
                os.write(pdfBytes);
                os.flush();
            } catch (Exception ex) {
                log.error("生成 PDF 文件异常", ex);
            }
        } catch (Exception ex) {
            log.error("生成 PDF 文件异常", ex);
        }
    }


}
