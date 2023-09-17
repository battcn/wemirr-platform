package com.wemirr.platform.authority.controller.message;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.db.configuration.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.platform.authority.domain.entity.message.SiteMessage;
import com.wemirr.platform.authority.service.SiteMessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@Tag(name = "站内消息")
@RequiredArgsConstructor
@TenantDS
@RequestMapping("/site_messages")
public class SiteMessageController {

    private final TenantEnvironment tenantEnvironment;
    private final SiteMessageService siteMessageService;

    @GetMapping("/page")
    public Page<SiteMessage> pageList(String title, String level, Boolean mark, PageRequest request) {
        return siteMessageService.page(request.buildPage(), Wraps.<SiteMessage>lbQ()
                .like(SiteMessage::getTitle, title).eq(SiteMessage::getLevel, level)
                .eq(SiteMessage::getMark, mark).eq(SiteMessage::getReceiveId, tenantEnvironment.userId()));
    }

    @PatchMapping("/{id}/mark")
    public void mark(@PathVariable("id") Long id) {
        this.siteMessageService.updateById(SiteMessage.builder().mark(true).id(id).build());
    }


    @DeleteMapping("/{id}")
    public void del(@PathVariable("id") Long id) {
        this.siteMessageService.removeById(id);
    }

    @DeleteMapping("/batch_remove")
    public void batchDel(@RequestBody List<Long> ids) {
        this.siteMessageService.removeByIds(ids);
    }


}
