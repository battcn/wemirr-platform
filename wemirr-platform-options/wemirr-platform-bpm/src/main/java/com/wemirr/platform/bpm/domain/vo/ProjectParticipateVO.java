package com.wemirr.platform.bpm.domain.vo;

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
public class ProjectParticipateVO {

    private Long studentId;

    private String taskId;

    private Long projectParticipateId;

}
