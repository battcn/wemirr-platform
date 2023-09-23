package com.wemirr.platform.authority.domain.common.converts;

import com.wemirr.framework.db.mybatisplus.page.BasePageConverts;
import com.wemirr.platform.authority.domain.common.entity.SiteNotify;
import com.wemirr.platform.authority.domain.common.resp.SiteMessageResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
public class SiteNotifyConverts {

    public static final SiteNotify2VoConverts SITE_NOTIFY_2_VO_CONVERTS = new SiteNotify2VoConverts();

    public static class SiteNotify2VoConverts implements BasePageConverts<SiteNotify, SiteMessageResp> {

        @Override
        public SiteMessageResp convert(SiteNotify source) {
            if (source == null) {
                return null;
            }
            SiteMessageResp target = new SiteMessageResp();
            BeanUtils.copyProperties(source, target);
            target.setId(source.getId());
            if (StringUtils.isNotBlank(source.getReceiver())) {
                final List<Long> receiver = Arrays.stream(source.getReceiver()
                        .split(",")).map(Long::parseLong).collect(Collectors.toList());
                target.setReceiver(receiver);
            }
            return target;
        }
    }


}
