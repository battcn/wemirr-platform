package com.wemirr.platform.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableColumnPageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableColumnSaveReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTableColumnPageResp;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTableColumn;

import java.util.Collection;
import java.util.List;

/**
 * 数据表字段管理服务接口
 * 提供对数据库表字段的批量导入、列表查询、创建、修改等功能。
 *
 * @author xiao1
 * @since 2024-12
 */
public interface GenerateTableColumnService extends SuperService<GenerateTableColumn> {

    /**
     * 批量插入字段记录（部分列）
     *
     * @param entityList 字段实体集合 {@link Collection<GenerateTableColumn>}
     * @return 插入成功的条数
     */
    int insertBatchSomeColumn(Collection<GenerateTableColumn> entityList);

    /**
     * 根据表名获取对应的所有字段列表
     *
     * @param name 表名称
     * @return 字段列表 {@link List<GenerateTableColumn>}
     */
    List<GenerateTableColumn> listByTableName(String name);

    /**
     * 分页查询字段列表
     *
     * @param req 查询条件封装对象 {@link GenerateTableColumnPageReq}
     * @return 返回分页结果 {@link IPage< GenerateTableColumnPageResp >}
     */
    IPage<GenerateTableColumnPageResp> pageList(GenerateTableColumnPageReq req);

    /**
     * 创建新的字段配置
     *
     * @param req 字段保存请求数据传输对象 {@link GenerateTableColumnSaveReq}
     */
    void create(GenerateTableColumnSaveReq req);

    /**
     * 修改指定ID的字段配置
     *
     * @param id 要修改的字段ID
     * @param req 字段更新请求数据传输对象 {@link GenerateTableColumnSaveReq}
     */
    void modify(Long id, GenerateTableColumnSaveReq req);

    /**
     * 批量修改字段配置
     *
     * @param req 字段更新请求数据传输对象列表 {@link List<GenerateTableColumnSaveReq>}
     */
    void batchModify(List<GenerateTableColumnSaveReq> req);
}