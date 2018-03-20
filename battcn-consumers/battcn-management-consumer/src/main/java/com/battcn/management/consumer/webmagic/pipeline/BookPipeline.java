package com.battcn.management.consumer.webmagic.pipeline;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.book.facade.BookChapterService;
import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.commons.lang.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Levin
 * @since 2018/3/12 0012
 */
@Slf4j
@Component
public class BookPipeline implements Pipeline {


    private static final String TEMPLATE_NAME = "crawler/chapter/template";
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private BookService bookService;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private BookChapterService bookChapterService;


    private final TemplateEngine templateEngine;

    @Autowired
    public BookPipeline(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        Book book = resultItems.get("book");
        List<BookChapter> chapters = resultItems.get("chapters");
        BookChapter chapter = resultItems.get("chapter");
        if (book != null && book.getName() != null) {
            Book record = new Book();
            record.setName(book.getName());
            record.setSource(book.getSource());
            final List<Book> books = this.bookService.select(record);
            if (CollectionUtils.isEmpty(books)) {
                this.bookService.insertSelective(book);
            }
        }
        if (CollectionUtils.isNotEmpty(chapters)) {
            this.bookChapterService.insertList(chapters);
        }
        if (chapter != null && StringUtils.isNotEmpty(chapter.getSource())) {
            final BookChapter bookChapter = this.bookChapterService.selectOne(new BookChapter(chapter.getSource()));
            final Context context = new Context();
            context.setVariable("book", this.bookService.selectById(bookChapter.getBookNo()));
            context.setVariable("chapter", bookChapter);
            final String fileName = StringUtils.join(RandomUtils.generate(), ".html");
            log.info("[模板路径] - [{}]", fileName);
            try (FileWriter write = new FileWriter(fileName)) {
                bookChapter.setStatus(Boolean.TRUE);
                bookChapter.setTarget(fileName);
                bookChapter.setContent(chapter.getContent());
                templateEngine.process(TEMPLATE_NAME, context, write);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //生成成功,修改数据状态
            this.bookChapterService.updateSelectiveById(bookChapter);
        }
    }
}
