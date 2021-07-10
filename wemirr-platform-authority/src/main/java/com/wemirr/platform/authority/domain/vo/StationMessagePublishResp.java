package com.wemirr.platform.authority.domain.vo;

import com.wemirr.platform.authority.domain.enums.ReceiverType;
import lombok.Data;

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

}
