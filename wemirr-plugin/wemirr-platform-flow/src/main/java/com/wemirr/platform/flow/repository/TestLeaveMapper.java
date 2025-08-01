package com.wemirr.platform.flow.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.flow.domain.entity.TestLeave;
import org.springframework.stereotype.Repository;

/**
 * 请假Mapper接口
 *
 * @author battcn
 * @date 2023-07-21
 */
@Repository
public interface TestLeaveMapper extends SuperMapper<TestLeave> {

}
