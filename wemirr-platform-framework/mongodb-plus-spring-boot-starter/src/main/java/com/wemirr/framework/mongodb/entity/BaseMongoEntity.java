package com.wemirr.framework.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * MongoDB 基础实体类
 * <p>提供审计字段自动填充、乐观锁支持，字段命名与SuperEntity保持一致</p>
 *
 * <pre>{@code
 * @Document(collection = "user")
 * public class User extends BaseMongoEntity<String> {
 *     private String username;
 *     private String email;
 * }
 * }</pre>
 *
 * @param <ID> 主键类型
 * @author Levin
 */
@Data
public abstract class BaseMongoEntity<ID extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @MongoId
    private ID id;

    /**
     * 创建时间（自动填充）
     */
    @CreatedDate
    private Instant createTime;

    /**
     * 创建人ID（自动填充）
     */
    @CreatedBy
    private Long createBy;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 最后修改时间（自动填充）
     */
    @LastModifiedDate
    private Instant lastModifyTime;

    /**
     * 最后修改人ID（自动填充）
     */
    @LastModifiedBy
    private Long lastModifyBy;

    /**
     * 最后修改人名称
     */
    private String lastModifyName;

    /**
     * 逻辑删除标识
     */
    @Indexed
    private Boolean deleted;

    /**
     * 版本号（乐观锁）
     */
    @Version
    private Long version;
}
