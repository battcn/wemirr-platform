-- ----------------------------
-- 本SQL采用的是MYSQL5.6 如果版本不 < 5.6请去官方安装
-- 否则运行SQL会出现：[Err] 1067 - Invalid default value for 'gmt_create'
-- 如果真的想使用,请修改
-- `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' (修改前)
-- `gmt_create` datetime DEFAULT NULL COMMENT '创建时间' (修改后)
-- ----------------------------
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.1
Source Server Version : 100119
Source Host           : 192.168.0.1:3306
Source Database       : battcn-plus

Target Server Type    : MYSQL
Target Server Version : 100119
File Encoding         : 65001

Date: 2017-06-22 09:54:35
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of t_book
-- ----------------------------

DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
`id` INT(8) NOT NULL AUTO_INCREMENT COMMENT 'ID自增',
`book_no` VARCHAR (64) DEFAULT NULL COMMENT '书籍编号',
`name` VARCHAR (64) NOT NULL COMMENT '书籍名称',
`source` VARCHAR (64) DEFAULT NULL COMMENT '书籍来源',
`type` VARCHAR (20) DEFAULT NULL COMMENT '书籍类型(玄幻小说)',
`author` VARCHAR (20) DEFAULT NULL COMMENT '作者',
`description` VARCHAR (512) DEFAULT NULL COMMENT '描述（简介）',
`cover` VARCHAR (128) DEFAULT NULL COMMENT '书籍封面',
`status` BIT (1) DEFAULT b'0' COMMENT '是否完结 0=未完结 1=已完结',
`gmt_create` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
PRIMARY KEY ( `id` ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '书籍信息';

-- ----------------------------
-- Records of t_book_chapter
-- ----------------------------

DROP TABLE IF EXISTS `t_book_chapter`;
CREATE TABLE `t_book_chapter` (
`id` BIGINT (18) NOT NULL AUTO_INCREMENT COMMENT 'ID自增',
`link` VARCHAR (128) DEFAULT NULL COMMENT '章节链接地址',
`book_no` VARCHAR (64) DEFAULT NULL COMMENT '书籍编号',
`title` VARCHAR (64) DEFAULT NULL COMMENT '标题',
`content` VARCHAR (4096) NOT NULL COMMENT '内容',
`status` BIT (1) DEFAULT b'0' COMMENT '是否已生成模板文件 0=未生成 1=已生成',
`gmt_create` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
PRIMARY KEY (`id`) 
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '书籍章节';