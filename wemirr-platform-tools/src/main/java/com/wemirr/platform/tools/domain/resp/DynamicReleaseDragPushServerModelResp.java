package com.wemirr.platform.tools.domain.resp;

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
public class DynamicReleaseDragPushServerModelResp {

    private List<DynamicReleaseDragModel> modelList;
    private List<Long> checkedModels;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseDragModel {
        private Long id;
        private String label;
    }
}
