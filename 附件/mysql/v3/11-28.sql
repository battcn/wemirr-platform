ALTER TABLE `t_user`
    ADD COLUMN `last_login_ip`   varchar(255) NULL COMMENT '最后一次登录IP' AFTER `birthday`,
    ADD COLUMN `last_login_time` datetime     NULL COMMENT '最后一次登录时间' AFTER `last_login_ip`;


ALTER TABLE `c_login_log`
    DROP COLUMN `user_id`,
    DROP COLUMN `name`,
    MODIFY COLUMN `platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '平台' AFTER `principal`,
    MODIFY COLUMN `engine` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '引擎类型' AFTER `platform`,
    MODIFY COLUMN `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '浏览器名称' AFTER `engine`,
    MODIFY COLUMN `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '操作系统' AFTER `browser`,
    ADD COLUMN `login_type` varchar(50) NULL COMMENT '登录类型' AFTER `os`;


ALTER TABLE `c_login_log`
    ADD COLUMN `tenant_code` varchar(255) NULL COMMENT '租户编码' AFTER `tenant_id`;


ALTER TABLE `sys_resource`
    CHANGE COLUMN `label` `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称' AFTER `id`;

ALTER TABLE `sys_resource`
    ADD COLUMN `category` tinyint NULL COMMENT '分类' AFTER `type`;

ALTER TABLE `sys_resource`
    ADD COLUMN `keep_alive` bit(1) NULL COMMENT '开启后页面会缓存，不会重新加载，仅在标签页启用时有效' AFTER `status`;

ALTER TABLE `sys_resource`
    CHANGE COLUMN `display` `visible` bit(1) NULL DEFAULT b'1' COMMENT '0=隐藏;1=显示' AFTER `global`;


ALTER TABLE `sys_resource`
    DROP COLUMN `category`,
    MODIFY COLUMN `type` varchar(30) NULL DEFAULT 1 COMMENT '类型 directory=目录;menu=菜单;iframe=内嵌;link=外链;button=按钮' AFTER `style`;