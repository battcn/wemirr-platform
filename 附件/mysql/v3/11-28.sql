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