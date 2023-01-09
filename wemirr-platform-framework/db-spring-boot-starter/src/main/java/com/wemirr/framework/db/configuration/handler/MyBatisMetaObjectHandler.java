package com.wemirr.framework.db.configuration.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.db.TenantEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;

import java.time.LocalDateTime;

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

    /**
     * id类型判断符
     */
    private static final String ID_TYPE = "java.lang.String";

    private final long workerId;
    private final long dataCenterId;
    private final TenantEnvironment tenantEnvironment;


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
        if (tenantEnvironment.anonymous()) {
            log.warn("匿名接口导致无法获取用户信息,本次跳过织入动作......");
            return;
        }
        final Long userId = tenantEnvironment.userId();
        final String realName = tenantEnvironment.realName();
        if (userId == null) {
            return;
        }
        final Object originalObject = metaObject.getOriginalObject();
        if (originalObject instanceof SuperEntity) {
            this.setFieldValByName(SuperEntity.CREATE_USER, userId, metaObject);
            this.setFieldValByName(SuperEntity.CREATE_USER_NAME, realName, metaObject);
        } else {
            final ObjectWrapper wrapper = metaObject.getObjectWrapper();
            if (wrapper.hasGetter(Entity.CREATE_USER)) {
                this.setFieldValByName(Entity.CREATE_USER, userId, metaObject);
                this.setFieldValByName(SuperEntity.CREATE_USER_NAME, realName, metaObject);
            }
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
        if (tenantEnvironment.anonymous()) {
            log.warn("匿名接口导致无法获取用户信息,本次跳过织入动作......");
            return;
        }
        log.debug("开始给 Entity SuperEntity 字段赋值....");
        final Long userId = tenantEnvironment.userId();
        final String realName = tenantEnvironment.realName();
        final Object originalObject = metaObject.getOriginalObject();
        if (originalObject instanceof SuperEntity) {
            this.setFieldValByName(SuperEntity.UPDATE_USER, userId, metaObject);
            this.setFieldValByName(SuperEntity.UPDATE_USER_NAME, realName, metaObject);
        } else {
            final ObjectWrapper wrapper = metaObject.getObjectWrapper();
            if (wrapper.hasGetter(SuperEntity.UPDATE_USER)) {
                this.setFieldValByName(SuperEntity.UPDATE_USER, userId, metaObject);
                this.setFieldValByName(SuperEntity.UPDATE_USER_NAME, realName, metaObject);
            }
            if (wrapper.hasGetter(SuperEntity.UPDATE_TIME)) {
                this.setFieldValByName(SuperEntity.UPDATE_TIME, LocalDateTime.now(), metaObject);
                this.setFieldValByName(SuperEntity.UPDATE_USER_NAME, realName, metaObject);
            }
        }
    }
}
