package com.wemirr.framework.db.mybatis;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.ValidException;

import static com.wemirr.framework.commons.exception.ExceptionCode.SERVICE_MAPPER_ERROR;

/**
 * 不含缓存的Service实现
 * <p>
 * <p>
 * 2，removeById：重写 ServiceImpl 类的方法，删除db
 * 3，removeByIds：重写 ServiceImpl 类的方法，删除db
 * 4，updateAllById： 新增的方法： 修改数据（所有字段）
 * 5，updateById：重写 ServiceImpl 类的方法，修改db后
 *
 * @param <M> Mapper
 * @param <T> 实体
 * @author Levin
 * @since 2020年02月27日18:15:17
 */
public class SuperServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements SuperService<T> {

    public SuperMapper<T> getSuperMapper() {
        if (baseMapper != null) {
            return baseMapper;
        }
        throw new ValidException(SERVICE_MAPPER_ERROR);
    }

    private static final String COLON = ":";

    /**
     * 构建没有租户信息的key
     *
     * @param args
     * @return
     */
    protected static String buildKey(Object... args) {
        if (args.length == 1) {
            return String.valueOf(args[0]);
        } else if (args.length > 0) {
            return StrUtil.join(COLON, args);
        } else {
            return "";
        }
    }

    /**
     * 构建key
     *
     * @param args args
     * @return key
     */
    protected String key(Object... args) {
        return buildKey(args);
    }

    @Override
    public boolean save(T model) {
        Result<Boolean> result = handlerSave(model);
        if (result.getDefExec()) {
            return super.save(model);
        }
        return result.getData();
    }

    /**
     * 处理新增相关处理
     *
     * @param model model
     * @return Result
     */
    protected Result<Boolean> handlerSave(T model) {
        return Result.successDef();
    }

    /**
     * 处理修改相关处理
     *
     * @param model model
     * @return Result
     */
    protected Result<Boolean> handlerUpdateAllById(T model) {
        return Result.successDef();
    }

    /**
     * 处理修改相关处理
     *
     * @param model model
     * @return Result
     */
    protected Result<Boolean> handlerUpdateById(T model) {
        return Result.successDef();
    }


    @Override
    public boolean updateById(T model) {
        Result<Boolean> result = handlerUpdateById(model);
        if (result.getDefExec()) {
            return super.updateById(model);
        }
        return result.getData();
    }


}
