package com.wemirr.platform.suite.gen.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTable;
import org.springframework.stereotype.Repository;

/**
 * 数据表持久层接口
 * 继承自 `SuperMapper<GenerateTable>`，提供对 `generate_table` 表的基本 CRUD 操作，
 * 并定义了特定业务的自定义查询方法。
 *
 * @author xiao1
 * @since 2024-12
 */
@Repository
public interface GenerateTableMapper extends SuperMapper<GenerateTable> {

}