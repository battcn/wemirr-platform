/*
 Navicat Premium Data Transfer

 Source Server         : localhost-docker
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : v4-ai

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 27/12/2025 19:09:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ai_agent
-- ----------------------------
DROP TABLE IF EXISTS `ai_agent`;
CREATE TABLE `ai_agent` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `user_id` bigint DEFAULT NULL COMMENT 'userID',
                            `name` varchar(100) DEFAULT NULL COMMENT '智能体名称',
                            `kb_id` bigint DEFAULT NULL COMMENT '知识库ID',
                            `model_id` bigint DEFAULT NULL COMMENT '绑定的模型ID',
                            `temperature` decimal(3,2) DEFAULT '0.70' COMMENT '发散程度',
                            `description` varchar(255) DEFAULT NULL COMMENT '智能体描述',
                            `role_prompt` text COMMENT '角色设定(System Prompt)',
                            `tools` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '工具/函数配置',
                            `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                            `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Agent 头像',
                            `system_prompt` varchar(255) DEFAULT NULL COMMENT '预设系统提示词',
                            `mcp_server_ids` varchar(255) DEFAULT NULL,
                            `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                            `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                            `create_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                            `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
                            `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2004778922172108802 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='智能体配置';

-- ----------------------------
-- Table structure for ai_agent_kb_rel
-- ----------------------------
DROP TABLE IF EXISTS `ai_agent_kb_rel`;
CREATE TABLE `ai_agent_kb_rel` (
                                   `agent_id` bigint NOT NULL,
                                   `kb_id` bigint NOT NULL,
                                   `priority` int DEFAULT '0',
                                   PRIMARY KEY (`agent_id`,`kb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='智能体-知识库关联表';

-- ----------------------------
-- Table structure for ai_agent_mcp_rel
-- ----------------------------
DROP TABLE IF EXISTS `ai_agent_mcp_rel`;
CREATE TABLE `ai_agent_mcp_rel` (
                                    `agent_id` bigint NOT NULL,
                                    `mcp_server_id` bigint NOT NULL,
                                    PRIMARY KEY (`agent_id`,`mcp_server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='智能体-MCP关联表';

-- ----------------------------
-- Table structure for ai_conversation
-- ----------------------------
DROP TABLE IF EXISTS `ai_conversation`;
CREATE TABLE `ai_conversation` (
                                   `id` bigint NOT NULL COMMENT 'ID',
                                   `title` varchar(255) DEFAULT NULL COMMENT '会话名称',
                                   `user_id` bigint DEFAULT NULL COMMENT '归属人',
                                   `agent_id` bigint DEFAULT NULL COMMENT '智能体ID',
                                   `type` tinyint DEFAULT NULL COMMENT '对话类型：1-普通对话 2-通用智能体对话 3-平台智能体 4-知识库对话 5-图片生成',
                                   `last_message` text COMMENT '最后一条消息内容',
                                   `message_count` int DEFAULT NULL COMMENT '消息数量',
                                   `pinned` tinyint(1) DEFAULT NULL COMMENT '是否置顶',
                                   `knowledge_base_ids` bigint DEFAULT NULL COMMENT '关联的知识库ids',
                                   `tenant_id` bigint DEFAULT NULL,
                                   `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                   `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                   `create_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                   `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
                                   `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会话信息';

-- ----------------------------
-- Table structure for ai_knowledge_base
-- ----------------------------
DROP TABLE IF EXISTS `ai_knowledge_base`;
CREATE TABLE `ai_knowledge_base` (
                                     `id` bigint NOT NULL COMMENT 'ID',
                                     `name` varchar(255) DEFAULT NULL COMMENT '知识库名称',
                                     `collection_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'default_coll' COMMENT '向量集合名称',
                                     `chat_model_id` bigint NOT NULL COMMENT '对话模型ID',
                                     `embed_model_id` bigint DEFAULT NULL COMMENT 'Embedding模型ID',
                                     `rerank_model_id` bigint DEFAULT NULL,
                                     `score_threshold` decimal(10,2) DEFAULT NULL COMMENT '相似度阈值 (0.0-1.0)',
                                     `top_k` int DEFAULT NULL COMMENT '单次召回数量(TopK)',
                                     `chunk_size` double DEFAULT NULL,
                                     `chunk_overlap` varchar(255) DEFAULT NULL,
                                     `version` int DEFAULT NULL COMMENT '版本号',
                                     `metadata` json DEFAULT NULL COMMENT '元数据',
                                     `enable_graph` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                     `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '知识库描述',
                                     `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '租户ID',
                                     `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                     `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                     `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人名称',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                     `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最后修改人名称',
                                     `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识库';

-- ----------------------------
-- Table structure for ai_knowledge_chunk
-- ----------------------------
DROP TABLE IF EXISTS `ai_knowledge_chunk`;
CREATE TABLE `ai_knowledge_chunk` (
                                      `id` bigint NOT NULL COMMENT 'ID',
                                      `kb_id` bigint DEFAULT NULL COMMENT '所属知识库ID',
                                      `item_id` bigint DEFAULT NULL COMMENT '关联的知识条目ID',
                                      `content` longtext COMMENT '分片内容，用于embedding的文本',
                                      `vector_ref` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '向量ID',
                                      `chunk_idx` int DEFAULT '0',
                                      `token_count` int DEFAULT NULL COMMENT 'Token数量',
                                      `metadata` json DEFAULT NULL COMMENT '分片元数据',
                                      `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                      `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                      `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人名称',
                                      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                      `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                      `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最后修改人名称',
                                      `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识分片';

-- ----------------------------
-- Table structure for ai_knowledge_item
-- ----------------------------
DROP TABLE IF EXISTS `ai_knowledge_item`;
CREATE TABLE `ai_knowledge_item` (
                                     `id` bigint NOT NULL COMMENT 'ID',
                                     `kb_id` bigint DEFAULT NULL COMMENT '所属知识库ID',
                                     `type` tinyint NOT NULL COMMENT '类型: 1-File, 2-URL, 3-Text',
                                     `title` varchar(255) DEFAULT NULL COMMENT '标题（文档标题或FAQ的展示文本）',
                                     `content` longtext COMMENT '原始内容（用于分片与向量化）',
                                     `file_url` varchar(500) DEFAULT NULL,
                                     `file_size` bigint DEFAULT NULL COMMENT '文件大小，仅对文档类有效',
                                     `content_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '内容哈希，用于去重与变更检测',
                                     `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态: 0-待处理 1-处理中 2-成功 3-失败',
                                     `metadata` json DEFAULT NULL COMMENT '扩展元数据（JSON）',
                                     `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                     `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                     `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人名称',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                     `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最后修改人名称',
                                     `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识条目';

-- ----------------------------
-- Table structure for ai_mcp_server
-- ----------------------------
DROP TABLE IF EXISTS `ai_mcp_server`;
CREATE TABLE `ai_mcp_server` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                 `name` varchar(100) DEFAULT NULL COMMENT '服务名称',
                                 `command` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'STDIO命令',
                                 `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '服务地址',
                                 `env` json DEFAULT NULL,
                                 `args` text COMMENT '环境变量',
                                 `status` tinyint DEFAULT NULL COMMENT '状态',
                                 `type` varchar(10) DEFAULT NULL COMMENT '服务类型',
                                 `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                                 `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                 `create_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                 `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
                                 `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2004865314956963843 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Mcp配置';

-- ----------------------------
-- Table structure for ai_message
-- ----------------------------
DROP TABLE IF EXISTS `ai_message`;
CREATE TABLE `ai_message` (
                              `id` bigint NOT NULL COMMENT 'ID',
                              `conversation_id` bigint DEFAULT NULL COMMENT '会话ID，关联同一轮对话',
                              `parent_message_id` bigint DEFAULT NULL COMMENT '父消息ID，用于构建消息树（如用户提问 → AI回复）',
                              `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                              `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
                              `role` varchar(50) DEFAULT NULL COMMENT '消息角色：user / assistant / system',
                              `content` longtext COMMENT '最终展示内容',
                              `thinking` longtext COMMENT '思维链/CoT内容',
                              `trace_id` varchar(64) DEFAULT NULL COMMENT '全链路ID',
                              `meta_data` json DEFAULT NULL COMMENT '元数据',
                              `model_provider` varchar(100) DEFAULT NULL COMMENT '模型提供商，如 openai, deepseek, qwen',
                              `sequence_num` int DEFAULT NULL COMMENT '消息在会话中的顺序号，用于排序',
                              `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                              `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人名称',
                              `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                              `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最后修改人名称',
                              `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会话信息明细记录';

-- ----------------------------
-- Table structure for ai_model
-- ----------------------------
DROP TABLE IF EXISTS `ai_model`;
CREATE TABLE `ai_model` (
                            `id` bigint NOT NULL COMMENT 'ID',
                            `provider` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '提供商 (openai, deepseek)',
                            `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模型类型',
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模型名称',
                            `api_key` varchar(500) DEFAULT NULL COMMENT 'API密钥',
                            `base_url` varchar(500) DEFAULT NULL COMMENT '基础URL',
                            `status` bit(1) DEFAULT b'1' COMMENT '启用/停用',
                            `tenant_id` json DEFAULT NULL COMMENT '租户ID',
                            `variables` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '参数配置',
                            `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                            `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                            `create_name` varchar(255) DEFAULT NULL COMMENT '创建人名称',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                            `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
                            `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='多模态模型配置';

-- ----------------------------
-- Table structure for ai_vector_task
-- ----------------------------
DROP TABLE IF EXISTS `ai_vector_task`;
CREATE TABLE `ai_vector_task` (
                                  `id` bigint NOT NULL COMMENT 'ID',
                                  `task_id` varchar(255) DEFAULT NULL COMMENT '任务ID',
                                  `kb_id` bigint DEFAULT NULL COMMENT '知识库ID',
                                  `item_id` bigint DEFAULT NULL COMMENT '知识条目ID',
                                  `task_type` tinyint NOT NULL COMMENT '类型: 1-Build, 2-Embed',
                                  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态: 0-Pending 1-Running 2-Success 3-Failed',
                                  `progress` int DEFAULT '0',
                                  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                                  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
                                  `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                  `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人名称',
                                  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                  `last_modify_by` bigint DEFAULT NULL COMMENT '最后修改人ID',
                                  `last_modify_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最后修改人名称',
                                  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='向量化任务';

SET FOREIGN_KEY_CHECKS = 1;
