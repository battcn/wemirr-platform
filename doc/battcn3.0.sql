-- ----------------------------
-- 本SQL采用的是MYSQL5.6 如果版本不 < 5.6请去官方安装
-- 否则运行SQL会出现：[Err] 1067 - Invalid default value for 'gmt_create'
-- 如果真的想使用,请修改
-- `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' (修改前)
-- `gmt_create` datetime DEFAULT NULL COMMENT '创建时间' (修改后)
-- ----------------------------


/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : battcn3.0

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 17/03/2018 19:15:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT 'ID自增',
  `book_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍名称',
  `source` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍来源',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍类型(玄幻小说)',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `description` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述（简介）',
  `cover` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍封面',
  `status` bit(1) NULL DEFAULT b'0' COMMENT '是否完结 0=未完结 1=已完结',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书籍信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_book_chapter
-- ----------------------------
DROP TABLE IF EXISTS `t_book_chapter`;
CREATE TABLE `t_book_chapter`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'ID自增',
  `link` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节链接地址',
  `book_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍编号',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `status` bit(1) NULL DEFAULT b'0' COMMENT '是否已生成模板文件 0=未生成 1=已生成',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书籍章节' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `title` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `method` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行的方法',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息',
  `ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `duration` smallint(5) NULL DEFAULT NULL COMMENT '执行时间',
  `params` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
INSERT INTO `t_sys_log` VALUES (1, 'admin', '日志模块', '日志list', '进入日志查询的页面', '192.168.191.1', 2, NULL, '/sys/log/list', '2018-03-13 17:04:13', '2018-03-13 17:04:13');
INSERT INTO `t_sys_log` VALUES (2, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:11:19', '2018-03-13 17:11:19');
INSERT INTO `t_sys_log` VALUES (3, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:11:30', '2018-03-13 17:11:30');
INSERT INTO `t_sys_log` VALUES (4, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:12:28', '2018-03-13 17:12:28');
INSERT INTO `t_sys_log` VALUES (5, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:12:40', '2018-03-13 17:12:40');
INSERT INTO `t_sys_log` VALUES (6, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:14:16', '2018-03-13 17:14:16');
INSERT INTO `t_sys_log` VALUES (7, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:15:16', '2018-03-13 17:15:16');
INSERT INTO `t_sys_log` VALUES (8, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 3, NULL, '/crawler/proxy/list', '2018-03-13 17:21:35', '2018-03-13 17:21:35');
INSERT INTO `t_sys_log` VALUES (9, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 2, NULL, '/crawler/proxy/list', '2018-03-13 17:23:01', '2018-03-13 17:23:01');
INSERT INTO `t_sys_log` VALUES (10, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 2, NULL, '/crawler/proxy/list', '2018-03-14 09:40:27', '2018-03-14 09:40:27');
INSERT INTO `t_sys_log` VALUES (11, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 3, NULL, '/crawler/proxy/list', '2018-03-14 09:49:13', '2018-03-14 09:49:13');
INSERT INTO `t_sys_log` VALUES (12, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 2, NULL, '/crawler/proxy/list', '2018-03-14 10:15:49', '2018-03-14 10:15:49');
INSERT INTO `t_sys_log` VALUES (13, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 2, NULL, '/crawler/proxy/list', '2018-03-14 10:25:41', '2018-03-14 10:25:41');
INSERT INTO `t_sys_log` VALUES (14, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 10:32:00', '2018-03-14 10:32:00');
INSERT INTO `t_sys_log` VALUES (15, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 4, NULL, '/crawler/proxy/list', '2018-03-14 16:31:59', '2018-03-14 16:31:59');
INSERT INTO `t_sys_log` VALUES (16, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 1, NULL, '/crawler/proxy/list', '2018-03-14 16:35:35', '2018-03-14 16:35:35');
INSERT INTO `t_sys_log` VALUES (17, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:36:14', '2018-03-14 16:36:14');
INSERT INTO `t_sys_log` VALUES (18, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:37:46', '2018-03-14 16:37:46');
INSERT INTO `t_sys_log` VALUES (19, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:38:20', '2018-03-14 16:38:20');
INSERT INTO `t_sys_log` VALUES (20, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:38:27', '2018-03-14 16:38:27');
INSERT INTO `t_sys_log` VALUES (21, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:38:32', '2018-03-14 16:38:32');
INSERT INTO `t_sys_log` VALUES (22, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 5, NULL, '/crawler/proxy/list', '2018-03-14 16:40:20', '2018-03-14 16:40:20');
INSERT INTO `t_sys_log` VALUES (23, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:40:33', '2018-03-14 16:40:33');
INSERT INTO `t_sys_log` VALUES (24, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:42:23', '2018-03-14 16:42:23');
INSERT INTO `t_sys_log` VALUES (25, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:42:28', '2018-03-14 16:42:28');
INSERT INTO `t_sys_log` VALUES (26, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 1, NULL, '/crawler/proxy/list', '2018-03-16 19:11:23', '2018-03-16 19:11:23');
INSERT INTO `t_sys_log` VALUES (27, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 0, NULL, '/crawler/proxy/list', '2018-03-16 19:12:24', '2018-03-16 19:12:24');
INSERT INTO `t_sys_log` VALUES (28, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 0, NULL, '/crawler/proxy/list', '2018-03-16 19:12:33', '2018-03-16 19:12:33');
INSERT INTO `t_sys_log` VALUES (29, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 0, NULL, '/crawler/proxy/list', '2018-03-16 19:12:35', '2018-03-16 19:12:35');
INSERT INTO `t_sys_log` VALUES (30, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 1, NULL, '/crawler/proxies/list', '2018-03-16 19:18:11', '2018-03-16 19:18:11');
INSERT INTO `t_sys_log` VALUES (31, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 19:31:30', '2018-03-16 19:31:30');
INSERT INTO `t_sys_log` VALUES (32, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 0, NULL, '/crawler/proxies/list', '2018-03-16 19:34:25', '2018-03-16 19:34:25');
INSERT INTO `t_sys_log` VALUES (33, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 19:37:46', '2018-03-16 19:37:46');
INSERT INTO `t_sys_log` VALUES (34, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 19:38:23', '2018-03-16 19:38:23');
INSERT INTO `t_sys_log` VALUES (35, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 20:27:58', '2018-03-16 20:27:58');
INSERT INTO `t_sys_log` VALUES (36, 'admin', '日志模块', '日志list', '进入日志查询的页面', '192.168.2.109', 1, NULL, '/sys/log/list', '2018-03-16 20:35:55', '2018-03-16 20:35:55');
INSERT INTO `t_sys_log` VALUES (37, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 20:39:30', '2018-03-16 20:39:30');
INSERT INTO `t_sys_log` VALUES (38, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 5, NULL, '/crawler/proxies/list', '2018-03-17 10:37:19', '2018-03-17 10:37:19');
INSERT INTO `t_sys_log` VALUES (39, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 0, NULL, '/crawler/proxies/list', '2018-03-17 10:37:25', '2018-03-17 10:37:25');
INSERT INTO `t_sys_log` VALUES (40, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 1, NULL, '/crawler/proxies/list', '2018-03-17 10:47:08', '2018-03-17 10:47:08');
INSERT INTO `t_sys_log` VALUES (41, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 2, NULL, '/crawler/proxies/list', '2018-03-17 11:21:11', '2018-03-17 11:21:11');
INSERT INTO `t_sys_log` VALUES (42, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 0, NULL, '/crawler/proxies/list', '2018-03-17 11:27:12', '2018-03-17 11:27:12');
INSERT INTO `t_sys_log` VALUES (43, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 3, NULL, '/crawler/proxies/list', '2018-03-17 11:47:25', '2018-03-17 11:47:25');
INSERT INTO `t_sys_log` VALUES (44, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 37, NULL, '/crawler/proxies/list', '2018-03-17 13:20:14', '2018-03-17 13:20:14');
INSERT INTO `t_sys_log` VALUES (45, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 1, NULL, '/crawler/proxies/list', '2018-03-17 13:47:02', '2018-03-17 13:47:02');
INSERT INTO `t_sys_log` VALUES (46, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 0, NULL, '/crawler/proxies/list', '2018-03-17 13:47:08', '2018-03-17 13:47:08');
INSERT INTO `t_sys_log` VALUES (47, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 5, NULL, '/crawler/proxies/list', '2018-03-17 13:57:59', '2018-03-17 13:57:59');
INSERT INTO `t_sys_log` VALUES (48, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 4, NULL, '/crawler/proxies/list', '2018-03-17 16:06:26', '2018-03-17 16:06:26');
INSERT INTO `t_sys_log` VALUES (49, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 0, NULL, '/crawler/proxies/list', '2018-03-17 16:06:31', '2018-03-17 16:06:31');
INSERT INTO `t_sys_log` VALUES (50, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 0, NULL, '/crawler/proxies/list', '2018-03-17 16:07:26', '2018-03-17 16:07:26');
INSERT INTO `t_sys_log` VALUES (51, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 1, NULL, '/crawler/proxies/list', '2018-03-17 16:12:16', '2018-03-17 16:12:16');
INSERT INTO `t_sys_log` VALUES (52, 'admin', '代理池', '代理池', '进入代理池页', '192.168.11.59', 2, NULL, '/crawler/proxies/list', '2018-03-17 16:49:20', '2018-03-17 16:49:20');
INSERT INTO `t_sys_log` VALUES (53, 'admin', '日志模块', '日志list', '进入日志查询的页面', '192.168.2.109', 2, NULL, '/sys/log/list', '2018-03-17 17:55:14', '2018-03-17 17:55:14');

-- ----------------------------
-- Table structure for t_sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_manager`;
CREATE TABLE `t_sys_manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role_id` tinyint(3) NULL DEFAULT NULL COMMENT '角色',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `credential` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证凭证',
  `locked` bit(1) NULL DEFAULT b'1' COMMENT '是否启用 0=禁用 1=启用',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `photo` varchar(266) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `last_login_ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆IP',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_ACCOUNT`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_manager
-- ----------------------------
INSERT INTO `t_sys_manager` VALUES (1, 'admin', '123456', 1, '秋殇', '9c6c9e22ae8c773c8f07a75b28563152', b'1', NULL, NULL, NULL, NULL, NULL, NULL, '2017-06-16 08:59:01', '2017-06-16 08:59:01');
INSERT INTO `t_sys_manager` VALUES (2, 'battcn', 'battcn', 2, '11333', NULL, b'1', NULL, NULL, NULL, NULL, NULL, NULL, '2017-06-19 09:20:14', '2017-06-21 13:49:37');

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `channel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道',
  `permissions` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `locked` bit(1) NULL DEFAULT b'1' COMMENT '是否启用 0=禁用 1=启用',
  `parent_id` int(8) NULL DEFAULT NULL COMMENT '父级编号',
  `sequence` int(8) NULL DEFAULT NULL COMMENT '排序编号',
  `rank` int(8) NULL DEFAULT NULL COMMENT '级别',
  `sort` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tree所需',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference`(`parent_id`) USING BTREE,
  CONSTRAINT `t_sys_menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES (1, '系统管理', '系统管理', 'fa fa fa-home', '/sys/sys', 'sys', b'1', NULL, 1, 0, ',1,', '2017-06-16 09:00:09', '2018-03-13 15:47:31');
INSERT INTO `t_sys_menu` VALUES (2, '菜单管理', '菜单管理', 'fa fa-server', '/sys/menu', 'sys:menu', b'1', 1, 2, 0, ',1,2,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (3, '操作管理', '操作管理', 'fa fa-hand-lizard-o', '/sys/operate', 'sys:operate', b'1', 1, 3, 0, ',1,3,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (4, '角色管理', '角色管理', 'fa fa-sitemap', '/sys/role', 'sys:role', b'1', 1, 4, 0, ',1,4,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (5, '帐号管理', '帐号管理', 'fa fa-sun-o', '/sys/manager', 'sys:manager', b'1', 1, 5, 0, ',1,5,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (6, '日志管理', '日志管理', 'fa fa-comments-o', '/sys/log', 'sys:logs', b'1', 1, 6, 0, ',1,6,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (7, '字典管理', '字典管理', 'fa fa-newspaper-o', '/sys/dict', 'sys:dict', b'0', 1, 7, 0, ',1,7,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (8, '爬虫管理', '爬虫管理', 'fa fa-cogs', '/crawler', 'sys:crawler', b'1', NULL, 1, 0, ',8,', '2017-06-16 09:00:09', '2018-03-16 19:15:30');
INSERT INTO `t_sys_menu` VALUES (10, 'IP代理', 'IP代理', 'fa fa-cogs', '/crawler/proxies', 'crawler:proxies', b'1', 8, 2, 0, ',8,10,', '2018-03-13 15:48:14', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (11, '书籍管理', '书籍管理', 'fa fa-cogs', '/crawler/books', 'crawler:books', b'1', 8, 3, 0, ',8,11,', '2018-03-16 19:13:23', '2018-03-16 20:53:53');

-- ----------------------------
-- Table structure for t_sys_operate
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_operate`;
CREATE TABLE `t_sys_operate`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` int(8) NOT NULL COMMENT '菜单ID',
  `op` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sequence` int(11) NULL DEFAULT NULL COMMENT '排序号',
  `display` bit(1) NOT NULL COMMENT '是否显示出来',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `menu_op`(`menu_id`, `op`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_operate
-- ----------------------------
INSERT INTO `t_sys_operate` VALUES (1, 1, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-20 18:52:32', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (2, 2, 'edit', '编辑', 'edit', NULL, 3, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (3, 2, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (4, 2, 'remove', '删除', 'remove', NULL, 4, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (5, 2, 'save', '保存', 'save', NULL, 5, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (6, 2, 'add', '新增', 'plus', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (7, 3, 'edit', '编辑', 'edit', NULL, 3, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (8, 3, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (9, 3, 'remove', '删除', 'remove', NULL, 4, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (10, 3, 'save', '保存', 'save', NULL, 5, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (11, 3, 'add', '新增', 'plus', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-21 10:15:01');
INSERT INTO `t_sys_operate` VALUES (12, 4, 'edit', '编辑', 'edit', NULL, 3, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (13, 4, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (14, 4, 'remove', '删除', 'remove', NULL, 4, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (15, 4, 'save', '保存', 'save', NULL, 5, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (16, 4, 'add', '新增', 'plus', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (17, 5, 'edit', '编辑', 'edit', NULL, 3, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (18, 5, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (19, 5, 'remove', '删除', 'remove', NULL, 4, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (20, 5, 'save', '保存', 'save', NULL, 5, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (21, 5, 'add', '新增', 'plus', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (22, 6, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-21 09:29:01');
INSERT INTO `t_sys_operate` VALUES (23, 7, 'edit', '编辑', 'edit', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-21 09:29:09');
INSERT INTO `t_sys_operate` VALUES (24, 7, 'list', '查看', 'list', NULL, 2, b'0', '2017-06-16 09:01:08', '2017-06-21 09:29:10');
INSERT INTO `t_sys_operate` VALUES (25, 7, 'remove', '删除', 'remove', NULL, 1, b'1', '2017-06-16 09:01:08', '2017-06-21 09:29:12');
INSERT INTO `t_sys_operate` VALUES (26, 7, 'save', '保存', 'save', NULL, 3, b'0', '2017-06-16 09:01:08', '2017-06-21 09:29:13');
INSERT INTO `t_sys_operate` VALUES (27, 4, 'permissions', '分配权限', 'permissions', NULL, 1, b'1', '2017-06-20 13:38:21', '2017-06-21 09:29:14');
INSERT INTO `t_sys_operate` VALUES (28, 8, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-21 10:09:23');
INSERT INTO `t_sys_operate` VALUES (29, 6, 'export', '导出', 'file-excel-o', '导出Excel', 1, b'1', '2018-01-17 12:25:43', '2018-01-17 12:36:50');
INSERT INTO `t_sys_operate` VALUES (30, 9, 'list', '查看', 'list', '查看代理', 1, b'0', '2018-03-13 15:49:49', '2018-03-13 15:49:49');
INSERT INTO `t_sys_operate` VALUES (31, 10, 'list', '查看', 'list', '', NULL, b'0', '2018-03-13 17:18:01', '2018-03-13 17:18:01');
INSERT INTO `t_sys_operate` VALUES (32, 10, 'crawler', '爬虫', 'crawler', '更新代理', 1, b'1', '2018-03-14 09:42:46', '2018-03-14 09:42:46');
INSERT INTO `t_sys_operate` VALUES (33, 11, 'list', '查看', 'list', '书籍信息', 1, b'0', '2018-03-16 19:16:48', '2018-03-16 19:31:39');
INSERT INTO `t_sys_operate` VALUES (34, 12, 'list', '查看', 'list', '书籍信息', 1, b'0', '2018-03-16 19:17:08', '2018-03-16 19:17:08');

-- ----------------------------
-- Table structure for t_sys_proxy_pool
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_proxy_pool`;
CREATE TABLE `t_sys_proxy_pool`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT 'ID自增',
  `host` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代理IP',
  `port` int(8) NULL DEFAULT NULL COMMENT '代理端口',
  `anonymity` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '匿名度/匿名等级',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP类型（HTTP,HTTPS）',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属地',
  `validate_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证时间',
  `source` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP来源',
  `locked` bit(1) NULL DEFAULT b'1' COMMENT '是否启用 0=禁用 1=启用',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代理IP池' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, '超级管理员', 'manager', '超级管理员,不听话就封号', '2017-06-16 09:01:23', '2017-06-16 09:01:23');
INSERT INTO `t_sys_role` VALUES (2, '测试管理员', 'test', '专门测试没有权限的时候1', '2017-06-16 09:01:23', '2017-06-16 09:01:23');
INSERT INTO `t_sys_role` VALUES (3, '开发管理员', 'code', '我是开发不服删数据库', '2017-06-16 09:01:23', '2017-06-20 19:11:31');

-- ----------------------------
-- Table structure for t_sys_role_operate
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_operate`;
CREATE TABLE `t_sys_role_operate`  (
  `role_id` int(8) NOT NULL COMMENT '角色ID',
  `operate_id` int(8) NOT NULL COMMENT '操作表ID',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`role_id`, `operate_id`) USING BTREE,
  INDEX `operate_id`(`operate_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_operate
-- ----------------------------
INSERT INTO `t_sys_role_operate` VALUES (1, 1, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 2, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 3, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 4, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 5, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 6, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 7, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 8, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 9, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 10, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 11, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 12, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 13, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 14, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 15, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 16, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 17, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 18, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 19, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 20, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 21, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 22, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 23, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 24, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 25, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 26, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 27, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 28, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 29, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 31, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 32, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 33, '2018-03-16 19:17:28', '2018-03-16 19:17:28');
INSERT INTO `t_sys_role_operate` VALUES (1, 34, '2018-03-16 19:17:31', '2018-03-16 19:17:31');
INSERT INTO `t_sys_role_operate` VALUES (2, 1, '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES (2, 2, '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES (2, 3, '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES (2, 4, '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES (2, 5, '2017-06-16 09:01:39', '2017-06-16 09:01:39');

-- ----------------------------
-- Procedure structure for refreshTreeNodes
-- ----------------------------
DROP PROCEDURE IF EXISTS `refreshTreeNodes`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `refreshTreeNodes`( )
BEGIN
	DECLARE
		LEVEL INT;

	SET LEVEL = 0;
	UPDATE t_sys_menu a
	INNER JOIN ( SELECT id, rank, concat( ',', ID, ',' ) sort FROM t_sys_menu WHERE parent_id IS NULL ) b ON a.id = b.id
	SET a.rank = b.rank,
	a.sort = b.sort;
	WHILE
			FOUND_ROWS( ) > 0 DO

			SET LEVEL = LEVEL + 1;
		UPDATE t_sys_menu a
		INNER JOIN ( SELECT ID, rank, sort FROM t_sys_menu WHERE rank = LEVEL - 1 ) b ON a.parent_id = b.id
		SET a.rank = b.rank,
		a.sort = concat( b.sort, a.ID, ',' );

	END WHILE;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
