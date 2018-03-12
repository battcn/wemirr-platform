package com.battcn.management.webmagic.pipeline;

import com.battcn.management.webmagic.BookLabel;
import com.battcn.management.webmagic.entity.Book;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
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
            book.setInfo(bookInfo);
            book.setType(bookType);
            book.setName(bookName);
        }
        List<BookLabel> labels = resultItems.get("labels");
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");//模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html");//模板文件后缀
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //渲染模板
        FileWriter write = null;
        try {
            for (BookLabel label : labels) {
                Book bk = book;
                bk.setLabels(Lists.newArrayList(label));
                context.setVariable("book", bk);
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
