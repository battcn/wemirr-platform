package com.wemirr.platform.authority.controller.common;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.DictionaryItemDTO;
import com.wemirr.platform.authority.domain.entity.common.DictionaryItem;
import com.wemirr.platform.authority.service.DictionaryItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.wemirr.platform.authority.domain.converts.DictionaryConverts.DICTIONARY_ITEM_DTO_2_ITEM_PO_CONVERTS;

/**
 * 字典项
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "字典类型", description = "字典类型")
@RequestMapping("/dictionaries/{dictionary_id}/items")
public class DictionaryItemController {

    private final DictionaryItemService dictionaryItemService;

    @GetMapping
    @Operation(description = "查询字典子项 - [DONE] - [Levin]")
    @Parameters({
            @Parameter(name = "dictionary_id", description = "字典ID", in = ParameterIn.PATH),
            @Parameter(name = "name", description = "名称", in = ParameterIn.QUERY)
    })
    public Result<Page<DictionaryItem>> query(@PathVariable("dictionary_id") Long dictionaryId, String name, PageRequest params) {
        final Page<DictionaryItem> itemPage = this.dictionaryItemService.page(params.buildPage(), Wraps.<DictionaryItem>lbQ()
                .like(DictionaryItem::getName, name)
                .eq(DictionaryItem::getDictionaryId, dictionaryId));
        return Result.success(itemPage);
    }

    @PostMapping
    @Operation(description = "添加字典子项 - [DONE] - [Levin]")
    @Parameter(name = "dictionary_id", description = "字典ID", in = ParameterIn.PATH)
    public Result<ResponseEntity<Void>> save(@PathVariable("dictionary_id") Long dictionaryId, @Validated @RequestBody DictionaryItemDTO dto) {
        this.dictionaryItemService.addDictionaryItem(dictionaryId, DICTIONARY_ITEM_DTO_2_ITEM_PO_CONVERTS.convert(dto));
        return Result.success();
    }

    @PutMapping("/{id}")
    @Operation(description = "编辑字典子项 - [DONE] - [Levin]")
    @Parameter(name = "id", description = "子项ID", in = ParameterIn.PATH)
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody DictionaryItemDTO dto) {
        final DictionaryItem dictionaryItem = DICTIONARY_ITEM_DTO_2_ITEM_PO_CONVERTS.convert(dto);
        dictionaryItem.setId(id);
        this.dictionaryItemService.editDictionaryItem(dictionaryItem);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "删除字典子项 - [DONE] - [Levin]")
    @Parameter(name = "id", description = "子项ID", in = ParameterIn.PATH)
    public Result<ResponseEntity<Void>> del(@PathVariable Long id) {
        this.dictionaryItemService.removeById(id);
        return Result.success();
    }

}
