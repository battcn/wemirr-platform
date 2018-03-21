package com.battcn.management.consumer.webmagic.pageprocessor;

import com.battcn.framework.webmagic.utils.BrowserAgentUtil;
import com.battcn.system.pojo.po.ProxyPool;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.ProxyUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 * @since 2018/3/13 0013
 */
public class ProxyProcessor implements PageProcessor {


    private static final Site DEFAULT_SITE = Site.me()
            .setTimeOut(6000).setRetryTimes(3)
            .setSleepTime(1000).setCharset("utf-8").addHeader("Accept-Language", "zh-CN,zh;q=0.9")
            .setUserAgent(BrowserAgentUtil.getBrowserAgent());
    private static final String HTTPS = "HTTPS";
    private static final List<String> XICIDALILI_TARGET_REQUESTS = Lists.newArrayList("http://www.xicidaili.com/nt/2",
            "http://www.xicidaili.com/nt/3",
            "http://www.xicidaili.com/nt/4",
            "http://www.xicidaili.com/nt/5",
            "http://www.xicidaili.com/nt/6");

    private static final List<String> CODERBUSY_TARGET_REQUESTS = Lists.newArrayList("https://proxy.coderbusy.com/classical/anonymous-type/highanonymous.aspx?page=2",
            "https://proxy.coderbusy.com/classical/anonymous-type/highanonymous.aspx?page=3",
            "https://proxy.coderbusy.com/classical/anonymous-type/highanonymous.aspx?page=4",
            "https://proxy.coderbusy.com/classical/anonymous-type/highanonymous.aspx?page=5",
            "https://proxy.coderbusy.com/classical/anonymous-type/highanonymous.aspx?page=6");

    private static final String CROSSINCODE = "crossincode";
    private static final String XICIDAILI = "xicidaili";
    private static final String IP181 = "ip181";
    private static final String CODERBUSY = "coderbusy";

    @Override
    public void process(Page page) {
        String proxyLink = page.getUrl().get();
        if (proxyLink.contains(CROSSINCODE)) {
            List<Selectable> trs = page.getHtml().xpath("//table[@class='table table-bordered proxy-index-table']/tbody/tr").nodes();
            // Crossin 编程实验室 代理IP
            page.putField("proxies", resolveBaseProxy(CROSSINCODE, trs));
        } else if (proxyLink.contains(XICIDAILI)) {
            // 大象代理
            page.putField("proxies", resolveXicidailiProxy(XICIDAILI, page));
            page.addTargetRequests(XICIDALILI_TARGET_REQUESTS);
        } else if (proxyLink.contains(IP181)) {
            List<Selectable> trs = page.getHtml().xpath("//table[@class='table table-hover panel-default panel ctable']/tbody/tr").nodes();
            // ip181 代理
            page.putField("proxies", resolveBaseProxy(IP181, trs));
        } else if (proxyLink.contains(CODERBUSY)) {
            List<Selectable> trs = page.getHtml().xpath("//table[@class='table']/tbody/tr").nodes();
            page.putField("proxies", resolveCoderbusyProxy(trs));
            page.addTargetRequests(CODERBUSY_TARGET_REQUESTS);
        }
    }

    private static List<ProxyPool> resolveCoderbusyProxy(List<Selectable> trs) {
        if (CollectionUtils.isEmpty(trs)) {
            return null;
        }
        return trs.stream().map(node -> {
            final String host = node.xpath("//td[1]/text()").get();
            final int port = Integer.parseInt(node.xpath("//td[2]/text()").get());
            final String location = node.xpath("//td[3]/text()").get();
            final String anonymity = node.xpath("//td[7]/a/text()").get();
            final String type = node.xpath("//td[5]/text()").get();
            final String validateTime = node.xpath("//td[6]/span/@title").get();
            final boolean locked = ProxyUtils.validateProxy(new Proxy(host, port));
            return new ProxyPool(null, host, port, anonymity, type, location, validateTime, locked, CODERBUSY);
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private static List<ProxyPool> resolveBaseProxy(String source, List<Selectable> trs) {
        if (CollectionUtils.isEmpty(trs)) {
            return null;
        }
        return trs.stream().skip(1).map(node -> {
            final String host = node.xpath("//td[1]/text()").get();
            final int port = Integer.parseInt(node.xpath("//td[2]/text()").get());
            final String anonymity = node.xpath("//td[3]/text()").get();
            final String type = node.xpath("//td[4]/text()").get();
            if (StringUtils.equalsIgnoreCase(source, IP181)) {
                final String validateTime = node.xpath("//td[5]/text()").get();
                final String location = node.xpath("//td[6]/text()").get();
                final boolean locked = ProxyUtils.validateProxy(new Proxy(host, port));
                return new ProxyPool(null, host, port, anonymity, type, location, validateTime, locked, source);
            }
            return null;
        }).collect(toList());
    }

    private static List<ProxyPool> resolveXicidailiProxy(String source, Page page) {
        List<Selectable> trs = page.getHtml().xpath("//table[@id='ip_list']/tbody/tr").nodes();
        if (CollectionUtils.isEmpty(trs)) {
            return null;
        }
        return trs.stream().skip(1).map(node -> {
            final String type = node.xpath("//td[6]/text()").get();
            if (StringUtils.equalsIgnoreCase(HTTPS, type)) {
                final String host = node.xpath("//td[2]/text()").get();
                final int port = Integer.parseInt(node.xpath("//td[3]/text()").get());
                final String location = node.xpath("//td[4]/a/text()").get();
                final String anonymity = node.xpath("//td[5]/text()").get();
                final String validateTime = StringUtils.join("20", node.xpath("//td[10]/text()").get());
                final boolean locked = ProxyUtils.validateProxy(new Proxy(host, port));
                return new ProxyPool(null, host, port, anonymity, type, location, validateTime, locked, source);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public Site getSite() {
        return DEFAULT_SITE;
    }

    /*public static void main(String[] args) {
        // https://proxy.coderbusy.com/classical/anonymous-type/highanonymous.aspx
        Spider.create(new ProxyProcessor()).setDownloader(new CrawlerDownloader()).addUrl("https://proxy.coderbusy.com/classical/anonymous-type/highanonymous.aspx").thread(10).run();
    }*/


}