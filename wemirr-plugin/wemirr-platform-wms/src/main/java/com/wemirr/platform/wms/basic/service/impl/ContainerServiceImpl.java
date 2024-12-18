package com.wemirr.platform.wms.basic.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.wms.basic.domain.entity.Container;
import com.wemirr.platform.wms.basic.domain.entity.ContainerLog;
import com.wemirr.platform.wms.basic.domain.req.ContainerOccupyReleaseReq;
import com.wemirr.platform.wms.basic.domain.req.ContainerSaveReq;
import com.wemirr.platform.wms.basic.mapper.ContainerLogMapper;
import com.wemirr.platform.wms.basic.mapper.ContainerMapper;
import com.wemirr.platform.wms.basic.service.ContainerService;
import com.wemirr.platform.wms.inbound.domain.enums.ContainerStatus;
import com.wemirr.platform.wms.inbound.domain.enums.WmsSequence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 容器表 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Service
@AllArgsConstructor
public class ContainerServiceImpl extends SuperServiceImpl<ContainerMapper, Container> implements ContainerService {

    private final ContainerLogMapper containerLogMapper;
    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext context;

    @Override
    public void create(ContainerSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<Container>lbQ().eq(Container::getCode, req.getCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该容器编号已存在");
        }
        var bean = BeanUtilPlus.toBean(req, Container.class);
        this.baseMapper.insert(bean);
    }

    @Override
    public void modify(Long id, ContainerSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<Container>lbQ().ne(Container::getId, id).eq(Container::getCode, req.getCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("该容器编号已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, Container.class);
        this.baseMapper.updateById(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void occupyOrRelease(List<ContainerOccupyReleaseReq> reqList) {
        List<ContainerLog> saveLogs = new ArrayList<>();
        List<ContainerLog> updateLogs = new ArrayList<>();
        for (ContainerOccupyReleaseReq req : reqList) {
            if (ContainerStatus.OCCUPY == req.getStatus()) {
                // 占用
                String taskNo = sequenceHelper.generate(WmsSequence.CONTAINER_TASK_NO, context.tenantId());
                ContainerLog containerLog = ContainerLog.builder()
                        .containerId(req.getContainerId())
                        .occupationTaskType(req.getOccupationTaskType())
                        .status(ContainerStatus.OCCUPY)
                        .taskNo(taskNo)
                        .docId(req.getDocId())
                        .docItemId(req.getDocItemId())
                        .build();
                saveLogs.add(containerLog);
            } else {
                // 释放或置为异常容器
                ContainerLog containerLog = containerLogMapper.selectOne(Wraps.<ContainerLog>lbQ()
                        .eq(ContainerLog::getContainerId, req.getContainerId())
                        .eq(ContainerLog::getStatus, ContainerStatus.OCCUPY)
                        .eq(ContainerLog::getDocId, req.getDocId())
                        .eq(ContainerLog::getDocItemId, req.getDocItemId()));
                if (containerLog == null) {
                    continue;
                }
                updateLogs.add(ContainerLog.builder().id(containerLog.getId()).status(req.getStatus()).build());
            }
        }
        if (CollUtil.isNotEmpty(saveLogs)) {
            containerLogMapper.insertBatchSomeColumn(saveLogs);
        }
        if (CollUtil.isNotEmpty(updateLogs)) {
            containerLogMapper.updateBatchSomeColumnById(updateLogs);
        }
    }


}
