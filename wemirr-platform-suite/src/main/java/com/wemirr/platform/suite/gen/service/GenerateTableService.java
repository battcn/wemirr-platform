package com.wemirr.platform.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTableDetailRep;
import com.wemirr.platform.suite.gen.domain.dto.rep.GenerateTablePageRep;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTablePageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableSaveReq;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiao1
 * @since 2024-12
 */
public interface GenerateTableService extends SuperService<GenerateTable> {
    List<GenerateTable> getGenTableListFromDs(String tableName) throws Exception;

    void importToGenTable(List<String> tableNames);

    IPage<GenerateTablePageRep> pageList(GenerateTablePageReq req);

    GenerateTableDetailRep detail(Long id);

    void modify(Long id, GenerateTableSaveReq req);

    void removeGen(Long id);

    Map<String, String> previewCode(Long id);

    void generate(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
