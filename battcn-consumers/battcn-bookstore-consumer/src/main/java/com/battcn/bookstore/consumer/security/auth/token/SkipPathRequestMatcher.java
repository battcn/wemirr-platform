package com.battcn.bookstore.consumer.security.auth.token;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 跳过请求匹配路径
 *
 * @author Levin
 * @since 2017-05-25
 */
public class SkipPathRequestMatcher implements RequestMatcher {

    private OrRequestMatcher orRequestMatcher;

    /**
     * @param paths 拦截的路径
     */
    public SkipPathRequestMatcher(List<String> paths) {
        Assert.notNull(paths, "路径不能为空");
        orRequestMatcher = new OrRequestMatcher(paths.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList()));
    }


    @Override
    public boolean matches(HttpServletRequest request) {

        //return orRequestMatcher.matches(request);
        return false;
    }
}
