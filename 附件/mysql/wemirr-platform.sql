/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : wemirr-platform

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 22/01/2021 16:35:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for common_area
-- ----------------------------
DROP TABLE IF EXISTS `common_area`;
CREATE TABLE `common_area` (
  `id` int(8) DEFAULT NULL COMMENT 'ID',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `parent_id` int(8) DEFAULT NULL COMMENT '父ID',
  `level` tinyint(2) DEFAULT NULL COMMENT '级别',
  `longitude` decimal(10,3) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,3) DEFAULT NULL COMMENT '纬度',
  `source` varchar(255) DEFAULT NULL COMMENT '数据 来源',
  `sequence` varchar(255) DEFAULT '0' COMMENT '排序',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省市区详情表';

-- ----------------------------
-- Table structure for common_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `common_dictionary`;
CREATE TABLE `common_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(64) NOT NULL COMMENT '编码\r\n一颗树仅仅有一个统一的编码',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型';

-- ----------------------------
-- Records of common_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `common_dictionary` VALUES (1, 'NATION', '民族', '123', b'1', 1, '1', '2019-06-01 09:42:50', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary` VALUES (2, 'POSITION_STATUS', '在职状态', '11', b'1', 1, '1', '2019-06-04 11:37:15', 2, NULL, '2020-10-21 08:58:04');
INSERT INTO `common_dictionary` VALUES (3, 'EDUCATION', '学历', '', b'1', 1, '1', '2019-06-04 11:33:52', 1, '1', '2019-06-04 11:33:52');
INSERT INTO `common_dictionary` VALUES (4, 'AREA_LEVEL', '行政区级', '', b'1', 1, '1', '2020-01-20 15:12:05', 1, '1', '2020-01-20 15:12:05');
INSERT INTO `common_dictionary` VALUES (5, 'ORG_TYPE', '机构类型', '', b'1', 1, '1', '2020-08-19 15:02:57', 1, '1', '2020-08-19 15:03:00');
INSERT INTO `common_dictionary` VALUES (6, 'SEX', '性别', '性别', b'1', 2, '不告诉你', '2020-11-18 07:24:43', NULL, NULL, NULL);
INSERT INTO `common_dictionary` VALUES (7, 'NOTICE', '消息类型', '消息类型', b'1', 2, '不告诉你', '2020-11-19 02:57:22', 2, '不告诉你', '2020-11-19 04:24:17');
COMMIT;

-- ----------------------------
-- Table structure for common_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `common_dictionary_item`;
CREATE TABLE `common_dictionary_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dictionary_id` bigint(20) NOT NULL COMMENT '类型ID',
  `dictionary_code` varchar(64) NOT NULL COMMENT '类型',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `sort_value` int(11) DEFAULT '1' COMMENT '排序',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dict_code_item_code_uniq` (`dictionary_code`,`code`) USING BTREE COMMENT '字典编码与字典项目编码联合唯一'
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COMMENT='字典项';

-- ----------------------------
-- Records of common_dictionary_item
-- ----------------------------
BEGIN;
INSERT INTO `common_dictionary_item` VALUES (1, 4, 'AREA_LEVEL', 'COUNTRY', '国家', b'1', '', 1, 3, NULL, '2020-01-20 15:12:57', 3, NULL, '2020-01-20 15:12:57');
INSERT INTO `common_dictionary_item` VALUES (2, 4, 'AREA_LEVEL', 'PROVINCE', '省份/直辖市', b'1', '', 2, 3, NULL, '2020-01-20 15:13:45', 3, NULL, '2020-01-20 15:13:45');
INSERT INTO `common_dictionary_item` VALUES (3, 4, 'AREA_LEVEL', 'CITY', '地市', b'1', '', 3, 3, NULL, '2020-01-20 15:14:16', 3, NULL, '2020-01-20 15:14:16');
INSERT INTO `common_dictionary_item` VALUES (4, 4, 'AREA_LEVEL', 'COUNTY', '区县', b'1', '', 4, 3, NULL, '2020-01-20 15:14:54', 3, NULL, '2020-01-20 15:14:54');
INSERT INTO `common_dictionary_item` VALUES (5, 5, 'ORG_TYPE', '01', '单位', b'1', '', 1, 1, NULL, '2020-08-19 15:03:40', 1, NULL, '2020-08-19 15:03:45');
INSERT INTO `common_dictionary_item` VALUES (6, 5, 'ORG_TYPE', '02', '部门', b'1', '', 1, 1, NULL, '2020-08-19 15:03:59', 1, NULL, '2020-08-19 15:04:04');
INSERT INTO `common_dictionary_item` VALUES (38, 3, 'EDUCATION', 'ZHUANKE', '专科', b'1', '', 4, 1, NULL, '2019-06-04 11:36:29', 1, NULL, '2019-06-04 11:36:29');
INSERT INTO `common_dictionary_item` VALUES (39, 3, 'EDUCATION', 'COLLEGE', '本科', b'1', '', 5, 1, NULL, '2019-06-04 11:36:19', 1, NULL, '2019-06-04 11:36:19');
INSERT INTO `common_dictionary_item` VALUES (40, 3, 'EDUCATION', 'SUOSHI', '硕士', b'1', '', 6, 1, NULL, '2019-06-04 11:36:29', 1, NULL, '2019-06-04 11:36:29');
INSERT INTO `common_dictionary_item` VALUES (41, 3, 'EDUCATION', 'BOSHI', '博士', b'1', '', 7, 1, NULL, '2019-06-04 11:36:29', 1, NULL, '2019-06-04 11:36:29');
INSERT INTO `common_dictionary_item` VALUES (42, 3, 'EDUCATION', 'BOSHIHOU', '博士后', b'1', '', 8, 1, NULL, '2019-06-04 11:36:29', 1, NULL, '2019-06-04 11:36:29');
INSERT INTO `common_dictionary_item` VALUES (43, 1, 'NATION', 'mz_hanz', '汉族', b'1', '', 0, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (44, 1, 'NATION', 'mz_zz', '壮族', b'1', '', 1, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (45, 1, 'NATION', 'mz_mz', '满族', b'1', '', 2, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (46, 1, 'NATION', 'mz_hz', '回族', b'1', '', 3, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (47, 1, 'NATION', 'mz_miaoz', '苗族', b'1', '', 4, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (48, 1, 'NATION', 'mz_wwez', '维吾尔族', b'1', '', 5, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (49, 1, 'NATION', 'mz_tjz', '土家族', b'1', '', 6, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (50, 1, 'NATION', 'mz_yz', '彝族', b'1', '', 7, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (51, 1, 'NATION', 'mz_mgz', '蒙古族', b'1', '', 8, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (52, 1, 'NATION', 'mz_zhangz', '藏族', b'1', '', 9, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (53, 1, 'NATION', 'mz_byz', '布依族', b'1', '', 10, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (54, 1, 'NATION', 'mz_dz', '侗族', b'1', '', 11, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (55, 1, 'NATION', 'mz_yaoz', '瑶族', b'1', '', 12, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (56, 1, 'NATION', 'mz_cxz', '朝鲜族', b'1', '', 13, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (57, 1, 'NATION', 'mz_bz', '白族', b'1', '', 14, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (58, 1, 'NATION', 'mz_hnz', '哈尼族', b'1', '', 15, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (59, 1, 'NATION', 'mz_hskz', '哈萨克族', b'1', '', 16, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (60, 1, 'NATION', 'mz_lz', '黎族', b'1', '', 17, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (61, 1, 'NATION', 'mz_daiz', '傣族', b'1', '', 18, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (62, 1, 'NATION', 'mz_sz', '畲族', b'1', '', 19, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (63, 1, 'NATION', 'mz_llz', '傈僳族', b'1', '', 20, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (64, 1, 'NATION', 'mz_glz', '仡佬族', b'1', '', 21, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (65, 1, 'NATION', 'mz_dxz', '东乡族', b'1', '', 22, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (66, 1, 'NATION', 'mz_gsz', '高山族', b'1', '', 23, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (67, 1, 'NATION', 'mz_lhz', '拉祜族', b'1', '', 24, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (68, 1, 'NATION', 'mz_shuiz', '水族', b'1', '', 25, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (69, 1, 'NATION', 'mz_wz', '佤族', b'1', '', 26, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (70, 1, 'NATION', 'mz_nxz', '纳西族', b'1', '', 27, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (71, 1, 'NATION', 'mz_qz', '羌族', b'1', '', 28, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (72, 1, 'NATION', 'mz_tz', '土族', b'1', '', 29, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (73, 1, 'NATION', 'mz_zlz', '仫佬族', b'1', '', 30, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (74, 1, 'NATION', 'mz_xbz', '锡伯族', b'1', '', 31, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (75, 1, 'NATION', 'mz_kehzz', '柯尔克孜族', b'1', '', 32, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (76, 1, 'NATION', 'mz_dwz', '达斡尔族', b'1', '', 33, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (77, 1, 'NATION', 'mz_jpz', '景颇族', b'1', '', 34, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (78, 1, 'NATION', 'mz_mlz', '毛南族', b'1', '', 35, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (79, 1, 'NATION', 'mz_slz', '撒拉族', b'1', '', 36, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (80, 1, 'NATION', 'mz_tjkz', '塔吉克族', b'1', '', 37, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (81, 1, 'NATION', 'mz_acz', '阿昌族', b'1', '', 38, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (82, 1, 'NATION', 'mz_pmz', '普米族', b'1', '', 39, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (83, 1, 'NATION', 'mz_ewkz', '鄂温克族', b'1', '', 40, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (84, 1, 'NATION', 'mz_nz', '怒族', b'1', '', 41, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (85, 1, 'NATION', 'mz_jz', '京族', b'1', '', 42, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (86, 1, 'NATION', 'mz_jnz', '基诺族', b'1', '', 43, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (87, 1, 'NATION', 'mz_daz', '德昂族', b'1', '', 44, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (88, 1, 'NATION', 'mz_baz', '保安族', b'1', '', 45, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (89, 1, 'NATION', 'mz_elsz', '俄罗斯族', b'1', '', 46, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (90, 1, 'NATION', 'mz_ygz', '裕固族', b'1', '', 47, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (91, 1, 'NATION', 'mz_wzbkz', '乌兹别克族', b'1', '', 48, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (92, 1, 'NATION', 'mz_mbz', '门巴族', b'1', '', 49, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (93, 1, 'NATION', 'mz_elcz', '鄂伦春族', b'1', '', 50, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (94, 1, 'NATION', 'mz_dlz', '独龙族', b'1', '', 51, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (95, 1, 'NATION', 'mz_tkez', '塔塔尔族', b'1', '', 52, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (96, 1, 'NATION', 'mz_hzz', '赫哲族', b'1', '', 53, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (97, 1, 'NATION', 'mz_lbz', '珞巴族', b'1', '', 54, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (98, 1, 'NATION', 'mz_blz', '布朗族', b'1', '', 55, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (99, 2, 'POSITION_STATUS', 'WORKING', '在职', b'1', '', 1, 1, NULL, '2019-06-04 11:38:16', 2, NULL, '2020-10-21 07:40:05');
INSERT INTO `common_dictionary_item` VALUES (100, 2, 'POSITION_STATUS', 'QUIT', '离职', b'1', '', 2, 1, NULL, '2019-06-04 11:38:50', 2, NULL, '2020-10-21 07:40:05');
INSERT INTO `common_dictionary_item` VALUES (101, 4, 'AREA_LEVEL', 'TOWNS', '乡镇', b'1', '', 5, 3, NULL, '2020-03-09 23:33:46', 3, NULL, '2020-03-09 23:33:46');
INSERT INTO `common_dictionary_item` VALUES (102, 3, 'EDUCATION', 'XIAOXUE', '小学', b'1', '', 1, 3, NULL, '2020-03-09 23:34:13', 3, NULL, '2020-03-09 23:34:13');
INSERT INTO `common_dictionary_item` VALUES (103, 3, 'EDUCATION', 'ZHONGXUE', '中学', b'1', '', 2, 3, NULL, '2020-03-09 23:34:32', 3, NULL, '2020-03-09 23:34:32');
INSERT INTO `common_dictionary_item` VALUES (104, 3, 'EDUCATION', 'GAOZHONG', '高中', b'1', '', 3, 3, NULL, '2020-03-09 23:34:40', 3, NULL, '2020-03-09 23:34:40');
INSERT INTO `common_dictionary_item` VALUES (105, 3, 'EDUCATION', 'QITA', '其他', b'1', '', 20, 3, NULL, '2020-03-09 23:34:54', 3, NULL, '2020-03-09 23:34:54');
INSERT INTO `common_dictionary_item` VALUES (106, 1, 'NATION', 'mz_qt', '其他', b'1', '', 100, 3, NULL, '2020-03-09 23:38:29', 2, NULL, '2020-10-21 08:39:45');
INSERT INTO `common_dictionary_item` VALUES (107, 2, 'POSITION_STATUS', 'LEAVE', '请假', b'1', '', 3, 3, NULL, '2020-03-09 23:39:30', 2, NULL, '2020-10-21 07:40:05');
INSERT INTO `common_dictionary_item` VALUES (108, 6, 'SEX', 'MALE', '男', b'1', '男', 1, 2, '不告诉你', '2020-11-18 07:24:58', 2, '不告诉你', '2020-11-18 07:25:31');
INSERT INTO `common_dictionary_item` VALUES (109, 6, 'SEX', 'FEMALE', '女', b'1', '女', 1, 2, '不告诉你', '2020-11-18 07:25:07', 2, '不告诉你', '2020-11-18 07:25:42');
INSERT INTO `common_dictionary_item` VALUES (110, 7, 'NOTICE', 'success', '通知', b'1', '通知', 1, 2, '不告诉你', '2020-11-19 02:58:37', 2, '不告诉你', '2020-11-19 04:24:29');
INSERT INTO `common_dictionary_item` VALUES (111, 7, 'NOTICE', 'info', '消息', b'1', '消息', 1, 2, '不告诉你', '2020-11-19 02:58:55', 2, '不告诉你', '2020-11-19 09:13:38');
INSERT INTO `common_dictionary_item` VALUES (112, 7, 'NOTICE', 'danger', '告警', b'1', '预警', 1, 2, '不告诉你', '2020-11-19 02:59:05', 2, '不告诉你', '2020-11-19 09:13:50');
COMMIT;

-- ----------------------------
-- Table structure for common_generate
-- ----------------------------
DROP TABLE IF EXISTS `common_generate`;
CREATE TABLE `common_generate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `root_dir` varchar(255) DEFAULT NULL COMMENT '根目录',
  `swagger2` bit(2) DEFAULT b'0' COMMENT '是否添加swagger2',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `table_name` varchar(255) DEFAULT NULL COMMENT '表名',
  `parent_package` varchar(255) DEFAULT NULL COMMENT '父包',
  `module_name` varchar(255) DEFAULT NULL COMMENT '模块名',
  `table_prefix` varchar(255) DEFAULT NULL COMMENT '表前缀',
  `api_url_prefix` varchar(255) DEFAULT NULL COMMENT 'api地址前缀',
  `logic_delete_field` varchar(255) DEFAULT NULL COMMENT '逻辑删除字段',
  `super_controller_class` varchar(255) DEFAULT NULL COMMENT '控制器包名',
  `platform_id` bigint(20) DEFAULT '0' COMMENT '平台ID',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_name` varchar(255) DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='代码生成器';

-- ----------------------------
-- Records of common_generate
-- ----------------------------
BEGIN;
INSERT INTO `common_generate` VALUES (6, NULL, b'00', 'Levin', 't_user', 'com.wemirr.platform', 'authority', 't_', '', 'locked', 'com.wemirr.platform.authority.controller.UserController', 1, 0, NULL, '2020-11-06 09:34:40', 2, '不告诉你', '2020-11-09 03:32:53');
INSERT INTO `common_generate` VALUES (31, NULL, b'00', 'Levin', 't_user', 'com.wemirr.platform.authority', 'User', 't_', '', 'locked', 'com.wemirr.platform.authority.controller.UserController', 1, 2, '不告诉你', '2020-11-06 09:34:40', 2, '不告诉你', '2020-11-09 03:31:46');
COMMIT;

-- ----------------------------
-- Table structure for common_login_log
-- ----------------------------
DROP TABLE IF EXISTS `common_login_log`;
CREATE TABLE `common_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '登录人ID',
  `ip` varchar(50) DEFAULT NULL COMMENT '登录IP',
  `client_id` varchar(50) DEFAULT NULL COMMENT '登录人客户端ID',
  `name` varchar(50) DEFAULT NULL COMMENT '登录人姓名',
  `principal` varchar(30) DEFAULT NULL COMMENT '登录人账号',
  `platform` varchar(255) DEFAULT NULL COMMENT '平台',
  `engine` varchar(255) DEFAULT NULL COMMENT '引擎类型',
  `engine_version` varchar(255) DEFAULT NULL COMMENT '引擎版本',
  `browser` varchar(255) DEFAULT NULL COMMENT '浏览器名称',
  `browser_version` varchar(255) DEFAULT NULL COMMENT '浏览器版本',
  `os` varchar(255) DEFAULT NULL COMMENT '操作系统',
  `location` varchar(50) DEFAULT NULL COMMENT '登录地点',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登录日志';

-- ----------------------------
-- Records of common_login_log
-- ----------------------------
BEGIN;
INSERT INTO `common_login_log` VALUES (1, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.80', 'OSX', '内网', '2020-10-22 02:48:43');
INSERT INTO `common_login_log` VALUES (2, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.80', 'OSX', '湖南', '2020-10-23 07:36:29');
INSERT INTO `common_login_log` VALUES (3, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-26 02:02:31');
INSERT INTO `common_login_log` VALUES (4, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-27 02:54:51');
INSERT INTO `common_login_log` VALUES (5, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-28 03:14:41');
INSERT INTO `common_login_log` VALUES (6, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-28 03:15:06');
INSERT INTO `common_login_log` VALUES (7, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-28 03:15:55');
INSERT INTO `common_login_log` VALUES (8, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-28 03:16:33');
INSERT INTO `common_login_log` VALUES (9, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-28 03:16:41');
INSERT INTO `common_login_log` VALUES (10, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-28 03:18:04');
INSERT INTO `common_login_log` VALUES (11, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-28 03:18:21');
INSERT INTO `common_login_log` VALUES (12, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-28 08:37:04');
INSERT INTO `common_login_log` VALUES (13, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-29 03:16:45');
INSERT INTO `common_login_log` VALUES (14, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 03:31:34');
INSERT INTO `common_login_log` VALUES (15, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '未知', '2020-10-30 04:59:05');
INSERT INTO `common_login_log` VALUES (16, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '未知', '2020-10-30 04:59:06');
INSERT INTO `common_login_log` VALUES (17, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '未知', '2020-10-30 04:59:56');
INSERT INTO `common_login_log` VALUES (18, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '未知', '2020-10-30 05:01:30');
INSERT INTO `common_login_log` VALUES (19, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:06:05');
INSERT INTO `common_login_log` VALUES (20, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:07:14');
INSERT INTO `common_login_log` VALUES (21, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:08:15');
INSERT INTO `common_login_log` VALUES (22, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:09:09');
INSERT INTO `common_login_log` VALUES (23, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:09:16');
INSERT INTO `common_login_log` VALUES (24, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:09:30');
INSERT INTO `common_login_log` VALUES (25, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:09:43');
INSERT INTO `common_login_log` VALUES (26, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:10:24');
INSERT INTO `common_login_log` VALUES (27, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:11:00');
INSERT INTO `common_login_log` VALUES (28, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:12:18');
INSERT INTO `common_login_log` VALUES (29, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:13:11');
INSERT INTO `common_login_log` VALUES (30, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:15:57');
INSERT INTO `common_login_log` VALUES (31, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:17:58');
INSERT INTO `common_login_log` VALUES (32, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:19:37');
INSERT INTO `common_login_log` VALUES (33, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:20:42');
INSERT INTO `common_login_log` VALUES (34, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:20:57');
INSERT INTO `common_login_log` VALUES (35, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:22:49');
INSERT INTO `common_login_log` VALUES (36, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:22:59');
INSERT INTO `common_login_log` VALUES (37, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:23:09');
INSERT INTO `common_login_log` VALUES (38, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-10-30 05:23:31');
INSERT INTO `common_login_log` VALUES (39, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-02 06:09:38');
INSERT INTO `common_login_log` VALUES (40, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-02 07:17:32');
INSERT INTO `common_login_log` VALUES (41, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-03 03:05:41');
INSERT INTO `common_login_log` VALUES (42, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-03 09:21:24');
INSERT INTO `common_login_log` VALUES (43, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-04 03:11:16');
INSERT INTO `common_login_log` VALUES (44, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-05 01:28:58');
INSERT INTO `common_login_log` VALUES (45, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-05 01:39:26');
INSERT INTO `common_login_log` VALUES (46, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-05 08:06:16');
INSERT INTO `common_login_log` VALUES (47, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-06 02:10:34');
INSERT INTO `common_login_log` VALUES (48, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-06 02:19:36');
INSERT INTO `common_login_log` VALUES (49, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-06 02:20:41');
INSERT INTO `common_login_log` VALUES (50, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.111', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-06 02:21:05');
INSERT INTO `common_login_log` VALUES (51, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-06 06:22:30');
INSERT INTO `common_login_log` VALUES (52, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-06 06:23:44');
INSERT INTO `common_login_log` VALUES (53, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-06 09:14:55');
INSERT INTO `common_login_log` VALUES (54, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-06 09:28:30');
INSERT INTO `common_login_log` VALUES (55, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-06 09:29:25');
INSERT INTO `common_login_log` VALUES (56, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-09 03:01:00');
INSERT INTO `common_login_log` VALUES (57, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-09 03:30:03');
INSERT INTO `common_login_log` VALUES (58, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-09 06:49:07');
INSERT INTO `common_login_log` VALUES (59, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-09 07:05:19');
INSERT INTO `common_login_log` VALUES (60, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-09 08:22:59');
INSERT INTO `common_login_log` VALUES (61, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-09 08:54:00');
INSERT INTO `common_login_log` VALUES (62, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-09 09:43:49');
INSERT INTO `common_login_log` VALUES (63, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-09 10:32:40');
INSERT INTO `common_login_log` VALUES (64, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-10 03:00:25');
INSERT INTO `common_login_log` VALUES (65, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-10 03:20:51');
INSERT INTO `common_login_log` VALUES (66, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-10 07:21:35');
INSERT INTO `common_login_log` VALUES (67, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-11 02:11:58');
INSERT INTO `common_login_log` VALUES (68, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-11 03:19:20');
INSERT INTO `common_login_log` VALUES (69, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-11 06:10:08');
INSERT INTO `common_login_log` VALUES (70, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-12 01:24:58');
INSERT INTO `common_login_log` VALUES (71, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-12 04:49:57');
INSERT INTO `common_login_log` VALUES (72, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-12 11:28:10');
INSERT INTO `common_login_log` VALUES (73, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-13 02:41:52');
INSERT INTO `common_login_log` VALUES (74, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-14 03:29:00');
INSERT INTO `common_login_log` VALUES (75, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.183', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-14 05:04:06');
INSERT INTO `common_login_log` VALUES (76, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-16 02:41:13');
INSERT INTO `common_login_log` VALUES (77, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-16 09:46:50');
INSERT INTO `common_login_log` VALUES (78, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-17 02:40:56');
INSERT INTO `common_login_log` VALUES (79, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-18 02:50:12');
INSERT INTO `common_login_log` VALUES (80, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-19 02:43:29');
INSERT INTO `common_login_log` VALUES (81, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-19 06:36:12');
INSERT INTO `common_login_log` VALUES (82, 1, '127.0.0.1', NULL, '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-19 06:36:26');
INSERT INTO `common_login_log` VALUES (83, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-20 01:52:48');
INSERT INTO `common_login_log` VALUES (84, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-20 05:52:20');
INSERT INTO `common_login_log` VALUES (85, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-23 02:38:36');
INSERT INTO `common_login_log` VALUES (86, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-24 02:30:54');
INSERT INTO `common_login_log` VALUES (87, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-24 02:39:00');
INSERT INTO `common_login_log` VALUES (88, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-25 01:49:12');
INSERT INTO `common_login_log` VALUES (89, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '605.1.15', 'Safari', '14.0.1', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-25 06:07:19');
INSERT INTO `common_login_log` VALUES (90, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 01:28:38');
INSERT INTO `common_login_log` VALUES (91, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 08:37:50');
INSERT INTO `common_login_log` VALUES (92, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 10:55:09');
INSERT INTO `common_login_log` VALUES (93, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '605.1.15', 'Safari', '14.0.1', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 10:55:56');
INSERT INTO `common_login_log` VALUES (94, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '605.1.15', 'Safari', '14.0.1', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 10:56:45');
INSERT INTO `common_login_log` VALUES (95, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 10:59:26');
INSERT INTO `common_login_log` VALUES (96, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 11:01:57');
INSERT INTO `common_login_log` VALUES (97, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 12:34:35');
INSERT INTO `common_login_log` VALUES (98, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:07:12');
INSERT INTO `common_login_log` VALUES (99, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:07:16');
INSERT INTO `common_login_log` VALUES (100, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:07:54');
INSERT INTO `common_login_log` VALUES (101, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:08:01');
INSERT INTO `common_login_log` VALUES (102, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:08:33');
INSERT INTO `common_login_log` VALUES (103, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:16:40');
INSERT INTO `common_login_log` VALUES (104, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:16:44');
INSERT INTO `common_login_log` VALUES (105, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:20:24');
INSERT INTO `common_login_log` VALUES (106, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:20:46');
INSERT INTO `common_login_log` VALUES (107, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:21:01');
INSERT INTO `common_login_log` VALUES (108, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:21:11');
INSERT INTO `common_login_log` VALUES (109, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:21:59');
INSERT INTO `common_login_log` VALUES (110, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:45:30');
INSERT INTO `common_login_log` VALUES (111, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:45:36');
INSERT INTO `common_login_log` VALUES (112, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:46:17');
INSERT INTO `common_login_log` VALUES (113, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:51:08');
INSERT INTO `common_login_log` VALUES (114, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-26 13:52:10');
INSERT INTO `common_login_log` VALUES (115, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-27 05:12:36');
INSERT INTO `common_login_log` VALUES (116, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-27 05:47:00');
INSERT INTO `common_login_log` VALUES (117, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-27 09:43:02');
INSERT INTO `common_login_log` VALUES (118, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-30 03:21:47');
INSERT INTO `common_login_log` VALUES (119, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-30 06:15:47');
INSERT INTO `common_login_log` VALUES (120, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-30 06:17:39');
INSERT INTO `common_login_log` VALUES (121, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-30 06:17:53');
INSERT INTO `common_login_log` VALUES (122, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-11-30 11:34:28');
INSERT INTO `common_login_log` VALUES (123, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-12-01 10:02:29');
INSERT INTO `common_login_log` VALUES (124, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-12-02 02:08:12');
INSERT INTO `common_login_log` VALUES (125, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-12-02 03:01:43');
INSERT INTO `common_login_log` VALUES (126, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-12-03 06:13:21');
INSERT INTO `common_login_log` VALUES (127, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.67', 'OSX', '0|0|0|内网IP|内网IP', '2020-12-04 06:59:10');
INSERT INTO `common_login_log` VALUES (128, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.88', 'OSX', '0|0|0|内网IP|内网IP', '2020-12-07 08:10:15');
INSERT INTO `common_login_log` VALUES (129, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.88', 'OSX', '0|0|0|内网IP|内网IP', '2020-12-08 08:03:28');
INSERT INTO `common_login_log` VALUES (130, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.88', 'OSX', '0|0|0|内网IP|内网IP', '2020-12-09 08:44:33');
INSERT INTO `common_login_log` VALUES (131, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.88', 'OSX', '0|0|0|内网IP|内网IP', '2020-12-10 08:44:14');
INSERT INTO `common_login_log` VALUES (132, 1, '0:0:0:0:0:0:0:1', 'client', '测试登录人', 'memmsc1', 'Mac', 'Webkit', '537.36', 'Chrome', '87.0.4280.141', 'OSX', '0|0|0|内网IP|内网IP', '2021-01-20 06:53:03');
COMMIT;

-- ----------------------------
-- Table structure for common_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `common_opt_log`;
CREATE TABLE `common_opt_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  `location` varchar(255) DEFAULT NULL,
  `trace` varchar(255) DEFAULT NULL COMMENT '日志链路追踪id日志标志',
  `type` varchar(5) DEFAULT 'OPT' COMMENT '日志类型\n#LogType{OPT:操作类型;EX:异常类型}',
  `description` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `class_path` varchar(255) DEFAULT NULL COMMENT '类路径',
  `action_method` varchar(50) DEFAULT NULL COMMENT '请求方法',
  `request_uri` varchar(50) DEFAULT NULL COMMENT '请求地址',
  `http_method` varchar(10) DEFAULT 'GET' COMMENT '请求类型\n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
  `params` longtext COMMENT '请求参数',
  `result` longtext COMMENT '返回值',
  `ex_desc` longtext COMMENT '异常详情信息',
  `ex_detail` longtext COMMENT '异常描述',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `consuming_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  `browser` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `os` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `engine` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `engine_version` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `platform` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `browser_version` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_type` (`type`) USING BTREE COMMENT '日志类型'
) ENGINE=InnoDB AUTO_INCREMENT=1326106709160226941 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of common_opt_log
-- ----------------------------
BEGIN;
INSERT INTO `common_opt_log` VALUES (1326106703820877826, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'edit', '/roles/1', 'PUT', '[1,{\"code\":\"PLATFORM_ADMIN\",\"description\":\"22221123\",\"locked\":false,\"name\":\"平台管理员\",\"orgList\":[],\"scopeType\":\"THIS_LEVEL\"}]', NULL, NULL, NULL, '2020-11-10 18:17:30', '2020-11-10 18:17:30', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-10 10:17:30');
COMMIT;

-- ----------------------------
-- Table structure for common_opt_log_0
-- ----------------------------
DROP TABLE IF EXISTS `common_opt_log_0`;
CREATE TABLE `common_opt_log_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  `location` varchar(255) DEFAULT NULL,
  `trace` varchar(255) DEFAULT NULL COMMENT '日志链路追踪id日志标志',
  `type` varchar(5) DEFAULT 'OPT' COMMENT '日志类型\n#LogType{OPT:操作类型;EX:异常类型}',
  `description` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `class_path` varchar(255) DEFAULT NULL COMMENT '类路径',
  `action_method` varchar(50) DEFAULT NULL COMMENT '请求方法',
  `request_uri` varchar(50) DEFAULT NULL COMMENT '请求地址',
  `http_method` varchar(10) DEFAULT 'GET' COMMENT '请求类型\n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
  `params` longtext COMMENT '请求参数',
  `result` longtext COMMENT '返回值',
  `ex_desc` longtext COMMENT '异常详情信息',
  `ex_detail` longtext COMMENT '异常描述',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `consuming_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  `browser` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `os` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `engine` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `engine_version` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `platform` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `browser_version` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_type` (`type`) USING BTREE COMMENT '日志类型'
) ENGINE=InnoDB AUTO_INCREMENT=1326106709160226819 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of common_opt_log_0
-- ----------------------------
BEGIN;
INSERT INTO `common_opt_log_0` VALUES (1326106703820877826, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'edit', '/roles/1', 'PUT', '[1,{\"code\":\"PLATFORM_ADMIN\",\"description\":\"22221123\",\"locked\":false,\"name\":\"平台管理员\",\"orgList\":[],\"scopeType\":\"THIS_LEVEL\"}]', NULL, NULL, NULL, '2020-11-10 18:17:30', '2020-11-10 18:17:30', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-10 10:17:30');
INSERT INTO `common_opt_log_0` VALUES (1326106709160226818, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'edit', '/roles/1', 'PUT', '[1,{\"code\":\"PLATFORM_ADMIN\",\"description\":\"22221123\",\"locked\":false,\"name\":\"平台管理员\",\"orgList\":[],\"scopeType\":\"THIS_LEVEL\"}]', NULL, NULL, NULL, '2020-11-10 18:17:31', '2020-11-10 18:17:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-10 10:17:32');
COMMIT;

-- ----------------------------
-- Table structure for common_opt_log_1
-- ----------------------------
DROP TABLE IF EXISTS `common_opt_log_1`;
CREATE TABLE `common_opt_log_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  `location` varchar(255) DEFAULT NULL,
  `trace` varchar(255) DEFAULT NULL COMMENT '日志链路追踪id日志标志',
  `type` varchar(5) DEFAULT 'OPT' COMMENT '日志类型\n#LogType{OPT:操作类型;EX:异常类型}',
  `description` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `class_path` varchar(255) DEFAULT NULL COMMENT '类路径',
  `action_method` varchar(50) DEFAULT NULL COMMENT '请求方法',
  `request_uri` varchar(50) DEFAULT NULL COMMENT '请求地址',
  `http_method` varchar(10) DEFAULT 'GET' COMMENT '请求类型\n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
  `params` longtext COMMENT '请求参数',
  `result` longtext COMMENT '返回值',
  `ex_desc` longtext COMMENT '异常详情信息',
  `ex_detail` longtext COMMENT '异常描述',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `consuming_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  `browser` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `os` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `engine` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `engine_version` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `platform` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `browser_version` varchar(500) DEFAULT NULL COMMENT '浏览器',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_type` (`type`) USING BTREE COMMENT '日志类型'
) ENGINE=InnoDB AUTO_INCREMENT=1326106698024349698 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of common_opt_log_1
-- ----------------------------
BEGIN;
INSERT INTO `common_opt_log_1` VALUES (1326106664461529089, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'edit', '/roles/1', 'PUT', '[1,{\"code\":\"PLATFORM_ADMIN\",\"description\":\"22221123\",\"locked\":false,\"name\":\"平台管理员\",\"orgList\":[],\"scopeType\":\"THIS_LEVEL\"}]', NULL, NULL, NULL, '2020-11-10 18:17:20', '2020-11-10 18:17:20', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-10 10:17:21');
INSERT INTO `common_opt_log_1` VALUES (1326106698024349697, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'edit', '/roles/1', 'PUT', '[1,{\"code\":\"PLATFORM_ADMIN\",\"description\":\"22221123\",\"locked\":false,\"name\":\"平台管理员\",\"orgList\":[],\"scopeType\":\"THIS_LEVEL\"}]', NULL, NULL, NULL, '2020-11-10 18:17:29', '2020-11-10 18:17:29', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-10 10:17:29');
COMMIT;

-- ----------------------------
-- Table structure for gen_dynamic_release_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_dynamic_release_column`;
CREATE TABLE `gen_dynamic_release_column` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grid_id` bigint(20) NOT NULL COMMENT '所属表格',
  `title` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `key` varchar(50) DEFAULT NULL COMMENT '字段（key = 映射table中的字段)',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `width` int(8) DEFAULT NULL COMMENT '宽度',
  `show_overflow_tooltip` bit(1) DEFAULT b'0' COMMENT '0=不显示 1=显示',
  `disabled` bit(1) DEFAULT b'0' COMMENT '0=启用 1=禁用',
  `fixed` bit(1) DEFAULT b'0' COMMENT '列是否固定在左侧或者右侧，true 表示固定在左侧',
  `form` varchar(1024) DEFAULT NULL COMMENT 'form',
  `dict` varchar(1024) DEFAULT NULL COMMENT 'dict',
  `search` varchar(1024) DEFAULT NULL COMMENT 'search',
  `simple` bit(1) DEFAULT b'0' COMMENT '0=简单模式',
  `column_json` text COMMENT 'column_json 如果非简单模式，则直接取json内容渲染',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='动态发布表格字段';

-- ----------------------------
-- Records of gen_dynamic_release_column
-- ----------------------------
BEGIN;
INSERT INTO `gen_dynamic_release_column` VALUES (1, 1, '编号', 'id', 'input', NULL, b'0', b'1', b'0', '{\"disabled\":true,\"rules\":{\"required\":false,\"type\":\"email\"}}', '{\"code\":\"\",\"url\":\"\"}', '{\"disabled\":true,\"expression\":\"\"}', b'0', NULL, 0, NULL, '2020-11-13 06:25:51', 2, '不告诉你', '2020-11-18 07:39:19');
INSERT INTO `gen_dynamic_release_column` VALUES (2, 1, '名称', 'name', 'input', NULL, b'0', b'0', b'0', '{\"disabled\":false,\"rules\":{\"required\":true}}', '{}', '{\"disabled\":false,\"expression\":\"like\"}', b'0', NULL, 0, NULL, '2020-11-13 06:26:10', 2, '不告诉你', '2020-11-18 07:39:07');
INSERT INTO `gen_dynamic_release_column` VALUES (3, 1, '性别', 'sex', 'radio', NULL, b'0', b'0', b'0', '{\"disabled\":false,\"rules\":{\"required\":true}}', '{\"code\":\"SEX\"}', '{\"disabled\":false,\"expression\":\"eq\"}', b'0', NULL, 0, NULL, '2020-11-13 06:26:22', 2, '不告诉你', '2020-11-18 07:28:21');
INSERT INTO `gen_dynamic_release_column` VALUES (4, 2, '编号', 'id', NULL, NULL, b'0', b'0', b'0', NULL, NULL, '{\"disabled\": true}', b'0', NULL, 0, NULL, '2020-11-13 06:26:33', 0, NULL, '2020-11-16 10:02:57');
INSERT INTO `gen_dynamic_release_column` VALUES (5, 2, '标题', 'title', NULL, NULL, b'0', b'0', b'0', NULL, NULL, '{\"disabled\": false}', b'0', NULL, 0, NULL, '2020-11-13 06:26:46', 0, NULL, '2020-11-16 10:02:57');
INSERT INTO `gen_dynamic_release_column` VALUES (6, 1, '标题', 'title', 'input', NULL, b'0', b'0', b'0', '{\"disabled\":false,\"rules\":{\"required\":true,\"type\":\"\"}}', '{\"code\":\"\"}', '{\"disabled\":false,\"expression\":\"eq\"}', b'0', NULL, 2, '不告诉你', '2020-11-17 07:52:06', 2, '不告诉你', '2020-11-18 07:29:15');
INSERT INTO `gen_dynamic_release_column` VALUES (7, 1, '创建时间', 'created_time', 'input', NULL, b'0', b'0', b'0', '{\"disabled\":true,\"rules\":{\"required\":false}}', '{}', '{\"disabled\":true,\"expression\":\"\"}', b'0', NULL, 2, '不告诉你', '2020-11-17 07:52:27', 2, '不告诉你', '2020-11-18 07:38:06');
INSERT INTO `gen_dynamic_release_column` VALUES (8, 1, '民族', 'nation', 'select', NULL, b'0', b'0', b'0', '{\"disabled\":false,\"rules\":{\"required\":true}}', '{\"code\":\"NATION\"}', '{\"disabled\":false,\"expression\":\"eq\"}', b'0', NULL, 2, '不告诉你', '2020-11-18 03:35:12', 2, '不告诉你', '2020-11-18 07:29:18');
COMMIT;

-- ----------------------------
-- Table structure for gen_dynamic_release_datasource
-- ----------------------------
DROP TABLE IF EXISTS `gen_dynamic_release_datasource`;
CREATE TABLE `gen_dynamic_release_datasource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `database` varchar(100) NOT NULL COMMENT '数据库',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `host` varchar(50) NOT NULL DEFAULT 'localhost' COMMENT '数据库连接',
  `port` int(8) NOT NULL DEFAULT '3306' COMMENT '数据库端口',
  `description` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=正常1=禁用',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='动态发布的链接数据源';

-- ----------------------------
-- Records of gen_dynamic_release_datasource
-- ----------------------------
BEGIN;
INSERT INTO `gen_dynamic_release_datasource` VALUES (1, 'wemirr-test', 'root', '123456', 'localhost', 3306, '学校系统', b'0', 0, NULL, '2020-11-13 08:10:59', 2, '不告诉你', '2020-11-17 06:42:41');
COMMIT;

-- ----------------------------
-- Table structure for gen_dynamic_release_drag
-- ----------------------------
DROP TABLE IF EXISTS `gen_dynamic_release_drag`;
CREATE TABLE `gen_dynamic_release_drag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL COMMENT '模板名称',
  `model` varchar(30) DEFAULT NULL COMMENT '表单模块（唯一）',
  `setting` text,
  `table_name` varchar(255) DEFAULT NULL COMMENT '表名',
  `description` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=正常1=禁用',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='拖拽的动态发布表单';

-- ----------------------------
-- Records of gen_dynamic_release_drag
-- ----------------------------
BEGIN;
INSERT INTO `gen_dynamic_release_drag` VALUES (12, '上海战区', 'az_shanghai', '{\"disabled\":false,\"dragId\":12,\"fields\":[{\"__config__\":{\"changeTag\":true,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"formId\":101,\"label\":\"名称\",\"layout\":\"colFormItem\",\"renderKey\":\"1011606817614558\",\"required\":true,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\"},\"__slot__\":{},\"__vModel__\":\"name\",\"clearable\":true,\"disabled\":false,\"placeholder\":\"请输入名称\",\"prefix-icon\":\"\",\"readonly\":false,\"show-word-limit\":false,\"suffix-icon\":\"\"},{\"__config__\":{\"changeTag\":true,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"formId\":102,\"label\":\"描述\",\"layout\":\"colFormItem\",\"renderKey\":\"1021606817615549\",\"required\":true,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"textarea\"},\"__vModel__\":\"desc\",\"disabled\":false,\"placeholder\":\"请输入描述\",\"readonly\":false,\"show-word-limit\":false},{\"__config__\":{\"changeTag\":true,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"formId\":103,\"label\":\"密码\",\"layout\":\"colFormItem\",\"renderKey\":\"1031606817616188\",\"required\":true,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"password\"},\"__slot__\":{},\"__vModel__\":\"password\",\"clearable\":true,\"disabled\":false,\"placeholder\":\"请输入密码\",\"prefix-icon\":\"\",\"readonly\":false,\"show-word-limit\":false,\"suffix-icon\":\"\"},{\"__config__\":{\"changeTag\":true,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"formId\":104,\"label\":\"标题\",\"layout\":\"colFormItem\",\"renderKey\":\"1041606817616583\",\"required\":true,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\"},\"__slot__\":{},\"__vModel__\":\"title\",\"clearable\":true,\"disabled\":false,\"placeholder\":\"请输入标题\",\"prefix-icon\":\"\",\"readonly\":false,\"show-word-limit\":false,\"suffix-icon\":\"\"},{\"__config__\":{\"changeTag\":true,\"document\":\"https://element.eleme.cn/#/zh-CN/component/date-picker\",\"formId\":115,\"label\":\"日期范围\",\"layout\":\"colFormItem\",\"regList\":[],\"renderKey\":\"1151607076358523\",\"required\":true,\"showLabel\":true,\"span\":24,\"tag\":\"el-date-picker\",\"tagIcon\":\"date-range\"},\"__vModel__\":\"field115\",\"clearable\":true,\"disabled\":false,\"end-placeholder\":\"结束日期\",\"format\":\"yyyy-MM-dd\",\"range-separator\":\"至\",\"readonly\":false,\"start-placeholder\":\"开始日期\",\"value-format\":\"yyyy-MM-dd\"},{\"__config__\":{\"changeTag\":true,\"defaultValue\":[],\"document\":\"https://element.eleme.cn/#/zh-CN/component/checkbox\",\"formId\":116,\"label\":\"多选框组\",\"layout\":\"colFormItem\",\"optionType\":\"default\",\"regList\":[],\"renderKey\":\"1161607073866844\",\"required\":true,\"showLabel\":true,\"span\":24,\"tag\":\"el-checkbox-group\",\"tagIcon\":\"checkbox\"},\"__slot__\":{\"options\":[{\"label\":\"选项一\",\"value\":\"1\"},{\"label\":\"选项二\",\"value\":\"2\"}]},\"__vModel__\":\"field116\",\"disabled\":false}],\"formBtns\":true,\"formModel\":\"formData\",\"formRef\":\"elForm\",\"formRules\":\"rules\",\"gutter\":15,\"labelPosition\":\"right\",\"labelWidth\":100,\"size\":\"medium\",\"span\":24}', 'tenant_1_az_shanghai', 'az_shanghai', 1, b'0', 2, '不告诉你', '2020-12-01 10:03:49', 2, '不告诉你', '2020-12-04 10:06:16');
INSERT INTO `gen_dynamic_release_drag` VALUES (13, '江苏战区', 'az_jiangsu', '{\"disabled\":false,\"dragId\":13,\"fields\":[{\"__config__\":{\"changeTag\":true,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"formId\":101,\"label\":\"标题\",\"layout\":\"colFormItem\",\"renderKey\":\"1011606890781096\",\"required\":true,\"showLabel\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\"},\"__slot__\":{},\"__vModel__\":\"title\",\"clearable\":true,\"disabled\":false,\"placeholder\":\"请输入标题\",\"prefix-icon\":\"\",\"readonly\":false,\"show-word-limit\":false,\"suffix-icon\":\"\"},{\"__config__\":{\"changeTag\":true,\"document\":\"https://element.eleme.cn/#/zh-CN/component/select\",\"formId\":102,\"label\":\"性别\",\"layout\":\"colFormItem\",\"renderKey\":\"1021606890790596\",\"required\":true,\"showLabel\":true,\"tag\":\"el-select\",\"tagIcon\":\"select\"},\"__slot__\":{\"options\":[{\"label\":\"男\",\"value\":\"1\"},{\"label\":\"女\",\"value\":\"2\"}]},\"__vModel__\":\"sex\",\"clearable\":true,\"disabled\":false,\"placeholder\":\"请选择性别\"},{\"__config__\":{\"changeTag\":true,\"document\":\"http://tinymce.ax-z.cn\",\"formId\":103,\"label\":\"编辑器\",\"layout\":\"colFormItem\",\"renderKey\":\"1606891614229\",\"required\":false,\"showLabel\":true,\"tag\":\"tinymce\",\"tagIcon\":\"rich-text\"},\"__vModel__\":\"content\",\"placeholder\":\"请输入编辑器编辑器\"}],\"formBtns\":true,\"formModel\":\"formData\",\"formRef\":\"elForm\",\"formRules\":\"rules\",\"gutter\":15,\"labelPosition\":\"right\",\"labelWidth\":100,\"size\":\"medium\",\"span\":24}', 'tenant_1_az_jiangsu', '江苏战区的小伙伴们', 1, b'0', 2, '不告诉你', '2020-12-02 06:32:57', 2, '不告诉你', '2020-12-02 08:00:28');
COMMIT;

-- ----------------------------
-- Table structure for gen_dynamic_release_drag_push_server
-- ----------------------------
DROP TABLE IF EXISTS `gen_dynamic_release_drag_push_server`;
CREATE TABLE `gen_dynamic_release_drag_push_server` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `method` varchar(30) DEFAULT NULL COMMENT '请求方法',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `secret` varchar(255) DEFAULT NULL COMMENT '加密秘钥',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=正常1=禁用',
  `opt` varchar(255) DEFAULT NULL COMMENT '操作项',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='一键发布-数据推送服务器';

-- ----------------------------
-- Records of gen_dynamic_release_drag_push_server
-- ----------------------------
BEGIN;
INSERT INTO `gen_dynamic_release_drag_push_server` VALUES (3, 'POST', 'http://localhost:9000/tools/test_data_push', '123', b'0', 'DELETE,INSERT,UPDATE', 2, '不告诉你', '2020-12-03 08:45:17', 2, '不告诉你', '2020-12-08 08:57:24');
COMMIT;

-- ----------------------------
-- Table structure for gen_dynamic_release_drag_push_server_model
-- ----------------------------
DROP TABLE IF EXISTS `gen_dynamic_release_drag_push_server_model`;
CREATE TABLE `gen_dynamic_release_drag_push_server_model` (
  `push_id` bigint(20) NOT NULL COMMENT '推送ID(push_server_id)',
  `drag_id` bigint(20) NOT NULL COMMENT 'drag_id',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`push_id`,`drag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='一键发布-数据推送服务与drag关联表';

-- ----------------------------
-- Records of gen_dynamic_release_drag_push_server_model
-- ----------------------------
BEGIN;
INSERT INTO `gen_dynamic_release_drag_push_server_model` VALUES (3, 12, '2020-12-08 08:38:31');
INSERT INTO `gen_dynamic_release_drag_push_server_model` VALUES (3, 13, '2020-12-08 08:38:31');
COMMIT;

-- ----------------------------
-- Table structure for gen_dynamic_release_grid
-- ----------------------------
DROP TABLE IF EXISTS `gen_dynamic_release_grid`;
CREATE TABLE `gen_dynamic_release_grid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model` varchar(30) DEFAULT NULL COMMENT '编码（唯一）',
  `template_id` int(8) DEFAULT NULL COMMENT '模板ID',
  `datasource_id` int(8) DEFAULT NULL COMMENT '数据库连接',
  `table_name` varchar(30) DEFAULT NULL COMMENT '数据库表名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=正常1=禁用',
  `router_prefix` varchar(255) DEFAULT NULL COMMENT '路由前缀',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='动态发布表格';

-- ----------------------------
-- Records of gen_dynamic_release_grid
-- ----------------------------
BEGIN;
INSERT INTO `gen_dynamic_release_grid` VALUES (1, 'test1', 1, 1, 'gen_test1', '描述信息', b'0', NULL, 0, NULL, '2020-11-13 06:24:24', 2, '不告诉你', '2020-11-13 09:48:24');
COMMIT;

-- ----------------------------
-- Table structure for gen_test1
-- ----------------------------
DROP TABLE IF EXISTS `gen_test1`;
CREATE TABLE `gen_test1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '操作IP',
  `sex` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of gen_test1
-- ----------------------------
BEGIN;
INSERT INTO `gen_test1` VALUES (5, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-17 07:24:55');
INSERT INTO `gen_test1` VALUES (7, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:48');
INSERT INTO `gen_test1` VALUES (8, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:48');
INSERT INTO `gen_test1` VALUES (9, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:48');
INSERT INTO `gen_test1` VALUES (10, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (11, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (12, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (13, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (14, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (15, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (17, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (18, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (19, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (20, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (21, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (22, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (23, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (24, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (25, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (26, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (27, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (28, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:51');
INSERT INTO `gen_test1` VALUES (32, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (33, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (34, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (35, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (36, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (37, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (38, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (39, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (40, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (41, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (42, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (43, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (44, '名字1', 'MALE', '我会个标题', 'mz_byz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (45, '名字2', 'FEMALE', '标题儿', 'mz_blz', '2020-11-18 07:52:52');
INSERT INTO `gen_test1` VALUES (46, '名称', 'MALE', '标题', 'mz_acz', '2020-11-18 07:52:52');
COMMIT;

-- ----------------------------
-- Table structure for gen_test2
-- ----------------------------
DROP TABLE IF EXISTS `gen_test2`;
CREATE TABLE `gen_test2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '操作IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of gen_test2
-- ----------------------------
BEGIN;
INSERT INTO `gen_test2` VALUES (1, 'standard');
COMMIT;

-- ----------------------------
-- Table structure for sys_core_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_core_org`;
CREATE TABLE `sys_core_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `abbreviation` varchar(255) DEFAULT '' COMMENT '简称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父ID',
  `tree_path` varchar(255) DEFAULT ',' COMMENT '树结构',
  `sequence` int(11) DEFAULT '1' COMMENT '排序',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `FU_PATH` (`tree_path`)
) ENGINE=InnoDB AUTO_INCREMENT=10019 DEFAULT CHARSET=utf8mb4 COMMENT='组织';

-- ----------------------------
-- Records of sys_core_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_core_org` VALUES (100, '鏖战八方', '鏖战', 0, ',', 1, b'1', '初始化数据', 1, '1', '2019-07-10 17:02:18', 1, '1', '2020-10-29 06:38:46');
INSERT INTO `sys_core_org` VALUES (101, '鏖战八方上海分部', '上海鏖战', 100, ',100,', 0, b'1', '初始化数据', 1, '1', '2019-08-06 09:10:53', 1, '1', '2020-10-29 06:38:37');
INSERT INTO `sys_core_org` VALUES (102, '鏖战八方北京分部', '北京八方', 100, ',100,', 1, b'1', '初始化数据', 1, '1', '2019-11-07 16:13:09', 1, '1', '2020-10-29 06:38:43');
INSERT INTO `sys_core_org` VALUES (10001, '综合部', '综合部', 101, ',100,101,', 0, b'1', '前台&HR', 3, '3', '2019-11-12 11:34:27', 3, '3', '2020-10-29 02:32:22');
INSERT INTO `sys_core_org` VALUES (10002, '管理层', '管理层', 100, ',100,', 3, b'1', '', 3, '3', '2019-11-12 11:34:39', 3, '3', '2020-10-29 02:32:23');
INSERT INTO `sys_core_org` VALUES (10003, '总经办', '', 100, ',100,', 2, b'1', '', 3, '3', '2019-11-12 11:35:37', 3, '3', '2020-10-29 02:32:25');
INSERT INTO `sys_core_org` VALUES (10004, '财务部', '', 100, ',100,', 4, b'1', '', 3, '3', '2019-11-12 11:37:17', 3, '3', '2020-10-29 02:32:27');
INSERT INTO `sys_core_org` VALUES (10005, '市场部', '', 100, ',100,', 5, b'1', '', 3, '3', '2019-11-12 11:37:37', 3, '3', '2020-10-29 02:32:31');
INSERT INTO `sys_core_org` VALUES (10007, '研发部', '22', 101, ',100,101,', 5, b'1', '22', 3, '3', '2019-11-12 11:38:21', 2, '不告诉你', '2020-10-29 06:18:50');
INSERT INTO `sys_core_org` VALUES (10008, '产品部', '32', 101, ',100,101,', 2, b'0', '32', 3, '3', '2019-11-12 11:38:31', 2, '不告诉你', '2020-10-29 06:17:39');
INSERT INTO `sys_core_org` VALUES (10009, '综合部', '综合部', 102, ',100,102,', 0, b'1', '', 3, '3', '2019-11-12 11:38:39', 3, '3', '2020-10-29 02:32:39');
INSERT INTO `sys_core_org` VALUES (10010, '研发部', '', 102, ',100,102,', 0, b'1', '', 3, '3', '2019-11-12 11:38:50', 3, '3', '2020-10-29 02:32:41');
INSERT INTO `sys_core_org` VALUES (10011, '销售部', '', 102, ',100,102,', 2, b'1', '', 3, '3', '2019-11-12 11:39:00', 3, '3', '2020-10-29 02:32:43');
INSERT INTO `sys_core_org` VALUES (10018, '测试部门1', '测试', 102, ',', 1, b'1', '测试部门', 2, '不告诉你', '2020-10-29 06:39:09', 2, '不告诉你', '2020-12-02 09:30:24');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu_bak
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_bak`;
CREATE TABLE `sys_menu_bak` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单ID',
  `path` varchar(255) DEFAULT '' COMMENT '路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `sequence` int(11) DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `style` varchar(255) DEFAULT NULL COMMENT '样式',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型（1=菜单;2=按钮）',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=启用;1=禁用',
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置菜单（0=否;1=是）',
  `global` bit(1) DEFAULT b'0' COMMENT '公共资源\nTrue是无需分配所有人就可以访问的',
  `description` varchar(200) DEFAULT '' COMMENT '描述',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `INX_STATUS` (`global`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10303 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu_bak
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu_bak` VALUES (1, '系统管理', NULL, 0, '/system/management', 'layoutHeaderAside', 4, 'plug', NULL, NULL, b'0', b'0', b'0', '系统管理', 1, NULL, '2019-07-25 15:35:12', 3, NULL, '2019-11-11 14:32:02');
INSERT INTO `sys_menu_bak` VALUES (2, '运维监控', NULL, 0, '/system/development', 'layoutHeaderAside', 1, 'television', NULL, NULL, b'0', b'0', b'0', '运维监控', NULL, NULL, '2020-11-09 09:49:52', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (101, '用户中心', NULL, 1, '/system/management/user', '/system/management/user', 0, 'user', NULL, NULL, b'0', b'0', b'0', '用户中心', 1, NULL, '2019-07-25 16:11:00', 2, '不告诉你', '2019-11-11 14:28:40');
INSERT INTO `sys_menu_bak` VALUES (102, '权限管理', NULL, 1, '/system/management/auth', '/system/management/auth', 1, 'bar-chart', NULL, NULL, b'0', b'0', b'0', '权限管理', 1, NULL, '2019-07-25 16:11:41', 3, NULL, '2019-11-11 14:28:43');
INSERT INTO `sys_menu_bak` VALUES (103, '基础配置', NULL, 1, '/system/management/basic', '/system/management/basic', 2, 'location-arrow', NULL, NULL, b'0', b'0', b'0', '基础配置', 1, NULL, '2019-07-25 16:13:09', 2, '不告诉你', '2019-11-11 14:28:49');
INSERT INTO `sys_menu_bak` VALUES (200, '监控中心', NULL, 2, '/system/development/index', '/system/development/index', 0, 'television', NULL, NULL, b'0', b'0', b'0', '监控中心', NULL, NULL, '2020-11-09 09:55:04', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (201, '登录日志', NULL, 2, '/system/development/log/login', '/system/development/log/login', 1, 'sign-in', NULL, NULL, b'0', b'0', b'0', '登录日志', NULL, NULL, '2020-11-09 09:50:13', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (202, '操作日志', NULL, 2, '/system/development/log/opt', '/system/development/log/opt', 2, 'deaf', NULL, NULL, b'0', b'0', b'0', '操作日志', NULL, NULL, '2020-11-09 09:50:19', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (203, '代码生成', NULL, 2, '/system/development/generate', '/system/development/generate', 3, 'tablet', NULL, NULL, b'0', b'0', b'0', '代码生成', NULL, NULL, '2020-11-09 09:50:25', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (204, '表单设计', NULL, 2, '/system/development/build', '/system/development/build', 4, 'tablet', NULL, NULL, b'0', b'0', b'0', '表单设计', NULL, NULL, '2020-11-09 09:50:30', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (10101, '用户管理', NULL, 101, '/system/management/user', '/system/management/user', 1, 'user', NULL, NULL, b'0', b'0', b'0', '用户管理', NULL, NULL, '2020-11-09 07:50:53', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (10102, '组织管理', NULL, 101, '/system/management/user/org', '/system/management/user/org', 1, 'list', NULL, NULL, b'0', b'0', b'0', '组织管理', NULL, NULL, '2020-11-09 07:51:00', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (10103, '岗位管理', NULL, 101, '/system/management/user/station', '/system/management/user/station', 1, 'sitemap', NULL, NULL, b'0', b'0', b'0', '岗位管理', NULL, NULL, '2020-11-09 07:51:09', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (10201, '菜单管理', NULL, 102, '/system/management/auth/menu', '/system/management/auth/menu', 1, 'bars', NULL, NULL, b'0', b'0', b'0', '菜单管理', NULL, NULL, '2020-11-09 07:51:37', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (10202, '角色管理', NULL, 102, '/system/management/auth/role', '/system/management/auth/role', 1, 'id-card', NULL, NULL, b'0', b'0', b'0', '角色管理', NULL, NULL, '2020-11-09 07:51:41', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (10301, '数据字典', NULL, 103, '/system/management/basic/dictionary', '/system/management/basic/dictionary', 1, 'database', NULL, NULL, b'0', b'0', b'0', '数据字典', NULL, NULL, '2020-11-09 07:52:59', NULL, NULL, NULL);
INSERT INTO `sys_menu_bak` VALUES (10302, '地区信息', NULL, 103, '/system/management/basic/area', '/system/management/basic/area', 1, 'location-arrow', NULL, NULL, b'0', b'0', b'0', '地区信息', NULL, NULL, '2020-11-09 07:53:12', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `client_id` varchar(32) NOT NULL COMMENT '客户端ID',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '客户端秘钥',
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL COMMENT '客户端名称',
  `scope` varchar(256) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '认证类型',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT 'web服务站点',
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  `tenant_type` tinyint(2) DEFAULT NULL COMMENT '租户类型',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='终端信息表';

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client_details` VALUES ('client', 'client', NULL, NULL, 'server', 'password,refresh_token,sms', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_oauth_client_details` VALUES ('test', 'test', NULL, NULL, 'server', 'password,refresh_token,sms', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `model` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单ID',
  `path` varchar(255) DEFAULT '' COMMENT '路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `sequence` int(11) DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `style` varchar(255) DEFAULT NULL COMMENT '样式',
  `type` tinyint(2) DEFAULT '1' COMMENT '类型（1=菜单;2=按钮）',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=启用;1=禁用',
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置菜单（0=否;1=是）',
  `global` bit(1) DEFAULT b'0' COMMENT '公共资源\nTrue是无需分配所有人就可以访问的',
  `description` varchar(200) DEFAULT '' COMMENT '描述',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `INX_STATUS` (`global`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000017 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES (1, '系统管理', NULL, 'system:view', 0, '/system/management', 'layoutHeaderAside', 0, '', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 09:39:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (2, '开发平台', NULL, 'development:view', 0, '/system/development', 'layoutHeaderAside', 1, 'television', NULL, 1, b'0', b'0', b'0', '运维监控', NULL, NULL, '2020-11-09 09:49:52', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (101, '用户中心', NULL, 'user:info:view', 1, '/system/management/user', NULL, 0, 'user', NULL, 1, b'0', b'0', b'0', '用户中心', 1, NULL, '2019-07-25 16:11:00', 2, '不告诉你', '2019-11-11 14:28:40');
INSERT INTO `sys_resource` VALUES (102, '权限管理', NULL, 'auth:view', 1, '/system/management/auth', NULL, 1, 'bar-chart', NULL, 1, b'0', b'0', b'0', '权限管理', 1, NULL, '2019-07-25 16:11:41', 3, NULL, '2019-11-11 14:28:43');
INSERT INTO `sys_resource` VALUES (103, '基础配置', NULL, 'basic:view', 1, '/system/management/basic', NULL, 2, 'location-arrow', NULL, 1, b'0', b'0', b'0', '基础配置', 1, NULL, '2019-07-25 16:13:09', 2, '不告诉你', '2019-11-11 14:28:49');
INSERT INTO `sys_resource` VALUES (104, '消息中心', NULL, 'message:center:view', 1, '/system/management/message', NULL, 4, 'commenting', NULL, 1, b'0', b'0', b'0', '消息中心', NULL, NULL, '2020-11-19 02:43:10', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (106, '网关管理', NULL, 'system:gateway:view', 1, '/system/management/gateway', NULL, 6, 'openid', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 02:38:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (200, '监控中心', NULL, 'development:view', 2, '/system/development/index', '/system/development/index', 2, 'television', NULL, 1, b'0', b'0', b'0', '监控中心', NULL, NULL, '2020-11-09 09:55:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (201, '日志管理', NULL, 'log:view', 2, '/system/development/log', NULL, 2, 'file-text', NULL, 1, b'0', b'0', b'0', '日志管理', NULL, NULL, '2020-11-12 04:51:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (203, '代码生成', NULL, 'development:generate:view', 2, '/system/development/generate', '/system/development/generate', 4, 'tablet', NULL, 1, b'0', b'0', b'0', '代码生成', NULL, NULL, '2020-11-09 09:50:25', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (204, '一键发布', NULL, 'development:build:view', 2, '/system/development/build', NULL, 0, 'arrows', NULL, 1, b'0', b'0', b'0', NULL, NULL, NULL, '2020-11-09 09:50:30', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (205, '试点发布', NULL, 'point:release:view', 2, '/system/development/release', NULL, 1, 'telegram', NULL, 1, b'0', b'0', b'0', '试点发布', NULL, NULL, '2020-11-12 05:00:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (206, '服务治理', NULL, 'service:governance:view', 2, '/system/development/governance', NULL, 1, '', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 05:39:01', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10101, '用户管理', NULL, 'user:view', 101, '/system/management/user', '/system/management/user', 1, 'user', NULL, 1, b'0', b'0', b'0', '用户管理', NULL, NULL, '2020-11-09 07:50:53', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10102, '组织管理', NULL, 'org:view', 101, '/system/management/user/org', '/system/management/user/org', 1, 'list', NULL, 1, b'0', b'0', b'0', '组织管理', NULL, NULL, '2020-11-09 07:51:00', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10103, '岗位管理', NULL, 'station:view', 101, '/system/management/user/station', '/system/management/user/station', 1, 'sitemap', NULL, 1, b'0', b'0', b'0', '岗位管理', NULL, NULL, '2020-11-09 07:51:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10201, '菜单管理', NULL, 'menu:view', 102, '/system/management/auth/menu', '/system/management/auth/menu', 1, 'bars', NULL, 1, b'0', b'0', b'0', '菜单管理', NULL, NULL, '2020-11-09 07:51:37', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10202, '角色管理', NULL, 'role:view', 102, '/system/management/auth/role', '/system/management/auth/role', 1, 'id-card', NULL, 1, b'0', b'0', b'0', '角色管理', NULL, NULL, '2020-11-09 07:51:41', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10301, '数据字典', NULL, 'dictionary:view', 103, '/system/management/basic/dictionary', '/system/management/basic/dictionary', 1, 'database', NULL, 1, b'0', b'0', b'0', '数据字典', NULL, NULL, '2020-11-09 07:52:59', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10302, '地区信息', NULL, 'area:view', 103, '/system/management/basic/area', '/system/management/basic/area', 1, 'location-arrow', NULL, 1, b'0', b'0', b'0', '地区信息', NULL, NULL, '2020-11-09 07:53:12', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10401, '发布消息', NULL, 'message:center:publish', 104, '/system/management/message/publish', '/system/management/message/publish', 1, 'envira', NULL, 1, b'0', b'0', b'0', '发布消息', NULL, NULL, '2020-11-19 02:48:22', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10402, '我的消息', NULL, 'message:center:my', 104, '/system/management/message/my', '/system/management/message/my', 1, 'envelope', NULL, 1, b'0', b'0', b'0', '我的消息', NULL, NULL, '2020-11-19 02:50:54', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10601, '限流规则', NULL, 'system:gateway:limit', 106, '/system/management/gateway/limit', '/system/management/gateway/limit', 1, 'tint', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 02:38:51', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10602, '黑白名单', NULL, 'system:gateway:rejet', 106, '/system/management/gateway/rejet', '/system/management/gateway/rejet', 2, 'list-ul', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 02:39:02', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20101, '登录日志', NULL, 'log:login:view', 201, '/system/development/log/login', '/system/development/log/login', 1, 'sign-in', NULL, 1, b'0', b'0', b'0', '登录日志', NULL, NULL, '2020-11-09 09:50:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20102, '操作日志', NULL, 'log:opt:view', 201, '/system/development/log/opt', '/system/development/log/opt', 2, 'deaf', NULL, 1, b'0', b'0', b'0', '操作日志', NULL, NULL, '2020-11-09 09:50:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20401, '表单配置', NULL, 'development:form:config:view', 204, '/system/development/build/setting', '/system/development/build/setting', 1, 'cog', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-23 07:25:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20403, '模板列表', NULL, 'development:form:model:view', 204, '/system/development/build/index', '/system/development/build/index', 1, 'list', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-25 10:19:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20404, '数据推送', NULL, 'development:data:push:view', 204, '/system/development/build/extend/push', '/system/development/build/extend/push', 1, 'paper-plane', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-12-03 07:53:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20501, '数据源管理', NULL, 'development:datasource:view', 205, '/system/development/release/datasource', '/system/development/release/datasource', 0, 'database', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:01:31', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20502, '模型管理', NULL, 'development:model:view', 205, '/system/development/release/model', '/system/development/release/model', 1, 'modx', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:03:29', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20503, '模板管理', NULL, 'development:template:view', 205, '/system/development/release/template', '/system/development/release/template', 1, 'empire', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:04:07', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20504, '表格管理', NULL, 'development:grid:view', 205, '/system/development/release/grid', '/system/development/release/grid', 1, 'th-large', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:04:58', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20505, '表单管理', NULL, 'development:form:view', 205, '/system/development/release/form', '/system/development/release/form', 1, 'table', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:05:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20506, '测试生成', NULL, 'test:view', 205, '/system/development/release/test1', '/system/development/release/standard', 2, 'table', NULL, 1, b'0', b'0', b'0', '测试啊测', NULL, NULL, '2020-11-12 06:20:29', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20601, '注册中心', NULL, 'service:governance:nacos:view', 206, '/system/development/governance/nacos', '/system/development/governance/nacos', 1, '', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 05:42:36', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10000001, '添加', NULL, 'user:add', 10101, '', NULL, 0, '', NULL, 2, b'0', b'0', b'0', '', 2, '不告诉你', '2020-11-12 01:45:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10000010, '删除', NULL, 'user:del', 10101, '', NULL, 0, '', NULL, 2, b'0', b'0', b'0', '', 2, '不告诉你', '2020-11-12 03:32:49', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10000012, '鏖战八方', '', NULL, 0, '/system/development/release/tenant_1', 'layoutHeaderAside', 0, 'phone-outline', NULL, 1, b'0', b'0', b'0', '', 2, '不告诉你', '2020-12-01 10:03:17', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10000015, '上海战区', 'az_shanghai', NULL, 10000012, '/system/development/release/tenant_1/az_shanghai', '/system/development/build/standard', 0, 'assistive-listening-systems', NULL, 5, b'1', b'0', b'1', '', 2, '不告诉你', '2020-12-01 10:15:27', 2, '不告诉你', NULL);
INSERT INTO `sys_resource` VALUES (10000016, '江苏战区', 'az_jiangsu', NULL, 10000012, '/system/development/release/tenant_1/az_jiangsu', '/system/development/build/standard', 0, 'american-sign-language-interpreting', NULL, 5, b'0', b'0', b'0', '', 2, '不告诉你', '2020-12-02 06:34:10', 2, '不告诉你', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_resource_copy1
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_copy1`;
CREATE TABLE `sys_resource_copy1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `model` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单ID',
  `path` varchar(255) DEFAULT '' COMMENT '路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `sequence` int(11) DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `style` varchar(255) DEFAULT NULL COMMENT '样式',
  `type` tinyint(2) DEFAULT '1' COMMENT '类型（1=菜单;2=按钮）',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=启用;1=禁用',
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置菜单（0=否;1=是）',
  `global` bit(1) DEFAULT b'0' COMMENT '公共资源\nTrue是无需分配所有人就可以访问的',
  `description` varchar(200) DEFAULT '' COMMENT '描述',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `INX_STATUS` (`global`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000014 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单';

-- ----------------------------
-- Records of sys_resource_copy1
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource_copy1` VALUES (1, '系统管理', NULL, 'system:view', 0, '/system/management', 'layoutHeaderAside', 0, '', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 09:39:19', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (2, '开发平台', NULL, 'development:view', 0, '/system/development', 'layoutHeaderAside', 1, 'television', NULL, 1, b'0', b'0', b'0', '运维监控', NULL, NULL, '2020-11-09 09:49:52', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (101, '用户中心', NULL, 'user:info:view', 1, '/system/management/user', NULL, 0, 'user', NULL, 1, b'0', b'0', b'0', '用户中心', 1, NULL, '2019-07-25 16:11:00', 2, '不告诉你', '2019-11-11 14:28:40');
INSERT INTO `sys_resource_copy1` VALUES (102, '权限管理', NULL, 'auth:view', 1, '/system/management/auth', NULL, 1, 'bar-chart', NULL, 1, b'0', b'0', b'0', '权限管理', 1, NULL, '2019-07-25 16:11:41', 3, NULL, '2019-11-11 14:28:43');
INSERT INTO `sys_resource_copy1` VALUES (103, '基础配置', NULL, 'basic:view', 1, '/system/management/basic', NULL, 2, 'location-arrow', NULL, 1, b'0', b'0', b'0', '基础配置', 1, NULL, '2019-07-25 16:13:09', 2, '不告诉你', '2019-11-11 14:28:49');
INSERT INTO `sys_resource_copy1` VALUES (104, '消息中心', NULL, 'message:center:view', 1, '/system/management/message', NULL, 4, 'commenting', NULL, 1, b'0', b'0', b'0', '消息中心', NULL, NULL, '2020-11-19 02:43:10', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (106, '网关管理', NULL, 'system:gateway:view', 1, '/system/management/gateway', NULL, 6, 'openid', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 02:38:13', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (200, '监控中心', NULL, 'development:view', 2, '/system/development/index', '/system/development/index', 2, 'television', NULL, 1, b'0', b'0', b'0', '监控中心', NULL, NULL, '2020-11-09 09:55:04', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (201, '日志管理', NULL, 'log:view', 2, '/system/development/log', NULL, 2, 'file-text', NULL, 1, b'0', b'0', b'0', '日志管理', NULL, NULL, '2020-11-12 04:51:38', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (203, '代码生成', NULL, 'development:generate:view', 2, '/system/development/generate', '/system/development/generate', 4, 'tablet', NULL, 1, b'0', b'0', b'0', '代码生成', NULL, NULL, '2020-11-09 09:50:25', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (204, '一键发布', NULL, 'development:build:view', 2, '/system/development/build', NULL, 0, 'arrows', NULL, 1, b'0', b'0', b'0', NULL, NULL, NULL, '2020-11-09 09:50:30', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (205, '试点发布', NULL, 'point:release:view', 2, '/system/development/release', NULL, 1, 'telegram', NULL, 1, b'0', b'0', b'0', '试点发布', NULL, NULL, '2020-11-12 05:00:38', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (206, '服务治理', NULL, 'service:governance:view', 2, '/system/development/governance', NULL, 1, '', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 05:39:01', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10101, '用户管理', NULL, 'user:view', 101, '/system/management/user', '/system/management/user', 1, 'user', NULL, 1, b'0', b'0', b'0', '用户管理', NULL, NULL, '2020-11-09 07:50:53', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10102, '组织管理', NULL, 'org:view', 101, '/system/management/user/org', '/system/management/user/org', 1, 'list', NULL, 1, b'0', b'0', b'0', '组织管理', NULL, NULL, '2020-11-09 07:51:00', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10103, '岗位管理', NULL, 'station:view', 101, '/system/management/user/station', '/system/management/user/station', 1, 'sitemap', NULL, 1, b'0', b'0', b'0', '岗位管理', NULL, NULL, '2020-11-09 07:51:09', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10201, '菜单管理', NULL, 'menu:view', 102, '/system/management/auth/menu', '/system/management/auth/menu', 1, 'bars', NULL, 1, b'0', b'0', b'0', '菜单管理', NULL, NULL, '2020-11-09 07:51:37', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10202, '角色管理', NULL, 'role:view', 102, '/system/management/auth/role', '/system/management/auth/role', 1, 'id-card', NULL, 1, b'0', b'0', b'0', '角色管理', NULL, NULL, '2020-11-09 07:51:41', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10301, '数据字典', NULL, 'dictionary:view', 103, '/system/management/basic/dictionary', '/system/management/basic/dictionary', 1, 'database', NULL, 1, b'0', b'0', b'0', '数据字典', NULL, NULL, '2020-11-09 07:52:59', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10302, '地区信息', NULL, 'area:view', 103, '/system/management/basic/area', '/system/management/basic/area', 1, 'location-arrow', NULL, 1, b'0', b'0', b'0', '地区信息', NULL, NULL, '2020-11-09 07:53:12', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10401, '发布消息', NULL, 'message:center:publish', 104, '/system/management/message/publish', '/system/management/message/publish', 1, 'envira', NULL, 1, b'0', b'0', b'0', '发布消息', NULL, NULL, '2020-11-19 02:48:22', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10402, '我的消息', NULL, 'message:center:my', 104, '/system/management/message/my', '/system/management/message/my', 1, 'envelope', NULL, 1, b'0', b'0', b'0', '我的消息', NULL, NULL, '2020-11-19 02:50:54', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10601, '限流规则', NULL, 'system:gateway:limit', 106, '/system/management/gateway/limit', '/system/management/gateway/limit', 1, 'tint', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 02:38:51', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10602, '黑白名单', NULL, 'system:gateway:rejet', 106, '/system/management/gateway/rejet', '/system/management/gateway/rejet', 2, 'list-ul', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 02:39:02', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20101, '登录日志', NULL, 'log:login:view', 201, '/system/development/log/login', '/system/development/log/login', 1, 'sign-in', NULL, 1, b'0', b'0', b'0', '登录日志', NULL, NULL, '2020-11-09 09:50:13', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20102, '操作日志', NULL, 'log:opt:view', 201, '/system/development/log/opt', '/system/development/log/opt', 2, 'deaf', NULL, 1, b'0', b'0', b'0', '操作日志', NULL, NULL, '2020-11-09 09:50:19', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20401, '表单配置', NULL, 'development:form:config:view', 204, '/system/development/build/setting', '/system/development/build/setting', 1, 'cog', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-23 07:25:13', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20402, '测试一键', NULL, 'test', 204, '/system/development/release/test', '/system/development/build/standard', 9, 'battery-empty', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 09:44:42', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20403, '表单列表', NULL, 'test', 204, '/system/development/build/index', '/system/development/build/index', 1, '', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-25 10:19:43', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20501, '数据源管理', NULL, 'development:datasource:view', 205, '/system/development/release/datasource', '/system/development/release/datasource', 0, 'database', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:01:31', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20502, '模型管理', NULL, 'development:model:view', 205, '/system/development/release/model', '/system/development/release/model', 1, 'modx', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:03:29', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20503, '模板管理', NULL, 'development:template:view', 205, '/system/development/release/template', '/system/development/release/template', 1, 'empire', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:04:07', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20504, '表格管理', NULL, 'development:grid:view', 205, '/system/development/release/grid', '/system/development/release/grid', 1, 'th-large', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:04:58', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20505, '表单管理', NULL, 'development:form:view', 205, '/system/development/release/form', '/system/development/release/form', 1, 'table', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-12 05:05:13', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20506, '测试生成', NULL, 'test:view', 205, '/system/development/release/test1', '/system/development/release/standard', 2, 'table', NULL, 1, b'0', b'0', b'0', '测试啊测', NULL, NULL, '2020-11-12 06:20:29', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (20601, '注册中心', NULL, 'service:governance:nacos:view', 206, '/system/development/governance/nacos', '/system/development/governance/nacos', 1, '', NULL, 1, b'0', b'0', b'0', '', NULL, NULL, '2020-11-20 05:42:36', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10000001, '添加', NULL, 'user:add', 10101, '', NULL, 0, '', NULL, 2, b'0', b'0', b'0', '', 2, '不告诉你', '2020-11-12 01:45:04', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10000010, '删除', NULL, 'user:del', 10101, '', NULL, 0, '', NULL, 2, b'0', b'0', b'0', '', 2, '不告诉你', '2020-11-12 03:32:49', NULL, NULL, NULL);
INSERT INTO `sys_resource_copy1` VALUES (10000013, '测试一下', 'test', '123', 204, '/system/development/release/tenant_1/test', '/system/development/build/standard', 0, 'el-icon-phone', NULL, 5, b'0', b'0', b'0', '', 2, '不告诉你', '2020-11-27 10:27:19', 2, '不告诉你', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` int(20) DEFAULT NULL COMMENT '租户编码',
  `code` varchar(30) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `scope_type` tinyint(2) DEFAULT NULL COMMENT '数据权限范围，值越大，权限越大',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=正常1=禁用',
  `super` bit(1) DEFAULT b'0' COMMENT '0=非 1=管理员',
  `readonly` bit(1) DEFAULT b'0' COMMENT '是否内置角色',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 1, 'PLATFORM_ADMIN', '平台管理员', '22221123', 50, b'0', b'1', b'1', 1, '1', '2019-10-25 13:46:00', 57, NULL, '2020-11-19 06:37:12');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_org`;
CREATE TABLE `sys_role_org` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `org_id` bigint(20) NOT NULL COMMENT '组织ID',
  UNIQUE KEY `role_id` (`role_id`,`org_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_role_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_org` VALUES (4, 102);
INSERT INTO `sys_role_org` VALUES (4, 10002);
INSERT INTO `sys_role_org` VALUES (4, 10003);
INSERT INTO `sys_role_org` VALUES (4, 10004);
INSERT INTO `sys_role_org` VALUES (4, 10005);
INSERT INTO `sys_role_org` VALUES (4, 10006);
INSERT INTO `sys_role_org` VALUES (4, 10009);
INSERT INTO `sys_role_org` VALUES (4, 10010);
INSERT INTO `sys_role_org` VALUES (4, 10011);
INSERT INTO `sys_role_org` VALUES (4, 10018);
INSERT INTO `sys_role_org` VALUES (5, 102);
INSERT INTO `sys_role_org` VALUES (5, 10005);
INSERT INTO `sys_role_org` VALUES (5, 10009);
INSERT INTO `sys_role_org` VALUES (5, 10010);
INSERT INTO `sys_role_org` VALUES (5, 10011);
INSERT INTO `sys_role_org` VALUES (5, 10018);
INSERT INTO `sys_role_org` VALUES (6, 100);
INSERT INTO `sys_role_org` VALUES (6, 101);
INSERT INTO `sys_role_org` VALUES (6, 102);
INSERT INTO `sys_role_org` VALUES (6, 10001);
INSERT INTO `sys_role_org` VALUES (6, 10002);
INSERT INTO `sys_role_org` VALUES (6, 10003);
INSERT INTO `sys_role_org` VALUES (6, 10004);
INSERT INTO `sys_role_org` VALUES (6, 10005);
INSERT INTO `sys_role_org` VALUES (6, 10007);
INSERT INTO `sys_role_org` VALUES (6, 10009);
INSERT INTO `sys_role_org` VALUES (6, 10010);
INSERT INTO `sys_role_org` VALUES (6, 10011);
INSERT INTO `sys_role_org` VALUES (6, 10018);
INSERT INTO `sys_role_org` VALUES (7, 100);
INSERT INTO `sys_role_org` VALUES (7, 101);
INSERT INTO `sys_role_org` VALUES (7, 102);
INSERT INTO `sys_role_org` VALUES (7, 10001);
INSERT INTO `sys_role_org` VALUES (7, 10002);
INSERT INTO `sys_role_org` VALUES (7, 10003);
INSERT INTO `sys_role_org` VALUES (7, 10004);
INSERT INTO `sys_role_org` VALUES (7, 10005);
INSERT INTO `sys_role_org` VALUES (7, 10006);
INSERT INTO `sys_role_org` VALUES (7, 10007);
INSERT INTO `sys_role_org` VALUES (7, 10009);
INSERT INTO `sys_role_org` VALUES (7, 10010);
INSERT INTO `sys_role_org` VALUES (7, 10011);
INSERT INTO `sys_role_org` VALUES (7, 10018);
INSERT INTO `sys_role_org` VALUES (8, 100);
INSERT INTO `sys_role_org` VALUES (8, 101);
INSERT INTO `sys_role_org` VALUES (8, 102);
INSERT INTO `sys_role_org` VALUES (8, 10001);
INSERT INTO `sys_role_org` VALUES (8, 10002);
INSERT INTO `sys_role_org` VALUES (8, 10003);
INSERT INTO `sys_role_org` VALUES (8, 10004);
INSERT INTO `sys_role_org` VALUES (8, 10005);
INSERT INTO `sys_role_org` VALUES (8, 10006);
INSERT INTO `sys_role_org` VALUES (8, 10007);
INSERT INTO `sys_role_org` VALUES (8, 10009);
INSERT INTO `sys_role_org` VALUES (8, 10010);
INSERT INTO `sys_role_org` VALUES (8, 10011);
INSERT INTO `sys_role_org` VALUES (8, 10018);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res` (
  `role_id` bigint(8) NOT NULL COMMENT '角色ID',
  `res_id` bigint(8) NOT NULL COMMENT '菜单ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY `idx_role_res` (`role_id`,`res_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_res` VALUES (1, 1, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 2, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 101, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 102, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 103, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 104, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 200, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 201, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 203, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 204, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 205, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10101, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10102, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10103, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10201, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10202, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10301, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10302, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10401, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10402, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 20101, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 20102, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 20403, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 20404, '2020-12-03 07:54:36');
INSERT INTO `sys_role_res` VALUES (1, 20501, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 20504, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 20506, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10000001, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10000010, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10000012, '2020-12-01 10:03:17');
INSERT INTO `sys_role_res` VALUES (1, 10000013, '2020-11-27 10:30:18');
INSERT INTO `sys_role_res` VALUES (1, 10000015, '2020-12-01 10:15:27');
INSERT INTO `sys_role_res` VALUES (1, 10000016, '2020-12-02 06:34:10');
INSERT INTO `sys_role_res` VALUES (1, 10000017, '2020-12-04 15:25:01');
COMMIT;

-- ----------------------------
-- Table structure for sys_station
-- ----------------------------
DROP TABLE IF EXISTS `sys_station`;
CREATE TABLE `sys_station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_code` varchar(20) DEFAULT '' COMMENT '租户编码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `org_id` bigint(20) DEFAULT '0' COMMENT '组织ID\n#c_core_org',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_name` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_name` varchar(255) DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1012128 DEFAULT CHARSET=utf8mb4 COMMENT='岗位';

-- ----------------------------
-- Records of sys_station
-- ----------------------------
BEGIN;
INSERT INTO `sys_station` VALUES (100, '0000', '总经理', 10008, b'1', '总部-1把手', '2019-07-10 17:03:03', NULL, 1, 2, '不告诉你', '2020-12-02 06:29:26');
INSERT INTO `sys_station` VALUES (101, '0000', '副总经理', 10001, b'1', '总部-2把手', '2019-07-22 17:07:55', NULL, 1, 3, NULL, '2020-10-29 07:33:50');
INSERT INTO `sys_station` VALUES (103, '0000', '研发经理', 10002, b'1', '子公司-研发部老大', '2019-11-07 16:08:49', NULL, 3, 3, NULL, '2020-10-29 07:34:33');
INSERT INTO `sys_station` VALUES (104, '0000', '副总经理', 101, b'1', '子公司-老大', '2019-11-16 09:51:45', NULL, 3, 3, NULL, '2020-10-29 07:34:35');
INSERT INTO `sys_station` VALUES (106, '0000', '产品经理', 10002, b'1', '子公司-产品部老大', '2019-11-16 09:53:27', NULL, 3, 3, NULL, '2020-10-29 07:34:36');
INSERT INTO `sys_station` VALUES (107, '0000', '人事经理', 10002, b'1', '子公司-综合老大', '2019-11-16 09:54:43', NULL, 3, 3, NULL, '2020-10-29 07:34:38');
INSERT INTO `sys_station` VALUES (108, '0000', 'Java工程师', 10007, b'1', '普通员工', '2019-11-16 09:55:04', NULL, 3, 3, NULL, '2020-10-29 07:34:39');
INSERT INTO `sys_station` VALUES (109, '0000', '需求工程师', 10007, b'1', '普通员工', '2019-11-16 09:55:27', NULL, 3, 3, NULL, '2020-10-29 07:34:41');
INSERT INTO `sys_station` VALUES (1011, '0000', 'UI工程师', 10007, b'1', '普通员工', '2019-11-16 09:55:40', NULL, 3, 3, NULL, '2020-10-29 07:34:43');
INSERT INTO `sys_station` VALUES (1012, '0000', '运维工程师', 10007, b'1', '普通员工', '2019-11-16 09:55:53', NULL, 3, 3, NULL, '2020-10-29 07:34:46');
INSERT INTO `sys_station` VALUES (1013, '0000', '前台小姐姐', 10007, b'1', '普通员工', '2019-11-16 09:56:04', NULL, 3, 3, NULL, '2020-10-29 07:34:47');
INSERT INTO `sys_station` VALUES (1014, '0000', '人事经理', 10007, b'1', '北京分公司-综合部老大', '2019-11-16 09:56:38', NULL, 3, 3, NULL, '2020-10-29 07:34:49');
INSERT INTO `sys_station` VALUES (1015, '0000', '研发经理', 10007, b'1', '北京分公司-研发部老大', '2019-11-16 09:57:07', NULL, 3, 3, NULL, '2020-10-29 07:34:51');
INSERT INTO `sys_station` VALUES (1016, '0000', '销售经理', 10007, b'1', '北京销售部老大', '2019-11-16 09:57:40', NULL, 3, 3, NULL, '2020-10-29 07:34:54');
INSERT INTO `sys_station` VALUES (1017, '0000', '行政', 10007, b'1', '普通员工', '2019-11-16 09:57:59', NULL, 3, 3, NULL, '2020-10-29 07:34:55');
INSERT INTO `sys_station` VALUES (1018, '0000', '大前端工程师', 10007, b'1', '普通员工', '2019-11-16 09:58:11', NULL, 3, 3, NULL, '2020-10-29 07:34:58');
INSERT INTO `sys_station` VALUES (10121, '0000', '销售总监', 10007, b'1', '总部2把手', '2019-11-16 09:59:10', NULL, 3, 3, NULL, '2020-10-29 07:35:02');
INSERT INTO `sys_station` VALUES (10122, '0000', '销售员工', 10007, b'1', '普通员工', '2019-11-16 09:58:41', NULL, 3, 3, NULL, '2020-10-29 07:35:01');
INSERT INTO `sys_station` VALUES (10123, '0000', '财务总监', 10007, b'1', '总部2把手', '2019-11-16 09:59:39', NULL, 3, 3, NULL, '2020-10-29 07:35:04');
INSERT INTO `sys_station` VALUES (10124, '0000', '市场经理', 10007, b'1', '总部市场部老大', '2019-11-16 10:00:03', NULL, 3, 3, NULL, '2020-10-29 07:35:05');
INSERT INTO `sys_station` VALUES (10125, '0000', '销售总监', 10007, b'1', '总部销售部老大', '2019-11-16 10:00:21', NULL, 3, NULL, NULL, '2020-10-29 07:35:07');
INSERT INTO `sys_station` VALUES (10128, '0000', '前端工程师', 100, b'1', '普通员工', '2019-11-16 10:00:43', NULL, 3, NULL, NULL, '2020-10-29 07:35:09');
INSERT INTO `sys_station` VALUES (1012127, '', '测试', 10001, b'1', '组合部门组合部门', '2020-11-04 10:31:12', '不告诉你', 2, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_station_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_station_message`;
CREATE TABLE `sys_station_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `title` varchar(64) DEFAULT NULL COMMENT '名称',
  `content` varchar(1024) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `mark` bit(1) DEFAULT b'0' COMMENT '状态（0=未读 1=已读）',
  `receive_id` bigint(20) DEFAULT NULL COMMENT '接收人ID',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COMMENT='站内消息';

-- ----------------------------
-- Records of sys_station_message
-- ----------------------------
BEGIN;
INSERT INTO `sys_station_message` VALUES (1, 'success', '测试消息1', NULL, '公告', b'1', 2, 0, NULL, '2020-11-18 09:24:44', 2, '不告诉你', '2020-11-19 11:26:23');
INSERT INTO `sys_station_message` VALUES (119, 'success', '123213', '<p>123213213</p>', NULL, b'0', 2, 2, '不告诉你', '2020-11-19 11:31:31', NULL, NULL, '2020-11-19 11:33:19');
INSERT INTO `sys_station_message` VALUES (121, 'success', '123123', '<p>111</p>', NULL, b'1', 1, 2, '不告诉你', '2020-11-20 02:32:10', 2, '不告诉你', '2020-11-20 02:33:46');
INSERT INTO `sys_station_message` VALUES (122, 'success', '123123', '<p>111</p>', NULL, b'0', 7, 2, '不告诉你', '2020-11-20 02:32:10', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (123, 'success', '123123', '<p>111</p>', NULL, b'0', 5, 2, '不告诉你', '2020-11-20 02:32:10', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (124, 'success', '123', '<p>123</p>', NULL, b'1', 1, 2, '不告诉你', '2020-11-27 06:24:32', 2, '不告诉你', '2020-11-27 08:00:39');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  UNIQUE KEY `role_id` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `t_gateway_route`;
CREATE TABLE `t_gateway_route` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `route_id` varchar(64) NOT NULL COMMENT '路由ID',
  `uri` varchar(200) DEFAULT NULL COMMENT '企业邮箱',
  `order` tinyint(4) DEFAULT '0' COMMENT '排序',
  `predicates` varchar(512) DEFAULT NULL COMMENT '谓语条件',
  `filters` varchar(512) DEFAULT NULL COMMENT '过滤器',
  `locked` bit(1) DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_route_id` (`route_id`) USING BTREE COMMENT '路由ID唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网关路由表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(30) NOT NULL COMMENT '账号',
  `password` varchar(64) DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID\n#c_core_org\n@InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.github.zuihou.authority.entity.core.Org>',
  `station_id` bigint(20) DEFAULT NULL COMMENT '岗位ID\n#c_core_station\n@InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>',
  `readonly` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否内置',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证',
  `sex` tinyint(1) DEFAULT '1' COMMENT '性别\n#Sex{W:女;M:男;N:未知}',
  `status` bit(1) DEFAULT b'0' COMMENT '状态 \n1启用 0禁用',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `nation` varchar(20) DEFAULT NULL COMMENT '民族\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION) RemoteData<String, String>\n',
  `education` varchar(20) DEFAULT NULL COMMENT '学历\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION) RemoteData<String, String>',
  `position_status` varchar(20) DEFAULT NULL COMMENT '职位状态\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.POSITION_STATUS) RemoteData<String, String>',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_ACCOUNT` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 'memmsc2', '', '唐亚峰3', 10001, 101, b'0', '1837307557@qq.com', '13002171912', '111111111', 1, b'0', 'https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/10/13/79584221289944.jpg', '描述信息吧', 'mz_daiz', 'SUOSHI', 'WORKING', '2020-11-05', 0, NULL, '2020-10-16 03:25:36', 2, '不告诉你', '2020-11-19 09:20:17');
INSERT INTO `t_user` VALUES (5, 'memmsc5', '', '唐亚峰5', 10001, 100, b'0', '1837307557@qq.com', '13002171912', '111111111', 1, b'0', 'https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/10/13/79584221289944.jpg', '描述信息吧', 'mz_acz', 'COLLEGE', 'LEAVE', '2020-11-05', 0, NULL, '2020-10-16 03:26:18', 2, '不告诉你', '2020-11-05 02:55:50');
INSERT INTO `t_user` VALUES (7, 'memmsc7', '', '唐亚峰7', 100, 10128, b'0', '1837307557@qq.com', '13002171912', '111111111', 1, b'0', 'https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/10/13/79584221289944.jpg', '描述信息吧', 'mz_daz', 'COLLEGE', 'LEAVE', '2020-11-05', 0, NULL, '2020-10-16 06:10:49', 2, '不告诉你', '2020-11-05 02:55:51');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
