package com.wemirr.platform.suite.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.constants.StorageConstants;
import com.wemirr.platform.suite.file.domain.dto.rep.FileStoragePageResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageDelReq;
import com.wemirr.platform.suite.file.domain.dto.req.FileStoragePageReq;
import com.wemirr.platform.suite.file.domain.entity.FileStorage;
import com.wemirr.platform.suite.file.domain.enums.MineType;
import com.wemirr.platform.suite.file.repository.FileStorageMapper;
import com.wemirr.platform.suite.file.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.hash.HashInfo;
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
public class FileStorageServiceImpl extends SuperServiceImpl<FileStorageMapper, FileStorage> implements FileStorageService {


    private final org.dromara.x.file.storage.core.FileStorageService fileStorageService;

    private final AuthenticationContext authenticationContext;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final StringRedisTemplate stringRedisTemplate;

    private static final long KB = 1024;
    private static final long MB = KB * 1024;
    private static final long GB = MB * 1024;
    private static final long TB = GB * 1024;




    @Override
    public FileStorage upload(MultipartFile file) throws JsonProcessingException {
        String platform = getPlatform();
        if (fileStorageService.getFileStorage(platform) == null){
            throw  CheckedException.badRequest("未找到对应的存储平台，请检查配置");
        }
        FileInfo info = fileStorageService
                .of(file)
                .setPlatform(platform)
                .upload();
        FileStorage fileStorage = toFileInfoRecord(info);
        fileStorage.setTenantId(authenticationContext.tenantId());
        fileStorage.setFileType(MineType.ofName(info.getContentType()));
        fileStorage.setPlatformV(removeFirstDash(fileStorage.getPlatform()));
        this.save(fileStorage);
        return fileStorage;
    }

    @Override
    public FileStorage uploadImage(MultipartFile file) throws JsonProcessingException {
        FileInfo info = fileStorageService
                .of(file)
//                .setThumbnailSuffix() //指定缩略图后缀，必须是 thumbnailator 支持的图片格式，默认使用全局的
//                .setSaveThFilename() //指定缩略图的保存文件名，注意此文件名不含后缀，默认自动生成
                .image(img -> img.size(1000,1000))  // 将图片大小调整到 1000*1000
                .thumbnail(th -> th.size(200,200))  // 再生成一张 200*200 的缩略图
                .upload();
        FileStorage detail = BeanUtil.copyProperties(
                info, FileStorage.class, "metadata", "userMetadata", "thMetadata", "thUserMetadata", "attr", "hashInfo");
        detail.setMetadata(valueToJson(info.getMetadata()));
        detail.setUserMetadata(valueToJson(info.getUserMetadata()));
        detail.setThMetadata(valueToJson(info.getThMetadata()));
        detail.setThUserMetadata(valueToJson(info.getThUserMetadata()));
        detail.setAttr(valueToJson(info.getAttr()));
        detail.setHashInfo(valueToJson(info.getHashInfo()));
        return detail;
    }
    @Override
    public void saveFile(FileStorage fileInfo) {
        this.save(fileInfo);
    }

    @Override
    public void deleteFile(FileStorageDelReq fileStorageDelReq) throws JsonProcessingException {
        org.dromara.x.file.storage.core.platform.FileStorage fileStorage = fileStorageService.getFileStorage(fileStorageDelReq.getPlatform());
        if (fileStorage == null){

            throw  CheckedException.badRequest("未找到对应的存储平台或对应平台未开启");
        }
        FileStorage resourceInfoRecord = this.getById(fileStorageDelReq.getId());
        if (resourceInfoRecord == null){
            throw  CheckedException.badRequest("文件不存在,请确认文件id");
        }
        FileInfo fileInfo = toFileInfo(resourceInfoRecord);
        if (fileStorageService.delete(fileInfo)){
            this.removeById(fileStorageDelReq.getId());
        }
    }

    @Override
    public void rename(Long id, String originName) {
        baseMapper.rename(id, originName);
    }

    @Override
    public IPage<FileStoragePageResp> pageList(FileStoragePageReq req)
    {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<FileStorage>lbQ()
                        .eq(FileStorage::getFileType, req.getFileType())
                        .like(FileStorage::getOriginalFilename,req.getOriginalFilename())
                        .like(FileStorage::getCreatedName,req.getCreatedName()))
                .convert(x -> BeanUtil.toBean(x, FileStoragePageResp.class));
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
    public FileStorage toFileInfoRecord(FileInfo info) throws JsonProcessingException {
        FileStorage detail = BeanUtil.copyProperties(
                info, FileStorage.class, "metadata", "userMetadata", "thMetadata", "thUserMetadata", "attr", "hashInfo");
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
    public FileInfo toFileInfo(FileStorage detail) throws JsonProcessingException {
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
    private static String removeFirstDash(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        int index = str.indexOf('-');
        if (index != -1 && index < str.length() - 1) {
            return str.substring(index + 1);
        }
        return str;
    }

}
