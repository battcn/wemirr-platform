package com.wemirr.platform.authority.repository.common;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.framework.i18n.domain.I18nMessage;
import com.wemirr.platform.authority.domain.common.entity.I18nData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface I18nDataMapper extends SuperMapper<I18nData> {


    /**
     * 加载 i18n 数据
     *
     * @return 查询结果
     */
    List<I18nMessage> loadI18nMessage();


}
