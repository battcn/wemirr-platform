CREATE TABLE `tms_order_event_file` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID(tms_order.id)',
  `event_id` bigint NOT NULL DEFAULT '0' COMMENT '订单节点ID(tms_order_event.id)',
  `file_id` bigint NOT NULL DEFAULT '0' COMMENT '订单节点ID(tms_order_event.id)',
  `source_id` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
  `locked` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
  `created_by` bigint NOT NULL COMMENT '创建人ID',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `event_order_id` (`order_id`) USING BTREE,
  KEY `event_event_id` (`event_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='订单节点文件表';