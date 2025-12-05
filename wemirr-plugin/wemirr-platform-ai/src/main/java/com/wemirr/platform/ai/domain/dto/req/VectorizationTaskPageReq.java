package com.wemirr.platform.ai.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xJh
 * @since 2025/10/22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "向量任务分页查询请求")
public class VectorizationTaskPageReq extends PageRequest {
}
