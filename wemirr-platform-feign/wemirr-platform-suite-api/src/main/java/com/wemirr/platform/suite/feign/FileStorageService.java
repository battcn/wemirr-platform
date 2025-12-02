package com.wemirr.platform.suite.feign;

import com.wemirr.framework.feign.plugin.token.AutoRefreshTokenProperties;
import com.wemirr.platform.suite.feign.domain.resp.FileStorageRep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import static com.wemirr.platform.suite.feign.FileStorageService.FEIGN_CLIENT_NAME;

/**
 * @author xJh
 * @date 2025/11/6
 **/
@FeignClient(name = FEIGN_CLIENT_NAME, dismiss404 = true,path = "/file-storage")
public interface FileStorageService {

    String FEIGN_CLIENT_NAME = "wemirr-platform-suite";

    /**
     * 文件上传接口
     *
     * @param multipartFile 文件
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = {AutoRefreshTokenProperties.X_AUTO_TOKEN, "ignore-header=Content-Type"})
    FileStorageRep upload(@RequestBody MultipartFile multipartFile);

}
