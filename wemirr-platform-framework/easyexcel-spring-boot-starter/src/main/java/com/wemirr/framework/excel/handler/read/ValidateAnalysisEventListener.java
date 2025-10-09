package com.wemirr.framework.excel.handler.read;

import cn.idev.excel.read.listener.ReadListener;
import com.wemirr.framework.excel.domain.ValidateLine;

import java.util.List;

/**
 * 带校验的数据解析
 *
 * @author Levin
 */
public interface ValidateAnalysisEventListener<T> extends ReadListener<T> {


    /**
     * 解析 Excel 数据结果集
     *
     * @return 集合
     */
    List<T> getList();

    /**
     * 校验读取行
     *
     * @return 集合
     */
    List<ValidateLine> getValidateLines();

}
