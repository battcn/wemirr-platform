package com.wemirr.framework.boot;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 根据ip查询地址
 *
 * @author Levin
 * @since 2019/10/30
 */
@Slf4j
public class RegionUtils {

    private static final String JAVA_TEMP_DIR = "java.io.tmpdir";

    private static DbSearcher searcher = null;

    static {
        try {
            // 因为jar无法读取文件,复制创建临时文件
            String dbPath = RegionUtils.class.getResource("/ip2region/ip2region.db").getPath();
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
            DbConfig config = new DbConfig();
            searcher = new DbSearcher(config, dbPath);
            log.info("bean [{}]", config);
            log.info("bean [{}]", searcher);
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
            //db
            if (searcher == null || StrUtil.isEmpty(ip)) {
                log.error("DbSearcher is null");
                return StrUtil.EMPTY;
            }
            long startTime = System.currentTimeMillis();
            //查询算法 DbSearcher.BTREE_ALGORITHM: BINARY_ALGORITHM MEMORY_ALGORITYM
            int algorithm = DbSearcher.MEMORY_ALGORITYM;
            // 采用内存加载算法，提高检索效率
            Method method = searcher.getClass().getMethod("memorySearch", String.class);
            DataBlock dataBlock;
            if (!Util.isIpAddress(ip)) {
                log.warn("warning: Invalid ip address");
            }
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            if (dataBlock == null) {
                return "未知";
            }
            String result = dataBlock.getRegion();
            long endTime = System.currentTimeMillis();
            log.debug("region use time[{}] result[{}]", endTime - startTime, result);
            return result;

        } catch (Exception e) {
            log.error("error:", e);
        }
        return StrUtil.EMPTY;
    }


}
