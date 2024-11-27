package com.wemirr.platform.demo.domain.resp;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExcelDomain {

    @ExcelProperty(value = "${excel.name}", index = 0)
    private String name;

    @ExcelProperty(value = "${excel.type}", index = 1)
    private String type;

}
