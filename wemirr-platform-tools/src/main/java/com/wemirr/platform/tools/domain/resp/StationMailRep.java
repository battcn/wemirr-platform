package com.wemirr.platform.tools.domain.resp;

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
public class StationMailRep {

    private Long id;
    private String title;
    private String createTime;
    private StationMailType type;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StationMailType {
        private String code;
        private String desc;
    }

}
