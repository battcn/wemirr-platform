package com.wemirr.framework.db.mybatisplus.ext;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    /**
     * 默认批次提交数量
     */
    private static final int DEFAULT_BATCH_SIZE = 500;

    private static final Logger logger = LoggerFactory.getLogger(SuperServiceImpl.class);

    public SuperMapper<T> getSuperMapper() {
        if (baseMapper != null) {
            return baseMapper;
        }
        throw new RuntimeException("Mapper类转换异常");
    }

    @Override
    public boolean insertBatch(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            logger.warn("请求数据为空....");
            return false;
        }
        if (list.size() <= DEFAULT_BATCH_SIZE) {
            return this.baseMapper.insertBatchSomeColumn(list) > 0;
        }
        int size = list.size();
        int i = 1;
        List<T> recordList = Lists.newArrayList();
        for (T element : list) {
            recordList.add(element);
            if ((i % DEFAULT_BATCH_SIZE == 0) || i == size) {
                logger.info("数据写入数据库.....");
                this.baseMapper.insertBatchSomeColumn(recordList);
                logger.info("清空临时容器内容.....");
                recordList.clear();
            }
            i++;
        }
        return true;
    }

    @Override
    public boolean updateBatch(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            logger.warn("请求数据为空....");
            return false;
        }
        if (list.size() <= DEFAULT_BATCH_SIZE) {
            return this.baseMapper.updateBatchSomeColumnById(list) > 0;
        }
        int size = list.size();
        int i = 1;
        List<T> recordList = Lists.newArrayList();
        for (T element : list) {
            recordList.add(element);
            if ((i % DEFAULT_BATCH_SIZE == 0) || i == size) {
                logger.info("数据写入数据库.....");
                this.baseMapper.updateBatchSomeColumnById(recordList);
                logger.info("清空临时容器内容.....");
                recordList.clear();
            }
            i++;
        }
        return true;
    }

}
