package com.wemirr.platform.workflow.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.wemirr.platform.iam.feign.UserFeign;
import com.wemirr.platform.iam.feign.domain.req.UserFeignPageReq;
import com.wemirr.platform.iam.feign.domain.resp.UserInfoResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.ui.dto.HandlerFunDto;
import org.dromara.warm.flow.ui.dto.HandlerQuery;
import org.dromara.warm.flow.ui.service.HandlerSelectService;
import org.dromara.warm.flow.ui.vo.HandlerFeedBackVo;
import org.dromara.warm.flow.ui.vo.HandlerSelectVo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 流程图处理人回写,根据审批人id 回写名称
 * 目前只接入用户维度,后续可增加角色部门
 *
 * @author battcn
 * @since 2025/8/7
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class HandlerSelectServiceImpl implements HandlerSelectService {

    private final static String DEFAULT_HANDLE_TYPE = "用户";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final UserFeign userFeign;

    @Override
    public List<String> getHandlerType() {
        return List.of(DEFAULT_HANDLE_TYPE);
    }

    @Override
    public HandlerSelectVo getHandlerSelect(HandlerQuery query) {
        if (DEFAULT_HANDLE_TYPE.equals(query.getHandlerType())) {
            return selectUserList(query);
        }
        return new HandlerSelectVo();
    }

    /**
     * 获取用户列表
     */
    private HandlerSelectVo selectUserList(HandlerQuery query) {
        var req = UserFeignPageReq.builder().username(query.getHandlerCode()).nickName(query.getHandlerName())
//                .startTime(query.getBeginTime()).endTime(query.getEndTime())
                .build();
        req.setCurrent(query.getPageNum());
        req.setSize(query.getPageSize());
        var page = userFeign.pageList(req);
        HandlerFunDto<UserInfoResp> handlerFunDto = new HandlerFunDto<>(page.getRecords(), page.getTotal())
                .setStorageId(x -> x.getId().toString())
                .setHandlerCode(UserInfoResp::getUsername)
                .setHandlerName(UserInfoResp::getNickName)
                // TODO 需要根据客户端 Local 转换时区,等 进出存上线后 TimeZoneUtil 有一个获取方式
                .setCreateTime(user -> {
                    if (user.getCreateTime() == null) {
                        return null;
                    }
                    return LocalDateTime.ofInstant(user.getCreateTime(), ZoneId.systemDefault()).format(DATE_TIME_FORMATTER);
                })
                .setGroupName(UserInfoResp::getOrgName);
        return getHandlerSelectVo(handlerFunDto);
    }

    @Override
    public List<HandlerFeedBackVo> handlerFeedback(List<String> storageIds) {
        if (CollUtil.isEmpty(storageIds)) {
            return new ArrayList<>();
        }
        try {
            var userMap = Optional.ofNullable(userFeign.findByIds(Sets.newHashSet(storageIds))).orElseGet(Map::of);
            return storageIds.stream()
                    .filter(StrUtil::isNotBlank)
                    .map(storageId -> {
                        var userInfo = Optional.ofNullable(userMap.get(storageId)).orElseGet(UserInfoResp::new);
                        return new HandlerFeedBackVo(storageId, userInfo.getNickName());
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // 异常处理：返回默认值
            log.error("办理人权限名称回显异常 => {}", e.getLocalizedMessage());
            return storageIds.stream().map(storageId -> new HandlerFeedBackVo(storageId, "")).collect(Collectors.toList());
        }
    }
}
