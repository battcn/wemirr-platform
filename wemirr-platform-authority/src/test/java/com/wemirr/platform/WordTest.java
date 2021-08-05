package com.wemirr.platform;

import com.alibaba.fastjson.JSON;
import com.wemirr.framework.boot.utils.BeanUtilPlus;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.platform.authority.domain.dto.TenantSaveDTO;
import com.wemirr.platform.authority.domain.entity.baseinfo.Tenant;
import org.junit.jupiter.api.Test;

public class WordTest {

//    @Test
//    public void test1() {
//        // http://deepoove.com/poi-tl/#_%E6%96%87%E6%9C%AC
//        //模板、文件、图片路径
//        String workPath = System.getProperty("user.dir") + "/wemirr-platform-authority/src/main/resources/";
//        String templateName = "test.docx";
//        //模板替换数据封装
//        Map<String, Object> datas = new HashMap<String, Object>() {
//            {
//                //文本
//                put("name", "xiaoguo");
//            }
//        };
//        //生成word文档 todo 返回生成的word文件引用，可以根据需要是否要删除文件
//        File file = WordUtils.generate(datas, workPath + templateName, workPath);
//    }

    @Test
    public void test1() {
        String uri = "/users/8";
        final String id = StringUtils.substringAfterLast(uri, "/");
        System.out.println(id);
    }

    @Test
    public void test2() {
        TenantSaveDTO dto = new TenantSaveDTO();
        dto.setAddress("哈哈哈");
        dto.setName("测试一下");
        final Tenant tenant = BeanUtilPlus.toBean(1, dto, Tenant.class);
        System.out.println(JSON.toJSONString(tenant));
    }

}
