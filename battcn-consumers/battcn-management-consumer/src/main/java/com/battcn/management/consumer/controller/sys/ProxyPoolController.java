package com.battcn.management.consumer.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.management.consumer.annotation.BattcnLog;
import com.battcn.system.facade.ProxyPoolService;
import com.battcn.system.pojo.po.ProxyPool;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Levin
 */
@Controller
@RequestMapping("/sys/proxy_pool")
public class ProxyPoolController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private ProxyPoolService proxyPoolService;


    @GetMapping(value = "/list")
    @BattcnLog(description = "进入代理池页", module = "代理池", methods = "代理池")
    @ApiOperation(value = "跳转IP代理池", hidden = true)
    public String list() {
        return "sys/proxy_pool/list";
    }

    @RequestMapping(value = "/query")
    @ResponseBody
    @ApiOperation(value = "根据分页条件查询分页的结果集", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageInfo<ProxyPool> query(DataGrid grid) {
        return this.proxyPoolService.listForDataGrid(grid);
    }

}
