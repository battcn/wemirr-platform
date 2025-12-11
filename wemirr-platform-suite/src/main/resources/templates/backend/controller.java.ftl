package ${package}.${moduleName}.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RequestMapping;
import ${package}.${moduleName}.domain.entity.${ClassName};
import ${package}.${moduleName}.domain.dto.resp.${ClassName}PageResp;
import ${package}.${moduleName}.domain.dto.resp.${ClassName}DetailResp;
import ${package}.${moduleName}.domain.dto.req.${ClassName}PageReq;
import ${package}.${moduleName}.domain.dto.req.${ClassName}SaveReq;
import ${package}.${moduleName}.service.${ClassName}Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;



/**
* ${table.comment!} 控制器
* @author ${author}
* @since ${date}
*/
@RestController
@AllArgsConstructor
@RequestMapping("/<#if businessName??>${businessName}</#if>")
public class ${ClassName}Controller {

private final ${ClassName}Service ${businessName}Service;

/**
* 分页查询
*/
@GetMapping("/page")
public IPage<${ClassName}PageResp> pageList(${ClassName}PageReq req) {
    return ${businessName}Service.pageList(req);
    }

    /**
    * 通过id查询 ${table.comment!}
    *
    * @param id id
    * @return ${ClassName}DetailResp
    */
    @GetMapping("/{id}")
    public ${ClassName}DetailResp detail(@PathVariable("id") Long id) {
    return ${businessName}Service.detail(id);
    }

    /**
    * 新增${table.comment!}
    *
    * @param ${businessName} ${businessName}
    */
    @PostMapping("/create")
    public void create(@RequestBody ${ClassName}SaveReq ${businessName}) {
    ${businessName}Service.create(${businessName});
    }

    /**
    * 修改
    *
    * @param id      id
    * @param ${businessName} ${businessName}
    */
    @PutMapping("/{id}/modify")
    public void modify(@PathVariable("id") Long id ,@RequestBody ${ClassName}SaveReq req) {
    ${businessName}Service.modify(id,req);
    }

    /**
    * 通过id删除${table.comment!}
    *
    * @param id id
    */
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
    ${businessName}Service.delete(id);
    }
    }




