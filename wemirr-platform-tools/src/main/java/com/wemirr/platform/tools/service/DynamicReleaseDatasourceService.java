package com.wemirr.platform.tools.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDatasource;
import com.zaxxer.hikari.HikariDataSource;

import java.util.List;

/**
 * @author Levin
 */
public interface DynamicReleaseDatasourceService extends SuperService<DynamicReleaseDatasource> {

    /**
     * ping 数据库连接
     *
     * @param id 数据连接ID
     * @return Ping 结果
     */
    HikariDataSource ping(Long id);

    /**
     * 根据连接池ID信息获取 表
     *
     * @param id ID
     * @return 表名
     */
    List<String> tables(Long id);
}
