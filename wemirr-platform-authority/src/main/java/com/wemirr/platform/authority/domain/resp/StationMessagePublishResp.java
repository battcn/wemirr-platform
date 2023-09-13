package com.wemirr.platform.authority.domain.resp;

import com.wemirr.platform.authority.domain.enums.ReceiverType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class StationMessagePublishResp {

    private Long id;
    private String title;
    private String level;
    private ReceiverType type;
    private List<Long> receiver;
    private String description;
    private String content;
    private String createdName;
    private LocalDateTime createdTime;

}
