package com.battcn.management.consumer.webmagic.pipeline;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.book.facade.BookChapterService;
import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookChapter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Levin
 * @since 2018/3/12 0012
 */
@Component
public class BookPipeline implements Pipeline {


    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private BookService bookService;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private BookChapterService bookChapterService;


    @Override
    public void process(ResultItems resultItems, Task task) {
        Book book = resultItems.get("book");
        List<BookChapter> chapters = resultItems.get("chapters");
        if (book != null) {
            this.bookService.insertSelective(book);
        }
        if (CollectionUtils.isNotEmpty(chapters)) {
            chapters.forEach(chapter -> bookChapterService.insertSelective(chapter));
        }
    }

    /**
     * TODO 无效代码
     */
    private void createHtml() {
        final Context context = new Context();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //模板所在目录，相对于当前ClassLoader的ClassPath
        resolver.setPrefix("templates/");
        //模板文件后缀
        resolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //渲染模板
        FileWriter write = null;
        try {
            List<BookChapter> chapters = null;
            for (BookChapter chapter : chapters) {
                context.setVariable("chapter", chapter);
                write = new FileWriter(StringUtils.join(UUID.randomUUID().toString(), String.valueOf(System.nanoTime()), ".html"));
                templateEngine.process("example", context, write);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (write != null) {
                    write.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
