package com.battcn.management.consumer.controller.sys;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.framework.exception.CustomException;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.management.consumer.annotation.BattcnLog;
import com.battcn.system.facade.LogService;
import com.battcn.system.pojo.po.Log;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;


/**
 * @author Levin
 */
@Controller
@RequestMapping("/sys/log")
@ApiIgnore
public class LogController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private LogService logService;


    @RequestMapping(value = "/list")
    @BattcnLog(description = "进入日志查询的页面", module = "日志模块", methods = "日志list")
    @ApiOperation(value = "跳转日志页面", hidden = true)
    public String list() throws CustomException {
        return "sys/log/list";
    }

    @RequestMapping(value = "/query")
    @ResponseBody
    @ApiOperation(value = "根据分页条件查询分页的结果集", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 404, message = "地址错误"),
            @ApiResponse(code = 500, message = "系统错误,请联系管理人员")})
    public PageInfo<Log> query(DataGrid grid, String datetime) {
        return this.logService.listForDataGrid(grid, datetime);
    }


    /**
     * 采用网上大神的POI操作, 速度杠杠的：http://git.oschina.net/jueyue/easypoi<br>
     *
     * @param response 响应流
     * @throws Exception 异常信息
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    @ApiOperation(value = "日志导出", hidden = true)
    public void export(HttpServletResponse response) throws Exception {
        Workbook workBook = ExcelExportUtil.exportExcel(new ExportParams("系统日志", "日志详情"), Log.class, this.logService.listAll());
        String fileName = "系统日志.xlsx";
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
        response.setContentType("application/vnd.ms-excel");
        OutputStream os = response.getOutputStream();
        workBook.write(os);
        os.flush();
        os.close();
    }
}
