package com.wemirr.framework.excel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * 校验错误信息
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateLine {

    /**
     * 读取数据行
     */
    private Long line;

    /**
     * 异常信息
     */
    @Builder.Default
    private Set<String> errors = new HashSet<>();

}