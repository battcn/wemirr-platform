package com.wemirr.platform.authority.domain.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.boot.log.AccessLogInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("common_opt_log")
public class OptLog extends AccessLogInfo {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

}
