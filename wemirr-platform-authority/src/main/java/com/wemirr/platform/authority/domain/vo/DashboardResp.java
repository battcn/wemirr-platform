package com.wemirr.platform.authority.domain.vo;


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
public class DashboardResp {

    private long todayIp;
    private long loginTotal;
    private List<LoginRecord> loginRecords;
    private List<LoginArea> loginAreas;

}
