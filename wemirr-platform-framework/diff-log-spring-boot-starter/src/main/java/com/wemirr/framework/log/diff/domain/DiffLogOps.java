package com.wemirr.framework.log.diff.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author muzhantong
 */
@Data
@Builder
public class DiffLogOps {

    private String successLogTemplate;
    private String failLogTemplate;
    private String operatorId;
    private String group;
    private String businessKey;
    private String tag;
    private String extra;
    private String condition;
    private String isSuccess;
}
