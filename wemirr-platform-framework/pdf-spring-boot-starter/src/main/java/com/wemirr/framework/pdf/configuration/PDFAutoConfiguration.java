package com.wemirr.framework.pdf.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Levin
 */
@Slf4j
@Configuration
public class PDFAutoConfiguration {
    @Bean(initMethod = "init")
    public PDFTemplate pdfTemplate() {
        return new PDFTemplate();
    }
}
