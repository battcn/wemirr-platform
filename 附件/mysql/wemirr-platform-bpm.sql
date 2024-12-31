/*
 Navicat Premium Data Transfer

 Source Server         : 119.3.249.86-[v3-dev]
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : 119.3.249.86:3306
 Source Schema         : wemirr-platform-bpm

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 31/12/2024 21:47:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wp_process_category
-- ----------------------------
DROP TABLE IF EXISTS `wp_process_category`;
CREATE TABLE `wp_process_category` (
  `id` bigint NOT NULL COMMENT '类别id',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类别编码(唯一)',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类别名称',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类别状态:0-禁用,1-启用。默认0',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `icon` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类别logo',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0=未删除;1=已删除）',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_code` (`code`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='流程类别';

-- ----------------------------
-- Records of wp_process_category
-- ----------------------------
BEGIN;
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 1, 'CW', '财务管理', 1, '财务管理', NULL, 1, b'0', NULL, '2023-10-17 04:53:29', NULL, NULL, NULL);
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, 1, 'PROJECT', '项目管理', 1, '项目管理', NULL, 1, b'0', NULL, '2023-10-17 04:54:02', NULL, NULL, NULL);
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (4, 1, 'ACCOUNT', '账号申请', 1, '账号申请', NULL, 1, b'0', NULL, '2023-10-17 04:54:22', NULL, NULL, NULL);
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (5, 1, 'DATA', '数字研发', 1, '数字研发', NULL, 1, b'0', NULL, '2023-10-17 04:54:40', NULL, NULL, NULL);
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (6, 1, 'OTHER', '其它申请', 1, '其它申请', NULL, NULL, b'0', NULL, '2023-10-17 04:55:06', NULL, NULL, NULL);
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (7, 1, 'FW', '法务管理', 1, '法务管理', 'ant-design:align-center-outlined', NULL, b'0', NULL, '2023-10-17 04:55:27', 1, '长风一梦8888', '2024-10-11 15:15:37');
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1704064840869396481, 1, 'HR', '人事专项', 1, '人事专项', NULL, 1, b'0', '长风一梦8888', '2023-09-19 09:28:12', NULL, NULL, NULL);
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1816354843372052481, 1, 'TEST', '测试ICON', 1, '测试ICON', 'ion:snow-outline', 1, b'0', '长风一梦8888', '2024-07-25 14:08:36', 1, '长风一梦8888', '2024-07-25 14:16:06');
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1822905204253671425, 3, 'PAY', '支付流程', 1, '哈哈哈', 'ant-design:alipay-circle-filled', 1816052061532356609, b'0', '马化腾', '2024-08-12 15:57:23', 1, '平台管理员', '2024-12-26 15:08:29');
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1857314409126207490, 1, 'adsfdsaf', 'adsfdsaf', 1, 'dasfdsaf', 'ant-design:amazon-circle-filled', 1, b'1', '长风一梦8888', '2024-11-15 14:47:17', 1, '平台管理员', '2024-12-26 15:04:39');
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1859870286605623297, 1, '456', '456', 1, '456', 'ant-design:alipay-circle-filled', 1, b'1', '长风一梦8888', '2024-11-22 16:03:26', 1, '长风一梦8888', '2024-12-06 01:58:42');
INSERT INTO `wp_process_category` (`id`, `tenant_id`, `code`, `name`, `status`, `description`, `icon`, `created_by`, `deleted`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1866504202607304706, 1, 'test', 'test', 1, 'test', 'ant-design:account-book-filled', 1, b'1', '长风一梦8888', '2024-12-10 23:24:15', 1, '平台管理员', '2024-12-26 15:04:37');
COMMIT;

-- ----------------------------
-- Table structure for wp_process_deploy_history
-- ----------------------------
DROP TABLE IF EXISTS `wp_process_deploy_history`;
CREATE TABLE `wp_process_deploy_history` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '历史模型id',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户编码',
  `tenant_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户名称',
  `model_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模型id',
  `process_definition_key` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程定义key,如果出现池会有多个，逗号隔开',
  `process_definition_id` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程定义ids,如果出现池会有多个，逗号隔开',
  `diagram_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT 'bpmn模型(转换为base64存储)',
  `deployment_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部署名称',
  `diagram_names` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模型名称',
  `deployment_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部署id',
  `resource_names` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源名称',
  `resource_ids` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源ids',
  `have_pool` tinyint(1) DEFAULT '0' COMMENT '是否pool模型,0-不是,1-是。默认0',
  `deployment_time` datetime DEFAULT NULL COMMENT '部署时间',
  `process_category_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模型类别ID',
  `process_category_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模型类别编码',
  `process_category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模型类别名字',
  `version` int NOT NULL DEFAULT '1' COMMENT '当前模型版本',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0=未删除;1=已删除）',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态:0-正常,1-已删除,默认0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_deployment_name` (`deployment_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='流程模型历史';

-- ----------------------------
-- Records of wp_process_deploy_history
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wp_process_instance_ext
-- ----------------------------
DROP TABLE IF EXISTS `wp_process_instance_ext`;
CREATE TABLE `wp_process_instance_ext` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '主键ID',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `diagram_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `diagram_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `proc_def_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程定义ID',
  `proc_def_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程定义名称(流程名称)',
  `proc_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程定义key',
  `proc_inst_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程实例ID',
  `proc_inst_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程实例名称',
  `proc_inst_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `proc_inst_category_id` bigint DEFAULT NULL,
  `proc_inst_category_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `proc_inst_category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `proc_inst_activate` tinyint(1) DEFAULT NULL COMMENT '流程实例是否已挂起0：激活，1：挂起',
  `proc_inst_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程实例状态  处理中， 已完成， 已作废',
  `proc_inst_start_time` datetime DEFAULT NULL COMMENT '流程开始时间',
  `proc_inst_end_time` datetime DEFAULT NULL COMMENT '流程结束时间',
  `business_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务KEY（如：ID或者编号）\n',
  `business_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务组（如：表名）\n',
  `form_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '流程实例绑定的数据',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0=未删除;1=已删除）',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='camunda扩展表 流程实例扩展信息表';

-- ----------------------------
-- Records of wp_process_instance_ext
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wp_process_model
-- ----------------------------
DROP TABLE IF EXISTS `wp_process_model`;
CREATE TABLE `wp_process_model` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模型id',
  `code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模型编码',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `diagram_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `diagram_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT 'bpmn模型(转换为base64存储)',
  `diagram_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模型名称,如果出现池会有多个，逗号隔开',
  `definition_key` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程定义key,如果出现池会有多个，逗号隔开',
  `definition_id` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '流程定义ids,如果出现池会有多个，逗号隔开',
  `category_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模型类别ID',
  `category_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '模型状态:0-未部署,1-已经部署,2-新版本待部署,参考常量字段:ModelStateType',
  `have_pool` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否pool模型,0-不是,1-是。默认0',
  `resource_names` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源名称',
  `resource_ids` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源ids',
  `deploy_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部署id',
  `deploy_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部署名称',
  `deploy_time` datetime DEFAULT NULL COMMENT '部署时间',
  `version` int NOT NULL DEFAULT '1' COMMENT '当前模型版本',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `deleted` bit(1) DEFAULT b'0',
  `form_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表单key',
  `unique_help` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '1' COMMENT '唯一索引帮助字段,默认1，如果删除该值为主键',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_process_category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='流程模型管理';

-- ----------------------------
-- Records of wp_process_model
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wp_process_model_form
-- ----------------------------
DROP TABLE IF EXISTS `wp_process_model_form`;
CREATE TABLE `wp_process_model_form` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模型id',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `model_id` bigint NOT NULL COMMENT '模型id',
  `form_script` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表单配置信息',
  `form_schemas` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '表单字段信息',
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0=未删除;1=已删除）',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='流程模型管理';

-- ----------------------------
-- Records of wp_process_model_form
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wp_process_task_comment
-- ----------------------------
DROP TABLE IF EXISTS `wp_process_task_comment`;
CREATE TABLE `wp_process_task_comment` (
  `id` bigint NOT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `type` int DEFAULT '0' COMMENT '0=评论;1=审批',
  `proc_inst_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_task_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `attachment` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL,
  `created_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='审核内容与评论';

-- ----------------------------
-- Records of wp_process_task_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wp_process_task_ext
-- ----------------------------
DROP TABLE IF EXISTS `wp_process_task_ext`;
CREATE TABLE `wp_process_task_ext` (
  `id` bigint NOT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `proc_def_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_def_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_def_key` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_category_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_category_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_category_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_inst_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_inst_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_task_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_task_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_task_def_key` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `assignee` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `initiator_id` bigint DEFAULT NULL,
  `initiator_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `task_instance_start_time` datetime DEFAULT NULL,
  `task_instance_end_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `delete_reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `created_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='流程历史任务表';

-- ----------------------------
-- Records of wp_process_task_ext
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wp_process_task_his
-- ----------------------------
DROP TABLE IF EXISTS `wp_process_task_his`;
CREATE TABLE `wp_process_task_his` (
  `id` bigint NOT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `proc_inst_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_task_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_task_def_key` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_task_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `proc_task_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `activity_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `attachment` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `created_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='流程历史任务表';

-- ----------------------------
-- Records of wp_process_task_his
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
