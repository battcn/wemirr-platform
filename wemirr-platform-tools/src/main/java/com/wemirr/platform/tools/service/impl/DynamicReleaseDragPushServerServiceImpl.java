package com.wemirr.platform.tools.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDrag;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServer;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServerModel;
import com.wemirr.platform.tools.domain.req.BatchKey;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerModelResp;
import com.wemirr.platform.tools.mapper.DynamicReleaseDragMapper;
import com.wemirr.platform.tools.mapper.DynamicReleaseDragPushServerMapper;
import com.wemirr.platform.tools.mapper.DynamicReleaseDragPushServerModelMapper;
import com.wemirr.platform.tools.service.DynamicReleaseDragPushServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class DynamicReleaseDragPushServerServiceImpl extends SuperServiceImpl<DynamicReleaseDragPushServerMapper, DynamicReleaseDragPushServer> implements DynamicReleaseDragPushServerService {

    private final DynamicReleaseDragPushServerModelMapper dynamicReleaseDragPushServerModelMapper;
    private final DynamicReleaseDragMapper dynamicReleaseDragMapper;

    @Override
    public DynamicReleaseDragPushServerModelResp queryModelByPushId(Long pushId) {
        final DynamicReleaseDragPushServerModelResp.DynamicReleaseDragPushServerModelRespBuilder builder = DynamicReleaseDragPushServerModelResp.builder();
        final List<DynamicReleaseDrag> dynamicReleaseDrags = this.dynamicReleaseDragMapper.selectList(Wraps.lbQ());
        if (CollectionUtil.isNotEmpty(dynamicReleaseDrags)) {
            builder.modelList(dynamicReleaseDrags.stream().map(drag -> DynamicReleaseDragPushServerModelResp.DynamicReleaseDragModel.builder()
                    .id(drag.getId()).label(drag.getLabel()).build()).collect(Collectors.toList()));
        }
        final List<DynamicReleaseDragPushServerModel> list = dynamicReleaseDragPushServerModelMapper.selectList(Wraps.<DynamicReleaseDragPushServerModel>lbQ()
                .eq(DynamicReleaseDragPushServerModel::getPushId, pushId));
        if (CollectionUtil.isNotEmpty(list)) {
            builder.checkedModels(list.stream().map(DynamicReleaseDragPushServerModel::getDragId).collect(Collectors.toList()));
        }
        return builder.build();
    }

    @Override
    @DSTransactional
    public void binding(Long id, BatchKey<Long> ids) {
        this.dynamicReleaseDragPushServerModelMapper.deleteById(id);
        if (ids == null || CollectionUtil.isEmpty(ids.getIds())) {
            return;
        }
        for (Long dragId : ids.getIds()) {
            this.dynamicReleaseDragPushServerModelMapper.insert(DynamicReleaseDragPushServerModel.builder()
                    .pushId(id).dragId(dragId).build());
        }
    }
}
