package com.wemirr.platform.workflow.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.workflow.domain.dto.req.DefinitionDeployReq;
import com.wemirr.platform.workflow.domain.dto.req.DefinitionPageReq;
import com.wemirr.platform.workflow.domain.dto.req.FormDesignSaveReq;
import com.wemirr.platform.workflow.domain.dto.req.InstanceStartReq;
import com.wemirr.platform.workflow.domain.dto.resp.*;
import com.wemirr.platform.workflow.domain.entity.FlowCategory;
import com.wemirr.platform.workflow.domain.entity.FlowModelForm;
import com.wemirr.platform.workflow.domain.entity.InstanceExt;
import com.wemirr.platform.workflow.feign.domain.enums.ApprovalStatus;
import com.wemirr.platform.workflow.feign.domain.enums.DefId2Tag;
import com.wemirr.platform.workflow.feign.domain.req.WorkflowStartReq;
import com.wemirr.platform.workflow.feign.domain.resp.InstanceStartResp;
import com.wemirr.platform.workflow.repository.FlowCategoryMapper;
import com.wemirr.platform.workflow.repository.InstanceExtMapper;
import com.wemirr.platform.workflow.repository.ProcessModelFormMapper;
import com.wemirr.platform.workflow.repository.WorkflowMapper;
import com.wemirr.platform.workflow.service.DefExtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.dto.DefJson;
import org.dromara.warm.flow.core.dto.FlowParams;
import org.dromara.warm.flow.core.entity.Definition;
import org.dromara.warm.flow.core.service.DefService;
import org.dromara.warm.flow.core.service.InsService;
import org.dromara.warm.flow.orm.entity.FlowDefinition;
import org.dromara.warm.flow.orm.mapper.FlowDefinitionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 流程定义扩展服务
 * <p>
 * 提供流程定义的部署、发布、启动等扩展功能
 *
 * @author Levin
 * @since 2025-05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefExtServiceImpl implements DefExtService {

    private final AuthenticationContext context;
    private final DefService defService;
    private final InsService insService;
    private final InstanceExtMapper instanceExtMapper;
    private final WorkflowMapper workflowMapper;
    private final FlowCategoryMapper flowCategoryMapper;
    private final ProcessModelFormMapper processModelFormMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkflowDefinitionResp deploy(DefinitionDeployReq req) {
        Definition definition = defService.importJson(req.getDefConfigJson());
        return WorkflowDefinitionResp.builder().definitionId(definition.getId()).flowCode(definition.getFlowCode()).build();
    }


    /**
     * 发布流程
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publish(Long id) {
        defService.publish(id);
    }

    @Override
    public DefJson detail(Long id) {
        return defService.queryDesign(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public InstanceStartResp startFlow(WorkflowStartReq req) {
        // 增加逻辑判断,根据业务编码查询是否存在流程实例了,若已存在流程实例,则获取流程实例的任务进行审批,
        var instance = insService.start(req.getBusinessId(), FlowParams.build().variable(req.getVariables())
                .flowCode(req.getFlowCode()));
        if (instance == null) {
            throw CheckedException.notFound("流程启动失败");
        }
        var definition = Optional.ofNullable(defService.getById(instance.getDefinitionId())).orElseThrow(() -> CheckedException.notFound("流程定义创建失败"));
        // 同步新增流程实例扩展信息
        var instanceExt = InstanceExt.builder().operationType(req.getAction())
                .businessType(DefId2Tag.ofBusinessType(definition.getFlowCode()))
                .instanceId(instance.getId()).businessId(req.getBusinessId()).businessCode(req.getBusinessCode())
                .title(req.getTitle()).keyword(req.getKeyword()).remark(req.getRemark()).attachment(req.getAttachment()).build();
        this.instanceExtMapper.insert(instanceExt);
        return InstanceStartResp.builder().flowStatus(ApprovalStatus.of(instance.getFlowStatus())).instanceId(instance.getId()).build();
    }

    @Override
    public IPage<FlowDefinitionPageResp> pageList(DefinitionPageReq req) {
        return workflowMapper.selectDefinitionPageList(req.buildPage(), req);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unPublish(Long id) {
        this.defService.unPublish(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        this.defService.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void copyDef(Long id) {
        this.defService.copyDef(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void active(Long id) {
        this.defService.active(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unActive(Long id) {
        this.defService.unActive(id);
    }

    @Override
    public Map<String, Object> exportJson(Long id) {
        return JacksonUtils.readValue(defService.exportJson(id), new TypeReference<>() {
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importDef(InputStream inputStream) {
        defService.importIs(inputStream);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFormDesign(Long id, FormDesignSaveReq req) {
        final FlowModelForm modelForm = this.processModelFormMapper.selectOne(FlowModelForm::getModelId, id);
        String schemasJson = JacksonUtils.toJson(req.getSchemas());
        if (modelForm == null) {
            this.processModelFormMapper.insert(FlowModelForm.builder()
                    .modelId(id)
                    .formSchemas(schemasJson)
                    .formScript(req.getScript())
                    .build());
        } else {
            modelForm.setFormSchemas(schemasJson);
            modelForm.setFormScript(req.getScript());
            this.processModelFormMapper.updateById(modelForm);
        }
    }

    @Override
    public DesignModelFormResp findFormDesign(Long id) {
        final FlowModelForm modelForm = this.processModelFormMapper.selectOne(FlowModelForm::getModelId, id);
        if (modelForm == null) {
            return null;
        }
        return DesignModelFormResp.builder()
                .modelId(modelForm.getModelId())
                .schemas(JacksonUtils.readValue(modelForm.getFormSchemas(), new TypeReference<>() {
                }))
                .script(modelForm.getFormScript())
                .build();
    }

    @Override
    public List<DesignModelGroupListResp> groupList() {
        var list = this.flowCategoryMapper.selectList(FlowCategory::getStatus, 1);
        if (CollUtil.isEmpty(list)) {
            return List.of();
        }
        var definitionMapper = SpringUtil.getBean(FlowDefinitionMapper.class);
        final List<FlowDefinition> modelList = definitionMapper.selectList(Wraps.<FlowDefinition>lbQ().eq(FlowDefinition::getIsPublish, 1));
        final Map<String, List<FlowDefinition>> map = modelList.stream().collect(Collectors.groupingBy(FlowDefinition::getCategory));
        return list.stream().map(category -> {
            final List<DesignModelListResp> designModelList = Optional.ofNullable(map.get(String.valueOf(category.getId()))).orElseGet(List::of)
                    .stream()
                    .map(x -> DesignModelListResp.builder()
                            .id(x.getId()).definitionId(x.getId()).diagramName(x.getFlowName()).diagramIcon(category.getIcon()).build())
                    .toList();
            return DesignModelGroupListResp.builder().categoryId(category.getId()).categoryName(category.getName()).modelList(designModelList).build();
        }).toList();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public InstanceStartResp startInstance(Long id, InstanceStartReq req) {
        var definition = Optional.ofNullable(defService.getById(id)).orElseThrow(() -> CheckedException.notFound("流程定义不存在"));
        if (definition.getIsPublish() != 1) {
            throw CheckedException.badRequest("模型未部署");
        }
        // 设置流程参数
        Map<String, Object> variables = new LinkedHashMap<>(req.getFormData());
        variables.put("ext.instanceName", req.getInstanceName());
        variables.put("ext.businessKey", req.getBusinessKey());
        variables.put("ext.businessGroup", req.getBusinessGroup());
        variables.put("ext.formData", req.getFormData());
        variables.put("approverName", context.nickName());
        long startTime = System.currentTimeMillis();
        // 增加逻辑判断,根据业务编码查询是否存在流程实例了,若已存在流程实例,则获取流程实例的任务进行审批,
        var instance = insService.start(req.getBusinessKey(), FlowParams.build().variable(variables).flowCode(definition.getFlowCode()));
        if (instance == null) {
            throw CheckedException.notFound("流程启动失败");
        }
        var form = Optional.ofNullable(processModelFormMapper.selectOne(FlowModelForm::getModelId, id)).orElseGet(FlowModelForm::new);
        // 同步新增流程实例扩展信息
        var instanceExt = InstanceExt.builder().operationType("other")
                .businessType(DefId2Tag.ofBusinessType(definition.getFlowCode()))
                .instanceId(instance.getId()).businessId(req.getBusinessKey()).businessCode(req.getBusinessKey())
                .title(definition.getFlowName())
                .formData(req.getFormData() == null ? null : JacksonUtils.toJson(req.getFormData()))
                .formSchemas(form.getFormSchemas())
                .formScript(form.getFormScript())
                .build();
        this.instanceExtMapper.insert(instanceExt);
        log.debug("start instance 耗时 => {}", (System.currentTimeMillis() - startTime));
        return InstanceStartResp.builder().flowStatus(ApprovalStatus.of(instance.getFlowStatus())).instanceId(instance.getId()).build();
    }

}
