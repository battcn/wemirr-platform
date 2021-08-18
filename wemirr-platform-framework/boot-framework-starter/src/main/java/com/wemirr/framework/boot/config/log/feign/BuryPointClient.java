package com.wemirr.framework.boot.config.log.feign;


import com.wemirr.framework.boot.config.log.OptLogDTO;
import com.wemirr.framework.commons.entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Levin
 */
@FeignClient(name = "wemirr-platform-bury", decode404 = true, fallback = BuryPointClient.BuryPointClientFallback.class)
public interface BuryPointClient {


    /**
     * 添加埋点
     *
     * @param dto 埋点信息
     * @return 添加结果
     */
    @PostMapping("/opt_logs")
    Result<String> buryPoint(@RequestBody OptLogDTO dto);

    @Component
    @RequiredArgsConstructor
    class BuryPointClientFallback implements BuryPointClient {

        @Override
        public Result<String> buryPoint(OptLogDTO dto) {
            return Result.fail("埋点失败");
        }
    }

    // 如果你需要分表分库的操作日志记录，请用这种方式哦
    //    @Bean
//    @ConditionalOnExpression("'${log.type}'.equals('bury')")
//    public SysLogListener sysLogListener(BuryPointClient buryPointClient) {
//        return new SysLogListener(buryPointClient::buryPoint);
//    }

}
