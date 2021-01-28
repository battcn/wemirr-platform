package ${package.Controller};

import com.veryreader.d2p.api.modules.base.vo.IdsRequestVO;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.veryreader.d2p.api.model.vo.Ret;
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
 private final  ${entity}Service ${table.entityPath}Service;

 /**
 * 分页查询
 * @param page 分页对象
 * @return
 */
 @GetMapping("/page")
 public Ret get${entity}Page(Page<${entity}> page, ${entity} query
 , @RequestParam(value = "dateRange", required = false) String dateRange
 ) {

 // setDefaultSort(page, "create_time", true);

 LambdaQueryWrapper<${entity}> wrapper = Wrappers.lambdaQuery(query);
 // betweenDateRange(wrapper, ${entity}::getCreateTime, dateRange);
 // wrapper.eq(${entity}::getDelFlag, '0');


 return Ret.success("",${table.entityPath}Service.page(page, wrapper));
 }

 /**
 * 通过id查询${table.comment!}
 * @param id id
 * @return Ret
 */
 @GetMapping("/info")
 public Ret getById(@RequestParam("id") Long id) {
 return Ret.success("",${table.entityPath}Service.getById(id));
 }

 /**
 * 新增${table.comment!}
 * @param ${table.entityPath} ${table.comment!}
 * @return Ret
 */
 @PostMapping("/add")
 @PreAuthorize("@pms.hasPermission('${package.ModuleName}:${table.entityPath}:add')")
 public Ret save(@RequestBody ${entity} ${table.entityPath}) {
  ${table.entityPath}Service.save(${table.entityPath});
  return Ret.success("",${table.entityPath}.getId());
 }

 /**
 * 修改${table.comment!}
 * @param ${table.entityPath} ${table.comment!}
 * @return Ret
 */
 @PostMapping("/update")
 @PreAuthorize("@pms.hasPermission('${package.ModuleName}:${table.entityPath}:edit')")
 public Ret updateById(@RequestBody ${entity} ${table.entityPath}) {
 return Ret.success("",${table.entityPath}Service.updateById(${table.entityPath}));
 }

 /**
 * 通过id删除${table.comment!}
 * @param id id
 * @return Ret
 */
 @PostMapping("/delete")
 @PreAuthorize("@pms.hasPermission('${package.ModuleName}:${table.entityPath}:del')")
 public Ret removeById(@RequestParam Long id) {
  return Ret.success("",${table.entityPath}Service.removeById(id));
 }


 /**
 * 批量删除${table.comment!}
 * @param body ids
 * @return Ret
 */
 @PostMapping("/batchDelete")
 @PreAuthorize("@pms.hasPermission('${package.ModuleName}:${table.entityPath}:del')")
 public Ret removeByIds(@RequestBody IdsRequestVO body) {
  return Ret.success("",${table.entityPath}Service.removeByIds(body.getIds()));
 }

}
</#if>




