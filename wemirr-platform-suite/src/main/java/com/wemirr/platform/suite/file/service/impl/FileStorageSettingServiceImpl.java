package com.wemirr.platform.suite.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.dto.resp.FileStorageSettingPageResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingPageReq;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingSaveReq;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;
import com.wemirr.platform.suite.file.event.StorageSettingTemplate;
import com.wemirr.platform.suite.file.repository.FileStorageSettingMapper;
import com.wemirr.platform.suite.file.service.FileStorageSettingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Service
@AllArgsConstructor
public class FileStorageSettingServiceImpl extends SuperServiceImpl<FileStorageSettingMapper, FileStorageSetting> implements FileStorageSettingService {

    private final AuthenticationContext context;
    private final StorageSettingTemplate storageSettingTemplate;

    @Override
    public void create(FileStorageSettingSaveReq req) {
        // 获取当前租户ID
        Long tenantId = context.tenantId();
        // 检查平台名称是否已存在
        Long count = this.baseMapper.selectCount(Wraps.<FileStorageSetting>lbQ().eq(FileStorageSetting::getType, req.getType())
                .eq(FileStorageSetting::getBucketName, req.getBucketName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("令牌桶已存在");
        }
        // 复制属性并设置租户ID与平台名称
        FileStorageSetting setting = BeanUtil.toBean(req, FileStorageSetting.class);
        setting.setPlatform(StrUtil.join("-", req.getType(), req.getBucketName()));
        // 保存存储配置
        this.baseMapper.insert(setting);
        // 更新存储配置状态
        if (setting.getStatus()) {
            this.baseMapper.update(FileStorageSetting.builder().status(false).build(), Wraps.<FileStorageSetting>lbQ()
                    .ne(FileStorageSetting::getId, setting.getId()).eq(FileStorageSetting::getTenantId, tenantId));
        }
        this.storageSettingTemplate.publish(setting, 1);
    }


    @Override
    public void delete(Long id) {
        FileStorageSetting setting = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("配置不存在"));
        if (setting.getStatus()) {
            throw CheckedException.badRequest("该平台名称已启用，无法删除");
        } else {
            this.removeById(id);
        }
        this.storageSettingTemplate.publish(setting, 3);
    }

    @Override
    public void modify(Long id, FileStorageSettingSaveReq req) {
        Optional.ofNullable(baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("配置不存在"));
        // 获取当前租户ID
        Long tenantId = context.tenantId();
        // 检查平台名称是否已存在
        Long count = this.baseMapper.selectCount(Wraps.<FileStorageSetting>lbQ().ne(FileStorageSetting::getId, id)
                .eq(FileStorageSetting::getType, req.getType()).eq(FileStorageSetting::getBucketName, req.getBucketName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("令牌桶已存在");
        }
        FileStorageSetting bean = BeanUtilPlus.toBean(id, req, FileStorageSetting.class);
        bean.setTenantId(tenantId);
        bean.setPlatform(StrUtil.join("-", req.getType(), req.getBucketName()));
        // 更新存储配置状态
        if (req.getStatus()) {
            baseMapper.update(FileStorageSetting.builder().status(false).build(), Wraps.<FileStorageSetting>lbQ()
                    .ne(FileStorageSetting::getId, id).eq(FileStorageSetting::getTenantId, tenantId));
        }
        // 保存存储配置
        this.baseMapper.updateById(bean);
        this.storageSettingTemplate.publish(bean, 2);
    }

    @Override
    public IPage<FileStorageSettingPageResp> pageList(FileStorageSettingPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<FileStorageSetting>lbQ()
                        .eq(FileStorageSetting::getStatus, req.getStatus())
                        .eq(FileStorageSetting::getType, req.getType()))
                .convert(x -> BeanUtil.toBean(x, FileStorageSettingPageResp.class));
    }
}
