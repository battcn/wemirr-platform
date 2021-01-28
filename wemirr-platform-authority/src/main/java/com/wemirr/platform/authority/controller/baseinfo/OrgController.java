package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.annotation.SysLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.OrgSaveDTO;
import com.wemirr.platform.authority.domain.entity.Org;
import com.wemirr.platform.authority.service.OrgService;
import com.wemirr.platform.authority.util.TreeUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wemirr.framework.commons.entity.Result.success;


/**
 * 组织架构
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/org")
@RequiredArgsConstructor
@Tag(name = "组织架构", description = "组织架构")
public class OrgController {

    private final OrgService orgService;

    /**
     * 查询系统所有的组织树
     */
    @GetMapping("/trees")
    @Operation(summary = "查询系统所有的组织树", description = "查询系统所有的组织树")
    public Result<List<Org>> tree(String name, Boolean status) {
        List<Org> list = this.orgService.list(Wraps.<Org>lbQ()
                .like(Org::getLabel, name).eq(Org::getStatus, status)
                .orderByAsc(Org::getSequence));
        return Result.success(TreeUtils.buildTree(list));
    }

    @PostMapping
    @SysLog(value = "保存组织架构")
    @Operation(summary = "保存编辑组织架构")
    public Result<ResponseEntity<Void>> save(@Validated @RequestBody OrgSaveDTO dto) {
        final Org bean = BeanUtil.toBean(dto, Org.class);
        orgService.save(bean);
        return success();
    }

    @PutMapping("/{id}")
    @SysLog(value = "编辑组织架构")
    @Operation(summary = "编辑编辑组织架构")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody OrgSaveDTO dto) {
        final Org bean = BeanUtil.toBean(dto, Org.class);
        bean.setId(id);
        orgService.updateById(bean);
        return success();
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除组织架构")
    @Operation(summary = "删除组织架构")
    public Result<ResponseEntity<Void>> del(@PathVariable Long id) {
        orgService.removeById(id);
        return success();
    }


}
