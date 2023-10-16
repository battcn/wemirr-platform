package com.wemirr.framework.boot;


import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;

/**
 * 根据ip查询地址
 *
 * @author Levin
 * @since 2019/10/30
 */
@Slf4j
public final class RegionUtils {


    /**
     * IP 查询器，启动加载到内存中
     */
    private static Searcher SEARCHER;

    static {
        try {
            long now = System.currentTimeMillis();
            byte[] bytes = ResourceUtil.readBytes("ip2region.xdb");
            SEARCHER = Searcher.newWithBuffer(bytes);
            log.info("启动加载 RegionUtils 成功，耗时 ({}) 毫秒", System.currentTimeMillis() - now);
        } catch (IOException e) {
            log.error("启动加载 RegionUtils 失败", e);
        }
    }

    /**
     * 解析IP
     *
     * @param ip ip
     * @return 查询结果
     */
    public static String getRegion(String ip) {
        try {
            if (SEARCHER == null || StrUtil.isEmpty(ip)) {
                log.error("searcher or ip is null");
                return StrUtil.EMPTY;
            }
            long startTime = System.currentTimeMillis();
            String result = SEARCHER.search(ip);
            long endTime = System.currentTimeMillis();
            log.debug("region use time[{}] result[{}]", endTime - startTime, result);
            return result;

        } catch (Exception e) {
            log.error("error - {}", e.getLocalizedMessage());
        }
        return StrUtil.EMPTY;
    }


}
