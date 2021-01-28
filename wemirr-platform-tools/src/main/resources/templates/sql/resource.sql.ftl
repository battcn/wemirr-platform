-- 模块菜单
INSERT INTO `pm_resource` (`name`, `title`, `permission`, `path`, `component`, `icon`, `sort`, `type`, `parent_id`,`platform_id`, `del_flag`, `create_time`, `update_time`)
SELECT  '${package.ModuleName}', '${package.ModuleName}', null, null, null, null, '100', '1', '1', ${cfg.platformId},'0', '${cfg.now}', '${cfg.now}'
FROM DUAL WHERE NOT EXISTS(SELECT id FROM pm_resource WHERE name = '${package.ModuleName}');

-- 查找模块菜单id
set @parentId = (select id from pm_resource where  name = '${package.ModuleName}');

-- 管理菜单
INSERT INTO `pm_resource` (`name`, `title`, `permission`, `path`, `component`, `icon`, `sort`, `type`, `parent_id`, `platform_id`, `del_flag`, `create_time`, `update_time`)
VALUES ( '${table.entityPath}_manager','${table.comment!}管理',
'${package.ModuleName}:${table.entityPath}:view',
'/${package.ModuleName}/${table.entityPath}',
'/${package.ModuleName}/views/${table.entityPath}', null, '100', '1', @parentId, ${cfg.platformId},'0', '${cfg.now}', '${cfg.now}');


-- 菜单对应按钮SQL
set @parentId = @@identity;
INSERT INTO `pm_resource` (`name`, `title`, `permission`, `path`, `component`, `icon`, `sort`, `type`, `parent_id`, `platform_id`,`del_flag`, `create_time`, `update_time`)
VALUES ( '${package.ModuleName}_${table.entityPath}_view','${table.comment!}查看','${package.ModuleName}:${table.entityPath}:view', null, null, null, '100', '2', @parentId, ${cfg.platformId},'0', '${cfg.now}', '${cfg.now}');

INSERT INTO `pm_resource` (`name`, `title`, `permission`, `path`, `component`, `icon`, `sort`, `type`, `parent_id`,`platform_id`, `del_flag`, `create_time`, `update_time`)
VALUES ( '${package.ModuleName}_${table.entityPath}_add','${table.comment!}新增','${package.ModuleName}:${table.entityPath}:add', null, null, null, '100', '2', @parentId, ${cfg.platformId},'0', '${cfg.now}', '${cfg.now}');

INSERT INTO `pm_resource` (`name`, `title`, `permission`, `path`, `component`, `icon`, `sort`, `type`, `parent_id`,`platform_id`, `del_flag`, `create_time`, `update_time`)
VALUES ( '${package.ModuleName}_${table.entityPath}_edit','${table.comment!}修改','${package.ModuleName}:${table.entityPath}:edit', null, null, null, '100', '2', @parentId, ${cfg.platformId},'0', '${cfg.now}', '${cfg.now}');

INSERT INTO `pm_resource` (`name`, `title`, `permission`, `path`, `component`, `icon`, `sort`, `type`, `parent_id`, `platform_id`,`del_flag`, `create_time`, `update_time`)
VALUES ( '${package.ModuleName}_${table.entityPath}_del','${table.comment!}删除','${package.ModuleName}:${table.entityPath}:del', null, null, null, '100', '2', @parentId, ${cfg.platformId},'0', '${cfg.now}', '${cfg.now}');






