package com.wemirr.framework.log.diff.domain.req;

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
public class DiffLogInfoQueryReq {


    private String businessKey;

    private String type;

    private String subType;

}
