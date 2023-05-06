package com.wemirr.framework.db.injector;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.wemirr.framework.db.mybatis.UpdateBatchMethod;

import java.util.List;

/**
 * 自定义sql 注入器
 *
 * @author Levin
 * @since 2020年02月19日15:39:49
 */
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        //增加自定义方法
        methodList.add(new InsertBatchSomeColumn(i -> i.getFieldFill() != FieldFill.UPDATE));
//        methodList.add(new UpdateBatchMethod(i -> i.getFieldFill() != FieldFill.INSERT));
        return methodList;
    }

}
