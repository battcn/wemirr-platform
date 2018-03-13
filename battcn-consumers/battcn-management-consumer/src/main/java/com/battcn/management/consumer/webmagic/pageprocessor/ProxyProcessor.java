package com.battcn.management.consumer.webmagic.pageprocessor;

import com.battcn.framework.webmagic.utils.BrowserAgentUtil;
import com.battcn.system.pojo.po.ProxyPool;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.ProxyUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 * @since 2018/3/13 0013
 */
public class ProxyProcessor implements PageProcessor {

    private static final Site DEFAULT_SITE = Site.me()
            .setTimeOut(6000).setRetryTimes(3)
            .setSleepTime(1000).setCharset("UTF-8")
            .addHeader("Accept-Encoding", "/")
            .setUserAgent(BrowserAgentUtil.getBrowserAgent());

    @Override
    public void process(Page page) {
        List<Selectable> trs = page.getHtml().xpath("//table[@class='table table-bordered proxy-index-table']/tbody/tr").nodes();
        List<ProxyPool> proxies = trs.stream().skip(1).map(node -> {
            final String host = node.xpath("//td[1]/text()").get();
            final int port = Integer.parseInt(node.xpath("//td[2]/text()").get());
            final String anonymity = node.xpath("//td[3]/text()").get();
            final String type = node.xpath("//td[4]/text()").get();
            final String location = node.xpath("//td[5]/text()").get();
            final String lastValidateTime = node.xpath("//td[6]/text()").get();
            final boolean locked = ProxyUtils.validateProxy(new Proxy(host, port));
            return new ProxyPool(null, host, port, anonymity, type, location, lastValidateTime, locked);
        }).collect(toList());
    }

    @Override
    public Site getSite() {
        return DEFAULT_SITE;
    }

    public static void main(String[] args) {
        Spider.create(new ProxyProcessor()).addUrl("http://lab.crossincode.com/proxy/").thread(5).run();
    }
}