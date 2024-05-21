package com.wemirr.framework.mongodb.toolkit;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.mongodb.client.MongoDatabase;
import com.wemirr.framework.commons.exception.CheckedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.lang.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Levin
 */
@Slf4j
public class DynamicMongoTemplate extends MongoTemplate {


    /**
     * 为什么要用链表存储(准确的是栈)
     * <pre>
     * 为了支持嵌套切换，如ABC三个service都是不同的数据源
     * 其中A的某个业务要调B的方法，B的方法需要调用C的方法。一级一级调用切换，形成了链。
     * 传统的只设置当前线程的方式不能满足此业务需求，必须使用栈，后进先出。
     * </pre>
     */
    private static final TransmittableThreadLocal<Deque<MongoDatabaseFactory>> MONGODB_FACTORY_HOLDER = TransmittableThreadLocal.withInitial(ArrayDeque::new);

    public DynamicMongoTemplate(MongoDatabaseFactory mongoDbFactory) {
        super(mongoDbFactory);
    }

    public DynamicMongoTemplate(MongoDatabaseFactory factory, @Nullable MongoConverter mongoConverter) {
        super(factory, mongoConverter);
    }

    public void push(MongoDatabaseFactory factory) {
        if (ObjUtil.isEmpty(factory)) {
            throw CheckedException.badRequest("mongodb ds not blank");
        }
        Deque<MongoDatabaseFactory> deque = MONGODB_FACTORY_HOLDER.get();
        if (deque == null) {
            deque = new ArrayDeque<>();
        }
        deque.push(factory);
    }

    public void clear() {
        MONGODB_FACTORY_HOLDER.remove();
    }

    @Override
    public MongoDatabase getDb() {
        return MONGODB_FACTORY_HOLDER.get().getLast().getMongoDatabase();
    }
}