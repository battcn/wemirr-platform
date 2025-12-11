package ${package}.${moduleName}.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import ${package}.${moduleName}.domain.dto.req.${ClassName}PageReq;
import ${package}.${moduleName}.domain.dto.req.${ClassName}SaveReq;
import ${package}.${moduleName}.domain.dto.resp.${ClassName}PageResp;
import ${package}.${moduleName}.domain.dto.resp.${ClassName}DetailResp;
import ${package}.${moduleName}.domain.entity.${ClassName};
import ${package}.${moduleName}.repository.${ClassName}Mapper;
import ${package}.${moduleName}.service.${ClassName}Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ${ClassName}ServiceImpl extends SuperServiceImpl<${ClassName}Mapper, ${ClassName}> implements ${ClassName}Service {

private final ${ClassName}Mapper ${businessName}Mapper;

@Override
public IPage<${ClassName}PageResp> pageList(${ClassName}PageReq req) {
 return this.baseMapper.selectPage(req.buildPage(), null)
 .convert(x -> BeanUtil.toBean(x, ${ClassName}PageResp.class));
 }

 @Override
 public ${ClassName}DetailResp detail(Long id) {
 ${ClassName} ${businessName} = this.baseMapper.selectById(id);
 Optional.ofNullable(${businessName})
 .orElseThrow(() -> CheckedException.notFound("实体不存在"));
 return BeanUtil.toBean(${businessName}, ${ClassName}DetailResp.class);
 }

 @Override
 public void create(${ClassName}SaveReq req) {
 ${ClassName} ${businessName} = BeanUtil.toBean(req, ${ClassName}.class);
 this.baseMapper.insert(${businessName});
 }

 @Override
 public void modify(Long id, ${ClassName}SaveReq req) {
 Optional.ofNullable(this.baseMapper.selectById(id))
 .orElseThrow(() -> CheckedException.notFound("实体不存在"));
 ${ClassName} ${businessName} = BeanUtilPlus.toBean(id,req, ${ClassName}.class);
 this.baseMapper.updateById(${businessName});
 }

 @Override
 public void delete(Long id) {
 this.baseMapper.deleteById(id);
 }
 }