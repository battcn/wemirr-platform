package com.battcn.management.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

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
        page.addTargetRequests(page.getHtml().links().regex("http://www.biquge5200\\.com/0_844/\\.html").all());
        page.putField("title", page.getHtml().css("#list").xpath("//dd/a/text()"));
        //System.out.println(page.getHtml().toString());

        //page.putField("author", page.getUrl().regex("http://www\\.biquge5200\\.com/0_844\\.com/(\\\\w+)/.*").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BookRepoPageProcessor()).addUrl("http://www.biquge5200.com/0_844").addPipeline(new JsonFilePipeline("D:\\webmagic")).thread(5).run();
    }
}