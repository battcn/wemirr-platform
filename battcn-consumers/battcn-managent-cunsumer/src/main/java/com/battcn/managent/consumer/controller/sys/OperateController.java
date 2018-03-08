package com.battcn.managent.consumer.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.managent.consumer.annotation.BattcnLog;
import com.battcn.managent.consumer.controller.BaseController;
import com.battcn.system.facade.MenuService;
import com.battcn.system.facade.OperateService;
import com.battcn.system.pojo.dto.OperateDto;
import com.battcn.system.pojo.po.Operate;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.battcn.framework.exception.CustomException.notFound;


/**
 * @author Levin
 */
@Controller
@RequestMapping("/sys/operate")
@ApiIgnore
public class OperateController extends BaseController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private MenuService menuService;
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private OperateService operateService;

    @GetMapping("/list")
    public String list() {
        return "sys/operate/list";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id, HttpServletRequest request) {
        if (id != null) {
            request.setAttribute("dto", Optional.ofNullable(this.operateService.selectById(id)).orElseThrow(() -> notFound("该数据已失效")));
        }
        request.setAttribute("menus", this.menuService.listMenu());
        return "sys/operate/edit";
    }

    @GetMapping(value = "/query")
    @ResponseBody
    public PageInfo<OperateDto> query(DataGrid grid) {
        return this.operateService.listOperateByPage(grid);
    }

    @BattcnLog(module = "操作管理", methods = "保存操作", description = "添加/修改操作信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public void save(Operate dto) {
        /*if (dto != null && dto.getId() != null) {
            return ApiResult.getResponse(this.operateService.updateSelectiveById(dto));
        }
        return ApiResult.getResponse(this.operateService.insertSelective(dto));*/
    }

    @BattcnLog(module = "操作管理", methods = "移除操作", description = "删除操作信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public void del(Integer[] ids) {
        Lists.newArrayList(ids).forEach(this.operateService::deleteById);
        //return ApiResult.SUCCESS;
    }

}
