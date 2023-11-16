/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : wemirr-platform

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 16/11/2023 18:11:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
    `username` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
    `password` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '密码',
    `nick_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
    `org_id` bigint DEFAULT NULL COMMENT '组织ID',
    `station_id` bigint DEFAULT NULL COMMENT '岗位ID',
    `readonly` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否内置',
    `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
    `mobile` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机',
    `id_card` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证',
    `sex` tinyint DEFAULT '1' COMMENT '性别\n#Sex{W:女;M:男;N:未知}',
    `status` bit(1) DEFAULT b'0' COMMENT '状态 \n1启用 0禁用',
    `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '头像',
    `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
    `nation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '民族',
    `education` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学历',
    `position_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职位状态',
    `birthday` date DEFAULT NULL COMMENT '生日',
    `deleted` bit(1) DEFAULT b'0',
    `created_by` bigint DEFAULT '0' COMMENT '创建人id',
    `created_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
    `last_modified_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人名称',
    `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `UN_ACCOUNT_TENANT` (`username`,`tenant_id`) USING BTREE COMMENT '账号唯一约束'
) ENGINE=InnoDB AUTO_INCREMENT=1725059377381027843 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 1, 'admin', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '长风一梦0000', 100, 100, b'0', '1837307557@qq.com', '13002171111', '111111111', 1, b'1', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '个人介绍', 'mz_daiz', 'SUOSHI', 'QUIT', '2023-10-14', b'0', 0, NULL, '2020-10-16 03:25:36', 1, '长风一梦0000', NULL);
INSERT INTO `t_user` VALUES (23, 1, '1234', '{bcrypt}$2a$10$j1U0rIRl8ODzc2j5rkLx8OSvjjEZ.cJ/Xe8DzkqL2jgAyX2c3x.4C', '444', NULL, NULL, b'0', NULL, '13002171921', NULL, 1, b'0', '', NULL, NULL, NULL, NULL, NULL, b'1', 1, '长风一梦8888', '2022-12-04 08:05:29', NULL, NULL, '2023-11-16 06:37:05');
INSERT INTO `t_user` VALUES (24, 1, 'admin2', '{bcrypt}$2a$10$ieDPTa5Awa8Kq48BzRuBuurU8.4AvUYUOX1RmRxxbccf56vUX/wbC', '仨是多3', 100, 100, b'0', 'em@163.com', '13002171921', NULL, 1, b'1', '', NULL, 'mz_daiz', 'XIAOXUE', 'QUIT', NULL, b'0', 1, '长风一梦8888', '2023-02-21 01:17:43', 1, '长风一梦8888', NULL);
INSERT INTO `t_user` VALUES (1710534752819261442, 2, 'admin', '{bcrypt}$2a$10$VjkzPHKfMoN0ZNLJSl.PkOWx9LVA7BqLmcI6sgyvzpe5D1INtm8xe', '雷军', NULL, NULL, b'0', NULL, '13002176666', NULL, 1, b'1', '', NULL, NULL, NULL, NULL, NULL, b'0', 1, '长风一梦8888', NULL, 0, NULL, NULL);
INSERT INTO `t_user` VALUES (1725059377381027842, 1, 'admin3', '{bcrypt}$2a$10$oaKlJc26SNFT4obD4B0aOOil12YtKH7/yyayzhbGrLrWyAgc.X.Ji', 'admin3', 10001, 104, b'0', NULL, '13002171933', NULL, 1, b'1', '', NULL, NULL, NULL, 'QUIT', NULL, b'0', 1, '长风一梦0000', '2023-11-16 15:53:00', 0, NULL, '2023-11-16 07:55:24');
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_dictionary_item`;
CREATE TABLE `t_tenant_dictionary_item` (
  `id` bigint NOT NULL COMMENT '雪花算法id',
  `tenant_id` bigint NOT NULL COMMENT '租户id，t_tenant.id',
  `dictionary_id` bigint NOT NULL COMMENT '类型ID',
  `dictionary_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `value` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `label` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '颜色',
  `deleted` bit(1) DEFAULT b'0',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '描述',
  `sequence` int DEFAULT '1' COMMENT '排序',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_tenant_dictionary_item
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_dictionary_item` VALUES (1686980638975541249, 1, 1686979951801745410, 'CONTRACT_SEAL_TYPE', 'contractSeal', '合同章', b'1', 'success', b'0', '', 1, 1, '长风一梦8888', '2023-08-03 06:01:41', 1, '长风一梦8888', '2023-08-03 06:02:47');
INSERT INTO `t_tenant_dictionary_item` VALUES (1686980976612818945, 1, 1686979951801745410, 'CONTRACT_SEAL_TYPE', 'officialSeal', '公章', b'1', 'success', b'0', '', 1, 1, '长风一梦8888', '2023-08-03 06:03:02', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1698636487254986754, 1, 1698634230920761345, 'PAYMENT_TYPE', 'L0104', '退质保金-供应商', b'1', 'success', b'0', '', 1, 1, '长风一梦8888', '2023-09-04 09:57:52', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1698636601793040386, 1, 1698634230920761345, 'PAYMENT_TYPE', 'L010', '预付款', b'1', 'success', b'0', '', 1, 1, '长风一梦8888', '2023-09-04 09:58:19', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1698636716859576322, 1, 1698634230920761345, 'PAYMENT_TYPE', 'L0106', '退供应商履约保证金', b'1', 'success', b'0', '', 1, 1, '长风一梦8888', '2023-09-04 09:58:47', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1698636805413916673, 1, 1698634189967577089, 'PAYMENT_METHOD', '1', '电汇', b'1', 'success', b'0', '', 1, 1, '长风一梦8888', '2023-09-04 09:59:08', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1698636835654848514, 1, 1698634189967577089, 'PAYMENT_METHOD', '2', '支票', b'1', 'success', b'0', '', 1, 1, '长风一梦8888', '2023-09-04 09:59:15', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1699342168325775361, 1, 1699341989447098369, 'TMS_PLATE_COLOR', 'RED', '红色', b'1', 'warning', b'0', '', 1, 1, '长风一梦8888', '2023-09-06 08:41:59', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1699342201385279489, 1, 1699341989447098369, 'TMS_PLATE_COLOR', 'BLUE', '蓝色', b'1', 'success', b'0', '', 1, 1, '长风一梦8888', '2023-09-06 08:42:07', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1699342322571304961, 1, 1699341989447098369, 'TMS_PLATE_COLOR', 'YELLOW', '黄色', b'1', 'warning', b'0', '', 1, 1, '长风一梦8888', '2023-09-06 08:42:36', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1699344187669241858, 1, 1699343995867914241, 'TMS_ENERGY_TYPE', '0', '汽油', b'1', NULL, b'0', '', 1, 1, '长风一梦8888', '2023-09-06 08:50:01', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1699344223807365121, 1, 1699343995867914241, 'TMS_ENERGY_TYPE', '1', '柴油', b'1', NULL, b'0', '', 1, 1, '长风一梦8888', '2023-09-06 08:50:10', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1700070437932326913, 1, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'annually_inspect', '年检', b'1', NULL, b'0', '', 1, 1, '长风一梦8888', '2023-09-08 08:55:52', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1700070499823476737, 1, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'annually_review', '年审', b'1', NULL, b'0', '', 1, 1, '长风一梦8888', '2023-09-08 08:56:07', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1700070552264859650, 1, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'strong_insurance', '强险', b'1', NULL, b'0', '', 1, 1, '长风一梦8888', '2023-09-08 08:56:20', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1700070643922984961, 1, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'commercial_insurance', '商业险', b'1', NULL, b'0', '', 1, 1, '长风一梦8888', '2023-09-08 08:56:41', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary_item` VALUES (1700070708079058946, 1, 1700070256599982081, 'TMS_EXPENSE_ITEM', 'gps_fee', 'GPS费用', b'1', NULL, b'0', '', 1, 1, '长风一梦8888', '2023-09-08 08:56:57', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_dictionary`;
CREATE TABLE `t_tenant_dictionary` (
  `id` bigint NOT NULL COMMENT '雪花算法ID',
  `tenant_id` bigint NOT NULL COMMENT '租户ID t_tenant.id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `status` bit(1) DEFAULT NULL COMMENT '状态',
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置角色',
  `sequence` tinyint DEFAULT NULL COMMENT '排序',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `created_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人ID',
  `last_modified_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `_index_code_tenant_id` (`tenant_id`,`code`) USING BTREE COMMENT '组合键：tenant_id、code'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_tenant_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_dictionary` VALUES (1686979951801745410, 1, 'CONTRACT_SEAL_TYPE', '用印类型', NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-08-03 05:58:57', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary` VALUES (1698634189967577089, 1, 'PAYMENT_METHOD', '付款方法', NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-04 09:48:44', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary` VALUES (1698634230920761345, 1, 'PAYMENT_TYPE', '付款类型', NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-04 09:48:54', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary` VALUES (1699341989447098369, 1, 'TMS_PLATE_COLOR', 'TMS车牌颜色', 'TMS车牌颜色', b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-06 08:41:17', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary` VALUES (1699343995867914241, 1, 'TMS_ENERGY_TYPE', 'TMS能源类型', NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-06 08:49:15', NULL, NULL, NULL);
INSERT INTO `t_tenant_dictionary` VALUES (1700070256599982081, 1, 'TMS_EXPENSE_ITEM', 'TMS费用项目', NULL, b'1', b'0', 0, b'0', 1, '长风一梦8888', '2023-09-08 08:55:09', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_config
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_config`;
CREATE TABLE `t_tenant_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `dynamic_datasource_id` bigint NOT NULL COMMENT '动态数据源ID',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='租户配置信息';

-- ----------------------------
-- Records of t_tenant_config
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
  `status` tinyint DEFAULT '0' COMMENT '0=未认证,1=已认证',
  `alias` varchar(50) DEFAULT NULL COMMENT '简称',
  `logo` varchar(255) DEFAULT NULL COMMENT 'LOGO',
  `email` varchar(50) DEFAULT NULL COMMENT '租户邮箱',
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
  `locked` bit(1) DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1722515686367961091 DEFAULT CHARSET=utf8mb3 COMMENT='租户信息';

-- ----------------------------
-- Records of t_tenant
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant` VALUES (1, '0000', '平台超级租户', 1, 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '000000@qq.com', '唐亚峰', '13002170000', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'https://cloud.battcn.com', NULL, b'0', b'0', 0, NULL, '2021-07-31 07:27:08', 1, '长风一梦', '2022-06-07 07:25:54');
INSERT INTO `t_tenant` VALUES (2, '2222', '小米科技有限责任公司', 1, 1, '小米', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '666666@qq.com', '雷军', '13002176666', '4', 110000, '北京市', 110100, '市辖区', '北京市海淀区西二旗中路33号院6号楼6层006号 ', 110108, '海淀区', '91110108551385082Q', '雷军', 'https://cloud.battcn.com', NULL, b'0', b'0', 0, NULL, '2021-07-31 08:14:02', 1, '长风一梦', '2022-06-07 07:24:34');
INSERT INTO `t_tenant` VALUES (3, '8888', '深圳腾讯科技股份有限公司', 1, 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '888888@qq.com', '马化腾', '13002178888', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'https://cloud.battcn.com', NULL, b'0', b'0', 0, NULL, '2021-07-31 07:27:08', 1, '长风一梦', '2022-06-07 07:25:25');
INSERT INTO `t_tenant` VALUES (1722515686367961090, '3333', '测试3333', 0, 0, '测试', NULL, NULL, '测试3333', '13000000000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', b'0', 1, '长风一梦0000', '2023-11-09 15:25:17', 1, '长风一梦0000', '2023-11-09 15:28:35');
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
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `content_type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
  `size` bigint DEFAULT NULL COMMENT '文件大小',
  `location` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录地点',
  `ip` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'IP',
  `engine` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '引擎类型',
  `engine_version` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '引擎版本',
  `os` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作系统',
  `bucket` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作系统',
  `origin_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原始名称',
  `target_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '目标名称',
  `mapping_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '映射地址',
  `full_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '完整地址',
  `extend` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拓展字段',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_TARGET_NAME` (`target_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文件';

-- ----------------------------
-- Records of t_file
-- ----------------------------
BEGIN;
INSERT INTO `t_file` VALUES (17, 1, 'image/jpeg', 98, '0|0|0|内网IP|内网IP', '127.0.0.1', '537.36', 'Webkit', 'OSX', 'battcn', '动物.jpg', '/20210804/61990178-5aac-4ba7-9f0b-1a52c36d8bed.jpg', 'http://qiniu.battcn.com', 'http://qiniu.battcn.com/20210804/61990178-5aac-4ba7-9f0b-1a52c36d8bed.jpg', NULL, b'0', 2, '不告诉你', '2021-08-04 03:03:20', NULL, NULL, NULL);
INSERT INTO `t_file` VALUES (18, 1, 'image/jpeg', 98, '0|0|0|内网IP|内网IP', '127.0.0.1', '537.36', 'Webkit', 'OSX', 'battcn', '动物.jpg', '/20210804/9178dfa8-6d57-4e01-856e-6952d3977a45.jpg', 'http://qiniu.battcn.com', 'http://qiniu.battcn.com/20210804/9178dfa8-6d57-4e01-856e-6952d3977a45.jpg', NULL, b'0', 2, '不告诉你', '2021-08-04 03:03:38', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_dynamic_datasource
-- ----------------------------
DROP TABLE IF EXISTS `t_dynamic_datasource`;
CREATE TABLE `t_dynamic_datasource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pool_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '连接池名称',
  `username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `db_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据库类型',
  `driver_class_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `database` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据库名称',
  `connection_type` tinyint DEFAULT '0' COMMENT '连接类型（0=单库多schema ,1 = 单库单schema）',
  `host` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'localhost' COMMENT '数据库连接',
  `port` int NOT NULL DEFAULT '3306' COMMENT '数据库端口',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述信息',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=正常1=禁用',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='动态数据源';

-- ----------------------------
-- Records of t_dynamic_datasource
-- ----------------------------
BEGIN;
INSERT INTO `t_dynamic_datasource` VALUES (1, 'TenantDataSourcePool', 'root', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', NULL, 0, 'localhost:3306', 3306, 'tenant-8888', b'0', b'0', NULL, NULL, '2021-08-06 13:57:40', 1, '长风一梦8888', '2021-11-27 06:50:34');
INSERT INTO `t_dynamic_datasource` VALUES (2, '123123123', 'admin', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', NULL, 0, '123123213123', 3306, NULL, b'0', b'1', 1, '长风一梦8888', '2023-02-21 01:02:51', 1, '长风一梦8888', '2023-11-09 07:28:47');
INSERT INTO `t_dynamic_datasource` VALUES (3, '21312312', 'admin', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', NULL, 0, '31231231231231', 3306, NULL, b'0', b'1', 1, '长风一梦8888', '2023-02-21 01:05:06', NULL, NULL, '2023-11-09 07:28:46');
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
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (8, 1);
INSERT INTO `sys_user_role` VALUES (10, 2);
INSERT INTO `sys_user_role` VALUES (12, 4);
INSERT INTO `sys_user_role` VALUES (14, 2);
INSERT INTO `sys_user_role` VALUES (19, 2);
INSERT INTO `sys_user_role` VALUES (20, 2);
INSERT INTO `sys_user_role` VALUES (21, 2);
INSERT INTO `sys_user_role` VALUES (22, 2);
INSERT INTO `sys_user_role` VALUES (24, 2);
INSERT INTO `sys_user_role` VALUES (1710534752819261442, 2);
INSERT INTO `sys_user_role` VALUES (1725059377381027842, 1725059482402205698);
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
  `type` tinyint DEFAULT NULL COMMENT '类型',
  `sequence` tinyint DEFAULT NULL COMMENT '排序',
  `org_id` bigint DEFAULT '0' COMMENT '组织ID\n#c_core_org',
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
) ENGINE=InnoDB AUTO_INCREMENT=10129 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='岗位';

-- ----------------------------
-- Records of sys_station
-- ----------------------------
BEGIN;
INSERT INTO `sys_station` VALUES (100, 1, '总经理', 'CEO', 2, 0, 100, b'1', '总部-1把手', b'0', '2019-07-10 17:03:03', NULL, 1, 1, '长风一梦8888', '2021-11-27 06:35:22');
INSERT INTO `sys_station` VALUES (101, 1, '副总经理', NULL, 2, 1, 10001, b'1', '总部-2把手', b'0', '2019-07-22 17:07:55', NULL, 1, 1, '长风一梦', '2021-07-28 02:58:53');
INSERT INTO `sys_station` VALUES (103, 1, '研发经理', NULL, 1, 1, 10002, b'1', '子公司-研发部老大', b'0', '2019-11-07 16:08:49', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:06');
INSERT INTO `sys_station` VALUES (104, 1, '副总经理', NULL, 2, 1, 10001, b'1', '子公司-老大', b'0', '2019-11-16 09:51:45', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:29');
INSERT INTO `sys_station` VALUES (106, 1, '产品经理', NULL, 0, 1, 10002, b'1', '子公司-产品部老大', b'0', '2019-11-16 09:53:27', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_station` VALUES (107, 1, '人事经理', NULL, 0, 1, 10002, b'1', '子公司-综合老大', b'0', '2019-11-16 09:54:43', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_station` VALUES (108, 1, 'Java研发', 'CTI', 0, 1, 10203, b'1', '普通员工', b'0', '2019-11-16 09:55:04', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:38');
INSERT INTO `sys_station` VALUES (1011, 1, 'UI工程师', 'CTI', 0, 1, 10007, b'1', '普通员工', b'0', '2019-11-16 09:55:40', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:08');
INSERT INTO `sys_station` VALUES (1012, 1, '运维工程师', 'CTI', NULL, 1, 10007, b'1', '普通员工', b'0', '2019-11-16 09:55:53', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:09');
INSERT INTO `sys_station` VALUES (1013, 1, '行政经理', NULL, 0, 1, 10003, b'1', '普通员工', b'0', '2019-11-16 09:56:04', NULL, 3, 1, '长风一梦8888', '2021-11-27 06:36:22');
INSERT INTO `sys_station` VALUES (1014, 1, '人事经理', NULL, 0, 1, 10007, b'1', '北京分公司-综合部老大', b'0', '2019-11-16 09:56:38', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:09');
INSERT INTO `sys_station` VALUES (1015, 1, '研发经理', NULL, 1, 1, 10007, b'1', '北京分公司-研发部老大', b'0', '2019-11-16 09:57:07', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:10');
INSERT INTO `sys_station` VALUES (1016, 1, '销售经理', NULL, 0, 1, 10007, b'1', '北京销售部老大', b'0', '2019-11-16 09:57:40', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:10');
INSERT INTO `sys_station` VALUES (10121, 1, '销售总监', NULL, 1, 1, 10005, b'1', '总部2把手', b'0', '2019-11-16 09:59:10', NULL, 3, 1, '长风一梦', '2021-07-29 02:36:31');
INSERT INTO `sys_station` VALUES (10122, 1, '销售员工', NULL, 0, 1, 10007, b'1', '普通员工', b'0', '2019-11-16 09:58:41', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:11');
INSERT INTO `sys_station` VALUES (10123, 1, '财务总监', NULL, 2, 1, 10007, b'1', '总部2把手', b'0', '2019-11-16 09:59:39', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:11');
INSERT INTO `sys_station` VALUES (10124, 1, '市场经理', NULL, 0, 1, 10007, b'1', '总部市场部老大', b'0', '2019-11-16 10:00:03', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:11');
INSERT INTO `sys_station` VALUES (10128, 1, '前端工程师', 'CTI', 1, 1, 10007, b'1', '普通员工', b'0', '2019-11-16 10:00:43', NULL, 3, 1, '长风一梦8888', '2021-11-27 06:38:03');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of sys_site_notify
-- ----------------------------
BEGIN;
INSERT INTO `sys_site_notify` VALUES (2, 1, '1', 1, '1', '测试消息', '2,1', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, '管理员', '2021-07-12 11:41:31', 1, '长风一梦8888', '2021-11-16 11:06:34');
INSERT INTO `sys_site_notify` VALUES (3, 1, '0', 1, '1', '测试通知', '1', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'0', 1, '管理员', '2021-07-12 11:42:15', 1, '长风一梦8888', '2021-11-16 11:06:38');
INSERT INTO `sys_site_notify` VALUES (4, 2, '2', 1, '1', '测试待办', '1', '<p>测试待办</p><img src=\"http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/15/30369909559790.png\" contenteditable=\"false\"/>', '测试待办', b'0', 1, '管理员', '2021-07-12 11:42:26', 1, '长风一梦8888', '2021-11-16 11:06:38');
COMMIT;

-- ----------------------------
-- Table structure for sys_site_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_site_message`;
CREATE TABLE `sys_site_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `level` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息级别',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `mark` bit(1) DEFAULT b'0' COMMENT '状态（0=未读 1=已读）',
  `receive_id` bigint DEFAULT NULL COMMENT '接收人ID',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of sys_site_message
-- ----------------------------
BEGIN;
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
INSERT INTO `sys_role_res` VALUES (1, 1, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 2, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 11, '2023-09-18 02:53:03');
INSERT INTO `sys_role_res` VALUES (1, 20, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 30, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 50, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 101, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 102, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 103, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 105, '2023-09-14 09:17:17');
INSERT INTO `sys_role_res` VALUES (1, 106, '2023-09-14 09:10:09');
INSERT INTO `sys_role_res` VALUES (1, 120, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 201, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 202, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 203, '2023-11-06 09:30:30');
INSERT INTO `sys_role_res` VALUES (1, 205, '2023-10-06 10:26:29');
INSERT INTO `sys_role_res` VALUES (1, 1101, '2023-09-18 02:53:07');
INSERT INTO `sys_role_res` VALUES (1, 1102, '2023-09-18 02:53:09');
INSERT INTO `sys_role_res` VALUES (1, 2001, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 3001, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 3002, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 5001, '2023-05-18 05:39:59');
INSERT INTO `sys_role_res` VALUES (1, 5002, '2023-05-18 05:40:53');
INSERT INTO `sys_role_res` VALUES (1, 5030, '2023-09-15 15:49:31');
INSERT INTO `sys_role_res` VALUES (1, 5050, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 5060, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 5070, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 10101, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 10102, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 10103, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 10201, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 10202, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 10301, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 10302, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 10303, '2023-11-14 08:41:50');
INSERT INTO `sys_role_res` VALUES (1, 10304, '2023-11-14 08:41:56');
INSERT INTO `sys_role_res` VALUES (1, 10501, '2023-09-14 09:17:19');
INSERT INTO `sys_role_res` VALUES (1, 10601, '2023-09-14 09:10:12');
INSERT INTO `sys_role_res` VALUES (1, 10602, '2023-09-14 09:10:15');
INSERT INTO `sys_role_res` VALUES (1, 12001, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 12002, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 20101, '2023-11-09 07:24:49');
INSERT INTO `sys_role_res` VALUES (1, 20102, '2023-11-09 07:28:01');
INSERT INTO `sys_role_res` VALUES (1, 20103, '2023-11-09 07:28:08');
INSERT INTO `sys_role_res` VALUES (1, 20104, '2023-11-09 07:30:53');
INSERT INTO `sys_role_res` VALUES (1, 20301, '2023-11-14 08:46:53');
INSERT INTO `sys_role_res` VALUES (1, 20302, '2023-11-14 08:46:56');
INSERT INTO `sys_role_res` VALUES (1, 20501, '2023-10-06 10:36:23');
INSERT INTO `sys_role_res` VALUES (1, 20502, '2023-10-06 10:36:25');
INSERT INTO `sys_role_res` VALUES (1, 20503, '2023-10-06 10:36:28');
INSERT INTO `sys_role_res` VALUES (1, 110101, '2023-09-18 03:02:11');
INSERT INTO `sys_role_res` VALUES (1, 110102, '2023-09-18 03:02:24');
INSERT INTO `sys_role_res` VALUES (1, 110103, '2023-09-18 03:02:28');
INSERT INTO `sys_role_res` VALUES (1, 110201, '2023-09-18 03:02:34');
INSERT INTO `sys_role_res` VALUES (1, 110202, '2023-09-18 03:02:38');
INSERT INTO `sys_role_res` VALUES (1, 110203, '2023-09-18 03:02:40');
INSERT INTO `sys_role_res` VALUES (1, 500201, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 500202, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 500203, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 503001, '2023-09-16 12:07:47');
INSERT INTO `sys_role_res` VALUES (1, 503002, '2023-09-16 12:07:49');
INSERT INTO `sys_role_res` VALUES (1, 1010101, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1010102, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1010104, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1010301, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1010302, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1010303, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1020201, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1020202, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1020203, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1020204, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1020205, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1030101, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1030102, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1030103, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 1030104, '2023-06-02 07:10:39');
INSERT INTO `sys_role_res` VALUES (1, 1030105, '2023-09-16 14:18:20');
INSERT INTO `sys_role_res` VALUES (1, 1030106, '2023-09-16 14:18:31');
INSERT INTO `sys_role_res` VALUES (1, 1030107, '2023-09-16 14:18:38');
INSERT INTO `sys_role_res` VALUES (1, 1030108, '2023-09-16 14:18:50');
INSERT INTO `sys_role_res` VALUES (1, 1030301, '2023-11-14 09:07:52');
INSERT INTO `sys_role_res` VALUES (1, 1030302, '2023-11-14 09:07:54');
INSERT INTO `sys_role_res` VALUES (1, 1030303, '2023-11-14 09:07:56');
INSERT INTO `sys_role_res` VALUES (2, 1, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 20, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 30, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 101, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 102, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 103, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 120, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 2001, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 3001, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 3002, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 10101, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 10102, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 10103, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 10202, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 10301, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 12001, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 12002, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1010104, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1010302, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1020201, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1020202, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1020203, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1020204, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1020205, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1030101, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1030102, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (2, 1030103, '2023-09-21 05:05:50');
INSERT INTO `sys_role_res` VALUES (1725059482402205698, 1, '2023-11-16 07:55:43');
INSERT INTO `sys_role_res` VALUES (1725059482402205698, 101, '2023-11-16 07:55:43');
INSERT INTO `sys_role_res` VALUES (1725059482402205698, 120, '2023-11-16 07:55:43');
INSERT INTO `sys_role_res` VALUES (1725059482402205698, 10101, '2023-11-16 07:55:43');
INSERT INTO `sys_role_res` VALUES (1725059482402205698, 12001, '2023-11-16 07:55:43');
INSERT INTO `sys_role_res` VALUES (1725059482402205698, 12002, '2023-11-16 07:55:43');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` int DEFAULT NULL COMMENT '租户编码',
  `code` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色编码',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `scope_type` tinyint DEFAULT NULL COMMENT '数据权限范围，值越大，权限越大',
  `locked` tinyint(1) DEFAULT '0' COMMENT '0=正常1=禁用',
  `super` tinyint(1) DEFAULT '0' COMMENT '0=非 1=管理员',
  `readonly` tinyint(1) DEFAULT '0' COMMENT '是否内置角色',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1725059482402205699 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 1, 'PLATFORM_ADMIN', '平台管理员', '平台管理员，拥有所有数据可视权', 50, 0, 1, 1, 0, '超级管理员', '2019-10-25 13:46:00', 57, NULL, '2021-07-15 06:38:17');
INSERT INTO `sys_role` VALUES (2, 1, 'TENANT-ADMIN', '租户管理员', '租户管理员', 50, 0, 0, 1, 1, '长风一梦8888', '2022-06-07 07:52:29', 0, NULL, '2022-06-07 07:54:07');
INSERT INTO `sys_role` VALUES (1725059482402205698, 1, 'TEST', '测试权限范围', '123213', 10, 0, 0, 0, 1, '长风一梦0000', '2023-11-16 15:53:25', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
  `tree_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT ',' COMMENT '该节点的所有父节点',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限',
  `parent_id` bigint DEFAULT '0' COMMENT '父级菜单ID',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件',
  `sequence` int DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '菜单图标',
  `style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '样式',
  `type` tinyint DEFAULT '1' COMMENT '类型（1=菜单;2=按钮）',
  `status` bit(1) DEFAULT b'1' COMMENT '1=启用;0=禁用',
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置菜单（0=否;1=是）',
  `global` bit(1) DEFAULT b'0' COMMENT '公共资源\nTrue是无需分配所有人就可以访问的',
  `display` bit(1) DEFAULT b'1' COMMENT '0=隐藏;1=显示',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `INX_STATUS` (`global`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1060204 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES (1, '系统管理', ',', 'system:view', 0, '/system/management', 'Layout', 2, 'ant-design:dashboard-filled', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2020-11-20 09:39:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (2, '平台管理', ',', 'platform:view', 0, '/platform', 'Layout', 1, 'clarity-thin-client-line', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-08-06 09:26:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (11, '审批管理', ',', NULL, 0, '/bpm', 'Layout', 11, 'ant-design:appstore-twotone', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2022-09-09 14:55:58', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (50, '开发平台', ',', 'development:view', 0, '/development', 'Layout', 50, 'ant-design:appstore-add-outlined', NULL, 1, b'1', b'0', b'0', b'1', '运维监控', b'0', NULL, NULL, '2020-11-09 09:49:52', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (101, '用户中心', ',1', 'user:info:view', 1, '/system/center', 'Layout', 0, 'ant-design:user-outlined', NULL, 1, b'1', b'0', b'0', b'1', '用户中心', b'0', 1, NULL, '2019-07-25 16:11:00', 2, '不告诉你', '2019-11-11 14:28:40');
INSERT INTO `sys_resource` VALUES (102, '权限管理', ',1', 'auth:view', 1, '/system/auth', NULL, 1, 'ant-design:security-scan-outlined', NULL, 1, b'1', b'0', b'0', b'1', '权限管理', b'0', 1, NULL, '2019-07-25 16:11:41', 3, NULL, '2019-11-11 14:28:43');
INSERT INTO `sys_resource` VALUES (103, '业务数据', ',1', 'business:view', 1, '/system/basic', NULL, 2, 'file-icons:config-coffeescript', NULL, 1, b'1', b'0', b'0', b'1', '基础配置', b'0', 1, NULL, '2019-07-25 16:13:09', 2, '不告诉你', '2019-11-11 14:28:49');
INSERT INTO `sys_resource` VALUES (105, '资源中心', ',', 'resource:view', 1, '/resource', 'Layout', 20, 'carbon-group-resource', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-08-03 05:47:50', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (106, '消息中心', ',', 'message:view', 1, '/message', 'Layout', 30, 'bx-bx-message-alt-dots', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-02 09:48:51', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (120, '日志管理', ',1', 'log:view', 1, '/development/log', NULL, 20, 'radix-icons:activity-log', NULL, 1, b'1', b'0', b'0', b'1', '日志管理', b'0', NULL, NULL, '2020-11-12 04:51:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (201, '租户管理', ',2', 'tenant:page', 2, '/platform/tenant/index', '/wemirr/platform/tenant/index', 0, 'ant-design:code-sandbox-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-30 09:11:36', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (202, '连接管理', ',2', NULL, 2, '/platform/tenant/database', '/wemirr/platform/tenant/database/index', 1, 'ant-design:database-filled', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦', '2021-08-06 09:33:09', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (203, '业务数据', ',2', 'plat:view', 2, '/platform/business', 'Layout', 2, 'file-icons:config-coffeescript', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-11-14 08:43:46', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1101, '流程设置', ',11', NULL, 11, '/bpm/setting', 'Layout', 11, 'ant-design:delivered-procedure-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2022-09-09 14:55:58', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (1102, '审批办公', ',11', NULL, 11, '/bpm/approval', 'Layout', 11, 'ant-design:appstore-add-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2022-10-20 07:42:59', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (5001, '代码生成', ',50', 'development:generate:code', 50, '/development/generate/code', '/wemirr/development/generate/code/index', 1, 'ant-design:code-sandbox-circle-filled', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-10-30 08:41:51', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (5002, '网关管理', ',50', 'system:gateway:view', 50, '/development/gateway', NULL, 0, 'ant-design:gateway-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2020-11-20 02:38:13', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (5020, '在线开发', ',50', NULL, 50, '/development/online', '/wemirr/development/online/index', 2, 'ant-design:codepen-square-filled', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2022-12-12 11:45:35', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (5030, '安全中心', ',50', 'application:management', 50, '/system/security', 'Layout', 0, 'carbon-application', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦', '2021-07-30 09:10:07', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (5050, '监控中心', ',50', 'development:view', 50, '/system/monitor', '/system/development/monitor', 50, 'eos-icons:monitoring', NULL, 1, b'1', b'0', b'1', b'1', '监控中心', b'0', NULL, NULL, '2020-11-09 09:55:04', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (5060, '注册中心', ',50', 'service:governance:nacos:view', 50, 'http://localhost:8848/nacos', '/system/development/nacos', 60, 'ant-design:insert-row-left-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2020-11-20 05:42:36', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (5070, '服务治理', ',50', 'nepxion:platform', 50, 'http://218.78.55.64:6001/platform', 'Layout', 70, 'ri-government-fill', NULL, 1, b'1', b'0', b'1', b'1', '', b'0', NULL, NULL, '2021-07-12 08:09:34', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (10101, '用户管理', ',1,101', 'sys:users:page', 101, '/system/user', '/wemirr/system/user/index', 0, 'heroicons-outline:user-group', NULL, 1, b'1', b'0', b'0', b'1', '用户管理', b'0', NULL, NULL, '2020-11-09 07:50:53', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (10102, '组织管理', ',1,101', 'sys:org:page', 101, '/system/org', '/wemirr/system/org/index', 1, 'codicon-organization', NULL, 1, b'1', b'0', b'0', b'1', '组织管理', b'0', NULL, NULL, '2020-11-09 07:51:00', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10103, '岗位管理', ',1,101', 'stations:page', 101, '/system/station', '/wemirr/system/station/index', 2, 'ant-design:usergroup-add-outlined', NULL, 1, b'1', b'0', b'0', b'1', '岗位管理', b'0', NULL, NULL, '2020-11-09 07:51:09', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (10201, '菜单管理', ',1,102', 'menu:view', 102, '/system/menu', '/wemirr/system/auth/menu/index', 1, 'ant-design:menu-unfold-outlined', NULL, 1, b'1', b'0', b'0', b'1', '菜单管理', b'0', NULL, NULL, '2020-11-09 07:51:37', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (10202, '角色管理', ',1,102', 'sys:roles:page', 102, '/system/role', '/wemirr/system/auth/role/index', 1, 'eos-icons:role-binding-outlined', NULL, 1, b'1', b'0', b'0', b'1', '角色管理', b'0', NULL, NULL, '2020-11-09 07:51:41', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10301, '租户字典', ',103', 'tenant:dict:view', 103, '/system/basic/dict', '/wemirr/system/basic/dict/index', 1, 'bx-bxs-data', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-10-06 10:25:58', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10303, '语言管理', ',103', 'sys:i18n:page', 103, '/system/basic/i18n', '/wemirr/system/basic/i18n/index', 2, 'cil:language', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-11-06 09:29:54', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10501, '文件管理', ',105', 'resource:file', 105, '/system/resource/file', '/wemirr/system/resource/file/index', 1, 'akar-icons:file', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-08-03 05:49:21', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10601, '我的消息', ',106', 'message:my', 106, '/system/message/my', '/wemirr/system/message/my/index', 1, 'ant-design:comment-outlined', NULL, 1, b'1', b'0', b'0', b'1', '我的消息', b'0', NULL, NULL, '2020-11-19 02:50:54', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (10602, '站内消息', ',106', 'sys:site_notifies:page', 106, '/system/message/notify', '/wemirr/system/message/notify/index', 1, 'codicon-repo-push', NULL, 1, b'1', b'0', b'0', b'1', '发布消息', b'0', NULL, NULL, '2020-11-19 02:48:22', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (12001, '登录日志', ',1,120', 'log:login:page', 120, '/system/log/login', '/wemirr/system/log/login/index', 1, 'carbon-login', NULL, 1, b'1', b'0', b'0', b'1', '登录日志', b'0', NULL, NULL, '2020-11-09 09:50:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (12002, '操作日志', ',1,120', 'log:opt:page', 120, '/system/log/opt', '/wemirr/system/log/opt/index', 2, 'carbon-operation', NULL, 1, b'1', b'0', b'0', b'1', '操作日志', b'0', NULL, NULL, '2020-11-09 09:50:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20101, '添加', ',201', 'tenant:add', 201, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:20', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20102, '编辑', ',201', 'tenant:edit', 201, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:30', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20103, '删除', ',201', 'tenant:remove', 201, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20104, '连接配置', ',201', 'tenant:config', 201, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-11-09 07:30:31', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20105, '初始数据', ',201', 'tenant:init', 201, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-11-09 07:31:24', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20301, '数据字典', ',1,203', 'sys:dict:page', 203, '/platform/dict', '/wemirr/platform/basic/dict/index', 1, 'bx-bxs-data', NULL, 1, b'1', b'0', b'0', b'1', '数据字典', b'0', NULL, NULL, '2020-11-09 07:52:59', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20302, '地区信息', ',1,203', 'area:view', 203, '/platform/area', '/wemirr/platform/basic/area/index', 1, 'ant-design:area-chart-outlined', NULL, 1, b'1', b'0', b'0', b'1', '地区信息', b'0', NULL, NULL, '2020-11-09 07:53:12', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20501, '添加', ',2,205', 'tenant:dict:add', 205, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-10-06 10:35:37', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20502, '编辑', ',2,205', 'tenant:dict:edit', 205, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-10-06 10:35:55', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20503, '删除', ',2,205', 'tenant:dict:remove', 205, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-10-06 10:36:05', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (110101, '模型管理', ',1101,11', NULL, 1101, '/bpm/process/model', '/wemirr/bpm/process/model/index', 0, 'ant-design:align-center-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 05:42:40', 1603373363570712578, '不会说话的人', NULL);
INSERT INTO `sys_resource` VALUES (110102, '流程类别', ',1101,11', NULL, 1101, '/bpm/process/category', '/wemirr/bpm/process/category/index', 0, 'ant-design:ant-design-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 06:26:07', 1603373363570712578, '不会说话的人', NULL);
INSERT INTO `sys_resource` VALUES (110103, '流程列表', ',1102,11', NULL, 1102, '/bpm/process/list', '/wemirr/bpm/process/list/index', 0, 'ant-design:shake-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 07:16:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (110202, '任务列表', ',1102,11', NULL, 1102, '/bpm/task/list', '/wemirr/bpm/task/list/index', 0, 'ant-design:ant-design-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 07:18:42', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (110203, '历史任务', ',1102,11', NULL, 1102, '/bpm/task/history', '/wemirr/bpm/task/history/index', 0, 'ant-design:appstore-add-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', 1603373363570712578, '不会说话的人', '2023-02-23 07:42:27', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (500201, '限流规则', ',50,5002', 'development:gateway:limit', 5002, '/development/gateway/limit', '/wemirr/development/gateway/limit/index', 1, 'carbon-rule', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2020-11-20 02:38:51', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (500202, '限访名单', ',50,5002', 'development:gateway:blacklist', 5002, '/development/gateway/blacklist', '/wemirr/development/gateway/blacklist/index', 2, 'carbon-ai-status-rejected', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2020-11-20 02:39:02', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (500203, '网关路由', ',50,5002', 'development:gateway:route', 5002, '/development/gateway/route', '/wemirr/development/gateway/route/index', 3, 'mdi-router-wireless-settings', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-02-02 07:52:26', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (503001, '终端管理', ',5030,50', NULL, 5030, '/system/security/client', '/wemirr/system/security/client/index', 1, '', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-09-16 12:03:48', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (503002, '令牌管理', ',5030,50', NULL, 5030, '/system/security/token', '/wemirr/system/security/token/index', 1, '', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-09-16 12:05:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010101, '添加', ',1,101,10101', 'sys:users:add', 10101, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-05 10:12:11', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (1010102, '编辑', ',1,101,10101', 'sys:users:edit', 10101, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-05 10:13:50', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (1010104, '删除', ',1,101,10101', 'sys:users:remove', 10101, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-05 14:25:09', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (1010201, '添加', ',1,101,10102', 'sys:dict:add', 10102, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:54:12', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010202, '编辑', ',1,101,10102', 'sys:dict:edit', 10102, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:54:18', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010203, '删除', ',1,101,10102', 'sys:dict:remove', 10102, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:54:21', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010301, '添加', ',1,101,10103', 'sys:stations:add', 10103, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:46:49', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010302, '编辑', ',1,101,10103', 'sys:stations:edit', 10103, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:47:31', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010303, '删除', ',1,101,10103', 'sys:stations:remove', 10103, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:47:34', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020201, '添加', ',1,102,10202', 'sys:roles:add', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:56:38', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (1020202, '编辑', ',1,102,10202', 'sys:roles:edit', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:56:40', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (1020203, '删除', ',1,102,10202', 'sys:roles:remove', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:56:43', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (1020204, '分配用户', ',1,102,10202', 'sys:roles:distribution:user', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 10:02:27', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (1020205, '分配权限', ',1,102,10202', 'sys:roles:distribution:res', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 10:02:36', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (1030101, '添加', ',1,103,10301', 'sys:dict:add', 10301, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:54:12', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030102, '编辑', ',1,103,10301', 'sys:dict:edit', 10301, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:54:18', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030103, '删除', ',1,103,10301', 'sys:dict:remove', 10301, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:54:21', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030105, '添加', ',', 'sys:site_notifies:add', 10602, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:20', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030301, '添加', ',1,103,10303', 'sys:i18n:add', 10303, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:54:12', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030302, '编辑', ',1,103,10303', 'sys:i18n:edit', 10303, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:54:18', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030303, '删除', ',1,103,10303', 'sys:i18n:remove', 10303, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-21 09:54:21', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1060201, '编辑', ',', 'sys:site_notifies:edit', 10602, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:30', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1060202, '删除', ',', 'sys:site_notifies:remove', 10602, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1060203, '发布', ',', 'sys:site_notifies:publish', 10602, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:50', NULL, NULL, NULL);
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
  `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT ',' COMMENT '所有父级ID',
  `parent_id` bigint DEFAULT '0' COMMENT '父ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=1710534752424996866 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组织';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` VALUES (100, '鏖战八方', 1, '鏖战', '0746-8485560转1', ',', 0, 1, b'1', b'0', '初始化数据', 1, '1', '2019-07-10 17:02:18', 1, '1', '2021-07-20 06:26:04');
INSERT INTO `sys_org` VALUES (10001, '鏖战八方上海分部', 1, '鏖战上海', '0746-8485560转2', ',100', 100, 0, b'1', b'0', '初始化数据', 1, '1', '2019-08-06 09:10:53', 1, '1', '2021-07-20 06:26:05');
INSERT INTO `sys_org` VALUES (10002, '鏖战八方北京分部', 1, '鏖战北京', '0746-8485560转3', ',100', 100, 1, b'1', b'0', '初始化数据', 1, '1', '2019-11-07 16:13:09', 1, '1', '2021-07-20 06:26:05');
INSERT INTO `sys_org` VALUES (10003, '管理层', 1, '管理层', '0746-8485560转4', ',100', 100, 3, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:34:39', 3, '3', '2021-07-20 06:26:06');
INSERT INTO `sys_org` VALUES (10004, '总经办', 1, '', '0746-8485560', ',100', 100, 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:35:37', 1, '长风一梦', '2021-07-20 06:26:06');
INSERT INTO `sys_org` VALUES (10005, '财务部', 1, '', NULL, ',100', 100, 4, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:37:17', 3, '3', '2021-07-20 06:26:06');
INSERT INTO `sys_org` VALUES (10006, '市场部', 1, '', NULL, ',100', 100, 5, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:37:37', 3, '3', '2021-07-20 06:26:06');
INSERT INTO `sys_org` VALUES (10101, '综合部', 1, '综合部', '0746-8485560转7', ',100,10001', 10001, 0, b'1', b'0', '前台&HR11', 3, '3', '2019-11-12 11:34:27', 1, '长风一梦8888', '2022-12-04 07:59:50');
INSERT INTO `sys_org` VALUES (10102, '研发部', 1, '研发部', '0746-8485560转5', ',100,10001', 10001, 5, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:21', 2, '不告诉你', '2021-07-20 06:26:11');
INSERT INTO `sys_org` VALUES (10103, '产品部', 1, '产品部', '0746-8485560转6', ',100,10001', 10001, 2, b'0', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:31', 2, '不告诉你', '2021-07-20 06:26:07');
INSERT INTO `sys_org` VALUES (10201, '综合部', 1, '综合部', NULL, ',100,10002', 10002, 0, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:39', 3, '3', '2021-07-20 06:26:07');
INSERT INTO `sys_org` VALUES (10202, '测试部', 1, '测试部', NULL, ',100,10002', 10002, 1, b'1', b'0', '初始化数据', 2, '不告诉你', '2020-10-29 06:39:09', 2, '不告诉你', '2021-07-20 07:13:59');
INSERT INTO `sys_org` VALUES (10203, '研发部', 1, '', NULL, ',100,10002', 10002, 0, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:50', 3, '3', '2021-07-20 07:14:00');
INSERT INTO `sys_org` VALUES (10204, '销售部', 1, '', NULL, ',100,10002', 10002, 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:39:00', 3, '3', '2021-07-20 07:14:01');
INSERT INTO `sys_org` VALUES (1710534752424996865, '小米科技有限责任公司', 2, '', NULL, ',', 0, 0, b'1', b'0', '不可删除不可修改', 1, '长风一梦8888', NULL, 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_data_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_permission_resource`;
CREATE TABLE `sys_data_permission_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `owner_id` bigint NOT NULL COMMENT '拥有者',
  `owner_type` varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '拥有类型（角色，用户）',
  `data_id` bigint NOT NULL COMMENT '数据ID',
  `data_type` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '数据类型（机构、角色、租户等等）',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='数据权限资源表';

-- ----------------------------
-- Records of sys_data_permission_resource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_registered_client`;
CREATE TABLE `oauth2_registered_client` (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `client_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `client_secret_expires_at` timestamp NULL DEFAULT NULL,
  `client_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `client_authentication_methods` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `authorization_grant_types` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `redirect_uris` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `post_logout_redirect_uris` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `scopes` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `client_settings` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `token_settings` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
BEGIN;
INSERT INTO `oauth2_registered_client` VALUES ('4', 'client', '2023-09-11 06:39:32', '{bcrypt}$2a$10$myyT/CfX.R6KlZPRGUofleBGF81MX5O2HaNwqD8crp3OwYJwwW1fe', NULL, 'client', 'client_secret_basic', 'refresh_token,client_credentials,authorization_code,urn:ietf:params:oauth:grant-type:custom,password', 'http://127.0.0.1:5001/oauth2/code,https://www.baidu.com', '', 'client', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",7200],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",604800],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300]}', b'0', NULL, NULL, '2023-09-15 16:43:14', NULL, NULL, NULL);
INSERT INTO `oauth2_registered_client` VALUES ('ace5077d-cc3e-47b4-9997-e786dc2b4b17', 'messaging-client', '2023-08-18 09:55:44', '{bcrypt}$2a$10$VRaHmV/Kfw/lOfuwRrChoe17auuUpN5NLxJh0UpLvDcvS1K4KNovC', NULL, 'ace5077d-cc3e-47b4-9997-e786dc2b4b17', 'client_secret_basic', 'refresh_token,client_credentials,authorization_code,urn:ietf:params:oauth:grant-type:sms_code,urn:ietf:params:oauth:grant-type:password,urn:ietf:params:oauth:grant-type:custom,password', 'http://127.0.0.1:5001/oauth2/code,https://www.baidu.com', '', 'platform,web,openid,profile,message.read,message.write', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",7200],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",604800],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300]}', b'0', NULL, NULL, '2023-09-18 02:58:27', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for common_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `common_opt_log`;
CREATE TABLE `common_opt_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作IP',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `trace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志链路追踪id日志标志',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作描述',
  `action` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类路径',
  `uri` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求地址',
  `http_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'GET' COMMENT '请求类型\n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
  `request` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求参数',
  `response` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '返回值',
  `message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '异常详情信息',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `consuming_time` bigint DEFAULT '0' COMMENT '消耗时间',
  `browser` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器',
  `os` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器',
  `engine` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器',
  `platform` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统日志';

-- ----------------------------
-- Records of common_opt_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for common_login_log
-- ----------------------------
DROP TABLE IF EXISTS `common_login_log`;
CREATE TABLE `common_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint DEFAULT NULL COMMENT '登录人ID',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录IP',
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录地点',
  `client_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人客户端ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人姓名',
  `principal` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人账号',
  `platform` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台',
  `engine` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '引擎类型',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
  `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作系统',
  `created_by` bigint DEFAULT NULL,
  `created_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录日志';

-- ----------------------------
-- Records of common_login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for common_i18n_locale_message
-- ----------------------------
DROP TABLE IF EXISTS `common_i18n_locale_message`;
CREATE TABLE `common_i18n_locale_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint DEFAULT NULL COMMENT 'i18n_data.id',
  `locale` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '语言',
  `message` varchar(255) DEFAULT NULL COMMENT '文本值，可以使用 {} 加角标，作为占位符',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1724355494610595843 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国际化信息';

-- ----------------------------
-- Records of common_i18n_locale_message
-- ----------------------------
BEGIN;
INSERT INTO `common_i18n_locale_message` VALUES (1721821543056539650, 1721821542981042178, 'zh_CN', '测试中文内容 {i18n.demo}', 1, '长风一梦0000', '2023-11-07 17:27:00');
INSERT INTO `common_i18n_locale_message` VALUES (1721821543060733954, 1721821542981042178, 'en_US', 'English Context', 1, '长风一梦0000', '2023-11-07 17:27:00');
COMMIT;

-- ----------------------------
-- Table structure for common_i18n_data
-- ----------------------------
DROP TABLE IF EXISTS `common_i18n_data`;
CREATE TABLE `common_i18n_data` (
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
-- Records of common_i18n_data
-- ----------------------------
BEGIN;
INSERT INTO `common_i18n_data` VALUES (1721821542981042178, 'i18n.db', '测试中文内容 {i18n.demo}', 1, b'0', 1, '长风一梦0000', '2023-11-07 17:27:00', 0, NULL, NULL);
INSERT INTO `common_i18n_data` VALUES (1724355494514126849, '123', '213', 1, b'1', 1, '长风一梦0000', '2023-11-14 17:16:02', 0, NULL, '2023-11-14 09:16:04');
COMMIT;

-- ----------------------------
-- Table structure for common_generate
-- ----------------------------
DROP TABLE IF EXISTS `common_generate`;
CREATE TABLE `common_generate` (
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
) ENGINE=InnoDB AUTO_INCREMENT=1706551921692655619 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成器';

-- ----------------------------
-- Records of common_generate
-- ----------------------------
BEGIN;
INSERT INTO `common_generate` VALUES (6, NULL, b'01', 'Levin', 't_user', 'com.wemirr.platform', 'authority', 't_', '/api/users', 'locked', 1, b'00', 0, NULL, '2020-11-06 09:34:40', 1, '长风一梦8888', '2022-06-06 05:04:37');
INSERT INTO `common_generate` VALUES (31, NULL, b'00', 'Levin', 't_user', 'com.wemirr.platform.authority', 'User', 't_', '/api/users', 'locked', 1, b'00', 2, '不告诉你', '2020-11-06 09:34:40', 2, '不告诉你', '2021-11-26 06:11:04');
INSERT INTO `common_generate` VALUES (1706551921692655618, '/Users/battcn/Development/opensource/wemirr-platform/wemirr-platform-tools/target', b'00', '长风一梦8888', 'common_dictionary', 'com.wemirr.platform.authority', 'common', 'common', '/api', NULL, 0, b'00', 1, '长风一梦8888', NULL, 1, '长风一梦8888', NULL);
COMMIT;

-- ----------------------------
-- Table structure for common_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `common_dictionary_item`;
CREATE TABLE `common_dictionary_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dictionary_id` bigint NOT NULL COMMENT '类型ID',
  `dictionary_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型',
  `value` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '编码',
  `label` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `color` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '颜色',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '描述',
  `sequence` int DEFAULT '1' COMMENT '排序',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dict_code_item_code_uniq` (`dictionary_code`,`value`) USING BTREE COMMENT '字典编码与字典项目编码联合唯一'
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典项';

-- ----------------------------
-- Records of common_dictionary_item
-- ----------------------------
BEGIN;
INSERT INTO `common_dictionary_item` VALUES (1, 4, 'AREA_LEVEL', 'COUNTRY', '国家', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-01-20 15:12:57', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (2, 4, 'AREA_LEVEL', 'PROVINCE', '省份', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2020-01-20 15:13:45', 1, '长风一梦', '2021-07-31 08:18:51');
INSERT INTO `common_dictionary_item` VALUES (3, 4, 'AREA_LEVEL', 'CITY', '地市', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2020-01-20 15:14:16', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (4, 4, 'AREA_LEVEL', 'COUNTY', '区县', b'1', 'warning', '', 4, b'0', 0, '系统管理员', '2020-01-20 15:14:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (5, 5, 'ORG_TYPE', '01', '单位', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-08-19 15:03:40', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (6, 5, 'ORG_TYPE', '02', '部门', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-08-19 15:03:59', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (38, 3, 'EDUCATION', 'ZHUANKE', '专科', b'1', 'warning', '', 4, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (39, 3, 'EDUCATION', 'COLLEGE', '本科', b'1', 'warning', '', 5, b'0', 0, '系统管理员', '2019-06-04 11:36:19', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (40, 3, 'EDUCATION', 'SUOSHI', '硕士', b'1', 'warning', '', 6, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (41, 3, 'EDUCATION', 'BOSHI', '博士', b'1', 'warning', '', 7, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (42, 3, 'EDUCATION', 'BOSHIHOU', '博士后', b'1', 'warning', '', 8, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (43, 1, 'NATION', 'mz_hanz', '汉族', b'1', 'warning', '', 0, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (44, 1, 'NATION', 'mz_zz', '壮族', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (45, 1, 'NATION', 'mz_mz', '满族', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (46, 1, 'NATION', 'mz_hz', '回族', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (47, 1, 'NATION', 'mz_miaoz', '苗族', b'1', 'warning', '', 4, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (48, 1, 'NATION', 'mz_wwez', '维吾尔族', b'1', 'warning', '', 5, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (49, 1, 'NATION', 'mz_tjz', '土家族', b'1', 'warning', '', 6, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (50, 1, 'NATION', 'mz_yz', '彝族', b'1', 'warning', '', 7, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (51, 1, 'NATION', 'mz_mgz', '蒙古族', b'1', 'warning', '', 8, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (52, 1, 'NATION', 'mz_zhangz', '藏族', b'1', 'warning', '', 9, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (53, 1, 'NATION', 'mz_byz', '布依族', b'1', 'warning', '', 10, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (54, 1, 'NATION', 'mz_dz', '侗族', b'1', 'warning', '', 11, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (55, 1, 'NATION', 'mz_yaoz', '瑶族', b'1', 'warning', '', 12, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (56, 1, 'NATION', 'mz_cxz', '朝鲜族', b'1', 'warning', '', 13, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (57, 1, 'NATION', 'mz_bz', '白族', b'1', 'warning', '', 14, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (58, 1, 'NATION', 'mz_hnz', '哈尼族', b'1', 'warning', '', 15, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (59, 1, 'NATION', 'mz_hskz', '哈萨克族', b'1', 'warning', '', 16, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (60, 1, 'NATION', 'mz_lz', '黎族', b'1', 'warning', '', 17, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (61, 1, 'NATION', 'mz_daiz', '傣族', b'1', 'warning', '', 18, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (62, 1, 'NATION', 'mz_sz', '畲族', b'1', 'warning', '', 19, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (63, 1, 'NATION', 'mz_llz', '傈僳族', b'1', 'warning', '', 20, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (64, 1, 'NATION', 'mz_glz', '仡佬族', b'1', 'warning', '', 21, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (65, 1, 'NATION', 'mz_dxz', '东乡族', b'1', 'warning', '', 22, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (66, 1, 'NATION', 'mz_gsz', '高山族', b'1', 'warning', '', 23, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (67, 1, 'NATION', 'mz_lhz', '拉祜族', b'1', 'warning', '', 24, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (68, 1, 'NATION', 'mz_shuiz', '水族', b'1', 'warning', '', 25, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (69, 1, 'NATION', 'mz_wz', '佤族', b'1', 'warning', '', 26, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (70, 1, 'NATION', 'mz_nxz', '纳西族', b'1', 'warning', '', 27, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (71, 1, 'NATION', 'mz_qz', '羌族', b'1', 'warning', '', 28, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (72, 1, 'NATION', 'mz_tz', '土族', b'1', 'warning', '', 29, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (73, 1, 'NATION', 'mz_zlz', '仫佬族', b'1', 'warning', '', 30, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (74, 1, 'NATION', 'mz_xbz', '锡伯族', b'1', 'warning', '', 31, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (75, 1, 'NATION', 'mz_kehzz', '柯尔克孜族', b'1', 'warning', '', 32, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (76, 1, 'NATION', 'mz_dwz', '达斡尔族', b'1', 'warning', '', 33, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (77, 1, 'NATION', 'mz_jpz', '景颇族', b'1', 'warning', '', 34, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (78, 1, 'NATION', 'mz_mlz', '毛南族', b'1', 'warning', '', 35, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (79, 1, 'NATION', 'mz_slz', '撒拉族', b'1', 'warning', '', 36, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (80, 1, 'NATION', 'mz_tjkz', '塔吉克族', b'1', 'warning', '', 37, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (81, 1, 'NATION', 'mz_acz', '阿昌族', b'1', 'warning', '', 38, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (82, 1, 'NATION', 'mz_pmz', '普米族', b'1', 'warning', '', 39, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (83, 1, 'NATION', 'mz_ewkz', '鄂温克族', b'1', 'warning', '', 40, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (84, 1, 'NATION', 'mz_nz', '怒族', b'1', 'warning', '', 41, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (85, 1, 'NATION', 'mz_jz', '京族', b'1', 'warning', '', 42, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (86, 1, 'NATION', 'mz_jnz', '基诺族', b'1', 'warning', '', 43, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (87, 1, 'NATION', 'mz_daz', '德昂族', b'1', 'warning', '', 44, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (88, 1, 'NATION', 'mz_baz', '保安族', b'1', 'warning', '', 45, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (89, 1, 'NATION', 'mz_elsz', '俄罗斯族', b'1', 'warning', '', 46, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (90, 1, 'NATION', 'mz_ygz', '裕固族', b'1', 'warning', '', 47, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (91, 1, 'NATION', 'mz_wzbkz', '乌兹别克族', b'1', 'warning', '', 48, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (92, 1, 'NATION', 'mz_mbz', '门巴族', b'1', 'warning', '', 49, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (93, 1, 'NATION', 'mz_elcz', '鄂伦春族', b'1', 'warning', '', 50, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (94, 1, 'NATION', 'mz_dlz', '独龙族', b'1', 'warning', '', 51, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (95, 1, 'NATION', 'mz_tkez', '塔塔尔族', b'1', 'warning', '', 52, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (96, 1, 'NATION', 'mz_hzz', '赫哲族', b'1', 'warning', '', 53, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (97, 1, 'NATION', 'mz_lbz', '珞巴族', b'1', 'warning', '', 54, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (98, 1, 'NATION', 'mz_blz', '布朗族', b'1', 'warning', '', 55, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (99, 2, 'POSITION_STATUS', 'WORKING', '在职', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2019-06-04 11:38:16', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (100, 2, 'POSITION_STATUS', 'QUIT', '离职', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2019-06-04 11:38:50', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (101, 4, 'AREA_LEVEL', 'TOWNS', '乡镇', b'1', 'warning', '', 5, b'0', 0, '系统管理员', '2020-03-09 23:33:46', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (102, 3, 'EDUCATION', 'XIAOXUE', '小学', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-03-09 23:34:13', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (103, 3, 'EDUCATION', 'ZHONGXUE', '中学', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2020-03-09 23:34:32', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (104, 3, 'EDUCATION', 'GAOZHONG', '高中', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2020-03-09 23:34:40', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (105, 3, 'EDUCATION', 'QITA', '其他', b'1', 'warning', '', 20, b'0', 0, '系统管理员', '2020-03-09 23:34:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (106, 1, 'NATION', 'mz_qt', '其他', b'1', 'warning', '', 100, b'0', 0, '系统管理员', '2020-03-09 23:38:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (107, 2, 'POSITION_STATUS', 'LEAVE', '请假', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2020-03-09 23:39:30', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (108, 6, 'SEX', '1', '男', b'1', 'success', '男', 1, b'0', 0, '系统管理员', '2020-11-18 07:24:58', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (109, 6, 'SEX', '2', '女', b'1', 'error', '女', 1, b'0', 0, '系统管理员', '2020-11-18 07:25:07', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (110, 7, 'NOTICE', '0', '通知', b'1', 'success', '通知', 1, b'0', 0, '系统管理员', '2020-11-19 02:58:37', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (111, 7, 'NOTICE', '1', '消息', b'1', 'success', '消息', 1, b'0', 0, '系统管理员', '2020-11-19 02:58:55', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (112, 7, 'NOTICE', '2', '待办', b'1', 'error', '待办', 1, b'0', 0, '系统管理员', '2020-11-19 02:59:05', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (113, 8, 'STATION_TYPE', '0', '基层', b'1', 'success', '1111', 1, b'0', 0, '系统管理员', '2021-07-16 04:06:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (114, 9, 'COLOR', 'success', '成功', b'1', 'success', '成功', 1, b'0', 0, '系统管理员', '2021-07-16 04:12:47', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (115, 9, 'COLOR', 'warning', '警告', b'1', 'warning', '警告', 1, b'0', 0, '系统管理员', '2021-07-16 04:15:06', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (116, 9, 'COLOR', 'error', '错误', b'1', 'error', '红色', 1, b'0', 0, '系统管理员', '2021-07-16 04:16:55', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (118, 8, 'STATION_TYPE', '1', '中层', b'1', 'success', '中层', 1, b'0', 0, '系统管理员', '2021-07-16 04:59:51', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (119, 8, 'STATION_TYPE', '2', '高层', b'1', 'warning', '高层', 1, b'0', 0, '系统管理员', '2021-07-16 05:00:07', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (120, 10, 'INDUSTRY', '1', '医疗', b'1', 'success', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:08', NULL, NULL, '2021-07-31 08:20:13');
INSERT INTO `common_dictionary_item` VALUES (121, 10, 'INDUSTRY', '2', '教育', b'1', 'success', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:17', NULL, NULL, '2021-07-31 08:20:14');
INSERT INTO `common_dictionary_item` VALUES (122, 10, 'INDUSTRY', '3', '金融', b'1', 'success', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:28', NULL, NULL, '2021-07-31 08:20:17');
INSERT INTO `common_dictionary_item` VALUES (123, 10, 'INDUSTRY', '4', '互联网', b'1', 'warning', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:40', 1, '长风一梦', '2021-07-31 08:20:17');
INSERT INTO `common_dictionary_item` VALUES (124, 10, 'INDUSTRY', '5', '电商', b'1', 'warning', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:58', 1, '长风一梦', '2021-07-31 08:20:18');
COMMIT;

-- ----------------------------
-- Table structure for common_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `common_dictionary`;
CREATE TABLE `common_dictionary` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编码\r\n一颗树仅仅有一个统一的编码',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `description` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置角色',
  `sequence` tinyint DEFAULT NULL COMMENT '排序',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典类型';

-- ----------------------------
-- Records of common_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `common_dictionary` VALUES (1, 'NATION', '民族', '123', b'1', b'1', 0, b'0', 0, '系统管理员', '2019-06-01 09:42:50', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (2, 'POSITION_STATUS', '在职状态', '11', b'1', b'1', 1, b'0', 0, '系统管理员', '2019-06-04 11:37:15', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (3, 'EDUCATION', '学历', '', b'1', b'1', 2, b'0', 0, '系统管理员', '2019-06-04 11:33:52', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (4, 'AREA_LEVEL', '行政区级', '', b'1', b'1', 3, b'0', 0, '系统管理员', '2020-01-20 15:12:05', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (5, 'ORG_TYPE', '机构类型', '', b'1', b'1', 4, b'0', 0, '系统管理员', '2020-08-19 15:02:57', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (6, 'SEX', '性别', '性别', b'1', b'1', 5, b'0', 0, '系统管理员', '2020-11-18 07:24:43', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (7, 'NOTICE', '消息类型', '消息类型', b'1', b'1', 6, b'0', 0, '系统管理员', '2020-11-19 02:57:22', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (8, 'STATION_TYPE', '岗位类型', NULL, b'1', b'1', 7, b'0', 0, '系统管理员', '2021-07-16 03:34:04', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (9, 'COLOR', '颜色', NULL, b'1', b'1', 8, b'0', 0, '系统管理员', '2021-07-16 04:12:21', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (10, 'INDUSTRY', '行业类型', '行业类型', b'1', b'1', 9, b'0', 0, '系统管理员', '2021-07-31 08:10:07', 0, '系统管理员', '2021-07-31 08:20:24');
INSERT INTO `common_dictionary` VALUES (11, '1231', '123', '23', b'1', b'0', 0, b'1', 1, '长风一梦8888', '2023-04-17 01:41:52', NULL, NULL, '2023-04-17 01:41:59');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
