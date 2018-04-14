package com.battcn.management.consumer.controller.crawler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.webmagic.downloader.CrawlerDownloader;
import com.battcn.management.consumer.annotation.BattcnLog;
import com.battcn.management.consumer.util.ApiResult;
import com.battcn.management.consumer.webmagic.pageprocessor.ProxyProcessor;
import com.battcn.management.consumer.webmagic.pipeline.ProxyPipeline;
import com.battcn.system.facade.ProxyPoolService;
import com.battcn.system.pojo.po.ProxyPool;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.Executors;


/**
 * @author Levin
 */
@Controller
@RequestMapping("/crawler/proxies")
public class ProxyPoolController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private ProxyPoolService proxyPoolService;

    private final ProxyPipeline proxyPipeline;

    @Autowired
    public ProxyPoolController(ProxyPipeline proxyPipeline) {
        this.proxyPipeline = proxyPipeline;
    }


    @GetMapping(value = "/list")
    @BattcnLog(description = "进入代理池页", module = "代理池", method = "代理池")
    @ApiOperation(value = "跳转IP代理池", hidden = true)
    public String list() {
        return "crawler/proxy/list";
    }

    @GetMapping(value = "/query")
    @ResponseBody
    @ApiOperation(value = "根据分页条件查询分页的结果集", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageInfo<ProxyPool> query(DataGrid grid) {
        return this.proxyPoolService.listForDataGrid(grid);
    }

    @PostMapping("/crawler")
    @ResponseBody
    public ApiResult<String> crawler() {
        Executors.newSingleThreadExecutor().execute(() -> Spider.create(new ProxyProcessor()).setDownloader(new CrawlerDownloader()).addUrl("http://lab.crossincode.com/proxy/", "http://www.xicidaili.com/nn", "http://www.ip181.com/").addPipeline(proxyPipeline).thread(10).run());
        return ApiResult.getSuccess("已进入后台处理,先干点别的吧");
    }

}
