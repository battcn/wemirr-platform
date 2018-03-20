package com.battcn.management.consumer.controller.crawler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.book.facade.BookChapterService;
import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.exception.CustomException;
import com.battcn.framework.webmagic.downloader.CrawlerDownloader;
import com.battcn.management.consumer.util.ApiResult;
import com.battcn.management.consumer.webmagic.pageprocessor.BookProcessor;
import com.battcn.management.consumer.webmagic.pipeline.BookPipeline;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;
import us.codecraft.webmagic.Spider;

import java.util.Optional;

/**
 * @author Levin
 * @since 2018/3/19 0019
 */
@Slf4j
@Controller
@RequestMapping("/crawler/chapters")
@Api(value = "文章列表")
@ApiIgnore
public class ChapterController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private BookService bookService;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private BookChapterService bookChapterService;

    private final BookPipeline bookPipeline;

    @Autowired
    public ChapterController(BookPipeline bookPipeline) {
        this.bookPipeline = bookPipeline;
    }

    @ResponseBody
    @RequestMapping("/{chapter_id}/generate")
    public ApiResult<String> generateTemplate(@PathVariable("chapter_id") Long chapterId) {
        final BookChapter chapter = Optional.ofNullable(bookChapterService.selectById(chapterId)).orElseThrow(() -> CustomException.badRequest("章节不存在"));
        Spider.create(new BookProcessor()).addUrl(chapter.getSource()).setDownloader(new CrawlerDownloader()).addPipeline(bookPipeline).thread(2).run();
        return ApiResult.SUCCESS;
    }


}