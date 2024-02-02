/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wemirr.framework.storage.properties;

import com.wemirr.framework.storage.cloud.qiniu.QiNiuRegion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.wemirr.framework.storage.StorageOperation.OSS_CONFIG_PREFIX_QINIU;

/**
 * <a href="https://developer.qiniu.com/kodo/manual/1206/put-policy">上传策略配置</a>
 *
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = OSS_CONFIG_PREFIX_QINIU)
public class QiNiuStorageProperties extends BaseStorageProperties {

    /**
     * 存储区域
     **/
    private QiNiuRegion region;

    /**
     * 上传策略是否支持额外参数 默认true不支持
     **/
    private Boolean strict = false;

    /**
     * 不同的存储区域可以定制不同的上传策略,如果没有配置则使用上面默认配置
     **/
    private Map<String, QiNiuStrategy> strategies = new HashMap<>();

    @Data
    public static class QiNiuStrategy {

        private String domain;
        private String scope;
        private String keyPrefix;
        /**
         * 若为 1，表示允许用户上传以 scope 的 keyPrefix 为前缀的文件。
         **/
        private Boolean prefixScope;

        /**
         * 上传凭证有效截止时间。Unix时间戳，单位为秒。该截止时间为上传完成后，在七牛空间生成文件的校验时间，而非上传的开始时间，一般建议设置
         * 为上传开始时间 + 3600s，用户可根据具体的业务场景对凭证截止时间进行调整。
         **/
        private Duration deadline;

        /**
         * 限定为新增语意。如果设置为非 0 值，则无论 scope 设置为什么形式，仅能以新增模式上传文件。
         **/
        private Boolean insertOnly;

        /**
         * 唯一属主标识。特殊场景下非常有用，例如根据 App-Client 标识给图片或视频打水印。
         **/
        private String endUser;

        /**
         * Web 端文件上传成功后，浏览器执行 303 跳转的 URL。通常用于表单上传。
         * 文件上传成功后会跳转到 returnUrl?upload_ret=queryString，queryString 包含returnBody 内容。
         * 如不设置 returnUrl，则直接将 returnBody 的内容返回给客户端。
         **/
        private String returnUrl;

        /**
         * 上传成功后，自定义七牛云最终返回給上传端（在指定 returnUrl 时是携带在跳转路径参数中）的数据。
         * 支持魔法变量和自定义变量。returnBody要求是合法的 JSON 文本。
         * 例如 {"key": $(key),"hash": $(etag), "w": $(imageInfo.width), "h": $(imageInfo.height)}。
         **/
        private String returnBody;

        /**
         * 上传成功后，七牛云向业务服务器发送 POST 请求的 URL。必须是公网上可以正常进行 POST 请求并能响应 HTTP/1.1 200 OK 的有效 URL。
         * 另外，为了给客户端有一致的体验，我们要求callbackUrl 返回包 Content-Type 为 "application/json"，
         * 即返回的内容必须是合法的 JSON 文本。出于高可用的考虑，本字段允许设置多个 callbackUrl（用英文符号 ;分隔），
         * 在前一个 callbackUrl 请求失败的时候会依次重试下一个 callbackUrl。
         * 一个典型例子是：http://ip1/callback;http://ip2/callback， 并同时指定下面的callbackHost 字段。
         * 在 callbackUrl 中使用 ip 的好处是减少对 dns 解析的依赖， 可改善回调的性能和稳定性。
         * 指定 callbackUrl，必须指定 callbackbody，且值不能为空。
         **/
        private String callbackUrl;

        /**
         * 上传成功后，七牛云向业务服务器发送回调通知时的 Host 值。
         * 与 callbackUrl 配合使用，仅当设置了 callbackUrl 时才有效。
         **/
        private String callbackHost;

        /**
         * 上传成功后，七牛云向业务服务器发送 Content-Type: application/x-www-form-urlencoded 的 POST 请求。
         * 业务服务器可以通过直接读取请求的 query来获得该字段，支持魔法变量和自定义变量。
         * callbackBody 要求是合法的 url query string。
         * 例如key=$(key)&hash=$(etag)&w=$(imageInfo.width)&h=$(imageInfo.height)。
         * 如果callbackBodyType指定为application/json，则callbackBody应为json格式，
         * 例如:{"key":"$(key)","hash":"$(etag)","w":"$(imageInfo.width)","h":"$(imageInfo.height)"}。
         **/
        private String callbackBody;

        /**
         * 上传成功后，七牛云向业务服务器发送回调通知 callbackBody 的 Content-Type。
         * 默认为 application/x-www-form-urlencoded，也可设置为 application/json。
         **/
        private String callbackBodyType;

        /**
         * 资源上传成功后触发执行的预转持久化处理指令列表。
         * 每个指令是一个 API 规格字符串，多个指令用;分隔。请参阅persistenOps详解与示例。
         * 同时添加 persistentPipeline字段，使用专用队列处理，请参阅persistentPipeline。
         **/
        private String persistentOps;

        /**
         * 接收持久化处理结果通知的 URL。必须是公网上可以正常进行 POST 请求并能响应 HTTP/1.1 200 OK 的有效 URL。
         * 该 URL 获取的内容和持久化处理状态查询的处理结果一致。 发送 body 格式是Content-Type 为 application/json 的 POST 请求，
         * 需要按照读取流的形式读取请求的 body 才能获取。
         **/
        private String persistentNotifyUrl;

        /**
         * 转码队列名。资源上传成功后，触发转码时指定独立的队列进行转码。 为空则表示使用公用队列，处理速度比较慢。建议使用专用队列。
         **/
        private String persistentPipeline;

        /**
         * 自定义资源名。支持魔法变量和自定义变量。这个字段仅当用户上传的时候没有主动指定 key 的时候起作用。
         **/
        private String saveKey;

        /**
         * 限定上传文件大小最小值，单位Byte。
         **/
        private Long sizeMin;

        /**
         * 限定上传文件大小最大值，单位Byte。超过限制上传文件大小的最大值会被判为上传失败，返回 413 状态码。
         **/
        private Long sizeMax;

        /**
         * 开启 MimeType 侦测功能。设为非 0 值，则忽略上传端传递的文件 MimeType 信息，使用七牛服务器侦测内容后的判断结果。
         * 默认设为 0 值，如上传端指定了 MimeType 则直接使用该值，否则按如下顺序侦测
         * MimeType 值： 1. 检查文件扩展名； 2. 检查 Key 扩展名； 3. 侦测内容。
         * 如不能侦测出正确的值，会默认使用 application/octet-stream。
         **/
        private Boolean detectMime;

        /**
         * 限定用户上传的文件类型。指定本字段值，七牛服务器会侦测文件内容以判断 MimeType，再用判断值跟指定值进行匹配，
         * 匹配成功则允许上传，匹配失败则返回 403 状态码。示例： image/*表示只允许上传图片类型
         * image/jpeg;image/png表示只允许上传jpg和png类型的图片 !application/json;text/plain表示禁止上传json文本和纯文本。
         * 注意最前面的感叹号！
         **/
        private String mimeLimit;

        /**
         * 文件存储类型。0 为普通存储（默认），1 为低频存储。
         **/
        private Boolean fileType;
    }

}
