/*
 Navicat Premium Data Transfer

 Source Server         : localhost-docker
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : v4-flow

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 09/10/2025 15:50:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flow_category
-- ----------------------------
DROP TABLE IF EXISTS `flow_category`;
CREATE TABLE `flow_category` (
  `id` bigint NOT NULL,
  `icon` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `name` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '类目名称',
  `status` bit(1) DEFAULT b'0' COMMENT '状态',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '备注信息',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除标识',
  `create_by` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_by` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人id',
  `last_modify_name` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='流程分类';

-- ----------------------------
-- Records of flow_category
-- ----------------------------
BEGIN;
INSERT INTO `flow_category` (`id`, `icon`, `name`, `status`, `description`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1959130054009225218, 'carbon:apps', '基础流程', b'1', '基础流程大类', NULL, b'0', '1', '平台管理员', '2025-08-23 13:46:18', '1', '平台管理员', '2025-08-23 13:47:59');
INSERT INTO `flow_category` (`id`, `icon`, `name`, `status`, `description`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1959168044773302273, 'carbon:add-comment', '基础分类', b'1', '基础分类基础分类', 1, b'0', '1', '平台管理员', '2025-08-23 16:17:16', '1', '平台管理员', '2025-09-08 17:13:50');
COMMIT;

-- ----------------------------
-- Table structure for flow_definition
-- ----------------------------
DROP TABLE IF EXISTS `flow_definition`;
CREATE TABLE `flow_definition` (
  `id` bigint NOT NULL COMMENT '主键id',
  `flow_code` varchar(40) COLLATE utf8mb3_bin NOT NULL COMMENT '流程编码',
  `flow_name` varchar(100) COLLATE utf8mb3_bin NOT NULL COMMENT '流程名称',
  `category` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '流程类别',
  `version` varchar(20) COLLATE utf8mb3_bin NOT NULL COMMENT '流程版本',
  `is_publish` smallint NOT NULL DEFAULT '0' COMMENT '是否发布（0未发布 1已发布 9失效）',
  `form_custom` char(1) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审批表单是否自定义（Y是 N否）',
  `form_path` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审批表单路径',
  `activity_status` smallint NOT NULL DEFAULT '1' COMMENT '流程激活状态（0挂起 1激活）',
  `listener_type` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '监听器类型',
  `listener_path` text COLLATE utf8mb3_bin COMMENT '监听器路径',
  `ext` text COLLATE utf8mb3_bin COMMENT '扩展字段，预留给业务系统使用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '0' COMMENT '删除标志',
  `tenant_id` varchar(40) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '租户id',
  `model_value` varchar(40) COLLATE utf8mb3_bin NOT NULL COMMENT '设计器模型（CLASSICS经典模型 MIMIC仿钉钉模型）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='流程定义表';

-- ----------------------------
-- Records of flow_definition
-- ----------------------------
BEGIN;
INSERT INTO `flow_definition` (`id`, `flow_code`, `flow_name`, `category`, `version`, `is_publish`, `form_custom`, `form_path`, `activity_status`, `listener_type`, `listener_path`, `ext`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`, `model_value`) VALUES (1965289765146341378, 'HH', '测试-普通流程', '1959168044773302273', '1', 1, 'N', NULL, 1, '', '', NULL, '2025-09-09 13:42:48', NULL, '2025-10-09 14:56:08', '1', '0', '1', 'MIMIC');
COMMIT;

-- ----------------------------
-- Table structure for flow_his_task
-- ----------------------------
DROP TABLE IF EXISTS `flow_his_task`;
CREATE TABLE `flow_his_task` (
  `id` bigint NOT NULL COMMENT '主键id',
  `definition_id` bigint NOT NULL COMMENT '对应flow_definition表的id',
  `instance_id` bigint NOT NULL COMMENT '对应flow_instance表的id',
  `task_id` bigint NOT NULL COMMENT '对应flow_task表的id',
  `node_code` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '开始节点编码',
  `node_name` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '开始节点名称',
  `node_type` smallint DEFAULT NULL COMMENT '开始节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
  `target_node_code` varchar(200) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '目标节点编码',
  `target_node_name` varchar(200) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '结束节点名称',
  `approver` varchar(40) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审批者',
  `cooperate_type` smallint NOT NULL COMMENT '协作方式(1审批 2转办 3委派 4会签 5票签 6加签 7减签)',
  `collaborator` text COLLATE utf8mb3_bin COMMENT '协作人',
  `skip_type` varchar(10) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '流转类型（PASS通过 REJECT退回 NONE无动作）',
  `flow_status` varchar(20) COLLATE utf8mb3_bin NOT NULL COMMENT '流程状态（0待提交 1审批中 2审批通过 4终止 5作废 6撤销 8已完成 9已退回 10失效 11拿回）',
  `form_custom` char(1) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审批表单是否自定义（Y是 N否）',
  `form_path` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审批表单路径',
  `ext` longtext COLLATE utf8mb3_bin COMMENT '扩展字段，预留给业务系统使用',
  `message` text COLLATE utf8mb3_bin COMMENT '审批意见',
  `tenant_id` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '租户id',
  `variable` longtext COLLATE utf8mb3_bin COMMENT '任务变量',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '0' COMMENT '删除标志',
  `create_by` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '任务开始时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '审批完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='历史任务记录表';

-- ----------------------------
-- Records of flow_his_task
-- ----------------------------
BEGIN;
INSERT INTO `flow_his_task` (`id`, `definition_id`, `instance_id`, `task_id`, `node_code`, `node_name`, `node_type`, `target_node_code`, `target_node_name`, `approver`, `cooperate_type`, `collaborator`, `skip_type`, `flow_status`, `form_custom`, `form_path`, `ext`, `message`, `tenant_id`, `variable`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1976180357854269442, 1965289765146341378, 1976180357850075137, 1976180357854269441, 'startId', '开始', 0, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', '管理员审批', '1', 1, NULL, 'PASS', '2', NULL, NULL, NULL, NULL, '1', '{\n  \"reson\" : \"1\",\n  \"name\" : \"张三\",\n  \"day\" : \"10\",\n  \"checkbox_gcx7i9d9\" : [ \"3\" ],\n  \"remark\" : \"测试一把\",\n  \"ext.instanceName\" : \"测试-普通流程\",\n  \"ext.businessKey\" : \"1759993087407\",\n  \"ext.businessGroup\" : \"DEFAULT\",\n  \"ext.formData\" : {\n    \"reson\" : \"1\",\n    \"name\" : \"张三\",\n    \"day\" : \"10\",\n    \"checkbox_gcx7i9d9\" : [ \"3\" ],\n    \"remark\" : \"测试一把\"\n  },\n  \"approverName\" : \"平台管理员\"\n}', '0', NULL, '2025-10-09 14:58:08', NULL, '2025-10-09 14:58:08');
INSERT INTO `flow_his_task` (`id`, `definition_id`, `instance_id`, `task_id`, `node_code`, `node_name`, `node_type`, `target_node_code`, `target_node_name`, `approver`, `cooperate_type`, `collaborator`, `skip_type`, `flow_status`, `form_custom`, `form_path`, `ext`, `message`, `tenant_id`, `variable`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1976180464364425218, 1965289765146341378, 1976180357850075137, 1976180357854269443, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', '管理员审批', 1, 'e974c61e-778a-4521-ae6d-90c82c4b7eb7', '测试审批', '1', 1, NULL, 'PASS', '2', 'N', NULL, 'PASS', '我还能说什么', '1', '{\n  \"checkbox_gcx7i9d9\" : [ \"3\" ],\n  \"ext.businessGroup\" : \"DEFAULT\",\n  \"name\" : \"张三\",\n  \"reson\" : \"1\",\n  \"remark\" : \"测试一把\",\n  \"ext.formData\" : {\n    \"reson\" : \"1\",\n    \"name\" : \"张三\",\n    \"day\" : \"10\",\n    \"checkbox_gcx7i9d9\" : [ \"3\" ],\n    \"remark\" : \"测试一把\"\n  },\n  \"ext.instanceName\" : \"测试-普通流程\",\n  \"ext.businessKey\" : \"1759993087407\",\n  \"day\" : \"10\",\n  \"approverName\" : \"平台管理员\"\n}', '0', NULL, '2025-10-09 14:58:08', NULL, '2025-10-09 14:58:33');
INSERT INTO `flow_his_task` (`id`, `definition_id`, `instance_id`, `task_id`, `node_code`, `node_name`, `node_type`, `target_node_code`, `target_node_name`, `approver`, `cooperate_type`, `collaborator`, `skip_type`, `flow_status`, `form_custom`, `form_path`, `ext`, `message`, `tenant_id`, `variable`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1976180548523134979, 1965289765146341378, 1976180548518940674, 1976180548523134978, 'startId', '开始', 0, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', '管理员审批', '1', 1, NULL, 'PASS', '2', NULL, NULL, NULL, NULL, '1', '{\n  \"reson\" : \"2\",\n  \"name\" : \"李四\",\n  \"day\" : \"5\",\n  \"checkbox_gcx7i9d9\" : [ \"3\" ],\n  \"remark\" : \"醉了真的\",\n  \"ext.instanceName\" : \"测试-普通流程\",\n  \"ext.businessKey\" : \"1759993133019\",\n  \"ext.businessGroup\" : \"DEFAULT\",\n  \"ext.formData\" : {\n    \"reson\" : \"2\",\n    \"name\" : \"李四\",\n    \"day\" : \"5\",\n    \"checkbox_gcx7i9d9\" : [ \"3\" ],\n    \"remark\" : \"醉了真的\"\n  },\n  \"approverName\" : \"平台管理员\"\n}', '0', NULL, '2025-10-09 14:58:53', NULL, '2025-10-09 14:58:53');
COMMIT;

-- ----------------------------
-- Table structure for flow_instance
-- ----------------------------
DROP TABLE IF EXISTS `flow_instance`;
CREATE TABLE `flow_instance` (
  `id` bigint NOT NULL COMMENT '主键id',
  `definition_id` bigint NOT NULL COMMENT '对应flow_definition表的id',
  `business_id` varchar(40) COLLATE utf8mb3_bin NOT NULL COMMENT '业务id',
  `node_type` smallint NOT NULL COMMENT '节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
  `node_code` varchar(40) COLLATE utf8mb3_bin NOT NULL COMMENT '流程节点编码',
  `node_name` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '流程节点名称',
  `variable` longtext COLLATE utf8mb3_bin COMMENT '任务变量',
  `flow_status` varchar(20) COLLATE utf8mb3_bin NOT NULL COMMENT '流程状态（0待提交 1审批中 2审批通过 4终止 5作废 6撤销 8已完成 9已退回 10失效 11拿回）',
  `activity_status` smallint NOT NULL COMMENT '流程激活状态（0挂起 1激活）',
  `def_json` longtext COLLATE utf8mb3_bin COMMENT '流程定义json',
  `create_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人',
  `ext` text COLLATE utf8mb3_bin COMMENT '扩展字段，预留给业务系统使用',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '0' COMMENT '删除标志',
  `tenant_id` varchar(40) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='流程实例表';

-- ----------------------------
-- Records of flow_instance
-- ----------------------------
BEGIN;
INSERT INTO `flow_instance` (`id`, `definition_id`, `business_id`, `node_type`, `node_code`, `node_name`, `variable`, `flow_status`, `activity_status`, `def_json`, `create_by`, `create_time`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1976180357850075137, 1965289765146341378, '1759993087407', 1, 'e974c61e-778a-4521-ae6d-90c82c4b7eb7', '测试审批', '{\n  \"reson\" : \"1\",\n  \"name\" : \"张三\",\n  \"day\" : \"10\",\n  \"checkbox_gcx7i9d9\" : [ \"3\" ],\n  \"remark\" : \"测试一把\",\n  \"ext.instanceName\" : \"测试-普通流程\",\n  \"ext.businessKey\" : \"1759993087407\",\n  \"ext.businessGroup\" : \"DEFAULT\",\n  \"ext.formData\" : {\n    \"reson\" : \"1\",\n    \"name\" : \"张三\",\n    \"day\" : \"10\",\n    \"checkbox_gcx7i9d9\" : [ \"3\" ],\n    \"remark\" : \"测试一把\"\n  },\n  \"approverName\" : \"平台管理员\"\n}', '1', 1, '{\n  \"flowCode\" : \"HH\",\n  \"flowName\" : \"测试-普通流程\",\n  \"modelValue\" : \"MIMIC\",\n  \"category\" : \"1959168044773302273\",\n  \"version\" : \"1\",\n  \"isPublish\" : 1,\n  \"formCustom\" : \"N\",\n  \"nodeList\" : [ {\n    \"nodeType\" : 0,\n    \"nodeCode\" : \"startId\",\n    \"nodeName\" : \"开始\",\n    \"nodeRatio\" : 0.000,\n    \"coordinate\" : \"700,100|700,100\",\n    \"ext\" : \"[]\",\n    \"status\" : 2,\n    \"extMap\" : {\n      \"办理人\" : \"平台管理员\",\n      \"办理时间\" : \"2025-10-09 14:58:07\"\n    },\n    \"skipList\" : [ {\n      \"nowNodeCode\" : \"startId\",\n      \"nextNodeCode\" : \"ad9a90f2-2f52-47f0-8200-fcae80e8d21b\",\n      \"skipType\" : \"PASS\",\n      \"coordinate\" : \"700,140;700,210\",\n      \"status\" : 2,\n      \"createBy\" : \"1\",\n      \"updateBy\" : \"1\"\n    } ],\n    \"createBy\" : \"1\",\n    \"updateBy\" : \"1\"\n  }, {\n    \"nodeType\" : 2,\n    \"nodeCode\" : \"endId\",\n    \"nodeName\" : \"结束\",\n    \"nodeRatio\" : 0.000,\n    \"coordinate\" : \"700,530|700,530\",\n    \"ext\" : \"[]\",\n    \"status\" : 0,\n    \"createBy\" : \"1\",\n    \"updateBy\" : \"1\"\n  }, {\n    \"nodeType\" : 1,\n    \"nodeCode\" : \"ad9a90f2-2f52-47f0-8200-fcae80e8d21b\",\n    \"nodeName\" : \"管理员审批\",\n    \"permissionFlag\" : \"1\",\n    \"nodeRatio\" : 0.000,\n    \"coordinate\" : \"700,250|700,250\",\n    \"ext\" : \"[]\",\n    \"status\" : 2,\n    \"extMap\" : {\n      \"待办人\" : \"平台管理员\",\n      \"办理人\" : \"平台管理员\",\n      \"办理时间\" : \"2025-10-09 14:58:33\"\n    },\n    \"skipList\" : [ {\n      \"nowNodeCode\" : \"ad9a90f2-2f52-47f0-8200-fcae80e8d21b\",\n      \"nextNodeCode\" : \"e974c61e-778a-4521-ae6d-90c82c4b7eb7\",\n      \"skipType\" : \"PASS\",\n      \"coordinate\" : \"700,290;700,360\",\n      \"status\" : 2,\n      \"createBy\" : \"1\",\n      \"updateBy\" : \"1\"\n    } ],\n    \"createBy\" : \"1\",\n    \"updateBy\" : \"1\"\n  }, {\n    \"nodeType\" : 1,\n    \"nodeCode\" : \"e974c61e-778a-4521-ae6d-90c82c4b7eb7\",\n    \"nodeName\" : \"测试审批\",\n    \"permissionFlag\" : \"2\",\n    \"nodeRatio\" : 0.000,\n    \"coordinate\" : \"700,400|700,400\",\n    \"ext\" : \"[]\",\n    \"status\" : 1,\n    \"extMap\" : {\n      \"待办人\" : \"测试管理员\"\n    },\n    \"skipList\" : [ {\n      \"nowNodeCode\" : \"e974c61e-778a-4521-ae6d-90c82c4b7eb7\",\n      \"nextNodeCode\" : \"endId\",\n      \"skipType\" : \"PASS\",\n      \"coordinate\" : \"700,440;700,510\",\n      \"status\" : 0,\n      \"createBy\" : \"1\",\n      \"updateBy\" : \"1\"\n    } ],\n    \"createBy\" : \"1\",\n    \"updateBy\" : \"1\"\n  } ],\n  \"topTextShow\" : false,\n  \"updateBy\" : \"1\"\n}', '1', '2025-10-09 14:58:08', '2025-10-09 14:58:33', '1', NULL, '0', '1');
INSERT INTO `flow_instance` (`id`, `definition_id`, `business_id`, `node_type`, `node_code`, `node_name`, `variable`, `flow_status`, `activity_status`, `def_json`, `create_by`, `create_time`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1976180548518940674, 1965289765146341378, '1759993133019', 1, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', '管理员审批', '{\n  \"reson\" : \"2\",\n  \"name\" : \"李四\",\n  \"day\" : \"5\",\n  \"checkbox_gcx7i9d9\" : [ \"3\" ],\n  \"remark\" : \"醉了真的\",\n  \"ext.instanceName\" : \"测试-普通流程\",\n  \"ext.businessKey\" : \"1759993133019\",\n  \"ext.businessGroup\" : \"DEFAULT\",\n  \"ext.formData\" : {\n    \"reson\" : \"2\",\n    \"name\" : \"李四\",\n    \"day\" : \"5\",\n    \"checkbox_gcx7i9d9\" : [ \"3\" ],\n    \"remark\" : \"醉了真的\"\n  },\n  \"approverName\" : \"平台管理员\"\n}', '1', 1, '{\n  \"flowCode\" : \"HH\",\n  \"flowName\" : \"测试-普通流程\",\n  \"modelValue\" : \"MIMIC\",\n  \"category\" : \"1959168044773302273\",\n  \"version\" : \"1\",\n  \"isPublish\" : 1,\n  \"formCustom\" : \"N\",\n  \"nodeList\" : [ {\n    \"nodeType\" : 0,\n    \"nodeCode\" : \"startId\",\n    \"nodeName\" : \"开始\",\n    \"nodeRatio\" : 0.000,\n    \"coordinate\" : \"700,100|700,100\",\n    \"ext\" : \"[]\",\n    \"status\" : 2,\n    \"extMap\" : {\n      \"办理人\" : \"平台管理员\",\n      \"办理时间\" : \"2025-10-09 14:58:53\"\n    },\n    \"skipList\" : [ {\n      \"nowNodeCode\" : \"startId\",\n      \"nextNodeCode\" : \"ad9a90f2-2f52-47f0-8200-fcae80e8d21b\",\n      \"skipType\" : \"PASS\",\n      \"coordinate\" : \"700,140;700,210\",\n      \"status\" : 2,\n      \"createBy\" : \"1\",\n      \"updateBy\" : \"1\"\n    } ],\n    \"createBy\" : \"1\",\n    \"updateBy\" : \"1\"\n  }, {\n    \"nodeType\" : 2,\n    \"nodeCode\" : \"endId\",\n    \"nodeName\" : \"结束\",\n    \"nodeRatio\" : 0.000,\n    \"coordinate\" : \"700,530|700,530\",\n    \"ext\" : \"[]\",\n    \"status\" : 0,\n    \"createBy\" : \"1\",\n    \"updateBy\" : \"1\"\n  }, {\n    \"nodeType\" : 1,\n    \"nodeCode\" : \"ad9a90f2-2f52-47f0-8200-fcae80e8d21b\",\n    \"nodeName\" : \"管理员审批\",\n    \"permissionFlag\" : \"1\",\n    \"nodeRatio\" : 0.000,\n    \"coordinate\" : \"700,250|700,250\",\n    \"ext\" : \"[]\",\n    \"status\" : 1,\n    \"extMap\" : {\n      \"待办人\" : \"平台管理员\"\n    },\n    \"skipList\" : [ {\n      \"nowNodeCode\" : \"ad9a90f2-2f52-47f0-8200-fcae80e8d21b\",\n      \"nextNodeCode\" : \"e974c61e-778a-4521-ae6d-90c82c4b7eb7\",\n      \"skipType\" : \"PASS\",\n      \"coordinate\" : \"700,290;700,360\",\n      \"status\" : 0,\n      \"createBy\" : \"1\",\n      \"updateBy\" : \"1\"\n    } ],\n    \"createBy\" : \"1\",\n    \"updateBy\" : \"1\"\n  }, {\n    \"nodeType\" : 1,\n    \"nodeCode\" : \"e974c61e-778a-4521-ae6d-90c82c4b7eb7\",\n    \"nodeName\" : \"测试审批\",\n    \"permissionFlag\" : \"2\",\n    \"nodeRatio\" : 0.000,\n    \"coordinate\" : \"700,400|700,400\",\n    \"ext\" : \"[]\",\n    \"status\" : 0,\n    \"skipList\" : [ {\n      \"nowNodeCode\" : \"e974c61e-778a-4521-ae6d-90c82c4b7eb7\",\n      \"nextNodeCode\" : \"endId\",\n      \"skipType\" : \"PASS\",\n      \"coordinate\" : \"700,440;700,510\",\n      \"status\" : 0,\n      \"createBy\" : \"1\",\n      \"updateBy\" : \"1\"\n    } ],\n    \"createBy\" : \"1\",\n    \"updateBy\" : \"1\"\n  } ],\n  \"topTextShow\" : false,\n  \"updateBy\" : \"1\"\n}', '1', '2025-10-09 14:58:53', '2025-10-09 14:58:53', '1', NULL, '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for flow_instance_ext
-- ----------------------------
DROP TABLE IF EXISTS `flow_instance_ext`;
CREATE TABLE `flow_instance_ext` (
  `id` bigint NOT NULL,
  `title` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '流程实例标题',
  `keyword` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '业务搜索关键词',
  `instance_id` bigint NOT NULL COMMENT '流程实例id',
  `business_id` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '业务id',
  `business_code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '业务编码',
  `operation_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '流程实例的来源',
  `business_type` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '来源类型',
  `attachment` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '文件',
  `variable` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '扩展属性',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '备注信息',
  `form_data` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin,
  `form_schemas` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin,
  `form_script` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin,
  `finish_time` datetime DEFAULT NULL COMMENT '任务完成时间',
  `deleted` varchar(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_by` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_by` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人id',
  `last_modify_name` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `flow_instance_ext_idx_instance_id` (`instance_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

-- ----------------------------
-- Records of flow_instance_ext
-- ----------------------------
BEGIN;
INSERT INTO `flow_instance_ext` (`id`, `title`, `keyword`, `instance_id`, `business_id`, `business_code`, `operation_type`, `business_type`, `attachment`, `variable`, `remark`, `form_data`, `form_schemas`, `form_script`, `finish_time`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1976180359238389762, '测试-普通流程', NULL, 1976180357850075137, '1759993087407', '1759993087407', 'other', NULL, NULL, NULL, NULL, '{\"reson\":\"1\",\"name\":\"张三\",\"day\":\"10\",\"checkbox_gcx7i9d9\":[\"3\"],\"remark\":\"测试一把\"}', '[{\"type\":\"page\",\"id\":\"root\",\"label\":\"页面\",\"children\":[{\"label\":\"表单\",\"type\":\"form\",\"componentProps\":{\"name\":\"default\",\"labelWidth\":\"100px\",\"labelLayout\":\"fixed\",\"labelCol\":{\"span\":5},\"wrapperCol\":{\"span\":19}},\"children\":[{\"label\":\"名称\",\"type\":\"input\",\"field\":\"name\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入名称\"},\"id\":\"input_qho9m970\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"天数\",\"type\":\"input\",\"field\":\"day\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入天数\"},\"id\":\"input_ag5im0tt\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"原因\",\"type\":\"radio\",\"field\":\"reson\",\"input\":true,\"componentProps\":{\"options\":[{\"label\":\"调休\",\"value\":\"1\"},{\"label\":\"病假\",\"value\":\"2\"},{\"label\":\"婚假\",\"value\":\"3\"}],\"defaultValue\":\"选项1\"},\"id\":\"radio_0rvc1sr1\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"多选框\",\"type\":\"checkbox\",\"field\":\"checkbox_gcx7i9d9\",\"input\":true,\"componentProps\":{\"options\":[{\"label\":\"加班对冲\",\"value\":\"1\"},{\"label\":\"工资抵扣\",\"value\":\"2\"},{\"label\":\"领导光环\",\"value\":\"3\"}]},\"id\":\"checkbox_gcx7i9d9\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"array\",\"trigger\":[\"change\"]}]},{\"label\":\"备注\",\"type\":\"textarea\",\"field\":\"remark\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入\"},\"id\":\"textarea_zudir3gs\"}],\"id\":\"form_mybk34y3\"}],\"componentProps\":{\"style\":{\"padding\":\"16px\"}}}]', 'const { defineExpose, find } = epic;\n  \n  function test (){\n      console.log(\'test\')\n  }\n  \n  // 通过defineExpose暴露的函数或者属性\n  defineExpose({\n   test\n  })', NULL, '0', '1', '平台管理员', '2025-10-09 14:58:08', NULL, NULL, NULL);
INSERT INTO `flow_instance_ext` (`id`, `title`, `keyword`, `instance_id`, `business_id`, `business_code`, `operation_type`, `business_type`, `attachment`, `variable`, `remark`, `form_data`, `form_schemas`, `form_script`, `finish_time`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1976180548753821697, '测试-普通流程', NULL, 1976180548518940674, '1759993133019', '1759993133019', 'other', NULL, NULL, NULL, NULL, '{\"reson\":\"2\",\"name\":\"李四\",\"day\":\"5\",\"checkbox_gcx7i9d9\":[\"3\"],\"remark\":\"醉了真的\"}', '[{\"type\":\"page\",\"id\":\"root\",\"label\":\"页面\",\"children\":[{\"label\":\"表单\",\"type\":\"form\",\"componentProps\":{\"name\":\"default\",\"labelWidth\":\"100px\",\"labelLayout\":\"fixed\",\"labelCol\":{\"span\":5},\"wrapperCol\":{\"span\":19}},\"children\":[{\"label\":\"名称\",\"type\":\"input\",\"field\":\"name\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入名称\"},\"id\":\"input_qho9m970\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"天数\",\"type\":\"input\",\"field\":\"day\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入天数\"},\"id\":\"input_ag5im0tt\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"原因\",\"type\":\"radio\",\"field\":\"reson\",\"input\":true,\"componentProps\":{\"options\":[{\"label\":\"调休\",\"value\":\"1\"},{\"label\":\"病假\",\"value\":\"2\"},{\"label\":\"婚假\",\"value\":\"3\"}],\"defaultValue\":\"选项1\"},\"id\":\"radio_0rvc1sr1\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"多选框\",\"type\":\"checkbox\",\"field\":\"checkbox_gcx7i9d9\",\"input\":true,\"componentProps\":{\"options\":[{\"label\":\"加班对冲\",\"value\":\"1\"},{\"label\":\"工资抵扣\",\"value\":\"2\"},{\"label\":\"领导光环\",\"value\":\"3\"}]},\"id\":\"checkbox_gcx7i9d9\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"array\",\"trigger\":[\"change\"]}]},{\"label\":\"备注\",\"type\":\"textarea\",\"field\":\"remark\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入\"},\"id\":\"textarea_zudir3gs\"}],\"id\":\"form_mybk34y3\"}],\"componentProps\":{\"style\":{\"padding\":\"16px\"}}}]', 'const { defineExpose, find } = epic;\n  \n  function test (){\n      console.log(\'test\')\n  }\n  \n  // 通过defineExpose暴露的函数或者属性\n  defineExpose({\n   test\n  })', NULL, '0', '1', '平台管理员', '2025-10-09 14:58:53', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for flow_model_form
-- ----------------------------
DROP TABLE IF EXISTS `flow_model_form`;
CREATE TABLE `flow_model_form` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模型id',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `form_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表单编码',
  `model_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模型id',
  `form_config` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表单配置信息',
  `form_fields` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '表单字段信息',
  `form_script` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `form_schemas` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0=未删除;1=已删除）',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='流程模型管理';

-- ----------------------------
-- Records of flow_model_form
-- ----------------------------
BEGIN;
INSERT INTO `flow_model_form` (`id`, `tenant_id`, `form_code`, `model_id`, `form_config`, `form_fields`, `form_script`, `form_schemas`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES ('1965289824202141698', 1, NULL, '1965289765146341378', NULL, NULL, 'const { defineExpose, find } = epic;\n  \n  function test (){\n      console.log(\'test\')\n  }\n  \n  // 通过defineExpose暴露的函数或者属性\n  defineExpose({\n   test\n  })', '[{\"type\":\"page\",\"id\":\"root\",\"label\":\"页面\",\"children\":[{\"label\":\"表单\",\"type\":\"form\",\"componentProps\":{\"name\":\"default\",\"labelWidth\":\"100px\",\"labelLayout\":\"fixed\",\"labelCol\":{\"span\":5},\"wrapperCol\":{\"span\":19}},\"children\":[{\"label\":\"名称\",\"type\":\"input\",\"field\":\"name\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入名称\"},\"id\":\"input_qho9m970\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"天数\",\"type\":\"input\",\"field\":\"day\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入天数\"},\"id\":\"input_ag5im0tt\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"原因\",\"type\":\"radio\",\"field\":\"reson\",\"input\":true,\"componentProps\":{\"options\":[{\"label\":\"调休\",\"value\":\"1\"},{\"label\":\"病假\",\"value\":\"2\"},{\"label\":\"婚假\",\"value\":\"3\"}],\"defaultValue\":\"选项1\"},\"id\":\"radio_0rvc1sr1\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"多选框\",\"type\":\"checkbox\",\"field\":\"checkbox_gcx7i9d9\",\"input\":true,\"componentProps\":{\"options\":[{\"label\":\"加班对冲\",\"value\":\"1\"},{\"label\":\"工资抵扣\",\"value\":\"2\"},{\"label\":\"领导光环\",\"value\":\"3\"}]},\"id\":\"checkbox_gcx7i9d9\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"array\",\"trigger\":[\"change\"]}]},{\"label\":\"备注\",\"type\":\"textarea\",\"field\":\"remark\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入\"},\"id\":\"textarea_zudir3gs\"}],\"id\":\"form_mybk34y3\"}],\"componentProps\":{\"style\":{\"padding\":\"16px\"}}}]', b'0', 1, '平台管理员', '2025-09-09 13:43:02', 1, '平台管理员', '2025-09-15 16:40:27');
COMMIT;

-- ----------------------------
-- Table structure for flow_node
-- ----------------------------
DROP TABLE IF EXISTS `flow_node`;
CREATE TABLE `flow_node` (
  `id` bigint NOT NULL COMMENT '主键id',
  `node_type` smallint NOT NULL COMMENT '节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
  `definition_id` bigint NOT NULL COMMENT '流程定义id',
  `node_code` varchar(100) COLLATE utf8mb3_bin NOT NULL COMMENT '流程节点编码',
  `node_name` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '流程节点名称',
  `permission_flag` varchar(200) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '权限标识（权限类型:权限标识，可以多个，用逗号隔开)',
  `node_ratio` decimal(6,3) DEFAULT NULL COMMENT '流程签署比例值',
  `coordinate` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '坐标',
  `any_node_skip` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '任意结点跳转',
  `listener_type` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '监听器类型',
  `listener_path` text COLLATE utf8mb3_bin COMMENT '监听器路径',
  `handler_type` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '处理器类型',
  `handler_path` text COLLATE utf8mb3_bin COMMENT '处理器路径',
  `form_custom` char(1) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审批表单是否自定义（Y是 N否）',
  `form_path` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审批表单路径',
  `version` varchar(20) COLLATE utf8mb3_bin NOT NULL COMMENT '版本',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人',
  `ext` longtext COLLATE utf8mb3_bin COMMENT '节点扩展属性',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '0' COMMENT '删除标志',
  `tenant_id` varchar(40) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='流程节点表';

-- ----------------------------
-- Records of flow_node
-- ----------------------------
BEGIN;
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1965289765188284418, 0, 1965289765146341378, 'startId', '开始', NULL, 0.000, '700,100|700,100', NULL, '', '', NULL, NULL, NULL, NULL, '1', '2025-09-09 13:42:48', NULL, '2025-09-09 13:42:48', NULL, '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1965289765188284419, 2, 1965289765146341378, 'endId', '结束', NULL, 0.000, '700,380|700,380', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2025-09-09 13:42:48', NULL, '2025-09-09 13:42:48', NULL, '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1965289765188284420, 1, 1965289765146341378, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', '审批', '1', 0.000, '700,250|700,250', NULL, '', '', NULL, NULL, '', NULL, '1', '2025-09-09 13:42:48', NULL, '2025-09-09 13:42:48', NULL, '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1967853472531546113, 0, 1965289765146341378, 'startId', '开始', NULL, 0.000, '700,100|700,100', NULL, '', '', NULL, NULL, NULL, NULL, '1', '2025-09-16 15:30:03', NULL, '2025-09-16 15:30:03', NULL, '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1967853472531546114, 2, 1965289765146341378, 'endId', '结束', NULL, 0.000, '700,530|700,530', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2025-09-16 15:30:03', NULL, '2025-09-16 15:30:03', NULL, '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1967853472531546115, 1, 1965289765146341378, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', '管理员审批', '1', 0.000, '700,250|700,250', NULL, '', '', NULL, NULL, '', NULL, '1', '2025-09-16 15:30:03', NULL, '2025-09-16 15:30:03', NULL, '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1967853472531546116, 1, 1965289765146341378, 'e974c61e-778a-4521-ae6d-90c82c4b7eb7', '测试审批', '24', 0.000, '700,400|700,400', NULL, '', '', NULL, NULL, '', NULL, '1', '2025-09-16 15:30:03', NULL, '2025-09-16 15:30:03', NULL, '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1968598962596474881, 0, 1968598962399342594, 'startId', '开始', NULL, 0.000, '700,100|700,100', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2025-09-18 16:52:22', '1', '2025-09-18 16:52:22', '1', '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1968598962596474882, 1, 1968598962399342594, 'betweenId', '中间节点', '', 0.000, '700,250|700,250', NULL, '', '', NULL, NULL, '', NULL, '1', '2025-09-18 16:52:22', '1', '2025-09-18 16:52:22', '1', '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1968598962596474883, 2, 1968598962399342594, 'endId', '结束', NULL, 0.000, '700,380|700,380', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2025-09-18 16:52:22', '1', '2025-09-18 16:52:22', '1', '[]', '1', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1968599057790398466, 0, 1968598962399342594, 'startId', '开始', NULL, 0.000, '700,100|700,100', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2025-09-18 16:52:45', '1', '2025-09-18 16:52:45', '1', '[]', '0', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1968599057790398467, 1, 1968598962399342594, 'betweenId', '中间节点', '', 0.000, '700,250|700,250', NULL, '', '', NULL, NULL, '', NULL, '1', '2025-09-18 16:52:45', '1', '2025-09-18 16:52:45', '1', '[]', '0', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1968599057790398468, 2, 1968598962399342594, 'endId', '结束', NULL, 0.000, '700,380|700,380', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2025-09-18 16:52:45', '1', '2025-09-18 16:52:45', '1', '[]', '0', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1976179795893059585, 0, 1965289765146341378, 'startId', '开始', NULL, 0.000, '700,100|700,100', NULL, '', '', NULL, NULL, NULL, NULL, '1', '2025-10-09 14:55:54', '1', '2025-10-09 14:55:54', '1', '[]', '0', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1976179795893059586, 2, 1965289765146341378, 'endId', '结束', NULL, 0.000, '700,530|700,530', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2025-10-09 14:55:54', '1', '2025-10-09 14:55:54', '1', '[]', '0', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1976179795893059587, 1, 1965289765146341378, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', '管理员审批', '1', 0.000, '700,250|700,250', NULL, '', '', NULL, NULL, '', NULL, '1', '2025-10-09 14:55:54', '1', '2025-10-09 14:55:54', '1', '[]', '0', '1');
INSERT INTO `flow_node` (`id`, `node_type`, `definition_id`, `node_code`, `node_name`, `permission_flag`, `node_ratio`, `coordinate`, `any_node_skip`, `listener_type`, `listener_path`, `handler_type`, `handler_path`, `form_custom`, `form_path`, `version`, `create_time`, `create_by`, `update_time`, `update_by`, `ext`, `del_flag`, `tenant_id`) VALUES (1976179795893059588, 1, 1965289765146341378, 'e974c61e-778a-4521-ae6d-90c82c4b7eb7', '测试审批', '2', 0.000, '700,400|700,400', NULL, '', '', NULL, NULL, '', NULL, '1', '2025-10-09 14:55:54', '1', '2025-10-09 14:55:54', '1', '[]', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for flow_skip
-- ----------------------------
DROP TABLE IF EXISTS `flow_skip`;
CREATE TABLE `flow_skip` (
  `id` bigint NOT NULL COMMENT '主键id',
  `definition_id` bigint NOT NULL COMMENT '流程定义id',
  `now_node_code` varchar(100) COLLATE utf8mb3_bin NOT NULL COMMENT '当前流程节点的编码',
  `now_node_type` smallint DEFAULT NULL COMMENT '当前节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
  `next_node_code` varchar(100) COLLATE utf8mb3_bin NOT NULL COMMENT '下一个流程节点的编码',
  `next_node_type` smallint DEFAULT NULL COMMENT '下一个节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
  `skip_name` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '跳转名称',
  `skip_type` varchar(40) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '跳转类型（PASS审批通过 REJECT退回）',
  `skip_condition` varchar(200) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '跳转条件',
  `coordinate` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '坐标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '0' COMMENT '删除标志',
  `tenant_id` varchar(40) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='节点跳转关联表';

-- ----------------------------
-- Records of flow_skip
-- ----------------------------
BEGIN;
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1965289765293142017, 1965289765146341378, 'startId', 0, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', NULL, NULL, 'PASS', NULL, '700,140;700,210', '2025-09-09 13:42:48', NULL, '2025-09-09 13:42:48', NULL, '1', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1965289765293142018, 1965289765146341378, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', 1, 'endId', NULL, NULL, 'PASS', NULL, '700,290;700,360', '2025-09-09 13:42:48', NULL, '2025-09-09 13:42:48', NULL, '1', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1967853472586072066, 1965289765146341378, 'startId', 0, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', NULL, NULL, 'PASS', NULL, '700,140;700,210', '2025-09-16 15:30:03', NULL, '2025-09-16 15:30:03', NULL, '1', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1967853472586072067, 1965289765146341378, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', 1, 'e974c61e-778a-4521-ae6d-90c82c4b7eb7', NULL, NULL, 'PASS', NULL, '700,290;700,360', '2025-09-16 15:30:03', NULL, '2025-09-16 15:30:03', NULL, '1', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1967853472586072068, 1965289765146341378, 'e974c61e-778a-4521-ae6d-90c82c4b7eb7', 1, 'endId', NULL, NULL, 'PASS', NULL, '700,440;700,510', '2025-09-16 15:30:03', NULL, '2025-09-16 15:30:03', NULL, '1', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1968598962797801474, 1968598962399342594, 'startId', 0, 'betweenId', NULL, NULL, 'PASS', NULL, '700,140;700,210', '2025-09-18 16:52:22', '1', '2025-09-18 16:52:22', '1', '1', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1968598962797801475, 1968598962399342594, 'betweenId', 1, 'endId', NULL, NULL, 'PASS', NULL, '700,290;700,360', '2025-09-18 16:52:22', '1', '2025-09-18 16:52:22', '1', '1', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1968599057895256066, 1968598962399342594, 'startId', 0, 'betweenId', NULL, NULL, 'PASS', NULL, '700,140;700,210|NaN,NaN', '2025-09-18 16:52:45', '1', '2025-09-18 16:52:45', '1', '0', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1968599057895256067, 1968598962399342594, 'betweenId', 1, 'endId', NULL, NULL, 'PASS', NULL, '700,290;700,360|NaN,NaN', '2025-09-18 16:52:45', '1', '2025-09-18 16:52:45', '1', '0', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1976179795981139969, 1965289765146341378, 'startId', 0, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', NULL, NULL, 'PASS', NULL, '700,140;700,210', '2025-10-09 14:55:54', '1', '2025-10-09 14:55:54', '1', '0', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1976179795981139970, 1965289765146341378, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', 1, 'e974c61e-778a-4521-ae6d-90c82c4b7eb7', NULL, NULL, 'PASS', NULL, '700,290;700,360', '2025-10-09 14:55:54', '1', '2025-10-09 14:55:54', '1', '0', '1');
INSERT INTO `flow_skip` (`id`, `definition_id`, `now_node_code`, `now_node_type`, `next_node_code`, `next_node_type`, `skip_name`, `skip_type`, `skip_condition`, `coordinate`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1976179795981139971, 1965289765146341378, 'e974c61e-778a-4521-ae6d-90c82c4b7eb7', 1, 'endId', NULL, NULL, 'PASS', NULL, '700,440;700,510', '2025-10-09 14:55:54', '1', '2025-10-09 14:55:54', '1', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for flow_task
-- ----------------------------
DROP TABLE IF EXISTS `flow_task`;
CREATE TABLE `flow_task` (
  `id` bigint NOT NULL COMMENT '主键id',
  `definition_id` bigint NOT NULL COMMENT '对应flow_definition表的id',
  `instance_id` bigint NOT NULL COMMENT '对应flow_instance表的id',
  `node_code` varchar(100) COLLATE utf8mb3_bin NOT NULL COMMENT '节点编码',
  `node_name` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '节点名称',
  `node_type` smallint NOT NULL COMMENT '节点类型（0开始节点 1中间节点 2结束节点 3互斥网关 4并行网关）',
  `flow_status` varchar(20) COLLATE utf8mb3_bin NOT NULL COMMENT '流程状态（0待提交 1审批中 2审批通过 4终止 5作废 6撤销 8已完成 9已退回 10失效 11拿回）',
  `form_custom` char(1) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审批表单是否自定义（Y是 N否）',
  `form_path` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审批表单路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '0' COMMENT '删除标志',
  `tenant_id` varchar(40) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='待办任务表';

-- ----------------------------
-- Records of flow_task
-- ----------------------------
BEGIN;
INSERT INTO `flow_task` (`id`, `definition_id`, `instance_id`, `node_code`, `node_name`, `node_type`, `flow_status`, `form_custom`, `form_path`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1976180357854269443, 1965289765146341378, 1976180357850075137, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', '管理员审批', 1, '1', 'N', NULL, '2025-10-09 14:58:08', '1', NULL, '1', '1', '1');
INSERT INTO `flow_task` (`id`, `definition_id`, `instance_id`, `node_code`, `node_name`, `node_type`, `flow_status`, `form_custom`, `form_path`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1976180464276344834, 1965289765146341378, 1976180357850075137, 'e974c61e-778a-4521-ae6d-90c82c4b7eb7', '测试审批', 1, '1', 'N', NULL, '2025-10-09 14:58:33', '1', '2025-10-09 14:58:33', '1', '0', '1');
INSERT INTO `flow_task` (`id`, `definition_id`, `instance_id`, `node_code`, `node_name`, `node_type`, `flow_status`, `form_custom`, `form_path`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1976180548523134980, 1965289765146341378, 1976180548518940674, 'ad9a90f2-2f52-47f0-8200-fcae80e8d21b', '管理员审批', 1, '1', 'N', NULL, '2025-10-09 14:58:53', '1', '2025-10-09 14:58:53', '1', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for flow_user
-- ----------------------------
DROP TABLE IF EXISTS `flow_user`;
CREATE TABLE `flow_user` (
  `id` bigint NOT NULL COMMENT '主键id',
  `type` char(1) COLLATE utf8mb3_bin NOT NULL COMMENT '人员类型（1待办任务的审批人权限 2待办任务的转办人权限 3待办任务的委托人权限）',
  `processed_by` varchar(80) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '权限人',
  `associated` bigint NOT NULL COMMENT '任务表id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(80) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '0' COMMENT '删除标志',
  `tenant_id` varchar(40) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`),
  KEY `user_associated_idx` (`associated`) USING BTREE,
  KEY `user_processed_type` (`processed_by`,`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='流程用户表';

-- ----------------------------
-- Records of flow_user
-- ----------------------------
BEGIN;
INSERT INTO `flow_user` (`id`, `type`, `processed_by`, `associated`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1976180358420500482, '1', '1', 1976180357854269443, '2025-10-09 14:58:08', '1', '2025-10-09 14:58:08', '1', '1', '1');
INSERT INTO `flow_user` (`id`, `type`, `processed_by`, `associated`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1976180464452505602, '1', '2', 1976180464276344834, '2025-10-09 14:58:33', '1', '2025-10-09 14:58:33', '1', '0', '1');
INSERT INTO `flow_user` (`id`, `type`, `processed_by`, `associated`, `create_time`, `create_by`, `update_time`, `update_by`, `del_flag`, `tenant_id`) VALUES (1976180548653158402, '1', '1', 1976180548523134980, '2025-10-09 14:58:53', '1', '2025-10-09 14:58:53', '1', '0', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
