package com.wemirr.platform.warmflow.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.warmflow.domain.entity.TestLeave;
import com.wemirr.platform.warmflow.domain.vo.TestLeaveVo;
import org.springframework.stereotype.Repository;

/**
 * 请假Mapper接口
 *
 * @author may
 * @date 2023-07-21
 */
@Repository
public interface TestLeaveMapper extends SuperMapper<TestLeave> {

}
