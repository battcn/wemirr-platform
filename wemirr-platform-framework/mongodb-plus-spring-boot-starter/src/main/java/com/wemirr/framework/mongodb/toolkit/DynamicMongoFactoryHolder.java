package com.wemirr.framework.mongodb.toolkit;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class DynamicMongoFactoryHolder {


    /**
     * 所有数据库
     */
    private static final Map<Object, MongoDatabaseFactory> TEMPLATE_MAP = new ConcurrentHashMap<>();

    /**
     * 添加数据源
     *
     * @param ds            数据源名称
     * @param connectionUrl 数据源 mongodb://admin:X1*C0+qPxQCm@192.168.12.96:27017/cbms_dev2?authSource=admin
     */
    @SneakyThrows
    public static void set(Object ds, String connectionUrl) {
        if (StrUtil.isBlank(connectionUrl)) {
            log.error("mongodb connection url is empty");
            return;
        }
        remove(ds);
        TEMPLATE_MAP.put(ds, new SimpleMongoClientDatabaseFactory(connectionUrl));
        log.info("mongodb-dynamic-template - add a template named [{}] success", ds);
    }

    public static MongoDatabaseFactory poll() {
        MongoTemplate template = SpringUtil.getBean(MongoTemplate.class);
        AuthenticationContext context = SpringUtil.getBean(AuthenticationContext.class);
        final Long tenantId = context.tenantId();
        if (ObjUtil.isEmpty(tenantId)) {
            return template.getMongoDatabaseFactory();
        }
        return get(context.tenantId());
    }

    public static MongoDatabaseFactory get(Object ds) {
        return Optional.ofNullable(TEMPLATE_MAP.get(ds)).orElseThrow(() -> CheckedException.notFound("MongoDb数据源不存在"));
    }

    public static synchronized void remove(Object ds) {
        TEMPLATE_MAP.remove(ds);
    }

}
