package com.battcn.managent.consumer.controller.sys;

import com.alibaba.fastjson.JSON;
import com.battcn.managent.consumer.controller.BaseController;
import com.battcn.system.facade.RoleOperateService;
import com.battcn.system.pojo.po.RoleOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * @author Levin
 */
@Controller
@RequestMapping("/sys/role/operate/")
@ApiIgnore
public class RoleOperateController extends BaseController {

    private final RoleOperateService roleOperateService;

    public RoleOperateController(RoleOperateService roleOperateService) {
        this.roleOperateService = roleOperateService;
    }

    @GetMapping("{roleId}")
    @ResponseBody
    public void listOperate(@PathVariable Integer roleId) {
        //return ApiResult.getSuccess(this.roleOperateService.listRoleOperateByRoleId(roleId));
    }

    @PostMapping("permissions")
    @ResponseBody
    public void savePermissions(Integer[] operateId, Integer roleId) {
        logger.debug("[数据] - [{}] - [{}]", JSON.toJSONString(operateId), roleId);
        //return this.roleOperateService.batchInsertRoleOperate(operateId, roleId);
    }

}
