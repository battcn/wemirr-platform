package com.wemirr.platform.authority.controller.message;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.SysLog;
import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.entity.message.SiteNotify;
import com.wemirr.platform.authority.domain.enums.ReceiverType;
import com.wemirr.platform.authority.domain.req.SiteMessagePageReq;
import com.wemirr.platform.authority.domain.req.SiteMessageReq;
import com.wemirr.platform.authority.domain.resp.CommonDataResp;
import com.wemirr.platform.authority.domain.resp.SiteMessageResp;
import com.wemirr.platform.authority.service.SiteNotifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wemirr.platform.authority.domain.converts.SiteNotifyConverts.SITE_NOTIFY_2_VO_CONVERTS;

/**
 * @author Levin
 */
@TenantDS
@RequestMapping("/site_notifies")
@RestController
@RequiredArgsConstructor
@Tag(name = "站内消息")
public class SiteNotifyController {

    private final SiteNotifyService siteNotifyService;

    @GetMapping("/page")
    @Operation(summary = "站内通知列表")
    @PreAuthorize("hasAuthority('sys:site_notifies:page')")
    public IPage<SiteMessageResp> publishList(SiteMessagePageReq req) {
        return siteNotifyService.page(req.buildPage(), Wraps.<SiteNotify>lbQ()
                        .eq(SiteNotify::getTitle, req.getTitle()).eq(SiteNotify::getLevel, req.getLevel())
                        .eq(SiteNotify::getType, ReceiverType.of(req.getType())))
                .convert(SITE_NOTIFY_2_VO_CONVERTS::convert);
    }

    @GetMapping("/{type}/receivers")
    public List<CommonDataResp> query(@PathVariable ReceiverType type, String search) {
        return this.siteNotifyService.queryReceiverByType(type, search);
    }

    @PostMapping
    @SysLog(value = "添加站内通知")
    @Operation(summary = "添加站内通知")
    @PreAuthorize("hasAuthority('sys:site_notifies:add')")
    public void add(@Validated @RequestBody SiteMessageReq req) {
        final SiteNotify bean = BeanUtil.toBean(req, SiteNotify.class);
        bean.setReceiver(StringUtils.join(req.getReceiver(), ","));
        siteNotifyService.save(bean);
    }

    @PutMapping("/{id}")
    @SysLog(value = "编辑站内通知")
    @Operation(summary = "编辑站内通知")
    @PreAuthorize("hasAuthority('sys:site_notifies:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody SiteMessageReq req) {
        final SiteNotify bean = BeanUtil.toBean(req, SiteNotify.class);
        bean.setReceiver(StringUtils.join(req.getReceiver(), ","));
        bean.setId(id);
        siteNotifyService.updateById(bean);
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除站内通知")
    @Operation(summary = "删除站内通知")
    @PreAuthorize("hasAuthority('sys:site_notifies:remove')")
    public void del(@PathVariable Long id) {
        siteNotifyService.removeById(id);
    }

    @PatchMapping("/{id}/publish")
    @SysLog(value = "发布通知")
    @Operation(summary = "发布通知")
    @PreAuthorize("hasAuthority('sys:site_notifies:publish')")
    public void publish(@PathVariable Long id) {
        siteNotifyService.publish(id);
    }


}
