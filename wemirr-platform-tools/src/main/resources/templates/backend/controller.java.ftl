package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.db.page.PageRequest;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@AllArgsConstructor
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>manager/${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final ${entity}Service ${table.entityPath}Service;

    /**
     * 分页查询
     */
    @GetMapping
    public Result<Page<${entity}>> get${entity}Page(PageRequest pageRequest) {
        return Result.success(${table.entityPath}Service.page(pageRequest.buildPage()));
    }

    /**
     * 通过id查询${table.comment!}
     *
     * @param id id
     * @return Ret
     */
    @GetMapping("{id}")
    public Result<${entity}> getById(@PathVariable("id") Long id) {
        return Result.success(${table.entityPath}Service.getById(id));
    }

    /**
     * 新增${table.comment!}
     *
     * @param ${table.entityPath} ${table.comment!}
     * @return Result
     */
    @PostMapping
    <#-- @PreAuthorize("@pms.hasPermission('${package.moduleName}:${table.entityPath}:add')")-->
    public Result<?> save(@RequestBody ${entity} ${table.entityPath}) {
        ${table.entityPath}Service.save(${table.entityPath});
        return Result.success();
    }

    /**
     * 修改${table.comment!}
     *
     * @param ${table.entityPath} ${table.comment!}
     * @return Ret
     */
    @PutMapping("/{id}")
    <#-- @PreAuthorize("@pms.hasPermission('${package.ModuleName}:${table.entityPath}:edit')")-->
    public Result<?> updateById(@PathVariable("id") Long id ,@RequestBody ${entity} ${table.entityPath}) {
        return Result.success(${table.entityPath}Service.updateById(${table.entityPath}));
    }

    /**
     * 通过id删除${table.comment!}
     *
     * @param id id
     * @return Result
     */
    @DeleteMapping("{id}")
    <#-- @PreAuthorize("@pms.hasPermission('${package.ModuleName}:${table.entityPath}:del')")-->
    public Result<?> removeById(@PathVariable Long id) {
        ${table.entityPath}Service.removeById(id);
        return Result.success();
    }

}
</#if>




