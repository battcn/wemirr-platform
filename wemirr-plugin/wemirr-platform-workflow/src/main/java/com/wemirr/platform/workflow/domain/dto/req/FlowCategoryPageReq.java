package com.wemirr.platform.workflow.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author battcn
 * @since 2025/8/15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FlowCategoryPageReq extends PageRequest {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "开始日期")
    private Instant startTime;

    @Schema(description = "截止日期")
    private Instant endTime;


}
