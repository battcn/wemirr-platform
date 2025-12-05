package com.wemirr.platform.ai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.ai.domain.dto.rep.ChatAgentDetailRep;
import com.wemirr.platform.ai.domain.dto.rep.ChatAgentPageRep;
import com.wemirr.platform.ai.domain.dto.req.ChatAgentPageReq;
import com.wemirr.platform.ai.domain.dto.req.ChatAgentSaveReq;
import com.wemirr.platform.ai.domain.entity.ChatAgent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 智能体服务接口
 *
 * @author xJh
 * @since 2025/11/4
 **/
public interface ChatAgentService extends SuperService<ChatAgent> {

    /**
     * 分页查询智能体
     *
     * @param req 分页查询请求
     * @return 分页结果
     */
    IPage<ChatAgentPageRep> pageList(ChatAgentPageReq req);

    /**
     * 获取智能体详情
     *
     * @param id 智能体ID
     * @return 智能体详情
     */
    ChatAgentDetailRep detail(Long id);

    /**
     * 创建智能体
     *
     * @param req 创建请求
     */
    void create(ChatAgentSaveReq req);

    /**
     * 修改智能体
     *
     * @param id  智能体ID
     * @param req 修改请求
     */
    void modify(Long id, ChatAgentSaveReq req);

    /**
     * 删除智能体
     *
     * @param id 智能体ID
     */
    void remove(Long id);

    /**
     * 根据用户ID获取智能体列表
     *
     * @param userId 用户ID
     * @return 智能体列表
     */
    List<ChatAgent> listByUserId(Long userId);


    /**
     * 检查智能体名称是否存在
     *
     * @param name   智能体名称
     * @param userId 用户ID
     * @param id     排除的智能体ID（修改时使用）
     * @return 是否存在
     */
    boolean existsByName(String name, Long userId, Long id);

    String uploadAvatar( MultipartFile file);

    ChatAgentDetailRep detailByAgentId(Long agentId);

    List<ChatAgent> listByModelId(String modelId);
}
