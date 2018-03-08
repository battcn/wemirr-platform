package com.battcn.managent.consumer.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.framework.exception.CustomException;
import com.battcn.managent.consumer.util.SessionUtil;
import com.battcn.system.facade.MenuService;
import com.battcn.system.facade.OperateService;
import com.battcn.system.pojo.po.Menu;
import com.battcn.system.pojo.po.Operate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

/**
 * @author Levin
 */
@Api(value = "入口请求", description = "请求入口", tags = "1.0")
@Controller
public class IndexController extends BaseController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", check = false)
    private MenuService menuService;
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", check = false)
    private OperateService operateService;


    @ApiIgnore
    @GetMapping(value = {"/index"})
    public String index(Model model) {
        Integer roleId = SessionUtil.getSession().getRoleId().intValue();
        model.addAttribute("trees", menuService.listTree(roleId));
        return "index";
    }

    @ApiIgnore
    @GetMapping(value = "denied")
    public String denied() {
        return DENIED;
    }

    @ApiIgnore
    @GetMapping(value = "main")
    public String main() {
        return PATH_MAIN;
    }

    @RequestMapping("op_{oper}_{menuId}")
    @ApiOperation(value = "根据oper与id转发到指定的地址", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 404, message = "地址错误"),
            @ApiResponse(code = 500, message = "系统错误,请联系管理人员")})
    public String forward(@PathVariable String oper, @PathVariable Integer menuId, Model model) {
        if (SessionUtil.getSession() == null) {
            return "redirect:/denied";
        }
        Menu menu = Optional.ofNullable(this.menuService.selectById(menuId)).orElseThrow(() -> CustomException.notFound("无访问权限"));
        Integer roleId = SessionUtil.getSession().getRoleId().intValue();
        List<Operate> op = Optional.ofNullable(this.operateService.listOperateByRoleIdAndMenuId(roleId, menuId)).orElseThrow(() -> CustomException.forbidden("无权限"));
        if (CollectionUtils.isEmpty(op)) {
            return "redirect:/denied";
        }
        model.addAttribute("operates", op);
        model.addAttribute("OP", op.get(0));
        // 获取对应的菜单对象
        model.addAttribute("MENU", menu);
        String forward = String.format("forward:%s/%s", menu.getChannel(), oper);
        logger.info("[转发地址] - [{}]", forward);
        return forward;
    }

}
