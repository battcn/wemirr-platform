package com.battcn.management.consumer.webmagic.pipeline;

import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookChapter;
import org.apache.commons.lang3.StringUtils;
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
public class BookPipeline implements Pipeline {

    private static final Book book = new Book();
    private static final Context context = new Context();

    @Override
    public void process(ResultItems resultItems, Task task) {

        String bookName = resultItems.get("bookName");
        String bookType = resultItems.get("bookType");
        String bookAuthor = resultItems.get("bookAuthor");
        String bookInfo = resultItems.get("bookInfo");
        String bookCover = resultItems.get("bookCover");
        if (StringUtils.isNoneBlank(bookName, bookType, bookAuthor, bookInfo, bookCover)) {
            book.setAuthor(bookAuthor);
            book.setCover(bookCover);
            book.setDescription(bookInfo);
            book.setType(bookType);
            book.setName(bookName);
            context.setVariable("book", book);
        }
        List<BookChapter> chapters = resultItems.get("chapters");
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
