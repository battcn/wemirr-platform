package com.wemirr.framework.db.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.commons.security.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * MyBatis Plus 元数据处理类
 * 用于自动 注入 id, createdTime, lastModifiedTime, createdBy, lastModifiedBy 等字段
 *
 * @author Levin
 * @since 2019/04/29
 */
@Slf4j
@RequiredArgsConstructor
public class MyBatisMetaObjectHandler implements MetaObjectHandler {

    private final AuthenticationContext authenticationContext;


    /**
     * 注意：不支持 复合主键 自动注入！！
     * <p>
     * 所有的继承了Entity、SuperEntity的实体，在insert时，
     * id： id为空时， 通过IdGenerate生成唯一ID， 不为空则使用传递进来的id
     * createdBy, lastModifiedBy: 自动赋予 当前线程上的登录人id
     * createdTime, lastModifiedTime: 自动赋予 服务器的当前时间
     * <p>
     * 未继承任何父类的实体，且主键标注了 @TableId(value = "xxx", type = IdType.INPUT) 自动注入 主键
     * 主键的字段名称任意
     *
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 如果要自己设置服务器时间就自己赋值,否则建议使用数据库的默认时间 DEFAULT CURRENT_TIMESTAMP
        final Object createTime = Optional.ofNullable(metaObject.getValue(Entity.CREATE_TIME)).orElseGet(Instant::now);
        this.setFieldValByName(Entity.CREATE_TIME, createTime, metaObject);
        if (authenticationContext.anonymous()) {
            log.warn("匿名接口导致无法获取用户信息,本次跳过织入动作......");
            return;
        }
        final Long userId = authenticationContext.userId();
        if (Objects.nonNull(userId)) {
            this.setFieldValByName(Entity.CREATE_USER, userId, metaObject);
        }
        final String realName = authenticationContext.realName();
        if (Objects.nonNull(realName)) {
            this.setFieldValByName(Entity.CREATE_USER_NAME, realName, metaObject);
        }
    }

    /**
     * 所有的继承了Entity、SuperEntity的实体，在update时，
     * lastModifiedBy: 自动赋予 当前线程上的登录人id
     * lastModifiedTime: 自动赋予 服务器的当前时间
     *
     * @param metaObject metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 如果要自己设置服务器时间就自己赋值,否则建议使用数据库的默认时间 DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
        final Object updateTime = Optional.ofNullable(metaObject.getValue(SuperEntity.UPDATE_TIME)).orElseGet(Instant::now);
        this.setFieldValByName(SuperEntity.UPDATE_TIME, updateTime, metaObject);
        if (authenticationContext.anonymous()) {
            log.warn("匿名接口导致无法获取用户信息,本次跳过织入动作......");
            return;
        }
        final Long userId = authenticationContext.userId();
        if (Objects.nonNull(userId)) {
            this.setFieldValByName(SuperEntity.UPDATE_USER, userId, metaObject);
        }
        final String realName = authenticationContext.realName();
        if (Objects.nonNull(realName)) {
            this.setFieldValByName(SuperEntity.UPDATE_USER_NAME, realName, metaObject);
        }
    }
}
