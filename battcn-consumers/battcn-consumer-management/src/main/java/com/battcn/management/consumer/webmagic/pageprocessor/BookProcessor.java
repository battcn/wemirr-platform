package com.battcn.management.consumer.webmagic.pageprocessor;

import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.webmagic.utils.BrowserAgentUtil;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;


/**
 * 读取 http://www.biquge5200.com/ 网站的书籍信息
 *
 * @author Levin
 * @since 2018/3/9 0009
 */
public class BookProcessor implements PageProcessor {

    private static final String BOOK_AUTHOR_SEPARATOR_CHARS = "：";
    public static final String ALL_BOOK_LINK = "http://www.biquge5200.com/xiaoshuodaquan/";
    private static final String BOOK_REGEX = "http://www.biquge5200\\.com/\\w+/";
    private static final String CHAPTER_REGEX = "http://www\\.biquge5200\\.com/\\w+/\\w+\\.html";

    private static final Site DEFAULT_SITE = Site.me()
            .setTimeOut(30000).setRetryTimes(3)
            .setSleepTime(10000).setCharset("utf-8").addHeader("Accept-Language", "zh-CN,zh;q=0.9")
            .setUserAgent(BrowserAgentUtil.getBrowserAgent());

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(CHAPTER_REGEX).match()) {
            String source = page.getUrl().get();
            String content = page.getHtml().xpath("//div[@id='content']/html()").get();
            BookChapter chapter = new BookChapter();
            chapter.setSource(source);
            chapter.setContent(content);
            page.putField("chapter", chapter);
        } else if (page.getUrl().regex(BOOK_REGEX).match() && !StringUtils.equalsIgnoreCase(page.getUrl().get(), ALL_BOOK_LINK)) {
            // 单本书的信息
            // 这里解析出了 列表页的 基本信息
            String bookType = page.getHtml().xpath("//div[@class='con_top']/a[2]/text()").get();
            Selectable info = page.getHtml().css("#maininfo");
            String bookName = info.xpath("//h1/text()").get();
            String bookAuthor = info.xpath("//p[1]/text()").get();
            String bookInfo = page.getHtml().xpath("//div[@id='intro']/p/text()").get();
            String bookCover = page.getHtml().xpath("//div[@id='fmimg']//img").$("img", "src").get();
            Book book = new Book();
            book.setName(bookName);
            book.setSource(page.getUrl().get());
            book.setBookType(bookType);
            if (bookAuthor.contains(BOOK_AUTHOR_SEPARATOR_CHARS)) {
                book.setAuthor(StringUtils.split(bookAuthor, "：")[1]);
            } else {
                book.setAuthor(bookAuthor);
            }
            book.setDescription(bookInfo);
            book.setCover(bookCover);
            String bookNo = StringUtils.join("NO", System.nanoTime());
            book.setBookNo(bookNo);
            List<Selectable> nodes = page.getHtml().xpath("//div[@id='list']//dd/a").nodes();
            List<BookChapter> chapters = Lists.newArrayList();
            for (Selectable node : nodes) {
                String link = node.links().get();
                String title = node.xpath("//a/text()").get();
                BookChapter chapter = new BookChapter();
                chapter.setSource(link);
                chapter.setBookNo(bookNo);
                chapter.setTitle(title);
                chapters.add(chapter);
            }
            page.putField("book", book);
            page.putField("chapters", chapters);
            page.addTargetRequests(page.getHtml().css("#main").links().regex(BOOK_REGEX).all());
        }
    }

    @Override
    public Site getSite() {
        return DEFAULT_SITE;
    }

}