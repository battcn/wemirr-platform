package com.battcn.managent.consumer.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.managent.consumer.annotation.BattcnLog;
import com.battcn.managent.consumer.controller.BaseController;
import com.battcn.system.facade.MenuService;
import com.battcn.system.pojo.po.Menu;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

import static com.battcn.framework.exception.CustomException.notFound;


/**
 * @author Levin
 */
@Controller
@RequestMapping("/sys/menu")
@Api(value = "菜单管理")
@ApiIgnore
public class MenuController extends BaseController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private MenuService menuService;


    @GetMapping("/list")
    public String list(DataGrid grid) {
        request.setAttribute("page", this.menuService.listMenuForDataGrid(grid));
        return "sys/menu/list";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id) {
        if (id != null) {
            request.setAttribute("dto", Optional.ofNullable(this.menuService.selectById(id)).orElseThrow(() -> notFound("该数据已失效")));
        }
        request.setAttribute("menus", this.menuService.listMenu());
        return "sys/menu/edit";
    }

    @BattcnLog(module = "菜单管理", methods = "保存菜单", description = "添加/修改菜单信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public void save(Menu menu) {
        if (menu != null) {
            this.menuService.saveOrUpdate(menu);
        }
        //return ApiResult.SUCCESS;
    }

    @BattcnLog(module = "菜单管理", methods = "移除菜单", description = "删除菜单信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public void del(Integer[] ids) {
        Lists.newArrayList(ids).forEach(this.menuService::deleteById);
        //return ApiResult.SUCCESS;
    }


}
