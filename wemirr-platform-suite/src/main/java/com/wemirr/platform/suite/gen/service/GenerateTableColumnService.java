package com.wemirr.platform.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTableColumnPageRep;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableColumnPageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableColumnSaveReq;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTableColumn;

import java.util.Collection;
import java.util.List;

/**
 * @author xiao1
 * @date 2024-12
 */
public interface GenerateTableColumnService extends SuperService<GenerateTableColumn> {

    int insertBatchSomeColumn(Collection<GenerateTableColumn> entityList);


    List<GenerateTableColumn> listByTableName(String name);

    IPage<GenerateTableColumnPageRep> pageList(GenerateTableColumnPageReq req);

    void create(GenerateTableColumnSaveReq req);

    void modify(Long id, GenerateTableColumnSaveReq req);

    void batchModify(List<GenerateTableColumnSaveReq> req);
}
