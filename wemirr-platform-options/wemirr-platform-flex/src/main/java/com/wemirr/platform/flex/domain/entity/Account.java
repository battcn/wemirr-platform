package com.wemirr.platform.flex.domain.entity;


import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
@Table("tb_account")
public class Account {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String userName;

    private Integer age;

    private Instant birthday;

    private Integer tenantId;
}