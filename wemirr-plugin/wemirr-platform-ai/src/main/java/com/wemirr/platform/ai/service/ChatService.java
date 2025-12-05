package com.wemirr.platform.ai.service;

import com.wemirr.platform.ai.domain.dto.req.AskReq;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author xJh
 * @since 2025/10/11
 **/
public interface ChatService {

    SseEmitter chatStream(AskReq askReq);

}
