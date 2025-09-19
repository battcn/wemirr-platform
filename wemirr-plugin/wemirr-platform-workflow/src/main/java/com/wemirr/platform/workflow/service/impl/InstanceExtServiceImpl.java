

package com.wemirr.platform.workflow.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.workflow.domain.constant.VariableConstant;
import com.wemirr.platform.workflow.domain.dto.req.InstancePageReq;
import com.wemirr.platform.workflow.domain.dto.resp.FlowTaskApproveListResp;
import com.wemirr.platform.workflow.domain.dto.resp.InstancePageResp;
import com.wemirr.platform.workflow.domain.entity.InstanceExt;
import com.wemirr.platform.workflow.repository.InstanceExtMapper;
import com.wemirr.platform.workflow.repository.WorkflowMapper;
import com.wemirr.platform.workflow.service.InstanceExtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsFirst;
import static java.util.stream.Collectors.toList;

/**
 * 流程实例扩展信息
 *
 * @author battcn
 * @since 2025/5/28
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class InstanceExtServiceImpl extends SuperServiceImpl<InstanceExtMapper, InstanceExt> implements InstanceExtService {

    private final WorkflowMapper workflowMapper;
    private final AuthenticationContext context;


    @Override
    public IPage<InstancePageResp> mePageList(InstancePageReq req) {
        req.setUserId(String.valueOf(context.userId()));
        return workflowMapper.selectInstancePageList(req.buildPage(), req);
    }


    @Override
    public List<FlowTaskApproveListResp> allTask(Long instanceId) {
        var list = workflowMapper.selectTaskByInstanceId(instanceId);
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        handlerVariable(list);
        return list.stream()
                .sorted(comparing(FlowTaskApproveListResp::getApprovalTime, nullsFirst(reverseOrder()))
                        // 可以继续添加其他排序条件
                        .thenComparing(FlowTaskApproveListResp::getApprovalTime, reverseOrder()))
                .collect(toList());
    }

    @Override
    public IPage<InstancePageResp> pageList(InstancePageReq req) {
        return workflowMapper.selectInstancePageList(req.buildPage(), req);
    }

    private void handlerVariable(List<FlowTaskApproveListResp> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        for (FlowTaskApproveListResp task : list) {
            if (StrUtil.isBlank(task.getVariable())) {
                continue;
            }
            JSONObject variable = JSONObject.parseObject(task.getVariable());
            String approverName = variable.getString(VariableConstant.VAR_APPROVE_USER);
            if (StrUtil.isEmpty(approverName)) {
                approverName = variable.getString("user");
//                    task.setTransferApproverName(jsonObject.getString(VariableConstant.VAR_TRANSFER_APPROVE_USER));
            }
            task.setApproverName(approverName);
        }
    }
}
