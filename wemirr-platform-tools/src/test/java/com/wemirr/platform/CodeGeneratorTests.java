package com.wemirr.platform;


import cn.hutool.core.util.ZipUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

class CodeGeneratorTests {


    @Test
    void sys() {
        final File zip = ZipUtil.zip("/Users/battcn/Development/opensource/wemirr-platform/.generated/");
        System.out.println(zip.getPath());
    }
}
