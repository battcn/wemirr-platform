package com.battcn.management.webmagic.pageprocessor;

import java.util.ArrayList;
import java.util.List;

import com.battcn.management.webmagic.entity.ProxyIp;
import com.battcn.management.webmagic.utils.IPCheckUtil;
import com.battcn.management.webmagic.utils.UserAgentUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * Created by CrowHawk on 17/10/16.
 * edit by yanglei on 17/12/22.
 */
public class ProxyPoolProcessor implements PageProcessor {

	private Site site = Site.me().setTimeOut(6000).setRetryTimes(3).setSleepTime(1000).setCharset("UTF-8")
			.addHeader("Accept-Encoding", "/").setUserAgent(UserAgentUtil.getRandomUserAgent());

	@Override
	public void process(Page page) {
		List<String> ipList = page.getHtml().xpath("//table[@id='ip_list']/tbody/tr").all();
		List<ProxyIp> result = new ArrayList<>();

		if (ipList != null && ipList.size() > 0) {
			ipList.remove(0); // 移除表头
			for (String tmp : ipList) {
				Html html = Html.create(tmp);
				ProxyIp proxyIp = new ProxyIp();
				String[] data = html.xpath("//body/text()").toString().trim().split("\\s+");
				// 初步筛选 筛选https 速度小于2秒的
				String ipType = data[3];
				String ipSpeed = html.xpath("//div[@class='bar']/@title").toString();
				ipSpeed = ipSpeed.substring(0, ipSpeed.indexOf('秒'));
				double Speed = Double.parseDouble(ipSpeed);
				proxyIp.setIp(data[0]);
				proxyIp.setPort(Integer.valueOf(data[1]));
				System.out.println(proxyIp.getIp() + "  " + proxyIp.getPort());
				if (ipType.equals("HTTPS") && Speed <= 2.0) {
					// 质量ip检验
					if (IPCheckUtil.checkValidIP(proxyIp.getIp(), proxyIp.getPort())) {
						System.out.println("有效ip"+proxyIp.getIp() + "  " + proxyIp.getPort());
						result.add(proxyIp);
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
