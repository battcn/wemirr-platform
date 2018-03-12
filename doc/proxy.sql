/*
Navicat MySQL Data Transfer

Source Server         : 阿里云
Source Server Version : 50638
Source Host           : 106.14.126.115:3306
Source Database       : Miscellaneous

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-03-11 12:53:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ip_pool
-- ----------------------------
DROP TABLE IF EXISTS `ip_pool`;
CREATE TABLE `ip_pool` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `port` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=604 DEFAULT CHARSET=utf8;
