package com.battcn.management.webmagic;

import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 读取GIT信息
 *
 * @author Levin
 * @since 2018/3/9 0009
 */
public class BookRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        //   http://www.biquge5200\\.com/0_844
        //page.addTargetRequests(page.getHtml().links().all());
        //List<String> all1 = page.getHtml().links().regex("http://www.biquge5200\\.com/0_844/\\.*\\.html").all();
        Book book = new Book();
        String bookType = page.getHtml().xpath("//div[@class='con_top']/a[2]/text()").get();
        //System.out.println("bookType===" + bookType);
        Selectable info = page.getHtml().css("#maininfo");
        String bookAuthor = info.xpath("//h1/text()").get();
        String bookName = info.xpath("//p[1]/text()").get();
        //System.out.println("bookAuthor===" + info.xpath("//h1/text()"));
        //System.out.println("bookName===" + info.xpath("//p[1]/text()"));
        String bookInfo = page.getHtml().css("#intro").xpath("//p/text()").get();
        //System.out.println("bookInfo===" + page.getHtml().css("#intro").xpath("//p/text()"));
        String bookCover = page.getHtml().css("#fmimg").xpath("//img").$("img", "src").get();
        //System.out.println("bookCover===" + bookCover);
        book.setType(bookType);
        book.setAuthor(bookAuthor);
        book.setInfo(bookInfo);
        book.setCover(bookCover);
        List<BookLabel> labels = Lists.newArrayList();
        List<Selectable> nodes = page.getHtml().css("#list").xpath("//dd/a").nodes();
        int i = 0;
        for (Selectable node : nodes) {
            i++;
            if (i > 10) {
                continue;
            }
            //System.out.println("bookLink===" + node.links());
            //System.out.println("bookTitle" + node.xpath("//a/text()"));
            labels.add(new BookLabel(node.links().get(), node.xpath("//a/text()").get()));
        }
        book.setLabels(labels);
        System.out.println("=--=============================="+JSON.toJSONString(book));
        //List<Selectable> nodes = xpath.nodes();
        //System.out.println(page.getHtml().css("#list").xpath("//dd/a").toString());
//        nodes.forEach(node -> {
//            System.out.println(node.links());
//            System.out.println(node.xpath("//a/text()"));
//        });
        /*List<String> all = page.getHtml().links().regex("http://www.biquge5200\\.com/0_844/\\.html").all();
        System.out.println(all);
        System.out.println(page.getHtml().css("#list").xpath("//dd/a").toString());
        System.out.println(page.getHtml().css("#list").xpath("//dd/a/text()").toString());*/
        //page.putField("title", page.getHtml().css("#list").xpath("//dd/a/text()"));
        //System.out.println(page.getHtml().toString());

        //page.putField("author", page.getUrl().regex("http://www\\.biquge5200\\.com/0_844\\.com/(\\\\w+)/.*").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BookRepoPageProcessor()).addUrl("http://www.biquge5200.com/0_844").addPipeline(new JsonFilePipeline("E:\\webmagic")).thread(5).run();
    }
}