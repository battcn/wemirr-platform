CREATE TABLE `common_i18n_data` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '唯一标识 = 业务:关键词',
    `language` varchar(10) DEFAULT NULL COMMENT '语言',
    `message` varchar(255) DEFAULT NULL COMMENT '文本值，可以使用 {} 加角标，作为占位符',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态0：未删除，1：已删除',
    `created_by` bigint DEFAULT '0' COMMENT '创建人id',
    `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
    `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
    `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_laguage_keyword` (`language`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国际化信息';