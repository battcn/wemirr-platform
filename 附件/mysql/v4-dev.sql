/*
 Navicat Premium Data Transfer

 Source Server         : localhost-docker
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : v4-dev

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 22/12/2025 08:35:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for b_message_channel
-- ----------------------------
DROP TABLE IF EXISTS `b_message_channel`;
CREATE TABLE `b_message_channel` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渠道标题',
                                     `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息类型',
                                     `status` bit(1) DEFAULT b'1' COMMENT '状态（0=禁用;1=启用）',
                                     `setting` json DEFAULT NULL COMMENT '设置（JSON）',
                                     `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                     `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
                                     `deleted` bit(1) DEFAULT b'0',
                                     `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                     `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                     `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                                     `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                     `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1864548132428439555 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息通知';

-- ----------------------------
-- Records of b_message_channel
-- ----------------------------
BEGIN;
INSERT INTO `b_message_channel` (`id`, `title`, `type`, `status`, `setting`, `tenant_id`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1864537666578714625, '系统内置', 'system', b'1', NULL, 1, '系统内置 webstock 消息', b'0', NULL, NULL, '2024-12-05 13:09:56', 1, '平台管理员', '2025-11-28 11:30:39');
INSERT INTO `b_message_channel` (`id`, `title`, `type`, `status`, `setting`, `tenant_id`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1864543691579056130, '213123', NULL, b'1', NULL, 1, '12312', b'0', NULL, NULL, '2024-12-05 13:33:52', NULL, NULL, '2024-12-05 05:58:16');
INSERT INTO `b_message_channel` (`id`, `title`, `type`, `status`, `setting`, `tenant_id`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1864548132428439554, '腾讯邮箱', 'email', b'1', '{\"id\": \"1864548132428439554\", \"host\": \"smtp.qq.com\", \"port\": \"465\", \"smtp\": {\"ssl\": true, \"auth\": true}, \"type\": \"email\", \"title\": \"腾讯邮箱\", \"status\": true, \"password\": \"123\", \"protocol\": \"smtp\", \"tenantId\": \"1\", \"username\": \"123@qq.com\", \"description\": \"描述\"}', 1, '描述', b'0', NULL, NULL, '2024-12-05 13:51:31', NULL, NULL, '2024-12-05 06:31:14');
COMMIT;

-- ----------------------------
-- Table structure for b_message_notify
-- ----------------------------
DROP TABLE IF EXISTS `b_message_notify`;
CREATE TABLE `b_message_notify` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
                                    `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息类型',
                                    `template_id` bigint DEFAULT NULL COMMENT '消息模板ID',
                                    `variables` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息变量',
                                    `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                    `user_id` bigint DEFAULT NULL COMMENT '接收用户ID',
                                    `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                    `subscribe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订阅人 比如 邮箱,手机号,钉钉ID等',
                                    `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                    `deleted` bit(1) DEFAULT b'0',
                                    `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                                    `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                    `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息通知';

-- ----------------------------
-- Records of b_message_notify
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for b_message_template
-- ----------------------------
DROP TABLE IF EXISTS `b_message_template`;
CREATE TABLE `b_message_template` (
                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                      `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板编码',
                                      `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名称',
                                      `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板类型',
                                      `subject` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息标题（如邮件标题）',
                                      `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息标题（如邮件标题）',
                                      `status` bit(1) DEFAULT b'1' COMMENT '状态（0=禁用;1=启用）',
                                      `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                      `deleted` bit(1) DEFAULT b'0',
                                      `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                      `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                      `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                                      `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                      `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1863835249327370242 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of b_message_template
-- ----------------------------
BEGIN;
INSERT INTO `b_message_template` (`id`, `code`, `name`, `type`, `subject`, `content`, `status`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1863835249327370241, '0001', '系统消息', 'system', '系统消息', '<p>欢迎 ${username} 来到 WP 系统</p>', b'1', NULL, b'0', NULL, NULL, '2024-12-03 14:38:46', 1, '平台管理员', '2025-11-28 11:31:37');
COMMIT;

-- ----------------------------
-- Table structure for c_diff_log
-- ----------------------------
DROP TABLE IF EXISTS `c_diff_log`;
CREATE TABLE `c_diff_log` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
                              `service_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '服务名称',
                              `business_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务组',
                              `business_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务标签',
                              `business_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务关键字',
                              `status` int DEFAULT NULL COMMENT '状态',
                              `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '描述信息',
                              `variables` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '修改字段',
                              `extra` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '额外信息，JSON格式',
                              `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                              `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
                              `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者名称',
                              `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='通用差异日志表';

-- ----------------------------
-- Records of c_diff_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_gen_demo
-- ----------------------------
DROP TABLE IF EXISTS `c_gen_demo`;
CREATE TABLE `c_gen_demo` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                              `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '名称',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

-- ----------------------------
-- Records of c_gen_demo
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_generate_table
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_table`;
CREATE TABLE `c_generate_table` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称',
                                    `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表描述',
                                    `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实体类名称(首字母大写)',
                                    `package_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '生成包路径',
                                    `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '生成模块名',
                                    `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作者',
                                    `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
                                    `business_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务名称 [英文，用于api路径命名]',
                                    `remove_prefix` tinyint(1) DEFAULT '0' COMMENT '是否去掉前缀',
                                    `prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '前缀',
                                    `template_group_id` bigint DEFAULT NULL COMMENT '关联模板组',
                                    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                    `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
                                    `last_modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
                                    `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                    `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后修改人名称',
                                    `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除标记 (0: 未删除, 1: 已删除)',
                                    `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                    `swagger` tinyint(1) DEFAULT '1' COMMENT '是否开启swagger配置',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1998692347235778562 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='代码生成表';

-- ----------------------------
-- Records of c_generate_table
-- ----------------------------
BEGIN;
INSERT INTO `c_generate_table` (`id`, `name`, `comment`, `class_name`, `package_name`, `module_name`, `author`, `email`, `business_name`, `remove_prefix`, `prefix`, `template_group_id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `swagger`) VALUES (1893148668509442050, 'b_message_channel', '消息通知', 'BMessageChannel', 'com.wemirr.platform', 'GenCode', '平台管理员', NULL, 'bMessageChannel', 0, '', 1872173937937879041, '2025-02-10 09:32:36', 1, '平台管理员', '2025-02-22 11:59:50', 1, '平台管理员', b'1', 1, 1);
INSERT INTO `c_generate_table` (`id`, `name`, `comment`, `class_name`, `package_name`, `module_name`, `author`, `email`, `business_name`, `remove_prefix`, `prefix`, `template_group_id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `swagger`) VALUES (1893152402421485569, 'b_message_notify', '消息通知', 'BMessageNotify', 'com.wemirr.platform', 'GenCode', '平台管理员', NULL, 'bMessageNotify', 0, '', 1872173937937879041, '2025-02-10 09:32:36', 1, '平台管理员', '2025-02-22 12:14:40', 1, '平台管理员', b'1', 1, 1);
INSERT INTO `c_generate_table` (`id`, `name`, `comment`, `class_name`, `package_name`, `module_name`, `author`, `email`, `business_name`, `remove_prefix`, `prefix`, `template_group_id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `swagger`) VALUES (1893152842248863745, 'b_message_template', '站内消息', 'BMessageTemplate', 'com.wemirr.platform', 'GenCode', '平台管理员', NULL, 'bMessageTemplate', 0, '', 1872173937937879041, '2025-02-10 09:32:36', 1, '平台管理员', '2025-02-22 12:16:25', 1, '平台管理员', b'1', 1, 1);
INSERT INTO `c_generate_table` (`id`, `name`, `comment`, `class_name`, `package_name`, `module_name`, `author`, `email`, `business_name`, `remove_prefix`, `prefix`, `template_group_id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `swagger`) VALUES (1893154833356275714, 'b_message_channel', '消息通知', 'MessageChannel', 'com.wemirr.platform', 'suite', '平台管理员', NULL, 'messageChannel', 1, 'b_', 1872173937937879041, '2025-02-10 09:32:36', 1, '平台管理员', '2025-02-22 12:26:19', 1, '平台管理员', b'0', 1, 1);
INSERT INTO `c_generate_table` (`id`, `name`, `comment`, `class_name`, `package_name`, `module_name`, `author`, `email`, `business_name`, `remove_prefix`, `prefix`, `template_group_id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `swagger`) VALUES (1998677670455832577, 'b_message_notify', '消息通知', 'MessageNotify', 'com.wemirr.platform', 'GenCode', '平台管理员', NULL, 'messageNotify', 1, 'b_', 1872173937937879041, '2025-08-26 01:37:11', 1, '平台管理员', '2025-12-10 17:52:25', 1, '平台管理员', b'0', NULL, 1);
INSERT INTO `c_generate_table` (`id`, `name`, `comment`, `class_name`, `package_name`, `module_name`, `author`, `email`, `business_name`, `remove_prefix`, `prefix`, `template_group_id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `swagger`) VALUES (1998692347235778561, 'c_diff_log', '通用差异日志表', 'DiffLog', 'com.wemirr.platform', 'GenCode', '平台管理员', NULL, 'diffLog', 1, 'c_', 1872173937937879041, '2025-08-26 01:37:11', 1, '平台管理员', '2025-12-10 17:52:57', 1, '平台管理员', b'0', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for c_generate_table_column
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_table_column`;
CREATE TABLE `c_generate_table_column` (
                                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                           `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称 [ alipan 是用表名称进行关联的 ]',
                                           `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
                                           `sort` int DEFAULT NULL COMMENT '排序',
                                           `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段类型',
                                           `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '注释',
                                           `property_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性类型',
                                           `property_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性名',
                                           `property_package` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性所属的包',
                                           `pk` tinyint(1) DEFAULT NULL COMMENT '主键 0：否 1：是',
                                           `increment` tinyint(1) DEFAULT NULL COMMENT '是否自增',
                                           `required` tinyint(1) DEFAULT NULL COMMENT '是否必填',
                                           `inserted` tinyint(1) DEFAULT NULL COMMENT '是否为插入字段',
                                           `edit` tinyint(1) DEFAULT NULL COMMENT '是否编辑字段',
                                           `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                           `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
                                           `last_modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
                                           `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                           `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后修改人名称',
                                           `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除标记 (0: 未删除, 1: 已删除)',
                                           `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                           `generate` tinyint(1) DEFAULT '1' COMMENT '当前字段是否需要生成',
                                           `list` tinyint(1) DEFAULT '1' COMMENT '当前字段是否列表展示',
                                           `search` tinyint(1) DEFAULT '0' COMMENT '当前字段是否作为查询条件',
                                           `search_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '查询条件',
                                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1998692347156086786 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='代码生成表列';

-- ----------------------------
-- Records of c_generate_table_column
-- ----------------------------
BEGIN;
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833150754817, 'b_message_channel', 'id', 1, 'bigint', 'ID', 'Long', 'id', NULL, 1, 1, 0, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 0, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833184309249, 'b_message_channel', 'title', 2, 'varchar', '渠道标题', 'String', 'title', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833196892161, 'b_message_channel', 'type', 3, 'varchar', '消息类型', 'String', 'type', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833196892162, 'b_message_channel', 'status', 4, 'bit', '状态（0=禁用;1=启用）', 'Boolean', 'status', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833205280769, 'b_message_channel', 'setting', 5, 'json', '设置（JSON）', 'String', 'setting', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833209475073, 'b_message_channel', 'tenant_id', 6, 'bigint', '租户ID', 'Long', 'tenantId', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833209475074, 'b_message_channel', 'description', 7, 'varchar', '描述信息', 'String', 'description', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833217863682, 'b_message_channel', 'deleted', 8, 'bit', '', 'Boolean', 'deleted', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833222057986, 'b_message_channel', 'created_by', 9, 'bigint', '创建人id', 'Long', 'createdBy', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833222057987, 'b_message_channel', 'created_name', 10, 'varchar', '创建人名称', 'String', 'createdName', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833230446593, 'b_message_channel', 'created_time', 11, 'datetime', '创建时间', 'Date', 'createdTime', 'java.util.Date', 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833259806722, 'b_message_channel', 'last_modified_by', 12, 'bigint', '更新人id', 'Long', 'lastModifiedBy', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833293361153, 'b_message_channel', 'last_modified_name', 13, 'varchar', '更新人名称', 'String', 'lastModifiedName', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1893154833293361154, 'b_message_channel', 'last_modified_time', 14, 'datetime', '更新时间', 'Date', 'lastModifiedTime', 'java.util.Date', 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670292254721, 'b_message_notify', 'id', 1, 'bigint', 'ID', 'Long', 'id', NULL, 1, 1, 0, 0, 0, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 0, 0, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670304837634, 'b_message_notify', 'title', 2, 'varchar', '标题', 'String', 'title', NULL, 0, 0, 1, 1, 1, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670304837635, 'b_message_notify', 'type', 3, 'varchar', '消息类型', 'String', 'type', NULL, 0, 0, 1, 1, 1, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670309031937, 'b_message_notify', 'template_id', 4, 'bigint', '消息模板ID', 'Long', 'templateId', NULL, 0, 0, 1, 1, 1, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670309031938, 'b_message_notify', 'variables', 5, 'varchar', '消息变量', 'String', 'variables', NULL, 0, 0, 1, 1, 1, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670309031939, 'b_message_notify', 'content', 6, 'varchar', '', 'String', 'content', NULL, 0, 0, 1, 1, 1, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670313226241, 'b_message_notify', 'user_id', 7, 'bigint', '接收用户ID', 'Long', 'userId', NULL, 0, 0, 1, 1, 1, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670313226242, 'b_message_notify', 'nickname', 8, 'varchar', '', 'String', 'nickname', NULL, 0, 0, 1, 1, 1, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670313226243, 'b_message_notify', 'subscribe', 9, 'varchar', '订阅人 比如 邮箱,手机号,钉钉ID等', 'String', 'subscribe', NULL, 0, 0, 1, 1, 1, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670317420545, 'b_message_notify', 'tenant_id', 10, 'bigint', '租户ID', 'Long', 'tenantId', NULL, 0, 0, 1, 0, 0, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670317420546, 'b_message_notify', 'deleted', 11, 'bit', '', 'Boolean', 'deleted', NULL, 0, 0, 1, 0, 0, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670317420547, 'b_message_notify', 'create_by', 12, 'bigint', '创建人id', 'Long', 'createBy', NULL, 0, 0, 1, 0, 0, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670321614849, 'b_message_notify', 'create_name', 13, 'varchar', '创建人名称', 'String', 'createName', NULL, 0, 0, 1, 0, 0, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670321614850, 'b_message_notify', 'create_time', 14, 'datetime', '创建时间', 'Date', 'createTime', 'java.util.Date', 0, 0, 1, 0, 0, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670330003457, 'b_message_notify', 'last_modify_by', 15, 'bigint', '更新人id', 'Long', 'lastModifyBy', NULL, 0, 0, 1, 0, 0, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670334197762, 'b_message_notify', 'last_modify_name', 16, 'varchar', '更新人名称', 'String', 'lastModifyName', NULL, 0, 0, 1, 0, 0, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998677670338392066, 'b_message_notify', 'last_modify_time', 17, 'datetime', '更新时间', 'Date', 'lastModifyTime', 'java.util.Date', 0, 0, 1, 0, 0, '2025-12-10 16:54:25', 1, '平台管理员', '2025-12-10 08:54:24', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347059617794, 'c_diff_log', 'id', 1, 'bigint', '主键，自增', 'Long', 'id', NULL, 1, 1, 0, 0, 0, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 0, 0, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347072200705, 'c_diff_log', 'variables', 2, 'json', '变量信息，JSON格式', 'String', 'variables', NULL, 0, 0, 1, 1, 1, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347076395009, 'c_diff_log', 'service_name', 3, 'varchar', '服务名称', 'String', 'serviceName', NULL, 0, 0, 1, 1, 1, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347076395010, 'c_diff_log', 'business_group', 4, 'varchar', '业务组', 'String', 'businessGroup', NULL, 0, 0, 1, 1, 1, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347080589313, 'c_diff_log', 'business_tag', 5, 'varchar', '业务标签', 'String', 'businessTag', NULL, 0, 0, 1, 1, 1, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347080589314, 'c_diff_log', 'business_key', 6, 'varchar', '业务关键字', 'String', 'businessKey', NULL, 0, 0, 1, 1, 1, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347084783618, 'c_diff_log', 'description', 7, 'text', '描述信息', 'String', 'description', NULL, 0, 0, 1, 1, 1, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347084783619, 'c_diff_log', 'status', 8, 'int', '状态', 'Integer', 'status', NULL, 0, 0, 1, 1, 1, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347084783620, 'c_diff_log', 'tenant_id', 9, 'bigint', '租户ID', 'Long', 'tenantId', NULL, 0, 0, 1, 0, 0, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347088977922, 'c_diff_log', 'create_by', 10, 'bigint', '创建者ID', 'Long', 'createBy', NULL, 0, 0, 1, 0, 0, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347088977923, 'c_diff_log', 'create_name', 11, 'varchar', '创建者名称', 'String', 'createName', NULL, 0, 0, 1, 0, 0, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347093172226, 'c_diff_log', 'create_time', 12, 'timestamp', '创建时间', 'Date', 'createTime', 'java.util.Date', 0, 0, 1, 0, 0, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` (`id`, `table_name`, `name`, `sort`, `type`, `comment`, `property_type`, `property_name`, `property_package`, `pk`, `increment`, `required`, `inserted`, `edit`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`, `generate`, `list`, `search`, `search_condition`) VALUES (1998692347156086785, 'c_diff_log', 'extra', 13, 'text', '额外信息，JSON格式', 'String', 'extra', NULL, 0, 0, 1, 1, 1, '2025-12-10 17:52:44', 1, '平台管理员', '2025-12-10 09:52:44', NULL, NULL, b'0', NULL, 1, 1, 0, 'LIKE');
COMMIT;

-- ----------------------------
-- Table structure for c_generate_template
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_template`;
CREATE TABLE `c_generate_template` (
                                       `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                       `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板名称',
                                       `generate_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板路径',
                                       `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板描述',
                                       `code` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板代码',
                                       `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                       `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
                                       `last_modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
                                       `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                       `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后修改人名称',
                                       `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除标记 (0: 未删除, 1: 已删除)',
                                       `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1998666639570522114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用于存储代码生成模板的信息';

-- ----------------------------
-- Records of c_generate_template
-- ----------------------------
BEGIN;
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872172480907972610, 'WP规范（service）', 'backend/src/main/java/${packagePath}/${moduleName}/service/${ClassName}Service.java', 'WP规范（service）', 'package ${package}.${moduleName}.service;\n\nimport com.baomidou.mybatisplus.core.metadata.IPage;\nimport com.wemirr.framework.db.mybatisplus.ext.SuperService;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}PageReq;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}SaveReq;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}PageResp;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}DetailResp;\nimport ${package}.${moduleName}.domain.entity.${ClassName};\n\npublic interface ${ClassName}Service extends SuperService<${ClassName}>{\n\n    /**\n    * 分页查询\n    * @param req 分页请求参数\n    * @return 分页结果\n    */\n    IPage<${ClassName}PageResp> pageList(${ClassName}PageReq req);\n\n     /**\n     * 通过id查询详情\n     * @param id 实体ID\n     * @return 详情响应\n     */\n     ${ClassName}DetailResp detail(Long id);\n\n     /**\n     * 新增实体\n     * @param req 新增请求参数\n     */\n     void create(${ClassName}SaveReq req);\n\n     /**\n     * 修改实体\n     * @param id 实体ID\n     * @param req 修改请求参数\n     */\n     void modify(Long id, ${ClassName}SaveReq req);\n\n     /**\n     * 通过id删除实体\n     * @param id 实体ID\n     */\n     void delete(Long id);\n }', '2024-12-26 14:47:57', 1, '平台管理员', '2025-12-10 17:34:54', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872172659639848962, 'WP规范（serviceImpl）', 'backend/src/main/java/${packagePath}/${moduleName}/service/impl/${ClassName}ServiceImpl.java', 'WP规范（serviceImpl）', 'package ${package}.${moduleName}.service.impl;\n\nimport cn.hutool.core.bean.BeanUtil;\nimport com.wemirr.framework.commons.BeanUtilPlus;\nimport com.baomidou.mybatisplus.core.metadata.IPage;\nimport com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;\nimport com.wemirr.framework.commons.exception.CheckedException;\nimport com.wemirr.framework.db.mybatisplus.wrap.Wraps;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}PageReq;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}SaveReq;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}PageResp;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}DetailResp;\nimport ${package}.${moduleName}.domain.entity.${ClassName};\nimport ${package}.${moduleName}.repository.${ClassName}Mapper;\nimport ${package}.${moduleName}.service.${ClassName}Service;\nimport lombok.RequiredArgsConstructor;\nimport org.springframework.stereotype.Service;\nimport org.springframework.transaction.annotation.Transactional;\n\nimport java.util.Optional;\n\n@Service\n@RequiredArgsConstructor\npublic class ${ClassName}ServiceImpl extends SuperServiceImpl<${ClassName}Mapper, ${ClassName}> implements ${ClassName}Service {\n\n    private final ${ClassName}Mapper ${businessName}Mapper;\n\n    @Override\n    public IPage<${ClassName}PageResp> pageList(${ClassName}PageReq req) {\n       return this.baseMapper.selectPage(req.buildPage(), null)\n       .convert(x -> BeanUtil.toBean(x, ${ClassName}PageResp.class));\n     }\n\n     @Override\n     public ${ClassName}DetailResp detail(Long id) {\n     ${ClassName} ${businessName} = this.baseMapper.selectById(id);\n        Optional.ofNullable(${businessName})\n        .orElseThrow(() -> CheckedException.notFound(\"实体不存在\"));\n        return BeanUtil.toBean(${businessName}, ${ClassName}DetailResp.class);\n     }\n\n     @Override\n     public void create(${ClassName}SaveReq req) {\n         ${ClassName} ${businessName} = BeanUtil.toBean(req, ${ClassName}.class);\n         this.baseMapper.insert(${businessName});\n     }\n\n     @Override\n     public void modify(Long id, ${ClassName}SaveReq req) {\n          Optional.ofNullable(this.baseMapper.selectById(id))\n          .orElseThrow(() -> CheckedException.notFound(\"实体不存在\"));\n          ${ClassName} ${businessName} = BeanUtilPlus.toBean(id,req, ${ClassName}.class);\n          this.baseMapper.updateById(${businessName});\n     }\n\n     @Override\n     public void delete(Long id) {\n       this.baseMapper.deleteById(id);\n     }\n }', '2024-12-26 14:48:40', 1, '平台管理员', '2025-12-10 17:35:07', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872172878448300033, 'WP规范（entity）', 'backend/src/main/java/${packagePath}/${moduleName}/domain/entity/${ClassName}.java', 'WP规范（entity）', 'package ${package}.${moduleName}.domain.entity;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.baomidou.mybatisplus.annotation.*;\nimport com.wemirr.framework.commons.entity.SuperEntity;\nimport lombok.AllArgsConstructor;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\nimport lombok.NoArgsConstructor;\nimport lombok.experimental.SuperBuilder;\n\n\n/**\n* <p>\n* ${table.comment!}\n* </p>\n*\n* @author ${author}\n* @since ${date}\n*/\n@EqualsAndHashCode(callSuper = true)\n@Data\n@SuperBuilder\n@NoArgsConstructor\n@AllArgsConstructor\n@TableName(\"${table.name}\")\n<#if swagger>\n@Schema(description = \"${table.comment!}\")\n</#if>\npublic class ${ClassName} extends SuperEntity<Long>{\n\n    <#-- ----------  BEGIN 字段循环遍历  ---------->\n    <#list columns as field>\n       <#if  field.generate>\n        <#if field.comment?has_content>\n        /**\n         * ${field.comment}\n         **/\n        </#if>\n        <#if swagger>\n        @Schema(description = \"${field.comment!}\")\n        </#if>\n        private ${field.propertyType} ${field.propertyName};\n        </#if>\n    </#list>\n    <#-- ----------  END 字段循环遍历  ---------->\n\n}', '2024-12-26 14:49:32', 1, '平台管理员', '2025-12-10 17:35:18', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872173056794300417, 'WP规范（DetailResp）', 'backend/src/main/java/${packagePath}/${moduleName}/domain/dto/resp/${ClassName}DetailResp.java', 'WP规范（DetailResp）', 'package ${package}.${moduleName}.domain.dto.resp;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.baomidou.mybatisplus.annotation.*;\nimport com.wemirr.framework.commons.entity.SuperEntity;\nimport lombok.AllArgsConstructor;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\nimport lombok.NoArgsConstructor;\nimport lombok.experimental.SuperBuilder;\n\n\n/**\n* <p>\n    * ${table.comment!}\n    * </p>\n*\n* @author ${author}\n* @since ${date}\n*/\n@Data\n<#if swagger>\n@Schema(description = \"${ClassName}DetailResp\")\n</#if>\npublic class ${ClassName}DetailResp {\n\n    <#-- ----------  BEGIN 字段循环遍历  ---------->\n    <#list columns as field>\n        <#if  field.generate>\n            <#if field.comment?has_content>\n            /**\n            * ${field.comment}\n            **/\n            </#if>\n            <#if swagger>\n            @Schema(description = \"${field.comment!}\")\n            </#if>\n            private ${field.propertyType} ${field.propertyName};\n        </#if>\n    </#list>\n    <#-- ----------  END 字段循环遍历  ---------->\n\n}', '2024-12-26 14:50:15', 1, '平台管理员', '2025-12-10 17:35:30', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872173195764174850, 'WP规范（PageResp）', 'backend/src/main/java/${packagePath}/${moduleName}/domain/dto/resp/${ClassName}PageResp.java', 'WP规范（PageResp）', 'package ${package}.${moduleName}.domain.dto.resp;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.wemirr.framework.db.mybatisplus.page.PageRequest;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\n\n/**\n* <p>\n    * ${table.comment!}\n    *</p>\n*\n* @author ${author}\n* @since ${date}\n*/\n<#if swagger>\n@Schema(description = \"${ClassName}PageReq\")\n</#if>\n@Data\npublic class ${ClassName}PageResp {\n\n<#-- ----------  BEGIN 字段循环遍历  ---------->\n<#list columns as field>\n    <#if field.list || field.pk>\n        <#if field.comment?has_content>\n            /**\n            * ${field.comment}\n            **/\n        </#if>\n        <#if swagger>\n         @Schema(description = \"${field.comment!}\")\n        </#if>\n        private ${field.propertyType} ${field.propertyName};\n    </#if>\n</#list>\n<#-- ----------  END 字段循环遍历  ---------->\n\n}\n', '2024-12-26 14:50:48', 1, '平台管理员', '2025-12-10 17:35:51', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872173350030675970, 'WP规范（PageReq）', 'backend/src/main/java/${packagePath}/${moduleName}/domain/dto/req/${ClassName}PageReq.java', 'WP规范（PageReq）', 'package ${package}.${moduleName}.domain.dto.req;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.wemirr.framework.db.mybatisplus.page.PageRequest;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\n\n/**\n* <p>\n* ${table.comment!}\n*</p>\n*\n* @author ${author}\n* @since ${date}\n*/\n@Data\n@EqualsAndHashCode(callSuper = true)\n<#if swagger>\n@Schema(description = \"${ClassName}PageReq\")\n</#if>\npublic class ${ClassName}PageReq extends PageRequest {\n\n<#-- ----------  BEGIN 字段循环遍历  ---------->\n<#list columns as field>\n    <#if field.search>\n    <#if field.comment?has_content>\n    /**\n    * ${field.comment}\n    **/\n    </#if>\n    <#if swagger>\n    @Schema(description = \"${field.comment!}\")\n    </#if>\n    private ${field.propertyType} ${field.propertyName};\n    </#if>\n</#list>\n<#-- ----------  END 字段循环遍历  ---------->\n\n}\n', '2024-12-26 14:51:25', 1, '平台管理员', '2025-12-10 17:35:40', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872173466347114497, 'WP规范（SaveReq）', 'backend/src/main/java/${packagePath}/${moduleName}/domain/dto/req/${ClassName}SaveReq.java', 'WP规范（SaveReq）', 'package ${package}.${moduleName}.domain.dto.req;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.wemirr.framework.db.mybatisplus.page.PageRequest;\nimport jakarta.validation.constraints.NotBlank;\nimport jakarta.validation.constraints.NotNull;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\n\n/**\n* <p>\n* ${table.comment!}\n*</p>\n*\n* @author ${author}\n* @since ${date}\n*/\n@Data\n<#if swagger>\n@Schema(description = \"${ClassName}PageReq\")\n</#if>\npublic class ${ClassName}SaveReq  {\n\n<#-- ----------  BEGIN 字段循环遍历  ---------->\n<#list columns as field>\n    <#if field.pk >\n     /**\n      * ${field.comment}\n     **/\n    <#if swagger>\n    @Schema(description = \"${field.comment!}\")\n    </#if>\n    private ${field.propertyType} ${field.propertyName};\n    </#if>\n    <#if field.inserted  >\n    <#if field.comment?has_content>\n     /**\n      * ${field.comment}\n     **/\n    </#if>\n    <#if swagger>\n    @Schema(description = \"${field.comment!}\")\n    </#if>\n     <#if field.propertyType == \"String\">\n    @NotBlank(message = \"${field.comment}不能为空\")\n     </#if>\n    <#if field.propertyType != \"String\">\n    @NotNull(message = \"${field.comment}不能为空\")\n    </#if>\n    private ${field.propertyType} ${field.propertyName};\n    </#if>\n</#list>\n<#-- ----------  END 字段循环遍历  ---------->\n\n}\n', '2024-12-26 14:51:52', 1, '平台管理员', '2025-12-10 17:34:38', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872173626129125378, 'WP规范（control）', 'backend/src/main/java/${packagePath}/${moduleName}/controller/${ClassName}Controller.java', 'WP规范（control）', 'package ${package}.${moduleName}.controller;\n                     \nimport com.baomidou.mybatisplus.core.metadata.IPage;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport ${package}.${moduleName}.domain.entity.${ClassName};\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}PageResp;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}DetailResp;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}PageReq;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}SaveReq;\nimport ${package}.${moduleName}.service.${ClassName}Service;\nimport lombok.AllArgsConstructor;\nimport org.springframework.web.bind.annotation.*;\nimport org.springframework.web.bind.annotation.RestController;\n\n\n\n/**\n * ${table.comment!} 控制器\n * @author ${author}\n * @since ${date}\n */\n@RestController\n@AllArgsConstructor\n@RequestMapping(\"/<#if businessName??>${businessName}</#if>\")\npublic class ${ClassName}Controller {\n\n    private final ${ClassName}Service ${businessName}Service;\n\n    /**\n    * 分页查询\n    */\n    @GetMapping(\"/page\")\n    public IPage<${ClassName}PageResp> pageList(${ClassName}PageReq req) {\n        return ${businessName}Service.pageList(req);\n    }\n\n    /**\n    * 通过id查询 ${table.comment!}\n    *\n    * @param id id\n    * @return ${ClassName}DetailResp\n    */\n    @GetMapping(\"/{id}\")\n    public ${ClassName}DetailResp detail(@PathVariable(\"id\") Long id) {\n        return ${businessName}Service.detail(id);\n    }\n\n    /**\n    * 新增${table.comment!}\n    *\n    * @param ${businessName} ${businessName}\n    */\n    @PostMapping(\"/create\")\n    public void create(@RequestBody ${ClassName}SaveReq ${businessName}) {\n        ${businessName}Service.create(${businessName});\n    }\n\n    /**\n    * 修改\n    *\n    * @param id      id\n    * @param ${businessName} ${businessName}\n    */\n    @PutMapping(\"/{id}/modify\")\n    public void modify(@PathVariable(\"id\") Long id ,@RequestBody ${ClassName}SaveReq req) {\n        ${businessName}Service.modify(id,req);\n    }\n\n    /**\n    * 通过id删除${table.comment!}\n    *\n    * @param id id\n    */\n    @DeleteMapping(\"/{id}\")\n    public void remove(@PathVariable Long id) {\n        ${businessName}Service.delete(id);\n    }\n}\n\n\n\n\n', '2024-12-26 14:52:30', 1, '平台管理员', '2025-12-10 17:34:25', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872173774800424962, 'WP规范（mapper）', 'backend/src/main/java/${packagePath}/${moduleName}/repository/${ClassName}Mapper.java', 'WP规范（mapper）', 'package ${package}.${moduleName}.repository;\n\nimport com.wemirr.framework.db.mybatisplus.ext.SuperMapper;\nimport ${package}.${moduleName}.domain.entity.${ClassName};\nimport org.springframework.stereotype.Repository;\n\n/**\n * ${table.comment!}接口层\n *\n * @author ${author}\n * @since ${date}\n */\n\n@Repository\npublic interface ${ClassName}Mapper extends SuperMapper<${ClassName}> {\n\n}\n', '2024-12-26 14:53:06', 1, '平台管理员', '2025-12-10 17:32:19', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872180426937208833, 'WP规范（crud）', 'front/crud.tsx', 'WP规范（crud）', 'import type {\nCreateCrudOptionsProps,\nCreateCrudOptionsRet,\n} from \'@fast-crud/fast-crud\';\nimport * as api from \"./api\";\nimport { dict } from \"@fast-crud/fast-crud\";\nimport dayjs from \"dayjs\";\n\nexport default function (props: CreateCrudOptionsProps): CreateCrudOptionsRet {\nreturn {\ncrudOptions: {\nrequest: {\npageRequest: async (query) => await api.pageList(query),\naddRequest: async ({ form }) => await api.create(form),\neditRequest: async ({ form }) => await api.modify(form),\ndelRequest: async ({ row }) => await api.remove(row.id),\n},\ntoolbar: {},\nactionbar: {\nshow: true,\nbuttons: {},\n},\nrowHandle: {\nwidth: 270,\nbuttons: {\n\n},\n},\ncolumns: {\n// ----------  BEGIN 字段循环遍历  ----------\n<#list columns as field>\n    <#if field.list || field.pk >\n        ${field.propertyName}: {\n        title: \'${field.comment!}\',\n        <#if field.propertyType == \'String\'>\n            type: \"text\",\n        </#if>\n        <#if field.propertyType == \'Instant\'>\n            type: \'datetime\',\n        </#if>\n        <#if field.propertyType == \'Boolean\'>\n            type: \'dict-radio\',\n            // 字典配置\n            dict: dict({\n            data: [\n            { value: true, label: \"启用\", color: \"success\" },\n            { value: false, label: \"禁用\", color: \"error\" },\n            ],\n            }),\n        </#if>\n        <#if field.search>\n            search: { show: true },\n        <#else>\n            search: { show: false },\n        </#if>\n         <#if field.list && !field.pk>\n            column: { show: true, width: 160 },\n        <#else>\n            column: { show: false },\n        </#if>\n         \n        <#if field.edit>\n            form: {\n            // 表单配置\n            rules: [\n            { required: false, message: \"请输入${field.comment!}\" },\n            ],\n            },\n        <#else>\n            form: { show: false },\n        </#if>\n        }\n\n        <#if field_has_next>,</#if>\n    </#if>\n</#list>\n// ----------  END 字段循环遍历  ----------\n},\n},\n};\n}', '2024-12-26 15:19:32', 1, '平台管理员', '2025-12-10 17:32:43', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872187788049780738, 'WP规范（api）', 'front/api.ts', 'WP规范（api）', 'import { defHttp } from \'#/api/request\';\n\nconst apiPrefix = \"/<#if moduleName??>${moduleName}</#if>/<#if businessName??>${businessName}</#if>\";\n\nexport function pageList(query : any) {\n    return defHttp.get(apiPrefix + \'/page\', { params: query });\n}\nexport function create(obj  : any) {\n    return defHttp.post(apiPrefix + `/create`,obj);\n}\n\nexport function modify(row : any) {\n    return defHttp.put(apiPrefix + `<#noparse>/${row.id}/modify</#noparse>`, row);\n}\n\nexport function remove(id : any) {\n    return defHttp.delete(apiPrefix + `<#noparse>/${id}</#noparse>`);\n}', '2024-12-26 15:48:47', 1, '平台管理员', '2025-12-10 17:32:28', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872188138236416002, 'WP规范（index）', 'front/index.vue', 'WP规范（index）', '<template>\n    <fs-page class=\"page-layout-card\">\n        <fs-crud ref=\"crudRef\" v-bind=\"crudBinding\"/>\n    </fs-page>\n</template>\n\n<script lang=\"ts\">\n    import {defineComponent, onMounted} from \"vue\";\n    import {useFs} from \"@fast-crud/fast-crud\";\n    import createCrudOptions from \"./crud\";\n\n    //此处为组件定义\nexport default defineComponent({\n    name: \'${ClassName}\',\n    setup() {\n        const {crudRef, crudBinding, crudExpose} = useFs({createCrudOptions, context: {}});\n        // 页面打开后获取列表数据\n        onMounted(() => {\n            crudExpose.doRefresh();\n        });\n        return {\n            crudBinding,\n            crudRef,\n        };\n    },\n});\n</script>\n', '2024-12-26 15:50:10', 1, '平台管理员', '2025-12-10 17:32:24', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` (`id`, `name`, `generate_path`, `description`, `code`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1998666639570522113, '123123', '23', '123123213', '3123123123', '2025-12-10 16:10:35', 1, '平台管理员', '2025-12-10 16:45:54', 1, '平台管理员', b'1', NULL);
COMMIT;

-- ----------------------------
-- Table structure for c_generate_template_group
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_template_group`;
CREATE TABLE `c_generate_template_group` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
                                             `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板描述',
                                             `is_default` bit(1) DEFAULT b'0' COMMENT '是否作为默认生成分组 (0: 不是, 1: 是)',
                                             `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                             `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                             `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
                                             `last_modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
                                             `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                             `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后修改人名称',
                                             `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除标记 (0: 未删除, 1: 已删除)',
                                             `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1872173937937879042 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='模板分组';

-- ----------------------------
-- Records of c_generate_template_group
-- ----------------------------
BEGIN;
INSERT INTO `c_generate_template_group` (`id`, `name`, `description`, `is_default`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `tenant_id`) VALUES (1872173937937879041, 'WP默认模板组', 'WP默认模板组', b'1', '2024-12-26 14:53:45', 1, '平台管理员', '2025-12-10 17:22:43', 1, '平台管理员', b'0', 1);
COMMIT;

-- ----------------------------
-- Table structure for c_generate_template_group_relation
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_template_group_relation`;
CREATE TABLE `c_generate_template_group_relation` (
                                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                                      `template_id` bigint NOT NULL COMMENT '模板ID',
                                                      `group_id` bigint NOT NULL COMMENT '分组id',
                                                      `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                                      `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                                      `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
                                                      `create_time` datetime DEFAULT NULL COMMENT '创建人时间',
                                                      `last_modify_by` bigint DEFAULT NULL COMMENT '最近修改人ID',
                                                      `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最近修改人名称',
                                                      `last_modify_time` datetime DEFAULT NULL COMMENT '最近修改时间',
                                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1998684793084473348 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of c_generate_template_group_relation
-- ----------------------------
BEGIN;
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 1871561649547747329, 1871842432833343490, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, 1871561649547747329, 1871886476003246081, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3, 1872188138236416002, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (4, 1872187788049780738, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (5, 1872180426937208833, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (6, 1872173774800424962, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (7, 1872173466347114497, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (8, 1872173626129125378, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (9, 1872173350030675970, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10, 1872173195764174850, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (11, 1872173056794300417, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (12, 1872172878448300033, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (13, 1872172659639848962, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (14, 1872172480907972610, 1872173937937879041, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742694105090, 1872172480907972610, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742702493697, 1872172659639848962, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742702493698, 1872172878448300033, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742706688002, 1872173056794300417, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742706688003, 1872173195764174850, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742706688004, 1872173350030675970, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742706688005, 1872173626129125378, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742706688006, 1872173466347114497, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742710882306, 1872173774800424962, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742710882307, 1872180426937208833, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742710882308, 1872187788049780738, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684742710882309, 1872188138236416002, 1872173937937879041, 1, 1, '平台管理员', '2025-12-10 17:22:31', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793067696130, 1872188138236416002, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793076084737, 1872187788049780738, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793076084738, 1872180426937208833, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793076084739, 1872173774800424962, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793076084740, 1872173626129125378, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793080279041, 1872173466347114497, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793080279042, 1872173350030675970, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793080279043, 1872173195764174850, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793080279044, 1872173056794300417, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793084473345, 1872172878448300033, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793084473346, 1872172659639848962, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
INSERT INTO `c_generate_template_group_relation` (`id`, `template_id`, `group_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998684793084473347, 1872172480907972610, 1872173937937879041, 0, 1, '平台管理员', '2025-12-10 17:22:43', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for c_i18n_data
-- ----------------------------
DROP TABLE IF EXISTS `c_i18n_data`;
CREATE TABLE `c_i18n_data` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `code` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '唯一标识 = 业务:关键词',
                               `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                               `tenant_id` bigint DEFAULT NULL,
                               `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态0：未删除，1：已删除',
                               `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                               `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                               `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                               `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2001648671799848962 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='国际化信息';

-- ----------------------------
-- Records of c_i18n_data
-- ----------------------------
BEGIN;
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 'SEX.1', '性别->男', 1, b'0', 0, NULL, '2024-07-19 09:06:38', 1, '平台管理员', '2025-12-11 13:06:03');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, 'SEX.2', '性别->女', 1, b'0', 0, NULL, '2024-07-19 09:06:38', 0, NULL, '2024-07-19 09:18:35');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1721821542981042178, 'i18n.db', '测试中文内容 {i18n.demo}', 1, b'0', 1, '长风一梦0000', '2023-11-07 17:27:00', 1, '平台管理员', '2025-12-11 13:08:56');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1724355494514126849, '123', '213', 1, b'1', 1, '长风一梦0000', '2023-11-14 17:16:02', 0, NULL, '2023-11-14 09:16:04');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001648671799848961, '测试国际化', '12312', 1, b'0', 1, '平台管理员', '2025-12-18 21:40:07', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for c_i18n_locale_message
-- ----------------------------
DROP TABLE IF EXISTS `c_i18n_locale_message`;
CREATE TABLE `c_i18n_locale_message` (
                                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                         `parent_id` bigint DEFAULT NULL COMMENT 'i18n_data.id',
                                         `locale` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '语言',
                                         `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文本值，可以使用 {} 加角标，作为占位符',
                                         `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                         `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2001648671845986309 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='国际化信息';

-- ----------------------------
-- Records of c_i18n_locale_message
-- ----------------------------
BEGIN;
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (20, 2, 'zh_CN', '女', 0, NULL, '2024-07-19 09:19:03');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (21, 2, 'en_US', 'Female', 0, NULL, '2024-07-19 09:19:05');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (1998982589893181441, 1, 'en_US', 'Male', 1, '平台管理员', '2025-12-11 13:06:03');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (1998982589893181442, 1, 'zh_CN', '男', 1, '平台管理员', '2025-12-11 13:06:03');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (1998983313335209986, 1721821542981042178, 'zh_CN', '测试中文内容 {i18n.demo}', 1, '平台管理员', '2025-12-11 13:08:56');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (1998983313335209987, 1721821542981042178, 'en_US', 'English Context', 1, '平台管理员', '2025-12-11 13:08:56');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (2001648671845986305, 2001648671799848961, 'zh_CN', '213', 1, '平台管理员', '2025-12-18 21:40:07');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (2001648671845986306, 2001648671799848961, 'en_US', '123', 1, '平台管理员', '2025-12-18 21:40:07');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (2001648671845986307, 2001648671799848961, 'ja_JP', '123', 1, '平台管理员', '2025-12-18 21:40:07');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `create_by`, `create_name`, `create_time`) VALUES (2001648671845986308, 2001648671799848961, 'ko_KR', '123', 1, '平台管理员', '2025-12-18 21:40:07');
COMMIT;

-- ----------------------------
-- Table structure for c_login_log
-- ----------------------------
DROP TABLE IF EXISTS `c_login_log`;
CREATE TABLE `c_login_log` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                               `tenant_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户编码',
                               `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录IP',
                               `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录地点',
                               `client_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人客户端ID',
                               `principal` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人账号',
                               `platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台',
                               `engine` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '引擎类型',
                               `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
                               `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作系统',
                               `login_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录类型',
                               `create_by` bigint DEFAULT NULL,
                               `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='登录日志';

-- ----------------------------
-- Records of c_login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `c_opt_log`;
CREATE TABLE `c_opt_log` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                             `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户编码',
                             `module` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日志模块',
                             `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作描述',
                             `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP（支持IPv6）',
                             `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
                             `trace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日志链路追踪id日志标志',
                             `action` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类路径',
                             `uri` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求地址',
                             `http_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'GET' COMMENT '请求类型ENUM(''GET'', ''POST'', ''PUT'', ''DELETE'', ''PATCH'', ''TRACE'', ''HEAD'', ''OPTIONS'')',
                             `request` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '请求参数',
                             `response` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '返回值',
                             `message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '执行消息',
                             `status` bit(1) DEFAULT NULL COMMENT '日志状态（true=正常;false=异常）',
                             `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
                             `end_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
                             `duration` bigint DEFAULT '0' COMMENT '消耗时间',
                             `browser` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
                             `os` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作系统',
                             `engine` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器引擎',
                             `platform` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台信息',
                             `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求令牌',
                             `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                             `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统日志';

-- ----------------------------
-- Records of c_opt_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oss_config
-- ----------------------------
DROP TABLE IF EXISTS `oss_config`;
CREATE TABLE `oss_config` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `platform` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '平台名',
                              `tenant_id` bigint NOT NULL DEFAULT '0',
                              `code` varchar(64) NOT NULL DEFAULT '' COMMENT '配置唯一标识(原platform)',
                              `type` varchar(50) NOT NULL DEFAULT '' COMMENT '厂商类型',
                              `status` tinyint NOT NULL DEFAULT '0',
                              `access_key` varchar(100) DEFAULT '',
                              `secret_key` varchar(255) DEFAULT '',
                              `bucket_name` varchar(255) DEFAULT '',
                              `region` varchar(255) DEFAULT '',
                              `domain` varchar(255) DEFAULT '',
                              `base_path` varchar(255) DEFAULT '',
                              `end_point` varchar(255) DEFAULT '',
                              `deleted` bit(1) NOT NULL DEFAULT b'0',
                              `create_by` varchar(64) DEFAULT '',
                              `create_name` varchar(64) DEFAULT '',
                              `create_time` datetime DEFAULT NULL,
                              `last_modify_by` varchar(64) DEFAULT '',
                              `last_modify_name` varchar(64) DEFAULT '',
                              `last_modify_time` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `uk_tenant_code` (`tenant_id`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2000434144538492930 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='OSS配置';

-- ----------------------------
-- Records of oss_config
-- ----------------------------
BEGIN;
INSERT INTO `oss_config` (`id`, `platform`, `tenant_id`, `code`, `type`, `status`, `access_key`, `secret_key`, `bucket_name`, `region`, `domain`, `base_path`, `end_point`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 'minio-wp-local', 1, 'minio-wp-local', 'minio', 0, 'dQAztlq1gMdAaTfR5i7K', 'S6wpCw8Jo2ujqqs2bgYcBGbp4sOLtvzVy2Yvm6zF', 'wp-local', NULL, 'http://127.0.0.1:19000/wp-local/', 'minio/', 'http://127.0.0.1:19000', b'0', '1', '平台管理员', '2024-12-16 10:41:31', '1', '平台管理员', '2025-12-15 13:14:01');
INSERT INTO `oss_config` (`id`, `platform`, `tenant_id`, `code`, `type`, `status`, `access_key`, `secret_key`, `bucket_name`, `region`, `domain`, `base_path`, `end_point`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1868599415401865217, 's3-wp-local', 1, 's3-wp-local', 's3', 0, 'dQAztlq1gMdAaTfR5i7K', 'S6wpCw8Jo2ujqqs2bgYcBGbp4sOLtvzVy2Yvm6zF', 'wp-local', NULL, 'http://127.0.0.1:19000/wp-local/', 'dev/', 'http://127.0.0.1:19000', b'0', '1', '平台管理员', '2024-12-16 18:09:52', '1', '平台管理员', '2025-12-15 13:14:01');
COMMIT;

-- ----------------------------
-- Table structure for oss_file
-- ----------------------------
DROP TABLE IF EXISTS `oss_file`;
CREATE TABLE `oss_file` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户ID',
                            `platform` varchar(64) NOT NULL DEFAULT '' COMMENT '平台标识',
                            `category` varchar(32) DEFAULT '' COMMENT '分类',
                            `filename` varchar(256) NOT NULL DEFAULT '' COMMENT '文件名',
                            `original_filename` varchar(256) NOT NULL DEFAULT '' COMMENT '原始文件名',
                            `url` varchar(512) NOT NULL DEFAULT '' COMMENT 'URL地址',
                            `th_url` varchar(512) DEFAULT '' COMMENT '缩略图URL',
                            `base_path` varchar(256) DEFAULT '' COMMENT '基础路径',
                            `path` varchar(512) DEFAULT '' COMMENT '存储路径',
                            `ext` varchar(32) DEFAULT '' COMMENT '扩展名',
                            `content_type` varchar(128) DEFAULT '' COMMENT 'MIME类型',
                            `size` bigint DEFAULT '0' COMMENT '文件大小',
                            `th_filename` varchar(256) DEFAULT '',
                            `th_size` bigint DEFAULT '0',
                            `th_content_type` varchar(128) DEFAULT '',
                            `object_id` varchar(64) DEFAULT '' COMMENT '业务对象ID',
                            `object_type` varchar(64) DEFAULT '' COMMENT '业务对象类型',
                            `metadata` text COMMENT '元数据',
                            `user_metadata` text,
                            `th_metadata` text,
                            `th_user_metadata` text,
                            `attr` text,
                            `hash_info` text,
                            `file_acl` varchar(32) DEFAULT '',
                            `th_file_acl` varchar(32) DEFAULT '',
                            `deleted` bit(1) NOT NULL DEFAULT b'0',
                            `create_by` varchar(64) DEFAULT '',
                            `create_name` varchar(64) DEFAULT '',
                            `create_time` datetime DEFAULT NULL,
                            `last_modify_by` varchar(64) DEFAULT '',
                            `last_modify_name` varchar(64) DEFAULT '',
                            `last_modify_time` datetime DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `idx_tenant_platform` (`tenant_id`,`platform`),
                            KEY `idx_create_time` (`create_time`),
                            KEY `idx_filename` (`filename`),
                            KEY `idx_object` (`object_id`,`object_type`)
) ENGINE=InnoDB AUTO_INCREMENT=2000433342876880898 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='OSS文件记录';

-- ----------------------------
-- Records of oss_file
-- ----------------------------
BEGIN;
INSERT INTO `oss_file` (`id`, `tenant_id`, `platform`, `category`, `filename`, `original_filename`, `url`, `th_url`, `base_path`, `path`, `ext`, `content_type`, `size`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `hash_info`, `file_acl`, `th_file_acl`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999344453592420353, 0, 's3-wp-local', 'IMAGE', '693ba23e28ccaff50c5a9971.png', '新对话 (4).png', 'http://127.0.0.1:19000/wp-local/dev/693ba23e28ccaff50c5a9971.png', '', 'dev/', '', 'png', 'image/png', 3906621, NULL, 0, '', '', '', '{}', '{}', '{}', '{}', '{}', '{}', '', '', b'1', '1', '平台管理员', '2025-12-12 13:03:58', '1', '平台管理员', '2025-12-12 13:13:36');
INSERT INTO `oss_file` (`id`, `tenant_id`, `platform`, `category`, `filename`, `original_filename`, `url`, `th_url`, `base_path`, `path`, `ext`, `content_type`, `size`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `hash_info`, `file_acl`, `th_file_acl`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999344544160026626, 0, 's3-wp-local', 'IMAGE', '693ba25328ccaff50c5a9972.png', '新对话 (4).png', 'http://127.0.0.1:19000/wp-local/dev/693ba25328ccaff50c5a9972.png', '', 'dev/', '', 'png', 'image/png', 3906621, NULL, 0, '', '', '', '{}', '{}', '{}', '{}', '{}', '{}', '', '', b'0', '1', '平台管理员', '2025-12-12 13:04:20', '', '', NULL);
INSERT INTO `oss_file` (`id`, `tenant_id`, `platform`, `category`, `filename`, `original_filename`, `url`, `th_url`, `base_path`, `path`, `ext`, `content_type`, `size`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `hash_info`, `file_acl`, `th_file_acl`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999344667019579394, 0, 's3-wp-local', 'IMAGE', '693ba26a28ccaff50c5a9973.png', '新对话 (4).png', 'http://127.0.0.1:19000/wp-local/dev/693ba26a28ccaff50c5a9973.png', '', 'dev/', '', 'png', 'image/png', 3906621, NULL, 0, '', '', '', '{}', '{}', '{}', '{}', '{}', '{}', '', '', b'1', '1', '平台管理员', '2025-12-12 13:04:43', '1', '平台管理员', '2025-12-15 13:11:40');
INSERT INTO `oss_file` (`id`, `tenant_id`, `platform`, `category`, `filename`, `original_filename`, `url`, `th_url`, `base_path`, `path`, `ext`, `content_type`, `size`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `hash_info`, `file_acl`, `th_file_acl`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999344801811927042, 0, 's3-wp-local', 'IMAGE', '693ba27828ccaff50c5a9974.png', '新对话 (4).png', 'http://127.0.0.1:19000/wp-local/dev/693ba27828ccaff50c5a9974.png', '', 'dev/', '', 'png', 'image/png', 3906621, NULL, 0, '', '', '', '{}', '{}', '{}', '{}', '{}', '{}', '', '', b'1', '1', '平台管理员', '2025-12-12 13:04:57', '1', '平台管理员', '2025-12-12 13:11:30');
INSERT INTO `oss_file` (`id`, `tenant_id`, `platform`, `category`, `filename`, `original_filename`, `url`, `th_url`, `base_path`, `path`, `ext`, `content_type`, `size`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `hash_info`, `file_acl`, `th_file_acl`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999344939016007681, 0, 's3-wp-local', 'IMAGE', '693ba2ae28ccd85204a94f70.png', '新对话 (4).png', 'http://127.0.0.1:19000/wp-local/dev/693ba2ae28ccd85204a94f70.png', '', 'dev/', '', 'png', 'image/png', 3906621, NULL, 0, '', '', '', '{}', '{}', '{}', '{}', '{}', '{}', '', '', b'1', '1', '平台管理员', '2025-12-12 13:05:50', '1', '平台管理员', '2025-12-12 13:10:04');
INSERT INTO `oss_file` (`id`, `tenant_id`, `platform`, `category`, `filename`, `original_filename`, `url`, `th_url`, `base_path`, `path`, `ext`, `content_type`, `size`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `hash_info`, `file_acl`, `th_file_acl`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999345259603439617, 0, 's3-wp-local', 'OTHER', '693ba2fa28ccd85204a94f71.sql', 'v4-ai1.sql', 'http://127.0.0.1:19000/wp-local/dev/693ba2fa28ccd85204a94f71.sql', '', 'dev/', '', 'sql', 'application/octet-stream', 18532, NULL, 0, '', '', '', '{}', '{}', '{}', '{}', '{}', '{}', '', '', b'1', '1', '平台管理员', '2025-12-12 13:07:07', '1', '平台管理员', '2025-12-12 13:10:01');
INSERT INTO `oss_file` (`id`, `tenant_id`, `platform`, `category`, `filename`, `original_filename`, `url`, `th_url`, `base_path`, `path`, `ext`, `content_type`, `size`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `hash_info`, `file_acl`, `th_file_acl`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999346365695938561, 0, 's3-wp-local', 'IMAGE', '693ba40628cc6022a4d39203.png', '新对话 (4).png', 'http://127.0.0.1:19000/wp-local/dev/693ba40628cc6022a4d39203.png', '', 'dev/', '', 'png', 'image/png', 3906621, '', 0, '', '', '', '{}', '{}', '{}', '{}', '{}', '{}', '', '', b'1', '1', '平台管理员', '2025-12-12 13:11:34', '1', '平台管理员', '2025-12-12 13:15:23');
INSERT INTO `oss_file` (`id`, `tenant_id`, `platform`, `category`, `filename`, `original_filename`, `url`, `th_url`, `base_path`, `path`, `ext`, `content_type`, `size`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `hash_info`, `file_acl`, `th_file_acl`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2000433342876880897, 0, 's3-wp-local', 'IMAGE', '693f985970901771b3894983.png', '新对话 (4).png', 'http://127.0.0.1:19000/wp-local/dev/693f985970901771b3894983.png', '', 'dev/', '', 'png', 'image/png', 3906621, '', 0, '', '', '', '{}', '{}', '{}', '{}', '{}', '{}', '', '', b'1', '1', '平台管理员', '2025-12-15 13:10:49', '1', '平台管理员', '2025-12-15 13:11:37');
COMMIT;

-- ----------------------------
-- Table structure for plat_plan_def_res
-- ----------------------------
DROP TABLE IF EXISTS `plat_plan_def_res`;
CREATE TABLE `plat_plan_def_res` (
                                     `id` bigint NOT NULL AUTO_INCREMENT,
                                     `plan_id` bigint NOT NULL COMMENT '角色ID',
                                     `res_id` bigint NOT NULL COMMENT '菜单ID',
                                     `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标记',
                                     `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                     `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                     `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '修改人',
                                     `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                     `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
                                     PRIMARY KEY (`id`),
                                     KEY `def_res_idx_plan_id` (`plan_id`),
                                     KEY `def_res_idx_res_id` (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1999752937869541379 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色权限表';

-- ----------------------------
-- Records of plat_plan_def_res
-- ----------------------------
BEGIN;
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697511452673, 1999750697427566594, 3020207, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697515646977, 1999750697427566594, 32002, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697519841281, 1999750697427566594, 3, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697519841282, 1999750697427566594, 32003, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697524035586, 1999750697427566594, 3020202, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697524035587, 1999750697427566594, 3020203, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697524035588, 1999750697427566594, 3020201, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697524035589, 1999750697427566594, 30604, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697524035590, 1999750697427566594, 30605, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697528229889, 1999750697427566594, 30606, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697528229890, 1999750697427566594, 30607, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697528229891, 1999750697427566594, 30608, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697528229892, 1999750697427566594, 3010105, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697528229893, 1999750697427566594, 30101, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697532424194, 1999750697427566594, 3010104, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697532424195, 1999750697427566594, 30102, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697532424196, 1999750697427566594, 30103, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697536618498, 1999750697427566594, 3010101, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697536618499, 1999750697427566594, 3010103, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697536618500, 1999750697427566594, 3010102, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697536618501, 1999750697427566594, 31003, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697536618502, 1999750697427566594, 31005, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697540812802, 1999750697427566594, 3020208, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697540812803, 1999750697427566594, 31006, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697540812804, 1999750697427566594, 301, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697540812805, 1999750697427566594, 302, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697540812806, 1999750697427566594, 303, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697545007106, 1999750697427566594, 306, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697545007107, 1999750697427566594, 3010201, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697545007108, 1999750697427566594, 3010203, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697545007109, 1999750697427566594, 3060504, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697545007110, 1999750697427566594, 310, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697545007111, 1999750697427566594, 3010202, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697549201410, 1999750697427566594, 3060502, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697549201411, 1999750697427566594, 3060503, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697549201412, 1999750697427566594, 3060501, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697549201413, 1999750697427566594, 320, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697549201414, 1999750697427566594, 3010301, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697553395714, 1999750697427566594, 3010303, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697553395715, 1999750697427566594, 3010302, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697553395716, 1999750697427566594, 30301, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697553395717, 1999750697427566594, 30303, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697557590018, 1999750697427566594, 3020102, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697557590019, 1999750697427566594, 3020103, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697557590020, 1999750697427566594, 3020101, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697557590021, 1999750697427566594, 3030102, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697557590022, 1999750697427566594, 30201, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697561784321, 1999750697427566594, 3030103, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697561784322, 1999750697427566594, 30202, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697561784323, 1999750697427566594, 3030101, 1, 1, '平台管理员', '2025-12-13 15:58:15', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794583728130, 1999750697427566594, 320, 1, 1, '平台管理员', '2025-12-13 16:06:34', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794592116738, 1999750697427566594, 3020207, 1, 1, '平台管理员', '2025-12-13 16:06:34', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794592116739, 1999750697427566594, 32002, 1, 1, '平台管理员', '2025-12-13 16:06:34', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794596311041, 1999750697427566594, 32003, 1, 1, '平台管理员', '2025-12-13 16:06:34', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794596311042, 1999750697427566594, 3, 1, 1, '平台管理员', '2025-12-13 16:06:34', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794596311043, 1999750697427566594, 3020202, 1, 1, '平台管理员', '2025-12-13 16:06:34', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794596311044, 1999750697427566594, 3020203, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794596311045, 1999750697427566594, 3020201, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794600505346, 1999750697427566594, 30604, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794600505347, 1999750697427566594, 30605, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794600505348, 1999750697427566594, 30606, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794600505349, 1999750697427566594, 30607, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794600505350, 1999750697427566594, 3010301, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794604699649, 1999750697427566594, 30608, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794604699650, 1999750697427566594, 3010303, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794604699651, 1999750697427566594, 3010302, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794604699652, 1999750697427566594, 3010105, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794608893953, 1999750697427566594, 3010104, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794608893954, 1999750697427566594, 30101, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794608893955, 1999750697427566594, 30102, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794608893956, 1999750697427566594, 30103, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794608893957, 1999750697427566594, 3010101, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794608893958, 1999750697427566594, 3010103, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794613088257, 1999750697427566594, 31003, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794613088258, 1999750697427566594, 3010102, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794613088259, 1999750697427566594, 30301, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794613088260, 1999750697427566594, 31005, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794613088261, 1999750697427566594, 31006, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794617282561, 1999750697427566594, 3020208, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794617282562, 1999750697427566594, 30303, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794617282563, 1999750697427566594, 301, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794617282564, 1999750697427566594, 302, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794617282565, 1999750697427566594, 303, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794617282566, 1999750697427566594, 306, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794621476866, 1999750697427566594, 3010201, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794621476867, 1999750697427566594, 310, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794621476868, 1999750697427566594, 3060504, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794621476869, 1999750697427566594, 3010203, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794621476870, 1999750697427566594, 3010202, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794621476871, 1999750697427566594, 3030102, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794621476872, 1999750697427566594, 3060502, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794625671169, 1999750697427566594, 3030103, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794625671170, 1999750697427566594, 3060503, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794625671171, 1999750697427566594, 30202, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794625671172, 1999750697427566594, 3030101, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752794625671173, 1999750697427566594, 3060501, 1, 1, '平台管理员', '2025-12-13 16:06:35', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937823404033, 1999750697427566594, 320, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937831792641, 1999750697427566594, 3020207, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937831792642, 1999750697427566594, 32002, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937831792643, 1999750697427566594, 3, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937831792644, 1999750697427566594, 32003, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937835986945, 1999750697427566594, 3020202, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937835986946, 1999750697427566594, 3020203, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937835986947, 1999750697427566594, 3020201, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937835986948, 1999750697427566594, 30604, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937835986949, 1999750697427566594, 30605, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937840181249, 1999750697427566594, 30606, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937840181250, 1999750697427566594, 30607, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937840181251, 1999750697427566594, 30608, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937844375553, 1999750697427566594, 3010301, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937844375554, 1999750697427566594, 3010303, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937844375555, 1999750697427566594, 3010302, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937844375556, 1999750697427566594, 3010105, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937844375557, 1999750697427566594, 30101, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937848569857, 1999750697427566594, 3010104, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937848569858, 1999750697427566594, 30102, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937848569859, 1999750697427566594, 30103, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937848569860, 1999750697427566594, 3010101, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937848569861, 1999750697427566594, 3010103, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937848569862, 1999750697427566594, 3010102, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937852764161, 1999750697427566594, 31003, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937852764162, 1999750697427566594, 31005, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937852764163, 1999750697427566594, 30301, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937852764164, 1999750697427566594, 3020208, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937852764165, 1999750697427566594, 31006, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937852764166, 1999750697427566594, 30303, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937856958465, 1999750697427566594, 301, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937856958466, 1999750697427566594, 302, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937856958467, 1999750697427566594, 303, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937856958468, 1999750697427566594, 306, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937856958469, 1999750697427566594, 3010201, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937856958470, 1999750697427566594, 3010203, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937861152770, 1999750697427566594, 3060504, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937861152771, 1999750697427566594, 310, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937861152772, 1999750697427566594, 3010202, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937861152773, 1999750697427566594, 3060502, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937865347073, 1999750697427566594, 3030102, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937865347074, 1999750697427566594, 3060503, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937865347075, 1999750697427566594, 3030103, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937865347076, 1999750697427566594, 30202, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937869541377, 1999750697427566594, 3060501, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
INSERT INTO `plat_plan_def_res` (`id`, `plan_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999752937869541378, 1999750697427566594, 3030101, 0, 1, '平台管理员', '2025-12-13 16:07:09', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for plat_plan_definition
-- ----------------------------
DROP TABLE IF EXISTS `plat_plan_definition`;
CREATE TABLE `plat_plan_definition` (
                                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '产品定义ID',
                                        `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '产品名称',
                                        `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                        `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品Logo链接',
                                        `status` tinyint(1) DEFAULT '1' COMMENT '启用状态（租户字典 0=禁用;1=启用）',
                                        `deleted` bit(1) DEFAULT b'0',
                                        `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '产品详情',
                                        `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                        `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                                        `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                        `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1999750697427566595 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='产品定义表';

-- ----------------------------
-- Records of plat_plan_definition
-- ----------------------------
BEGIN;
INSERT INTO `plat_plan_definition` (`id`, `name`, `code`, `logo`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750697427566594, '租户默认套餐', 'PD202512130001', NULL, 1, b'0', '租户默认套餐', 1, '平台管理员', '2025-12-13 15:58:14', 1, '平台管理员', '2025-12-13 16:07:09');
COMMIT;

-- ----------------------------
-- Table structure for plat_plan_pricing
-- ----------------------------
DROP TABLE IF EXISTS `plat_plan_pricing`;
CREATE TABLE `plat_plan_pricing` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `plan_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '套餐ID',
                                     `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '定价名称',
                                     `user_num` int DEFAULT NULL COMMENT '用户数量',
                                     `month_num` int DEFAULT NULL COMMENT '月数',
                                     `amount` decimal(10,0) DEFAULT NULL COMMENT '总价',
                                     `unit_price` decimal(10,0) DEFAULT NULL COMMENT '用户单价',
                                     `over_price` decimal(10,2) DEFAULT NULL COMMENT '超额单价',
                                     `status` bit(1) DEFAULT b'0' COMMENT '状态(1=启用;0=禁用)',
                                     `description` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
                                     `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0=未删除;1=已删除）',
                                     `create_by` bigint DEFAULT NULL COMMENT '创建人id',
                                     `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                     `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                                     `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                     `last_modify_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='产品定价表';

-- ----------------------------
-- Records of plat_plan_pricing
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for plat_plan_subscription
-- ----------------------------
DROP TABLE IF EXISTS `plat_plan_subscription`;
CREATE TABLE `plat_plan_subscription` (
                                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                          `plan_id` bigint DEFAULT NULL COMMENT '产品ID',
                                          `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                          `user_num` int DEFAULT NULL COMMENT '用户数量',
                                          `month_num` int DEFAULT NULL COMMENT '月数',
                                          `license_price` decimal(19,2) DEFAULT NULL COMMENT '用户单价',
                                          `total_amount` decimal(19,2) DEFAULT NULL COMMENT '总金额',
                                          `discount_amount` decimal(19,2) DEFAULT NULL COMMENT '优惠金额',
                                          `statement_amount` decimal(19,2) DEFAULT NULL COMMENT '结算金额',
                                          `statement_price` decimal(19,2) DEFAULT NULL COMMENT '结算单价',
                                          `start_time` datetime DEFAULT NULL COMMENT '开始时间',
                                          `end_time` datetime DEFAULT NULL COMMENT '结束时间',
                                          `payment_status` tinyint DEFAULT NULL COMMENT '支付状态(0=待支付;10=部分支付;20=已支付)',
                                          `description` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
                                          `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除',
                                          `create_by` bigint DEFAULT NULL COMMENT '创建人id',
                                          `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                                          `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                          `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1999750850532245506 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='产品订阅信息';

-- ----------------------------
-- Records of plat_plan_subscription
-- ----------------------------
BEGIN;
INSERT INTO `plat_plan_subscription` (`id`, `plan_id`, `tenant_id`, `user_num`, `month_num`, `license_price`, `total_amount`, `discount_amount`, `statement_amount`, `statement_price`, `start_time`, `end_time`, `payment_status`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999750850532245505, 1999750697427566594, 8, 100, 12, 100.00, 120000.00, 0.00, 120000.00, 100.00, '2025-12-13 15:58:42', '2026-12-13 15:58:42', 20, '测试租户', b'0', 1, '平台管理员', '2025-12-13 15:58:51', NULL, NULL, '2025-12-13 08:52:44');
COMMIT;

-- ----------------------------
-- Table structure for sys_data_permission_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_permission_ref`;
CREATE TABLE `sys_data_permission_ref` (
                                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                           `scope_type` int DEFAULT NULL COMMENT '权限范围类型: 10-个人, 20-自定义, 30-本级, 40-本级及子级, 50-全部',
                                           `owner_id` bigint NOT NULL COMMENT '拥有者',
                                           `owner_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '拥有类型（角色，用户）',
                                           `data_id` bigint DEFAULT NULL COMMENT '数据ID',
                                           `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '数据类型（机构、角色、租户等等）',
                                           `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标记',
                                           `create_by` bigint DEFAULT NULL COMMENT '创建人id',
                                           `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `last_modify_by` bigint DEFAULT NULL COMMENT '最近修改人',
                                           `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最近修改人',
                                           `last_modify_time` datetime DEFAULT NULL COMMENT '最近修改时间',
                                           PRIMARY KEY (`id`) USING BTREE,
                                           KEY `data_perm_idx_owner_id` (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2001618919068160005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='数据权限资源表';

-- ----------------------------
-- Records of sys_data_permission_ref
-- ----------------------------
BEGIN;
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065538, NULL, 1948195640893706241, 'role', 100, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065539, NULL, 1948195640893706241, 'role', 10001, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065540, NULL, 1948195640893706241, 'role', 10002, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065541, NULL, 1948195640893706241, 'role', 10004, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065542, NULL, 1948195640893706241, 'role', 10003, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065543, NULL, 1948195640893706241, 'role', 10005, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065544, NULL, 1948195640893706241, 'role', 10006, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065545, NULL, 1948195640893706241, 'role', 10101, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065546, NULL, 1948195640893706241, 'role', 10103, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065547, NULL, 1948195640893706241, 'role', 10102, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065548, NULL, 1948195640893706241, 'role', 10201, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065549, NULL, 1948195640893706241, 'role', 10203, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065550, NULL, 1948195640893706241, 'role', 10202, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998250033728065551, NULL, 1948195640893706241, 'role', 10204, 'org', 0, 1, '平台管理员', '2025-12-09 12:35:08', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998288725465235458, NULL, 1945395080805158913, 'role', 10001, 'org', 0, 1, '平台管理员', '2025-12-09 15:08:53', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998288725465235459, NULL, 1945395080805158913, 'role', 100, 'org', 0, 1, '平台管理员', '2025-12-09 15:08:53', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998288725465235460, NULL, 1945395080805158913, 'role', 10101, 'org', 0, 1, '平台管理员', '2025-12-09 15:08:53', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998288725465235461, NULL, 1945395080805158913, 'role', 10103, 'org', 0, 1, '平台管理员', '2025-12-09 15:08:53', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139201, NULL, 1950568775576129537, 'role', 10204, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139202, NULL, 1950568775576129537, 'role', 10202, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139203, NULL, 1950568775576129537, 'role', 10203, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139204, NULL, 1950568775576129537, 'role', 10201, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139205, NULL, 1950568775576129537, 'role', 10002, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139206, NULL, 1950568775576129537, 'role', 10102, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139207, NULL, 1950568775576129537, 'role', 10103, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139208, NULL, 1950568775576129537, 'role', 10101, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139209, NULL, 1950568775576129537, 'role', 10001, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139210, NULL, 1950568775576129537, 'role', 100, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998704186745139211, NULL, 1950568775576129537, 'role', 10004, 'org', 0, 1, '平台管理员', '2025-12-10 18:39:47', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001520667085905922, 50, 2, 'role', NULL, 'company', 0, 1, '平台管理员', '2025-12-18 13:11:28', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001520667085905923, 20, 2, 'role', 1, 'user', 0, 1, '平台管理员', '2025-12-18 13:11:28', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001520667085905924, 20, 2, 'role', 2, 'user', 0, 1, '平台管理员', '2025-12-18 13:11:28', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001521973167005697, 20, 1, 'role', 10, 'company', 0, 1, '平台管理员', '2025-12-18 13:16:40', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001521973167005698, 20, 1, 'role', 6, 'company', 0, 1, '平台管理员', '2025-12-18 13:16:40', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001521973167005699, 20, 1, 'role', 4, 'company', 0, 1, '平台管理员', '2025-12-18 13:16:40', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001521973171200002, 20, 1, 'role', 2, 'company', 0, 1, '平台管理员', '2025-12-18 13:16:40', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001521973171200003, 10, 1, 'role', NULL, 'user', 0, 1, '平台管理员', '2025-12-18 13:16:40', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001618919063965697, 20, 2001586678602641409, 'role', 1, 'company', 0, 1, '平台管理员', '2025-12-18 19:41:53', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001618919068160002, 20, 2001586678602641409, 'role', 4, 'company', 0, 1, '平台管理员', '2025-12-18 19:41:53', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001618919068160003, 20, 2001586678602641409, 'role', 7, 'company', 0, 1, '平台管理员', '2025-12-18 19:41:53', NULL, NULL, NULL);
INSERT INTO `sys_data_permission_ref` (`id`, `scope_type`, `owner_id`, `owner_type`, `data_id`, `data_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001618919068160004, 10, 2001586678602641409, 'role', NULL, 'user', 0, 1, '平台管理员', '2025-12-18 19:41:53', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '名称',
                            `code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '编码\n一颗树仅仅有一个统一的编码',
                            `parent_id` bigint DEFAULT NULL COMMENT '父级字典ID',
                            `parent_code` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '父级字典编码',
                            `full_code_path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '完整路径',
                            `type` smallint DEFAULT NULL COMMENT '字典类型(0=平台字典;1=租户字典)',
                            `description` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述',
                            `status` bit(1) DEFAULT b'0' COMMENT '状态',
                            `sequence` int DEFAULT '0' COMMENT '排序',
                            `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除标记',
                            `create_by` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人id',
                            `create_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人名称',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `last_modify_by` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人id',
                            `last_modify_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新人名称',
                            `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1700070256599982176 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, '民族', 'NATION', 0, NULL, 'NATION', 0, '民族', b'1', 0, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, '在职状态', 'POSITION_STATUS', 0, NULL, 'POSITION_STATUS', 0, '在职状态', b'1', 1, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3, '学历', 'EDUCATION', 0, NULL, 'EDUCATION', 0, '学历', b'1', 2, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (4, '行政区级', 'AREA_LEVEL', 0, NULL, 'AREA_LEVEL', 0, '行政区级', b'1', 3, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (5, '机构类型', 'ORG_TYPE', 0, NULL, 'ORG_TYPE', 0, '机构类型', b'1', 4, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (6, '性别', 'SEX', 0, NULL, 'SEX', 0, '性别', b'1', 5, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (7, '消息类型', 'NOTICE', 0, NULL, 'NOTICE', 0, '消息类型', b'1', 6, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (8, '岗位类型', 'POSITION_TYPE', 0, NULL, 'POSITION_TYPE', 0, '岗位类型', b'1', 7, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (9, '颜色', 'COLOR', 0, NULL, 'COLOR', 0, '颜色', b'1', 8, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10, '行业类型', 'INDUSTRY', 0, NULL, 'INDUSTRY', 0, '行业类型', b'1', 9, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (11, '启停状态', 'STATUS', 0, NULL, 'STATUS', 1, '启停状态', b'1', 1, b'0', '0', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1686979951801745410, '用印类型', 'CONTRACT_SEAL_TYPE', 0, NULL, 'CONTRACT_SEAL_TYPE', 1, '用印类型', b'1', 0, b'0', '1', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1698634189967577089, '付款方法', 'PAYMENT_METHOD', 0, NULL, 'PAYMENT_METHOD', 1, '付款方法', b'1', 0, b'0', '1', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1698634230920761345, '付款类型', 'PAYMENT_TYPE', 0, NULL, 'PAYMENT_TYPE', 1, '付款类型', b'1', 0, b'0', '1', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1699341989447098369, 'TMS车牌颜色', 'TMS_PLATE_COLOR', 0, NULL, 'TMS_PLATE_COLOR', 1, 'TMS车牌颜色', b'1', 0, b'0', '1', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1699343995867914241, 'TMS能源类型', 'TMS_ENERGY_TYPE', 0, NULL, 'TMS_ENERGY_TYPE', 1, 'TMS能源类型', b'1', 0, b'0', '1', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982081, 'TMS费用项目', 'TMS_EXPENSE_ITEM', 0, NULL, 'TMS_EXPENSE_ITEM', 1, 'TMS费用项目', b'1', 0, b'0', '1', '平台管理员', '2025-12-18 09:46:32', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982082, '国家', 'COUNTRY', 4, 'AREA_LEVEL', 'AREA_LEVEL_COUNTRY', 0, '国家', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982083, '省份', 'PROVINCE', 4, 'AREA_LEVEL', 'AREA_LEVEL_PROVINCE', 0, '省份', b'1', 2, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982084, '地市', 'CITY', 4, 'AREA_LEVEL', 'AREA_LEVEL_CITY', 0, '地市', b'1', 3, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982085, '区县', 'COUNTY', 4, 'AREA_LEVEL', 'AREA_LEVEL_COUNTY', 0, '区县', b'1', 4, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982086, '单位', '01', 5, 'ORG_TYPE', 'ORG_TYPE_01', 0, '单位', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982087, '部门', '02', 5, 'ORG_TYPE', 'ORG_TYPE_02', 0, '部门', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982088, '专科', 'ZHUANKE', 3, 'EDUCATION', 'EDUCATION_ZHUANKE', 0, '专科', b'1', 4, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982089, '本科', 'COLLEGE', 3, 'EDUCATION', 'EDUCATION_COLLEGE', 0, '本科', b'1', 5, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982090, '硕士', 'SUOSHI', 3, 'EDUCATION', 'EDUCATION_SUOSHI', 0, '硕士', b'1', 6, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982091, '博士', 'BOSHI', 3, 'EDUCATION', 'EDUCATION_BOSHI', 0, '博士', b'1', 7, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982092, '博士后', 'BOSHIHOU', 3, 'EDUCATION', 'EDUCATION_BOSHIHOU', 0, '博士后', b'1', 8, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982093, '汉族', 'mz_hanz', 1, 'NATION', 'NATION_mz_hanz', 0, '汉族', b'1', 0, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982094, '壮族', 'mz_zz', 1, 'NATION', 'NATION_mz_zz', 0, '壮族', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982095, '满族', 'mz_mz', 1, 'NATION', 'NATION_mz_mz', 0, '满族', b'1', 2, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982096, '回族', 'mz_hz', 1, 'NATION', 'NATION_mz_hz', 0, '回族', b'1', 3, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982097, '苗族', 'mz_miaoz', 1, 'NATION', 'NATION_mz_miaoz', 0, '苗族', b'1', 4, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982098, '维吾尔族', 'mz_wwez', 1, 'NATION', 'NATION_mz_wwez', 0, '维吾尔族', b'1', 5, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982099, '土家族', 'mz_tjz', 1, 'NATION', 'NATION_mz_tjz', 0, '土家族', b'1', 6, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982100, '彝族', 'mz_yz', 1, 'NATION', 'NATION_mz_yz', 0, '彝族', b'1', 7, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982101, '蒙古族', 'mz_mgz', 1, 'NATION', 'NATION_mz_mgz', 0, '蒙古族', b'1', 8, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982102, '藏族', 'mz_zhangz', 1, 'NATION', 'NATION_mz_zhangz', 0, '藏族', b'1', 9, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982103, '布依族', 'mz_byz', 1, 'NATION', 'NATION_mz_byz', 0, '布依族', b'1', 10, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982104, '侗族', 'mz_dz', 1, 'NATION', 'NATION_mz_dz', 0, '侗族', b'1', 11, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982105, '瑶族', 'mz_yaoz', 1, 'NATION', 'NATION_mz_yaoz', 0, '瑶族', b'1', 12, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982106, '朝鲜族', 'mz_cxz', 1, 'NATION', 'NATION_mz_cxz', 0, '朝鲜族', b'1', 13, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982107, '白族', 'mz_bz', 1, 'NATION', 'NATION_mz_bz', 0, '白族', b'1', 14, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982108, '哈尼族', 'mz_hnz', 1, 'NATION', 'NATION_mz_hnz', 0, '哈尼族', b'1', 15, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982109, '哈萨克族', 'mz_hskz', 1, 'NATION', 'NATION_mz_hskz', 0, '哈萨克族', b'1', 16, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982110, '黎族', 'mz_lz', 1, 'NATION', 'NATION_mz_lz', 0, '黎族', b'1', 17, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982111, '傣族', 'mz_daiz', 1, 'NATION', 'NATION_mz_daiz', 0, '傣族', b'1', 18, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982112, '畲族', 'mz_sz', 1, 'NATION', 'NATION_mz_sz', 0, '畲族', b'1', 19, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982113, '傈僳族', 'mz_llz', 1, 'NATION', 'NATION_mz_llz', 0, '傈僳族', b'1', 20, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982114, '仡佬族', 'mz_glz', 1, 'NATION', 'NATION_mz_glz', 0, '仡佬族', b'1', 21, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982115, '东乡族', 'mz_dxz', 1, 'NATION', 'NATION_mz_dxz', 0, '东乡族', b'1', 22, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982116, '高山族', 'mz_gsz', 1, 'NATION', 'NATION_mz_gsz', 0, '高山族', b'1', 23, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982117, '拉祜族', 'mz_lhz', 1, 'NATION', 'NATION_mz_lhz', 0, '拉祜族', b'1', 24, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982118, '水族', 'mz_shuiz', 1, 'NATION', 'NATION_mz_shuiz', 0, '水族', b'1', 25, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982119, '佤族', 'mz_wz', 1, 'NATION', 'NATION_mz_wz', 0, '佤族', b'1', 26, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982120, '纳西族', 'mz_nxz', 1, 'NATION', 'NATION_mz_nxz', 0, '纳西族', b'1', 27, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982121, '羌族', 'mz_qz', 1, 'NATION', 'NATION_mz_qz', 0, '羌族', b'1', 28, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982122, '土族', 'mz_tz', 1, 'NATION', 'NATION_mz_tz', 0, '土族', b'1', 29, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982123, '仫佬族', 'mz_zlz', 1, 'NATION', 'NATION_mz_zlz', 0, '仫佬族', b'1', 30, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982124, '锡伯族', 'mz_xbz', 1, 'NATION', 'NATION_mz_xbz', 0, '锡伯族', b'1', 31, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982125, '柯尔克孜族', 'mz_kehzz', 1, 'NATION', 'NATION_mz_kehzz', 0, '柯尔克孜族', b'1', 32, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982126, '达斡尔族', 'mz_dwz', 1, 'NATION', 'NATION_mz_dwz', 0, '达斡尔族', b'1', 33, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982127, '景颇族', 'mz_jpz', 1, 'NATION', 'NATION_mz_jpz', 0, '景颇族', b'1', 34, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982128, '毛南族', 'mz_mlz', 1, 'NATION', 'NATION_mz_mlz', 0, '毛南族', b'1', 35, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982129, '撒拉族', 'mz_slz', 1, 'NATION', 'NATION_mz_slz', 0, '撒拉族', b'1', 36, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982130, '塔吉克族', 'mz_tjkz', 1, 'NATION', 'NATION_mz_tjkz', 0, '塔吉克族', b'1', 37, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982131, '阿昌族', 'mz_acz', 1, 'NATION', 'NATION_mz_acz', 0, '阿昌族', b'1', 38, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982132, '普米族', 'mz_pmz', 1, 'NATION', 'NATION_mz_pmz', 0, '普米族', b'1', 39, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982133, '鄂温克族', 'mz_ewkz', 1, 'NATION', 'NATION_mz_ewkz', 0, '鄂温克族', b'1', 40, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982134, '怒族', 'mz_nz', 1, 'NATION', 'NATION_mz_nz', 0, '怒族', b'1', 41, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982135, '京族', 'mz_jz', 1, 'NATION', 'NATION_mz_jz', 0, '京族', b'1', 42, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982136, '基诺族', 'mz_jnz', 1, 'NATION', 'NATION_mz_jnz', 0, '基诺族', b'1', 43, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982137, '德昂族', 'mz_daz', 1, 'NATION', 'NATION_mz_daz', 0, '德昂族', b'1', 44, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982138, '保安族', 'mz_baz', 1, 'NATION', 'NATION_mz_baz', 0, '保安族', b'1', 45, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982139, '俄罗斯族', 'mz_elsz', 1, 'NATION', 'NATION_mz_elsz', 0, '俄罗斯族', b'1', 46, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982140, '裕固族', 'mz_ygz', 1, 'NATION', 'NATION_mz_ygz', 0, '裕固族', b'1', 47, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982141, '乌兹别克族', 'mz_wzbkz', 1, 'NATION', 'NATION_mz_wzbkz', 0, '乌兹别克族', b'1', 48, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982142, '门巴族', 'mz_mbz', 1, 'NATION', 'NATION_mz_mbz', 0, '门巴族', b'1', 49, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982143, '鄂伦春族', 'mz_elcz', 1, 'NATION', 'NATION_mz_elcz', 0, '鄂伦春族', b'1', 50, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982144, '独龙族', 'mz_dlz', 1, 'NATION', 'NATION_mz_dlz', 0, '独龙族', b'1', 51, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982145, '塔塔尔族', 'mz_tkez', 1, 'NATION', 'NATION_mz_tkez', 0, '塔塔尔族', b'1', 52, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982146, '赫哲族', 'mz_hzz', 1, 'NATION', 'NATION_mz_hzz', 0, '赫哲族', b'1', 53, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982147, '珞巴族', 'mz_lbz', 1, 'NATION', 'NATION_mz_lbz', 0, '珞巴族', b'1', 54, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982148, '布朗族', 'mz_blz', 1, 'NATION', 'NATION_mz_blz', 0, '布朗族', b'1', 55, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982149, '在职', 'WORKING', 2, 'POSITION_STATUS', 'POSITION_STATUS_WORKING', 0, '在职', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982150, '离职', 'QUIT', 2, 'POSITION_STATUS', 'POSITION_STATUS_QUIT', 0, '离职', b'1', 2, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982151, '乡镇', 'TOWNS', 4, 'AREA_LEVEL', 'AREA_LEVEL_TOWNS', 0, '乡镇', b'1', 5, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982152, '小学', 'XIAOXUE', 3, 'EDUCATION', 'EDUCATION_XIAOXUE', 0, '小学', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982153, '中学', 'ZHONGXUE', 3, 'EDUCATION', 'EDUCATION_ZHONGXUE', 0, '中学', b'1', 2, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982154, '高中', 'GAOZHONG', 3, 'EDUCATION', 'EDUCATION_GAOZHONG', 0, '高中', b'1', 3, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982155, '其他', 'QITA', 3, 'EDUCATION', 'EDUCATION_QITA', 0, '其他', b'1', 20, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982156, '其他', 'mz_qt', 1, 'NATION', 'NATION_mz_qt', 0, '其他', b'1', 100, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982157, '请假', 'LEAVE', 2, 'POSITION_STATUS', 'POSITION_STATUS_LEAVE', 0, '请假', b'1', 3, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982158, '男', '1', 6, 'SEX', 'SEX_1', 0, '男', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982159, '女', '2', 6, 'SEX', 'SEX_2', 0, '女', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982160, '通知', '0', 7, 'NOTICE', 'NOTICE_0', 0, '通知', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982161, '消息', '1', 7, 'NOTICE', 'NOTICE_1', 0, '消息', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982162, '待办', '2', 7, 'NOTICE', 'NOTICE_2', 0, '待办', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982163, '基层', '0', 8, 'POSITION_TYPE', 'POSITION_TYPE_0', 0, '基层', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982164, '成功', 'success', 9, 'COLOR', 'COLOR_success', 0, '成功', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982165, '警告', 'warning', 9, 'COLOR', 'COLOR_warning', 0, '警告', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982166, '错误', 'error', 9, 'COLOR', 'COLOR_error', 0, '错误', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982167, '中层', '1', 8, 'POSITION_TYPE', 'POSITION_TYPE_1', 0, '中层', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982168, '高层', '2', 8, 'POSITION_TYPE', 'POSITION_TYPE_2', 0, '高层', b'1', 1, b'0', '0', '系统管理员', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982169, '医疗', '1', 10, 'INDUSTRY', 'INDUSTRY_1', 0, '医疗', b'1', 1, b'0', '1', '长风一梦', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982170, '教育', '2', 10, 'INDUSTRY', 'INDUSTRY_2', 0, '教育', b'1', 1, b'0', '1', '长风一梦', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982171, '金融', '3', 10, 'INDUSTRY', 'INDUSTRY_3', 0, '金融', b'1', 1, b'0', '1', '长风一梦', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982172, '互联网', '4', 10, 'INDUSTRY', 'INDUSTRY_4', 0, '互联网', b'1', 1, b'0', '1', '长风一梦', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982173, '电商', '5', 10, 'INDUSTRY', 'INDUSTRY_5', 0, '电商', b'1', 1, b'1', '1', '长风一梦', '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982174, '启用', '1', 11, 'STATUS', 'STATUS_1', 0, '启用', b'1', 1, b'0', '0', NULL, '2025-12-18 09:49:38', NULL, NULL, NULL);
INSERT INTO `sys_dict` (`id`, `name`, `code`, `parent_id`, `parent_code`, `full_code_path`, `type`, `description`, `status`, `sequence`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070256599982175, '停用', '0', 11, 'STATUS', 'STATUS_0', 0, '停用', b'1', 1, b'0', '0', NULL, '2025-12-18 09:49:38', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
                           `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                           `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '简称',
                           `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系方式',
                           `parent_id` bigint DEFAULT '0' COMMENT '父ID',
                           `tree_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '树节点路径',
                           `sequence` int DEFAULT '1' COMMENT '排序',
                           `status` bit(1) DEFAULT b'1' COMMENT '状态',
                           `deleted` bit(1) DEFAULT b'0',
                           `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
                           `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                           `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                           `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                           `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1999051236041019395 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组织';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (100, '鏖战八方', 1, '鏖战', '0746-8485560转1', 0, '0', 1, b'1', b'0', '初始化数据', 1, '1', '2019-07-10 17:02:18', 1, '1', '2023-11-22 08:21:42');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10001, '鏖战八方上海分部', 1, '鏖战上海', '0746-8485560转2', 100, '0,100', 0, b'1', b'0', '初始化数据', 1, '1', '2019-08-06 09:10:53', NULL, NULL, '2024-12-07 13:39:58');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10002, '鏖战八方北京分部', 1, '鏖战北京', '0746-8485560转3', 100, '0,100', 1, b'1', b'0', '初始化数据', 1, '1', '2019-11-07 16:13:09', 1, '1', '2023-11-22 08:23:13');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10003, '管理层', 1, '管理层', '0746-8485560转4', 100, '0,100', 3, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:34:39', 3, '3', '2023-11-22 08:23:14');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10004, '总经办', 1, '', '0746-8485560', 100, '0,100', 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:35:37', 1, '长风一梦', '2023-11-22 08:23:15');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10005, '财务部', 1, '', NULL, 100, '0,100', 4, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:37:17', 3, '3', '2023-11-22 08:23:17');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10006, '市场部', 1, '', NULL, 100, '0,100', 5, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:37:37', 3, '3', '2023-11-22 08:23:21');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10101, '综合部', 1, '综合部', '0746-8485560转7', 10001, '0,100,10001', 0, b'1', b'0', '前台&HR11', 3, '3', '2019-11-12 11:34:27', 1, '长风一梦8888', '2023-11-22 08:23:23');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10102, '研发部', 1, '研发部', '0746-8485560转5', 10001, '0,100,10001', 5, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:21', 2, '不告诉你', '2023-11-22 08:23:25');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10103, '产品部', 1, '产品部', '0746-8485560转6', 10001, '0,100,10001', 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:31', NULL, NULL, '2024-12-07 13:40:22');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10201, '综合部', 1, '综合部', NULL, 10002, '0,100,10002', 0, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:39', 3, '3', '2023-11-22 08:23:30');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10202, '测试部', 1, '测试部', NULL, 10002, '0,100,10002', 1, b'1', b'0', '初始化数据', 2, '不告诉你', '2020-10-29 06:39:09', 2, '不告诉你', '2023-11-22 08:23:32');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10203, '研发部', 1, '', NULL, 10002, '0,100,10002', 0, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:50', 3, '3', '2023-11-22 08:23:34');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10204, '销售部', 1, '', NULL, 10002, '0,100,10002', 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:39:00', 3, '3', '2023-11-22 08:23:35');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999051236041019394, '深圳腾讯科技股份有限公司', 3, '', NULL, 0, NULL, 0, b'1', b'0', '不可删除不可修改', 1, '平台管理员', '2025-12-11 17:38:50', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
                                `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
                                `sequence` tinyint DEFAULT NULL COMMENT '排序',
                                `org_id` bigint DEFAULT '0' COMMENT '组织ID',
                                `status` bit(1) DEFAULT b'1' COMMENT '状态',
                                `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
                                `deleted` bit(1) DEFAULT b'0',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                `create_by` bigint DEFAULT NULL,
                                `last_modify_by` bigint DEFAULT NULL,
                                `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1865020143000756227 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='岗位';

-- ----------------------------
-- Records of sys_position
-- ----------------------------
BEGIN;
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (100, 1, '总经理', 'CEO', 0, 100, b'1', '总部-1把手2', b'0', '2019-07-10 17:03:03', NULL, 1, NULL, NULL, '2024-12-06 17:03:57');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (101, 1, '副总经理', NULL, 1, 10001, b'1', '总部-2把手', b'0', '2019-07-22 17:07:55', NULL, 1, 1, '长风一梦', '2021-07-28 02:58:53');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (103, 1, '研发经理', NULL, 1, 10002, b'1', '子公司-研发部老大', b'0', '2019-11-07 16:08:49', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:06');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (104, 1, '副总经理', NULL, 1, 10001, b'1', '子公司-老大', b'0', '2019-11-16 09:51:45', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:29');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (106, 1, '产品经理', NULL, 1, 10002, b'1', '子公司-产品部老大', b'0', '2019-11-16 09:53:27', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (107, 1, '人事经理', NULL, 1, 10002, b'1', '子公司-综合老大', b'0', '2019-11-16 09:54:43', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (108, 1, 'Java研发', 'CTI', 1, 10203, b'1', '普通员工', b'0', '2019-11-16 09:55:04', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:38');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1011, 1, 'UI工程师', 'CTI', 1, 10102, b'1', '普通员工', b'0', '2019-11-16 09:55:40', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:52');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1012, 1, '运维工程师', 'CTI', 1, 10203, b'1', '普通员工', b'0', '2019-11-16 09:55:53', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:45');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1013, 1, '行政经理', NULL, 1, 10003, b'1', '普通员工', b'0', '2019-11-16 09:56:04', NULL, 3, 1, '长风一梦8888', '2021-11-27 06:36:22');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1014, 1, '人事经理', NULL, 1, 10004, b'1', '北京分公司-综合部老大', b'0', '2019-11-16 09:56:38', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:36');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1015, 1, '研发经理', NULL, 1, 10102, b'1', '北京分公司-研发部老大', b'0', '2019-11-16 09:57:07', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:28');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1016, 1, '销售经理', NULL, 1, 10004, b'1', '北京销售部老大', b'0', '2019-11-16 09:57:40', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:19');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10121, 1, '销售总监', NULL, 1, 10005, b'1', '总部2把手', b'0', '2019-11-16 09:59:10', NULL, 3, 1, '长风一梦', '2021-07-29 02:36:31');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10122, 1, '销售员工', NULL, 1, 10006, b'1', '普通员工', b'0', '2019-11-16 09:58:41', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:11');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10123, 1, '财务总监', NULL, 1, 10003, b'1', '总部2把手', b'0', '2019-11-16 09:59:39', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:06');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10124, 1, '市场经理', NULL, 1, 10003, b'1', '总部市场部老大', b'0', '2019-11-16 10:00:03', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:01');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10128, 1, '前端工程师', 'CTI', 1, 10102, b'1', '普通员工', b'0', '2019-11-16 10:00:43', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:20:53');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1865020143000756226, NULL, '1', '1', 0, 10101, b'1', '213', b'1', '2024-12-06 21:07:07', NULL, NULL, NULL, NULL, '2024-12-06 21:07:11');
COMMIT;

-- ----------------------------
-- Table structure for sys_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_registered_client`;
CREATE TABLE `sys_registered_client` (
                                         `id` bigint NOT NULL AUTO_INCREMENT,
                                         `client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                         `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         `client_secret` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                         `client_secret_expires_at` timestamp NULL DEFAULT NULL,
                                         `client_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                         `client_authentication_methods` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                         `grant_types` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                         `redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                         `post_logout_redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                         `scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                         `client_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                         `token_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                         `status` bit(1) DEFAULT b'1' COMMENT '0=禁用;1=启用',
                                         `deleted` bit(1) DEFAULT b'0',
                                         `create_by` bigint DEFAULT NULL COMMENT '创建人id',
                                         `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                                         `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                         `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1865050872845828099 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_registered_client
-- ----------------------------
BEGIN;
INSERT INTO `sys_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`, `client_secret_expires_at`, `client_name`, `client_authentication_methods`, `grant_types`, `redirect_uris`, `post_logout_redirect_uris`, `scopes`, `client_settings`, `token_settings`, `status`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 'pc-web', '2024-09-11 06:39:32', 'pc-web', NULL, 'PC-WEB 管理系统', 'client_secret_basic', 'password,sms', 'http://127.0.0.1:5001/oauth2/code,https://www.baidu.com', '', '[[[wp]]]', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"accessTokenTimeToLive\":120}', b'1', b'0', NULL, NULL, '2023-09-15 16:43:14', NULL, NULL, '2024-12-07 12:24:30');
INSERT INTO `sys_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`, `client_secret_expires_at`, `client_name`, `client_authentication_methods`, `grant_types`, `redirect_uris`, `post_logout_redirect_uris`, `scopes`, `client_settings`, `token_settings`, `status`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, 'uniapp', '2023-08-18 09:55:44', 'uniapp', NULL, 'APP 客户端', NULL, 'password,sms', 'http://127.0.0.1:5001/oauth2/code,https://www.baidu.com', NULL, '[[[[wp]]]]', NULL, '{\"accessTokenTimeToLive\":120}', b'1', b'0', NULL, NULL, '2024-12-06 23:09:14', NULL, NULL, '2024-12-07 12:24:26');
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '归属的应用',
                                `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
                                `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限',
                                `parent_id` bigint DEFAULT '0' COMMENT '父级菜单ID',
                                `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '路径',
                                `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件',
                                `sequence` int DEFAULT '1' COMMENT '排序',
                                `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '菜单图标',
                                `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型 directory=目录;menu=菜单;iframe=内嵌;link=外链;button=按钮',
                                `status` bit(1) DEFAULT b'1' COMMENT '1=启用;0=禁用',
                                `keep_alive` bit(1) DEFAULT NULL COMMENT '开启后页面会缓存，不会重新加载，仅在标签页启用时有效',
                                `readonly` bit(1) DEFAULT b'0' COMMENT '内置菜单（0=否;1=是）',
                                `shared` bit(1) DEFAULT b'0' COMMENT '公共资源（True=无需分配所有人就可以访问）',
                                `visible` bit(1) DEFAULT b'1' COMMENT '0=隐藏;1=显示',
                                `meta` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'Vben 路由元信息(JSON)',
                                `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
                                `deleted` bit(1) DEFAULT b'0',
                                `create_by` bigint DEFAULT NULL COMMENT '创建人id',
                                `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                                `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                PRIMARY KEY (`id`) USING BTREE,
                                KEY `INX_SHARED` (`shared`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2002898793212821507 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 'pc-web', '平台管理', NULL, 0, '/plat', 'BasicLayout', 0, 'clarity-thin-client-line', 'directory', b'1', b'1', b'0', b'0', b'1', NULL, '123123', b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2025-12-10 19:26:44');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3, 'pc-web', '系统管理', NULL, 0, '/sys', 'BasicLayout', 3, 'ant-design:dashboard-filled', 'directory', b'1', NULL, b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (11, 'pc-web', '审批管理', NULL, 0, '/workflow', 'BasicLayout', 11, 'ant-design:appstore-twotone', 'directory', b'1', NULL, b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2022-09-09 14:55:58', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15, 'pc-web', '运输管理', NULL, 0, '/tms/management', 'BasicLayout', 10, 'ant-design:dropbox-outlined', 'directory', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 04:57:58', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (16, 'pc-web', '仓储管理', NULL, 0, '/wms', 'BasicLayout', 16, 'ant-design:home-twotone', 'directory', b'1', NULL, b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 04:57:58', 1, '长风一梦8888', '2024-05-28 16:59:43');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (50, 'pc-web', '开发平台', NULL, 0, '/dev', 'BasicLayout', 50, 'ant-design:appstore-add-outlined', 'directory', b'1', NULL, b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (60, 'pc-web', '人工智能', NULL, 0, '/ai', 'BasicLayout', 60, 'carbon:ai-recommend', 'directory', b'1', NULL, b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (104, 'pc-web', '租户管理', NULL, 1, '/plat/tenant', NULL, 104, 'ant-design:code-sandbox-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (105, 'pc-web', '套餐管理', NULL, 1, '/plat/plan', NULL, 105, 'icon-park-outline:ad-product', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2023-09-23 06:54:15', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (107, 'pc-web', '数据配置', NULL, 1, '/plat/setting', NULL, 107, 'file-icons:config-coffeescript', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (151, 'pc-web', '运输资源', NULL, 15, '/tms/resource', NULL, 0, 'ant-design:control-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:03:59', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (152, 'pc-web', '车辆维保', NULL, 15, '/tms/maintenance', NULL, 1, 'ant-design:mail-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-07 10:08:49', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (153, 'pc-web', '订单管理', NULL, 15, '/tms/order', NULL, 1, 'material-symbols:order-approve-sharp', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-10-07 14:33:32', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (160, 'pc-web', '基础管理', NULL, 16, '/wms/basic', NULL, 160, 'ant-design:mac-command-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:03:59', 1, '长风一梦8888', '2024-05-28 16:59:53');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (161, 'pc-web', '数据管理', NULL, 16, '/wms/metadata', NULL, 161, 'ant-design:calendar-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:03:10', 1, '长风一梦8888', '2024-06-20 23:15:15');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (162, 'pc-web', '入库管理', NULL, 16, '/wms/inbound', NULL, 162, 'uiw:login', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:03:10', 1, '长风一梦8888', '2024-07-25 15:47:09');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (163, 'pc-web', '库存管理', NULL, 16, '/wms/stock', NULL, 200, 'ant-design:calendar-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:03:10', 1, '长风一梦8888', '2024-07-25 15:47:14');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (166, 'pc-web', '出库管理', NULL, 16, '/wms/outbound', NULL, 166, 'uiw:logout', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:20:11', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (301, 'pc-web', '组织架构', NULL, 3, '/sys/structure', NULL, 301, 'ant-design:user-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (302, 'pc-web', '权限管理', NULL, 3, '/sys/auth', NULL, 302, 'ant-design:security-scan-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (303, 'pc-web', '设置中心', NULL, 3, '/sys/setting', NULL, 303, 'file-icons:config-coffeescript', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (306, 'pc-web', '消息管理', NULL, 3, '/sys/message', NULL, 306, 'mi:message', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (310, 'pc-web', '运维监控', NULL, 3, '/sys/monitor', NULL, 310, 'lucide:monitor-cog', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (320, 'pc-web', '存储管理', NULL, 3, '/sys/oss', NULL, 320, 'carbon:ibm-cloud-vpc-file-storage', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (350, 'pc-web', '安全中心', NULL, 1, '/plat/security', NULL, 350, 'carbon-application', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1101, 'pc-web', '流程设置', NULL, 11, '/workflow/setting', NULL, 1109, 'ant-design:delivered-procedure-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2022-09-09 14:55:58', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1102, 'pc-web', '审批办公', NULL, 11, '/workflow/approval', NULL, 10, 'ant-design:appstore-add-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2022-10-20 07:42:59', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1104, 'pc-web', '我的任务', NULL, 11, '/workflow/task', NULL, 1104, 'ant-design:appstore-add-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2022-10-20 07:42:59', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1106, 'pc-web', '流程监控', NULL, 11, '/workflow/monitor', NULL, 1106, 'ant-design:appstore-add-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2022-10-20 07:42:59', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (5003, 'pc-web', '在线开发', NULL, 50, '/dev/online', '', 5003, 'material-symbols:home-work-outline-rounded', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-12-12 13:11:50', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (5005, 'pc-web', '网关管理', NULL, 50, '/dev/gateway', NULL, 5005, 'ant-design:gateway-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (5050, 'pc-web', '定时任务', NULL, 50, '/dev/job', 'http://localhost:8871/snail-job', 5050, 'arcticons:jobstreet', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (5069, 'pc-web', '代码生成', NULL, 50, '/develop/gen', '', 0, 'ant-design:appstore-filled', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-12 13:11:50', 1, '平台管理员', '2025-02-22 22:26:20');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (6004, 'pc-web', '智能体', NULL, 60, '/ai/agent', NULL, 6004, 'mingcute:robot-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-12-09 08:07:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (6005, 'pc-web', '知识库', NULL, 60, '/ai/rag', NULL, 6005, 'mingcute:book-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-12-09 08:07:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (6006, 'pc-web', '模型中心', NULL, 60, '/ai/model', NULL, 6006, 'mingcute:box-3-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-12-09 08:07:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10401, 'pc-web', '租户中心', 'tenant:list', 104, '/plat/tenant/list', '/wemirr/platform/tenant/index', 10401, 'ant-design:code-sandbox-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10402, 'pc-web', '多数据源', NULL, 104, '/plat/db/list', '/wemirr/platform/db/index', 10402, 'ant-design:database-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', NULL, NULL, '2024-12-06 17:07:02');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10501, 'pc-web', '套餐定义', 'plat:plan:definition', 105, '/platform/plan/definition', '/wemirr/platform/plan/definition/index', 1, 'fluent-mdl2:product-list', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2023-09-23 06:59:39', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10502, 'pc-web', '套餐定价', 'plat: plan:pricing', 105, '/platform/plan/pricing', '/wemirr/platform/plan/pricing/index', 2, 'arcticons:pricespy', 'menu', b'0', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2023-09-23 06:59:39', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10503, 'pc-web', '套餐订阅', 'plat: plan:subscribe', 105, '/platform/plan/subscribe', '/wemirr/platform/plan/subscribe/index', 3, 'material-symbols:package-2-sharp', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2023-09-23 06:59:39', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10706, 'pc-web', '数据字典', 'dict:list', 107, '/plat/setting/dict', '/wemirr/platform/basic/dict/index', 10706, 'bx-bxs-data', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10707, 'pc-web', '地区信息', NULL, 107, '/plat/setting/area', '/wemirr/platform/basic/area/index', 10707, 'ant-design:area-chart-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15101, 'pc-web', '司机管理', NULL, 151, '/tms/resource/driver', '/wemirr/tms/resource/driver/index', 0, 'ant-design:dribbble-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15102, 'pc-web', '车辆管理', NULL, 151, '/tms/resource/truck', '/wemirr/tms/resource/truck/index', 0, 'ant-design:car-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15103, 'pc-web', '车队管理', NULL, 151, '/tms/resource/fleet', '/wemirr/tms/resource/fleet/index', 0, 'ant-design:safety-certificate-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15201, 'pc-web', '配件采购', NULL, 152, '/tms/maintenance/purchase', '/wemirr/tms/maintenance/purchase/index', 0, 'ant-design:delivered-procedure-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15202, 'pc-web', '车辆维修', NULL, 152, '/tms/maintenance/repair', '/wemirr/tms/maintenance/repair/index', 0, 'ant-design:dribbble-square-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15203, 'pc-web', '车辆保养', NULL, 152, '/tms/maintenance/maintenance', '/wemirr/tms/maintenance/maintenance/index', 0, 'ant-design:mail-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15204, 'pc-web', '车辆违章', NULL, 152, '/tms/maintenance/peccancy', '/wemirr/tms/maintenance/peccancy/index', 0, 'ant-design:snippets-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15205, 'pc-web', '车辆规费', NULL, 152, '/tms/maintenance/expense', '/wemirr/tms/maintenance/expense/index', 0, 'ant-design:experiment-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15206, 'pc-web', '车辆事故', NULL, 152, '/tms/maintenance/accident', '/wemirr/tms/maintenance/accident/index', 0, 'ant-design:warning-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15207, 'pc-web', '配件库存', NULL, 152, '/tms/maintenance/inventory', '/wemirr/tms/maintenance/inventory/index', 0, 'ant-design:compass-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (15301, 'pc-web', '订单中心', NULL, 153, '/tms/order/list', '/wemirr/tms/order/index', 0, 'icon-park-outline:transaction-order', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2023-09-05 05:05:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (16101, 'pc-web', '计量单位', NULL, 161, '/wms/metadata/unit', '/wemirr/wms/metadata/unit/index', 16101, 'ant-design:border-inner-outlined', 'menu', b'0', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-24 14:40:46', 1, '长风一梦8888', '2024-06-25 16:36:06');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (16102, 'pc-web', '单位转换', NULL, 161, '/wms/metadata/unit-conv', '/wemirr/wms/metadata/unit/conv/index', 16102, 'ant-design:block-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-24 14:41:17', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (16103, 'pc-web', '供应商管理', NULL, 161, '/wms/metadata/supplier', '/wemirr/wms/metadata/supplier/index', 16103, 'ant-design:aliwangwang-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-07-02 10:51:27', 1, '长风一梦8888', '2024-07-25 15:46:54');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (16108, 'pc-web', '承运商管理', NULL, 161, '/wms/metadata/carrier', '/wemirr/wms/metadata/carrier/index', 16108, 'ant-design:car-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-07-25 15:46:37', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (16201, 'pc-web', '收货计划', NULL, 162, '/wms/inbound/receivingPlan', '/wemirr/wms/inbound/receivingPlan/index', 16201, 'ant-design:calendar-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-21 09:57:57', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (16501, 'pc-web', '调账单', NULL, 165, '/wms/management/transfer', '/wemirr/wms/management/transfer/index', 165001, 'ant-design:file-done-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:18:09', 1, '长风一梦8888', '2024-06-20 23:19:07');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (16502, 'pc-web', '盘点订单', NULL, 165, '/wms/management/inventory', '/wemirr/wms/management/inventory/index', 165002, 'ant-design:field-time-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:18:58', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (16601, 'pc-web', '发货订单', NULL, 166, '/wms/outbound/outboundPlan', '/wemirr/wms/outbound/outboundPlan/index', 16601, 'material-symbols-light:order-approve-outline-sharp', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-21 09:59:55', 1, '长风一梦8888', '2024-08-09 11:07:55');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30101, 'pc-web', '用户管理', 'sys:user:page', 301, '/sys/user', '/wemirr/system/user/index', 30101, 'heroicons-outline:user-group', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30102, 'pc-web', '机构管理', 'sys:org:page', 301, '/sys/org', '/wemirr/system/org/index', 30102, 'codicon-organization', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30103, 'pc-web', '岗位管理', 'sys:position:page', 301, '/sys/position', '/wemirr/system/position/index', 30103, 'ant-design:usergroup-add-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30202, 'pc-web', '角色管理', 'sys:role:page', 302, '/system/role', '/wemirr/system/auth/role/index', 30202, 'eos-icons:role-binding-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30301, 'pc-web', '租户字典', 'tenant:dict:list', 303, '/sys/basic-dict', '/wemirr/system/basic/dict/index', 30301, 'bx-bxs-data', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30303, 'pc-web', '语言管理', 'i18n:page', 303, '/sys/basic-i18n', '/wemirr/system/basic/i18n/index', 30303, 'cil:language', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30604, 'pc-web', '通道设置', 'message:channel:setting', 306, '/sys/message/channel-setting', '/wemirr/system/message/setting/index', 30604, 'fluent:channel-48-regular', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30605, 'pc-web', '消息模板', 'message:template:page', 306, '/sys/message/template', '/wemirr/system/message/template', 30605, 'fluent:mail-template-24-regular', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30606, 'pc-web', '消息列表', 'message:list', 306, '/sys/message/list', '/wemirr/system/message/message-list', 30606, 'mdi:envelope-outline', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30607, 'pc-web', '消息推送', 'message:publish', 306, '/sys/message/publish', '/wemirr/system/message/publish', 30607, 'arcticons:efa-publish', 'menu', b'1', b'1', b'0', b'0', b'0', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (30608, 'pc-web', '我的消息', 'message:subscribe-list', 306, '/sys/message/subscribe-list', '/wemirr/system/message/subscribe-list', 30608, 'ant-design:comment-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (31003, 'pc-web', '在线用户', 'monitor:online:token-list', 310, '/sys/monitor/online', '/wemirr/system/monitor/online/index', 31003, 'mdi:account-online-outline', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"badge\":\"Alpha\",\"badgeVariants\":\"destructive\"}', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (31005, 'pc-web', '登录日志', 'monitor:log:login', 310, '/sys/monitor/login-log', '/wemirr/system/log/login-log', 31005, 'mdi:account-security-outline', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (31006, 'pc-web', '操作日志', 'monitor:log:opt', 310, '/sys/monitor/opt-log', '/wemirr/system/log/opt-log', 31006, 'carbon-operation', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (32002, 'pc-web', '文件管理', '', 320, '/sys/oss/file', '/wemirr/system/oss/file/index', 32002, 'hugeicons:folder-attachment', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-15 22:40:52');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (32003, 'pc-web', '配置管理', '', 320, '/sys/oss/setting', '/wemirr/system/oss/config/index', 32003, 'ep:setting', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-15 22:40:43');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (35003, 'pc-web', '应用管理', 'plat:app:page', 350, '/plat/app', '/wemirr/platform/security/app/index', 35003, 'streamline:application-add-solid', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (35005, 'pc-web', '菜单管理', 'sys:menu:page', 350, '/plat/menu', '/wemirr/platform/security/menu/index', 35005, 'ant-design:menu-unfold-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (110101, 'pc-web', '模型管理', NULL, 1101, '/workflow/process/model', '/wemirr/workflow/process/model/index', 0, 'ant-design:align-center-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 05:42:40', 1603373363570712578, '不会说话的人', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (110102, 'pc-web', '流程类别', NULL, 1101, '/workflow/process/category', '/wemirr/workflow/process/category/index', 0, 'ant-design:ant-design-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 06:26:07', 1603373363570712578, '不会说话的人', NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (110107, 'pc-web', '创建模型', 'process:design:add', 1101, '/workflow/process/design', '/wemirr/workflow/process/model/workflow', 110107, 'arcticons:efa-publish', 'menu', b'1', b'1', b'0', b'0', b'0', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (110108, 'pc-web', '表单设计', 'process:design:form', 1101, '/workflow/process/form-design', '/wemirr/workflow/process/model/form-design', 110107, 'arcticons:efa-publish', 'menu', b'1', b'1', b'0', b'0', b'0', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (110201, 'pc-web', '发起审批', NULL, 1102, '/workflow/process/launch', '/wemirr/workflow/process/launch/index', 0, 'gridicons:create', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 07:16:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (110401, 'pc-web', '我发起的', NULL, 1104, '/workflow/task/me-start', '/wemirr/workflow/task/me-start', 110401, 'ant-design:shake-outlined', 'menu', b'1', b'0', b'0', b'0', b'1', NULL, '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 07:16:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (110402, 'pc-web', '我的待办', NULL, 1104, '/workflow/task/todo', '/wemirr/workflow/task/todo', 110402, 'ant-design:shake-outlined', 'menu', b'1', b'0', b'0', b'0', b'1', NULL, '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 07:16:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (110403, 'pc-web', '我的已办', NULL, 1104, '/workflow/task/done', '/wemirr/workflow/task/done', 110403, 'ant-design:shake-outlined', 'menu', b'1', b'0', b'0', b'0', b'1', NULL, '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 07:16:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (110601, 'pc-web', '流程列表', NULL, 1106, '/workflow/process/list', '/wemirr/workflow/process/list/index', 10, 'ant-design:shake-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 07:16:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (160001, 'pc-web', '巷道管理', 'wms:aisle:view', 160, '/wms/basis/aisle', '/wemirr/wms/basic/aisle/index', 160001, 'ant-design:borderless-table-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-04-10 13:21:33', 1, '长风一梦8888', '2024-06-20 22:49:34');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (160002, 'pc-web', '容器管理', 'wms:container:view', 160, '/wms/basis/container', '/wemirr/wms/basic/container/index', 160002, 'ant-design:code-sandbox-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-04-10 13:21:33', 1, '长风一梦8888', '2024-06-20 22:50:47');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (160005, 'pc-web', '月台管理', 'wms:dock:view', 160, '/wms/basis/dock', '/wemirr/wms/basic/dock/index', 160005, 'ant-design:car-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-04-10 13:21:33', 1, '长风一梦8888', '2024-06-20 23:02:30');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (160006, 'pc-web', '储位管理', 'wms:location:view', 160, '/wms/basis/location', '/wemirr/wms/basic/location/index', 160006, 'ant-design:database-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-04-10 13:21:33', 1, '长风一梦8888', '2024-06-20 22:50:18');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (160010, 'pc-web', '库区管理', 'wms:storageArea:view', 160, '/wms/basis/storage-area', '/wemirr/wms/basic/storage-area/index', 160010, 'ant-design:apartment-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-04-10 13:21:33', 1, '长风一梦8888', '2024-06-20 22:49:12');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (160011, 'pc-web', '仓库管理', 'wms:warehouse:view', 160, '/wms/basis/warehouse', '/wemirr/wms/basic/warehouse/index', 160011, 'ant-design:bank-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-04-10 13:21:33', 1, '长风一梦8888', '2024-06-20 22:49:25');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (160012, 'pc-web', '工作台管理', 'wms:workbench:view', 160, '/wms/basis/workbench', '/wemirr/wms/basic/workbench/index', 160012, 'ant-design:desktop-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-04-10 13:21:32', 1, '长风一梦8888', '2024-06-20 22:50:59');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (161001, 'pc-web', '品牌管理', NULL, 161, '/wms/metadata/brand', '/wemirr/wms/metadata/brand/index', 161001, 'ant-design:crown-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:09:24', 1, '长风一梦8888', '2024-08-02 23:25:10');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (161002, 'pc-web', '物料类别', NULL, 161, '/wms/metadata/material/category', '/wemirr/wms/metadata/material/category/index', 161002, 'ant-design:copy-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:10:28', 1, '长风一梦8888', '2024-06-20 23:13:15');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (161003, 'pc-web', '物料管理', NULL, 161, '/wms/metadata/material', '/wemirr/wms/metadata/material/index', 161003, 'ant-design:compass-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:11:18', 1, '长风一梦8888', '2024-06-20 23:13:21');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (162002, 'pc-web', '入库订单', NULL, 162, '/wms/inbound/receipt', '/wemirr/wms/inbound/receipt/index', 162002, 'ant-design:delivered-procedure-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:07:52', 1, '长风一梦8888', '2024-06-21 09:57:03');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (163001, 'pc-web', '库存余额', 'wms:physicalInventory:view', 163, '/wms/stock/detail', '/wemirr/wms/stock/detail/index', 163001, 'ant-design:ant-design-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-04-10 13:21:33', 1, '长风一梦8888', '2024-07-04 11:06:59');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (163002, 'pc-web', '变动记录', NULL, 163, '/wms/stock/change', '/wemirr/wms/stock/change/index', 163002, 'ant-design:exception-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '长风一梦8888', '2024-06-20 23:05:20', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (500301, 'pc-web', '表单模型', NULL, 5003, '/dev/online/list', '/wemirr/develop/online/index', 500301, 'file-icons:3d-model', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"badge\":\"Alpha\",\"badgeVariants\":\"destructive\"}', '', b'0', NULL, NULL, '2024-12-13 08:40:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (500302, 'pc-web', '表单设计', 'dev:online:form-design', 5003, '/dev/online/form-design', '/wemirr/develop/online/form-design', 500302, 'arcticons:efa-publish', 'menu', b'1', b'1', b'0', b'0', b'0', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (500304, 'pc-web', '在线打印', NULL, 5003, '/dev/online/print', '/wemirr/develop/print/index', 500304, 'mi:print', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"badge\":\"Alpha\",\"badgeVariants\":\"destructive\"}', '', b'0', NULL, NULL, '2024-12-13 08:40:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (500308, 'pc-web', '测试用例', NULL, 5003, '/dev/online/demo', NULL, 500308, 'arcticons:democracy', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-12-13 08:40:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (500501, 'pc-web', '限流规则', 'dev:gateway:limit', 5005, '/dev/gateway/limit', '/wemirr/develop/gateway/limit/index', 500501, 'carbon-rule', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (500502, 'pc-web', '限访名单', 'dev:gateway:blacklist', 5005, '/dev/gateway/blacklist', '/wemirr/develop/gateway/blacklist/index', 500502, 'carbon-ai-status-rejected', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (500503, 'pc-web', '网关路由', 'dev:gateway:route', 5005, '/dev/gateway/route', '/wemirr/develop/gateway/route/index', 500503, 'mdi-router-wireless-settings', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (506901, 'pc-web', '生成配置', NULL, 5069, '/develop/gen/table', '/wemirr/develop/gen/table/index', 0, 'tabler:table', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-13 08:40:43', 1, '平台管理员', '2025-02-22 22:26:55');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (506902, 'pc-web', '模板配置', NULL, 5069, '/develop/gen/template', '/wemirr/develop/gen/template/index', 0, 'tabler:template', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-13 08:40:43', 1, '平台管理员', '2025-02-22 22:26:51');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (506903, 'pc-web', '列配置', NULL, 5069, '/develop/gen/column', '/wemirr/develop/gen/table-column/index', 0, 'tabler:table-column', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-13 08:40:43', 1, '平台管理员', '2025-02-22 22:26:48');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (506904, 'pc-web', '模板组', NULL, 5069, '/develop/gen/template-group', '/wemirr/develop/gen/template-group/index', 0, 'tabler:template', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-13 08:40:43', 1, '平台管理员', '2025-02-22 22:26:45');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (600101, 'pc-web', '智能问答', NULL, 6001, '/ai/local/talk', '/wemirr/ai/local/talk', 60001, 'mingcute:kakao-talk-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-01-10 09:50:35', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (600301, 'pc-web', '智能问答', NULL, 6003, '/ai/cloud/chat', '/wemirr/ai/cloud/chat', 6003, 'mingcute:kakao-talk-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-01-10 09:50:35', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (600401, 'pc-web', '智能体配置', NULL, 6004, '/ai/agent/config', '/wemirr/ai/agent/config/index', 600401, 'mingcute:settings-2-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-12-09 08:07:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (600402, 'pc-web', 'MCP 协议', NULL, 6004, '/ai/agent/mcp', '/wemirr/ai/agent/mcp/index', 600402, 'mingcute:plugin-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-12-09 08:07:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (600501, 'pc-web', '参数配置', NULL, 6005, '/ai/rag/config', '/wemirr/ai/rag/config/index', 600501, 'mingcute:file-check-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-12-09 08:07:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (600502, 'pc-web', '文档管理', NULL, 6005, '/ai/rag/doc', '/wemirr/ai/rag/doc/index', 600502, 'mingcute:file-check-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-12-09 08:07:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (600601, 'pc-web', '模型接入', NULL, 6006, '/ai/model/config', '/wemirr/ai/model/config/index', 600601, 'mingcute:box-3-line', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2025-12-09 08:07:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1040101, 'pc-web', '添加', 'tenant:add', 10401, '', NULL, 1040101, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1040102, 'pc-web', '编辑', 'tenant:edit', 10401, '', NULL, 1040102, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1040103, 'pc-web', '删除', 'tenant:remove', 10401, '', NULL, 1040103, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1040105, 'pc-web', '数据源配置', 'tenant:db-config', 10401, '', NULL, 1040105, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '数据源配置', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1040106, 'pc-web', '数据初始化', 'tenant:init-script', 10401, '', NULL, 1040106, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '基础数据初始化', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1040107, 'pc-web', '字典数据刷新', 'tenant:refresh-dict', 10401, '', NULL, 1040107, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '字典数据刷新', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1050101, 'pc-web', '添加', 'plan:definition:add', 10501, '', NULL, 1050101, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1050102, 'pc-web', '编辑', 'plan:definition:edit', 10501, '', NULL, 1050102, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1050103, 'pc-web', '删除', 'plan:definition:remove', 10501, '', NULL, 1050103, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1050108, 'pc-web', '授权', 'plan:definition:assign', 10501, '', NULL, 1050108, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '授权按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1070601, 'pc-web', '添加', 'dict:add', 10706, '', NULL, 1070601, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1070602, 'pc-web', '编辑', 'dict:edit', 10706, '', NULL, 1070602, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1070603, 'pc-web', '删除', 'dict:remove', 10706, '', NULL, 1070603, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1070605, 'pc-web', '刷新字典', 'dict:refresh', 10706, '', NULL, 1070605, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '字典数据刷新', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010101, 'pc-web', '添加', 'sys:user:add', 30101, '', NULL, 3010101, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010102, 'pc-web', '编辑', 'sys:user:edit', 30101, '', NULL, 3010102, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010103, 'pc-web', '删除', 'sys:user:remove', 30101, '', NULL, 3010103, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010104, 'pc-web', '重置密码', 'sys:user:reset', 30101, '', NULL, 3010104, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010105, 'pc-web', '导出', 'sys:user:export', 30101, '', NULL, 0, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '用户导出', b'0', 1, '平台管理员', '2024-12-25 10:58:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010201, 'pc-web', '添加', 'sys:org:add', 30102, '', NULL, 3010201, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010202, 'pc-web', '编辑', 'sys:org:edit', 30102, '', NULL, 3010202, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010203, 'pc-web', '删除', 'sys:org:remove', 30102, '', NULL, 3010203, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010301, 'pc-web', '添加', 'sys:position:add', 30103, '', NULL, 3010301, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010302, 'pc-web', '编辑', 'sys:position:edit', 30103, '', NULL, 3010302, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3010303, 'pc-web', '删除', 'sys:position:remove', 30103, '', NULL, 3010303, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3020101, 'pc-web', '添加', 'sys:menu:add', 30201, '', NULL, 3020101, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3020102, 'pc-web', '编辑', 'sys:menu:edit', 30201, '', NULL, 3020102, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3020103, 'pc-web', '删除', 'sys:menu:remove', 30201, '', NULL, 3020103, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3020201, 'pc-web', '添加', 'sys:role:add', 30202, '', NULL, 3020201, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3020202, 'pc-web', '编辑', 'sys:role:edit', 30202, '', NULL, 3020202, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3020203, 'pc-web', '删除', 'sys:role:remove', 30202, '', NULL, 3020203, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3020207, 'pc-web', '分配用户', 'sys:role:assign-users', 30202, '', NULL, 3020207, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '分配用户', b'0', 1, '平台管理员', '2024-12-09 15:54:08', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3020208, 'pc-web', '分配资源', 'sys:role:assign-resource', 30202, '', NULL, 3020208, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '分配资源', b'0', 1, '平台管理员', '2024-12-09 15:55:02', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3030101, 'pc-web', '添加', 'tenant:dict:add', 30301, '', NULL, 3030101, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3030102, 'pc-web', '编辑', 'tenant:dict:edit', 30301, '', NULL, 3030102, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3030103, 'pc-web', '删除', 'tenant:dict:remove', 30301, '', NULL, 3030103, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3060501, 'pc-web', '添加', 'message:template:add', 30605, '', NULL, 3060501, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3060502, 'pc-web', '编辑', 'message:template:edit', 30605, '', NULL, 3060502, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3060503, 'pc-web', '删除', 'message:template:remove', 30605, '', NULL, 3060503, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3060504, 'pc-web', '推送', 'message:template:publish', 30605, '', NULL, 3060504, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-12-07 10:30:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (50030801, 'pc-web', '学生管理', NULL, 500308, '/dev/online/demo/student', '/wemirr/develop/online/reader', 1, 'ph:student-fill', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"query\":{\"definitionKey\":\"student\"}}', '', b'0', NULL, NULL, '2025-01-08 09:38:42', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (50030802, 'pc-web', '老师管理', NULL, 500308, '/dev/online/demo/teacher', '/wemirr/develop/online/reader', 2, 'mdi:teacher', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"query\":{\"definitionKey\":\"teacher\"}}', '', b'0', NULL, NULL, '2025-01-08 09:38:42', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001483813821222913, 'pc-web', '测试菜单', 'test:menu', 0, '/test/menu', 'BasicLayout', 111110, 'carbon:align-box-middle-left', 'directory', b'1', NULL, b'0', b'0', b'1', '{\"activeIcon\":\"carbon:align-box-middle-left\"}', '', b'0', 1, '平台管理员', '2025-12-18 10:45:02', 1, '平台管理员', '2025-12-20 11:08:51');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001483967097868289, 'pc-web', '测试子菜单', '测试子菜单', 2001483813821222913, '/testsss', '/wemirr/platform/tenant/index222', 11, 'carbon:arrange', 'menu', b'1', NULL, b'0', b'0', b'1', '{\"activeIcon\":\"carbon:arrange\"}', '', b'0', 1, '平台管理员', '2025-12-18 10:45:38', 1, '平台管理员', '2025-12-20 11:08:31');
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001485011257266178, 'pc-web', '13231', '123', 2001483967097868289, '', NULL, 0, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '123', b'0', 1, '平台管理员', '2025-12-18 10:49:47', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `client_id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `shared`, `visible`, `meta`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001485026553892866, 'pc-web', '213', '213', 2001483967097868289, '', NULL, 0, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '213', b'0', 1, '平台管理员', '2025-12-18 10:49:51', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `tenant_id` bigint DEFAULT NULL COMMENT '租户编码',
                            `code` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '角色编码',
                            `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT '名称',
                            `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述信息',
                            `scope_type` tinyint DEFAULT NULL COMMENT '数据权限范围，值越大，权限越大',
                            `status` tinyint(1) DEFAULT '0' COMMENT '0=正常1=禁用',
                            `super` tinyint(1) DEFAULT '0' COMMENT '0=非 1=管理员',
                            `readonly` tinyint(1) DEFAULT '0' COMMENT '是否内置角色',
                            `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                            `create_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                            `last_modify_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
                            `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2001586678602641410 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `tenant_id`, `code`, `name`, `description`, `scope_type`, `status`, `super`, `readonly`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 1, 'PLATFORM-ADMIN', '平台管理员', '平台管理员，拥有所有数据可视权', 50, 1, 1, 1, 0, '超级管理员', '2019-10-25 13:46:00', 57, NULL, '2024-12-25 06:03:52');
INSERT INTO `sys_role` (`id`, `tenant_id`, `code`, `name`, `description`, `scope_type`, `status`, `super`, `readonly`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, 1, 'TENANT-ADMIN', '租户管理员', '租户管理员', 50, 1, 0, 1, 1, '长风一梦8888', '2022-06-07 07:52:29', 0, NULL, '2024-07-19 00:58:14');
INSERT INTO `sys_role` (`id`, `tenant_id`, `code`, `name`, `description`, `scope_type`, `status`, `super`, `readonly`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001586678602641409, 1, 'TEST', '测试', '测试角色', NULL, 1, 0, 0, 1, '平台管理员', '2025-12-18 17:33:47', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_org`;
CREATE TABLE `sys_role_org` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `role_id` bigint NOT NULL COMMENT '角色ID',
                                `org_id` bigint NOT NULL COMMENT '组织ID',
                                `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `last_modify_by` bigint DEFAULT NULL COMMENT '最近修改人',
                                `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最近修改人',
                                `last_modify_time` datetime DEFAULT NULL COMMENT '最近修改时间',
                                PRIMARY KEY (`id`),
                                KEY `sys_role_org_idx_role_id` (`role_id`),
                                KEY `sys_role_org_idx_org_id` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_role_org
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `role_id` bigint NOT NULL COMMENT '角色ID',
                                `res_id` bigint NOT NULL COMMENT '资源ID',
                                `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `last_modify_by` bigint DEFAULT NULL COMMENT '最近修改人',
                                `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最近修改人',
                                `last_modify_time` datetime DEFAULT NULL COMMENT '最近修改时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2002898793279930370 DEFAULT CHARSET=utf8mb3 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (420, 1, 1, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (421, 1, 3, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (422, 1, 11, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (423, 1, 15, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (424, 1, 16, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (425, 1, 50, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (426, 1, 60, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (427, 1, 104, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (428, 1, 105, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (429, 1, 107, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (430, 1, 151, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (431, 1, 152, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (432, 1, 153, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (433, 1, 160, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (434, 1, 161, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (435, 1, 162, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (436, 1, 163, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (437, 1, 164, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (438, 1, 165, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (439, 1, 166, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (440, 1, 168, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (441, 1, 301, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (442, 1, 302, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (443, 1, 303, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (444, 1, 306, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (445, 1, 310, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (446, 1, 320, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (447, 1, 350, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (448, 1, 1101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (449, 1, 1102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (450, 1, 1104, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (451, 1, 1106, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (452, 1, 5003, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (453, 1, 5005, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (454, 1, 5050, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (455, 1, 5069, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (456, 1, 6001, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (457, 1, 6003, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (458, 1, 6004, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (459, 1, 6005, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (460, 1, 6006, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (461, 1, 10401, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (462, 1, 10402, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (463, 1, 10501, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (464, 1, 10502, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (465, 1, 10503, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (466, 1, 10706, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (467, 1, 10707, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (468, 1, 15101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (469, 1, 15102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (470, 1, 15103, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (471, 1, 15201, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (472, 1, 15202, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (473, 1, 15203, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (474, 1, 15204, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (475, 1, 15205, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (476, 1, 15206, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (477, 1, 15207, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (478, 1, 15301, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (479, 1, 16101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (480, 1, 16102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (481, 1, 16103, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (482, 1, 16108, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (483, 1, 16201, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (484, 1, 16401, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (485, 1, 16408, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (486, 1, 16501, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (487, 1, 16502, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (488, 1, 16601, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (489, 1, 16608, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (490, 1, 30101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (491, 1, 30102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (492, 1, 30103, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (493, 1, 30201, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (494, 1, 30202, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (495, 1, 30301, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (496, 1, 30303, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (497, 1, 30604, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (498, 1, 30605, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (499, 1, 30606, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (500, 1, 30607, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (501, 1, 30608, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (502, 1, 31003, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (503, 1, 31005, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (504, 1, 31006, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (505, 1, 32002, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (506, 1, 32003, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (507, 1, 35005, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (508, 1, 110101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (509, 1, 110102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (510, 1, 110107, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (511, 1, 110108, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (512, 1, 110201, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (513, 1, 110202, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (514, 1, 110203, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (515, 1, 110401, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (516, 1, 110402, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (517, 1, 110403, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (518, 1, 110404, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (519, 1, 110601, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (520, 1, 160001, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (521, 1, 160002, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (522, 1, 160005, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (523, 1, 160006, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (524, 1, 160010, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (525, 1, 160011, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (526, 1, 160012, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (527, 1, 161001, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (528, 1, 161002, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (529, 1, 161003, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (530, 1, 162002, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (531, 1, 163001, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (532, 1, 163002, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (533, 1, 500301, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (534, 1, 500302, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (535, 1, 500304, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (536, 1, 500308, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (537, 1, 500501, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (538, 1, 500502, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (539, 1, 500503, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (540, 1, 506901, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (541, 1, 506902, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (542, 1, 506903, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (543, 1, 506904, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (544, 1, 600101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (545, 1, 600301, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (546, 1, 600401, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (547, 1, 600402, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (548, 1, 600501, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (549, 1, 600502, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (550, 1, 600601, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (551, 1, 1040101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (552, 1, 1040102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (553, 1, 1040103, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (554, 1, 1040105, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (555, 1, 1040106, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (556, 1, 1040107, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (557, 1, 1050101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (558, 1, 1050102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (559, 1, 1050103, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (560, 1, 1070601, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (561, 1, 1070602, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (562, 1, 1070603, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (563, 1, 1070605, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (564, 1, 3010101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (565, 1, 3010102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (566, 1, 3010103, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (567, 1, 3010104, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (568, 1, 3010105, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (569, 1, 3010201, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (570, 1, 3010202, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (571, 1, 3010203, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (572, 1, 3010301, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (573, 1, 3010302, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (574, 1, 3010303, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (575, 1, 3020101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (576, 1, 3020102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (577, 1, 3020103, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (578, 1, 3020201, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (579, 1, 3020202, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (580, 1, 3020203, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (581, 1, 3020207, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (582, 1, 3020208, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (583, 1, 3030101, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (584, 1, 3030102, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (585, 1, 3030103, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (586, 1, 3060501, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (587, 1, 3060502, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (588, 1, 3060503, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (589, 1, 3060504, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (590, 1, 50030801, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (591, 1, 50030802, 0, NULL, NULL, '2025-12-09 08:07:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (592, 1, 1050108, 0, NULL, NULL, '2025-12-10 13:30:34', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998921038033301505, 1, 1998921037957804033, 1, 1, '平台管理员', '2025-12-11 09:01:28', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998928433790824450, 1, 1998928433723715585, 1, 1, '平台管理员', '2025-12-11 09:30:52', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1998928507325362178, 1, 1998928507266641922, 1, 1, '平台管理员', '2025-12-11 09:31:09', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742017, 2, 1, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742018, 2, 10401, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742019, 2, 10402, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742020, 2, 10706, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742021, 2, 10707, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742022, 2, 10501, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742023, 2, 10503, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742024, 2, 104, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742025, 2, 105, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742026, 2, 107, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742027, 2, 35005, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001321053254742028, 2, 350, 1, 1, '平台管理员', '2025-12-17 23:58:17', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001483813884137474, 1, 2001483813821222913, 0, 1, '平台管理员', '2025-12-18 10:45:02', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001483967190142977, 1, 2001483967097868289, 0, 1, '平台管理员', '2025-12-18 10:45:38', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001485011324375042, 1, 2001485011257266178, 0, 1, '平台管理员', '2025-12-18 10:49:47', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001485026633584641, 1, 2001485026553892866, 0, 1, '平台管理员', '2025-12-18 10:49:51', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603652145154, 2, 2001485026553892866, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603652145155, 2, 10401, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339458, 2, 1, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339459, 2, 10402, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339460, 2, 10501, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339461, 2, 2001485011257266178, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339462, 2, 10503, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339463, 2, 104, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339464, 2, 105, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339465, 2, 2001483813821222913, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339466, 2, 107, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339467, 2, 10706, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339468, 2, 10707, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339469, 2, 2001483967097868289, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339470, 2, 35005, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339471, 2, 350, 0, 1, '平台管理员', '2025-12-18 15:06:27', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001549603656339472, 1, 35003, 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2002214386692927490, 1, 2002214386596458497, 1, 1, '平台管理员', '2025-12-20 11:08:04', NULL, NULL, NULL);
INSERT INTO `sys_role_res` (`id`, `role_id`, `res_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2002898793279930369, 1, 2002898793212821506, 1, 1, '平台管理员', '2025-12-22 08:27:39', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_site_notify
-- ----------------------------
DROP TABLE IF EXISTS `sys_site_notify`;
CREATE TABLE `sys_site_notify` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                   `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息级别',
                                   `status` tinyint(1) DEFAULT NULL COMMENT '0=为发布;1=已发布',
                                   `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
                                   `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
                                   `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '接受者ID',
                                   `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                   `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
                                   `deleted` bit(1) DEFAULT b'0',
                                   `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                   `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                                   `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                   `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1863776323592458243 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of sys_site_notify
-- ----------------------------
BEGIN;
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, 1, '1', 1, '1', '测试消息', '2,1', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, '管理员', '2021-07-12 11:41:31', 1, '长风一梦0000', '2023-12-15 14:16:51');
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3, 1, '0', 1, '1', '测试通知', '1', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'0', 1, '管理员', '2021-07-12 11:42:15', 1, '长风一梦0000', '2023-12-15 14:16:51');
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (4, 2, '2', 1, '1', '测试待办', '1', '<p>测试待办</p><img src=\"http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/15/30369909559790.png\" contenteditable=\"false\"/>', '测试待办', b'0', 1, '管理员', '2021-07-12 11:42:26', NULL, NULL, '2024-12-03 10:44:22');
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1863776323592458242, NULL, '0', NULL, '1', '12312', '1', '<p>12312321321</p>', '3213123123123', b'0', NULL, NULL, '2024-12-03 10:44:37', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_station
-- ----------------------------
DROP TABLE IF EXISTS `sys_station`;
CREATE TABLE `sys_station` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                               `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
                               `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
                               `sequence` tinyint DEFAULT NULL COMMENT '排序',
                               `org_id` bigint DEFAULT '0' COMMENT '组织ID',
                               `status` bit(1) DEFAULT b'1' COMMENT '状态',
                               `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
                               `deleted` bit(1) DEFAULT b'0',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                               `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                               `create_by` bigint DEFAULT NULL,
                               `last_modify_by` bigint DEFAULT NULL,
                               `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                               `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10129 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='岗位';

-- ----------------------------
-- Records of sys_station
-- ----------------------------
BEGIN;
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (100, 1, '总经理', 'CEO', 0, 100, b'1', '总部-1把手', b'0', '2019-07-10 17:03:03', NULL, 1, 1, '长风一梦8888', '2021-11-27 06:35:22');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (101, 1, '副总经理', NULL, 1, 10001, b'1', '总部-2把手', b'0', '2019-07-22 17:07:55', NULL, 1, 1, '长风一梦', '2021-07-28 02:58:53');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (103, 1, '研发经理', NULL, 1, 10002, b'1', '子公司-研发部老大', b'0', '2019-11-07 16:08:49', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:06');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (104, 1, '副总经理', NULL, 1, 10001, b'1', '子公司-老大', b'0', '2019-11-16 09:51:45', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:29');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (106, 1, '产品经理', NULL, 1, 10002, b'1', '子公司-产品部老大', b'0', '2019-11-16 09:53:27', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (107, 1, '人事经理', NULL, 1, 10002, b'1', '子公司-综合老大', b'0', '2019-11-16 09:54:43', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (108, 1, 'Java研发', 'CTI', 1, 10203, b'1', '普通员工', b'0', '2019-11-16 09:55:04', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:38');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1011, 1, 'UI工程师', 'CTI', 1, 10102, b'1', '普通员工', b'0', '2019-11-16 09:55:40', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:52');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1012, 1, '运维工程师', 'CTI', 1, 10203, b'1', '普通员工', b'0', '2019-11-16 09:55:53', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:45');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1013, 1, '行政经理', NULL, 1, 10003, b'1', '普通员工', b'0', '2019-11-16 09:56:04', NULL, 3, 1, '长风一梦8888', '2021-11-27 06:36:22');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1014, 1, '人事经理', NULL, 1, 10004, b'1', '北京分公司-综合部老大', b'0', '2019-11-16 09:56:38', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:36');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1015, 1, '研发经理', NULL, 1, 10102, b'1', '北京分公司-研发部老大', b'0', '2019-11-16 09:57:07', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:28');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1016, 1, '销售经理', NULL, 1, 10004, b'1', '北京销售部老大', b'0', '2019-11-16 09:57:40', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:19');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10121, 1, '销售总监', NULL, 1, 10005, b'1', '总部2把手', b'0', '2019-11-16 09:59:10', NULL, 3, 1, '长风一梦', '2021-07-29 02:36:31');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10122, 1, '销售员工', NULL, 1, 10006, b'1', '普通员工', b'0', '2019-11-16 09:58:41', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:11');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10123, 1, '财务总监', NULL, 1, 10003, b'1', '总部2把手', b'0', '2019-11-16 09:59:39', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:06');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10124, 1, '市场经理', NULL, 1, 10003, b'1', '总部市场部老大', b'0', '2019-11-16 10:00:03', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:01');
INSERT INTO `sys_station` (`id`, `tenant_id`, `name`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `create_time`, `create_name`, `create_by`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (10128, 1, '前端工程师', 'CTI', 1, 10102, b'1', '普通员工', b'0', '2019-11-16 10:00:43', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:20:53');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                 `user_id` bigint NOT NULL COMMENT '用户ID',
                                 `role_id` bigint NOT NULL COMMENT '角色ID',
                                 `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                 `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `last_modify_by` bigint DEFAULT NULL COMMENT '最近修改人',
                                 `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最近修改人',
                                 `last_modify_time` datetime DEFAULT NULL COMMENT '最近修改时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2001531418555060235 DEFAULT CHARSET=utf8mb3 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (165, 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (166, 1, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (167, 2, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (168, 3, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (169, 8, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (170, 10, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (171, 12, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (172, 14, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (173, 19, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (174, 20, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (175, 21, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (176, 22, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999051236422701058, 1999051236397535234, 2, 1, 1, '平台管理员', '2025-12-11 17:38:50', NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001531418555060226, 1999051236397535234, 2, 0, 1, '平台管理员', '2025-12-18 13:54:11', NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001531418555060227, 22, 2, 0, 1, '平台管理员', '2025-12-18 13:54:11', NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001531418555060228, 21, 2, 0, 1, '平台管理员', '2025-12-18 13:54:11', NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001531418555060229, 20, 2, 0, 1, '平台管理员', '2025-12-18 13:54:11', NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001531418555060230, 19, 2, 0, 1, '平台管理员', '2025-12-18 13:54:11', NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001531418555060231, 14, 2, 0, 1, '平台管理员', '2025-12-18 13:54:11', NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001531418555060232, 10, 2, 0, 1, '平台管理员', '2025-12-18 13:54:11', NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001531418555060233, 3, 2, 0, 1, '平台管理员', '2025-12-18 13:54:11', NULL, NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2001531418555060234, 1, 2, 0, 1, '平台管理员', '2025-12-18 13:54:11', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_db_instance
-- ----------------------------
DROP TABLE IF EXISTS `t_db_instance`;
CREATE TABLE `t_db_instance` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '实例名',
                                 `db_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据库类型',
                                 `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
                                 `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
                                 `driver_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                 `jdbc_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'localhost' COMMENT '数据库连接',
                                 `status` bit(1) DEFAULT b'0' COMMENT '0=停用1=启用',
                                 `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
                                 `deleted` bit(1) DEFAULT b'0',
                                 `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                 `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                                 `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最后修改人名称',
                                 `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1999753847152705538 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='动态数据源';

-- ----------------------------
-- Records of t_db_instance
-- ----------------------------
BEGIN;
INSERT INTO `t_db_instance` (`id`, `name`, `db_type`, `username`, `password`, `driver_class_name`, `jdbc_url`, `status`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999724240479338498, 'Docker-Local-Dynamic', 'mysql', 'root', '123456', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://localhost:3306/v4-pit?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai', b'1', NULL, b'0', 1, '平台管理员', '2025-12-13 14:13:07', 1, '平台管理员', '2025-12-13 16:39:50');
INSERT INTO `t_db_instance` (`id`, `name`, `db_type`, `username`, `password`, `driver_class_name`, `jdbc_url`, `status`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999753847152705537, 'Docker-Local-Scheam', 'mysql', 'root', '123456', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://localhost:3306/tenant-scheam?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai', b'1', NULL, b'0', 1, '平台管理员', '2025-12-13 16:10:45', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `t_gateway_route`;
CREATE TABLE `t_gateway_route` (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `route_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '路由ID',
                                   `uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业邮箱',
                                   `order` tinyint DEFAULT '0' COMMENT '排序',
                                   `predicates` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '谓语条件',
                                   `filters` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '过滤器',
                                   `locked` bit(1) DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
                                   `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
                                   `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   UNIQUE KEY `unique_route_id` (`route_id`) USING BTREE COMMENT '路由ID唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网关路由表';

-- ----------------------------
-- Records of t_gateway_route
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_online_form_data
-- ----------------------------
DROP TABLE IF EXISTS `t_online_form_data`;
CREATE TABLE `t_online_form_data` (
                                      `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                      `definition_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '业务组标识',
                                      `form_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '动态表单数据',
                                      `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                      `deleted` bit(1) DEFAULT b'0',
                                      `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                      `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                      `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                                      `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                      `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`),
                                      KEY `idx_business_group_key` (`definition_key`)
) ENGINE=InnoDB AUTO_INCREMENT=1876938407108820994 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='动态表单数据表';

-- ----------------------------
-- Records of t_online_form_data
-- ----------------------------
BEGIN;
INSERT INTO `t_online_form_data` (`id`, `definition_key`, `form_data`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1876938285876658177, 'student', '{\"definitionKey\":\"student\",\"id\":\"1876938285876658177\",\"createdName\":\"平台管理员\",\"createdTime\":\"2025-01-08T10:25:34Z\",\"name\":\"1\",\"email\":\"1222\"}', NULL, b'0', 1, '平台管理员', '2025-01-08 18:25:34', 1, '平台管理员', '2025-01-09 09:23:23');
INSERT INTO `t_online_form_data` (`id`, `definition_key`, `form_data`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1876938337336573954, 'teacher', '{\"definitionKey\":\"teacher\",\"id\":\"1876938337336573954\",\"createdName\":\"平台管理员\",\"createdTime\":\"2025-01-08T10:25:46Z\",\"name\":\"花花老师\",\"age\":18,\"remark\":\"你别问\",\"type\":[\"1\",\"4\"]}', NULL, b'0', 1, '平台管理员', '2025-01-08 18:25:46', 1, '平台管理员', '2025-01-09 16:39:34');
INSERT INTO `t_online_form_data` (`id`, `definition_key`, `form_data`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1876938382756691969, 'teacher', '{\"definitionKey\":\"teacher\",\"id\":\"1876938382756691969\",\"createdName\":\"平台管理员\",\"createdTime\":\"2025-01-08T10:25:57Z\",\"name\":\"小安老师\",\"age\":19,\"remark\":\"反差\",\"type\":[\"4\",\"3\"]}', NULL, b'0', 1, '平台管理员', '2025-01-08 18:25:57', 1, '平台管理员', '2025-01-09 16:39:45');
INSERT INTO `t_online_form_data` (`id`, `definition_key`, `form_data`, `tenant_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1876938407108820993, 'student', '{\"definitionKey\":\"student\",\"name\":\"3\",\"email\":\"4\"}', NULL, b'0', 1, '平台管理员', '2025-01-08 18:26:03', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_online_model
-- ----------------------------
DROP TABLE IF EXISTS `t_online_model`;
CREATE TABLE `t_online_model` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
                                  `definition_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '定义KEY',
                                  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
                                  `version` int NOT NULL DEFAULT '1' COMMENT '版本号',
                                  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '备注',
                                  `form_script` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '脚本',
                                  `form_schemas` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '表单配置',
                                  `form_crud_config` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '表单CRUD配置',
                                  `deleted` bit(1) DEFAULT b'0',
                                  `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                                  `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                  `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1876935098889273347 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='在线模型表';

-- ----------------------------
-- Records of t_online_model
-- ----------------------------
BEGIN;
INSERT INTO `t_online_model` (`id`, `title`, `definition_key`, `status`, `version`, `description`, `form_script`, `form_schemas`, `form_crud_config`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, '学生管理', 'student', 1, 1, '测试用的', 'const { defineExpose, find } = epic;\n\nfunction test (){\n    console.log(\'test\')\n}\n\n// 通过defineExpose暴露的函数或者属性\ndefineExpose({\n test\n})', '[{\"type\":\"page\",\"id\":\"root\",\"label\":\"页面\",\"children\":[{\"label\":\"表单\",\"type\":\"form\",\"componentProps\":{\"name\":\"default\",\"labelWidth\":\"100px\",\"labelLayout\":\"fixed\",\"labelCol\":{\"span\":5},\"wrapperCol\":{\"span\":19}},\"children\":[{\"label\":\"名称\",\"type\":\"input\",\"field\":\"name\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入名称\"},\"id\":\"input_npnj7ex7\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"邮箱\",\"type\":\"input\",\"field\":\"email\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入邮箱\"},\"id\":\"input_nrd81qrj\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]}],\"id\":\"form_rnxhlmui\"}],\"componentProps\":{\"style\":{\"padding\":\"16px\"}}}]', '{null:{\"search\":{\"show\":true},\"column\":{\"show\":true,\"width\":200},\"type\":\"page\",\"title\":\"页面\"}}', b'0', 1, '平台管理员', '2025-01-08 15:30:46', 1, '平台管理员', '2025-01-08 18:12:33');
INSERT INTO `t_online_model` (`id`, `title`, `definition_key`, `status`, `version`, `description`, `form_script`, `form_schemas`, `form_crud_config`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, '老师管理', 'teacher', 1, 1, '测试专用', 'const { defineExpose, find } = epic;\n\nfunction test (){\n    console.log(\'test\')\n}\n\n// 通过defineExpose暴露的函数或者属性\ndefineExpose({\n test\n})', '[{\"type\":\"page\",\"id\":\"root\",\"label\":\"页面\",\"children\":[{\"label\":\"表单\",\"type\":\"form\",\"componentProps\":{\"name\":\"default\",\"labelWidth\":\"100px\",\"labelLayout\":\"fixed\",\"labelCol\":{\"span\":5},\"wrapperCol\":{\"span\":19}},\"children\":[{\"label\":\"名称\",\"type\":\"input\",\"field\":\"name\",\"input\":true,\"componentProps\":{\"placeholder\":\"老师名称\"},\"id\":\"input_4cqc5eq4\"},{\"label\":\"年龄\",\"type\":\"number\",\"field\":\"age\",\"input\":true,\"componentProps\":{\"style\":{\"width\":\"100%\"},\"placeholder\":\"请输入\",\"defaultValue\":18},\"id\":\"number_ttaqiyd7\"},{\"label\":\"擅长\",\"type\":\"textarea\",\"field\":\"remark\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入\",\"defaultValue\":\"老师擅长姿势\"},\"id\":\"textarea_t4nbl3pp\"},{\"label\":\"类型\",\"type\":\"checkbox\",\"field\":\"type\",\"input\":true,\"componentProps\":{\"options\":[{\"label\":\"蛟龙出海\",\"value\":\"1\"},{\"label\":\"亢龙有悔\",\"value\":\"2\"},{\"label\":\"不动如山\",\"value\":\"3\"},{\"label\":\"探囊取物\",\"value\":\"4\"}],\"defaultValue\":[]},\"id\":\"checkbox_irvcgtg5\"}],\"id\":\"form_k4isjdth\"}],\"componentProps\":{\"style\":{\"padding\":\"16px\"}}}]', '{null:{\"search\":{\"show\":true},\"column\":{\"show\":true,\"width\":200},\"type\":\"page\",\"title\":\"页面\"}}', b'0', 1, '平台管理员', '2025-01-08 18:12:54', 1, '平台管理员', '2025-01-09 15:40:21');
COMMIT;

-- ----------------------------
-- Table structure for t_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant`;
CREATE TABLE `t_tenant` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户编码',
                            `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '租户名称',
                            `type` tinyint DEFAULT '0' COMMENT '0=其它,1=企业',
                            `alias` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '简称',
                            `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'LOGO',
                            `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户邮箱',
                            `status` bit(1) DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
                            `contact_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
                            `contact_phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人方式',
                            `industry` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '行业',
                            `province_id` int DEFAULT NULL COMMENT '省份',
                            `province_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省份',
                            `city_id` int DEFAULT NULL COMMENT '市',
                            `city_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
                            `address` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '详细地址',
                            `district_id` int DEFAULT NULL COMMENT '区县',
                            `district_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区县',
                            `credit_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '统一信用代码',
                            `legal_person_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '法人',
                            `web_site` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业网址',
                            `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
                            `deleted` bit(1) DEFAULT b'0',
                            `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                            `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                            `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                            `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1864956411491020802 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户信息';

-- ----------------------------
-- Records of t_tenant
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, '0000', '平台超级租户', 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '000000@qq.com', b'1', '唐亚峰', '13002170000', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'https://cloud.battcn.com', NULL, b'0', 0, NULL, '2021-07-31 07:27:08', 1, '长风一梦', '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, '2222', '小米科技有限责任公司', 1, '小米', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '666666@qq.com', b'1', '雷军', '13002176666', '4', 110000, '北京市', 110100, '市辖区', '北京市海淀区西二旗中路33号院6号楼6层006号 ', 110108, '海淀区', '91110108551385082Q', '雷军', 'https://cloud.battcn.com', NULL, b'0', 0, NULL, '2021-07-31 08:14:02', 1, '长风一梦', '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (4, '4444', '大米科技有限责任公司', 1, '大米', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '666666@qq.com', b'1', '电军', '13002176666', '4', 110000, '北京市', 110100, '市辖区', '北京市海淀区西二旗中路33号院6号楼6层006号 ', 110108, '海淀区', '91110108551385082Q', '电军', 'https://cloud.battcn.com', NULL, b'0', 0, NULL, '2021-07-31 08:14:02', 1, '长风一梦', '2025-12-13 08:12:39');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (8, '8888', '深圳腾讯科技股份有限公司', 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '888888@qq.com', b'1', '马化腾', '13002178888', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'http://localhost:5666', NULL, b'0', 0, NULL, '2021-07-31 07:27:08', 1, '平台管理员', '2025-12-13 08:12:58');
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_db_binding
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_db_binding`;
CREATE TABLE `t_tenant_db_binding` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `tenant_id` bigint NOT NULL COMMENT '租户ID',
                                       `db_instance_id` bigint DEFAULT NULL COMMENT 'DB实例ID',
                                       `strategy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '隔离策略: DATABASE/SCHEMA/COLUMN',
                                       `schema_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运行时Schema名称',
                                       `is_primary` bit(1) DEFAULT b'1' COMMENT '是否为主源',
                                       `deleted` bit(1) DEFAULT b'0',
                                       `create_by` bigint DEFAULT NULL,
                                       `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                       `create_time` datetime DEFAULT NULL,
                                       `last_modify_by` bigint DEFAULT NULL,
                                       `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                       `last_modify_time` datetime DEFAULT NULL,
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1999754763452944386 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户配置信息';

-- ----------------------------
-- Records of t_tenant_db_binding
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_db_binding` (`id`, `tenant_id`, `db_instance_id`, `strategy`, `schema_name`, `is_primary`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999724359408828418, 3, 1999724240479338498, 'SCHEMA', 'v4_dev_8888', b'1', b'0', 1, '平台管理员', '2025-12-13 14:13:35', 1, '平台管理员', '2025-12-13 14:45:30');
INSERT INTO `t_tenant_db_binding` (`id`, `tenant_id`, `db_instance_id`, `strategy`, `schema_name`, `is_primary`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999753977905938434, 2, 1999753847152705537, 'SCHEMA', 'tenant_scheam_2222', b'1', b'0', 1, '平台管理员', '2025-12-13 16:11:17', 1, '平台管理员', '2025-12-13 16:14:30');
INSERT INTO `t_tenant_db_binding` (`id`, `tenant_id`, `db_instance_id`, `strategy`, `schema_name`, `is_primary`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999754691218640898, 8, 1999724240479338498, 'DATABASE', NULL, b'1', b'0', 1, '平台管理员', '2025-12-13 16:14:07', NULL, NULL, NULL);
INSERT INTO `t_tenant_db_binding` (`id`, `tenant_id`, `db_instance_id`, `strategy`, `schema_name`, `is_primary`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999754763452944385, 4, 1999753847152705537, 'SCHEMA', 'tenant_scheam_4444', b'1', b'0', 1, '平台管理员', '2025-12-13 16:14:24', 1, '平台管理员', '2025-12-13 16:14:32');
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_dict`;
CREATE TABLE `t_tenant_dict` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '雪花算法ID',
                                 `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '编码',
                                 `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
                                 `parent_id` bigint DEFAULT NULL COMMENT '父级ID',
                                 `parent_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '上级编码',
                                 `full_code_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '完整编码路径',
                                 `status` bit(1) DEFAULT b'1' COMMENT '状态',
                                 `readonly` bit(1) DEFAULT b'0' COMMENT '0=否；1=是；只读数据不允许删除',
                                 `sequence` tinyint DEFAULT NULL COMMENT '排序',
                                 `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
                                 `tenant_id` bigint NOT NULL COMMENT '租户ID t_tenant.id',
                                 `deleted` bit(1) DEFAULT b'0',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                 `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `last_modify_by` bigint DEFAULT NULL COMMENT '更新人ID',
                                 `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                 `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 KEY `idx_code` (`code`) USING BTREE COMMENT '租户字典索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_tenant_dict
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                          `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                          `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
                          `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '密码',
                          `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
                          `org_id` bigint DEFAULT NULL COMMENT '组织ID',
                          `position_id` bigint DEFAULT NULL COMMENT '岗位ID',
                          `readonly` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否内置',
                          `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
                          `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '手机',
                          `id_card` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证',
                          `sex` tinyint DEFAULT '1' COMMENT '性别',
                          `status` bit(1) DEFAULT b'0' COMMENT '状态 \n1启用 0禁用',
                          `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '头像',
                          `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
                          `nation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '民族',
                          `education` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学历',
                          `position_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '职位状态',
                          `birthday` date DEFAULT NULL COMMENT '生日',
                          `last_login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最后一次登录IP',
                          `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
                          `deleted` bit(1) DEFAULT b'0',
                          `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                          `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                          `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                          `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `UN_ACCOUNT_TENANT` (`username`,`tenant_id`) USING BTREE COMMENT '账号唯一约束'
) ENGINE=InnoDB AUTO_INCREMENT=1999051236397535235 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 1, 'admin', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '平台管理员', 100, 100, b'0', '1837307557@qq.com', '13002171111', '000000000000000000', 1, b'1', 'http://127.0.0.1:19000/wp-local/dev/693a856102cefd0cffddf284.png', '平台最帅的超级管理员', 'mz_daiz', 'SUOSHI', 'QUIT', '2023-10-14', '192.168.19.166', '2025-12-22 08:23:36', b'0', 0, '系统管理员', '2020-10-16 03:25:36', NULL, NULL, NULL);
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, 1, 'test', '{bcrypt}$2a$10$l02Mp.D1IH0p6d5UxZ37TuhUxUmnIbnjwjMrhny/EMqnHR.MsdRNe', '测试管理员', 10001, 100, b'0', '1300000000@qq.com', '13002171921', '000000000000000000', 1, b'1', 'http://127.0.0.1:19000/wp-local/dev/693a8da302cebd51038e9d25.png', '测试测试测试', 'mz_hanz', 'SUOSHI', 'WORKING', '2023-10-14', NULL, NULL, b'0', 0, '系统管理员', '2022-12-04 08:05:29', 1, '平台管理员', '2025-12-15 19:45:09');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1999051236397535234, 3, '13002178888', '{bcrypt}$2a$10$CUJcTWhs.tzppV2OG2dkkeeE3M11tTi.Pb9pV6sOXcFPyqQIj6q9K', '马化腾', NULL, NULL, b'0', '888888@qq.com', '13002178888', NULL, 1, b'1', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', 1, '平台管理员', '2025-12-11 17:38:50', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_user_third_account
-- ----------------------------
DROP TABLE IF EXISTS `t_user_third_account`;
CREATE TABLE `t_user_third_account` (
                                        `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                                        `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                                        `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '平台类型',
                                        `account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '第三方平台用户唯一标识',
                                        `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
                                        `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '昵称',
                                        `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户邮箱',
                                        `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像 URL',
                                        `access_token` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '访问令牌',
                                        `refresh_token` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '刷新令牌',
                                        `token_expire_time` datetime DEFAULT NULL COMMENT '令牌过期时间',
                                        `deleted` bit(1) DEFAULT b'0',
                                        `create_by` bigint DEFAULT '0' COMMENT '创建人id',
                                        `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `last_modify_by` bigint DEFAULT '0' COMMENT '更新人id',
                                        `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                        `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `idx_user_platform` (`type`,`account_id`),
                                        KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1875008671508062211 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='第三方授权账户表';

-- ----------------------------
-- Records of t_user_third_account
-- ----------------------------
BEGIN;
INSERT INTO `t_user_third_account` (`id`, `user_id`, `type`, `account_id`, `username`, `nickname`, `email`, `avatar`, `access_token`, `refresh_token`, `token_expire_time`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1875008671508062210, NULL, 'gitee', '483986', 'battcn-levin', 'Levin丶枫过无痕', NULL, 'https://foruda.gitee.com/avatar/1676915438129308948/483986_battcn-levin_1578925304.png', '734c4243f9b25732e922c22b6ff1126f', '4a7124450744a3855b7da82ecc8b5af5d54a9f84db7dac8ab76989971f65ca63', NULL, b'0', NULL, NULL, '2025-01-03 10:37:58', NULL, NULL, '2025-01-03 15:19:35');
COMMIT;

-- ----------------------------
-- Table structure for tms_accident
-- ----------------------------
DROP TABLE IF EXISTS `tms_accident`;
CREATE TABLE `tms_accident` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '事故ID',
                                `accident_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '事故编号',
                                `truck_id` bigint DEFAULT NULL COMMENT '卡车ID',
                                `plate_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车牌号',
                                `driver_id` bigint DEFAULT NULL COMMENT '驾驶员ID',
                                `driver_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶员姓名',
                                `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '事故地点',
                                `responsible_party` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '责任方',
                                `accident_amount` decimal(10,2) NOT NULL COMMENT '事故金额',
                                `accident_date` date DEFAULT NULL COMMENT '事故日期',
                                `agent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '经办人姓名',
                                `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                `deleted` bit(1) DEFAULT b'0',
                                `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `last_modify_by` bigint DEFAULT NULL COMMENT '修改人ID',
                                `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1739954453250420738 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆事故表';

-- ----------------------------
-- Records of tms_accident
-- ----------------------------
BEGIN;
INSERT INTO `tms_accident` (`id`, `accident_no`, `truck_id`, `plate_no`, `driver_id`, `driver_name`, `address`, `responsible_party`, `accident_amount`, `accident_date`, `agent_name`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700845402592829442, 'TEN202309100001', 11, NULL, 11, NULL, '11', '11', 112.00, '2023-09-08', '121', '212', NULL, 1, '长风一梦8888', '2023-09-10 12:15:18', NULL, NULL, NULL);
INSERT INTO `tms_accident` (`id`, `accident_no`, `truck_id`, `plate_no`, `driver_id`, `driver_name`, `address`, `responsible_party`, `accident_amount`, `accident_date`, `agent_name`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1739954453250420737, 'TAN202312270001', 1739846242807664641, NULL, 1, NULL, '123123', '123', 132.00, '2023-12-27', '123', '123123', b'0', 1, '长风一梦8888', '2023-12-27 18:20:43', 1, '长风一梦8888', '2023-12-27 18:23:34');
COMMIT;

-- ----------------------------
-- Table structure for tms_driver
-- ----------------------------
DROP TABLE IF EXISTS `tms_driver`;
CREATE TABLE `tms_driver` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '驾驶员ID',
                              `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                              `id_card_no` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证号',
                              `id_card_start_date` date DEFAULT NULL COMMENT '身份证有效期开始日期',
                              `id_card_end_date` date DEFAULT NULL COMMENT '身份证有效期结束日期',
                              `license_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶证号',
                              `license_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶证类型',
                              `driver_license_start` date DEFAULT NULL COMMENT '驾驶证有效期开始日期',
                              `driver_license_end` date DEFAULT NULL COMMENT '驾驶证有效期结束日期',
                              `driver_license_issue_org` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶证发放机关',
                              `certificate_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '证书编号',
                              `certificate_start` date DEFAULT NULL COMMENT '证书有效期开始日期',
                              `certificate_end` date DEFAULT NULL COMMENT '证书有效期结束日期',
                              `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '备注',
                              `enabled` tinyint(1) DEFAULT NULL COMMENT '启用状态',
                              `file_portrait_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '人像照片ID',
                              `file_id_card_front` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证正面照片ID',
                              `file_id_card_back` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证背面照片ID',
                              `file_driver_license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶证照片ID',
                              `file_certificate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '证书照片ID',
                              `file_other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '其他照片ID',
                              `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
                              `mobile` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
                              `adapt_id_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '适用证件号',
                              `driver_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶员类型',
                              `default_truck_id` bigint DEFAULT NULL COMMENT '默认车辆ID',
                              `adapt_id_issue_date` date DEFAULT NULL COMMENT '适用证件发放日期',
                              `adapt_id_due_date` date DEFAULT NULL COMMENT '适用证件有效期至',
                              `file_adapt_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '适用证件照片ID',
                              `approval_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批状态',
                              `bpm_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'BPM状态',
                              `approval_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批用户ID',
                              `approval_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批用户名',
                              `approval_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '审批描述',
                              `deleted` bit(1) DEFAULT b'0',
                              `create_by` bigint NOT NULL COMMENT '创建人ID',
                              `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                              `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                              `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1698999611146285058 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='司机信息表';

-- ----------------------------
-- Records of tms_driver
-- ----------------------------
BEGIN;
INSERT INTO `tms_driver` (`id`, `tenant_id`, `id_card_no`, `id_card_start_date`, `id_card_end_date`, `license_code`, `license_type`, `driver_license_start`, `driver_license_end`, `driver_license_issue_org`, `certificate_code`, `certificate_start`, `certificate_end`, `remark`, `enabled`, `file_portrait_id`, `file_id_card_front`, `file_id_card_back`, `file_driver_license`, `file_certificate`, `file_other`, `real_name`, `mobile`, `adapt_id_no`, `driver_type`, `default_truck_id`, `adapt_id_issue_date`, `adapt_id_due_date`, `file_adapt_id`, `approval_status`, `bpm_status`, `approval_id`, `approval_name`, `approval_desc`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 1, '431103199008255733', '2023-09-05', '2023-09-05', 'HX0102', 'C2', '2023-09-01', '2023-09-22', NULL, NULL, '2023-09-01', '2023-09-05', '备注信息', 1, '1698991906406989826', '1698991906406989826', '1698991906406989826', '1698991906406989826', '1698991906406989826', '1698991906406989826', '哈哈哈', '13000001111', 'AI13213123', 'C2', 1739846242807664641, '2023-09-05', '2023-09-23', NULL, NULL, NULL, NULL, NULL, NULL, b'0', 1, '系统管理员', '2023-09-05 05:09:41', 1, '平台管理员', '2024-12-19 22:06:24');
COMMIT;

-- ----------------------------
-- Table structure for tms_expense
-- ----------------------------
DROP TABLE IF EXISTS `tms_expense`;
CREATE TABLE `tms_expense` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `expense_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '费用编号',
                               `expense_item` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '费用项目',
                               `truck_id` bigint DEFAULT NULL COMMENT '卡车ID',
                               `plate_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车牌号',
                               `expiry_date` date DEFAULT NULL COMMENT '到期日期',
                               `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
                               `handling_date` date DEFAULT NULL COMMENT '管理日期',
                               `agent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '经办人姓名',
                               `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                               `deleted` bit(1) DEFAULT b'0',
                               `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                               `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `last_modify_by` bigint DEFAULT NULL COMMENT '修改人ID',
                               `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                               `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1739953184926445571 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='费用表';

-- ----------------------------
-- Records of tms_expense
-- ----------------------------
BEGIN;
INSERT INTO `tms_expense` (`id`, `expense_no`, `expense_item`, `truck_id`, `plate_no`, `expiry_date`, `amount`, `handling_date`, `agent_name`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700070891471699970, 'TEN202309080002', 'gps_fee', NULL, NULL, '2023-09-23', 222.00, '2023-09-15', '123', '123123', NULL, 1, '长风一梦8888', '2023-09-08 08:57:41', NULL, NULL, NULL);
INSERT INTO `tms_expense` (`id`, `expense_no`, `expense_item`, `truck_id`, `plate_no`, `expiry_date`, `amount`, `handling_date`, `agent_name`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1739952440613646337, 'TEN202312270001', 'strong_insurance', NULL, NULL, '2023-12-27', 123.00, '2023-12-27', '123', '231231', b'1', 1, '长风一梦8888', '2023-12-27 18:12:43', 1, '长风一梦8888', '2023-12-27 18:15:44');
INSERT INTO `tms_expense` (`id`, `expense_no`, `expense_item`, `truck_id`, `plate_no`, `expiry_date`, `amount`, `handling_date`, `agent_name`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1739953184926445570, 'TEN202312270002', 'commercial_insurance', 1739846242807664641, NULL, '2023-12-27', 213.00, '2023-12-27', '唐亚峰', '123123', b'0', 1, '长风一梦8888', '2023-12-27 18:15:41', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tms_fleet
-- ----------------------------
DROP TABLE IF EXISTS `tms_fleet`;
CREATE TABLE `tms_fleet` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '车辆ID',
                             `leader_real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '队长姓名',
                             `leader_mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '队长手机号',
                             `leader_user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '队长用户ID',
                             `fleet_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车队名称',
                             `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                             `enabled` bit(1) DEFAULT b'1' COMMENT 'true = 启用/ false = 禁用',
                             `deleted` bit(1) DEFAULT b'0',
                             `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                             `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `last_modify_by` bigint DEFAULT NULL COMMENT '修改人ID',
                             `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                             `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1739849003846684674 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='运输车队';

-- ----------------------------
-- Records of tms_fleet
-- ----------------------------
BEGIN;
INSERT INTO `tms_fleet` (`id`, `leader_real_name`, `leader_mobile`, `leader_user_id`, `fleet_name`, `remark`, `enabled`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1699703444549881857, '唐队长', '13002171912', '123', '车队A', '3123213', b'1', b'0', 1, '长风一梦8888', '2023-09-07 08:37:35', 1, '长风一梦0000', '2023-12-27 11:25:08');
INSERT INTO `tms_fleet` (`id`, `leader_real_name`, `leader_mobile`, `leader_user_id`, `fleet_name`, `remark`, `enabled`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1739849003846684673, '12321', '13002171912', NULL, '车队A', '123123', b'1', b'0', 1, '长风一梦0000', '2023-12-27 11:21:42', 1, '长风一梦0000', '2023-12-27 11:25:05');
COMMIT;

-- ----------------------------
-- Table structure for tms_order
-- ----------------------------
DROP TABLE IF EXISTS `tms_order`;
CREATE TABLE `tms_order` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
                             `custom_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '客户单号',
                             `order_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台订单号;系统自动生成',
                             `waybill_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运输单号',
                             `project_id` bigint NOT NULL COMMENT '项目id(dic_project.id)',
                             `project_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '项目名称(dic_project.name)',
                             `transport_type` int NOT NULL DEFAULT '0' COMMENT '运输方式(dic_transport_type.id)',
                             `cargo_desc` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '货物信息',
                             `quantity` int NOT NULL DEFAULT '0' COMMENT '总数量',
                             `detail_quantity` int DEFAULT '0' COMMENT '订单明细数量',
                             `gross_weight` decimal(20,5) DEFAULT '0.00000' COMMENT '毛重',
                             `volume` decimal(20,5) DEFAULT '0.00000' COMMENT '体积',
                             `qty_unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单位',
                             `check_quantity` int DEFAULT '0' COMMENT '确认数量',
                             `check_weight` decimal(20,5) DEFAULT '0.00000' COMMENT '确认重量',
                             `check_volume` decimal(20,5) DEFAULT '0.00000' COMMENT '确认体积',
                             `declare_value` decimal(20,2) DEFAULT '0.00' COMMENT '声明价值',
                             `etd` datetime DEFAULT NULL COMMENT '预计发货时间',
                             `atd` datetime DEFAULT NULL COMMENT '实际发货时间',
                             `eta` datetime DEFAULT NULL COMMENT '预计到达时间',
                             `ata` datetime DEFAULT NULL COMMENT '实际到达时间',
                             `pick_time` datetime DEFAULT NULL COMMENT '提货时间',
                             `pod_time` datetime DEFAULT NULL COMMENT '签收时间',
                             `latest_event_type` int DEFAULT '0' COMMENT '最后一个节点类型(dic_event_type.id)',
                             `latest_event_id` bigint DEFAULT NULL COMMENT '最后一个节点id(tms_order_event.id)',
                             `order_status` int NOT NULL DEFAULT '0' COMMENT '订单状态:-10取消,0新建,10提货,20到达始发站,30离开始发站,40到达目的站,50离开目的站,60签收,70回单,80完成',
                             `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单备注',
                             `operator_id` bigint DEFAULT '0' COMMENT '操作人员ID(user.id)',
                             `transport_line_id` bigint DEFAULT '0' COMMENT '运输线路id(tms_transport_line.id)',
                             `has_epod` bit(1) DEFAULT b'0' COMMENT '是否有电子回单',
                             `source_type` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
                             `deleted` bit(1) DEFAULT b'0',
                             `create_by` bigint NOT NULL COMMENT '创建人ID',
                             `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                             `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                             `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `oo_system_no` (`custom_no`) USING BTREE,
                             KEY `oo_order_no` (`order_no`) USING BTREE,
                             KEY `oo_waybill_no` (`waybill_no`) USING BTREE,
                             KEY `oo_operator_id` (`operator_id`) USING BTREE,
                             KEY `oo_project_id` (`project_id`) USING BTREE,
                             KEY `oo_latest_event_type` (`latest_event_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1775347830765010946 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Records of tms_order
-- ----------------------------
BEGIN;
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (3, '', 'TEST001', 'WB001', 1, '万华化学危险品项目', 1, '货物1', 2, 100, 2000.10000, 3.30000, NULL, 0, 0.00000, 0.00000, 500.00, '2023-09-25 08:00:00', NULL, '2023-09-26 17:00:00', NULL, '2023-10-12 11:13:13', '2023-10-12 11:13:13', 0, 0, -10, '订单备注', 1, 0, b'0', 1, b'1', 1, '长风一梦8888', '2023-09-24 14:07:59', 1, '长风一梦8888', '2023-10-12 03:13:21');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (4, '', 'TEST001', 'WB001', 1, '万华化学危险品项目', 1, '货物1', 2, 100, 2000.10000, 3.30000, NULL, 0, 0.00000, 0.00000, 500.00, '2023-09-25 08:00:00', NULL, '2023-09-26 17:00:00', NULL, '2023-10-12 11:13:13', '2023-10-12 11:13:13', 0, 0, -10, '订单备注', 1, 0, b'0', 1, b'1', 1, '长风一梦8888', '2023-09-24 14:11:51', 1, '长风一梦8888', '2023-10-12 03:13:21');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (5, '', 'TEST001', 'WB001', 1, '万华化学危险品项目', 1, '货物1', 2, 100, 2000.10000, 3.30000, NULL, 0, 0.00000, 0.00000, 500.00, '2023-09-25 08:00:00', NULL, '2023-09-26 17:00:00', NULL, '2023-10-12 11:13:13', '2023-10-12 11:13:13', 0, 0, -10, '订单备注', 1, 0, b'0', 1, b'1', 1, '长风一梦8888', '2023-09-24 14:15:38', 1, '长风一梦8888', '2023-10-12 03:13:21');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705951547298496513, '', 'TEST001', 'WB001', 1, '万华化学危险品项目', 1, '货物1', 2, 100, 2000.10000, 3.30000, NULL, 0, 0.00000, 0.00000, 500.00, '2023-09-25 08:00:00', NULL, '2023-09-26 17:00:00', NULL, '2023-10-12 11:13:13', '2023-10-12 11:13:13', 0, 0, -10, '订单备注', 1, 0, b'0', 1, b'1', 1, '长风一梦8888', '2023-09-24 14:25:19', 1, '长风一梦8888', '2023-10-12 03:13:21');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705953334864699393, 'TON202309240001', 'TEST008', 'WB008', 1, '万华化学危险品项目', 1, '货物1', 2, 100, 2000.10000, 3.30000, NULL, 0, 0.00000, 0.00000, 500.00, '2023-09-25 08:00:00', NULL, '2023-09-26 17:00:00', NULL, '2023-10-12 11:13:13', '2023-10-12 11:13:13', 0, 0, 0, '订单备注', 0, 0, b'0', 1, b'1', 1, '长风一梦8888', '2023-09-24 14:32:25', 1, '长风一梦8888', '2023-10-13 16:01:52');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705956979316416513, 'TON202309240002', 'TEST001', 'WB001', 1, '万华化学危险品项目', 1, '货物1', 2, 100, 2000.10000, 3.30000, NULL, 0, 0.00000, 0.00000, 500.00, '2023-09-25 08:00:00', NULL, '2023-09-26 17:00:00', NULL, '2023-10-12 11:13:13', '2023-10-12 11:13:13', 0, 0, 20, '订单备注', 1, 0, b'0', 1, b'1', 1, '长风一梦8888', '2023-09-24 14:46:54', 1, '长风一梦8888', '2023-10-13 16:01:57');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707711488627687426, 'TON202309290001', 'TEST002', 'WB002', 1, '万华化学危险品项目', 1, '货物1', 2, 100, 400.10000, 3.30000, '2', 0, 0.00000, 0.00000, 500.00, '2023-09-25 08:00:00', NULL, '2023-09-26 17:00:00', NULL, '2023-10-12 11:13:13', '2023-10-12 11:13:13', 0, 0, 10, '订单备注', 1, 0, b'0', 1, b'1', 1, '长风一梦8888', '2023-09-29 10:58:41', 1, '长风一梦8888', '2024-04-03 10:20:06');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707711819205951489, 'TON202309290002', 'TEST002', 'WB002', 1, '万华化学危险品项目', 1, '货物1', 2, 100, 2000.10000, 3.30000, '3', 0, 0.00000, 0.00000, 500.00, '2023-09-25 08:00:00', NULL, '2023-09-26 17:00:00', NULL, '2023-10-12 11:13:13', '2023-10-12 11:13:13', 0, 0, 0, '订单备注', 1, 0, b'0', 1, b'1', 1, '长风一梦8888', '2023-09-29 10:59:59', 1, '长风一梦8888', '2024-04-03 10:20:07');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712333510775152642, 'TON202310120001', '12', '12', 1, NULL, 2, '213', 0, 0, 0.00000, 0.00000, NULL, 0, 0.00000, 0.00000, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, NULL, 1, 0, b'0', 0, b'1', 1, '长风一梦8888', '2023-10-12 13:04:57', 1, '长风一梦8888', '2023-10-12 13:05:10');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712378494794596353, 'KH202310130001', 'TON202310120002', 'YD202310130001', 1, NULL, 2, '危险化工品', 100, 0, 300.00000, 400.00000, '1', 0, 0.00000, 0.00000, 10000.00, '2023-10-13 17:37:49', NULL, '2023-10-13 17:37:47', NULL, NULL, NULL, 0, NULL, 0, NULL, 0, 0, b'0', 0, b'0', 1, '长风一梦8888', '2023-10-12 16:03:42', 1, '长风一梦8888', '2023-12-28 09:44:55');
INSERT INTO `tms_order` (`id`, `custom_no`, `order_no`, `waybill_no`, `project_id`, `project_name`, `transport_type`, `cargo_desc`, `quantity`, `detail_quantity`, `gross_weight`, `volume`, `qty_unit`, `check_quantity`, `check_weight`, `check_volume`, `declare_value`, `etd`, `atd`, `eta`, `ata`, `pick_time`, `pod_time`, `latest_event_type`, `latest_event_id`, `order_status`, `remark`, `operator_id`, `transport_line_id`, `has_epod`, `source_type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1775347830765010945, 'KH20240403000001', 'TON202404030001', 'YD20240403000001', 1, NULL, 1, '213123', 13, 0, 123.00000, 21.00000, '2', 0, 0.00000, 0.00000, 213.00, '2024-04-03 10:20:49', NULL, '2024-04-19 10:20:50', NULL, NULL, NULL, 0, NULL, 0, NULL, 0, 0, b'0', 0, b'0', 1, '长风一梦8888', '2024-04-03 10:21:22', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tms_order_address
-- ----------------------------
DROP TABLE IF EXISTS `tms_order_address`;
CREATE TABLE `tms_order_address` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                     `order_id` bigint NOT NULL COMMENT '订单ID(tms_order.id)',
                                     `company` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司/商店/站点名称',
                                     `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人姓名',
                                     `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '固定电话',
                                     `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机',
                                     `province_id` int DEFAULT NULL COMMENT '省份',
                                     `province_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省份',
                                     `city_id` int DEFAULT NULL COMMENT '市',
                                     `city_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
                                     `district_id` int DEFAULT NULL COMMENT '区县',
                                     `district_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区县',
                                     `address` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '详细地址',
                                     `warehouse` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '幢/码头/库位',
                                     `position` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '幢号/码头号/库位号',
                                     `sys_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地图解析的编码',
                                     `sys_province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '地图解析的省份',
                                     `sys_city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '地图解析的城市',
                                     `sys_district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '地图解析的区县',
                                     `longitude` decimal(20,6) DEFAULT '0.000000' COMMENT '地理经度',
                                     `latitude` decimal(20,6) DEFAULT '0.000000' COMMENT '地理纬度',
                                     `type` int DEFAULT '0' COMMENT '地址类型(0=发;1=收)',
                                     `deleted` bit(1) DEFAULT b'0',
                                     `create_by` bigint NOT NULL COMMENT '创建人ID',
                                     `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                     `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                     `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     KEY `oc_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1775347830794371074 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单发货人表';

-- ----------------------------
-- Records of tms_order_address
-- ----------------------------
BEGIN;
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712753783840186369, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 16:54:58', NULL, NULL, '2023-10-13 08:55:47');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712753783840186370, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 16:54:58', NULL, NULL, '2023-10-13 08:55:47');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712753989956673538, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 16:55:47', NULL, NULL, '2023-10-13 08:59:51');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712753989956673539, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 16:55:47', NULL, NULL, '2023-10-13 08:59:51');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712755015610802178, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 16:59:52', NULL, NULL, '2023-10-13 09:01:16');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712755015610802179, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 16:59:52', NULL, NULL, '2023-10-13 09:01:16');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712755370721550337, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:01:16', NULL, NULL, '2023-10-13 09:01:54');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712755370721550338, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:01:16', NULL, NULL, '2023-10-13 09:01:54');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712755531585691650, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:01:55', NULL, NULL, '2023-10-13 09:03:31');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712755531585691651, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:01:55', NULL, NULL, '2023-10-13 09:03:31');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712755936084369409, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 150000, NULL, 150400, NULL, 150404, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:03:31', NULL, NULL, '2023-10-13 09:05:42');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712755936084369410, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:03:31', NULL, NULL, '2023-10-13 09:05:42');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712756487924752385, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 150000, NULL, 150400, NULL, 150404, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:05:43', NULL, NULL, '2023-10-13 09:07:35');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712756487933140994, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:05:43', NULL, NULL, '2023-10-13 09:07:35');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712756960052387842, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:07:35', NULL, NULL, '2023-10-13 09:08:22');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712756960052387843, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:07:35', NULL, NULL, '2023-10-13 09:08:22');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712757156442284033, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 150000, NULL, 150300, NULL, 150303, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:08:22', NULL, NULL, '2023-10-13 09:09:53');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712757156442284034, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:08:22', NULL, NULL, '2023-10-13 09:09:53');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712757537310253058, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:09:53', NULL, NULL, '2023-10-13 09:15:39');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712757537310253059, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:09:53', NULL, NULL, '2023-10-13 09:15:39');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712758988443029505, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:15:39', NULL, NULL, '2023-10-13 09:15:53');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712758988443029506, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:15:39', NULL, NULL, '2023-10-13 09:15:53');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712759051051405314, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:15:54', NULL, NULL, '2023-10-13 09:33:18');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712759051051405315, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', NULL, NULL, NULL, NULL, NULL, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:15:54', NULL, NULL, '2023-10-13 09:33:18');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712763433910341634, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:33:19', NULL, NULL, '2023-10-13 09:36:04');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712763433910341635, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:33:19', NULL, NULL, '2023-10-13 09:36:04');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712764130378715137, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:36:05', NULL, NULL, '2023-10-13 09:37:52');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712764130378715138, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:36:05', NULL, NULL, '2023-10-13 09:37:52');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712764579727085570, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-13 17:37:52', NULL, NULL, '2023-10-14 02:15:30');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1712764579727085571, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-13 17:37:52', NULL, NULL, '2023-10-14 02:15:30');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1713015644846305282, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-14 10:15:31', NULL, NULL, '2023-10-14 02:19:13');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1713015644846305283, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-14 10:15:31', NULL, NULL, '2023-10-14 02:19:13');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1713016579093966850, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-14 10:19:13', NULL, NULL, '2023-10-14 02:34:46');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1713016579093966851, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-14 10:19:13', NULL, NULL, '2023-10-14 02:34:46');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1713020493210099714, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-14 10:34:47', NULL, NULL, '2023-10-14 02:40:18');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1713020493210099715, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-14 10:34:47', NULL, NULL, '2023-10-14 02:40:18');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1713021884007776257, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'1', 1, '长风一梦8888', '2023-10-14 10:40:18', NULL, NULL, '2023-12-28 01:44:55');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1713021884007776258, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-10-14 10:40:18', NULL, NULL, '2023-12-28 01:44:55');
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740186189062299650, 1707711488627687426, NULL, '3213213', '12321', '123123', 130000, NULL, 130100, NULL, 130102, NULL, '21321', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'0', 1, '长风一梦8888', '2023-12-28 09:41:33', NULL, NULL, NULL);
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740186189062299651, 1707711488627687426, NULL, '213213', '123', '123213', 130000, NULL, 130200, NULL, 130204, NULL, '123123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'0', 1, '长风一梦8888', '2023-12-28 09:41:33', NULL, NULL, NULL);
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740187006519566337, 1707711819205951489, NULL, '213', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'0', 1, '长风一梦8888', '2023-12-28 09:44:48', NULL, NULL, NULL);
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740187006519566338, 1707711819205951489, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'0', 1, '长风一梦8888', '2023-12-28 09:44:48', NULL, NULL, NULL);
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740187036848578561, 1712378494794596353, NULL, '唐亚峰', '0746-8585555', '13002170000', 110000, NULL, 110100, NULL, 110101, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'0', 1, '长风一梦8888', '2023-12-28 09:44:55', NULL, NULL, NULL);
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740187036848578562, 1712378494794596353, NULL, '唐亚峰', '12213', '13002170000', 120000, NULL, 120100, NULL, 120102, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'0', 1, '长风一梦8888', '2023-12-28 09:44:55', NULL, NULL, NULL);
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1775347830790176769, 1775347830765010945, NULL, '万华', '', NULL, 120000, NULL, 120100, NULL, 120102, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, b'0', 1, '长风一梦8888', '2024-04-03 10:21:22', NULL, NULL, NULL);
INSERT INTO `tms_order_address` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `type`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1775347830794371073, 1775347830765010945, NULL, '唐亚峰', NULL, NULL, 140000, NULL, 140300, NULL, 140303, NULL, '42-52 Export Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'0', 1, '长风一梦8888', '2024-04-03 10:21:22', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tms_order_consignee
-- ----------------------------
DROP TABLE IF EXISTS `tms_order_consignee`;
CREATE TABLE `tms_order_consignee` (
                                       `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                       `order_id` bigint NOT NULL COMMENT '订单ID(tms_order.id)',
                                       `company` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司/商店/站点名称',
                                       `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人姓名',
                                       `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '固定电话',
                                       `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机',
                                       `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省份',
                                       `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '城市',
                                       `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区县',
                                       `street` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '街道',
                                       `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '地址',
                                       `warehouse` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '幢/码头/库位',
                                       `position` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '幢号/码头号/库位号',
                                       `sys_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地图解析的编码',
                                       `sys_province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '地图解析的省份',
                                       `sys_city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '地图解析的城市',
                                       `sys_district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '地图解析的区县',
                                       `longitude` decimal(20,6) DEFAULT '0.000000' COMMENT '地理经度',
                                       `latitude` decimal(20,6) DEFAULT '0.000000' COMMENT '地理纬度',
                                       `source_id` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
                                       `locked` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
                                       `deleted` bit(1) DEFAULT b'0',
                                       `create_by` bigint NOT NULL COMMENT '创建人ID',
                                       `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                       `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                       `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                       PRIMARY KEY (`id`) USING BTREE,
                                       KEY `oc_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1707711819457609730 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单收货人表';

-- ----------------------------
-- Records of tms_order_consignee
-- ----------------------------
BEGIN;
INSERT INTO `tms_order_consignee` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province`, `city`, `district`, `street`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `source_id`, `locked`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705947188477091841, 0, '收货公司', '收货人', '65280001', '13636633667', '江苏省', '镇江市', '京口区', '', '学府路301号', '', '', '321102', '', '', '', 0.000000, 0.000000, 1, b'1', b'0', 1, '长风一梦8888', '2023-09-24 22:07:59', NULL, NULL, '2023-09-24 14:09:15');
INSERT INTO `tms_order_consignee` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province`, `city`, `district`, `street`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `source_id`, `locked`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705948337854799874, 0, '收货公司', '收货人', '65280001', '13636633667', '江苏省', '镇江市', '京口区', '', '学府路301号', '', '', '321102', '', '', '', 0.000000, 0.000000, 1, b'0', b'0', 1, '长风一梦8888', '2023-09-24 22:12:33', NULL, NULL, NULL);
INSERT INTO `tms_order_consignee` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province`, `city`, `district`, `street`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `source_id`, `locked`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705949153827307521, 0, '收货公司', '收货人', '65280001', '13636633667', '江苏省', '镇江市', '京口区', '', '学府路301号', '', '', '321102', '', '', '', 0.000000, 0.000000, 1, b'0', b'0', 1, '长风一梦8888', '2023-09-24 22:15:48', NULL, NULL, NULL);
INSERT INTO `tms_order_consignee` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province`, `city`, `district`, `street`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `source_id`, `locked`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705951621386682370, 1705951547298496513, '收货公司', '收货人', '65280001', '13636633667', '江苏省', '镇江市', '京口区', '', '学府路301号', '', '', '321102', '', '', '', 0.000000, 0.000000, 1, b'0', b'0', 1, '长风一梦8888', '2023-09-24 22:25:36', NULL, NULL, NULL);
INSERT INTO `tms_order_consignee` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province`, `city`, `district`, `street`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `source_id`, `locked`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705953335443513345, 1705953334864699393, '收货公司', '收货人', '65280001', '13636633667', '江苏省', '镇江市', '京口区', '', '学府路301号', '', '', '321102', '', '', '', 0.000000, 0.000000, 1, b'1', b'0', 1, '长风一梦8888', '2023-09-24 22:32:25', 1, '长风一梦8888', '2023-09-26 17:33:51');
INSERT INTO `tms_order_consignee` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province`, `city`, `district`, `street`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `source_id`, `locked`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705956979580657666, 1705956979316416513, '收货公司', '收货人', '65280001', '13636633667', '江苏省', '镇江市', '京口区', '', '学府路301号', '', '', '321102', '', '', '', 0.000000, 0.000000, 1, b'0', b'0', 1, '长风一梦8888', '2023-09-24 22:46:54', NULL, NULL, NULL);
INSERT INTO `tms_order_consignee` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province`, `city`, `district`, `street`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `source_id`, `locked`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1706723769730465793, 1705953334864699393, '收货公司', '收货人', '65280001', '13636633667', '江苏省', '镇江市', '京口区', '', '学府路301号', '', '', '321102', '', '', '', 0.000000, 0.000000, 1, b'0', b'0', 1, '长风一梦8888', '2023-09-27 01:33:51', NULL, NULL, NULL);
INSERT INTO `tms_order_consignee` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province`, `city`, `district`, `street`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `source_id`, `locked`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707711489172946945, 1707711488627687426, '收货公司', '收货人', '65280001', '13636633667', '江苏省', '南京市', '雨花台区', '', '学府路301号', '', '', '321102', '', '', '', 0.000000, 0.000000, 1, b'0', b'0', 1, '长风一梦8888', '2023-09-29 18:58:41', NULL, NULL, NULL);
INSERT INTO `tms_order_consignee` (`id`, `order_id`, `company`, `name`, `phone`, `mobile`, `province`, `city`, `district`, `street`, `address`, `warehouse`, `position`, `sys_code`, `sys_province`, `sys_city`, `sys_district`, `longitude`, `latitude`, `source_id`, `locked`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707711819457609729, 1707711819205951489, '收货公司', '收货人', '65280001', '13636633667', '上海市', '上海城区', '徐汇区', '', '学府路301号', '', '', '321102', '', '', '', 0.000000, 0.000000, 1, b'0', b'0', 1, '长风一梦8888', '2023-09-29 19:00:00', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tms_order_event
-- ----------------------------
DROP TABLE IF EXISTS `tms_order_event`;
CREATE TABLE `tms_order_event` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                   `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID(tms_order.id)',
                                   `system_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '平台单号(tms_order.system_number)',
                                   `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省份',
                                   `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '城市',
                                   `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区县',
                                   `longitude` decimal(20,6) DEFAULT '0.000000' COMMENT '经度',
                                   `latitude` decimal(20,6) DEFAULT '0.000000' COMMENT '纬度',
                                   `record_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
                                   `event_type` int NOT NULL DEFAULT '0' COMMENT '节点状态ID(dic_event_type.id)',
                                   `operator_id` bigint DEFAULT '0' COMMENT '操作人员ID(user.id)',
                                   `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '节点备注',
                                   `quantity` int NOT NULL DEFAULT '0' COMMENT '总数量',
                                   `gross_weight` decimal(20,5) DEFAULT '0.00000' COMMENT '毛重',
                                   `volume` decimal(20,5) DEFAULT '0.00000' COMMENT '体积',
                                   `sku_id` bigint DEFAULT NULL COMMENT '订单货物ID(tms_order_sku.id)',
                                   `station_id` bigint DEFAULT NULL COMMENT '站点ID(tms_station.id)',
                                   `task_id` bigint DEFAULT NULL COMMENT '任务ID(tms_task.id)',
                                   `driver_id` bigint DEFAULT NULL COMMENT '司机ID(tms_driver.id)',
                                   `driver_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '司机姓名(tms_driver.name)',
                                   `driver_mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '司机手机号(tms_driver.mobile)',
                                   `truck_id` bigint DEFAULT NULL COMMENT '车辆id(tms_truck.id)',
                                   `plate_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车牌号(tms_truck.plate_number)',
                                   `source_id` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
                                   `locked` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
                                   `create_by` bigint NOT NULL COMMENT '创建人ID',
                                   `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                   `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                   `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   KEY `event_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1707640174810255363 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单节点表';

-- ----------------------------
-- Records of tms_order_event
-- ----------------------------
BEGIN;
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705947188804247554, 0, '', NULL, NULL, NULL, 0.000000, 0.000000, '2023-09-24 14:07:59', 0, 1, NULL, 2, 2000.10000, 3.30000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-09-24 14:07:59', NULL, NULL, '2023-09-24 14:10:26');
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705948338173566977, 0, '', NULL, NULL, NULL, 0.000000, 0.000000, '2023-09-24 14:12:33', 0, 1, NULL, 2, 2000.10000, 3.30000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-09-24 14:12:33', NULL, NULL, '2023-09-24 14:23:25');
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705949154276098049, 0, '', NULL, NULL, NULL, 0.000000, 0.000000, '2023-09-24 14:15:48', 0, 1, NULL, 2, 2000.10000, 3.30000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-09-24 14:15:48', NULL, NULL, '2023-09-24 14:23:25');
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705951621583814658, 1705951547298496513, '', NULL, NULL, NULL, 0.000000, 0.000000, '2023-09-24 14:25:36', 0, 1, NULL, 2, 2000.10000, 3.30000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-09-24 14:25:36', NULL, NULL, '2023-09-24 15:07:58');
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705953335770669057, 1705953334864699393, 'TON202309240001', NULL, NULL, NULL, 0.000000, 0.000000, '2023-09-24 14:32:25', 0, 1, NULL, 2, 2000.10000, 3.30000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-09-24 14:32:25', NULL, NULL, '2023-09-24 15:07:58');
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705956979781984258, 1705956979316416513, 'TON202309240002', NULL, NULL, NULL, 0.000000, 0.000000, '2023-09-24 14:46:54', 0, 1, NULL, 2, 2000.10000, 3.30000, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, b'1', 1, '长风一梦8888', '2023-09-24 14:46:54', NULL, NULL, '2023-09-24 15:07:58');
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1706725449884852226, 1705953334864699393, 'TON202309240001', '北京市', '北京城区', '东城区', 0.000000, 0.000000, '2023-09-27 01:38:00', 10, 1, '节点备注', 1, 2.20000, 3.30000, 0, 0, 0, 0, '', '', 0, '', 0, b'0', 1, '长风一梦8888', '2023-09-26 17:40:31', 1, '长风一梦8888', '2023-09-26 17:57:37');
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707637996729229313, 1705953334864699393, 'TON202309240001', '上海市', '上海城区', '徐汇区', 0.000000, 0.000000, '2023-09-27 01:38:00', 101, 1, '节点备注', 1, 2.20000, 3.30000, 0, 0, 0, 0, '', '', 0, '', 0, b'0', 1, '长风一梦8888', '2023-09-29 06:06:39', NULL, NULL, NULL);
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707638583487193090, 1705953334864699393, 'TON202309240001', '上海市', '上海城区', '徐汇区', 0.000000, 0.000000, '2023-09-27 01:38:00', 101, 1, '节点备注', 1, 2.20000, 3.30000, 0, 0, 0, 0, '', '', 0, '', 0, b'0', 1, '长风一梦8888', '2023-09-29 06:08:59', NULL, NULL, NULL);
INSERT INTO `tms_order_event` (`id`, `order_id`, `system_no`, `province`, `city`, `district`, `longitude`, `latitude`, `record_time`, `event_type`, `operator_id`, `remark`, `quantity`, `gross_weight`, `volume`, `sku_id`, `station_id`, `task_id`, `driver_id`, `driver_name`, `driver_mobile`, `truck_id`, `plate_no`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707640174810255362, 1705953334864699393, 'TON202309240001', '上海市', '上海城区', '徐汇区', 0.000000, 0.000000, '2023-09-27 01:38:00', 101, 0, '节点备注', 1, 2.20000, 3.30000, 0, 0, 0, 0, '', '', 0, '', 0, b'0', 1, '长风一梦8888', '2023-09-29 06:15:18', 1, '长风一梦8888', '2023-09-29 06:17:01');
COMMIT;

-- ----------------------------
-- Table structure for tms_order_event_file
-- ----------------------------
DROP TABLE IF EXISTS `tms_order_event_file`;
CREATE TABLE `tms_order_event_file` (
                                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                        `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID(tms_order.id)',
                                        `event_id` bigint NOT NULL DEFAULT '0' COMMENT '订单节点ID(tms_order_event.id)',
                                        `file_id` bigint NOT NULL DEFAULT '0' COMMENT '订单节点ID(tms_order_event.id)',
                                        `source_id` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
                                        `locked` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
                                        `create_by` bigint NOT NULL COMMENT '创建人ID',
                                        `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                        `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                        `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                        PRIMARY KEY (`id`) USING BTREE,
                                        KEY `event_order_id` (`order_id`) USING BTREE,
                                        KEY `event_event_id` (`event_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1707641509819514883 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单节点文件表';

-- ----------------------------
-- Records of tms_order_event_file
-- ----------------------------
BEGIN;
INSERT INTO `tms_order_event_file` (`id`, `order_id`, `event_id`, `file_id`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707640175032553474, 1705953334864699393, 1707640174810255362, 55, 0, b'1', 1, '长风一梦8888', '2023-09-29 14:15:19', 1, '长风一梦8888', '2023-09-29 06:20:36');
INSERT INTO `tms_order_event_file` (`id`, `order_id`, `event_id`, `file_id`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707640608450957313, 1705953334864699393, 1707640174810255362, 66, 0, b'1', 1, '长风一梦8888', '2023-09-29 14:17:02', 1, '长风一梦8888', '2023-09-29 06:20:36');
INSERT INTO `tms_order_event_file` (`id`, `order_id`, `event_id`, `file_id`, `source_id`, `locked`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707641509819514882, 1705953334864699393, 1707640174810255362, 66, 0, b'0', 1, '长风一梦8888', '2023-09-29 14:20:37', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tms_order_file
-- ----------------------------
DROP TABLE IF EXISTS `tms_order_file`;
CREATE TABLE `tms_order_file` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID(tms_order.id)',
                                  `file_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID(tms_order.id)',
                                  `source_id` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
                                  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
                                  `create_by` bigint NOT NULL COMMENT '创建人ID',
                                  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                  `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                  `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  KEY `event_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单文件表';

-- ----------------------------
-- Records of tms_order_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tms_order_sku
-- ----------------------------
DROP TABLE IF EXISTS `tms_order_sku`;
CREATE TABLE `tms_order_sku` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                 `order_id` bigint NOT NULL COMMENT '订单ID(tms_order.id)',
                                 `sku_master_id` bigint DEFAULT NULL COMMENT '货物规格id(dic_sku_master.id)',
                                 `item_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '货号',
                                 `box_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '箱号',
                                 `cargo_desc` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '货物信息',
                                 `al` decimal(20,2) DEFAULT NULL COMMENT '长',
                                 `aw` decimal(20,2) DEFAULT NULL COMMENT '宽',
                                 `ah` decimal(20,2) DEFAULT NULL COMMENT '高',
                                 `quantity` int DEFAULT NULL COMMENT '数量',
                                 `weight` decimal(20,5) DEFAULT NULL COMMENT '毛重',
                                 `volume` decimal(20,5) DEFAULT NULL COMMENT '体积',
                                 `qty_unit` int DEFAULT NULL COMMENT '单位',
                                 `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '货物备注',
                                 `check_quantity` int DEFAULT '0' COMMENT '确认数量',
                                 `check_weight` decimal(20,5) DEFAULT '0.00000' COMMENT '确认重量',
                                 `check_volume` decimal(20,5) DEFAULT '0.00000' COMMENT '确认体积',
                                 `source_id` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
                                 `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
                                 `create_by` bigint NOT NULL COMMENT '创建人ID',
                                 `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                 `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                 `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 KEY `osku_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1740187006553120771 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单货物明细表';

-- ----------------------------
-- Records of tms_order_sku
-- ----------------------------
BEGIN;
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705947188607115265, 0, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'1', 1, '长风一梦8888', '2023-09-24 22:07:59', NULL, NULL, '2023-09-24 14:10:45');
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705947188607115266, 0, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'1', 1, '长风一梦8888', '2023-09-24 22:07:59', NULL, NULL, '2023-09-24 14:10:45');
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705948337976434689, 0, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-24 22:12:33', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705948337976434690, 0, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-24 22:12:33', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705949154020245505, 0, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-24 22:15:48', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705949154020245506, 0, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-24 22:15:48', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705951621516705793, 1705951547298496513, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-24 22:25:36', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705951621516705794, 1705951547298496513, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-24 22:25:36', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705953335573536770, 1705953334864699393, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'1', 1, '长风一梦8888', '2023-09-24 22:32:25', 1, '长风一梦8888', '2023-09-26 17:33:51');
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705953335636451330, 1705953334864699393, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'1', 1, '长风一梦8888', '2023-09-24 22:32:25', 1, '长风一梦8888', '2023-09-26 17:33:51');
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705956979647766530, 1705956979316416513, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-24 22:46:54', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1705956979714875393, 1705956979316416513, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-24 22:46:54', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1706723770061815810, 1705953334864699393, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-27 01:33:51', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1706723770061815811, 1705953334864699393, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-09-27 01:33:51', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707711489302970370, 1707711488627687426, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'1', 1, '长风一梦8888', '2023-09-29 18:58:41', NULL, NULL, '2023-12-28 01:41:33');
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707711489302970371, 1707711488627687426, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'1', 1, '长风一梦8888', '2023-09-29 18:58:41', NULL, NULL, '2023-12-28 01:41:33');
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707711819583438850, 1707711819205951489, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'1', 1, '长风一梦8888', '2023-09-29 19:00:00', NULL, NULL, '2023-12-28 01:44:48');
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1707711819583438851, 1707711819205951489, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'1', 1, '长风一梦8888', '2023-09-29 19:00:00', NULL, NULL, '2023-12-28 01:44:48');
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740186189100048385, 1707711488627687426, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-12-28 09:41:33', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740186189100048386, 1707711488627687426, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-12-28 09:41:33', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740187006553120769, 1707711819205951489, 2, 'ITEM002', 'BOX002', '测试货物2', 0.00, 0.00, 0.00, 1, 1000.10000, 1.70000, 0, '货物备注2', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-12-28 09:44:48', NULL, NULL, NULL);
INSERT INTO `tms_order_sku` (`id`, `order_id`, `sku_master_id`, `item_no`, `box_no`, `cargo_desc`, `al`, `aw`, `ah`, `quantity`, `weight`, `volume`, `qty_unit`, `remark`, `check_quantity`, `check_weight`, `check_volume`, `source_id`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1740187006553120770, 1707711819205951489, 1, 'ITEM001', 'BOX001', '测试货物1', 0.00, 0.00, 0.00, 1, 1000.00000, 1.60000, 0, '货物备注1', 0, 0.00000, 0.00000, 1, b'0', 1, '长风一梦8888', '2023-12-28 09:44:48', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tms_part_purchase
-- ----------------------------
DROP TABLE IF EXISTS `tms_part_purchase`;
CREATE TABLE `tms_part_purchase` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `purchase_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '采购编号',
                                     `part_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '零件名称',
                                     `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '型号',
                                     `quantity` int DEFAULT NULL COMMENT '数量',
                                     `quantity_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数量单位',
                                     `available_quantity` int DEFAULT NULL COMMENT '可用数量',
                                     `unit_price` int DEFAULT NULL COMMENT '单价',
                                     `subtotal_price` int DEFAULT NULL COMMENT '小计价格',
                                     `in_stock` tinyint(1) DEFAULT '0' COMMENT '是否入库',
                                     `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除(0=未删除;1=已删除)',
                                     `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                     `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '修改人ID',
                                     `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                     `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1739939803553181699 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='配件采购';

-- ----------------------------
-- Records of tms_part_purchase
-- ----------------------------
BEGIN;
INSERT INTO `tms_part_purchase` (`id`, `purchase_no`, `part_name`, `model`, `quantity`, `quantity_unit`, `available_quantity`, `unit_price`, `subtotal_price`, `in_stock`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1701108102744940545, 'PPN202309110001', '2', '3', 4, '5', 7, 6, NULL, 0, 1, 1, '长风一梦8888', '2023-09-11 05:39:11', 1, '长风一梦8888', '2023-12-27 17:22:52');
INSERT INTO `tms_part_purchase` (`id`, `purchase_no`, `part_name`, `model`, `quantity`, `quantity_unit`, `available_quantity`, `unit_price`, `subtotal_price`, `in_stock`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1739937682258767874, 'PPN202312270001', '21312', '21312', 2133, '123', 21312, 123, NULL, 0, 1, 1, '长风一梦8888', '2023-12-27 17:14:05', 1, '长风一梦8888', '2023-12-28 09:32:33');
INSERT INTO `tms_part_purchase` (`id`, `purchase_no`, `part_name`, `model`, `quantity`, `quantity_unit`, `available_quantity`, `unit_price`, `subtotal_price`, `in_stock`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1739939803553181698, 'PPN202312270002', '123', '123', 123, '21321', 123, 100000, 12300000, 0, 0, 1, '长风一梦8888', '2023-12-27 17:22:30', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tms_peccancy
-- ----------------------------
DROP TABLE IF EXISTS `tms_peccancy`;
CREATE TABLE `tms_peccancy` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `peccancy_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '违章编号',
                                `truck_id` bigint DEFAULT NULL COMMENT '卡车ID',
                                `plate_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车牌号',
                                `driver_id` bigint DEFAULT NULL COMMENT '驾驶员ID',
                                `driver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶员姓名',
                                `peccancy_item` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '违章项目',
                                `point_deduction` int DEFAULT NULL COMMENT '扣分',
                                `category_city_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '违章地点',
                                `category_city_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '违章地点ID',
                                `fine_amount` decimal(10,2) NOT NULL COMMENT '罚款金额',
                                `company_payable` decimal(10,2) DEFAULT NULL COMMENT '公司应付',
                                `driver_payable` decimal(10,2) DEFAULT NULL COMMENT '司机应付',
                                `peccancy_date` date DEFAULT NULL COMMENT '违章日期',
                                `expense_date` date DEFAULT NULL COMMENT '费用日期',
                                `agent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '经办人姓名',
                                `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                `deleted` bit(1) DEFAULT b'0',
                                `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `last_modify_by` bigint DEFAULT NULL COMMENT '修改人ID',
                                `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1739952107560742914 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆违章表';

-- ----------------------------
-- Records of tms_peccancy
-- ----------------------------
BEGIN;
INSERT INTO `tms_peccancy` (`id`, `peccancy_no`, `truck_id`, `plate_no`, `driver_id`, `driver_name`, `peccancy_item`, `point_deduction`, `category_city_name`, `category_city_id`, `fine_amount`, `company_payable`, `driver_payable`, `peccancy_date`, `expense_date`, `agent_name`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1700854734923403265, 'abc', 1739846242807664641, '111', 122, NULL, '123', 123, NULL, '123', 213.00, 123.00, 123.00, '2023-09-06', '2023-09-23', '123', '213', b'1', 1, '长风一梦8888', '2023-09-10 12:52:24', 1, '长风一梦8888', '2023-12-27 17:49:38');
INSERT INTO `tms_peccancy` (`id`, `peccancy_no`, `truck_id`, `plate_no`, `driver_id`, `driver_name`, `peccancy_item`, `point_deduction`, `category_city_name`, `category_city_id`, `fine_amount`, `company_payable`, `driver_payable`, `peccancy_date`, `expense_date`, `agent_name`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1739944524338814977, 'WZ202312270001', 1739846242807664641, '沪A88888', 1, NULL, '碰撞了', 3, NULL, '上还闵行区', 100.00, 50.00, 50.00, '2023-12-27', '2023-12-27', '不知道哦', '傻逼货拉拉', b'0', 1, '长风一梦8888', '2023-12-27 17:41:16', 1, '长风一梦8888', '2023-12-27 18:06:54');
INSERT INTO `tms_peccancy` (`id`, `peccancy_no`, `truck_id`, `plate_no`, `driver_id`, `driver_name`, `peccancy_item`, `point_deduction`, `category_city_name`, `category_city_id`, `fine_amount`, `company_payable`, `driver_payable`, `peccancy_date`, `expense_date`, `agent_name`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1739952107560742913, 'WZ202312270002', 1739846242807664641, '沪A88888', 1, NULL, '12312', 3123123, NULL, '12312', 123123.00, 12312.00, 213.00, '2023-12-27', '2023-12-27', '33', '123123', b'0', 1, '长风一梦8888', '2023-12-27 18:11:24', 1, '长风一梦8888', '2023-12-27 18:11:41');
COMMIT;

-- ----------------------------
-- Table structure for tms_task
-- ----------------------------
DROP TABLE IF EXISTS `tms_task`;
CREATE TABLE `tms_task` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
                            `task_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '调度计划名称',
                            `task_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '调度号',
                            `project_id` bigint NOT NULL COMMENT '项目id(dic_project.id)',
                            `transport_type` int NOT NULL DEFAULT '0' COMMENT '运输方式(dic_transport_type.id)',
                            `truck_type` int DEFAULT NULL COMMENT '车辆类型ID(dic_truck_type.id)',
                            `etd` datetime DEFAULT NULL COMMENT '预计提货时间',
                            `atd` datetime DEFAULT NULL COMMENT '实际提货时间',
                            `eta` datetime DEFAULT NULL COMMENT '预计到达时间',
                            `ata` datetime DEFAULT NULL COMMENT '实际到达时间',
                            `station_from` bigint DEFAULT NULL COMMENT '始发站点ID',
                            `station_to` bigint DEFAULT NULL COMMENT '目的站点ID',
                            `driver_id` bigint DEFAULT NULL COMMENT '司机ID(tms_driver.id)',
                            `driver_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '司机姓名(tms_driver.name)',
                            `driver_mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '司机手机号(tms_driver.mobile)',
                            `truck_id` bigint DEFAULT NULL COMMENT '车辆id(tms_truck.id)',
                            `plate_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车牌号(tms_truck.plate_number)',
                            `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务备注',
                            `task_status` int NOT NULL DEFAULT '0' COMMENT '订单状态:-10取消,0新建,10出发,20运输中,30到达,40完成',
                            `source_id` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
                            `locked` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
                            `create_by` bigint NOT NULL COMMENT '创建人ID',
                            `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                            `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                            `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            PRIMARY KEY (`id`) USING BTREE,
                            KEY `tt_task_no` (`task_no`) USING BTREE,
                            KEY `tt_project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='调度计划表';

-- ----------------------------
-- Records of tms_task
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tms_task_order
-- ----------------------------
DROP TABLE IF EXISTS `tms_task_order`;
CREATE TABLE `tms_task_order` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
                                  `task_id` bigint NOT NULL DEFAULT '0' COMMENT '调度计划ID(tms_task.id)',
                                  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID(tms_order.id)',
                                  `source_id` int DEFAULT '0' COMMENT '数据来源(dic_source.id)',
                                  `locked` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
                                  `create_by` bigint NOT NULL COMMENT '创建人ID',
                                  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                  `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                  `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  KEY `tos_task_id` (`task_id`) USING BTREE,
                                  KEY `tos_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='调度订单关联表';

-- ----------------------------
-- Records of tms_task_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tms_truck
-- ----------------------------
DROP TABLE IF EXISTS `tms_truck`;
CREATE TABLE `tms_truck` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '车辆ID',
                             `plate_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车牌号',
                             `is_external` tinyint(1) DEFAULT NULL COMMENT '是否外协',
                             `truck_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '卡车型号',
                             `truck_length` decimal(10,2) DEFAULT NULL COMMENT '卡车长度',
                             `truck_width` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '卡车宽度',
                             `energy_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '能源类型（租户字典 TMS_ENERGY_TYPE）',
                             `tires_count` int DEFAULT NULL COMMENT '轮胎数量',
                             `axles_count` int DEFAULT NULL COMMENT '轴数',
                             `file_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '档案编号',
                             `plate_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车牌颜色（租户字典 TMS_PLATE_COLOR）',
                             `usage_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '使用性质',
                             `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌',
                             `truck_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆识别代码',
                             `engine_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发动机号码',
                             `approved_passenger` int DEFAULT NULL COMMENT '核定载客人数',
                             `total_weight` decimal(10,2) DEFAULT NULL COMMENT '总质量',
                             `curb_weight` decimal(10,2) DEFAULT NULL COMMENT '整备质量',
                             `approved_weight` decimal(10,2) DEFAULT NULL COMMENT '核定载重',
                             `tow_weight` decimal(10,2) DEFAULT NULL COMMENT '牵引质量',
                             `scrap_date` datetime DEFAULT NULL COMMENT '报废日期',
                             `road_certificate_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '道路运输证号',
                             `road_certificate_due_date` date DEFAULT NULL COMMENT '道路运输证到期日期',
                             `owner_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所有人',
                             `owner_id_card_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所有人证件号',
                             `is_affiliation` tinyint(1) DEFAULT NULL COMMENT '是否隶属',
                             `subordination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挂靠单位',
                             `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                             `default_driver_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '默认司机ID',
                             `fleet_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车队ID',
                             `trailer_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挂车ID',
                             `file_driver_and_vehicle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶证照片ID',
                             `file_road_certificate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶证照片ID',
                             `file_other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '驾驶证照片ID',
                             `enabled` bit(1) DEFAULT b'1' COMMENT '启用状态',
                             `approval_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批状态',
                             `approval_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批用户ID',
                             `approval_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批用户名',
                             `approval_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批描述',
                             `deleted` bit(1) DEFAULT b'0',
                             `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                             `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `last_modify_by` bigint DEFAULT NULL COMMENT '修改人ID',
                             `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                             `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1739846242807664642 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆信息表';

-- ----------------------------
-- Records of tms_truck
-- ----------------------------
BEGIN;
INSERT INTO `tms_truck` (`id`, `plate_no`, `is_external`, `truck_model`, `truck_length`, `truck_width`, `energy_type`, `tires_count`, `axles_count`, `file_no`, `plate_color`, `usage_type`, `brand`, `truck_code`, `engine_code`, `approved_passenger`, `total_weight`, `curb_weight`, `approved_weight`, `tow_weight`, `scrap_date`, `road_certificate_code`, `road_certificate_due_date`, `owner_name`, `owner_id_card_no`, `is_affiliation`, `subordination`, `remark`, `default_driver_id`, `fleet_id`, `trailer_id`, `file_driver_and_vehicle`, `file_road_certificate`, `file_other`, `enabled`, `approval_status`, `approval_id`, `approval_name`, `approval_desc`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1739846242807664641, '沪A88888', 0, '12312', NULL, '213', '1', 123, 123123, '123123', 'BLUE', '12312', '小米', '213', '21312', 123, 3123.00, 312.00, 3122312.00, 3213.00, '2023-12-30 00:00:00', '21321', NULL, '123213', '123', 0, NULL, '213213', NULL, '1739849003846684673', NULL, NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL, b'0', 1, '长风一梦0000', '2023-12-27 11:10:44', 1, '长风一梦8888', '2023-12-27 09:07:56');
COMMIT;

-- ----------------------------
-- Table structure for wms_aisle
-- ----------------------------
DROP TABLE IF EXISTS `wms_aisle`;
CREATE TABLE `wms_aisle` (
                             `id` bigint NOT NULL COMMENT 'ID',
                             `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '巷道编号',
                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '巷道名称',
                             `status` tinyint NOT NULL COMMENT '是否启用',
                             `length` decimal(10,2) DEFAULT NULL COMMENT '长度',
                             `width` decimal(10,2) DEFAULT NULL COMMENT '宽度',
                             `height` decimal(10,2) DEFAULT NULL COMMENT '高度',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `create_by` bigint DEFAULT NULL COMMENT '创建人',
                             `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                             `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                             `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                             `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='巷道表';

-- ----------------------------
-- Records of wms_aisle
-- ----------------------------
BEGIN;
INSERT INTO `wms_aisle` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1803801637861460213, 'X004', '多层货架巷道', 1, 100.00, 200.00, 300.00, '2024-06-20 22:46:38', 1, '长风一梦8888', NULL, NULL, NULL, 0);
INSERT INTO `wms_aisle` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1803801637861460847, 'X003', '直通巷道', 1, 100.00, 200.00, 300.00, '2024-06-20 22:46:38', 1, '长风一梦8888', NULL, NULL, NULL, 0);
INSERT INTO `wms_aisle` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1803801637861461093, 'X002', '冷库巷道', 1, 100.00, 200.00, 300.00, '2024-06-20 22:46:38', 1, '长风一梦8888', NULL, NULL, NULL, 0);
INSERT INTO `wms_aisle` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1803801637861462018, 'X001', '自动化存储系统巷道', 1, 100.00, 200.00, 300.00, '2024-06-20 22:46:38', 1, '长风一梦8888', '2024-07-08 14:51:05', 1, '长风一梦8888', 0);
INSERT INTO `wms_aisle` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1829437051007893505, '123', '123', 1, 123.00, 123.00, 123.00, '2024-08-30 16:32:37', 1, '长风一梦8888', '2024-08-30 16:32:39', 1, '长风一梦8888', 1);
COMMIT;

-- ----------------------------
-- Table structure for wms_brand
-- ----------------------------
DROP TABLE IF EXISTS `wms_brand`;
CREATE TABLE `wms_brand` (
                             `id` bigint NOT NULL COMMENT '对象存储主键',
                             `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '品牌编号',
                             `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '品牌名称',
                             `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '品牌状态',
                             `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '品牌类型',
                             `country` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌所属国家',
                             `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌logo',
                             `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌官方网站URL',
                             `contact_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌联系人',
                             `contact_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人电话',
                             `owner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌所有者',
                             `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌公司',
                             `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人邮箱',
                             `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '品牌备注',
                             `province_id` bigint DEFAULT NULL COMMENT '省',
                             `province_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省名称',
                             `city_id` bigint DEFAULT NULL COMMENT '市',
                             `city_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市名称',
                             `district_id` bigint DEFAULT NULL COMMENT '区',
                             `district_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区名称',
                             `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '详细地址',
                             `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '品牌描述，详细介绍品牌信息',
                             `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                             `create_time` datetime NOT NULL COMMENT '创建时间',
                             `create_by` bigint DEFAULT NULL COMMENT '创建人',
                             `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                             `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                             `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='品牌表';

-- ----------------------------
-- Records of wms_brand
-- ----------------------------
BEGIN;
INSERT INTO `wms_brand` (`id`, `code`, `name`, `status`, `type`, `country`, `logo`, `website`, `contact_person`, `contact_phone`, `owner`, `company`, `email`, `remark`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `description`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (2, 'B00001', '英特尔', '启用', '电子产品', NULL, NULL, 'https://www.intel.cn/content/www/cn/zh/homepage.html', 'Veronica Lin', '(8621) 6116-5000', '鸡哥', '英特尔', '1231@163.com', '英特尔是半导体行业和计算创新领域的全球领先厂商，创始于1968年。2022年营收631亿美元', 110000, '北京市', 110100, '市辖区', 110101, '东城区', '上海市闵行区紫星路 880 号', '半导体行业和计算创新领域的全球领先厂商', 0, '2024-06-23 13:09:45', 1, '长风一梦8888', '2024-09-05 12:39:16', 1, '长风一梦8888');
INSERT INTO `wms_brand` (`id`, `code`, `name`, `status`, `type`, `country`, `logo`, `website`, `contact_person`, `contact_phone`, `owner`, `company`, `email`, `remark`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `description`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1831552758667530241, 'BR202409050001', '123', '0', '123', NULL, NULL, '123', '测试3333', '123', '123', '123', '213', NULL, 110000, '北京市', 110100, '市辖区', 110102, '西城区', '123', '123', 1, '2024-09-05 12:39:41', 1, '长风一梦8888', '2024-09-05 12:39:43', 1, '长风一梦8888');
COMMIT;

-- ----------------------------
-- Table structure for wms_carrier
-- ----------------------------
DROP TABLE IF EXISTS `wms_carrier`;
CREATE TABLE `wms_carrier` (
                               `id` bigint NOT NULL COMMENT 'ID',
                               `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '承运商编码',
                               `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '承运商名称',
                               `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '承运商类型：外贸、内贸',
                               `service_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '服务类型：陆运、海运、空运',
                               `effective_date` date DEFAULT NULL COMMENT '合同起始日期',
                               `expiration_date` date DEFAULT NULL COMMENT '合同结束日期',
                               `payment_term` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '付款条款',
                               `settlement_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '结算方式',
                               `contact_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
                               `postcode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮编',
                               `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
                               `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电子邮箱',
                               `fax` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '传真',
                               `province_id` bigint DEFAULT NULL COMMENT '省ID',
                               `province_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省',
                               `city_id` bigint DEFAULT NULL COMMENT '市ID',
                               `city_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
                               `district_id` bigint DEFAULT NULL COMMENT '区ID',
                               `district_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
                               `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '详细地址',
                               `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '备注',
                               `attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '合同文件',
                               `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
                               `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                               `create_by` bigint DEFAULT NULL COMMENT '创建人',
                               `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                               `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                               `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='承运商表';

-- ----------------------------
-- Records of wms_carrier
-- ----------------------------
BEGIN;
INSERT INTO `wms_carrier` (`id`, `code`, `name`, `type`, `service_type`, `effective_date`, `expiration_date`, `payment_term`, `settlement_type`, `contact_name`, `postcode`, `mobile`, `email`, `fax`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `description`, `attachment`, `status`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1818123769223319553, 'CY0001', '测试承运商一号', 'TRADE_IN', 'LAND', '2024-07-15', '2024-07-10', NULL, '现金', '张三', '201100', '13733000000', 'ddcat666@88.com', '10002', 110000, '北京市', 110100, '市辖区', 110101, '东城区', 'shanghai putuo', '你好啊', NULL, NULL, 0, 1, '长风一梦8888', '2024-07-30 11:17:40', 1, '长风一梦8888', '2024-07-30 11:20:12');
INSERT INTO `wms_carrier` (`id`, `code`, `name`, `type`, `service_type`, `effective_date`, `expiration_date`, `payment_term`, `settlement_type`, `contact_name`, `postcode`, `mobile`, `email`, `fax`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `description`, `attachment`, `status`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1818124828712898561, 'CY0002', '测试承运商二号', 'TRADE_IN', 'AIR', '2024-01-01', '2026-06-05', NULL, '线上付款', '李四', '201100', '13733600001', 'ddcat666@88.com', '123', 120000, '天津市', 120100, '市辖区', 120101, '和平区', '和平区老约翰药房1号', '测试备注1122', NULL, NULL, 0, 1, '长风一梦8888', '2024-07-30 11:21:53', 1, '长风一梦8888', '2024-09-05 14:23:41');
COMMIT;

-- ----------------------------
-- Table structure for wms_container
-- ----------------------------
DROP TABLE IF EXISTS `wms_container`;
CREATE TABLE `wms_container` (
                                 `id` bigint NOT NULL COMMENT 'ID',
                                 `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '容器编码',
                                 `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '容器名称',
                                 `spec_id` bigint NOT NULL COMMENT '容器规格',
                                 `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '占用任务类型',
                                 `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                 `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                 `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                 `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                 KEY `container_spec_id` (`spec_id`) USING BTREE,
                                 CONSTRAINT `wms_container_ibfk_1` FOREIGN KEY (`spec_id`) REFERENCES `wms_container_spec` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='容器表';

-- ----------------------------
-- Records of wms_container
-- ----------------------------
BEGIN;
INSERT INTO `wms_container` (`id`, `code`, `name`, `spec_id`, `remark`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804504627211673602, 'CT01', '一号托盘', 1804501657954844673, '集货,存储,分拣,拣货,入库', 0, '2024-06-22 21:20:04', 1, '长风一梦8888', NULL, NULL, NULL);
INSERT INTO `wms_container` (`id`, `code`, `name`, `spec_id`, `remark`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829447711045349377, 'CT011', 'CT01', 1804501657954844673, '123213', 1, '2024-08-30 17:14:58', 1, '长风一梦8888', '2024-08-30 17:15:01', 1, '长风一梦8888');
COMMIT;

-- ----------------------------
-- Table structure for wms_container_log
-- ----------------------------
DROP TABLE IF EXISTS `wms_container_log`;
CREATE TABLE `wms_container_log` (
                                     `id` bigint NOT NULL COMMENT 'ID',
                                     `occupation_task_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '占用任务类型',
                                     `task_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务号',
                                     `doc_id` bigint DEFAULT NULL COMMENT '单据id',
                                     `doc_item_id` bigint DEFAULT NULL COMMENT '单据行id',
                                     `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态(占用、释放、异常)',
                                     `end_time` datetime DEFAULT NULL COMMENT '结束时间',
                                     `error_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '错误日志',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                     `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                     `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                     `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                     `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                     `container_id` bigint DEFAULT NULL COMMENT '容器ID',
                                     `doc_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                     `doc_item_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='容器规格表';

-- ----------------------------
-- Records of wms_container_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_container_spec
-- ----------------------------
DROP TABLE IF EXISTS `wms_container_spec`;
CREATE TABLE `wms_container_spec` (
                                      `id` bigint NOT NULL COMMENT 'ID',
                                      `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '容器规格名称',
                                      `length` decimal(10,2) NOT NULL COMMENT '长度',
                                      `width` decimal(10,2) NOT NULL COMMENT '宽度',
                                      `height` decimal(10,2) NOT NULL COMMENT '高度',
                                      `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '1',
                                      `self_weight` decimal(10,2) NOT NULL COMMENT '容器自重(单位KG)',
                                      `load_length` decimal(10,2) NOT NULL COMMENT '可承载长度',
                                      `load_width` decimal(10,2) NOT NULL COMMENT '可承载宽度',
                                      `load_height` decimal(10,2) NOT NULL COMMENT '可承载高度',
                                      `load_weight` decimal(10,2) NOT NULL COMMENT '可承载重量(单位KG)',
                                      `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                      `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                      `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                      `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                      `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                      `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='容器规格表';

-- ----------------------------
-- Records of wms_container_spec
-- ----------------------------
BEGIN;
INSERT INTO `wms_container_spec` (`id`, `name`, `length`, `width`, `height`, `status`, `self_weight`, `load_length`, `load_width`, `load_height`, `load_weight`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804501657954844673, '标准托盘', 150.00, 150.00, 20.00, '1', 0.70, 150.00, 150.00, 300.00, 50.00, 0, '2024-06-22 21:08:16', 1, '长风一梦8888', NULL, NULL, NULL);
INSERT INTO `wms_container_spec` (`id`, `name`, `length`, `width`, `height`, `status`, `self_weight`, `load_length`, `load_width`, `load_height`, `load_weight`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829445990265044993, '123', 123.00, 213.00, 213.22, '1', 123.00, 123.00, 213.00, 123.00, 123.00, 1, '2024-08-30 17:08:08', 1, '长风一梦8888', '2024-08-30 17:08:12', 1, '长风一梦8888');
COMMIT;

-- ----------------------------
-- Table structure for wms_dock
-- ----------------------------
DROP TABLE IF EXISTS `wms_dock`;
CREATE TABLE `wms_dock` (
                            `id` bigint NOT NULL COMMENT 'ID',
                            `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '月台编号',
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '月台名称',
                            `spec` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '月台规格',
                            `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型',
                            `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '状态(空闲、占用、维护)',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `create_by` bigint DEFAULT NULL COMMENT '创建人',
                            `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                            `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                            `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                            `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='月台管理表';

-- ----------------------------
-- Records of wms_dock
-- ----------------------------
BEGIN;
INSERT INTO `wms_dock` (`id`, `code`, `name`, `spec`, `type`, `status`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1, 'D01', '一号月台', '50吨', '入库', '1', '2024-06-24 23:46:13', 1, '长风一梦8888', '2024-12-17 22:51:44', 1, '平台管理员', 0);
INSERT INTO `wms_dock` (`id`, `code`, `name`, `spec`, `type`, `status`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1829448646068981762, '123', '13', '123', '123', '1', '2024-08-30 17:18:41', 1, '长风一梦8888', '2024-08-30 17:18:45', 1, '长风一梦8888', 1);
COMMIT;

-- ----------------------------
-- Table structure for wms_inventory_receipt
-- ----------------------------
DROP TABLE IF EXISTS `wms_inventory_receipt`;
CREATE TABLE `wms_inventory_receipt` (
                                         `id` bigint NOT NULL COMMENT '入库单主键',
                                         `create_time` datetime NOT NULL COMMENT '创建时间',
                                         `create_by` bigint NOT NULL COMMENT '创建人',
                                         `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                         `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                         `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                         `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                         `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                         `receipt_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '入库单编号',
                                         `plan_id` bigint DEFAULT NULL COMMENT '关联的收货计划ID',
                                         `plan_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货计划单编号',
                                         `warehouse_id` bigint DEFAULT NULL COMMENT '仓库ID',
                                         `supplier_id` bigint DEFAULT NULL COMMENT '供应商ID',
                                         `status` int DEFAULT NULL COMMENT '状态',
                                         `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '备注',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='入库单表';

-- ----------------------------
-- Records of wms_inventory_receipt
-- ----------------------------
BEGIN;
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1808765826258481153, '2024-07-04 15:32:33', 1, '长风一梦8888', '2024-07-05 10:51:25', 1, '长风一梦8888', 0, 'IR202407040002', 1, 'P202406001', 1803800674882818049, 1, 30, '备注信息');
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1808765870781018113, '2024-07-04 15:32:43', 1, '长风一梦8888', '2024-07-09 11:26:38', 1, '长风一梦8888', 0, 'IR202407040003', 1, 'P202406001', 1803800674882818049, 1, 30, '备注信息');
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1808766388894994434, '2024-07-04 15:34:47', 1, '长风一梦8888', '2024-08-03 14:44:45', 1, '长风一梦8888', 1, 'IR202407040004', 1, 'P202406001', 1803800674882818049, 1, 30, '备注信息');
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1808767461097525249, '2024-07-04 15:39:03', 1, '长风一梦8888', '2024-07-10 15:12:01', 1, '长风一梦8888', 0, 'IR202407040005', 1, 'P202406001', 1803800674882818049, 1, 30, '备注信息');
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1808773853694943234, '2024-07-04 16:04:27', 1, '长风一梦8888', '2024-07-10 13:26:57', 1, '长风一梦8888', 0, 'IR202407040006', 1, 'P202406001', 1803800674882818049, 1, 30, '备注信息');
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1808775517264965633, '2024-07-04 16:11:03', 1, '长风一梦8888', '2024-07-10 15:02:20', 1, '长风一梦8888', 0, 'IR202407040007', 1, 'P202406001', 1803800674882818049, 1, 30, '备注信息');
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1808776669079560194, '2024-07-04 16:15:38', 1, '长风一梦8888', '2024-07-10 12:55:31', 1, '长风一梦8888', 0, 'IR202407040008', 1806165100000755713, 'P202406002', 1803801114382962689, 1, 30, NULL);
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1810275263481692161, '2024-07-08 19:30:31', 1, '长风一梦8888', '2024-07-10 11:20:12', 1, '长风一梦8888', 0, 'IR202407080001', 1, 'P202406001', 1803800674882818049, 1, 30, '备注信息');
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1819400262402555905, '2024-08-02 23:50:00', 1, '长风一梦8888', '2024-08-02 23:50:44', 1, '长风一梦8888', 0, 'IR202408020001', 1, 'P202406001', 1803800674882818049, 1, 30, '备注信息');
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1821021583418888193, '2024-08-07 11:12:33', 1, '长风一梦8888', NULL, NULL, NULL, 0, 'IR202408070001', 1810672841373261825, 'RP202407090002', 1803801114382962689, 1, 30, NULL);
INSERT INTO `wms_inventory_receipt` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_num`, `plan_id`, `plan_num`, `warehouse_id`, `supplier_id`, `status`, `remark`) VALUES (1821371393309155330, '2024-08-08 10:22:34', 1, '长风一梦8888', '2024-08-08 10:28:51', 1, '长风一梦8888', 0, 'IR202408080001', 1819565598712045569, 'RP202408030001', 1803801114382962689, 1, 30, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wms_inventory_receipt_item
-- ----------------------------
DROP TABLE IF EXISTS `wms_inventory_receipt_item`;
CREATE TABLE `wms_inventory_receipt_item` (
                                              `id` bigint NOT NULL COMMENT '明细行主键',
                                              `create_time` datetime NOT NULL COMMENT '创建时间',
                                              `create_by` bigint NOT NULL COMMENT '创建人',
                                              `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                              `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                              `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                              `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                              `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                              `receipt_id` bigint DEFAULT NULL COMMENT '入库单表ID',
                                              `plan_item_id` bigint DEFAULT NULL COMMENT '关联的收货计划行项ID',
                                              `location_id` bigint DEFAULT NULL COMMENT '储位ID',
                                              `material_id` bigint NOT NULL COMMENT '物料ID',
                                              `material_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物料编号',
                                              `material_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物料描述',
                                              `batch_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '批次号',
                                              `receiving_qty` decimal(18,2) NOT NULL COMMENT '收货数量',
                                              `receiving_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收货单位',
                                              `qty` decimal(18,2) NOT NULL COMMENT '入库数量',
                                              `unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '入库单位',
                                              `receipt_time` datetime DEFAULT NULL COMMENT '入库时间',
                                              `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '备注',
                                              `production_date` date DEFAULT NULL COMMENT '生产日期',
                                              `expiry_date` date DEFAULT NULL COMMENT '失效日期',
                                              PRIMARY KEY (`id`) USING BTREE,
                                              KEY `receipt_id` (`receipt_id`) USING BTREE,
                                              CONSTRAINT `wms_inventory_receipt_item_ibfk_1` FOREIGN KEY (`receipt_id`) REFERENCES `wms_inventory_receipt` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='入库单明细行';

-- ----------------------------
-- Records of wms_inventory_receipt_item
-- ----------------------------
BEGIN;
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808765826401087489, '2024-07-04 15:32:33', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808765826258481153, 2, NULL, 1, NULL, NULL, 'S2024050.01', 1.00, '个', 1.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808765826409476097, '2024-07-04 15:32:33', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808765826258481153, 1, NULL, 1, NULL, NULL, 'S2024050.01', 1.00, '个', 1.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808765870890070017, '2024-07-04 15:32:44', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808765870781018113, 2, NULL, 1, NULL, NULL, 'S2024050.01', 3.00, '个', 3.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808765870894264321, '2024-07-04 15:32:44', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808765870781018113, 1, NULL, 1, NULL, NULL, 'S2024050.01', 3.00, '个', 3.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808766389045989378, '2024-07-04 15:34:47', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808766388894994434, 2, NULL, 1, NULL, NULL, 'S2024050.01', 1.00, '个', 1.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808766389058572290, '2024-07-04 15:34:47', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808766388894994434, 1, NULL, 1, NULL, NULL, 'S2024050.01', 1.00, '个', 1.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808767461261103105, '2024-07-04 15:39:03', 1, '长风一梦8888', '2024-07-10 15:12:01', 1, '长风一梦8888', 0, 1808767461097525249, 2, 1804048315550044162, 1, NULL, NULL, 'S2024050.01', 10.00, '个', 10.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808767461269491714, '2024-07-04 15:39:03', 1, '长风一梦8888', '2024-07-10 15:12:01', 1, '长风一梦8888', 0, 1808767461097525249, 1, 1804048317126238912, 1, NULL, NULL, 'S2024050.01', 1.00, '个', 1.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808773853875298305, '2024-07-04 16:04:27', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808773853694943234, 2, NULL, 1, NULL, NULL, 'S2024050.01', 100.00, '个', 100.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808773853883686913, '2024-07-04 16:04:27', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808773853694943234, 1, NULL, 1, NULL, NULL, 'S2024050.01', 4.00, '个', 4.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808775517428543490, '2024-07-04 16:11:03', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808775517264965633, 2, NULL, 2, NULL, NULL, 'S2024050.01', 0.00, '个', 0.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808775517441126401, '2024-07-04 16:11:03', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808775517264965633, 1, NULL, 1, NULL, NULL, 'S2024050.01', 0.00, '个', 0.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1808776669209583618, '2024-07-04 16:15:38', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1808776669079560194, 1806165100290162690, NULL, 1, NULL, NULL, '2024-02-22', 1.00, '11', 1.00, '11', NULL, NULL, '2024-02-22', '2024-02-22');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1809057467389247490, '2024-07-05 10:51:26', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1808765826258481153, 1, 1804048315550044162, 1, NULL, NULL, 'S2024050.01', 1.00, '个', 1.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1809057467431190530, '2024-07-05 10:51:26', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1808765826258481153, 2, 1804048315550044162, 1, NULL, NULL, 'S2024050.01', 1.00, '个', 1.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810275264744177666, '2024-07-08 19:30:31', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1810275263481692161, 2, NULL, 2, NULL, NULL, 'S2024050.01', 885.00, '个', 885.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810515882271612930, '2024-07-09 11:26:39', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1808765870781018113, 1, 1804048371298309120, 1, NULL, NULL, 'S2024050.01', 3.00, '个', 3.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810515882305167362, '2024-07-09 11:26:39', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1808765870781018113, 2, 1804048317126238912, 1, NULL, NULL, 'S2024050.01', 3.00, '个', 3.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810876616344076290, '2024-07-10 11:20:04', 1, '长风一梦8888', NULL, NULL, NULL, 1, 1810275263481692161, 2, 1804048371298309120, 2, NULL, NULL, 'S2024050.01', 885.00, '个', 885.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810876649588129794, '2024-07-10 11:20:12', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810275263481692161, 2, 1804048371298309120, 2, NULL, NULL, 'S2024050.01', 885.00, '个', 885.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810900636619800577, '2024-07-10 12:55:31', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1808776669079560194, 1806165100290162690, 1804048371298309120, 1, NULL, NULL, '2024-02-22', 1000.00, '11', 1000.00, '11', NULL, NULL, '2024-02-22', '2024-02-22');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810901447059996674, '2024-07-10 12:58:45', 1, '长风一梦8888', '2024-07-10 15:02:20', 1, '长风一梦8888', 0, 1808775517264965633, 1, 1804048371298309120, 1, NULL, NULL, 'S2024050.01', 0.00, '个', 0.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810901447072579586, '2024-07-10 12:58:45', 1, '长风一梦8888', '2024-07-10 15:02:20', 1, '长风一梦8888', 0, 1808775517264965633, 2, 1804048371298309120, 2, NULL, NULL, 'S2024050.01', 0.00, '个', 0.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810908546045345793, '2024-07-10 13:26:57', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1808773853694943234, 1, 1804048371298309120, 1, NULL, NULL, 'S2024050.01', 1.00, '个', 1.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1810908546062123009, '2024-07-10 13:26:57', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1808773853694943234, 2, 1804048371298309120, 1, NULL, NULL, 'S2024050.01', 1.00, '个', 1.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1819400262918455297, '2024-08-02 23:50:00', 1, '长风一梦8888', '2024-08-02 23:50:44', 1, '长风一梦8888', 0, 1819400262402555905, 2, 1804048317126238912, 2, NULL, NULL, 'S2024050.01', 99.00, '个', 99.00, '个', NULL, NULL, '2024-03-01', '2024-12-30');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1819400262989758466, '2024-08-02 23:50:00', 1, '长风一梦8888', '2024-08-02 23:50:44', 1, '长风一梦8888', 0, 1819400262402555905, 1, 1804048371298309120, 1, NULL, NULL, 'S2024050.01', 3.00, '个', 3.00, '个', NULL, NULL, '2024-01-01', '2024-12-12');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1821021584186445826, '2024-08-07 11:12:33', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1821021583418888193, 1810672841884966914, NULL, 2, NULL, NULL, 'QS.103092', 100.00, '个', 100.00, '个', NULL, NULL, '2024-05-01', '2026-12-31');
INSERT INTO `wms_inventory_receipt_item` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `receipt_id`, `plan_item_id`, `location_id`, `material_id`, `material_code`, `material_name`, `batch_num`, `receiving_qty`, `receiving_unit`, `qty`, `unit`, `receipt_time`, `remark`, `production_date`, `expiry_date`) VALUES (1821371394114461697, '2024-08-08 10:22:34', 1, '长风一梦8888', '2024-08-08 10:28:51', 1, '长风一梦8888', 0, 1821371393309155330, 1819565598925955073, 1804048371298309120, 2, NULL, NULL, 'QS2024-01', 50.00, 'KG', 50.00, 'KG', NULL, NULL, '2024-01-01', '2025-12-31');
COMMIT;

-- ----------------------------
-- Table structure for wms_location
-- ----------------------------
DROP TABLE IF EXISTS `wms_location`;
CREATE TABLE `wms_location` (
                                `id` bigint NOT NULL COMMENT 'ID',
                                `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '储位编号',
                                `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '储位名称',
                                `warehouse_id` bigint NOT NULL COMMENT '仓库id',
                                `storage_area_id` bigint NOT NULL COMMENT '所属库区',
                                `aisle_id` bigint NOT NULL COMMENT '所属巷道',
                                `spec_id` bigint NOT NULL COMMENT '储位规格',
                                `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '储位类型（1=高储位;0=低储位）',
                                `shelf_layer` int NOT NULL COMMENT '位于货架第几层',
                                `picking_level` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '拣选储位销售等级',
                                `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                PRIMARY KEY (`id`) USING BTREE,
                                KEY `storage_area_id` (`storage_area_id`) USING BTREE,
                                KEY `aisle_id` (`aisle_id`) USING BTREE,
                                KEY `location_spec_id` (`spec_id`) USING BTREE,
                                CONSTRAINT `wms_location_ibfk_1` FOREIGN KEY (`storage_area_id`) REFERENCES `wms_storage_area` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
                                CONSTRAINT `wms_location_ibfk_2` FOREIGN KEY (`aisle_id`) REFERENCES `wms_aisle` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
                                CONSTRAINT `wms_location_ibfk_3` FOREIGN KEY (`spec_id`) REFERENCES `wms_location_spec` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='储位表';

-- ----------------------------
-- Records of wms_location
-- ----------------------------
BEGIN;
INSERT INTO `wms_location` (`id`, `code`, `name`, `warehouse_id`, `storage_area_id`, `aisle_id`, `spec_id`, `type`, `shelf_layer`, `picking_level`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804048315550044162, 'A01-05-01', '一号储位', 1803800674882818049, 1803982572009054388, 1803801637861462018, 1804045327838253057, '1', 1, '优', 0, '2024-06-21 15:06:51', 1, '长风一梦8888', '2024-07-08 19:10:08', 1, '长风一梦8888');
INSERT INTO `wms_location` (`id`, `code`, `name`, `warehouse_id`, `storage_area_id`, `aisle_id`, `spec_id`, `type`, `shelf_layer`, `picking_level`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804048317126238912, 'A01-05-02', '二号储位', 1803800674882818049, 1803982572009054388, 1803801637861462018, 1804045327838253057, '1', 1, '优', 0, '2024-06-21 15:06:51', 1, '长风一梦8888', '2024-07-08 19:10:08', 1, '长风一梦8888');
INSERT INTO `wms_location` (`id`, `code`, `name`, `warehouse_id`, `storage_area_id`, `aisle_id`, `spec_id`, `type`, `shelf_layer`, `picking_level`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804048371298309120, 'A01-06-01', '三号储位', 1803800674882818049, 1803982572009054388, 1803801637861462018, 1804045327838253057, '1', 1, '优', 0, '2024-06-21 15:06:51', 1, '长风一梦8888', '2024-07-08 19:10:08', 1, '长风一梦8888');
INSERT INTO `wms_location` (`id`, `code`, `name`, `warehouse_id`, `storage_area_id`, `aisle_id`, `spec_id`, `type`, `shelf_layer`, `picking_level`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829442586818056193, '123', '213', 1803801114382962689, 1803982572009054388, 1803801637861462018, 1804045327838253057, '0', 21313, '123', 1, '2024-08-30 16:54:37', 1, '长风一梦8888', '2024-08-30 16:54:41', 1, '长风一梦8888');
COMMIT;

-- ----------------------------
-- Table structure for wms_location_spec
-- ----------------------------
DROP TABLE IF EXISTS `wms_location_spec`;
CREATE TABLE `wms_location_spec` (
                                     `id` bigint NOT NULL COMMENT 'ID',
                                     `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '储位规格编号',
                                     `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '储位规格名称',
                                     `status` tinyint NOT NULL COMMENT '是否启用',
                                     `length` decimal(10,2) NOT NULL COMMENT '长度',
                                     `width` decimal(10,2) NOT NULL COMMENT '宽度',
                                     `height` decimal(10,2) NOT NULL COMMENT '高度',
                                     `load_capacity` decimal(10,2) NOT NULL COMMENT '承重',
                                     `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                     `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                     `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                     `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='储位规格表';

-- ----------------------------
-- Records of wms_location_spec
-- ----------------------------
BEGIN;
INSERT INTO `wms_location_spec` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `load_capacity`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804045327838253052, 'C02', '双向货架', 1, 1000.00, 100.00, 150.00, 1000.00, 0, '2024-06-21 14:54:58', 1, '长风一梦8888', NULL, NULL, NULL);
INSERT INTO `wms_location_spec` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `load_capacity`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804045327838253055, 'C03', '旋转式货架', 1, 1000.00, 100.00, 150.00, 1000.00, 0, '2024-06-21 14:54:58', 1, '长风一梦8888', NULL, NULL, NULL);
INSERT INTO `wms_location_spec` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `load_capacity`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804045327838253057, 'C01', '单向货架', 1, 1000.00, 100.00, 150.00, 1000.00, 0, '2024-06-21 14:54:58', 1, '长风一梦8888', NULL, NULL, NULL);
INSERT INTO `wms_location_spec` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `load_capacity`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829442144352538626, '123', 'x', 1, 123.00, 123.00, 13.00, 123.00, 1, '2024-08-30 16:52:51', 1, '长风一梦8888', '2024-08-30 16:52:57', 1, '长风一梦8888');
INSERT INTO `wms_location_spec` (`id`, `code`, `name`, `status`, `length`, `width`, `height`, `load_capacity`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829442388150652930, '123', '123', 0, 213.00, 213.00, 123.00, 123.00, 1, '2024-08-30 16:53:49', 1, '长风一梦8888', '2024-08-30 16:53:53', 1, '长风一梦8888');
COMMIT;

-- ----------------------------
-- Table structure for wms_material
-- ----------------------------
DROP TABLE IF EXISTS `wms_material`;
CREATE TABLE `wms_material` (
                                `id` bigint NOT NULL COMMENT '主键',
                                `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物料编号',
                                `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物料描述',
                                `category_id` bigint DEFAULT NULL COMMENT '物料类型ID',
                                `brand_id` bigint DEFAULT NULL COMMENT '品牌ID',
                                `unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单位',
                                `spec` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '规格',
                                `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '型号',
                                `bar_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '条码号',
                                `gross_weight` decimal(16,16) DEFAULT NULL COMMENT '毛重',
                                `net_weight` decimal(16,16) DEFAULT NULL COMMENT '净重',
                                `length` decimal(16,2) DEFAULT NULL COMMENT '长',
                                `width` decimal(16,2) DEFAULT NULL COMMENT '宽',
                                `height` decimal(16,2) DEFAULT NULL COMMENT '高',
                                `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                                `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                `create_time` datetime NOT NULL COMMENT '创建时间',
                                `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='物料表';

-- ----------------------------
-- Records of wms_material
-- ----------------------------
BEGIN;
INSERT INTO `wms_material` (`id`, `code`, `name`, `category_id`, `brand_id`, `unit`, `spec`, `model`, `bar_code`, `gross_weight`, `net_weight`, `length`, `width`, `height`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, '1001', 'Intel 酷睿 i5 12400F', 1804739268011200513, 2, '件', '酷睿i5 12代系列，10纳米，45×37.5mm', 'Alder Lake-S', '1001', 0.1200000000000000, 0.1200000000000000, 0.05, 0.04, 0.00, '22', 0, 1, '长风一梦8888', '2024-06-23 13:09:45', 1, '长风一梦8888', '2024-09-04 15:18:09');
INSERT INTO `wms_material` (`id`, `code`, `name`, `category_id`, `brand_id`, `unit`, `spec`, `model`, `bar_code`, `gross_weight`, `net_weight`, `length`, `width`, `height`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (2, '1002', 'Intel 酷睿 i7 12600F', 1804739268011200513, 2, '件', '酷睿i7 12代系列，10纳米，45×37.5mm', 'Alder Lake-S', '1001', 0.1200000000000000, 0.1200000000000000, 0.05, 0.04, 11.00, '123', 0, 1, '长风一梦8888', '2024-06-23 13:09:45', 1, '长风一梦8888', '2024-09-05 12:39:55');
COMMIT;

-- ----------------------------
-- Table structure for wms_material_category
-- ----------------------------
DROP TABLE IF EXISTS `wms_material_category`;
CREATE TABLE `wms_material_category` (
                                         `id` bigint NOT NULL COMMENT '对象存储主键',
                                         `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物料类型编号',
                                         `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物料类型名称',
                                         `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否启用',
                                         `parent_id` bigint DEFAULT NULL COMMENT '父类型ID',
                                         `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                         `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                         `create_by` bigint NOT NULL COMMENT '创建人',
                                         `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                         `create_time` datetime NOT NULL COMMENT '创建时间',
                                         `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                         `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                         `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='物料类型表';

-- ----------------------------
-- Records of wms_material_category
-- ----------------------------
BEGIN;
INSERT INTO `wms_material_category` (`id`, `code`, `name`, `status`, `parent_id`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804739268011200513, 'T1', '数码产品', '1', 0, '2', 0, 1, '长风一梦8888', '2024-06-23 12:52:27', '2024-08-09 16:49:31', 1, '长风一梦8888');
INSERT INTO `wms_material_category` (`id`, `code`, `name`, `status`, `parent_id`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1804743622403293186, 'DN01', '电脑', '1', 1804739268011200513, '电脑', 0, 1, '长风一梦8888', '2024-06-23 13:09:45', NULL, NULL, NULL);
INSERT INTO `wms_material_category` (`id`, `code`, `name`, `status`, `parent_id`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829478377229266946, 'MC202408300004', '123', '1', 1804743622403293186, '12313', 1, 1, '长风一梦8888', '2024-08-30 19:16:50', '2024-08-30 19:16:54', 1, '长风一梦8888');
INSERT INTO `wms_material_category` (`id`, `code`, `name`, `status`, `parent_id`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829478956395540482, 'MC202408300005', '123', '1', 1804743622403293186, '123123', 1, 1, '长风一梦8888', '2024-08-30 19:19:08', '2024-08-30 19:23:58', 1, '长风一梦8888');
INSERT INTO `wms_material_category` (`id`, `code`, `name`, `status`, `parent_id`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829478984388325378, 'MC202408300006', '12333', '1', 1829478956395540482, '12333', 1, 1, '长风一梦8888', '2024-08-30 19:19:15', '2024-08-30 19:23:58', 1, '长风一梦8888');
INSERT INTO `wms_material_category` (`id`, `code`, `name`, `status`, `parent_id`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829483072287039490, 'MC202408300007', '非危化工', '1', 0, '化工品', 0, 1, '长风一梦8888', '2024-08-30 19:35:29', NULL, NULL, NULL);
INSERT INTO `wms_material_category` (`id`, `code`, `name`, `status`, `parent_id`, `remark`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829483116205596673, 'MC202408300008', '电池', '1', 1829483072287039490, '有毒物品', 0, 1, '长风一梦8888', '2024-08-30 19:35:40', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wms_material_config
-- ----------------------------
DROP TABLE IF EXISTS `wms_material_config`;
CREATE TABLE `wms_material_config` (
                                       `id` bigint NOT NULL COMMENT '对象存储主键',
                                       `create_time` datetime NOT NULL COMMENT '创建时间',
                                       `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                       `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                       `last_modify_time` datetime NOT NULL COMMENT '更新时间',
                                       `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                       `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新人姓名',
                                       `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                                       `material_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物料编号',
                                       `batch` tinyint DEFAULT NULL COMMENT '批次管理',
                                       `validity_period` tinyint DEFAULT NULL COMMENT '有效期管理',
                                       `min_stock_day` int DEFAULT NULL COMMENT '最小在库天数',
                                       `danger` tinyint DEFAULT NULL COMMENT '危化品标识',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='物料表';

-- ----------------------------
-- Records of wms_material_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_outbound_plan
-- ----------------------------
DROP TABLE IF EXISTS `wms_outbound_plan`;
CREATE TABLE `wms_outbound_plan` (
                                     `id` bigint NOT NULL COMMENT '主键ID',
                                     `create_time` datetime NOT NULL COMMENT '创建时间',
                                     `create_by` bigint NOT NULL COMMENT '创建人',
                                     `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                     `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                     `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                     `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                     `plan_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '出库单编号',
                                     `warehouse_id` bigint DEFAULT NULL COMMENT '仓库ID',
                                     `supplier_id` bigint DEFAULT NULL COMMENT '供应商ID',
                                     `dock_id` bigint DEFAULT NULL COMMENT '月台ID',
                                     `carrier_id` bigint DEFAULT NULL COMMENT '承运商ID',
                                     `delivery_date` date DEFAULT NULL COMMENT '计划发货日期',
                                     `delivery_car_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运单号或车辆车牌号',
                                     `delivery_driver` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运输人',
                                     `driver_contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '司机联系方式',
                                     `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单类型',
                                     `customer_order_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户订单号',
                                     `delivery_receiver` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人',
                                     `delivery_receiver_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人电话',
                                     `delivery_receiver_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人地址',
                                     `delivery_mode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '送货方式',
                                     `status` int DEFAULT NULL COMMENT '状态',
                                     `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '备注',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='出库单';

-- ----------------------------
-- Records of wms_outbound_plan
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_outbound_plan_item
-- ----------------------------
DROP TABLE IF EXISTS `wms_outbound_plan_item`;
CREATE TABLE `wms_outbound_plan_item` (
                                          `id` bigint NOT NULL COMMENT '主键ID',
                                          `create_time` datetime NOT NULL COMMENT '创建时间',
                                          `create_by` bigint NOT NULL COMMENT '创建人',
                                          `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                          `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                          `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                          `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                          `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                          `plan_id` bigint NOT NULL COMMENT '发货计划ID',
                                          `plan_item_num` bigint DEFAULT NULL COMMENT '行号',
                                          `material_id` bigint DEFAULT NULL COMMENT '物料ID',
                                          `qty` decimal(18,2) NOT NULL COMMENT '计划发货数量',
                                          `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '计划发货单位',
                                          `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '备注',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='出库单行项';

-- ----------------------------
-- Records of wms_outbound_plan_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_outbound_plan_item_stock
-- ----------------------------
DROP TABLE IF EXISTS `wms_outbound_plan_item_stock`;
CREATE TABLE `wms_outbound_plan_item_stock` (
                                                `id` bigint NOT NULL COMMENT '主键ID',
                                                `create_time` datetime NOT NULL COMMENT '创建时间',
                                                `create_by` bigint NOT NULL COMMENT '创建人',
                                                `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                                `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                                `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                                `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                                `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                                `plan_item_id` bigint NOT NULL COMMENT '出库单行项ID',
                                                `stock_id` bigint DEFAULT NULL COMMENT '库存余额ID',
                                                `qty` decimal(18,2) NOT NULL COMMENT '计划发货数量',
                                                `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '计划发货单位',
                                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='出库行项库存余额明细行';

-- ----------------------------
-- Records of wms_outbound_plan_item_stock
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_product
-- ----------------------------
DROP TABLE IF EXISTS `wms_product`;
CREATE TABLE `wms_product` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                               `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
                               `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
                               `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌',
                               `spec` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '规格',
                               `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '型号',
                               `weight` decimal(10,2) DEFAULT NULL COMMENT '重量',
                               `volume` decimal(10,2) DEFAULT NULL COMMENT '体积',
                               `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '描述',
                               `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                               `deleted` bit(1) DEFAULT b'0',
                               `create_by` bigint DEFAULT NULL COMMENT '创建人id',
                               `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                               `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                               `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1798255495128072195 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='产品';

-- ----------------------------
-- Records of wms_product
-- ----------------------------
BEGIN;
INSERT INTO `wms_product` (`id`, `code`, `name`, `brand`, `spec`, `model`, `weight`, `volume`, `description`, `status`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1798255495128072194, 'WP202406050001', 'IPhone-Plus', '苹果', '500 * 500', '256G', 5.00, 5.00, '苹果', NULL, b'0', 1, '长风一梦8888', '2024-06-05 15:28:15', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wms_receiving_appointment
-- ----------------------------
DROP TABLE IF EXISTS `wms_receiving_appointment`;
CREATE TABLE `wms_receiving_appointment` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                             `appointment_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货预约编号',
                                             `warehouse_id` bigint DEFAULT NULL COMMENT '仓库ID',
                                             `warehouse_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                             `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库名字',
                                             `station_id` bigint DEFAULT NULL,
                                             `station_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                             `station_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                             `inventory_company_id` bigint DEFAULT NULL COMMENT '存货公司ID',
                                             `inventory_company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '存货公司名称',
                                             `contact_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '存货公司联系人',
                                             `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '存货公司电话',
                                             `driver_mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '司机手机号',
                                             `driver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '司机名称',
                                             `plan_arrival_time` timestamp NULL DEFAULT NULL COMMENT '预计到货时间',
                                             `status` int DEFAULT NULL,
                                             `deleted` bit(1) DEFAULT b'0',
                                             `create_by` bigint NOT NULL COMMENT '创建人ID',
                                             `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                             `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                             `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                             `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1795373005188677634 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='收货预约';

-- ----------------------------
-- Records of wms_receiving_appointment
-- ----------------------------
BEGIN;
INSERT INTO `wms_receiving_appointment` (`id`, `appointment_no`, `warehouse_id`, `warehouse_no`, `warehouse_name`, `station_id`, `station_no`, `station_name`, `inventory_company_id`, `inventory_company_name`, `contact_person`, `contact_phone`, `driver_mobile`, `driver_name`, `plan_arrival_time`, `status`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 'WP202405280001', 1, NULL, '1号仓库', NULL, NULL, NULL, 1, '1号仓', '小王', '13000000000', '小李', '13000001910', '2024-05-28 10:42:26', NULL, b'1', 1, '系统', '2024-05-28 02:42:36', 1, '长风一梦8888', '2024-05-28 16:33:46');
INSERT INTO `wms_receiving_appointment` (`id`, `appointment_no`, `warehouse_id`, `warehouse_no`, `warehouse_name`, `station_id`, `station_no`, `station_name`, `inventory_company_id`, `inventory_company_name`, `contact_person`, `contact_phone`, `driver_mobile`, `driver_name`, `plan_arrival_time`, `status`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1795357065810071554, 'WP202405280002', 1, NULL, '1号仓库', NULL, NULL, NULL, NULL, '123', '123', '123', '123', '123', '2024-05-28 15:30:09', NULL, b'1', 1, '长风一梦8888', '2024-05-28 15:30:55', 1, '长风一梦8888', '2024-05-28 16:33:48');
INSERT INTO `wms_receiving_appointment` (`id`, `appointment_no`, `warehouse_id`, `warehouse_no`, `warehouse_name`, `station_id`, `station_no`, `station_name`, `inventory_company_id`, `inventory_company_name`, `contact_person`, `contact_phone`, `driver_mobile`, `driver_name`, `plan_arrival_time`, `status`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1795373005188677633, 'WH202405280001', 1, NULL, '1号仓库', NULL, NULL, NULL, NULL, '小米企业', '雷布斯', '13000000000', '13000000001', '金司机', '2024-05-28 16:34:08', NULL, b'0', 1, '长风一梦8888', '2024-05-28 16:34:16', 1, '长风一梦8888', '2024-05-28 16:49:04');
COMMIT;

-- ----------------------------
-- Table structure for wms_receiving_appointment_item
-- ----------------------------
DROP TABLE IF EXISTS `wms_receiving_appointment_item`;
CREATE TABLE `wms_receiving_appointment_item` (
                                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                                  `receiving_appointment_id` bigint DEFAULT NULL COMMENT '预收货ID',
                                                  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品名称',
                                                  `quantity` bigint DEFAULT NULL COMMENT '数量',
                                                  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌',
                                                  `spec` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品规格',
                                                  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品型号',
                                                  `weight` decimal(10,2) DEFAULT NULL COMMENT '重量',
                                                  `volume` decimal(10,2) DEFAULT NULL COMMENT '体积',
                                                  `dict_quantity_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数量单位',
                                                  `dict_weight_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '重量单位',
                                                  `dict_volume_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '体积单位',
                                                  `dict_unit_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单位类型',
                                                  `dangerous` bit(1) DEFAULT b'0' COMMENT '是否危险',
                                                  `manufacture_date` timestamp NULL DEFAULT NULL COMMENT '生产日期',
                                                  `batch_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生产批次',
                                                  `deleted` bit(1) DEFAULT b'0',
                                                  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
                                                  `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                                  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                  `last_modify_by` bigint DEFAULT NULL COMMENT '更新人id',
                                                  `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                                  `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1795376732628955139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='收货预约明细';

-- ----------------------------
-- Records of wms_receiving_appointment_item
-- ----------------------------
BEGIN;
INSERT INTO `wms_receiving_appointment_item` (`id`, `receiving_appointment_id`, `goods_name`, `quantity`, `brand`, `spec`, `model`, `weight`, `volume`, `dict_quantity_unit`, `dict_weight_unit`, `dict_volume_unit`, `dict_unit_type`, `dangerous`, `manufacture_date`, `batch_no`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1795357065864597506, 1795357065810071554, '123', 123, '123', '213', '123', NULL, NULL, NULL, NULL, NULL, NULL, b'0', '2024-05-28 15:30:16', '123', b'0', 1, '长风一梦8888', '2024-05-28 15:30:55', NULL, NULL, NULL);
INSERT INTO `wms_receiving_appointment_item` (`id`, `receiving_appointment_id`, `goods_name`, `quantity`, `brand`, `spec`, `model`, `weight`, `volume`, `dict_quantity_unit`, `dict_weight_unit`, `dict_volume_unit`, `dict_unit_type`, `dangerous`, `manufacture_date`, `batch_no`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1795373005209649154, 1795373005188677633, '123', 123, '213', '123', '213', NULL, NULL, NULL, NULL, NULL, NULL, b'0', '2024-05-28 16:34:14', '123', b'1', 1, '长风一梦8888', '2024-05-28 16:34:16', NULL, NULL, NULL);
INSERT INTO `wms_receiving_appointment_item` (`id`, `receiving_appointment_id`, `goods_name`, `quantity`, `brand`, `spec`, `model`, `weight`, `volume`, `dict_quantity_unit`, `dict_weight_unit`, `dict_volume_unit`, `dict_unit_type`, `dangerous`, `manufacture_date`, `batch_no`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1795376338385321985, 1795373005188677633, '小米13', 100, '小米', '500 * 500', '手机', NULL, NULL, NULL, NULL, NULL, NULL, b'1', '2024-05-28 16:40:39', '20240528', b'1', 1, '长风一梦8888', '2024-05-28 16:47:30', NULL, NULL, NULL);
INSERT INTO `wms_receiving_appointment_item` (`id`, `receiving_appointment_id`, `goods_name`, `quantity`, `brand`, `spec`, `model`, `weight`, `volume`, `dict_quantity_unit`, `dict_weight_unit`, `dict_volume_unit`, `dict_unit_type`, `dangerous`, `manufacture_date`, `batch_no`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1795376338385321986, 1795373005188677633, '小米14', 100, '小米', '500 * 500', '手机', NULL, NULL, NULL, NULL, NULL, NULL, b'0', '2024-05-28 16:34:14', '20240528', b'1', 1, '长风一梦8888', '2024-05-28 16:47:30', NULL, NULL, NULL);
INSERT INTO `wms_receiving_appointment_item` (`id`, `receiving_appointment_id`, `goods_name`, `quantity`, `brand`, `spec`, `model`, `weight`, `volume`, `dict_quantity_unit`, `dict_weight_unit`, `dict_volume_unit`, `dict_unit_type`, `dangerous`, `manufacture_date`, `batch_no`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1795376732628955137, 1795373005188677633, '小米14', 100, '小米', '500 * 500', '手机', NULL, NULL, NULL, NULL, NULL, NULL, b'0', '2024-05-28 16:34:14', '20240528', b'0', 1, '长风一梦8888', '2024-05-28 16:49:04', NULL, NULL, NULL);
INSERT INTO `wms_receiving_appointment_item` (`id`, `receiving_appointment_id`, `goods_name`, `quantity`, `brand`, `spec`, `model`, `weight`, `volume`, `dict_quantity_unit`, `dict_weight_unit`, `dict_volume_unit`, `dict_unit_type`, `dangerous`, `manufacture_date`, `batch_no`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1795376732628955138, 1795373005188677633, '小米13', 100, '小米', '500 * 500', '手机', NULL, NULL, NULL, NULL, NULL, NULL, b'1', '2024-05-28 16:40:39', '20240528', b'0', 1, '长风一梦8888', '2024-05-28 16:49:04', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wms_receiving_plan
-- ----------------------------
DROP TABLE IF EXISTS `wms_receiving_plan`;
CREATE TABLE `wms_receiving_plan` (
                                      `id` bigint NOT NULL COMMENT '对象存储主键',
                                      `create_time` datetime NOT NULL COMMENT '创建时间',
                                      `create_by` bigint NOT NULL COMMENT '创建人',
                                      `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                      `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                      `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                      `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                      `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                      `plan_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货计划编号',
                                      `receiving_notice_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '到货通知单编号',
                                      `warehouse_id` bigint DEFAULT NULL COMMENT '仓库ID',
                                      `dock_id` bigint DEFAULT NULL COMMENT '月台ID',
                                      `supplier_id` bigint DEFAULT NULL COMMENT '供应商ID',
                                      `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型',
                                      `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源',
                                      `delivery_date` date DEFAULT NULL COMMENT '发货日期',
                                      `expected_arrival_time` date DEFAULT NULL COMMENT '预计到货时间',
                                      `actual_arrival_time` date DEFAULT NULL COMMENT '实际到货时间',
                                      `arrival_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '到货状态(已到货、延迟、未到货)',
                                      `status` int DEFAULT NULL COMMENT '状态',
                                      `delivery_car_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '送货车辆车牌号',
                                      `delivery_driver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '送货司机',
                                      `driver_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '司机联系方式',
                                      `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '备注',
                                      `container_id` bigint DEFAULT NULL COMMENT '容器ID',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='收货计划表';

-- ----------------------------
-- Records of wms_receiving_plan
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_receiving_plan_item
-- ----------------------------
DROP TABLE IF EXISTS `wms_receiving_plan_item`;
CREATE TABLE `wms_receiving_plan_item` (
                                           `id` bigint NOT NULL COMMENT '对象存储主键',
                                           `create_time` datetime NOT NULL COMMENT '创建时间',
                                           `create_by` bigint NOT NULL COMMENT '创建人',
                                           `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                           `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                           `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                           `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                           `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                           `receiving_plan_id` bigint DEFAULT NULL COMMENT '收货计划Id',
                                           `plan_item_num` int DEFAULT NULL COMMENT '收货计划行号',
                                           `receiving_notice_item_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '到货通知单行号',
                                           `material_id` bigint NOT NULL COMMENT '物料ID',
                                           `material_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物料编号',
                                           `material_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物料描述',
                                           `production_date` date DEFAULT NULL COMMENT '生产日期',
                                           `expiry_date` date DEFAULT NULL COMMENT '失效日期',
                                           `batch_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '批次号',
                                           `unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单位',
                                           `qty` decimal(18,2) DEFAULT NULL COMMENT '数量',
                                           `unit_price` decimal(18,2) DEFAULT NULL COMMENT '单位价格',
                                           `material_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='收货计划明细表';

-- ----------------------------
-- Records of wms_receiving_plan_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_scan_order
-- ----------------------------
DROP TABLE IF EXISTS `wms_scan_order`;
CREATE TABLE `wms_scan_order` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `ref_id` bigint DEFAULT NULL COMMENT '单据ID',
                                  `ref_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单据编码',
                                  `company_id` bigint DEFAULT NULL COMMENT '客户ID',
                                  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户名字',
                                  `warehouse_id` bigint DEFAULT NULL COMMENT '仓库ID',
                                  `warehouse_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库编码',
                                  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库名字',
                                  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作类型',
                                  `quantity` bigint DEFAULT NULL COMMENT '数量',
                                  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '描述',
                                  `deleted` bit(1) DEFAULT b'0',
                                  `create_by` bigint NOT NULL COMMENT '创建人ID',
                                  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                  `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                  `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='扫码订单';

-- ----------------------------
-- Records of wms_scan_order
-- ----------------------------
BEGIN;
INSERT INTO `wms_scan_order` (`id`, `ref_id`, `ref_code`, `company_id`, `company_name`, `warehouse_id`, `warehouse_code`, `warehouse_name`, `type`, `quantity`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 1, 'DD202406050001', 1, '西镜科技', 1, 'CK202406060001', '一号仓库', 'in', 100, '扫码', b'0', 1, '系统', '2024-06-06 03:05:52', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wms_scan_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_scan_order_detail`;
CREATE TABLE `wms_scan_order_detail` (
                                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                         `assets_id` bigint DEFAULT NULL COMMENT '资产ID',
                                         `assets_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资产编码',
                                         `ref_id` bigint DEFAULT NULL COMMENT '单据ID',
                                         `ref_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单据编码',
                                         `company_id` bigint DEFAULT NULL COMMENT '客户ID',
                                         `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户名字',
                                         `warehouse_id` bigint DEFAULT NULL COMMENT '仓库ID',
                                         `warehouse_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库编码',
                                         `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库名字',
                                         `product_id` bigint DEFAULT NULL,
                                         `product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                         `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                         `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作类型',
                                         `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '描述',
                                         `deleted` bit(1) DEFAULT b'0',
                                         `create_by` bigint NOT NULL COMMENT '创建人ID',
                                         `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                         `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                         `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='扫码订单明细';

-- ----------------------------
-- Records of wms_scan_order_detail
-- ----------------------------
BEGIN;
INSERT INTO `wms_scan_order_detail` (`id`, `assets_id`, `assets_code`, `ref_id`, `ref_code`, `company_id`, `company_name`, `warehouse_id`, `warehouse_code`, `warehouse_name`, `product_id`, `product_code`, `product_name`, `type`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 1, '202406 000001', 1, 'DD202406050001', 1, '公司名字', 1, 'CK202406060001', '一号仓库', 1, 'CP2024060001', '苹果14', 'in', '系统管理', b'0', 1, '系统管理', '2024-06-06 06:21:53', NULL, NULL, '2024-06-06 06:27:51');
COMMIT;

-- ----------------------------
-- Table structure for wms_stock
-- ----------------------------
DROP TABLE IF EXISTS `wms_stock`;
CREATE TABLE `wms_stock` (
                             `id` bigint NOT NULL COMMENT 'ID',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `create_by` bigint DEFAULT NULL COMMENT '创建人',
                             `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                             `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                             `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                             `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                             `warehouse_id` bigint NOT NULL COMMENT '仓库ID',
                             `location_id` bigint NOT NULL COMMENT '储位ID',
                             `material_id` bigint NOT NULL COMMENT '物料ID',
                             `batch_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '批次号',
                             `production_date` date DEFAULT NULL COMMENT '生产日期',
                             `expiry_date` date DEFAULT NULL COMMENT '失效日期',
                             `available_qty` decimal(18,2) DEFAULT NULL COMMENT '可用库存数量',
                             `qty` decimal(18,2) DEFAULT '0.00' COMMENT '库存数量',
                             `unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存单位',
                             `attribute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '特殊属性',
                             `level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物料存货等级(良品、待检品、不良品、返工品、报废品、退货品)',
                             `unit_price` decimal(18,2) DEFAULT '0.00' COMMENT '单价',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存余额表';

-- ----------------------------
-- Records of wms_stock
-- ----------------------------
BEGIN;
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1808698578244206594, '2024-07-04 11:05:20', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048315550044162, 1, 'S2024050.01', '2024-03-01', '2024-12-30', NULL, 80.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1808698578273566721, '2024-07-04 11:05:20', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048315550044162, 1, 'S2024050.01', '2024-01-01', '2024-12-12', NULL, 10.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1809057482098671617, '2024-07-05 10:51:29', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048315550044162, 1, 'S2024050.01', '2024-03-01', '2024-12-30', NULL, 1.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1809057482115448834, '2024-07-05 10:51:29', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048315550044162, 1, 'S2024050.01', '2024-01-01', '2024-12-12', NULL, 1.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810515894401540098, '2024-07-09 11:26:42', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048317126238912, 1, 'S2024050.01', '2024-03-01', '2024-12-30', 3.00, 3.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810515895630471169, '2024-07-09 11:26:42', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048371298309120, 1, 'S2024050.01', '2024-01-01', '2024-12-12', 3.00, 3.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810876672002490370, '2024-07-10 11:20:18', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048371298309120, 2, 'S2024050.01', '2024-03-01', '2024-12-30', 885.00, 885.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810901718049783809, '2024-07-10 12:59:49', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803801114382962689, 1804048371298309120, 1, '2024-02-22', '2024-02-22', '2024-02-22', 1000.00, 1000.00, '11', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810908572410740737, '2024-07-10 13:27:03', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048371298309120, 1, 'S2024050.01', '2024-03-01', '2024-12-30', 1.00, 1.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810908572519792642, '2024-07-10 13:27:03', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048371298309120, 1, 'S2024050.01', '2024-01-01', '2024-12-12', 1.00, 1.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810932644993585154, '2024-07-10 15:02:43', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048371298309120, 2, 'S2024050.01', '2024-03-01', '2024-12-30', 0.00, 0.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810932645278797825, '2024-07-10 15:02:43', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048371298309120, 1, 'S2024050.01', '2024-01-01', '2024-12-12', 0.00, 0.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810935014217568258, '2024-07-10 15:12:08', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048317126238912, 1, 'S2024050.01', '2024-01-01', '2024-12-12', 1.00, 1.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1810935014355980290, '2024-07-10 15:12:08', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048315550044162, 1, 'S2024050.01', '2024-03-01', '2024-12-30', 10.00, 10.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1819400449149747201, '2024-08-02 23:50:45', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048371298309120, 1, 'S2024050.01', '2024-01-01', '2024-12-12', 3.00, 3.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1819400449544011778, '2024-08-02 23:50:45', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803800674882818049, 1804048317126238912, 2, 'S2024050.01', '2024-03-01', '2024-12-30', 99.00, 99.00, '个', NULL, '0', 0.00);
INSERT INTO `wms_stock` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `warehouse_id`, `location_id`, `material_id`, `batch_num`, `production_date`, `expiry_date`, `available_qty`, `qty`, `unit`, `attribute`, `level`, `unit_price`) VALUES (1821372975249629186, '2024-08-08 10:28:51', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1803801114382962689, 1804048371298309120, 2, 'QS2024-01', '2024-01-01', '2025-12-31', 50.00, 50.00, 'KG', NULL, '0', 0.00);
COMMIT;

-- ----------------------------
-- Table structure for wms_stock_change
-- ----------------------------
DROP TABLE IF EXISTS `wms_stock_change`;
CREATE TABLE `wms_stock_change` (
                                    `id` bigint NOT NULL COMMENT 'ID',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                    `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                    `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                    `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                    `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                    `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                    `stock_id` bigint NOT NULL COMMENT '库存余额ID',
                                    `warehouse_id` bigint NOT NULL COMMENT '仓库ID',
                                    `location_id` bigint NOT NULL COMMENT '储位ID',
                                    `material_id` bigint NOT NULL COMMENT '物料ID',
                                    `change_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存变动类型(入库、出库、盘点、移库)',
                                    `doc_id` bigint NOT NULL COMMENT '单据ID',
                                    `doc_item_id` bigint NOT NULL COMMENT '单据行ID',
                                    `batch_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '批次号',
                                    `production_date` date DEFAULT NULL COMMENT '生产日期',
                                    `expiry_date` date DEFAULT NULL COMMENT '失效日期',
                                    `qty` decimal(18,2) DEFAULT '0.00' COMMENT '变动数量',
                                    `unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '库存单位',
                                    `attribute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '特殊属性',
                                    `level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物料存货等级(良品、待检品、不良品、返工品、报废品、退货品)',
                                    `unit_price` decimal(18,2) DEFAULT '0.00' COMMENT '单价',
                                    `remarks` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存余额变动表';

-- ----------------------------
-- Records of wms_stock_change
-- ----------------------------
BEGIN;
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810515895022297089, '2024-07-09 11:26:42', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810515894401540098, 1803800674882818049, 1804048317126238912, 1, '0', 1808765870781018113, 1810515882305167362, 'S2024050.01', '2024-03-01', '2024-12-30', 3.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810515896167342081, '2024-07-09 11:26:42', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810515895630471169, 1803800674882818049, 1804048371298309120, 1, '0', 1808765870781018113, 1810515882271612930, 'S2024050.01', '2024-01-01', '2024-12-12', 3.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810876672057016322, '2024-07-10 11:20:18', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810876672002490370, 1803800674882818049, 1804048371298309120, 2, '0', 1810275263481692161, 1810876649588129794, 'S2024050.01', '2024-03-01', '2024-12-30', 885.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810901718104309762, '2024-07-10 12:59:49', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810901718049783809, 1803801114382962689, 1804048371298309120, 1, '0', 1808776669079560194, 1810900636619800577, '2024-02-22', '2024-02-22', '2024-02-22', 1000.00, '11', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810908572461072386, '2024-07-10 13:27:03', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810908572410740737, 1803800674882818049, 1804048371298309120, 1, '0', 1808773853694943234, 1810908546062123009, 'S2024050.01', '2024-03-01', '2024-12-30', 1.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810908572578512898, '2024-07-10 13:27:03', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810908572519792642, 1803800674882818049, 1804048371298309120, 1, '0', 1808773853694943234, 1810908546045345793, 'S2024050.01', '2024-01-01', '2024-12-12', 1.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810932645161357314, '2024-07-10 15:02:43', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810932644993585154, 1803800674882818049, 1804048371298309120, 2, '0', 1808775517264965633, 1810901447072579586, 'S2024050.01', '2024-03-01', '2024-12-30', 0.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810932645387849730, '2024-07-10 15:02:43', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810932645278797825, 1803800674882818049, 1804048371298309120, 1, '0', 1808775517264965633, 1810901447059996674, 'S2024050.01', '2024-01-01', '2024-12-12', 0.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810935014280482817, '2024-07-10 15:12:08', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810935014217568258, 1803800674882818049, 1804048317126238912, 1, '0', 1808767461097525249, 1808767461269491714, 'S2024050.01', '2024-01-01', '2024-12-12', 1.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1810935014427283458, '2024-07-10 15:12:08', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1810935014355980290, 1803800674882818049, 1804048315550044162, 1, '0', 1808767461097525249, 1808767461261103105, 'S2024050.01', '2024-03-01', '2024-12-30', 10.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1819400449346879489, '2024-08-02 23:50:45', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1819400449149747201, 1803800674882818049, 1804048371298309120, 1, '0', 1819400262402555905, 1819400262989758466, 'S2024050.01', '2024-01-01', '2024-12-12', 3.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1819400449678229506, '2024-08-02 23:50:45', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1819400449544011778, 1803800674882818049, 1804048317126238912, 2, '0', 1819400262402555905, 1819400262918455297, 'S2024050.01', '2024-03-01', '2024-12-30', 99.00, '个', NULL, '0', 0.00, NULL);
INSERT INTO `wms_stock_change` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `stock_id`, `warehouse_id`, `location_id`, `material_id`, `change_type`, `doc_id`, `doc_item_id`, `batch_num`, `production_date`, `expiry_date`, `qty`, `unit`, `attribute`, `level`, `unit_price`, `remarks`) VALUES (1821372975643893762, '2024-08-08 10:28:52', 1, '长风一梦8888', NULL, NULL, NULL, 0, 1821372975249629186, 1803801114382962689, 1804048371298309120, 2, '0', 1821371393309155330, 1821371394114461697, 'QS2024-01', '2024-01-01', '2025-12-31', 50.00, 'KG', NULL, '0', 0.00, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wms_stock_flow
-- ----------------------------
DROP TABLE IF EXISTS `wms_stock_flow`;
CREATE TABLE `wms_stock_flow` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `warehouse_id` bigint DEFAULT NULL COMMENT '仓库ID',
                                  `warehouse_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库编码',
                                  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '仓库名字',
                                  `company_id` bigint DEFAULT NULL,
                                  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                  `ref_id` bigint DEFAULT NULL,
                                  `ref_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                  `product_id` bigint DEFAULT NULL COMMENT '产品ID',
                                  `product_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品编码',
                                  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品名字',
                                  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作类型',
                                  `quantity` bigint DEFAULT NULL COMMENT '数量(正整数就是增加  负数就是扣减)',
                                  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '描述',
                                  `deleted` bit(1) DEFAULT b'0',
                                  `create_by` bigint NOT NULL COMMENT '创建人ID',
                                  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人名称',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `last_modify_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                  `last_modify_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
                                  `last_modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存流水';

-- ----------------------------
-- Records of wms_stock_flow
-- ----------------------------
BEGIN;
INSERT INTO `wms_stock_flow` (`id`, `warehouse_id`, `warehouse_code`, `warehouse_name`, `company_id`, `company_name`, `ref_id`, `ref_code`, `product_id`, `product_code`, `product_name`, `type`, `quantity`, `description`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 1, 'WN202406050001', '一号仓库', NULL, NULL, NULL, NULL, 1, 'WP202406050001', '苹果', 'in', 100, '入库 100 个苹果', b'0', 1, '系统', '2024-06-05 07:59:30', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wms_storage_area
-- ----------------------------
DROP TABLE IF EXISTS `wms_storage_area`;
CREATE TABLE `wms_storage_area` (
                                    `id` bigint NOT NULL COMMENT 'ID',
                                    `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '库区编号',
                                    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '库区名称',
                                    `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '库区类型',
                                    `warehouse_id` bigint NOT NULL COMMENT '仓库id',
                                    `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '仓库状态',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                    `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                    `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                    `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                    `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                    `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    KEY `warehouse_id` (`warehouse_id`) USING BTREE,
                                    CONSTRAINT `wms_storage_area_ibfk_1` FOREIGN KEY (`warehouse_id`) REFERENCES `wms_warehouse` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库区表';

-- ----------------------------
-- Records of wms_storage_area
-- ----------------------------
BEGIN;
INSERT INTO `wms_storage_area` (`id`, `code`, `name`, `type`, `warehouse_id`, `status`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1803801589576634370, 'C1', '常温库区', '成品件库区', 1803800674882818049, '1', '2024-06-20 22:46:27', 1, '长风一梦8888', '2024-06-21 10:48:32', 1, '长风一梦8888', 0);
INSERT INTO `wms_storage_area` (`id`, `code`, `name`, `type`, `warehouse_id`, `status`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1803982431034302465, 'G1', '高值品区', '存放价值较高或需要特别保管的商品', 1803800674882818049, '1', '2024-06-21 10:45:03', 1, '长风一梦8888', NULL, NULL, NULL, 0);
INSERT INTO `wms_storage_area` (`id`, `code`, `name`, `type`, `warehouse_id`, `status`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1803982572009054210, 'LC1', '冷藏区', '存放需要冷藏保存的商品', 1803801114382962689, '1', '2024-06-21 10:45:36', 1, '长风一梦8888', '2024-06-21 14:49:44', 1, '长风一梦8888', 0);
INSERT INTO `wms_storage_area` (`id`, `code`, `name`, `type`, `warehouse_id`, `status`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1803982572009054388, 'H1', '恒温恒湿区', '电子元件', 1803801114382962689, '1', '2024-06-21 10:45:36', 1, '长风一梦8888', '2024-08-30 16:18:12', 1, '长风一梦8888', 0);
INSERT INTO `wms_storage_area` (`id`, `code`, `name`, `type`, `warehouse_id`, `status`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`) VALUES (1829435348602748929, '123', '123', '123', 1803801114382962689, '1', '2024-08-30 16:25:51', 1, '长风一梦8888', '2024-08-30 16:25:59', 1, '长风一梦8888', 1);
COMMIT;

-- ----------------------------
-- Table structure for wms_supplier
-- ----------------------------
DROP TABLE IF EXISTS `wms_supplier`;
CREATE TABLE `wms_supplier` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                `name` varchar(52) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '供应商名称',
                                `contact_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人姓名',
                                `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
                                `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电子邮件',
                                `address` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
                                `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司电话',
                                `establishment_date` date DEFAULT NULL COMMENT '成立日期',
                                `business_start_date` date DEFAULT NULL COMMENT '营业期限开始时间',
                                `business_end_date` date DEFAULT NULL COMMENT '营业期限截止时间',
                                `registration_authority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登记机关',
                                `registration_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '注册地址',
                                `business_scope` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '经营范围',
                                `enterprise_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业类型',
                                `legal_person` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '法定代表人',
                                `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'LOGO（存储路径或URL）',
                                `credit_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '统一信用代码',
                                `credit_limit` decimal(10,2) DEFAULT NULL COMMENT '信用限额',
                                `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '备注',
                                `province_id` bigint DEFAULT NULL COMMENT '省',
                                `province_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省名称',
                                `city_id` bigint DEFAULT NULL COMMENT '市',
                                `city_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市名称',
                                `district_id` bigint DEFAULT NULL COMMENT '区',
                                `district_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区名称',
                                `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                                `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                `last_modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`) USING BTREE,
                                KEY `idx_supplier_name` (`name`) USING BTREE,
                                KEY `idx_contact_name` (`contact_name`) USING BTREE,
                                KEY `idx_create_by` (`create_by`) USING BTREE,
                                KEY `idx_last_modify_by` (`last_modify_by`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1808032660916916227 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='供应商基本信息表';

-- ----------------------------
-- Records of wms_supplier
-- ----------------------------
BEGIN;
INSERT INTO `wms_supplier` (`id`, `code`, `name`, `contact_name`, `contact_phone`, `email`, `address`, `tel`, `establishment_date`, `business_start_date`, `business_end_date`, `registration_authority`, `registration_address`, `business_scope`, `enterprise_type`, `legal_person`, `logo`, `credit_code`, `credit_limit`, `description`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `deleted`, `create_by`, `create_name`, `create_time`, `last_modify_by`, `last_modify_name`, `last_modify_time`) VALUES (1, 'EWS202409050001', '英特尔科技（中国）有限公司', '55555', '77777', '66666', '上海市外高桥保税区英伦路999号', '2222', '1995-10-13', '1995-10-13', '2045-10-12', '44444', NULL, '装配、测试、销售集成电路、提供售后服务和技术培训（涉及许可经营的凭许可证经营）。', '有限责任公司（外国法人独资）', 'PATRICE C·SCATENA', NULL, '31014160733543X', 88888.00, '99999', 310000, '上海市', 310100, '市辖区', 310115, '浦东新区', 0, 1, '长风一梦8888', '2024-07-02 14:59:13', 1, '长风一梦8888', '2024-09-05 13:34:44');
COMMIT;

-- ----------------------------
-- Table structure for wms_unit
-- ----------------------------
DROP TABLE IF EXISTS `wms_unit`;
CREATE TABLE `wms_unit` (
                            `id` bigint NOT NULL COMMENT '对象存储主键',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            `create_by` bigint NOT NULL COMMENT '创建人',
                            `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                            `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                            `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                            `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                            `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '计量单位中文名称',
                            `name_en` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '计量单位英文名称',
                            `symbol` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '计量单位符号',
                            `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '计量单位类型',
                            `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                            `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='基本计量单位表';

-- ----------------------------
-- Records of wms_unit
-- ----------------------------
BEGIN;
INSERT INTO `wms_unit` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `name`, `name_en`, `symbol`, `type`, `description`, `status`) VALUES (1, '2024-06-23 13:09:45', 1, '长风一梦8888', '2024-12-11 10:16:11', 1, '长风一梦8888', 0, '个', 'Piece', '个', '数量', '描述1', '0');
INSERT INTO `wms_unit` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `name`, `name_en`, `symbol`, `type`, `description`, `status`) VALUES (2, '2024-06-23 13:09:45', 1, '长风一梦8888', NULL, NULL, NULL, 0, '千克', 'Kilogram', 'KG', '重量', '描述2', '1');
INSERT INTO `wms_unit` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `name`, `name_en`, `symbol`, `type`, `description`, `status`) VALUES (1831560095939559426, '2024-09-05 13:08:50', 1, '长风一梦8888', '2024-09-05 13:08:57', 1, '长风一梦8888', 1, '123', '123', '123', '213', '123', '0');
INSERT INTO `wms_unit` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `name`, `name_en`, `symbol`, `type`, `description`, `status`) VALUES (1831560594852020225, '2024-09-05 13:10:49', 1, '长风一梦8888', '2024-09-05 13:10:53', 1, '长风一梦8888', 1, '22', '22', '22', '22', '22', '1');
COMMIT;

-- ----------------------------
-- Table structure for wms_unit_conv
-- ----------------------------
DROP TABLE IF EXISTS `wms_unit_conv`;
CREATE TABLE `wms_unit_conv` (
                                 `id` bigint NOT NULL COMMENT '对象存储主键',
                                 `create_time` datetime NOT NULL COMMENT '创建时间',
                                 `create_by` bigint NOT NULL COMMENT '创建人',
                                 `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建人姓名',
                                 `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                 `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                 `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                 `unit_id` bigint NOT NULL COMMENT '基本计量单位ID',
                                 `unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '基本计量单位',
                                 `conv_unit_id` bigint DEFAULT NULL COMMENT '转换的计量单位ID',
                                 `conv_unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '转换的基本计量单位',
                                 `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                 `numerator` decimal(10,2) DEFAULT NULL COMMENT '分子',
                                 `denominator` decimal(10,2) DEFAULT NULL COMMENT '分母',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='基本计量单位转换表';

-- ----------------------------
-- Records of wms_unit_conv
-- ----------------------------
BEGIN;
INSERT INTO `wms_unit_conv` (`id`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`, `deleted`, `unit_id`, `unit`, `conv_unit_id`, `conv_unit`, `description`, `numerator`, `denominator`) VALUES (1, '2024-06-23 13:09:45', 1, '长风一梦8888', '2024-09-05 13:12:26', 1, '长风一梦8888', 0, 1, 'Piece', 2, '数量', '描述1', 100.00, 1000.00);
COMMIT;

-- ----------------------------
-- Table structure for wms_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `wms_warehouse`;
CREATE TABLE `wms_warehouse` (
                                 `id` bigint NOT NULL COMMENT 'ID',
                                 `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '仓库编号',
                                 `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '仓库名称',
                                 `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '仓库状态',
                                 `timezone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时区',
                                 `attribute` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性',
                                 `unit` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '计量单位',
                                 `contact_person` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
                                 `contact_email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
                                 `contact_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
                                 `postcode` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮编',
                                 `province_id` int DEFAULT NULL COMMENT '省份ID',
                                 `province_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                 `city_id` int DEFAULT NULL COMMENT '城市ID',
                                 `city_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                 `district_id` int DEFAULT NULL COMMENT '区县ID',
                                 `district_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                 `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                 `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                 `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                 `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                 `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                 `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='仓库表';

-- ----------------------------
-- Records of wms_warehouse
-- ----------------------------
BEGIN;
INSERT INTO `wms_warehouse` (`id`, `code`, `name`, `status`, `timezone`, `attribute`, `unit`, `contact_person`, `contact_email`, `contact_phone`, `postcode`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `remark`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1803800674882818049, 'WH01', '河北总仓', '1', 'UTC+8', '冷库', 'KG', 'cat dd', 'ddcat666@88.com', '13733600000', '201100', 130000, '河北省', 130200, '唐山市', 130202, '路南区', NULL, NULL, 0, '2024-06-20 22:42:49', 1, '长风一梦8888', '2024-06-21 15:20:56', 1, '长风一梦8888');
INSERT INTO `wms_warehouse` (`id`, `code`, `name`, `status`, `timezone`, `attribute`, `unit`, `contact_person`, `contact_email`, `contact_phone`, `postcode`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `remark`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1803801114382962689, 'WH02', '内蒙古分仓', '1', 'UTC+8', '常温库', 'KG', 'cat dd', 'ddcat666@88.com', '13733600001', '201100', 110000, '北京市', 110100, '市辖区', 110101, '东城区', NULL, NULL, 0, '2024-06-20 22:44:33', 1, '长风一梦8888', '2024-06-21 15:21:41', 1, '长风一梦8888');
INSERT INTO `wms_warehouse` (`id`, `code`, `name`, `status`, `timezone`, `attribute`, `unit`, `contact_person`, `contact_email`, `contact_phone`, `postcode`, `province_id`, `province_name`, `city_id`, `city_name`, `district_id`, `district_name`, `address`, `remark`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829421429238710274, '123213', '42-52 Export Street', '1', '213', '123123', '123', '测试3333', NULL, '13000000000', '4178', 120000, '天津市', 120100, '市辖区', 120102, '河东区', '42-52 Export Street', '123', 1, '2024-08-30 15:30:32', 1, '长风一梦8888', '2024-08-30 15:34:08', 1, '长风一梦8888');
COMMIT;

-- ----------------------------
-- Table structure for wms_workbench
-- ----------------------------
DROP TABLE IF EXISTS `wms_workbench`;
CREATE TABLE `wms_workbench` (
                                 `id` bigint NOT NULL COMMENT 'ID',
                                 `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '工作台编号',
                                 `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '工作台名称',
                                 `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '是否启用',
                                 `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '工作台类型',
                                 `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '工作台类别',
                                 `review_mode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '复核模式',
                                 `task_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务类型',
                                 `weight_mode` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '启用称重',
                                 `mac_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'MAC地址',
                                 `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                 `create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人姓名',
                                 `last_modify_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `last_modify_by` bigint DEFAULT NULL COMMENT '更新人',
                                 `last_modify_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人姓名',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='工作台表';

-- ----------------------------
-- Records of wms_workbench
-- ----------------------------
BEGIN;
INSERT INTO `wms_workbench` (`id`, `code`, `name`, `status`, `type`, `category`, `review_mode`, `task_type`, `weight_mode`, `mac_address`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829453647671713793, '123', '123', '1', '123', '123', '122321', '123', 'disabled', '1231', 1, '2024-08-30 17:38:34', 1, '长风一梦8888', '2024-08-30 17:41:26', 1, '长风一梦8888');
INSERT INTO `wms_workbench` (`id`, `code`, `name`, `status`, `type`, `category`, `review_mode`, `task_type`, `weight_mode`, `mac_address`, `deleted`, `create_time`, `create_by`, `create_name`, `last_modify_time`, `last_modify_by`, `last_modify_name`) VALUES (1829454221641236482, '12322', '1231', '1', '123', '123', '123', '123', 'disabled', '123', 1, '2024-08-30 17:40:51', 1, '长风一梦8888', '2024-08-30 17:41:24', 1, '长风一梦8888');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
