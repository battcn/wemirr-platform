package com.wemirr.platform.authority.domain.common.resp;

import com.wemirr.platform.authority.domain.baseinfo.enums.ReceiverType;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class SiteMessageResp {

    private Long id;
    private String title;
    private String level;
    private ReceiverType type;
    private List<Long> receiver;
    private String description;
    private String content;
    private String createdName;
    private Instant createdTime;

}
