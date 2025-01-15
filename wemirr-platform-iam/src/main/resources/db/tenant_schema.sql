SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for b_message_channel
-- ----------------------------
DROP TABLE IF EXISTS `b_message_channel`;
CREATE TABLE `b_message_channel` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渠道标题',
                                     `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息类型',
                                     `status` bit(1) DEFAULT b'1' COMMENT '状态（0=禁用;1=启用）',
                                     `setting` json DEFAULT NULL COMMENT '设置（JSON）',
                                     `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                     `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
                                     `deleted` bit(1) DEFAULT b'0',
                                     `created_by` bigint DEFAULT '0' COMMENT '创建人id',
                                     `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                     `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
                                     `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                     `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息通知';

-- ----------------------------
-- Records of b_message_channel
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for b_message_notify
-- ----------------------------
DROP TABLE IF EXISTS `b_message_notify`;
CREATE TABLE `b_message_notify` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                    `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
                                    `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息类型',
                                    `template_id` bigint DEFAULT NULL COMMENT '消息模板ID',
                                    `variables` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息变量',
                                    `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                    `user_id` bigint DEFAULT NULL COMMENT '接收用户ID',
                                    `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                    `subscribe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订阅人 比如 邮箱,手机号,钉钉ID等',
                                    `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                    `deleted` bit(1) DEFAULT b'0',
                                    `created_by` bigint DEFAULT '0' COMMENT '创建人id',
                                    `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
                                    `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                    `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息通知';

-- ----------------------------
-- Records of b_message_notify
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for b_message_template
-- ----------------------------
DROP TABLE IF EXISTS `b_message_template`;
CREATE TABLE `b_message_template` (
                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                      `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板编码',
                                      `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名称',
                                      `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板类型',
                                      `subject` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息标题（如邮件标题）',
                                      `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息标题（如邮件标题）',
                                      `status` bit(1) DEFAULT b'1' COMMENT '状态（0=禁用;1=启用）',
                                      `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                      `deleted` bit(1) DEFAULT b'0',
                                      `created_by` bigint DEFAULT '0' COMMENT '创建人id',
                                      `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                      `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
                                      `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
                                      `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of b_message_template
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_diff_log
-- ----------------------------
DROP TABLE IF EXISTS `c_diff_log`;
CREATE TABLE `c_diff_log` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
                              `variables` json DEFAULT NULL COMMENT '变量信息，JSON格式',
                              `service_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '服务名称',
                              `business_group` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '业务组',
                              `business_tag` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '业务标签',
                              `business_key` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '业务关键字',
                              `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '描述信息',
                              `status` int DEFAULT NULL COMMENT '状态',
                              `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                              `created_by` bigint DEFAULT NULL COMMENT '创建者ID',
                              `created_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建者名称',
                              `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `extra` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '额外信息，JSON格式',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='通用差异日志表';

-- ----------------------------
-- Records of c_diff_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_i18n_data
-- ----------------------------
DROP TABLE IF EXISTS `c_i18n_data`;
CREATE TABLE `c_i18n_data` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国际化信息';

-- ----------------------------
-- Records of c_i18n_data
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_i18n_locale_message
-- ----------------------------
DROP TABLE IF EXISTS `c_i18n_locale_message`;
CREATE TABLE `c_i18n_locale_message` (
                                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                         `parent_id` bigint DEFAULT NULL COMMENT 'i18n_data.id',
                                         `locale` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '语言',
                                         `message` varchar(255) DEFAULT NULL COMMENT '文本值，可以使用 {} 加角标，作为占位符',
                                         `created_by` bigint DEFAULT '0' COMMENT '创建人id',
                                         `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                         `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='国际化信息';

-- ----------------------------
-- Records of c_i18n_locale_message
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_login_log
-- ----------------------------
DROP TABLE IF EXISTS `c_login_log`;
CREATE TABLE `c_login_log` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                               `tenant_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户编码',
                               `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录IP',
                               `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录地点',
                               `client_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人客户端ID',
                               `principal` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人账号',
                               `platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台',
                               `engine` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '引擎类型',
                               `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
                               `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作系统',
                               `login_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录类型',
                               `created_by` bigint DEFAULT NULL,
                               `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录日志';

-- ----------------------------
-- Records of c_login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `c_opt_log`;
CREATE TABLE `c_opt_log` (
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
                             `status` bit(1) DEFAULT NULL COMMENT '日志状态（true=正常;false=异常）',
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
-- Records of c_opt_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_data_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_permission_resource`;
CREATE TABLE `sys_data_permission_resource` (
                                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                `owner_id` bigint NOT NULL COMMENT '拥有者',
                                                `owner_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '拥有类型（角色，用户）',
                                                `data_id` bigint NOT NULL COMMENT '数据ID',
                                                `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '数据类型（机构、角色、租户等等）',
                                                `created_by` bigint DEFAULT NULL COMMENT '创建人id',
                                                `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
                                                `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='数据权限资源表';

-- ----------------------------
-- Records of sys_data_permission_resource
-- ----------------------------
BEGIN;
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
                           `parent_id` bigint DEFAULT '0' COMMENT '父ID',
                           `tree_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '树节点路径',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组织';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
                                `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
                                `sequence` tinyint DEFAULT NULL COMMENT '排序',
                                `org_id` bigint DEFAULT '0' COMMENT '组织ID',
                                `status` bit(1) DEFAULT b'1' COMMENT '状态',
                                `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
                                `deleted` bit(1) DEFAULT b'0',
                                `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                `created_by` bigint DEFAULT NULL,
                                `last_modified_by` bigint DEFAULT NULL,
                                `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='岗位';

-- ----------------------------
-- Records of sys_position
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `tenant_id` bigint DEFAULT NULL COMMENT '租户编码',
                            `code` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '角色编码',
                            `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT '名称',
                            `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述信息',
                            `scope_type` tinyint DEFAULT NULL COMMENT '数据权限范围，值越大，权限越大',
                            `status` tinyint(1) DEFAULT '0' COMMENT '0=正常1=禁用',
                            `super` tinyint(1) DEFAULT '0' COMMENT '0=非 1=管理员',
                            `readonly` tinyint(1) DEFAULT '0' COMMENT '是否内置角色',
                            `created_by` bigint DEFAULT '0' COMMENT '创建人id',
                            `created_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
                            `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
                            `last_modified_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
                            `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of sys_site_notify
-- ----------------------------
BEGIN;
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
COMMIT;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                          `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                          `content_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
                          `size` bigint DEFAULT NULL COMMENT '文件大小',
                          `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录地点',
                          `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'IP',
                          `engine` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '引擎类型',
                          `engine_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '引擎版本',
                          `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作系统',
                          `bucket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作系统',
                          `origin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原始名称',
                          `target_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '目标名称',
                          `mapping_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '映射地址',
                          `full_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '完整地址',
                          `extend` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拓展字段',
                          `deleted` bit(1) DEFAULT b'0',
                          `created_by` bigint DEFAULT '0' COMMENT '创建人id',
                          `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
                          `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
                          `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人名称',
                          `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `UN_TARGET_NAME` (`target_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文件';

-- ----------------------------
-- Records of t_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_dict`;
CREATE TABLE `t_tenant_dict` (
                                 `id` bigint NOT NULL COMMENT '雪花算法ID',
                                 `code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '编码',
                                 `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
                                 `description` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
                                 `status` bit(1) DEFAULT b'1' COMMENT '状态',
                                 `readonly` bit(1) DEFAULT b'0' COMMENT '0=否；1=是；只读数据不允许删除',
                                 `sequence` tinyint DEFAULT NULL COMMENT '排序',
                                 `tenant_id` bigint DEFAULT NULL COMMENT '租户ID t_tenant.id',
                                 `deleted` bit(1) DEFAULT b'0',
                                 `created_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                 `created_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建人名称',
                                 `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `last_modified_by` bigint DEFAULT NULL COMMENT '更新人ID',
                                 `last_modified_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '更新人名称',
                                 `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 KEY `idx_code` (`code`) USING BTREE COMMENT '租户字典索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_tenant_dict
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_dict_item`;
CREATE TABLE `t_tenant_dict_item` (
                                      `id` bigint NOT NULL COMMENT '雪花算法id',
                                      `dict_id` varchar(255) DEFAULT NULL COMMENT '字典ID',
                                      `dict_code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '字典编码',
                                      `value` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '字典值',
                                      `label` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '子项名',
                                      `readonly` bit(1) DEFAULT b'0' COMMENT '是否只读 true = 只读;false = 可编辑修改',
                                      `status` bit(1) DEFAULT b'1' COMMENT '状态',
                                      `color` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '颜色',
                                      `deleted` bit(1) DEFAULT b'0',
                                      `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '描述',
                                      `sequence` int DEFAULT '1' COMMENT '排序',
                                      `tenant_id` bigint DEFAULT NULL COMMENT '租户id，t_tenant.id',
                                      `created_by` bigint DEFAULT '0' COMMENT '创建人id',
                                      `created_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建人名称',
                                      `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
                                      `last_modified_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '更新人名称',
                                      `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_tenant_dict_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                          `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                          `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
                          `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '密码',
                          `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
                          `org_id` bigint DEFAULT NULL COMMENT '组织ID',
                          `position_id` bigint DEFAULT NULL COMMENT '岗位ID',
                          `readonly` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否内置',
                          `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
                          `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机',
                          `id_card` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证',
                          `sex` tinyint DEFAULT '1' COMMENT '性别',
                          `status` bit(1) DEFAULT b'0' COMMENT '状态 \n1启用 0禁用',
                          `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '头像',
                          `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
                          `nation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '民族',
                          `education` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学历',
                          `position_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职位状态',
                          `birthday` date DEFAULT NULL COMMENT '生日',
                          `last_login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后一次登录IP',
                          `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
                          `deleted` bit(1) DEFAULT b'0',
                          `created_by` bigint DEFAULT '0' COMMENT '创建人id',
                          `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
                          `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
                          `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人名称',
                          `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `UN_ACCOUNT_TENANT` (`username`,`tenant_id`) USING BTREE COMMENT '账号唯一约束'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
