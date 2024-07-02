package com.wemirr.framework.excel.domain;

import com.wemirr.framework.excel.handler.head.HeadGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SheetInfo {


    @Builder.Default
    private int sheetNo = -1;
    /**
     * 名字
     */
    @Builder.Default
    private String name = "sheet1";

    /**
     * 包含字段
     */
    private List<String> includes;

    /**
     * 排除字段
     */
    private List<String> excludes;

    /**
     * 头生成器
     */
    @Builder.Default
    private Class<? extends HeadGenerator> headGenerateClass = HeadGenerator.class;

}
