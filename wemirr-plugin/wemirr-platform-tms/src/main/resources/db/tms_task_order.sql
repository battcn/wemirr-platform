CREATE TABLE `tms_task_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `task_id` bigint NOT NULL DEFAULT '0' COMMENT '调度计划ID(tms_task.id)',
  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID(tms_order.id)',
  `source_id` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
  `locked` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
  `create_by` bigint NOT NULL COMMENT '创建人ID',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `tos_task_id` (`task_id`) USING BTREE,
  KEY `tos_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='调度订单关联表';