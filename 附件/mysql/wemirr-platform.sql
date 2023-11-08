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

 Date: 07/10/2023 11:14:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for common_area
-- ----------------------------
DROP TABLE IF EXISTS `common_area`;
CREATE TABLE `common_area` (
  `id` int DEFAULT NULL COMMENT 'ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `parent_id` int DEFAULT NULL COMMENT '父ID',
  `level` tinyint DEFAULT NULL COMMENT '级别',
  `longitude` decimal(10,3) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,3) DEFAULT NULL COMMENT '纬度',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据 来源',
  `sequence` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '排序',
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态0：未删除，1：已删除',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  KEY `idx_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='省市区详情表';

-- ----------------------------
-- Records of common_area
-- ----------------------------
BEGIN;
INSERT INTO `common_area` VALUES (110000, '北京市', 0, 1, 116.408, 39.904, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:50', 1, '长风一梦', '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120000, '天津市', 0, 1, 117.201, 39.084, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130000, '河北省', 0, 1, 114.469, 38.037, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140000, '山西省', 0, 1, 112.562, 37.874, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150000, '内蒙古自治区', 0, 1, 111.766, 40.817, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210000, '辽宁省', 0, 1, 123.429, 41.835, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220000, '吉林省', 0, 1, 125.326, 43.897, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230000, '黑龙江省', 0, 1, 126.662, 45.742, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310000, '上海市', 0, 1, 121.474, 31.230, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320000, '江苏省', 0, 1, 118.763, 32.062, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330000, '浙江省', 0, 1, 120.153, 30.267, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340000, '安徽省', 0, 1, 117.285, 31.861, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350000, '福建省', 0, 1, 119.295, 26.101, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360000, '江西省', 0, 1, 115.909, 28.676, 'https://github.com/modood/Administrative-divisions-of-China', '36', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370000, '山东省', 0, 1, 117.020, 36.669, 'https://github.com/modood/Administrative-divisions-of-China', '37', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410000, '河南省', 0, 1, 113.754, 34.766, 'https://github.com/modood/Administrative-divisions-of-China', '41', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420000, '湖北省', 0, 1, 114.342, 30.546, 'https://github.com/modood/Administrative-divisions-of-China', '42', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430000, '湖南省', 0, 1, 112.984, 28.112, 'https://github.com/modood/Administrative-divisions-of-China', '43', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440000, '广东省', 0, 1, 113.267, 23.132, 'https://github.com/modood/Administrative-divisions-of-China', '44', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450000, '广西壮族自治区', 0, 1, 108.328, 22.815, 'https://github.com/modood/Administrative-divisions-of-China', '45', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460000, '海南省', 0, 1, 110.349, 20.017, 'https://github.com/modood/Administrative-divisions-of-China', '46', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500000, '重庆市', 0, 1, 106.552, 29.563, 'https://github.com/modood/Administrative-divisions-of-China', '50', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510000, '四川省', 0, 1, 104.076, 30.652, 'https://github.com/modood/Administrative-divisions-of-China', '51', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520000, '贵州省', 0, 1, 106.707, 26.598, 'https://github.com/modood/Administrative-divisions-of-China', '52', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530000, '云南省', 0, 1, 102.710, 25.046, 'https://github.com/modood/Administrative-divisions-of-China', '53', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540000, '西藏自治区', 0, 1, 91.117, 29.647, 'https://github.com/modood/Administrative-divisions-of-China', '54', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610000, '陕西省', 0, 1, 108.954, 34.265, 'https://github.com/modood/Administrative-divisions-of-China', '61', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620000, '甘肃省', 0, 1, 103.826, 36.059, 'https://github.com/modood/Administrative-divisions-of-China', '62', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630000, '青海省', 0, 1, 101.780, 36.621, 'https://github.com/modood/Administrative-divisions-of-China', '63', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640000, '宁夏回族自治区', 0, 1, 106.259, 38.471, 'https://github.com/modood/Administrative-divisions-of-China', '64', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650000, '新疆维吾尔自治区', 0, 1, 87.628, 43.793, 'https://github.com/modood/Administrative-divisions-of-China', '65', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110100, '市辖区', 110000, 2, 116.408, 39.904, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120100, '市辖区', 120000, 2, 117.201, 39.084, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130100, '石家庄市', 130000, 2, 114.515, 38.042, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130200, '唐山市', 130000, 2, 118.180, 39.631, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130300, '秦皇岛市', 130000, 2, 119.600, 39.935, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130400, '邯郸市', 130000, 2, 114.539, 36.626, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130500, '邢台市', 130000, 2, 114.505, 37.071, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130600, '保定市', 130000, 2, 115.465, 38.874, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130700, '张家口市', 130000, 2, 114.888, 40.824, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130800, '承德市', 130000, 2, 117.962, 40.954, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130900, '沧州市', 130000, 2, 116.839, 38.304, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131000, '廊坊市', 130000, 2, 116.684, 39.538, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131100, '衡水市', 130000, 2, 115.670, 37.739, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140100, '太原市', 140000, 2, 112.549, 37.871, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140200, '大同市', 140000, 2, 113.300, 40.077, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140300, '阳泉市', 140000, 2, 113.581, 37.857, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140400, '长治市', 140000, 2, 113.116, 36.195, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140500, '晋城市', 140000, 2, 112.852, 35.491, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140600, '朔州市', 140000, 2, 112.433, 39.332, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140700, '晋中市', 140000, 2, 112.753, 37.687, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140800, '运城市', 140000, 2, 111.008, 35.026, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140900, '忻州市', 140000, 2, 112.734, 38.417, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141000, '临汾市', 140000, 2, 111.519, 36.088, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141100, '吕梁市', 140000, 2, 111.144, 37.518, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150100, '呼和浩特市', 150000, 2, 111.749, 40.843, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150200, '包头市', 150000, 2, 109.840, 40.657, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150300, '乌海市', 150000, 2, 106.794, 39.655, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150400, '赤峰市', 150000, 2, 118.887, 42.258, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150500, '通辽市', 150000, 2, 122.243, 43.653, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150600, '鄂尔多斯市', 150000, 2, 109.781, 39.608, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150700, '呼伦贝尔市', 150000, 2, 119.766, 49.212, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150800, '巴彦淖尔市', 150000, 2, 107.388, 40.743, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150900, '乌兰察布市', 150000, 2, 113.133, 40.995, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152200, '兴安盟', 150000, 2, 122.067, 46.078, 'https://github.com/modood/Administrative-divisions-of-China', '2200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152500, '锡林郭勒盟', 150000, 2, 116.048, 43.933, 'https://github.com/modood/Administrative-divisions-of-China', '2500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152900, '阿拉善盟', 150000, 2, 105.729, 38.852, 'https://github.com/modood/Administrative-divisions-of-China', '2900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210100, '沈阳市', 210000, 2, 123.431, 41.806, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210200, '大连市', 210000, 2, 121.615, 38.914, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210300, '鞍山市', 210000, 2, 122.994, 41.109, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210400, '抚顺市', 210000, 2, 123.957, 41.881, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210500, '本溪市', 210000, 2, 123.766, 41.294, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210600, '丹东市', 210000, 2, 124.355, 40.000, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210700, '锦州市', 210000, 2, 121.127, 41.095, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210800, '营口市', 210000, 2, 122.235, 40.667, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210900, '阜新市', 210000, 2, 121.670, 42.022, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211000, '辽阳市', 210000, 2, 123.237, 41.267, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211100, '盘锦市', 210000, 2, 122.071, 41.120, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211200, '铁岭市', 210000, 2, 123.726, 42.224, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211300, '朝阳市', 210000, 2, 120.450, 41.574, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211400, '葫芦岛市', 210000, 2, 120.837, 40.711, 'https://github.com/modood/Administrative-divisions-of-China', '1400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220100, '长春市', 220000, 2, 125.324, 43.817, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220200, '吉林市', 220000, 2, 126.550, 43.838, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220300, '四平市', 220000, 2, 124.350, 43.166, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220400, '辽源市', 220000, 2, 125.144, 42.888, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220500, '通化市', 220000, 2, 125.940, 41.728, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220600, '白山市', 220000, 2, 126.424, 41.940, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220700, '松原市', 220000, 2, 124.825, 45.142, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220800, '白城市', 220000, 2, 122.839, 45.620, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (222400, '延边朝鲜族自治州', 220000, 2, 129.509, 42.891, 'https://github.com/modood/Administrative-divisions-of-China', '2400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230100, '哈尔滨市', 230000, 2, 126.535, 45.804, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230200, '齐齐哈尔市', 230000, 2, 123.918, 47.354, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230300, '鸡西市', 230000, 2, 130.969, 45.295, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230400, '鹤岗市', 230000, 2, 130.298, 47.350, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230500, '双鸭山市', 230000, 2, 131.159, 46.647, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230600, '大庆市', 230000, 2, 125.104, 46.589, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230700, '伊春市', 230000, 2, 128.841, 47.728, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230800, '佳木斯市', 230000, 2, 130.319, 46.800, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230900, '七台河市', 230000, 2, 131.003, 45.772, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231000, '牡丹江市', 230000, 2, 129.633, 44.552, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231100, '黑河市', 230000, 2, 127.529, 50.245, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231200, '绥化市', 230000, 2, 126.969, 46.654, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (232700, '大兴安岭地区', 230000, 2, 124.711, 52.335, 'https://github.com/modood/Administrative-divisions-of-China', '2700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310100, '市辖区', 310000, 2, 121.474, 31.230, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320100, '南京市', 320000, 2, 118.797, 32.060, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320200, '无锡市', 320000, 2, 120.312, 31.491, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320300, '徐州市', 320000, 2, 117.284, 34.206, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320400, '常州市', 320000, 2, 119.974, 31.811, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320500, '苏州市', 320000, 2, 120.585, 31.299, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320600, '南通市', 320000, 2, 120.894, 31.980, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320700, '连云港市', 320000, 2, 119.222, 34.597, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320800, '淮安市', 320000, 2, 119.015, 33.610, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320900, '盐城市', 320000, 2, 120.164, 33.347, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321000, '扬州市', 320000, 2, 119.413, 32.394, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321100, '镇江市', 320000, 2, 119.426, 32.188, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321200, '泰州市', 320000, 2, 119.923, 32.456, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321300, '宿迁市', 320000, 2, 118.275, 33.963, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330100, '杭州市', 330000, 2, 120.210, 30.246, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330200, '宁波市', 330000, 2, 121.550, 29.875, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330300, '温州市', 330000, 2, 120.699, 27.994, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330400, '嘉兴市', 330000, 2, 120.755, 30.746, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330500, '湖州市', 330000, 2, 120.087, 30.894, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330600, '绍兴市', 330000, 2, 120.580, 30.030, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330700, '金华市', 330000, 2, 119.647, 29.079, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330800, '衢州市', 330000, 2, 118.859, 28.970, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330900, '舟山市', 330000, 2, 122.207, 29.985, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331000, '台州市', 330000, 2, 121.421, 28.656, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331100, '丽水市', 330000, 2, 119.923, 28.468, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340100, '合肥市', 340000, 2, 117.227, 31.821, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340200, '芜湖市', 340000, 2, 118.433, 31.353, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340300, '蚌埠市', 340000, 2, 117.390, 32.916, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340400, '淮南市', 340000, 2, 117.000, 32.625, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340500, '马鞍山市', 340000, 2, 118.507, 31.670, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340600, '淮北市', 340000, 2, 116.798, 33.956, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340700, '铜陵市', 340000, 2, 117.812, 30.945, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340800, '安庆市', 340000, 2, 117.064, 30.543, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341000, '黄山市', 340000, 2, 118.337, 29.715, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341100, '滁州市', 340000, 2, 118.317, 32.302, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341200, '阜阳市', 340000, 2, 115.814, 32.890, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341300, '宿州市', 340000, 2, 116.964, 33.646, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341500, '六安市', 340000, 2, 116.522, 31.734, 'https://github.com/modood/Administrative-divisions-of-China', '1500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341600, '亳州市', 340000, 2, 115.779, 33.845, 'https://github.com/modood/Administrative-divisions-of-China', '1600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341700, '池州市', 340000, 2, 117.492, 30.665, 'https://github.com/modood/Administrative-divisions-of-China', '1700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341800, '宣城市', 340000, 2, 118.759, 30.941, 'https://github.com/modood/Administrative-divisions-of-China', '1800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350100, '福州市', 350000, 2, 119.296, 26.075, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350200, '厦门市', 350000, 2, 118.089, 24.480, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350300, '莆田市', 350000, 2, 119.008, 25.454, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350400, '三明市', 350000, 2, 117.639, 26.263, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350500, '泉州市', 350000, 2, 118.676, 24.874, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350600, '漳州市', 350000, 2, 117.647, 24.513, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350700, '南平市', 350000, 2, 118.178, 26.642, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350800, '龙岩市', 350000, 2, 117.018, 25.075, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350900, '宁德市', 350000, 2, 119.548, 26.666, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360100, '南昌市', 360000, 2, 115.858, 28.683, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360200, '景德镇市', 360000, 2, 117.178, 29.269, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360300, '萍乡市', 360000, 2, 113.855, 27.623, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360400, '九江市', 360000, 2, 116.002, 29.705, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360500, '新余市', 360000, 2, 114.917, 27.818, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360600, '鹰潭市', 360000, 2, 117.069, 28.260, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360700, '赣州市', 360000, 2, 114.935, 25.832, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360800, '吉安市', 360000, 2, 114.993, 27.113, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360900, '宜春市', 360000, 2, 114.417, 27.816, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361000, '抚州市', 360000, 2, 116.358, 27.949, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361100, '上饶市', 360000, 2, 117.943, 28.455, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370100, '济南市', 370000, 2, 117.120, 36.651, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370200, '青岛市', 370000, 2, 120.383, 36.067, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370300, '淄博市', 370000, 2, 118.055, 36.813, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370400, '枣庄市', 370000, 2, 117.324, 34.810, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370500, '东营市', 370000, 2, 118.675, 37.435, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370600, '烟台市', 370000, 2, 121.448, 37.464, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370700, '潍坊市', 370000, 2, 119.162, 36.707, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370800, '济宁市', 370000, 2, 116.587, 35.415, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370900, '泰安市', 370000, 2, 117.088, 36.200, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371000, '威海市', 370000, 2, 122.120, 37.513, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371100, '日照市', 370000, 2, 119.527, 35.416, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371300, '临沂市', 370000, 2, 118.356, 35.105, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371400, '德州市', 370000, 2, 116.357, 37.434, 'https://github.com/modood/Administrative-divisions-of-China', '1400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371500, '聊城市', 370000, 2, 115.985, 36.457, 'https://github.com/modood/Administrative-divisions-of-China', '1500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371600, '滨州市', 370000, 2, 117.971, 37.382, 'https://github.com/modood/Administrative-divisions-of-China', '1600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371700, '菏泽市', 370000, 2, 115.481, 35.234, 'https://github.com/modood/Administrative-divisions-of-China', '1700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410100, '郑州市', 410000, 2, 113.625, 34.747, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410200, '开封市', 410000, 2, 114.308, 34.797, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410300, '洛阳市', 410000, 2, 112.454, 34.620, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410400, '平顶山市', 410000, 2, 113.193, 33.766, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410500, '安阳市', 410000, 2, 114.392, 36.098, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410600, '鹤壁市', 410000, 2, 114.297, 35.747, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410700, '新乡市', 410000, 2, 113.927, 35.303, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410800, '焦作市', 410000, 2, 113.242, 35.216, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410900, '濮阳市', 410000, 2, 115.029, 35.762, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411000, '许昌市', 410000, 2, 113.853, 34.036, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411100, '漯河市', 410000, 2, 114.017, 33.581, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411200, '三门峡市', 410000, 2, 111.200, 34.772, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411300, '南阳市', 410000, 2, 112.528, 32.991, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411400, '商丘市', 410000, 2, 115.656, 34.414, 'https://github.com/modood/Administrative-divisions-of-China', '1400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411500, '信阳市', 410000, 2, 114.091, 32.147, 'https://github.com/modood/Administrative-divisions-of-China', '1500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411600, '周口市', 410000, 2, 114.697, 33.626, 'https://github.com/modood/Administrative-divisions-of-China', '1600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411700, '驻马店市', 410000, 2, 114.022, 33.012, 'https://github.com/modood/Administrative-divisions-of-China', '1700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (419000, '省直辖县级行政区划', 410000, 2, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '9000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420100, '武汉市', 420000, 2, 114.305, 30.593, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420200, '黄石市', 420000, 2, 115.039, 30.200, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420300, '十堰市', 420000, 2, 110.798, 32.629, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420500, '宜昌市', 420000, 2, 111.286, 30.692, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420600, '襄阳市', 420000, 2, 112.122, 32.009, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420700, '鄂州市', 420000, 2, 114.895, 30.392, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420800, '荆门市', 420000, 2, 112.199, 31.035, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420900, '孝感市', 420000, 2, 113.917, 30.925, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421000, '荆州市', 420000, 2, 112.240, 30.335, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421100, '黄冈市', 420000, 2, 114.872, 30.454, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421200, '咸宁市', 420000, 2, 114.322, 29.841, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421300, '随州市', 420000, 2, 113.382, 31.690, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (422800, '恩施土家族苗族自治州', 420000, 2, 109.488, 30.272, 'https://github.com/modood/Administrative-divisions-of-China', '2800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (429000, '省直辖县级行政区划', 420000, 2, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '9000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430100, '长沙市', 430000, 2, 112.939, 28.228, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430200, '株洲市', 430000, 2, 113.134, 27.828, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430300, '湘潭市', 430000, 2, 112.944, 27.830, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430400, '衡阳市', 430000, 2, 112.572, 26.893, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430500, '邵阳市', 430000, 2, 111.468, 27.239, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430600, '岳阳市', 430000, 2, 113.129, 29.357, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430700, '常德市', 430000, 2, 111.698, 29.032, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430800, '张家界市', 430000, 2, 110.479, 29.117, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430900, '益阳市', 430000, 2, 112.355, 28.554, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431000, '郴州市', 430000, 2, 113.015, 25.771, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:50', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431100, '永州市', 430000, 2, 111.613, 26.420, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431200, '怀化市', 430000, 2, 109.998, 27.555, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431300, '娄底市', 430000, 2, 111.993, 27.700, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (433100, '湘西土家族苗族自治州', 430000, 2, 109.739, 28.312, 'https://github.com/modood/Administrative-divisions-of-China', '3100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440100, '广州市', 440000, 2, 113.264, 23.129, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440200, '韶关市', 440000, 2, 113.598, 24.810, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440300, '深圳市', 440000, 2, 114.058, 22.543, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440400, '珠海市', 440000, 2, 113.577, 22.271, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440500, '汕头市', 440000, 2, 116.682, 23.354, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440600, '佛山市', 440000, 2, 113.121, 23.022, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440700, '江门市', 440000, 2, 113.082, 22.579, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440800, '湛江市', 440000, 2, 110.359, 21.271, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440900, '茂名市', 440000, 2, 110.925, 21.663, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441200, '肇庆市', 440000, 2, 112.465, 23.047, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441300, '惠州市', 440000, 2, 114.416, 23.112, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441400, '梅州市', 440000, 2, 116.122, 24.289, 'https://github.com/modood/Administrative-divisions-of-China', '1400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441500, '汕尾市', 440000, 2, 115.375, 22.786, 'https://github.com/modood/Administrative-divisions-of-China', '1500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441600, '河源市', 440000, 2, 114.700, 23.744, 'https://github.com/modood/Administrative-divisions-of-China', '1600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441700, '阳江市', 440000, 2, 111.982, 21.858, 'https://github.com/modood/Administrative-divisions-of-China', '1700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441800, '清远市', 440000, 2, 113.056, 23.682, 'https://github.com/modood/Administrative-divisions-of-China', '1800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900, '东莞市', 440000, 2, 113.752, 23.021, 'https://github.com/modood/Administrative-divisions-of-China', '1900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000, '中山市', 440000, 2, 113.393, 22.518, 'https://github.com/modood/Administrative-divisions-of-China', '2000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445100, '潮州市', 440000, 2, 116.623, 23.657, 'https://github.com/modood/Administrative-divisions-of-China', '5100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445200, '揭阳市', 440000, 2, 116.373, 23.550, 'https://github.com/modood/Administrative-divisions-of-China', '5200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445300, '云浮市', 440000, 2, 112.044, 22.915, 'https://github.com/modood/Administrative-divisions-of-China', '5300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450100, '南宁市', 450000, 2, 108.367, 22.817, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450200, '柳州市', 450000, 2, 109.416, 24.326, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450300, '桂林市', 450000, 2, 110.290, 25.274, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450400, '梧州市', 450000, 2, 111.279, 23.477, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450500, '北海市', 450000, 2, 109.120, 21.481, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450600, '防城港市', 450000, 2, 108.354, 21.687, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450700, '钦州市', 450000, 2, 108.654, 21.980, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450800, '贵港市', 450000, 2, 109.599, 23.112, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450900, '玉林市', 450000, 2, 110.165, 22.636, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451000, '百色市', 450000, 2, 106.618, 23.902, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451100, '贺州市', 450000, 2, 111.567, 24.404, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451200, '河池市', 450000, 2, 108.085, 24.693, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451300, '来宾市', 450000, 2, 109.221, 23.750, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451400, '崇左市', 450000, 2, 107.365, 22.377, 'https://github.com/modood/Administrative-divisions-of-China', '1400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460100, '海口市', 460000, 2, 110.198, 20.044, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460200, '三亚市', 460000, 2, 109.512, 18.253, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460300, '三沙市', 460000, 2, 112.339, 16.832, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400, '儋州市', 460000, 2, 109.581, 19.521, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469000, '省直辖县级行政区划', 460000, 2, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '9000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500100, '市辖区', 500000, 2, 106.552, 29.563, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500200, '县', 500000, 2, 106.552, 29.563, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510100, '成都市', 510000, 2, 104.067, 30.572, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510300, '自贡市', 510000, 2, 104.778, 29.339, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510400, '攀枝花市', 510000, 2, 101.719, 26.582, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510500, '泸州市', 510000, 2, 105.442, 28.872, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510600, '德阳市', 510000, 2, 104.398, 31.127, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510700, '绵阳市', 510000, 2, 104.679, 31.467, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510800, '广元市', 510000, 2, 105.843, 32.435, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510900, '遂宁市', 510000, 2, 105.593, 30.533, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511000, '内江市', 510000, 2, 105.058, 29.580, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511100, '乐山市', 510000, 2, 103.766, 29.552, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511300, '南充市', 510000, 2, 106.111, 30.838, 'https://github.com/modood/Administrative-divisions-of-China', '1300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511400, '眉山市', 510000, 2, 103.849, 30.075, 'https://github.com/modood/Administrative-divisions-of-China', '1400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511500, '宜宾市', 510000, 2, 104.643, 28.752, 'https://github.com/modood/Administrative-divisions-of-China', '1500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511600, '广安市', 510000, 2, 106.633, 30.456, 'https://github.com/modood/Administrative-divisions-of-China', '1600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511700, '达州市', 510000, 2, 107.468, 31.210, 'https://github.com/modood/Administrative-divisions-of-China', '1700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511800, '雅安市', 510000, 2, 103.013, 29.981, 'https://github.com/modood/Administrative-divisions-of-China', '1800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511900, '巴中市', 510000, 2, 106.747, 31.868, 'https://github.com/modood/Administrative-divisions-of-China', '1900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (512000, '资阳市', 510000, 2, 104.628, 30.129, 'https://github.com/modood/Administrative-divisions-of-China', '2000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513200, '阿坝藏族羌族自治州', 510000, 2, 102.225, 31.899, 'https://github.com/modood/Administrative-divisions-of-China', '3200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513300, '甘孜藏族自治州', 510000, 2, 101.962, 30.050, 'https://github.com/modood/Administrative-divisions-of-China', '3300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513400, '凉山彝族自治州', 510000, 2, 102.267, 27.882, 'https://github.com/modood/Administrative-divisions-of-China', '3400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520100, '贵阳市', 520000, 2, 106.630, 26.648, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520200, '六盘水市', 520000, 2, 104.830, 26.593, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520300, '遵义市', 520000, 2, 106.927, 27.726, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520400, '安顺市', 520000, 2, 105.948, 26.253, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520500, '毕节市', 520000, 2, 105.284, 27.303, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520600, '铜仁市', 520000, 2, 109.190, 27.732, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522300, '黔西南布依族苗族自治州', 520000, 2, 104.906, 25.088, 'https://github.com/modood/Administrative-divisions-of-China', '2300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522600, '黔东南苗族侗族自治州', 520000, 2, 107.983, 26.583, 'https://github.com/modood/Administrative-divisions-of-China', '2600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522700, '黔南布依族苗族自治州', 520000, 2, 107.522, 26.254, 'https://github.com/modood/Administrative-divisions-of-China', '2700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530100, '昆明市', 530000, 2, 102.833, 24.880, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530300, '曲靖市', 530000, 2, 103.796, 25.490, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530400, '玉溪市', 530000, 2, 102.547, 24.352, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530500, '保山市', 530000, 2, 99.162, 25.112, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530600, '昭通市', 530000, 2, 103.717, 27.338, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530700, '丽江市', 530000, 2, 100.228, 26.855, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530800, '普洱市', 530000, 2, 100.967, 22.825, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530900, '临沧市', 530000, 2, 100.080, 23.878, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532300, '楚雄彝族自治州', 530000, 2, 101.528, 25.046, 'https://github.com/modood/Administrative-divisions-of-China', '2300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532500, '红河哈尼族彝族自治州', 530000, 2, 103.375, 23.363, 'https://github.com/modood/Administrative-divisions-of-China', '2500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532600, '文山壮族苗族自治州', 530000, 2, 104.216, 23.401, 'https://github.com/modood/Administrative-divisions-of-China', '2600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532800, '西双版纳傣族自治州', 530000, 2, 100.798, 22.007, 'https://github.com/modood/Administrative-divisions-of-China', '2800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532900, '大理白族自治州', 530000, 2, 100.268, 25.606, 'https://github.com/modood/Administrative-divisions-of-China', '2900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533100, '德宏傣族景颇族自治州', 530000, 2, 98.585, 24.433, 'https://github.com/modood/Administrative-divisions-of-China', '3100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533300, '怒江傈僳族自治州', 530000, 2, 98.853, 25.853, 'https://github.com/modood/Administrative-divisions-of-China', '3300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533400, '迪庆藏族自治州', 530000, 2, 99.702, 27.819, 'https://github.com/modood/Administrative-divisions-of-China', '3400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540100, '拉萨市', 540000, 2, 91.141, 29.646, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540200, '日喀则市', 540000, 2, 88.881, 29.267, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540300, '昌都市', 540000, 2, 97.172, 31.141, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540400, '林芝市', 540000, 2, 94.361, 29.649, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540500, '山南市', 540000, 2, 91.773, 29.237, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540600, '那曲市', 540000, 2, 91.117, 29.647, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (542500, '阿里地区', 540000, 2, 80.106, 32.501, 'https://github.com/modood/Administrative-divisions-of-China', '2500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610100, '西安市', 610000, 2, 108.940, 34.342, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610200, '铜川市', 610000, 2, 108.945, 34.897, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610300, '宝鸡市', 610000, 2, 107.238, 34.362, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610400, '咸阳市', 610000, 2, 108.709, 34.330, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610500, '渭南市', 610000, 2, 109.510, 34.500, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610600, '延安市', 610000, 2, 109.490, 36.585, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610700, '汉中市', 610000, 2, 107.023, 33.067, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610800, '榆林市', 610000, 2, 109.735, 38.285, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610900, '安康市', 610000, 2, 109.029, 32.685, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (611000, '商洛市', 610000, 2, 109.940, 33.870, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620100, '兰州市', 620000, 2, 103.834, 36.061, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620200, '嘉峪关市', 620000, 2, 98.289, 39.773, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620300, '金昌市', 620000, 2, 102.188, 38.520, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620400, '白银市', 620000, 2, 104.139, 36.545, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620500, '天水市', 620000, 2, 105.725, 34.581, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620600, '武威市', 620000, 2, 102.638, 37.928, 'https://github.com/modood/Administrative-divisions-of-China', '0600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620700, '张掖市', 620000, 2, 100.450, 38.926, 'https://github.com/modood/Administrative-divisions-of-China', '0700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620800, '平凉市', 620000, 2, 106.665, 35.543, 'https://github.com/modood/Administrative-divisions-of-China', '0800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620900, '酒泉市', 620000, 2, 98.494, 39.732, 'https://github.com/modood/Administrative-divisions-of-China', '0900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621000, '庆阳市', 620000, 2, 107.644, 35.709, 'https://github.com/modood/Administrative-divisions-of-China', '1000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621100, '定西市', 620000, 2, 104.626, 35.581, 'https://github.com/modood/Administrative-divisions-of-China', '1100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621200, '陇南市', 620000, 2, 104.922, 33.401, 'https://github.com/modood/Administrative-divisions-of-China', '1200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (622900, '临夏回族自治州', 620000, 2, 103.211, 35.601, 'https://github.com/modood/Administrative-divisions-of-China', '2900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (623000, '甘南藏族自治州', 620000, 2, 102.911, 34.983, 'https://github.com/modood/Administrative-divisions-of-China', '3000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630100, '西宁市', 630000, 2, 101.778, 36.617, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630200, '海东市', 630000, 2, 102.104, 36.502, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632200, '海北藏族自治州', 630000, 2, 100.901, 36.954, 'https://github.com/modood/Administrative-divisions-of-China', '2200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632300, '黄南藏族自治州', 630000, 2, 102.015, 35.520, 'https://github.com/modood/Administrative-divisions-of-China', '2300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632500, '海南藏族自治州', 630000, 2, 100.620, 36.286, 'https://github.com/modood/Administrative-divisions-of-China', '2500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632600, '果洛藏族自治州', 630000, 2, 100.245, 34.471, 'https://github.com/modood/Administrative-divisions-of-China', '2600', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632700, '玉树藏族自治州', 630000, 2, 97.092, 33.012, 'https://github.com/modood/Administrative-divisions-of-China', '2700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632800, '海西蒙古族藏族自治州', 630000, 2, 97.370, 37.377, 'https://github.com/modood/Administrative-divisions-of-China', '2800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640100, '银川市', 640000, 2, 106.231, 38.487, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640200, '石嘴山市', 640000, 2, 106.383, 38.983, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640300, '吴忠市', 640000, 2, 106.198, 37.997, 'https://github.com/modood/Administrative-divisions-of-China', '0300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640400, '固原市', 640000, 2, 106.243, 36.016, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640500, '中卫市', 640000, 2, 105.197, 37.500, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650100, '乌鲁木齐市', 650000, 2, 87.617, 43.826, 'https://github.com/modood/Administrative-divisions-of-China', '0100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650200, '克拉玛依市', 650000, 2, 84.889, 45.580, 'https://github.com/modood/Administrative-divisions-of-China', '0200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650400, '吐鲁番市', 650000, 2, 89.190, 42.951, 'https://github.com/modood/Administrative-divisions-of-China', '0400', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650500, '哈密市', 650000, 2, 93.515, 42.819, 'https://github.com/modood/Administrative-divisions-of-China', '0500', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652300, '昌吉回族自治州', 650000, 2, 87.308, 44.011, 'https://github.com/modood/Administrative-divisions-of-China', '2300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652700, '博尔塔拉蒙古自治州', 650000, 2, 82.066, 44.906, 'https://github.com/modood/Administrative-divisions-of-China', '2700', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652800, '巴音郭楞蒙古自治州', 650000, 2, 86.145, 41.764, 'https://github.com/modood/Administrative-divisions-of-China', '2800', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652900, '阿克苏地区', 650000, 2, 80.261, 41.169, 'https://github.com/modood/Administrative-divisions-of-China', '2900', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653000, '克孜勒苏柯尔克孜自治州', 650000, 2, 76.168, 39.715, 'https://github.com/modood/Administrative-divisions-of-China', '3000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653100, '喀什地区', 650000, 2, 75.990, 39.470, 'https://github.com/modood/Administrative-divisions-of-China', '3100', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653200, '和田地区', 650000, 2, 79.922, 37.114, 'https://github.com/modood/Administrative-divisions-of-China', '3200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654000, '伊犁哈萨克自治州', 650000, 2, 81.324, 43.917, 'https://github.com/modood/Administrative-divisions-of-China', '4000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654200, '塔城地区', 650000, 2, 82.980, 46.745, 'https://github.com/modood/Administrative-divisions-of-China', '4200', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654300, '阿勒泰地区', 650000, 2, 88.141, 47.845, 'https://github.com/modood/Administrative-divisions-of-China', '4300', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659000, '自治区直辖县级行政区划', 650000, 2, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '9000', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110101, '东城区', 110100, 3, 116.416, 39.928, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110102, '西城区', 110100, 3, 116.366, 39.912, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110105, '朝阳区', 110100, 3, 116.601, 39.949, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110106, '丰台区', 110100, 3, 116.287, 39.858, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110107, '石景山区', 110100, 3, 116.223, 39.907, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110108, '海淀区', 110100, 3, 116.330, 39.972, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110109, '门头沟区', 110100, 3, 116.102, 39.941, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110111, '房山区', 110100, 3, 116.143, 39.749, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110112, '通州区', 110100, 3, 116.656, 39.910, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110113, '顺义区', 110100, 3, 116.655, 40.130, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110114, '昌平区', 110100, 3, 116.231, 40.221, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110115, '大兴区', 110100, 3, 116.341, 39.785, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110116, '怀柔区', 110100, 3, 116.642, 40.316, 'https://github.com/modood/Administrative-divisions-of-China', '16', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110117, '平谷区', 110100, 3, 117.121, 40.141, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110118, '密云区', 110100, 3, 116.843, 40.377, 'https://github.com/modood/Administrative-divisions-of-China', '18', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (110119, '延庆区', 110100, 3, 115.975, 40.457, 'https://github.com/modood/Administrative-divisions-of-China', '19', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120101, '和平区', 120100, 3, 117.208, 39.119, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120102, '河东区', 120100, 3, 117.252, 39.128, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120103, '河西区', 120100, 3, 117.223, 39.110, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120104, '南开区', 120100, 3, 117.151, 39.138, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120105, '河北区', 120100, 3, 117.197, 39.148, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120106, '红桥区', 120100, 3, 117.152, 39.167, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120110, '东丽区', 120100, 3, 117.314, 39.087, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120111, '西青区', 120100, 3, 117.014, 39.142, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120112, '津南区', 120100, 3, 117.392, 38.985, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120113, '北辰区', 120100, 3, 117.139, 39.218, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120114, '武清区', 120100, 3, 117.044, 39.384, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120115, '宝坻区', 120100, 3, 117.310, 39.717, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120116, '滨海新区', 120100, 3, 117.781, 39.246, 'https://github.com/modood/Administrative-divisions-of-China', '16', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120117, '宁河区', 120100, 3, 117.827, 39.330, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120118, '静海区', 120100, 3, 116.974, 38.948, 'https://github.com/modood/Administrative-divisions-of-China', '18', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (120119, '蓟州区', 120100, 3, 117.408, 40.046, 'https://github.com/modood/Administrative-divisions-of-China', '19', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130102, '长安区', 130100, 3, 114.539, 38.037, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130104, '桥西区', 130100, 3, 114.461, 38.004, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130105, '新华区', 130100, 3, 114.463, 38.051, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130107, '井陉矿区', 130100, 3, 114.515, 38.042, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130108, '裕华区', 130100, 3, 114.531, 38.006, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130109, '藁城区', 130100, 3, 114.847, 38.022, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130110, '鹿泉区', 130100, 3, 114.314, 38.086, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130111, '栾城区', 130100, 3, 114.648, 37.900, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130121, '井陉县', 130100, 3, 114.145, 38.032, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130123, '正定县', 130100, 3, 114.571, 38.146, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130125, '行唐县', 130100, 3, 114.553, 38.438, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130126, '灵寿县', 130100, 3, 114.376, 38.306, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130127, '高邑县', 130100, 3, 114.611, 37.615, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130128, '深泽县', 130100, 3, 115.201, 38.184, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130129, '赞皇县', 130100, 3, 114.386, 37.666, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130130, '无极县', 130100, 3, 114.976, 38.179, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130131, '平山县', 130100, 3, 114.199, 38.247, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130132, '元氏县', 130100, 3, 114.526, 37.767, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130133, '赵县', 130100, 3, 114.776, 37.756, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130171, '石家庄高新技术产业开发区', 130100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130172, '石家庄循环化工园区', 130100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130181, '辛集市', 130100, 3, 115.218, 37.943, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130183, '晋州市', 130100, 3, 115.044, 38.034, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130184, '新乐市', 130100, 3, 114.684, 38.343, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130202, '路南区', 130200, 3, 118.154, 39.625, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130203, '路北区', 130200, 3, 118.201, 39.624, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130204, '古冶区', 130200, 3, 118.448, 39.734, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130205, '开平区', 130200, 3, 118.262, 39.671, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130207, '丰南区', 130200, 3, 118.085, 39.576, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130208, '丰润区', 130200, 3, 118.162, 39.833, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130209, '曹妃甸区', 130200, 3, 118.460, 39.273, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130224, '滦南县', 130200, 3, 118.682, 39.519, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130225, '乐亭县', 130200, 3, 118.913, 39.426, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130227, '迁西县', 130200, 3, 118.315, 40.142, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130229, '玉田县', 130200, 3, 117.739, 39.900, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130271, '河北唐山芦台经济开发区', 130200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130272, '唐山市汉沽管理区', 130200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130273, '唐山高新技术产业开发区', 130200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130274, '河北唐山海港经济开发区', 130200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '74', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130281, '遵化市', 130200, 3, 117.966, 40.189, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130283, '迁安市', 130200, 3, 118.701, 39.999, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130284, '滦州市', 130200, 3, 118.690, 39.740, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130302, '海港区', 130300, 3, 119.565, 39.948, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130303, '山海关区', 130300, 3, 119.776, 39.979, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130304, '北戴河区', 130300, 3, 119.489, 39.835, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130306, '抚宁区', 130300, 3, 119.236, 39.889, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130321, '青龙满族自治县', 130300, 3, 118.950, 40.408, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130322, '昌黎县', 130300, 3, 119.163, 39.713, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130324, '卢龙县', 130300, 3, 118.893, 39.892, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130371, '秦皇岛市经济技术开发区', 130300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130372, '北戴河新区', 130300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130402, '邯山区', 130400, 3, 114.490, 36.580, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130403, '丛台区', 130400, 3, 114.493, 36.636, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130404, '复兴区', 130400, 3, 114.462, 36.639, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130406, '峰峰矿区', 130400, 3, 114.539, 36.626, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130407, '肥乡区', 130400, 3, 114.800, 36.548, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130408, '永年区', 130400, 3, 114.539, 36.626, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130423, '临漳县', 130400, 3, 114.620, 36.335, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130424, '成安县', 130400, 3, 114.670, 36.444, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130425, '大名县', 130400, 3, 115.148, 36.286, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130426, '涉县', 130400, 3, 113.691, 36.585, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130427, '磁县', 130400, 3, 114.374, 36.374, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130430, '邱县', 130400, 3, 115.187, 36.811, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130431, '鸡泽县', 130400, 3, 114.878, 36.920, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130432, '广平县', 130400, 3, 114.949, 36.483, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130433, '馆陶县', 130400, 3, 115.282, 36.548, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130434, '魏县', 130400, 3, 114.939, 36.360, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130435, '曲周县', 130400, 3, 114.945, 36.780, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130471, '邯郸经济技术开发区', 130400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130473, '邯郸冀南新区', 130400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130481, '武安市', 130400, 3, 114.204, 36.697, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130502, '襄都区', 130500, 3, 114.492, 37.062, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130503, '信都区', 130500, 3, 114.468, 37.060, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130505, '任泽区', 130500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130506, '南和区', 130500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130522, '临城县', 130500, 3, 114.499, 37.444, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130523, '内丘县', 130500, 3, 114.512, 37.287, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130524, '柏乡县', 130500, 3, 114.693, 37.482, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130525, '隆尧县', 130500, 3, 114.770, 37.350, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130528, '宁晋县', 130500, 3, 114.919, 37.620, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130529, '巨鹿县', 130500, 3, 115.037, 37.221, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130530, '新河县', 130500, 3, 115.242, 37.529, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130531, '广宗县', 130500, 3, 115.143, 37.075, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130532, '平乡县', 130500, 3, 115.030, 37.063, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130533, '威县', 130500, 3, 115.267, 36.975, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130534, '清河县', 130500, 3, 115.668, 37.040, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130535, '临西县', 130500, 3, 115.501, 36.871, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130571, '河北邢台经济开发区', 130500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130581, '南宫市', 130500, 3, 115.409, 37.359, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130582, '沙河市', 130500, 3, 114.503, 36.855, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130602, '竞秀区', 130600, 3, 115.459, 38.878, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130606, '莲池区', 130600, 3, 115.497, 38.883, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130607, '满城区', 130600, 3, 115.322, 38.949, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130608, '清苑区', 130600, 3, 115.490, 38.765, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130609, '徐水区', 130600, 3, 115.624, 39.026, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130623, '涞水县', 130600, 3, 115.714, 39.394, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130624, '阜平县', 130600, 3, 114.195, 38.849, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130626, '定兴县', 130600, 3, 115.808, 39.263, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130627, '唐县', 130600, 3, 114.983, 38.748, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130628, '高阳县', 130600, 3, 115.779, 38.700, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130629, '容城县', 130600, 3, 115.862, 39.043, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130630, '涞源县', 130600, 3, 114.694, 39.360, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130631, '望都县', 130600, 3, 115.155, 38.696, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130632, '安新县', 130600, 3, 115.936, 38.935, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130633, '易县', 130600, 3, 115.498, 39.349, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130634, '曲阳县', 130600, 3, 114.745, 38.622, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130635, '蠡县', 130600, 3, 115.584, 38.488, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130636, '顺平县', 130600, 3, 115.135, 38.837, 'https://github.com/modood/Administrative-divisions-of-China', '36', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130637, '博野县', 130600, 3, 115.464, 38.457, 'https://github.com/modood/Administrative-divisions-of-China', '37', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130638, '雄县', 130600, 3, 116.109, 38.995, 'https://github.com/modood/Administrative-divisions-of-China', '38', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130671, '保定高新技术产业开发区', 130600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130672, '保定白沟新城', 130600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130681, '涿州市', 130600, 3, 115.974, 39.485, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130682, '定州市', 130600, 3, 114.990, 38.516, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130683, '安国市', 130600, 3, 115.327, 38.418, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130684, '高碑店市', 130600, 3, 115.874, 39.327, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130702, '桥东区', 130700, 3, 114.894, 40.788, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130703, '桥西区', 130700, 3, 114.869, 40.820, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130705, '宣化区', 130700, 3, 115.100, 40.609, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130706, '下花园区', 130700, 3, 115.287, 40.503, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130708, '万全区', 130700, 3, 114.741, 40.767, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130709, '崇礼区', 130700, 3, 115.283, 40.975, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130722, '张北县', 130700, 3, 114.720, 41.159, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130723, '康保县', 130700, 3, 114.600, 41.852, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130724, '沽源县', 130700, 3, 115.689, 41.670, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130725, '尚义县', 130700, 3, 113.970, 41.076, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130726, '蔚县', 130700, 3, 114.589, 39.841, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130727, '阳原县', 130700, 3, 114.150, 40.104, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130728, '怀安县', 130700, 3, 114.386, 40.674, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130730, '怀来县', 130700, 3, 115.518, 40.415, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130731, '涿鹿县', 130700, 3, 115.205, 40.380, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130732, '赤城县', 130700, 3, 115.831, 40.913, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130771, '张家口经济开发区', 130700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130772, '张家口市察北管理区', 130700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130773, '张家口市塞北管理区', 130700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130802, '双桥区', 130800, 3, 117.943, 40.975, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130803, '双滦区', 130800, 3, 117.800, 40.959, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130804, '鹰手营子矿区', 130800, 3, 117.661, 40.547, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130821, '承德县', 130800, 3, 118.174, 40.768, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130822, '兴隆县', 130800, 3, 117.501, 40.417, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130824, '滦平县', 130800, 3, 117.333, 40.941, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130825, '隆化县', 130800, 3, 117.739, 41.314, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130826, '丰宁满族自治县', 130800, 3, 116.646, 41.209, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130827, '宽城满族自治县', 130800, 3, 118.485, 40.611, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130828, '围场满族蒙古族自治县', 130800, 3, 117.760, 41.939, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130871, '承德高新技术产业开发区', 130800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130881, '平泉市', 130800, 3, 117.962, 40.954, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130902, '新华区', 130900, 3, 116.866, 38.314, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130903, '运河区', 130900, 3, 116.832, 38.310, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130921, '沧县', 130900, 3, 117.007, 38.220, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130922, '青县', 130900, 3, 116.804, 38.583, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130923, '东光县', 130900, 3, 116.537, 37.888, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130924, '海兴县', 130900, 3, 117.497, 38.142, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130925, '盐山县', 130900, 3, 117.231, 38.058, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130926, '肃宁县', 130900, 3, 115.830, 38.423, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130927, '南皮县', 130900, 3, 116.708, 38.039, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130928, '吴桥县', 130900, 3, 116.392, 37.628, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130929, '献县', 130900, 3, 116.123, 38.190, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130930, '孟村回族自治县', 130900, 3, 117.104, 38.053, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130971, '河北沧州经济开发区', 130900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130972, '沧州高新技术产业开发区', 130900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130973, '沧州渤海新区', 130900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130981, '泊头市', 130900, 3, 116.578, 38.083, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130982, '任丘市', 130900, 3, 116.083, 38.684, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130983, '黄骅市', 130900, 3, 117.330, 38.371, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (130984, '河间市', 130900, 3, 116.100, 38.447, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131002, '安次区', 131000, 3, 116.695, 39.503, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131003, '广阳区', 131000, 3, 116.711, 39.523, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131022, '固安县', 131000, 3, 116.299, 39.438, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131023, '永清县', 131000, 3, 116.499, 39.322, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131024, '香河县', 131000, 3, 117.006, 39.761, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131025, '大城县', 131000, 3, 116.654, 38.705, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131026, '文安县', 131000, 3, 116.458, 38.873, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131028, '大厂回族自治县', 131000, 3, 116.990, 39.887, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131071, '廊坊经济技术开发区', 131000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131081, '霸州市', 131000, 3, 116.391, 39.126, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131082, '三河市', 131000, 3, 117.078, 39.983, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131102, '桃城区', 131100, 3, 115.675, 37.735, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131103, '冀州区', 131100, 3, 115.579, 37.551, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131121, '枣强县', 131100, 3, 115.724, 37.513, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:51', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131122, '武邑县', 131100, 3, 115.888, 37.802, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131123, '武强县', 131100, 3, 115.982, 38.041, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131124, '饶阳县', 131100, 3, 115.726, 38.236, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131125, '安平县', 131100, 3, 115.519, 38.235, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131126, '故城县', 131100, 3, 115.966, 37.347, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131127, '景县', 131100, 3, 116.271, 37.692, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131128, '阜城县', 131100, 3, 116.144, 37.869, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131171, '河北衡水高新技术产业开发区', 131100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131172, '衡水滨湖新区', 131100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (131182, '深州市', 131100, 3, 115.560, 38.002, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140105, '小店区', 140100, 3, 112.564, 37.743, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140106, '迎泽区', 140100, 3, 112.563, 37.863, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140107, '杏花岭区', 140100, 3, 112.571, 37.894, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140108, '尖草坪区', 140100, 3, 112.487, 37.940, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140109, '万柏林区', 140100, 3, 112.516, 37.859, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140110, '晋源区', 140100, 3, 112.478, 37.715, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140121, '清徐县', 140100, 3, 112.359, 37.607, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140122, '阳曲县', 140100, 3, 112.673, 38.058, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140123, '娄烦县', 140100, 3, 111.797, 38.068, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140171, '山西转型综合改革示范区', 140100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140181, '古交市', 140100, 3, 112.176, 37.907, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140212, '新荣区', 140200, 3, 113.140, 40.256, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140213, '平城区', 140200, 3, 113.300, 40.077, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140214, '云冈区', 140200, 3, 113.131, 40.109, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140215, '云州区', 140200, 3, 113.300, 40.077, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140221, '阳高县', 140200, 3, 113.749, 40.361, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140222, '天镇县', 140200, 3, 114.091, 40.420, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140223, '广灵县', 140200, 3, 114.274, 39.762, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140224, '灵丘县', 140200, 3, 114.234, 39.442, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140225, '浑源县', 140200, 3, 113.699, 39.693, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140226, '左云县', 140200, 3, 112.703, 40.013, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140271, '山西大同经济开发区', 140200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140302, '城区', 140300, 3, 113.601, 37.847, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140303, '矿区', 140300, 3, 113.581, 37.857, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140311, '郊区', 140300, 3, 113.594, 37.945, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140321, '平定县', 140300, 3, 113.658, 37.787, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140322, '盂县', 140300, 3, 113.412, 38.086, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140403, '潞州区', 140400, 3, 113.116, 36.195, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140404, '上党区', 140400, 3, 113.111, 36.187, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140405, '屯留区', 140400, 3, 112.892, 36.316, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140406, '潞城区', 140400, 3, 113.229, 36.334, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140423, '襄垣县', 140400, 3, 113.051, 36.536, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140425, '平顺县', 140400, 3, 113.436, 36.200, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140426, '黎城县', 140400, 3, 113.387, 36.504, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140427, '壶关县', 140400, 3, 113.207, 36.115, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140428, '长子县', 140400, 3, 112.878, 36.122, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140429, '武乡县', 140400, 3, 112.865, 36.838, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140430, '沁县', 140400, 3, 112.699, 36.756, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140431, '沁源县', 140400, 3, 112.337, 36.500, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140471, '山西长治高新技术产业园区', 140400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140502, '城区', 140500, 3, 112.854, 35.502, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140521, '沁水县', 140500, 3, 112.187, 35.690, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140522, '阳城县', 140500, 3, 112.415, 35.486, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140524, '陵川县', 140500, 3, 113.281, 35.776, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140525, '泽州县', 140500, 3, 112.899, 35.617, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140581, '高平市', 140500, 3, 112.924, 35.798, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140602, '朔城区', 140600, 3, 112.432, 39.319, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140603, '平鲁区', 140600, 3, 112.288, 39.512, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140621, '山阴县', 140600, 3, 112.817, 39.526, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140622, '应县', 140600, 3, 113.191, 39.554, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140623, '右玉县', 140600, 3, 112.467, 39.989, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140671, '山西朔州经济开发区', 140600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140681, '怀仁市', 140600, 3, 113.100, 39.828, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140702, '榆次区', 140700, 3, 112.708, 37.698, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140703, '太谷区', 140700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140721, '榆社县', 140700, 3, 112.975, 37.071, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140722, '左权县', 140700, 3, 113.379, 37.083, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140723, '和顺县', 140700, 3, 113.570, 37.330, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140724, '昔阳县', 140700, 3, 113.707, 37.611, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140725, '寿阳县', 140700, 3, 113.176, 37.895, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140727, '祁县', 140700, 3, 112.335, 37.358, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140728, '平遥县', 140700, 3, 112.176, 37.190, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140729, '灵石县', 140700, 3, 111.779, 36.848, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140781, '介休市', 140700, 3, 111.917, 37.027, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140802, '盐湖区', 140800, 3, 110.998, 35.015, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140821, '临猗县', 140800, 3, 110.775, 35.144, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140822, '万荣县', 140800, 3, 110.838, 35.415, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140823, '闻喜县', 140800, 3, 111.225, 35.357, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140824, '稷山县', 140800, 3, 110.983, 35.604, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140825, '新绛县', 140800, 3, 111.225, 35.616, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140826, '绛县', 140800, 3, 111.568, 35.491, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140827, '垣曲县', 140800, 3, 111.670, 35.298, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140828, '夏县', 140800, 3, 111.220, 35.141, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140829, '平陆县', 140800, 3, 111.194, 34.829, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140830, '芮城县', 140800, 3, 110.694, 34.694, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140881, '永济市', 140800, 3, 110.448, 34.867, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140882, '河津市', 140800, 3, 110.712, 35.596, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140902, '忻府区', 140900, 3, 112.746, 38.404, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140921, '定襄县', 140900, 3, 112.957, 38.474, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140922, '五台县', 140900, 3, 113.255, 38.728, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140923, '代县', 140900, 3, 112.960, 39.067, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140924, '繁峙县', 140900, 3, 113.266, 39.189, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140925, '宁武县', 140900, 3, 112.305, 39.002, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140926, '静乐县', 140900, 3, 111.939, 38.359, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140927, '神池县', 140900, 3, 112.211, 39.091, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140928, '五寨县', 140900, 3, 111.847, 38.911, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140929, '岢岚县', 140900, 3, 111.573, 38.704, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140930, '河曲县', 140900, 3, 111.138, 39.384, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140931, '保德县', 140900, 3, 111.087, 39.022, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140932, '偏关县', 140900, 3, 111.509, 39.436, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140971, '五台山风景名胜区', 140900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (140981, '原平市', 140900, 3, 112.711, 38.731, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141002, '尧都区', 141000, 3, 111.580, 36.079, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141021, '曲沃县', 141000, 3, 111.476, 35.641, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141022, '翼城县', 141000, 3, 111.719, 35.739, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141023, '襄汾县', 141000, 3, 111.442, 35.876, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141024, '洪洞县', 141000, 3, 111.675, 36.254, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141025, '古县', 141000, 3, 111.920, 36.267, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141026, '安泽县', 141000, 3, 112.250, 36.148, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141027, '浮山县', 141000, 3, 111.849, 35.968, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141028, '吉县', 141000, 3, 110.682, 36.098, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141029, '乡宁县', 141000, 3, 110.847, 35.970, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141030, '大宁县', 141000, 3, 110.753, 36.465, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141031, '隰县', 141000, 3, 110.941, 36.693, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141032, '永和县', 141000, 3, 110.632, 36.760, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141033, '蒲县', 141000, 3, 111.096, 36.412, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141034, '汾西县', 141000, 3, 111.564, 36.653, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141081, '侯马市', 141000, 3, 111.372, 35.619, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141082, '霍州市', 141000, 3, 111.755, 36.569, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141102, '离石区', 141100, 3, 111.151, 37.518, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141121, '文水县', 141100, 3, 112.029, 37.438, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141122, '交城县', 141100, 3, 112.156, 37.552, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141123, '兴县', 141100, 3, 111.128, 38.462, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141124, '临县', 141100, 3, 110.992, 37.951, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141125, '柳林县', 141100, 3, 110.889, 37.430, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141126, '石楼县', 141100, 3, 110.835, 36.997, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141127, '岚县', 141100, 3, 111.672, 38.279, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141128, '方山县', 141100, 3, 111.244, 37.895, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141129, '中阳县', 141100, 3, 111.180, 37.357, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141130, '交口县', 141100, 3, 111.181, 36.982, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141181, '孝义市', 141100, 3, 111.779, 37.146, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (141182, '汾阳市', 141100, 3, 111.770, 37.262, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150102, '新城区', 150100, 3, 111.666, 40.858, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150103, '回民区', 150100, 3, 111.624, 40.809, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150104, '玉泉区', 150100, 3, 111.674, 40.754, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150105, '赛罕区', 150100, 3, 111.702, 40.792, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150121, '土默特左旗', 150100, 3, 111.164, 40.730, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150122, '托克托县', 150100, 3, 111.194, 40.277, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150123, '和林格尔县', 150100, 3, 111.822, 40.379, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150124, '清水河县', 150100, 3, 111.648, 39.921, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150125, '武川县', 150100, 3, 111.451, 41.096, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150172, '呼和浩特经济技术开发区', 150100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150202, '东河区', 150200, 3, 110.044, 40.576, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150203, '昆都仑区', 150200, 3, 109.838, 40.642, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150204, '青山区', 150200, 3, 109.902, 40.643, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150205, '石拐区', 150200, 3, 110.061, 40.677, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150206, '白云鄂博矿区', 150200, 3, 109.840, 40.657, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150207, '九原区', 150200, 3, 109.968, 40.601, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150221, '土默特右旗', 150200, 3, 110.524, 40.569, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150222, '固阳县', 150200, 3, 110.061, 41.034, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150223, '达尔罕茂明安联合旗', 150200, 3, 110.433, 41.699, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150271, '包头稀土高新技术产业开发区', 150200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150302, '海勃湾区', 150300, 3, 106.823, 39.691, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150303, '海南区', 150300, 3, 106.891, 39.441, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150304, '乌达区', 150300, 3, 106.726, 39.506, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150402, '红山区', 150400, 3, 118.967, 42.272, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150403, '元宝山区', 150400, 3, 119.289, 42.039, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150404, '松山区', 150400, 3, 118.933, 42.284, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150421, '阿鲁科尔沁旗', 150400, 3, 120.066, 43.872, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150422, '巴林左旗', 150400, 3, 119.379, 43.971, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150423, '巴林右旗', 150400, 3, 118.665, 43.534, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150424, '林西县', 150400, 3, 118.055, 43.618, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150425, '克什克腾旗', 150400, 3, 117.546, 43.265, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150426, '翁牛特旗', 150400, 3, 119.007, 42.936, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150428, '喀喇沁旗', 150400, 3, 118.702, 41.927, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150429, '宁城县', 150400, 3, 118.558, 41.415, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150430, '敖汉旗', 150400, 3, 119.922, 42.291, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150502, '科尔沁区', 150500, 3, 122.256, 43.623, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150521, '科尔沁左翼中旗', 150500, 3, 123.312, 44.127, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150522, '科尔沁左翼后旗', 150500, 3, 122.357, 42.935, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150523, '开鲁县', 150500, 3, 121.319, 43.601, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150524, '库伦旗', 150500, 3, 121.811, 42.736, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150525, '奈曼旗', 150500, 3, 120.662, 42.845, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150526, '扎鲁特旗', 150500, 3, 120.912, 44.556, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150571, '通辽经济技术开发区', 150500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150581, '霍林郭勒市', 150500, 3, 119.664, 45.532, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150602, '东胜区', 150600, 3, 109.963, 39.823, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150603, '康巴什区', 150600, 3, 109.781, 39.608, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150621, '达拉特旗', 150600, 3, 110.034, 40.412, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150622, '准格尔旗', 150600, 3, 111.240, 39.864, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150623, '鄂托克前旗', 150600, 3, 107.478, 38.182, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150624, '鄂托克旗', 150600, 3, 107.976, 39.090, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150625, '杭锦旗', 150600, 3, 108.736, 39.833, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150626, '乌审旗', 150600, 3, 108.818, 38.604, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150627, '伊金霍洛旗', 150600, 3, 109.748, 39.565, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150702, '海拉尔区', 150700, 3, 119.736, 49.212, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150703, '扎赉诺尔区', 150700, 3, 117.670, 49.510, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150721, '阿荣旗', 150700, 3, 123.459, 48.127, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150722, '莫力达瓦达斡尔族自治旗', 150700, 3, 124.519, 48.478, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150723, '鄂伦春自治旗', 150700, 3, 123.726, 50.592, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150724, '鄂温克族自治旗', 150700, 3, 119.755, 49.147, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150725, '陈巴尔虎旗', 150700, 3, 119.424, 49.329, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150726, '新巴尔虎左旗', 150700, 3, 118.270, 48.218, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150727, '新巴尔虎右旗', 150700, 3, 116.824, 48.672, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150781, '满洲里市', 150700, 3, 117.379, 49.598, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150782, '牙克石市', 150700, 3, 120.736, 49.293, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150783, '扎兰屯市', 150700, 3, 122.737, 48.014, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150784, '额尔古纳市', 150700, 3, 120.181, 50.243, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150785, '根河市', 150700, 3, 121.520, 50.780, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150802, '临河区', 150800, 3, 107.364, 40.751, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150821, '五原县', 150800, 3, 108.268, 41.088, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150822, '磴口县', 150800, 3, 107.008, 40.331, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150823, '乌拉特前旗', 150800, 3, 108.652, 40.737, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150824, '乌拉特中旗', 150800, 3, 108.514, 41.588, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150825, '乌拉特后旗', 150800, 3, 107.075, 41.084, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150826, '杭锦后旗', 150800, 3, 107.151, 40.886, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150902, '集宁区', 150900, 3, 113.124, 40.991, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150921, '卓资县', 150900, 3, 112.578, 40.895, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150922, '化德县', 150900, 3, 114.010, 41.905, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150923, '商都县', 150900, 3, 113.578, 41.562, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150924, '兴和县', 150900, 3, 113.834, 40.872, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150925, '凉城县', 150900, 3, 112.504, 40.532, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150926, '察哈尔右翼前旗', 150900, 3, 113.215, 40.786, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150927, '察哈尔右翼中旗', 150900, 3, 112.636, 41.277, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150928, '察哈尔右翼后旗', 150900, 3, 113.191, 41.436, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150929, '四子王旗', 150900, 3, 111.707, 41.533, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (150981, '丰镇市', 150900, 3, 113.110, 40.437, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152201, '乌兰浩特市', 152200, 3, 122.093, 46.073, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152202, '阿尔山市', 152200, 3, 119.944, 47.177, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152221, '科尔沁右翼前旗', 152200, 3, 121.953, 46.080, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152222, '科尔沁右翼中旗', 152200, 3, 121.477, 45.061, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152223, '扎赉特旗', 152200, 3, 122.900, 46.723, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152224, '突泉县', 152200, 3, 121.594, 45.382, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152501, '二连浩特市', 152500, 3, 111.978, 43.653, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152502, '锡林浩特市', 152500, 3, 116.086, 43.933, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152522, '阿巴嘎旗', 152500, 3, 114.950, 44.023, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152523, '苏尼特左旗', 152500, 3, 113.667, 43.860, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152524, '苏尼特右旗', 152500, 3, 112.642, 42.743, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152525, '东乌珠穆沁旗', 152500, 3, 116.974, 45.498, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152526, '西乌珠穆沁旗', 152500, 3, 117.609, 44.588, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152527, '太仆寺旗', 152500, 3, 115.283, 41.877, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152528, '镶黄旗', 152500, 3, 113.847, 42.232, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152529, '正镶白旗', 152500, 3, 115.030, 42.287, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152530, '正蓝旗', 152500, 3, 115.992, 42.242, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152531, '多伦县', 152500, 3, 116.486, 42.204, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152571, '乌拉盖管委会', 152500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152921, '阿拉善左旗', 152900, 3, 105.666, 38.833, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152922, '阿拉善右旗', 152900, 3, 101.667, 39.216, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152923, '额济纳旗', 152900, 3, 101.056, 41.954, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (152971, '内蒙古阿拉善经济开发区', 152900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210102, '和平区', 210100, 3, 123.396, 41.789, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210103, '沈河区', 210100, 3, 123.459, 41.796, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210104, '大东区', 210100, 3, 123.509, 41.813, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210105, '皇姑区', 210100, 3, 123.442, 41.825, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210106, '铁西区', 210100, 3, 123.376, 41.803, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210111, '苏家屯区', 210100, 3, 123.344, 41.665, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210112, '浑南区', 210100, 3, 123.450, 41.715, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210113, '沈北新区', 210100, 3, 123.583, 41.912, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210114, '于洪区', 210100, 3, 123.308, 41.794, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210115, '辽中区', 210100, 3, 122.765, 41.517, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210123, '康平县', 210100, 3, 123.356, 42.741, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210124, '法库县', 210100, 3, 123.440, 42.501, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210181, '新民市', 210100, 3, 122.726, 41.933, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210202, '中山区', 210200, 3, 121.645, 38.919, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210203, '西岗区', 210200, 3, 121.612, 38.915, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210204, '沙河口区', 210200, 3, 121.594, 38.905, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210211, '甘井子区', 210200, 3, 121.525, 38.953, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210212, '旅顺口区', 210200, 3, 121.262, 38.852, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210213, '金州区', 210200, 3, 121.783, 39.050, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210214, '普兰店区', 210200, 3, 122.262, 39.742, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210224, '长海县', 210200, 3, 122.588, 39.273, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210281, '瓦房店市', 210200, 3, 121.980, 39.627, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210283, '庄河市', 210200, 3, 122.967, 39.681, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210302, '铁东区', 210300, 3, 122.991, 41.090, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210303, '铁西区', 210300, 3, 122.970, 41.120, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210304, '立山区', 210300, 3, 123.029, 41.150, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210311, '千山区', 210300, 3, 122.949, 41.069, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210321, '台安县', 210300, 3, 122.436, 41.413, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210323, '岫岩满族自治县', 210300, 3, 123.281, 40.291, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210381, '海城市', 210300, 3, 122.685, 40.882, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210402, '新抚区', 210400, 3, 123.913, 41.862, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210403, '东洲区', 210400, 3, 124.039, 41.853, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210404, '望花区', 210400, 3, 123.784, 41.854, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210411, '顺城区', 210400, 3, 123.945, 41.883, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210421, '抚顺县', 210400, 3, 124.098, 41.923, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210422, '新宾满族自治县', 210400, 3, 125.040, 41.734, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210423, '清原满族自治县', 210400, 3, 124.924, 42.101, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210502, '平山区', 210500, 3, 123.769, 41.300, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210503, '溪湖区', 210500, 3, 123.768, 41.329, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210504, '明山区', 210500, 3, 123.817, 41.309, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210505, '南芬区', 210500, 3, 123.745, 41.100, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210521, '本溪满族自治县', 210500, 3, 124.120, 41.302, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210522, '桓仁满族自治县', 210500, 3, 125.361, 41.267, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210602, '元宝区', 210600, 3, 124.396, 40.137, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210603, '振兴区', 210600, 3, 124.360, 40.105, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210604, '振安区', 210600, 3, 124.428, 40.158, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210624, '宽甸满族自治县', 210600, 3, 124.784, 40.731, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210681, '东港市', 210600, 3, 124.153, 39.863, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210682, '凤城市', 210600, 3, 124.067, 40.452, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210702, '古塔区', 210700, 3, 121.128, 41.117, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210703, '凌河区', 210700, 3, 121.151, 41.115, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210711, '太和区', 210700, 3, 121.104, 41.109, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210726, '黑山县', 210700, 3, 122.123, 41.666, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210727, '义县', 210700, 3, 121.239, 41.533, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210781, '凌海市', 210700, 3, 121.358, 41.173, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210782, '北镇市', 210700, 3, 121.796, 41.599, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210802, '站前区', 210800, 3, 122.259, 40.673, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210803, '西市区', 210800, 3, 122.206, 40.666, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210804, '鲅鱼圈区', 210800, 3, 122.108, 40.231, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210811, '老边区', 210800, 3, 122.380, 40.680, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210881, '盖州市', 210800, 3, 122.349, 40.401, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210882, '大石桥市', 210800, 3, 122.509, 40.645, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210902, '海州区', 210900, 3, 121.656, 42.013, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210903, '新邱区', 210900, 3, 121.793, 42.088, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210904, '太平区', 210900, 3, 121.679, 42.011, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210905, '清河门区', 210900, 3, 121.416, 41.783, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210911, '细河区', 210900, 3, 121.681, 42.025, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210921, '阜新蒙古族自治县', 210900, 3, 121.758, 42.065, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (210922, '彰武县', 210900, 3, 122.539, 42.387, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211002, '白塔区', 211000, 3, 123.174, 41.270, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211003, '文圣区', 211000, 3, 123.185, 41.263, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211004, '宏伟区', 211000, 3, 123.197, 41.218, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:52', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211005, '弓长岭区', 211000, 3, 123.420, 41.152, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211011, '太子河区', 211000, 3, 123.182, 41.253, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211021, '辽阳县', 211000, 3, 123.106, 41.205, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211081, '灯塔市', 211000, 3, 123.339, 41.426, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211102, '双台子区', 211100, 3, 122.060, 41.191, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211103, '兴隆台区', 211100, 3, 122.070, 41.159, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211104, '大洼区', 211100, 3, 122.071, 41.120, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211122, '盘山县', 211100, 3, 121.996, 41.243, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211202, '银州区', 211200, 3, 123.842, 42.286, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211204, '清河区', 211200, 3, 123.726, 42.224, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211221, '铁岭县', 211200, 3, 123.729, 42.223, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211223, '西丰县', 211200, 3, 124.727, 42.738, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211224, '昌图县', 211200, 3, 124.111, 42.786, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211281, '调兵山市', 211200, 3, 123.567, 42.468, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211282, '开原市', 211200, 3, 124.038, 42.546, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211302, '双塔区', 211300, 3, 120.454, 41.566, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211303, '龙城区', 211300, 3, 120.413, 41.577, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211321, '朝阳县', 211300, 3, 120.390, 41.498, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211322, '建平县', 211300, 3, 119.643, 41.403, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211324, '喀喇沁左翼蒙古族自治县', 211300, 3, 119.741, 41.128, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211381, '北票市', 211300, 3, 120.771, 41.801, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211382, '凌源市', 211300, 3, 119.402, 41.245, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211402, '连山区', 211400, 3, 120.869, 40.774, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211403, '龙港区', 211400, 3, 120.894, 40.736, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211404, '南票区', 211400, 3, 120.750, 41.107, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211421, '绥中县', 211400, 3, 120.344, 40.326, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211422, '建昌县', 211400, 3, 119.837, 40.824, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (211481, '兴城市', 211400, 3, 120.728, 40.615, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220102, '南关区', 220100, 3, 125.350, 43.864, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220103, '宽城区', 220100, 3, 125.327, 43.944, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220104, '朝阳区', 220100, 3, 125.288, 43.834, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220105, '二道区', 220100, 3, 125.374, 43.866, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220106, '绿园区', 220100, 3, 125.256, 43.881, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220112, '双阳区', 220100, 3, 125.665, 43.525, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220113, '九台区', 220100, 3, 125.840, 44.152, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220122, '农安县', 220100, 3, 125.185, 44.433, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220171, '长春经济技术开发区', 220100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220172, '长春净月高新技术产业开发区', 220100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220173, '长春高新技术产业开发区', 220100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220174, '长春汽车经济技术开发区', 220100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '74', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220182, '榆树市', 220100, 3, 126.533, 44.840, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220183, '德惠市', 220100, 3, 125.729, 44.522, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220184, '公主岭市', 220100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220202, '昌邑区', 220200, 3, 126.575, 43.882, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220203, '龙潭区', 220200, 3, 126.562, 43.911, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220204, '船营区', 220200, 3, 126.541, 43.833, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220211, '丰满区', 220200, 3, 126.562, 43.822, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220221, '永吉县', 220200, 3, 126.498, 43.673, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220271, '吉林经济开发区', 220200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220272, '吉林高新技术产业开发区', 220200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220273, '吉林中国新加坡食品区', 220200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220281, '蛟河市', 220200, 3, 127.345, 43.724, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220282, '桦甸市', 220200, 3, 126.746, 42.972, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220283, '舒兰市', 220200, 3, 126.966, 44.406, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220284, '磐石市', 220200, 3, 126.060, 42.946, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220302, '铁西区', 220300, 3, 124.346, 43.146, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220303, '铁东区', 220300, 3, 124.410, 43.162, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220322, '梨树县', 220300, 3, 124.335, 43.307, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220323, '伊通满族自治县', 220300, 3, 125.305, 43.346, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220382, '双辽市', 220300, 3, 123.503, 43.518, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220402, '龙山区', 220400, 3, 125.136, 42.902, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220403, '西安区', 220400, 3, 125.149, 42.927, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220421, '东丰县', 220400, 3, 125.531, 42.677, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220422, '东辽县', 220400, 3, 124.992, 42.926, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220502, '东昌区', 220500, 3, 125.955, 41.728, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220503, '二道江区', 220500, 3, 126.043, 41.774, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220521, '通化县', 220500, 3, 125.759, 41.680, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220523, '辉南县', 220500, 3, 126.047, 42.685, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220524, '柳河县', 220500, 3, 125.745, 42.285, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220581, '梅河口市', 220500, 3, 125.684, 42.530, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220582, '集安市', 220500, 3, 126.194, 41.125, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220602, '浑江区', 220600, 3, 126.416, 41.945, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220605, '江源区', 220600, 3, 126.591, 42.057, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220621, '抚松县', 220600, 3, 127.450, 42.221, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220622, '靖宇县', 220600, 3, 126.814, 42.389, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220623, '长白朝鲜族自治县', 220600, 3, 128.201, 41.420, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220681, '临江市', 220600, 3, 126.918, 41.812, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220702, '宁江区', 220700, 3, 124.817, 45.172, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220721, '前郭尔罗斯蒙古族自治县', 220700, 3, 124.823, 45.118, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220722, '长岭县', 220700, 3, 123.967, 44.276, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220723, '乾安县', 220700, 3, 124.041, 45.004, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220771, '吉林松原经济开发区', 220700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220781, '扶余市', 220700, 3, 126.050, 44.988, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220802, '洮北区', 220800, 3, 122.851, 45.622, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220821, '镇赉县', 220800, 3, 123.200, 45.847, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220822, '通榆县', 220800, 3, 123.088, 44.813, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220871, '吉林白城经济开发区', 220800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220881, '洮南市', 220800, 3, 122.784, 45.339, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (220882, '大安市', 220800, 3, 124.293, 45.507, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (222401, '延吉市', 222400, 3, 129.509, 42.891, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (222402, '图们市', 222400, 3, 129.844, 42.968, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (222403, '敦化市', 222400, 3, 128.232, 43.372, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (222404, '珲春市', 222400, 3, 130.366, 42.863, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (222405, '龙井市', 222400, 3, 129.427, 42.766, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (222406, '和龙市', 222400, 3, 129.010, 42.547, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (222424, '汪清县', 222400, 3, 129.772, 43.313, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (222426, '安图县', 222400, 3, 128.900, 43.112, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230102, '道里区', 230100, 3, 126.617, 45.756, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230103, '南岗区', 230100, 3, 126.669, 45.760, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230104, '道外区', 230100, 3, 126.649, 45.792, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230108, '平房区', 230100, 3, 126.638, 45.598, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230109, '松北区', 230100, 3, 126.510, 45.803, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230110, '香坊区', 230100, 3, 126.663, 45.708, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230111, '呼兰区', 230100, 3, 126.588, 45.889, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230112, '阿城区', 230100, 3, 126.958, 45.549, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230113, '双城区', 230100, 3, 126.313, 45.383, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230123, '依兰县', 230100, 3, 129.568, 46.325, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230124, '方正县', 230100, 3, 128.830, 45.852, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230125, '宾县', 230100, 3, 127.467, 45.746, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230126, '巴彦县', 230100, 3, 127.403, 46.085, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230127, '木兰县', 230100, 3, 128.043, 45.951, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230128, '通河县', 230100, 3, 128.749, 45.972, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230129, '延寿县', 230100, 3, 128.332, 45.452, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230183, '尚志市', 230100, 3, 128.010, 45.210, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230184, '五常市', 230100, 3, 127.168, 44.932, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230202, '龙沙区', 230200, 3, 123.958, 47.317, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230203, '建华区', 230200, 3, 123.955, 47.354, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230204, '铁锋区', 230200, 3, 123.978, 47.341, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230205, '昂昂溪区', 230200, 3, 123.822, 47.155, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230206, '富拉尔基区', 230200, 3, 123.629, 47.209, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230207, '碾子山区', 230200, 3, 122.888, 47.517, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230208, '梅里斯达斡尔族区', 230200, 3, 123.753, 47.310, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230221, '龙江县', 230200, 3, 123.205, 47.339, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230223, '依安县', 230200, 3, 125.306, 47.894, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230224, '泰来县', 230200, 3, 123.417, 46.394, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230225, '甘南县', 230200, 3, 123.507, 47.922, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230227, '富裕县', 230200, 3, 124.474, 47.775, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230229, '克山县', 230200, 3, 125.876, 48.037, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230230, '克东县', 230200, 3, 126.249, 48.042, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230231, '拜泉县', 230200, 3, 126.100, 47.596, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230281, '讷河市', 230200, 3, 124.884, 48.484, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230302, '鸡冠区', 230300, 3, 130.981, 45.304, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230303, '恒山区', 230300, 3, 130.905, 45.211, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230304, '滴道区', 230300, 3, 130.844, 45.349, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230305, '梨树区', 230300, 3, 130.697, 45.092, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230306, '城子河区', 230300, 3, 131.011, 45.337, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230307, '麻山区', 230300, 3, 130.478, 45.212, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230321, '鸡东县', 230300, 3, 131.124, 45.260, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230381, '虎林市', 230300, 3, 132.937, 45.763, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230382, '密山市', 230300, 3, 131.847, 45.530, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230402, '向阳区', 230400, 3, 130.294, 47.342, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230403, '工农区', 230400, 3, 130.275, 47.319, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230404, '南山区', 230400, 3, 130.287, 47.315, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230405, '兴安区', 230400, 3, 130.239, 47.253, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230406, '东山区', 230400, 3, 130.317, 47.339, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230407, '兴山区', 230400, 3, 130.304, 47.358, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230421, '萝北县', 230400, 3, 130.829, 47.577, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230422, '绥滨县', 230400, 3, 131.853, 47.289, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230502, '尖山区', 230500, 3, 131.158, 46.646, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230503, '岭东区', 230500, 3, 131.165, 46.593, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230505, '四方台区', 230500, 3, 131.335, 46.594, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230506, '宝山区', 230500, 3, 131.402, 46.577, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230521, '集贤县', 230500, 3, 131.140, 46.728, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230522, '友谊县', 230500, 3, 131.808, 46.767, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230523, '宝清县', 230500, 3, 132.197, 46.327, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230524, '饶河县', 230500, 3, 134.014, 46.798, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230602, '萨尔图区', 230600, 3, 125.115, 46.596, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230603, '龙凤区', 230600, 3, 125.135, 46.562, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230604, '让胡路区', 230600, 3, 124.871, 46.652, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230605, '红岗区', 230600, 3, 124.891, 46.398, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230606, '大同区', 230600, 3, 124.812, 46.040, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230621, '肇州县', 230600, 3, 125.269, 45.699, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230622, '肇源县', 230600, 3, 125.078, 45.519, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230623, '林甸县', 230600, 3, 124.864, 47.172, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230624, '杜尔伯特蒙古族自治县', 230600, 3, 124.443, 46.863, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230671, '大庆高新技术产业开发区', 230600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230717, '伊美区', 230700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230718, '乌翠区', 230700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '18', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230719, '友好区', 230700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '19', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230722, '嘉荫县', 230700, 3, 130.404, 48.889, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230723, '汤旺县', 230700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230724, '丰林县', 230700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230725, '大箐山县', 230700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230726, '南岔县', 230700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230751, '金林区', 230700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '51', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230781, '铁力市', 230700, 3, 128.032, 46.987, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230803, '向阳区', 230800, 3, 130.365, 46.808, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230804, '前进区', 230800, 3, 130.375, 46.814, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230805, '东风区', 230800, 3, 130.404, 46.823, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230811, '郊区', 230800, 3, 130.327, 46.810, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230822, '桦南县', 230800, 3, 130.553, 46.239, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230826, '桦川县', 230800, 3, 130.719, 47.023, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230828, '汤原县', 230800, 3, 129.905, 46.731, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230881, '同江市', 230800, 3, 132.511, 47.643, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230882, '富锦市', 230800, 3, 132.038, 47.250, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230883, '抚远市', 230800, 3, 134.308, 48.365, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230902, '新兴区', 230900, 3, 130.932, 45.816, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230903, '桃山区', 230900, 3, 131.020, 45.766, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230904, '茄子河区', 230900, 3, 131.068, 45.785, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (230921, '勃利县', 230900, 3, 130.592, 45.756, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231002, '东安区', 231000, 3, 129.627, 44.581, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231003, '阳明区', 231000, 3, 129.636, 44.596, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231004, '爱民区', 231000, 3, 129.592, 44.596, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231005, '西安区', 231000, 3, 129.616, 44.578, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231025, '林口县', 231000, 3, 130.284, 45.278, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231071, '牡丹江经济技术开发区', 231000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231081, '绥芬河市', 231000, 3, 131.153, 44.412, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231083, '海林市', 231000, 3, 129.380, 44.594, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231084, '宁安市', 231000, 3, 129.483, 44.341, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231085, '穆棱市', 231000, 3, 130.524, 44.919, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231086, '东宁市', 231000, 3, 131.123, 44.088, 'https://github.com/modood/Administrative-divisions-of-China', '86', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231102, '爱辉区', 231100, 3, 127.500, 50.252, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231123, '逊克县', 231100, 3, 128.479, 49.564, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231124, '孙吴县', 231100, 3, 127.336, 49.426, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231181, '北安市', 231100, 3, 126.491, 48.241, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231182, '五大连池市', 231100, 3, 126.206, 48.517, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231183, '嫩江市', 231100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231202, '北林区', 231200, 3, 126.986, 46.637, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231221, '望奎县', 231200, 3, 126.486, 46.833, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231222, '兰西县', 231200, 3, 126.288, 46.252, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231223, '青冈县', 231200, 3, 126.114, 46.690, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231224, '庆安县', 231200, 3, 127.508, 46.880, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231225, '明水县', 231200, 3, 125.906, 47.173, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231226, '绥棱县', 231200, 3, 127.115, 47.236, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231281, '安达市', 231200, 3, 125.352, 46.424, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231282, '肇东市', 231200, 3, 125.962, 46.051, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (231283, '海伦市', 231200, 3, 126.973, 47.462, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (232701, '漠河市', 232700, 3, 122.539, 52.972, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (232721, '呼玛县', 232700, 3, 126.665, 51.726, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (232722, '塔河县', 232700, 3, 124.710, 52.334, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (232761, '加格达奇区', 232700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '61', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (232762, '松岭区', 232700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '62', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (232763, '新林区', 232700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '63', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (232764, '呼中区', 232700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '64', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310101, '黄浦区', 310100, 3, 121.469, 31.230, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310104, '徐汇区', 310100, 3, 121.438, 31.199, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310105, '长宁区', 310100, 3, 121.425, 31.220, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310106, '静安区', 310100, 3, 121.459, 31.247, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310107, '普陀区', 310100, 3, 121.396, 31.250, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310109, '虹口区', 310100, 3, 121.505, 31.265, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310110, '杨浦区', 310100, 3, 121.526, 31.260, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310112, '闵行区', 310100, 3, 121.382, 31.113, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310113, '宝山区', 310100, 3, 121.490, 31.405, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310114, '嘉定区', 310100, 3, 121.265, 31.376, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310115, '浦东新区', 310100, 3, 121.544, 31.222, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310116, '金山区', 310100, 3, 121.342, 30.742, 'https://github.com/modood/Administrative-divisions-of-China', '16', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310117, '松江区', 310100, 3, 121.228, 31.032, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310118, '青浦区', 310100, 3, 121.124, 31.151, 'https://github.com/modood/Administrative-divisions-of-China', '18', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310120, '奉贤区', 310100, 3, 121.474, 30.918, 'https://github.com/modood/Administrative-divisions-of-China', '20', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (310151, '崇明区', 310100, 3, 121.397, 31.624, 'https://github.com/modood/Administrative-divisions-of-China', '51', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320102, '玄武区', 320100, 3, 118.798, 32.049, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320104, '秦淮区', 320100, 3, 118.795, 32.039, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320105, '建邺区', 320100, 3, 118.732, 32.004, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320106, '鼓楼区', 320100, 3, 118.770, 32.066, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320111, '浦口区', 320100, 3, 118.628, 32.059, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320113, '栖霞区', 320100, 3, 118.909, 32.096, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320114, '雨花台区', 320100, 3, 118.779, 31.991, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320115, '江宁区', 320100, 3, 118.840, 31.954, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320116, '六合区', 320100, 3, 118.821, 32.322, 'https://github.com/modood/Administrative-divisions-of-China', '16', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320117, '溧水区', 320100, 3, 119.028, 31.651, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320118, '高淳区', 320100, 3, 118.892, 31.328, 'https://github.com/modood/Administrative-divisions-of-China', '18', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320205, '锡山区', 320200, 3, 120.358, 31.590, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320206, '惠山区', 320200, 3, 120.299, 31.681, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320211, '滨湖区', 320200, 3, 120.272, 31.470, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320213, '梁溪区', 320200, 3, 120.297, 31.576, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320214, '新吴区', 320200, 3, 120.353, 31.551, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320281, '江阴市', 320200, 3, 120.285, 31.921, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320282, '宜兴市', 320200, 3, 119.823, 31.341, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320302, '鼓楼区', 320300, 3, 117.186, 34.289, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320303, '云龙区', 320300, 3, 117.252, 34.253, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320305, '贾汪区', 320300, 3, 117.467, 34.436, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320311, '泉山区', 320300, 3, 117.194, 34.244, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320312, '铜山区', 320300, 3, 117.169, 34.181, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320321, '丰县', 320300, 3, 116.595, 34.694, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320322, '沛县', 320300, 3, 116.938, 34.722, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320324, '睢宁县', 320300, 3, 117.942, 33.913, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320371, '徐州经济技术开发区', 320300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320381, '新沂市', 320300, 3, 118.355, 34.370, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320382, '邳州市', 320300, 3, 118.013, 34.339, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320402, '天宁区', 320400, 3, 119.975, 31.780, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320404, '钟楼区', 320400, 3, 119.902, 31.802, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320411, '新北区', 320400, 3, 119.972, 31.831, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320412, '武进区', 320400, 3, 119.942, 31.701, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:53', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320413, '金坛区', 320400, 3, 119.598, 31.723, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320481, '溧阳市', 320400, 3, 119.484, 31.417, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320505, '虎丘区', 320500, 3, 120.567, 31.295, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320506, '吴中区', 320500, 3, 120.632, 31.264, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320507, '相城区', 320500, 3, 120.643, 31.369, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320508, '姑苏区', 320500, 3, 120.617, 31.336, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320509, '吴江区', 320500, 3, 120.645, 31.139, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320571, '苏州工业园区', 320500, 3, 120.807, 31.351, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320581, '常熟市', 320500, 3, 120.752, 31.654, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320582, '张家港市', 320500, 3, 120.479, 31.900, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320583, '昆山市', 320500, 3, 120.981, 31.386, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320585, '太仓市', 320500, 3, 121.131, 31.458, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320602, '崇川区', 320600, 3, 120.857, 32.010, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320611, '港闸区', 320600, 3, 120.819, 32.032, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320612, '通州区', 320600, 3, 121.075, 32.064, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320623, '如东县', 320600, 3, 121.185, 32.332, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320671, '南通经济技术开发区', 320600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320681, '启东市', 320600, 3, 121.657, 31.808, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320682, '如皋市', 320600, 3, 120.575, 32.371, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320684, '海门市', 320600, 3, 121.182, 31.871, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320685, '海安市', 320600, 3, 120.467, 32.534, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320703, '连云区', 320700, 3, 119.339, 34.760, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320706, '海州区', 320700, 3, 119.194, 34.607, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320707, '赣榆区', 320700, 3, 119.173, 34.841, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320722, '东海县', 320700, 3, 118.753, 34.542, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320723, '灌云县', 320700, 3, 119.239, 34.284, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320724, '灌南县', 320700, 3, 119.316, 34.087, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320771, '连云港经济技术开发区', 320700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320772, '连云港高新技术产业开发区', 320700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320803, '淮安区', 320800, 3, 119.141, 33.503, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320804, '淮阴区', 320800, 3, 119.035, 33.632, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320812, '清江浦区', 320800, 3, 119.015, 33.610, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320813, '洪泽区', 320800, 3, 118.873, 33.294, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320826, '涟水县', 320800, 3, 119.260, 33.781, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320830, '盱眙县', 320800, 3, 118.544, 33.012, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320831, '金湖县', 320800, 3, 119.021, 33.025, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320871, '淮安经济技术开发区', 320800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320902, '亭湖区', 320900, 3, 120.197, 33.391, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320903, '盐都区', 320900, 3, 120.154, 33.338, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320904, '大丰区', 320900, 3, 120.501, 33.200, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320921, '响水县', 320900, 3, 119.578, 34.199, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320922, '滨海县', 320900, 3, 119.821, 33.990, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320923, '阜宁县', 320900, 3, 119.803, 33.759, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320924, '射阳县', 320900, 3, 120.258, 33.775, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320925, '建湖县', 320900, 3, 119.799, 33.464, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320971, '盐城经济技术开发区', 320900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (320981, '东台市', 320900, 3, 120.320, 32.868, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321002, '广陵区', 321000, 3, 119.432, 32.395, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321003, '邗江区', 321000, 3, 119.398, 32.378, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321012, '江都区', 321000, 3, 119.570, 32.435, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321023, '宝应县', 321000, 3, 119.361, 33.240, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321071, '扬州经济技术开发区', 321000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321081, '仪征市', 321000, 3, 119.185, 32.272, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321084, '高邮市', 321000, 3, 119.459, 32.782, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321102, '京口区', 321100, 3, 119.470, 32.198, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321111, '润州区', 321100, 3, 119.412, 32.195, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321112, '丹徒区', 321100, 3, 119.434, 32.132, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321171, '镇江新区', 321100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321181, '丹阳市', 321100, 3, 119.607, 32.010, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321182, '扬中市', 321100, 3, 119.798, 32.235, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321183, '句容市', 321100, 3, 119.169, 31.945, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321202, '海陵区', 321200, 3, 119.919, 32.491, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321203, '高港区', 321200, 3, 119.882, 32.319, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321204, '姜堰区', 321200, 3, 120.128, 32.509, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321271, '泰州医药高新技术产业开发区', 321200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321281, '兴化市', 321200, 3, 119.853, 32.910, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321282, '靖江市', 321200, 3, 120.277, 31.983, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321283, '泰兴市', 321200, 3, 120.052, 32.172, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321302, '宿城区', 321300, 3, 118.243, 33.963, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321311, '宿豫区', 321300, 3, 118.331, 33.947, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321322, '沭阳县', 321300, 3, 118.805, 34.111, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321323, '泗阳县', 321300, 3, 118.703, 33.723, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321324, '泗洪县', 321300, 3, 118.224, 33.476, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (321371, '宿迁经济技术开发区', 321300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330102, '上城区', 330100, 3, 120.184, 30.254, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330103, '下城区', 330100, 3, 120.181, 30.282, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330104, '江干区', 330100, 3, 120.214, 30.263, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330105, '拱墅区', 330100, 3, 120.141, 30.319, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330106, '西湖区', 330100, 3, 120.130, 30.259, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330108, '滨江区', 330100, 3, 120.147, 30.162, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330109, '萧山区', 330100, 3, 120.264, 30.184, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330110, '余杭区', 330100, 3, 120.245, 30.410, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330111, '富阳区', 330100, 3, 119.960, 30.049, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330112, '临安区', 330100, 3, 119.725, 30.234, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330122, '桐庐县', 330100, 3, 119.691, 29.794, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330127, '淳安县', 330100, 3, 119.042, 29.609, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330182, '建德市', 330100, 3, 119.281, 29.475, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330203, '海曙区', 330200, 3, 121.551, 29.860, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330205, '江北区', 330200, 3, 121.555, 29.887, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330206, '北仑区', 330200, 3, 121.845, 29.899, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330211, '镇海区', 330200, 3, 121.717, 29.949, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330212, '鄞州区', 330200, 3, 121.547, 29.817, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330213, '奉化区', 330200, 3, 121.518, 29.570, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330225, '象山县', 330200, 3, 121.869, 29.477, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330226, '宁海县', 330200, 3, 121.429, 29.288, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330281, '余姚市', 330200, 3, 121.155, 30.038, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330282, '慈溪市', 330200, 3, 121.267, 30.170, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330302, '鹿城区', 330300, 3, 120.655, 28.015, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330303, '龙湾区', 330300, 3, 120.811, 27.933, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330304, '瓯海区', 330300, 3, 120.709, 27.914, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330305, '洞头区', 330300, 3, 121.157, 27.836, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330324, '永嘉县', 330300, 3, 120.682, 28.151, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330326, '平阳县', 330300, 3, 120.566, 27.662, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330327, '苍南县', 330300, 3, 120.426, 27.518, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330328, '文成县', 330300, 3, 120.091, 27.787, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330329, '泰顺县', 330300, 3, 119.718, 27.557, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330371, '温州经济技术开发区', 330300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330381, '瑞安市', 330300, 3, 120.655, 27.779, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330382, '乐清市', 330300, 3, 120.967, 28.116, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330383, '龙港市', 330300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330402, '南湖区', 330400, 3, 120.783, 30.748, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330411, '秀洲区', 330400, 3, 120.709, 30.765, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330421, '嘉善县', 330400, 3, 120.926, 30.831, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330424, '海盐县', 330400, 3, 120.946, 30.526, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330481, '海宁市', 330400, 3, 120.681, 30.511, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330482, '平湖市', 330400, 3, 121.015, 30.677, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330483, '桐乡市', 330400, 3, 120.565, 30.630, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330502, '吴兴区', 330500, 3, 120.186, 30.857, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330503, '南浔区', 330500, 3, 120.419, 30.850, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330521, '德清县', 330500, 3, 119.977, 30.543, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330522, '长兴县', 330500, 3, 119.911, 31.027, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330523, '安吉县', 330500, 3, 119.680, 30.639, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330602, '越城区', 330600, 3, 120.583, 29.988, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330603, '柯桥区', 330600, 3, 120.495, 30.082, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330604, '上虞区', 330600, 3, 120.868, 30.033, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330624, '新昌县', 330600, 3, 120.904, 29.500, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330681, '诸暨市', 330600, 3, 120.247, 29.709, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330683, '嵊州市', 330600, 3, 120.831, 29.561, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330702, '婺城区', 330700, 3, 119.572, 29.086, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330703, '金东区', 330700, 3, 119.693, 29.099, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330723, '武义县', 330700, 3, 119.816, 28.893, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330726, '浦江县', 330700, 3, 119.892, 29.452, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330727, '磐安县', 330700, 3, 120.450, 29.054, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330781, '兰溪市', 330700, 3, 119.461, 29.209, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330782, '义乌市', 330700, 3, 120.075, 29.307, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330783, '东阳市', 330700, 3, 120.242, 29.290, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330784, '永康市', 330700, 3, 119.647, 29.079, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330802, '柯城区', 330800, 3, 118.871, 28.969, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330803, '衢江区', 330800, 3, 118.959, 28.980, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330822, '常山县', 330800, 3, 118.511, 28.901, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330824, '开化县', 330800, 3, 118.415, 29.137, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330825, '龙游县', 330800, 3, 119.172, 29.028, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330881, '江山市', 330800, 3, 118.627, 28.737, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330902, '定海区', 330900, 3, 122.107, 30.020, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330903, '普陀区', 330900, 3, 122.324, 29.972, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330921, '岱山县', 330900, 3, 122.226, 30.264, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (330922, '嵊泗县', 330900, 3, 122.451, 30.726, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331002, '椒江区', 331000, 3, 121.443, 28.674, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331003, '黄岩区', 331000, 3, 121.262, 28.650, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331004, '路桥区', 331000, 3, 121.365, 28.583, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331022, '三门县', 331000, 3, 121.396, 29.105, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331023, '天台县', 331000, 3, 121.007, 29.144, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331024, '仙居县', 331000, 3, 120.735, 28.849, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331081, '温岭市', 331000, 3, 121.386, 28.373, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331082, '临海市', 331000, 3, 121.145, 28.858, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331083, '玉环市', 331000, 3, 121.232, 28.136, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331102, '莲都区', 331100, 3, 119.913, 28.446, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331121, '青田县', 331100, 3, 120.290, 28.139, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331122, '缙云县', 331100, 3, 120.092, 28.659, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331123, '遂昌县', 331100, 3, 119.276, 28.592, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331124, '松阳县', 331100, 3, 119.482, 28.449, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331125, '云和县', 331100, 3, 119.573, 28.116, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331126, '庆元县', 331100, 3, 119.063, 27.619, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331127, '景宁畲族自治县', 331100, 3, 119.636, 27.973, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (331181, '龙泉市', 331100, 3, 119.141, 28.075, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340102, '瑶海区', 340100, 3, 117.309, 31.858, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340103, '庐阳区', 340100, 3, 117.265, 31.879, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340104, '蜀山区', 340100, 3, 117.261, 31.851, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340111, '包河区', 340100, 3, 117.310, 31.793, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340121, '长丰县', 340100, 3, 117.168, 32.478, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340122, '肥东县', 340100, 3, 117.469, 31.888, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340123, '肥西县', 340100, 3, 117.158, 31.707, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340124, '庐江县', 340100, 3, 117.288, 31.256, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340171, '合肥高新技术产业开发区', 340100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340172, '合肥经济技术开发区', 340100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340173, '合肥新站高新技术产业开发区', 340100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340181, '巢湖市', 340100, 3, 117.862, 31.599, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340202, '镜湖区', 340200, 3, 118.385, 31.340, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340203, '弋江区', 340200, 3, 118.373, 31.312, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340207, '鸠江区', 340200, 3, 118.392, 31.369, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340208, '三山区', 340200, 3, 118.268, 31.220, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340221, '芜湖县', 340200, 3, 118.576, 31.135, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340222, '繁昌县', 340200, 3, 118.201, 31.081, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340223, '南陵县', 340200, 3, 118.334, 30.915, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340271, '芜湖经济技术开发区', 340200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340272, '安徽芜湖长江大桥经济开发区', 340200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340281, '无为市', 340200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340302, '龙子湖区', 340300, 3, 117.394, 32.943, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340303, '蚌山区', 340300, 3, 117.368, 32.944, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340304, '禹会区', 340300, 3, 117.342, 32.930, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340311, '淮上区', 340300, 3, 117.359, 32.965, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340321, '怀远县', 340300, 3, 117.205, 32.970, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340322, '五河县', 340300, 3, 117.879, 33.128, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340323, '固镇县', 340300, 3, 117.317, 33.317, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340371, '蚌埠市高新技术开发区', 340300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340372, '蚌埠市经济开发区', 340300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340402, '大通区', 340400, 3, 117.053, 32.632, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340403, '田家庵区', 340400, 3, 117.017, 32.647, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340404, '谢家集区', 340400, 3, 116.859, 32.600, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340405, '八公山区', 340400, 3, 116.833, 32.631, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340406, '潘集区', 340400, 3, 116.835, 32.772, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340421, '凤台县', 340400, 3, 116.711, 32.709, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340422, '寿县', 340400, 3, 116.787, 32.573, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340503, '花山区', 340500, 3, 118.493, 31.720, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340504, '雨山区', 340500, 3, 118.499, 31.682, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340506, '博望区', 340500, 3, 118.845, 31.558, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340521, '当涂县', 340500, 3, 118.498, 31.571, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340522, '含山县', 340500, 3, 118.101, 31.736, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340523, '和县', 340500, 3, 118.351, 31.742, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340602, '杜集区', 340600, 3, 116.828, 33.991, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340603, '相山区', 340600, 3, 116.794, 33.960, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340604, '烈山区', 340600, 3, 116.813, 33.895, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340621, '濉溪县', 340600, 3, 116.766, 33.915, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340705, '铜官区', 340700, 3, 117.816, 30.928, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340706, '义安区', 340700, 3, 117.812, 30.945, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340711, '郊区', 340700, 3, 117.807, 30.909, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340722, '枞阳县', 340700, 3, 117.251, 30.706, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340802, '迎江区', 340800, 3, 117.091, 30.512, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340803, '大观区', 340800, 3, 117.022, 30.554, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340811, '宜秀区', 340800, 3, 117.070, 30.541, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340822, '怀宁县', 340800, 3, 116.829, 30.734, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340825, '太湖县', 340800, 3, 116.309, 30.454, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340826, '宿松县', 340800, 3, 116.129, 30.154, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340827, '望江县', 340800, 3, 116.694, 30.124, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340828, '岳西县', 340800, 3, 116.360, 30.849, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340871, '安徽安庆经济开发区', 340800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340881, '桐城市', 340800, 3, 116.974, 31.036, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (340882, '潜山市', 340800, 3, 116.581, 30.631, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341002, '屯溪区', 341000, 3, 118.315, 29.696, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341003, '黄山区', 341000, 3, 118.142, 30.273, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341004, '徽州区', 341000, 3, 118.337, 29.827, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341021, '歙县', 341000, 3, 118.415, 29.861, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341022, '休宁县', 341000, 3, 118.199, 29.789, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341023, '黟县', 341000, 3, 117.938, 29.925, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341024, '祁门县', 341000, 3, 117.717, 29.854, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341102, '琅琊区', 341100, 3, 118.306, 32.295, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341103, '南谯区', 341100, 3, 118.297, 32.330, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341122, '来安县', 341100, 3, 118.436, 32.452, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341124, '全椒县', 341100, 3, 118.273, 32.085, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341125, '定远县', 341100, 3, 117.699, 32.531, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341126, '凤阳县', 341100, 3, 117.532, 32.875, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341171, '苏滁现代产业园', 341100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341172, '滁州经济技术开发区', 341100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341181, '天长市', 341100, 3, 119.005, 32.668, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341182, '明光市', 341100, 3, 118.018, 32.782, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341202, '颍州区', 341200, 3, 115.807, 32.883, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341203, '颍东区', 341200, 3, 115.857, 32.912, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341204, '颍泉区', 341200, 3, 115.808, 32.925, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341221, '临泉县', 341200, 3, 115.261, 33.040, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341222, '太和县', 341200, 3, 115.622, 33.160, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341225, '阜南县', 341200, 3, 115.596, 32.658, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341226, '颍上县', 341200, 3, 116.257, 32.653, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341271, '阜阳合肥现代产业园区', 341200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341272, '阜阳经济技术开发区', 341200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341282, '界首市', 341200, 3, 115.375, 33.257, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341302, '埇桥区', 341300, 3, 116.977, 33.640, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341321, '砀山县', 341300, 3, 116.367, 34.443, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341322, '萧县', 341300, 3, 116.947, 34.189, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341323, '灵璧县', 341300, 3, 117.552, 33.553, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341324, '泗县', 341300, 3, 117.911, 33.483, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341371, '宿州马鞍山现代产业园区', 341300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341372, '宿州经济技术开发区', 341300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341502, '金安区', 341500, 3, 116.540, 31.749, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341503, '裕安区', 341500, 3, 116.480, 31.738, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341504, '叶集区', 341500, 3, 115.909, 31.849, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341522, '霍邱县', 341500, 3, 116.278, 32.353, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341523, '舒城县', 341500, 3, 116.949, 31.462, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341524, '金寨县', 341500, 3, 115.934, 31.727, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341525, '霍山县', 341500, 3, 116.333, 31.393, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341602, '谯城区', 341600, 3, 115.779, 33.876, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341621, '涡阳县', 341600, 3, 116.216, 33.493, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341622, '蒙城县', 341600, 3, 116.564, 33.266, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341623, '利辛县', 341600, 3, 116.209, 33.145, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341702, '贵池区', 341700, 3, 117.567, 30.687, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341721, '东至县', 341700, 3, 117.028, 30.111, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341722, '石台县', 341700, 3, 117.486, 30.210, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341723, '青阳县', 341700, 3, 117.847, 30.639, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341802, '宣州区', 341800, 3, 118.756, 30.946, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341821, '郎溪县', 341800, 3, 119.180, 31.126, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:54', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341823, '泾县', 341800, 3, 118.420, 30.689, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341824, '绩溪县', 341800, 3, 118.579, 30.068, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341825, '旌德县', 341800, 3, 118.540, 30.286, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341871, '宣城市经济开发区', 341800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341881, '宁国市', 341800, 3, 118.983, 30.634, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (341882, '广德市', 341800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350102, '鼓楼区', 350100, 3, 119.304, 26.082, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350103, '台江区', 350100, 3, 119.314, 26.053, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350104, '仓山区', 350100, 3, 119.274, 26.047, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350105, '马尾区', 350100, 3, 119.456, 25.990, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350111, '晋安区', 350100, 3, 119.329, 26.082, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350112, '长乐区', 350100, 3, 119.523, 25.963, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350121, '闽侯县', 350100, 3, 119.132, 26.150, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350122, '连江县', 350100, 3, 119.540, 26.197, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350123, '罗源县', 350100, 3, 119.550, 26.490, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350124, '闽清县', 350100, 3, 118.863, 26.221, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350125, '永泰县', 350100, 3, 118.933, 25.867, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350128, '平潭县', 350100, 3, 119.790, 25.499, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350181, '福清市', 350100, 3, 119.384, 25.721, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350203, '思明区', 350200, 3, 118.083, 24.446, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350205, '海沧区', 350200, 3, 118.033, 24.485, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350206, '湖里区', 350200, 3, 118.147, 24.513, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350211, '集美区', 350200, 3, 118.097, 24.576, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350212, '同安区', 350200, 3, 118.152, 24.723, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350213, '翔安区', 350200, 3, 118.248, 24.619, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350302, '城厢区', 350300, 3, 118.994, 25.419, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350303, '涵江区', 350300, 3, 119.116, 25.459, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350304, '荔城区', 350300, 3, 119.015, 25.432, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350305, '秀屿区', 350300, 3, 119.106, 25.319, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350322, '仙游县', 350300, 3, 118.692, 25.362, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350402, '梅列区', 350400, 3, 117.646, 26.272, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350403, '三元区', 350400, 3, 117.608, 26.234, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350421, '明溪县', 350400, 3, 117.202, 26.357, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350423, '清流县', 350400, 3, 116.817, 26.178, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350424, '宁化县', 350400, 3, 116.654, 26.262, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350425, '大田县', 350400, 3, 117.847, 25.693, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350426, '尤溪县', 350400, 3, 118.190, 26.170, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350427, '沙县', 350400, 3, 117.792, 26.397, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350428, '将乐县', 350400, 3, 117.471, 26.729, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350429, '泰宁县', 350400, 3, 117.176, 26.900, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350430, '建宁县', 350400, 3, 116.846, 26.831, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350481, '永安市', 350400, 3, 117.374, 25.976, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350502, '鲤城区', 350500, 3, 118.587, 24.908, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350503, '丰泽区', 350500, 3, 118.669, 24.871, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350504, '洛江区', 350500, 3, 118.671, 24.940, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350505, '泉港区', 350500, 3, 118.916, 25.120, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350521, '惠安县', 350500, 3, 118.797, 25.031, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350524, '安溪县', 350500, 3, 118.186, 25.056, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350525, '永春县', 350500, 3, 118.676, 24.874, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350526, '德化县', 350500, 3, 118.241, 25.491, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350527, '金门县', 350500, 3, 118.317, 24.433, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350581, '石狮市', 350500, 3, 118.648, 24.732, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350582, '晋江市', 350500, 3, 118.552, 24.782, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350583, '南安市', 350500, 3, 118.386, 24.960, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350602, '芗城区', 350600, 3, 117.654, 24.511, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350603, '龙文区', 350600, 3, 117.710, 24.503, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350622, '云霄县', 350600, 3, 117.340, 23.958, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350623, '漳浦县', 350600, 3, 117.614, 24.117, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350624, '诏安县', 350600, 3, 117.175, 23.712, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350625, '长泰县', 350600, 3, 117.759, 24.625, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350626, '东山县', 350600, 3, 117.430, 23.701, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350627, '南靖县', 350600, 3, 117.357, 24.515, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350628, '平和县', 350600, 3, 117.315, 24.363, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350629, '华安县', 350600, 3, 117.534, 25.004, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350681, '龙海市', 350600, 3, 117.818, 24.447, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350702, '延平区', 350700, 3, 118.182, 26.637, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350703, '建阳区', 350700, 3, 118.120, 27.332, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350721, '顺昌县', 350700, 3, 117.810, 26.793, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350722, '浦城县', 350700, 3, 118.541, 27.917, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350723, '光泽县', 350700, 3, 117.334, 27.541, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350724, '松溪县', 350700, 3, 118.785, 27.526, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350725, '政和县', 350700, 3, 118.858, 27.366, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350781, '邵武市', 350700, 3, 117.493, 27.340, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350782, '武夷山市', 350700, 3, 118.035, 27.757, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350783, '建瓯市', 350700, 3, 118.305, 27.023, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350802, '新罗区', 350800, 3, 117.037, 25.098, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350803, '永定区', 350800, 3, 116.732, 24.724, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350821, '长汀县', 350800, 3, 116.358, 25.834, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350823, '上杭县', 350800, 3, 116.420, 25.050, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350824, '武平县', 350800, 3, 116.100, 25.095, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350825, '连城县', 350800, 3, 116.754, 25.711, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350881, '漳平市', 350800, 3, 117.420, 25.290, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350902, '蕉城区', 350900, 3, 119.526, 26.661, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350921, '霞浦县', 350900, 3, 120.006, 26.885, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350922, '古田县', 350900, 3, 118.746, 26.578, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350923, '屏南县', 350900, 3, 118.986, 26.908, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350924, '寿宁县', 350900, 3, 119.515, 27.454, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350925, '周宁县', 350900, 3, 119.339, 27.105, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350926, '柘荣县', 350900, 3, 119.901, 27.234, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350981, '福安市', 350900, 3, 119.652, 27.087, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (350982, '福鼎市', 350900, 3, 120.217, 27.324, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360102, '东湖区', 360100, 3, 115.899, 28.685, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360103, '西湖区', 360100, 3, 115.877, 28.658, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360104, '青云谱区', 360100, 3, 115.926, 28.621, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360111, '青山湖区', 360100, 3, 115.962, 28.683, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360112, '新建区', 360100, 3, 115.815, 28.693, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360113, '红谷滩区', 360100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360121, '南昌县', 360100, 3, 115.944, 28.546, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360123, '安义县', 360100, 3, 115.549, 28.845, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360124, '进贤县', 360100, 3, 116.269, 28.367, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360202, '昌江区', 360200, 3, 117.184, 29.273, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360203, '珠山区', 360200, 3, 117.203, 29.300, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360222, '浮梁县', 360200, 3, 117.215, 29.352, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360281, '乐平市', 360200, 3, 117.152, 28.978, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360302, '安源区', 360300, 3, 113.871, 27.615, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360313, '湘东区', 360300, 3, 113.733, 27.640, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360321, '莲花县', 360300, 3, 113.962, 27.128, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360322, '上栗县', 360300, 3, 113.795, 27.880, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360323, '芦溪县', 360300, 3, 114.030, 27.631, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360402, '濂溪区', 360400, 3, 115.989, 29.672, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360403, '浔阳区', 360400, 3, 115.990, 29.728, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360404, '柴桑区', 360400, 3, 115.911, 29.608, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360423, '武宁县', 360400, 3, 115.101, 29.256, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360424, '修水县', 360400, 3, 114.547, 29.026, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360425, '永修县', 360400, 3, 115.832, 29.012, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360426, '德安县', 360400, 3, 115.767, 29.299, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360428, '都昌县', 360400, 3, 116.204, 29.273, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360429, '湖口县', 360400, 3, 116.252, 29.731, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360430, '彭泽县', 360400, 3, 116.549, 29.896, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360481, '瑞昌市', 360400, 3, 115.681, 29.676, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360482, '共青城市', 360400, 3, 115.809, 29.248, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360483, '庐山市', 360400, 3, 116.045, 29.448, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360502, '渝水区', 360500, 3, 114.945, 27.800, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360521, '分宜县', 360500, 3, 114.692, 27.815, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360602, '月湖区', 360600, 3, 117.037, 28.239, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360603, '余江区', 360600, 3, 116.823, 28.206, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360681, '贵溪市', 360600, 3, 117.245, 28.293, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360702, '章贡区', 360700, 3, 114.921, 25.818, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360703, '南康区', 360700, 3, 114.935, 25.832, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360704, '赣县区', 360700, 3, 115.012, 25.861, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360722, '信丰县', 360700, 3, 114.923, 25.387, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360723, '大余县', 360700, 3, 114.362, 25.401, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360724, '上犹县', 360700, 3, 114.551, 25.785, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360725, '崇义县', 360700, 3, 114.308, 25.682, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360726, '安远县', 360700, 3, 115.394, 25.137, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360728, '定南县', 360700, 3, 115.028, 24.784, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360729, '全南县', 360700, 3, 114.530, 24.742, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360730, '宁都县', 360700, 3, 116.014, 26.471, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360731, '于都县', 360700, 3, 115.416, 25.952, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360732, '兴国县', 360700, 3, 115.363, 26.338, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360733, '会昌县', 360700, 3, 115.786, 25.600, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360734, '寻乌县', 360700, 3, 115.647, 24.963, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360735, '石城县', 360700, 3, 116.347, 26.315, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360781, '瑞金市', 360700, 3, 116.027, 25.886, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360783, '龙南市', 360700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360802, '吉州区', 360800, 3, 114.995, 27.144, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360803, '青原区', 360800, 3, 115.015, 27.082, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360821, '吉安县', 360800, 3, 114.908, 27.040, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360822, '吉水县', 360800, 3, 115.136, 27.230, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360823, '峡江县', 360800, 3, 115.317, 27.583, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360824, '新干县', 360800, 3, 115.387, 27.740, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360825, '永丰县', 360800, 3, 115.444, 27.319, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360826, '泰和县', 360800, 3, 114.909, 26.790, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360827, '遂川县', 360800, 3, 114.521, 26.314, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360828, '万安县', 360800, 3, 114.786, 26.458, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360829, '安福县', 360800, 3, 114.620, 27.393, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360830, '永新县', 360800, 3, 114.243, 26.945, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360881, '井冈山市', 360800, 3, 114.289, 26.748, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360902, '袁州区', 360900, 3, 114.425, 27.799, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360921, '奉新县', 360900, 3, 115.400, 28.688, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360922, '万载县', 360900, 3, 114.446, 28.106, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360923, '上高县', 360900, 3, 114.948, 28.239, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360924, '宜丰县', 360900, 3, 114.804, 28.394, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360925, '靖安县', 360900, 3, 115.363, 28.861, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360926, '铜鼓县', 360900, 3, 114.371, 28.521, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360981, '丰城市', 360900, 3, 115.771, 28.159, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360982, '樟树市', 360900, 3, 115.546, 28.055, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (360983, '高安市', 360900, 3, 115.376, 28.417, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361002, '临川区', 361000, 3, 116.371, 27.971, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361003, '东乡区', 361000, 3, 116.604, 28.248, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361021, '南城县', 361000, 3, 116.637, 27.570, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361022, '黎川县', 361000, 3, 116.908, 27.282, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361023, '南丰县', 361000, 3, 116.526, 27.218, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361024, '崇仁县', 361000, 3, 116.061, 27.764, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361025, '乐安县', 361000, 3, 115.830, 27.429, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361026, '宜黄县', 361000, 3, 116.222, 27.546, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361027, '金溪县', 361000, 3, 116.755, 27.919, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361028, '资溪县', 361000, 3, 117.060, 27.706, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361030, '广昌县', 361000, 3, 116.326, 26.837, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361102, '信州区', 361100, 3, 117.966, 28.431, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361103, '广丰区', 361100, 3, 118.191, 28.436, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361104, '广信区', 361100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361123, '玉山县', 361100, 3, 118.245, 28.682, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361124, '铅山县', 361100, 3, 117.709, 28.315, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361125, '横峰县', 361100, 3, 117.596, 28.407, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361126, '弋阳县', 361100, 3, 117.450, 28.378, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361127, '余干县', 361100, 3, 116.696, 28.702, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361128, '鄱阳县', 361100, 3, 116.700, 29.012, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361129, '万年县', 361100, 3, 117.058, 28.695, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361130, '婺源县', 361100, 3, 117.862, 29.248, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (361181, '德兴市', 361100, 3, 117.579, 28.946, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370102, '历下区', 370100, 3, 117.076, 36.666, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370103, '市中区', 370100, 3, 116.998, 36.651, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370104, '槐荫区', 370100, 3, 116.901, 36.651, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370105, '天桥区', 370100, 3, 116.987, 36.678, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370112, '历城区', 370100, 3, 117.065, 36.680, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370113, '长清区', 370100, 3, 116.752, 36.554, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370114, '章丘区', 370100, 3, 117.526, 36.681, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370115, '济阳区', 370100, 3, 117.174, 36.979, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370116, '莱芜区', 370100, 3, 117.678, 36.214, 'https://github.com/modood/Administrative-divisions-of-China', '16', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370117, '钢城区', 370100, 3, 117.120, 36.651, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370124, '平阴县', 370100, 3, 116.456, 36.289, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370126, '商河县', 370100, 3, 117.157, 37.309, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370171, '济南高新技术产业开发区', 370100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370202, '市南区', 370200, 3, 120.386, 36.080, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370203, '市北区', 370200, 3, 120.375, 36.088, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370211, '黄岛区', 370200, 3, 120.046, 35.873, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370212, '崂山区', 370200, 3, 120.469, 36.108, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370213, '李沧区', 370200, 3, 120.433, 36.145, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370214, '城阳区', 370200, 3, 120.396, 36.307, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370215, '即墨区', 370200, 3, 120.447, 36.390, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370271, '青岛高新技术产业开发区', 370200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370281, '胶州市', 370200, 3, 120.033, 36.265, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370283, '平度市', 370200, 3, 119.988, 36.777, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370285, '莱西市', 370200, 3, 120.518, 36.889, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370302, '淄川区', 370300, 3, 117.967, 36.643, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370303, '张店区', 370300, 3, 118.018, 36.807, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370304, '博山区', 370300, 3, 117.862, 36.495, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370305, '临淄区', 370300, 3, 118.309, 36.827, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370306, '周村区', 370300, 3, 117.870, 36.803, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370321, '桓台县', 370300, 3, 118.098, 36.960, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370322, '高青县', 370300, 3, 117.827, 37.171, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370323, '沂源县', 370300, 3, 118.171, 36.185, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370402, '市中区', 370400, 3, 117.556, 34.864, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370403, '薛城区', 370400, 3, 117.263, 34.795, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370404, '峄城区', 370400, 3, 117.591, 34.773, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370405, '台儿庄区', 370400, 3, 117.734, 34.563, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370406, '山亭区', 370400, 3, 117.462, 35.100, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370481, '滕州市', 370400, 3, 117.166, 35.114, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370502, '东营区', 370500, 3, 118.582, 37.449, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370503, '河口区', 370500, 3, 118.526, 37.886, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370505, '垦利区', 370500, 3, 118.675, 37.435, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370522, '利津县', 370500, 3, 118.255, 37.490, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370523, '广饶县', 370500, 3, 118.407, 37.054, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370571, '东营经济技术开发区', 370500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370572, '东营港经济开发区', 370500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370602, '芝罘区', 370600, 3, 121.400, 37.541, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370611, '福山区', 370600, 3, 121.268, 37.498, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370612, '牟平区', 370600, 3, 121.601, 37.387, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370613, '莱山区', 370600, 3, 121.445, 37.511, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370614, '蓬莱区', 370600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370671, '烟台高新技术产业开发区', 370600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370672, '烟台经济技术开发区', 370600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370681, '龙口市', 370600, 3, 120.478, 37.646, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370682, '莱阳市', 370600, 3, 120.712, 36.979, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370683, '莱州市', 370600, 3, 119.942, 37.177, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370685, '招远市', 370600, 3, 120.434, 37.355, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:55', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370686, '栖霞市', 370600, 3, 120.850, 37.335, 'https://github.com/modood/Administrative-divisions-of-China', '86', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370687, '海阳市', 370600, 3, 121.158, 36.776, 'https://github.com/modood/Administrative-divisions-of-China', '87', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370702, '潍城区', 370700, 3, 119.025, 36.728, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370703, '寒亭区', 370700, 3, 119.220, 36.775, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370704, '坊子区', 370700, 3, 119.166, 36.654, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370705, '奎文区', 370700, 3, 119.132, 36.708, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370724, '临朐县', 370700, 3, 118.543, 36.513, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370725, '昌乐县', 370700, 3, 118.830, 36.707, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370772, '潍坊滨海经济技术开发区', 370700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370781, '青州市', 370700, 3, 118.480, 36.685, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370782, '诸城市', 370700, 3, 119.410, 35.996, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370783, '寿光市', 370700, 3, 118.791, 36.855, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370784, '安丘市', 370700, 3, 119.219, 36.478, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370785, '高密市', 370700, 3, 119.756, 36.383, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370786, '昌邑市', 370700, 3, 119.399, 36.859, 'https://github.com/modood/Administrative-divisions-of-China', '86', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370811, '任城区', 370800, 3, 116.595, 35.407, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370812, '兖州区', 370800, 3, 116.784, 35.553, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370826, '微山县', 370800, 3, 117.129, 34.807, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370827, '鱼台县', 370800, 3, 116.651, 35.013, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370828, '金乡县', 370800, 3, 116.312, 35.067, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370829, '嘉祥县', 370800, 3, 116.342, 35.408, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370830, '汶上县', 370800, 3, 116.489, 35.733, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370831, '泗水县', 370800, 3, 117.251, 35.664, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370832, '梁山县', 370800, 3, 116.096, 35.802, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370871, '济宁高新技术产业开发区', 370800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370881, '曲阜市', 370800, 3, 116.987, 35.581, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370883, '邹城市', 370800, 3, 117.004, 35.405, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370902, '泰山区', 370900, 3, 117.135, 36.192, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370911, '岱岳区', 370900, 3, 117.042, 36.188, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370921, '宁阳县', 370900, 3, 116.806, 35.759, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370923, '东平县', 370900, 3, 116.470, 35.937, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370982, '新泰市', 370900, 3, 117.768, 35.909, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (370983, '肥城市', 370900, 3, 116.768, 36.183, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371002, '环翠区', 371000, 3, 122.123, 37.502, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371003, '文登区', 371000, 3, 122.058, 37.194, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371071, '威海火炬高技术产业开发区', 371000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371072, '威海经济技术开发区', 371000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371073, '威海临港经济技术开发区', 371000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371082, '荣成市', 371000, 3, 122.487, 37.165, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371083, '乳山市', 371000, 3, 121.540, 36.920, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371102, '东港区', 371100, 3, 119.462, 35.425, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371103, '岚山区', 371100, 3, 119.319, 35.122, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371121, '五莲县', 371100, 3, 119.207, 35.752, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371122, '莒县', 371100, 3, 118.837, 35.580, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371171, '日照经济技术开发区', 371100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371302, '兰山区', 371300, 3, 118.348, 35.052, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371311, '罗庄区', 371300, 3, 118.285, 34.997, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371312, '河东区', 371300, 3, 118.403, 35.090, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371321, '沂南县', 371300, 3, 118.465, 35.550, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371322, '郯城县', 371300, 3, 118.367, 34.614, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371323, '沂水县', 371300, 3, 118.628, 35.790, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371324, '兰陵县', 371300, 3, 118.071, 34.857, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371325, '费县', 371300, 3, 117.977, 35.266, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371326, '平邑县', 371300, 3, 117.640, 35.506, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371327, '莒南县', 371300, 3, 118.835, 35.175, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371328, '蒙阴县', 371300, 3, 117.945, 35.710, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371329, '临沭县', 371300, 3, 118.651, 34.920, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371371, '临沂高新技术产业开发区', 371300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371402, '德城区', 371400, 3, 116.299, 37.451, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371403, '陵城区', 371400, 3, 116.576, 37.336, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371422, '宁津县', 371400, 3, 116.800, 37.652, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371423, '庆云县', 371400, 3, 117.385, 37.775, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371424, '临邑县', 371400, 3, 116.867, 37.190, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371425, '齐河县', 371400, 3, 116.763, 36.783, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371426, '平原县', 371400, 3, 116.434, 37.165, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371427, '夏津县', 371400, 3, 116.002, 36.948, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371428, '武城县', 371400, 3, 116.069, 37.213, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371471, '德州经济技术开发区', 371400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371472, '德州运河经济开发区', 371400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371481, '乐陵市', 371400, 3, 117.232, 37.730, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371482, '禹城市', 371400, 3, 116.638, 36.934, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371502, '东昌府区', 371500, 3, 115.988, 36.435, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371503, '茌平区', 371500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371521, '阳谷县', 371500, 3, 115.792, 36.114, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371522, '莘县', 371500, 3, 115.671, 36.234, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371524, '东阿县', 371500, 3, 116.248, 36.335, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371525, '冠县', 371500, 3, 115.443, 36.484, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371526, '高唐县', 371500, 3, 116.230, 36.847, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371581, '临清市', 371500, 3, 115.705, 36.838, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371602, '滨城区', 371600, 3, 118.019, 37.431, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371603, '沾化区', 371600, 3, 118.099, 37.699, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371621, '惠民县', 371600, 3, 117.510, 37.490, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371622, '阳信县', 371600, 3, 117.578, 37.641, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371623, '无棣县', 371600, 3, 117.626, 37.770, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371625, '博兴县', 371600, 3, 118.132, 37.150, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371681, '邹平市', 371600, 3, 117.743, 36.863, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371702, '牡丹区', 371700, 3, 115.418, 35.253, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371703, '定陶区', 371700, 3, 115.570, 35.073, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371721, '曹县', 371700, 3, 115.542, 34.826, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371722, '单县', 371700, 3, 116.107, 34.779, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371723, '成武县', 371700, 3, 115.890, 34.952, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371724, '巨野县', 371700, 3, 116.065, 35.387, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371725, '郓城县', 371700, 3, 115.944, 35.600, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371726, '鄄城县', 371700, 3, 115.510, 35.563, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371728, '东明县', 371700, 3, 115.090, 35.289, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371771, '菏泽经济技术开发区', 371700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (371772, '菏泽高新技术开发区', 371700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410102, '中原区', 410100, 3, 113.613, 34.748, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410103, '二七区', 410100, 3, 113.640, 34.724, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410104, '管城回族区', 410100, 3, 113.678, 34.755, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410105, '金水区', 410100, 3, 113.661, 34.800, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410106, '上街区', 410100, 3, 113.309, 34.803, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410108, '惠济区', 410100, 3, 113.617, 34.867, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410122, '中牟县', 410100, 3, 113.976, 34.719, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410171, '郑州经济技术开发区', 410100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410172, '郑州高新技术产业开发区', 410100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410173, '郑州航空港经济综合实验区', 410100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410181, '巩义市', 410100, 3, 113.022, 34.748, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410182, '荥阳市', 410100, 3, 113.440, 34.746, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410183, '新密市', 410100, 3, 113.391, 34.539, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410184, '新郑市', 410100, 3, 113.741, 34.396, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410185, '登封市', 410100, 3, 113.050, 34.454, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410202, '龙亭区', 410200, 3, 114.355, 34.816, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410203, '顺河回族区', 410200, 3, 114.365, 34.800, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410204, '鼓楼区', 410200, 3, 114.348, 34.789, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410205, '禹王台区', 410200, 3, 114.348, 34.777, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410212, '祥符区', 410200, 3, 114.441, 34.757, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410221, '杞县', 410200, 3, 114.783, 34.549, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410222, '通许县', 410200, 3, 114.467, 34.480, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410223, '尉氏县', 410200, 3, 114.193, 34.411, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410225, '兰考县', 410200, 3, 114.821, 34.822, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410302, '老城区', 410300, 3, 112.469, 34.684, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410303, '西工区', 410300, 3, 112.428, 34.660, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410304, '瀍河回族区', 410300, 3, 112.500, 34.680, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410305, '涧西区', 410300, 3, 112.396, 34.658, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410306, '吉利区', 410300, 3, 112.589, 34.901, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410311, '洛龙区', 410300, 3, 112.464, 34.619, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410322, '孟津县', 410300, 3, 112.445, 34.825, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410323, '新安县', 410300, 3, 112.132, 34.729, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410324, '栾川县', 410300, 3, 111.616, 33.786, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410325, '嵩县', 410300, 3, 112.086, 34.135, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410326, '汝阳县', 410300, 3, 112.473, 34.154, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410327, '宜阳县', 410300, 3, 112.179, 34.515, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410328, '洛宁县', 410300, 3, 111.653, 34.389, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410329, '伊川县', 410300, 3, 112.426, 34.421, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410371, '洛阳高新技术产业开发区', 410300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410381, '偃师市', 410300, 3, 112.790, 34.727, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410402, '新华区', 410400, 3, 113.294, 33.737, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410403, '卫东区', 410400, 3, 113.335, 33.735, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410404, '石龙区', 410400, 3, 112.899, 33.899, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410411, '湛河区', 410400, 3, 113.321, 33.726, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410421, '宝丰县', 410400, 3, 113.055, 33.868, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410422, '叶县', 410400, 3, 113.357, 33.627, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410423, '鲁山县', 410400, 3, 112.908, 33.739, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410425, '郏县', 410400, 3, 113.213, 33.972, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410471, '平顶山高新技术产业开发区', 410400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410472, '平顶山市城乡一体化示范区', 410400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410481, '舞钢市', 410400, 3, 113.525, 33.308, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410482, '汝州市', 410400, 3, 112.845, 34.167, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410502, '文峰区', 410500, 3, 114.357, 36.090, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410503, '北关区', 410500, 3, 114.356, 36.107, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410505, '殷都区', 410500, 3, 114.303, 36.110, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410506, '龙安区', 410500, 3, 114.324, 36.096, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410522, '安阳县', 410500, 3, 114.130, 36.131, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410523, '汤阴县', 410500, 3, 114.358, 35.925, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410526, '滑县', 410500, 3, 114.519, 35.575, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410527, '内黄县', 410500, 3, 114.901, 35.972, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410571, '安阳高新技术产业开发区', 410500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410581, '林州市', 410500, 3, 113.820, 36.083, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410602, '鹤山区', 410600, 3, 114.163, 35.955, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410603, '山城区', 410600, 3, 114.184, 35.898, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410611, '淇滨区', 410600, 3, 114.299, 35.741, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410621, '浚县', 410600, 3, 114.551, 35.676, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410622, '淇县', 410600, 3, 114.198, 35.608, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410671, '鹤壁经济技术开发区', 410600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410702, '红旗区', 410700, 3, 113.875, 35.304, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410703, '卫滨区', 410700, 3, 113.866, 35.302, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410704, '凤泉区', 410700, 3, 113.915, 35.384, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410711, '牧野区', 410700, 3, 113.909, 35.315, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410721, '新乡县', 410700, 3, 113.805, 35.191, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410724, '获嘉县', 410700, 3, 113.657, 35.260, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410725, '原阳县', 410700, 3, 113.940, 35.066, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410726, '延津县', 410700, 3, 114.205, 35.142, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410727, '封丘县', 410700, 3, 114.419, 35.041, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410771, '新乡高新技术产业开发区', 410700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410772, '新乡经济技术开发区', 410700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410773, '新乡市平原城乡一体化示范区', 410700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410781, '卫辉市', 410700, 3, 114.065, 35.398, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410782, '辉县市', 410700, 3, 113.805, 35.462, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410783, '长垣市', 410700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410802, '解放区', 410800, 3, 113.231, 35.240, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410803, '中站区', 410800, 3, 113.183, 35.237, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410804, '马村区', 410800, 3, 113.322, 35.256, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410811, '山阳区', 410800, 3, 113.255, 35.215, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410821, '修武县', 410800, 3, 113.448, 35.224, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410822, '博爱县', 410800, 3, 113.069, 35.166, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410823, '武陟县', 410800, 3, 113.402, 35.099, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410825, '温县', 410800, 3, 113.081, 34.940, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410871, '焦作城乡一体化示范区', 410800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410882, '沁阳市', 410800, 3, 112.943, 35.091, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410883, '孟州市', 410800, 3, 112.790, 34.908, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410902, '华龙区', 410900, 3, 115.074, 35.777, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410922, '清丰县', 410900, 3, 115.104, 35.885, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410923, '南乐县', 410900, 3, 115.205, 36.070, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410926, '范县', 410900, 3, 115.504, 35.852, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410927, '台前县', 410900, 3, 115.872, 35.969, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410928, '濮阳县', 410900, 3, 115.029, 35.712, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410971, '河南濮阳工业园区', 410900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (410972, '濮阳经济技术开发区', 410900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411002, '魏都区', 411000, 3, 113.823, 34.025, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411003, '建安区', 411000, 3, 113.823, 34.125, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411024, '鄢陵县', 411000, 3, 114.177, 34.102, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411025, '襄城县', 411000, 3, 113.489, 33.866, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411071, '许昌经济技术开发区', 411000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411081, '禹州市', 411000, 3, 113.488, 34.141, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411082, '长葛市', 411000, 3, 113.820, 34.194, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411102, '源汇区', 411100, 3, 113.911, 33.582, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411103, '郾城区', 411100, 3, 114.007, 33.587, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411104, '召陵区', 411100, 3, 114.094, 33.587, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411121, '舞阳县', 411100, 3, 113.609, 33.438, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411122, '临颍县', 411100, 3, 113.931, 33.827, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411171, '漯河经济技术开发区', 411100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411202, '湖滨区', 411200, 3, 111.188, 34.771, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411203, '陕州区', 411200, 3, 111.104, 34.721, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411221, '渑池县', 411200, 3, 111.762, 34.767, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411224, '卢氏县', 411200, 3, 111.048, 34.054, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411271, '河南三门峡经济开发区', 411200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411281, '义马市', 411200, 3, 111.874, 34.747, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411282, '灵宝市', 411200, 3, 110.894, 34.517, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411302, '宛城区', 411300, 3, 112.540, 33.004, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411303, '卧龙区', 411300, 3, 112.529, 32.990, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411321, '南召县', 411300, 3, 112.429, 33.490, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411322, '方城县', 411300, 3, 113.012, 33.254, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411323, '西峡县', 411300, 3, 111.474, 33.307, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411324, '镇平县', 411300, 3, 112.235, 33.034, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411325, '内乡县', 411300, 3, 111.849, 33.045, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411326, '淅川县', 411300, 3, 111.491, 33.138, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411327, '社旗县', 411300, 3, 112.948, 33.056, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411328, '唐河县', 411300, 3, 112.808, 32.681, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411329, '新野县', 411300, 3, 112.360, 32.521, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411330, '桐柏县', 411300, 3, 113.428, 32.380, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411371, '南阳高新技术产业开发区', 411300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411372, '南阳市城乡一体化示范区', 411300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411381, '邓州市', 411300, 3, 112.087, 32.688, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411402, '梁园区', 411400, 3, 115.614, 34.444, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411403, '睢阳区', 411400, 3, 115.653, 34.388, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411421, '民权县', 411400, 3, 115.180, 34.648, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411422, '睢县', 411400, 3, 115.072, 34.446, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411423, '宁陵县', 411400, 3, 115.314, 34.460, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411424, '柘城县', 411400, 3, 115.306, 34.091, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411425, '虞城县', 411400, 3, 115.841, 34.403, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411426, '夏邑县', 411400, 3, 116.131, 34.238, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411471, '豫东综合物流产业聚集区', 411400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411472, '河南商丘经济开发区', 411400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411481, '永城市', 411400, 3, 116.450, 33.929, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411502, '浉河区', 411500, 3, 114.059, 32.117, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411503, '平桥区', 411500, 3, 114.126, 32.101, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411521, '罗山县', 411500, 3, 114.513, 32.203, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411522, '光山县', 411500, 3, 114.919, 32.011, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411523, '新县', 411500, 3, 114.879, 31.644, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411524, '商城县', 411500, 3, 115.407, 31.798, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411525, '固始县', 411500, 3, 115.654, 32.168, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411526, '潢川县', 411500, 3, 115.052, 32.131, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411527, '淮滨县', 411500, 3, 115.420, 32.473, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411528, '息县', 411500, 3, 114.740, 32.343, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411571, '信阳高新技术产业开发区', 411500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411602, '川汇区', 411600, 3, 114.651, 33.648, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411603, '淮阳区', 411600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411621, '扶沟县', 411600, 3, 114.395, 34.060, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411622, '西华县', 411600, 3, 114.425, 33.809, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411623, '商水县', 411600, 3, 114.612, 33.542, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411624, '沈丘县', 411600, 3, 115.099, 33.409, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411625, '郸城县', 411600, 3, 115.177, 33.645, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411627, '太康县', 411600, 3, 114.838, 34.064, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411628, '鹿邑县', 411600, 3, 115.484, 33.860, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411671, '河南周口经济开发区', 411600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411681, '项城市', 411600, 3, 114.875, 33.466, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411702, '驿城区', 411700, 3, 113.994, 32.973, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411721, '西平县', 411700, 3, 114.022, 33.388, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411722, '上蔡县', 411700, 3, 114.264, 33.262, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411723, '平舆县', 411700, 3, 114.619, 32.963, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411724, '正阳县', 411700, 3, 114.393, 32.606, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411725, '确山县', 411700, 3, 114.026, 32.802, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411726, '泌阳县', 411700, 3, 113.327, 32.724, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411727, '汝南县', 411700, 3, 114.362, 33.007, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411728, '遂平县', 411700, 3, 114.013, 33.146, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411729, '新蔡县', 411700, 3, 114.921, 32.811, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (411771, '河南驻马店经济开发区', 411700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (419001, '济源市', 419000, 3, 112.602, 35.069, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420102, '江岸区', 420100, 3, 114.279, 30.593, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420103, '江汉区', 420100, 3, 114.271, 30.601, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420104, '硚口区', 420100, 3, 114.266, 30.571, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:56', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420105, '汉阳区', 420100, 3, 114.219, 30.554, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420106, '武昌区', 420100, 3, 114.316, 30.554, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420107, '青山区', 420100, 3, 114.386, 30.640, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420111, '洪山区', 420100, 3, 114.344, 30.500, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420112, '东西湖区', 420100, 3, 114.137, 30.620, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420113, '汉南区', 420100, 3, 114.084, 30.309, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420114, '蔡甸区', 420100, 3, 114.029, 30.582, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420115, '江夏区', 420100, 3, 114.322, 30.376, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420116, '黄陂区', 420100, 3, 114.376, 30.883, 'https://github.com/modood/Administrative-divisions-of-China', '16', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420117, '新洲区', 420100, 3, 114.801, 30.842, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420202, '黄石港区', 420200, 3, 115.066, 30.223, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420203, '西塞山区', 420200, 3, 115.110, 30.205, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420204, '下陆区', 420200, 3, 114.961, 30.174, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420205, '铁山区', 420200, 3, 114.901, 30.207, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420222, '阳新县', 420200, 3, 115.215, 29.830, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420281, '大冶市', 420200, 3, 114.980, 30.096, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420302, '茅箭区', 420300, 3, 110.814, 32.592, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420303, '张湾区', 420300, 3, 110.769, 32.652, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420304, '郧阳区', 420300, 3, 110.812, 32.835, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420322, '郧西县', 420300, 3, 110.426, 32.993, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420323, '竹山县', 420300, 3, 110.229, 32.225, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420324, '竹溪县', 420300, 3, 109.715, 32.318, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420325, '房县', 420300, 3, 110.727, 32.040, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420381, '丹江口市', 420300, 3, 111.513, 32.540, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420502, '西陵区', 420500, 3, 111.286, 30.711, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420503, '伍家岗区', 420500, 3, 111.361, 30.644, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420504, '点军区', 420500, 3, 111.268, 30.693, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420505, '猇亭区', 420500, 3, 111.435, 30.531, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420506, '夷陵区', 420500, 3, 111.326, 30.770, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420525, '远安县', 420500, 3, 111.641, 31.061, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420526, '兴山县', 420500, 3, 110.747, 31.348, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420527, '秭归县', 420500, 3, 110.978, 30.826, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420528, '长阳土家族自治县', 420500, 3, 111.207, 30.473, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420529, '五峰土家族自治县', 420500, 3, 110.675, 30.200, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420581, '宜都市', 420500, 3, 111.450, 30.378, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420582, '当阳市', 420500, 3, 111.788, 30.821, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420583, '枝江市', 420500, 3, 111.761, 30.426, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420602, '襄城区', 420600, 3, 112.134, 32.010, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420606, '樊城区', 420600, 3, 112.136, 32.045, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420607, '襄州区', 420600, 3, 112.212, 32.087, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420624, '南漳县', 420600, 3, 111.839, 31.775, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420625, '谷城县', 420600, 3, 111.653, 32.264, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420626, '保康县', 420600, 3, 111.261, 31.878, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420682, '老河口市', 420600, 3, 111.684, 32.359, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420683, '枣阳市', 420600, 3, 112.772, 32.129, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420684, '宜城市', 420600, 3, 112.258, 31.720, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420702, '梁子湖区', 420700, 3, 114.685, 30.100, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420703, '华容区', 420700, 3, 114.730, 30.534, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420704, '鄂城区', 420700, 3, 114.892, 30.401, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420802, '东宝区', 420800, 3, 112.201, 31.052, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420804, '掇刀区', 420800, 3, 112.208, 30.973, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420822, '沙洋县', 420800, 3, 112.589, 30.709, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420881, '钟祥市', 420800, 3, 112.588, 31.168, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420882, '京山市', 420800, 3, 113.120, 31.018, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420902, '孝南区', 420900, 3, 113.911, 30.917, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420921, '孝昌县', 420900, 3, 113.998, 31.258, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420922, '大悟县', 420900, 3, 114.127, 31.561, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420923, '云梦县', 420900, 3, 113.754, 31.021, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420981, '应城市', 420900, 3, 113.563, 30.940, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420982, '安陆市', 420900, 3, 113.689, 31.256, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (420984, '汉川市', 420900, 3, 113.839, 30.661, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421002, '沙市区', 421000, 3, 112.256, 30.311, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421003, '荆州区', 421000, 3, 112.190, 30.353, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421022, '公安县', 421000, 3, 112.230, 30.058, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421023, '监利县', 421000, 3, 112.897, 29.812, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421024, '江陵县', 421000, 3, 112.425, 30.042, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421071, '荆州经济技术开发区', 421000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421081, '石首市', 421000, 3, 112.425, 29.721, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421083, '洪湖市', 421000, 3, 113.476, 29.825, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421087, '松滋市', 421000, 3, 111.757, 30.175, 'https://github.com/modood/Administrative-divisions-of-China', '87', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421102, '黄州区', 421100, 3, 114.879, 30.434, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421121, '团风县', 421100, 3, 114.872, 30.644, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421122, '红安县', 421100, 3, 114.618, 31.288, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421123, '罗田县', 421100, 3, 115.399, 30.783, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421124, '英山县', 421100, 3, 115.681, 30.735, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421125, '浠水县', 421100, 3, 115.266, 30.452, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421126, '蕲春县', 421100, 3, 115.437, 30.226, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421127, '黄梅县', 421100, 3, 115.944, 30.070, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421171, '龙感湖管理区', 421100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421181, '麻城市', 421100, 3, 115.008, 31.173, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421182, '武穴市', 421100, 3, 115.561, 29.844, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421202, '咸安区', 421200, 3, 114.299, 29.853, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421221, '嘉鱼县', 421200, 3, 113.939, 29.971, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421222, '通城县', 421200, 3, 113.817, 29.245, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421223, '崇阳县', 421200, 3, 114.040, 29.556, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421224, '通山县', 421200, 3, 114.483, 29.605, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421281, '赤壁市', 421200, 3, 113.901, 29.725, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421303, '曾都区', 421300, 3, 113.371, 31.716, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421321, '随县', 421300, 3, 113.300, 31.854, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (421381, '广水市', 421300, 3, 113.826, 31.617, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (422801, '恩施市', 422800, 3, 109.480, 30.295, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (422802, '利川市', 422800, 3, 108.910, 30.211, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (422822, '建始县', 422800, 3, 109.718, 30.597, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (422823, '巴东县', 422800, 3, 110.341, 31.042, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (422825, '宣恩县', 422800, 3, 109.491, 29.987, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (422826, '咸丰县', 422800, 3, 109.140, 29.665, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (422827, '来凤县', 422800, 3, 109.408, 29.493, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (422828, '鹤峰县', 422800, 3, 110.034, 29.890, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (429004, '仙桃市', 429000, 3, 113.443, 30.328, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (429005, '潜江市', 429000, 3, 112.900, 30.402, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (429006, '天门市', 429000, 3, 113.167, 30.664, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (429021, '神农架林区', 429000, 3, 110.676, 31.745, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430102, '芙蓉区', 430100, 3, 113.033, 28.185, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430103, '天心区', 430100, 3, 112.990, 28.113, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430104, '岳麓区', 430100, 3, 112.931, 28.235, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430105, '开福区', 430100, 3, 112.984, 28.206, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430111, '雨花区', 430100, 3, 113.038, 28.138, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430112, '望城区', 430100, 3, 112.820, 28.347, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430121, '长沙县', 430100, 3, 113.081, 28.246, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430181, '浏阳市', 430100, 3, 113.643, 28.163, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430182, '宁乡市', 430100, 3, 112.552, 28.277, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430202, '荷塘区', 430200, 3, 113.173, 27.856, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430203, '芦淞区', 430200, 3, 113.153, 27.785, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430204, '石峰区', 430200, 3, 113.118, 27.875, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430211, '天元区', 430200, 3, 113.082, 27.827, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430212, '渌口区', 430200, 3, 113.140, 27.703, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430223, '攸县', 430200, 3, 113.396, 27.015, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430224, '茶陵县', 430200, 3, 113.539, 26.777, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430225, '炎陵县', 430200, 3, 113.773, 26.490, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430271, '云龙示范区', 430200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430281, '醴陵市', 430200, 3, 113.497, 27.646, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430302, '雨湖区', 430300, 3, 112.903, 27.855, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430304, '岳塘区', 430300, 3, 112.925, 27.809, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430321, '湘潭县', 430300, 3, 112.951, 27.779, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430371, '湖南湘潭高新技术产业园区', 430300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430372, '湘潭昭山示范区', 430300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430373, '湘潭九华示范区', 430300, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430381, '湘乡市', 430300, 3, 112.551, 27.718, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430382, '韶山市', 430300, 3, 112.527, 27.915, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430405, '珠晖区', 430400, 3, 112.620, 26.895, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430406, '雁峰区', 430400, 3, 112.572, 26.893, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430407, '石鼓区', 430400, 3, 112.598, 26.943, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430408, '蒸湘区', 430400, 3, 112.567, 26.911, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430412, '南岳区', 430400, 3, 112.739, 27.232, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430421, '衡阳县', 430400, 3, 112.371, 26.970, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430422, '衡南县', 430400, 3, 112.678, 26.738, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430423, '衡山县', 430400, 3, 112.868, 27.230, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430424, '衡东县', 430400, 3, 112.953, 27.081, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430426, '祁东县', 430400, 3, 112.090, 26.800, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430471, '衡阳综合保税区', 430400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430472, '湖南衡阳高新技术产业园区', 430400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430473, '湖南衡阳松木经济开发区', 430400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430481, '耒阳市', 430400, 3, 112.860, 26.422, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430482, '常宁市', 430400, 3, 112.400, 26.421, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430502, '双清区', 430500, 3, 111.496, 27.233, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430503, '大祥区', 430500, 3, 111.439, 27.221, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430511, '北塔区', 430500, 3, 111.452, 27.246, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430522, '新邵县', 430500, 3, 111.459, 27.321, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430523, '邵阳县', 430500, 3, 111.274, 26.991, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430524, '隆回县', 430500, 3, 111.032, 27.114, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430525, '洞口县', 430500, 3, 110.576, 27.060, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430527, '绥宁县', 430500, 3, 110.156, 26.582, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430528, '新宁县', 430500, 3, 110.857, 26.433, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430529, '城步苗族自治县', 430500, 3, 110.322, 26.391, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430581, '武冈市', 430500, 3, 110.632, 26.727, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430582, '邵东市', 430500, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430602, '岳阳楼区', 430600, 3, 113.130, 29.372, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430603, '云溪区', 430600, 3, 113.272, 29.473, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430611, '君山区', 430600, 3, 113.006, 29.461, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430621, '岳阳县', 430600, 3, 113.116, 29.144, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430623, '华容县', 430600, 3, 112.540, 29.531, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430624, '湘阴县', 430600, 3, 112.909, 28.689, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430626, '平江县', 430600, 3, 113.581, 28.702, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430671, '岳阳市屈原管理区', 430600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430681, '汨罗市', 430600, 3, 113.067, 28.807, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430682, '临湘市', 430600, 3, 113.450, 29.477, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430702, '武陵区', 430700, 3, 111.683, 29.055, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430703, '鼎城区', 430700, 3, 111.681, 29.019, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430721, '安乡县', 430700, 3, 112.171, 29.411, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430722, '汉寿县', 430700, 3, 111.971, 28.906, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430723, '澧县', 430700, 3, 111.759, 29.633, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430724, '临澧县', 430700, 3, 111.648, 29.441, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430725, '桃源县', 430700, 3, 111.489, 28.903, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430726, '石门县', 430700, 3, 111.380, 29.584, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430771, '常德市西洞庭管理区', 430700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430781, '津市市', 430700, 3, 111.877, 29.605, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430802, '永定区', 430800, 3, 110.537, 29.120, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430811, '武陵源区', 430800, 3, 110.550, 29.346, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430821, '慈利县', 430800, 3, 111.140, 29.430, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430822, '桑植县', 430800, 3, 110.205, 29.414, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430902, '资阳区', 430900, 3, 112.324, 28.591, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430903, '赫山区', 430900, 3, 112.374, 28.579, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430921, '南县', 430900, 3, 112.396, 29.361, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430922, '桃江县', 430900, 3, 112.156, 28.518, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430923, '安化县', 430900, 3, 111.213, 28.374, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430971, '益阳市大通湖管理区', 430900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430972, '湖南益阳高新技术产业园区', 430900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (430981, '沅江市', 430900, 3, 112.355, 28.846, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431002, '北湖区', 431000, 3, 113.011, 25.784, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431003, '苏仙区', 431000, 3, 113.042, 25.800, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431021, '桂阳县', 431000, 3, 112.734, 25.754, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431022, '宜章县', 431000, 3, 112.949, 25.400, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431023, '永兴县', 431000, 3, 113.117, 26.127, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431024, '嘉禾县', 431000, 3, 112.369, 25.588, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431025, '临武县', 431000, 3, 112.563, 25.276, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431026, '汝城县', 431000, 3, 113.685, 25.533, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431027, '桂东县', 431000, 3, 113.945, 26.078, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431028, '安仁县', 431000, 3, 113.269, 26.709, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431081, '资兴市', 431000, 3, 113.236, 25.976, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431102, '零陵区', 431100, 3, 111.631, 26.222, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431103, '冷水滩区', 431100, 3, 111.592, 26.461, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431121, '祁阳县', 431100, 3, 111.841, 26.580, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431122, '东安县', 431100, 3, 111.314, 26.394, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431123, '双牌县', 431100, 3, 111.660, 25.962, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431124, '道县', 431100, 3, 111.601, 25.526, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431125, '江永县', 431100, 3, 111.344, 25.274, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431126, '宁远县', 431100, 3, 111.946, 25.571, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431127, '蓝山县', 431100, 3, 112.197, 25.370, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431128, '新田县', 431100, 3, 112.203, 25.904, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431129, '江华瑶族自治县', 431100, 3, 111.579, 25.186, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431171, '永州经济技术开发区', 431100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431172, '永州市金洞管理区', 431100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431173, '永州市回龙圩管理区', 431100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431202, '鹤城区', 431200, 3, 110.040, 27.579, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431221, '中方县', 431200, 3, 109.945, 27.440, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431222, '沅陵县', 431200, 3, 110.394, 28.453, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431223, '辰溪县', 431200, 3, 110.184, 28.006, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431224, '溆浦县', 431200, 3, 110.595, 27.908, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431225, '会同县', 431200, 3, 109.736, 26.887, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431226, '麻阳苗族自治县', 431200, 3, 109.803, 27.866, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431227, '新晃侗族自治县', 431200, 3, 109.175, 27.353, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431228, '芷江侗族自治县', 431200, 3, 109.685, 27.443, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431229, '靖州苗族侗族自治县', 431200, 3, 109.696, 26.575, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431230, '通道侗族自治县', 431200, 3, 109.784, 26.158, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431271, '怀化市洪江管理区', 431200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431281, '洪江市', 431200, 3, 109.837, 27.209, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431302, '娄星区', 431300, 3, 112.002, 27.730, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431321, '双峰县', 431300, 3, 112.175, 27.457, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431322, '新化县', 431300, 3, 111.327, 27.727, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431381, '冷水江市', 431300, 3, 111.436, 27.686, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (431382, '涟源市', 431300, 3, 111.664, 27.693, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (433101, '吉首市', 433100, 3, 109.927, 28.297, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (433122, '泸溪县', 433100, 3, 110.220, 28.217, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (433123, '凤凰县', 433100, 3, 109.581, 27.958, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (433124, '花垣县', 433100, 3, 109.482, 28.572, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (433125, '保靖县', 433100, 3, 109.661, 28.700, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (433126, '古丈县', 433100, 3, 109.951, 28.617, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (433127, '永顺县', 433100, 3, 109.851, 29.001, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (433130, '龙山县', 433100, 3, 109.444, 29.458, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440103, '荔湾区', 440100, 3, 113.244, 23.126, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440104, '越秀区', 440100, 3, 113.267, 23.129, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440105, '海珠区', 440100, 3, 113.317, 23.084, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440106, '天河区', 440100, 3, 113.361, 23.125, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440111, '白云区', 440100, 3, 113.273, 23.157, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440112, '黄埔区', 440100, 3, 113.460, 23.106, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440113, '番禺区', 440100, 3, 113.384, 22.937, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440114, '花都区', 440100, 3, 113.220, 23.404, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440115, '南沙区', 440100, 3, 113.525, 22.802, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440117, '从化区', 440100, 3, 113.587, 23.549, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440118, '增城区', 440100, 3, 113.811, 23.261, 'https://github.com/modood/Administrative-divisions-of-China', '18', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440203, '武江区', 440200, 3, 113.588, 24.793, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440204, '浈江区', 440200, 3, 113.611, 24.804, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440205, '曲江区', 440200, 3, 113.605, 24.683, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440222, '始兴县', 440200, 3, 114.062, 24.953, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440224, '仁化县', 440200, 3, 113.749, 25.086, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440229, '翁源县', 440200, 3, 114.130, 24.350, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440232, '乳源瑶族自治县', 440200, 3, 113.276, 24.776, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440233, '新丰县', 440200, 3, 114.207, 24.060, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440281, '乐昌市', 440200, 3, 113.348, 25.130, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440282, '南雄市', 440200, 3, 114.312, 25.118, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440303, '罗湖区', 440300, 3, 114.132, 22.548, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440304, '福田区', 440300, 3, 114.055, 22.522, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440305, '南山区', 440300, 3, 113.924, 22.528, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440306, '宝安区', 440300, 3, 113.884, 22.555, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440307, '龙岗区', 440300, 3, 114.247, 22.721, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440308, '盐田区', 440300, 3, 114.237, 22.556, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440309, '龙华区', 440300, 3, 114.045, 22.697, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440310, '坪山区', 440300, 3, 114.362, 22.688, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440311, '光明区', 440300, 3, 114.058, 22.543, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440402, '香洲区', 440400, 3, 113.544, 22.266, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440403, '斗门区', 440400, 3, 113.296, 22.209, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440404, '金湾区', 440400, 3, 113.363, 22.147, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440507, '龙湖区', 440500, 3, 116.717, 23.371, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:57', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440511, '金平区', 440500, 3, 116.703, 23.366, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440512, '濠江区', 440500, 3, 116.727, 23.286, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440513, '潮阳区', 440500, 3, 116.602, 23.265, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440514, '潮南区', 440500, 3, 116.433, 23.250, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440515, '澄海区', 440500, 3, 116.756, 23.466, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440523, '南澳县', 440500, 3, 117.023, 23.422, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440604, '禅城区', 440600, 3, 113.122, 23.010, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440605, '南海区', 440600, 3, 113.148, 23.025, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440606, '顺德区', 440600, 3, 113.293, 22.805, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440607, '三水区', 440600, 3, 112.897, 23.156, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440608, '高明区', 440600, 3, 112.893, 22.900, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440703, '蓬江区', 440700, 3, 113.079, 22.595, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440704, '江海区', 440700, 3, 113.112, 22.560, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440705, '新会区', 440700, 3, 113.034, 22.458, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440781, '台山市', 440700, 3, 112.796, 22.252, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440783, '开平市', 440700, 3, 112.699, 22.376, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440784, '鹤山市', 440700, 3, 112.964, 22.765, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440785, '恩平市', 440700, 3, 112.305, 22.183, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440802, '赤坎区', 440800, 3, 110.366, 21.266, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440803, '霞山区', 440800, 3, 110.398, 21.192, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440804, '坡头区', 440800, 3, 110.455, 21.245, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440811, '麻章区', 440800, 3, 110.334, 21.263, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440823, '遂溪县', 440800, 3, 110.250, 21.377, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440825, '徐闻县', 440800, 3, 110.177, 20.325, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440881, '廉江市', 440800, 3, 110.286, 21.610, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440882, '雷州市', 440800, 3, 110.097, 20.914, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440883, '吴川市', 440800, 3, 110.778, 21.442, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440902, '茂南区', 440900, 3, 110.918, 21.641, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440904, '电白区', 440900, 3, 111.014, 21.514, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440981, '高州市', 440900, 3, 110.853, 21.918, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440982, '化州市', 440900, 3, 110.640, 21.664, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (440983, '信宜市', 440900, 3, 110.947, 22.354, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441202, '端州区', 441200, 3, 112.485, 23.052, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441203, '鼎湖区', 441200, 3, 112.568, 23.158, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441204, '高要区', 441200, 3, 112.458, 23.026, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441223, '广宁县', 441200, 3, 112.441, 23.635, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441224, '怀集县', 441200, 3, 112.185, 23.912, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441225, '封开县', 441200, 3, 111.512, 23.424, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441226, '德庆县', 441200, 3, 111.786, 23.144, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441284, '四会市', 441200, 3, 112.734, 23.327, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441302, '惠城区', 441300, 3, 114.382, 23.084, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441303, '惠阳区', 441300, 3, 114.457, 22.789, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441322, '博罗县', 441300, 3, 114.289, 23.173, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441323, '惠东县', 441300, 3, 114.720, 22.985, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441324, '龙门县', 441300, 3, 114.255, 23.728, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441402, '梅江区', 441400, 3, 116.117, 24.310, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441403, '梅县区', 441400, 3, 116.082, 24.265, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441422, '大埔县', 441400, 3, 116.695, 24.348, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441423, '丰顺县', 441400, 3, 116.182, 23.740, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441424, '五华县', 441400, 3, 115.777, 23.931, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441426, '平远县', 441400, 3, 115.892, 24.567, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441427, '蕉岭县', 441400, 3, 116.171, 24.659, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441481, '兴宁市', 441400, 3, 115.724, 24.126, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441502, '城区', 441500, 3, 115.365, 22.779, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441521, '海丰县', 441500, 3, 115.323, 22.967, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441523, '陆河县', 441500, 3, 115.660, 23.302, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441581, '陆丰市', 441500, 3, 115.652, 22.918, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441602, '源城区', 441600, 3, 114.703, 23.734, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441621, '紫金县', 441600, 3, 115.184, 23.635, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441622, '龙川县', 441600, 3, 115.260, 24.100, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441623, '连平县', 441600, 3, 114.489, 24.370, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441624, '和平县', 441600, 3, 114.939, 24.442, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441625, '东源县', 441600, 3, 114.746, 23.788, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441702, '江城区', 441700, 3, 111.955, 21.862, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441704, '阳东区', 441700, 3, 112.006, 21.868, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441721, '阳西县', 441700, 3, 111.618, 21.752, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441781, '阳春市', 441700, 3, 111.792, 22.170, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441802, '清城区', 441800, 3, 113.063, 23.698, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441803, '清新区', 441800, 3, 113.018, 23.735, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441821, '佛冈县', 441800, 3, 113.532, 23.879, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441823, '阳山县', 441800, 3, 112.641, 24.465, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441825, '连山壮族瑶族自治县', 441800, 3, 112.094, 24.570, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441826, '连南瑶族自治县', 441800, 3, 112.287, 24.726, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441881, '英德市', 441800, 3, 113.402, 24.205, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441882, '连州市', 441800, 3, 112.377, 24.781, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445102, '湘桥区', 445100, 3, 116.629, 23.675, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445103, '潮安区', 445100, 3, 116.678, 23.463, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445122, '饶平县', 445100, 3, 117.004, 23.664, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445202, '榕城区', 445200, 3, 116.367, 23.525, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445203, '揭东区', 445200, 3, 116.412, 23.566, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445222, '揭西县', 445200, 3, 115.842, 23.431, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445224, '惠来县', 445200, 3, 116.295, 23.033, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445281, '普宁市', 445200, 3, 116.166, 23.298, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445302, '云城区', 445300, 3, 112.044, 22.928, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445303, '云安区', 445300, 3, 112.003, 23.071, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445321, '新兴县', 445300, 3, 112.225, 22.696, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445322, '郁南县', 445300, 3, 111.535, 23.235, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (445381, '罗定市', 445300, 3, 111.570, 22.769, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450102, '兴宁区', 450100, 3, 108.369, 22.854, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450103, '青秀区', 450100, 3, 108.494, 22.786, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450105, '江南区', 450100, 3, 108.273, 22.782, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450107, '西乡塘区', 450100, 3, 108.307, 22.833, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450108, '良庆区', 450100, 3, 108.322, 22.759, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450109, '邕宁区', 450100, 3, 108.487, 22.758, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450110, '武鸣区', 450100, 3, 108.275, 23.159, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450123, '隆安县', 450100, 3, 107.696, 23.166, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450124, '马山县', 450100, 3, 108.177, 23.708, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450125, '上林县', 450100, 3, 108.605, 23.432, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450126, '宾阳县', 450100, 3, 108.810, 23.218, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450127, '横县', 450100, 3, 109.261, 22.680, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450202, '城中区', 450200, 3, 109.411, 24.316, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450203, '鱼峰区', 450200, 3, 109.452, 24.319, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450204, '柳南区', 450200, 3, 109.386, 24.336, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450205, '柳北区', 450200, 3, 109.402, 24.363, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450206, '柳江区', 450200, 3, 109.326, 24.255, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450222, '柳城县', 450200, 3, 109.245, 24.652, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450223, '鹿寨县', 450200, 3, 109.751, 24.473, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450224, '融安县', 450200, 3, 109.398, 25.225, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450225, '融水苗族自治县', 450200, 3, 109.256, 25.066, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450226, '三江侗族自治县', 450200, 3, 109.608, 25.783, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450302, '秀峰区', 450300, 3, 110.264, 25.274, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450303, '叠彩区', 450300, 3, 110.302, 25.314, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450304, '象山区', 450300, 3, 110.281, 25.262, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450305, '七星区', 450300, 3, 110.318, 25.253, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450311, '雁山区', 450300, 3, 110.287, 25.102, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450312, '临桂区', 450300, 3, 110.212, 25.239, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450321, '阳朔县', 450300, 3, 110.497, 24.778, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450323, '灵川县', 450300, 3, 110.326, 25.410, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450324, '全州县', 450300, 3, 111.073, 25.929, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450325, '兴安县', 450300, 3, 110.672, 25.612, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450326, '永福县', 450300, 3, 109.983, 24.980, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450327, '灌阳县', 450300, 3, 111.161, 25.489, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450328, '龙胜各族自治县', 450300, 3, 110.011, 25.798, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450329, '资源县', 450300, 3, 110.653, 26.042, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450330, '平乐县', 450300, 3, 110.643, 24.633, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450332, '恭城瑶族自治县', 450300, 3, 110.828, 24.832, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450381, '荔浦市', 450300, 3, 110.395, 24.488, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450403, '万秀区', 450400, 3, 111.321, 23.473, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450405, '长洲区', 450400, 3, 111.275, 23.486, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450406, '龙圩区', 450400, 3, 111.246, 23.410, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450421, '苍梧县', 450400, 3, 111.544, 23.845, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450422, '藤县', 450400, 3, 110.915, 23.375, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450423, '蒙山县', 450400, 3, 110.525, 24.194, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450481, '岑溪市', 450400, 3, 110.995, 22.918, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450502, '海城区', 450500, 3, 109.117, 21.475, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450503, '银海区', 450500, 3, 109.140, 21.449, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450512, '铁山港区', 450500, 3, 109.422, 21.529, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450521, '合浦县', 450500, 3, 109.207, 21.661, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450602, '港口区', 450600, 3, 108.380, 21.643, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450603, '防城区', 450600, 3, 108.353, 21.769, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450621, '上思县', 450600, 3, 107.984, 22.154, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450681, '东兴市', 450600, 3, 107.972, 21.548, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450702, '钦南区', 450700, 3, 108.657, 21.939, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450703, '钦北区', 450700, 3, 108.449, 22.133, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450721, '灵山县', 450700, 3, 109.291, 22.417, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450722, '浦北县', 450700, 3, 109.557, 22.272, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450802, '港北区', 450800, 3, 109.572, 23.112, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450803, '港南区', 450800, 3, 109.600, 23.076, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450804, '覃塘区', 450800, 3, 109.453, 23.127, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450821, '平南县', 450800, 3, 110.392, 23.539, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450881, '桂平市', 450800, 3, 110.079, 23.394, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450902, '玉州区', 450900, 3, 110.151, 22.628, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450903, '福绵区', 450900, 3, 110.059, 22.586, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450921, '容县', 450900, 3, 110.558, 22.858, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450922, '陆川县', 450900, 3, 110.264, 22.321, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450923, '博白县', 450900, 3, 109.976, 22.273, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450924, '兴业县', 450900, 3, 109.877, 22.821, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (450981, '北流市', 450900, 3, 110.354, 22.708, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451002, '右江区', 451000, 3, 106.619, 23.901, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451003, '田阳区', 451000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451022, '田东县', 451000, 3, 107.126, 23.597, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451024, '德保县', 451000, 3, 106.615, 23.323, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451026, '那坡县', 451000, 3, 105.833, 23.387, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451027, '凌云县', 451000, 3, 106.561, 24.348, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451028, '乐业县', 451000, 3, 106.557, 24.777, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451029, '田林县', 451000, 3, 106.229, 24.294, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451030, '西林县', 451000, 3, 105.094, 24.490, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451031, '隆林各族自治县', 451000, 3, 105.344, 24.771, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451081, '靖西市', 451000, 3, 106.418, 23.135, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451082, '平果市', 451000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451102, '八步区', 451100, 3, 111.552, 24.412, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451103, '平桂区', 451100, 3, 111.567, 24.404, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451121, '昭平县', 451100, 3, 110.811, 24.169, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451122, '钟山县', 451100, 3, 111.303, 24.526, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451123, '富川瑶族自治县', 451100, 3, 111.277, 24.814, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451202, '金城江区', 451200, 3, 108.037, 24.690, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451203, '宜州区', 451200, 3, 108.085, 24.693, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451221, '南丹县', 451200, 3, 107.541, 24.974, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451222, '天峨县', 451200, 3, 107.174, 24.999, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451223, '凤山县', 451200, 3, 107.042, 24.547, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451224, '东兰县', 451200, 3, 107.374, 24.511, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451225, '罗城仫佬族自治县', 451200, 3, 108.905, 24.777, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451226, '环江毛南族自治县', 451200, 3, 108.258, 24.826, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451227, '巴马瑶族自治县', 451200, 3, 107.259, 24.142, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451228, '都安瑶族自治县', 451200, 3, 108.105, 23.933, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451229, '大化瑶族自治县', 451200, 3, 107.998, 23.736, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451302, '兴宾区', 451300, 3, 109.233, 23.736, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451321, '忻城县', 451300, 3, 108.666, 24.066, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451322, '象州县', 451300, 3, 109.684, 23.959, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451323, '武宣县', 451300, 3, 109.663, 23.594, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451324, '金秀瑶族自治县', 451300, 3, 110.189, 24.130, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451381, '合山市', 451300, 3, 108.886, 23.807, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451402, '江州区', 451400, 3, 107.353, 22.405, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451421, '扶绥县', 451400, 3, 107.904, 22.635, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451422, '宁明县', 451400, 3, 107.076, 22.140, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451423, '龙州县', 451400, 3, 106.854, 22.343, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451424, '大新县', 451400, 3, 107.201, 22.829, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451425, '天等县', 451400, 3, 107.143, 23.081, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (451481, '凭祥市', 451400, 3, 106.766, 22.094, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460105, '秀英区', 460100, 3, 110.294, 20.008, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460106, '龙华区', 460100, 3, 110.328, 20.031, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460107, '琼山区', 460100, 3, 110.354, 20.003, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460108, '美兰区', 460100, 3, 110.366, 20.029, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460202, '海棠区', 460200, 3, 109.761, 18.408, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460203, '吉阳区', 460200, 3, 109.578, 18.281, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460204, '天涯区', 460200, 3, 109.506, 18.247, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460205, '崖州区', 460200, 3, 109.174, 18.352, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460321, '西沙群岛', 460300, 3, 112.027, 16.330, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460322, '南沙群岛', 460300, 3, 112.339, 16.832, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460323, '中沙群岛的岛礁及其海域', 460300, 3, 112.339, 16.832, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469001, '五指山市', 469000, 3, 109.517, 18.775, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469002, '琼海市', 469000, 3, 110.475, 19.259, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469005, '文昌市', 469000, 3, 110.798, 19.543, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469006, '万宁市', 469000, 3, 110.391, 18.795, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469007, '东方市', 469000, 3, 108.652, 19.095, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469021, '定安县', 469000, 3, 110.359, 19.681, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469022, '屯昌县', 469000, 3, 110.103, 19.352, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469023, '澄迈县', 469000, 3, 110.007, 19.739, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469024, '临高县', 469000, 3, 109.691, 19.912, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469025, '白沙黎族自治县', 469000, 3, 109.451, 19.225, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469026, '昌江黎族自治县', 469000, 3, 109.056, 19.298, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469027, '乐东黎族自治县', 469000, 3, 109.173, 18.750, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469028, '陵水黎族自治县', 469000, 3, 110.038, 18.506, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469029, '保亭黎族苗族自治县', 469000, 3, 109.702, 18.638, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (469030, '琼中黎族苗族自治县', 469000, 3, 109.838, 19.033, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500101, '万州区', 500100, 3, 108.409, 30.808, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500102, '涪陵区', 500100, 3, 107.389, 29.703, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500103, '渝中区', 500100, 3, 106.569, 29.553, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500104, '大渡口区', 500100, 3, 106.482, 29.485, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500105, '江北区', 500100, 3, 106.574, 29.607, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500106, '沙坪坝区', 500100, 3, 106.457, 29.541, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500107, '九龙坡区', 500100, 3, 106.532, 29.514, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500108, '南岸区', 500100, 3, 106.644, 29.500, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500109, '北碚区', 500100, 3, 106.445, 29.823, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500110, '綦江区', 500100, 3, 106.651, 29.028, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500111, '大足区', 500100, 3, 105.722, 29.707, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500112, '渝北区', 500100, 3, 106.631, 29.718, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500113, '巴南区', 500100, 3, 106.534, 29.376, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500114, '黔江区', 500100, 3, 108.771, 29.534, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500115, '长寿区', 500100, 3, 107.081, 29.858, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500116, '江津区', 500100, 3, 106.259, 29.290, 'https://github.com/modood/Administrative-divisions-of-China', '16', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500117, '合川区', 500100, 3, 106.276, 29.972, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500118, '永川区', 500100, 3, 105.927, 29.356, 'https://github.com/modood/Administrative-divisions-of-China', '18', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500119, '南川区', 500100, 3, 107.099, 29.158, 'https://github.com/modood/Administrative-divisions-of-China', '19', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500120, '璧山区', 500100, 3, 106.227, 29.592, 'https://github.com/modood/Administrative-divisions-of-China', '20', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500151, '铜梁区', 500100, 3, 106.056, 29.845, 'https://github.com/modood/Administrative-divisions-of-China', '51', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500152, '潼南区', 500100, 3, 105.841, 30.191, 'https://github.com/modood/Administrative-divisions-of-China', '52', b'0', 0, NULL, '2021-07-29 08:28:58', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500153, '荣昌区', 500100, 3, 105.595, 29.405, 'https://github.com/modood/Administrative-divisions-of-China', '53', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500154, '开州区', 500100, 3, 108.393, 31.161, 'https://github.com/modood/Administrative-divisions-of-China', '54', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500155, '梁平区', 500100, 3, 107.770, 30.654, 'https://github.com/modood/Administrative-divisions-of-China', '55', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500156, '武隆区', 500100, 3, 107.760, 29.326, 'https://github.com/modood/Administrative-divisions-of-China', '56', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500229, '城口县', 500200, 3, 108.664, 31.948, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500230, '丰都县', 500200, 3, 107.731, 29.864, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500231, '垫江县', 500200, 3, 107.333, 30.328, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500233, '忠县', 500200, 3, 108.039, 30.300, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500235, '云阳县', 500200, 3, 108.697, 30.931, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500236, '奉节县', 500200, 3, 109.464, 31.018, 'https://github.com/modood/Administrative-divisions-of-China', '36', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500237, '巫山县', 500200, 3, 109.879, 31.075, 'https://github.com/modood/Administrative-divisions-of-China', '37', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500238, '巫溪县', 500200, 3, 109.570, 31.399, 'https://github.com/modood/Administrative-divisions-of-China', '38', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500240, '石柱土家族自治县', 500200, 3, 108.114, 29.999, 'https://github.com/modood/Administrative-divisions-of-China', '40', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500241, '秀山土家族苗族自治县', 500200, 3, 109.007, 28.448, 'https://github.com/modood/Administrative-divisions-of-China', '41', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500242, '酉阳土家族苗族自治县', 500200, 3, 108.768, 28.841, 'https://github.com/modood/Administrative-divisions-of-China', '42', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (500243, '彭水苗族土家族自治县', 500200, 3, 108.166, 29.294, 'https://github.com/modood/Administrative-divisions-of-China', '43', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510104, '锦江区', 510100, 3, 104.081, 30.658, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510105, '青羊区', 510100, 3, 104.062, 30.674, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510106, '金牛区', 510100, 3, 104.117, 30.763, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510107, '武侯区', 510100, 3, 104.043, 30.642, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510108, '成华区', 510100, 3, 104.101, 30.660, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510112, '龙泉驿区', 510100, 3, 104.275, 30.557, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510113, '青白江区', 510100, 3, 104.251, 30.879, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510114, '新都区', 510100, 3, 104.159, 30.823, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510115, '温江区', 510100, 3, 103.837, 30.690, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510116, '双流区', 510100, 3, 103.952, 30.333, 'https://github.com/modood/Administrative-divisions-of-China', '16', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510117, '郫都区', 510100, 3, 103.901, 30.796, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510118, '新津区', 510100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '18', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510121, '金堂县', 510100, 3, 104.412, 30.862, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510129, '大邑县', 510100, 3, 103.512, 30.572, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510131, '蒲江县', 510100, 3, 103.506, 30.197, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510181, '都江堰市', 510100, 3, 103.650, 30.976, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510182, '彭州市', 510100, 3, 103.958, 30.990, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510183, '邛崃市', 510100, 3, 103.464, 30.410, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510184, '崇州市', 510100, 3, 103.673, 30.630, 'https://github.com/modood/Administrative-divisions-of-China', '84', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510185, '简阳市', 510100, 3, 104.547, 30.411, 'https://github.com/modood/Administrative-divisions-of-China', '85', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510302, '自流井区', 510300, 3, 104.777, 29.337, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510303, '贡井区', 510300, 3, 104.715, 29.346, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510304, '大安区', 510300, 3, 104.774, 29.364, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510311, '沿滩区', 510300, 3, 104.874, 29.273, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510321, '荣县', 510300, 3, 104.417, 29.445, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510322, '富顺县', 510300, 3, 104.975, 29.181, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510402, '东区', 510400, 3, 101.719, 26.582, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510403, '西区', 510400, 3, 101.719, 26.582, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510411, '仁和区', 510400, 3, 101.739, 26.498, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510421, '米易县', 510400, 3, 102.110, 26.891, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510422, '盐边县', 510400, 3, 101.855, 26.683, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510502, '江阳区', 510500, 3, 105.435, 28.879, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510503, '纳溪区', 510500, 3, 105.371, 28.773, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510504, '龙马潭区', 510500, 3, 105.438, 28.913, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510521, '泸县', 510500, 3, 105.382, 29.152, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510522, '合江县', 510500, 3, 105.831, 28.811, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510524, '叙永县', 510500, 3, 105.445, 28.156, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510525, '古蔺县', 510500, 3, 105.813, 28.039, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510603, '旌阳区', 510600, 3, 104.417, 31.142, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510604, '罗江区', 510600, 3, 104.510, 31.317, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510623, '中江县', 510600, 3, 104.679, 31.033, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510681, '广汉市', 510600, 3, 104.282, 30.976, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510682, '什邡市', 510600, 3, 104.168, 31.127, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510683, '绵竹市', 510600, 3, 104.221, 31.338, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510703, '涪城区', 510700, 3, 104.750, 31.471, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510704, '游仙区', 510700, 3, 104.766, 31.474, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510705, '安州区', 510700, 3, 104.567, 31.535, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510722, '三台县', 510700, 3, 105.095, 31.096, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510723, '盐亭县', 510700, 3, 105.389, 31.208, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510725, '梓潼县', 510700, 3, 105.171, 31.643, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510726, '北川羌族自治县', 510700, 3, 104.468, 31.617, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510727, '平武县', 510700, 3, 104.556, 32.410, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510781, '江油市', 510700, 3, 104.746, 31.778, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510802, '利州区', 510800, 3, 105.845, 32.434, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510811, '昭化区', 510800, 3, 105.964, 32.323, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510812, '朝天区', 510800, 3, 105.890, 32.644, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510821, '旺苍县', 510800, 3, 106.290, 32.229, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510822, '青川县', 510800, 3, 105.239, 32.575, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510823, '剑阁县', 510800, 3, 105.525, 32.288, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510824, '苍溪县', 510800, 3, 105.935, 31.732, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510903, '船山区', 510900, 3, 105.568, 30.525, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510904, '安居区', 510900, 3, 105.456, 30.355, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510921, '蓬溪县', 510900, 3, 105.708, 30.758, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510923, '大英县', 510900, 3, 105.237, 30.594, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (510981, '射洪市', 510900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511002, '市中区', 511000, 3, 105.068, 29.587, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511011, '东兴区', 511000, 3, 105.075, 29.593, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511024, '威远县', 511000, 3, 104.669, 29.527, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511025, '资中县', 511000, 3, 104.852, 29.764, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511071, '内江经济开发区', 511000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511083, '隆昌市', 511000, 3, 105.288, 29.339, 'https://github.com/modood/Administrative-divisions-of-China', '83', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511102, '市中区', 511100, 3, 103.761, 29.555, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511111, '沙湾区', 511100, 3, 103.550, 29.413, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511112, '五通桥区', 511100, 3, 103.818, 29.407, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511113, '金口河区', 511100, 3, 103.079, 29.244, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511123, '犍为县', 511100, 3, 103.949, 29.208, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511124, '井研县', 511100, 3, 104.070, 29.651, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511126, '夹江县', 511100, 3, 103.572, 29.738, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511129, '沐川县', 511100, 3, 103.902, 28.957, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511132, '峨边彝族自治县', 511100, 3, 103.262, 29.230, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511133, '马边彝族自治县', 511100, 3, 103.546, 28.836, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511181, '峨眉山市', 511100, 3, 103.485, 29.601, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511302, '顺庆区', 511300, 3, 106.092, 30.796, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511303, '高坪区', 511300, 3, 106.119, 30.782, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511304, '嘉陵区', 511300, 3, 106.072, 30.759, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511321, '南部县', 511300, 3, 106.037, 31.347, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511322, '营山县', 511300, 3, 106.565, 31.076, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511323, '蓬安县', 511300, 3, 106.412, 31.029, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511324, '仪陇县', 511300, 3, 106.303, 31.272, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511325, '西充县', 511300, 3, 105.901, 30.996, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511381, '阆中市', 511300, 3, 106.005, 31.558, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511402, '东坡区', 511400, 3, 103.832, 30.042, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511403, '彭山区', 511400, 3, 103.873, 30.193, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511421, '仁寿县', 511400, 3, 104.134, 29.996, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511423, '洪雅县', 511400, 3, 103.373, 29.905, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511424, '丹棱县', 511400, 3, 103.513, 30.014, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511425, '青神县', 511400, 3, 103.847, 29.831, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511502, '翠屏区', 511500, 3, 104.645, 28.759, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511503, '南溪区', 511500, 3, 104.643, 28.752, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511504, '叙州区', 511500, 3, 104.533, 28.690, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511523, '江安县', 511500, 3, 104.643, 28.752, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511524, '长宁县', 511500, 3, 104.914, 28.577, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511525, '高县', 511500, 3, 104.518, 28.435, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511526, '珙县', 511500, 3, 104.713, 28.444, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511527, '筠连县', 511500, 3, 104.643, 28.752, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511528, '兴文县', 511500, 3, 105.237, 28.303, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511529, '屏山县', 511500, 3, 104.156, 28.708, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511602, '广安区', 511600, 3, 106.642, 30.474, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511603, '前锋区', 511600, 3, 106.893, 30.496, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511621, '岳池县', 511600, 3, 106.440, 30.538, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511622, '武胜县', 511600, 3, 106.296, 30.349, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511623, '邻水县', 511600, 3, 106.930, 30.335, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511681, '华蓥市', 511600, 3, 106.783, 30.390, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511702, '通川区', 511700, 3, 107.505, 31.215, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511703, '达川区', 511700, 3, 107.512, 31.196, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511722, '宣汉县', 511700, 3, 107.727, 31.354, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511723, '开江县', 511700, 3, 107.869, 31.083, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511724, '大竹县', 511700, 3, 107.205, 30.736, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511725, '渠县', 511700, 3, 106.973, 30.837, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511771, '达州经济开发区', 511700, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511781, '万源市', 511700, 3, 108.035, 32.082, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511802, '雨城区', 511800, 3, 103.033, 30.005, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511803, '名山区', 511800, 3, 103.109, 30.070, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511822, '荥经县', 511800, 3, 102.847, 29.793, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511823, '汉源县', 511800, 3, 102.645, 29.347, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511824, '石棉县', 511800, 3, 102.359, 29.228, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511825, '天全县', 511800, 3, 102.758, 30.067, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511826, '芦山县', 511800, 3, 102.928, 30.144, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511827, '宝兴县', 511800, 3, 102.815, 30.368, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511902, '巴州区', 511900, 3, 106.769, 31.851, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511903, '恩阳区', 511900, 3, 106.655, 31.787, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511921, '通江县', 511900, 3, 107.245, 31.912, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511922, '南江县', 511900, 3, 106.829, 32.347, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511923, '平昌县', 511900, 3, 107.104, 31.561, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (511971, '巴中经济开发区', 511900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (512002, '雁江区', 512000, 3, 104.677, 30.108, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (512021, '安岳县', 512000, 3, 105.336, 30.097, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (512022, '乐至县', 512000, 3, 105.020, 30.275, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513201, '马尔康市', 513200, 3, 102.207, 31.906, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513221, '汶川县', 513200, 3, 103.590, 31.477, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513222, '理县', 513200, 3, 103.167, 31.436, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513223, '茂县', 513200, 3, 103.854, 31.681, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513224, '松潘县', 513200, 3, 103.605, 32.655, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513225, '九寨沟县', 513200, 3, 104.244, 33.252, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513226, '金川县', 513200, 3, 102.064, 31.476, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513227, '小金县', 513200, 3, 102.364, 30.999, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513228, '黑水县', 513200, 3, 102.990, 32.062, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513230, '壤塘县', 513200, 3, 100.979, 32.266, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513231, '阿坝县', 513200, 3, 101.707, 32.902, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513232, '若尔盖县', 513200, 3, 102.962, 33.576, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513233, '红原县', 513200, 3, 102.544, 32.791, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513301, '康定市', 513300, 3, 101.957, 29.998, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513322, '泸定县', 513300, 3, 102.235, 29.914, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513323, '丹巴县', 513300, 3, 101.890, 30.879, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513324, '九龙县', 513300, 3, 101.507, 29.000, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513325, '雅江县', 513300, 3, 101.014, 30.032, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513326, '道孚县', 513300, 3, 101.125, 30.980, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513327, '炉霍县', 513300, 3, 100.676, 31.392, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513328, '甘孜县', 513300, 3, 99.993, 31.623, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513329, '新龙县', 513300, 3, 100.311, 30.939, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513330, '德格县', 513300, 3, 98.581, 31.806, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513331, '白玉县', 513300, 3, 98.824, 31.210, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513332, '石渠县', 513300, 3, 98.103, 32.979, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513333, '色达县', 513300, 3, 100.333, 32.268, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513334, '理塘县', 513300, 3, 100.270, 29.996, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513335, '巴塘县', 513300, 3, 99.111, 30.005, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513336, '乡城县', 513300, 3, 99.798, 28.931, 'https://github.com/modood/Administrative-divisions-of-China', '36', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513337, '稻城县', 513300, 3, 100.298, 29.037, 'https://github.com/modood/Administrative-divisions-of-China', '37', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513338, '得荣县', 513300, 3, 99.286, 28.713, 'https://github.com/modood/Administrative-divisions-of-China', '38', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513401, '西昌市', 513400, 3, 102.264, 27.895, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513422, '木里藏族自治县', 513400, 3, 101.280, 27.929, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513423, '盐源县', 513400, 3, 101.509, 27.423, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513424, '德昌县', 513400, 3, 102.176, 27.403, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513425, '会理县', 513400, 3, 102.245, 26.655, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513426, '会东县', 513400, 3, 102.578, 26.635, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513427, '宁南县', 513400, 3, 102.760, 27.066, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513428, '普格县', 513400, 3, 102.541, 27.376, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513429, '布拖县', 513400, 3, 102.812, 27.706, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513430, '金阳县', 513400, 3, 103.249, 27.697, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513431, '昭觉县', 513400, 3, 102.843, 28.014, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513432, '喜德县', 513400, 3, 102.413, 28.307, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513433, '冕宁县', 513400, 3, 102.177, 28.550, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513434, '越西县', 513400, 3, 102.508, 28.640, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513435, '甘洛县', 513400, 3, 102.772, 28.966, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513436, '美姑县', 513400, 3, 103.132, 28.329, 'https://github.com/modood/Administrative-divisions-of-China', '36', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (513437, '雷波县', 513400, 3, 103.572, 28.263, 'https://github.com/modood/Administrative-divisions-of-China', '37', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520102, '南明区', 520100, 3, 106.714, 26.568, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520103, '云岩区', 520100, 3, 106.724, 26.605, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520111, '花溪区', 520100, 3, 106.670, 26.410, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520112, '乌当区', 520100, 3, 106.751, 26.631, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520113, '白云区', 520100, 3, 106.623, 26.679, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520115, '观山湖区', 520100, 3, 106.622, 26.601, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520121, '开阳县', 520100, 3, 106.965, 27.058, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520122, '息烽县', 520100, 3, 106.740, 27.090, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520123, '修文县', 520100, 3, 106.592, 26.839, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520181, '清镇市', 520100, 3, 106.471, 26.556, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520201, '钟山区', 520200, 3, 104.844, 26.575, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520203, '六枝特区', 520200, 3, 105.480, 26.201, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520221, '水城县', 520200, 3, 104.958, 26.548, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520281, '盘州市', 520200, 3, 104.472, 25.710, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520302, '红花岗区', 520300, 3, 106.894, 27.645, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520303, '汇川区', 520300, 3, 106.934, 27.750, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520304, '播州区', 520300, 3, 106.927, 27.726, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520322, '桐梓县', 520300, 3, 106.826, 28.134, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520323, '绥阳县', 520300, 3, 107.191, 27.946, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520324, '正安县', 520300, 3, 107.454, 28.553, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520325, '道真仡佬族苗族自治县', 520300, 3, 107.613, 28.862, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520326, '务川仡佬族苗族自治县', 520300, 3, 107.899, 28.563, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520327, '凤冈县', 520300, 3, 107.716, 27.955, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520328, '湄潭县', 520300, 3, 107.465, 27.749, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520329, '余庆县', 520300, 3, 107.905, 27.215, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520330, '习水县', 520300, 3, 106.197, 28.331, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520381, '赤水市', 520300, 3, 105.697, 28.590, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520382, '仁怀市', 520300, 3, 106.400, 27.792, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520402, '西秀区', 520400, 3, 105.966, 26.245, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520403, '平坝区', 520400, 3, 106.256, 26.406, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520422, '普定县', 520400, 3, 105.743, 26.301, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520423, '镇宁布依族苗族自治县', 520400, 3, 105.770, 26.057, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520424, '关岭布依族苗族自治县', 520400, 3, 105.619, 25.944, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520425, '紫云苗族布依族自治县', 520400, 3, 106.084, 25.751, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520502, '七星关区', 520500, 3, 105.305, 27.298, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520521, '大方县', 520500, 3, 105.612, 27.137, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520522, '黔西县', 520500, 3, 106.034, 27.008, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520523, '金沙县', 520500, 3, 106.220, 27.459, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520524, '织金县', 520500, 3, 105.771, 26.663, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520525, '纳雍县', 520500, 3, 105.383, 26.778, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:28:59', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520526, '威宁彝族回族苗族自治县', 520500, 3, 104.279, 26.856, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520527, '赫章县', 520500, 3, 104.727, 27.123, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520602, '碧江区', 520600, 3, 109.181, 27.691, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520603, '万山区', 520600, 3, 109.214, 27.518, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520621, '江口县', 520600, 3, 108.840, 27.700, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520622, '玉屏侗族自治县', 520600, 3, 108.915, 27.243, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520623, '石阡县', 520600, 3, 108.224, 27.514, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520624, '思南县', 520600, 3, 108.254, 27.938, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520625, '印江土家族苗族自治县', 520600, 3, 108.410, 27.994, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520626, '德江县', 520600, 3, 108.120, 28.264, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520627, '沿河土家族自治县', 520600, 3, 108.504, 28.564, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (520628, '松桃苗族自治县', 520600, 3, 109.203, 28.154, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522301, '兴义市', 522300, 3, 104.895, 25.092, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522302, '兴仁市', 522300, 3, 105.186, 25.435, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522323, '普安县', 522300, 3, 104.953, 25.784, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522324, '晴隆县', 522300, 3, 105.219, 25.835, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522325, '贞丰县', 522300, 3, 105.650, 25.386, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522326, '望谟县', 522300, 3, 106.094, 25.171, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522327, '册亨县', 522300, 3, 105.812, 24.984, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522328, '安龙县', 522300, 3, 105.443, 25.099, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522601, '凯里市', 522600, 3, 107.981, 26.567, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522622, '黄平县', 522600, 3, 107.916, 26.905, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522623, '施秉县', 522600, 3, 108.124, 27.033, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522624, '三穗县', 522600, 3, 108.675, 26.953, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522625, '镇远县', 522600, 3, 108.430, 27.049, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522626, '岑巩县', 522600, 3, 108.816, 27.174, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522627, '天柱县', 522600, 3, 109.208, 26.910, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522628, '锦屏县', 522600, 3, 109.201, 26.676, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522629, '剑河县', 522600, 3, 108.442, 26.728, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522630, '台江县', 522600, 3, 108.321, 26.668, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522631, '黎平县', 522600, 3, 109.137, 26.230, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522632, '榕江县', 522600, 3, 108.522, 25.932, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522633, '从江县', 522600, 3, 108.905, 25.753, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522634, '雷山县', 522600, 3, 108.078, 26.378, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522635, '麻江县', 522600, 3, 107.589, 26.491, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522636, '丹寨县', 522600, 3, 107.789, 26.198, 'https://github.com/modood/Administrative-divisions-of-China', '36', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522701, '都匀市', 522700, 3, 107.519, 26.259, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522702, '福泉市', 522700, 3, 107.520, 26.686, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522722, '荔波县', 522700, 3, 107.886, 25.411, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522723, '贵定县', 522700, 3, 107.233, 26.557, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522725, '瓮安县', 522700, 3, 107.472, 27.078, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522726, '独山县', 522700, 3, 107.545, 25.822, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522727, '平塘县', 522700, 3, 107.323, 25.832, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522728, '罗甸县', 522700, 3, 106.751, 25.425, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522729, '长顺县', 522700, 3, 106.447, 26.022, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522730, '龙里县', 522700, 3, 106.980, 26.453, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522731, '惠水县', 522700, 3, 106.657, 26.132, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (522732, '三都水族自治县', 522700, 3, 107.870, 25.983, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530102, '五华区', 530100, 3, 102.707, 25.044, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530103, '盘龙区', 530100, 3, 102.752, 25.116, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530111, '官渡区', 530100, 3, 102.744, 25.015, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530112, '西山区', 530100, 3, 102.664, 25.038, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530113, '东川区', 530100, 3, 103.188, 26.083, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530114, '呈贡区', 530100, 3, 102.821, 24.886, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530115, '晋宁区', 530100, 3, 102.595, 24.670, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530124, '富民县', 530100, 3, 102.498, 25.222, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530125, '宜良县', 530100, 3, 103.141, 24.920, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530126, '石林彝族自治县', 530100, 3, 103.291, 24.772, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530127, '嵩明县', 530100, 3, 103.037, 25.339, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530128, '禄劝彝族苗族自治县', 530100, 3, 102.472, 25.551, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530129, '寻甸回族彝族自治县', 530100, 3, 103.257, 25.558, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530181, '安宁市', 530100, 3, 102.478, 24.919, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530302, '麒麟区', 530300, 3, 103.805, 25.495, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530303, '沾益区', 530300, 3, 103.796, 25.490, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530304, '马龙区', 530300, 3, 103.578, 25.428, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530322, '陆良县', 530300, 3, 103.667, 25.030, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530323, '师宗县', 530300, 3, 103.985, 24.822, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530324, '罗平县', 530300, 3, 104.309, 24.885, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530325, '富源县', 530300, 3, 104.255, 25.674, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530326, '会泽县', 530300, 3, 103.297, 26.418, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530381, '宣威市', 530300, 3, 104.104, 26.220, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530402, '红塔区', 530400, 3, 102.540, 24.341, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530403, '江川区', 530400, 3, 102.754, 24.288, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530423, '通海县', 530400, 3, 102.760, 24.112, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530424, '华宁县', 530400, 3, 102.929, 24.193, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530425, '易门县', 530400, 3, 102.163, 24.672, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530426, '峨山彝族自治县', 530400, 3, 102.406, 24.169, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530427, '新平彝族傣族自治县', 530400, 3, 101.990, 24.070, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530428, '元江哈尼族彝族傣族自治县', 530400, 3, 101.998, 23.597, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530481, '澄江市', 530400, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530502, '隆阳区', 530500, 3, 99.166, 25.121, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530521, '施甸县', 530500, 3, 99.189, 24.723, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530523, '龙陵县', 530500, 3, 98.689, 24.587, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530524, '昌宁县', 530500, 3, 99.605, 24.828, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530581, '腾冲市', 530500, 3, 98.485, 25.033, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530602, '昭阳区', 530600, 3, 103.707, 27.320, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530621, '鲁甸县', 530600, 3, 103.558, 27.187, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530622, '巧家县', 530600, 3, 102.930, 26.908, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530623, '盐津县', 530600, 3, 104.234, 28.109, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530624, '大关县', 530600, 3, 103.891, 27.748, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530625, '永善县', 530600, 3, 103.638, 28.229, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530626, '绥江县', 530600, 3, 103.969, 28.592, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530627, '镇雄县', 530600, 3, 104.874, 27.442, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530628, '彝良县', 530600, 3, 104.048, 27.625, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530629, '威信县', 530600, 3, 105.049, 27.847, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530681, '水富市', 530600, 3, 104.416, 28.630, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530702, '古城区', 530700, 3, 100.226, 26.877, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530721, '玉龙纳西族自治县', 530700, 3, 100.237, 26.821, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530722, '永胜县', 530700, 3, 100.751, 26.684, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530723, '华坪县', 530700, 3, 101.266, 26.629, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530724, '宁蒗彝族自治县', 530700, 3, 100.852, 27.282, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530802, '思茅区', 530800, 3, 100.977, 22.787, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530821, '宁洱哈尼族彝族自治县', 530800, 3, 101.045, 23.062, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530822, '墨江哈尼族自治县', 530800, 3, 101.692, 23.432, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530823, '景东彝族自治县', 530800, 3, 100.834, 24.447, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530824, '景谷傣族彝族自治县', 530800, 3, 100.703, 23.497, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530825, '镇沅彝族哈尼族拉祜族自治县', 530800, 3, 101.109, 24.004, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530826, '江城哈尼族彝族自治县', 530800, 3, 101.862, 22.586, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530827, '孟连傣族拉祜族佤族自治县', 530800, 3, 99.584, 22.329, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530828, '澜沧拉祜族自治县', 530800, 3, 99.932, 22.556, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530829, '西盟佤族自治县', 530800, 3, 99.590, 22.645, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530902, '临翔区', 530900, 3, 100.083, 23.895, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530921, '凤庆县', 530900, 3, 99.928, 24.580, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530922, '云县', 530900, 3, 100.123, 24.437, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530923, '永德县', 530900, 3, 99.259, 24.018, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530924, '镇康县', 530900, 3, 98.825, 23.763, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530925, '双江拉祜族佤族布朗族傣族自治县', 530900, 3, 99.828, 23.473, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530926, '耿马傣族佤族自治县', 530900, 3, 99.397, 23.538, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (530927, '沧源佤族自治县', 530900, 3, 99.246, 23.147, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532301, '楚雄市', 532300, 3, 101.546, 25.033, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532322, '双柏县', 532300, 3, 101.642, 24.689, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532323, '牟定县', 532300, 3, 101.547, 25.313, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532324, '南华县', 532300, 3, 101.274, 25.192, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532325, '姚安县', 532300, 3, 101.242, 25.504, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532326, '大姚县', 532300, 3, 101.337, 25.730, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532327, '永仁县', 532300, 3, 101.666, 26.049, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532328, '元谋县', 532300, 3, 101.875, 25.704, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532329, '武定县', 532300, 3, 102.404, 25.530, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532331, '禄丰县', 532300, 3, 102.079, 25.150, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532501, '个旧市', 532500, 3, 103.160, 23.359, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532502, '开远市', 532500, 3, 103.267, 23.714, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532503, '蒙自市', 532500, 3, 103.365, 23.396, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532504, '弥勒市', 532500, 3, 103.415, 24.412, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532523, '屏边苗族自治县', 532500, 3, 103.688, 22.984, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532524, '建水县', 532500, 3, 102.827, 23.635, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532525, '石屏县', 532500, 3, 102.495, 23.706, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532527, '泸西县', 532500, 3, 103.766, 24.532, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532528, '元阳县', 532500, 3, 102.835, 23.220, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532529, '红河县', 532500, 3, 102.421, 23.369, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532530, '金平苗族瑶族傣族自治县', 532500, 3, 103.226, 22.780, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532531, '绿春县', 532500, 3, 102.392, 22.994, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532532, '河口瑶族自治县', 532500, 3, 103.939, 22.529, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532601, '文山市', 532600, 3, 104.233, 23.386, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532622, '砚山县', 532600, 3, 104.337, 23.606, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532623, '西畴县', 532600, 3, 104.673, 23.438, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532624, '麻栗坡县', 532600, 3, 104.703, 23.126, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532625, '马关县', 532600, 3, 104.394, 23.013, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532626, '丘北县', 532600, 3, 104.196, 24.042, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532627, '广南县', 532600, 3, 105.055, 24.046, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532628, '富宁县', 532600, 3, 105.631, 23.625, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532801, '景洪市', 532800, 3, 100.772, 22.000, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532822, '勐海县', 532800, 3, 100.453, 21.957, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532823, '勐腊县', 532800, 3, 101.565, 21.459, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532901, '大理市', 532900, 3, 100.241, 25.593, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532922, '漾濞彝族自治县', 532900, 3, 99.958, 25.670, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532923, '祥云县', 532900, 3, 100.551, 25.484, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532924, '宾川县', 532900, 3, 100.575, 25.827, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532925, '弥渡县', 532900, 3, 100.491, 25.344, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532926, '南涧彝族自治县', 532900, 3, 100.509, 25.044, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532927, '巍山彝族回族自治县', 532900, 3, 100.307, 25.227, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532928, '永平县', 532900, 3, 99.541, 25.465, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532929, '云龙县', 532900, 3, 99.371, 25.886, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532930, '洱源县', 532900, 3, 99.951, 26.111, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532931, '剑川县', 532900, 3, 99.906, 26.537, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (532932, '鹤庆县', 532900, 3, 100.176, 26.560, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533102, '瑞丽市', 533100, 3, 97.855, 24.018, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533103, '芒市', 533100, 3, 98.588, 24.434, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533122, '梁河县', 533100, 3, 98.297, 24.804, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533123, '盈江县', 533100, 3, 97.944, 24.691, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533124, '陇川县', 533100, 3, 97.792, 24.183, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533301, '泸水市', 533300, 3, 98.858, 25.823, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533323, '福贡县', 533300, 3, 98.869, 26.902, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533324, '贡山独龙族怒族自治县', 533300, 3, 98.666, 27.741, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533325, '兰坪白族普米族自治县', 533300, 3, 99.417, 26.454, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533401, '香格里拉市', 533400, 3, 99.701, 27.830, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533422, '德钦县', 533400, 3, 98.912, 28.486, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (533423, '维西傈僳族自治县', 533400, 3, 99.287, 27.177, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540102, '城关区', 540100, 3, 91.140, 29.655, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540103, '堆龙德庆区', 540100, 3, 91.141, 29.646, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540104, '达孜区', 540100, 3, 91.350, 29.669, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540121, '林周县', 540100, 3, 91.265, 29.894, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540122, '当雄县', 540100, 3, 91.101, 30.473, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540123, '尼木县', 540100, 3, 90.165, 29.432, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540124, '曲水县', 540100, 3, 90.744, 29.353, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540127, '墨竹工卡县', 540100, 3, 91.731, 29.834, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540171, '格尔木藏青工业园区', 540100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540172, '拉萨经济技术开发区', 540100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '72', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540173, '西藏文化旅游创意园区', 540100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '73', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540174, '达孜工业园区', 540100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '74', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540202, '桑珠孜区', 540200, 3, 88.887, 29.270, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540221, '南木林县', 540200, 3, 89.099, 29.682, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540222, '江孜县', 540200, 3, 89.606, 28.912, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540223, '定日县', 540200, 3, 87.126, 28.659, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540224, '萨迦县', 540200, 3, 88.022, 28.900, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540225, '拉孜县', 540200, 3, 87.637, 29.082, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540226, '昂仁县', 540200, 3, 87.236, 29.295, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540227, '谢通门县', 540200, 3, 88.262, 29.433, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540228, '白朗县', 540200, 3, 89.098, 28.814, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540229, '仁布县', 540200, 3, 89.842, 29.231, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540230, '康马县', 540200, 3, 89.682, 28.556, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540231, '定结县', 540200, 3, 87.766, 28.364, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540232, '仲巴县', 540200, 3, 84.032, 29.770, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540233, '亚东县', 540200, 3, 88.907, 27.485, 'https://github.com/modood/Administrative-divisions-of-China', '33', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540234, '吉隆县', 540200, 3, 85.298, 28.852, 'https://github.com/modood/Administrative-divisions-of-China', '34', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540235, '聂拉木县', 540200, 3, 85.982, 28.155, 'https://github.com/modood/Administrative-divisions-of-China', '35', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540236, '萨嘎县', 540200, 3, 85.233, 29.329, 'https://github.com/modood/Administrative-divisions-of-China', '36', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540237, '岗巴县', 540200, 3, 88.520, 28.275, 'https://github.com/modood/Administrative-divisions-of-China', '37', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540302, '卡若区', 540300, 3, 97.180, 31.139, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540321, '江达县', 540300, 3, 98.218, 31.499, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540322, '贡觉县', 540300, 3, 98.271, 30.860, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540323, '类乌齐县', 540300, 3, 96.600, 31.212, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540324, '丁青县', 540300, 3, 95.596, 31.412, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540325, '察雅县', 540300, 3, 97.569, 30.654, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540326, '八宿县', 540300, 3, 96.918, 30.053, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540327, '左贡县', 540300, 3, 97.841, 29.671, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540328, '芒康县', 540300, 3, 98.593, 29.680, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540329, '洛隆县', 540300, 3, 95.825, 30.742, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540330, '边坝县', 540300, 3, 94.708, 30.934, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540402, '巴宜区', 540400, 3, 94.361, 29.654, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540421, '工布江达县', 540400, 3, 93.246, 29.885, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540422, '米林县', 540400, 3, 94.361, 29.649, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540423, '墨脱县', 540400, 3, 95.332, 29.326, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540424, '波密县', 540400, 3, 95.768, 29.859, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540425, '察隅县', 540400, 3, 97.465, 28.660, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540426, '朗县', 540400, 3, 93.075, 29.046, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540502, '乃东区', 540500, 3, 91.762, 29.225, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540521, '扎囊县', 540500, 3, 91.337, 29.245, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540522, '贡嘎县', 540500, 3, 90.984, 29.289, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540523, '桑日县', 540500, 3, 92.016, 29.259, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540524, '琼结县', 540500, 3, 91.684, 29.025, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540525, '曲松县', 540500, 3, 92.204, 29.063, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540526, '措美县', 540500, 3, 91.434, 28.438, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:00', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540527, '洛扎县', 540500, 3, 90.860, 28.386, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540528, '加查县', 540500, 3, 92.594, 29.140, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540529, '隆子县', 540500, 3, 91.773, 29.237, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540530, '错那县', 540500, 3, 91.773, 29.237, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540531, '浪卡子县', 540500, 3, 90.398, 28.968, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540602, '色尼区', 540600, 3, 91.117, 29.647, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540621, '嘉黎县', 540600, 3, 93.233, 30.641, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540622, '比如县', 540600, 3, 93.680, 31.480, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540623, '聂荣县', 540600, 3, 92.303, 32.108, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540624, '安多县', 540600, 3, 91.682, 32.265, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540625, '申扎县', 540600, 3, 88.710, 30.931, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540626, '索县', 540600, 3, 93.786, 31.887, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540627, '班戈县', 540600, 3, 90.010, 31.392, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540628, '巴青县', 540600, 3, 94.053, 31.919, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540629, '尼玛县', 540600, 3, 87.237, 31.785, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (540630, '双湖县', 540600, 3, 88.838, 33.189, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (542521, '普兰县', 542500, 3, 81.176, 30.294, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (542522, '札达县', 542500, 3, 79.803, 31.479, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (542523, '噶尔县', 542500, 3, 80.096, 32.491, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (542524, '日土县', 542500, 3, 79.732, 33.381, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (542525, '革吉县', 542500, 3, 81.145, 32.387, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (542526, '改则县', 542500, 3, 84.063, 32.303, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (542527, '措勤县', 542500, 3, 85.159, 31.017, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610102, '新城区', 610100, 3, 108.961, 34.266, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610103, '碑林区', 610100, 3, 108.934, 34.231, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610104, '莲湖区', 610100, 3, 108.944, 34.265, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610111, '灞桥区', 610100, 3, 109.065, 34.273, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610112, '未央区', 610100, 3, 108.947, 34.293, 'https://github.com/modood/Administrative-divisions-of-China', '12', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610113, '雁塔区', 610100, 3, 108.927, 34.213, 'https://github.com/modood/Administrative-divisions-of-China', '13', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610114, '阎良区', 610100, 3, 109.226, 34.662, 'https://github.com/modood/Administrative-divisions-of-China', '14', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610115, '临潼区', 610100, 3, 109.214, 34.367, 'https://github.com/modood/Administrative-divisions-of-China', '15', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610116, '长安区', 610100, 3, 108.907, 34.159, 'https://github.com/modood/Administrative-divisions-of-China', '16', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610117, '高陵区', 610100, 3, 109.088, 34.535, 'https://github.com/modood/Administrative-divisions-of-China', '17', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610118, '鄠邑区', 610100, 3, 108.605, 34.109, 'https://github.com/modood/Administrative-divisions-of-China', '18', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610122, '蓝田县', 610100, 3, 109.323, 34.152, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610124, '周至县', 610100, 3, 108.222, 34.164, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610202, '王益区', 610200, 3, 109.076, 35.069, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610203, '印台区', 610200, 3, 109.100, 35.114, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610204, '耀州区', 610200, 3, 108.981, 34.909, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610222, '宜君县', 610200, 3, 109.117, 35.399, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610302, '渭滨区', 610300, 3, 107.150, 34.371, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610303, '金台区', 610300, 3, 107.147, 34.376, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610304, '陈仓区', 610300, 3, 107.364, 34.355, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610322, '凤翔县', 610300, 3, 107.401, 34.521, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610323, '岐山县', 610300, 3, 107.621, 34.443, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610324, '扶风县', 610300, 3, 107.900, 34.375, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610326, '眉县', 610300, 3, 107.750, 34.274, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610327, '陇县', 610300, 3, 106.864, 34.893, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610328, '千阳县', 610300, 3, 107.132, 34.642, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610329, '麟游县', 610300, 3, 107.794, 34.678, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610330, '凤县', 610300, 3, 106.516, 33.908, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610331, '太白县', 610300, 3, 107.319, 34.058, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610402, '秦都区', 610400, 3, 108.706, 34.330, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610403, '杨陵区', 610400, 3, 108.085, 34.272, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610404, '渭城区', 610400, 3, 108.737, 34.362, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610422, '三原县', 610400, 3, 108.941, 34.617, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610423, '泾阳县', 610400, 3, 108.843, 34.527, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610424, '乾县', 610400, 3, 108.239, 34.528, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610425, '礼泉县', 610400, 3, 108.425, 34.482, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610426, '永寿县', 610400, 3, 108.142, 34.692, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610428, '长武县', 610400, 3, 107.799, 35.206, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610429, '旬邑县', 610400, 3, 108.334, 35.112, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610430, '淳化县', 610400, 3, 108.581, 34.799, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610431, '武功县', 610400, 3, 108.200, 34.260, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610481, '兴平市', 610400, 3, 108.490, 34.299, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610482, '彬州市', 610400, 3, 108.709, 34.330, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610502, '临渭区', 610500, 3, 109.493, 34.498, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610503, '华州区', 610500, 3, 109.761, 34.512, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610522, '潼关县', 610500, 3, 110.246, 34.544, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610523, '大荔县', 610500, 3, 109.942, 34.797, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610524, '合阳县', 610500, 3, 110.149, 35.238, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610525, '澄城县', 610500, 3, 109.932, 35.190, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610526, '蒲城县', 610500, 3, 109.587, 34.956, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610527, '白水县', 610500, 3, 109.591, 35.177, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610528, '富平县', 610500, 3, 109.180, 34.751, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610581, '韩城市', 610500, 3, 110.443, 35.477, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610582, '华阴市', 610500, 3, 110.092, 34.566, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610602, '宝塔区', 610600, 3, 109.493, 36.591, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610603, '安塞区', 610600, 3, 109.329, 36.864, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610621, '延长县', 610600, 3, 110.012, 36.579, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610622, '延川县', 610600, 3, 110.194, 36.878, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610625, '志丹县', 610600, 3, 108.768, 36.822, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610626, '吴起县', 610600, 3, 108.176, 36.927, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610627, '甘泉县', 610600, 3, 109.351, 36.277, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610628, '富县', 610600, 3, 109.380, 35.988, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610629, '洛川县', 610600, 3, 109.432, 35.762, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610630, '宜川县', 610600, 3, 110.169, 36.050, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610631, '黄龙县', 610600, 3, 109.840, 35.584, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610632, '黄陵县', 610600, 3, 109.263, 35.579, 'https://github.com/modood/Administrative-divisions-of-China', '32', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610681, '子长市', 610600, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610702, '汉台区', 610700, 3, 107.032, 33.068, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610703, '南郑区', 610700, 3, 106.936, 32.999, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610722, '城固县', 610700, 3, 107.334, 33.157, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610723, '洋县', 610700, 3, 107.546, 33.223, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610724, '西乡县', 610700, 3, 107.767, 32.983, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610725, '勉县', 610700, 3, 106.673, 33.154, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610726, '宁强县', 610700, 3, 106.257, 32.830, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610727, '略阳县', 610700, 3, 106.157, 33.327, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610728, '镇巴县', 610700, 3, 107.895, 32.537, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610729, '留坝县', 610700, 3, 106.921, 33.618, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610730, '佛坪县', 610700, 3, 107.991, 33.524, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610802, '榆阳区', 610800, 3, 109.720, 38.277, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610803, '横山区', 610800, 3, 109.294, 37.962, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610822, '府谷县', 610800, 3, 111.067, 39.028, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610824, '靖边县', 610800, 3, 108.794, 37.599, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610825, '定边县', 610800, 3, 107.601, 37.595, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610826, '绥德县', 610800, 3, 110.263, 37.503, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610827, '米脂县', 610800, 3, 110.184, 37.755, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610828, '佳县', 610800, 3, 110.491, 38.020, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610829, '吴堡县', 610800, 3, 110.740, 37.452, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610830, '清涧县', 610800, 3, 110.121, 37.089, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610831, '子洲县', 610800, 3, 110.035, 37.611, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610881, '神木市', 610800, 3, 110.499, 38.842, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610902, '汉滨区', 610900, 3, 109.027, 32.695, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610921, '汉阴县', 610900, 3, 108.509, 32.893, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610922, '石泉县', 610900, 3, 108.248, 33.038, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610923, '宁陕县', 610900, 3, 108.314, 33.311, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610924, '紫阳县', 610900, 3, 108.534, 32.520, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610925, '岚皋县', 610900, 3, 108.902, 32.307, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610926, '平利县', 610900, 3, 109.362, 32.389, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610927, '镇坪县', 610900, 3, 109.527, 31.884, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610928, '旬阳县', 610900, 3, 109.365, 32.834, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (610929, '白河县', 610900, 3, 110.113, 32.809, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (611002, '商州区', 611000, 3, 109.941, 33.863, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (611021, '洛南县', 611000, 3, 110.149, 34.091, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (611022, '丹凤县', 611000, 3, 110.332, 33.698, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (611023, '商南县', 611000, 3, 110.882, 33.531, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (611024, '山阳县', 611000, 3, 109.882, 33.532, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (611025, '镇安县', 611000, 3, 109.153, 33.423, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (611026, '柞水县', 611000, 3, 109.114, 33.686, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620102, '城关区', 620100, 3, 103.825, 36.057, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620103, '七里河区', 620100, 3, 103.786, 36.066, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620104, '西固区', 620100, 3, 103.628, 36.088, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620105, '安宁区', 620100, 3, 103.719, 36.104, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620111, '红古区', 620100, 3, 102.859, 36.346, 'https://github.com/modood/Administrative-divisions-of-China', '11', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620121, '永登县', 620100, 3, 103.260, 36.737, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620122, '皋兰县', 620100, 3, 103.947, 36.333, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620123, '榆中县', 620100, 3, 104.113, 35.843, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620171, '兰州新区', 620100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620201, '嘉峪关市', 620200, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620302, '金川区', 620300, 3, 102.194, 38.521, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620321, '永昌县', 620300, 3, 101.985, 38.243, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620402, '白银区', 620400, 3, 104.149, 36.535, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620403, '平川区', 620400, 3, 104.825, 36.728, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620421, '靖远县', 620400, 3, 104.677, 36.571, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620422, '会宁县', 620400, 3, 105.053, 35.693, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620423, '景泰县', 620400, 3, 104.063, 37.184, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620502, '秦州区', 620500, 3, 105.724, 34.581, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620503, '麦积区', 620500, 3, 105.890, 34.570, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620521, '清水县', 620500, 3, 106.137, 34.750, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620522, '秦安县', 620500, 3, 105.675, 34.859, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620523, '甘谷县', 620500, 3, 105.341, 34.745, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620524, '武山县', 620500, 3, 104.891, 34.721, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620525, '张家川回族自治县', 620500, 3, 106.205, 34.988, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620602, '凉州区', 620600, 3, 102.730, 37.821, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620621, '民勤县', 620600, 3, 103.094, 38.624, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620622, '古浪县', 620600, 3, 102.898, 37.470, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620623, '天祝藏族自治县', 620600, 3, 103.142, 36.972, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620702, '甘州区', 620700, 3, 100.478, 38.930, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620721, '肃南裕固族自治县', 620700, 3, 99.616, 38.837, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620722, '民乐县', 620700, 3, 100.813, 38.431, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620723, '临泽县', 620700, 3, 100.164, 39.153, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620724, '高台县', 620700, 3, 99.819, 39.378, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620725, '山丹县', 620700, 3, 101.089, 38.785, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620802, '崆峒区', 620800, 3, 106.675, 35.542, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620821, '泾川县', 620800, 3, 107.368, 35.333, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620822, '灵台县', 620800, 3, 107.621, 35.065, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620823, '崇信县', 620800, 3, 107.035, 35.302, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620825, '庄浪县', 620800, 3, 106.037, 35.202, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620826, '静宁县', 620800, 3, 105.733, 35.522, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620881, '华亭市', 620800, 3, 106.653, 35.218, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620902, '肃州区', 620900, 3, 98.508, 39.745, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620921, '金塔县', 620900, 3, 98.903, 39.984, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620922, '瓜州县', 620900, 3, 95.782, 40.521, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620923, '肃北蒙古族自治县', 620900, 3, 94.877, 39.512, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620924, '阿克塞哈萨克族自治县', 620900, 3, 94.340, 39.634, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620981, '玉门市', 620900, 3, 98.494, 39.732, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (620982, '敦煌市', 620900, 3, 94.662, 40.142, 'https://github.com/modood/Administrative-divisions-of-China', '82', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621002, '西峰区', 621000, 3, 107.651, 35.731, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621021, '庆城县', 621000, 3, 107.882, 36.016, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621022, '环县', 621000, 3, 107.309, 36.568, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621023, '华池县', 621000, 3, 107.990, 36.461, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621024, '合水县', 621000, 3, 108.020, 35.819, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621025, '正宁县', 621000, 3, 108.360, 35.492, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621026, '宁县', 621000, 3, 107.928, 35.502, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621027, '镇原县', 621000, 3, 107.201, 35.677, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621102, '安定区', 621100, 3, 104.611, 35.581, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621121, '通渭县', 621100, 3, 105.242, 35.211, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621122, '陇西县', 621100, 3, 104.635, 35.004, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621123, '渭源县', 621100, 3, 104.215, 35.137, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621124, '临洮县', 621100, 3, 103.860, 35.395, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621125, '漳县', 621100, 3, 104.472, 34.848, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621126, '岷县', 621100, 3, 104.037, 34.438, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621202, '武都区', 621200, 3, 104.926, 33.392, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621221, '成县', 621200, 3, 105.742, 33.750, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621222, '文县', 621200, 3, 104.683, 32.944, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621223, '宕昌县', 621200, 3, 104.393, 34.047, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621224, '康县', 621200, 3, 105.609, 33.329, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621225, '西和县', 621200, 3, 105.299, 34.014, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621226, '礼县', 621200, 3, 105.179, 34.189, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621227, '徽县', 621200, 3, 106.088, 33.769, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (621228, '两当县', 621200, 3, 106.305, 33.909, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (622901, '临夏市', 622900, 3, 103.243, 35.604, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (622921, '临夏县', 622900, 3, 102.996, 35.492, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (622922, '康乐县', 622900, 3, 103.708, 35.371, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (622923, '永靖县', 622900, 3, 103.286, 35.958, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (622924, '广河县', 622900, 3, 103.576, 35.488, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (622925, '和政县', 622900, 3, 103.351, 35.425, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (622926, '东乡族自治县', 622900, 3, 103.389, 35.664, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (622927, '积石山保安族东乡族撒拉族自治县', 622900, 3, 102.876, 35.718, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (623001, '合作市', 623000, 3, 102.911, 35.000, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (623021, '临潭县', 623000, 3, 103.354, 34.693, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (623022, '卓尼县', 623000, 3, 103.507, 34.590, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (623023, '舟曲县', 623000, 3, 104.372, 33.785, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (623024, '迭部县', 623000, 3, 103.222, 34.056, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (623025, '玛曲县', 623000, 3, 102.073, 33.998, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (623026, '碌曲县', 623000, 3, 102.487, 34.591, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (623027, '夏河县', 623000, 3, 102.522, 35.203, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630102, '城东区', 630100, 3, 101.804, 36.600, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:01', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630103, '城中区', 630100, 3, 101.785, 36.621, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630104, '城西区', 630100, 3, 101.766, 36.628, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630105, '城北区', 630100, 3, 101.766, 36.650, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630106, '湟中区', 630100, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630121, '大通回族土族自治县', 630100, 3, 101.686, 36.927, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630123, '湟源县', 630100, 3, 101.256, 36.682, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630202, '乐都区', 630200, 3, 102.402, 36.482, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630203, '平安区', 630200, 3, 102.109, 36.501, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630222, '民和回族土族自治县', 630200, 3, 102.831, 36.320, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630223, '互助土族自治县', 630200, 3, 101.959, 36.844, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630224, '化隆回族自治县', 630200, 3, 102.264, 36.095, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (630225, '循化撒拉族自治县', 630200, 3, 102.486, 35.849, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632221, '门源回族自治县', 632200, 3, 101.622, 37.376, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632222, '祁连县', 632200, 3, 100.253, 38.177, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632223, '海晏县', 632200, 3, 100.994, 36.896, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632224, '刚察县', 632200, 3, 100.146, 37.325, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632321, '同仁县', 632300, 3, 102.018, 35.516, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632322, '尖扎县', 632300, 3, 102.031, 35.938, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632323, '泽库县', 632300, 3, 101.467, 35.035, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632324, '河南蒙古族自治县', 632300, 3, 101.616, 34.735, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632521, '共和县', 632500, 3, 100.620, 36.284, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632522, '同德县', 632500, 3, 100.578, 35.255, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632523, '贵德县', 632500, 3, 101.435, 36.041, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632524, '兴海县', 632500, 3, 99.988, 35.589, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632525, '贵南县', 632500, 3, 100.748, 35.587, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632621, '玛沁县', 632600, 3, 100.239, 34.477, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632622, '班玛县', 632600, 3, 100.737, 32.933, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632623, '甘德县', 632600, 3, 99.901, 33.969, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632624, '达日县', 632600, 3, 99.651, 33.749, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632625, '久治县', 632600, 3, 101.483, 33.429, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632626, '玛多县', 632600, 3, 98.209, 34.916, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632701, '玉树市', 632700, 3, 97.009, 32.993, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632722, '杂多县', 632700, 3, 95.301, 32.893, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632723, '称多县', 632700, 3, 97.111, 33.369, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632724, '治多县', 632700, 3, 95.613, 33.853, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632725, '囊谦县', 632700, 3, 96.481, 32.203, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632726, '曲麻莱县', 632700, 3, 95.797, 34.126, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632801, '格尔木市', 632800, 3, 94.928, 36.406, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632802, '德令哈市', 632800, 3, 97.361, 37.369, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632803, '茫崖市', 632800, 3, 97.370, 37.377, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632821, '乌兰县', 632800, 3, 98.480, 36.930, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632822, '都兰县', 632800, 3, 98.096, 36.302, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632823, '天峻县', 632800, 3, 99.023, 37.301, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (632857, '大柴旦行政委员会', 632800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '57', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640104, '兴庆区', 640100, 3, 106.289, 38.474, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640105, '西夏区', 640100, 3, 106.156, 38.496, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640106, '金凤区', 640100, 3, 106.243, 38.473, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640121, '永宁县', 640100, 3, 106.253, 38.277, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640122, '贺兰县', 640100, 3, 106.350, 38.554, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640181, '灵武市', 640100, 3, 106.340, 38.103, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640202, '大武口区', 640200, 3, 106.368, 39.019, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640205, '惠农区', 640200, 3, 106.781, 39.239, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640221, '平罗县', 640200, 3, 106.523, 38.914, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640302, '利通区', 640300, 3, 106.213, 37.983, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640303, '红寺堡区', 640300, 3, 106.062, 37.426, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640323, '盐池县', 640300, 3, 107.407, 37.783, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640324, '同心县', 640300, 3, 105.914, 36.981, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640381, '青铜峡市', 640300, 3, 106.079, 38.021, 'https://github.com/modood/Administrative-divisions-of-China', '81', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640402, '原州区', 640400, 3, 106.288, 36.004, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640422, '西吉县', 640400, 3, 105.729, 35.964, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640423, '隆德县', 640400, 3, 106.112, 35.626, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640424, '泾源县', 640400, 3, 106.331, 35.498, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640425, '彭阳县', 640400, 3, 106.638, 35.850, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640502, '沙坡头区', 640500, 3, 105.191, 37.515, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640521, '中宁县', 640500, 3, 105.685, 37.492, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (640522, '海原县', 640500, 3, 105.643, 36.565, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650102, '天山区', 650100, 3, 87.632, 43.794, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650103, '沙依巴克区', 650100, 3, 87.598, 43.801, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650104, '新市区', 650100, 3, 87.574, 43.844, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650105, '水磨沟区', 650100, 3, 87.642, 43.832, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650106, '头屯河区', 650100, 3, 87.617, 43.826, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650107, '达坂城区', 650100, 3, 88.311, 43.364, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650109, '米东区', 650100, 3, 87.656, 43.974, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650121, '乌鲁木齐县', 650100, 3, 87.409, 43.471, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650202, '独山子区', 650200, 3, 84.887, 44.328, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650203, '克拉玛依区', 650200, 3, 84.868, 45.603, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650204, '白碱滩区', 650200, 3, 85.119, 45.685, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650205, '乌尔禾区', 650200, 3, 85.694, 46.089, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650402, '高昌区', 650400, 3, 89.182, 42.948, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650421, '鄯善县', 650400, 3, 90.213, 42.869, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650422, '托克逊县', 650400, 3, 88.639, 42.792, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650502, '伊州区', 650500, 3, 93.515, 42.827, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650521, '巴里坤哈萨克自治县', 650500, 3, 93.017, 43.599, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (650522, '伊吾县', 650500, 3, 94.697, 43.255, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652301, '昌吉市', 652300, 3, 87.304, 44.013, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652302, '阜康市', 652300, 3, 87.947, 44.169, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652323, '呼图壁县', 652300, 3, 86.899, 44.191, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652324, '玛纳斯县', 652300, 3, 86.214, 44.304, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652325, '奇台县', 652300, 3, 89.594, 44.022, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652327, '吉木萨尔县', 652300, 3, 89.180, 44.000, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652328, '木垒哈萨克自治县', 652300, 3, 90.286, 43.835, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652701, '博乐市', 652700, 3, 82.051, 44.854, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652702, '阿拉山口市', 652700, 3, 82.559, 45.172, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652722, '精河县', 652700, 3, 82.894, 44.600, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652723, '温泉县', 652700, 3, 81.025, 44.969, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652801, '库尔勒市', 652800, 3, 86.175, 41.726, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652822, '轮台县', 652800, 3, 84.252, 41.778, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652823, '尉犁县', 652800, 3, 86.261, 41.344, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652824, '若羌县', 652800, 3, 88.167, 39.023, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652825, '且末县', 652800, 3, 85.532, 38.137, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652826, '焉耆回族自治县', 652800, 3, 86.574, 42.060, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652827, '和静县', 652800, 3, 86.384, 42.324, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652828, '和硕县', 652800, 3, 86.864, 42.268, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652829, '博湖县', 652800, 3, 86.632, 41.980, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652871, '库尔勒经济技术开发区', 652800, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '71', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652901, '阿克苏市', 652900, 3, 80.261, 41.169, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652902, '库车市', 652900, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652922, '温宿县', 652900, 3, 80.239, 41.277, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652924, '沙雅县', 652900, 3, 82.782, 41.222, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652925, '新和县', 652900, 3, 82.609, 41.548, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652926, '拜城县', 652900, 3, 81.874, 41.797, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652927, '乌什县', 652900, 3, 79.224, 41.215, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652928, '阿瓦提县', 652900, 3, 80.373, 40.645, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (652929, '柯坪县', 652900, 3, 79.047, 40.508, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653001, '阿图什市', 653000, 3, 76.168, 39.716, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653022, '阿克陶县', 653000, 3, 75.947, 39.148, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653023, '阿合奇县', 653000, 3, 78.446, 40.937, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653024, '乌恰县', 653000, 3, 75.259, 39.719, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653101, '喀什市', 653100, 3, 75.990, 39.470, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653121, '疏附县', 653100, 3, 75.863, 39.375, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653122, '疏勒县', 653100, 3, 76.048, 39.401, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653123, '英吉沙县', 653100, 3, 76.176, 38.930, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653124, '泽普县', 653100, 3, 77.260, 38.185, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653125, '莎车县', 653100, 3, 77.246, 38.414, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653126, '叶城县', 653100, 3, 77.414, 37.883, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653127, '麦盖提县', 653100, 3, 77.648, 38.902, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653128, '岳普湖县', 653100, 3, 76.773, 39.224, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653129, '伽师县', 653100, 3, 76.724, 39.488, 'https://github.com/modood/Administrative-divisions-of-China', '29', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653130, '巴楚县', 653100, 3, 78.549, 39.785, 'https://github.com/modood/Administrative-divisions-of-China', '30', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653131, '塔什库尔干塔吉克自治县', 653100, 3, 75.230, 37.772, 'https://github.com/modood/Administrative-divisions-of-China', '31', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653201, '和田市', 653200, 3, 79.914, 37.112, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653221, '和田县', 653200, 3, 79.819, 37.120, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653222, '墨玉县', 653200, 3, 79.729, 37.277, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653223, '皮山县', 653200, 3, 78.284, 37.621, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653224, '洛浦县', 653200, 3, 80.189, 37.074, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653225, '策勒县', 653200, 3, 80.806, 36.998, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653226, '于田县', 653200, 3, 81.677, 36.857, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (653227, '民丰县', 653200, 3, 82.696, 37.064, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654002, '伊宁市', 654000, 3, 81.278, 43.909, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654003, '奎屯市', 654000, 3, 84.903, 44.427, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654004, '霍尔果斯市', 654000, 3, 80.421, 44.202, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654021, '伊宁县', 654000, 3, 81.527, 43.977, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654022, '察布查尔锡伯自治县', 654000, 3, 81.151, 43.841, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654023, '霍城县', 654000, 3, 80.874, 44.054, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654024, '巩留县', 654000, 3, 82.232, 43.483, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654025, '新源县', 654000, 3, 83.261, 43.430, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654026, '昭苏县', 654000, 3, 81.131, 43.157, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654027, '特克斯县', 654000, 3, 81.836, 43.217, 'https://github.com/modood/Administrative-divisions-of-China', '27', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654028, '尼勒克县', 654000, 3, 82.512, 43.800, 'https://github.com/modood/Administrative-divisions-of-China', '28', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654201, '塔城市', 654200, 3, 82.979, 46.749, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654202, '乌苏市', 654200, 3, 84.714, 44.419, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654221, '额敏县', 654200, 3, 83.628, 46.525, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654223, '沙湾县', 654200, 3, 85.619, 44.326, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654224, '托里县', 654200, 3, 83.607, 45.948, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654225, '裕民县', 654200, 3, 82.983, 46.201, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654226, '和布克赛尔蒙古自治县', 654200, 3, 85.728, 46.793, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654301, '阿勒泰市', 654300, 3, 88.132, 47.827, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654321, '布尔津县', 654300, 3, 86.875, 47.702, 'https://github.com/modood/Administrative-divisions-of-China', '21', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654322, '富蕴县', 654300, 3, 89.531, 46.995, 'https://github.com/modood/Administrative-divisions-of-China', '22', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654323, '福海县', 654300, 3, 87.487, 47.112, 'https://github.com/modood/Administrative-divisions-of-China', '23', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654324, '哈巴河县', 654300, 3, 86.419, 48.061, 'https://github.com/modood/Administrative-divisions-of-China', '24', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654325, '青河县', 654300, 3, 90.383, 46.674, 'https://github.com/modood/Administrative-divisions-of-China', '25', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (654326, '吉木乃县', 654300, 3, 85.874, 47.443, 'https://github.com/modood/Administrative-divisions-of-China', '26', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659001, '石河子市', 659000, 3, 86.080, 44.305, 'https://github.com/modood/Administrative-divisions-of-China', '01', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659002, '阿拉尔市', 659000, 3, 81.281, 40.548, 'https://github.com/modood/Administrative-divisions-of-China', '02', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659003, '图木舒克市', 659000, 3, 79.074, 39.869, 'https://github.com/modood/Administrative-divisions-of-China', '03', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659004, '五家渠市', 659000, 3, 87.543, 44.167, 'https://github.com/modood/Administrative-divisions-of-China', '04', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659005, '北屯市', 659000, 3, 87.833, 47.327, 'https://github.com/modood/Administrative-divisions-of-China', '05', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659006, '铁门关市', 659000, 3, 85.670, 41.863, 'https://github.com/modood/Administrative-divisions-of-China', '06', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659007, '双河市', 659000, 3, 82.354, 44.841, 'https://github.com/modood/Administrative-divisions-of-China', '07', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659008, '可克达拉市', 659000, 3, 81.045, 43.945, 'https://github.com/modood/Administrative-divisions-of-China', '08', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659009, '昆玉市', 659000, 3, 79.291, 37.210, 'https://github.com/modood/Administrative-divisions-of-China', '09', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (659010, '胡杨河市', 659000, 3, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '10', b'0', 0, NULL, '2021-07-29 08:29:02', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900003, '东城街道', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '003', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900004, '南城街道', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '004', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900005, '万江街道', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '005', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900006, '莞城街道', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '006', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900101, '石碣镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '101', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900102, '石龙镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '102', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900103, '茶山镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '103', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900104, '石排镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '104', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900105, '企石镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '105', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900106, '横沥镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '106', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900107, '桥头镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '107', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900108, '谢岗镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '108', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900109, '东坑镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '109', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900110, '常平镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '110', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900111, '寮步镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '111', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900112, '樟木头镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '112', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900113, '大朗镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '113', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900114, '黄江镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '114', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900115, '清溪镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '115', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900116, '塘厦镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '116', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900117, '凤岗镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '117', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900118, '大岭山镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '118', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900119, '长安镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '119', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900121, '虎门镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '121', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900122, '厚街镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '122', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900123, '沙田镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '123', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900124, '道滘镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '124', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900125, '洪梅镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '125', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900126, '麻涌镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '126', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900127, '望牛墩镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '127', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900128, '中堂镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '128', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900129, '高埗镇', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '129', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900401, '松山湖', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '401', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900402, '东莞港', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '402', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (441900403, '东莞生态园', 441900, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '403', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000001, '石岐街道', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '001', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000002, '东区街道', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '002', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000003, '中山港街道', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '003', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000004, '西区街道', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '004', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000005, '南区街道', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '005', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000006, '五桂山街道', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '006', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000100, '小榄镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '100', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000101, '黄圃镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '101', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000102, '民众镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '102', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000103, '东凤镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '103', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000104, '东升镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '104', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000105, '古镇镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '105', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000106, '沙溪镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '106', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000107, '坦洲镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '107', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000108, '港口镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '108', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000109, '三角镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '109', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000110, '横栏镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '110', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000111, '南头镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '111', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000112, '阜沙镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '112', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000113, '南朗镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '113', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000114, '三乡镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '114', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000115, '板芙镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '115', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000116, '大涌镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '116', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (442000117, '神湾镇', 442000, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '117', b'0', 0, NULL, '2021-07-29 08:30:33', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400100, '那大镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '100', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400101, '和庆镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '101', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400102, '南丰镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '102', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400103, '大成镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '103', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400104, '雅星镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '104', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400105, '兰洋镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '105', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400106, '光村镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '106', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400107, '木棠镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '107', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400108, '海头镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '108', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400109, '峨蔓镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '109', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400111, '王五镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '111', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400112, '白马井镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '112', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400113, '中和镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '113', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400114, '排浦镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '114', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400115, '东成镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '115', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400116, '新州镇', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '116', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400499, '洋浦经济开发区', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '499', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
INSERT INTO `common_area` VALUES (460400500, '华南热作学院', 460400, 4, NULL, NULL, 'https://github.com/modood/Administrative-divisions-of-China', '500', b'0', 0, NULL, '2021-07-29 08:30:40', 0, NULL, '2023-09-26 04:51:56');
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
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录日志';

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
) ENGINE=InnoDB AUTO_INCREMENT=10205 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组织';

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
) ENGINE=InnoDB AUTO_INCREMENT=1030109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES (1, '系统管理', ',', 'system:view', 0, '/system/management', 'Layout', 1, 'ant-design:dashboard-filled', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2020-11-20 09:39:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (2, '平台管理', ',', 'platform:view', 0, '/platform', 'Layout', 2, 'clarity-thin-client-line', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-08-06 09:26:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (11, '审批管理', ',', NULL, 0, '/bpm', 'Layout', 11, 'ant-design:appstore-twotone', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2022-09-09 14:55:58', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (50, '开发平台', ',', 'development:view', 0, '/development', 'Layout', 50, 'ant-design:appstore-add-outlined', NULL, 1, b'1', b'0', b'0', b'1', '运维监控', b'0', NULL, NULL, '2020-11-09 09:49:52', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (101, '用户中心', ',1', 'user:info:view', 1, '/system/center', 'Layout', 0, 'ant-design:user-outlined', NULL, 1, b'1', b'0', b'0', b'1', '用户中心', b'0', 1, NULL, '2019-07-25 16:11:00', 2, '不告诉你', '2019-11-11 14:28:40');
INSERT INTO `sys_resource` VALUES (102, '权限管理', ',1', 'auth:view', 1, '/system/auth', NULL, 1, 'ant-design:security-scan-outlined', NULL, 1, b'1', b'0', b'0', b'1', '权限管理', b'0', 1, NULL, '2019-07-25 16:11:41', 3, NULL, '2019-11-11 14:28:43');
INSERT INTO `sys_resource` VALUES (103, '基础数据', ',1', 'basic:view', 1, '/system/basic', NULL, 2, 'file-icons:config-coffeescript', NULL, 1, b'1', b'0', b'0', b'1', '基础配置', b'0', 1, NULL, '2019-07-25 16:13:09', 2, '不告诉你', '2019-11-11 14:28:49');
INSERT INTO `sys_resource` VALUES (105, '资源中心', ',', 'resource:view', 1, '/resource', 'Layout', 20, 'carbon-group-resource', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-08-03 05:47:50', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (106, '消息中心', ',', '123123', 1, '/message', 'Layout', 30, 'bx-bx-message-alt-dots', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-02 09:48:51', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (120, '日志管理', ',1', 'log:view', 1, '/development/log', NULL, 20, 'radix-icons:activity-log', NULL, 1, b'1', b'0', b'0', b'1', '日志管理', b'0', NULL, NULL, '2020-11-12 04:51:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (201, '租户管理', ',2', 'tenant:page', 2, '/platform/tenant/index', '/wemirr/platform/tenant/index', 0, 'ant-design:code-sandbox-outlined', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-07-30 09:11:36', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (202, '连接管理', ',2', NULL, 2, '/platform/tenant/database', '/wemirr/platform/tenant/database/index', 1, 'ant-design:database-filled', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦', '2021-08-06 09:33:09', 1, '长风一梦', NULL);
INSERT INTO `sys_resource` VALUES (205, '租户字典', ',2', NULL, 2, '/platform/tenant/dict', '/wemirr/platform/tenant/dict/index', 1, 'bx-bxs-data', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2023-10-06 10:25:58', NULL, NULL, NULL);
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
INSERT INTO `sys_resource` VALUES (10301, '数据字典', ',1,103', 'sys:dict:page', 103, '/system/dictionary', '/wemirr/system/basic/dictionary/index', 1, 'bx-bxs-data', NULL, 1, b'1', b'0', b'0', b'1', '数据字典', b'0', NULL, NULL, '2020-11-09 07:52:59', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10302, '地区信息', ',1,103', 'area:view', 103, '/system/area', '/wemirr/system/basic/area/index', 1, 'ant-design:area-chart-outlined', NULL, 1, b'1', b'0', b'0', b'1', '地区信息', b'0', NULL, NULL, '2020-11-09 07:53:12', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10501, '文件管理', ',105', 'resource:file', 105, '/system/resource/file', '/wemirr/system/resource/file/index', 1, 'akar-icons:file', NULL, 1, b'1', b'0', b'0', b'1', '', b'0', NULL, NULL, '2021-08-03 05:49:21', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10601, '我的消息', ',106', 'message:my', 106, '/system/message/my', '/wemirr/system/message/my/index', 1, 'ant-design:comment-outlined', NULL, 1, b'1', b'0', b'0', b'1', '我的消息', b'0', NULL, NULL, '2020-11-19 02:50:54', 1, '长风一梦8888', NULL);
INSERT INTO `sys_resource` VALUES (10602, '站内消息', ',106', 'sys:site_notifies:page', 106, '/system/message/notify', '/wemirr/system/message/notify/index', 1, 'codicon-repo-push', NULL, 1, b'1', b'0', b'0', b'1', '发布消息', b'0', NULL, NULL, '2020-11-19 02:48:22', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (12001, '登录日志', ',1,120', 'log:login:page', 120, '/system/log/login', '/wemirr/system/log/login/index', 1, 'carbon-login', NULL, 1, b'1', b'0', b'0', b'1', '登录日志', b'0', NULL, NULL, '2020-11-09 09:50:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (12002, '操作日志', ',1,120', 'log:opt:page', 120, '/system/log/opt', '/wemirr/system/log/opt/index', 2, 'carbon-operation', NULL, 1, b'1', b'0', b'0', b'1', '操作日志', b'0', NULL, NULL, '2020-11-09 09:50:19', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20101, '添加', ',', 'tenant:add', 201, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:20', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20102, '编辑', ',', 'tenant:edit', 201, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:30', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20103, '删除', ',', 'tenant:remove', 201, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:38', NULL, NULL, NULL);
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
INSERT INTO `sys_resource` VALUES (1030106, '编辑', ',', 'sys:site_notifies:edit', 10602, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:30', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030107, '删除', ',', 'sys:site_notifies:remove', 10602, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:38', NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (1030108, '发布', ',', 'sys:site_notifies:publish', 10602, '', NULL, 0, '', NULL, 2, b'1', b'0', b'0', b'1', '', b'0', 1, '长风一梦8888', '2023-09-16 14:18:50', NULL, NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 1, 'PLATFORM_ADMIN', '平台管理员', '平台管理员，拥有所有数据可视权', 50, 0, 1, 1, 0, '超级管理员', '2019-10-25 13:46:00', 57, NULL, '2021-07-15 06:38:17');
INSERT INTO `sys_role` VALUES (2, 1, 'TENANT-ADMIN', '租户管理员', '租户管理员', 50, 0, 0, 1, 1, '长风一梦8888', '2022-06-07 07:52:29', 0, NULL, '2022-06-07 07:54:07');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_org`;
CREATE TABLE `sys_role_org` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `org_id` bigint NOT NULL COMMENT '组织ID',
  UNIQUE KEY `role_id` (`role_id`,`org_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';

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
INSERT INTO `sys_role_res` VALUES (1, 10501, '2023-09-14 09:17:19');
INSERT INTO `sys_role_res` VALUES (1, 10601, '2023-09-14 09:10:12');
INSERT INTO `sys_role_res` VALUES (1, 10602, '2023-09-14 09:10:15');
INSERT INTO `sys_role_res` VALUES (1, 12001, '2021-08-10 02:31:23');
INSERT INTO `sys_role_res` VALUES (1, 12002, '2021-08-10 02:31:23');
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
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of sys_site_message
-- ----------------------------
BEGIN;
INSERT INTO `sys_site_message` VALUES (1, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-12 20:05:39', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (5, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-13 09:26:59', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (7, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-13 09:26:59', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (9, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-13 09:26:59', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (16, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-13 09:27:06', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (18, '1', '测试消息', '<p>测试消息</p>', '测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-13 09:27:11', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (28, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-13 09:28:06', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (34, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-13 10:01:48', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (37, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:33:41', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (39, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:33:41', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (41, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:33:42', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (49, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:33:56', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (51, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:33:56', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (53, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:33:57', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (59, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:51:42', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (61, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:51:42', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (63, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:51:43', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (71, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-14 17:51:52', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (72, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '管理员', '2021-07-15 20:10:36', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (76, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦', '2021-07-21 15:43:40', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (78, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦', '2021-07-21 15:43:40', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (80, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦', '2021-07-21 15:43:40', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (83, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦', '2021-07-21 15:43:44', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (87, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦', '2021-07-21 15:43:48', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (89, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦', '2021-07-22 10:59:20', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (90, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'1', 1, b'0', 1, '长风一梦', '2021-07-22 10:59:20', 1, '长风一梦', '2021-07-27 10:51:14');
INSERT INTO `sys_site_message` VALUES (91, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2021-11-16 19:06:34', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (92, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'1', 1, b'0', 1, '长风一梦8888', '2021-11-16 19:06:34', 1, '长风一梦8888', '2022-06-07 07:23:03');
INSERT INTO `sys_site_message` VALUES (93, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'0', 1, '长风一梦8888', '2021-11-16 19:06:38', 1, '长风一梦8888', '2022-06-07 07:23:04');
INSERT INTO `sys_site_message` VALUES (94, '2', '测试待办', '<p>测试待办</p><img src=\"http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/15/30369909559790.png\" contenteditable=\"false\"/>', '测试待办', b'1', 1, b'0', 1, '长风一梦8888', '2021-11-16 19:06:39', 1, '长风一梦8888', '2022-06-07 07:23:02');
INSERT INTO `sys_site_message` VALUES (95, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 14:35:27', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (96, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'0', 1, '长风一梦8888', '2023-05-18 14:35:27', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (97, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 14:35:28', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (98, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'0', 1, '长风一梦8888', '2023-05-18 14:35:28', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (99, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 14:35:52', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (100, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'0', 1, '长风一梦8888', '2023-05-18 14:35:52', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (101, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 14:35:53', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (102, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'0', 1, '长风一梦8888', '2023-05-18 14:35:53', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (103, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'0', 1, '长风一梦8888', '2023-05-18 14:35:53', 1, '长风一梦8888', '2023-05-18 07:29:03');
INSERT INTO `sys_site_message` VALUES (104, '2', '测试待办', '<p>测试待办</p><img src=\"http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/15/30369909559790.png\" contenteditable=\"false\"/>', '测试待办', b'0', 1, b'0', 1, '长风一梦8888', '2023-05-18 14:35:54', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (105, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 14:36:09', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (106, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'0', 1, '长风一梦8888', '2023-05-18 14:36:09', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (107, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 14:36:25', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (108, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'0', 1, '长风一梦8888', '2023-05-18 14:36:25', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (109, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'0', 1, '长风一梦8888', '2023-05-18 14:36:26', 1, '长风一梦8888', '2023-05-18 07:29:03');
INSERT INTO `sys_site_message` VALUES (110, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 14:36:28', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (111, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 14:36:28', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (112, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 14:57:41', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (113, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 14:57:41', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (114, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 14:57:42', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (115, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 14:57:42', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (116, '2', '测试待办', '<p>测试待办</p><img src=\"http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/15/30369909559790.png\" contenteditable=\"false\"/>', '测试待办', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:13:02', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (117, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 15:13:08', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (118, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:13:08', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (119, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 15:13:08', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (120, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:13:08', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (121, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:13:08', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (122, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 15:13:09', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (123, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:13:09', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (124, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:14:50', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (125, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:14:50', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (126, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:14:50', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (127, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:14:50', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (128, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 15:15:11', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (129, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:15:11', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (130, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 15:15:11', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (131, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:15:11', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (132, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-05-18 15:15:12', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (133, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:15:12', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (134, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:15:19', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (135, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-05-18 15:15:19', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (136, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-09-16 20:53:55', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (137, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-09-16 20:54:25', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (138, '2', '测试待办', '<p>测试待办</p><img src=\"http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/15/30369909559790.png\" contenteditable=\"false\"/>', '测试待办', b'0', 1, b'1', 1, '长风一梦8888', '2023-09-17 16:56:25', 1, '长风一梦8888', '2023-09-17 09:24:16');
INSERT INTO `sys_site_message` VALUES (139, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'1', 1, '长风一梦8888', '2023-09-17 16:56:26', 1, '长风一梦8888', '2023-09-17 09:24:22');
INSERT INTO `sys_site_message` VALUES (140, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-09-17 16:56:27', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (141, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'1', 1, '长风一梦8888', '2023-09-17 16:56:27', 1, '长风一梦8888', '2023-09-17 09:24:14');
INSERT INTO `sys_site_message` VALUES (142, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'0', 1, b'0', 1, '长风一梦8888', '2023-09-17 17:24:40', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (143, '0', '测试通知', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'1', 1, b'0', 1, '长风一梦8888', '2023-09-17 17:24:45', 1, '长风一梦8888', '2023-09-17 14:18:41');
INSERT INTO `sys_site_message` VALUES (144, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 2, b'0', 1, '长风一梦8888', '2023-09-17 21:46:34', NULL, NULL, NULL);
INSERT INTO `sys_site_message` VALUES (145, '1', '测试消息', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, b'0', 1, '长风一梦8888', '2023-09-17 21:46:34', NULL, NULL, NULL);
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
INSERT INTO `sys_station` VALUES (100, 1, '总经理', 'CEO', 2, 0, 100, b'1', '总部-1把手', '2019-07-10 17:03:03', NULL, 1, 1, '长风一梦8888', '2021-11-27 06:35:22');
INSERT INTO `sys_station` VALUES (101, 1, '副总经理', NULL, 2, 1, 10001, b'1', '总部-2把手', '2019-07-22 17:07:55', NULL, 1, 1, '长风一梦', '2021-07-28 02:58:53');
INSERT INTO `sys_station` VALUES (103, 1, '研发经理', NULL, 1, 1, 10002, b'1', '子公司-研发部老大', '2019-11-07 16:08:49', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:06');
INSERT INTO `sys_station` VALUES (104, 1, '副总经理', NULL, 2, 1, 10001, b'1', '子公司-老大', '2019-11-16 09:51:45', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:29');
INSERT INTO `sys_station` VALUES (106, 1, '产品经理', NULL, 0, 1, 10002, b'1', '子公司-产品部老大', '2019-11-16 09:53:27', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_station` VALUES (107, 1, '人事经理', NULL, 0, 1, 10002, b'1', '子公司-综合老大', '2019-11-16 09:54:43', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_station` VALUES (108, 1, 'Java研发', 'CTI', 0, 1, 10203, b'1', '普通员工', '2019-11-16 09:55:04', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:38');
INSERT INTO `sys_station` VALUES (1011, 1, 'UI工程师', 'CTI', 0, 1, 10007, b'1', '普通员工', '2019-11-16 09:55:40', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:08');
INSERT INTO `sys_station` VALUES (1012, 1, '运维工程师', 'CTI', NULL, 1, 10007, b'1', '普通员工', '2019-11-16 09:55:53', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:09');
INSERT INTO `sys_station` VALUES (1013, 1, '行政经理', NULL, 0, 1, 10003, b'1', '普通员工', '2019-11-16 09:56:04', NULL, 3, 1, '长风一梦8888', '2021-11-27 06:36:22');
INSERT INTO `sys_station` VALUES (1014, 1, '人事经理', NULL, 0, 1, 10007, b'1', '北京分公司-综合部老大', '2019-11-16 09:56:38', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:09');
INSERT INTO `sys_station` VALUES (1015, 1, '研发经理', NULL, 1, 1, 10007, b'1', '北京分公司-研发部老大', '2019-11-16 09:57:07', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:10');
INSERT INTO `sys_station` VALUES (1016, 1, '销售经理', NULL, 0, 1, 10007, b'1', '北京销售部老大', '2019-11-16 09:57:40', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:10');
INSERT INTO `sys_station` VALUES (10121, 1, '销售总监', NULL, 1, 1, 10005, b'1', '总部2把手', '2019-11-16 09:59:10', NULL, 3, 1, '长风一梦', '2021-07-29 02:36:31');
INSERT INTO `sys_station` VALUES (10122, 1, '销售员工', NULL, 0, 1, 10007, b'1', '普通员工', '2019-11-16 09:58:41', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:11');
INSERT INTO `sys_station` VALUES (10123, 1, '财务总监', NULL, 2, 1, 10007, b'1', '总部2把手', '2019-11-16 09:59:39', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:11');
INSERT INTO `sys_station` VALUES (10124, 1, '市场经理', NULL, 0, 1, 10007, b'1', '总部市场部老大', '2019-11-16 10:00:03', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:11');
INSERT INTO `sys_station` VALUES (10128, 1, '前端工程师', 'CTI', 1, 1, 10007, b'1', '普通员工', '2019-11-16 10:00:43', NULL, 3, 1, '长风一梦8888', '2021-11-27 06:38:03');
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
INSERT INTO `t_dynamic_datasource` VALUES (2, '123123123', 'admin', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', NULL, 0, '123123213123', 3306, NULL, b'0', b'0', 1, '长风一梦8888', '2023-02-21 01:02:51', 1, '长风一梦8888', '2023-02-21 01:04:52');
INSERT INTO `t_dynamic_datasource` VALUES (3, '21312312', 'admin', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', NULL, 0, '31231231231231', 3306, NULL, b'0', b'0', 1, '长风一梦8888', '2023-02-21 01:05:06', NULL, NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='租户信息';

-- ----------------------------
-- Records of t_tenant
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant` VALUES (1, '0000', '平台超级租户', 1, 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '000000@qq.com', '唐亚峰', '13002170000', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'https://cloud.battcn.com', NULL, b'0', b'0', 0, NULL, '2021-07-31 07:27:08', 1, '长风一梦', '2022-06-07 07:25:54');
INSERT INTO `t_tenant` VALUES (2, '2222', '小米科技有限责任公司', 1, 1, '小米', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '666666@qq.com', '雷军', '13002176666', '4', 110000, '北京市', 110100, '市辖区', '北京市海淀区西二旗中路33号院6号楼6层006号 ', 110108, '海淀区', '91110108551385082Q', '雷军', 'https://cloud.battcn.com', NULL, b'0', b'0', 0, NULL, '2021-07-31 08:14:02', 1, '长风一梦', '2022-06-07 07:24:34');
INSERT INTO `t_tenant` VALUES (3, '8888', '深圳腾讯科技股份有限公司', 1, 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '888888@qq.com', '马化腾', '13002178888', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'https://cloud.battcn.com', NULL, b'0', b'0', 0, NULL, '2021-07-31 07:27:08', 1, '长风一梦', '2022-06-07 07:25:25');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 COMMENT='租户配置信息';

-- ----------------------------
-- Records of t_tenant_config
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_config` VALUES (3, 1, 1, b'0', 1, '长风一梦8888', '2021-11-16 12:08:03', NULL, NULL, NULL);
INSERT INTO `t_tenant_config` VALUES (4, 4, 1, b'0', 1, '长风一梦8888', '2022-01-03 06:48:52', NULL, NULL, NULL);
INSERT INTO `t_tenant_config` VALUES (6, 5, 1, b'0', 1, '长风一梦8888', '2022-01-04 09:48:02', NULL, NULL, NULL);
INSERT INTO `t_tenant_config` VALUES (7, 6, 1, b'0', 1, '长风一梦8888', '2022-01-04 09:52:40', NULL, NULL, NULL);
INSERT INTO `t_tenant_config` VALUES (9, 2, 1, b'1', 1, '长风一梦8888', '2022-06-08 02:11:17', NULL, NULL, '2022-12-04 08:28:49');
INSERT INTO `t_tenant_config` VALUES (10, 2, 1, b'1', 1, '长风一梦8888', '2022-12-04 08:28:49', NULL, NULL, '2022-12-04 08:28:57');
INSERT INTO `t_tenant_config` VALUES (11, 2, 1, b'1', 1, '长风一梦8888', '2022-12-04 08:28:57', NULL, NULL, '2022-12-04 08:30:03');
INSERT INTO `t_tenant_config` VALUES (12, 2, 1, b'1', 1, '长风一梦8888', '2022-12-04 08:30:03', NULL, NULL, '2022-12-04 08:31:17');
INSERT INTO `t_tenant_config` VALUES (13, 3, 1, b'1', 1, '长风一梦8888', '2022-12-04 08:31:08', NULL, NULL, '2022-12-04 08:36:04');
INSERT INTO `t_tenant_config` VALUES (14, 2, 1, b'1', 1, '长风一梦8888', '2022-12-04 08:31:17', NULL, NULL, '2022-12-04 08:31:52');
INSERT INTO `t_tenant_config` VALUES (15, 2, 1, b'1', 1, '长风一梦8888', '2022-12-04 08:31:52', NULL, NULL, '2022-12-04 08:36:00');
INSERT INTO `t_tenant_config` VALUES (16, 2, 1, b'1', 1, '长风一梦8888', '2022-12-04 08:36:00', NULL, NULL, '2022-12-04 08:36:10');
INSERT INTO `t_tenant_config` VALUES (17, 3, 1, b'1', 1, '长风一梦8888', '2022-12-04 08:36:04', NULL, NULL, '2022-12-04 08:37:36');
INSERT INTO `t_tenant_config` VALUES (18, 2, 1, b'1', 1, '长风一梦8888', '2022-12-04 08:36:10', NULL, NULL, '2022-12-04 08:37:32');
INSERT INTO `t_tenant_config` VALUES (19, 2, 1, b'0', 1, '长风一梦8888', '2022-12-04 08:37:32', NULL, NULL, NULL);
INSERT INTO `t_tenant_config` VALUES (20, 3, 1, b'0', 1, '长风一梦8888', '2022-12-04 08:37:36', NULL, NULL, NULL);
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
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `username` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `org_id` bigint DEFAULT NULL COMMENT '组织ID\n#c_core_org\n@InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.github.zuihou.authority.entity.core.Org>',
  `station_id` bigint DEFAULT NULL COMMENT '岗位ID\n#c_core_station\n@InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>',
  `readonly` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否内置',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机',
  `id_card` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证',
  `sex` tinyint DEFAULT '1' COMMENT '性别\n#Sex{W:女;M:男;N:未知}',
  `status` bit(1) DEFAULT b'0' COMMENT '状态 \n1启用 0禁用',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '头像',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `nation` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '民族\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION) RemoteData<String, String>\n',
  `education` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学历\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION) RemoteData<String, String>',
  `position_status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职位状态\n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.POSITION_STATUS) RemoteData<String, String>',
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 1, 'admin', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '长风一梦8888', 100, 100, b'0', '1837307557@qq.com', '13002171912', '111111111', 1, b'1', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '12323', 'mz_daiz', 'SUOSHI', 'QUIT', '2020-11-05', b'0', 0, NULL, '2020-10-16 03:25:36', 1, '长风一梦8888', '2023-09-11 07:22:44');
INSERT INTO `t_user` VALUES (23, 1, '1234', '{bcrypt}$2a$10$j1U0rIRl8ODzc2j5rkLx8OSvjjEZ.cJ/Xe8DzkqL2jgAyX2c3x.4C', '444', NULL, NULL, b'0', NULL, '13002171921', NULL, 1, b'0', '', NULL, NULL, NULL, NULL, NULL, b'1', 1, '长风一梦8888', '2022-12-04 08:05:29', NULL, NULL, '2022-12-04 08:06:21');
INSERT INTO `t_user` VALUES (24, 1, 'admin2', '{bcrypt}$2a$10$ieDPTa5Awa8Kq48BzRuBuurU8.4AvUYUOX1RmRxxbccf56vUX/wbC', '1300217195', 100, 100, b'0', 'em@163.com', '13002171921', NULL, 1, b'1', '', NULL, 'mz_daiz', 'XIAOXUE', 'QUIT', NULL, b'0', 1, '长风一梦8888', '2023-02-21 01:17:43', NULL, NULL, '2023-02-21 01:20:11');
COMMIT;



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
) ENGINE=InnoDB AUTO_INCREMENT=1721821542981042179 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国际化信息';


CREATE TABLE `common_i18n_locale_message` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `parent_id` bigint DEFAULT NULL COMMENT 'i18n_data.id',
    `locale` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '语言',
    `message` varchar(255) DEFAULT NULL COMMENT '文本值，可以使用 {} 加角标，作为占位符',
    `created_by` bigint DEFAULT '0' COMMENT '创建人id',
    `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1721821543060733955 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国际化信息';

SET FOREIGN_KEY_CHECKS = 1;
