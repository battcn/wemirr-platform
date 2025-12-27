package com.wemirr.platform.ai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.domain.dto.req.McpServerConfigPageReq;
import com.wemirr.platform.ai.domain.dto.req.McpServerConfigSaveReq;
import com.wemirr.platform.ai.domain.dto.resp.McpConnectionTestRep;
import com.wemirr.platform.ai.domain.dto.resp.McpToolInfoRsep;
import com.wemirr.platform.ai.domain.entity.McpServerEntity;
import com.wemirr.platform.ai.repository.McpServerMapper;
import com.wemirr.platform.ai.service.McpConnectionManager;
import com.wemirr.platform.ai.service.McpServerConfigService;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.mcp.client.McpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xJh
 * @since 2025/12/07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class McpServerConfigServiceImpl extends SuperServiceImpl<McpServerMapper, McpServerEntity> implements McpServerConfigService {

    private final McpConnectionManager mcpConnectionManager;

    @Override
    public IPage<McpServerEntity> pageList(McpServerConfigPageReq req) {
        return this.page(req.buildPage(), Wraps.<McpServerEntity>lbQ()
                .like(McpServerEntity::getName, req.getName())
                .eq(McpServerEntity::getStatus, req.getStatus())
                .orderByDesc(McpServerEntity::getId));
    }

    @Override
    public void create(McpServerConfigSaveReq req) {
        McpServerEntity config = BeanUtil.toBean(req, McpServerEntity.class);
        this.baseMapper.insert(config);
    }

    @Override
    public void modify(Long id, McpServerConfigSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("MCP配置不存在"));
        McpServerEntity config = BeanUtilPlus.toBean(id, req, McpServerEntity.class);
        this.baseMapper.updateById(config);
        // 刷新连接缓存
        mcpConnectionManager.refreshClient(id);
    }

    @Override
    public void remove(Long id) {
        this.baseMapper.deleteById(id);
        // 关闭连接
        mcpConnectionManager.closeClient(id);
    }

    @Override
    public List<McpServerEntity> listEnabled() {
        return this.list(new LambdaQueryWrapper<McpServerEntity>()
                .eq(McpServerEntity::getStatus, true));
    }

    @Override
    public void refresh(Long id) {
        mcpConnectionManager.refreshClient(id);
    }

    @Override
    public McpConnectionTestRep testConnection(Long id) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 先关闭已有连接
            mcpConnectionManager.refreshClient(id);
            
            // 创建新连接
            McpClient client = mcpConnectionManager.getClient(id);
            
            // 获取工具列表（验证连接是否真正可用）
            List<ToolSpecification> tools = client.listTools();
            
            long responseTime = System.currentTimeMillis() - startTime;
            
            McpServerEntity config = Optional.ofNullable(this.baseMapper.selectById(id))
                    .orElseThrow(() -> CheckedException.notFound("MCP配置不存在"));
            
            return McpConnectionTestRep.builder()
                    .success(true)
                    .serverName(config.getName())
                    .toolCount(tools != null ? tools.size() : 0)
                    .responseTime(responseTime)
                    .build();
                    
        } catch (Exception e) {
            log.error("MCP连接测试失败: id={}", id, e);
            long responseTime = System.currentTimeMillis() - startTime;
            
            return McpConnectionTestRep.builder()
                    .success(false)
                    .errorMessage(e.getMessage())
                    .responseTime(responseTime)
                    .build();
        }
    }

    @Override
    public List<McpToolInfoRsep> getTools(Long id) {
        McpClient client = mcpConnectionManager.getClient(id);
        List<ToolSpecification> toolSpecs = client.listTools();
        
        if (toolSpecs == null || toolSpecs.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<McpToolInfoRsep> result = new ArrayList<>();
        for (ToolSpecification spec : toolSpecs) {
            McpToolInfoRsep toolInfo = McpToolInfoRsep.builder()
                    .name(spec.name())
                    .description(spec.description())
                    .build();
            result.add(toolInfo);
        }
        
        return result;
    }

    @Override
    public void toggleStatus(Long id, Boolean status) {
        McpServerEntity config = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("MCP配置不存在"));
        
        config.setStatus(status);
        this.baseMapper.updateById(config);
        
        // 如果禁用，关闭连接
        if (Boolean.FALSE.equals(status)) {
            mcpConnectionManager.closeClient(id);
        }
    }
}

