package com.wemirr.platform.suite.file.event;


import java.util.List;

/**
 * @author xiao1
 * @date 2024-12
 */
public interface RedisStorageConfigEventPublish {

    void publish(RedisStorageConfigEvent message);

}
