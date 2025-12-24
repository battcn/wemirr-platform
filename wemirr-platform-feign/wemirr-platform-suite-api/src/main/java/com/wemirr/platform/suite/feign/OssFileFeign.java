package com.wemirr.platform.suite.feign;

import com.wemirr.framework.commons.remote.LoadService;
import com.wemirr.framework.feign.plugin.token.AutoRefreshTokenProperties;
import com.wemirr.platform.suite.feign.domain.resp.OssFilePreviewResp;
import com.wemirr.platform.suite.feign.domain.resp.OssFileResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.wemirr.platform.suite.feign.OssFileFeign.FEIGN_CLIENT_NAME;

/**
 * @author xJh
 * @since 2025/11/6
 **/
@FeignClient(name = FEIGN_CLIENT_NAME, dismiss404 = true, path = "/oss-files")
public interface OssFileFeign extends LoadService<OssFilePreviewResp> {

    String FEIGN_CLIENT_NAME = "wemirr-platform-suite";

    /**
     * 文件上传接口
     *
     * @param multipartFile 文件
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = {AutoRefreshTokenProperties.X_AUTO_TOKEN, "ignore-header=Content-Type"})
    OssFileResp upload(@RequestPart("file") MultipartFile multipartFile);

    /**
     * 根据 ID 批量查询
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return 查询结果
     */
    @Override
    @PostMapping("/preview-map")
    Map<Object, OssFilePreviewResp> findByIds(@RequestBody Set<Object> ids);

    /**
     * 根据原始的url 列表 返回一个预签名可下载的 oss url 列表
     * @param req req
     * @return 可预览的地址
     */
    @PostMapping("/preview-list")
    List<String> previewList(@RequestBody List<String> req);

    /**
     * 根据一个原始的url列表 返回一个 map
     * map 的 key 是原始的url value 是预签名的oss url
     * @param req req
     * @return 可预览的地址
     */
    @PostMapping("/preview-map")
    Map<String, String> previewMap(@RequestBody List<String> req);

}
