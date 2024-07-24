package com.wemirr.framework.log.diff.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 操作类型
 *
 * @author Levin
 * @since 2020-02-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
public enum ChangeAction {


    ADDED("added", "添加"),
    REMOVED("removed", "删除"),
    UPDATED("updated", "修改");

    private String type;
    private String desc;
}
