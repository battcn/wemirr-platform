package com.wemirr.platform.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTablePageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableSaveReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTableDetailResp;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTablePageResp;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 数据表管理服务接口
 * 提供从数据源导入表结构、分页查询、详情查看、代码预览与生成下载等核心功能。
 *
 * @author xiao1
 * @since 2024-12
 */
public interface GenerateTableService extends SuperService<GenerateTable> {

    /**
     * 从指定的数据源中获取表结构列表
     *
     * @param tableName 可选过滤表名
     * @return 表结构列表 {@link List<GenerateTable>}
     * @throws Exception 获取过程中可能抛出异常
     */
    List<GenerateTable> getGenTableListFromDs(String tableName) throws Exception;

    /**
     * 将指定的表导入到生成器中
     *
     * @param tableNames 要导入的表名称列表
     */
    void importToGenTable(List<String> tableNames);

    /**
     * 分页查询已导入的表结构列表
     *
     * @param req 查询条件封装对象 {@link GenerateTablePageReq}
     * @return 返回分页结果 {@link IPage< GenerateTablePageResp >}
     */
    IPage<GenerateTablePageResp> pageList(GenerateTablePageReq req);

    /**
     * 查看指定ID的表结构详情
     *
     * @param id 表结构ID
     * @return 表结构详情响应对象 {@link GenerateTableDetailResp}
     */
    GenerateTableDetailResp detail(Long id);

    /**
     * 修改指定ID的表结构信息
     *
     * @param id 要修改的表结构ID
     * @param req 表结构更新请求数据传输对象 {@link GenerateTableSaveReq}
     */
    void modify(Long id, GenerateTableSaveReq req);

    /**
     * 删除指定ID的表结构记录
     *
     * @param id 要删除的表结构ID
     */
    void removeGen(Long id);

    /**
     * 预览基于当前表结构生成的代码内容
     *
     * @param id 表结构ID
     * @return 键值对形式的代码内容映射
     */
    Map<String, String> previewCode(Long id);

    /**
     * 生成并打包下载指定表结构对应的代码文件
     *
     * @param id 表结构ID
     * @param request HTTP 请求对象
     * @param response HTTP 响应对象
     * @throws IOException 下载过程可能出现IO异常
     */
    void generate(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException;
}