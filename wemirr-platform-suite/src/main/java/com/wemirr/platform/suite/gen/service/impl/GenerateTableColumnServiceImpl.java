package com.wemirr.platform.suite.gen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableColumnPageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableColumnSaveReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTableColumnPageResp;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTableColumn;
import com.wemirr.platform.suite.gen.repository.GenerateTableColumnMapper;
import com.wemirr.platform.suite.gen.service.GenerateTableColumnService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author xiao1
 * @since 2024-12
 */
@Service
public class GenerateTableColumnServiceImpl extends SuperServiceImpl<GenerateTableColumnMapper, GenerateTableColumn> implements GenerateTableColumnService {


    @Override
    public List<GenerateTableColumn> listByTableName(String name) {
        //DO :如果针对本系统，superEntity的字段应该去除，无需modaldata里 。 已经用generate字段做判别了
        return this.list(Wraps.<GenerateTableColumn>lbQ().eq(GenerateTableColumn::getTableName, name)
                //  .eq(GenerateTableColumn::getGenerate, true)
                .orderByAsc(GenerateTableColumn::getSort));
    }

    @Override
    public IPage<GenerateTableColumnPageResp> pageList(GenerateTableColumnPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<GenerateTableColumn>lbQ()
                        .like(GenerateTableColumn::getName, req.getName())
                        .like(GenerateTableColumn::getTableName, req.getTableName())
                        .orderByAsc(GenerateTableColumn::getTableName)
                        .orderByAsc(GenerateTableColumn::getSort)
                )
                .convert(x -> BeanUtil.toBean(x, GenerateTableColumnPageResp.class));

    }

    @Override
    public void create(GenerateTableColumnSaveReq req) {
        //
    }

    @Override
    public void modify(Long id, GenerateTableColumnSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("模板不存在"));
        GenerateTableColumn generateTableColumn = BeanUtilPlus.toBean(id, req, GenerateTableColumn.class);
        this.baseMapper.updateById(generateTableColumn);
    }

    @Override
    public void batchModify(List<GenerateTableColumnSaveReq> req) {
        List<GenerateTableColumn> columns = BeanUtilPlus.toBeans(req, GenerateTableColumn.class);
        this.baseMapper.updateBatch(columns);
    }

    @Override
    public int insertBatchSomeColumn(Collection<GenerateTableColumn> entityList) {
        return this.baseMapper.insertBatchSomeColumn(entityList);
    }
}
