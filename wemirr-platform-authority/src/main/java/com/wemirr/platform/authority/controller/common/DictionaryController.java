package com.wemirr.platform.authority.controller.common;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.annotation.SysLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.DictionaryDTO;
import com.wemirr.platform.authority.domain.entity.common.Dictionary;
import com.wemirr.platform.authority.domain.entity.common.DictionaryItem;
import com.wemirr.platform.authority.service.DictionaryItemService;
import com.wemirr.platform.authority.service.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wemirr.platform.authority.domain.converts.DictionaryConverts.DICTIONARY_DTO_2_PO_CONVERTS;

/**
 * 字典类型
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionaries")
@Tag(name = "字典类型", description = "字典类型")
@RequiredArgsConstructor
public class DictionaryController {


    private final DictionaryService dictionaryService;
    private final DictionaryItemService dictionaryItemService;

    @GetMapping
    @SysLog(value = "字典查询")
    @Operation(description = "查询字典 - [DONE] - [Levin]")
    @Parameter(name = "name", description = "名称", in = ParameterIn.QUERY)
    public Result<IPage<Dictionary>> query(PageRequest pageRequest, String name, String code, Boolean status) {
        final Page<Dictionary> page = this.dictionaryService.page(pageRequest.buildPage(),
                Wraps.<Dictionary>lbQ().eq(Dictionary::getStatus, status)
                        .like(Dictionary::getCode, code).like(Dictionary::getName, name));
        return Result.success(page);
    }

    @PostMapping
    @SysLog(value = "字典新增")
    @Operation(description = "新增字典 - [DONE] - [Levin]")
    public Result<ResponseEntity<Void>> save(@Validated @RequestBody DictionaryDTO dto) {
        this.dictionaryService.addDictionary(DICTIONARY_DTO_2_PO_CONVERTS.convert(dto));
        return Result.success();
    }

    @PutMapping("/{id}")
    @SysLog(value = "字典编辑")
    @Operation(description = "编辑字典 - [DONE] - [Levin]")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody DictionaryDTO dto) {
        final Dictionary dictionary = DICTIONARY_DTO_2_PO_CONVERTS.convert(dto);
        dictionary.setId(id);
        this.dictionaryService.editDictionary(dictionary);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除指定字典项", request = true)
    @Operation(description = "删除字典 - [DONE] - [Levin]")
    public Result<ResponseEntity<Void>> del(@PathVariable Long id) {
        this.dictionaryService.deleteById(id);
        return Result.success();
    }


    @GetMapping("/{dictionary_code}/list")
    @Operation(description = "查询字典子项 - [DONE] - [Levin]")
    @Parameter(name = "dictionary_code", description = "编码", in = ParameterIn.PATH)
    public Result<List<DictionaryItem>> list(@PathVariable("dictionary_code") String dictionaryCode) {
        final List<DictionaryItem> list = this.dictionaryItemService.list(Wraps.<DictionaryItem>lbQ()
                .eq(DictionaryItem::getStatus, true).eq(DictionaryItem::getDictionaryCode, dictionaryCode));
        return Result.success(list);
    }
}
