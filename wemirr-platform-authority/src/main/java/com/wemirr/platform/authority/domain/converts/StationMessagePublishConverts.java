package com.wemirr.platform.authority.domain.converts;

import com.wemirr.framework.db.page.BasePageConverts;
import com.wemirr.platform.authority.domain.entity.message.StationMessagePublish;
import com.wemirr.platform.authority.domain.resp.StationMessagePublishResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
public class StationMessagePublishConverts {

    public static final StationMessagePublish2VoConverts STATION_MESSAGE_PUBLISH_2_VO_CONVERTS = new StationMessagePublish2VoConverts();

    public static class StationMessagePublish2VoConverts implements BasePageConverts<StationMessagePublish, StationMessagePublishResp> {

        @Override
        public StationMessagePublishResp convert(StationMessagePublish source) {
            if (source == null) {
                return null;
            }
            StationMessagePublishResp target = new StationMessagePublishResp();
            BeanUtils.copyProperties(source, target);
            target.setId(source.getId());
            if (StringUtils.isNotBlank(source.getReceiver())) {
                final List<Long> receiver = Arrays.stream(source.getReceiver().split(",")).map(Long::parseLong).collect(Collectors.toList());
                target.setReceiver(receiver);
            }
            return target;
        }
    }


}
