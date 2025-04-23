package com.wemirr.platform.suite.gen.domain.dto.rep;

import lombok.Data;

import java.util.List;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
public class GenerateTemplateGroupPageRep {

    private Long id;

    /**
     * 分组名称
     */
    private String name;


    /**
     * 分组描述
     */
    private String desciption;

    /**
     * 是否作为默认分组
     */
    private Boolean isDefault;

    private List<Long> templateIds;
}
