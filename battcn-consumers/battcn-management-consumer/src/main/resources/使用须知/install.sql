TRUNCATE TABLE  `t_sys_manager`;
TRUNCATE TABLE  `t_sys_menu`;
TRUNCATE TABLE  `t_sys_operate`;
TRUNCATE TABLE  `t_sys_role`;
TRUNCATE TABLE  `t_sys_role_operate`;


INSERT INTO `t_sys_manager` VALUES ('1', 'admin', '123456', '1', '秋殇', '9c6c9e22ae8c773c8f07a75b28563152', '', null, null, null, null, null, null, '2017-06-16 08:59:01', '2017-06-16 08:59:01');
INSERT INTO `t_sys_manager` VALUES ('2', 'battcn', 'battcn', '2', '11333', null, '', null, null, null, null, null, null, '2017-06-19 09:20:14', '2017-06-21 13:49:37');


INSERT INTO `t_sys_menu` VALUES ('1', '系统管理', '系统管理', 'fa fa fa-home', '/sys/sys', 'sys', '', null, '6', '0', ',1,', '2017-06-16 09:00:09', '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES ('2', '菜单管理', '菜单管理', 'fa fa-server', '/sys/menu', 'sys:menu', '', '1', '2', '1', ',1,2,', '2017-06-16 09:00:09', '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES ('3', '操作管理', '操作管理', 'fa fa-hand-lizard-o', '/sys/operate', 'sys:operate', '', '1', '3', '1', ',1,3,', '2017-06-16 09:00:09', '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES ('4', '角色管理', '角色管理', 'fa fa-sitemap', '/sys/role', 'sys:role', '', '1', '4', '1', ',1,4,', '2017-06-16 09:00:09', '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES ('5', '帐号管理', '帐号管理', 'fa fa-sun-o', '/sys/manager', 'sys:manager', '', '1', '1', '1', ',1,5,', '2017-06-16 09:00:09', '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES ('6', '日志管理', '日志管理', 'fa fa-comments-o', '/sys/log', 'sys:logs', '', '1', '7', '1', ',1,6,', '2017-06-16 09:00:09', '2017-06-20 18:57:17');
INSERT INTO `t_sys_menu` VALUES ('7', '字典管理', '字典管理', 'fa fa-newspaper-o', '/sys/dict', 'sys:dict', '\0', '1', '5', '1', ',1,7,', '2017-06-16 09:00:09', '2017-06-21 10:11:01');
INSERT INTO `t_sys_menu` VALUES ('8', '鏖战工具', '鏖战工具', 'fa fa-cogs', '/battcn/utils', 'battcn:utils', '\0', null, '1', '0', ',8,', '2017-06-16 09:00:09', '2017-06-21 11:44:19');
INSERT INTO `t_sys_menu` VALUES ('9', '测试', null, null, null, null, '\0', '8', null, '1', ',8,9,', '2017-06-20 19:05:47', '2017-06-21 11:44:57');



-- ----------------------------
-- Records of t_sys_operate
-- ----------------------------
INSERT INTO `t_sys_operate` VALUES ('1', '1', 'list', '查看', 'list', null, '1', '0', '2017-06-20 18:52:32', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('2', '2', 'edit', '编辑', 'edit', null, '3', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('3', '2', 'list', '查看', 'list', null, '1', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('4', '2', 'remove', '删除', 'remove', null, '4', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('5', '2', 'save', '保存', 'save', null, '5', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('6', '2', 'add', '新增', 'plus', null, '2', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('7', '3', 'edit', '编辑', 'edit', null, '3', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('8', '3', 'list', '查看', 'list', null, '1', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('9', '3', 'remove', '删除', 'remove', null, '4', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('10', '3', 'save', '保存', 'save', null, '5', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('11', '3', 'add', '新增', 'plus', null, '2', '1', '2017-06-16 09:01:08', '2017-06-21 10:15:01');
INSERT INTO `t_sys_operate` VALUES ('12', '4', 'edit', '编辑', 'edit', null, '3', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('13', '4', 'list', '查看', 'list', null, '1', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('14', '4', 'remove', '删除', 'remove', null, '4', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('15', '4', 'save', '保存', 'save', null, '5', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('16', '4', 'add', '新增', 'plus', null, '2', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('17', '5', 'edit', '编辑', 'edit', null, '3', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('18', '5', 'list', '查看', 'list', null, '1', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('19', '5', 'remove', '删除', 'remove', null, '4', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('20', '5', 'save', '保存', 'save', null, '5', '0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('21', '5', 'add', '新增', 'plus', null, '2', '1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES ('22', '6', 'list', '查看', 'list', null, '1', '0', '2017-06-16 09:01:08', '2017-06-21 09:29:01');
INSERT INTO `t_sys_operate` VALUES ('23', '7', 'edit', '编辑', 'edit', null, '2', '1', '2017-06-16 09:01:08', '2017-06-21 09:29:09');
INSERT INTO `t_sys_operate` VALUES ('24', '7', 'list', '查看', 'list', null, '2', '0', '2017-06-16 09:01:08', '2017-06-21 09:29:10');
INSERT INTO `t_sys_operate` VALUES ('25', '7', 'remove', '删除', 'remove', null, '1', '1', '2017-06-16 09:01:08', '2017-06-21 09:29:12');
INSERT INTO `t_sys_operate` VALUES ('26', '7', 'save', '保存', 'save', null, '3', '0', '2017-06-16 09:01:08', '2017-06-21 09:29:13');
INSERT INTO `t_sys_operate` VALUES ('27', '4', 'permissions', '分配权限', 'permissions', null, '1', '1', '2017-06-20 13:38:21', '2017-06-21 09:29:14');
INSERT INTO `t_sys_operate` VALUES ('28', '8', 'list', '查看', 'list', null, '1', '0', '2017-06-16 09:01:08', '2017-06-21 10:09:23');
INSERT INTO `t_sys_operate` VALUES ('29', '6', 'export', '导出', 'file-excel-o', '导出Excel', '1', '1', '2018-01-17 12:25:43', '2018-01-17 12:36:50');



-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '超级管理员', 'manager', '超级管理员,不听话就封号', '2017-06-16 09:01:23', '2017-06-16 09:01:23');
INSERT INTO `t_sys_role` VALUES ('2', '测试管理员', 'test', '专门测试没有权限的时候1', '2017-06-16 09:01:23', '2017-06-16 09:01:23');
INSERT INTO `t_sys_role` VALUES ('3', '开发管理员', 'code', '我是开发不服删数据库', '2017-06-16 09:01:23', '2017-06-20 19:11:31');


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

