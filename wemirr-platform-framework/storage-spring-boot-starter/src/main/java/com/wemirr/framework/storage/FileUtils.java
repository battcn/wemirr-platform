package com.wemirr.framework.storage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Levin
 */
public class FileUtils {


    /**
     * 根据旧的名称生成新的名称
     *
     * @param originName originName
     * @return 生成结果
     */
    public static String randomName(String originName) {
        final String uuid = UUID.randomUUID().toString();
        if (StringUtils.isBlank(originName)) {
            return uuid;
        }
        final String extension = FileUtil.extName(originName);
        return uuid + "." + extension;
    }

    private static final String SEPARATOR = "/";


    public static String targetName(boolean random, String prefix, String originName) {
        return buildTargetName(random, prefix, originName).replaceAll("//", "/");
    }

    private static String buildTargetName(boolean random, String prefix, String originName) {
        if (!random) {
            return StringUtils.join(SEPARATOR, originName);
        }
        final String name = randomName(originName);
        if (StringUtils.isNotBlank(name)) {
            return StringUtils.isBlank(prefix) ? name : StringUtils.join(prefix, SEPARATOR, name);
        }
        return StringUtils.isBlank(prefix) ? name : StringUtils.join(prefix, name);
    }


}
