package com.wemirr.platform.authority.service.impl;

import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.entity.log.LoginLog;
import com.wemirr.platform.authority.domain.vo.DashboardResp;
import com.wemirr.platform.authority.domain.vo.LoginArea;
import com.wemirr.platform.authority.domain.vo.LoginRecord;
import com.wemirr.platform.authority.repository.LoginLogMapper;
import com.wemirr.platform.authority.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final LoginLogMapper loginLogMapper;

    @Override
    public DashboardResp dashboard() {
        final LocalDate now = LocalDate.now();
        final LocalDateTime startTime = LocalDateTime.of(now, LocalTime.of(0, 0, 0));
        final LocalDateTime endTime = LocalDateTime.of(now, LocalTime.of(23, 59, 59));
        final Integer todayIp = this.loginLogMapper.selectCount(Wraps.<LoginLog>lbQ().between(LoginLog::getCreatedTime, startTime, endTime));
        final long loginTotal = this.loginLogMapper.countDistinctLoginIp();
        final List<LoginRecord> loginRecords = this.loginLogMapper.selectLoginRecord();
        final List<LoginArea> loginAreas = this.loginLogMapper.selectLoginArea();
        return DashboardResp.builder()
                .todayIp(todayIp).loginTotal(loginTotal)
                .loginAreas(loginAreas)
                .loginRecords(loginRecords)
                .build();
    }
}
