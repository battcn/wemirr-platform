package com.battcn.management.webmagic.pipeline;

import com.alibaba.fastjson.JSON;
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
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Levin
 * @since 2018/3/12 0012
 */
public class BookPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        final Set<Map.Entry<String, Object>> entries = resultItems.getAll().entrySet();
        final String json = JSON.toJSONString(entries);
        final Book book = JSON.parseObject(json, Book.class);

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");//模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html");//模板文件后缀
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文(Model)
        Context context = new Context();
        //渲染模板
        FileWriter write = null;
        try {
            for (BookLabel label : book.getLabels()) {
                Book bk = book;
                bk.setLabels(Lists.newArrayList(label));
                context.setVariable("book", bk);
                write = new FileWriter(StringUtils.join(UUID.randomUUID().toString(), String.valueOf(System.nanoTime()), ".html"));
                templateEngine.process("example", context, write);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
