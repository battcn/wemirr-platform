package com.wemirr.platform.tools.controller.dynamic;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServer;
import com.wemirr.platform.tools.domain.req.BatchKey;
import com.wemirr.platform.tools.domain.req.DynamicReleaseDragExtendReq;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerModelResp;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerResp;
import com.wemirr.platform.tools.service.DynamicReleaseDragPushServerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final DynamicReleaseDragPushServerService dynamicReleaseDragPushServerService;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public IPage<DynamicReleaseDragPushServerResp> page(PageRequest pageRequest) {
        return dynamicReleaseDragPushServerService.page(pageRequest.buildPage()).convert(DYNAMIC_RELEASE_DRAG_PUSH_SERVER_PO_2_RESP::convert);
    }

    @Operation(summary = "添加数据推送")
    @PostMapping
    public void add(@Validated @RequestBody DynamicReleaseDragExtendReq req) {
        final DynamicReleaseDragPushServer dragExtend = DynamicReleaseDragPushServer.builder()
                .locked(req.getLocked()).method(req.getMethod()).secret(req.getSecret())
                .url(req.getUrl()).opt(CollectionUtil.join(req.getOpt(), ",")).build();
        dynamicReleaseDragPushServerService.save(dragExtend);
    }

    @Operation(summary = "编辑数据推送")
    @PutMapping("/{id}")
    public void edit(@PathVariable Long id, @Validated @RequestBody DynamicReleaseDragExtendReq req) {
        final DynamicReleaseDragPushServer dragExtend = DynamicReleaseDragPushServer.builder()
                .locked(req.getLocked()).method(req.getMethod()).secret(req.getSecret())
                .url(req.getUrl()).opt(CollectionUtil.join(req.getOpt(), ","))
                .build();
        dragExtend.setId(id);
        dynamicReleaseDragPushServerService.updateById(dragExtend);
    }

    @Operation(summary = "分配模板")
    @GetMapping("/{id}/drag_models_and_check_ids")
    public DynamicReleaseDragPushServerModelResp modelRespResult(@PathVariable Long id) {
        return this.dynamicReleaseDragPushServerService.queryModelByPushId(id);
    }

    @Operation(summary = "绑定模板")
    @PutMapping("/{id}/binding")
    public void binding(@PathVariable Long id, @RequestBody BatchKey<Long> ids) {
        this.dynamicReleaseDragPushServerService.binding(id, ids);
    }

    @Operation(summary = "删除数据推送")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        dynamicReleaseDragPushServerService.removeById(id);
    }


}
