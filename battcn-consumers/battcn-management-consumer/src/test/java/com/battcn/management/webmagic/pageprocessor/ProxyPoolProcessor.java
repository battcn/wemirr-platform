package com.battcn.management.webmagic.pageprocessor;

import com.battcn.framework.webmagic.utils.BrowserAgentUtil;
import com.battcn.framework.webmagic.utils.ProxyCheckUtils;
import com.battcn.management.webmagic.entity.ProxyPool;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lei.Yang
 * @since 2018/03/08
 */
public class ProxyPoolProcessor implements PageProcessor {

    private Site site = Site.me().setTimeOut(6000).setRetryTimes(3).setSleepTime(1000).setCharset("UTF-8")
            .addHeader("Accept-Encoding", "/").setUserAgent(BrowserAgentUtil.getBrowserAgent());

    @Override
    public void process(Page page) {
        List<String> ipList = page.getHtml().xpath("//table[@id='ip_list']/tbody/tr").all();
        List<ProxyPool> result = new ArrayList<>();
        if (ipList != null && ipList.size() > 0) {
            ipList.remove(0); // 移除表头
            for (String tmp : ipList) {
                Html html = Html.create(tmp);
                ProxyPool proxyPool = new ProxyPool();
                String[] data = html.xpath("//body/text()").toString().trim().split("\\s+");
                // 初步筛选 筛选https 速度小于2秒的
                String ipType = data[3];
                String ipSpeed = html.xpath("//div[@class='bar']/@title").toString();
                ipSpeed = ipSpeed.substring(0, ipSpeed.indexOf('秒'));
                double Speed = Double.parseDouble(ipSpeed);
                proxyPool.setIp(data[0]);
                proxyPool.setPort(Integer.valueOf(data[1]));
                System.out.println(proxyPool.getIp() + "  " + proxyPool.getPort());
                if (ipType.equals("HTTPS") && Speed <= 2.0) {
                    // 质量ip检验
                    if (ProxyCheckUtils.validIpAddress(proxyPool.getIp(), proxyPool.getPort())) {
                        System.out.println("有效ip" + proxyPool.getIp() + "  " + proxyPool.getPort());
                        result.add(proxyPool);
                    }
                }

            }
        }
        page.putField("result", result);
        page.addTargetRequest("http://www.xicidaili.com/nt/8");
        page.addTargetRequest("http://www.xicidaili.com/nt/7");
        page.addTargetRequest("http://www.xicidaili.com/nt/6");
        page.addTargetRequest("http://www.xicidaili.com/nt/5");
        page.addTargetRequest("http://www.xicidaili.com/nn/4");
        page.addTargetRequest("http://www.xicidaili.com/nn/3");
        page.addTargetRequest("http://www.xicidaili.com/nn/2");

    }

    @Override
    public Site getSite() {
        return site;
    }
}
