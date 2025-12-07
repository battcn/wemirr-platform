package com.wemirr.platform.ai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.dto.rep.McpConnectionTestRep;
import com.wemirr.platform.ai.domain.dto.rep.McpToolInfoRep;
import com.wemirr.platform.ai.domain.dto.req.McpServerConfigPageReq;
import com.wemirr.platform.ai.domain.dto.req.McpServerConfigSaveReq;
import com.wemirr.platform.ai.domain.entity.McpServerConfig;

import java.util.List;

/**
 * MCP配置服务接口
 *
 * @author xJh
 * @since 2025/12/07
 */
public interface McpServerConfigService extends SuperService<McpServerConfig> {
    
    /**
     * 分页查询
     */
    IPage<McpServerConfig> pageList(McpServerConfigPageReq req);

    /**
     * 新增配置
     */
    void create(McpServerConfigSaveReq req);

    /**
     * 修改配置
     */
    void modify(Long id, McpServerConfigSaveReq req);

    /**
     * 删除配置
     */
    void remove(Long id);
    
    /**
     * 获取所有启用的配置
     * @return 启用的配置列表
     */
    List<McpServerConfig> listEnabled();

    /**
     * 刷新连接
     */
    void refresh(Long id);

    /**
     * 测试MCP连接
     */
    McpConnectionTestRep testConnection(Long id);

    /**
     * 获取MCP工具列表
     */
    List<McpToolInfoRep> getTools(Long id);

    /**
     * 切换启用状态
     */
    void toggleStatus(Long id, Boolean status);
}


