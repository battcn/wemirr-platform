package ${package}.${moduleName}.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import ${package}.${moduleName}.domain.dto.req.${ClassName}PageReq;
import ${package}.${moduleName}.domain.dto.req.${ClassName}SaveReq;
import ${package}.${moduleName}.domain.dto.resp.${ClassName}PageResp;
import ${package}.${moduleName}.domain.dto.resp.${ClassName}DetailResp;
import ${package}.${moduleName}.domain.entity.${ClassName};

public interface ${ClassName}Service extends SuperService<${ClassName}>{

/**
* 分页查询
* @param req 分页请求参数
* @return 分页结果
*/
IPage<${ClassName}PageResp> pageList(${ClassName}PageReq req);

 /**
 * 通过id查询详情
 * @param id 实体ID
 * @return 详情响应
 */
 ${ClassName}DetailResp detail(Long id);

 /**
 * 新增实体
 * @param req 新增请求参数
 */
 void create(${ClassName}SaveReq req);

 /**
 * 修改实体
 * @param id 实体ID
 * @param req 修改请求参数
 */
 void modify(Long id, ${ClassName}SaveReq req);

 /**
 * 通过id删除实体
 * @param id 实体ID
 */
 void delete(Long id);
 }