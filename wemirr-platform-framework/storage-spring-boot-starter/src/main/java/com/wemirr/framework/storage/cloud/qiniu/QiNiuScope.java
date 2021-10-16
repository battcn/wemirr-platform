package com.wemirr.framework.storage.cloud.qiniu;

/**
 * 指定上传的目标资源空间 Bucket 和资源键 Key（最大为 750 字节）。
 * 有三种格式：
 * <p> bucket ：表示允许用户上传文件到指定的 bucket。在这种格式下文件只能新增，若已存在同名资源（且文件内容/etag不一致），
 * 上传会失败；若已存在资源的内容/etag一致，则上传会返回成功。</p>
 *
 * <p>bucket:key，表示只允许用户上传指定 key 的文件。在这种格式下文件默认允许修改，若已存在同名资源则会被覆盖。
 * 如果只希望上传指定 key 的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1。</p>
 *
 * <p> bucket:keyPrefix，表示只允许用户上传指定以 keyPrefix 为前缀的文件，
 * 当且仅当 isPrefixScope 字段为 1 时生效，isPrefixScope 为 1 时无法覆盖上传。</p>
 *
 * @author Levin
 */
public enum QiNiuScope {


    /**
     * 默认
     */
    DEFAULT,
    REPLACE,
    PREFIXAL;
}