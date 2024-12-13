package com.wemirr.platform.suite.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.suite.file.domain.constants.StorageConstants;
import com.wemirr.platform.suite.file.domain.dto.req.ResourceDelReq;
import com.wemirr.platform.suite.file.domain.entity.ResourceInfoRecord;
import com.wemirr.platform.suite.file.domain.enums.MineType;
import com.wemirr.platform.suite.file.exception.FileException;
import com.wemirr.platform.suite.file.repository.ResourceInfoRecordMapper;
import com.wemirr.platform.suite.file.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.hash.HashInfo;
import org.dromara.x.file.storage.core.platform.FileStorage;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author xiao1
 * @date 2024-12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends SuperServiceImpl<ResourceInfoRecordMapper, ResourceInfoRecord> implements ResourceService {


    private final FileStorageService fileStorageService;

    private final AuthenticationContext authenticationContext;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final StringRedisTemplate stringRedisTemplate;

    private static final long KB = 1024;
    private static final long MB = KB * 1024;
    private static final long GB = MB * 1024;
    private static final long TB = GB * 1024;




    @Override
    public ResourceInfoRecord upload(MultipartFile file) throws JsonProcessingException {
        String platform = getPlatform();
        if (fileStorageService.getFileStorage(platform) == null){
            throw new FileException("未找到对应的存储平台，请检查配置",platform);
        }
        FileInfo info = fileStorageService
                .of(file)
                .setPlatform(platform)
                .upload();
//        FileInfoRecord detail = BeanUtil.copyProperties(
//                info, FileInfoRecord.class, "metadata", "userMetadata", "thMetadata", "thUserMetadata", "attr", "hashInfo");
//        detail.setMetadata(valueToJson(info.getMetadata()));
//        detail.setUserMetadata(valueToJson(info.getUserMetadata()));
//        detail.setThMetadata(valueToJson(info.getThMetadata()));
//        detail.setThUserMetadata(valueToJson(info.getThUserMetadata()));
//        detail.setAttr(valueToJson(info.getAttr()));
//        detail.setHashInfo(valueToJson(info.getHashInfo()));
        ResourceInfoRecord resourceInfoRecord = toFileInfoRecord(info);
        resourceInfoRecord.setTenantId(authenticationContext.tenantId());
        resourceInfoRecord.setFileType(MineType.ofName(info.getContentType()));
        //TOOD: 设置上传人
        this.save(resourceInfoRecord);
        return resourceInfoRecord;
    }

    @Override
    public ResourceInfoRecord uploadImage(MultipartFile file) throws JsonProcessingException {
        FileInfo info = fileStorageService
                .of(file)
//                .setThumbnailSuffix() //指定缩略图后缀，必须是 thumbnailator 支持的图片格式，默认使用全局的
//                .setSaveThFilename() //指定缩略图的保存文件名，注意此文件名不含后缀，默认自动生成
                .image(img -> img.size(1000,1000))  // 将图片大小调整到 1000*1000
                .thumbnail(th -> th.size(200,200))  // 再生成一张 200*200 的缩略图
                .upload();
        ResourceInfoRecord detail = BeanUtil.copyProperties(
                info, ResourceInfoRecord.class, "metadata", "userMetadata", "thMetadata", "thUserMetadata", "attr", "hashInfo");
        detail.setMetadata(valueToJson(info.getMetadata()));
        detail.setUserMetadata(valueToJson(info.getUserMetadata()));
        detail.setThMetadata(valueToJson(info.getThMetadata()));
        detail.setThUserMetadata(valueToJson(info.getThUserMetadata()));
        detail.setAttr(valueToJson(info.getAttr()));
        detail.setHashInfo(valueToJson(info.getHashInfo()));
        return detail;
    }
    @Override
    public void saveFile(ResourceInfoRecord fileInfo) {
        this.save(fileInfo);
    }

    @Override
    public void deleteFile(ResourceDelReq resourceDelReq) throws JsonProcessingException {
        FileStorage fileStorage = fileStorageService.getFileStorage(resourceDelReq.getPlatform());
        if (fileStorage == null){
            throw new FileException("未找到对应的存储平台或对应平台未开启", resourceDelReq.getPlatform());
        }
        ResourceInfoRecord resourceInfoRecord = this.getById(resourceDelReq.getId());
        if (resourceInfoRecord == null){
            throw new FileException("文件不存在,请确认文件id", resourceDelReq.getPlatform());
        }
        FileInfo fileInfo = toFileInfo(resourceInfoRecord);
        if (fileStorageService.delete(fileInfo)){
            this.removeById(resourceDelReq.getId());
        }
    }

    @Override
    public ResourceInfoRecord rename(Long id, String originName) {
        baseMapper.rename(id, originName);
        return null;
    }

    public String valueToJson(Object value) throws JsonProcessingException {
        if (value == null) return null;
        return objectMapper.writeValueAsString(value);
    }

    public String getPlatform() {
        Long tenantId = authenticationContext.tenantId();
        return stringRedisTemplate.opsForValue().get(String.format(StorageConstants.STORAGE_CONFIG_KEY_PREFIX, tenantId));
    }

    /**
     * 将 FileInfo 转为 FileInfoRecord
     */
    public ResourceInfoRecord toFileInfoRecord(FileInfo info) throws JsonProcessingException {
        ResourceInfoRecord detail = BeanUtil.copyProperties(
                info, ResourceInfoRecord.class, "metadata", "userMetadata", "thMetadata", "thUserMetadata", "attr", "hashInfo");
        detail.setMetadata(valueToJson(info.getMetadata()));
        detail.setUserMetadata(valueToJson(info.getUserMetadata()));
        detail.setThMetadata(valueToJson(info.getThMetadata()));
        detail.setThUserMetadata(valueToJson(info.getThUserMetadata()));
        detail.setAttr(valueToJson(info.getAttr()));
        detail.setHashInfo(valueToJson(info.getHashInfo()));
        detail.setFormatSize(formatFileSize(info.getSize()));
        return detail;
    }
    /**
     * 将 FileInfoRecord 转为 FileInfo
     */
    public FileInfo toFileInfo(ResourceInfoRecord detail) throws JsonProcessingException {
        FileInfo info = BeanUtil.copyProperties(
                detail, FileInfo.class, "metadata", "userMetadata", "thMetadata", "thUserMetadata", "attr", "hashInfo");

        // 这里手动获取数据库中的 json 字符串 并转成 元数据，方便使用
        info.setMetadata(jsonToMetadata(detail.getMetadata()));
        info.setUserMetadata(jsonToMetadata(detail.getUserMetadata()));
        info.setThMetadata(jsonToMetadata(detail.getThMetadata()));
        info.setThUserMetadata(jsonToMetadata(detail.getThUserMetadata()));
        // 这里手动获取数据库中的 json 字符串 并转成 附加属性字典，方便使用
        info.setAttr(jsonToDict(detail.getAttr()));
        // 这里手动获取数据库中的 json 字符串 并转成 哈希信息，方便使用
        info.setHashInfo(jsonToHashInfo(detail.getHashInfo()));
        return info;
    }
    /**
     * 将 json 字符串转换成元数据对象
     */
    public Map<String, String> jsonToMetadata(String json) throws JsonProcessingException {
        if (StrUtil.isBlank(json)) return null;
        return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
    }

    /**
     * 将 json 字符串转换成字典对象
     */
    public Dict jsonToDict(String json) throws JsonProcessingException {
        if (StrUtil.isBlank(json)) return null;
        return objectMapper.readValue(json, Dict.class);
    }

    /**
     * 将 json 字符串转换成哈希信息对象
     */
    public HashInfo jsonToHashInfo(String json) throws JsonProcessingException {
        if (StrUtil.isBlank(json)) return null;
        return objectMapper.readValue(json, HashInfo.class);
    }

    public static String formatFileSize(long  bytes) throws NumberFormatException {
        if (bytes >= TB) {
            return String.format("%.2f TB", bytes / (double) TB);
        } else if (bytes >= GB) {
            return String.format("%.2f GB", bytes / (double) GB);
        } else if (bytes >= MB) {
            return String.format("%.2f MB", bytes / (double) MB);
        } else if (bytes >= KB) {
            return String.format("%.2f KB", bytes / (double) KB);
        } else {
            return String.format("%d B", bytes);
        }
    }


}
