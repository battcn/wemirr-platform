SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_logs`;
CREATE TABLE `t_sys_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `methods` varchar(128) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `optime` datetime DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `params` longtext,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_logs
-- ----------------------------
INSERT INTO `t_sys_logs` VALUES ('11', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 15:37:35', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('12', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 15:38:39', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('13', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 15:45:04', '0:0:0:0:0:0:0:1', '7', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('14', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 15:53:56', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('15', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 15:56:00', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('7', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 15:37:17', '0:0:0:0:0:0:0:1', '8', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('8', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 15:37:19', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('9', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 15:37:20', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('10', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 15:37:21', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('16', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 16:00:41', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('17', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 16:00:47', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('18', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 16:01:01', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('19', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 16:05:13', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('20', 'admin', '日志模块', '日志list', '进入日志查询的页面', '2016-09-15 16:05:51', '0:0:0:0:0:0:0:1', '0', null, '/pub/logs/list');
INSERT INTO `t_sys_logs` VALUES ('68', 'test', '日志模块', '日志list', '进入日志查询的页面', '2017-01-04 16:02:38', '39.82.200.82', '0', null, '/pub/logs/list');
