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

 Date: 31/07/2021 11:40:41
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型';

-- ----------------------------
-- Records of common_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `common_dictionary` VALUES (1, 'NATION', '民族', '123', b'1', b'1', 0, 1, '1', '2019-06-01 09:42:50', 2, NULL, '2021-07-16 04:31:23');
INSERT INTO `common_dictionary` VALUES (2, 'POSITION_STATUS', '在职状态', '11', b'1', b'1', 1, 1, '1', '2019-06-04 11:37:15', 2, NULL, '2021-07-16 04:31:24');
INSERT INTO `common_dictionary` VALUES (3, 'EDUCATION', '学历', '', b'1', b'1', 2, 1, '1', '2019-06-04 11:33:52', 1, '1', '2021-07-16 04:31:25');
INSERT INTO `common_dictionary` VALUES (4, 'AREA_LEVEL', '行政区级', '', b'1', b'1', 3, 1, '1', '2020-01-20 15:12:05', 1, '1', '2021-07-16 04:31:26');
INSERT INTO `common_dictionary` VALUES (5, 'ORG_TYPE', '机构类型', '', b'1', b'1', 4, 1, '1', '2020-08-19 15:02:57', 1, '管理员', '2021-07-16 04:31:27');
INSERT INTO `common_dictionary` VALUES (6, 'SEX', '性别', '性别', b'1', b'1', 5, 2, '不告诉你', '2020-11-18 07:24:43', NULL, NULL, '2021-07-16 04:31:29');
INSERT INTO `common_dictionary` VALUES (7, 'NOTICE', '消息类型', '消息类型', b'1', b'1', 6, 2, '不告诉你', '2020-11-19 02:57:22', 2, '不告诉你', '2021-07-16 04:31:30');
INSERT INTO `common_dictionary` VALUES (8, 'STATION_TYPE', '岗位类型', NULL, b'1', b'1', 7, 1, '长风一梦', '2021-07-16 03:34:04', NULL, NULL, '2021-07-16 04:31:32');
INSERT INTO `common_dictionary` VALUES (9, 'COLOR', '颜色', NULL, b'1', b'1', 8, 1, '长风一梦', '2021-07-16 04:12:21', NULL, NULL, '2021-07-16 04:31:33');
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
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COMMENT='字典项';

-- ----------------------------
-- Records of common_dictionary_item
-- ----------------------------
BEGIN;
INSERT INTO `common_dictionary_item` VALUES (1, 4, 'AREA_LEVEL', 'COUNTRY', '国家', b'1', 'warning', '', 1, 3, NULL, '2020-01-20 15:12:57', 3, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (2, 4, 'AREA_LEVEL', 'PROVINCE', '省份/直辖市', b'1', 'warning', '', 2, 3, NULL, '2020-01-20 15:13:45', 3, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (3, 4, 'AREA_LEVEL', 'CITY', '地市', b'1', 'warning', '', 3, 3, NULL, '2020-01-20 15:14:16', 3, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (4, 4, 'AREA_LEVEL', 'COUNTY', '区县', b'1', 'warning', '', 4, 3, NULL, '2020-01-20 15:14:54', 3, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (5, 5, 'ORG_TYPE', '01', '单位', b'1', 'warning', '', 1, 1, NULL, '2020-08-19 15:03:40', 1, '管理员', '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (6, 5, 'ORG_TYPE', '02', '部门', b'1', 'warning', '', 1, 1, NULL, '2020-08-19 15:03:59', 1, '管理员', '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (38, 3, 'EDUCATION', 'ZHUANKE', '专科', b'1', 'warning', '', 4, 1, NULL, '2019-06-04 11:36:29', 1, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (39, 3, 'EDUCATION', 'COLLEGE', '本科', b'1', 'warning', '', 5, 1, NULL, '2019-06-04 11:36:19', 1, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (40, 3, 'EDUCATION', 'SUOSHI', '硕士', b'1', 'warning', '', 6, 1, NULL, '2019-06-04 11:36:29', 1, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (41, 3, 'EDUCATION', 'BOSHI', '博士', b'1', 'warning', '', 7, 1, NULL, '2019-06-04 11:36:29', 1, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (42, 3, 'EDUCATION', 'BOSHIHOU', '博士后', b'1', 'warning', '', 8, 1, NULL, '2019-06-04 11:36:29', 1, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (43, 1, 'NATION', 'mz_hanz', '汉族', b'1', 'warning', '', 0, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (44, 1, 'NATION', 'mz_zz', '壮族', b'1', 'warning', '', 1, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (45, 1, 'NATION', 'mz_mz', '满族', b'1', 'warning', '', 2, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (46, 1, 'NATION', 'mz_hz', '回族', b'1', 'warning', '', 3, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (47, 1, 'NATION', 'mz_miaoz', '苗族', b'1', 'warning', '', 4, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (48, 1, 'NATION', 'mz_wwez', '维吾尔族', b'1', 'warning', '', 5, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (49, 1, 'NATION', 'mz_tjz', '土家族', b'1', 'warning', '', 6, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (50, 1, 'NATION', 'mz_yz', '彝族', b'1', 'warning', '', 7, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (51, 1, 'NATION', 'mz_mgz', '蒙古族', b'1', 'warning', '', 8, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (52, 1, 'NATION', 'mz_zhangz', '藏族', b'1', 'warning', '', 9, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (53, 1, 'NATION', 'mz_byz', '布依族', b'1', 'warning', '', 10, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (54, 1, 'NATION', 'mz_dz', '侗族', b'1', 'warning', '', 11, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (55, 1, 'NATION', 'mz_yaoz', '瑶族', b'1', 'warning', '', 12, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (56, 1, 'NATION', 'mz_cxz', '朝鲜族', b'1', 'warning', '', 13, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (57, 1, 'NATION', 'mz_bz', '白族', b'1', 'warning', '', 14, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (58, 1, 'NATION', 'mz_hnz', '哈尼族', b'1', 'warning', '', 15, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (59, 1, 'NATION', 'mz_hskz', '哈萨克族', b'1', 'warning', '', 16, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (60, 1, 'NATION', 'mz_lz', '黎族', b'1', 'warning', '', 17, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (61, 1, 'NATION', 'mz_daiz', '傣族', b'1', 'warning', '', 18, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (62, 1, 'NATION', 'mz_sz', '畲族', b'1', 'warning', '', 19, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (63, 1, 'NATION', 'mz_llz', '傈僳族', b'1', 'warning', '', 20, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (64, 1, 'NATION', 'mz_glz', '仡佬族', b'1', 'warning', '', 21, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (65, 1, 'NATION', 'mz_dxz', '东乡族', b'1', 'warning', '', 22, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (66, 1, 'NATION', 'mz_gsz', '高山族', b'1', 'warning', '', 23, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (67, 1, 'NATION', 'mz_lhz', '拉祜族', b'1', 'warning', '', 24, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (68, 1, 'NATION', 'mz_shuiz', '水族', b'1', 'warning', '', 25, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (69, 1, 'NATION', 'mz_wz', '佤族', b'1', 'warning', '', 26, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (70, 1, 'NATION', 'mz_nxz', '纳西族', b'1', 'warning', '', 27, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (71, 1, 'NATION', 'mz_qz', '羌族', b'1', 'warning', '', 28, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (72, 1, 'NATION', 'mz_tz', '土族', b'1', 'warning', '', 29, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (73, 1, 'NATION', 'mz_zlz', '仫佬族', b'1', 'warning', '', 30, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (74, 1, 'NATION', 'mz_xbz', '锡伯族', b'1', 'warning', '', 31, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (75, 1, 'NATION', 'mz_kehzz', '柯尔克孜族', b'1', 'warning', '', 32, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (76, 1, 'NATION', 'mz_dwz', '达斡尔族', b'1', 'warning', '', 33, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (77, 1, 'NATION', 'mz_jpz', '景颇族', b'1', 'warning', '', 34, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (78, 1, 'NATION', 'mz_mlz', '毛南族', b'1', 'warning', '', 35, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (79, 1, 'NATION', 'mz_slz', '撒拉族', b'1', 'warning', '', 36, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (80, 1, 'NATION', 'mz_tjkz', '塔吉克族', b'1', 'warning', '', 37, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (81, 1, 'NATION', 'mz_acz', '阿昌族', b'1', 'warning', '', 38, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (82, 1, 'NATION', 'mz_pmz', '普米族', b'1', 'warning', '', 39, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (83, 1, 'NATION', 'mz_ewkz', '鄂温克族', b'1', 'warning', '', 40, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (84, 1, 'NATION', 'mz_nz', '怒族', b'1', 'warning', '', 41, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (85, 1, 'NATION', 'mz_jz', '京族', b'1', 'warning', '', 42, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (86, 1, 'NATION', 'mz_jnz', '基诺族', b'1', 'warning', '', 43, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (87, 1, 'NATION', 'mz_daz', '德昂族', b'1', 'warning', '', 44, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (88, 1, 'NATION', 'mz_baz', '保安族', b'1', 'warning', '', 45, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (89, 1, 'NATION', 'mz_elsz', '俄罗斯族', b'1', 'warning', '', 46, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (90, 1, 'NATION', 'mz_ygz', '裕固族', b'1', 'warning', '', 47, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (91, 1, 'NATION', 'mz_wzbkz', '乌兹别克族', b'1', 'warning', '', 48, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (92, 1, 'NATION', 'mz_mbz', '门巴族', b'1', 'warning', '', 49, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (93, 1, 'NATION', 'mz_elcz', '鄂伦春族', b'1', 'warning', '', 50, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (94, 1, 'NATION', 'mz_dlz', '独龙族', b'1', 'warning', '', 51, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (95, 1, 'NATION', 'mz_tkez', '塔塔尔族', b'1', 'warning', '', 52, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (96, 1, 'NATION', 'mz_hzz', '赫哲族', b'1', 'warning', '', 53, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (97, 1, 'NATION', 'mz_lbz', '珞巴族', b'1', 'warning', '', 54, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (98, 1, 'NATION', 'mz_blz', '布朗族', b'1', 'warning', '', 55, 1, NULL, '2018-03-15 20:11:01', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (99, 2, 'POSITION_STATUS', 'WORKING', '在职', b'1', 'warning', '', 1, 1, NULL, '2019-06-04 11:38:16', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (100, 2, 'POSITION_STATUS', 'QUIT', '离职', b'1', 'warning', '', 2, 1, NULL, '2019-06-04 11:38:50', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (101, 4, 'AREA_LEVEL', 'TOWNS', '乡镇', b'1', 'warning', '', 5, 3, NULL, '2020-03-09 23:33:46', 3, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (102, 3, 'EDUCATION', 'XIAOXUE', '小学', b'1', 'warning', '', 1, 3, NULL, '2020-03-09 23:34:13', 3, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (103, 3, 'EDUCATION', 'ZHONGXUE', '中学', b'1', 'warning', '', 2, 3, NULL, '2020-03-09 23:34:32', 3, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (104, 3, 'EDUCATION', 'GAOZHONG', '高中', b'1', 'warning', '', 3, 3, NULL, '2020-03-09 23:34:40', 3, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (105, 3, 'EDUCATION', 'QITA', '其他', b'1', 'warning', '', 20, 3, NULL, '2020-03-09 23:34:54', 3, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (106, 1, 'NATION', 'mz_qt', '其他', b'1', 'warning', '', 100, 3, NULL, '2020-03-09 23:38:29', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (107, 2, 'POSITION_STATUS', 'LEAVE', '请假', b'1', 'warning', '', 3, 3, NULL, '2020-03-09 23:39:30', 2, NULL, '2021-07-16 06:43:08');
INSERT INTO `common_dictionary_item` VALUES (108, 6, 'SEX', '1', '男', b'1', 'success', '男', 1, 2, '不告诉你', '2020-11-18 07:24:58', 2, '不告诉你', '2021-07-13 07:31:44');
INSERT INTO `common_dictionary_item` VALUES (109, 6, 'SEX', '2', '女', b'1', 'error', '女', 1, 2, '不告诉你', '2020-11-18 07:25:07', 2, '不告诉你', '2021-07-13 07:31:41');
INSERT INTO `common_dictionary_item` VALUES (110, 7, 'NOTICE', '0', '通知', b'1', 'success', '通知', 1, 2, '不告诉你', '2020-11-19 02:58:37', 2, '不告诉你', '2021-07-12 11:37:43');
INSERT INTO `common_dictionary_item` VALUES (111, 7, 'NOTICE', '1', '消息', b'1', 'success', '消息', 1, 2, '不告诉你', '2020-11-19 02:58:55', 2, '不告诉你', '2021-07-12 11:37:46');
INSERT INTO `common_dictionary_item` VALUES (112, 7, 'NOTICE', '2', '待办', b'1', 'error', '待办', 1, 2, '不告诉你', '2020-11-19 02:59:05', 2, '不告诉你', '2021-07-12 11:37:47');
INSERT INTO `common_dictionary_item` VALUES (113, 8, 'STATION_TYPE', '0', '基层', b'1', 'success', '1111', 1, 1, '长风一梦', '2021-07-16 04:06:54', NULL, NULL, NULL);
INSERT INTO `common_dictionary_item` VALUES (114, 9, 'COLOR', 'success', '成功', b'1', 'success', '成功', 1, 1, '长风一梦', '2021-07-16 04:12:47', 1, '长风一梦', '2021-07-16 04:12:59');
INSERT INTO `common_dictionary_item` VALUES (115, 9, 'COLOR', 'warning', '警告', b'1', 'warning', '警告', 1, 1, '长风一梦', '2021-07-16 04:15:06', 1, '长风一梦', '2021-07-16 04:16:21');
INSERT INTO `common_dictionary_item` VALUES (116, 9, 'COLOR', 'error', '错误', b'1', 'error', '红色', 1, 1, '长风一梦', '2021-07-16 04:16:55', 1, '长风一梦', '2021-07-16 04:17:07');
INSERT INTO `common_dictionary_item` VALUES (118, 8, 'STATION_TYPE', '1', '中层', b'1', 'success', '中层', 1, 1, '长风一梦', '2021-07-16 04:59:51', 1, '长风一梦', '2021-07-16 05:06:09');
INSERT INTO `common_dictionary_item` VALUES (119, 8, 'STATION_TYPE', '2', '高层', b'1', 'warning', '高层', 1, 1, '长风一梦', '2021-07-16 05:00:07', 1, '长风一梦', '2021-07-16 05:07:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Records of common_login_log
-- ----------------------------
BEGIN;
INSERT INTO `common_login_log` VALUES (1, 1, '116.232.50.208', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '中国|0|上海|上海市|电信', '2021-07-20 17:50:30');
INSERT INTO `common_login_log` VALUES (2, 1, '116.232.50.208', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '中国|0|上海|上海市|电信', '2021-07-20 18:21:30');
INSERT INTO `common_login_log` VALUES (3, 1, '117.90.150.110', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '中国|0|江苏省|镇江市|电信', '2021-07-20 18:26:49');
INSERT INTO `common_login_log` VALUES (4, 1, '116.21.30.198', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '91.0.4472.106', 'Windows 10 or Windows Server 2016', '中国|0|广东省|广州市|电信', '2021-07-20 18:26:54');
INSERT INTO `common_login_log` VALUES (5, 1, '36.17.80.247', 'client', '长风一梦', 'admin', 'Android', 'Webkit', '537.36', 'Chrome', '77.0.3865.120', 'Android', '中国|0|浙江省|杭州市|电信', '2021-07-20 18:26:59');
INSERT INTO `common_login_log` VALUES (6, 1, '61.141.78.145', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'Windows 10 or Windows Server 2016', '中国|0|广东省|深圳市|电信', '2021-07-20 18:27:19');
INSERT INTO `common_login_log` VALUES (7, 1, '123.118.101.112', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '中国|0|北京|北京市|联通', '2021-07-20 18:27:25');
INSERT INTO `common_login_log` VALUES (8, 1, '106.84.174.132', 'client', '长风一梦', 'admin', 'Android', 'Webkit', '537.36', 'Chrome', '77.0.3865.120', 'Android', '中国|0|重庆|重庆市|电信', '2021-07-20 18:27:41');
INSERT INTO `common_login_log` VALUES (9, 1, '60.173.241.160', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '中国|0|安徽省|合肥市|电信', '2021-07-20 18:27:43');
INSERT INTO `common_login_log` VALUES (10, 1, '113.98.62.131', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '91.0.4472.124', 'Windows 10 or Windows Server 2016', '中国|0|广东省|深圳市|电信', '2021-07-20 18:28:20');
INSERT INTO `common_login_log` VALUES (11, 1, '171.221.151.182', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '91.0.4472.124', 'Windows 10 or Windows Server 2016', '中国|0|四川省|成都市|电信', '2021-07-20 18:28:35');
INSERT INTO `common_login_log` VALUES (12, 1, '203.168.22.242', 'client', '长风一梦', 'admin', 'Android', 'Webkit', '537.36', 'Chrome', '77.0.3865.120', 'Android', '中国|0|广东省|广州市|电信', '2021-07-20 18:29:16');
INSERT INTO `common_login_log` VALUES (13, 1, '117.90.150.110', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '中国|0|江苏省|镇江市|电信', '2021-07-20 18:29:27');
INSERT INTO `common_login_log` VALUES (14, 1, '222.93.89.15', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '88.0.4324.150', 'Windows 7 or Windows Server 2008R2', '中国|0|江苏省|苏州市|电信', '2021-07-20 18:33:05');
INSERT INTO `common_login_log` VALUES (15, 1, '175.0.52.25', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '89.0.4389.82', 'Windows 10 or Windows Server 2016', '中国|0|湖南省|长沙市|电信', '2021-07-20 18:33:38');
INSERT INTO `common_login_log` VALUES (16, 1, '223.104.244.109', 'client', '长风一梦', 'admin', 'Android', 'Webkit', '537.36', 'Chrome', '77.0.3865.120', 'Android', '中国|0|浙江省|杭州市|移动', '2021-07-20 18:43:33');
INSERT INTO `common_login_log` VALUES (17, 1, '113.246.243.159', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '71.0.3578.98', 'Windows 7 or Windows Server 2008R2', '中国|0|湖南省|长沙市|电信', '2021-07-20 18:45:13');
INSERT INTO `common_login_log` VALUES (18, 1, '183.9.236.79', 'client', '长风一梦', 'admin', 'Android', 'Webkit', '537.36', 'Chrome', '77.0.3865.120', 'Android', '中国|0|广东省|揭阳市|电信', '2021-07-20 19:04:09');
INSERT INTO `common_login_log` VALUES (19, 1, '182.149.165.217', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'Windows 10 or Windows Server 2016', '中国|0|四川省|成都市|电信', '2021-07-20 19:10:55');
INSERT INTO `common_login_log` VALUES (20, 1, '58.60.1.71', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'Windows 10 or Windows Server 2016', '中国|0|广东省|深圳市|电信', '2021-07-20 19:21:43');
INSERT INTO `common_login_log` VALUES (21, 1, '223.96.72.37', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'MSEdge', '91.0.864.70', 'Windows 10 or Windows Server 2016', '中国|0|山东省|淄博市|移动', '2021-07-20 20:03:09');
INSERT INTO `common_login_log` VALUES (22, 1, '180.164.33.214', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '91.0.4472.124', 'Windows 10 or Windows Server 2016', '中国|0|上海|上海市|电信', '2021-07-20 20:45:09');
INSERT INTO `common_login_log` VALUES (23, 1, '211.97.104.127', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '中国|0|福建省|福州市|联通', '2021-07-20 22:42:18');
INSERT INTO `common_login_log` VALUES (24, 1, '58.19.7.53', 'client', '长风一梦', 'admin', 'Android', 'Webkit', '537.36', 'Chrome', '77.0.3865.120', 'Android', '中国|0|湖北省|武汉市|联通', '2021-07-21 08:10:19');
INSERT INTO `common_login_log` VALUES (25, 1, '116.232.50.208', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '中国|0|上海|上海市|电信', '2021-07-21 09:20:12');
INSERT INTO `common_login_log` VALUES (26, 1, '118.163.184.20', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '中国|0|台湾省|0|中华电信', '2021-07-21 10:21:41');
INSERT INTO `common_login_log` VALUES (27, 1, '118.163.184.20', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '中国|0|台湾省|0|中华电信', '2021-07-21 10:21:51');
INSERT INTO `common_login_log` VALUES (28, 1, '180.167.193.50', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '中国|0|上海|上海市|电信', '2021-07-21 13:54:06');
INSERT INTO `common_login_log` VALUES (29, 1, '116.232.50.208', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '中国|0|上海|上海市|电信', '2021-07-21 14:18:00');
INSERT INTO `common_login_log` VALUES (30, 1, '59.37.32.158', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '86.0.4240.198', 'Windows 10 or Windows Server 2016', '中国|0|广东省|惠州市|电信', '2021-07-21 14:37:16');
INSERT INTO `common_login_log` VALUES (31, 1, '171.8.245.75', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'Windows 10 or Windows Server 2016', '中国|0|河南省|郑州市|电信', '2021-07-21 15:29:47');
INSERT INTO `common_login_log` VALUES (32, 1, '171.217.44.126', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'MSEdge', '91.0.864.71', 'Windows 10 or Windows Server 2016', '中国|0|四川省|成都市|电信', '2021-07-21 15:30:25');
INSERT INTO `common_login_log` VALUES (33, 1, '220.160.18.78', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '91.0.4472.124', 'Windows 10 or Windows Server 2016', '中国|0|福建省|福州市|电信', '2021-07-21 15:31:05');
INSERT INTO `common_login_log` VALUES (34, 1, '116.236.204.90', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '91.0.4472.124', 'Windows 10 or Windows Server 2016', '中国|0|上海|上海市|电信', '2021-07-21 15:31:14');
INSERT INTO `common_login_log` VALUES (35, 1, '101.88.40.168', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '74.0.3729.169', 'Windows 10 or Windows Server 2016', '中国|0|上海|上海市|电信', '2021-07-21 15:33:47');
INSERT INTO `common_login_log` VALUES (36, 1, '183.37.146.217', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'MSEdge', '91.0.864.70', 'Windows 10 or Windows Server 2016', '中国|0|广东省|深圳市|电信', '2021-07-21 15:38:49');
INSERT INTO `common_login_log` VALUES (37, 1, '222.209.208.110', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'MSEdge', '91.0.864.54', 'Windows 10 or Windows Server 2016', '中国|0|四川省|成都市|电信', '2021-07-21 15:38:50');
INSERT INTO `common_login_log` VALUES (38, 1, '180.139.209.161', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '92.0.4484.7', 'Windows 10 or Windows Server 2016', '中国|0|广西|南宁市|电信', '2021-07-21 15:38:59');
INSERT INTO `common_login_log` VALUES (39, 1, '223.90.124.217', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '92.0.4515.107', 'Windows 10 or Windows Server 2016', '中国|0|河南省|信阳市|移动', '2021-07-21 15:40:02');
INSERT INTO `common_login_log` VALUES (40, 1, '221.226.125.133', 'client', '长风一梦', 'admin', 'Windows', 'Webkit', '537.36', 'Chrome', '81.0.4044.138', 'Windows 7 or Windows Server 2008R2', '中国|0|江苏省|南京市|电信', '2021-07-21 15:40:20');
INSERT INTO `common_login_log` VALUES (41, 1, '116.232.50.208', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '中国|0|上海|上海市|电信', '2021-07-21 15:44:09');
INSERT INTO `common_login_log` VALUES (42, 1, '113.81.130.199', 'client', '长风一梦', 'admin', 'Android', 'Webkit', '537.36', 'Chrome', '77.0.3865.120', 'Android', '中国|0|广东省|惠州市|电信', '2021-07-21 15:49:02');
INSERT INTO `common_login_log` VALUES (43, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-21 09:05:16');
INSERT INTO `common_login_log` VALUES (44, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-21 09:06:42');
INSERT INTO `common_login_log` VALUES (45, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-21 09:09:03');
INSERT INTO `common_login_log` VALUES (46, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-22 01:36:45');
INSERT INTO `common_login_log` VALUES (47, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-22 01:36:52');
INSERT INTO `common_login_log` VALUES (48, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-22 01:36:58');
INSERT INTO `common_login_log` VALUES (49, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-22 02:18:39');
INSERT INTO `common_login_log` VALUES (50, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-22 13:38:40');
INSERT INTO `common_login_log` VALUES (51, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.114', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-23 01:43:22');
INSERT INTO `common_login_log` VALUES (52, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-24 09:06:33');
INSERT INTO `common_login_log` VALUES (53, 2, '127.0.0.1', 'client', '南柯一梦', 'nankeyimeng', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-24 09:18:51');
INSERT INTO `common_login_log` VALUES (54, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-24 09:19:23');
INSERT INTO `common_login_log` VALUES (55, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 05:29:05');
INSERT INTO `common_login_log` VALUES (56, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 08:07:51');
INSERT INTO `common_login_log` VALUES (57, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 10:29:22');
INSERT INTO `common_login_log` VALUES (58, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 10:30:55');
INSERT INTO `common_login_log` VALUES (59, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 10:31:12');
INSERT INTO `common_login_log` VALUES (60, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 10:31:46');
INSERT INTO `common_login_log` VALUES (61, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 10:32:14');
INSERT INTO `common_login_log` VALUES (62, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 10:33:47');
INSERT INTO `common_login_log` VALUES (63, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 10:35:38');
INSERT INTO `common_login_log` VALUES (64, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-27 10:37:07');
INSERT INTO `common_login_log` VALUES (65, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '91.0.4472.164', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-28 01:39:51');
INSERT INTO `common_login_log` VALUES (66, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '92.0.4515.107', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-29 01:36:21');
INSERT INTO `common_login_log` VALUES (67, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '92.0.4515.107', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-30 02:18:47');
INSERT INTO `common_login_log` VALUES (68, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '92.0.4515.107', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-30 05:28:28');
INSERT INTO `common_login_log` VALUES (69, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '92.0.4515.107', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-30 09:07:24');
INSERT INTO `common_login_log` VALUES (70, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '92.0.4515.107', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-31 02:14:03');
INSERT INTO `common_login_log` VALUES (71, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '92.0.4515.107', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-31 02:25:42');
INSERT INTO `common_login_log` VALUES (72, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '92.0.4515.107', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-31 02:27:33');
INSERT INTO `common_login_log` VALUES (73, 1, '127.0.0.1', 'client', '长风一梦', 'admin', 'Mac', 'Webkit', '537.36', 'Chrome', '92.0.4515.107', 'OSX', '0|0|0|内网IP|内网IP', '2021-07-31 02:28:42');
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
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

-- ----------------------------
-- Records of common_opt_log
-- ----------------------------
BEGIN;
INSERT INTO `common_opt_log` VALUES (83, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 12:02:59', '2021-07-28 12:02:59', 18, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 04:02:58');
INSERT INTO `common_opt_log` VALUES (84, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:25:55', '2021-07-28 17:25:55', 23, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:25:55');
INSERT INTO `common_opt_log` VALUES (85, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:26:18', '2021-07-28 17:26:18', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:26:17');
INSERT INTO `common_opt_log` VALUES (86, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:29:23', '2021-07-28 17:29:23', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:29:22');
INSERT INTO `common_opt_log` VALUES (87, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:29:51', '2021-07-28 17:29:51', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:29:50');
INSERT INTO `common_opt_log` VALUES (88, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:36:13', '2021-07-28 17:36:13', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:36:12');
INSERT INTO `common_opt_log` VALUES (89, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:36:25', '2021-07-28 17:36:25', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:36:24');
INSERT INTO `common_opt_log` VALUES (90, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:36:38', '2021-07-28 17:36:38', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:36:37');
INSERT INTO `common_opt_log` VALUES (91, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:24', '2021-07-28 17:37:24', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:24');
INSERT INTO `common_opt_log` VALUES (92, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:24', '2021-07-28 17:37:24', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:24');
INSERT INTO `common_opt_log` VALUES (93, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:25', '2021-07-28 17:37:25', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:24');
INSERT INTO `common_opt_log` VALUES (94, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:27', '2021-07-28 17:37:27', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:26');
INSERT INTO `common_opt_log` VALUES (95, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:27', '2021-07-28 17:37:27', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:26');
INSERT INTO `common_opt_log` VALUES (96, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:27', '2021-07-28 17:37:27', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:27');
INSERT INTO `common_opt_log` VALUES (97, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:31', '2021-07-28 17:37:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:30');
INSERT INTO `common_opt_log` VALUES (98, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:31', '2021-07-28 17:37:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:30');
INSERT INTO `common_opt_log` VALUES (99, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:31', '2021-07-28 17:37:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:31');
INSERT INTO `common_opt_log` VALUES (100, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},\"UAT发布\"]', NULL, NULL, NULL, '2021-07-28 17:37:36', '2021-07-28 17:37:36', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:35');
INSERT INTO `common_opt_log` VALUES (101, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},\"UAT发布\"]', NULL, NULL, NULL, '2021-07-28 17:37:36', '2021-07-28 17:37:36', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:35');
INSERT INTO `common_opt_log` VALUES (102, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},\"UAT发布\"]', NULL, NULL, NULL, '2021-07-28 17:37:36', '2021-07-28 17:37:36', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:36');
INSERT INTO `common_opt_log` VALUES (103, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:37:36', '2021-07-28 17:37:36', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:37:36');
INSERT INTO `common_opt_log` VALUES (104, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null]', NULL, NULL, NULL, '2021-07-28 17:38:38', '2021-07-28 17:38:38', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '91.0.4472.164', 1, '长风一梦', '2021-07-28 09:38:37');
INSERT INTO `common_opt_log` VALUES (105, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 10:30:52', '2021-07-29 10:30:52', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 02:30:52');
INSERT INTO `common_opt_log` VALUES (106, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 10:31:02', '2021-07-29 10:31:02', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 02:31:02');
INSERT INTO `common_opt_log` VALUES (107, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑岗位', 'com.wemirr.platform.authority.controller.baseinfo.StationController', 'edit', '/stations/10121', 'PUT', '[10121,{\"description\":\"总部2把手\",\"name\":\"销售总监\",\"orgId\":10005,\"sequence\":1,\"status\":true,\"type\":1}]', NULL, NULL, NULL, '2021-07-29 10:36:31', '2021-07-29 10:36:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 02:36:31');
INSERT INTO `common_opt_log` VALUES (108, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:12:21', '2021-07-29 11:12:21', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:12:21');
INSERT INTO `common_opt_log` VALUES (109, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:13:19', '2021-07-29 11:13:19', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:13:19');
INSERT INTO `common_opt_log` VALUES (110, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:13:42', '2021-07-29 11:13:42', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:13:42');
INSERT INTO `common_opt_log` VALUES (111, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:02', '2021-07-29 11:14:02', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:02');
INSERT INTO `common_opt_log` VALUES (112, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:04', '2021-07-29 11:14:04', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:04');
INSERT INTO `common_opt_log` VALUES (113, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:07', '2021-07-29 11:14:07', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:06');
INSERT INTO `common_opt_log` VALUES (114, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:07', '2021-07-29 11:14:07', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:07');
INSERT INTO `common_opt_log` VALUES (115, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:09', '2021-07-29 11:14:09', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:08');
INSERT INTO `common_opt_log` VALUES (116, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:09', '2021-07-29 11:14:09', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:08');
INSERT INTO `common_opt_log` VALUES (117, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:10', '2021-07-29 11:14:10', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:09');
INSERT INTO `common_opt_log` VALUES (118, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:11', '2021-07-29 11:14:11', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:10');
INSERT INTO `common_opt_log` VALUES (119, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:11', '2021-07-29 11:14:11', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:11');
INSERT INTO `common_opt_log` VALUES (120, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:13', '2021-07-29 11:14:13', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:12');
INSERT INTO `common_opt_log` VALUES (121, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:13', '2021-07-29 11:14:13', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:13');
INSERT INTO `common_opt_log` VALUES (122, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:14', '2021-07-29 11:14:14', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:14');
INSERT INTO `common_opt_log` VALUES (123, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:16', '2021-07-29 11:14:16', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:15');
INSERT INTO `common_opt_log` VALUES (124, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,\"1321\"]', NULL, NULL, NULL, '2021-07-29 11:14:17', '2021-07-29 11:14:17', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:16');
INSERT INTO `common_opt_log` VALUES (125, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,\"1321\"]', NULL, NULL, NULL, '2021-07-29 11:14:17', '2021-07-29 11:14:17', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:17');
INSERT INTO `common_opt_log` VALUES (126, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:18', '2021-07-29 11:14:18', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:17');
INSERT INTO `common_opt_log` VALUES (127, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},\"123\",null]', NULL, NULL, NULL, '2021-07-29 11:14:21', '2021-07-29 11:14:21', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:20');
INSERT INTO `common_opt_log` VALUES (128, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},\"123\",null]', NULL, NULL, NULL, '2021-07-29 11:14:21', '2021-07-29 11:14:21', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:21');
INSERT INTO `common_opt_log` VALUES (129, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:22', '2021-07-29 11:14:22', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:21');
INSERT INTO `common_opt_log` VALUES (130, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},\"民族\\t\",null]', NULL, NULL, NULL, '2021-07-29 11:14:24', '2021-07-29 11:14:24', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:24');
INSERT INTO `common_opt_log` VALUES (131, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},\"民族\\t\",null]', NULL, NULL, NULL, '2021-07-29 11:14:24', '2021-07-29 11:14:24', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:24');
INSERT INTO `common_opt_log` VALUES (132, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:25', '2021-07-29 11:14:25', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:25');
INSERT INTO `common_opt_log` VALUES (133, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},\"民族\",null]', NULL, NULL, NULL, '2021-07-29 11:14:27', '2021-07-29 11:14:27', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:26');
INSERT INTO `common_opt_log` VALUES (134, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},\"民族\",null]', NULL, NULL, NULL, '2021-07-29 11:14:27', '2021-07-29 11:14:27', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:27');
INSERT INTO `common_opt_log` VALUES (135, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:14:28', '2021-07-29 11:14:28', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:14:27');
INSERT INTO `common_opt_log` VALUES (136, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:15:06', '2021-07-29 11:15:06', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:15:05');
INSERT INTO `common_opt_log` VALUES (137, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:15:16', '2021-07-29 11:15:16', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:15:16');
INSERT INTO `common_opt_log` VALUES (138, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:16:18', '2021-07-29 11:16:18', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:16:18');
INSERT INTO `common_opt_log` VALUES (139, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:16:30', '2021-07-29 11:16:30', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:16:30');
INSERT INTO `common_opt_log` VALUES (140, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:16:32', '2021-07-29 11:16:32', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:16:31');
INSERT INTO `common_opt_log` VALUES (141, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:16:47', '2021-07-29 11:16:47', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:16:46');
INSERT INTO `common_opt_log` VALUES (142, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:18:48', '2021-07-29 11:18:48', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:18:48');
INSERT INTO `common_opt_log` VALUES (143, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:19:15', '2021-07-29 11:19:15', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:19:14');
INSERT INTO `common_opt_log` VALUES (144, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:19:26', '2021-07-29 11:19:26', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:19:26');
INSERT INTO `common_opt_log` VALUES (145, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:19:31', '2021-07-29 11:19:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:19:30');
INSERT INTO `common_opt_log` VALUES (146, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:20:29', '2021-07-29 11:20:29', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:20:28');
INSERT INTO `common_opt_log` VALUES (147, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:20:42', '2021-07-29 11:20:42', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:20:42');
INSERT INTO `common_opt_log` VALUES (148, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:20:48', '2021-07-29 11:20:48', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:20:47');
INSERT INTO `common_opt_log` VALUES (149, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:20:58', '2021-07-29 11:20:58', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:20:58');
INSERT INTO `common_opt_log` VALUES (150, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:21:00', '2021-07-29 11:21:00', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:20:59');
INSERT INTO `common_opt_log` VALUES (151, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:21:06', '2021-07-29 11:21:06', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:21:05');
INSERT INTO `common_opt_log` VALUES (152, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:21:17', '2021-07-29 11:21:17', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:21:16');
INSERT INTO `common_opt_log` VALUES (153, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:21:26', '2021-07-29 11:21:26', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:21:25');
INSERT INTO `common_opt_log` VALUES (154, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:21:29', '2021-07-29 11:21:29', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:21:29');
INSERT INTO `common_opt_log` VALUES (155, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:21:36', '2021-07-29 11:21:36', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:21:36');
INSERT INTO `common_opt_log` VALUES (156, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:22:02', '2021-07-29 11:22:02', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:22:02');
INSERT INTO `common_opt_log` VALUES (157, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:22:17', '2021-07-29 11:22:17', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:22:16');
INSERT INTO `common_opt_log` VALUES (158, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:23:25', '2021-07-29 11:23:25', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:23:25');
INSERT INTO `common_opt_log` VALUES (159, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:23:30', '2021-07-29 11:23:30', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:23:30');
INSERT INTO `common_opt_log` VALUES (160, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:23:33', '2021-07-29 11:23:33', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:23:33');
INSERT INTO `common_opt_log` VALUES (161, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:23:38', '2021-07-29 11:23:38', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:23:37');
INSERT INTO `common_opt_log` VALUES (162, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:24:38', '2021-07-29 11:24:38', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:24:38');
INSERT INTO `common_opt_log` VALUES (163, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:24:51', '2021-07-29 11:24:51', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:24:51');
INSERT INTO `common_opt_log` VALUES (164, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:25:03', '2021-07-29 11:25:03', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:25:03');
INSERT INTO `common_opt_log` VALUES (165, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:25:19', '2021-07-29 11:25:19', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:25:19');
INSERT INTO `common_opt_log` VALUES (166, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:25:37', '2021-07-29 11:25:37', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:25:37');
INSERT INTO `common_opt_log` VALUES (167, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:25:50', '2021-07-29 11:25:50', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:25:49');
INSERT INTO `common_opt_log` VALUES (168, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:25:58', '2021-07-29 11:25:58', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:25:57');
INSERT INTO `common_opt_log` VALUES (169, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:26:08', '2021-07-29 11:26:08', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:26:08');
INSERT INTO `common_opt_log` VALUES (170, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:26:23', '2021-07-29 11:26:23', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:26:22');
INSERT INTO `common_opt_log` VALUES (171, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:26:42', '2021-07-29 11:26:42', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:26:42');
INSERT INTO `common_opt_log` VALUES (172, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:26:45', '2021-07-29 11:26:45', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:26:45');
INSERT INTO `common_opt_log` VALUES (173, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:26:48', '2021-07-29 11:26:48', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:26:47');
INSERT INTO `common_opt_log` VALUES (174, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 11:26:56', '2021-07-29 11:26:56', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 03:26:55');
INSERT INTO `common_opt_log` VALUES (175, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'EX', '编辑角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'edit', '/roles/2', 'PUT', '[2,{\"code\":\"TEST_ADMIN\",\"description\":\"测试管理员，测试老大\",\"locked\":true,\"name\":\"测试管理员\",\"readonly\":true,\"scopeType\":\"CUSTOMIZE\"}]', NULL, '内置角色无法编辑', 'CheckedException(code=400)\n	at com.wemirr.framework.commons.exception.CheckedException.badRequest(CheckedException.java:55)\n	at com.wemirr.platform.authority.service.impl.RoleServiceImpl.updateRole(RoleServiceImpl.java:87)\n	at com.wemirr.platform.authority.service.impl.RoleServiceImpl$$FastClassBySpringCGLIB$$dd62d439.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:687)\n	at com.wemirr.platform.authority.service.impl.RoleServiceImpl$$EnhancerBySpringCGLIB$$babcd02a.updateRole(<generated>)\n	at com.wemirr.platform.authority.controller.baseinfo.RoleController.edit(RoleController.java:103)\n	at com.wemirr.platform.authority.controller.baseinfo.RoleController$$FastClassBySpringCGLIB$$1cddd27e.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.validation.beanvalidation.MethodValidationInterceptor.invoke(MethodValidationInterceptor.java:119)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:55)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor.invoke(MethodBeforeAdviceInterceptor.java:56)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:95)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)\n	at com.wemirr.platform.authority.controller.baseinfo.RoleController$$EnhancerBySpringCGLIB$$74a1304d.edit(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\n	at org.springframework.web.servlet.FrameworkServlet.doPut(FrameworkServlet.java:920)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:520)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:584)\n	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)\n	at io.undertow.websockets.jsr.JsrWebSocketFilter.doFilter(JsrWebSocketFilter.java:173)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:126)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:90)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:118)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:158)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter.doFilter(OAuth2AuthenticationProcessingFilter.java:176)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:92)\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:77)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)\n	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)\n	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)\n	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)\n	at io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:68)\n	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:111)\n	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)\n	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)\n	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)\n	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)\n	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:269)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:133)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:130)\n	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)\n	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:249)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:99)\n	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:390)\n	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:836)\n	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)\n	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:2019)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1558)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1449)\n	at java.lang.Thread.run(Thread.java:748)\n', '2021-07-29 14:36:01', '2021-07-29 14:36:01', 14, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:36:01');
INSERT INTO `common_opt_log` VALUES (176, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:39:18', '2021-07-29 14:39:18', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:39:18');
INSERT INTO `common_opt_log` VALUES (177, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:39:24', '2021-07-29 14:39:24', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:39:24');
INSERT INTO `common_opt_log` VALUES (178, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:39:26', '2021-07-29 14:39:26', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:39:25');
INSERT INTO `common_opt_log` VALUES (179, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:39:52', '2021-07-29 14:39:52', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:39:51');
INSERT INTO `common_opt_log` VALUES (180, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:39:54', '2021-07-29 14:39:54', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:39:54');
INSERT INTO `common_opt_log` VALUES (181, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:39:58', '2021-07-29 14:39:58', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:39:58');
INSERT INTO `common_opt_log` VALUES (182, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:40:01', '2021-07-29 14:40:01', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:40:00');
INSERT INTO `common_opt_log` VALUES (183, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:40:01', '2021-07-29 14:40:01', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:40:01');
INSERT INTO `common_opt_log` VALUES (184, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:40:02', '2021-07-29 14:40:02', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:40:02');
INSERT INTO `common_opt_log` VALUES (185, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:40:03', '2021-07-29 14:40:03', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:40:03');
INSERT INTO `common_opt_log` VALUES (186, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:40:03', '2021-07-29 14:40:03', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:40:03');
INSERT INTO `common_opt_log` VALUES (187, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:40:06', '2021-07-29 14:40:06', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:40:05');
INSERT INTO `common_opt_log` VALUES (188, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:40:29', '2021-07-29 14:40:29', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:40:28');
INSERT INTO `common_opt_log` VALUES (189, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:40:31', '2021-07-29 14:40:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:40:31');
INSERT INTO `common_opt_log` VALUES (190, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null]', NULL, NULL, NULL, '2021-07-29 14:47:25', '2021-07-29 14:47:25', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-29 06:47:24');
INSERT INTO `common_opt_log` VALUES (191, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null,null]', NULL, NULL, NULL, '2021-07-30 10:19:25', '2021-07-30 10:19:25', 17, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-30 02:19:25');
INSERT INTO `common_opt_log` VALUES (192, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null,null]', NULL, NULL, NULL, '2021-07-30 11:10:45', '2021-07-30 11:10:45', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-30 03:10:44');
INSERT INTO `common_opt_log` VALUES (193, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null,null]', NULL, NULL, NULL, '2021-07-30 15:24:46', '2021-07-30 15:24:46', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-30 07:24:46');
INSERT INTO `common_opt_log` VALUES (194, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null,null]', NULL, NULL, NULL, '2021-07-30 15:54:48', '2021-07-30 15:54:48', 40, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-30 07:54:48');
INSERT INTO `common_opt_log` VALUES (195, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"/wemirr/system/application/index\",\"global\":false,\"icon\":\"ant-design:appstore-twotone\",\"label\":\"应用管理\",\"parentId\":1,\"path\":\"/system/application\",\"sequence\":0,\"status\":true,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2021-07-30 17:10:08', '2021-07-30 17:10:08', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-30 09:10:07');
INSERT INTO `common_opt_log` VALUES (196, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"asc\":true,\"current\":1,\"size\":20},null,null,null]', NULL, NULL, NULL, '2021-07-30 17:51:29', '2021-07-30 17:51:29', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-30 09:51:28');
INSERT INTO `common_opt_log` VALUES (197, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '保存应用', 'com.wemirr.platform.authority.controller.ApplicationController', 'save', '/applications', 'POST', '[{\"accessTokenValidity\":86400,\"authorizedGrantTypes\":\"password,refresh_token,client_credentials,authorization_code\",\"clientId\":\"client\",\"clientName\":\"我是客户端\",\"clientSecret\":\"client\",\"refreshTokenValidity\":604800,\"scope\":\"server\",\"status\":true,\"type\":0}]', NULL, NULL, NULL, '2021-07-31 11:29:36', '2021-07-31 11:29:36', 45, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-31 03:29:36');
INSERT INTO `common_opt_log` VALUES (198, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '保存应用', 'com.wemirr.platform.authority.controller.ApplicationController', 'save', '/applications', 'POST', '[{\"accessTokenValidity\":86400,\"authorizedGrantTypes\":\"password,client_credentials,authorization_code\",\"clientId\":\"client\",\"clientName\":\"我是客户端\",\"clientSecret\":\"client\",\"refreshTokenValidity\":604800,\"scope\":\"server\",\"status\":true,\"type\":0}]', NULL, NULL, NULL, '2021-07-31 11:30:13', '2021-07-31 11:30:13', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-31 03:30:12');
INSERT INTO `common_opt_log` VALUES (199, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改应用', 'com.wemirr.platform.authority.controller.ApplicationController', 'edit', '/applications/client', 'PUT', '[{\"accessTokenValidity\":86400,\"authorizedGrantTypes\":\"password,client_credentials,authorization_code\",\"clientId\":\"client\",\"clientName\":\"我是客户端\",\"clientSecret\":\"client\",\"refreshTokenValidity\":604800,\"scope\":\"server\",\"status\":true,\"type\":0}]', NULL, NULL, NULL, '2021-07-31 11:36:20', '2021-07-31 11:36:20', 27, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-31 03:36:19');
INSERT INTO `common_opt_log` VALUES (200, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改应用', 'com.wemirr.platform.authority.controller.ApplicationController', 'edit', '/applications/test', 'PUT', '[{\"accessTokenValidity\":86400,\"authorizedGrantTypes\":\"password,refresh_token,client_credentials,authorization_code\",\"clientId\":\"test\",\"clientName\":\"测试客户端\",\"clientSecret\":\"test\",\"refreshTokenValidity\":604800,\"scope\":\"server\",\"status\":true,\"type\":0}]', NULL, NULL, NULL, '2021-07-31 11:36:25', '2021-07-31 11:36:25', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-31 03:36:24');
INSERT INTO `common_opt_log` VALUES (201, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'EX', '添加应用', 'com.wemirr.platform.authority.controller.ApplicationController', 'save', '/applications', 'POST', '[{\"accessTokenValidity\":43200,\"authorizedGrantTypes\":\"password\",\"clientId\":\"123\",\"clientName\":\"123\",\"clientSecret\":\"123\",\"scope\":\"all\",\"status\":true,\"type\":0}]', NULL, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n### The error may exist in com/wemirr/platform/authority/repository/OAuthClientDetailsMapper.java (best guess)\n### The error may involve com.wemirr.platform.authority.repository.OAuthClientDetailsMapper.insert-Inline\n### The error occurred while setting parameters\n### SQL: INSERT INTO sys_oauth_client_details (client_secret, client_name, scope, authorized_grant_types, access_token_validity, status, type) VALUES (?, ?, ?, ?, ?, ?, ?)\n### Cause: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n; Field \'client_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'client_id\' doesn\'t have a default value', 'org.springframework.dao.DataIntegrityViolationException: \n### Error updating database.  Cause: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n### The error may exist in com/wemirr/platform/authority/repository/OAuthClientDetailsMapper.java (best guess)\n### The error may involve com.wemirr.platform.authority.repository.OAuthClientDetailsMapper.insert-Inline\n### The error occurred while setting parameters\n### SQL: INSERT INTO sys_oauth_client_details (client_secret, client_name, scope, authorized_grant_types, access_token_validity, status, type) VALUES (?, ?, ?, ?, ?, ?, ?)\n### Cause: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n; Field \'client_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:251)\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:88)\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:440)\n	at com.sun.proxy.$Proxy166.insert(Unknown Source)\n	at org.mybatis.spring.SqlSessionTemplate.insert(SqlSessionTemplate.java:271)\n	at com.baomidou.mybatisplus.core.override.MybatisMapperMethod.execute(MybatisMapperMethod.java:60)\n	at com.baomidou.mybatisplus.core.override.MybatisMapperProxy$PlainMethodInvoker.invoke(MybatisMapperProxy.java:148)\n	at com.baomidou.mybatisplus.core.override.MybatisMapperProxy.invoke(MybatisMapperProxy.java:89)\n	at com.sun.proxy.$Proxy184.insert(Unknown Source)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:139)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\n	at com.sun.proxy.$Proxy185.insert(Unknown Source)\n	at com.baomidou.mybatisplus.extension.service.IService.save(IService.java:63)\n	at com.wemirr.framework.boot.service.impl.SuperServiceImpl.save(SuperServiceImpl.java:68)\n	at com.wemirr.framework.boot.service.impl.SuperServiceImpl$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:687)\n	at com.wemirr.platform.authority.service.impl.ApplicationServiceImpl$$EnhancerBySpringCGLIB$$1.save(<generated>)\n	at com.wemirr.platform.authority.controller.ApplicationController.save(ApplicationController.java:60)\n	at com.wemirr.platform.authority.controller.ApplicationController$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:55)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor.invoke(MethodBeforeAdviceInterceptor.java:56)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:95)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)\n	at com.wemirr.platform.authority.controller.ApplicationController$$EnhancerBySpringCGLIB$$1.save(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:517)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:584)\n	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)\n	at io.undertow.websockets.jsr.JsrWebSocketFilter.doFilter(JsrWebSocketFilter.java:173)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:126)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:90)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:118)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:158)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter.doFilter(OAuth2AuthenticationProcessingFilter.java:176)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:92)\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:77)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)\n	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)\n	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)\n	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)\n	at io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:68)\n	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:111)\n	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)\n	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)\n	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)\n	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)\n	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.jrHandle(ServletInitialHandler.java:40001)\n	at org.zeroturnaround.javarebel.integration.servlet.undertow.cbp.ServletInitialHandlerCBP.handleRequest(ServletInitialHandlerCBP.java:138)\n	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:269)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:133)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:130)\n	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)\n	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:249)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:99)\n	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:390)\n	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:836)\n	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)\n	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:2019)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1558)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1449)\n	at java.lang.Thread.run(Thread.java:748)\nCaused by: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129)\n	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:953)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.execute(ClientPreparedStatement.java:370)\n	at com.zaxxer.hikari.pool.ProxyPreparedStatement.execute(ProxyPreparedStatement.java:44)\n	at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.execute(HikariProxyPreparedStatement.java)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.logging.jdbc.PreparedStatementLogger.invoke(PreparedStatementLogger.java:59)\n	at com.sun.proxy.$Proxy297.execute(Unknown Source)\n	at org.apache.ibatis.executor.statement.PreparedStatementHandler.update(PreparedStatementHandler.java:47)\n	at org.apache.ibatis.executor.statement.RoutingStatementHandler.update(RoutingStatementHandler.java:74)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\n	at com.sun.proxy.$Proxy295.update(Unknown Source)\n	at com.baomidou.mybatisplus.core.executor.MybatisSimpleExecutor.doUpdate(MybatisSimpleExecutor.java:56)\n	at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:117)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.plugin.Invocation.proceed(Invocation.java:49)\n	at com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor.intercept(MybatisPlusInterceptor.java:83)\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:61)\n	at com.sun.proxy.$Proxy294.update(Unknown Source)\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:197)\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:184)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:426)\n	... 147 more\n', '2021-07-31 11:36:33', '2021-07-31 11:36:34', 202, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-31 03:36:33');
INSERT INTO `common_opt_log` VALUES (202, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'EX', '添加应用', 'com.wemirr.platform.authority.controller.ApplicationController', 'save', '/applications', 'POST', '[{\"accessTokenValidity\":43200,\"authorizedGrantTypes\":\"password\",\"clientId\":\"123123\",\"clientName\":\"123\",\"clientSecret\":\"123\",\"scope\":\"all\",\"status\":true,\"type\":0}]', NULL, '\n### Error updating database.  Cause: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n### The error may exist in com/wemirr/platform/authority/repository/OAuthClientDetailsMapper.java (best guess)\n### The error may involve com.wemirr.platform.authority.repository.OAuthClientDetailsMapper.insert-Inline\n### The error occurred while setting parameters\n### SQL: INSERT INTO sys_oauth_client_details (client_secret, client_name, scope, authorized_grant_types, access_token_validity, status, type) VALUES (?, ?, ?, ?, ?, ?, ?)\n### Cause: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n; Field \'client_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'client_id\' doesn\'t have a default value', 'org.springframework.dao.DataIntegrityViolationException: \n### Error updating database.  Cause: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n### The error may exist in com/wemirr/platform/authority/repository/OAuthClientDetailsMapper.java (best guess)\n### The error may involve com.wemirr.platform.authority.repository.OAuthClientDetailsMapper.insert-Inline\n### The error occurred while setting parameters\n### SQL: INSERT INTO sys_oauth_client_details (client_secret, client_name, scope, authorized_grant_types, access_token_validity, status, type) VALUES (?, ?, ?, ?, ?, ?, ?)\n### Cause: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n; Field \'client_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:251)\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:88)\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:440)\n	at com.sun.proxy.$Proxy166.insert(Unknown Source)\n	at org.mybatis.spring.SqlSessionTemplate.insert(SqlSessionTemplate.java:271)\n	at com.baomidou.mybatisplus.core.override.MybatisMapperMethod.execute(MybatisMapperMethod.java:60)\n	at com.baomidou.mybatisplus.core.override.MybatisMapperProxy$PlainMethodInvoker.invoke(MybatisMapperProxy.java:148)\n	at com.baomidou.mybatisplus.core.override.MybatisMapperProxy.invoke(MybatisMapperProxy.java:89)\n	at com.sun.proxy.$Proxy184.insert(Unknown Source)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:139)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\n	at com.sun.proxy.$Proxy185.insert(Unknown Source)\n	at com.baomidou.mybatisplus.extension.service.IService.save(IService.java:63)\n	at com.wemirr.framework.boot.service.impl.SuperServiceImpl.save(SuperServiceImpl.java:68)\n	at com.wemirr.framework.boot.service.impl.SuperServiceImpl$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:687)\n	at com.wemirr.platform.authority.service.impl.ApplicationServiceImpl$$EnhancerBySpringCGLIB$$1.save(<generated>)\n	at com.wemirr.platform.authority.controller.ApplicationController.save(ApplicationController.java:60)\n	at com.wemirr.platform.authority.controller.ApplicationController$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:55)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor.invoke(MethodBeforeAdviceInterceptor.java:56)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:95)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)\n	at com.wemirr.platform.authority.controller.ApplicationController$$EnhancerBySpringCGLIB$$1.save(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:517)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:584)\n	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)\n	at io.undertow.websockets.jsr.JsrWebSocketFilter.doFilter(JsrWebSocketFilter.java:173)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:126)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:90)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:118)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:158)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter.doFilter(OAuth2AuthenticationProcessingFilter.java:176)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:92)\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:77)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)\n	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)\n	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)\n	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)\n	at io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:68)\n	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:111)\n	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)\n	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)\n	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)\n	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)\n	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.jrHandle(ServletInitialHandler.java:40001)\n	at org.zeroturnaround.javarebel.integration.servlet.undertow.cbp.ServletInitialHandlerCBP.handleRequest(ServletInitialHandlerCBP.java:138)\n	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:269)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:133)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:130)\n	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)\n	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:249)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:99)\n	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:390)\n	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:836)\n	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)\n	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:2019)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1558)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1449)\n	at java.lang.Thread.run(Thread.java:748)\nCaused by: java.sql.SQLException: Field \'client_id\' doesn\'t have a default value\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129)\n	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:953)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.execute(ClientPreparedStatement.java:370)\n	at com.zaxxer.hikari.pool.ProxyPreparedStatement.execute(ProxyPreparedStatement.java:44)\n	at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.execute(HikariProxyPreparedStatement.java)\n	at sun.reflect.GeneratedMethodAccessor212.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.logging.jdbc.PreparedStatementLogger.invoke(PreparedStatementLogger.java:59)\n	at com.sun.proxy.$Proxy297.execute(Unknown Source)\n	at org.apache.ibatis.executor.statement.PreparedStatementHandler.update(PreparedStatementHandler.java:47)\n	at org.apache.ibatis.executor.statement.RoutingStatementHandler.update(RoutingStatementHandler.java:74)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\n	at com.sun.proxy.$Proxy295.update(Unknown Source)\n	at com.baomidou.mybatisplus.core.executor.MybatisSimpleExecutor.doUpdate(MybatisSimpleExecutor.java:56)\n	at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:117)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.plugin.Invocation.proceed(Invocation.java:49)\n	at com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor.intercept(MybatisPlusInterceptor.java:83)\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:61)\n	at com.sun.proxy.$Proxy294.update(Unknown Source)\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:197)\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:184)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:426)\n	... 147 more\n', '2021-07-31 11:36:56', '2021-07-31 11:37:15', 18307, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-31 03:37:14');
INSERT INTO `common_opt_log` VALUES (203, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加应用', 'com.wemirr.platform.authority.controller.ApplicationController', 'save', '/applications', 'POST', '[{\"accessTokenValidity\":43200,\"authorizedGrantTypes\":\"password\",\"clientId\":\"123123\",\"clientName\":\"123\",\"clientSecret\":\"123\",\"scope\":\"all\",\"status\":true,\"type\":0}]', NULL, NULL, NULL, '2021-07-31 11:38:27', '2021-07-31 11:38:27', 57, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-31 03:38:26');
INSERT INTO `common_opt_log` VALUES (204, '127.0.0.1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除应用', 'com.wemirr.platform.authority.controller.ApplicationController', 'del', '/applications/123123', 'DELETE', '[123123]', NULL, NULL, NULL, '2021-07-31 11:38:31', '2021-07-31 11:38:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', '92.0.4515.107', 1, '长风一梦', '2021-07-31 03:38:31');
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
) ENGINE=InnoDB AUTO_INCREMENT=1030105 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES (1, '系统管理', ',', 'system:view', 0, '/system/management', 'Layout', 1, 'ant-design:dashboard-filled', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 09:39:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (2, '开发平台', ',', 'development:view', 0, '/development', 'Layout', 1, 'ant-design:appstore-add-outlined', NULL, 1, b'1', b'0', b'0', b'1', '运维监控', NULL, NULL, '2020-11-09 09:49:52', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (3, '消息平台', ',', NULL, 0, '/message', 'Layout', 1, 'bx-bx-message-alt-dots', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-02 09:48:51', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (99, '首页', ',', NULL, 0, '/dashboard/home', '/dashboard/home/index', 0, 'ant-design:home-outlined', NULL, 1, b'1', b'0', b'0', b'0', '', 1, '长风一梦', '2021-07-27 09:12:30', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (101, '用户中心', ',1', 'user:info:view', 1, '/system/center', 'Layout', 0, 'ant-design:user-outlined', NULL, 1, b'1', b'0', b'0', b'1', '用户中心', 1, NULL, '2019-07-25 16:11:00', 2, '不告诉你', '2019-11-11 14:28:40');
INSERT INTO `sys_resource` VALUES (102, '权限管理', ',1', 'auth:view', 1, '/system/auth', NULL, 1, 'ant-design:security-scan-outlined', NULL, 1, b'1', b'0', b'0', b'1', '权限管理', 1, NULL, '2019-07-25 16:11:41', 3, NULL, '2019-11-11 14:28:43');
INSERT INTO `sys_resource` VALUES (103, '基础数据', ',1', 'basic:view', 1, '/system/basic', NULL, 2, 'file-icons:config-coffeescript', NULL, 1, b'1', b'0', b'0', b'1', '基础配置', 1, NULL, '2019-07-25 16:13:09', 2, '不告诉你', '2019-11-11 14:28:49');
INSERT INTO `sys_resource` VALUES (108, '租户管理', ',1,101', 'tenant:management', 1, '/system/tenant', '/wemirr/management/tenant/index', 8, 'clarity-thin-client-line', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-30 09:11:36', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (109, '应用管理', ',1,101', 'application:management', 1, '/system/application', '/wemirr/management/application/index', 9, 'carbon-application', NULL, 1, b'1', b'0', b'0', b'1', '', 1, '长风一梦', '2021-07-30 09:10:07', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (201, '日志管理', ',2', 'log:view', 2, '/development/log', NULL, 1, 'radix-icons:activity-log', NULL, 1, b'1', b'0', b'0', b'1', '日志管理', NULL, NULL, '2020-11-12 04:51:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (202, '网关管理', ',2', 'system:gateway:view', 2, '/development/gateway', NULL, 2, 'ant-design:gateway-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 02:38:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (203, '监控中心', ',2', 'development:view', 2, '/system/monitor', 'http://localhost:9000/monitor', 3, 'eos-icons:monitoring', NULL, 1, b'1', b'0', b'0', b'1', '监控中心', NULL, NULL, '2020-11-09 09:55:04', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (210, '注册中心', ',2', 'service:governance:nacos:view', 2, 'http://localhost:8848/nacos', '/system/development/nacos', 100, 'simple-icons:theregister', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 05:42:36', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (220, '服务治理', ',2', 'nepxion:platform', 2, 'http://218.78.55.64:6001/platform', '', 102, 'ri-government-fill', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-12 08:09:34', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (301, '我的消息', ',3', 'message:my', 3, '/message/my', '/wemirr/message/my/index', 1, 'wpf-my-topic', NULL, 1, b'1', b'0', b'0', b'1', '我的消息', NULL, NULL, '2020-11-19 02:50:54', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (302, '发布消息', ',3', 'message:publish', 3, '/message/publish', '/wemirr/message/publish/index', 1, 'codicon-repo-push', NULL, 1, b'1', b'0', b'0', b'1', '发布消息', NULL, NULL, '2020-11-19 02:48:22', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10101, '用户管理', ',1,101', 'user:management', 101, '/system/user', '/wemirr/management/user/index', 1, 'heroicons-outline:user-group', NULL, 1, b'1', b'0', b'0', b'1', '用户管理', NULL, NULL, '2020-11-09 07:50:53', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (10102, '组织管理', ',1,101', 'org:view', 101, '/system/org', '/wemirr/management/org/index', 1, 'codicon-organization', NULL, 1, b'1', b'0', b'0', b'1', '组织管理', NULL, NULL, '2020-11-09 07:51:00', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10103, '岗位管理', ',1,101', 'station:management', 101, '/system/station', '/wemirr/management/station/index', 1, 'eos-icons:job', NULL, 1, b'1', b'0', b'0', b'1', '岗位管理', NULL, NULL, '2020-11-09 07:51:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10201, '菜单管理', ',1,102', 'menu:view', 102, '/system/menu', '/wemirr/management/auth/menu/index', 1, 'ant-design:menu-unfold-outlined', NULL, 1, b'1', b'0', b'0', b'1', '菜单管理', NULL, NULL, '2020-11-09 07:51:37', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (10202, '角色管理', ',1,102', 'role:management', 102, '/system/role', '/wemirr/management/auth/role/index', 1, 'eos-icons:role-binding-outlined', NULL, 1, b'1', b'0', b'0', b'1', '角色管理', NULL, NULL, '2020-11-09 07:51:41', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10301, '数据字典', ',1,103', 'dict:management', 103, '/system/dictionary', '/wemirr/management/basic/dictionary/index', 1, 'bx-bxs-data', NULL, 1, b'1', b'0', b'0', b'1', '数据字典', NULL, NULL, '2020-11-09 07:52:59', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10302, '地区信息', ',1,103', 'area:view', 103, '/system/area', '/wemirr/management/basic/area/index', 1, 'ant-design:area-chart-outlined', NULL, 1, b'1', b'0', b'0', b'1', '地区信息', NULL, NULL, '2020-11-09 07:53:12', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20101, '登录日志', ',2,201', 'log:login', 201, '/development/log/login', '/wemirr/development/log/login/index', 1, 'carbon-login', NULL, 1, b'1', b'0', b'0', b'1', '登录日志', NULL, NULL, '2020-11-09 09:50:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20102, '操作日志', ',2,201', 'log:opt', 201, '/development/log/opt', '/wemirr/development/log/opt/index', 2, 'carbon-operation', NULL, 1, b'1', b'0', b'0', b'1', '操作日志', NULL, NULL, '2020-11-09 09:50:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20201, '限流规则', ',2,202', 'development:gateway:limit', 202, '/development/gateway/limit', '/wemirr/development/gateway/limit/index', 1, 'carbon-rule', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 02:38:51', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20202, '限访名单', ',2,201', 'development:gateway:blacklist', 202, '/development/gateway/blacklist', '/wemirr/development/gateway/blacklist/index', 2, 'carbon-ai-status-rejected', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2020-11-20 02:39:02', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20203, '网关路由', ',2,201', 'development:gateway:route', 202, '/development/gateway/route', '/wemirr/development/gateway/route/index', 3, 'mdi-router-wireless-settings', NULL, 1, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-02-02 07:52:26', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (30101, '发布', ',3,301', '30101', 301, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-06 04:02:51', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (30102, '测试', ',3,301', '30102', 301, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-06 04:05:11', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010101, '添加', ',1,101,10101', 'user:management:add', 10101, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-05 10:12:11', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (1010102, '编辑', ',1,101,10101', 'user:management:edit', 10101, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-05 10:13:50', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (1010104, '删除', ',1,101,10101', 'user:management:remove', 10101, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-05 14:25:09', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (1010301, '添加', ',1,101,10103', 'station:management:add', 10103, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:46:49', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010302, '编辑', ',1,101,10103', 'station:management:edit', 10103, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:47:31', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1010303, '删除', ',1,101,10103', 'station:management:remove', 10103, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:47:34', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020201, '添加', ',1,102,10202', 'role:management:add', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:56:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020202, '编辑', ',1,102,10202', 'role:management:edit', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:56:40', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020203, '删除', ',1,102,10202', 'role:management:remove', 10202, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 09:56:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020204, '分配用户', ',1,102,10202', 'role:management:distribution_user', 0, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 10:02:27', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1020205, '分配权限', ',1,102,10202', 'role:management:distribution_res', 0, '', NULL, 1, '', NULL, 2, b'1', b'0', b'0', b'1', '', NULL, NULL, '2021-07-21 10:02:36', NULL, NULL, NULL);
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
INSERT INTO `sys_role_res` VALUES (1, 1, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 2, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 3, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 101, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 102, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 103, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 108, '2021-07-30 09:44:24');
INSERT INTO `sys_role_res` VALUES (1, 109, '2021-07-30 09:44:29');
INSERT INTO `sys_role_res` VALUES (1, 201, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 202, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 203, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 210, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 220, '2021-07-12 08:10:25');
INSERT INTO `sys_role_res` VALUES (1, 301, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 302, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 10101, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 10102, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 10103, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 10201, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 10202, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 10301, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 10302, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 20101, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 20102, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 20201, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 20202, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 20203, '2021-07-04 13:54:18');
INSERT INTO `sys_role_res` VALUES (1, 30101, '2021-07-06 04:04:41');
INSERT INTO `sys_role_res` VALUES (1, 30102, '2021-07-06 04:05:00');
INSERT INTO `sys_role_res` VALUES (1, 1010101, '2021-07-05 10:13:22');
INSERT INTO `sys_role_res` VALUES (1, 1010102, '2021-07-05 10:13:55');
INSERT INTO `sys_role_res` VALUES (1, 1010104, '2021-07-05 14:25:29');
INSERT INTO `sys_role_res` VALUES (1, 1010301, '2021-07-21 09:48:28');
INSERT INTO `sys_role_res` VALUES (1, 1010302, '2021-07-21 09:48:33');
INSERT INTO `sys_role_res` VALUES (1, 1010303, '2021-07-21 09:48:36');
INSERT INTO `sys_role_res` VALUES (1, 1020201, '2021-07-21 09:57:47');
INSERT INTO `sys_role_res` VALUES (1, 1020202, '2021-07-21 09:57:51');
INSERT INTO `sys_role_res` VALUES (1, 1020203, '2021-07-21 09:57:53');
INSERT INTO `sys_role_res` VALUES (1, 1020204, '2021-07-21 10:08:21');
INSERT INTO `sys_role_res` VALUES (1, 1020205, '2021-07-21 10:08:24');
INSERT INTO `sys_role_res` VALUES (1, 1030101, '2021-07-21 09:55:35');
INSERT INTO `sys_role_res` VALUES (1, 1030102, '2021-07-21 09:55:39');
INSERT INTO `sys_role_res` VALUES (1, 1030103, '2021-07-21 09:55:41');
INSERT INTO `sys_role_res` VALUES (1, 1030104, '2021-07-30 09:10:07');
INSERT INTO `sys_role_res` VALUES (1, 12121213, '2021-07-21 08:12:28');
INSERT INTO `sys_role_res` VALUES (2, 2, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 3, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 201, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 202, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 203, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 210, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 220, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 301, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 302, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 20101, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 20102, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 20201, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 20202, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 20203, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 30101, '2021-07-28 03:00:15');
INSERT INTO `sys_role_res` VALUES (2, 30102, '2021-07-28 03:00:15');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 1, 'admin', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '长风一梦', 100, 100, b'0', '1837307557@qq.com', '13002171912', '111111111', 1, b'1', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '12323', 'mz_daiz', 'SUOSHI', 'WORKING', '2020-11-05', 0, NULL, '2020-10-16 03:25:36', 1, '长风一梦', '2021-07-21 05:21:20');
INSERT INTO `t_user` VALUES (2, 1, 'nankeyimeng', '{bcrypt}$2a$10$RjxqgHEAdKysnrmB1dteru42i553UOdpJo0BLjy8N671vqGRP557S', '南柯一梦', 101, 101, b'0', '1837307557@qq.com', '13002171912', '111111111', 1, b'1', '/api/upload/form/download?key=file/2021/7/15/18308224025036.png', '描述信息吧', 'mz_acz', 'COLLEGE', 'LEAVE', '2020-11-05', 0, NULL, '2020-10-16 03:26:18', 1, '管理员', '2021-07-21 05:17:08');
INSERT INTO `t_user` VALUES (3, 2, 'chengfengyimeng', '{bcrypt}$2a$10$RjxqgHEAdKysnrmB1dteru42i553UOdpJo0BLjy8N671vqGRP557S', '唐城一梦', 102, 108, b'0', '1837307557@qq.com', '13002171912', '111111111', 2, b'1', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '描述信息吧', 'mz_daz', 'COLLEGE', 'LEAVE', '2020-11-05', 0, NULL, '2020-10-16 06:10:49', 1, '管理员', '2021-07-21 05:17:11');
INSERT INTO `t_user` VALUES (8, 1, 'tangyafeng', '123456', '禁止操作演示环境的核心数据', 100, 100, b'0', '1837307557@qq.com', '13002171912', NULL, 1, b'0', 'http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/27/51998836180857.png', NULL, NULL, NULL, 'QUIT', NULL, 1, '长风一梦', '2021-07-27 05:34:07', 1, '长风一梦', '2021-07-27 05:36:31');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
