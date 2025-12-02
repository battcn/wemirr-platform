package com.wemirr.platform.ai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.ai.domain.entity.VectorMetadata;
import com.wemirr.platform.ai.repository.VectorMetadataMapper;
import com.wemirr.platform.ai.service.VectorMetadataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 向量元数据服务实现类
 *
 * @author xJh
 * @since 2025/10/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VectorMetadataServiceImpl extends SuperServiceImpl<VectorMetadataMapper, VectorMetadata> implements VectorMetadataService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveVectorMetadata(String vectorId, Long kbId, Long itemId, Long chunkId, Map<String, Object> metadata) {
        VectorMetadata vectorMetadata = VectorMetadata.builder()
                .vectorId(vectorId)
                .kbId(kbId)
                .itemId(itemId)
                .chunkId(chunkId)
                .metadata(metadata)
                .deleted(false)
                .build();
        
        baseMapper.insert(vectorMetadata);
        return vectorMetadata.getId();
    }

    @Override
    public VectorMetadata findByVectorId(String vectorId) {
        LambdaQueryWrapper<VectorMetadata> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VectorMetadata::getVectorId, vectorId)
                .eq(VectorMetadata::getDeleted, false);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<VectorMetadata> findByKbId(Long kbId) {
        LambdaQueryWrapper<VectorMetadata> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VectorMetadata::getKbId, kbId)
                .eq(VectorMetadata::getDeleted, false);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<VectorMetadata> findByItemId(Long itemId) {
        LambdaQueryWrapper<VectorMetadata> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VectorMetadata::getItemId, itemId)
                .eq(VectorMetadata::getDeleted, false);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public VectorMetadata findByChunkId(Long chunkId) {
        LambdaQueryWrapper<VectorMetadata> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VectorMetadata::getChunkId, chunkId)
                .eq(VectorMetadata::getDeleted, false);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByVectorId(String vectorId) {
        LambdaUpdateWrapper<VectorMetadata> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(VectorMetadata::getVectorId, vectorId)
                .set(VectorMetadata::getDeleted, true);
        return baseMapper.update(null, wrapper) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByKbId(Long kbId) {
        LambdaUpdateWrapper<VectorMetadata> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(VectorMetadata::getKbId, kbId)
                .set(VectorMetadata::getDeleted, true);
        return baseMapper.update(null, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByItemId(Long itemId) {
        LambdaUpdateWrapper<VectorMetadata> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(VectorMetadata::getItemId, itemId)
                .set(VectorMetadata::getDeleted, true);
        return baseMapper.update(null, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(List<VectorMetadata> metadataList) {
        if (metadataList == null || metadataList.isEmpty()) {
            return ;
        }
        
        // 设置默认值
        metadataList.forEach(metadata -> {
            if (metadata.getDeleted() == null) {
                metadata.setDeleted(false);
            }
        });
        
         baseMapper.insertBatch(metadataList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMetadata(String vectorId, Map<String, Object> metadata) {
        LambdaUpdateWrapper<VectorMetadata> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(VectorMetadata::getVectorId, vectorId)
                .set(VectorMetadata::getMetadata, metadata);
        return baseMapper.update(null, wrapper) > 0;
    }
}
