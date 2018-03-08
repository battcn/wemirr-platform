package com.battcn.managent.consumer.controller.sys;

import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.managent.consumer.annotation.BattcnLog;
import com.battcn.managent.consumer.controller.BaseController;
import com.battcn.system.facade.MenuService;
import com.battcn.system.facade.RoleService;
import com.battcn.system.pojo.po.Role;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/sys/role")
@ApiIgnore
public class RoleController extends BaseController {

    private final RoleService roleService;
    private final MenuService menuService;

    public RoleController(RoleService roleService, MenuService menuService) {
        this.roleService = roleService;
        this.menuService = menuService;
    }


    @GetMapping("/list")
    public String list() {
        return "sys/role/list";
    }

    @GetMapping("permissions")
    public String permissions(Integer roleId) {
        request.setAttribute("permissions", this.menuService.listPermissions());
        request.setAttribute("roleId", roleId);
        return "sys/role/permissions";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id) {
        if (id != null) {
            request.setAttribute("dto", Optional.ofNullable(this.roleService.selectById(id)).orElseThrow(() -> notFound("未找到记录")));
        }
        return "sys/role/edit";
    }

    @GetMapping(value = "/query")
    @ResponseBody
    public PageInfo<Role> query(DataGrid grid, String name, String account) {
        return this.roleService.listForDataGrid(grid);
    }

    @BattcnLog(module = "角色管理", methods = "保存角色", description = "添加/修改角色信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public void save(Role dto) {
        /*dto.setGmtModified(new Date());
        if (dto.getId() != null) {
            return ApiResult.getResponse(this.roleService.updateSelectiveById(dto));
        }
        return ApiResult.getResponse(this.roleService.insertSelective(dto));*/
    }

    @BattcnLog(module = "角色管理", methods = "移除角色", description = "删除角色信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public void del(Integer[] ids) {
        this.roleService.deleteRoleAndOperate(ids);
        //return ApiResult.SUCCESS;
    }

}
