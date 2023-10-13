package com.wemirr.framework.boot;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

/**
 * 根据ip查询地址
 *
 * @author Levin
 * @since 2019/10/30
 */
@Slf4j
public class RegionUtils {

    private static final String JAVA_TEMP_DIR = "java.io.tmpdir";

    private static Searcher searcher = null;


    static {
        try {
            // 因为jar无法读取文件,复制创建临时文件
            String dbPath = Objects.requireNonNull(RegionUtils.class.getResource("/ip2region/ip2region.xdb")).getPath();
            File file = new File(dbPath);
            if (!file.exists()) {
                String tmpDir = System.getProperties().getProperty(JAVA_TEMP_DIR);
                dbPath = tmpDir + "ip2region/ip2region.db";
                file = new File(dbPath);
                String classPath = "classpath:ip2region/ip2region.db";
                InputStream resourceAsStream = ResourceUtil.getStreamSafe(classPath);
                if (resourceAsStream != null) {
                    FileUtil.writeFromStream(resourceAsStream, file);
                }
            }
            searcher = Searcher.newWithFileOnly(file.getPath());
        } catch (Exception e) {
            log.error("init ip region error", e);
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
            if (searcher == null || StrUtil.isEmpty(ip)) {
                log.error("searcher or ip is null");
                return StrUtil.EMPTY;
            }
            long startTime = System.currentTimeMillis();
            String result = searcher.search(ip);
            long endTime = System.currentTimeMillis();
            log.debug("region use time[{}] result[{}]", endTime - startTime, result);
            return result;

        } catch (Exception e) {
            log.error("error - {}", e.getLocalizedMessage());
        }
        return StrUtil.EMPTY;
    }


}
