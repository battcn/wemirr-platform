package com.wemirr.platform.iam.system.func;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.log.diff.service.IParseFunction;
import com.wemirr.platform.iam.system.domain.entity.User;
import com.wemirr.platform.iam.system.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Levin
 */
@Slf4j
@Component
public class UserParseFunction implements IParseFunction {

    public static final String NAME = "getById";

    @Resource
    private UserService userService;

    @Override
    public String functionName() {
        return NAME;
    }

    @Override
    public String apply(Object value) {
        if (StrUtil.isEmptyIfStr(value)) {
            return "";
        }

        // 获取用户信息
        User user = userService.getById(Convert.toLong(value));
        if (user == null) {
            log.warn("[apply][获取用户{{}}为空", value);
            return "";
        }
        // 返回格式 WP(13888888888)
        String nickname = user.getNickName();
        if (StrUtil.isEmpty(user.getMobile())) {
            return nickname;
        }
        return StrUtil.format("{}({})", nickname, user.getMobile());
    }

}