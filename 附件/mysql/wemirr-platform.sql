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

 Date: 10/08/2021 10:32:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置角色',
  `sequence` tinyint(3) DEFAULT NULL COMMENT '排序',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型';

-- ----------------------------
-- Records of common_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `common_dictionary` VALUES (1, 'NATION', '民族', '123', b'1', b'1', 0, 0, '系统管理员', '2019-06-01 09:42:50', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (2, 'POSITION_STATUS', '在职状态', '11', b'1', b'1', 1, 0, '系统管理员', '2019-06-04 11:37:15', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (3, 'EDUCATION', '学历', '', b'1', b'1', 2, 0, '系统管理员', '2019-06-04 11:33:52', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (4, 'AREA_LEVEL', '行政区级', '', b'1', b'1', 3, 0, '系统管理员', '2020-01-20 15:12:05', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (5, 'ORG_TYPE', '机构类型', '', b'1', b'1', 4, 0, '系统管理员', '2020-08-19 15:02:57', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (6, 'SEX', '性别', '性别', b'1', b'1', 5, 0, '系统管理员', '2020-11-18 07:24:43', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (7, 'NOTICE', '消息类型', '消息类型', b'1', b'1', 6, 0, '系统管理员', '2020-11-19 02:57:22', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (8, 'STATION_TYPE', '岗位类型', NULL, b'1', b'1', 7, 0, '系统管理员', '2021-07-16 03:34:04', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (9, 'COLOR', '颜色', NULL, b'1', b'1', 8, 0, '系统管理员', '2021-07-16 04:12:21', 0, '系统管理员', '2021-07-31 08:11:09');
INSERT INTO `common_dictionary` VALUES (10, 'INDUSTRY', '行业类型', '行业类型', b'1', b'1', 9, 0, '系统管理员', '2021-07-31 08:10:07', 0, '系统管理员', '2021-07-31 08:20:24');
COMMIT;

-- ----------------------------
-- Table structure for common_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `common_dictionary_item`;
CREATE TABLE `common_dictionary_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dictionary_id` bigint(20) NOT NULL COMMENT '类型ID',
  `dictionary_code` varchar(64) NOT NULL COMMENT '类型',
  `value` varchar(64) NOT NULL DEFAULT '' COMMENT '编码',
  `label` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `color` varchar(255) DEFAULT NULL COMMENT '颜色',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `sequence` int(11) DEFAULT '1' COMMENT '排序',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dict_code_item_code_uniq` (`dictionary_code`,`value`) USING BTREE COMMENT '字典编码与字典项目编码联合唯一'
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COMMENT='字典项';

-- ----------------------------
-- Records of common_dictionary_item
-- ----------------------------
BEGIN;
INSERT INTO `common_dictionary_item` VALUES (1, 4, 'AREA_LEVEL', 'COUNTRY', '国家', b'1', 'warning', '', 1, 0, '系统管理员', '2020-01-20 15:12:57', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (2, 4, 'AREA_LEVEL', 'PROVINCE', '省份', b'1', 'warning', '', 2, 0, '系统管理员', '2020-01-20 15:13:45', 1, '长风一梦', '2021-07-31 08:18:51');
INSERT INTO `common_dictionary_item` VALUES (3, 4, 'AREA_LEVEL', 'CITY', '地市', b'1', 'warning', '', 3, 0, '系统管理员', '2020-01-20 15:14:16', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (4, 4, 'AREA_LEVEL', 'COUNTY', '区县', b'1', 'warning', '', 4, 0, '系统管理员', '2020-01-20 15:14:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (5, 5, 'ORG_TYPE', '01', '单位', b'1', 'warning', '', 1, 0, '系统管理员', '2020-08-19 15:03:40', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (6, 5, 'ORG_TYPE', '02', '部门', b'1', 'warning', '', 1, 0, '系统管理员', '2020-08-19 15:03:59', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (38, 3, 'EDUCATION', 'ZHUANKE', '专科', b'1', 'warning', '', 4, 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (39, 3, 'EDUCATION', 'COLLEGE', '本科', b'1', 'warning', '', 5, 0, '系统管理员', '2019-06-04 11:36:19', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (40, 3, 'EDUCATION', 'SUOSHI', '硕士', b'1', 'warning', '', 6, 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (41, 3, 'EDUCATION', 'BOSHI', '博士', b'1', 'warning', '', 7, 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (42, 3, 'EDUCATION', 'BOSHIHOU', '博士后', b'1', 'warning', '', 8, 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (43, 1, 'NATION', 'mz_hanz', '汉族', b'1', 'warning', '', 0, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (44, 1, 'NATION', 'mz_zz', '壮族', b'1', 'warning', '', 1, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (45, 1, 'NATION', 'mz_mz', '满族', b'1', 'warning', '', 2, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (46, 1, 'NATION', 'mz_hz', '回族', b'1', 'warning', '', 3, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (47, 1, 'NATION', 'mz_miaoz', '苗族', b'1', 'warning', '', 4, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (48, 1, 'NATION', 'mz_wwez', '维吾尔族', b'1', 'warning', '', 5, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (49, 1, 'NATION', 'mz_tjz', '土家族', b'1', 'warning', '', 6, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (50, 1, 'NATION', 'mz_yz', '彝族', b'1', 'warning', '', 7, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (51, 1, 'NATION', 'mz_mgz', '蒙古族', b'1', 'warning', '', 8, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (52, 1, 'NATION', 'mz_zhangz', '藏族', b'1', 'warning', '', 9, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (53, 1, 'NATION', 'mz_byz', '布依族', b'1', 'warning', '', 10, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (54, 1, 'NATION', 'mz_dz', '侗族', b'1', 'warning', '', 11, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (55, 1, 'NATION', 'mz_yaoz', '瑶族', b'1', 'warning', '', 12, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (56, 1, 'NATION', 'mz_cxz', '朝鲜族', b'1', 'warning', '', 13, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (57, 1, 'NATION', 'mz_bz', '白族', b'1', 'warning', '', 14, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (58, 1, 'NATION', 'mz_hnz', '哈尼族', b'1', 'warning', '', 15, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (59, 1, 'NATION', 'mz_hskz', '哈萨克族', b'1', 'warning', '', 16, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (60, 1, 'NATION', 'mz_lz', '黎族', b'1', 'warning', '', 17, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (61, 1, 'NATION', 'mz_daiz', '傣族', b'1', 'warning', '', 18, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (62, 1, 'NATION', 'mz_sz', '畲族', b'1', 'warning', '', 19, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (63, 1, 'NATION', 'mz_llz', '傈僳族', b'1', 'warning', '', 20, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (64, 1, 'NATION', 'mz_glz', '仡佬族', b'1', 'warning', '', 21, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (65, 1, 'NATION', 'mz_dxz', '东乡族', b'1', 'warning', '', 22, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (66, 1, 'NATION', 'mz_gsz', '高山族', b'1', 'warning', '', 23, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (67, 1, 'NATION', 'mz_lhz', '拉祜族', b'1', 'warning', '', 24, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (68, 1, 'NATION', 'mz_shuiz', '水族', b'1', 'warning', '', 25, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (69, 1, 'NATION', 'mz_wz', '佤族', b'1', 'warning', '', 26, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (70, 1, 'NATION', 'mz_nxz', '纳西族', b'1', 'warning', '', 27, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (71, 1, 'NATION', 'mz_qz', '羌族', b'1', 'warning', '', 28, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (72, 1, 'NATION', 'mz_tz', '土族', b'1', 'warning', '', 29, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (73, 1, 'NATION', 'mz_zlz', '仫佬族', b'1', 'warning', '', 30, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (74, 1, 'NATION', 'mz_xbz', '锡伯族', b'1', 'warning', '', 31, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (75, 1, 'NATION', 'mz_kehzz', '柯尔克孜族', b'1', 'warning', '', 32, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (76, 1, 'NATION', 'mz_dwz', '达斡尔族', b'1', 'warning', '', 33, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (77, 1, 'NATION', 'mz_jpz', '景颇族', b'1', 'warning', '', 34, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (78, 1, 'NATION', 'mz_mlz', '毛南族', b'1', 'warning', '', 35, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (79, 1, 'NATION', 'mz_slz', '撒拉族', b'1', 'warning', '', 36, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (80, 1, 'NATION', 'mz_tjkz', '塔吉克族', b'1', 'warning', '', 37, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (81, 1, 'NATION', 'mz_acz', '阿昌族', b'1', 'warning', '', 38, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (82, 1, 'NATION', 'mz_pmz', '普米族', b'1', 'warning', '', 39, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (83, 1, 'NATION', 'mz_ewkz', '鄂温克族', b'1', 'warning', '', 40, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (84, 1, 'NATION', 'mz_nz', '怒族', b'1', 'warning', '', 41, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (85, 1, 'NATION', 'mz_jz', '京族', b'1', 'warning', '', 42, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (86, 1, 'NATION', 'mz_jnz', '基诺族', b'1', 'warning', '', 43, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (87, 1, 'NATION', 'mz_daz', '德昂族', b'1', 'warning', '', 44, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (88, 1, 'NATION', 'mz_baz', '保安族', b'1', 'warning', '', 45, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (89, 1, 'NATION', 'mz_elsz', '俄罗斯族', b'1', 'warning', '', 46, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (90, 1, 'NATION', 'mz_ygz', '裕固族', b'1', 'warning', '', 47, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (91, 1, 'NATION', 'mz_wzbkz', '乌兹别克族', b'1', 'warning', '', 48, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (92, 1, 'NATION', 'mz_mbz', '门巴族', b'1', 'warning', '', 49, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (93, 1, 'NATION', 'mz_elcz', '鄂伦春族', b'1', 'warning', '', 50, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (94, 1, 'NATION', 'mz_dlz', '独龙族', b'1', 'warning', '', 51, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (95, 1, 'NATION', 'mz_tkez', '塔塔尔族', b'1', 'warning', '', 52, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (96, 1, 'NATION', 'mz_hzz', '赫哲族', b'1', 'warning', '', 53, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (97, 1, 'NATION', 'mz_lbz', '珞巴族', b'1', 'warning', '', 54, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (98, 1, 'NATION', 'mz_blz', '布朗族', b'1', 'warning', '', 55, 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (99, 2, 'POSITION_STATUS', 'WORKING', '在职', b'1', 'warning', '', 1, 0, '系统管理员', '2019-06-04 11:38:16', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (100, 2, 'POSITION_STATUS', 'QUIT', '离职', b'1', 'warning', '', 2, 0, '系统管理员', '2019-06-04 11:38:50', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (101, 4, 'AREA_LEVEL', 'TOWNS', '乡镇', b'1', 'warning', '', 5, 0, '系统管理员', '2020-03-09 23:33:46', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (102, 3, 'EDUCATION', 'XIAOXUE', '小学', b'1', 'warning', '', 1, 0, '系统管理员', '2020-03-09 23:34:13', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (103, 3, 'EDUCATION', 'ZHONGXUE', '中学', b'1', 'warning', '', 2, 0, '系统管理员', '2020-03-09 23:34:32', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (104, 3, 'EDUCATION', 'GAOZHONG', '高中', b'1', 'warning', '', 3, 0, '系统管理员', '2020-03-09 23:34:40', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (105, 3, 'EDUCATION', 'QITA', '其他', b'1', 'warning', '', 20, 0, '系统管理员', '2020-03-09 23:34:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (106, 1, 'NATION', 'mz_qt', '其他', b'1', 'warning', '', 100, 0, '系统管理员', '2020-03-09 23:38:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (107, 2, 'POSITION_STATUS', 'LEAVE', '请假', b'1', 'warning', '', 3, 0, '系统管理员', '2020-03-09 23:39:30', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (108, 6, 'SEX', '1', '男', b'1', 'success', '男', 1, 0, '系统管理员', '2020-11-18 07:24:58', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (109, 6, 'SEX', '2', '女', b'1', 'error', '女', 1, 0, '系统管理员', '2020-11-18 07:25:07', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (110, 7, 'NOTICE', '0', '通知', b'1', 'success', '通知', 1, 0, '系统管理员', '2020-11-19 02:58:37', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (111, 7, 'NOTICE', '1', '消息', b'1', 'success', '消息', 1, 0, '系统管理员', '2020-11-19 02:58:55', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (112, 7, 'NOTICE', '2', '待办', b'1', 'error', '待办', 1, 0, '系统管理员', '2020-11-19 02:59:05', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (113, 8, 'STATION_TYPE', '0', '基层', b'1', 'success', '1111', 1, 0, '系统管理员', '2021-07-16 04:06:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (114, 9, 'COLOR', 'success', '成功', b'1', 'success', '成功', 1, 0, '系统管理员', '2021-07-16 04:12:47', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (115, 9, 'COLOR', 'warning', '警告', b'1', 'warning', '警告', 1, 0, '系统管理员', '2021-07-16 04:15:06', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (116, 9, 'COLOR', 'error', '错误', b'1', 'error', '红色', 1, 0, '系统管理员', '2021-07-16 04:16:55', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (118, 8, 'STATION_TYPE', '1', '中层', b'1', 'success', '中层', 1, 0, '系统管理员', '2021-07-16 04:59:51', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (119, 8, 'STATION_TYPE', '2', '高层', b'1', 'warning', '高层', 1, 0, '系统管理员', '2021-07-16 05:00:07', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `common_dictionary_item` VALUES (120, 10, 'INDUSTRY', '1', '医疗', b'1', 'success', '', 1, 1, '长风一梦', '2021-07-31 08:17:08', NULL, NULL, '2021-07-31 08:20:13');
INSERT INTO `common_dictionary_item` VALUES (121, 10, 'INDUSTRY', '2', '教育', b'1', 'success', '', 1, 1, '长风一梦', '2021-07-31 08:17:17', NULL, NULL, '2021-07-31 08:20:14');
INSERT INTO `common_dictionary_item` VALUES (122, 10, 'INDUSTRY', '3', '金融', b'1', 'success', '', 1, 1, '长风一梦', '2021-07-31 08:17:28', NULL, NULL, '2021-07-31 08:20:17');
INSERT INTO `common_dictionary_item` VALUES (123, 10, 'INDUSTRY', '4', '互联网', b'1', 'warning', '', 1, 1, '长风一梦', '2021-07-31 08:17:40', 1, '长风一梦', '2021-07-31 08:20:17');
INSERT INTO `common_dictionary_item` VALUES (124, 10, 'INDUSTRY', '5', '电商', b'1', 'warning', '', 1, 1, '长风一梦', '2021-07-31 08:17:58', 1, '长风一梦', '2021-07-31 08:20:18');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Records of common_login_log
-- ----------------------------
BEGIN;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

-- ----------------------------
-- Records of common_opt_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `client_id` varchar(32) NOT NULL COMMENT '客户端ID',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '客户端秘钥',
  `status` bit(1) DEFAULT b'1' COMMENT '应用状态',
  `type` tinyint(2) DEFAULT '0' COMMENT '应用类型（0=综合应用,1=服务应用,2=PC网页,3=手机网页,4=小程序）',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '微服务应用名（暂时不建议用）',
  `client_name` varchar(255) DEFAULT NULL COMMENT '客户端名称',
  `scope` varchar(256) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '认证类型',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT 'web服务站点',
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT '43200' COMMENT 'token 有效期默认12小时',
  `refresh_token_validity` int(11) DEFAULT '604800' COMMENT 'refresh token  有效期默认7天',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '附加信息',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '自动审批',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='终端信息表';

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client_details` VALUES ('client', 'client', b'1', 0, NULL, '我是客户端', 'server', 'password,client_credentials,authorization_code', NULL, NULL, 86400, 604800, NULL, NULL);
INSERT INTO `sys_oauth_client_details` VALUES ('test', 'test', b'1', 0, NULL, '测试客户端', 'server', 'password,refresh_token,client_credentials,authorization_code', NULL, NULL, 86400, 604800, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `alias` varchar(255) DEFAULT '' COMMENT '简称',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `tree_path` varchar(255) DEFAULT ',' COMMENT '所有父级ID',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父ID',
  `sequence` int(11) DEFAULT '1' COMMENT '排序',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10205 DEFAULT CHARSET=utf8mb4 COMMENT='组织';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` VALUES (100, '鏖战八方', 1, '鏖战', '0746-8485560转1', ',', 0, 1, b'1', '初始化数据', 1, '1', '2019-07-10 17:02:18', 1, '1', '2021-07-20 06:26:04');
INSERT INTO `sys_org` VALUES (10001, '鏖战八方上海分部', 1, '鏖战上海', '0746-8485560转2', ',100', 100, 0, b'1', '初始化数据', 1, '1', '2019-08-06 09:10:53', 1, '1', '2021-07-20 06:26:05');
INSERT INTO `sys_org` VALUES (10002, '鏖战八方北京分部', 1, '鏖战北京', '0746-8485560转3', ',100', 100, 1, b'1', '初始化数据', 1, '1', '2019-11-07 16:13:09', 1, '1', '2021-07-20 06:26:05');
INSERT INTO `sys_org` VALUES (10003, '管理层', 1, '管理层', '0746-8485560转4', ',100', 100, 3, b'1', '初始化数据', 3, '3', '2019-11-12 11:34:39', 3, '3', '2021-07-20 06:26:06');
INSERT INTO `sys_org` VALUES (10004, '总经办', 1, '', '0746-8485560', ',100', 100, 2, b'1', '初始化数据', 3, '3', '2019-11-12 11:35:37', 1, '长风一梦', '2021-07-20 06:26:06');
INSERT INTO `sys_org` VALUES (10005, '财务部', 1, '', NULL, ',100', 100, 4, b'1', '初始化数据', 3, '3', '2019-11-12 11:37:17', 3, '3', '2021-07-20 06:26:06');
INSERT INTO `sys_org` VALUES (10006, '市场部', 1, '', NULL, ',100', 100, 5, b'1', '初始化数据', 3, '3', '2019-11-12 11:37:37', 3, '3', '2021-07-20 06:26:06');
INSERT INTO `sys_org` VALUES (10101, '综合部', 1, '综合部', '0746-8485560转7', ',100,10001', 10001, 0, b'1', '前台&HR', 3, '3', '2019-11-12 11:34:27', 1, '长风一梦', '2021-07-20 06:26:07');
INSERT INTO `sys_org` VALUES (10102, '研发部', 1, '研发部', '0746-8485560转5', ',100,10001', 10001, 5, b'1', '初始化数据', 3, '3', '2019-11-12 11:38:21', 2, '不告诉你', '2021-07-20 06:26:11');
INSERT INTO `sys_org` VALUES (10103, '产品部', 1, '产品部', '0746-8485560转6', ',100,10001', 10001, 2, b'0', '初始化数据', 3, '3', '2019-11-12 11:38:31', 2, '不告诉你', '2021-07-20 06:26:07');
INSERT INTO `sys_org` VALUES (10201, '综合部', 1, '综合部', NULL, ',100,10002', 10002, 0, b'1', '初始化数据', 3, '3', '2019-11-12 11:38:39', 3, '3', '2021-07-20 06:26:07');
INSERT INTO `sys_org` VALUES (10202, '测试部', 1, '测试部', NULL, ',100,10002', 10002, 1, b'1', '初始化数据', 2, '不告诉你', '2020-10-29 06:39:09', 2, '不告诉你', '2021-07-20 07:13:59');
INSERT INTO `sys_org` VALUES (10203, '研发部', 1, '', NULL, ',100,10002', 10002, 0, b'1', '初始化数据', 3, '3', '2019-11-12 11:38:50', 3, '3', '2021-07-20 07:14:00');
INSERT INTO `sys_org` VALUES (10204, '销售部', 1, '', NULL, ',100,10002', 10002, 2, b'1', '初始化数据', 3, '3', '2019-11-12 11:39:00', 3, '3', '2021-07-20 07:14:01');
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `tree_path` varchar(500) DEFAULT ',' COMMENT '该节点的所有父节点',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单ID',
  `path` varchar(255) DEFAULT '' COMMENT '路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `sequence` int(11) DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `style` varchar(255) DEFAULT NULL COMMENT '样式',
  `type` tinyint(2) DEFAULT '1' COMMENT '类型（1=菜单;2=按钮）',
  `status` bit(1) DEFAULT b'1' COMMENT '1=启用;0=禁用',
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置菜单（0=否;1=是）',
  `global` bit(1) DEFAULT b'0' COMMENT '公共资源\nTrue是无需分配所有人就可以访问的',
  `display` bit(1) DEFAULT b'1' COMMENT '0=隐藏;1=显示',
  `description` varchar(200) DEFAULT '' COMMENT '描述',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `INX_STATUS` (`global`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1030104 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES (1, '系统管理', ',', 'system:view', 0, '/system/management', 'Layout', 1, 'ant-design:dashboard-filled', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 09:39:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (2, '租户中心', ',', 'tenant:view', 0, '/tenant', 'Layout', 2, 'clarity-thin-client-line', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-08-06 09:26:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20, '资源中心', ',', 'resource:view', 0, '/resource', 'Layout', 20, 'carbon-group-resource', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-08-03 05:47:50', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (30, '消息平台', ',', NULL, 0, '/message', 'Layout', 30, 'bx-bx-message-alt-dots', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-02 09:48:51', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (50, '开发平台', ',', 'development:view', 0, '/development', 'Layout', 50, 'ant-design:appstore-add-outlined', NULL, 1, b'1', b'0', b'0', b'1', '运维监控', NULL, NULL, '2020-11-09 09:49:52', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (101, '用户中心', ',1', 'user:info:view', 1, '/system/center', 'Layout', 0, 'ant-design:user-outlined', NULL, 1, b'1', b'0', b'0', b'1', '用户中心', 1, NULL, '2019-07-25 16:11:00', 2, '不告诉你', '2019-11-11 14:28:40');
INSERT INTO `sys_resource` VALUES (102, '权限管理', ',1', 'auth:view', 1, '/system/auth', NULL, 1, 'ant-design:security-scan-outlined', NULL, 1, b'1', b'0', b'0', b'1', '权限管理', 1, NULL, '2019-07-25 16:11:41', 3, NULL, '2019-11-11 14:28:43');
INSERT INTO `sys_resource` VALUES (103, '基础数据', ',1', 'basic:view', 1, '/system/basic', NULL, 2, 'file-icons:config-coffeescript', NULL, 1, b'1', b'0', b'0', b'1', '基础配置', 1, NULL, '2019-07-25 16:13:09', 2, '不告诉你', '2019-11-11 14:28:49');
INSERT INTO `sys_resource` VALUES (120, '日志管理', ',1', 'log:view', 1, '/development/log', NULL, 20, 'radix-icons:activity-log', NULL, 1, b'1', b'0', b'0', b'1', '日志管理', NULL, NULL, '2020-11-12 04:51:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (201, '租户管理', ',2', 'tenant:management', 2, '/tenant/index', '/wemirr/tenant/index', 0, 'clarity-thin-client-line', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-30 09:11:36', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (202, '连接管理', ',2', NULL, 2, '/tenant/database', '/wemirr/tenant/database/index', 1, 'ant-design:database-filled', NULL, 1, b'1', b'0', b'0', b'1', '', 1, '长风一梦', '2021-08-06 09:33:09', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (2001, '文件管理', ',20', 'resource:file', 20, '/resource/file', '/wemirr/resource/file/index', 1, 'akar-icons:file', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-08-03 05:49:21', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (3001, '我的消息', ',30', 'message:my', 30, '/message/my', '/wemirr/message/my/index', 1, 'wpf-my-topic', NULL, 1, b'1', b'0', b'0', b'1', '我的消息', NULL, NULL, '2020-11-19 02:50:54', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (3002, '发布消息', ',30', 'message:publish', 30, '/message/publish', '/wemirr/message/publish/index', 1, 'codicon-repo-push', NULL, 1, b'1', b'0', b'0', b'1', '发布消息', NULL, NULL, '2020-11-19 02:48:22', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (5002, '网关管理', ',50', 'system:gateway:view', 50, '/development/gateway', NULL, 2, 'ant-design:gateway-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 02:38:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (5030, '应用管理', ',50', 'application:management', 50, '/system/application', '/wemirr/management/application/index', 30, 'carbon-application', NULL, 1, b'1', b'0', b'0', b'1', '', 1, '长风一梦', '2021-07-30 09:10:07', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (5050, '监控中心', ',50', 'development:view', 50, '/system/monitor', 'http://localhost:9000/monitor', 50, 'eos-icons:monitoring', NULL, 1, b'1', b'0', b'0', b'1', '监控中心', NULL, NULL, '2020-11-09 09:55:04', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (5060, '注册中心', ',50', 'service:governance:nacos:view', 50, 'http://localhost:8848/nacos', '/system/development/nacos', 60, 'simple-icons:theregister', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 05:42:36', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (5070, '服务治理', ',50', 'nepxion:platform', 50, 'http://218.78.55.64:6001/platform', '', 70, 'ri-government-fill', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-12 08:09:34', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (10101, '用户管理', ',1,101', 'user:management', 101, '/system/user', '/wemirr/management/user/index', 1, 'heroicons-outline:user-group', NULL, 1, b'1', b'0', b'0', b'1', '用户管理', NULL, NULL, '2020-11-09 07:50:53', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (10102, '组织管理', ',1,101', 'org:view', 101, '/system/org', '/wemirr/management/org/index', 1, 'codicon-organization', NULL, 1, b'1', b'0', b'0', b'1', '组织管理', NULL, NULL, '2020-11-09 07:51:00', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10103, '岗位管理', ',1,101', 'station:management', 101, '/system/station', '/wemirr/management/station/index', 1, 'eos-icons:job', NULL, 1, b'1', b'0', b'0', b'1', '岗位管理', NULL, NULL, '2020-11-09 07:51:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10201, '菜单管理', ',1,102', 'menu:view', 102, '/system/menu', '/wemirr/management/auth/menu/index', 1, 'ant-design:menu-unfold-outlined', NULL, 1, b'1', b'0', b'0', b'1', '菜单管理', NULL, NULL, '2020-11-09 07:51:37', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (10202, '角色管理', ',1,102', 'role:management', 102, '/system/role', '/wemirr/management/auth/role/index', 1, 'eos-icons:role-binding-outlined', NULL, 1, b'1', b'0', b'0', b'1', '角色管理', NULL, NULL, '2020-11-09 07:51:41', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10301, '数据字典', ',1,103', 'dict:management', 103, '/system/dictionary', '/wemirr/management/basic/dictionary/index', 1, 'bx-bxs-data', NULL, 1, b'1', b'0', b'0', b'1', '数据字典', NULL, NULL, '2020-11-09 07:52:59', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10302, '地区信息', ',1,103', 'area:view', 103, '/system/area', '/wemirr/management/basic/area/index', 1, 'ant-design:area-chart-outlined', NULL, 1, b'1', b'0', b'0', b'1', '地区信息', NULL, NULL, '2020-11-09 07:53:12', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (12001, '登录日志', ',1,120', 'log:login', 120, '/development/log/login', '/wemirr/development/log/login/index', 1, 'carbon-login', NULL, 1, b'1', b'0', b'0', b'1', '登录日志', NULL, NULL, '2020-11-09 09:50:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (12002, '操作日志', ',1,120', 'log:opt', 120, '/development/log/opt', '/wemirr/development/log/opt/index', 2, 'carbon-operation', NULL, 1, b'1', b'0', b'0', b'1', '操作日志', NULL, NULL, '2020-11-09 09:50:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (500201, '限流规则', ',50,5002', 'development:gateway:limit', 5002, '/development/gateway/limit', '/wemirr/development/gateway/limit/index', 1, 'carbon-rule', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 02:38:51', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (500202, '限访名单', ',50,5002', 'development:gateway:blacklist', 5002, '/development/gateway/blacklist', '/wemirr/development/gateway/blacklist/index', 2, 'carbon-ai-status-rejected', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 02:39:02', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (500203, '网关路由', ',50,5002', 'development:gateway:route', 5002, '/development/gateway/route', '/wemirr/development/gateway/route/index', 3, 'mdi-router-wireless-settings', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-02-02 07:52:26', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010101, '添加', ',1,101,10101', 'user:management:add', 10101, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-05 10:12:11', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (1010102, '编辑', ',1,101,10101', 'user:management:edit', 10101, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-05 10:13:50', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (1010104, '删除', ',1,101,10101', 'user:management:remove', 10101, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-05 14:25:09', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (1010301, '添加', ',1,101,10103', 'station:management:add', 10103, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:46:49', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010302, '编辑', ',1,101,10103', 'station:management:edit', 10103, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:47:31', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010303, '删除', ',1,101,10103', 'station:management:remove', 10103, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:47:34', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020201, '添加', ',1,102,10202', 'role:management:add', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:56:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020202, '编辑', ',1,102,10202', 'role:management:edit', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:56:40', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020203, '删除', ',1,102,10202', 'role:management:remove', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:56:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020204, '分配用户', ',1,102,10202', 'role:management:distribution_user', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 10:02:27', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020205, '分配权限', ',1,102,10202', 'role:management:distribution_res', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 10:02:36', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030101, '添加', ',1,103,10301', 'dict:management:add', 10301, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:54:12', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030102, '编辑', ',1,103,10301', 'dict:management:edit', 10301, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:54:18', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030103, '删除', ',1,103,10301', 'dict:management:remove', 10301, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:54:21', NULL, NULL, NULL);
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
  `locked` tinyint(1) DEFAULT '0' COMMENT '0=正常1=禁用',
  `super` tinyint(1) DEFAULT '0' COMMENT '0=非 1=管理员',
  `readonly` tinyint(1) DEFAULT '0' COMMENT '是否内置角色',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 1, 'PLATFORM_ADMIN', '平台管理员', '平台管理员，拥有所有数据可视权', 50, 0, 1, 1, 0, '超级管理员', '2019-10-25 13:46:00', 57, NULL, '2021-07-15 06:38:17');
INSERT INTO `sys_role` VALUES (2, 1, 'TEST_ADMIN', '测试管理员', '测试管理员，测试老大', 20, 0, 0, 1, 0, '超级管理员', '2021-07-06 06:07:52', 0, NULL, '2021-07-15 06:38:15');
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
INSERT INTO `sys_role_org` VALUES (5, 100);
INSERT INTO `sys_role_org` VALUES (5, 101);
INSERT INTO `sys_role_org` VALUES (5, 102);
INSERT INTO `sys_role_org` VALUES (5, 10001);
INSERT INTO `sys_role_org` VALUES (5, 10005);
INSERT INTO `sys_role_org` VALUES (5, 10007);
INSERT INTO `sys_role_org` VALUES (5, 10009);
INSERT INTO `sys_role_org` VALUES (5, 10010);
INSERT INTO `sys_role_org` VALUES (5, 10011);
INSERT INTO `sys_role_org` VALUES (5, 10018);
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
INSERT INTO `sys_role_res` VALUES (1, 1, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 2, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 20, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 30, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 50, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 101, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 102, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 103, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 120, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 201, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 202, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 2001, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 3001, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 3002, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 5002, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 5030, '2021-08-10 02:31:23');
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
INSERT INTO `sys_role_res` VALUES (1, 12001, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 12002, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 500201, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 500202, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 500203, '2021-08-10 02:31:23');
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
COMMIT;

-- ----------------------------
-- Table structure for sys_station
-- ----------------------------
DROP TABLE IF EXISTS `sys_station`;
CREATE TABLE `sys_station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型',
  `sequence` tinyint(3) DEFAULT NULL COMMENT '排序',
  `org_id` bigint(20) DEFAULT '0' COMMENT '组织ID\n#c_core_org',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_name` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `last_modified_by` bigint(20) DEFAULT NULL,
  `last_modified_name` varchar(255) DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10129 DEFAULT CHARSET=utf8mb4 COMMENT='岗位';

-- ----------------------------
-- Records of sys_station
-- ----------------------------
BEGIN;
INSERT INTO `sys_station` VALUES (100, 1, '总经理', 'CEO', 2, 0, 100, b'1', '总部-1把手', '2019-07-10 17:03:03', NULL, 1, 1, '长风一梦', '2021-07-20 18:25:05');
INSERT INTO `sys_station` VALUES (101, 1, '副总经理', NULL, 2, 1, 10001, b'1', '总部-2把手', '2019-07-22 17:07:55', NULL, 1, 1, '长风一梦', '2021-07-28 02:58:53');
INSERT INTO `sys_station` VALUES (103, 1, '研发经理', NULL, 1, 1, 10002, b'1', '子公司-研发部老大', '2019-11-07 16:08:49', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:06');
INSERT INTO `sys_station` VALUES (104, 1, '副总经理', NULL, 2, 1, 10001, b'1', '子公司-老大', '2019-11-16 09:51:45', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:29');
INSERT INTO `sys_station` VALUES (106, 1, '产品经理', NULL, 0, 1, 10002, b'1', '子公司-产品部老大', '2019-11-16 09:53:27', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_station` VALUES (107, 1, '人事经理', NULL, 0, 1, 10002, b'1', '子公司-综合老大', '2019-11-16 09:54:43', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_station` VALUES (108, 1, 'Java研发', 'CTI', 0, 1, 10203, b'1', '普通员工', '2019-11-16 09:55:04', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:38');
INSERT INTO `sys_station` VALUES (1011, 1, 'UI工程师', 'CTI', 0, 1, 10007, b'1', '普通员工', '2019-11-16 09:55:40', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:08');
INSERT INTO `sys_station` VALUES (1012, 1, '运维工程师', 'CTI', NULL, 1, 10007, b'1', '普通员工', '2019-11-16 09:55:53', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:09');
INSERT INTO `sys_station` VALUES (1013, 1, '行政经理', NULL, 0, 1, 10003, b'1', '普通员工', '2019-11-16 09:56:04', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:09');
INSERT INTO `sys_station` VALUES (1014, 1, '人事经理', NULL, 0, 1, 10007, b'1', '北京分公司-综合部老大', '2019-11-16 09:56:38', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:09');
INSERT INTO `sys_station` VALUES (1015, 1, '研发经理', NULL, 1, 1, 10007, b'1', '北京分公司-研发部老大', '2019-11-16 09:57:07', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:10');
INSERT INTO `sys_station` VALUES (1016, 1, '销售经理', NULL, 0, 1, 10007, b'1', '北京销售部老大', '2019-11-16 09:57:40', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:10');
INSERT INTO `sys_station` VALUES (10121, 1, '销售总监', NULL, 1, 1, 10005, b'1', '总部2把手', '2019-11-16 09:59:10', NULL, 3, 1, '长风一梦', '2021-07-29 02:36:31');
INSERT INTO `sys_station` VALUES (10122, 1, '销售员工', NULL, 0, 1, 10007, b'1', '普通员工', '2019-11-16 09:58:41', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:11');
INSERT INTO `sys_station` VALUES (10123, 1, '财务总监', NULL, 2, 1, 10007, b'1', '总部2把手', '2019-11-16 09:59:39', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:11');
INSERT INTO `sys_station` VALUES (10124, 1, '市场经理', NULL, 0, 1, 10007, b'1', '总部市场部老大', '2019-11-16 10:00:03', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:11');
INSERT INTO `sys_station` VALUES (10128, 1, '前端工程师', 'CTI', 0, 1, 10007, b'1', '普通员工', '2019-11-16 10:00:43', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:13');
COMMIT;

-- ----------------------------
-- Table structure for sys_station_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_station_message`;
CREATE TABLE `sys_station_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `level` varchar(64) DEFAULT NULL COMMENT '消息级别',
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
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COMMENT='站内消息';

-- ----------------------------
-- Records of sys_station_message
-- ----------------------------
BEGIN;
INSERT INTO `sys_station_message` VALUES (1, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, 1, '管理员', '2021-07-12 20:05:39', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (5, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, 1, '管理员', '2021-07-13 09:26:59', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (7, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, 1, '管理员', '2021-07-13 09:26:59', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (9, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, 1, '管理员', '2021-07-13 09:26:59', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (16, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, 1, '管理员', '2021-07-13 09:27:06', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (18, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, 1, '管理员', '2021-07-13 09:27:11', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (28, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-13 09:28:06', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (34, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-13 10:01:48', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (37, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:33:41', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (39, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:33:41', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (41, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:33:42', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (49, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:33:56', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (51, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:33:56', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (53, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:33:57', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (59, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:51:42', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (61, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:51:42', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (63, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:51:43', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (71, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-14 17:51:52', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (72, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '管理员', '2021-07-15 20:10:36', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (76, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '长风一梦', '2021-07-21 15:43:40', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (78, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '长风一梦', '2021-07-21 15:43:40', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (80, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '长风一梦', '2021-07-21 15:43:40', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (83, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '长风一梦', '2021-07-21 15:43:44', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (87, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '长风一梦', '2021-07-21 15:43:48', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (89, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, 1, '长风一梦', '2021-07-22 10:59:20', NULL, NULL, NULL);
INSERT INTO `sys_station_message` VALUES (90, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'1', 1, 1, '长风一梦', '2021-07-22 10:59:20', 1, '长风一梦', '2021-07-27 10:51:14');
COMMIT;

-- ----------------------------
-- Table structure for sys_station_message_publish
-- ----------------------------
DROP TABLE IF EXISTS `sys_station_message_publish`;
CREATE TABLE `sys_station_message_publish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `level` varchar(255) DEFAULT NULL COMMENT '消息级别',
  `status` tinyint(1) DEFAULT NULL COMMENT '0=为发布;1=已发布',
  `type` varchar(64) DEFAULT NULL COMMENT '编码',
  `title` varchar(64) DEFAULT NULL COMMENT '名称',
  `receiver` varchar(255) DEFAULT NULL COMMENT '接受者ID',
  `content` varchar(1024) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='站内消息';

-- ----------------------------
-- Records of sys_station_message_publish
-- ----------------------------
BEGIN;
INSERT INTO `sys_station_message_publish` VALUES (2, 1, '1', 1, '1', '测试消息', '2,1', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', 1, '管理员', '2021-07-12 11:41:31', 1, '长风一梦', '2021-07-21 07:43:39');
INSERT INTO `sys_station_message_publish` VALUES (3, 1, '0', 1, '1', '测试通知', '1', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', 1, '管理员', '2021-07-12 11:42:15', 1, '长风一梦', '2021-07-21 07:43:40');
INSERT INTO `sys_station_message_publish` VALUES (4, 2, '2', 0, '1', '测试待办', '1', '<p>测试待办</p><img src=\"http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/15/30369909559790.png\" contenteditable=\"false\"/>', '测试待办', 1, '管理员', '2021-07-12 11:42:26', 1, '管理员', '2021-07-20 07:30:23');
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
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (8, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_dynamic_datasource
-- ----------------------------
DROP TABLE IF EXISTS `t_dynamic_datasource`;
CREATE TABLE `t_dynamic_datasource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pool_name` varchar(100) DEFAULT NULL COMMENT '连接池名称',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `db_type` varchar(255) DEFAULT NULL COMMENT '数据库类型',
  `driver_class_name` varchar(255) DEFAULT NULL,
  `database` varchar(255) DEFAULT NULL COMMENT '数据库名称',
  `connection_type` tinyint(2) DEFAULT '0' COMMENT '连接类型（0=单库多schema ,1 = 单库单schema）',
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='动态数据源';

-- ----------------------------
-- Records of t_dynamic_datasource
-- ----------------------------
BEGIN;
INSERT INTO `t_dynamic_datasource` VALUES (1, 'TenantDataSourcePool', 'root', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', 'tenant-8888', 0, 'localhost:3306', 3306, 'tenant-8888', b'0', NULL, NULL, '2021-08-06 13:57:40', NULL, NULL, '2021-08-09 08:26:35');
COMMIT;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `content_type` varchar(100) DEFAULT NULL COMMENT '文件类型',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `location` varchar(50) DEFAULT NULL COMMENT '登录地点',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP',
  `engine` varchar(255) DEFAULT NULL COMMENT '引擎类型',
  `engine_version` varchar(255) DEFAULT NULL COMMENT '引擎版本',
  `os` varchar(255) DEFAULT NULL COMMENT '操作系统',
  `bucket` varchar(255) DEFAULT NULL COMMENT '操作系统',
  `origin_name` varchar(255) DEFAULT NULL COMMENT '原始名称',
  `target_name` varchar(255) DEFAULT NULL COMMENT '目标名称',
  `mapping_path` varchar(255) DEFAULT NULL COMMENT '映射地址',
  `full_url` varchar(255) DEFAULT NULL COMMENT '完整地址',
  `extend` varchar(255) DEFAULT NULL COMMENT '拓展字段',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_TARGET_NAME` (`target_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='文件';

-- ----------------------------
-- Records of t_file
-- ----------------------------
BEGIN;
INSERT INTO `t_file` VALUES (17, 1, 'image/jpeg', 98, '0|0|0|内网IP|内网IP', '127.0.0.1', '537.36', 'Webkit', 'OSX', 'battcn', '动物.jpg', '/20210804/61990178-5aac-4ba7-9f0b-1a52c36d8bed.jpg', 'http://qiniu.battcn.com', 'http://qiniu.battcn.com/20210804/61990178-5aac-4ba7-9f0b-1a52c36d8bed.jpg', NULL, 2, '不告诉你', '2021-08-04 03:03:20', NULL, NULL, NULL);
INSERT INTO `t_file` VALUES (18, 1, 'image/jpeg', 98, '0|0|0|内网IP|内网IP', '127.0.0.1', '537.36', 'Webkit', 'OSX', 'battcn', '动物.jpg', '/20210804/9178dfa8-6d57-4e01-856e-6952d3977a45.jpg', 'http://qiniu.battcn.com', 'http://qiniu.battcn.com/20210804/9178dfa8-6d57-4e01-856e-6952d3977a45.jpg', NULL, 2, '不告诉你', '2021-08-04 03:03:38', NULL, NULL, NULL);
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
-- Records of t_gateway_route
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant`;
CREATE TABLE `t_tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '租户编码',
  `name` varchar(64) NOT NULL COMMENT '租户名称',
  `type` tinyint(2) DEFAULT '0' COMMENT '0=其它,1=企业',
  `status` tinyint(2) DEFAULT '0' COMMENT '0=未认证,1=已认证',
  `alias` varchar(50) DEFAULT NULL COMMENT '简称',
  `logo` varchar(255) DEFAULT NULL COMMENT 'LOGO',
  `email` varchar(50) DEFAULT NULL COMMENT '租户邮箱',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(30) DEFAULT NULL COMMENT '联系人方式',
  `industry` varchar(255) DEFAULT NULL COMMENT '行业',
  `province_id` int(8) DEFAULT NULL COMMENT '省份',
  `province_name` varchar(64) DEFAULT NULL COMMENT '省份',
  `city_id` int(8) DEFAULT NULL COMMENT '市',
  `city_name` varchar(64) DEFAULT NULL COMMENT '市',
  `address` varchar(250) DEFAULT NULL COMMENT '详细地址',
  `district_id` int(8) DEFAULT NULL COMMENT '区县',
  `district_name` varchar(64) DEFAULT NULL COMMENT '区县',
  `credit_code` varchar(50) DEFAULT NULL COMMENT '统一信用代码',
  `legal_person_name` varchar(50) DEFAULT NULL COMMENT '法人',
  `web_site` varchar(200) DEFAULT NULL COMMENT '企业网址',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `locked` bit(1) DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='租户信息';

-- ----------------------------
-- Records of t_tenant
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant` VALUES (1, '8888', '深圳市腾讯计算机系统有限公司', 1, 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '888888@qq.com', '马化腾', '13002178888', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'https://cloud.battcn.com', NULL, b'0', 0, NULL, '2021-07-31 07:27:08', 1, '长风一梦', '2021-08-05 08:34:56');
INSERT INTO `t_tenant` VALUES (2, 'A002', '小米科技有限责任公司', 1, 1, '小米', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '666666@qq.com', '雷军', '13002176666', '4', 110000, '北京市', 110100, '市辖区', '北京市海淀区西二旗中路33号院6号楼6层006号 ', 110108, '海淀区', '91110108551385082Q', '雷军', 'https://cloud.battcn.com', NULL, b'0', 0, NULL, '2021-07-31 08:14:02', 1, '长风一梦', '2021-08-05 08:38:40');
INSERT INTO `t_tenant` VALUES (3, '0000', '个人租户', 0, 0, '租户', NULL, '1837307557@qq.com', '唐亚峰', '13002170000', '1', 310000, NULL, 310100, NULL, '漕河泾开发区0000号', 310104, NULL, NULL, NULL, NULL, NULL, b'0', 1, '长风一梦', '2021-08-01 08:10:51', NULL, NULL, '2021-08-09 02:44:17');
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_config
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_config`;
CREATE TABLE `t_tenant_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `dynamic_datasource_id` bigint(20) NOT NULL COMMENT '动态数据源ID',
  `created_by` bigint(20) DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='租户配置信息';

-- ----------------------------
-- Records of t_tenant_config
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_config` VALUES (1, 1, 1, 0, NULL, '2021-08-09 02:25:19', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `username` varchar(30) NOT NULL COMMENT '账号',
  `password` varchar(200) DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID\n#c_core_org\n@InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.github.zuihou.authority.entity.core.Org>',
  `station_id` bigint(20) DEFAULT NULL COMMENT '岗位ID\n#c_core_station\n@InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>',
  `readonly` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否内置',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证',
  `sex` tinyint(2) DEFAULT '1' COMMENT '性别\n#Sex{W:女;M:男;N:未知}',
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 3, 'admin', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '长风一梦8888', 100, 100, b'0', '1837307557@qq.com', '13002171912', '111111111', 1, b'1', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '12323', 'mz_daiz', 'SUOSHI', 'WORKING', '2020-11-05', 0, NULL, '2020-10-16 03:25:36', 1, '长风一梦', '2021-08-10 03:18:14');
COMMIT;


DROP TABLE IF EXISTS `common_generate`;
CREATE TABLE `common_generate` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `root_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '根目录',
    `swagger2` bit(2) DEFAULT b'0' COMMENT '是否添加swagger2',
    `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作者',
    `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表名',
    `parent_package` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父包',
    `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模块名',
    `table_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表前缀',
    `api_url_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'api地址前缀',
    `logic_delete_field` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '逻辑删除字段',
    `platform_id` bigint DEFAULT '0' COMMENT '平台ID',
    `created_by` bigint DEFAULT '0' COMMENT '创建人id',
    `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_modified_by` bigint DEFAULT NULL,
    `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成器';

BEGIN;
INSERT INTO `common_generate` (`id`, `root_dir`, `swagger2`, `author`, `table_name`, `parent_package`, `module_name`, `table_prefix`, `api_url_prefix`, `logic_delete_field`, `platform_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (6, NULL, b'01', 'Levin', 't_user', 'com.wemirr.platform', 'authority', 't_', '/api/users', 'locked', 1, 0, NULL, '2020-11-06 09:34:40', 2, '不告诉你', '2021-11-26 06:11:03');
INSERT INTO `common_generate` (`id`, `root_dir`, `swagger2`, `author`, `table_name`, `parent_package`, `module_name`, `table_prefix`, `api_url_prefix`, `logic_delete_field`, `platform_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (31, NULL, b'00', 'Levin', 't_user', 'com.wemirr.platform.authority', 'User', 't_', '/api/users', 'locked', 1, 2, '不告诉你', '2020-11-06 09:34:40', 2, '不告诉你', '2021-11-26 06:11:04');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
