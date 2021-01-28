package com.wemirr.platform.tools.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServer;
import com.wemirr.platform.tools.domain.req.BatchKey;
import com.wemirr.platform.tools.domain.req.DynamicReleaseDragExtendReq;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerModelResp;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerResp;
import com.wemirr.platform.tools.service.DynamicReleaseDragPushServerService;
import com.wemirr.platform.tools.service.DynamicReleaseDragService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.wemirr.platform.tools.domain.converts.DynamicReleaseDragPushServerConverts.DYNAMIC_RELEASE_DRAG_PUSH_SERVER_PO_2_RESP;

/**
 * 扩展功能
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/dynamic_release_drag_push_servers")
@RequiredArgsConstructor
@Tag(name = "数据推送", description = "数据推送")
@Validated
public class DynamicReleaseDragPushServerController {

    private final DynamicReleaseDragService dynamicReleaseDragService;
    private final DynamicReleaseDragPushServerService dynamicReleaseDragPushServerService;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public Result<IPage<DynamicReleaseDragPushServerResp>> page(PageRequest pageRequest) {
        final Page<DynamicReleaseDragPushServer> page = dynamicReleaseDragPushServerService.page(pageRequest.buildPage());
        return Result.success(DYNAMIC_RELEASE_DRAG_PUSH_SERVER_PO_2_RESP.convertPage(page));
    }

    @Operation(summary = "添加数据推送")
    @PostMapping
    public Result<ResponseEntity<Void>> add(@Validated @RequestBody DynamicReleaseDragExtendReq req) {
        final DynamicReleaseDragPushServer dragExtend = DynamicReleaseDragPushServer.builder()
                .locked(req.getLocked()).method(req.getMethod()).secret(req.getSecret())
                .url(req.getUrl()).opt(CollectionUtil.join(req.getOpt(), ","))
                .build();
        dynamicReleaseDragPushServerService.save(dragExtend);
        return Result.success();
    }

    @Operation(summary = "编辑数据推送")
    @PutMapping("/{id}")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody DynamicReleaseDragExtendReq req) {
        final DynamicReleaseDragPushServer dragExtend = DynamicReleaseDragPushServer.builder()
                .locked(req.getLocked()).method(req.getMethod()).secret(req.getSecret())
                .url(req.getUrl()).opt(CollectionUtil.join(req.getOpt(), ","))
                .build();
        dragExtend.setId(id);
        dynamicReleaseDragPushServerService.updateById(dragExtend);
        return Result.success();
    }

    @Operation(summary = "分配模板")
    @GetMapping("/{id}/drag_models_and_check_ids")
    public Result<DynamicReleaseDragPushServerModelResp> modelRespResult(@PathVariable Long id) {
        return Result.success(this.dynamicReleaseDragPushServerService.queryModelByPushId(id));
    }

    @Operation(summary = "绑定模板")
    @PutMapping("/{id}/binding")
    public Result<ResponseEntity<Void>> binding(@PathVariable Long id, @RequestBody BatchKey<Long> ids) {
        this.dynamicReleaseDragPushServerService.binding(id, ids);
        return Result.success();
    }

    @Operation(summary = "删除数据推送")
    @DeleteMapping("/{id}")
    public Result<ResponseEntity<Void>> remove(@PathVariable Long id) {
        dynamicReleaseDragPushServerService.removeById(id);
        return Result.success();
    }


}
