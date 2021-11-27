package com.wemirr.platform.authority.controller.message;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.annotation.log.SysLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.configuration.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.platform.authority.domain.dto.StationMessagePublishReq;
import com.wemirr.platform.authority.domain.entity.message.StationMessagePublish;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import com.wemirr.platform.authority.domain.vo.CommonDataResp;
import com.wemirr.platform.authority.domain.vo.StationMessagePublishResp;
import com.wemirr.platform.authority.service.StationMessagePublishService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wemirr.platform.authority.domain.converts.StationMessagePublishConverts.STATION_MESSAGE_PUBLISH_2_VO_CONVERTS;

/**
 * @author Levin
 */
@TenantDS
@RequestMapping("station_messages_publish")
@RestController
@RequiredArgsConstructor
public class StationMessagePublishController {

    private final StationMessagePublishService stationMessagePublishService;

    @GetMapping
    public IPage<StationMessagePublishResp> publishList(PageRequest request, String title, Integer level, Integer type) {
        return stationMessagePublishService.page(request.buildPage(), Wraps.<StationMessagePublish>lbQ()
                .eq(StationMessagePublish::getTitle, title).eq(StationMessagePublish::getLevel, level)
                .eq(StationMessagePublish::getType, ReceiverType.of(type))).convert(STATION_MESSAGE_PUBLISH_2_VO_CONVERTS::convert);
    }


    @GetMapping("/{type}/receivers")
    public Result<List<CommonDataResp>> query(@PathVariable ReceiverType type, String search) {
        return Result.success(this.stationMessagePublishService.queryReceiverByType(type, search));
    }

    @PostMapping
    @SysLog(value = "添加发布消息")
    @Operation(summary = "添加发布消息")
    public void add(@Validated @RequestBody StationMessagePublishReq req) {
        final StationMessagePublish bean = BeanUtil.toBean(req, StationMessagePublish.class);
        bean.setReceiver(StringUtils.join(req.getReceiver(), ","));
        stationMessagePublishService.save(bean);
    }

    @PutMapping("/{id}")
    @SysLog(value = "编辑发布消息")
    @Operation(summary = "编辑发布消息")
    public void edit(@PathVariable Long id, @Validated @RequestBody StationMessagePublishReq req) {
        final StationMessagePublish bean = BeanUtil.toBean(req, StationMessagePublish.class);
        bean.setReceiver(StringUtils.join(req.getReceiver(), ","));
        bean.setId(id);
        stationMessagePublishService.updateById(bean);
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除发布消息")
    @Operation(summary = "删除发布消息")
    public void del(@PathVariable Long id) {
        stationMessagePublishService.removeById(id);
    }

    @PatchMapping("/{id}/publish")
    @SysLog(value = "发布消息通知")
    @Operation(summary = "发布消息通知")
    public void publish(@PathVariable Long id) {
        stationMessagePublishService.publish(id);
    }


}
