package com.wemirr.platform.tools.domain.req;

import com.wemirr.framework.boot.entity.PageRequest;
import lombok.*;

import java.util.List;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DynamicReleaseQueryDrag extends PageRequest {

    private List<DynamicReleaseQuery> queries;
}
