package com.wemirr.platform.tools.controller.dynamic;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import com.wemirr.platform.tools.service.DynamicReleaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.RequestEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dynamic_releases/{model}")
@RequiredArgsConstructor
@Tag(name = "动态发布", description = "动态发布")
public class DynamicReleaseController {

    @Qualifier("mySqlDynamicReleaseServiceImpl")
    private final DynamicReleaseService<Long> dynamicReleaseService;

    @GetMapping("/pages")
    public Result<IPage<?>> pages(@PathVariable String model, PageRequest request, @RequestParam Map<String, Object> params) {
        final IPage<?> pages = this.dynamicReleaseService.pages(model, request, params);
        return Result.success(pages);
    }


    @GetMapping("/curd_options")
    public Result<DynamicReleaseCurdOptionResp> curdOptions(@PathVariable String model) {
        final DynamicReleaseCurdOptionResp curdOptions = dynamicReleaseService.curdOptions(model);
        return Result.success(curdOptions);
    }

    @PostMapping
    public Result<RequestEntity<Void>> add(@PathVariable String model, @RequestBody Map<String, Object> map) {
        this.dynamicReleaseService.save(model, map);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<RequestEntity<Void>> edit(@PathVariable String model, @PathVariable Long id, @RequestBody Map<String, Object> map) {
        if (MapUtil.isNotEmpty(map)) {
            // 删除主键ID
            map.remove("id");
            this.dynamicReleaseService.updateById(model, id, map);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<RequestEntity<Void>> del(@PathVariable String model, @PathVariable Long id) {
        this.dynamicReleaseService.deleteById(model, id);
        return Result.success();
    }

}
