-- ----------------------------
-- 本SQL采用的是MYSQL5.6 如果版本不 < 5.6请去官方安装
-- 否则运行SQL会出现：[Err] 1067 - Invalid default value for 'gmt_create'
-- 如果真的想使用,请修改
-- `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' (修改前)
-- `gmt_create` datetime DEFAULT NULL COMMENT '创建时间' (修改后)
-- ----------------------------


/*
Navicat MySQL Data Transfer

Source Server         : 192.168.18.183
Source Server Version : 100119
Source Host           : 192.168.18.183:3306
Source Database       : battcn-plus

Target Server Type    : MYSQL
Target Server Version : 100119
File Encoding         : 65001

Date: 2017-06-22 09:54:35
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log` (
  `id`           INT(11)   NOT NULL AUTO_INCREMENT
  COMMENT '自增ID',
  `account`      VARCHAR(50)        DEFAULT NULL
  COMMENT '账号',
  `title`        VARCHAR(40)        DEFAULT NULL
  COMMENT '标题',
  `methods`      VARCHAR(128)       DEFAULT NULL
  COMMENT '执行的方法',
  `message`      VARCHAR(255)       DEFAULT NULL
  COMMENT '消息',
  `ip`           VARCHAR(15)        DEFAULT NULL
  COMMENT 'IP',
  `duration`     SMALLINT(5)        DEFAULT NULL
  COMMENT '执行时间',
  `params`       LONGTEXT COMMENT '请求参数',
  `url`          VARCHAR(255)       DEFAULT NULL
  COMMENT '请求地址',
  `gmt_create`   DATETIME           DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `gmt_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_manager`;
CREATE TABLE `t_sys_manager` (
  `id`            INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT '自增ID',
  `account`       VARCHAR(50) NOT NULL
  COMMENT '账号',
  `password`      VARCHAR(50) NOT NULL
  COMMENT '密码',
  `role_id`       TINYINT(3)           DEFAULT NULL
  COMMENT '角色',
  `name`          VARCHAR(50)          DEFAULT NULL
  COMMENT '姓名',
  `credential`    VARCHAR(100)         DEFAULT NULL
  COMMENT '验证凭证',
  `locked`        BIT(1)               DEFAULT b'1'
  COMMENT '是否启用 0=禁用 1=启用',
  `email`         VARCHAR(64)          DEFAULT NULL
  COMMENT '邮箱',
  `phone`         VARCHAR(64)          DEFAULT NULL
  COMMENT '电话',
  `sex`           VARCHAR(255)         DEFAULT NULL
  COMMENT '性别',
  `type`          VARCHAR(255)         DEFAULT NULL
  COMMENT '类型',
  `photo`         VARCHAR(266)         DEFAULT NULL
  COMMENT '头像',
  `last_login_ip` VARCHAR(15)          DEFAULT NULL
  COMMENT '登陆IP',
  `gmt_create`    DATETIME             DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `gmt_modified`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_ACCOUNT` (`account`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COMMENT = '用户管理表';

-- ----------------------------
-- Records of t_sys_manager
-- ----------------------------
INSERT INTO `t_sys_manager` VALUES
  ('1', 'admin', '123456', '1', '秋殇', '9c6c9e22ae8c773c8f07a75b28563152', '', NULL, NULL, NULL, NULL, NULL, NULL,
   '2017-06-16 08:59:01', '2017-06-16 08:59:01');
INSERT INTO `t_sys_manager` VALUES
  ('2', 'battcn', 'battcn', '2', '11333', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '2017-06-19 09:20:14',
   '2017-06-21 13:49:37');

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id`           INT(8)      NOT NULL AUTO_INCREMENT
  COMMENT '自增ID',
  `name`         VARCHAR(50) NOT NULL
  COMMENT '名称',
  `remark`       VARCHAR(2000)        DEFAULT NULL
  COMMENT '备注',
  `icon`         VARCHAR(100)         DEFAULT NULL
  COMMENT '图标',
  `channel`      VARCHAR(100)         DEFAULT NULL
  COMMENT '渠道',
  `param`        VARCHAR(200)         DEFAULT NULL,
  `locked`       BIT(1)               DEFAULT b'1'
  COMMENT '是否启用 0=禁用 1=启用',
  `pater_id`     INT(8)               DEFAULT NULL
  COMMENT '父级编号',
  `ordno`        INT(8)               DEFAULT NULL
  COMMENT '排序编号',
  `n_level`      INT(8)               DEFAULT NULL
  COMMENT '级别',
  `scort`        VARCHAR(100)         DEFAULT NULL
  COMMENT 'tree所需',
  `gmt_create`   DATETIME             DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `gmt_modified` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference` (`pater_id`),
  CONSTRAINT `t_sys_menu_ibfk_1` FOREIGN KEY (`pater_id`) REFERENCES `t_sys_menu` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8
  COMMENT = '菜单表';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES
  ('1', '系统管理', '系统管理', 'fa fa fa-home', '/sys/sys', 'sys', '', NULL, '6', '0', ',1,', '2017-06-16 09:00:09',
   '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES
  ('2', '菜单管理', '菜单管理', 'fa fa-server', '/sys/menu', 'sys:menu', '', '1', '2', '1', ',1,2,', '2017-06-16 09:00:09',
   '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES
  ('3', '操作管理', '操作管理', 'fa fa-hand-lizard-o', '/sys/operate', 'sys:operate', '', '1', '3', '1', ',1,3,',
   '2017-06-16 09:00:09', '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES
  ('4', '角色管理', '角色管理', 'fa fa-sitemap', '/sys/role', 'sys:role', '', '1', '4', '1', ',1,4,', '2017-06-16 09:00:09',
   '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES
  ('5', '帐号管理', '帐号管理', 'fa fa-sun-o', '/sys/manager', 'sys:manager', '', '1', '1', '1', ',1,5,',
   '2017-06-16 09:00:09', '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES
  ('6', '日志管理', '日志管理', 'fa fa-comments-o', '/sys/log', 'sys:logs', '', '1', '7', '1', ',1,6,', '2017-06-16 09:00:09',
   '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES
  ('7', '字典管理', '字典管理', 'fa fa-newspaper-o', '/sys/dict', 'sys:dict', '\0', '1', '5', '1', ',1,7,',
   '2017-06-16 09:00:09', '2017-06-21 10:11:01');
INSERT INTO `t_sys_menu` VALUES
  ('8', '鏖战工具', '鏖战工具', 'fa fa-cogs', '/battcn/utils', 'battcn:utils', '\0', NULL, '1', '0', ',8,',
   '2017-06-16 09:00:09', '2017-06-21 11:44:19');
INSERT INTO `t_sys_menu`
VALUES ('9', '测试', NULL, NULL, NULL, NULL, '\0', '8', NULL, '1', ',8,9,', '2017-06-20 19:05:47', '2017-06-21 11:44:57');

-- ----------------------------
-- Table structure for t_sys_operate
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_operate`;
CREATE TABLE `t_sys_operate` (
  `id`           INT(8)      NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `menu_id`      INT(8)      NOT NULL
  COMMENT '菜单ID',
  `op`           VARCHAR(30) NOT NULL
  COMMENT '选项',
  `name`         VARCHAR(64) NOT NULL
  COMMENT '名字',
  `icon`         VARCHAR(50)          DEFAULT NULL
  COMMENT '图标',
  `remark`       VARCHAR(2000)        DEFAULT NULL
  COMMENT '备注',
  `ordno`        INT(11)              DEFAULT NULL
  COMMENT '排序号',
  `display`      INT(1)      NOT NULL
  COMMENT '是否显示出来',
  `gmt_create`   DATETIME             DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `gmt_modified` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_op` (`menu_id`, `op`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 29
  DEFAULT CHARSET = utf8
  COMMENT = '操作表';

-- ----------------------------
-- Records of t_sys_operate
-- ----------------------------
INSERT INTO `t_sys_operate`
VALUES ('1', '1', 'list', '查看', 'list', NULL, '1', '0', '2017-06-20 18:52:32', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('2', '2', 'edit', '编辑', 'edit', NULL, '3', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('3', '2', 'list', '查看', 'list', NULL, '1', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('4', '2', 'remove', '删除', 'remove', NULL, '4', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('5', '2', 'save', '保存', 'save', NULL, '5', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('6', '2', 'add', '新增', 'plus', NULL, '2', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('7', '3', 'edit', '编辑', 'edit', NULL, '3', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('8', '3', 'list', '查看', 'list', NULL, '1', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('9', '3', 'remove', '删除', 'remove', NULL, '4', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('10', '3', 'save', '保存', 'save', NULL, '5', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('11', '3', 'add', '新增', 'plus', NULL, '2', '1', '2017-06-16 09:01:08', '2017-06-21 10:15:01');
INSERT INTO `t_sys_operate`
VALUES ('12', '4', 'edit', '编辑', 'edit', NULL, '3', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('13', '4', 'list', '查看', 'list', NULL, '1', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('14', '4', 'remove', '删除', 'remove', NULL, '4', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('15', '4', 'save', '保存', 'save', NULL, '5', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('16', '4', 'add', '新增', 'plus', NULL, '2', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('17', '5', 'edit', '编辑', 'edit', NULL, '3', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('18', '5', 'list', '查看', 'list', NULL, '1', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('19', '5', 'remove', '删除', 'remove', NULL, '4', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('20', '5', 'save', '保存', 'save', NULL, '5', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('21', '5', 'add', '新增', 'plus', NULL, '2', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate`
VALUES ('22', '6', 'list', '查看', 'list', NULL, '1', '0', '2017-06-16 09:01:08', '2017-06-21 09:29:01');
INSERT INTO `t_sys_operate`
VALUES ('23', '7', 'edit', '编辑', 'edit', NULL, '2', '1', '2017-06-16 09:01:08', '2017-06-21 09:29:09');
INSERT INTO `t_sys_operate`
VALUES ('24', '7', 'list', '查看', 'list', NULL, '2', '0', '2017-06-16 09:01:08', '2017-06-21 09:29:10');
INSERT INTO `t_sys_operate`
VALUES ('25', '7', 'remove', '删除', 'remove', NULL, '1', '1', '2017-06-16 09:01:08', '2017-06-21 09:29:12');
INSERT INTO `t_sys_operate`
VALUES ('26', '7', 'save', '保存', 'save', NULL, '3', '0', '2017-06-16 09:01:08', '2017-06-21 09:29:13');
INSERT INTO `t_sys_operate`
VALUES ('27', '4', 'permissions', '分配权限', 'permissions', NULL, '1', '1', '2017-06-20 13:38:21', '2017-06-21 09:29:14');
INSERT INTO `t_sys_operate`
VALUES ('28', '8', 'list', '查看', 'list', NULL, '1', '0', '2017-06-16 09:01:08', '2017-06-21 10:09:23');
INSERT INTO `t_sys_operate`
VALUES ('29', '6', 'export', '导出', 'file-excel-o', '导出Excel', '1', '1', '2018-01-17 12:25:43', '2018-01-17 12:36:50');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id`           INT(8)      NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `name`         VARCHAR(50) NOT NULL
  COMMENT '角色名',
  `code`         VARCHAR(50)          DEFAULT NULL
  COMMENT '编号',
  `remark`       VARCHAR(200)         DEFAULT NULL
  COMMENT '备注',
  `gmt_create`   DATETIME             DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `gmt_modified` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '超级管理员', 'manager', '超级管理员,不听话就封号', '2017-06-16 09:01:23', '2017-06-16 09:01:23');
INSERT INTO `t_sys_role` VALUES ('2', '测试管理员', 'test', '专门测试没有权限的时候1', '2017-06-16 09:01:23', '2017-06-16 09:01:23');
INSERT INTO `t_sys_role` VALUES ('3', '开发管理员', 'code', '我是开发不服删数据库', '2017-06-16 09:01:23', '2017-06-20 19:11:31');

-- ----------------------------
-- Table structure for t_sys_role_operate
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_operate`;
CREATE TABLE `t_sys_role_operate` (
  `role_id`      INT(8)    NOT NULL
  COMMENT '角色ID',
  `operate_id`   INT(8)    NOT NULL
  COMMENT '操作表ID',
  `gmt_create`   DATETIME           DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `gmt_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后修改时间',
  PRIMARY KEY (`role_id`, `operate_id`),
  KEY `operate_id` (`operate_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_sys_role_operate
-- ----------------------------
INSERT INTO `t_sys_role_operate` VALUES ('1', '1', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '2', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '3', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '4', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '5', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '6', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '7', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '8', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '9', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '10', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '11', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '12', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '13', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '14', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '15', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '16', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '17', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '18', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '19', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('1', '20', '2017-06-16 09:01:40', '2017-06-16 09:01:40');
INSERT INTO `t_sys_role_operate` VALUES ('1', '21', '2017-06-16 09:01:40', '2017-06-20 20:17:35');
INSERT INTO `t_sys_role_operate` VALUES ('1', '22', '2017-06-20 20:17:58', '2017-06-20 20:18:14');
INSERT INTO `t_sys_role_operate` VALUES ('1', '23', '2017-06-16 09:01:40', '2017-06-20 19:12:06');
INSERT INTO `t_sys_role_operate` VALUES ('1', '24', '2017-06-16 09:01:40', '2017-06-20 19:12:07');
INSERT INTO `t_sys_role_operate` VALUES ('1', '25', '2017-06-16 09:01:40', '2017-06-20 19:12:07');
INSERT INTO `t_sys_role_operate` VALUES ('1', '26', '2017-06-16 09:01:40', '2017-06-20 19:12:07');
INSERT INTO `t_sys_role_operate` VALUES ('1', '27', '2017-06-16 09:01:40', '2017-06-20 19:12:07');
INSERT INTO `t_sys_role_operate` VALUES ('1', '28', '2017-06-16 09:01:40', '2017-06-20 19:14:27');
INSERT INTO `t_sys_role_operate` VALUES ('1', '29', '2017-06-16 09:01:40', '2017-06-20 20:18:03');
INSERT INTO `t_sys_role_operate` VALUES ('1', '30', '2017-06-16 09:01:40', '2017-06-20 20:18:03');
INSERT INTO `t_sys_role_operate` VALUES ('1', '31', '2017-06-20 20:17:54', '2017-06-20 20:18:11');
INSERT INTO `t_sys_role_operate` VALUES ('2', '1', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('2', '2', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('2', '3', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('2', '4', '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES ('2', '5', '2017-06-16 09:01:39', '2017-06-16 09:01:39');

-- ----------------------------
-- Procedure structure for refreshTreeNodes
-- ----------------------------
DROP PROCEDURE IF EXISTS `refreshTreeNodes`;
DELIMITER ;;
CREATE DEFINER =`root`@`localhost` PROCEDURE `refreshTreeNodes`()
  BEGIN

    DECLARE LEVEL INT;
    SET LEVEL = 0;
    UPDATE t_sys_menu a
      INNER JOIN (
                   SELECT
                     id,
                     n_level,
                     concat(',', ID, ',') scort
                   FROM
                     t_sys_menu
                   WHERE
                     pater_id IS NULL
                 ) b ON a.id = b.id
    SET a.n_level = b.n_level,
      a.scort     = b.scort;
    WHILE FOUND_ROWS() > 0 DO
      SET LEVEL = LEVEL + 1;
      UPDATE t_sys_menu a
        INNER JOIN (
                     SELECT
                       ID,
                       n_level,
                       scort
                     FROM
                       t_sys_menu
                     WHERE
                       n_level = LEVEL - 1
                   ) b ON a.pater_id = b.id
      SET a.n_level = b.n_level,
        a.scort     = concat(b.scort, a.ID, ',');
    END
    WHILE;
  END
;;
DELIMITER ;