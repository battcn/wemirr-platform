package com.battcn.management.consumer.controller.crawler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.book.facade.BookChapterService;
import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.commons.lang.RandomUtils;
import com.battcn.management.consumer.util.ApiResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import springfox.documentation.annotations.ApiIgnore;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Levin
 * @since 2018/3/19 0019
 */
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

    private final TemplateEngine templateEngine;

    @Autowired
    public ChapterController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @RequestMapping("/{chapter_id}/generate")
    public ApiResult<String> generateTemplate(@PathVariable("chapter_id") Long chapterId) throws IOException {
        final BookChapter chapter = bookChapterService.selectById(chapterId);
        Book record = new Book();
        record.setBookNo(chapter.getBookNo());
        final Book book = this.bookService.selectOne(record);
        final Context context = new Context();
        context.setVariable("book", book);
        context.setVariable("chapter", chapter);
        FileWriter write = new FileWriter(StringUtils.join(RandomUtils.generate(), ".html"));
        templateEngine.process("crawler/chapter/template", context, write);
        return ApiResult.SUCCESS;
    }


}