package com.wemirr.platform.iam.feign;

import com.wemirr.framework.commons.FeignConstants;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.feign.plugin.token.AutoRefreshTokenProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Levin
 */
@FeignClient(name = FeignConstants.TOOLS_FEIGN_NAME, path = "/files", dismiss404 = true)
public interface FileServiceFeign {


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = {AutoRefreshTokenProperties.X_AUTO_TOKEN, "ignore-header=Content-Type"})
    Result<?> upload(@RequestPart("file") MultipartFile file);


    @GetMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {AutoRefreshTokenProperties.X_AUTO_TOKEN, "ignore-header=Content-Type"})
    String getToken(@RequestParam("key") String key, @RequestParam(defaultValue = "true", name = "random") Boolean random);

}
