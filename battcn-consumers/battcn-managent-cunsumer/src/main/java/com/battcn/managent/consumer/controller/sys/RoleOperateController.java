package com.battcn.managent.consumer.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.battcn.managent.consumer.controller.BaseController;
import com.battcn.managent.consumer.util.ApiResult;
import com.battcn.system.facade.RoleOperateService;
import com.battcn.system.pojo.po.RoleOperate;
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

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private RoleOperateService roleOperateService;

    @GetMapping("{roleId}")
    @ResponseBody
    public ApiResult<List<RoleOperate>> listOperate(@PathVariable Integer roleId) {
        return ApiResult.getSuccess(this.roleOperateService.listRoleOperateByRoleId(roleId));
    }

    @PostMapping("permissions")
    @ResponseBody
    public void savePermissions(Integer[] operateId, Integer roleId) {
        logger.debug("[数据] - [{}] - [{}]", JSON.toJSONString(operateId), roleId);
        this.roleOperateService.batchInsertRoleOperate(operateId, roleId);
    }

}
