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
-- Records of common_area
-- ----------------------------
BEGIN;
INSERT INTO `common_area` VALUES (513225, '九寨沟县', 513200, 3, 104.244, 33.252, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513201, '马尔康市', 513200, 3, 102.207, 31.906, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513224, '松潘县', 513200, 3, 103.605, 32.655, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513233, '红原县', 513200, 3, 102.544, 32.791, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513223, '茂县', 513200, 3, 103.854, 31.681, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513230, '壤塘县', 513200, 3, 100.979, 32.266, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513222, '理县', 513200, 3, 103.167, 31.436, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513226, '金川县', 513200, 3, 102.064, 31.476, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513227, '小金县', 513200, 3, 102.364, 30.999, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513231, '阿坝县', 513200, 3, 101.707, 32.902, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513232, '若尔盖县', 513200, 3, 102.962, 33.576, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513221, '汶川县', 513200, 3, 103.590, 31.477, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513228, '黑水县', 513200, 3, 102.990, 32.062, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632823, '天峻县', 632800, 3, 99.023, 37.301, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632801, '格尔木市', 632800, 3, 94.928, 36.406, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632822, '都兰县', 632800, 3, 98.096, 36.302, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632825, '海西蒙古族藏族自治州直辖', 632800, 3, 97.370, 37.377, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632821, '乌兰县', 632800, 3, 98.480, 36.930, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632803, '茫崖市', 632800, 3, 97.370, 37.377, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632802, '德令哈市', 632800, 3, 97.361, 37.369, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370982, '新泰市', 370900, 3, 117.768, 35.909, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370911, '岱岳区', 370900, 3, 117.042, 36.188, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370921, '宁阳县', 370900, 3, 116.806, 35.759, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370983, '肥城市', 370900, 3, 116.768, 36.183, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370902, '泰山区', 370900, 3, 117.135, 36.192, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370923, '东平县', 370900, 3, 116.470, 35.937, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421182, '武穴市', 421100, 3, 115.561, 29.844, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421125, '浠水县', 421100, 3, 115.266, 30.452, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421181, '麻城市', 421100, 3, 115.008, 31.173, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421122, '红安县', 421100, 3, 114.618, 31.288, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421127, '黄梅县', 421100, 3, 115.944, 30.070, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421121, '团风县', 421100, 3, 114.872, 30.644, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421126, '蕲春县', 421100, 3, 115.437, 30.226, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421102, '黄州区', 421100, 3, 114.879, 30.434, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421123, '罗田县', 421100, 3, 115.399, 30.783, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421124, '英山县', 421100, 3, 115.681, 30.735, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330226, '宁海县', 330200, 3, 121.429, 29.288, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330282, '慈溪市', 330200, 3, 121.267, 30.170, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330206, '北仑区', 330200, 3, 121.845, 29.899, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330211, '镇海区', 330200, 3, 121.717, 29.949, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330213, '奉化区', 330200, 3, 121.518, 29.570, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330225, '象山县', 330200, 3, 121.869, 29.477, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330212, '鄞州区', 330200, 3, 121.547, 29.817, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330281, '余姚市', 330200, 3, 121.155, 30.038, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330205, '江北区', 330200, 3, 121.555, 29.887, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330203, '海曙区', 330200, 3, 121.551, 29.860, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640323, '盐池县', 640300, 3, 107.407, 37.783, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640303, '红寺堡区', 640300, 3, 106.062, 37.426, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640302, '利通区', 640300, 3, 106.213, 37.983, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640381, '青铜峡市', 640300, 3, 106.079, 38.021, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640324, '同心县', 640300, 3, 105.914, 36.981, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411327, '社旗县', 411300, 3, 112.948, 33.056, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411326, '淅川县', 411300, 3, 111.491, 33.138, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411381, '邓州市', 411300, 3, 112.087, 32.688, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411330, '桐柏县', 411300, 3, 113.428, 32.380, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411323, '西峡县', 411300, 3, 111.474, 33.307, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411322, '方城县', 411300, 3, 113.012, 33.254, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411324, '镇平县', 411300, 3, 112.235, 33.034, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411303, '卧龙区', 411300, 3, 112.529, 32.990, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411321, '南召县', 411300, 3, 112.429, 33.490, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411328, '唐河县', 411300, 3, 112.808, 32.681, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411329, '新野县', 411300, 3, 112.360, 32.521, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411302, '宛城区', 411300, 3, 112.540, 33.004, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411325, '内乡县', 411300, 3, 111.849, 33.045, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650104, '新市区', 650100, 3, 87.574, 43.844, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650121, '乌鲁木齐县', 650100, 3, 87.409, 43.471, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650107, '达坂城区', 650100, 3, 88.311, 43.364, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650106, '头屯河区', 650100, 3, 87.617, 43.826, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650105, '水磨沟区', 650100, 3, 87.642, 43.832, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650103, '沙依巴克区', 650100, 3, 87.598, 43.801, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650109, '米东区', 650100, 3, 87.656, 43.974, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650102, '天山区', 650100, 3, 87.632, 43.794, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445321, '新兴县', 445300, 3, 112.225, 22.696, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445381, '罗定市', 445300, 3, 111.570, 22.769, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445322, '郁南县', 445300, 3, 111.535, 23.235, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445302, '云城区', 445300, 3, 112.044, 22.928, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445303, '云安区', 445300, 3, 112.003, 23.071, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530922, '云县', 530900, 3, 100.123, 24.437, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530902, '临翔区', 530900, 3, 100.083, 23.895, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530927, '沧源佤族自治县', 530900, 3, 99.246, 23.147, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530925, '双江拉祜族佤族布朗族傣族自治县', 530900, 3, 99.828, 23.473, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530923, '永德县', 530900, 3, 99.259, 24.018, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530926, '耿马傣族佤族自治县', 530900, 3, 99.397, 23.538, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530924, '镇康县', 530900, 3, 98.825, 23.763, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530921, '凤庆县', 530900, 3, 99.928, 24.580, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152922, '阿拉善右旗', 152900, 3, 101.667, 39.216, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152921, '阿拉善左旗', 152900, 3, 105.666, 38.833, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152923, '额济纳旗', 152900, 3, 101.056, 41.954, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150602, '东胜区', 150600, 3, 109.963, 39.823, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150622, '准格尔旗', 150600, 3, 111.240, 39.864, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150626, '乌审旗', 150600, 3, 108.818, 38.604, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150603, '康巴什区', 150600, 3, 109.781, 39.608, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150627, '伊金霍洛旗', 150600, 3, 109.748, 39.565, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150624, '鄂托克旗', 150600, 3, 107.976, 39.090, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150625, '杭锦旗', 150600, 3, 108.736, 39.833, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150621, '达拉特旗', 150600, 3, 110.034, 40.412, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150623, '鄂托克前旗', 150600, 3, 107.478, 38.182, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620822, '灵台县', 620800, 3, 107.621, 35.065, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620826, '静宁县', 620800, 3, 105.733, 35.522, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620881, '华亭市', 620800, 3, 106.653, 35.218, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620825, '庄浪县', 620800, 3, 106.037, 35.202, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620802, '崆峒区', 620800, 3, 106.675, 35.542, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620823, '崇信县', 620800, 3, 107.035, 35.302, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620821, '泾川县', 620800, 3, 107.368, 35.333, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341204, '颍泉区', 341200, 3, 115.808, 32.925, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341221, '临泉县', 341200, 3, 115.261, 33.040, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341226, '颍上县', 341200, 3, 116.257, 32.653, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341225, '阜南县', 341200, 3, 115.596, 32.658, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341282, '界首市', 341200, 3, 115.375, 33.257, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341203, '颍东区', 341200, 3, 115.857, 32.912, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341202, '颍州区', 341200, 3, 115.807, 32.883, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341222, '太和县', 341200, 3, 115.622, 33.160, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140882, '河津市', 140800, 3, 110.712, 35.596, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140830, '芮城县', 140800, 3, 110.694, 34.694, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140821, '临猗县', 140800, 3, 110.775, 35.144, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140823, '闻喜县', 140800, 3, 111.225, 35.357, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140828, '夏县', 140800, 3, 111.220, 35.141, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140827, '垣曲县', 140800, 3, 111.670, 35.298, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140802, '盐湖区', 140800, 3, 110.998, 35.015, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140824, '稷山县', 140800, 3, 110.983, 35.604, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140881, '永济市', 140800, 3, 110.448, 34.867, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140822, '万荣县', 140800, 3, 110.838, 35.415, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140826, '绛县', 140800, 3, 111.568, 35.491, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140829, '平陆县', 140800, 3, 111.194, 34.829, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140825, '新绛县', 140800, 3, 111.225, 35.616, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410182, '荥阳市', 410100, 3, 113.440, 34.746, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410106, '上街区', 410100, 3, 113.309, 34.803, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410122, '中牟县', 410100, 3, 113.976, 34.719, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410105, '金水区', 410100, 3, 113.661, 34.800, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410104, '管城回族区', 410100, 3, 113.678, 34.755, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410108, '惠济区', 410100, 3, 113.617, 34.867, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410185, '登封市', 410100, 3, 113.050, 34.454, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410181, '巩义市', 410100, 3, 113.022, 34.748, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410102, '中原区', 410100, 3, 113.613, 34.748, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410183, '新密市', 410100, 3, 113.391, 34.539, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410184, '新郑市', 410100, 3, 113.741, 34.396, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410103, '二七区', 410100, 3, 113.640, 34.724, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320411, '新北区', 320400, 3, 119.972, 31.831, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320413, '金坛区', 320400, 3, 119.598, 31.723, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320481, '溧阳市', 320400, 3, 119.484, 31.417, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320412, '武进区', 320400, 3, 119.942, 31.701, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320404, '钟楼区', 320400, 3, 119.902, 31.802, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320402, '天宁区', 320400, 3, 119.975, 31.780, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230706, '翠峦区', 230700, 3, 128.670, 47.727, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230714, '乌伊岭区', 230700, 3, 129.438, 48.590, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230708, '美溪区', 230700, 3, 129.129, 47.635, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230710, '五营区', 230700, 3, 129.245, 48.108, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230707, '新青区', 230700, 3, 129.534, 48.290, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230716, '上甘岭区', 230700, 3, 129.024, 47.975, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230715, '红星区', 230700, 3, 129.391, 48.239, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230711, '乌马河区', 230700, 3, 128.799, 47.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230709, '金山屯区', 230700, 3, 129.429, 47.413, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230702, '伊春区', 230700, 3, 128.907, 47.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230722, '嘉荫县', 230700, 3, 130.404, 48.889, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230705, '西林区', 230700, 3, 129.313, 47.481, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230704, '友好区', 230700, 3, 128.841, 47.854, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230713, '带岭区', 230700, 3, 129.021, 47.028, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230712, '汤旺河区', 230700, 3, 129.571, 48.455, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230703, '南岔区', 230700, 3, 129.283, 47.138, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230781, '铁力市', 230700, 3, 128.032, 46.987, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370831, '泗水县', 370800, 3, 117.251, 35.664, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370830, '汶上县', 370800, 3, 116.489, 35.733, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370829, '嘉祥县', 370800, 3, 116.342, 35.408, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370827, '鱼台县', 370800, 3, 116.651, 35.013, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370832, '梁山县', 370800, 3, 116.096, 35.802, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370826, '微山县', 370800, 3, 117.129, 34.807, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370883, '邹城市', 370800, 3, 117.004, 35.405, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370881, '曲阜市', 370800, 3, 116.987, 35.581, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370828, '金乡县', 370800, 3, 116.312, 35.067, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370811, '任城区', 370800, 3, 116.595, 35.407, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370812, '兖州区', 370800, 3, 116.784, 35.553, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632726, '曲麻莱县', 632700, 3, 95.797, 34.126, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632701, '玉树市', 632700, 3, 97.009, 32.993, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632725, '囊谦县', 632700, 3, 96.481, 32.203, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632722, '杂多县', 632700, 3, 95.301, 32.893, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632724, '治多县', 632700, 3, 95.613, 33.853, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632723, '称多县', 632700, 3, 97.111, 33.369, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421002, '沙市区', 421000, 3, 112.256, 30.311, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421003, '荆州区', 421000, 3, 112.190, 30.353, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421081, '石首市', 421000, 3, 112.425, 29.721, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421023, '监利县', 421000, 3, 112.897, 29.812, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421024, '江陵县', 421000, 3, 112.425, 30.042, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421022, '公安县', 421000, 3, 112.230, 30.058, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421087, '松滋市', 421000, 3, 111.757, 30.175, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421083, '洪湖市', 421000, 3, 113.476, 29.825, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330303, '龙湾区', 330300, 3, 120.811, 27.933, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330304, '瓯海区', 330300, 3, 120.709, 27.914, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330329, '泰顺县', 330300, 3, 119.718, 27.557, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330326, '平阳县', 330300, 3, 120.566, 27.662, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330382, '乐清市', 330300, 3, 120.967, 28.116, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330381, '瑞安市', 330300, 3, 120.655, 27.779, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330305, '洞头区', 330300, 3, 121.157, 27.836, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330302, '鹿城区', 330300, 3, 120.655, 28.015, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330324, '永嘉县', 330300, 3, 120.682, 28.151, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330327, '苍南县', 330300, 3, 120.426, 27.518, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330328, '文成县', 330300, 3, 120.091, 27.787, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340121, '长丰县', 340100, 3, 117.168, 32.478, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340122, '肥东县', 340100, 3, 117.469, 31.888, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340102, '瑶海区', 340100, 3, 117.309, 31.858, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340124, '庐江县', 340100, 3, 117.288, 31.256, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340103, '庐阳区', 340100, 3, 117.265, 31.879, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340181, '巢湖市', 340100, 3, 117.862, 31.599, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340123, '肥西县', 340100, 3, 117.158, 31.707, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340104, '蜀山区', 340100, 3, 117.261, 31.851, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340111, '包河区', 340100, 3, 117.310, 31.793, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640221, '平罗县', 640200, 3, 106.523, 38.914, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640205, '惠农区', 640200, 3, 106.781, 39.239, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640202, '大武口区', 640200, 3, 106.368, 39.019, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411202, '湖滨区', 411200, 3, 111.188, 34.771, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411281, '义马市', 411200, 3, 111.874, 34.747, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411203, '陕州区', 411200, 3, 111.104, 34.721, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411282, '灵宝市', 411200, 3, 110.894, 34.517, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411221, '渑池县', 411200, 3, 111.762, 34.767, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411224, '卢氏县', 411200, 3, 111.048, 34.054, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (512021, '安岳县', 512000, 3, 105.336, 30.097, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (512022, '乐至县', 512000, 3, 105.020, 30.275, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (512002, '雁江区', 512000, 3, 104.677, 30.108, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220882, '大安市', 220800, 3, 124.293, 45.507, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220821, '镇赉县', 220800, 3, 123.200, 45.847, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220802, '洮北区', 220800, 3, 122.851, 45.622, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220881, '洮南市', 220800, 3, 122.784, 45.339, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220822, '通榆县', 220800, 3, 123.088, 44.813, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640122, '贺兰县', 640100, 3, 106.350, 38.554, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640121, '永宁县', 640100, 3, 106.253, 38.277, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640106, '金凤区', 640100, 3, 106.243, 38.473, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640104, '兴庆区', 640100, 3, 106.289, 38.474, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640181, '灵武市', 640100, 3, 106.340, 38.103, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640105, '西夏区', 640100, 3, 106.156, 38.496, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652325, '奇台县', 652300, 3, 89.594, 44.022, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652302, '阜康市', 652300, 3, 87.947, 44.169, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652301, '昌吉市', 652300, 3, 87.304, 44.013, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652324, '玛纳斯县', 652300, 3, 86.214, 44.304, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652323, '呼图壁县', 652300, 3, 86.899, 44.191, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652328, '木垒哈萨克自治县', 652300, 3, 90.286, 43.835, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652327, '吉木萨尔县', 652300, 3, 89.180, 44.000, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130922, '青县', 130900, 3, 116.804, 38.583, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130902, '新华区', 130900, 3, 116.866, 38.314, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130924, '海兴县', 130900, 3, 117.497, 38.142, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130929, '献县', 130900, 3, 116.123, 38.190, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130981, '泊头市', 130900, 3, 116.578, 38.083, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130927, '南皮县', 130900, 3, 116.708, 38.039, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130984, '河间市', 130900, 3, 116.100, 38.447, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130925, '盐山县', 130900, 3, 117.231, 38.058, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130930, '孟村回族自治县', 130900, 3, 117.104, 38.053, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130982, '任丘市', 130900, 3, 116.083, 38.684, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130921, '沧县', 130900, 3, 117.007, 38.220, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130903, '运河区', 130900, 3, 116.832, 38.310, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130928, '吴桥县', 130900, 3, 116.392, 37.628, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130923, '东光县', 130900, 3, 116.537, 37.888, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130983, '黄骅市', 130900, 3, 117.330, 38.371, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130926, '肃宁县', 130900, 3, 115.830, 38.423, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445222, '揭西县', 445200, 3, 115.842, 23.431, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445281, '普宁市', 445200, 3, 116.166, 23.298, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445224, '惠来县', 445200, 3, 116.295, 23.033, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445202, '榕城区', 445200, 3, 116.367, 23.525, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445203, '揭东区', 445200, 3, 116.412, 23.566, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230603, '龙凤区', 230600, 3, 125.135, 46.562, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230602, '萨尔图区', 230600, 3, 125.115, 46.596, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230604, '让胡路区', 230600, 3, 124.871, 46.652, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230623, '林甸县', 230600, 3, 124.864, 47.172, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230621, '肇州县', 230600, 3, 125.269, 45.699, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230622, '肇源县', 230600, 3, 125.078, 45.519, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230606, '大同区', 230600, 3, 124.812, 46.040, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230624, '杜尔伯特蒙古族自治县', 230600, 3, 124.443, 46.863, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230605, '红岗区', 230600, 3, 124.891, 46.398, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450902, '玉州区', 450900, 3, 110.151, 22.628, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450921, '容县', 450900, 3, 110.558, 22.858, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450981, '北流市', 450900, 3, 110.354, 22.708, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450924, '兴业县', 450900, 3, 109.877, 22.821, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450923, '博白县', 450900, 3, 109.976, 22.273, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450922, '陆川县', 450900, 3, 110.264, 22.321, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450903, '福绵区', 450900, 3, 110.059, 22.586, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150521, '科尔沁左翼中旗', 150500, 3, 123.312, 44.127, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150523, '开鲁县', 150500, 3, 121.319, 43.601, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150525, '奈曼旗', 150500, 3, 120.662, 42.845, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150502, '科尔沁区', 150500, 3, 122.256, 43.623, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150524, '库伦旗', 150500, 3, 121.811, 42.736, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150526, '扎鲁特旗', 150500, 3, 120.912, 44.556, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150522, '科尔沁左翼后旗', 150500, 3, 122.357, 42.935, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150581, '霍林郭勒市', 150500, 3, 119.664, 45.532, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620981, '玉门市', 620900, 3, 98.494, 39.732, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620902, '肃州区', 620900, 3, 98.508, 39.745, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620922, '瓜州县', 620900, 3, 95.782, 40.521, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620923, '肃北蒙古族自治县', 620900, 3, 94.877, 39.512, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620924, '阿克塞哈萨克族自治县', 620900, 3, 94.340, 39.634, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620921, '金塔县', 620900, 3, 98.903, 39.984, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620982, '敦煌市', 620900, 3, 94.662, 40.142, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341323, '灵璧县', 341300, 3, 117.552, 33.553, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341321, '砀山县', 341300, 3, 116.367, 34.443, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341324, '泗县', 341300, 3, 117.911, 33.483, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341302, '埇桥区', 341300, 3, 116.977, 33.640, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341322, '萧县', 341300, 3, 116.947, 34.189, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320585, '太仓市', 320500, 3, 121.131, 31.458, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320571, '苏州工业园区', 320500, 3, 120.807, 31.351, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320506, '吴中区', 320500, 3, 120.632, 31.264, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320509, '吴江区', 320500, 3, 120.645, 31.139, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320581, '常熟市', 320500, 3, 120.752, 31.654, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320582, '张家港市', 320500, 3, 120.479, 31.900, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320505, '虎丘区', 320500, 3, 120.567, 31.295, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320508, '姑苏区', 320500, 3, 120.617, 31.336, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320507, '相城区', 320500, 3, 120.643, 31.369, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320583, '昆山市', 320500, 3, 120.981, 31.386, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140722, '左权县', 140700, 3, 113.379, 37.083, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140724, '昔阳县', 140700, 3, 113.707, 37.611, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140728, '平遥县', 140700, 3, 112.176, 37.190, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140702, '榆次区', 140700, 3, 112.708, 37.698, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140721, '榆社县', 140700, 3, 112.975, 37.071, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140725, '寿阳县', 140700, 3, 113.176, 37.895, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140723, '和顺县', 140700, 3, 113.570, 37.330, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140727, '祁县', 140700, 3, 112.335, 37.358, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140781, '介休市', 140700, 3, 111.917, 37.027, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140726, '太谷县', 140700, 3, 112.551, 37.421, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140729, '灵石县', 140700, 3, 111.779, 36.848, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (433126, '古丈县', 433100, 3, 109.951, 28.617, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (433101, '吉首市', 433100, 3, 109.927, 28.297, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (433127, '永顺县', 433100, 3, 109.851, 29.001, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (433123, '凤凰县', 433100, 3, 109.581, 27.958, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (433125, '保靖县', 433100, 3, 109.661, 28.700, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (433124, '花垣县', 433100, 3, 109.482, 28.572, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (433122, '泸溪县', 433100, 3, 110.220, 28.217, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (433130, '龙山县', 433100, 3, 109.444, 29.458, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540523, '桑日县', 540500, 3, 92.016, 29.259, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540528, '加查县', 540500, 3, 92.594, 29.140, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540530, '错那县', 540500, 3, 91.773, 29.237, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540522, '贡嘎县', 540500, 3, 90.984, 29.289, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540524, '琼结县', 540500, 3, 91.684, 29.025, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540529, '隆子县', 540500, 3, 91.773, 29.237, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540525, '曲松县', 540500, 3, 92.204, 29.063, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540527, '洛扎县', 540500, 3, 90.860, 28.386, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540531, '浪卡子县', 540500, 3, 90.398, 28.968, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540526, '措美县', 540500, 3, 91.434, 28.438, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540521, '扎囊县', 540500, 3, 91.337, 29.245, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540502, '乃东区', 540500, 3, 91.762, 29.225, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410482, '汝州市', 410400, 3, 112.845, 34.167, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410404, '石龙区', 410400, 3, 112.899, 33.899, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410421, '宝丰县', 410400, 3, 113.055, 33.868, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410402, '新华区', 410400, 3, 113.294, 33.737, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410481, '舞钢市', 410400, 3, 113.525, 33.308, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410423, '鲁山县', 410400, 3, 112.908, 33.739, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410422, '叶县', 410400, 3, 113.357, 33.627, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410411, '湛河区', 410400, 3, 113.321, 33.726, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410403, '卫东区', 410400, 3, 113.335, 33.735, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410425, '郏县', 410400, 3, 113.213, 33.972, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330424, '海盐县', 330400, 3, 120.946, 30.526, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330481, '海宁市', 330400, 3, 120.681, 30.511, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330411, '秀洲区', 330400, 3, 120.709, 30.765, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330421, '嘉善县', 330400, 3, 120.926, 30.831, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330482, '平湖市', 330400, 3, 121.015, 30.677, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330402, '南湖区', 330400, 3, 120.783, 30.748, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330483, '桐乡市', 330400, 3, 120.565, 30.630, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632622, '班玛县', 632600, 3, 100.737, 32.933, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632624, '达日县', 632600, 3, 99.651, 33.749, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632623, '甘德县', 632600, 3, 99.901, 33.969, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632625, '久治县', 632600, 3, 101.483, 33.429, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632626, '玛多县', 632600, 3, 98.209, 34.916, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632621, '玛沁县', 632600, 3, 100.239, 34.477, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370785, '高密市', 370700, 3, 119.756, 36.383, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370784, '安丘市', 370700, 3, 119.219, 36.478, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370782, '诸城市', 370700, 3, 119.410, 35.996, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370703, '寒亭区', 370700, 3, 119.220, 36.775, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370705, '奎文区', 370700, 3, 119.132, 36.708, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370702, '潍城区', 370700, 3, 119.025, 36.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370783, '寿光市', 370700, 3, 118.791, 36.855, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370725, '昌乐县', 370700, 3, 118.830, 36.707, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370724, '临朐县', 370700, 3, 118.543, 36.513, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370786, '昌邑市', 370700, 3, 119.399, 36.859, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370704, '坊子区', 370700, 3, 119.166, 36.654, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370781, '青州市', 370700, 3, 118.480, 36.685, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210904, '太平区', 210900, 3, 121.679, 42.011, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210902, '海州区', 210900, 3, 121.656, 42.013, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210911, '细河区', 210900, 3, 121.681, 42.025, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210903, '新邱区', 210900, 3, 121.793, 42.088, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210905, '清河门区', 210900, 3, 121.416, 41.783, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210922, '彰武县', 210900, 3, 122.539, 42.387, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210921, '阜新蒙古族自治县', 210900, 3, 121.758, 42.065, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420202, '黄石港区', 420200, 3, 115.066, 30.223, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420281, '大冶市', 420200, 3, 114.980, 30.096, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420222, '阳新县', 420200, 3, 115.215, 29.830, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420203, '西塞山区', 420200, 3, 115.110, 30.205, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420204, '下陆区', 420200, 3, 114.961, 30.174, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420205, '铁山区', 420200, 3, 114.901, 30.207, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340207, '鸠江区', 340200, 3, 118.392, 31.369, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340225, '无为县', 340200, 3, 117.902, 31.303, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340203, '弋江区', 340200, 3, 118.373, 31.312, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340222, '繁昌县', 340200, 3, 118.201, 31.081, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340208, '三山区', 340200, 3, 118.268, 31.220, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340202, '镜湖区', 340200, 3, 118.385, 31.340, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340223, '南陵县', 340200, 3, 118.334, 30.915, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340221, '芜湖县', 340200, 3, 118.576, 31.135, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411503, '平桥区', 411500, 3, 114.126, 32.101, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411521, '罗山县', 411500, 3, 114.513, 32.203, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411523, '新县', 411500, 3, 114.879, 31.644, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411502, '浉河区', 411500, 3, 114.059, 32.117, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411526, '潢川县', 411500, 3, 115.052, 32.131, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411527, '淮滨县', 411500, 3, 115.420, 32.473, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411528, '息县', 411500, 3, 114.740, 32.343, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411525, '固始县', 411500, 3, 115.654, 32.168, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411522, '光山县', 411500, 3, 114.919, 32.011, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411524, '商城县', 411500, 3, 115.407, 31.798, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420116, '黄陂区', 420100, 3, 114.376, 30.883, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420104, '硚口区', 420100, 3, 114.266, 30.571, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420102, '江岸区', 420100, 3, 114.279, 30.593, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420105, '汉阳区', 420100, 3, 114.219, 30.554, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420103, '江汉区', 420100, 3, 114.271, 30.601, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420111, '洪山区', 420100, 3, 114.344, 30.500, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420114, '蔡甸区', 420100, 3, 114.029, 30.582, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420113, '汉南区', 420100, 3, 114.084, 30.309, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420106, '武昌区', 420100, 3, 114.316, 30.554, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420115, '江夏区', 420100, 3, 114.322, 30.376, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420117, '新洲区', 420100, 3, 114.801, 30.842, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420107, '青山区', 420100, 3, 114.386, 30.640, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420112, '东西湖区', 420100, 3, 114.137, 30.620, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220702, '宁江区', 220700, 3, 124.817, 45.172, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220721, '前郭尔罗斯蒙古族自治县', 220700, 3, 124.823, 45.118, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220723, '乾安县', 220700, 3, 124.041, 45.004, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220722, '长岭县', 220700, 3, 123.967, 44.276, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220781, '扶余市', 220700, 3, 126.050, 44.988, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230523, '宝清县', 230500, 3, 132.197, 46.327, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230505, '四方台区', 230500, 3, 131.335, 46.594, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230506, '宝山区', 230500, 3, 131.402, 46.577, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230502, '尖山区', 230500, 3, 131.158, 46.646, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230522, '友谊县', 230500, 3, 131.808, 46.767, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230521, '集贤县', 230500, 3, 131.140, 46.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230524, '饶河县', 230500, 3, 134.014, 46.798, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230503, '岭东区', 230500, 3, 131.165, 46.593, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150824, '乌拉特中旗', 150800, 3, 108.514, 41.588, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150802, '临河区', 150800, 3, 107.364, 40.751, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150821, '五原县', 150800, 3, 108.268, 41.088, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150826, '杭锦后旗', 150800, 3, 107.151, 40.886, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150823, '乌拉特前旗', 150800, 3, 108.652, 40.737, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150825, '乌拉特后旗', 150800, 3, 107.075, 41.084, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150822, '磴口县', 150800, 3, 107.008, 40.331, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530723, '华坪县', 530700, 3, 101.266, 26.629, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530724, '宁蒗彝族自治县', 530700, 3, 100.852, 27.282, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530722, '永胜县', 530700, 3, 100.751, 26.684, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530721, '玉龙纳西族自治县', 530700, 3, 100.237, 26.821, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530702, '古城区', 530700, 3, 100.226, 26.877, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421321, '随县', 421300, 3, 113.300, 31.854, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421303, '曾都区', 421300, 3, 113.371, 31.716, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421381, '广水市', 421300, 3, 113.826, 31.617, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630202, '乐都区', 630200, 3, 102.402, 36.482, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630203, '平安区', 630200, 3, 102.109, 36.501, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630224, '化隆回族自治县', 630200, 3, 102.264, 36.095, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630225, '循化撒拉族自治县', 630200, 3, 102.486, 35.849, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630222, '民和回族土族自治县', 630200, 3, 102.831, 36.320, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630223, '互助土族自治县', 630200, 3, 101.959, 36.844, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360925, '靖安县', 360900, 3, 115.363, 28.861, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360983, '高安市', 360900, 3, 115.376, 28.417, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360981, '丰城市', 360900, 3, 115.771, 28.159, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360923, '上高县', 360900, 3, 114.948, 28.239, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360926, '铜鼓县', 360900, 3, 114.371, 28.521, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360982, '樟树市', 360900, 3, 115.546, 28.055, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360921, '奉新县', 360900, 3, 115.400, 28.688, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360902, '袁州区', 360900, 3, 114.425, 27.799, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360924, '宜丰县', 360900, 3, 114.804, 28.394, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360922, '万载县', 360900, 3, 114.446, 28.106, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320685, '海安市', 320600, 3, 120.467, 32.534, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320684, '海门市', 320600, 3, 121.182, 31.871, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320623, '如东县', 320600, 3, 121.185, 32.332, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320611, '港闸区', 320600, 3, 120.819, 32.032, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320682, '如皋市', 320600, 3, 120.575, 32.371, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320602, '崇川区', 320600, 3, 120.857, 32.010, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320681, '启东市', 320600, 3, 121.657, 31.808, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320612, '通州区', 320600, 3, 121.075, 32.064, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431121, '祁阳县', 431100, 3, 111.841, 26.580, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431126, '宁远县', 431100, 3, 111.946, 25.571, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431102, '零陵区', 431100, 3, 111.631, 26.222, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431103, '冷水滩区', 431100, 3, 111.592, 26.461, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431122, '东安县', 431100, 3, 111.314, 26.394, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431127, '蓝山县', 431100, 3, 112.197, 25.370, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431123, '双牌县', 431100, 3, 111.660, 25.962, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431124, '道县', 431100, 3, 111.601, 25.526, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431129, '江华瑶族自治县', 431100, 3, 111.579, 25.186, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431125, '江永县', 431100, 3, 111.344, 25.274, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431128, '新田县', 431100, 3, 112.203, 25.904, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540623, '聂荣县', 540600, 3, 92.303, 32.108, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540627, '班戈县', 540600, 3, 90.010, 31.392, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540630, '双湖县', 540600, 3, 88.838, 33.189, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540624, '安多县', 540600, 3, 91.682, 32.265, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540629, '尼玛县', 540600, 3, 87.237, 31.785, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540628, '巴青县', 540600, 3, 94.053, 31.919, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540626, '索县', 540600, 3, 93.786, 31.887, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540622, '比如县', 540600, 3, 93.680, 31.480, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540602, '色尼区', 540600, 3, 91.117, 29.647, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540621, '嘉黎县', 540600, 3, 93.233, 30.641, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540625, '申扎县', 540600, 3, 88.710, 30.931, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410325, '嵩县', 410300, 3, 112.086, 34.135, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410306, '吉利区', 410300, 3, 112.589, 34.901, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410323, '新安县', 410300, 3, 112.132, 34.729, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410304, '瀍河回族区', 410300, 3, 112.500, 34.680, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410322, '孟津县', 410300, 3, 112.445, 34.825, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410328, '洛宁县', 410300, 3, 111.653, 34.389, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410324, '栾川县', 410300, 3, 111.616, 33.786, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410327, '宜阳县', 410300, 3, 112.179, 34.515, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410326, '汝阳县', 410300, 3, 112.473, 34.154, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410311, '洛龙区', 410300, 3, 112.464, 34.619, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410302, '老城区', 410300, 3, 112.469, 34.684, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410303, '西工区', 410300, 3, 112.428, 34.660, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410305, '涧西区', 410300, 3, 112.396, 34.658, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410381, '偃师市', 410300, 3, 112.790, 34.727, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410329, '伊川县', 410300, 3, 112.426, 34.421, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330503, '南浔区', 330500, 3, 120.419, 30.850, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330502, '吴兴区', 330500, 3, 120.186, 30.857, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330523, '安吉县', 330500, 3, 119.680, 30.639, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330521, '德清县', 330500, 3, 119.977, 30.543, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330522, '长兴县', 330500, 3, 119.911, 31.027, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632524, '兴海县', 632500, 3, 99.988, 35.589, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632525, '贵南县', 632500, 3, 100.748, 35.587, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632523, '贵德县', 632500, 3, 101.435, 36.041, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632522, '同德县', 632500, 3, 100.578, 35.255, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632521, '共和县', 632500, 3, 100.620, 36.284, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421222, '通城县', 421200, 3, 113.817, 29.245, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421224, '通山县', 421200, 3, 114.483, 29.605, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421281, '赤壁市', 421200, 3, 113.901, 29.725, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421221, '嘉鱼县', 421200, 3, 113.939, 29.971, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421223, '崇阳县', 421200, 3, 114.040, 29.556, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421202, '咸安区', 421200, 3, 114.299, 29.853, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370634, '长岛县', 370600, 3, 120.737, 37.921, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370612, '牟平区', 370600, 3, 121.601, 37.387, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370602, '芝罘区', 370600, 3, 121.400, 37.541, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370684, '蓬莱市', 370600, 3, 120.759, 37.811, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370685, '招远市', 370600, 3, 120.434, 37.355, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370686, '栖霞市', 370600, 3, 120.850, 37.335, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370683, '莱州市', 370600, 3, 119.942, 37.177, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370682, '莱阳市', 370600, 3, 120.712, 36.979, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370681, '龙口市', 370600, 3, 120.478, 37.646, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370613, '莱山区', 370600, 3, 121.445, 37.511, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370687, '海阳市', 370600, 3, 121.158, 36.776, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370611, '福山区', 370600, 3, 121.268, 37.498, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654321, '布尔津县', 654300, 3, 86.875, 47.702, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654326, '吉木乃县', 654300, 3, 85.874, 47.443, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654324, '哈巴河县', 654300, 3, 86.419, 48.061, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654322, '富蕴县', 654300, 3, 89.531, 46.995, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654301, '阿勒泰市', 654300, 3, 88.132, 47.827, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654323, '福海县', 654300, 3, 87.487, 47.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654325, '青河县', 654300, 3, 90.383, 46.674, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210882, '大石桥市', 210800, 3, 122.509, 40.645, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210804, '鲅鱼圈区', 210800, 3, 122.108, 40.231, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210881, '盖州市', 210800, 3, 122.349, 40.401, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210802, '站前区', 210800, 3, 122.259, 40.673, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210803, '西市区', 210800, 3, 122.206, 40.666, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210811, '老边区', 210800, 3, 122.380, 40.680, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340322, '五河县', 340300, 3, 117.879, 33.128, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340303, '蚌山区', 340300, 3, 117.368, 32.944, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340304, '禹会区', 340300, 3, 117.342, 32.930, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340321, '怀远县', 340300, 3, 117.205, 32.970, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340302, '龙子湖区', 340300, 3, 117.394, 32.943, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340311, '淮上区', 340300, 3, 117.359, 32.965, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340323, '固镇县', 340300, 3, 117.317, 33.317, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411481, '永城市', 411400, 3, 116.450, 33.929, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411423, '宁陵县', 411400, 3, 115.314, 34.460, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411424, '柘城县', 411400, 3, 115.306, 34.091, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411425, '虞城县', 411400, 3, 115.841, 34.403, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411403, '睢阳区', 411400, 3, 115.653, 34.388, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411402, '梁园区', 411400, 3, 115.614, 34.444, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411426, '夏邑县', 411400, 3, 116.131, 34.238, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411421, '民权县', 411400, 3, 115.180, 34.648, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411422, '睢县', 411400, 3, 115.072, 34.446, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220621, '抚松县', 220600, 3, 127.450, 42.221, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220605, '江源区', 220600, 3, 126.591, 42.057, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220681, '临江市', 220600, 3, 126.918, 41.812, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220622, '靖宇县', 220600, 3, 126.814, 42.389, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220623, '长白朝鲜族自治县', 220600, 3, 128.201, 41.420, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220602, '浑江区', 220600, 3, 126.416, 41.945, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530822, '墨江哈尼族自治县', 530800, 3, 101.692, 23.432, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530829, '西盟佤族自治县', 530800, 3, 99.590, 22.645, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530802, '思茅区', 530800, 3, 100.977, 22.787, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530821, '宁洱哈尼族彝族自治县', 530800, 3, 101.045, 23.062, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530825, '镇沅彝族哈尼族拉祜族自治县', 530800, 3, 101.109, 24.004, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530823, '景东彝族自治县', 530800, 3, 100.834, 24.447, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530827, '孟连傣族拉祜族佤族自治县', 530800, 3, 99.584, 22.329, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530826, '江城哈尼族彝族自治县', 530800, 3, 101.862, 22.586, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530828, '澜沧拉祜族自治县', 530800, 3, 99.932, 22.556, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530824, '景谷傣族彝族自治县', 530800, 3, 100.703, 23.497, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230406, '东山区', 230400, 3, 130.317, 47.339, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230405, '兴安区', 230400, 3, 130.239, 47.253, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230403, '工农区', 230400, 3, 130.275, 47.319, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230404, '南山区', 230400, 3, 130.287, 47.315, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230402, '向阳区', 230400, 3, 130.294, 47.342, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230422, '绥滨县', 230400, 3, 131.853, 47.289, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230421, '萝北县', 230400, 3, 130.829, 47.577, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230407, '兴山区', 230400, 3, 130.304, 47.358, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630121, '大通回族土族自治县', 630100, 3, 101.686, 36.927, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630104, '城西区', 630100, 3, 101.766, 36.628, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630105, '城北区', 630100, 3, 101.766, 36.650, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630103, '城中区', 630100, 3, 101.785, 36.621, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630123, '湟源县', 630100, 3, 101.256, 36.682, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630122, '湟中县', 630100, 3, 101.572, 36.501, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630102, '城东区', 630100, 3, 101.804, 36.600, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150784, '额尔古纳市', 150700, 3, 120.181, 50.243, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150781, '满洲里市', 150700, 3, 117.379, 49.598, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150721, '阿荣旗', 150700, 3, 123.459, 48.127, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150702, '海拉尔区', 150700, 3, 119.736, 49.212, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150703, '扎赉诺尔区', 150700, 3, 117.670, 49.510, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150783, '扎兰屯市', 150700, 3, 122.737, 48.014, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150727, '新巴尔虎右旗', 150700, 3, 116.824, 48.672, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150725, '陈巴尔虎旗', 150700, 3, 119.424, 49.329, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150723, '鄂伦春自治旗', 150700, 3, 123.726, 50.592, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150722, '莫力达瓦达斡尔族自治旗', 150700, 3, 124.519, 48.478, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150726, '新巴尔虎左旗', 150700, 3, 118.270, 48.218, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150785, '根河市', 150700, 3, 121.520, 50.780, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150724, '鄂温克族自治旗', 150700, 3, 119.755, 49.147, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150782, '牙克石市', 150700, 3, 120.736, 49.293, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653201, '和田市', 653200, 3, 79.914, 37.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653225, '策勒县', 653200, 3, 80.806, 36.998, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653222, '墨玉县', 653200, 3, 79.729, 37.277, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653226, '于田县', 653200, 3, 81.677, 36.857, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653223, '皮山县', 653200, 3, 78.284, 37.621, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653227, '民丰县', 653200, 3, 82.696, 37.064, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653224, '洛浦县', 653200, 3, 80.189, 37.074, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653221, '和田县', 653200, 3, 79.819, 37.120, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320723, '灌云县', 320700, 3, 119.239, 34.284, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320706, '海州区', 320700, 3, 119.194, 34.607, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320703, '连云区', 320700, 3, 119.339, 34.760, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320722, '东海县', 320700, 3, 118.753, 34.542, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320724, '灌南县', 320700, 3, 119.316, 34.087, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320707, '赣榆区', 320700, 3, 119.173, 34.841, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410212, '祥符区', 410200, 3, 114.441, 34.757, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410203, '顺河回族区', 410200, 3, 114.365, 34.800, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410222, '通许县', 410200, 3, 114.467, 34.480, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410204, '鼓楼区', 410200, 3, 114.348, 34.789, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410225, '兰考县', 410200, 3, 114.821, 34.822, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410205, '禹王台区', 410200, 3, 114.348, 34.777, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410202, '龙亭区', 410200, 3, 114.355, 34.816, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410223, '尉氏县', 410200, 3, 114.193, 34.411, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410221, '杞县', 410200, 3, 114.783, 34.549, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140921, '定襄县', 140900, 3, 112.957, 38.474, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140924, '繁峙县', 140900, 3, 113.266, 39.189, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140932, '偏关县', 140900, 3, 111.509, 39.436, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140922, '五台县', 140900, 3, 113.255, 38.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140926, '静乐县', 140900, 3, 111.939, 38.359, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140928, '五寨县', 140900, 3, 111.847, 38.911, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140927, '神池县', 140900, 3, 112.211, 39.091, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140929, '岢岚县', 140900, 3, 111.573, 38.704, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140902, '忻府区', 140900, 3, 112.746, 38.404, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140931, '保德县', 140900, 3, 111.087, 39.022, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140925, '宁武县', 140900, 3, 112.305, 39.002, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140981, '原平市', 140900, 3, 112.711, 38.731, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140930, '河曲县', 140900, 3, 111.138, 39.384, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140923, '代县', 140900, 3, 112.960, 39.067, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360881, '井冈山市', 360800, 3, 114.289, 26.748, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360822, '吉水县', 360800, 3, 115.136, 27.230, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360827, '遂川县', 360800, 3, 114.521, 26.314, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360802, '吉州区', 360800, 3, 114.995, 27.144, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360821, '吉安县', 360800, 3, 114.908, 27.040, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360823, '峡江县', 360800, 3, 115.317, 27.583, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360828, '万安县', 360800, 3, 114.786, 26.458, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360824, '新干县', 360800, 3, 115.387, 27.740, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360830, '永新县', 360800, 3, 114.243, 26.945, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360825, '永丰县', 360800, 3, 115.444, 27.319, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360803, '青原区', 360800, 3, 115.015, 27.082, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360829, '安福县', 360800, 3, 114.620, 27.393, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360826, '泰和县', 360800, 3, 114.909, 26.790, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (232722, '塔河县', 232700, 3, 124.710, 52.334, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (232721, '呼玛县', 232700, 3, 126.665, 51.726, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (232718, '加格达奇区', 232700, 3, 124.140, 50.409, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (232701, '漠河市', 232700, 3, 122.539, 52.972, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341524, '金寨县', 341500, 3, 115.934, 31.727, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341525, '霍山县', 341500, 3, 116.333, 31.393, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341504, '叶集区', 341500, 3, 115.909, 31.849, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341523, '舒城县', 341500, 3, 116.949, 31.462, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341503, '裕安区', 341500, 3, 116.480, 31.738, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341522, '霍邱县', 341500, 3, 116.278, 32.353, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341502, '金安区', 341500, 3, 116.540, 31.749, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431003, '苏仙区', 431000, 3, 113.042, 25.800, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431021, '桂阳县', 431000, 3, 112.734, 25.754, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431024, '嘉禾县', 431000, 3, 112.369, 25.588, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431081, '资兴市', 431000, 3, 113.236, 25.976, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431027, '桂东县', 431000, 3, 113.945, 26.078, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431002, '北湖区', 431000, 3, 113.011, 25.784, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431026, '汝城县', 431000, 3, 113.685, 25.533, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431022, '宜章县', 431000, 3, 112.949, 25.400, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431023, '永兴县', 431000, 3, 113.117, 26.127, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431025, '临武县', 431000, 3, 112.563, 25.276, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431028, '安仁县', 431000, 3, 113.269, 26.709, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431381, '冷水江市', 431300, 3, 111.436, 27.686, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431302, '娄星区', 431300, 3, 112.002, 27.730, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431321, '双峰县', 431300, 3, 112.175, 27.457, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431322, '新化县', 431300, 3, 111.327, 27.727, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431382, '涟源市', 431300, 3, 111.664, 27.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620321, '永昌县', 620300, 3, 101.985, 38.243, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620302, '金川区', 620300, 3, 102.194, 38.521, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511325, '西充县', 511300, 3, 105.901, 30.996, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511323, '蓬安县', 511300, 3, 106.412, 31.029, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511381, '阆中市', 511300, 3, 106.005, 31.558, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511303, '高坪区', 511300, 3, 106.119, 30.782, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511322, '营山县', 511300, 3, 106.565, 31.076, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511321, '南部县', 511300, 3, 106.037, 31.347, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511324, '仪陇县', 511300, 3, 106.303, 31.272, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511304, '嘉陵区', 511300, 3, 106.072, 30.759, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511302, '顺庆区', 511300, 3, 106.092, 30.796, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410611, '淇滨区', 410600, 3, 114.299, 35.741, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410602, '鹤山区', 410600, 3, 114.163, 35.955, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410622, '淇县', 410600, 3, 114.198, 35.608, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410603, '山城区', 410600, 3, 114.184, 35.898, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410621, '浚县', 410600, 3, 114.551, 35.676, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330624, '新昌县', 330600, 3, 120.904, 29.500, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330681, '诸暨市', 330600, 3, 120.247, 29.709, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330602, '越城区', 330600, 3, 120.583, 29.988, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330604, '上虞区', 330600, 3, 120.868, 30.033, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330683, '嵊州市', 330600, 3, 120.831, 29.561, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330603, '柯桥区', 330600, 3, 120.495, 30.082, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370523, '广饶县', 370500, 3, 118.407, 37.054, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370503, '河口区', 370500, 3, 118.526, 37.886, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370522, '利津县', 370500, 3, 118.255, 37.490, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370502, '东营区', 370500, 3, 118.582, 37.449, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370505, '垦利区', 370500, 3, 118.675, 37.435, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210702, '古塔区', 210700, 3, 121.128, 41.117, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210711, '太和区', 210700, 3, 121.104, 41.109, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210727, '义县', 210700, 3, 121.239, 41.533, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210782, '北镇市', 210700, 3, 121.796, 41.599, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210726, '黑山县', 210700, 3, 122.123, 41.666, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210781, '凌海市', 210700, 3, 121.358, 41.173, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210703, '凌河区', 210700, 3, 121.151, 41.115, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340421, '凤台县', 340400, 3, 116.711, 32.709, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340402, '大通区', 340400, 3, 117.053, 32.632, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340406, '潘集区', 340400, 3, 116.835, 32.772, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340405, '八公山区', 340400, 3, 116.833, 32.631, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340404, '谢家集区', 340400, 3, 116.859, 32.600, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340422, '寿县', 340400, 3, 116.787, 32.573, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340403, '田家庵区', 340400, 3, 117.017, 32.647, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430211, '天元区', 430200, 3, 113.082, 27.827, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430223, '攸县', 430200, 3, 113.396, 27.015, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430204, '石峰区', 430200, 3, 113.118, 27.875, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430212, '渌口区', 430200, 3, 113.140, 27.703, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430225, '炎陵县', 430200, 3, 113.773, 26.490, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430203, '芦淞区', 430200, 3, 113.153, 27.785, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430281, '醴陵市', 430200, 3, 113.497, 27.646, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430202, '荷塘区', 430200, 3, 113.173, 27.856, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430224, '茶陵县', 430200, 3, 113.539, 26.777, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350982, '福鼎市', 350900, 3, 120.217, 27.324, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350923, '屏南县', 350900, 3, 118.986, 26.908, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350921, '霞浦县', 350900, 3, 120.006, 26.885, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350925, '周宁县', 350900, 3, 119.339, 27.105, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350924, '寿宁县', 350900, 3, 119.515, 27.454, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350926, '柘荣县', 350900, 3, 119.901, 27.234, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350902, '蕉城区', 350900, 3, 119.526, 26.661, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350981, '福安市', 350900, 3, 119.652, 27.087, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350922, '古田县', 350900, 3, 118.746, 26.578, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650521, '巴里坤哈萨克自治县', 650500, 3, 93.017, 43.599, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650522, '伊吾县', 650500, 3, 94.697, 43.255, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650502, '伊州区', 650500, 3, 93.515, 42.827, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411721, '西平县', 411700, 3, 114.022, 33.388, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411727, '汝南县', 411700, 3, 114.362, 33.007, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411729, '新蔡县', 411700, 3, 114.921, 32.811, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411726, '泌阳县', 411700, 3, 113.327, 32.724, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411728, '遂平县', 411700, 3, 114.013, 33.146, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411724, '正阳县', 411700, 3, 114.393, 32.606, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411702, '驿城区', 411700, 3, 113.994, 32.973, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411725, '确山县', 411700, 3, 114.026, 32.802, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411723, '平舆县', 411700, 3, 114.619, 32.963, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411722, '上蔡县', 411700, 3, 114.264, 33.262, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130632, '安新县', 130600, 3, 115.936, 38.935, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130683, '安国市', 130600, 3, 115.327, 38.418, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130637, '博野县', 130600, 3, 115.464, 38.457, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130628, '高阳县', 130600, 3, 115.779, 38.700, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130608, '清苑区', 130600, 3, 115.490, 38.765, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130682, '定州市', 130600, 3, 114.990, 38.516, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130633, '易县', 130600, 3, 115.498, 39.349, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130629, '容城县', 130600, 3, 115.862, 39.043, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130609, '徐水区', 130600, 3, 115.624, 39.026, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130627, '唐县', 130600, 3, 114.983, 38.748, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130684, '高碑店市', 130600, 3, 115.874, 39.327, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130634, '曲阳县', 130600, 3, 114.745, 38.622, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130681, '涿州市', 130600, 3, 115.974, 39.485, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130602, '竞秀区', 130600, 3, 115.459, 38.878, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130606, '莲池区', 130600, 3, 115.497, 38.883, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130636, '顺平县', 130600, 3, 115.135, 38.837, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130638, '雄县', 130600, 3, 116.109, 38.995, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130626, '定兴县', 130600, 3, 115.808, 39.263, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130624, '阜平县', 130600, 3, 114.195, 38.849, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130631, '望都县', 130600, 3, 115.155, 38.696, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130635, '蠡县', 130600, 3, 115.584, 38.488, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130630, '涞源县', 130600, 3, 114.694, 39.360, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130623, '涞水县', 130600, 3, 115.714, 39.394, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130607, '满城区', 130600, 3, 115.322, 38.949, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533124, '陇川县', 533100, 3, 97.792, 24.183, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533103, '芒市', 533100, 3, 98.588, 24.434, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533102, '瑞丽市', 533100, 3, 97.855, 24.018, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533123, '盈江县', 533100, 3, 97.944, 24.691, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533122, '梁河县', 533100, 3, 98.297, 24.804, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220503, '二道江区', 220500, 3, 126.043, 41.774, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220524, '柳河县', 220500, 3, 125.745, 42.285, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220521, '通化县', 220500, 3, 125.759, 41.680, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220582, '集安市', 220500, 3, 126.194, 41.125, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220581, '梅河口市', 220500, 3, 125.684, 42.530, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220502, '东昌区', 220500, 3, 125.955, 41.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220523, '辉南县', 220500, 3, 126.047, 42.685, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652801, '库尔勒市', 652800, 3, 86.175, 41.726, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652827, '和静县', 652800, 3, 86.384, 42.324, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652829, '博湖县', 652800, 3, 86.632, 41.980, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652822, '轮台县', 652800, 3, 84.252, 41.778, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652826, '焉耆回族自治县', 652800, 3, 86.574, 42.060, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652825, '且末县', 652800, 3, 85.532, 38.137, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652828, '和硕县', 652800, 3, 86.864, 42.268, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652823, '尉犁县', 652800, 3, 86.261, 41.344, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652824, '若羌县', 652800, 3, 88.167, 39.023, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371728, '东明县', 371700, 3, 115.090, 35.289, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371725, '郓城县', 371700, 3, 115.944, 35.600, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371722, '单县', 371700, 3, 116.107, 34.779, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371724, '巨野县', 371700, 3, 116.065, 35.387, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371723, '成武县', 371700, 3, 115.890, 34.952, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371703, '定陶区', 371700, 3, 115.570, 35.073, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371721, '曹县', 371700, 3, 115.542, 34.826, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371702, '牡丹区', 371700, 3, 115.418, 35.253, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371726, '鄄城县', 371700, 3, 115.510, 35.563, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610631, '黄龙县', 610600, 3, 109.840, 35.584, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610622, '延川县', 610600, 3, 110.194, 36.878, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610623, '子长县', 610600, 3, 109.675, 37.143, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610629, '洛川县', 610600, 3, 109.432, 35.762, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610602, '宝塔区', 610600, 3, 109.493, 36.591, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610621, '延长县', 610600, 3, 110.012, 36.579, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610625, '志丹县', 610600, 3, 108.768, 36.822, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610628, '富县', 610600, 3, 109.380, 35.988, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610626, '吴起县', 610600, 3, 108.176, 36.927, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610603, '安塞区', 610600, 3, 109.329, 36.864, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610632, '黄陵县', 610600, 3, 109.263, 35.579, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610627, '甘泉县', 610600, 3, 109.351, 36.277, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610630, '宜川县', 610600, 3, 110.169, 36.050, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230304, '滴道区', 230300, 3, 130.844, 45.349, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230305, '梨树区', 230300, 3, 130.697, 45.092, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230303, '恒山区', 230300, 3, 130.905, 45.211, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230302, '鸡冠区', 230300, 3, 130.981, 45.304, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230307, '麻山区', 230300, 3, 130.478, 45.212, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230382, '密山市', 230300, 3, 131.847, 45.530, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230381, '虎林市', 230300, 3, 132.937, 45.763, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230321, '鸡东县', 230300, 3, 131.124, 45.260, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230306, '城子河区', 230300, 3, 131.011, 45.337, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140423, '襄垣县', 140400, 3, 113.051, 36.536, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140425, '平顺县', 140400, 3, 113.436, 36.200, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140405, '屯留区', 140400, 3, 112.892, 36.316, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140406, '潞城区', 140400, 3, 113.229, 36.334, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140426, '黎城县', 140400, 3, 113.387, 36.504, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140431, '沁源县', 140400, 3, 112.337, 36.500, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140427, '壶关县', 140400, 3, 113.207, 36.115, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140429, '武乡县', 140400, 3, 112.865, 36.838, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140404, '上党区', 140400, 3, 113.111, 36.187, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140430, '沁县', 140400, 3, 112.699, 36.756, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140403, '潞州区', 140400, 3, 113.116, 36.195, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140428, '长子县', 140400, 3, 112.878, 36.122, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150204, '青山区', 150200, 3, 109.902, 40.643, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150222, '固阳县', 150200, 3, 110.061, 41.034, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150202, '东河区', 150200, 3, 110.044, 40.576, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150205, '石拐区', 150200, 3, 110.061, 40.677, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150203, '昆都仑区', 150200, 3, 109.838, 40.642, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150207, '九原区', 150200, 3, 109.968, 40.601, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150206, '白云鄂博矿区', 150200, 3, 109.840, 40.657, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150223, '达尔罕茂明安联合旗', 150200, 3, 110.433, 41.699, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150221, '土默特右旗', 150200, 3, 110.524, 40.569, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152531, '多伦县', 152500, 3, 116.486, 42.204, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152522, '阿巴嘎旗', 152500, 3, 114.950, 44.023, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152501, '二连浩特市', 152500, 3, 111.978, 43.653, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152528, '镶黄旗', 152500, 3, 113.847, 42.232, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152524, '苏尼特右旗', 152500, 3, 112.642, 42.743, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152526, '西乌珠穆沁旗', 152500, 3, 117.609, 44.588, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152525, '东乌珠穆沁旗', 152500, 3, 116.974, 45.498, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152529, '正镶白旗', 152500, 3, 115.030, 42.287, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152530, '正蓝旗', 152500, 3, 115.992, 42.242, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152502, '锡林浩特市', 152500, 3, 116.086, 43.933, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152527, '太仆寺旗', 152500, 3, 115.283, 41.877, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152523, '苏尼特左旗', 152500, 3, 113.667, 43.860, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320813, '洪泽区', 320800, 3, 118.873, 33.294, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320804, '淮阴区', 320800, 3, 119.035, 33.632, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320830, '盱眙县', 320800, 3, 118.544, 33.012, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320803, '淮安区', 320800, 3, 119.141, 33.503, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320826, '涟水县', 320800, 3, 119.260, 33.781, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320812, '清江浦区', 320800, 3, 119.015, 33.610, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320831, '金湖县', 320800, 3, 119.021, 33.025, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620403, '平川区', 620400, 3, 104.825, 36.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620421, '靖远县', 620400, 3, 104.677, 36.571, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620402, '白银区', 620400, 3, 104.149, 36.535, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620422, '会宁县', 620400, 3, 105.053, 35.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620423, '景泰县', 620400, 3, 104.063, 37.184, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360722, '信丰县', 360700, 3, 114.923, 25.387, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360730, '宁都县', 360700, 3, 116.014, 26.471, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360731, '于都县', 360700, 3, 115.416, 25.952, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360704, '赣县区', 360700, 3, 115.012, 25.861, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360729, '全南县', 360700, 3, 114.530, 24.742, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360727, '龙南县', 360700, 3, 114.790, 24.911, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360725, '崇义县', 360700, 3, 114.308, 25.682, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360726, '安远县', 360700, 3, 115.394, 25.137, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360733, '会昌县', 360700, 3, 115.786, 25.600, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360728, '定南县', 360700, 3, 115.028, 24.784, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360723, '大余县', 360700, 3, 114.362, 25.401, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360781, '瑞金市', 360700, 3, 116.027, 25.886, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360732, '兴国县', 360700, 3, 115.363, 26.338, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360702, '章贡区', 360700, 3, 114.921, 25.818, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360734, '寻乌县', 360700, 3, 115.647, 24.963, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360703, '南康区', 360700, 3, 114.935, 25.832, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360724, '上犹县', 360700, 3, 114.551, 25.785, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360735, '石城县', 360700, 3, 116.347, 26.315, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341621, '涡阳县', 341600, 3, 116.216, 33.493, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341602, '谯城区', 341600, 3, 115.779, 33.876, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341622, '蒙城县', 341600, 3, 116.564, 33.266, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341623, '利辛县', 341600, 3, 116.209, 33.145, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431227, '新晃侗族自治县', 431200, 3, 109.175, 27.353, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431230, '通道侗族自治县', 431200, 3, 109.784, 26.158, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431229, '靖州苗族侗族自治县', 431200, 3, 109.696, 26.575, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431222, '沅陵县', 431200, 3, 110.394, 28.453, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431224, '溆浦县', 431200, 3, 110.595, 27.908, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431221, '中方县', 431200, 3, 109.945, 27.440, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431202, '鹤城区', 431200, 3, 110.040, 27.579, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431226, '麻阳苗族自治县', 431200, 3, 109.803, 27.866, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431281, '洪江市', 431200, 3, 109.837, 27.209, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431223, '辰溪县', 431200, 3, 110.184, 28.006, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431228, '芷江侗族自治县', 431200, 3, 109.685, 27.443, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431225, '会同县', 431200, 3, 109.736, 26.887, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410502, '文峰区', 410500, 3, 114.357, 36.090, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410581, '林州市', 410500, 3, 113.820, 36.083, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410527, '内黄县', 410500, 3, 114.901, 35.972, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410506, '龙安区', 410500, 3, 114.324, 36.096, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410523, '汤阴县', 410500, 3, 114.358, 35.925, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410526, '滑县', 410500, 3, 114.519, 35.575, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410522, '安阳县', 410500, 3, 114.130, 36.131, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410505, '殷都区', 410500, 3, 114.303, 36.110, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410503, '北关区', 410500, 3, 114.356, 36.107, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330783, '东阳市', 330700, 3, 120.242, 29.290, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330727, '磐安县', 330700, 3, 120.450, 29.054, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330726, '浦江县', 330700, 3, 119.892, 29.452, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330782, '义乌市', 330700, 3, 120.075, 29.307, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330703, '金东区', 330700, 3, 119.693, 29.099, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330781, '兰溪市', 330700, 3, 119.461, 29.209, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330784, '永康市', 330700, 3, 119.647, 29.079, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330702, '婺城区', 330700, 3, 119.572, 29.086, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330723, '武义县', 330700, 3, 119.816, 28.893, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370402, '市中区', 370400, 3, 117.556, 34.864, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370481, '滕州市', 370400, 3, 117.166, 35.114, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370403, '薛城区', 370400, 3, 117.263, 34.795, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370406, '山亭区', 370400, 3, 117.462, 35.100, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370404, '峄城区', 370400, 3, 117.591, 34.773, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370405, '台儿庄区', 370400, 3, 117.734, 34.563, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420303, '张湾区', 420300, 3, 110.769, 32.652, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420323, '竹山县', 420300, 3, 110.229, 32.225, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420302, '茅箭区', 420300, 3, 110.814, 32.592, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420322, '郧西县', 420300, 3, 110.426, 32.993, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420324, '竹溪县', 420300, 3, 109.715, 32.318, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420381, '丹江口市', 420300, 3, 111.513, 32.540, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420304, '郧阳区', 420300, 3, 110.812, 32.835, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420325, '房县', 420300, 3, 110.727, 32.040, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522328, '安龙县', 522300, 3, 105.443, 25.099, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522301, '兴义市', 522300, 3, 104.895, 25.092, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522326, '望谟县', 522300, 3, 106.094, 25.171, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522323, '普安县', 522300, 3, 104.953, 25.784, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522324, '晴隆县', 522300, 3, 105.219, 25.835, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522302, '兴仁市', 522300, 3, 105.186, 25.435, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522327, '册亨县', 522300, 3, 105.812, 24.984, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522325, '贞丰县', 522300, 3, 105.650, 25.386, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340522, '含山县', 340500, 3, 118.101, 31.736, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340523, '和县', 340500, 3, 118.351, 31.742, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340504, '雨山区', 340500, 3, 118.499, 31.682, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340521, '当涂县', 340500, 3, 118.498, 31.571, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340506, '博望区', 340500, 3, 118.845, 31.558, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340503, '花山区', 340500, 3, 118.493, 31.720, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210682, '凤城市', 210600, 3, 124.067, 40.452, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210603, '振兴区', 210600, 3, 124.360, 40.105, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210602, '元宝区', 210600, 3, 124.396, 40.137, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210604, '振安区', 210600, 3, 124.428, 40.158, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210681, '东港市', 210600, 3, 124.153, 39.863, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210624, '宽甸满族自治县', 210600, 3, 124.784, 40.731, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430103, '天心区', 430100, 3, 112.990, 28.113, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430181, '浏阳市', 430100, 3, 113.643, 28.163, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430102, '芙蓉区', 430100, 3, 113.033, 28.185, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430111, '雨花区', 430100, 3, 113.038, 28.138, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430112, '望城区', 430100, 3, 112.820, 28.347, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430104, '岳麓区', 430100, 3, 112.931, 28.235, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430121, '长沙县', 430100, 3, 113.081, 28.246, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430182, '宁乡市', 430100, 3, 112.552, 28.277, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430105, '开福区', 430100, 3, 112.984, 28.206, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350803, '永定区', 350800, 3, 116.732, 24.724, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350825, '连城县', 350800, 3, 116.754, 25.711, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350802, '新罗区', 350800, 3, 117.037, 25.098, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350821, '长汀县', 350800, 3, 116.358, 25.834, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350824, '武平县', 350800, 3, 116.100, 25.095, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350881, '漳平市', 350800, 3, 117.420, 25.290, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350823, '上杭县', 350800, 3, 116.420, 25.050, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650402, '高昌区', 650400, 3, 89.182, 42.948, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650421, '鄯善县', 650400, 3, 90.213, 42.869, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650422, '托克逊县', 650400, 3, 88.639, 42.792, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510121, '金堂县', 510100, 3, 104.412, 30.862, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510129, '大邑县', 510100, 3, 103.512, 30.572, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510183, '邛崃市', 510100, 3, 103.464, 30.410, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510115, '温江区', 510100, 3, 103.837, 30.690, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510112, '龙泉驿区', 510100, 3, 104.275, 30.557, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510131, '蒲江县', 510100, 3, 103.506, 30.197, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510181, '都江堰市', 510100, 3, 103.650, 30.976, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510113, '青白江区', 510100, 3, 104.251, 30.879, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510106, '金牛区', 510100, 3, 104.117, 30.763, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510108, '成华区', 510100, 3, 104.101, 30.660, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510117, '郫都区', 510100, 3, 103.901, 30.796, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510114, '新都区', 510100, 3, 104.159, 30.823, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510105, '青羊区', 510100, 3, 104.062, 30.674, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510107, '武侯区', 510100, 3, 104.043, 30.642, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510116, '双流区', 510100, 3, 103.952, 30.333, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510104, '锦江区', 510100, 3, 104.081, 30.658, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510182, '彭州市', 510100, 3, 103.958, 30.990, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510132, '新津县', 510100, 3, 103.811, 30.410, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510185, '简阳市', 510100, 3, 104.547, 30.411, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510184, '崇州市', 510100, 3, 103.673, 30.630, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130523, '内丘县', 130500, 3, 114.512, 37.287, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130532, '平乡县', 130500, 3, 115.030, 37.063, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130522, '临城县', 130500, 3, 114.499, 37.444, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130534, '清河县', 130500, 3, 115.668, 37.040, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130529, '巨鹿县', 130500, 3, 115.037, 37.221, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130535, '临西县', 130500, 3, 115.501, 36.871, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130524, '柏乡县', 130500, 3, 114.693, 37.482, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130582, '沙河市', 130500, 3, 114.503, 36.855, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130581, '南宫市', 130500, 3, 115.409, 37.359, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130502, '桥东区', 130500, 3, 114.492, 37.062, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130533, '威县', 130500, 3, 115.267, 36.975, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130531, '广宗县', 130500, 3, 115.143, 37.075, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130528, '宁晋县', 130500, 3, 114.919, 37.620, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130526, '任县', 130500, 3, 114.672, 37.121, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130521, '邢台县', 130500, 3, 114.507, 37.073, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130530, '新河县', 130500, 3, 115.242, 37.529, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130527, '南和县', 130500, 3, 114.684, 37.005, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130525, '隆尧县', 130500, 3, 114.770, 37.350, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130503, '桥西区', 130500, 3, 114.468, 37.060, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411623, '商水县', 411600, 3, 114.612, 33.542, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411681, '项城市', 411600, 3, 114.875, 33.466, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411622, '西华县', 411600, 3, 114.425, 33.809, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411627, '太康县', 411600, 3, 114.838, 34.064, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411625, '郸城县', 411600, 3, 115.177, 33.645, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411628, '鹿邑县', 411600, 3, 115.484, 33.860, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411624, '沈丘县', 411600, 3, 115.099, 33.409, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411621, '扶沟县', 411600, 3, 114.395, 34.060, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411602, '川汇区', 411600, 3, 114.651, 33.648, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411626, '淮阳县', 411600, 3, 114.886, 33.732, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371681, '邹平市', 371600, 3, 117.743, 36.863, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371621, '惠民县', 371600, 3, 117.510, 37.490, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371625, '博兴县', 371600, 3, 118.132, 37.150, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371603, '沾化区', 371600, 3, 118.099, 37.699, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371623, '无棣县', 371600, 3, 117.626, 37.770, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371622, '阳信县', 371600, 3, 117.578, 37.641, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371602, '滨城区', 371600, 3, 118.019, 37.431, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610725, '勉县', 610700, 3, 106.673, 33.154, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610726, '宁强县', 610700, 3, 106.257, 32.830, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610728, '镇巴县', 610700, 3, 107.895, 32.537, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610703, '南郑区', 610700, 3, 106.936, 32.999, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610723, '洋县', 610700, 3, 107.546, 33.223, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610722, '城固县', 610700, 3, 107.334, 33.157, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610729, '留坝县', 610700, 3, 106.921, 33.618, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610724, '西乡县', 610700, 3, 107.767, 32.983, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610727, '略阳县', 610700, 3, 106.157, 33.327, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610730, '佛坪县', 610700, 3, 107.991, 33.524, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610702, '汉台区', 610700, 3, 107.032, 33.068, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652702, '阿拉山口市', 652700, 3, 82.559, 45.172, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652722, '精河县', 652700, 3, 82.894, 44.600, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652723, '温泉县', 652700, 3, 81.025, 44.969, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652701, '博乐市', 652700, 3, 82.051, 44.854, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220422, '东辽县', 220400, 3, 124.992, 42.926, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220403, '西安区', 220400, 3, 125.149, 42.927, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220421, '东丰县', 220400, 3, 125.531, 42.677, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220402, '龙山区', 220400, 3, 125.136, 42.902, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230230, '克东县', 230200, 3, 126.249, 48.042, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230229, '克山县', 230200, 3, 125.876, 48.037, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230206, '富拉尔基区', 230200, 3, 123.629, 47.209, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230223, '依安县', 230200, 3, 125.306, 47.894, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230208, '梅里斯达斡尔族区', 230200, 3, 123.753, 47.310, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230224, '泰来县', 230200, 3, 123.417, 46.394, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230203, '建华区', 230200, 3, 123.955, 47.354, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230231, '拜泉县', 230200, 3, 126.100, 47.596, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230281, '讷河市', 230200, 3, 124.884, 48.484, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230204, '铁锋区', 230200, 3, 123.978, 47.341, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230205, '昂昂溪区', 230200, 3, 123.822, 47.155, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230221, '龙江县', 230200, 3, 123.205, 47.339, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230227, '富裕县', 230200, 3, 124.474, 47.775, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230202, '龙沙区', 230200, 3, 123.958, 47.317, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230225, '甘南县', 230200, 3, 123.507, 47.922, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230207, '碾子山区', 230200, 3, 122.888, 47.517, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320902, '亭湖区', 320900, 3, 120.197, 33.391, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320922, '滨海县', 320900, 3, 119.821, 33.990, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320981, '东台市', 320900, 3, 120.320, 32.868, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320924, '射阳县', 320900, 3, 120.258, 33.775, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320904, '大丰区', 320900, 3, 120.501, 33.200, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320921, '响水县', 320900, 3, 119.578, 34.199, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320925, '建湖县', 320900, 3, 119.799, 33.464, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320923, '阜宁县', 320900, 3, 119.803, 33.759, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320903, '盐都区', 320900, 3, 120.154, 33.338, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140303, '矿区', 140300, 3, 113.581, 37.857, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140321, '平定县', 140300, 3, 113.658, 37.787, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140302, '城区', 140300, 3, 113.601, 37.847, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140322, '盂县', 140300, 3, 113.412, 38.086, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140311, '郊区', 140300, 3, 113.594, 37.945, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150103, '回民区', 150100, 3, 111.624, 40.809, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150102, '新城区', 150100, 3, 111.666, 40.858, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150125, '武川县', 150100, 3, 111.451, 41.096, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150124, '清水河县', 150100, 3, 111.648, 39.921, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150105, '赛罕区', 150100, 3, 111.702, 40.792, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150104, '玉泉区', 150100, 3, 111.674, 40.754, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150123, '和林格尔县', 150100, 3, 111.822, 40.379, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150122, '托克托县', 150100, 3, 111.194, 40.277, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150121, '土默特左旗', 150100, 3, 111.164, 40.730, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341723, '青阳县', 341700, 3, 117.847, 30.639, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341721, '东至县', 341700, 3, 117.028, 30.111, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341702, '贵池区', 341700, 3, 117.567, 30.687, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341722, '石台县', 341700, 3, 117.486, 30.210, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620524, '武山县', 620500, 3, 104.891, 34.721, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620502, '秦州区', 620500, 3, 105.724, 34.581, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620503, '麦积区', 620500, 3, 105.890, 34.570, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620521, '清水县', 620500, 3, 106.137, 34.750, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620525, '张家川回族自治县', 620500, 3, 106.205, 34.988, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620522, '秦安县', 620500, 3, 105.675, 34.859, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620523, '甘谷县', 620500, 3, 105.341, 34.745, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360603, '余江区', 360600, 3, 116.823, 28.206, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360681, '贵溪市', 360600, 3, 117.245, 28.293, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360602, '月湖区', 360600, 3, 117.037, 28.239, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410822, '博爱县', 410800, 3, 113.069, 35.166, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410825, '温县', 410800, 3, 113.081, 34.940, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410882, '沁阳市', 410800, 3, 112.943, 35.091, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410804, '马村区', 410800, 3, 113.322, 35.256, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410883, '孟州市', 410800, 3, 112.790, 34.908, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410803, '中站区', 410800, 3, 113.183, 35.237, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410821, '修武县', 410800, 3, 113.448, 35.224, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410823, '武陟县', 410800, 3, 113.402, 35.099, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410802, '解放区', 410800, 3, 113.231, 35.240, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410811, '山阳区', 410800, 3, 113.255, 35.215, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370305, '临淄区', 370300, 3, 118.309, 36.827, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370303, '张店区', 370300, 3, 118.018, 36.807, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370323, '沂源县', 370300, 3, 118.171, 36.185, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370321, '桓台县', 370300, 3, 118.098, 36.960, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370306, '周村区', 370300, 3, 117.870, 36.803, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370302, '淄川区', 370300, 3, 117.967, 36.643, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370304, '博山区', 370300, 3, 117.862, 36.495, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370322, '高青县', 370300, 3, 117.827, 37.171, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330822, '常山县', 330800, 3, 118.511, 28.901, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330802, '柯城区', 330800, 3, 118.871, 28.969, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330824, '开化县', 330800, 3, 118.415, 29.137, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330825, '龙游县', 330800, 3, 119.172, 29.028, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330881, '江山市', 330800, 3, 118.627, 28.737, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330803, '衢江区', 330800, 3, 118.959, 28.980, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511181, '峨眉山市', 511100, 3, 103.485, 29.601, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511133, '马边彝族自治县', 511100, 3, 103.546, 28.836, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511102, '市中区', 511100, 3, 103.761, 29.555, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511123, '犍为县', 511100, 3, 103.949, 29.208, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511129, '沐川县', 511100, 3, 103.902, 28.957, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511126, '夹江县', 511100, 3, 103.572, 29.738, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511113, '金口河区', 511100, 3, 103.079, 29.244, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511132, '峨边彝族自治县', 511100, 3, 103.262, 29.230, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511112, '五通桥区', 511100, 3, 103.818, 29.407, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511111, '沙湾区', 511100, 3, 103.550, 29.413, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511124, '井研县', 511100, 3, 104.070, 29.651, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513426, '会东县', 513400, 3, 102.578, 26.635, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513424, '德昌县', 513400, 3, 102.176, 27.403, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513432, '喜德县', 513400, 3, 102.413, 28.307, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513433, '冕宁县', 513400, 3, 102.177, 28.550, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513425, '会理县', 513400, 3, 102.245, 26.655, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513428, '普格县', 513400, 3, 102.541, 27.376, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513437, '雷波县', 513400, 3, 103.572, 28.263, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513401, '西昌市', 513400, 3, 102.264, 27.895, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513423, '盐源县', 513400, 3, 101.509, 27.423, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513434, '越西县', 513400, 3, 102.508, 28.640, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513430, '金阳县', 513400, 3, 103.249, 27.697, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513435, '甘洛县', 513400, 3, 102.772, 28.966, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513422, '木里藏族自治县', 513400, 3, 101.280, 27.929, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513427, '宁南县', 513400, 3, 102.760, 27.066, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513431, '昭觉县', 513400, 3, 102.843, 28.014, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513429, '布拖县', 513400, 3, 102.812, 27.706, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513436, '美姑县', 513400, 3, 103.132, 28.329, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441302, '惠城区', 441300, 3, 114.382, 23.084, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441322, '博罗县', 441300, 3, 114.289, 23.173, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441324, '龙门县', 441300, 3, 114.255, 23.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441303, '惠阳区', 441300, 3, 114.457, 22.789, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441323, '惠东县', 441300, 3, 114.720, 22.985, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420626, '保康县', 420600, 3, 111.261, 31.878, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420683, '枣阳市', 420600, 3, 112.772, 32.129, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420602, '襄城区', 420600, 3, 112.134, 32.010, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420625, '谷城县', 420600, 3, 111.653, 32.264, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420624, '南漳县', 420600, 3, 111.839, 31.775, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420682, '老河口市', 420600, 3, 111.684, 32.359, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420684, '宜城市', 420600, 3, 112.258, 31.720, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420606, '樊城区', 420600, 3, 112.136, 32.045, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420607, '襄州区', 420600, 3, 112.212, 32.087, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231224, '庆安县', 231200, 3, 127.508, 46.880, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231226, '绥棱县', 231200, 3, 127.115, 47.236, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231281, '安达市', 231200, 3, 125.352, 46.424, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231223, '青冈县', 231200, 3, 126.114, 46.690, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231283, '海伦市', 231200, 3, 126.973, 47.462, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231222, '兰西县', 231200, 3, 126.288, 46.252, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231221, '望奎县', 231200, 3, 126.486, 46.833, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231225, '明水县', 231200, 3, 125.906, 47.173, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231282, '肇东市', 231200, 3, 125.962, 46.051, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231202, '北林区', 231200, 3, 126.986, 46.637, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640502, '沙坡头区', 640500, 3, 105.191, 37.515, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640521, '中宁县', 640500, 3, 105.685, 37.492, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640522, '海原县', 640500, 3, 105.643, 36.565, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451121, '昭平县', 451100, 3, 110.811, 24.169, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451123, '富川瑶族自治县', 451100, 3, 111.277, 24.814, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451103, '平桂区', 451100, 3, 111.567, 24.404, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451102, '八步区', 451100, 3, 111.552, 24.412, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451122, '钟山县', 451100, 3, 111.303, 24.526, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340604, '烈山区', 340600, 3, 116.813, 33.895, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340602, '杜集区', 340600, 3, 116.828, 33.991, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340603, '相山区', 340600, 3, 116.794, 33.960, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340621, '濉溪县', 340600, 3, 116.766, 33.915, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210522, '桓仁满族自治县', 210500, 3, 125.361, 41.267, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210505, '南芬区', 210500, 3, 123.745, 41.100, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210502, '平山区', 210500, 3, 123.769, 41.300, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210521, '本溪满族自治县', 210500, 3, 124.120, 41.302, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210504, '明山区', 210500, 3, 123.817, 41.309, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210503, '溪湖区', 210500, 3, 123.768, 41.329, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350783, '建瓯市', 350700, 3, 118.305, 27.023, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350723, '光泽县', 350700, 3, 117.334, 27.541, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350725, '政和县', 350700, 3, 118.858, 27.366, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350781, '邵武市', 350700, 3, 117.493, 27.340, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350702, '延平区', 350700, 3, 118.182, 26.637, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350724, '松溪县', 350700, 3, 118.785, 27.526, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350782, '武夷山市', 350700, 3, 118.035, 27.757, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350721, '顺昌县', 350700, 3, 117.810, 26.793, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350722, '浦城县', 350700, 3, 118.541, 27.917, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350703, '建阳区', 350700, 3, 118.120, 27.332, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430423, '衡山县', 430400, 3, 112.868, 27.230, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430424, '衡东县', 430400, 3, 112.953, 27.081, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430405, '珠晖区', 430400, 3, 112.620, 26.895, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430412, '南岳区', 430400, 3, 112.739, 27.232, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430421, '衡阳县', 430400, 3, 112.371, 26.970, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430408, '蒸湘区', 430400, 3, 112.567, 26.911, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430422, '衡南县', 430400, 3, 112.678, 26.738, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430426, '祁东县', 430400, 3, 112.090, 26.800, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430407, '石鼓区', 430400, 3, 112.598, 26.943, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430481, '耒阳市', 430400, 3, 112.860, 26.422, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430482, '常宁市', 430400, 3, 112.400, 26.421, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430406, '雁峰区', 430400, 3, 112.572, 26.893, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130828, '围场满族蒙古族自治县', 130800, 3, 117.760, 41.939, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130802, '双桥区', 130800, 3, 117.943, 40.975, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130824, '滦平县', 130800, 3, 117.333, 40.941, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130821, '承德县', 130800, 3, 118.174, 40.768, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130825, '隆化县', 130800, 3, 117.739, 41.314, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130881, '平泉市', 130800, 3, 117.962, 40.954, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130803, '双滦区', 130800, 3, 117.800, 40.959, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130826, '丰宁满族自治县', 130800, 3, 116.646, 41.209, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130827, '宽城满族自治县', 130800, 3, 118.485, 40.611, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130804, '鹰手营子矿区', 130800, 3, 117.661, 40.547, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130822, '兴隆县', 130800, 3, 117.501, 40.417, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220382, '双辽市', 220300, 3, 123.503, 43.518, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220323, '伊通满族自治县', 220300, 3, 125.305, 43.346, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220322, '梨树县', 220300, 3, 124.335, 43.307, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220302, '铁西区', 220300, 3, 124.346, 43.146, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220381, '公主岭市', 220300, 3, 124.823, 43.505, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220303, '铁东区', 220300, 3, 124.410, 43.162, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371525, '冠县', 371500, 3, 115.443, 36.484, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371526, '高唐县', 371500, 3, 116.230, 36.847, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371522, '莘县', 371500, 3, 115.671, 36.234, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371581, '临清市', 371500, 3, 115.705, 36.838, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371523, '茌平县', 371500, 3, 116.255, 36.581, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371521, '阳谷县', 371500, 3, 115.792, 36.114, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371502, '东昌府区', 371500, 3, 115.988, 36.435, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371524, '东阿县', 371500, 3, 116.248, 36.335, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440281, '乐昌市', 440200, 3, 113.348, 25.130, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440282, '南雄市', 440200, 3, 114.312, 25.118, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440224, '仁化县', 440200, 3, 113.749, 25.086, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440222, '始兴县', 440200, 3, 114.062, 24.953, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440203, '武江区', 440200, 3, 113.588, 24.793, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440229, '翁源县', 440200, 3, 114.130, 24.350, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440205, '曲江区', 440200, 3, 113.605, 24.683, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440204, '浈江区', 440200, 3, 113.611, 24.804, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440233, '新丰县', 440200, 3, 114.207, 24.060, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440232, '乳源瑶族自治县', 440200, 3, 113.276, 24.776, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610826, '绥德县', 610800, 3, 110.263, 37.503, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610825, '定边县', 610800, 3, 107.601, 37.595, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610830, '清涧县', 610800, 3, 110.121, 37.089, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610803, '横山区', 610800, 3, 109.294, 37.962, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610881, '神木市', 610800, 3, 110.499, 38.842, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610824, '靖边县', 610800, 3, 108.794, 37.599, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610802, '榆阳区', 610800, 3, 109.720, 38.277, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610822, '府谷县', 610800, 3, 111.067, 39.028, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610829, '吴堡县', 610800, 3, 110.740, 37.452, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610827, '米脂县', 610800, 3, 110.184, 37.755, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610828, '佳县', 610800, 3, 110.491, 38.020, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610831, '子洲县', 610800, 3, 110.035, 37.611, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341822, '广德县', 341800, 3, 119.421, 30.878, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341825, '旌德县', 341800, 3, 118.540, 30.286, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341821, '郎溪县', 341800, 3, 119.180, 31.126, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341824, '绩溪县', 341800, 3, 118.579, 30.068, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341881, '宁国市', 341800, 3, 118.983, 30.634, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341802, '宣州区', 341800, 3, 118.756, 30.946, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341823, '泾县', 341800, 3, 118.420, 30.689, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230102, '道里区', 230100, 3, 126.617, 45.756, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230183, '尚志市', 230100, 3, 128.010, 45.210, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230110, '香坊区', 230100, 3, 126.663, 45.708, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230123, '依兰县', 230100, 3, 129.568, 46.325, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230108, '平房区', 230100, 3, 126.638, 45.598, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230127, '木兰县', 230100, 3, 128.043, 45.951, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230184, '五常市', 230100, 3, 127.168, 44.932, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230128, '通河县', 230100, 3, 128.749, 45.972, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230126, '巴彦县', 230100, 3, 127.403, 46.085, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230104, '道外区', 230100, 3, 126.649, 45.792, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230112, '阿城区', 230100, 3, 126.958, 45.549, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230109, '松北区', 230100, 3, 126.510, 45.803, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230124, '方正县', 230100, 3, 128.830, 45.852, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230125, '宾县', 230100, 3, 127.467, 45.746, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230103, '南岗区', 230100, 3, 126.669, 45.760, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230113, '双城区', 230100, 3, 126.313, 45.383, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230129, '延寿县', 230100, 3, 128.332, 45.452, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230111, '呼兰区', 230100, 3, 126.588, 45.889, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500240, '石柱土家族自治县', 500200, 3, 108.114, 29.999, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500243, '彭水苗族土家族自治县', 500200, 3, 108.166, 29.294, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500230, '丰都县', 500200, 3, 107.731, 29.864, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500235, '云阳县', 500200, 3, 108.697, 30.931, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500241, '秀山土家族苗族自治县', 500200, 3, 109.007, 28.448, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500237, '巫山县', 500200, 3, 109.879, 31.075, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500231, '垫江县', 500200, 3, 107.333, 30.328, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500233, '忠县', 500200, 3, 108.039, 30.300, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500236, '奉节县', 500200, 3, 109.464, 31.018, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500238, '巫溪县', 500200, 3, 109.570, 31.399, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500242, '酉阳土家族苗族自治县', 500200, 3, 108.768, 28.841, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500229, '城口县', 500200, 3, 108.664, 31.948, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150421, '阿鲁科尔沁旗', 150400, 3, 120.066, 43.872, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150430, '敖汉旗', 150400, 3, 119.922, 42.291, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150422, '巴林左旗', 150400, 3, 119.379, 43.971, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150429, '宁城县', 150400, 3, 118.558, 41.415, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150428, '喀喇沁旗', 150400, 3, 118.702, 41.927, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150404, '松山区', 150400, 3, 118.933, 42.284, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150425, '克什克腾旗', 150400, 3, 117.546, 43.265, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150403, '元宝山区', 150400, 3, 119.289, 42.039, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150423, '巴林右旗', 150400, 3, 118.665, 43.534, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150426, '翁牛特旗', 150400, 3, 119.007, 42.936, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150424, '林西县', 150400, 3, 118.055, 43.618, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150402, '红山区', 150400, 3, 118.967, 42.272, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140603, '平鲁区', 140600, 3, 112.288, 39.512, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140602, '朔城区', 140600, 3, 112.432, 39.319, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140621, '山阴县', 140600, 3, 112.817, 39.526, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140622, '应县', 140600, 3, 113.191, 39.554, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140623, '右玉县', 140600, 3, 112.467, 39.989, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140681, '怀仁市', 140600, 3, 113.100, 39.828, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620622, '古浪县', 620600, 3, 102.898, 37.470, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620621, '民勤县', 620600, 3, 103.094, 38.624, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620602, '凉州区', 620600, 3, 102.730, 37.821, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620623, '天祝藏族自治县', 620600, 3, 103.142, 36.972, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (622924, '广河县', 622900, 3, 103.576, 35.488, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (622901, '临夏市', 622900, 3, 103.243, 35.604, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (622922, '康乐县', 622900, 3, 103.708, 35.371, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (622926, '东乡族自治县', 622900, 3, 103.389, 35.664, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (622927, '积石山保安族东乡族撒拉族自治县', 622900, 3, 102.876, 35.718, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (622921, '临夏县', 622900, 3, 102.996, 35.492, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (622925, '和政县', 622900, 3, 103.351, 35.425, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (622923, '永靖县', 622900, 3, 103.286, 35.958, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360521, '分宜县', 360500, 3, 114.692, 27.815, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360502, '渝水区', 360500, 3, 114.945, 27.800, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410703, '卫滨区', 410700, 3, 113.866, 35.302, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410726, '延津县', 410700, 3, 114.205, 35.142, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410702, '红旗区', 410700, 3, 113.875, 35.304, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410724, '获嘉县', 410700, 3, 113.657, 35.260, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410728, '长垣县', 410700, 3, 114.669, 35.202, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410782, '辉县市', 410700, 3, 113.805, 35.462, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410781, '卫辉市', 410700, 3, 114.065, 35.398, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410727, '封丘县', 410700, 3, 114.419, 35.041, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410725, '原阳县', 410700, 3, 113.940, 35.066, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410721, '新乡县', 410700, 3, 113.805, 35.191, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410711, '牧野区', 410700, 3, 113.909, 35.315, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410704, '凤泉区', 410700, 3, 113.915, 35.384, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511024, '威远县', 511000, 3, 104.669, 29.527, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511025, '资中县', 511000, 3, 104.852, 29.764, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511002, '市中区', 511000, 3, 105.068, 29.587, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511011, '东兴区', 511000, 3, 105.075, 29.593, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511083, '隆昌市', 511000, 3, 105.288, 29.339, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370215, '即墨区', 370200, 3, 120.447, 36.390, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370211, '黄岛区', 370200, 3, 120.046, 35.873, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370285, '莱西市', 370200, 3, 120.518, 36.889, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370281, '胶州市', 370200, 3, 120.033, 36.265, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370214, '城阳区', 370200, 3, 120.396, 36.307, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370212, '崂山区', 370200, 3, 120.469, 36.108, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370213, '李沧区', 370200, 3, 120.433, 36.145, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370283, '平度市', 370200, 3, 119.988, 36.777, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370202, '市南区', 370200, 3, 120.386, 36.080, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370203, '市北区', 370200, 3, 120.375, 36.088, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330902, '定海区', 330900, 3, 122.107, 30.020, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330903, '普陀区', 330900, 3, 122.324, 29.972, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330921, '岱山县', 330900, 3, 122.226, 30.264, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330922, '嵊泗县', 330900, 3, 122.451, 30.726, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513334, '理塘县', 513300, 3, 100.270, 29.996, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513331, '白玉县', 513300, 3, 98.824, 31.210, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513329, '新龙县', 513300, 3, 100.311, 30.939, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513327, '炉霍县', 513300, 3, 100.676, 31.392, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513301, '康定市', 513300, 3, 101.957, 29.998, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513332, '石渠县', 513300, 3, 98.103, 32.979, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513326, '道孚县', 513300, 3, 101.125, 30.980, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513322, '泸定县', 513300, 3, 102.235, 29.914, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513337, '稻城县', 513300, 3, 100.298, 29.037, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513338, '得荣县', 513300, 3, 99.286, 28.713, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513328, '甘孜县', 513300, 3, 99.993, 31.623, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513323, '丹巴县', 513300, 3, 101.890, 30.879, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513333, '色达县', 513300, 3, 100.333, 32.268, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513336, '乡城县', 513300, 3, 99.798, 28.931, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513335, '巴塘县', 513300, 3, 99.111, 30.005, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513330, '德格县', 513300, 3, 98.581, 31.806, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513325, '雅江县', 513300, 3, 101.014, 30.032, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513324, '九龙县', 513300, 3, 101.507, 29.000, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441223, '广宁县', 441200, 3, 112.441, 23.635, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441226, '德庆县', 441200, 3, 111.786, 23.144, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441284, '四会市', 441200, 3, 112.734, 23.327, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441204, '高要区', 441200, 3, 112.458, 23.026, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441203, '鼎湖区', 441200, 3, 112.568, 23.158, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441224, '怀集县', 441200, 3, 112.185, 23.912, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441202, '端州区', 441200, 3, 112.485, 23.052, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441225, '封开县', 441200, 3, 111.512, 23.424, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (422826, '咸丰县', 422800, 3, 109.140, 29.665, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (422802, '利川市', 422800, 3, 108.910, 30.211, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (422822, '建始县', 422800, 3, 109.718, 30.597, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (422801, '恩施市', 422800, 3, 109.480, 30.295, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (422825, '宣恩县', 422800, 3, 109.491, 29.987, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (422827, '来凤县', 422800, 3, 109.408, 29.493, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (422823, '巴东县', 422800, 3, 110.341, 31.042, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (422828, '鹤峰县', 422800, 3, 110.034, 29.890, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420504, '点军区', 420500, 3, 111.268, 30.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420503, '伍家岗区', 420500, 3, 111.361, 30.644, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420506, '夷陵区', 420500, 3, 111.326, 30.770, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420525, '远安县', 420500, 3, 111.641, 31.061, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420526, '兴山县', 420500, 3, 110.747, 31.348, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420528, '长阳土家族自治县', 420500, 3, 111.207, 30.473, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420529, '五峰土家族自治县', 420500, 3, 110.675, 30.200, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420582, '当阳市', 420500, 3, 111.788, 30.821, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420583, '枝江市', 420500, 3, 111.761, 30.426, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420502, '西陵区', 420500, 3, 111.286, 30.711, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420527, '秭归县', 420500, 3, 110.978, 30.826, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420505, '猇亭区', 420500, 3, 111.435, 30.531, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420581, '宜都市', 420500, 3, 111.450, 30.378, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340706, '义安区', 340700, 3, 117.812, 30.945, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340711, '郊区', 340700, 3, 117.807, 30.909, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340705, '铜官区', 340700, 3, 117.816, 30.928, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340722, '枞阳县', 340700, 3, 117.251, 30.706, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210422, '新宾满族自治县', 210400, 3, 125.040, 41.734, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210421, '抚顺县', 210400, 3, 124.098, 41.923, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210411, '顺城区', 210400, 3, 123.945, 41.883, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210404, '望花区', 210400, 3, 123.784, 41.854, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210403, '东洲区', 210400, 3, 124.039, 41.853, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210423, '清原满族自治县', 210400, 3, 124.924, 42.101, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210402, '新抚区', 210400, 3, 123.913, 41.862, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231123, '逊克县', 231100, 3, 128.479, 49.564, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231124, '孙吴县', 231100, 3, 127.336, 49.426, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231102, '爱辉区', 231100, 3, 127.500, 50.252, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231182, '五大连池市', 231100, 3, 126.206, 48.517, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231181, '北安市', 231100, 3, 126.491, 48.241, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231121, '嫩江县', 231100, 3, 125.221, 49.186, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640402, '原州区', 640400, 3, 106.288, 36.004, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640425, '彭阳县', 640400, 3, 106.638, 35.850, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640422, '西吉县', 640400, 3, 105.729, 35.964, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640423, '隆德县', 640400, 3, 106.112, 35.626, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640424, '泾源县', 640400, 3, 106.331, 35.498, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451081, '靖西市', 451000, 3, 106.418, 23.135, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451024, '德保县', 451000, 3, 106.615, 23.323, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451031, '隆林各族自治县', 451000, 3, 105.344, 24.771, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451021, '田阳县', 451000, 3, 106.915, 23.736, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451023, '平果县', 451000, 3, 107.590, 23.329, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451028, '乐业县', 451000, 3, 106.557, 24.777, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451026, '那坡县', 451000, 3, 105.833, 23.387, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451002, '右江区', 451000, 3, 106.619, 23.901, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451027, '凌云县', 451000, 3, 106.561, 24.348, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451029, '田林县', 451000, 3, 106.229, 24.294, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451022, '田东县', 451000, 3, 107.126, 23.597, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451030, '西林县', 451000, 3, 105.094, 24.490, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350629, '华安县', 350600, 3, 117.534, 25.004, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350623, '漳浦县', 350600, 3, 117.614, 24.117, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350627, '南靖县', 350600, 3, 117.357, 24.515, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350602, '芗城区', 350600, 3, 117.654, 24.511, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350624, '诏安县', 350600, 3, 117.175, 23.712, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350625, '长泰县', 350600, 3, 117.759, 24.625, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350603, '龙文区', 350600, 3, 117.710, 24.503, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350626, '东山县', 350600, 3, 117.430, 23.701, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350628, '平和县', 350600, 3, 117.315, 24.363, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350681, '龙海市', 350600, 3, 117.818, 24.447, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350622, '云霄县', 350600, 3, 117.340, 23.958, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371482, '禹城市', 371400, 3, 116.638, 36.934, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371427, '夏津县', 371400, 3, 116.002, 36.948, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371403, '陵城区', 371400, 3, 116.576, 37.336, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371481, '乐陵市', 371400, 3, 117.232, 37.730, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371425, '齐河县', 371400, 3, 116.763, 36.783, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371423, '庆云县', 371400, 3, 117.385, 37.775, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371426, '平原县', 371400, 3, 116.434, 37.165, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371428, '武城县', 371400, 3, 116.069, 37.213, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371424, '临邑县', 371400, 3, 116.867, 37.190, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371422, '宁津县', 371400, 3, 116.800, 37.652, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371402, '德城区', 371400, 3, 116.299, 37.451, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430321, '湘潭县', 430300, 3, 112.951, 27.779, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430381, '湘乡市', 430300, 3, 112.551, 27.718, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430302, '雨湖区', 430300, 3, 112.903, 27.855, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430382, '韶山市', 430300, 3, 112.527, 27.915, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430304, '岳塘区', 430300, 3, 112.925, 27.809, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650204, '白碱滩区', 650200, 3, 85.119, 45.685, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650205, '乌尔禾区', 650200, 3, 85.694, 46.089, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650203, '克拉玛依区', 650200, 3, 84.868, 45.603, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650202, '独山子区', 650200, 3, 84.887, 44.328, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610921, '汉阴县', 610900, 3, 108.509, 32.893, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610922, '石泉县', 610900, 3, 108.248, 33.038, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610902, '汉滨区', 610900, 3, 109.027, 32.695, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610925, '岚皋县', 610900, 3, 108.902, 32.307, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610927, '镇坪县', 610900, 3, 109.527, 31.884, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610926, '平利县', 610900, 3, 109.362, 32.389, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610928, '旬阳县', 610900, 3, 109.365, 32.834, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610929, '白河县', 610900, 3, 110.113, 32.809, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610924, '紫阳县', 610900, 3, 108.534, 32.520, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610923, '宁陕县', 610900, 3, 108.314, 33.311, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130723, '康保县', 130700, 3, 114.600, 41.852, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130703, '桥西区', 130700, 3, 114.869, 40.820, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130709, '崇礼区', 130700, 3, 115.283, 40.975, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130730, '怀来县', 130700, 3, 115.518, 40.415, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130726, '蔚县', 130700, 3, 114.589, 39.841, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130732, '赤城县', 130700, 3, 115.831, 40.913, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130728, '怀安县', 130700, 3, 114.386, 40.674, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130725, '尚义县', 130700, 3, 113.970, 41.076, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130731, '涿鹿县', 130700, 3, 115.205, 40.380, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130705, '宣化区', 130700, 3, 115.100, 40.609, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130708, '万全区', 130700, 3, 114.741, 40.767, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130727, '阳原县', 130700, 3, 114.150, 40.104, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130702, '桥东区', 130700, 3, 114.894, 40.788, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130724, '沽源县', 130700, 3, 115.689, 41.670, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130706, '下花园区', 130700, 3, 115.287, 40.503, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130722, '张北县', 130700, 3, 114.720, 41.159, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220284, '磐石市', 220200, 3, 126.060, 42.946, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220221, '永吉县', 220200, 3, 126.498, 43.673, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220281, '蛟河市', 220200, 3, 127.345, 43.724, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220211, '丰满区', 220200, 3, 126.562, 43.822, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220282, '桦甸市', 220200, 3, 126.746, 42.972, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220204, '船营区', 220200, 3, 126.541, 43.833, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220203, '龙潭区', 220200, 3, 126.562, 43.911, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220202, '昌邑区', 220200, 3, 126.575, 43.882, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220283, '舒兰市', 220200, 3, 126.966, 44.406, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440114, '花都区', 440100, 3, 113.220, 23.404, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440105, '海珠区', 440100, 3, 113.317, 23.084, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440117, '从化区', 440100, 3, 113.587, 23.549, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440115, '南沙区', 440100, 3, 113.525, 22.802, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440106, '天河区', 440100, 3, 113.361, 23.125, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440103, '荔湾区', 440100, 3, 113.244, 23.126, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440111, '白云区', 440100, 3, 113.273, 23.157, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440118, '增城区', 440100, 3, 113.811, 23.261, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440112, '黄埔区', 440100, 3, 113.460, 23.106, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440104, '越秀区', 440100, 3, 113.267, 23.129, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440113, '番禺区', 440100, 3, 113.384, 22.937, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360424, '修水县', 360400, 3, 114.547, 29.026, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360426, '德安县', 360400, 3, 115.767, 29.299, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360482, '共青城市', 360400, 3, 115.809, 29.248, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360403, '浔阳区', 360400, 3, 115.990, 29.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360423, '武宁县', 360400, 3, 115.101, 29.256, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360429, '湖口县', 360400, 3, 116.252, 29.731, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360428, '都昌县', 360400, 3, 116.204, 29.273, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360402, '濂溪区', 360400, 3, 115.989, 29.672, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360430, '彭泽县', 360400, 3, 116.549, 29.896, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360481, '瑞昌市', 360400, 3, 115.681, 29.676, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360404, '柴桑区', 360400, 3, 115.911, 29.608, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360483, '庐山市', 360400, 3, 116.045, 29.448, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360425, '永修县', 360400, 3, 115.832, 29.012, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500115, '长寿区', 500100, 3, 107.081, 29.858, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500108, '南岸区', 500100, 3, 106.644, 29.500, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500106, '沙坪坝区', 500100, 3, 106.457, 29.541, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500112, '渝北区', 500100, 3, 106.631, 29.718, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500116, '江津区', 500100, 3, 106.259, 29.290, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500118, '永川区', 500100, 3, 105.927, 29.356, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500111, '大足区', 500100, 3, 105.722, 29.707, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500120, '璧山区', 500100, 3, 106.227, 29.592, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500103, '渝中区', 500100, 3, 106.569, 29.553, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500104, '大渡口区', 500100, 3, 106.482, 29.485, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500107, '九龙坡区', 500100, 3, 106.532, 29.514, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500119, '南川区', 500100, 3, 107.099, 29.158, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500110, '綦江区', 500100, 3, 106.651, 29.028, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500156, '武隆区', 500100, 3, 107.760, 29.326, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500105, '江北区', 500100, 3, 106.574, 29.607, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500154, '开州区', 500100, 3, 108.393, 31.161, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500153, '荣昌区', 500100, 3, 105.595, 29.405, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500109, '北碚区', 500100, 3, 106.445, 29.823, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500155, '梁平区', 500100, 3, 107.770, 30.654, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500152, '潼南区', 500100, 3, 105.841, 30.191, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500101, '万州区', 500100, 3, 108.409, 30.808, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500151, '铜梁区', 500100, 3, 106.056, 29.845, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500113, '巴南区', 500100, 3, 106.534, 29.376, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500114, '黔江区', 500100, 3, 108.771, 29.534, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500117, '合川区', 500100, 3, 106.276, 29.972, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500102, '涪陵区', 500100, 3, 107.389, 29.703, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140581, '高平市', 140500, 3, 112.924, 35.798, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140521, '沁水县', 140500, 3, 112.187, 35.690, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140522, '阳城县', 140500, 3, 112.415, 35.486, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140525, '泽州县', 140500, 3, 112.899, 35.617, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140524, '陵川县', 140500, 3, 113.281, 35.776, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140502, '城区', 140500, 3, 112.854, 35.502, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150303, '海南区', 150300, 3, 106.891, 39.441, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150304, '乌达区', 150300, 3, 106.726, 39.506, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150302, '海勃湾区', 150300, 3, 106.823, 39.691, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620725, '山丹县', 620700, 3, 101.089, 38.785, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620724, '高台县', 620700, 3, 99.819, 39.378, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620702, '甘州区', 620700, 3, 100.478, 38.930, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620721, '肃南裕固族自治县', 620700, 3, 99.616, 38.837, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620723, '临泽县', 620700, 3, 100.164, 39.153, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620722, '民乐县', 620700, 3, 100.813, 38.431, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370115, '济阳区', 370100, 3, 117.174, 36.979, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370104, '槐荫区', 370100, 3, 116.901, 36.651, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370117, '钢城区', 370100, 3, 117.120, 36.651, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370105, '天桥区', 370100, 3, 116.987, 36.678, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370114, '章丘区', 370100, 3, 117.526, 36.681, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370116, '莱芜区', 370100, 3, 117.678, 36.214, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370103, '市中区', 370100, 3, 116.998, 36.651, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370113, '长清区', 370100, 3, 116.752, 36.554, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370124, '平阴县', 370100, 3, 116.456, 36.289, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370126, '商河县', 370100, 3, 117.157, 37.309, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370112, '历城区', 370100, 3, 117.065, 36.680, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370102, '历下区', 370100, 3, 117.076, 36.666, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530103, '盘龙区', 530100, 3, 102.752, 25.116, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530129, '寻甸回族彝族自治县', 530100, 3, 103.257, 25.558, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530125, '宜良县', 530100, 3, 103.141, 24.920, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530128, '禄劝彝族苗族自治县', 530100, 3, 102.472, 25.551, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530127, '嵩明县', 530100, 3, 103.037, 25.339, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530111, '官渡区', 530100, 3, 102.744, 25.015, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530102, '五华区', 530100, 3, 102.707, 25.044, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530126, '石林彝族自治县', 530100, 3, 103.291, 24.772, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530114, '呈贡区', 530100, 3, 102.821, 24.886, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530112, '西山区', 530100, 3, 102.664, 25.038, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530115, '晋宁区', 530100, 3, 102.595, 24.670, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530181, '安宁市', 530100, 3, 102.478, 24.919, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530113, '东川区', 530100, 3, 103.188, 26.083, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530124, '富民县', 530100, 3, 102.498, 25.222, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441502, '城区', 441500, 3, 115.365, 22.779, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441521, '海丰县', 441500, 3, 115.323, 22.967, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441581, '陆丰市', 441500, 3, 115.652, 22.918, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441523, '陆河县', 441500, 3, 115.660, 23.302, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610124, '周至县', 610100, 3, 108.222, 34.164, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610112, '未央区', 610100, 3, 108.947, 34.293, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610118, '鄠邑区', 610100, 3, 108.605, 34.109, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610122, '蓝田县', 610100, 3, 109.323, 34.152, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610117, '高陵区', 610100, 3, 109.088, 34.535, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610116, '长安区', 610100, 3, 108.907, 34.159, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610114, '阎良区', 610100, 3, 109.226, 34.662, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610103, '碑林区', 610100, 3, 108.934, 34.231, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610115, '临潼区', 610100, 3, 109.214, 34.367, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610102, '新城区', 610100, 3, 108.961, 34.266, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610104, '莲湖区', 610100, 3, 108.944, 34.265, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610113, '雁塔区', 610100, 3, 108.927, 34.213, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610111, '灞桥区', 610100, 3, 109.065, 34.273, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340802, '迎江区', 340800, 3, 117.091, 30.512, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340822, '怀宁县', 340800, 3, 116.829, 30.734, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340825, '太湖县', 340800, 3, 116.309, 30.454, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340803, '大观区', 340800, 3, 117.022, 30.554, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340882, '潜山市', 340800, 3, 116.581, 30.631, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340881, '桐城市', 340800, 3, 116.974, 31.036, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340828, '岳西县', 340800, 3, 116.360, 30.849, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340811, '宜秀区', 340800, 3, 117.070, 30.541, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340827, '望江县', 340800, 3, 116.694, 30.124, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340826, '宿松县', 340800, 3, 116.129, 30.154, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420881, '钟祥市', 420800, 3, 112.588, 31.168, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420822, '沙洋县', 420800, 3, 112.589, 30.709, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420882, '京山市', 420800, 3, 113.120, 31.018, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420804, '掇刀区', 420800, 3, 112.208, 30.973, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420802, '东宝区', 420800, 3, 112.201, 31.052, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231025, '林口县', 231000, 3, 130.284, 45.278, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231086, '东宁市', 231000, 3, 131.123, 44.088, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231083, '海林市', 231000, 3, 129.380, 44.594, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231004, '爱民区', 231000, 3, 129.592, 44.596, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231005, '西安区', 231000, 3, 129.616, 44.578, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231081, '绥芬河市', 231000, 3, 131.153, 44.412, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231002, '东安区', 231000, 3, 129.627, 44.581, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231084, '宁安市', 231000, 3, 129.483, 44.341, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231085, '穆棱市', 231000, 3, 130.524, 44.919, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231003, '阳明区', 231000, 3, 129.636, 44.596, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210304, '立山区', 210300, 3, 123.029, 41.150, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210321, '台安县', 210300, 3, 122.436, 41.413, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210311, '千山区', 210300, 3, 122.949, 41.069, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210381, '海城市', 210300, 3, 122.685, 40.882, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210323, '岫岩满族自治县', 210300, 3, 123.281, 40.291, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210302, '铁东区', 210300, 3, 122.991, 41.090, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210303, '铁西区', 210300, 3, 122.970, 41.120, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510681, '广汉市', 510600, 3, 104.282, 30.976, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510683, '绵竹市', 510600, 3, 104.221, 31.338, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510604, '罗江区', 510600, 3, 104.510, 31.317, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510603, '旌阳区', 510600, 3, 104.417, 31.142, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510623, '中江县', 510600, 3, 104.679, 31.033, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510682, '什邡市', 510600, 3, 104.168, 31.127, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451322, '象州县', 451300, 3, 109.684, 23.959, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451324, '金秀瑶族自治县', 451300, 3, 110.189, 24.130, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451381, '合山市', 451300, 3, 108.886, 23.807, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451321, '忻城县', 451300, 3, 108.666, 24.066, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451302, '兴宾区', 451300, 3, 109.233, 23.736, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451323, '武宣县', 451300, 3, 109.663, 23.594, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621125, '漳县', 621100, 3, 104.472, 34.848, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621122, '陇西县', 621100, 3, 104.635, 35.004, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621126, '岷县', 621100, 3, 104.037, 34.438, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621123, '渭源县', 621100, 3, 104.215, 35.137, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621121, '通渭县', 621100, 3, 105.242, 35.211, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621102, '安定区', 621100, 3, 104.611, 35.581, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621124, '临洮县', 621100, 3, 103.860, 35.395, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371323, '沂水县', 371300, 3, 118.628, 35.790, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371321, '沂南县', 371300, 3, 118.465, 35.550, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371328, '蒙阴县', 371300, 3, 117.945, 35.710, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371322, '郯城县', 371300, 3, 118.367, 34.614, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371312, '河东区', 371300, 3, 118.403, 35.090, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371311, '罗庄区', 371300, 3, 118.285, 34.997, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371302, '兰山区', 371300, 3, 118.348, 35.052, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371327, '莒南县', 371300, 3, 118.835, 35.175, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371329, '临沭县', 371300, 3, 118.651, 34.920, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371324, '兰陵县', 371300, 3, 118.071, 34.857, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371326, '平邑县', 371300, 3, 117.640, 35.506, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371325, '费县', 371300, 3, 117.977, 35.266, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430624, '湘阴县', 430600, 3, 112.909, 28.689, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430621, '岳阳县', 430600, 3, 113.116, 29.144, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430602, '岳阳楼区', 430600, 3, 113.130, 29.372, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430611, '君山区', 430600, 3, 113.006, 29.461, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430623, '华容县', 430600, 3, 112.540, 29.531, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430682, '临湘市', 430600, 3, 113.450, 29.477, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430603, '云溪区', 430600, 3, 113.272, 29.473, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430626, '平江县', 430600, 3, 113.581, 28.702, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430681, '汨罗市', 430600, 3, 113.067, 28.807, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130208, '丰润区', 130200, 3, 118.162, 39.833, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130204, '古冶区', 130200, 3, 118.448, 39.734, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130283, '迁安市', 130200, 3, 118.701, 39.999, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130281, '遵化市', 130200, 3, 117.966, 40.189, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130203, '路北区', 130200, 3, 118.201, 39.624, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130227, '迁西县', 130200, 3, 118.315, 40.142, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130209, '曹妃甸区', 130200, 3, 118.460, 39.273, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130225, '乐亭县', 130200, 3, 118.913, 39.426, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130229, '玉田县', 130200, 3, 117.739, 39.900, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130202, '路南区', 130200, 3, 118.154, 39.625, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130207, '丰南区', 130200, 3, 118.085, 39.576, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130224, '滦南县', 130200, 3, 118.682, 39.519, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130205, '开平区', 130200, 3, 118.262, 39.671, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130284, '滦州市', 130200, 3, 118.690, 39.740, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520327, '凤冈县', 520300, 3, 107.716, 27.955, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520302, '红花岗区', 520300, 3, 106.894, 27.645, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520326, '务川仡佬族苗族自治县', 520300, 3, 107.899, 28.563, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520303, '汇川区', 520300, 3, 106.934, 27.750, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520329, '余庆县', 520300, 3, 107.905, 27.215, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520381, '赤水市', 520300, 3, 105.697, 28.590, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520322, '桐梓县', 520300, 3, 106.826, 28.134, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520323, '绥阳县', 520300, 3, 107.191, 27.946, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520382, '仁怀市', 520300, 3, 106.400, 27.792, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520324, '正安县', 520300, 3, 107.454, 28.553, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520325, '道真仡佬族苗族自治县', 520300, 3, 107.613, 28.862, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520330, '习水县', 520300, 3, 106.197, 28.331, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520328, '湄潭县', 520300, 3, 107.465, 27.749, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520304, '播州区', 520300, 3, 106.927, 27.726, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (222402, '图们市', 222400, 3, 129.844, 42.968, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (222406, '和龙市', 222400, 3, 129.010, 42.547, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (222424, '汪清县', 222400, 3, 129.772, 43.313, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (222403, '敦化市', 222400, 3, 128.232, 43.372, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (222404, '珲春市', 222400, 3, 130.366, 42.863, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (222401, '延吉市', 222400, 3, 129.509, 42.891, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (222426, '安图县', 222400, 3, 128.900, 43.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (222405, '龙井市', 222400, 3, 129.427, 42.766, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220122, '农安县', 220100, 3, 125.185, 44.433, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220112, '双阳区', 220100, 3, 125.665, 43.525, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220103, '宽城区', 220100, 3, 125.327, 43.944, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220106, '绿园区', 220100, 3, 125.256, 43.881, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220104, '朝阳区', 220100, 3, 125.288, 43.834, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220105, '二道区', 220100, 3, 125.374, 43.866, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220183, '德惠市', 220100, 3, 125.729, 44.522, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220102, '南关区', 220100, 3, 125.350, 43.864, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220182, '榆树市', 220100, 3, 126.533, 44.840, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220113, '九台区', 220100, 3, 125.840, 44.152, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522626, '岑巩县', 522600, 3, 108.816, 27.174, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522629, '剑河县', 522600, 3, 108.442, 26.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522630, '台江县', 522600, 3, 108.321, 26.668, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522635, '麻江县', 522600, 3, 107.589, 26.491, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522634, '雷山县', 522600, 3, 108.078, 26.378, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522601, '凯里市', 522600, 3, 107.981, 26.567, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522627, '天柱县', 522600, 3, 109.208, 26.910, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522628, '锦屏县', 522600, 3, 109.201, 26.676, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522622, '黄平县', 522600, 3, 107.916, 26.905, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522632, '榕江县', 522600, 3, 108.522, 25.932, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522636, '丹寨县', 522600, 3, 107.789, 26.198, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522631, '黎平县', 522600, 3, 109.137, 26.230, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522623, '施秉县', 522600, 3, 108.124, 27.033, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522624, '三穗县', 522600, 3, 108.675, 26.953, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522633, '从江县', 522600, 3, 108.905, 25.753, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522625, '镇远县', 522600, 3, 108.430, 27.049, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440404, '金湾区', 440400, 3, 113.363, 22.147, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440403, '斗门区', 440400, 3, 113.296, 22.209, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440402, '香洲区', 440400, 3, 113.544, 22.266, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350502, '鲤城区', 350500, 3, 118.587, 24.908, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350521, '惠安县', 350500, 3, 118.797, 25.031, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350524, '安溪县', 350500, 3, 118.186, 25.056, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350582, '晋江市', 350500, 3, 118.552, 24.782, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350527, '金门县', 350500, 3, 118.317, 24.433, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350583, '南安市', 350500, 3, 118.386, 24.960, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350581, '石狮市', 350500, 3, 118.648, 24.732, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350526, '德化县', 350500, 3, 118.241, 25.491, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350504, '洛江区', 350500, 3, 118.671, 24.940, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350503, '丰泽区', 350500, 3, 118.669, 24.871, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350525, '永春县', 350500, 3, 118.676, 24.874, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350505, '泉港区', 350500, 3, 118.916, 25.120, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360321, '莲花县', 360300, 3, 113.962, 27.128, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360322, '上栗县', 360300, 3, 113.795, 27.880, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360302, '安源区', 360300, 3, 113.871, 27.615, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360313, '湘东区', 360300, 3, 113.733, 27.640, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360323, '芦溪县', 360300, 3, 114.030, 27.631, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211422, '建昌县', 211400, 3, 119.837, 40.824, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211481, '兴城市', 211400, 3, 120.728, 40.615, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211402, '连山区', 211400, 3, 120.869, 40.774, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211421, '绥中县', 211400, 3, 120.344, 40.326, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211404, '南票区', 211400, 3, 120.750, 41.107, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211403, '龙港区', 211400, 3, 120.894, 40.736, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511802, '雨城区', 511800, 3, 103.033, 30.005, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511826, '芦山县', 511800, 3, 102.928, 30.144, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511822, '荥经县', 511800, 3, 102.847, 29.793, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511803, '名山区', 511800, 3, 103.109, 30.070, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511825, '天全县', 511800, 3, 102.758, 30.067, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511824, '石棉县', 511800, 3, 102.359, 29.228, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511827, '宝兴县', 511800, 3, 102.815, 30.368, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511823, '汉源县', 511800, 3, 102.645, 29.347, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450203, '鱼峰区', 450200, 3, 109.452, 24.319, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450225, '融水苗族自治县', 450200, 3, 109.256, 25.066, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450205, '柳北区', 450200, 3, 109.402, 24.363, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450202, '城中区', 450200, 3, 109.411, 24.316, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450206, '柳江区', 450200, 3, 109.326, 24.255, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450223, '鹿寨县', 450200, 3, 109.751, 24.473, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450222, '柳城县', 450200, 3, 109.245, 24.652, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450224, '融安县', 450200, 3, 109.398, 25.225, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450204, '柳南区', 450200, 3, 109.386, 24.336, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450226, '三江侗族自治县', 450200, 3, 109.608, 25.783, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410926, '范县', 410900, 3, 115.504, 35.852, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410922, '清丰县', 410900, 3, 115.104, 35.885, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410902, '华龙区', 410900, 3, 115.074, 35.777, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410928, '濮阳县', 410900, 3, 115.029, 35.712, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410923, '南乐县', 410900, 3, 115.205, 36.070, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410927, '台前县', 410900, 3, 115.872, 35.969, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441481, '兴宁市', 441400, 3, 115.724, 24.126, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441403, '梅县区', 441400, 3, 116.082, 24.265, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441426, '平远县', 441400, 3, 115.892, 24.567, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441424, '五华县', 441400, 3, 115.777, 23.931, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441427, '蕉岭县', 441400, 3, 116.171, 24.659, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441402, '梅江区', 441400, 3, 116.117, 24.310, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441423, '丰顺县', 441400, 3, 116.182, 23.740, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441422, '大埔县', 441400, 3, 116.695, 24.348, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141121, '文水县', 141100, 3, 112.029, 37.438, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141130, '交口县', 141100, 3, 111.181, 36.982, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141126, '石楼县', 141100, 3, 110.835, 36.997, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141129, '中阳县', 141100, 3, 111.180, 37.357, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141128, '方山县', 141100, 3, 111.244, 37.895, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141102, '离石区', 141100, 3, 111.151, 37.518, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141125, '柳林县', 141100, 3, 110.889, 37.430, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141123, '兴县', 141100, 3, 111.128, 38.462, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141124, '临县', 141100, 3, 110.992, 37.951, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141182, '汾阳市', 141100, 3, 111.770, 37.262, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141122, '交城县', 141100, 3, 112.156, 37.552, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141181, '孝义市', 141100, 3, 111.779, 37.146, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141127, '岚县', 141100, 3, 111.672, 38.279, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610203, '印台区', 610200, 3, 109.100, 35.114, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610202, '王益区', 610200, 3, 109.076, 35.069, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610204, '耀州区', 610200, 3, 108.981, 34.909, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610222, '宜君县', 610200, 3, 109.117, 35.399, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420703, '华容区', 420700, 3, 114.730, 30.534, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420702, '梁子湖区', 420700, 3, 114.685, 30.100, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420704, '鄂城区', 420700, 3, 114.892, 30.401, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510504, '龙马潭区', 510500, 3, 105.438, 28.913, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510525, '古蔺县', 510500, 3, 105.813, 28.039, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510524, '叙永县', 510500, 3, 105.445, 28.156, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510521, '泸县', 510500, 3, 105.382, 29.152, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510503, '纳溪区', 510500, 3, 105.371, 28.773, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510522, '合江县', 510500, 3, 105.831, 28.811, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510502, '江阳区', 510500, 3, 105.435, 28.879, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210212, '旅顺口区', 210200, 3, 121.262, 38.852, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210211, '甘井子区', 210200, 3, 121.525, 38.953, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210281, '瓦房店市', 210200, 3, 121.980, 39.627, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210213, '金州区', 210200, 3, 121.783, 39.050, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210224, '长海县', 210200, 3, 122.588, 39.273, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210283, '庄河市', 210200, 3, 122.967, 39.681, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210214, '普兰店区', 210200, 3, 122.262, 39.742, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210203, '西岗区', 210200, 3, 121.612, 38.915, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210202, '中山区', 210200, 3, 121.645, 38.919, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210204, '沙河口区', 210200, 3, 121.594, 38.905, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451221, '南丹县', 451200, 3, 107.541, 24.974, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451228, '都安瑶族自治县', 451200, 3, 108.105, 23.933, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451203, '宜州区', 451200, 3, 108.085, 24.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451202, '金城江区', 451200, 3, 108.037, 24.690, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451229, '大化瑶族自治县', 451200, 3, 107.998, 23.736, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451226, '环江毛南族自治县', 451200, 3, 108.258, 24.826, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451225, '罗城仫佬族自治县', 451200, 3, 108.905, 24.777, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451223, '凤山县', 451200, 3, 107.042, 24.547, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451222, '天峨县', 451200, 3, 107.174, 24.999, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451224, '东兰县', 451200, 3, 107.374, 24.511, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451227, '巴马瑶族自治县', 451200, 3, 107.259, 24.142, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621224, '康县', 621200, 3, 105.609, 33.329, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621225, '西和县', 621200, 3, 105.299, 34.014, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621222, '文县', 621200, 3, 104.683, 32.944, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621221, '成县', 621200, 3, 105.742, 33.750, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621223, '宕昌县', 621200, 3, 104.393, 34.047, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621202, '武都区', 621200, 3, 104.926, 33.392, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621226, '礼县', 621200, 3, 105.179, 34.189, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621228, '两当县', 621200, 3, 106.305, 33.909, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621227, '徽县', 621200, 3, 106.088, 33.769, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130111, '栾城区', 130100, 3, 114.648, 37.900, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130121, '井陉县', 130100, 3, 114.145, 38.032, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130184, '新乐市', 130100, 3, 114.684, 38.343, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130105, '新华区', 130100, 3, 114.463, 38.051, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130110, '鹿泉区', 130100, 3, 114.314, 38.086, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130130, '无极县', 130100, 3, 114.976, 38.179, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130108, '裕华区', 130100, 3, 114.531, 38.006, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130107, '井陉矿区', 130100, 3, 114.515, 38.042, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130126, '灵寿县', 130100, 3, 114.376, 38.306, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130123, '正定县', 130100, 3, 114.571, 38.146, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130181, '辛集市', 130100, 3, 115.218, 37.943, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130128, '深泽县', 130100, 3, 115.201, 38.184, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130131, '平山县', 130100, 3, 114.199, 38.247, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130132, '元氏县', 130100, 3, 114.526, 37.767, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130183, '晋州市', 130100, 3, 115.044, 38.034, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130102, '长安区', 130100, 3, 114.539, 38.037, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130127, '高邑县', 130100, 3, 114.611, 37.615, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130104, '桥西区', 130100, 3, 114.461, 38.004, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130133, '赵县', 130100, 3, 114.776, 37.756, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130129, '赞皇县', 130100, 3, 114.386, 37.666, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130125, '行唐县', 130100, 3, 114.553, 38.438, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130109, '藁城区', 130100, 3, 114.847, 38.022, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430581, '武冈市', 430500, 3, 110.632, 26.727, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430503, '大祥区', 430500, 3, 111.439, 27.221, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430528, '新宁县', 430500, 3, 110.857, 26.433, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430529, '城步苗族自治县', 430500, 3, 110.322, 26.391, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430523, '邵阳县', 430500, 3, 111.274, 26.991, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430521, '邵东县', 430500, 3, 111.744, 27.259, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430524, '隆回县', 430500, 3, 111.032, 27.114, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430525, '洞口县', 430500, 3, 110.576, 27.060, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430522, '新邵县', 430500, 3, 111.459, 27.321, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430511, '北塔区', 430500, 3, 111.452, 27.246, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430502, '双清区', 430500, 3, 111.496, 27.233, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430527, '绥宁县', 430500, 3, 110.156, 26.582, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520425, '紫云苗族布依族自治县', 520400, 3, 106.084, 25.751, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520424, '关岭布依族苗族自治县', 520400, 3, 105.619, 25.944, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520423, '镇宁布依族苗族自治县', 520400, 3, 105.770, 26.057, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520422, '普定县', 520400, 3, 105.743, 26.301, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520402, '西秀区', 520400, 3, 105.966, 26.245, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520403, '平坝区', 520400, 3, 106.256, 26.406, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522730, '龙里县', 522700, 3, 106.980, 26.453, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522732, '三都水族自治县', 522700, 3, 107.870, 25.983, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522701, '都匀市', 522700, 3, 107.519, 26.259, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522725, '瓮安县', 522700, 3, 107.472, 27.078, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522731, '惠水县', 522700, 3, 106.657, 26.132, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522727, '平塘县', 522700, 3, 107.323, 25.832, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522723, '贵定县', 522700, 3, 107.233, 26.557, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522729, '长顺县', 522700, 3, 106.447, 26.022, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522726, '独山县', 522700, 3, 107.545, 25.822, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522728, '罗甸县', 522700, 3, 106.751, 25.425, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522702, '福泉市', 522700, 3, 107.520, 26.686, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522722, '荔波县', 522700, 3, 107.886, 25.411, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440308, '盐田区', 440300, 3, 114.237, 22.556, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440305, '南山区', 440300, 3, 113.924, 22.528, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440307, '龙岗区', 440300, 3, 114.247, 22.721, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440310, '坪山区', 440300, 3, 114.362, 22.688, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440304, '福田区', 440300, 3, 114.055, 22.522, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440303, '罗湖区', 440300, 3, 114.132, 22.548, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440309, '龙华区', 440300, 3, 114.045, 22.697, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440311, '光明区', 440300, 3, 114.058, 22.543, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440306, '宝安区', 440300, 3, 113.884, 22.555, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350424, '宁化县', 350400, 3, 116.654, 26.262, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350426, '尤溪县', 350400, 3, 118.190, 26.170, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350402, '梅列区', 350400, 3, 117.646, 26.272, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350423, '清流县', 350400, 3, 116.817, 26.178, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350421, '明溪县', 350400, 3, 117.202, 26.357, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350403, '三元区', 350400, 3, 117.608, 26.234, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350427, '沙县', 350400, 3, 117.792, 26.397, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350481, '永安市', 350400, 3, 117.374, 25.976, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350428, '将乐县', 350400, 3, 117.471, 26.729, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350429, '泰宁县', 350400, 3, 117.176, 26.900, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350425, '大田县', 350400, 3, 117.847, 25.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350430, '建宁县', 350400, 3, 116.846, 26.831, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360203, '珠山区', 360200, 3, 117.203, 29.300, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360202, '昌江区', 360200, 3, 117.184, 29.273, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360222, '浮梁县', 360200, 3, 117.215, 29.352, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360281, '乐平市', 360200, 3, 117.152, 28.978, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532531, '绿春县', 532500, 3, 102.392, 22.994, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532532, '河口瑶族自治县', 532500, 3, 103.939, 22.529, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532523, '屏边苗族自治县', 532500, 3, 103.688, 22.984, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532529, '红河县', 532500, 3, 102.421, 23.369, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532527, '泸西县', 532500, 3, 103.766, 24.532, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532503, '蒙自市', 532500, 3, 103.365, 23.396, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532530, '金平苗族瑶族傣族自治县', 532500, 3, 103.226, 22.780, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532525, '石屏县', 532500, 3, 102.495, 23.706, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532504, '弥勒市', 532500, 3, 103.415, 24.412, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532524, '建水县', 532500, 3, 102.827, 23.635, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532501, '个旧市', 532500, 3, 103.160, 23.359, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532502, '开远市', 532500, 3, 103.267, 23.714, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532528, '元阳县', 532500, 3, 102.835, 23.220, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511725, '渠县', 511700, 3, 106.973, 30.837, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511723, '开江县', 511700, 3, 107.869, 31.083, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511781, '万源市', 511700, 3, 108.035, 32.082, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511724, '大竹县', 511700, 3, 107.205, 30.736, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511702, '通川区', 511700, 3, 107.505, 31.215, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511703, '达川区', 511700, 3, 107.512, 31.196, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511722, '宣汉县', 511700, 3, 107.727, 31.354, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211322, '建平县', 211300, 3, 119.643, 41.403, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211302, '双塔区', 211300, 3, 120.454, 41.566, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211382, '凌源市', 211300, 3, 119.402, 41.245, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211321, '朝阳县', 211300, 3, 120.390, 41.498, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211303, '龙城区', 211300, 3, 120.413, 41.577, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211324, '喀喇沁左翼蒙古族自治县', 211300, 3, 119.741, 41.128, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211381, '北票市', 211300, 3, 120.771, 41.801, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450107, '西乡塘区', 450100, 3, 108.307, 22.833, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450123, '隆安县', 450100, 3, 107.696, 23.166, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450124, '马山县', 450100, 3, 108.177, 23.708, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450126, '宾阳县', 450100, 3, 108.810, 23.218, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450102, '兴宁区', 450100, 3, 108.369, 22.854, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450127, '横县', 450100, 3, 109.261, 22.680, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450103, '青秀区', 450100, 3, 108.494, 22.786, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450108, '良庆区', 450100, 3, 108.322, 22.759, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450110, '武鸣区', 450100, 3, 108.275, 23.159, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450109, '邕宁区', 450100, 3, 108.487, 22.758, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450125, '上林县', 450100, 3, 108.605, 23.432, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450105, '江南区', 450100, 3, 108.273, 22.782, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620122, '皋兰县', 620100, 3, 103.947, 36.333, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620121, '永登县', 620100, 3, 103.260, 36.737, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620123, '榆中县', 620100, 3, 104.113, 35.843, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620102, '城关区', 620100, 3, 103.825, 36.057, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620105, '安宁区', 620100, 3, 103.719, 36.104, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620111, '红古区', 620100, 3, 102.859, 36.346, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620103, '七里河区', 620100, 3, 103.786, 36.066, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620104, '西固区', 620100, 3, 103.628, 36.088, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460105, '秀英区', 460100, 3, 110.294, 20.008, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460108, '美兰区', 460100, 3, 110.366, 20.029, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460107, '琼山区', 460100, 3, 110.354, 20.003, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460106, '龙华区', 460100, 3, 110.328, 20.031, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441781, '阳春市', 441700, 3, 111.792, 22.170, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441702, '江城区', 441700, 3, 111.955, 21.862, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441704, '阳东区', 441700, 3, 112.006, 21.868, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441721, '阳西县', 441700, 3, 111.618, 21.752, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610327, '陇县', 610300, 3, 106.864, 34.893, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610303, '金台区', 610300, 3, 107.147, 34.376, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610330, '凤县', 610300, 3, 106.516, 33.908, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610331, '太白县', 610300, 3, 107.319, 34.058, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610302, '渭滨区', 610300, 3, 107.150, 34.371, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610304, '陈仓区', 610300, 3, 107.364, 34.355, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610329, '麟游县', 610300, 3, 107.794, 34.678, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610323, '岐山县', 610300, 3, 107.621, 34.443, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610328, '千阳县', 610300, 3, 107.132, 34.642, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610326, '眉县', 610300, 3, 107.750, 34.274, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610324, '扶风县', 610300, 3, 107.900, 34.375, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610322, '凤翔县', 610300, 3, 107.401, 34.521, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520102, '南明区', 520100, 3, 106.714, 26.568, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520122, '息烽县', 520100, 3, 106.740, 27.090, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520113, '白云区', 520100, 3, 106.623, 26.679, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520112, '乌当区', 520100, 3, 106.751, 26.631, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520111, '花溪区', 520100, 3, 106.670, 26.410, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520121, '开阳县', 520100, 3, 106.965, 27.058, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520123, '修文县', 520100, 3, 106.592, 26.839, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520103, '云岩区', 520100, 3, 106.724, 26.605, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520115, '观山湖区', 520100, 3, 106.622, 26.601, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520181, '清镇市', 520100, 3, 106.471, 26.556, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510403, '西区', 510400, 3, 101.719, 26.582, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510411, '仁和区', 510400, 3, 101.739, 26.498, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510421, '米易县', 510400, 3, 102.110, 26.891, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510402, '东区', 510400, 3, 101.719, 26.582, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510422, '盐边县', 510400, 3, 101.855, 26.683, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210114, '于洪区', 210100, 3, 123.308, 41.794, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210104, '大东区', 210100, 3, 123.509, 41.813, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210103, '沈河区', 210100, 3, 123.459, 41.796, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210181, '新民市', 210100, 3, 122.726, 41.933, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210115, '辽中区', 210100, 3, 122.765, 41.517, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210102, '和平区', 210100, 3, 123.396, 41.789, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210106, '铁西区', 210100, 3, 123.376, 41.803, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210112, '浑南区', 210100, 3, 123.450, 41.715, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210113, '沈北新区', 210100, 3, 123.583, 41.912, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210105, '皇姑区', 210100, 3, 123.442, 41.825, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210123, '康平县', 210100, 3, 123.356, 42.741, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210124, '法库县', 210100, 3, 123.440, 42.501, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210111, '苏家屯区', 210100, 3, 123.344, 41.665, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371122, '莒县', 371100, 3, 118.837, 35.580, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371121, '五莲县', 371100, 3, 119.207, 35.752, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371103, '岚山区', 371100, 3, 119.319, 35.122, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371102, '东港区', 371100, 3, 119.462, 35.425, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130434, '魏县', 130400, 3, 114.939, 36.360, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130433, '馆陶县', 130400, 3, 115.282, 36.548, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130430, '邱县', 130400, 3, 115.187, 36.811, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130481, '武安市', 130400, 3, 114.204, 36.697, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130431, '鸡泽县', 130400, 3, 114.878, 36.920, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130407, '肥乡区', 130400, 3, 114.800, 36.548, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130423, '临漳县', 130400, 3, 114.620, 36.335, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130406, '峰峰矿区', 130400, 3, 114.539, 36.626, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130427, '磁县', 130400, 3, 114.374, 36.374, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130404, '复兴区', 130400, 3, 114.462, 36.639, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130408, '永年区', 130400, 3, 114.539, 36.626, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130424, '成安县', 130400, 3, 114.670, 36.444, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130426, '涉县', 130400, 3, 113.691, 36.585, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130402, '邯山区', 130400, 3, 114.490, 36.580, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130403, '丛台区', 130400, 3, 114.493, 36.636, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130425, '大名县', 130400, 3, 115.148, 36.286, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130435, '曲周县', 130400, 3, 114.945, 36.780, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130432, '广平县', 130400, 3, 114.949, 36.483, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430811, '武陵源区', 430800, 3, 110.550, 29.346, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430821, '慈利县', 430800, 3, 111.140, 29.430, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430802, '永定区', 430800, 3, 110.537, 29.120, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430822, '桑植县', 430800, 3, 110.205, 29.414, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440607, '三水区', 440600, 3, 112.897, 23.156, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440606, '顺德区', 440600, 3, 113.293, 22.805, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440604, '禅城区', 440600, 3, 113.122, 23.010, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440605, '南海区', 440600, 3, 113.148, 23.025, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440608, '高明区', 440600, 3, 112.893, 22.900, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533325, '兰坪白族普米族自治县', 533300, 3, 99.417, 26.454, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533323, '福贡县', 533300, 3, 98.869, 26.902, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533324, '贡山独龙族怒族自治县', 533300, 3, 98.666, 27.741, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533301, '泸水市', 533300, 3, 98.858, 25.823, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350305, '秀屿区', 350300, 3, 119.106, 25.319, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350322, '仙游县', 350300, 3, 118.692, 25.362, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350303, '涵江区', 350300, 3, 119.116, 25.459, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350302, '城厢区', 350300, 3, 118.994, 25.419, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350304, '荔城区', 350300, 3, 119.015, 25.432, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360121, '南昌县', 360100, 3, 115.944, 28.546, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360103, '西湖区', 360100, 3, 115.877, 28.658, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360111, '青山湖区', 360100, 3, 115.962, 28.683, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360105, '湾里区', 360100, 3, 115.731, 28.715, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360112, '新建区', 360100, 3, 115.815, 28.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360124, '进贤县', 360100, 3, 116.269, 28.367, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360102, '东湖区', 360100, 3, 115.899, 28.685, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360123, '安义县', 360100, 3, 115.549, 28.845, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360104, '青云谱区', 360100, 3, 115.926, 28.621, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140226, '左云县', 140200, 3, 112.703, 40.013, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140221, '阳高县', 140200, 3, 113.749, 40.361, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140225, '浑源县', 140200, 3, 113.699, 39.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140212, '新荣区', 140200, 3, 113.140, 40.256, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140213, '平城区', 140200, 3, 113.300, 40.077, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140215, '云州区', 140200, 3, 113.300, 40.077, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140222, '天镇县', 140200, 3, 114.091, 40.420, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140214, '云冈区', 140200, 3, 113.131, 40.109, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140224, '灵丘县', 140200, 3, 114.234, 39.442, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140223, '广灵县', 140200, 3, 114.274, 39.762, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450423, '蒙山县', 450400, 3, 110.525, 24.194, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450406, '龙圩区', 450400, 3, 111.246, 23.410, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450481, '岑溪市', 450400, 3, 110.995, 22.918, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450421, '苍梧县', 450400, 3, 111.544, 23.845, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450403, '万秀区', 450400, 3, 111.321, 23.473, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450405, '长洲区', 450400, 3, 111.275, 23.486, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450422, '藤县', 450400, 3, 110.915, 23.375, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211281, '调兵山市', 211200, 3, 123.567, 42.468, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211202, '银州区', 211200, 3, 123.842, 42.286, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211221, '铁岭县', 211200, 3, 123.729, 42.223, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211223, '西丰县', 211200, 3, 124.727, 42.738, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211204, '清河区', 211200, 3, 123.726, 42.224, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211224, '昌图县', 211200, 3, 124.111, 42.786, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211282, '开原市', 211200, 3, 124.038, 42.546, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511681, '华蓥市', 511600, 3, 106.783, 30.390, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511602, '广安区', 511600, 3, 106.642, 30.474, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511603, '前锋区', 511600, 3, 106.893, 30.496, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511621, '岳池县', 511600, 3, 106.440, 30.538, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511623, '邻水县', 511600, 3, 106.930, 30.335, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511622, '武胜县', 511600, 3, 106.296, 30.349, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211122, '盘山县', 211100, 3, 121.996, 41.243, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211102, '双台子区', 211100, 3, 122.060, 41.191, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211104, '大洼区', 211100, 3, 122.071, 41.120, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211103, '兴隆台区', 211100, 3, 122.070, 41.159, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511402, '东坡区', 511400, 3, 103.832, 30.042, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511403, '彭山区', 511400, 3, 103.873, 30.193, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511421, '仁寿县', 511400, 3, 104.134, 29.996, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511423, '洪雅县', 511400, 3, 103.373, 29.905, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511424, '丹棱县', 511400, 3, 103.513, 30.014, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511425, '青神县', 511400, 3, 103.847, 29.831, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532301, '楚雄市', 532300, 3, 101.546, 25.033, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532324, '南华县', 532300, 3, 101.274, 25.192, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532326, '大姚县', 532300, 3, 101.337, 25.730, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532327, '永仁县', 532300, 3, 101.666, 26.049, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532323, '牟定县', 532300, 3, 101.547, 25.313, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532328, '元谋县', 532300, 3, 101.875, 25.704, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532331, '禄丰县', 532300, 3, 102.079, 25.150, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532325, '姚安县', 532300, 3, 101.242, 25.504, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532322, '双柏县', 532300, 3, 101.642, 24.689, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532329, '武定县', 532300, 3, 102.404, 25.530, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441625, '东源县', 441600, 3, 114.746, 23.788, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441621, '紫金县', 441600, 3, 115.184, 23.635, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441602, '源城区', 441600, 3, 114.703, 23.734, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441622, '龙川县', 441600, 3, 115.260, 24.100, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441624, '和平县', 441600, 3, 114.939, 24.442, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441623, '连平县', 441600, 3, 114.489, 24.370, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460204, '天涯区', 460200, 3, 109.506, 18.247, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460202, '海棠区', 460200, 3, 109.761, 18.408, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460205, '崖州区', 460200, 3, 109.174, 18.352, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460203, '吉阳区', 460200, 3, 109.578, 18.281, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310114, '嘉定区', 310100, 3, 121.265, 31.376, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310151, '崇明区', 310100, 3, 121.397, 31.624, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310105, '长宁区', 310100, 3, 121.425, 31.220, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310117, '松江区', 310100, 3, 121.228, 31.032, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310113, '宝山区', 310100, 3, 121.490, 31.405, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310115, '浦东新区', 310100, 3, 121.544, 31.222, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310101, '黄浦区', 310100, 3, 121.469, 31.230, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310116, '金山区', 310100, 3, 121.342, 30.742, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310106, '静安区', 310100, 3, 121.459, 31.247, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310110, '杨浦区', 310100, 3, 121.526, 31.260, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310118, '青浦区', 310100, 3, 121.124, 31.151, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310107, '普陀区', 310100, 3, 121.396, 31.250, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310109, '虹口区', 310100, 3, 121.505, 31.265, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310112, '闵行区', 310100, 3, 121.382, 31.113, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310104, '徐汇区', 310100, 3, 121.438, 31.199, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310120, '奉贤区', 310100, 3, 121.474, 30.918, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610423, '泾阳县', 610400, 3, 108.843, 34.527, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610482, '彬州市', 610400, 3, 108.709, 34.330, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610424, '乾县', 610400, 3, 108.239, 34.528, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610431, '武功县', 610400, 3, 108.200, 34.260, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610430, '淳化县', 610400, 3, 108.581, 34.799, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610422, '三原县', 610400, 3, 108.941, 34.617, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610403, '杨陵区', 610400, 3, 108.085, 34.272, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610404, '渭城区', 610400, 3, 108.737, 34.362, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610481, '兴平市', 610400, 3, 108.490, 34.299, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610402, '秦都区', 610400, 3, 108.706, 34.330, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610426, '永寿县', 610400, 3, 108.142, 34.692, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610425, '礼泉县', 610400, 3, 108.425, 34.482, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610428, '长武县', 610400, 3, 107.799, 35.206, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610429, '旬邑县', 610400, 3, 108.334, 35.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420982, '安陆市', 420900, 3, 113.689, 31.256, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420902, '孝南区', 420900, 3, 113.911, 30.917, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420921, '孝昌县', 420900, 3, 113.998, 31.258, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420984, '汉川市', 420900, 3, 113.839, 30.661, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420981, '应城市', 420900, 3, 113.563, 30.940, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420922, '大悟县', 420900, 3, 114.127, 31.561, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420923, '云梦县', 420900, 3, 113.754, 31.021, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520203, '六枝特区', 520200, 3, 105.480, 26.201, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520221, '水城县', 520200, 3, 104.958, 26.548, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520201, '钟山区', 520200, 3, 104.844, 26.575, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520281, '盘州市', 520200, 3, 104.472, 25.710, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451424, '大新县', 451400, 3, 107.201, 22.829, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451422, '宁明县', 451400, 3, 107.076, 22.140, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451481, '凭祥市', 451400, 3, 106.766, 22.094, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451425, '天等县', 451400, 3, 107.143, 23.081, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451402, '江州区', 451400, 3, 107.353, 22.405, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451423, '龙州县', 451400, 3, 106.854, 22.343, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451421, '扶绥县', 451400, 3, 107.904, 22.635, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321023, '宝应县', 321000, 3, 119.361, 33.240, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321084, '高邮市', 321000, 3, 119.459, 32.782, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321081, '仪征市', 321000, 3, 119.185, 32.272, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321003, '邗江区', 321000, 3, 119.398, 32.378, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321012, '江都区', 321000, 3, 119.570, 32.435, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321002, '广陵区', 321000, 3, 119.432, 32.395, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371082, '荣成市', 371000, 3, 122.487, 37.165, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371083, '乳山市', 371000, 3, 121.540, 36.920, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371003, '文登区', 371000, 3, 122.058, 37.194, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371002, '环翠区', 371000, 3, 122.123, 37.502, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510322, '富顺县', 510300, 3, 104.975, 29.181, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510311, '沿滩区', 510300, 3, 104.874, 29.273, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510321, '荣县', 510300, 3, 104.417, 29.445, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510302, '自流井区', 510300, 3, 104.777, 29.337, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510303, '贡井区', 510300, 3, 104.715, 29.346, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510304, '大安区', 510300, 3, 104.774, 29.364, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430724, '临澧县', 430700, 3, 111.648, 29.441, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430725, '桃源县', 430700, 3, 111.489, 28.903, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430703, '鼎城区', 430700, 3, 111.681, 29.019, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430723, '澧县', 430700, 3, 111.759, 29.633, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430726, '石门县', 430700, 3, 111.380, 29.584, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430702, '武陵区', 430700, 3, 111.683, 29.055, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430721, '安乡县', 430700, 3, 112.171, 29.411, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430722, '汉寿县', 430700, 3, 111.971, 28.906, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430781, '津市市', 430700, 3, 111.877, 29.605, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130324, '卢龙县', 130300, 3, 118.893, 39.892, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130306, '抚宁区', 130300, 3, 119.236, 39.889, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130304, '北戴河区', 130300, 3, 119.489, 39.835, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130303, '山海关区', 130300, 3, 119.776, 39.979, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130322, '昌黎县', 130300, 3, 119.163, 39.713, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130321, '青龙满族自治县', 130300, 3, 118.950, 40.408, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130302, '海港区', 130300, 3, 119.565, 39.948, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533423, '维西傈僳族自治县', 533400, 3, 99.287, 27.177, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533401, '香格里拉市', 533400, 3, 99.701, 27.830, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533422, '德钦县', 533400, 3, 98.912, 28.486, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440511, '金平区', 440500, 3, 116.703, 23.366, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440514, '潮南区', 440500, 3, 116.433, 23.250, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440523, '南澳县', 440500, 3, 117.023, 23.422, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440507, '龙湖区', 440500, 3, 116.717, 23.371, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440512, '濠江区', 440500, 3, 116.727, 23.286, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440515, '澄海区', 440500, 3, 116.756, 23.466, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440513, '潮阳区', 440500, 3, 116.602, 23.265, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350213, '翔安区', 350200, 3, 118.248, 24.619, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350203, '思明区', 350200, 3, 118.083, 24.446, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350205, '海沧区', 350200, 3, 118.033, 24.485, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350211, '集美区', 350200, 3, 118.097, 24.576, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350206, '湖里区', 350200, 3, 118.147, 24.513, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350212, '同安区', 350200, 3, 118.152, 24.723, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610528, '富平县', 610500, 3, 109.180, 34.751, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610582, '华阴市', 610500, 3, 110.092, 34.566, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610527, '白水县', 610500, 3, 109.591, 35.177, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610525, '澄城县', 610500, 3, 109.932, 35.190, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610526, '蒲城县', 610500, 3, 109.587, 34.956, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610581, '韩城市', 610500, 3, 110.443, 35.477, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610522, '潼关县', 610500, 3, 110.246, 34.544, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610523, '大荔县', 610500, 3, 109.942, 34.797, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610524, '合阳县', 610500, 3, 110.149, 35.238, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610502, '临渭区', 610500, 3, 109.493, 34.498, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610503, '华州区', 610500, 3, 109.761, 34.512, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652928, '阿瓦提县', 652900, 3, 80.373, 40.645, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652927, '乌什县', 652900, 3, 79.224, 41.215, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652923, '库车县', 652900, 3, 82.962, 41.718, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652925, '新和县', 652900, 3, 82.609, 41.548, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652924, '沙雅县', 652900, 3, 82.782, 41.222, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652922, '温宿县', 652900, 3, 80.239, 41.277, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652926, '拜城县', 652900, 3, 81.874, 41.797, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652929, '柯坪县', 652900, 3, 79.047, 40.508, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652901, '阿克苏市', 652900, 3, 80.261, 41.169, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140106, '迎泽区', 140100, 3, 112.563, 37.863, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140110, '晋源区', 140100, 3, 112.478, 37.715, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140121, '清徐县', 140100, 3, 112.359, 37.607, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140123, '娄烦县', 140100, 3, 111.797, 38.068, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140107, '杏花岭区', 140100, 3, 112.571, 37.894, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140181, '古交市', 140100, 3, 112.176, 37.907, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140122, '阳曲县', 140100, 3, 112.673, 38.058, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140108, '尖草坪区', 140100, 3, 112.487, 37.940, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140105, '小店区', 140100, 3, 112.564, 37.743, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140109, '万柏林区', 140100, 3, 112.516, 37.859, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450327, '灌阳县', 450300, 3, 111.161, 25.489, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450303, '叠彩区', 450300, 3, 110.302, 25.314, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450326, '永福县', 450300, 3, 109.983, 24.980, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450324, '全州县', 450300, 3, 111.073, 25.929, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450332, '恭城瑶族自治县', 450300, 3, 110.828, 24.832, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450311, '雁山区', 450300, 3, 110.287, 25.102, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450302, '秀峰区', 450300, 3, 110.264, 25.274, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450312, '临桂区', 450300, 3, 110.212, 25.239, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450330, '平乐县', 450300, 3, 110.643, 24.633, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450328, '龙胜各族自治县', 450300, 3, 110.011, 25.798, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450323, '灵川县', 450300, 3, 110.326, 25.410, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450325, '兴安县', 450300, 3, 110.672, 25.612, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450321, '阳朔县', 450300, 3, 110.497, 24.778, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450381, '荔浦市', 450300, 3, 110.395, 24.488, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450305, '七星区', 450300, 3, 110.318, 25.253, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450304, '象山区', 450300, 3, 110.281, 25.262, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450329, '资源县', 450300, 3, 110.653, 26.042, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511529, '屏山县', 511500, 3, 104.156, 28.708, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511526, '珙县', 511500, 3, 104.713, 28.444, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511524, '长宁县', 511500, 3, 104.914, 28.577, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511502, '翠屏区', 511500, 3, 104.645, 28.759, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511527, '筠连县', 511500, 3, 104.643, 28.752, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511503, '南溪区', 511500, 3, 104.643, 28.752, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511525, '高县', 511500, 3, 104.518, 28.435, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511523, '江安县', 511500, 3, 104.643, 28.752, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511528, '兴文县', 511500, 3, 105.237, 28.303, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511504, '叙州区', 511500, 3, 104.533, 28.690, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152222, '科尔沁右翼中旗', 152200, 3, 121.477, 45.061, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152202, '阿尔山市', 152200, 3, 119.944, 47.177, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152221, '科尔沁右翼前旗', 152200, 3, 121.953, 46.080, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152201, '乌兰浩特市', 152200, 3, 122.093, 46.073, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152224, '突泉县', 152200, 3, 121.594, 45.382, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152223, '扎赉特旗', 152200, 3, 122.900, 46.723, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131003, '广阳区', 131000, 3, 116.711, 39.523, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131082, '三河市', 131000, 3, 117.078, 39.983, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131023, '永清县', 131000, 3, 116.499, 39.322, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131025, '大城县', 131000, 3, 116.654, 38.705, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131022, '固安县', 131000, 3, 116.299, 39.438, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131081, '霸州市', 131000, 3, 116.391, 39.126, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131028, '大厂回族自治县', 131000, 3, 116.990, 39.887, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131026, '文安县', 131000, 3, 116.458, 38.873, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131002, '安次区', 131000, 3, 116.695, 39.503, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131024, '香河县', 131000, 3, 117.006, 39.761, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211021, '辽阳县', 211000, 3, 123.106, 41.205, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211005, '弓长岭区', 211000, 3, 123.420, 41.152, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211003, '文圣区', 211000, 3, 123.185, 41.263, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211004, '宏伟区', 211000, 3, 123.197, 41.218, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211011, '太子河区', 211000, 3, 123.182, 41.253, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211081, '灯塔市', 211000, 3, 123.339, 41.426, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211002, '白塔区', 211000, 3, 123.174, 41.270, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540328, '芒康县', 540300, 3, 98.593, 29.680, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540324, '丁青县', 540300, 3, 95.596, 31.412, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540326, '八宿县', 540300, 3, 96.918, 30.053, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540322, '贡觉县', 540300, 3, 98.271, 30.860, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540321, '江达县', 540300, 3, 98.218, 31.499, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540327, '左贡县', 540300, 3, 97.841, 29.671, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540330, '边坝县', 540300, 3, 94.708, 30.934, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540325, '察雅县', 540300, 3, 97.569, 30.654, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540329, '洛隆县', 540300, 3, 95.825, 30.742, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540323, '类乌齐县', 540300, 3, 96.600, 31.212, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540302, '卡若区', 540300, 3, 97.180, 31.139, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460321, '西沙群岛', 460300, 3, 112.027, 16.330, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460323, '中沙群岛的岛礁及其海域', 460300, 3, 112.339, 16.832, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460322, '南沙群岛', 460300, 3, 112.339, 16.832, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361103, '广丰区', 361100, 3, 118.191, 28.436, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361125, '横峰县', 361100, 3, 117.596, 28.407, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361130, '婺源县', 361100, 3, 117.862, 29.248, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361129, '万年县', 361100, 3, 117.058, 28.695, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361128, '鄱阳县', 361100, 3, 116.700, 29.012, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361181, '德兴市', 361100, 3, 117.579, 28.946, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361102, '信州区', 361100, 3, 117.966, 28.431, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361123, '玉山县', 361100, 3, 118.245, 28.682, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361124, '铅山县', 361100, 3, 117.709, 28.315, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361121, '上饶县', 361100, 3, 117.908, 28.449, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361126, '弋阳县', 361100, 3, 117.450, 28.378, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361127, '余干县', 361100, 3, 116.696, 28.702, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654202, '乌苏市', 654200, 3, 84.714, 44.419, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654201, '塔城市', 654200, 3, 82.979, 46.749, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654221, '额敏县', 654200, 3, 83.628, 46.525, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654224, '托里县', 654200, 3, 83.607, 45.948, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654226, '和布克赛尔蒙古自治县', 654200, 3, 85.728, 46.793, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654223, '沙湾县', 654200, 3, 85.619, 44.326, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654225, '裕民县', 654200, 3, 82.983, 46.201, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120119, '蓟州区', 120100, 3, 117.408, 40.046, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120118, '静海区', 120100, 3, 116.974, 38.948, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120113, '北辰区', 120100, 3, 117.139, 39.218, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120104, '南开区', 120100, 3, 117.151, 39.138, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120116, '滨海新区', 120100, 3, 117.781, 39.246, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120114, '武清区', 120100, 3, 117.044, 39.384, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120106, '红桥区', 120100, 3, 117.152, 39.167, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120117, '宁河区', 120100, 3, 117.827, 39.330, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120103, '河西区', 120100, 3, 117.223, 39.110, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120102, '河东区', 120100, 3, 117.252, 39.128, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120112, '津南区', 120100, 3, 117.392, 38.985, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120115, '宝坻区', 120100, 3, 117.310, 39.717, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120105, '河北区', 120100, 3, 117.197, 39.148, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120110, '东丽区', 120100, 3, 117.314, 39.087, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120101, '和平区', 120100, 3, 117.208, 39.119, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120111, '西青区', 120100, 3, 117.014, 39.142, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321282, '靖江市', 321200, 3, 120.277, 31.983, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321283, '泰兴市', 321200, 3, 120.052, 32.172, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321204, '姜堰区', 321200, 3, 120.128, 32.509, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321281, '兴化市', 321200, 3, 119.853, 32.910, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321203, '高港区', 321200, 3, 119.882, 32.319, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321202, '海陵区', 321200, 3, 119.919, 32.491, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321112, '丹徒区', 321100, 3, 119.434, 32.132, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321181, '丹阳市', 321100, 3, 119.607, 32.010, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321111, '润州区', 321100, 3, 119.412, 32.195, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321183, '句容市', 321100, 3, 119.169, 31.945, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321102, '京口区', 321100, 3, 119.470, 32.198, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321182, '扬中市', 321100, 3, 119.798, 32.235, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440804, '坡头区', 440800, 3, 110.455, 21.245, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440803, '霞山区', 440800, 3, 110.398, 21.192, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440882, '雷州市', 440800, 3, 110.097, 20.914, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440811, '麻章区', 440800, 3, 110.334, 21.263, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440825, '徐闻县', 440800, 3, 110.177, 20.325, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440823, '遂溪县', 440800, 3, 110.250, 21.377, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440881, '廉江市', 440800, 3, 110.286, 21.610, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440802, '赤坎区', 440800, 3, 110.366, 21.266, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440883, '吴川市', 440800, 3, 110.778, 21.442, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (623027, '夏河县', 623000, 3, 102.522, 35.203, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (623021, '临潭县', 623000, 3, 103.354, 34.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (623024, '迭部县', 623000, 3, 103.222, 34.056, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (623022, '卓尼县', 623000, 3, 103.507, 34.590, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (623026, '碌曲县', 623000, 3, 102.487, 34.591, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (623025, '玛曲县', 623000, 3, 102.073, 33.998, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (623023, '舟曲县', 623000, 3, 104.372, 33.785, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (623001, '合作市', 623000, 3, 102.911, 35.000, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350122, '连江县', 350100, 3, 119.540, 26.197, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350128, '平潭县', 350100, 3, 119.790, 25.499, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350103, '台江区', 350100, 3, 119.314, 26.053, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350102, '鼓楼区', 350100, 3, 119.304, 26.082, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350104, '仓山区', 350100, 3, 119.274, 26.047, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350121, '闽侯县', 350100, 3, 119.132, 26.150, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350123, '罗源县', 350100, 3, 119.550, 26.490, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350125, '永泰县', 350100, 3, 118.933, 25.867, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350124, '闽清县', 350100, 3, 118.863, 26.221, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350112, '长乐区', 350100, 3, 119.523, 25.963, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350105, '马尾区', 350100, 3, 119.456, 25.990, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350111, '晋安区', 350100, 3, 119.329, 26.082, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350181, '福清市', 350100, 3, 119.384, 25.721, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532823, '勐腊县', 532800, 3, 101.565, 21.459, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532801, '景洪市', 532800, 3, 100.772, 22.000, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532822, '勐海县', 532800, 3, 100.453, 21.957, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653125, '莎车县', 653100, 3, 77.246, 38.414, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653124, '泽普县', 653100, 3, 77.260, 38.185, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653129, '伽师县', 653100, 3, 76.724, 39.488, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653121, '疏附县', 653100, 3, 75.863, 39.375, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653127, '麦盖提县', 653100, 3, 77.648, 38.902, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653128, '岳普湖县', 653100, 3, 76.773, 39.224, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653123, '英吉沙县', 653100, 3, 76.176, 38.930, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653126, '叶城县', 653100, 3, 77.414, 37.883, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653131, '塔什库尔干塔吉克自治县', 653100, 3, 75.230, 37.772, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653101, '喀什市', 653100, 3, 75.990, 39.470, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653130, '巴楚县', 653100, 3, 78.549, 39.785, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653122, '疏勒县', 653100, 3, 76.048, 39.401, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530523, '龙陵县', 530500, 3, 98.689, 24.587, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530524, '昌宁县', 530500, 3, 99.605, 24.828, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530521, '施甸县', 530500, 3, 99.189, 24.723, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530502, '隆阳区', 530500, 3, 99.166, 25.121, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530581, '腾冲市', 530500, 3, 98.485, 25.033, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632324, '河南蒙古族自治县', 632300, 3, 101.616, 34.735, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632322, '尖扎县', 632300, 3, 102.031, 35.938, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632321, '同仁县', 632300, 3, 102.018, 35.516, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632323, '泽库县', 632300, 3, 101.467, 35.035, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450602, '港口区', 450600, 3, 108.380, 21.643, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450603, '防城区', 450600, 3, 108.353, 21.769, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450621, '上思县', 450600, 3, 107.984, 22.154, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450681, '东兴市', 450600, 3, 107.972, 21.548, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540421, '工布江达县', 540400, 3, 93.246, 29.885, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540402, '巴宜区', 540400, 3, 94.361, 29.654, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540426, '朗县', 540400, 3, 93.075, 29.046, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540423, '墨脱县', 540400, 3, 95.332, 29.326, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540424, '波密县', 540400, 3, 95.768, 29.859, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540422, '米林县', 540400, 3, 94.361, 29.649, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540425, '察隅县', 540400, 3, 97.465, 28.660, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441821, '佛冈县', 441800, 3, 113.532, 23.879, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441826, '连南瑶族自治县', 441800, 3, 112.287, 24.726, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441802, '清城区', 441800, 3, 113.063, 23.698, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441823, '阳山县', 441800, 3, 112.641, 24.465, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441882, '连州市', 441800, 3, 112.377, 24.781, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441825, '连山壮族瑶族自治县', 441800, 3, 112.094, 24.570, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441803, '清新区', 441800, 3, 113.018, 23.735, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441881, '英德市', 441800, 3, 113.402, 24.205, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331083, '玉环市', 331000, 3, 121.232, 28.136, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331022, '三门县', 331000, 3, 121.396, 29.105, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331024, '仙居县', 331000, 3, 120.735, 28.849, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331004, '路桥区', 331000, 3, 121.365, 28.583, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331081, '温岭市', 331000, 3, 121.386, 28.373, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331082, '临海市', 331000, 3, 121.145, 28.858, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331002, '椒江区', 331000, 3, 121.443, 28.674, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331003, '黄岩区', 331000, 3, 121.262, 28.650, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331023, '天台县', 331000, 3, 121.007, 29.144, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361021, '南城县', 361000, 3, 116.637, 27.570, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361003, '东乡区', 361000, 3, 116.604, 28.248, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361002, '临川区', 361000, 3, 116.371, 27.971, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361030, '广昌县', 361000, 3, 116.326, 26.837, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361028, '资溪县', 361000, 3, 117.060, 27.706, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361024, '崇仁县', 361000, 3, 116.061, 27.764, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361026, '宜黄县', 361000, 3, 116.222, 27.546, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361023, '南丰县', 361000, 3, 116.526, 27.218, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361025, '乐安县', 361000, 3, 115.830, 27.429, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361022, '黎川县', 361000, 3, 116.908, 27.282, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361027, '金溪县', 361000, 3, 116.755, 27.919, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321311, '宿豫区', 321300, 3, 118.331, 33.947, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321324, '泗洪县', 321300, 3, 118.224, 33.476, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321322, '沭阳县', 321300, 3, 118.805, 34.111, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321323, '泗阳县', 321300, 3, 118.703, 33.723, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321302, '宿城区', 321300, 3, 118.243, 33.963, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510904, '安居区', 510900, 3, 105.456, 30.355, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510922, '射洪县', 510900, 3, 105.388, 30.871, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510903, '船山区', 510900, 3, 105.568, 30.525, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510923, '大英县', 510900, 3, 105.237, 30.594, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510921, '蓬溪县', 510900, 3, 105.708, 30.758, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440703, '蓬江区', 440700, 3, 113.079, 22.595, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440705, '新会区', 440700, 3, 113.034, 22.458, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440781, '台山市', 440700, 3, 112.796, 22.252, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440704, '江海区', 440700, 3, 113.112, 22.560, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440783, '开平市', 440700, 3, 112.699, 22.376, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440784, '鹤山市', 440700, 3, 112.964, 22.765, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440785, '恩平市', 440700, 3, 112.305, 22.183, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430903, '赫山区', 430900, 3, 112.374, 28.579, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430902, '资阳区', 430900, 3, 112.324, 28.591, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430981, '沅江市', 430900, 3, 112.355, 28.846, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430922, '桃江县', 430900, 3, 112.156, 28.518, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430923, '安化县', 430900, 3, 111.213, 28.374, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430921, '南县', 430900, 3, 112.396, 29.361, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331181, '龙泉市', 331100, 3, 119.141, 28.075, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331125, '云和县', 331100, 3, 119.573, 28.116, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331127, '景宁畲族自治县', 331100, 3, 119.636, 27.973, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331121, '青田县', 331100, 3, 120.290, 28.139, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331122, '缙云县', 331100, 3, 120.092, 28.659, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331126, '庆元县', 331100, 3, 119.063, 27.619, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331102, '莲都区', 331100, 3, 119.913, 28.446, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331123, '遂昌县', 331100, 3, 119.276, 28.592, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331124, '松阳县', 331100, 3, 119.482, 28.449, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532925, '弥渡县', 532900, 3, 100.491, 25.344, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532923, '祥云县', 532900, 3, 100.551, 25.484, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532929, '云龙县', 532900, 3, 99.371, 25.886, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532922, '漾濞彝族自治县', 532900, 3, 99.958, 25.670, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532930, '洱源县', 532900, 3, 99.951, 26.111, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532932, '鹤庆县', 532900, 3, 100.176, 26.560, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532927, '巍山彝族回族自治县', 532900, 3, 100.307, 25.227, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532931, '剑川县', 532900, 3, 99.906, 26.537, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532924, '宾川县', 532900, 3, 100.575, 25.827, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532901, '大理市', 532900, 3, 100.241, 25.593, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532926, '南涧彝族自治县', 532900, 3, 100.509, 25.044, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532928, '永平县', 532900, 3, 99.541, 25.465, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530629, '威信县', 530600, 3, 105.049, 27.847, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530602, '昭阳区', 530600, 3, 103.707, 27.320, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530681, '水富市', 530600, 3, 104.416, 28.630, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530621, '鲁甸县', 530600, 3, 103.558, 27.187, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530625, '永善县', 530600, 3, 103.638, 28.229, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530628, '彝良县', 530600, 3, 104.048, 27.625, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530623, '盐津县', 530600, 3, 104.234, 28.109, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530624, '大关县', 530600, 3, 103.891, 27.748, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530622, '巧家县', 530600, 3, 102.930, 26.908, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530627, '镇雄县', 530600, 3, 104.874, 27.442, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530626, '绥江县', 530600, 3, 103.969, 28.592, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150902, '集宁区', 150900, 3, 113.124, 40.991, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150922, '化德县', 150900, 3, 114.010, 41.905, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150928, '察哈尔右翼后旗', 150900, 3, 113.191, 41.436, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150925, '凉城县', 150900, 3, 112.504, 40.532, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150926, '察哈尔右翼前旗', 150900, 3, 113.215, 40.786, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150927, '察哈尔右翼中旗', 150900, 3, 112.636, 41.277, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150981, '丰镇市', 150900, 3, 113.110, 40.437, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150921, '卓资县', 150900, 3, 112.578, 40.895, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150923, '商都县', 150900, 3, 113.578, 41.562, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150924, '兴和县', 150900, 3, 113.834, 40.872, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150929, '四子王旗', 150900, 3, 111.707, 41.533, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653001, '阿图什市', 653000, 3, 76.168, 39.716, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653024, '乌恰县', 653000, 3, 75.259, 39.719, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653023, '阿合奇县', 653000, 3, 78.446, 40.937, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653022, '阿克陶县', 653000, 3, 75.947, 39.148, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (611024, '山阳县', 611000, 3, 109.882, 33.532, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (611021, '洛南县', 611000, 3, 110.149, 34.091, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (611022, '丹凤县', 611000, 3, 110.332, 33.698, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (611023, '商南县', 611000, 3, 110.882, 33.531, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (611026, '柞水县', 611000, 3, 109.114, 33.686, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (611025, '镇安县', 611000, 3, 109.153, 33.423, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (611002, '商州区', 611000, 3, 109.941, 33.863, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632223, '海晏县', 632200, 3, 100.994, 36.896, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632224, '刚察县', 632200, 3, 100.146, 37.325, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632221, '门源回族自治县', 632200, 3, 101.622, 37.376, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632222, '祁连县', 632200, 3, 100.253, 38.177, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450521, '合浦县', 450500, 3, 109.207, 21.661, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450503, '银海区', 450500, 3, 109.140, 21.449, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450502, '海城区', 450500, 3, 109.117, 21.475, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450512, '铁山港区', 450500, 3, 109.422, 21.529, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320116, '六合区', 320100, 3, 118.821, 32.322, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320115, '江宁区', 320100, 3, 118.840, 31.954, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320118, '高淳区', 320100, 3, 118.892, 31.328, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320117, '溧水区', 320100, 3, 119.028, 31.651, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320114, '雨花台区', 320100, 3, 118.779, 31.991, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320113, '栖霞区', 320100, 3, 118.909, 32.096, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320105, '建邺区', 320100, 3, 118.732, 32.004, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320111, '浦口区', 320100, 3, 118.628, 32.059, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320106, '鼓楼区', 320100, 3, 118.770, 32.066, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320102, '玄武区', 320100, 3, 118.798, 32.049, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320104, '秦淮区', 320100, 3, 118.795, 32.039, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540102, '城关区', 540100, 3, 91.140, 29.655, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540123, '尼木县', 540100, 3, 90.165, 29.432, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540104, '达孜区', 540100, 3, 91.350, 29.669, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540121, '林周县', 540100, 3, 91.265, 29.894, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540124, '曲水县', 540100, 3, 90.744, 29.353, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540103, '堆龙德庆区', 540100, 3, 91.141, 29.646, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540122, '当雄县', 540100, 3, 91.101, 30.473, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540127, '墨竹工卡县', 540100, 3, 91.731, 29.834, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110116, '怀柔区', 110100, 3, 116.642, 40.316, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110106, '丰台区', 110100, 3, 116.287, 39.858, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110115, '大兴区', 110100, 3, 116.341, 39.785, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110119, '延庆区', 110100, 3, 115.975, 40.457, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110105, '朝阳区', 110100, 3, 116.601, 39.949, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110107, '石景山区', 110100, 3, 116.223, 39.907, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110112, '通州区', 110100, 3, 116.656, 39.910, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110108, '海淀区', 110100, 3, 116.330, 39.972, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110117, '平谷区', 110100, 3, 117.121, 40.141, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110102, '西城区', 110100, 3, 116.366, 39.912, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110111, '房山区', 110100, 3, 116.143, 39.749, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110118, '密云区', 110100, 3, 116.843, 40.377, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110101, '东城区', 110100, 3, 116.416, 39.928, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110114, '昌平区', 110100, 3, 116.231, 40.221, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110113, '顺义区', 110100, 3, 116.655, 40.130, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110109, '门头沟区', 110100, 3, 116.102, 39.941, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654028, '尼勒克县', 654000, 3, 82.512, 43.800, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654003, '奎屯市', 654000, 3, 84.903, 44.427, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654026, '昭苏县', 654000, 3, 81.131, 43.157, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654027, '特克斯县', 654000, 3, 81.836, 43.217, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654021, '伊宁县', 654000, 3, 81.527, 43.977, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654022, '察布查尔锡伯自治县', 654000, 3, 81.151, 43.841, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654002, '伊宁市', 654000, 3, 81.278, 43.909, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654004, '霍尔果斯市', 654000, 3, 80.421, 44.202, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654023, '霍城县', 654000, 3, 80.874, 44.054, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654024, '巩留县', 654000, 3, 82.232, 43.483, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654025, '新源县', 654000, 3, 83.261, 43.430, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141028, '吉县', 141000, 3, 110.682, 36.098, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141023, '襄汾县', 141000, 3, 111.442, 35.876, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141029, '乡宁县', 141000, 3, 110.847, 35.970, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141081, '侯马市', 141000, 3, 111.372, 35.619, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141082, '霍州市', 141000, 3, 111.755, 36.569, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141032, '永和县', 141000, 3, 110.632, 36.760, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141031, '隰县', 141000, 3, 110.941, 36.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141026, '安泽县', 141000, 3, 112.250, 36.148, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141027, '浮山县', 141000, 3, 111.849, 35.968, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141030, '大宁县', 141000, 3, 110.753, 36.465, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141034, '汾西县', 141000, 3, 111.564, 36.653, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141024, '洪洞县', 141000, 3, 111.675, 36.254, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141025, '古县', 141000, 3, 111.920, 36.267, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141002, '尧都区', 141000, 3, 111.580, 36.079, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141033, '蒲县', 141000, 3, 111.096, 36.412, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141021, '曲沃县', 141000, 3, 111.476, 35.641, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141022, '翼城县', 141000, 3, 111.719, 35.739, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411103, '郾城区', 411100, 3, 114.007, 33.587, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411102, '源汇区', 411100, 3, 113.911, 33.582, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411121, '舞阳县', 411100, 3, 113.609, 33.438, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411104, '召陵区', 411100, 3, 114.094, 33.587, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411122, '临颍县', 411100, 3, 113.931, 33.827, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510824, '苍溪县', 510800, 3, 105.935, 31.732, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510812, '朝天区', 510800, 3, 105.890, 32.644, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510823, '剑阁县', 510800, 3, 105.525, 32.288, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510822, '青川县', 510800, 3, 105.239, 32.575, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510811, '昭化区', 510800, 3, 105.964, 32.323, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510802, '利州区', 510800, 3, 105.845, 32.434, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510821, '旺苍县', 510800, 3, 106.290, 32.229, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520521, '大方县', 520500, 3, 105.612, 27.137, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520524, '织金县', 520500, 3, 105.771, 26.663, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520525, '纳雍县', 520500, 3, 105.383, 26.778, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520527, '赫章县', 520500, 3, 104.727, 27.123, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520523, '金沙县', 520500, 3, 106.220, 27.459, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520526, '威宁彝族回族苗族自治县', 520500, 3, 104.279, 26.856, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520502, '七星关区', 520500, 3, 105.305, 27.298, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520522, '黔西县', 520500, 3, 106.034, 27.008, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445102, '湘桥区', 445100, 3, 116.629, 23.675, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445122, '饶平县', 445100, 3, 117.004, 23.664, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445103, '潮安区', 445100, 3, 116.678, 23.463, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450821, '平南县', 450800, 3, 110.392, 23.539, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450803, '港南区', 450800, 3, 109.600, 23.076, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450802, '港北区', 450800, 3, 109.572, 23.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450804, '覃塘区', 450800, 3, 109.453, 23.127, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450881, '桂平市', 450800, 3, 110.079, 23.394, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532624, '麻栗坡县', 532600, 3, 104.703, 23.126, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532623, '西畴县', 532600, 3, 104.673, 23.438, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532625, '马关县', 532600, 3, 104.394, 23.013, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532622, '砚山县', 532600, 3, 104.337, 23.606, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532626, '丘北县', 532600, 3, 104.196, 24.042, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532628, '富宁县', 532600, 3, 105.631, 23.625, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532601, '文山市', 532600, 3, 104.233, 23.386, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532627, '广南县', 532600, 3, 105.055, 24.046, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530322, '陆良县', 530300, 3, 103.667, 25.030, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530325, '富源县', 530300, 3, 104.255, 25.674, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530304, '马龙区', 530300, 3, 103.578, 25.428, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530326, '会泽县', 530300, 3, 103.297, 26.418, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530324, '罗平县', 530300, 3, 104.309, 24.885, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530302, '麒麟区', 530300, 3, 103.805, 25.495, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530323, '师宗县', 530300, 3, 103.985, 24.822, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530303, '沾益区', 530300, 3, 103.796, 25.490, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530381, '宣威市', 530300, 3, 104.104, 26.220, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341024, '祁门县', 341000, 3, 117.717, 29.854, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341021, '歙县', 341000, 3, 118.415, 29.861, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341004, '徽州区', 341000, 3, 118.337, 29.827, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341023, '黟县', 341000, 3, 117.938, 29.925, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341002, '屯溪区', 341000, 3, 118.315, 29.696, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341022, '休宁县', 341000, 3, 118.199, 29.789, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341003, '黄山区', 341000, 3, 118.142, 30.273, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230902, '新兴区', 230900, 3, 130.932, 45.816, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230921, '勃利县', 230900, 3, 130.592, 45.756, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230904, '茄子河区', 230900, 3, 131.068, 45.785, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230903, '桃山区', 230900, 3, 131.020, 45.766, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320281, '江阴市', 320200, 3, 120.285, 31.921, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320282, '宜兴市', 320200, 3, 119.823, 31.341, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320213, '梁溪区', 320200, 3, 120.297, 31.576, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320206, '惠山区', 320200, 3, 120.299, 31.681, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320214, '新吴区', 320200, 3, 120.353, 31.551, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320211, '滨湖区', 320200, 3, 120.272, 31.470, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320205, '锡山区', 320200, 3, 120.358, 31.590, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131124, '饶阳县', 131100, 3, 115.726, 38.236, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131121, '枣强县', 131100, 3, 115.724, 37.513, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131182, '深州市', 131100, 3, 115.560, 38.002, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131103, '冀州区', 131100, 3, 115.579, 37.551, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131126, '故城县', 131100, 3, 115.966, 37.347, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131102, '桃城区', 131100, 3, 115.675, 37.735, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131123, '武强县', 131100, 3, 115.982, 38.041, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131122, '武邑县', 131100, 3, 115.888, 37.802, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131128, '阜城县', 131100, 3, 116.144, 37.869, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131125, '安平县', 131100, 3, 115.519, 38.235, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131127, '景县', 131100, 3, 116.271, 37.692, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540227, '谢通门县', 540200, 3, 88.262, 29.433, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540225, '拉孜县', 540200, 3, 87.637, 29.082, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540235, '聂拉木县', 540200, 3, 85.982, 28.155, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540231, '定结县', 540200, 3, 87.766, 28.364, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540228, '白朗县', 540200, 3, 89.098, 28.814, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540237, '岗巴县', 540200, 3, 88.520, 28.275, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540202, '桑珠孜区', 540200, 3, 88.887, 29.270, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540226, '昂仁县', 540200, 3, 87.236, 29.295, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540223, '定日县', 540200, 3, 87.126, 28.659, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540233, '亚东县', 540200, 3, 88.907, 27.485, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540224, '萨迦县', 540200, 3, 88.022, 28.900, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540229, '仁布县', 540200, 3, 89.842, 29.231, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540234, '吉隆县', 540200, 3, 85.298, 28.852, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540222, '江孜县', 540200, 3, 89.606, 28.912, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540236, '萨嘎县', 540200, 3, 85.233, 29.329, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540221, '南木林县', 540200, 3, 89.099, 29.682, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540232, '仲巴县', 540200, 3, 84.032, 29.770, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540230, '康马县', 540200, 3, 89.682, 28.556, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (542522, '札达县', 542500, 3, 79.803, 31.479, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (542527, '措勤县', 542500, 3, 85.159, 31.017, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (542523, '噶尔县', 542500, 3, 80.096, 32.491, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (542524, '日土县', 542500, 3, 79.732, 33.381, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (542526, '改则县', 542500, 3, 84.063, 32.303, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (542525, '革吉县', 542500, 3, 81.145, 32.387, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (542521, '普兰县', 542500, 3, 81.176, 30.294, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330104, '江干区', 330100, 3, 120.214, 30.263, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330110, '余杭区', 330100, 3, 120.245, 30.410, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330122, '桐庐县', 330100, 3, 119.691, 29.794, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330111, '富阳区', 330100, 3, 119.960, 30.049, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330182, '建德市', 330100, 3, 119.281, 29.475, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330112, '临安区', 330100, 3, 119.725, 30.234, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330103, '下城区', 330100, 3, 120.181, 30.282, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330106, '西湖区', 330100, 3, 120.130, 30.259, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330109, '萧山区', 330100, 3, 120.264, 30.184, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330108, '滨江区', 330100, 3, 120.147, 30.162, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330102, '上城区', 330100, 3, 120.184, 30.254, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330105, '拱墅区', 330100, 3, 120.141, 30.319, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330127, '淳安县', 330100, 3, 119.042, 29.609, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411025, '襄城县', 411000, 3, 113.489, 33.866, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411003, '建安区', 411000, 3, 113.823, 34.125, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411081, '禹州市', 411000, 3, 113.488, 34.141, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411082, '长葛市', 411000, 3, 113.820, 34.194, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411002, '魏都区', 411000, 3, 113.823, 34.025, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411024, '鄢陵县', 411000, 3, 114.177, 34.102, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510722, '三台县', 510700, 3, 105.095, 31.096, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510726, '北川羌族自治县', 510700, 3, 104.468, 31.617, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510725, '梓潼县', 510700, 3, 105.171, 31.643, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510723, '盐亭县', 510700, 3, 105.389, 31.208, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510705, '安州区', 510700, 3, 104.567, 31.535, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510704, '游仙区', 510700, 3, 104.766, 31.474, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510781, '江油市', 510700, 3, 104.746, 31.778, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510727, '平武县', 510700, 3, 104.556, 32.410, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510703, '涪城区', 510700, 3, 104.750, 31.471, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440904, '电白区', 440900, 3, 111.014, 21.514, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440983, '信宜市', 440900, 3, 110.947, 22.354, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440981, '高州市', 440900, 3, 110.853, 21.918, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440982, '化州市', 440900, 3, 110.640, 21.664, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440902, '茂南区', 440900, 3, 110.918, 21.641, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621002, '西峰区', 621000, 3, 107.651, 35.731, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621021, '庆城县', 621000, 3, 107.882, 36.016, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621026, '宁县', 621000, 3, 107.928, 35.502, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621022, '环县', 621000, 3, 107.309, 36.568, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621024, '合水县', 621000, 3, 108.020, 35.819, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621027, '镇原县', 621000, 3, 107.201, 35.677, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621025, '正宁县', 621000, 3, 108.360, 35.492, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621023, '华池县', 621000, 3, 107.990, 36.461, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520625, '印江土家族苗族自治县', 520600, 3, 108.410, 27.994, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520624, '思南县', 520600, 3, 108.254, 27.938, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520603, '万山区', 520600, 3, 109.214, 27.518, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520602, '碧江区', 520600, 3, 109.181, 27.691, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520621, '江口县', 520600, 3, 108.840, 27.700, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520622, '玉屏侗族自治县', 520600, 3, 108.915, 27.243, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520626, '德江县', 520600, 3, 108.120, 28.264, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520628, '松桃苗族自治县', 520600, 3, 109.203, 28.154, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520623, '石阡县', 520600, 3, 108.224, 27.514, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520627, '沿河土家族自治县', 520600, 3, 108.504, 28.564, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530425, '易门县', 530400, 3, 102.163, 24.672, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530422, '澄江县', 530400, 3, 102.908, 24.674, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530402, '红塔区', 530400, 3, 102.540, 24.341, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530423, '通海县', 530400, 3, 102.760, 24.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530426, '峨山彝族自治县', 530400, 3, 102.406, 24.169, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530427, '新平彝族傣族自治县', 530400, 3, 101.990, 24.070, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530403, '江川区', 530400, 3, 102.754, 24.288, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530428, '元江哈尼族彝族傣族自治县', 530400, 3, 101.998, 23.597, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530424, '华宁县', 530400, 3, 102.929, 24.193, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450721, '灵山县', 450700, 3, 109.291, 22.417, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450722, '浦北县', 450700, 3, 109.557, 22.272, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450703, '钦北区', 450700, 3, 108.449, 22.133, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450702, '钦南区', 450700, 3, 108.657, 21.939, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341126, '凤阳县', 341100, 3, 117.532, 32.875, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341102, '琅琊区', 341100, 3, 118.306, 32.295, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341124, '全椒县', 341100, 3, 118.273, 32.085, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341182, '明光市', 341100, 3, 118.018, 32.782, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341103, '南谯区', 341100, 3, 118.297, 32.330, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341181, '天长市', 341100, 3, 119.005, 32.668, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341125, '定远县', 341100, 3, 117.699, 32.531, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341122, '来安县', 341100, 3, 118.436, 32.452, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320311, '泉山区', 320300, 3, 117.194, 34.244, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320322, '沛县', 320300, 3, 116.938, 34.722, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320305, '贾汪区', 320300, 3, 117.467, 34.436, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320303, '云龙区', 320300, 3, 117.252, 34.253, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320381, '新沂市', 320300, 3, 118.355, 34.370, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320324, '睢宁县', 320300, 3, 117.942, 33.913, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320321, '丰县', 320300, 3, 116.595, 34.694, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320312, '铜山区', 320300, 3, 117.169, 34.181, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320302, '鼓楼区', 320300, 3, 117.186, 34.289, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320382, '邳州市', 320300, 3, 118.013, 34.339, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230804, '前进区', 230800, 3, 130.375, 46.814, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230803, '向阳区', 230800, 3, 130.365, 46.808, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230828, '汤原县', 230800, 3, 129.905, 46.731, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230822, '桦南县', 230800, 3, 130.553, 46.239, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230883, '抚远市', 230800, 3, 134.308, 48.365, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230826, '桦川县', 230800, 3, 130.719, 47.023, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230881, '同江市', 230800, 3, 132.511, 47.643, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230882, '富锦市', 230800, 3, 132.038, 47.250, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230811, '郊区', 230800, 3, 130.327, 46.810, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230805, '东风区', 230800, 3, 130.404, 46.823, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511902, '巴州区', 511900, 3, 106.769, 31.851, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511903, '恩阳区', 511900, 3, 106.655, 31.787, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511923, '平昌县', 511900, 3, 107.104, 31.561, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511922, '南江县', 511900, 3, 106.829, 32.347, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511921, '通江县', 511900, 3, 107.245, 31.912, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-21 10:23:06', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513200, '阿坝藏族羌族自治州', 510000, 2, 102.225, 31.899, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632800, '海西蒙古族藏族自治州', 630000, 2, 97.370, 37.377, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370900, '泰安市', 370000, 2, 117.088, 36.200, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421100, '黄冈市', 420000, 2, 114.872, 30.454, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330200, '宁波市', 330000, 2, 121.550, 29.875, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640300, '吴忠市', 640000, 2, 106.198, 37.997, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411300, '南阳市', 410000, 2, 112.528, 32.991, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650100, '乌鲁木齐市', 650000, 2, 87.617, 43.826, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445300, '云浮市', 440000, 2, 112.044, 22.915, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530900, '临沧市', 530000, 2, 100.080, 23.878, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152900, '阿拉善盟', 150000, 2, 105.729, 38.852, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150600, '鄂尔多斯市', 150000, 2, 109.781, 39.608, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620800, '平凉市', 620000, 2, 106.665, 35.543, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341200, '阜阳市', 340000, 2, 115.814, 32.890, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140800, '运城市', 140000, 2, 111.008, 35.026, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410100, '郑州市', 410000, 2, 113.625, 34.747, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320400, '常州市', 320000, 2, 119.974, 31.811, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230700, '伊春市', 230000, 2, 128.841, 47.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370800, '济宁市', 370000, 2, 116.587, 35.415, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632700, '玉树藏族自治州', 630000, 2, 97.092, 33.012, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421000, '荆州市', 420000, 2, 112.240, 30.335, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330300, '温州市', 330000, 2, 120.699, 27.994, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340100, '合肥市', 340000, 2, 117.227, 31.821, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640200, '石嘴山市', 640000, 2, 106.383, 38.983, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411200, '三门峡市', 410000, 2, 111.200, 34.772, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (512000, '资阳市', 510000, 2, 104.628, 30.129, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220800, '白城市', 220000, 2, 122.839, 45.620, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640100, '银川市', 640000, 2, 106.231, 38.487, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652300, '昌吉回族自治州', 650000, 2, 87.308, 44.011, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130900, '沧州市', 130000, 2, 116.839, 38.304, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445200, '揭阳市', 440000, 2, 116.373, 23.550, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230600, '大庆市', 230000, 2, 125.104, 46.589, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450900, '玉林市', 450000, 2, 110.165, 22.636, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150500, '通辽市', 150000, 2, 122.243, 43.653, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620900, '酒泉市', 620000, 2, 98.494, 39.732, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341300, '宿州市', 340000, 2, 116.964, 33.646, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320500, '苏州市', 320000, 2, 120.585, 31.299, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140700, '晋中市', 140000, 2, 112.753, 37.687, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (433100, '湘西土家族苗族自治州', 430000, 2, 109.739, 28.312, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540500, '山南市', 540000, 2, 91.773, 29.237, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (429021, '神农架林区', 420000, 2, 110.676, 31.745, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410400, '平顶山市', 410000, 2, 113.193, 33.766, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330400, '嘉兴市', 330000, 2, 120.755, 30.746, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632600, '果洛藏族自治州', 630000, 2, 100.245, 34.471, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (442000, '中山市', 440000, 2, 113.393, 22.518, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370700, '潍坊市', 370000, 2, 119.162, 36.707, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210900, '阜新市', 210000, 2, 121.670, 42.022, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420200, '黄石市', 420000, 2, 115.039, 30.200, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340200, '芜湖市', 340000, 2, 118.433, 31.353, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411500, '信阳市', 410000, 2, 114.091, 32.147, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420100, '武汉市', 420000, 2, 114.305, 30.593, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220700, '松原市', 220000, 2, 124.825, 45.142, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230500, '双鸭山市', 230000, 2, 131.159, 46.647, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150800, '巴彦淖尔市', 150000, 2, 107.388, 40.743, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530700, '丽江市', 530000, 2, 100.228, 26.855, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421300, '随州市', 420000, 2, 113.382, 31.690, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630200, '海东市', 630000, 2, 102.104, 36.502, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360900, '宜春市', 360000, 2, 114.417, 27.816, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320600, '南通市', 320000, 2, 120.894, 31.980, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431100, '永州市', 430000, 2, 111.613, 26.420, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540600, '那曲市', 540000, 2, 91.117, 29.647, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410300, '洛阳市', 410000, 2, 112.454, 34.620, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330500, '湖州市', 330000, 2, 120.087, 30.894, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632500, '海南藏族自治州', 630000, 2, 100.620, 36.286, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (421200, '咸宁市', 420000, 2, 114.322, 29.841, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370600, '烟台市', 370000, 2, 121.448, 37.464, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654300, '阿勒泰地区', 650000, 2, 88.141, 47.845, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210800, '营口市', 210000, 2, 122.235, 40.667, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (419001, '济源市', 410000, 2, 112.602, 35.069, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340300, '蚌埠市', 340000, 2, 117.390, 32.916, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411400, '商丘市', 410000, 2, 115.656, 34.414, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220600, '白山市', 220000, 2, 126.424, 41.940, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530800, '普洱市', 530000, 2, 100.967, 22.825, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230400, '鹤岗市', 230000, 2, 130.298, 47.350, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (630100, '西宁市', 630000, 2, 101.778, 36.617, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150700, '呼伦贝尔市', 150000, 2, 119.766, 49.212, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653200, '和田地区', 650000, 2, 79.922, 37.114, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320700, '连云港市', 320000, 2, 119.222, 34.597, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410200, '开封市', 410000, 2, 114.308, 34.797, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140900, '忻州市', 140000, 2, 112.734, 38.417, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360800, '吉安市', 360000, 2, 114.993, 27.113, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (232700, '大兴安岭地区', 230000, 2, 124.711, 52.335, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341500, '六安市', 340000, 2, 116.522, 31.734, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431000, '郴州市', 430000, 2, 113.015, 25.771, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431300, '娄底市', 430000, 2, 111.993, 27.700, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620300, '金昌市', 620000, 2, 102.188, 38.520, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511300, '南充市', 510000, 2, 106.111, 30.838, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410600, '鹤壁市', 410000, 2, 114.297, 35.747, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330600, '绍兴市', 330000, 2, 120.580, 30.030, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370500, '东营市', 370000, 2, 118.675, 37.435, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210700, '锦州市', 210000, 2, 121.127, 41.095, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340400, '淮南市', 340000, 2, 117.000, 32.625, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430200, '株洲市', 430000, 2, 113.134, 27.828, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350900, '宁德市', 350000, 2, 119.548, 26.666, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650500, '哈密市', 650000, 2, 93.515, 42.819, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411700, '驻马店市', 410000, 2, 114.022, 33.012, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130600, '保定市', 130000, 2, 115.465, 38.874, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533100, '德宏傣族景颇族自治州', 530000, 2, 98.585, 24.433, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220500, '通化市', 220000, 2, 125.940, 41.728, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652800, '巴音郭楞蒙古自治州', 650000, 2, 86.145, 41.764, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371700, '菏泽市', 370000, 2, 115.481, 35.234, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610600, '延安市', 610000, 2, 109.490, 36.585, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230300, '鸡西市', 230000, 2, 130.969, 45.295, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140400, '长治市', 140000, 2, 113.116, 36.195, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150200, '包头市', 150000, 2, 109.840, 40.657, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152500, '锡林郭勒盟', 150000, 2, 116.048, 43.933, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320800, '淮安市', 320000, 2, 119.015, 33.610, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620400, '白银市', 620000, 2, 104.139, 36.545, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360700, '赣州市', 360000, 2, 114.935, 25.832, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341600, '亳州市', 340000, 2, 115.779, 33.845, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (431200, '怀化市', 430000, 2, 109.998, 27.555, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410500, '安阳市', 410000, 2, 114.392, 36.098, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330700, '金华市', 330000, 2, 119.647, 29.079, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370400, '枣庄市', 370000, 2, 117.324, 34.810, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (429006, '天门市', 420000, 2, 113.167, 30.664, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (429005, '潜江市', 420000, 2, 112.900, 30.402, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (429004, '仙桃市', 420000, 2, 113.443, 30.328, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420300, '十堰市', 420000, 2, 110.798, 32.629, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522300, '黔西南布依族苗族自治州', 520000, 2, 104.906, 25.088, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340500, '马鞍山市', 340000, 2, 118.507, 31.670, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210600, '丹东市', 210000, 2, 124.355, 40.000, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430100, '长沙市', 430000, 2, 112.939, 28.228, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350800, '龙岩市', 350000, 2, 117.018, 25.075, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650400, '吐鲁番市', 650000, 2, 89.190, 42.951, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510100, '成都市', 510000, 2, 104.067, 30.572, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130500, '邢台市', 130000, 2, 114.505, 37.071, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411600, '周口市', 410000, 2, 114.697, 33.626, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371600, '滨州市', 370000, 2, 117.971, 37.382, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610700, '汉中市', 610000, 2, 107.023, 33.067, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652700, '博尔塔拉蒙古自治州', 650000, 2, 82.066, 44.906, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220400, '辽源市', 220000, 2, 125.144, 42.888, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230200, '齐齐哈尔市', 230000, 2, 123.918, 47.354, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320900, '盐城市', 320000, 2, 120.164, 33.347, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140300, '阳泉市', 140000, 2, 113.581, 37.857, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150100, '呼和浩特市', 150000, 2, 111.749, 40.843, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341700, '池州市', 340000, 2, 117.492, 30.665, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620500, '天水市', 620000, 2, 105.725, 34.581, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360600, '鹰潭市', 360000, 2, 117.069, 28.260, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410800, '焦作市', 410000, 2, 113.242, 35.216, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370300, '淄博市', 370000, 2, 118.055, 36.813, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330800, '衢州市', 330000, 2, 118.859, 28.970, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511100, '乐山市', 510000, 2, 103.766, 29.552, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513400, '凉山彝族自治州', 510000, 2, 102.267, 27.882, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441300, '惠州市', 440000, 2, 114.416, 23.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420600, '襄阳市', 420000, 2, 112.122, 32.009, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231200, '绥化市', 230000, 2, 126.969, 46.654, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640500, '中卫市', 640000, 2, 105.197, 37.500, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451100, '贺州市', 450000, 2, 111.567, 24.404, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340600, '淮北市', 340000, 2, 116.798, 33.956, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210500, '本溪市', 210000, 2, 123.766, 41.294, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350700, '南平市', 350000, 2, 118.178, 26.642, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430400, '衡阳市', 430000, 2, 112.572, 26.893, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130800, '承德市', 130000, 2, 117.962, 40.954, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220300, '四平市', 220000, 2, 124.350, 43.166, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371500, '聊城市', 370000, 2, 115.985, 36.457, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440200, '韶关市', 440000, 2, 113.598, 24.810, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610800, '榆林市', 610000, 2, 109.735, 38.285, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (659005, '北屯市', 650000, 2, 87.833, 47.327, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (659004, '五家渠市', 650000, 2, 87.543, 44.167, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (659003, '图木舒克市', 650000, 2, 79.074, 39.869, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (659002, '阿拉尔市', 650000, 2, 81.281, 40.548, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341800, '宣城市', 340000, 2, 118.759, 30.941, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (659009, '昆玉市', 650000, 2, 79.291, 37.210, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230100, '哈尔滨市', 230000, 2, 126.535, 45.804, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (659008, '可克达拉市', 650000, 2, 81.045, 43.945, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500200, '重庆郊县', 500000, 2, 106.552, 29.563, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (659007, '双河市', 650000, 2, 82.354, 44.841, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150400, '赤峰市', 150000, 2, 118.887, 42.258, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (659006, '铁门关市', 650000, 2, 85.670, 41.863, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140600, '朔州市', 140000, 2, 112.433, 39.332, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (659001, '石河子市', 650000, 2, 86.080, 44.305, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620600, '武威市', 620000, 2, 102.638, 37.928, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (622900, '临夏回族自治州', 620000, 2, 103.211, 35.601, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360500, '新余市', 360000, 2, 114.917, 27.818, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410700, '新乡市', 410000, 2, 113.927, 35.303, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511000, '内江市', 510000, 2, 105.058, 29.580, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370200, '青岛市', 370000, 2, 120.383, 36.067, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330900, '舟山市', 330000, 2, 122.207, 29.985, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (513300, '甘孜藏族自治州', 510000, 2, 101.962, 30.050, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441200, '肇庆市', 440000, 2, 112.465, 23.047, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (422800, '恩施土家族苗族自治州', 420000, 2, 109.488, 30.272, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420500, '宜昌市', 420000, 2, 111.286, 30.692, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340700, '铜陵市', 340000, 2, 117.812, 30.945, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210400, '抚顺市', 210000, 2, 123.957, 41.881, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231100, '黑河市', 230000, 2, 127.529, 50.245, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640400, '固原市', 640000, 2, 106.243, 36.016, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451000, '百色市', 450000, 2, 106.618, 23.902, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350600, '漳州市', 350000, 2, 117.647, 24.513, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371400, '德州市', 370000, 2, 116.357, 37.434, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430300, '湘潭市', 430000, 2, 112.944, 27.830, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650200, '克拉玛依市', 650000, 2, 84.889, 45.580, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610900, '安康市', 610000, 2, 109.029, 32.685, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130700, '张家口市', 130000, 2, 114.888, 40.824, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220200, '吉林市', 220000, 2, 126.550, 43.838, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440100, '广州市', 440000, 2, 113.264, 23.129, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360400, '九江市', 360000, 2, 116.002, 29.705, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500100, '重庆城区', 500000, 2, 106.552, 29.563, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140500, '晋城市', 140000, 2, 112.852, 35.491, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150300, '乌海市', 150000, 2, 106.794, 39.655, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620700, '张掖市', 620000, 2, 100.450, 38.926, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370100, '济南市', 370000, 2, 117.120, 36.651, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530100, '昆明市', 530000, 2, 102.833, 24.880, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441500, '汕尾市', 440000, 2, 115.375, 22.786, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610100, '西安市', 610000, 2, 108.940, 34.342, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340800, '安庆市', 340000, 2, 117.064, 30.543, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420800, '荆门市', 420000, 2, 112.199, 31.035, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (231000, '牡丹江市', 230000, 2, 129.633, 44.552, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210300, '鞍山市', 210000, 2, 122.994, 41.109, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510600, '德阳市', 510000, 2, 104.398, 31.127, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451300, '来宾市', 450000, 2, 109.221, 23.750, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621100, '定西市', 620000, 2, 104.626, 35.581, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371300, '临沂市', 370000, 2, 118.356, 35.105, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430600, '岳阳市', 430000, 2, 113.129, 29.357, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130200, '唐山市', 130000, 2, 118.180, 39.631, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520300, '遵义市', 520000, 2, 106.927, 27.726, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (222400, '延边朝鲜族自治州', 220000, 2, 129.509, 42.891, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220100, '长春市', 220000, 2, 125.324, 43.817, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522600, '黔东南苗族侗族自治州', 520000, 2, 107.983, 26.583, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440400, '珠海市', 440000, 2, 113.577, 22.271, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350500, '泉州市', 350000, 2, 118.676, 24.874, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360300, '萍乡市', 360000, 2, 113.855, 27.623, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211400, '葫芦岛市', 210000, 2, 120.837, 40.711, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511800, '雅安市', 510000, 2, 103.013, 29.981, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450200, '柳州市', 450000, 2, 109.416, 24.326, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410900, '濮阳市', 410000, 2, 115.029, 35.762, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441400, '梅州市', 440000, 2, 116.122, 24.289, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141100, '吕梁市', 140000, 2, 111.144, 37.518, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610200, '铜川市', 610000, 2, 108.945, 34.897, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420700, '鄂州市', 420000, 2, 114.895, 30.392, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510500, '泸州市', 510000, 2, 105.442, 28.872, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210200, '大连市', 210000, 2, 121.615, 38.914, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451200, '河池市', 450000, 2, 108.085, 24.693, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621200, '陇南市', 620000, 2, 104.922, 33.401, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130100, '石家庄市', 130000, 2, 114.515, 38.042, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430500, '邵阳市', 430000, 2, 111.468, 27.239, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520400, '安顺市', 520000, 2, 105.948, 26.253, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (522700, '黔南布依族苗族自治州', 520000, 2, 107.522, 26.254, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440300, '深圳市', 440000, 2, 114.058, 22.543, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350400, '三明市', 350000, 2, 117.639, 26.263, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360200, '景德镇市', 360000, 2, 117.178, 29.269, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532500, '红河哈尼族彝族自治州', 530000, 2, 103.375, 23.363, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511700, '达州市', 510000, 2, 107.468, 31.210, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211300, '朝阳市', 210000, 2, 120.450, 41.574, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450100, '南宁市', 450000, 2, 108.367, 22.817, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620100, '兰州市', 620000, 2, 103.834, 36.061, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460100, '海口市', 460000, 2, 110.198, 20.044, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441700, '阳江市', 440000, 2, 111.982, 21.858, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610300, '宝鸡市', 610000, 2, 107.238, 34.362, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520100, '贵阳市', 520000, 2, 106.630, 26.648, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510400, '攀枝花市', 510000, 2, 101.719, 26.582, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210100, '沈阳市', 210000, 2, 123.431, 41.806, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371100, '日照市', 370000, 2, 119.527, 35.416, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130400, '邯郸市', 130000, 2, 114.539, 36.626, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430800, '张家界市', 430000, 2, 110.479, 29.117, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440600, '佛山市', 440000, 2, 113.121, 23.022, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533300, '怒江傈僳族自治州', 530000, 2, 98.853, 25.853, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350300, '莆田市', 350000, 2, 119.008, 25.454, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360100, '南昌市', 360000, 2, 115.858, 28.683, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140200, '大同市', 140000, 2, 113.300, 40.077, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450400, '梧州市', 450000, 2, 111.279, 23.477, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211200, '铁岭市', 210000, 2, 123.726, 42.224, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511600, '广安市', 510000, 2, 106.633, 30.456, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620200, '嘉峪关市', 620000, 2, 98.289, 39.773, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211100, '盘锦市', 210000, 2, 122.071, 41.120, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511400, '眉山市', 510000, 2, 103.849, 30.075, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532300, '楚雄彝族自治州', 530000, 2, 101.528, 25.046, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441600, '河源市', 440000, 2, 114.700, 23.744, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460200, '三亚市', 460000, 2, 109.512, 18.253, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (310100, '上海城区', 310000, 2, 121.474, 31.230, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469028, '陵水黎族自治县', 460000, 2, 110.038, 18.506, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469027, '乐东黎族自治县', 460000, 2, 109.173, 18.750, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610400, '咸阳市', 610000, 2, 108.709, 34.330, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469026, '昌江黎族自治县', 460000, 2, 109.056, 19.298, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469025, '白沙黎族自治县', 460000, 2, 109.451, 19.225, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469024, '临高县', 460000, 2, 109.691, 19.912, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420900, '孝感市', 420000, 2, 113.917, 30.925, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469023, '澄迈县', 460000, 2, 110.007, 19.739, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469022, '屯昌县', 460000, 2, 110.103, 19.352, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469021, '定安县', 460000, 2, 110.359, 19.681, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520200, '六盘水市', 520000, 2, 104.830, 26.593, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (451400, '崇左市', 450000, 2, 107.365, 22.377, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321000, '扬州市', 320000, 2, 119.413, 32.394, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (371000, '威海市', 370000, 2, 122.120, 37.513, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510300, '自贡市', 510000, 2, 104.778, 29.339, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430700, '常德市', 430000, 2, 111.698, 29.032, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (130300, '秦皇岛市', 130000, 2, 119.600, 39.935, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (533400, '迪庆藏族自治州', 530000, 2, 99.702, 27.819, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440500, '汕头市', 440000, 2, 116.682, 23.354, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350200, '厦门市', 350000, 2, 118.089, 24.480, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (610500, '渭南市', 610000, 2, 109.510, 34.500, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (652900, '阿克苏地区', 650000, 2, 80.261, 41.169, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469029, '保亭黎族苗族自治县', 460000, 2, 109.702, 18.638, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140100, '太原市', 140000, 2, 112.549, 37.871, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469030, '琼中黎族苗族自治县', 460000, 2, 109.838, 19.033, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450300, '桂林市', 450000, 2, 110.290, 25.274, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511500, '宜宾市', 510000, 2, 104.643, 28.752, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (152200, '兴安盟', 150000, 2, 122.067, 46.078, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131000, '廊坊市', 130000, 2, 116.684, 39.538, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (211000, '辽阳市', 210000, 2, 123.237, 41.267, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540300, '昌都市', 540000, 2, 97.172, 31.141, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441900, '东莞市', 440000, 2, 113.752, 23.021, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460300, '三沙市', 460000, 2, 112.339, 16.832, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469006, '万宁市', 460000, 2, 110.391, 18.795, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469005, '文昌市', 460000, 2, 110.798, 19.543, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469002, '琼海市', 460000, 2, 110.475, 19.259, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361100, '上饶市', 360000, 2, 117.943, 28.455, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469001, '五指山市', 460000, 2, 109.517, 18.775, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654200, '塔城地区', 650000, 2, 82.980, 46.745, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120100, '天津城区', 120000, 2, 117.201, 39.084, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321200, '泰州市', 320000, 2, 119.923, 32.456, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321100, '镇江市', 320000, 2, 119.426, 32.188, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440800, '湛江市', 440000, 2, 110.359, 21.271, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (623000, '甘南藏族自治州', 620000, 2, 102.911, 34.983, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350100, '福州市', 350000, 2, 119.296, 26.075, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (469007, '东方市', 460000, 2, 108.652, 19.095, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532800, '西双版纳傣族自治州', 530000, 2, 100.798, 22.007, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653100, '喀什地区', 650000, 2, 75.990, 39.470, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530500, '保山市', 530000, 2, 99.162, 25.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632300, '黄南藏族自治州', 630000, 2, 102.015, 35.520, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450600, '防城港市', 450000, 2, 108.354, 21.687, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540400, '林芝市', 540000, 2, 94.361, 29.649, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (441800, '清远市', 440000, 2, 113.056, 23.682, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460400, '儋州市', 460000, 2, 109.581, 19.521, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331000, '台州市', 330000, 2, 121.421, 28.656, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (361000, '抚州市', 360000, 2, 116.358, 27.949, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (321300, '宿迁市', 320000, 2, 118.275, 33.963, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510900, '遂宁市', 510000, 2, 105.593, 30.533, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440700, '江门市', 440000, 2, 113.082, 22.579, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430900, '益阳市', 430000, 2, 112.355, 28.554, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (331100, '丽水市', 330000, 2, 119.923, 28.468, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532900, '大理白族自治州', 530000, 2, 100.268, 25.606, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530600, '昭通市', 530000, 2, 103.717, 27.338, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (150900, '乌兰察布市', 150000, 2, 113.133, 40.995, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (653000, '克孜勒苏柯尔克孜自治州', 650000, 2, 76.168, 39.715, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (611000, '商洛市', 610000, 2, 109.940, 33.870, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (632200, '海北藏族自治州', 630000, 2, 100.901, 36.954, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450500, '北海市', 450000, 2, 109.120, 21.481, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320100, '南京市', 320000, 2, 118.797, 32.060, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540100, '拉萨市', 540000, 2, 91.141, 29.646, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110100, '北京城区', 110000, 2, 116.408, 39.904, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (654000, '伊犁哈萨克自治州', 650000, 2, 81.324, 43.917, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (141000, '临汾市', 140000, 2, 111.519, 36.088, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411100, '漯河市', 410000, 2, 114.017, 33.581, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510800, '广元市', 510000, 2, 105.843, 32.435, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520500, '毕节市', 520000, 2, 105.284, 27.303, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (445100, '潮州市', 440000, 2, 116.623, 23.657, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450800, '贵港市', 450000, 2, 109.599, 23.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (532600, '文山壮族苗族自治州', 530000, 2, 104.216, 23.401, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530300, '曲靖市', 530000, 2, 103.796, 25.490, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341000, '黄山市', 340000, 2, 118.337, 29.715, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230900, '七台河市', 230000, 2, 131.003, 45.772, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320200, '无锡市', 320000, 2, 120.312, 31.491, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (131100, '衡水市', 130000, 2, 115.670, 37.739, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540200, '日喀则市', 540000, 2, 88.881, 29.267, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (542500, '阿里地区', 540000, 2, 80.106, 32.501, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330100, '杭州市', 330000, 2, 120.210, 30.246, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (411000, '许昌市', 410000, 2, 113.853, 34.036, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510700, '绵阳市', 510000, 2, 104.679, 31.467, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440900, '茂名市', 440000, 2, 110.925, 21.663, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (621000, '庆阳市', 620000, 2, 107.644, 35.709, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520600, '铜仁市', 520000, 2, 109.190, 27.732, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530400, '玉溪市', 530000, 2, 102.547, 24.352, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450700, '钦州市', 450000, 2, 108.654, 21.980, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (341100, '滁州市', 340000, 2, 118.317, 32.302, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320300, '徐州市', 320000, 2, 117.284, 34.206, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230800, '佳木斯市', 230000, 2, 130.319, 46.800, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (511900, '巴中市', 510000, 2, 106.747, 31.868, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:30:38', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (510000, '四川省', 0, 1, 104.076, 30.652, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '0', 0, NULL, '2020-10-22 06:32:05', 2, '不告诉你', '2020-11-05 11:42:54');
INSERT INTO `common_area` VALUES (150000, '内蒙古自治区', 0, 1, 111.766, 40.817, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '2', 0, NULL, '2020-10-22 06:32:05', 2, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (220000, '吉林省', 0, 1, 125.326, 43.897, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (340000, '安徽省', 0, 1, 117.285, 31.861, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (110000, '北京市', 0, 0, 116.408, 39.904, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:37');
INSERT INTO `common_area` VALUES (630000, '青海省', 0, 1, 101.780, 36.621, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (520000, '贵州省', 0, 1, 106.707, 26.598, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (360000, '江西省', 0, 1, 115.909, 28.676, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (410000, '河南省', 0, 1, 113.754, 34.766, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (420000, '湖北省', 0, 1, 114.342, 30.546, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (140000, '山西省', 0, 1, 112.562, 37.874, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (540000, '西藏自治区', 0, 1, 91.117, 29.647, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (530000, '云南省', 0, 1, 102.710, 25.046, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (810000, '香港特别行政区', 0, 1, 114.164, 22.276, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (650000, '新疆维吾尔自治区', 0, 1, 87.628, 43.793, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (500000, '重庆市', 0, 1, 106.552, 29.563, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (640000, '宁夏回族自治区', 0, 1, 106.259, 38.471, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (120000, '天津市', 0, 1, 117.201, 39.084, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (230000, '黑龙江省', 0, 1, 126.662, 45.742, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (620000, '甘肃省', 0, 1, 103.826, 36.059, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (350000, '福建省', 0, 1, 119.295, 26.101, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (450000, '广西壮族自治区', 0, 1, 108.328, 22.815, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (330000, '浙江省', 0, 1, 120.153, 30.267, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 2, '不告诉你', '2020-11-05 11:41:03');
INSERT INTO `common_area` VALUES (310000, '上海市', 0, 1, 121.474, 31.230, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 2, '不告诉你', '2020-11-05 11:42:34');
INSERT INTO `common_area` VALUES (610000, '陕西省', 0, 1, 108.954, 34.265, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (440000, '广东省', 0, 1, 113.267, 23.132, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '0', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:43:16');
INSERT INTO `common_area` VALUES (130000, '河北省', 0, 1, 114.469, 38.037, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (210000, '辽宁省', 0, 1, 123.429, 41.835, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (370000, '山东省', 0, 1, 117.020, 36.669, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (820000, '澳门特别行政区', 0, 1, 113.543, 22.187, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (460000, '海南省', 0, 1, 110.349, 20.017, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (320000, '江苏省', 0, 1, 118.763, 32.062, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (710000, '台湾省', 0, 1, 121.509, 25.044, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '1', 0, NULL, '2020-10-22 06:32:05', 0, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (430000, '湖南省', 0, 1, 112.984, 28.112, 'http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html', '0', 0, NULL, '2020-10-22 06:32:05', 2, NULL, '2020-10-22 09:42:25');
INSERT INTO `common_area` VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 2, '不告诉你', '2020-11-05 12:00:27', NULL, NULL, NULL);
COMMIT;

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
INSERT INTO `common_opt_log` VALUES (1326106709160226818, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'edit', '/roles/1', 'PUT', '[1,{\"code\":\"PLATFORM_ADMIN\",\"description\":\"22221123\",\"locked\":false,\"name\":\"平台管理员\",\"orgList\":[],\"scopeType\":\"THIS_LEVEL\"}]', NULL, NULL, NULL, '2020-11-10 18:17:31', '2020-11-10 18:17:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-10 10:17:32');
INSERT INTO `common_opt_log` VALUES (1326106709160226819, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'add', '/roles', 'POST', '[{\"code\":\"1\",\"description\":\"1\",\"locked\":false,\"name\":\"1\",\"orgList\":[100,101,10001,10007,102,10009,10010,10018,10011,10003,10002,10004,10005],\"scopeType\":\"CUSTOMIZE\"}]', NULL, NULL, NULL, '2020-11-11 18:23:24', '2020-11-11 18:23:24', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-11 10:23:23');
INSERT INTO `common_opt_log` VALUES (1326106709160226820, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'edit', '/roles/6', 'PUT', '[6,{\"code\":\"1\",\"description\":\"1\",\"locked\":true,\"name\":\"1\",\"orgList\":[100,101,10001,10007,102,10009,10010,10018,10011,10003,10002,10004,10005],\"scopeType\":\"CUSTOMIZE\"}]', NULL, NULL, NULL, '2020-11-11 18:23:30', '2020-11-11 18:23:30', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-11 10:23:29');
INSERT INTO `common_opt_log` VALUES (1326106709160226821, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'edit', '/roles/6', 'PUT', '[6,{\"code\":\"1\",\"description\":\"1\",\"locked\":false,\"name\":\"1\",\"orgList\":[100,101,10001,10007,102,10009,10010,10018,10011,10003,10002,10004,10005],\"scopeType\":\"CUSTOMIZE\"}]', NULL, NULL, NULL, '2020-11-11 18:23:40', '2020-11-11 18:23:40', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-11 10:23:39');
INSERT INTO `common_opt_log` VALUES (1326106709160226822, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除角色', 'com.wemirr.platform.authority.controller.baseinfo.RoleController', 'del', '/roles/6', 'DELETE', '[6]', NULL, NULL, NULL, '2020-11-11 18:24:28', '2020-11-11 18:24:28', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-11 10:24:27');
INSERT INTO `common_opt_log` VALUES (1326106709160226823, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"1\",\"sequence\":0}]', NULL, NULL, NULL, '2020-11-12 09:45:05', '2020-11-12 09:45:05', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 01:45:04');
INSERT INTO `common_opt_log` VALUES (1326106709160226824, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000001', 'DELETE', '[10000001]', NULL, NULL, NULL, '2020-11-12 09:45:10', '2020-11-12 09:45:10', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 01:45:09');
INSERT INTO `common_opt_log` VALUES (1326106709160226825, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"1\",\"permission\":\"1\",\"sequence\":0}]', NULL, NULL, NULL, '2020-11-12 09:49:30', '2020-11-12 09:49:30', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 01:49:30');
INSERT INTO `common_opt_log` VALUES (1326106709160226826, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"11\",\"parentId\":10101,\"permission\":\"11\",\"sequence\":0}]', NULL, NULL, NULL, '2020-11-12 10:31:27', '2020-11-12 10:31:27', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:31:27');
INSERT INTO `common_opt_log` VALUES (1326106709160226827, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"1\",\"parentId\":10101,\"permission\":\"1\",\"sequence\":0}]', NULL, NULL, NULL, '2020-11-12 10:32:07', '2020-11-12 10:32:07', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:32:06');
INSERT INTO `common_opt_log` VALUES (1326106709160226828, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000004', 'PUT', '[10000004,{\"icon\":\"\",\"label\":\"22\",\"parentId\":10101,\"path\":\"\",\"permission\":\"12\",\"sequence\":0}]', NULL, NULL, NULL, '2020-11-12 10:41:23', '2020-11-12 10:41:23', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:41:23');
INSERT INTO `common_opt_log` VALUES (1326106709160226829, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000004', 'DELETE', '[10000004]', NULL, NULL, NULL, '2020-11-12 10:41:29', '2020-11-12 10:41:29', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:41:28');
INSERT INTO `common_opt_log` VALUES (1326106709160226830, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"1\",\"parentId\":10101,\"permission\":\"2\",\"sequence\":0}]', NULL, NULL, NULL, '2020-11-12 10:42:08', '2020-11-12 10:42:08', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:42:07');
INSERT INTO `common_opt_log` VALUES (1326106709160226831, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"2\",\"parentId\":10101,\"permission\":\"2\",\"sequence\":0}]', NULL, NULL, NULL, '2020-11-12 10:42:51', '2020-11-12 10:42:51', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:42:51');
INSERT INTO `common_opt_log` VALUES (1326106709160226832, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"1\",\"parentId\":10101,\"permission\":\"2\",\"sequence\":0}]', NULL, NULL, NULL, '2020-11-12 10:44:01', '2020-11-12 10:44:01', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:44:00');
INSERT INTO `common_opt_log` VALUES (1326106709160226833, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"1\",\"parentId\":10101,\"permission\":\"2\",\"sequence\":0}]', NULL, NULL, NULL, '2020-11-12 10:44:04', '2020-11-12 10:44:04', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:44:03');
INSERT INTO `common_opt_log` VALUES (1326106709160226834, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"2\",\"parentId\":10101,\"permission\":\"2\",\"sequence\":0,\"type\":\"BUTTON\"}]', NULL, NULL, NULL, '2020-11-12 10:45:51', '2020-11-12 10:45:51', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:45:50');
INSERT INTO `common_opt_log` VALUES (1326106709160226835, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000009', 'DELETE', '[10000009]', NULL, NULL, NULL, '2020-11-12 10:45:53', '2020-11-12 10:45:53', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 02:45:53');
INSERT INTO `common_opt_log` VALUES (1326106709160226836, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"label\":\"删除\",\"parentId\":10101,\"permission\":\"user:del\",\"sequence\":0,\"type\":\"BUTTON\"}]', NULL, NULL, NULL, '2020-11-12 11:32:49', '2020-11-12 11:32:49', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 03:32:49');
INSERT INTO `common_opt_log` VALUES (1326106709160226837, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-12 11:33:12', '2020-11-12 11:33:12', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 03:33:12');
INSERT INTO `common_opt_log` VALUES (1326106709160226838, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-12 11:33:14', '2020-11-12 11:33:14', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 03:33:13');
INSERT INTO `common_opt_log` VALUES (1326106709160226839, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-12 11:33:19', '2020-11-12 11:33:19', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 03:33:18');
INSERT INTO `common_opt_log` VALUES (1326106709160226840, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-12 16:58:05', '2020-11-12 16:58:05', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-12 08:58:04');
INSERT INTO `common_opt_log` VALUES (1326106709160226841, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-13 15:24:20', '2020-11-13 15:24:20', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-13 07:24:20');
INSERT INTO `common_opt_log` VALUES (1326106709160226842, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-16 14:58:11', '2020-11-16 14:58:11', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-16 06:58:11');
INSERT INTO `common_opt_log` VALUES (1326106709160226843, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-16 15:08:33', '2020-11-16 15:08:33', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-16 07:08:33');
INSERT INTO `common_opt_log` VALUES (1326106709160226844, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-17 15:00:52', '2020-11-17 15:00:52', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-17 07:00:52');
INSERT INTO `common_opt_log` VALUES (1326106709160226845, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-18 11:02:00', '2020-11-18 11:02:00', 15, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-18 03:02:00');
INSERT INTO `common_opt_log` VALUES (1326106709160226846, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-18 14:11:36', '2020-11-18 14:11:36', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-18 06:11:36');
INSERT INTO `common_opt_log` VALUES (1326106709160226847, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-18 14:13:34', '2020-11-18 14:13:34', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-18 06:13:33');
INSERT INTO `common_opt_log` VALUES (1326106709160226848, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-18 14:24:49', '2020-11-18 14:24:49', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-18 06:24:48');
INSERT INTO `common_opt_log` VALUES (1326106709160226849, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-18 15:24:32', '2020-11-18 15:24:32', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-18 07:24:32');
INSERT INTO `common_opt_log` VALUES (1326106709160226850, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典新增', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'save', '/dictionaries', 'POST', '[{\"code\":\"SEX\",\"description\":\"性别\",\"name\":\"性别\",\"status\":true}]', NULL, NULL, NULL, '2020-11-18 15:24:44', '2020-11-18 15:24:44', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-18 07:24:43');
INSERT INTO `common_opt_log` VALUES (1326106709160226851, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":15},null]', NULL, NULL, NULL, '2020-11-18 15:24:44', '2020-11-18 15:24:44', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-18 07:24:43');
INSERT INTO `common_opt_log` VALUES (1326106709160226852, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-18 15:26:12', '2020-11-18 15:26:12', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-18 07:26:11');
INSERT INTO `common_opt_log` VALUES (1326106709160226853, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-18 15:37:47', '2020-11-18 15:37:47', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-18 07:37:47');
INSERT INTO `common_opt_log` VALUES (1326106709160226854, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-19 10:56:56', '2020-11-19 10:56:56', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-19 02:56:55');
INSERT INTO `common_opt_log` VALUES (1326106709160226855, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典新增', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'save', '/dictionaries', 'POST', '[{\"code\":\"NOTICE\",\"description\":\"消息通知\",\"name\":\"消息通知\",\"status\":true}]', NULL, NULL, NULL, '2020-11-19 10:57:23', '2020-11-19 10:57:23', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-19 02:57:22');
INSERT INTO `common_opt_log` VALUES (1326106709160226856, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-19 10:57:23', '2020-11-19 10:57:23', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-19 02:57:22');
INSERT INTO `common_opt_log` VALUES (1326106709160226857, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-19 12:24:08', '2020-11-19 12:24:08', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-19 04:24:08');
INSERT INTO `common_opt_log` VALUES (1326106709160226858, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典编辑', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'edit', '/dictionaries/7', 'PUT', '[7,{\"code\":\"NOTICE\",\"description\":\"消息类型\",\"name\":\"消息类型\",\"status\":true}]', NULL, NULL, NULL, '2020-11-19 12:24:18', '2020-11-19 12:24:18', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-19 04:24:17');
INSERT INTO `common_opt_log` VALUES (1326106709160226859, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-19 12:24:18', '2020-11-19 12:24:18', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-19 04:24:17');
INSERT INTO `common_opt_log` VALUES (1326106709160226860, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-19 17:13:23', '2020-11-19 17:13:23', 59, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-19 09:13:23');
INSERT INTO `common_opt_log` VALUES (1326106709160226861, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-19 17:13:55', '2020-11-19 17:13:55', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-19 09:13:55');
INSERT INTO `common_opt_log` VALUES (1326106709160226862, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-19 17:14:23', '2020-11-19 17:14:23', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-19 09:14:23');
INSERT INTO `common_opt_log` VALUES (1326106709160226863, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-24 17:05:21', '2020-11-24 17:05:21', 22, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-24 09:05:20');
INSERT INTO `common_opt_log` VALUES (1326106709160226864, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-25 11:24:53', '2020-11-25 11:24:53', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-25 03:24:52');
INSERT INTO `common_opt_log` VALUES (1326106709160226865, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-11-26 17:51:28', '2020-11-26 17:51:28', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 09:51:28');
INSERT INTO `common_opt_log` VALUES (1326106709160226866, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-11-26 18:00:52', '2020-11-26 18:00:52', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 10:00:51');
INSERT INTO `common_opt_log` VALUES (1326106709160226867, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-11-26 18:01:46', '2020-11-26 18:01:46', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 10:01:46');
INSERT INTO `common_opt_log` VALUES (1326106709160226868, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-11-26 18:01:55', '2020-11-26 18:01:55', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 10:01:54');
INSERT INTO `common_opt_log` VALUES (1326106709160226869, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-11-26 18:04:37', '2020-11-26 18:04:37', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 10:04:36');
INSERT INTO `common_opt_log` VALUES (1326106709160226870, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-11-26 18:56:00', '2020-11-26 18:56:00', 0, 'Safari', 'OSX', 'Webkit', '605.1.15', 'Mac', NULL, 2, '不告诉你', '2020-11-26 10:55:59');
INSERT INTO `common_opt_log` VALUES (1326106709160226871, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-11-26 18:56:49', '2020-11-26 18:56:49', 0, 'Safari', 'OSX', 'Webkit', '605.1.15', 'Mac', NULL, 2, '不告诉你', '2020-11-26 10:56:49');
INSERT INTO `common_opt_log` VALUES (1326106709160226872, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-11-26 18:59:52', '2020-11-26 18:59:52', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 10:59:52');
INSERT INTO `common_opt_log` VALUES (1326106709160226873, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-26 22:00:18', '2020-11-26 22:00:18', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 14:00:17');
INSERT INTO `common_opt_log` VALUES (1326106709160226874, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-26 22:12:38', '2020-11-26 22:12:38', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 14:12:38');
INSERT INTO `common_opt_log` VALUES (1326106709160226875, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-26 22:12:42', '2020-11-26 22:12:42', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 14:12:41');
INSERT INTO `common_opt_log` VALUES (1326106709160226876, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-26 22:13:03', '2020-11-26 22:13:03', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 14:13:03');
INSERT INTO `common_opt_log` VALUES (1326106709160226877, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-26 22:50:32', '2020-11-26 22:50:32', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 14:50:32');
INSERT INTO `common_opt_log` VALUES (1326106709160226878, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-26 23:52:26', '2020-11-26 23:52:26', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 15:52:25');
INSERT INTO `common_opt_log` VALUES (1326106709160226879, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-11-27 00:08:18', '2020-11-27 00:08:18', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-26 16:08:18');
INSERT INTO `common_opt_log` VALUES (1326106709160226880, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"测试一下\",\"global\":false,\"icon\":\"el-icon-phone\",\"label\":\"测试一下模板\",\"parentId\":204,\"path\":\"\",\"sequence\":1}]', NULL, NULL, NULL, '2020-11-27 17:45:28', '2020-11-27 17:45:28', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 09:45:28');
INSERT INTO `common_opt_log` VALUES (1326106709160226881, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000011', 'PUT', '[10000011,{\"component\":\"\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下模板\",\"parentId\":204,\"path\":\"\",\"sequence\":1,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-27 17:53:48', '2020-11-27 17:53:48', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 09:53:47');
INSERT INTO `common_opt_log` VALUES (1326106709160226882, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000011', 'PUT', '[10000011,{\"component\":\"/system/development/build/standard\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下模板\",\"parentId\":204,\"path\":\"/system/development/release/null\",\"sequence\":1,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-27 17:58:50', '2020-11-27 17:58:50', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 09:58:49');
INSERT INTO `common_opt_log` VALUES (1326106709160226883, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000011', 'PUT', '[10000011,{\"component\":\"/system/development/build/standard\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下模板\",\"model\":\"test\",\"parentId\":204,\"path\":\"/system/development/release/null\",\"sequence\":1,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-27 18:00:58', '2020-11-27 18:00:58', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:00:58');
INSERT INTO `common_opt_log` VALUES (1326106709160226884, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000011', 'PUT', '[10000011,{\"component\":\"/system/development/build/standard\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下模板\",\"model\":\"test\",\"parentId\":204,\"path\":\"/system/development/release/test\",\"sequence\":1,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-27 18:02:28', '2020-11-27 18:02:28', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:02:28');
INSERT INTO `common_opt_log` VALUES (1326106709160226885, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000011', 'PUT', '[10000011,{\"component\":\"/system/development/build/standard\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下模板\",\"model\":\"test\",\"parentId\":204,\"path\":\"/system/development/release/tenant_1_test\",\"sequence\":1,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-11-27 18:10:47', '2020-11-27 18:10:47', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:10:47');
INSERT INTO `common_opt_log` VALUES (1326106709160226886, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000011', 'PUT', '[10000011,{\"component\":\"/system/development/build/standard\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下模板\",\"model\":\"test\",\"parentId\":204,\"path\":\"/system/development/release/tenant_1_test\",\"sequence\":1,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-27 18:11:22', '2020-11-27 18:11:22', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:11:21');
INSERT INTO `common_opt_log` VALUES (1326106709160226887, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000011', 'PUT', '[10000011,{\"component\":\"/system/development/build/standard\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下模板\",\"model\":\"test\",\"parentId\":204,\"path\":\"/system/development/release/tenant_1_test\",\"sequence\":1,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-27 18:19:37', '2020-11-27 18:19:37', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:19:36');
INSERT INTO `common_opt_log` VALUES (1326106709160226888, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000011', 'PUT', '[10000011,{\"component\":\"/system/development/build/standard\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下模板\",\"model\":\"test\",\"parentId\":204,\"path\":\"/system/development/release/test2\",\"sequence\":1,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-11-27 18:21:11', '2020-11-27 18:21:11', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:21:11');
INSERT INTO `common_opt_log` VALUES (1326106709160226889, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000011', 'DELETE', '[10000011]', NULL, NULL, NULL, '2020-11-27 18:22:03', '2020-11-27 18:22:03', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:22:03');
INSERT INTO `common_opt_log` VALUES (1326106709160226890, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"测试一下\",\"global\":false,\"icon\":\"\",\"label\":\"测试一下\",\"model\":\"test\",\"parentId\":204,\"path\":\"\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-27 18:22:19', '2020-11-27 18:22:19', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:22:19');
INSERT INTO `common_opt_log` VALUES (1326106709160226891, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000012', 'PUT', '[10000012,{\"component\":\"/system/development/build/standard\",\"icon\":\"\",\"label\":\"测试一下\",\"model\":\"test\",\"parentId\":204,\"path\":\"/system/development/release/test_tenant_1\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-11-27 18:23:28', '2020-11-27 18:23:28', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:23:27');
INSERT INTO `common_opt_log` VALUES (1326106709160226892, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"/system/development/build/standard\",\"description\":\"123\",\"global\":false,\"icon\":\"el-icon-phone\",\"label\":\"测试一下\",\"model\":\"\",\"parentId\":204,\"path\":\"/system/development/release/test\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-11-27 18:27:19', '2020-11-27 18:27:19', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:27:19');
INSERT INTO `common_opt_log` VALUES (1326106709160226893, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000013', 'PUT', '[10000013,{\"component\":\"/system/development/build/standard\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下\",\"model\":\"\",\"parentId\":204,\"path\":\"/system/development/release123123/test123123\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-11-27 18:27:35', '2020-11-27 18:27:35', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:27:35');
INSERT INTO `common_opt_log` VALUES (1326106709160226894, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000013', 'PUT', '[10000013,{\"component\":\"/system/development/build/standard\",\"icon\":\"el-icon-phone\",\"label\":\"测试一下\",\"model\":\"test\",\"parentId\":204,\"path\":\"/system/development/release123123/test123123\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-27 18:28:16', '2020-11-27 18:28:16', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-27 10:28:15');
INSERT INTO `common_opt_log` VALUES (1326106709160226895, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-11-30 11:23:16', '2020-11-30 11:23:16', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:23:15');
INSERT INTO `common_opt_log` VALUES (1326106709160226896, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"123213213\",\"global\":false,\"icon\":\"el-icon-user\",\"label\":\"测试平台\",\"model\":\"test\",\"parentId\":0,\"path\":\"\",\"sequence\":1,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-30 11:24:55', '2020-11-30 11:24:55', 1, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:24:55');
INSERT INTO `common_opt_log` VALUES (1326106709160226897, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"wemirr/index\",\"description\":\"123\",\"global\":false,\"icon\":\"el-icon-circle-check\",\"label\":\"哈哈\",\"model\":\"test\",\"parentId\":10000014,\"path\":\"\",\"sequence\":1,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-30 11:25:24', '2020-11-30 11:25:24', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:25:23');
INSERT INTO `common_opt_log` VALUES (1326106709160226898, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"description\":\"123\",\"icon\":\"el-icon-circle-check\",\"label\":\"哈哈\",\"model\":\"student2\",\"parentId\":10000014,\"path\":\"/system/development/release/tenant_1/test\",\"sequence\":1,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-30 11:26:30', '2020-11-30 11:26:30', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:26:30');
INSERT INTO `common_opt_log` VALUES (1326106709160226899, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"wemirr/index\",\"description\":\"2313123\",\"global\":false,\"icon\":\"el-icon-setting\",\"label\":\"唐亚峰\",\"model\":\"\",\"parentId\":0,\"path\":\"\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-11-30 11:31:57', '2020-11-30 11:31:57', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:31:56');
INSERT INTO `common_opt_log` VALUES (1326106709160226900, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000016', 'DELETE', '[10000016]', NULL, NULL, NULL, '2020-11-30 11:32:10', '2020-11-30 11:32:10', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:32:10');
INSERT INTO `common_opt_log` VALUES (1326106709160226901, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000015', 'DELETE', '[10000015]', NULL, NULL, NULL, '2020-11-30 11:32:24', '2020-11-30 11:32:24', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:32:24');
INSERT INTO `common_opt_log` VALUES (1326106709160226902, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000014', 'DELETE', '[10000014]', NULL, NULL, NULL, '2020-11-30 11:32:28', '2020-11-30 11:32:28', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:32:28');
INSERT INTO `common_opt_log` VALUES (1326106709160226903, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-delete\",\"label\":\"测试平台110\",\"model\":\"\",\"parentId\":0,\"path\":\"\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-11-30 11:32:39', '2020-11-30 11:32:39', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:32:38');
INSERT INTO `common_opt_log` VALUES (1326106709160226904, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-phone\",\"label\":\"测试子节点\",\"model\":\"student2\",\"parentId\":10000017,\"path\":\"\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-30 11:33:01', '2020-11-30 11:33:01', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 03:33:01');
INSERT INTO `common_opt_log` VALUES (1326106709160226905, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000018', 'DELETE', '[10000018]', NULL, NULL, NULL, '2020-11-30 13:40:10', '2020-11-30 13:40:10', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 05:40:10');
INSERT INTO `common_opt_log` VALUES (1326106709160226906, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000017', 'DELETE', '[10000017]', NULL, NULL, NULL, '2020-11-30 13:40:15', '2020-11-30 13:40:15', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 05:40:14');
INSERT INTO `common_opt_log` VALUES (1326106709160226907, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-platform-eleme\",\"label\":\"测试平台\",\"model\":\"\",\"parentId\":0,\"path\":\"\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-11-30 13:40:31', '2020-11-30 13:40:31', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 05:40:30');
INSERT INTO `common_opt_log` VALUES (1326106709160226908, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-eleme\",\"label\":\"学生管理\",\"model\":\"student2\",\"parentId\":10000019,\"path\":\"\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-30 13:40:54', '2020-11-30 13:40:54', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 05:40:53');
INSERT INTO `common_opt_log` VALUES (1326106709160226909, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-s-custom\",\"label\":\"学生管理\",\"model\":\"\",\"parentId\":0,\"path\":\"\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-11-30 13:43:44', '2020-11-30 13:43:44', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 05:43:44');
INSERT INTO `common_opt_log` VALUES (1326106709160226910, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"学生列表\",\"global\":false,\"icon\":\"el-icon-user-solid\",\"label\":\"学生列表\",\"model\":\"student2\",\"parentId\":10000021,\"path\":\"\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-30 13:44:01', '2020-11-30 13:44:01', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 05:44:00');
INSERT INTO `common_opt_log` VALUES (1326106709160226911, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000022', 'DELETE', '[10000022]', NULL, NULL, NULL, '2020-11-30 14:18:08', '2020-11-30 14:18:08', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 06:18:07');
INSERT INTO `common_opt_log` VALUES (1326106709160226912, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000021', 'DELETE', '[10000021]', NULL, NULL, NULL, '2020-11-30 14:18:12', '2020-11-30 14:18:12', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 06:18:11');
INSERT INTO `common_opt_log` VALUES (1326106709160226913, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-circle-check\",\"label\":\"学生管理\",\"model\":\"student\",\"parentId\":204,\"path\":\"\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-11-30 19:35:17', '2020-11-30 19:35:17', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-11-30 11:35:16');
INSERT INTO `common_opt_log` VALUES (1326106709160226914, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/20402', 'DELETE', '[20402]', NULL, NULL, NULL, '2020-12-01 18:02:51', '2020-12-01 18:02:51', 26, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-01 10:02:50');
INSERT INTO `common_opt_log` VALUES (1326106709160226915, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '删除资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'del', '/resources/10000011', 'DELETE', '[10000011]', NULL, NULL, NULL, '2020-12-01 18:03:00', '2020-12-01 18:03:00', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-01 10:03:00');
INSERT INTO `common_opt_log` VALUES (1326106709160226916, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-phone-outline\",\"label\":\"鏖战八方\",\"model\":\"\",\"parentId\":0,\"path\":\"\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-12-01 18:03:18', '2020-12-01 18:03:18', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-01 10:03:17');
INSERT INTO `common_opt_log` VALUES (1326106709160226917, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'EX', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"wemirr/index\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-phone\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, 'com.wemirr.platform.authority.mapper.RoleResMapper.insert (batch index #1) failed. Cause: java.sql.BatchUpdateException: Duplicate entry \'1-10000013\' for key \'idx_role_res\'\n; Duplicate entry \'1-10000013\' for key \'idx_role_res\'; nested exception is java.sql.BatchUpdateException: Duplicate entry \'1-10000013\' for key \'idx_role_res\'', 'org.springframework.dao.DuplicateKeyException: com.wemirr.platform.authority.mapper.RoleResMapper.insert (batch index #1) failed. Cause: java.sql.BatchUpdateException: Duplicate entry \'1-10000013\' for key \'idx_role_res\'\n; Duplicate entry \'1-10000013\' for key \'idx_role_res\'; nested exception is java.sql.BatchUpdateException: Duplicate entry \'1-10000013\' for key \'idx_role_res\'\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:247)\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:88)\n	at com.baomidou.mybatisplus.extension.toolkit.SqlHelper.executeBatch(SqlHelper.java:189)\n	at com.baomidou.mybatisplus.extension.toolkit.SqlHelper.executeBatch(SqlHelper.java:211)\n	at com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.executeBatch(ServiceImpl.java:234)\n	at com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.saveBatch(ServiceImpl.java:130)\n	at com.baomidou.mybatisplus.extension.service.IService.saveBatch(IService.java:71)\n	at com.baomidou.mybatisplus.extension.service.IService$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:367)\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:118)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)\n	at com.wemirr.platform.authority.service.impl.RoleResServiceImpl$$EnhancerBySpringCGLIB$$1.saveBatch(<generated>)\n	at com.wemirr.platform.authority.service.impl.ResourceServiceImpl.addResource(ResourceServiceImpl.java:78)\n	at com.wemirr.platform.authority.service.impl.ResourceServiceImpl$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:367)\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:118)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)\n	at com.wemirr.platform.authority.service.impl.ResourceServiceImpl$$EnhancerBySpringCGLIB$$1.addResource(<generated>)\n	at com.wemirr.platform.authority.controller.baseinfo.ResourceController.save(ResourceController.java:102)\n	at com.wemirr.platform.authority.controller.baseinfo.ResourceController$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.validation.beanvalidation.MethodValidationInterceptor.invoke(MethodValidationInterceptor.java:119)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:55)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor.invoke(MethodBeforeAdviceInterceptor.java:56)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:95)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)\n	at com.wemirr.platform.authority.controller.baseinfo.ResourceController$$EnhancerBySpringCGLIB$$1.save(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:517)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:584)\n	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)\n	at io.undertow.websockets.jsr.JsrWebSocketFilter.doFilter(JsrWebSocketFilter.java:173)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:126)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:90)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:118)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:158)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter.doFilter(OAuth2AuthenticationProcessingFilter.java:176)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:92)\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:77)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)\n	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)\n	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)\n	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)\n	at io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:68)\n	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:132)\n	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)\n	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)\n	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)\n	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)\n	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.jrHandle(ServletInitialHandler.java:40001)\n	at org.zeroturnaround.javarebel.integration.servlet.undertow.cbp.ServletInitialHandlerCBP.handleRequest(ServletInitialHandlerCBP.java:138)\n	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:269)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:133)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:130)\n	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)\n	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:249)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:99)\n	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:370)\n	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:836)\n	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)\n	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:2019)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1558)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1449)\n	at java.lang.Thread.run(Thread.java:748)\nCaused by: java.sql.BatchUpdateException: Duplicate entry \'1-10000013\' for key \'idx_role_res\'\n	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n	at com.mysql.cj.util.Util.handleNewInstance(Util.java:192)\n	at com.mysql.cj.util.Util.getInstance(Util.java:167)\n	at com.mysql.cj.util.Util.getInstance(Util.java:174)\n	at com.mysql.cj.jdbc.exceptions.SQLError.createBatchUpdateException(SQLError.java:224)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeBatchSerially(ClientPreparedStatement.java:853)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeBatchInternal(ClientPreparedStatement.java:435)\n	at com.mysql.cj.jdbc.StatementImpl.executeBatch(StatementImpl.java:796)\n	at com.zaxxer.hikari.pool.ProxyStatement.executeBatch(ProxyStatement.java:128)\n	at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.executeBatch(HikariProxyPreparedStatement.java)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.logging.jdbc.PreparedStatementLogger.invoke(PreparedStatementLogger.java:78)\n	at com.sun.proxy.$Proxy302.executeBatch(Unknown Source)\n	at com.baomidou.mybatisplus.core.executor.MybatisBatchExecutor.doFlushStatements(MybatisBatchExecutor.java:135)\n	at org.apache.ibatis.executor.BaseExecutor.flushStatements(BaseExecutor.java:129)\n	at org.apache.ibatis.executor.BaseExecutor.flushStatements(BaseExecutor.java:122)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\n	at com.sun.proxy.$Proxy300.flushStatements(Unknown Source)\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.flushStatements(DefaultSqlSession.java:252)\n	at com.baomidou.mybatisplus.extension.toolkit.SqlHelper.lambda$executeBatch$0(SqlHelper.java:217)\n	at com.baomidou.mybatisplus.extension.toolkit.SqlHelper.executeBatch(SqlHelper.java:179)\n	... 154 more\nCaused by: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'1-10000013\' for key \'idx_role_res\'\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:117)\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)\n	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:953)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1092)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeBatchSerially(ClientPreparedStatement.java:832)\n	... 176 more\n', '2020-12-01 18:14:49', '2020-12-01 18:14:49', 142, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-01 10:14:49');
INSERT INTO `common_opt_log` VALUES (1326106709160226918, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'EX', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"wemirr/index\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-phone\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, 'com.wemirr.platform.authority.mapper.RoleResMapper.insert (batch index #1) failed. Cause: java.sql.BatchUpdateException: Duplicate entry \'1-10000014\' for key \'idx_role_res\'\n; Duplicate entry \'1-10000014\' for key \'idx_role_res\'; nested exception is java.sql.BatchUpdateException: Duplicate entry \'1-10000014\' for key \'idx_role_res\'', 'org.springframework.dao.DuplicateKeyException: com.wemirr.platform.authority.mapper.RoleResMapper.insert (batch index #1) failed. Cause: java.sql.BatchUpdateException: Duplicate entry \'1-10000014\' for key \'idx_role_res\'\n; Duplicate entry \'1-10000014\' for key \'idx_role_res\'; nested exception is java.sql.BatchUpdateException: Duplicate entry \'1-10000014\' for key \'idx_role_res\'\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:247)\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:88)\n	at com.baomidou.mybatisplus.extension.toolkit.SqlHelper.executeBatch(SqlHelper.java:189)\n	at com.baomidou.mybatisplus.extension.toolkit.SqlHelper.executeBatch(SqlHelper.java:211)\n	at com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.executeBatch(ServiceImpl.java:234)\n	at com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.saveBatch(ServiceImpl.java:130)\n	at com.baomidou.mybatisplus.extension.service.IService.saveBatch(IService.java:71)\n	at com.baomidou.mybatisplus.extension.service.IService$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:367)\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:118)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)\n	at com.wemirr.platform.authority.service.impl.RoleResServiceImpl$$EnhancerBySpringCGLIB$$1.saveBatch(<generated>)\n	at com.wemirr.platform.authority.service.impl.ResourceServiceImpl.addResource(ResourceServiceImpl.java:78)\n	at com.wemirr.platform.authority.service.impl.ResourceServiceImpl$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:367)\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:118)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)\n	at com.wemirr.platform.authority.service.impl.ResourceServiceImpl$$EnhancerBySpringCGLIB$$1.addResource(<generated>)\n	at com.wemirr.platform.authority.controller.baseinfo.ResourceController.save(ResourceController.java:102)\n	at com.wemirr.platform.authority.controller.baseinfo.ResourceController$$FastClassBySpringCGLIB$$1.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.validation.beanvalidation.MethodValidationInterceptor.invoke(MethodValidationInterceptor.java:119)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:55)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor.invoke(MethodBeforeAdviceInterceptor.java:56)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:95)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)\n	at com.wemirr.platform.authority.controller.baseinfo.ResourceController$$EnhancerBySpringCGLIB$$1.save(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:517)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:584)\n	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)\n	at io.undertow.websockets.jsr.JsrWebSocketFilter.doFilter(JsrWebSocketFilter.java:173)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:126)\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:90)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:118)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:158)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter.doFilter(OAuth2AuthenticationProcessingFilter.java:176)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:92)\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:77)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:93)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n	at io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\n	at io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\n	at io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)\n	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)\n	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)\n	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)\n	at io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:68)\n	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:132)\n	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)\n	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)\n	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)\n	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)\n	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.jrHandle(ServletInitialHandler.java:40001)\n	at org.zeroturnaround.javarebel.integration.servlet.undertow.cbp.ServletInitialHandlerCBP.handleRequest(ServletInitialHandlerCBP.java:138)\n	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:269)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:133)\n	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:130)\n	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)\n	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)\n	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:249)\n	at io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:78)\n	at io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:99)\n	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:370)\n	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:836)\n	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)\n	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:2019)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1558)\n	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1449)\n	at java.lang.Thread.run(Thread.java:748)\nCaused by: java.sql.BatchUpdateException: Duplicate entry \'1-10000014\' for key \'idx_role_res\'\n	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n	at com.mysql.cj.util.Util.handleNewInstance(Util.java:192)\n	at com.mysql.cj.util.Util.getInstance(Util.java:167)\n	at com.mysql.cj.util.Util.getInstance(Util.java:174)\n	at com.mysql.cj.jdbc.exceptions.SQLError.createBatchUpdateException(SQLError.java:224)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeBatchSerially(ClientPreparedStatement.java:853)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeBatchInternal(ClientPreparedStatement.java:435)\n	at com.mysql.cj.jdbc.StatementImpl.executeBatch(StatementImpl.java:796)\n	at com.zaxxer.hikari.pool.ProxyStatement.executeBatch(ProxyStatement.java:128)\n	at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.executeBatch(HikariProxyPreparedStatement.java)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.logging.jdbc.PreparedStatementLogger.invoke(PreparedStatementLogger.java:78)\n	at com.sun.proxy.$Proxy302.executeBatch(Unknown Source)\n	at com.baomidou.mybatisplus.core.executor.MybatisBatchExecutor.doFlushStatements(MybatisBatchExecutor.java:135)\n	at org.apache.ibatis.executor.BaseExecutor.flushStatements(BaseExecutor.java:129)\n	at org.apache.ibatis.executor.BaseExecutor.flushStatements(BaseExecutor.java:122)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)\n	at com.sun.proxy.$Proxy300.flushStatements(Unknown Source)\n	at org.apache.ibatis.session.defaults.DefaultSqlSession.flushStatements(DefaultSqlSession.java:252)\n	at com.baomidou.mybatisplus.extension.toolkit.SqlHelper.lambda$executeBatch$0(SqlHelper.java:217)\n	at com.baomidou.mybatisplus.extension.toolkit.SqlHelper.executeBatch(SqlHelper.java:179)\n	... 154 more\nCaused by: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'1-10000014\' for key \'idx_role_res\'\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:117)\n	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)\n	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:953)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1092)\n	at com.mysql.cj.jdbc.ClientPreparedStatement.executeBatchSerially(ClientPreparedStatement.java:832)\n	... 176 more\n', '2020-12-01 18:15:19', '2020-12-01 18:15:19', 24, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-01 10:15:18');
INSERT INTO `common_opt_log` VALUES (1326106709160226919, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"wemirr/index\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-phone\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-01 18:15:28', '2020-12-01 18:15:28', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-01 10:15:27');
INSERT INTO `common_opt_log` VALUES (1326106709160226920, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑用户', 'com.wemirr.platform.authority.controller.baseinfo.UserController', 'edit', '/users/1', 'PUT', '[1,{\"avatar\":\"https://d2p-demo-1251260344.cos.ap-guangzhou.myqcloud.com/file/2020/10/13/79584221289944.jpg\",\"description\":\"描述信息吧\",\"education\":\"SUOSHI\",\"email\":\"1837307557@qq.com\",\"mobile\":\"13002171912\",\"nation\":\"mz_daiz\",\"nickName\":\"唐亚峰3\",\"orgId\":10001,\"positionStatus\":\"WORKING\",\"sex\":\"MAN\",\"stationId\":101,\"status\":false}]', NULL, NULL, NULL, '2020-12-02 11:26:01', '2020-12-02 11:26:01', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 03:26:01');
INSERT INTO `common_opt_log` VALUES (1326106709160226921, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑岗位', 'com.wemirr.platform.authority.controller.baseinfo.StationController', 'edit', '/stations/100', 'PUT', '[100,{\"description\":\"总部-1把手\",\"name\":\"总经理\",\"orgId\":10008,\"status\":true}]', NULL, NULL, NULL, '2020-12-02 14:29:26', '2020-12-02 14:29:26', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 06:29:26');
INSERT INTO `common_opt_log` VALUES (1326106709160226922, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '添加资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'save', '/resources', 'POST', '[{\"component\":\"\",\"description\":\"\",\"global\":false,\"icon\":\"el-icon-s-order\",\"label\":\"江苏战区\",\"model\":\"az_jiangsu\",\"parentId\":10000012,\"path\":\"\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-02 14:34:10', '2020-12-02 14:34:10', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 06:34:10');
INSERT INTO `common_opt_log` VALUES (1326106709160226923, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000016', 'PUT', '[10000016,{\"component\":\"/system/development/build/standard\",\"icon\":\"american-sign-language-interpreting\",\"label\":\"江苏战区\",\"model\":\"az_jiangsu\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_jiangsu\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-12-02 16:11:39', '2020-12-02 16:11:39', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 08:11:39');
INSERT INTO `common_opt_log` VALUES (1326106709160226924, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-12-02 16:11:45', '2020-12-02 16:11:45', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 08:11:44');
INSERT INTO `common_opt_log` VALUES (1326106709160226925, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-02 16:19:52', '2020-12-02 16:19:52', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 08:19:51');
INSERT INTO `common_opt_log` VALUES (1326106709160226926, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000016', 'PUT', '[10000016,{\"component\":\"/system/development/build/standard\",\"icon\":\"american-sign-language-interpreting\",\"label\":\"江苏战区\",\"model\":\"az_jiangsu\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_jiangsu\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-02 16:20:02', '2020-12-02 16:20:02', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 08:20:01');
INSERT INTO `common_opt_log` VALUES (1326106709160226927, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"MENU\"}]', NULL, NULL, NULL, '2020-12-02 16:20:41', '2020-12-02 16:20:41', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 08:20:41');
INSERT INTO `common_opt_log` VALUES (1326106709160226928, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-02 16:20:44', '2020-12-02 16:20:44', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 08:20:44');
INSERT INTO `common_opt_log` VALUES (1326106709160226929, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '编辑组织架构', 'com.wemirr.platform.authority.controller.baseinfo.OrgController', 'edit', '/org/10018', 'PUT', '[10018,{\"abbreviation\":\"测试\",\"description\":\"测试部门\",\"label\":\"测试部门1\",\"parentId\":102,\"sequence\":1,\"status\":true}]', NULL, NULL, NULL, '2020-12-02 17:30:24', '2020-12-02 17:30:24', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-02 09:30:24');
INSERT INTO `common_opt_log` VALUES (1326106709160226930, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-03 14:16:43', '2020-12-03 14:16:43', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 06:16:43');
INSERT INTO `common_opt_log` VALUES (1326106709160226931, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"global\":true,\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-03 14:16:48', '2020-12-03 14:16:48', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 06:16:47');
INSERT INTO `common_opt_log` VALUES (1326106709160226932, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"global\":true,\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-03 14:17:51', '2020-12-03 14:17:51', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 06:17:51');
INSERT INTO `common_opt_log` VALUES (1326106709160226933, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"global\":true,\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-03 14:18:11', '2020-12-03 14:18:11', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 06:18:10');
INSERT INTO `common_opt_log` VALUES (1326106709160226934, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"global\":true,\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-03 14:20:00', '2020-12-03 14:20:00', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 06:19:59');
INSERT INTO `common_opt_log` VALUES (1326106709160226935, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"global\":true,\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"locked\":true,\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-03 14:21:30', '2020-12-03 14:21:30', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 06:21:29');
INSERT INTO `common_opt_log` VALUES (1326106709160226936, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"global\":true,\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"locked\":true,\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-03 14:21:59', '2020-12-03 14:21:59', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 06:21:59');
INSERT INTO `common_opt_log` VALUES (1326106709160226937, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"global\":true,\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"locked\":true,\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-03 14:25:34', '2020-12-03 14:25:34', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 06:25:34');
INSERT INTO `common_opt_log` VALUES (1326106709160226938, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '修改资源', 'com.wemirr.platform.authority.controller.baseinfo.ResourceController', 'edit', '/resources/10000015', 'PUT', '[10000015,{\"component\":\"/system/development/build/standard\",\"global\":true,\"icon\":\"assistive-listening-systems\",\"label\":\"上海战区\",\"locked\":true,\"model\":\"az_shanghai\",\"parentId\":10000012,\"path\":\"/system/development/release/tenant_1/az_shanghai\",\"sequence\":0,\"type\":\"BUILD_PUBLISH\"}]', NULL, NULL, NULL, '2020-12-03 14:27:32', '2020-12-03 14:27:32', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 06:27:31');
INSERT INTO `common_opt_log` VALUES (1326106709160226939, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":20},null]', NULL, NULL, NULL, '2020-12-03 15:37:33', '2020-12-03 15:37:33', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-03 07:37:33');
INSERT INTO `common_opt_log` VALUES (1326106709160226940, '0:0:0:0:0:0:0:1', '0|0|0|内网IP|内网IP', NULL, 'OPT', '字典查询', 'com.wemirr.platform.authority.controller.common.DictionaryController', 'query', '/dictionaries', 'GET', '[{\"current\":1,\"desc\":false,\"size\":10},null]', NULL, NULL, NULL, '2020-12-04 16:27:53', '2020-12-04 16:27:53', 0, 'Chrome', 'OSX', 'Webkit', '537.36', 'Mac', NULL, 2, '不告诉你', '2020-12-04 08:27:53');
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
