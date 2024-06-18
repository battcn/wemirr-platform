package com.wemirr.platform.authority.domain.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("common_diff_log")
public class DiffLogEntity extends DiffLogInfo {

    @TableId(type = IdType.ASSIGN_ID)
    @OrderBy
    private Long id;



}
