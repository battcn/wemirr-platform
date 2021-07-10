package com.wemirr.platform.authority.controller.message;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.annotation.SysLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.authority.domain.dto.StationMessagePublishReq;
import com.wemirr.platform.authority.domain.entity.message.StationMessagePublish;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import com.wemirr.platform.authority.domain.vo.CommonDataResp;
import com.wemirr.platform.authority.domain.vo.StationMessagePublishResp;
import com.wemirr.platform.authority.service.StationMessagePublishService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wemirr.framework.commons.entity.Result.success;
import static com.wemirr.platform.authority.domain.converts.StationMessagePublishConverts.STATION_MESSAGE_PUBLISH_2_VO_CONVERTS;

/**
 * @author Levin
 */
@RequestMapping("station_messages_publish")
@RestController
@RequiredArgsConstructor
public class StationMessagePublishController {

    private final StationMessagePublishService stationMessagePublishService;

    @GetMapping
    public Result<IPage<StationMessagePublishResp>> publishList(PageRequest request) {
        final Page<StationMessagePublish> page = stationMessagePublishService.page(request.buildPage());
        return Result.success(  STATION_MESSAGE_PUBLISH_2_VO_CONVERTS.convertPage(page));
    }


    @GetMapping("/{type}/receivers")
    public Result<List<CommonDataResp>> query(@PathVariable ReceiverType type, String search) {
        return Result.success(this.stationMessagePublishService.queryReceiverByType(type, search));
    }

    @PostMapping
    @SysLog(value = "添加发布消息")
    @Operation(summary = "添加发布消息")
    public Result<ResponseEntity<Void>> add(@Validated @RequestBody StationMessagePublishReq req) {
        final StationMessagePublish bean = BeanUtil.toBean(req, StationMessagePublish.class);
        bean.setReceiver(StringUtils.join(req.getReceiver(),","));
        stationMessagePublishService.save(bean);
        return success();
    }

    @PutMapping("/{id}")
    @SysLog(value = "编辑发布消息")
    @Operation(summary = "编辑发布消息")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody StationMessagePublishReq req) {
        final StationMessagePublish bean = BeanUtil.toBean(req, StationMessagePublish.class);
        bean.setId(id);
        stationMessagePublishService.updateById(bean);
        return success();
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除发布消息")
    @Operation(summary = "删除发布消息")
    public Result<ResponseEntity<Void>> del(@PathVariable Long id) {
        stationMessagePublishService.removeById(id);
        return success();
    }


}
