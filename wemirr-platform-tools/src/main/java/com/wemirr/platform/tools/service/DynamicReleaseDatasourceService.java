package com.wemirr.platform.tools.service;

import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDatasource;
import com.zaxxer.hikari.HikariDataSource;

import java.util.List;

/**
 * @author Levin
 */
public interface DynamicReleaseDatasourceService extends SuperService<DynamicReleaseDatasource> {


    HikariDataSource ping(Long id);

    List<String> tables(Long id);
}
