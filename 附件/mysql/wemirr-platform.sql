/*
 Navicat Premium Data Transfer

 Source Server         : localhost-docker
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : wemirr-platform

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 17/12/2024 14:26:22
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
  `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1864548132428439555 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息通知';

-- ----------------------------
-- Records of b_message_channel
-- ----------------------------
BEGIN;
INSERT INTO `b_message_channel` (`id`, `title`, `type`, `status`, `setting`, `tenant_id`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1864537666578714625, '系统内置', 'system', b'1', NULL, 1, '系统内置 webstock 消息', b'0', NULL, NULL, '2024-12-05 13:09:56', NULL, NULL, '2024-12-05 14:17:23');
INSERT INTO `b_message_channel` (`id`, `title`, `type`, `status`, `setting`, `tenant_id`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1864543691579056130, '213123', NULL, b'1', NULL, 1, '12312', b'0', NULL, NULL, '2024-12-05 13:33:52', NULL, NULL, '2024-12-05 05:58:16');
INSERT INTO `b_message_channel` (`id`, `title`, `type`, `status`, `setting`, `tenant_id`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1864548132428439554, '腾讯邮箱', 'email', b'1', '{\"id\": \"1864548132428439554\", \"host\": \"smtp.qq.com\", \"port\": \"465\", \"smtp\": {\"ssl\": true, \"auth\": true}, \"type\": \"email\", \"title\": \"腾讯邮箱\", \"status\": true, \"password\": \"123\", \"protocol\": \"smtp\", \"tenantId\": \"1\", \"username\": \"123@qq.com\", \"description\": \"描述\"}', 1, '描述', b'0', NULL, NULL, '2024-12-05 13:51:31', NULL, NULL, '2024-12-05 06:31:14');
COMMIT;

-- ----------------------------
-- Table structure for b_message_notify
-- ----------------------------
DROP TABLE IF EXISTS `b_message_notify`;
CREATE TABLE `b_message_notify` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `type` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息类型',
  `template_id` bigint DEFAULT NULL COMMENT '消息模板ID',
  `variables` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息变量',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '接收用户ID',
  `nickname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `subscribe` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订阅人 比如 邮箱,手机号,钉钉ID等',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1868884501967917059 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息通知';

-- ----------------------------
-- Records of b_message_notify
-- ----------------------------
BEGIN;
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868884501955334145, '系统消息', 'email', 1863835249327370241, '{\"username\":\"1\"}', '欢迎 1 来到 WP 系统', 1, '平台管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-17 13:02:42', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868884501963722753, '系统消息', 'ding-talk', 1863835249327370241, '{\"username\":\"1\"}', '欢迎 1 来到 WP 系统', 1, '平台管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-17 13:02:42', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868884501967917058, '系统消息', 'sms', 1863835249327370241, '{\"username\":\"1\"}', '欢迎 1 来到 WP 系统', 1, '平台管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-17 13:02:42', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for b_message_template
-- ----------------------------
DROP TABLE IF EXISTS `b_message_template`;
CREATE TABLE `b_message_template` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板编码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名称',
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板类型',
  `subject` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息标题（如邮件标题）',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息标题（如邮件标题）',
  `status` bit(1) DEFAULT b'1' COMMENT '状态（0=禁用;1=启用）',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1863835249327370242 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of b_message_template
-- ----------------------------
BEGIN;
INSERT INTO `b_message_template` (`id`, `code`, `name`, `type`, `subject`, `content`, `status`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1863835249327370241, '0001', '系统消息', 'email,ding-talk,sms', '系统消息', '欢迎 ${username} 来到 WP 系统', b'1', NULL, b'0', NULL, NULL, '2024-12-03 14:38:46', NULL, NULL, '2024-12-06 11:09:43');
COMMIT;

-- ----------------------------
-- Table structure for c_diff_log
-- ----------------------------
DROP TABLE IF EXISTS `c_diff_log`;
CREATE TABLE `c_diff_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `variables` json DEFAULT NULL COMMENT '变量信息，JSON格式',
  `service_name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '服务名称',
  `business_group` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '业务组',
  `business_tag` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '业务标签',
  `business_key` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '业务关键字',
  `description` text COLLATE utf8mb3_bin COMMENT '描述信息',
  `status` int DEFAULT NULL COMMENT '状态',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `created_by` bigint DEFAULT NULL COMMENT '创建者ID',
  `created_name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建者名称',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `extra` text COLLATE utf8mb3_bin COMMENT '额外信息，JSON格式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='通用差异日志表';

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
  `name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '名称',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

-- ----------------------------
-- Records of c_gen_demo
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_generate
-- ----------------------------
DROP TABLE IF EXISTS `c_generate`;
CREATE TABLE `c_generate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `root_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '根目录',
  `springdoc` bit(2) DEFAULT b'0' COMMENT '是否添加swagger2',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作者',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表名',
  `parent_package` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父包',
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模块名',
  `table_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表前缀',
  `api_url_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'api地址前缀',
  `logic_delete_field` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '逻辑删除字段',
  `platform_id` bigint DEFAULT '0' COMMENT '平台ID',
  `deleted` bit(2) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1867459734991794178 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成器';

-- ----------------------------
-- Records of c_generate
-- ----------------------------
BEGIN;
INSERT INTO `c_generate` (`id`, `root_dir`, `springdoc`, `author`, `table_name`, `parent_package`, `module_name`, `table_prefix`, `api_url_prefix`, `logic_delete_field`, `platform_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (6, '/Users/battcn/Work/Company/WEMIRR/idea-workspace/wemirr-platform/wemirr-platform-suite/target', b'00', 'Levin', 't_user', 'com.wemirr.platform', 'authority', 't_', '/api/users', 'locked', 1, b'01', 0, NULL, '2020-11-06 09:34:40', 1, '平台管理员', '2024-12-13 13:57:01');
INSERT INTO `c_generate` (`id`, `root_dir`, `springdoc`, `author`, `table_name`, `parent_package`, `module_name`, `table_prefix`, `api_url_prefix`, `logic_delete_field`, `platform_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (31, '/Users/battcn/Work/Company/WEMIRR/idea-workspace/wemirr-platform/wemirr-platform-suite/target', b'00', 'Levin', 't_user', 'com.test', 'User', 't_', '/api/users', 'locked', 1, b'01', 2, '不告诉你', '2020-11-06 09:34:40', 1, '平台管理员', '2024-12-13 14:33:34');
INSERT INTO `c_generate` (`id`, `root_dir`, `springdoc`, `author`, `table_name`, `parent_package`, `module_name`, `table_prefix`, `api_url_prefix`, `logic_delete_field`, `platform_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1706551921692655618, '/Users/battcn/Work/Company/WEMIRR/idea-workspace/wemirr-platform/wemirr-platform-suite/target', b'00', '长风一梦8888', 'c_generate', 'com.wemirr.platform.test', 'common', 'c_', '/api', NULL, 0, b'01', 1, '长风一梦8888', NULL, 1, '平台管理员', '2024-12-13 13:57:03');
INSERT INTO `c_generate` (`id`, `root_dir`, `springdoc`, `author`, `table_name`, `parent_package`, `module_name`, `table_prefix`, `api_url_prefix`, `logic_delete_field`, `platform_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1867459734991794177, '/Users/battcn/Work/Company/WEMIRR/idea-workspace/wemirr-platform/wemirr-platform-suite/src/main/java', b'00', 'Levin', 'c_gen_demo', 'com.wemirr.platform.suite', 'gen', 'c_', '/suite/gen', NULL, 0, b'00', 1, '平台管理员', '2024-12-13 14:41:11', 1, '平台管理员', '2024-12-13 14:44:50');
COMMIT;

-- ----------------------------
-- Table structure for c_i18n_data
-- ----------------------------
DROP TABLE IF EXISTS `c_i18n_data`;
CREATE TABLE `c_i18n_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '唯一标识 = 业务:关键词',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态0：未删除，1：已删除',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1724355494514126850 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国际化信息';

-- ----------------------------
-- Records of c_i18n_data
-- ----------------------------
BEGIN;
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 'SEX.1', '性别->男', 1, b'0', 0, NULL, '2024-07-19 09:06:38', 0, NULL, '2024-07-19 09:18:35');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 'SEX.2', '性别->女', 1, b'0', 0, NULL, '2024-07-19 09:06:38', 0, NULL, '2024-07-19 09:18:35');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1721821542981042178, 'i18n.db', '测试中文内容 {i18n.demo}', 1, b'0', 1, '长风一梦0000', '2023-11-07 17:27:00', NULL, NULL, '2024-12-06 17:09:22');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1724355494514126849, '123', '213', 1, b'1', 1, '长风一梦0000', '2023-11-14 17:16:02', 0, NULL, '2023-11-14 09:16:04');
COMMIT;

-- ----------------------------
-- Table structure for c_i18n_locale_message
-- ----------------------------
DROP TABLE IF EXISTS `c_i18n_locale_message`;
CREATE TABLE `c_i18n_locale_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint DEFAULT NULL COMMENT 'i18n_data.id',
  `locale` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '语言',
  `message` varchar(255) DEFAULT NULL COMMENT '文本值，可以使用 {} 加角标，作为占位符',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1864960309274497027 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国际化信息';

-- ----------------------------
-- Records of c_i18n_locale_message
-- ----------------------------
BEGIN;
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (10, 1, 'zh_CN', '男', 1, NULL, '2024-07-19 09:07:18');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (11, 1, 'en_US', 'Male', 1, NULL, '2024-07-19 09:07:31');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (20, 2, 'zh_CN', '女', 0, NULL, '2024-07-19 09:19:03');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (21, 2, 'en_US', 'Female', 0, NULL, '2024-07-19 09:19:05');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (1864960309261914113, 1721821542981042178, 'en_US', 'English Context', NULL, NULL, '2024-12-06 17:09:22');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (1864960309274497026, 1721821542981042178, 'zh_CN', '测试中文内容 {i18n.demo}', NULL, NULL, '2024-12-06 17:09:22');
COMMIT;

-- ----------------------------
-- Table structure for c_login_log
-- ----------------------------
DROP TABLE IF EXISTS `c_login_log`;
CREATE TABLE `c_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户编码',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录IP',
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录地点',
  `client_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人客户端ID',
  `principal` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人账号',
  `platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台',
  `engine` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '引擎类型',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作系统',
  `login_type` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录类型',
  `created_by` bigint DEFAULT NULL,
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录日志';

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
  `tenant_code` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户编码',
  `module` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志模块',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作描述',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作IP（支持IPv6）',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `trace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志链路追踪id日志标志',
  `action` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类路径',
  `uri` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求地址',
  `http_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'GET' COMMENT '请求类型ENUM(''GET'', ''POST'', ''PUT'', ''DELETE'', ''PATCH'', ''TRACE'', ''HEAD'', ''OPTIONS'')',
  `request` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求参数',
  `response` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '返回值',
  `message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '执行消息',
  `status` bit(1) DEFAULT NULL COMMENT '日志状态（true=正常;false=异常）',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `duration` bigint DEFAULT '0' COMMENT '消耗时间',
  `browser` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器名称',
  `os` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作系统',
  `engine` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器引擎',
  `platform` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '平台信息',
  `token` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求令牌',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统日志';

-- ----------------------------
-- Records of c_opt_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_data_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_permission_resource`;
CREATE TABLE `sys_data_permission_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `owner_id` bigint NOT NULL COMMENT '拥有者',
  `owner_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '拥有类型（角色，用户）',
  `data_id` bigint NOT NULL COMMENT '数据ID',
  `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '数据类型（机构、角色、租户等等）',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1813866396912828422 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='数据权限资源表';

-- ----------------------------
-- Records of sys_data_permission_resource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编码\r\n一颗树仅仅有一个统一的编码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `type` tinyint DEFAULT '0' COMMENT '字典类型(0=平台字典;1=租户字典)',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置角色',
  `sequence` tinyint DEFAULT NULL COMMENT '排序',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1865624518832398339 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 'NATION', '民族', 0, '123', b'1', b'1', 0, b'0', 0, '系统管理员', '2019-06-01 09:42:50', 0, '系统管理员', '2024-12-08 04:21:19');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 'POSITION_STATUS', '在职状态', 0, '11', b'1', b'1', 1, b'0', 0, '系统管理员', '2019-06-04 11:37:15', 0, '系统管理员', '2024-12-08 04:21:19');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, 'EDUCATION', '学历', 0, '', b'1', b'1', 2, b'0', 0, '系统管理员', '2019-06-04 11:33:52', 0, '系统管理员', '2024-12-08 04:21:19');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (4, 'AREA_LEVEL', '行政区级', 0, '', b'1', b'1', 3, b'0', 0, '系统管理员', '2020-01-20 15:12:05', 0, '系统管理员', '2024-12-08 04:21:19');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (5, 'ORG_TYPE', '机构类型', 0, '', b'1', b'1', 4, b'0', 0, '系统管理员', '2020-08-19 15:02:57', 0, '系统管理员', '2024-12-08 04:21:19');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (6, 'SEX', '性别', 0, '性别', b'1', b'1', 5, b'0', 0, '系统管理员', '2020-11-18 07:24:43', 0, '系统管理员', '2024-12-08 04:21:19');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (7, 'NOTICE', '消息类型', 0, '消息类型', b'1', b'1', 6, b'0', 0, '系统管理员', '2020-11-19 02:57:22', 0, '系统管理员', '2024-12-08 04:21:19');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (8, 'POSITION_TYPE', '岗位类型', 0, NULL, b'1', b'1', 7, b'0', 0, '系统管理员', '2021-07-16 03:34:04', 0, '系统管理员', '2024-12-08 10:24:01');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (9, 'COLOR', '颜色', 0, NULL, b'1', b'1', 8, b'0', 0, '系统管理员', '2021-07-16 04:12:21', 0, '系统管理员', '2024-12-08 04:21:19');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10, 'INDUSTRY', '行业类型', 0, '行业类型', b'1', b'1', 9, b'0', 0, '系统管理员', '2021-07-31 08:10:07', 0, '系统管理员', '2024-12-08 04:21:19');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1686979951801745410, 'CONTRACT_SEAL_TYPE', '用印类型', 1, NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-08-03 05:58:57', NULL, NULL, '2024-12-08 10:27:50');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1698634189967577089, 'PAYMENT_METHOD', '付款方法', 1, NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-04 09:48:44', NULL, NULL, '2024-12-08 10:27:50');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1698634230920761345, 'PAYMENT_TYPE', '付款类型', 1, NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-04 09:48:54', NULL, NULL, '2024-12-08 10:27:50');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1699341989447098369, 'TMS_PLATE_COLOR', 'TMS车牌颜色', 1, 'TMS车牌颜色', b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-06 08:41:17', NULL, NULL, '2024-12-08 10:27:50');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1699343995867914241, 'TMS_ENERGY_TYPE', 'TMS能源类型', 1, NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-06 08:49:15', NULL, NULL, '2024-12-08 10:27:50');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1700070256599982081, 'TMS_EXPENSE_ITEM', 'TMS费用项目', 1, NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-08 08:55:09', NULL, NULL, '2024-12-08 10:27:50');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `readonly`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865614807147950081, 'TENANT-DICT', '租户字典', 1, '测试租户字典推送和同步', b'1', b'1', 1, b'0', 1, '平台管理员', '2024-12-08 12:30:06', NULL, NULL, '2024-12-08 10:27:50');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dict_id` bigint DEFAULT NULL COMMENT '类型ID',
  `dict_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型',
  `value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '编码',
  `label` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '颜色',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '描述',
  `sequence` int DEFAULT '1' COMMENT '排序',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dict_code_item_code_uniq` (`dict_code`,`value`) USING BTREE COMMENT '字典编码与字典项目编码联合唯一'
) ENGINE=InnoDB AUTO_INCREMENT=1865928793793933314 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 4, 'AREA_LEVEL', 'COUNTRY', '国家', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-01-20 15:12:57', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 4, 'AREA_LEVEL', 'PROVINCE', '省份', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2020-01-20 15:13:45', 1, '长风一梦', '2021-07-31 08:18:51');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, 4, 'AREA_LEVEL', 'CITY', '地市', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2020-01-20 15:14:16', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (4, 4, 'AREA_LEVEL', 'COUNTY', '区县', b'1', 'warning', '', 4, b'0', 0, '系统管理员', '2020-01-20 15:14:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (5, 5, 'ORG_TYPE', '01', '单位', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-08-19 15:03:40', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (6, 5, 'ORG_TYPE', '02', '部门', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-08-19 15:03:59', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (38, 3, 'EDUCATION', 'ZHUANKE', '专科', b'1', 'warning', '', 4, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (39, 3, 'EDUCATION', 'COLLEGE', '本科', b'1', 'warning', '', 5, b'0', 0, '系统管理员', '2019-06-04 11:36:19', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (40, 3, 'EDUCATION', 'SUOSHI', '硕士', b'1', 'warning', '', 6, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (41, 3, 'EDUCATION', 'BOSHI', '博士', b'1', 'warning', '', 7, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (42, 3, 'EDUCATION', 'BOSHIHOU', '博士后', b'1', 'warning', '', 8, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (43, 1, 'NATION', 'mz_hanz', '汉族', b'1', 'warning', '', 0, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (44, 1, 'NATION', 'mz_zz', '壮族', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (45, 1, 'NATION', 'mz_mz', '满族', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (46, 1, 'NATION', 'mz_hz', '回族', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (47, 1, 'NATION', 'mz_miaoz', '苗族', b'1', 'warning', '', 4, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (48, 1, 'NATION', 'mz_wwez', '维吾尔族', b'1', 'warning', '', 5, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (49, 1, 'NATION', 'mz_tjz', '土家族', b'1', 'warning', '', 6, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (50, 1, 'NATION', 'mz_yz', '彝族', b'1', 'warning', '', 7, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (51, 1, 'NATION', 'mz_mgz', '蒙古族', b'1', 'warning', '', 8, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (52, 1, 'NATION', 'mz_zhangz', '藏族', b'1', 'warning', '', 9, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (53, 1, 'NATION', 'mz_byz', '布依族', b'1', 'warning', '', 10, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (54, 1, 'NATION', 'mz_dz', '侗族', b'1', 'warning', '', 11, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (55, 1, 'NATION', 'mz_yaoz', '瑶族', b'1', 'warning', '', 12, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (56, 1, 'NATION', 'mz_cxz', '朝鲜族', b'1', 'warning', '', 13, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (57, 1, 'NATION', 'mz_bz', '白族', b'1', 'warning', '', 14, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (58, 1, 'NATION', 'mz_hnz', '哈尼族', b'1', 'warning', '', 15, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (59, 1, 'NATION', 'mz_hskz', '哈萨克族', b'1', 'warning', '', 16, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (60, 1, 'NATION', 'mz_lz', '黎族', b'1', 'warning', '', 17, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (61, 1, 'NATION', 'mz_daiz', '傣族', b'1', 'warning', '', 18, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (62, 1, 'NATION', 'mz_sz', '畲族', b'1', 'warning', '', 19, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (63, 1, 'NATION', 'mz_llz', '傈僳族', b'1', 'warning', '', 20, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (64, 1, 'NATION', 'mz_glz', '仡佬族', b'1', 'warning', '', 21, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (65, 1, 'NATION', 'mz_dxz', '东乡族', b'1', 'warning', '', 22, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (66, 1, 'NATION', 'mz_gsz', '高山族', b'1', 'warning', '', 23, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (67, 1, 'NATION', 'mz_lhz', '拉祜族', b'1', 'warning', '', 24, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (68, 1, 'NATION', 'mz_shuiz', '水族', b'1', 'warning', '', 25, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (69, 1, 'NATION', 'mz_wz', '佤族', b'1', 'warning', '', 26, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (70, 1, 'NATION', 'mz_nxz', '纳西族', b'1', 'warning', '', 27, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (71, 1, 'NATION', 'mz_qz', '羌族', b'1', 'warning', '', 28, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (72, 1, 'NATION', 'mz_tz', '土族', b'1', 'warning', '', 29, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (73, 1, 'NATION', 'mz_zlz', '仫佬族', b'1', 'warning', '', 30, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (74, 1, 'NATION', 'mz_xbz', '锡伯族', b'1', 'warning', '', 31, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (75, 1, 'NATION', 'mz_kehzz', '柯尔克孜族', b'1', 'warning', '', 32, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (76, 1, 'NATION', 'mz_dwz', '达斡尔族', b'1', 'warning', '', 33, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (77, 1, 'NATION', 'mz_jpz', '景颇族', b'1', 'warning', '', 34, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (78, 1, 'NATION', 'mz_mlz', '毛南族', b'1', 'warning', '', 35, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (79, 1, 'NATION', 'mz_slz', '撒拉族', b'1', 'warning', '', 36, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (80, 1, 'NATION', 'mz_tjkz', '塔吉克族', b'1', 'warning', '', 37, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (81, 1, 'NATION', 'mz_acz', '阿昌族', b'1', 'warning', '', 38, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (82, 1, 'NATION', 'mz_pmz', '普米族', b'1', 'warning', '', 39, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (83, 1, 'NATION', 'mz_ewkz', '鄂温克族', b'1', 'warning', '', 40, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (84, 1, 'NATION', 'mz_nz', '怒族', b'1', 'warning', '', 41, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (85, 1, 'NATION', 'mz_jz', '京族', b'1', 'warning', '', 42, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (86, 1, 'NATION', 'mz_jnz', '基诺族', b'1', 'warning', '', 43, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (87, 1, 'NATION', 'mz_daz', '德昂族', b'1', 'warning', '', 44, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (88, 1, 'NATION', 'mz_baz', '保安族', b'1', 'warning', '', 45, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (89, 1, 'NATION', 'mz_elsz', '俄罗斯族', b'1', 'warning', '', 46, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (90, 1, 'NATION', 'mz_ygz', '裕固族', b'1', 'warning', '', 47, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (91, 1, 'NATION', 'mz_wzbkz', '乌兹别克族', b'1', 'warning', '', 48, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (92, 1, 'NATION', 'mz_mbz', '门巴族', b'1', 'warning', '', 49, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (93, 1, 'NATION', 'mz_elcz', '鄂伦春族', b'1', 'warning', '', 50, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (94, 1, 'NATION', 'mz_dlz', '独龙族', b'1', 'warning', '', 51, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (95, 1, 'NATION', 'mz_tkez', '塔塔尔族', b'1', 'warning', '', 52, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (96, 1, 'NATION', 'mz_hzz', '赫哲族', b'1', 'warning', '', 53, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (97, 1, 'NATION', 'mz_lbz', '珞巴族', b'1', 'warning', '', 54, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (98, 1, 'NATION', 'mz_blz', '布朗族', b'1', 'warning', '', 55, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (99, 2, 'POSITION_STATUS', 'WORKING', '在职', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2019-06-04 11:38:16', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (100, 2, 'POSITION_STATUS', 'QUIT', '离职', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2019-06-04 11:38:50', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (101, 4, 'AREA_LEVEL', 'TOWNS', '乡镇', b'1', 'warning', '', 5, b'0', 0, '系统管理员', '2020-03-09 23:33:46', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (102, 3, 'EDUCATION', 'XIAOXUE', '小学', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-03-09 23:34:13', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (103, 3, 'EDUCATION', 'ZHONGXUE', '中学', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2020-03-09 23:34:32', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (104, 3, 'EDUCATION', 'GAOZHONG', '高中', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2020-03-09 23:34:40', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (105, 3, 'EDUCATION', 'QITA', '其他', b'1', 'warning', '', 20, b'0', 0, '系统管理员', '2020-03-09 23:34:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (106, 1, 'NATION', 'mz_qt', '其他', b'1', 'warning', '', 100, b'0', 0, '系统管理员', '2020-03-09 23:38:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (107, 2, 'POSITION_STATUS', 'LEAVE', '请假', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2020-03-09 23:39:30', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (108, 6, 'SEX', '1', '男', b'1', 'success', '男', 1, b'0', 0, '系统管理员', '2020-11-18 07:24:58', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (109, 6, 'SEX', '2', '女', b'1', 'error', '女', 1, b'0', 0, '系统管理员', '2020-11-18 07:25:07', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (110, 7, 'NOTICE', '0', '通知', b'1', 'success', '通知', 1, b'0', 0, '系统管理员', '2020-11-19 02:58:37', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (111, 7, 'NOTICE', '1', '消息', b'1', 'success', '消息', 1, b'0', 0, '系统管理员', '2020-11-19 02:58:55', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (112, 7, 'NOTICE', '2', '待办', b'1', 'error', '待办', 1, b'0', 0, '系统管理员', '2020-11-19 02:59:05', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (113, 8, 'POSITION_TYPE', '0', '基层', b'1', 'success', '1111', 1, b'0', 0, '系统管理员', '2021-07-16 04:06:54', 0, '系统管理员', '2024-12-08 10:23:52');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (114, 9, 'COLOR', 'success', '成功', b'1', 'success', '成功', 1, b'0', 0, '系统管理员', '2021-07-16 04:12:47', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (115, 9, 'COLOR', 'warning', '警告', b'1', 'warning', '警告', 1, b'0', 0, '系统管理员', '2021-07-16 04:15:06', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (116, 9, 'COLOR', 'error', '错误', b'1', 'error', '红色', 1, b'0', 0, '系统管理员', '2021-07-16 04:16:55', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (118, 8, 'POSITION_TYPE', '1', '中层', b'1', 'success', '中层', 1, b'0', 0, '系统管理员', '2021-07-16 04:59:51', 0, '系统管理员', '2024-12-08 10:23:49');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (119, 8, 'POSITION_TYPE', '2', '高层', b'1', 'warning', '高层', 1, b'0', 0, '系统管理员', '2021-07-16 05:00:07', 0, '系统管理员', '2024-12-08 10:23:50');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (120, 10, 'INDUSTRY', '1', '医疗', b'1', 'success', '医疗行业', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:08', NULL, NULL, '2024-12-08 11:37:08');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (121, 10, 'INDUSTRY', '2', '教育', b'1', 'success', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:17', NULL, NULL, '2021-07-31 08:20:14');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (122, 10, 'INDUSTRY', '3', '金融', b'1', 'success', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:28', NULL, NULL, '2021-07-31 08:20:17');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (123, 10, 'INDUSTRY', '4', '互联网', b'1', 'warning', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:40', 1, '长风一梦', '2021-07-31 08:20:17');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (124, 10, 'INDUSTRY', '5', '电商', b'1', 'warning', '', 1, b'1', 1, '长风一梦', '2021-07-31 08:17:58', 1, '长风一梦000011', '2024-07-17 17:41:50');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613636006027265, 1865614807147950081, 'TENANT-DICT', 'A', '租户A', b'1', NULL, '租户A', 1, b'0', NULL, NULL, '2024-12-08 12:25:27', 0, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085314, 1865614807147950081, 'TENANT-DICT', 'B', '租户B', b'1', NULL, '租户B', 1, b'0', NULL, NULL, '2024-12-08 12:25:35', 0, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085661, 1686979951801745410, 'CONTRACT_SEAL_TYPE', 'contractSeal', '合同章', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-08-03 06:01:41', 1, '长风一梦8888', '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085662, 1686979951801745410, 'CONTRACT_SEAL_TYPE', 'officialSeal', '公章', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-08-03 06:03:02', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085663, 1698634230920761345, 'PAYMENT_TYPE', 'L0104', '退质保金-供应商', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:57:52', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085664, 1698634230920761345, 'PAYMENT_TYPE', 'L010', '预付款', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:58:19', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085665, 1698634230920761345, 'PAYMENT_TYPE', 'L0106', '退供应商履约保证金', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:58:47', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085666, 1698634189967577089, 'PAYMENT_METHOD', '1', '电汇', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:59:08', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085667, 1698634189967577089, 'PAYMENT_METHOD', '2', '支票', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:59:15', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085668, 1699341989447098369, 'TMS_PLATE_COLOR', 'RED', '红色', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-06 08:41:59', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085669, 1699341989447098369, 'TMS_PLATE_COLOR', 'BLUE', '蓝色', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-06 08:42:07', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085670, 1699341989447098369, 'TMS_PLATE_COLOR', 'YELLOW', '黄色', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-06 08:42:36', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085671, 1699343995867914241, 'TMS_ENERGY_TYPE', '0', '汽油', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-06 08:50:01', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085672, 1699343995867914241, 'TMS_ENERGY_TYPE', '1', '柴油', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-06 08:50:10', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085673, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'annually_inspect', '年检', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-08 08:55:52', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085674, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'annually_review', '年审', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-08 08:56:07', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085675, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'strong_insurance', '强险', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-08 08:56:20', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085676, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'commercial_insurance', '商业险', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-08 08:56:41', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085677, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'gps_fee', 'GPS费用', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-08 08:56:57', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865928793793933313, 1865614807147950081, 'TENANT-DICT', '123', '123', b'1', NULL, '123', 1, b'1', NULL, NULL, '2024-12-09 09:17:46', NULL, NULL, '2024-12-09 09:17:51');
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
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1865599612409217026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组织';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (100, '鏖战八方', 1, '鏖战', '0746-8485560转1', 0, '0', 1, b'1', b'0', '初始化数据', 1, '1', '2019-07-10 17:02:18', 1, '1', '2023-11-22 08:21:42');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10001, '鏖战八方上海分部', 1, '鏖战上海', '0746-8485560转2', 100, '0,100', 0, b'1', b'0', '初始化数据', 1, '1', '2019-08-06 09:10:53', NULL, NULL, '2024-12-07 13:39:58');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10002, '鏖战八方北京分部', 1, '鏖战北京', '0746-8485560转3', 100, '0,100', 1, b'1', b'0', '初始化数据', 1, '1', '2019-11-07 16:13:09', 1, '1', '2023-11-22 08:23:13');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10003, '管理层', 1, '管理层', '0746-8485560转4', 100, '0,100', 3, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:34:39', 3, '3', '2023-11-22 08:23:14');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10004, '总经办', 1, '', '0746-8485560', 100, '0,100', 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:35:37', 1, '长风一梦', '2023-11-22 08:23:15');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10005, '财务部', 1, '', NULL, 100, '0,100', 4, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:37:17', 3, '3', '2023-11-22 08:23:17');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10006, '市场部', 1, '', NULL, 100, '0,100', 5, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:37:37', 3, '3', '2023-11-22 08:23:21');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10101, '综合部', 1, '综合部', '0746-8485560转7', 10001, '0,100,10001', 0, b'1', b'0', '前台&HR11', 3, '3', '2019-11-12 11:34:27', 1, '长风一梦8888', '2023-11-22 08:23:23');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10102, '研发部', 1, '研发部', '0746-8485560转5', 10001, '0,100,10001', 5, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:21', 2, '不告诉你', '2023-11-22 08:23:25');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10103, '产品部', 1, '产品部', '0746-8485560转6', 10001, '0,100,10001', 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:31', NULL, NULL, '2024-12-07 13:40:22');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10201, '综合部', 1, '综合部', NULL, 10002, '0,100,10002', 0, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:39', 3, '3', '2023-11-22 08:23:30');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10202, '测试部', 1, '测试部', NULL, 10002, '0,100,10002', 1, b'1', b'0', '初始化数据', 2, '不告诉你', '2020-10-29 06:39:09', 2, '不告诉你', '2023-11-22 08:23:32');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10203, '研发部', 1, '', NULL, 10002, '0,100,10002', 0, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:50', 3, '3', '2023-11-22 08:23:34');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10204, '销售部', 1, '', NULL, 10002, '0,100,10002', 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:39:00', 3, '3', '2023-11-22 08:23:35');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865599612409217025, '深圳腾讯科技股份有限公司', 3, '', NULL, 0, NULL, 0, b'1', b'0', '不可删除不可修改', NULL, NULL, '2024-12-08 11:29:43', 0, NULL, NULL);
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
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1865020143000756227 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='岗位';

-- ----------------------------
-- Records of sys_position
-- ----------------------------
BEGIN;
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (100, 1, '总经理', 'CEO', 0, 100, b'1', '总部-1把手2', b'0', '2019-07-10 17:03:03', NULL, 1, NULL, NULL, '2024-12-06 17:03:57');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (101, 1, '副总经理', NULL, 1, 10001, b'1', '总部-2把手', b'0', '2019-07-22 17:07:55', NULL, 1, 1, '长风一梦', '2021-07-28 02:58:53');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (103, 1, '研发经理', NULL, 1, 10002, b'1', '子公司-研发部老大', b'0', '2019-11-07 16:08:49', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:06');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (104, 1, '副总经理', NULL, 1, 10001, b'1', '子公司-老大', b'0', '2019-11-16 09:51:45', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:29');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (106, 1, '产品经理', NULL, 1, 10002, b'1', '子公司-产品部老大', b'0', '2019-11-16 09:53:27', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (107, 1, '人事经理', NULL, 1, 10002, b'1', '子公司-综合老大', b'0', '2019-11-16 09:54:43', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (108, 1, 'Java研发', 'CTI', 1, 10203, b'1', '普通员工', b'0', '2019-11-16 09:55:04', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:38');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1011, 1, 'UI工程师', 'CTI', 1, 10102, b'1', '普通员工', b'0', '2019-11-16 09:55:40', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:52');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1012, 1, '运维工程师', 'CTI', 1, 10203, b'1', '普通员工', b'0', '2019-11-16 09:55:53', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:45');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1013, 1, '行政经理', NULL, 1, 10003, b'1', '普通员工', b'0', '2019-11-16 09:56:04', NULL, 3, 1, '长风一梦8888', '2021-11-27 06:36:22');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1014, 1, '人事经理', NULL, 1, 10004, b'1', '北京分公司-综合部老大', b'0', '2019-11-16 09:56:38', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:36');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1015, 1, '研发经理', NULL, 1, 10102, b'1', '北京分公司-研发部老大', b'0', '2019-11-16 09:57:07', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:28');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1016, 1, '销售经理', NULL, 1, 10004, b'1', '北京销售部老大', b'0', '2019-11-16 09:57:40', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:19');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10121, 1, '销售总监', NULL, 1, 10005, b'1', '总部2把手', b'0', '2019-11-16 09:59:10', NULL, 3, 1, '长风一梦', '2021-07-29 02:36:31');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10122, 1, '销售员工', NULL, 1, 10006, b'1', '普通员工', b'0', '2019-11-16 09:58:41', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:11');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10123, 1, '财务总监', NULL, 1, 10003, b'1', '总部2把手', b'0', '2019-11-16 09:59:39', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:06');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10124, 1, '市场经理', NULL, 1, 10003, b'1', '总部市场部老大', b'0', '2019-11-16 10:00:03', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:01');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10128, 1, '前端工程师', 'CTI', 1, 10102, b'1', '普通员工', b'0', '2019-11-16 10:00:43', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:20:53');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865020143000756226, NULL, '1', '1', 0, 10101, b'1', '213', b'1', '2024-12-06 21:07:07', NULL, NULL, NULL, NULL, '2024-12-06 21:07:11');
COMMIT;

-- ----------------------------
-- Table structure for sys_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_registered_client`;
CREATE TABLE `sys_registered_client` (
  `id` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `client_id` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `client_secret_expires_at` timestamp NULL DEFAULT NULL,
  `client_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `client_authentication_methods` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `grant_types` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `redirect_uris` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `post_logout_redirect_uris` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `scopes` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `client_settings` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `token_settings` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `status` bit(1) DEFAULT b'1' COMMENT '0=禁用;1=启用',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;

-- ----------------------------
-- Records of sys_registered_client
-- ----------------------------
BEGIN;
INSERT INTO `sys_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`, `client_secret_expires_at`, `client_name`, `client_authentication_methods`, `grant_types`, `redirect_uris`, `post_logout_redirect_uris`, `scopes`, `client_settings`, `token_settings`, `status`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES ('1', 'pc-web', '2024-09-11 06:39:32', 'pc-web', NULL, 'PC-WEB 管理系统', 'client_secret_basic', 'password,sms', 'http://127.0.0.1:5001/oauth2/code,https://www.baidu.com', '', '[[[wp]]]', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"accessTokenTimeToLive\":120}', b'1', b'0', NULL, NULL, '2023-09-15 16:43:14', NULL, NULL, '2024-12-07 12:24:30');
INSERT INTO `sys_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`, `client_secret_expires_at`, `client_name`, `client_authentication_methods`, `grant_types`, `redirect_uris`, `post_logout_redirect_uris`, `scopes`, `client_settings`, `token_settings`, `status`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES ('1865050872845828098', 'uniapp', '2023-08-18 09:55:44', 'pc-web', NULL, 'APP 客户端', NULL, 'password,sms', 'http://127.0.0.1:5001/oauth2/code,https://www.baidu.com', NULL, '[[[[wp]]]]', NULL, '{\"accessTokenTimeToLive\":120}', b'1', b'0', NULL, NULL, '2024-12-06 23:09:14', NULL, NULL, '2024-12-07 12:24:26');
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
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
  `global` bit(1) DEFAULT b'0' COMMENT '公共资源\nTrue是无需分配所有人就可以访问的',
  `visible` bit(1) DEFAULT b'1' COMMENT '0=隐藏;1=显示',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `INX_STATUS` (`global`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1866028768334876675 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, '平台管理', NULL, 0, '/plat', 'BasicLayout', 1, 'clarity-thin-client-line', 'directory', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, '系统管理', NULL, 0, '/sys', 'BasicLayout', 3, 'ant-design:dashboard-filled', 'directory', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (50, '开发平台', NULL, 0, '/dev', 'BasicLayout', 50, 'ant-design:appstore-add-outlined', 'directory', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (104, '租户管理', NULL, 1, '/plat/tenant', NULL, 104, 'ant-design:code-sandbox-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (107, '数据配置', NULL, 1, '/plat/setting', NULL, 107, 'file-icons:config-coffeescript', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (301, '组织架构', NULL, 3, '/sys/structure', NULL, 301, 'ant-design:user-outlined', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (302, '权限管理', NULL, 3, '/sys/auth', NULL, 302, 'ant-design:security-scan-outlined', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (303, '设置中心', NULL, 3, '/sys/setting', NULL, 303, 'file-icons:config-coffeescript', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (306, '消息管理', NULL, 3, '/sys/message', NULL, 306, 'mi:message', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (310, '运维监控', NULL, 3, '/sys/monitor', NULL, 310, 'lucide:monitor-cog', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (320, '存储管理', NULL, 3, '/sys/oss', NULL, 320, 'carbon:ibm-cloud-vpc-file-storage', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (350, '安全中心', NULL, 1, '/plat/security', NULL, 350, 'carbon-application', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (5003, '在线开发', NULL, 50, '/dev/online', '', 5003, 'material-symbols:home-work-outline-rounded', 'menu', b'1', NULL, b'0', b'0', b'1', '', b'0', NULL, NULL, '2024-12-12 13:11:50', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (5005, '网关管理', NULL, 50, '/dev/gateway', NULL, 5005, 'ant-design:gateway-outlined', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (5008, '代码生成', NULL, 50, '/dev/gen', '/wemirr/development/generate/code/index', 5008, 'streamline:ai-generate-variation-spark-solid', 'menu', b'1', NULL, b'0', b'0', b'1', '', b'0', NULL, NULL, '2024-12-12 13:11:50', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (5050, '定时任务', NULL, 50, '/dev/job', 'http://localhost:8871/snail-job', 5050, 'arcticons:jobstreet', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10401, '租户中心', 'tenant:list', 104, '/plat/tenant/list', '/wemirr/platform/tenant/index', 10401, 'ant-design:code-sandbox-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10402, '多数据源', NULL, 104, '/plat/db/list', '/wemirr/platform/db/index', 10402, 'ant-design:database-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', NULL, NULL, '2024-12-06 17:07:02');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10706, '数据字典', 'dict:list', 107, '/plat/setting/dict', '/wemirr/platform/basic/dict/index', 10706, 'bx-bxs-data', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10707, '地区信息', NULL, 107, '/plat/setting/area', '/wemirr/platform/basic/area/index', 10707, 'ant-design:area-chart-outlined', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30101, '用户管理', 'sys:user:page', 301, '/sys/user', '/wemirr/system/user/index', 30101, 'heroicons-outline:user-group', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30102, '机构管理', 'sys:org:page', 301, '/sys/org', '/wemirr/system/org/index', 30102, 'codicon-organization', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30103, '岗位管理', 'sys:position:page', 301, '/sys/position', '/wemirr/system/position/index', 30103, 'ant-design:usergroup-add-outlined', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30201, '菜单管理', 'sys:menu:page', 302, '/system/menu', '/wemirr/system/auth/menu/index', 30201, 'ant-design:menu-unfold-outlined', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30202, '角色管理', 'sys:role:page', 302, '/system/role', '/wemirr/system/auth/role/index', 30202, 'eos-icons:role-binding-outlined', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30301, '租户字典', 'tenant:dict:list', 303, '/sys/basic-dict', '/wemirr/system/basic/dict/index', 30301, 'bx-bxs-data', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30303, '语言管理', 'i18n:page', 303, '/sys/basic-i18n', '/wemirr/system/basic/i18n/index', 30303, 'cil:language', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30604, '通道设置', 'message:channel:setting', 306, '/sys/message/channel-setting', '/wemirr/system/message/setting/index', 30604, 'fluent:channel-48-regular', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30605, '消息模板', 'message:template:page', 306, '/sys/message/template', '/wemirr/system/message/template', 30605, 'fluent:mail-template-24-regular', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30606, '消息列表', 'message:list', 306, '/sys/message/list', '/wemirr/system/message/message-list', 30606, 'mdi:envelope-outline', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30607, '消息推送', 'message:publish', 306, '/sys/message/publish', '/wemirr/system/message/publish', 30607, 'arcticons:efa-publish', 'menu', b'1', NULL, b'0', b'0', b'0', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (30608, '我的消息', 'message:subscribe-list', 306, '/sys/message/subscribe-list', '/wemirr/system/message/subscribe-list', 30608, 'ant-design:comment-outlined', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (31003, '在线用户', 'monitor:online:token-list', 310, '/sys/monitor/online', '/wemirr/system/monitor/online/index', 31003, 'mdi:account-online-outline', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (31005, '登录日志', 'monitor:log:login', 310, '/sys/monitor/login-log', '/wemirr/system/log/login-log', 31005, 'mdi:account-security-outline', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (31006, '操作日志', 'monitor:log:opt', 310, '/sys/monitor/opt-log', '/wemirr/system/log/opt-log', 31006, 'carbon-operation', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (32002, '文件管理', '', 320, '/sys/oss/file', '/wemirr/system/storage/file/index', 0, 'hugeicons:folder-attachment', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-15 22:40:52');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (32003, '配置管理', '', 320, '/sys/oss/setting', '/wemirr/system/storage/setting/index', 0, 'ep:setting', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-15 22:40:43');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (35005, '应用管理', 'plat:app:page', 350, '/plat/security/app', '/wemirr/platform/security/app/index', 35005, 'streamline:application-add-solid', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (500301, '表单中心', NULL, 5003, '/dev/online/list', '/wemirr/development/online/index', 1, 'arcticons:jobstreet', 'menu', b'1', NULL, b'0', b'0', b'1', '', b'0', NULL, NULL, '2024-12-13 08:40:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (500501, '限流规则', 'dev:gateway:limit', 5005, '/dev/gateway/limit', '/wemirr/development/gateway/limit/index', 500501, 'carbon-rule', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (500502, '限访名单', 'dev:gateway:blacklist', 5005, '/dev/gateway/blacklist', '/wemirr/development/gateway/blacklist/index', 500502, 'carbon-ai-status-rejected', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (500503, '网关路由', 'dev:gateway:route', 5005, '/dev/gateway/route', '/wemirr/development/gateway/route/index', 500503, 'mdi-router-wireless-settings', 'menu', b'1', NULL, b'0', b'0', b'1', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1040101, '添加', 'tenant:add', 10401, '', NULL, 1040101, '', 'button', b'1', NULL, b'0', b'0', b'1', '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1040102, '编辑', 'tenant:edit', 10401, '', NULL, 1040102, '', 'button', b'1', NULL, b'0', b'0', b'1', '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1040103, '删除', 'tenant:remove', 10401, '', NULL, 1040103, '', 'button', b'1', NULL, b'0', b'0', b'1', '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1040105, '数据源配置', 'tenant:db-config', 10401, '', NULL, 1040105, '', 'button', b'1', NULL, b'0', b'0', b'1', '数据源配置', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1040106, '数据初始化', 'tenant:init-script', 10401, '', NULL, 1040106, '', 'button', b'1', NULL, b'0', b'0', b'1', '基础数据初始化', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1040107, '字典数据刷新', 'tenant:refresh-dict', 10401, '', NULL, 1040107, '', 'button', b'1', NULL, b'0', b'0', b'1', '字典数据刷新', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1070601, '添加', 'dict:add', 10706, '', NULL, 1070601, '', 'button', b'1', NULL, b'0', b'0', b'1', '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1070602, '编辑', 'dict:edit', 10706, '', NULL, 1070602, '', 'button', b'1', NULL, b'0', b'0', b'1', '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1070603, '删除', 'dict:remove', 10706, '', NULL, 1070603, '', 'button', b'1', NULL, b'0', b'0', b'1', '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1070605, '刷新字典', 'dict:refresh', 10706, '', NULL, 1070605, '', 'button', b'1', NULL, b'0', b'0', b'1', '字典数据刷新', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010101, '添加', 'sys:user:add', 30101, '', NULL, 3010101, '', 'button', b'1', NULL, b'0', b'0', b'1', '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010102, '编辑', 'sys:user:edit', 30101, '', NULL, 3010102, '', 'button', b'1', NULL, b'0', b'0', b'1', '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010103, '删除', 'sys:user:remove', 30101, '', NULL, 3010103, '', 'button', b'1', NULL, b'0', b'0', b'1', '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010104, '重置密码', 'sys:user:reset', 30101, '', NULL, 3010104, '', 'button', b'1', NULL, b'0', b'0', b'1', '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010201, '添加', 'sys:user:add', 30102, '', NULL, 3010201, '', 'button', b'1', NULL, b'0', b'0', b'1', '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010202, '编辑', 'sys:user:edit', 30102, '', NULL, 3010202, '', 'button', b'1', NULL, b'0', b'0', b'1', '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010203, '删除', 'sys:user:remove', 30102, '', NULL, 3010203, '', 'button', b'1', NULL, b'0', b'0', b'1', '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010301, '添加', 'sys:position:add', 30103, '', NULL, 3010301, '', 'button', b'1', NULL, b'0', b'0', b'1', '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010302, '编辑', 'sys:position:edit', 30103, '', NULL, 3010302, '', 'button', b'1', NULL, b'0', b'0', b'1', '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3010303, '删除', 'sys:position:remove', 30103, '', NULL, 3010303, '', 'button', b'1', NULL, b'0', b'0', b'1', '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3020101, '添加', 'sys:menu:add', 30201, '', NULL, 3020101, '', 'button', b'1', NULL, b'0', b'0', b'1', '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3020102, '编辑', 'sys:menu:edit', 30201, '', NULL, 3020102, '', 'button', b'1', NULL, b'0', b'0', b'1', '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3020103, '删除', 'sys:menu:remove', 30201, '', NULL, 3020103, '', 'button', b'1', NULL, b'0', b'0', b'1', '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3020201, '添加', 'sys:role:add', 30202, '', NULL, 3020201, '', 'button', b'1', NULL, b'0', b'0', b'1', '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3020202, '编辑', 'sys:role:edit', 30202, '', NULL, 3020202, '', 'button', b'1', NULL, b'0', b'0', b'1', '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3020203, '删除', 'sys:role:remove', 30202, '', NULL, 3020203, '', 'button', b'1', NULL, b'0', b'0', b'1', '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3020207, '分配用户', 'sys:role:assign-users', 30202, '', NULL, 3020207, '', 'button', b'1', NULL, b'0', b'0', b'1', '分配用户', b'0', 1, '平台管理员', '2024-12-09 15:54:08', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3020208, '分配资源', 'sys:role:assign-resource', 30202, '', NULL, 3020208, '', 'button', b'1', NULL, b'0', b'0', b'1', '分配资源', b'0', 1, '平台管理员', '2024-12-09 15:55:02', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3060501, '添加', 'message:template:add', 30605, '', NULL, 3060501, '', 'button', b'1', NULL, b'0', b'0', b'1', '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3060502, '编辑', 'message:template:edit', 30605, '', NULL, 3060502, '', 'button', b'1', NULL, b'0', b'0', b'1', '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3060503, '删除', 'message:template:remove', 30605, '', NULL, 3060503, '', 'button', b'1', NULL, b'0', b'0', b'1', '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3060504, '推送', 'message:template:publish', 30605, '', NULL, 3060504, '', 'button', b'1', NULL, b'0', b'0', b'1', '', b'0', NULL, NULL, '2024-12-07 10:30:13', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` int DEFAULT NULL COMMENT '租户编码',
  `code` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '角色编码',
  `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述信息',
  `scope_type` tinyint DEFAULT NULL COMMENT '数据权限范围，值越大，权限越大',
  `status` tinyint(1) DEFAULT '0' COMMENT '0=正常1=禁用',
  `super` tinyint(1) DEFAULT '0' COMMENT '0=非 1=管理员',
  `readonly` tinyint(1) DEFAULT '0' COMMENT '是否内置角色',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1829323672360452099 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `tenant_id`, `code`, `name`, `description`, `scope_type`, `status`, `super`, `readonly`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 1, 'PLATFORM_ADMIN', '平台管理员', '平台管理员，拥有所有数据可视权', 50, 1, 1, 1, 0, '超级管理员', '2019-10-25 13:46:00', 57, NULL, '2024-07-19 00:58:14');
INSERT INTO `sys_role` (`id`, `tenant_id`, `code`, `name`, `description`, `scope_type`, `status`, `super`, `readonly`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 1, 'TENANT-ADMIN', '租户管理员', '租户管理员', 50, 1, 0, 1, 1, '长风一梦8888', '2022-06-07 07:52:29', 0, NULL, '2024-07-19 00:58:14');
INSERT INTO `sys_role` (`id`, `tenant_id`, `code`, `name`, `description`, `scope_type`, `status`, `super`, `readonly`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1725059482402205698, 1, 'TEST', '测试权限范围', '123', 10, 1, 0, 0, 1, '长风一梦0000', '2023-11-16 15:53:25', 0, NULL, '2024-12-06 09:09:11');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `res_id` bigint NOT NULL COMMENT '菜单ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY `idx_role_res` (`role_id`,`res_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 50, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 104, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 107, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 301, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 302, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 303, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 306, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 310, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 320, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 350, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 5003, '2024-12-13 08:37:13');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 5005, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 5008, '2024-12-12 13:12:00');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 5050, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10401, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10402, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10706, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10707, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30101, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30102, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30103, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30201, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30202, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30301, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30303, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30604, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30605, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30606, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30607, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30608, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 31003, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 31005, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 31006, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 32001, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 32002, '2024-12-16 01:47:10');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 32003, '2024-12-16 01:47:15');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 35005, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500301, '2024-12-13 08:41:51');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500501, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500502, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500503, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040101, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040102, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040103, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040105, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040106, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040107, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1070601, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1070602, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1070603, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1070605, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010101, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010102, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010103, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010104, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010201, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010202, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010203, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010301, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010302, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010303, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020101, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020102, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020103, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020201, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020202, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020203, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020207, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020208, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3060501, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3060502, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3060503, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3060504, '2024-12-09 08:07:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 30101, '2024-12-10 04:46:55');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 30102, '2024-12-10 04:46:55');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 3010102, '2024-12-10 04:46:55');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 3010103, '2024-12-10 04:46:55');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 3010104, '2024-12-10 04:46:55');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 3010202, '2024-12-10 04:46:55');
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
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1863776323592458243 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of sys_site_notify
-- ----------------------------
BEGIN;
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 1, '1', 1, '1', '测试消息', '2,1', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, '管理员', '2021-07-12 11:41:31', 1, '长风一梦0000', '2023-12-15 14:16:51');
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, 1, '0', 1, '1', '测试通知', '1', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'0', 1, '管理员', '2021-07-12 11:42:15', 1, '长风一梦0000', '2023-12-15 14:16:51');
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (4, 2, '2', 1, '1', '测试待办', '1', '<p>测试待办</p><img src=\"http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/15/30369909559790.png\" contenteditable=\"false\"/>', '测试待办', b'0', 1, '管理员', '2021-07-12 11:42:26', NULL, NULL, '2024-12-03 10:44:22');
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1863776323592458242, NULL, '0', NULL, '1', '12312', '1', '<p>12312321321</p>', '3213123123123', b'0', NULL, NULL, '2024-12-03 10:44:37', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  UNIQUE KEY `role_id` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1725059482402205698);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (8, 1);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (10, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (12, 4);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (14, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (19, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (20, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (21, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (22, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (24, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (24, 1725059482402205698);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1863475850498969601, 1725059482402205698);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1865599612446965761, 2);
COMMIT;

-- ----------------------------
-- Table structure for t_db_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_db_setting`;
CREATE TABLE `t_db_setting` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '连接名',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `db_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据库类型',
  `driver_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `host` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'localhost' COMMENT '数据库连接',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述信息',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=正常1=禁用',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1735576153244049410 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='动态数据源';

-- ----------------------------
-- Records of t_db_setting
-- ----------------------------
BEGIN;
INSERT INTO `t_db_setting` (`id`, `name`, `username`, `password`, `db_type`, `driver_class_name`, `host`, `description`, `locked`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, '测试专用1', 'root', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', 'localhost:3306', 'tenant-8888', b'0', b'0', NULL, NULL, '2021-08-06 13:57:40', NULL, NULL, '2024-12-06 17:01:53');
INSERT INTO `t_db_setting` (`id`, `name`, `username`, `password`, `db_type`, `driver_class_name`, `host`, `description`, `locked`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, '测试专用2', 'admin', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', '123123213123', NULL, b'0', b'1', 1, '长风一梦8888', '2023-02-21 01:02:51', 1, '长风一梦8888', '2023-12-15 08:24:47');
INSERT INTO `t_db_setting` (`id`, `name`, `username`, `password`, `db_type`, `driver_class_name`, `host`, `description`, `locked`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, '测试专用3', 'admin', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', '31231231231231', NULL, b'0', b'1', 1, '长风一梦8888', '2023-02-21 01:05:06', NULL, NULL, '2023-12-15 08:24:47');
COMMIT;

-- ----------------------------
-- Table structure for t_file_storage
-- ----------------------------
DROP TABLE IF EXISTS `t_file_storage`;
CREATE TABLE `t_file_storage` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `platform` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '存储平台',
  `url` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '预览地址',
  `size` bigint DEFAULT NULL COMMENT '文件大小，单位字节',
  `format_size` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `filename` varchar(256) DEFAULT NULL COMMENT '文件名称',
  `original_filename` varchar(256) DEFAULT NULL COMMENT '原始文件名',
  `base_path` varchar(256) DEFAULT NULL COMMENT '基础存储路径',
  `path` varchar(256) DEFAULT NULL COMMENT '存储路径',
  `ext` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件扩展名',
  `content_type` varchar(128) DEFAULT NULL COMMENT 'MIME类型',
  `th_url` varchar(512) DEFAULT NULL COMMENT '缩略图访问路径',
  `th_filename` varchar(256) DEFAULT NULL COMMENT '缩略图名称',
  `th_size` bigint DEFAULT NULL COMMENT '缩略图大小，单位字节',
  `th_content_type` varchar(128) DEFAULT NULL COMMENT '缩略图MIME类型',
  `object_id` varchar(32) DEFAULT NULL COMMENT '文件所属对象id',
  `object_type` varchar(32) DEFAULT NULL COMMENT '文件所属对象类型，例如用户头像，评价图片',
  `metadata` text COMMENT '文件元数据',
  `user_metadata` text COMMENT '文件用户元数据',
  `th_metadata` text COMMENT '缩略图元数据',
  `th_user_metadata` text COMMENT '缩略图用户元数据',
  `attr` text COMMENT '附加属性',
  `file_acl` varchar(32) DEFAULT NULL COMMENT '文件ACL',
  `th_file_acl` varchar(32) DEFAULT NULL COMMENT '缩略图文件ACL',
  `hash_info` text COMMENT '哈希信息',
  `category` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1868879288737353730 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='文件记录表';

-- ----------------------------
-- Records of t_file_storage
-- ----------------------------
BEGIN;
INSERT INTO `t_file_storage` (`id`, `platform`, `url`, `size`, `format_size`, `filename`, `original_filename`, `base_path`, `path`, `ext`, `content_type`, `th_url`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `file_acl`, `th_file_acl`, `hash_info`, `category`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868878857646768129, 's3-wp-local', 'http://127.0.0.1:19000/wp-local/dev/676100b07090747e073e4234.png', 116918, '114.18 KB', '676100b07090747e073e4234.png', '系统架构图.png', 'dev/', '', 'png', 'image/png', NULL, NULL, NULL, NULL, NULL, NULL, '{}', '{}', '{}', '{}', '{}', NULL, NULL, '{}', 'IMAGE', 1, b'0', '1', '平台管理员', '2024-12-17 12:40:16', NULL, NULL, NULL);
INSERT INTO `t_file_storage` (`id`, `platform`, `url`, `size`, `format_size`, `filename`, `original_filename`, `base_path`, `path`, `ext`, `content_type`, `th_url`, `th_filename`, `th_size`, `th_content_type`, `object_id`, `object_type`, `metadata`, `user_metadata`, `th_metadata`, `th_user_metadata`, `attr`, `file_acl`, `th_file_acl`, `hash_info`, `category`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868879288737353729, 's3-wp-local', 'http://127.0.0.1:19000/wp-local/dev/67610116e30e4578789e9754.png', 116918, '114.18 KB', '67610116e30e4578789e9754.png', '92947efb109647c3a85ab714dbc9b4d2.png', 'dev/', '', 'png', 'image/png', NULL, NULL, NULL, NULL, NULL, NULL, '{}', '{}', '{}', '{}', '{}', NULL, NULL, '{}', 'IMAGE', 1, b'0', '1', '平台管理员', '2024-12-17 12:41:59', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_file_storage_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_file_storage_setting`;
CREATE TABLE `t_file_storage_setting` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint DEFAULT NULL,
  `platform` varchar(64) DEFAULT NULL COMMENT 'type+bucket_name 组合',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型（七牛云、阿里云、腾讯云、Minio、其他S3）',
  `status` tinyint DEFAULT '0' COMMENT '状态',
  `access_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'AccessKey',
  `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'SecretKey',
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '存储区域',
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '域名',
  `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '存储桶',
  `base_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '基础路径',
  `end_point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '服务端点',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1868599415401865218 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_file_storage_setting
-- ----------------------------
BEGIN;
INSERT INTO `t_file_storage_setting` (`id`, `tenant_id`, `platform`, `type`, `status`, `access_key`, `secret_key`, `region`, `domain`, `bucket_name`, `base_path`, `end_point`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 1, 'minio-wp-local', 'minio', 0, 'dQAztlq1gMdAaTfR5i7K', 'S6wpCw8Jo2ujqqs2bgYcBGbp4sOLtvzVy2Yvm6zF', NULL, 'http://127.0.0.1:19000/wp-local/', 'wp-local', 'minio/', 'http://127.0.0.1:19000', b'0', '1', '平台管理员', '2024-12-16 10:41:31', '1', '平台管理员', '2024-12-17 11:19:59');
INSERT INTO `t_file_storage_setting` (`id`, `tenant_id`, `platform`, `type`, `status`, `access_key`, `secret_key`, `region`, `domain`, `bucket_name`, `base_path`, `end_point`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868599415401865217, 1, 's3-wp-local', 's3', 1, 'dQAztlq1gMdAaTfR5i7K	', 'S6wpCw8Jo2ujqqs2bgYcBGbp4sOLtvzVy2Yvm6zF', NULL, 'http://127.0.0.1:19000/wp-local/', 'wp-local', 'dev/', 'http://127.0.0.1:19000', b'0', '1', '平台管理员', '2024-12-16 18:09:52', '1', '平台管理员', '2024-12-17 11:19:59');
COMMIT;

-- ----------------------------
-- Table structure for t_gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `t_gateway_route`;
CREATE TABLE `t_gateway_route` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `route_id` varchar(64) NOT NULL COMMENT '路由ID',
  `uri` varchar(200) DEFAULT NULL COMMENT '企业邮箱',
  `order` tinyint DEFAULT '0' COMMENT '排序',
  `predicates` varchar(512) DEFAULT NULL COMMENT '谓语条件',
  `filters` varchar(512) DEFAULT NULL COMMENT '过滤器',
  `locked` bit(1) DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_route_id` (`route_id`) USING BTREE COMMENT '路由ID唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='网关路由表';

-- ----------------------------
-- Records of t_gateway_route
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant`;
CREATE TABLE `t_tenant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '租户编码',
  `name` varchar(64) NOT NULL COMMENT '租户名称',
  `type` tinyint DEFAULT '0' COMMENT '0=其它,1=企业',
  `alias` varchar(50) DEFAULT NULL COMMENT '简称',
  `logo` varchar(255) DEFAULT NULL COMMENT 'LOGO',
  `email` varchar(50) DEFAULT NULL COMMENT '租户邮箱',
  `status` bit(1) DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(30) DEFAULT NULL COMMENT '联系人方式',
  `industry` varchar(255) DEFAULT NULL COMMENT '行业',
  `province_id` int DEFAULT NULL COMMENT '省份',
  `province_name` varchar(64) DEFAULT NULL COMMENT '省份',
  `city_id` int DEFAULT NULL COMMENT '市',
  `city_name` varchar(64) DEFAULT NULL COMMENT '市',
  `address` varchar(250) DEFAULT NULL COMMENT '详细地址',
  `district_id` int DEFAULT NULL COMMENT '区县',
  `district_name` varchar(64) DEFAULT NULL COMMENT '区县',
  `credit_code` varchar(50) DEFAULT NULL COMMENT '统一信用代码',
  `legal_person_name` varchar(50) DEFAULT NULL COMMENT '法人',
  `web_site` varchar(200) DEFAULT NULL COMMENT '企业网址',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1864956411491020802 DEFAULT CHARSET=utf8mb3 COMMENT='租户信息';

-- ----------------------------
-- Records of t_tenant
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, '0000', '平台超级租户', 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '000000@qq.com', b'1', '唐亚峰', '13002170000', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'https://cloud.battcn.com', NULL, b'0', 0, NULL, '2021-07-31 07:27:08', 1, '长风一梦', '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, '2222', '小米科技有限责任公司', 1, '小米', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '666666@qq.com', b'1', '雷军', '13002176666', '4', 110000, '北京市', 110100, '市辖区', '北京市海淀区西二旗中路33号院6号楼6层006号 ', 110108, '海淀区', '91110108551385082Q', '雷军', 'https://cloud.battcn.com', NULL, b'0', 0, NULL, '2021-07-31 08:14:02', 1, '长风一梦', '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, '8888', '深圳腾讯科技股份有限公司', 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '888888@qq.com', b'1', '马化腾', '13002178888', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'http://localhost:5666', NULL, b'0', 0, NULL, '2021-07-31 07:27:08', 1, '平台管理员', '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1722515686367961090, '3333', '测试3333', 0, '测试', NULL, '1231@163.com', b'1', '测试3333', '13000000000', NULL, 130000, NULL, 130500, NULL, '123', 130505, NULL, NULL, NULL, '123123', NULL, b'1', 1, '长风一梦0000', '2023-11-09 15:25:17', NULL, NULL, '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1821121516151009281, 'ces4', 'ces4', 0, 'ces4', NULL, NULL, b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ces4.com', NULL, b'1', 1, '平台管理员', '2024-08-07 17:49:39', NULL, NULL, '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1821132304500666370, 'ces5', 'ces5', 0, 'ces5', NULL, NULL, b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ces5.com', NULL, NULL, NULL, NULL, 'ces5.com', NULL, b'1', 1, '平台管理员', '2024-08-07 18:32:31', 1, '平台管理员', '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1864956411491020801, '12345', '123', 0, '123', NULL, NULL, b'1', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, b'1', NULL, NULL, '2024-12-06 16:53:52', NULL, NULL, '2024-12-10 08:23:47');
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_dict`;
CREATE TABLE `t_tenant_dict` (
  `id` bigint NOT NULL COMMENT '雪花算法ID',
  `code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '编码',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `description` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly` bit(1) DEFAULT b'0' COMMENT '0=否；1=是；只读数据不允许删除',
  `sequence` tinyint DEFAULT NULL COMMENT '排序',
  `tenant_id` bigint NOT NULL COMMENT '租户ID t_tenant.id',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `created_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人ID',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_code` (`code`) USING BTREE COMMENT '租户字典索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_tenant_dict
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185326841858, 'TENANT-DICT', '租户字典', '测试租户字典推送和同步', b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185326841859, 'TMS_EXPENSE_ITEM', 'TMS费用项目', NULL, b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185335230466, 'TMS_ENERGY_TYPE', 'TMS能源类型', NULL, b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185335230467, 'TMS_PLATE_COLOR', 'TMS车牌颜色', 'TMS车牌颜色', b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185339424769, 'PAYMENT_TYPE', '付款类型', NULL, b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185343619074, 'PAYMENT_METHOD', '付款方法', NULL, b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185352007681, 'CONTRACT_SEAL_TYPE', '用印类型', NULL, b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_dict_item`;
CREATE TABLE `t_tenant_dict_item` (
  `id` bigint NOT NULL COMMENT '雪花算法id',
  `dict_id` varchar(255) DEFAULT NULL COMMENT '字典ID',
  `dict_code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '字典编码',
  `value` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '字典值',
  `label` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '子项名',
  `readonly` bit(1) DEFAULT b'0' COMMENT '是否只读 true = 只读;false = 可编辑修改',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `color` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '颜色',
  `deleted` bit(1) DEFAULT b'0',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '描述',
  `sequence` int DEFAULT '1' COMMENT '排序',
  `tenant_id` bigint NOT NULL COMMENT '租户id，t_tenant.id',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_tenant_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185465253889, '1700070256599982081', 'TMS_EXPENSE_ITEM', 'gps_fee', 'GPS费用', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185473642498, '1700070256599982081', 'TMS_EXPENSE_ITEM', 'commercial_insurance', '商业险', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185477836801, '1700070256599982081', 'TMS_EXPENSE_ITEM', 'strong_insurance', '强险', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185482031105, '1700070256599982081', 'TMS_EXPENSE_ITEM', 'annually_review', '年审', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185482031106, '1700070256599982081', 'TMS_EXPENSE_ITEM', 'annually_inspect', '年检', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185503002625, '1699343995867914241', 'TMS_ENERGY_TYPE', '1', '柴油', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185503002626, '1699343995867914241', 'TMS_ENERGY_TYPE', '0', '汽油', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185511391234, '1699341989447098369', 'TMS_PLATE_COLOR', 'YELLOW', '黄色', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185511391235, '1699341989447098369', 'TMS_PLATE_COLOR', 'BLUE', '蓝色', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185515585538, '1699341989447098369', 'TMS_PLATE_COLOR', 'RED', '红色', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185528168449, '1698634189967577089', 'PAYMENT_METHOD', '2', '支票', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185532362753, '1698634189967577089', 'PAYMENT_METHOD', '1', '电汇', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185536557058, '1698634230920761345', 'PAYMENT_TYPE', 'L0106', '退供应商履约保证金', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185536557059, '1698634230920761345', 'PAYMENT_TYPE', 'L010', '预付款', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185544945666, '1698634230920761345', 'PAYMENT_TYPE', 'L0104', '退质保金-供应商', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185544945667, '1686979951801745410', 'CONTRACT_SEAL_TYPE', 'officialSeal', '公章', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185553334273, '1686979951801745410', 'CONTRACT_SEAL_TYPE', 'contractSeal', '合同章', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185557528577, '1865614807147950081', 'TENANT-DICT', 'B', '租户B', b'1', b'1', NULL, b'0', '租户B', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185557528578, '1865614807147950081', 'TENANT-DICT', 'A', '租户A', b'1', b'1', NULL, b'0', '租户A', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_setting`;
CREATE TABLE `t_tenant_setting` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `site_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '站点',
  `site_title` varchar(255) DEFAULT NULL COMMENT '站点标题',
  `site_sub_title` varchar(255) DEFAULT NULL COMMENT '子标题',
  `site_logo` varchar(255) DEFAULT NULL COMMENT 'LOGO',
  `db_id` bigint DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL,
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_name` varchar(255) DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='租户配置信息';

-- ----------------------------
-- Records of t_tenant_setting
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_setting` (`id`, `tenant_id`, `site_url`, `site_title`, `site_sub_title`, `site_logo`, `db_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 1, '0000.wp.com', 'WEMIRR SAAS 平台', '欢迎来到 wemirr-platform 云 saas 平台', NULL, 1, b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tenant_setting` (`id`, `tenant_id`, `site_url`, `site_title`, `site_sub_title`, `site_logo`, `db_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 2, '2222.wp.com', '2222 - 平台', '欢迎来到 WP 2222 平台', NULL, 1, b'0', NULL, NULL, NULL, 1, '平台管理员', '2024-12-12 17:35:19');
INSERT INTO `t_tenant_setting` (`id`, `tenant_id`, `site_url`, `site_title`, `site_sub_title`, `site_logo`, `db_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, 3, '8888.wp.com', '8888', '8888', NULL, 1, b'0', NULL, NULL, NULL, 1, '平台管理员', '2024-12-11 20:13:40');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `org_id` bigint DEFAULT NULL COMMENT '组织ID',
  `position_id` bigint DEFAULT NULL COMMENT '岗位ID',
  `readonly` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否内置',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机',
  `id_card` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证',
  `sex` tinyint DEFAULT '1' COMMENT '性别',
  `status` bit(1) DEFAULT b'0' COMMENT '状态 \n1启用 0禁用',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '头像',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `nation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '民族',
  `education` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学历',
  `position_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职位状态',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `last_login_ip` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后一次登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_ACCOUNT_TENANT` (`username`,`tenant_id`) USING BTREE COMMENT '账号唯一约束'
) ENGINE=InnoDB AUTO_INCREMENT=1865599612446965762 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 1, 'admin', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '平台管理员', 100, 100, b'0', '1837307557@qq.com', '13002171111', '111111111', 1, b'1', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '平台最帅的超级管理员', 'mz_daiz', 'SUOSHI', 'QUIT', '2023-10-14', '0:0:0:0:0:0:0:1', '2024-12-16 16:20:25', b'0', 0, NULL, '2020-10-16 03:25:36', NULL, NULL, '2024-12-16 16:20:25');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (23, 1, 'test', '{bcrypt}$2a$10$j1U0rIRl8ODzc2j5rkLx8OSvjjEZ.cJ/Xe8DzkqL2jgAyX2c3x.4C', '444', NULL, NULL, b'0', '1837307557@qq.com', '13002171921', NULL, 1, b'0', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', 1, '长风一梦8888', '2022-12-04 08:05:29', NULL, NULL, '2024-12-07 14:34:19');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (24, 1, 'admin2', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '测试管理员', 100, 100, b'0', '1837307557@qq.com', '13002171921', NULL, 1, b'1', '', '修改描述信息吧222', 'mz_daiz', 'XIAOXUE', 'QUIT', NULL, '0:0:0:0:0:0:0:1', '2024-12-07 22:34:47', b'0', 1, '长风一梦8888', '2023-02-21 01:17:43', NULL, NULL, '2024-12-07 22:34:47');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1710534752819261442, 2, 'admin', '{bcrypt}$2a$10$VjkzPHKfMoN0ZNLJSl.PkOWx9LVA7BqLmcI6sgyvzpe5D1INtm8xe', '雷军', NULL, NULL, b'0', '1837307557@qq.com', '13002176666', NULL, 1, b'1', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', 1, '长风一梦8888', NULL, 0, NULL, '2024-12-05 15:36:32');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1725059377381027842, 1, 'admin3', '{bcrypt}$2a$10$oaKlJc26SNFT4obD4B0aOOil12YtKH7/yyayzhbGrLrWyAgc.X.Ji', 'admin3', 10001, 104, b'0', '1837307557@qq.com', '13002171934', NULL, 2, b'0', '', NULL, NULL, NULL, 'QUIT', NULL, NULL, NULL, b'1', 1, '长风一梦0000', '2023-11-16 15:53:00', 1, '长风一梦000011', '2024-12-05 15:36:32');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1863475850498969601, 1, 'guoxue', '{bcrypt}$2a$10$cLEEmhkxOWcRamyIy79dKuUleCXfNZmk5EwaZxNa/3/6BglOnEokm', '2323', 10001, 101, b'0', '1837307557@qq.com', '13002171933', NULL, 1, b'1', '', '11', 'mz_lhz', 'COLLEGE', 'WORKING', NULL, '0:0:0:0:0:0:0:1', '2024-12-09 14:56:49', b'0', NULL, NULL, '2024-12-02 14:50:39', 1, '平台管理员', '2024-12-12 20:48:21');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865599612446965761, 3, 'admin', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '马化腾', NULL, NULL, b'0', NULL, '13002178888', NULL, 1, b'1', '', NULL, NULL, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', '2024-12-12 14:39:10', b'0', NULL, NULL, '2024-12-08 11:29:43', NULL, NULL, '2024-12-12 14:39:10');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
