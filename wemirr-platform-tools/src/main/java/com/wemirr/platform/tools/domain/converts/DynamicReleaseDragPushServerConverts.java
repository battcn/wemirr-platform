package com.wemirr.platform.tools.domain.converts;

import com.wemirr.framework.boot.entity.BasePageConverts;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServer;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerResp;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
public class DynamicReleaseDragPushServerConverts {


    public static final DynamicReleaseDragPushServerConverts.DynamicReleaseDragPushServerPo2Resp DYNAMIC_RELEASE_DRAG_PUSH_SERVER_PO_2_RESP = new DynamicReleaseDragPushServerConverts.DynamicReleaseDragPushServerPo2Resp();


    public static class DynamicReleaseDragPushServerPo2Resp implements BasePageConverts<DynamicReleaseDragPushServer, DynamicReleaseDragPushServerResp> {
        @Override
        public DynamicReleaseDragPushServerResp convert(DynamicReleaseDragPushServer source) {
            if (source == null) {
                return null;
            }
            DynamicReleaseDragPushServerResp resp = DynamicReleaseDragPushServerResp.builder()
                    .id(source.getId()).method(source.getMethod()).createdTime(source.getCreatedTime())
                    .secret(source.getSecret()).url(source.getUrl())
                    .locked(source.getLocked()).build();
            if (StringUtils.isNotBlank(source.getOpt())) {
                resp.setOpt(Arrays.stream(source.getOpt().split(",")).collect(Collectors.toList()));
            }
            return resp;
        }
    }


}
