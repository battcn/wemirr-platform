package com.wemirr.platform.ai.core.processor;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基于Langchain4j的文档处理器
 *
 * @author xJh
 * @since 2025/10/20
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentProcessor {

    /**
     * 默认分片大小（字符数） 【如果有token计算器，按token计算】
     */
    private static final int DEFAULT_CHUNK_SIZE = 1000;
    
    /**
     * 默认分片重叠大小（字符数） 【如果有token计算器，按token计算】
     */
    private static final int DEFAULT_CHUNK_OVERLAP = 200;

    /**
     * 从文件中提取文本
     *
     * @param file 文件
     * @param contentType 内容类型
     * @return 提取的文本
     * @throws IOException IO异常
     */
    public String extractText(File file, String contentType) throws IOException {
        DocumentParser parser = getParserByContentType(contentType);
        FileInputStream fileInputStream = new FileInputStream(file);
        Document document = parser.parse(fileInputStream);
        return document.text();
    }

    /**
     * 将文本分割成多个分片
     *
     * @param text 文本
     * @return 分片列表
     */
    public List<String> splitText(String text) {
        return splitText(text, DEFAULT_CHUNK_SIZE, DEFAULT_CHUNK_OVERLAP);
    }

    /**
     * 将文本分割成多个分片
     *
     * @param text 文本
     * @param chunkSize 分片大小 (按token计算）
     * @param chunkOverlap 分片重叠大小
     * @return 分片列表
     */
    public List<String> splitText(String text, int chunkSize, int chunkOverlap) {
        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 创建文档
        Document document = Document.from(text);
        
        // 创建分片器
        DocumentSplitter splitter = DocumentSplitters.recursive(chunkSize, chunkOverlap);
        
        // 分片
        List<TextSegment> segments = splitter.split(document);
        
        // 转换为字符串列表
        return segments.stream()
                .map(TextSegment::text)
                .collect(Collectors.toList());
    }

    /**
     * 计算文本的token数量
     *
     * @param text 文本
     * @return token数量
     */
    public int countTokens(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        // TODO tokenizer
        return text.split("\\s+").length;
    }
    
    /**
     * 根据内容类型获取解析器
     *
     * @param contentType 内容类型
     * @return 文档解析器
     */
    private DocumentParser getParserByContentType(String contentType) {
        if (contentType == null) {
            contentType = "";
        }
        
        if (contentType.contains("pdf")) {
            return new ApacheTikaDocumentParser();
        } else if (contentType.contains("word") || contentType.contains("docx") || 
                   contentType.contains("excel") || contentType.contains("xlsx") || 
                   contentType.contains("powerpoint") || contentType.contains("pptx")) {
            return new ApacheTikaDocumentParser();
        } else if (contentType.contains("html")) {
            return new ApacheTikaDocumentParser();
        } else {
            // 默认使用文本解析器
            return new TextDocumentParser();
        }
    }
    
    /**
     * 处理文件并分片
     *
     * @param file 文件
     * @param contentType 内容类型
     * @param chunkSize 分片大小
     * @param chunkOverlap 分片重叠大小
     * @return 分片列表
     * @throws IOException IO异常
     */
    public List<String> processFileAndSplit(File file, String contentType, int chunkSize, int chunkOverlap) throws IOException {
        DocumentParser parser = getParserByContentType(contentType);
        FileInputStream inputStream = new FileInputStream(file);
        Document document = parser.parse(inputStream);
        
        DocumentSplitter splitter = DocumentSplitters.recursive(chunkSize, chunkOverlap);
        List<TextSegment> segments = splitter.split(document);
        
        return segments.stream()
                .map(TextSegment::text)
                .collect(Collectors.toList());
    }
    
    /**
     * 处理文件并分片（使用默认分片大小）
     *
     * @param file 文件
     * @param contentType 内容类型
     * @return 分片列表
     * @throws IOException IO异常
     */
    public List<String> processFileAndSplit(File file, String contentType) throws IOException {
        return processFileAndSplit(file, contentType, DEFAULT_CHUNK_SIZE, DEFAULT_CHUNK_OVERLAP);
    }
}
