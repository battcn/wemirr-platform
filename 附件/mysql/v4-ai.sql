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

 Date: 11/12/2025 12:37:41
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ai_chat_agent
-- ----------------------------
DROP TABLE IF EXISTS `ai_chat_agent`;
CREATE TABLE `ai_chat_agent`
(
    `id`                bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `create_by`         bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`       varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`       datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time`  datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`    bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name`  varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`           tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `user_id`           bigint       DEFAULT NULL COMMENT 'userID',
    `name`              varchar(100) DEFAULT NULL COMMENT '智能体名称',
    `chat_model_id`     bigint       DEFAULT NULL COMMENT '绑定会话模型',
    `description`       varchar(255) DEFAULT NULL COMMENT '智能体描述',
    `ai_system_message` varchar(500) DEFAULT NULL COMMENT '智能体角色预设',
    `kb_id`             bigint       DEFAULT NULL COMMENT '关联知识库id',
    `tools`             varchar(500) DEFAULT NULL COMMENT '工具配置',
    `tenant_id`         bigint       DEFAULT NULL COMMENT '租户ID',
    `mcp_server_ids`    varchar(255) DEFAULT NULL COMMENT 'mcp服务器配置',
    `avatar`            varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1998378647072931863 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='智能体配置';

-- ----------------------------
-- Table structure for ai_chat_message_store
-- ----------------------------
DROP TABLE IF EXISTS `ai_chat_message_store`;
CREATE TABLE `ai_chat_message_store`
(
    `uid`     varchar(255) NOT NULL COMMENT '消息UID',
    `message` longtext COMMENT '消息内容',
    PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AI记忆消息持久化';

-- ----------------------------
-- Table structure for ai_conversation
-- ----------------------------
DROP TABLE IF EXISTS `ai_conversation`;
CREATE TABLE `ai_conversation`
(
    `id`                 bigint NOT NULL COMMENT 'ID',
    `create_by`          bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`        varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`        datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time`   datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`     bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name`   varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`            tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `title`              varchar(255) DEFAULT NULL COMMENT '会话名称',
    `uuid`               varchar(255) DEFAULT NULL COMMENT '对话uuid',
    `user_id`            bigint       DEFAULT NULL COMMENT '用户ID',
    `knowledge_base_ids` bigint       DEFAULT NULL COMMENT '关联的知识库ids',
    `agent_id`           bigint       DEFAULT NULL COMMENT '智能体ID',
    `type`               tinyint      DEFAULT NULL COMMENT '对话类型：1-普通对话 2-通用智能体对话 3-平台智能体 4-知识库对话 5-图片生成',
    `last_message`       text COMMENT '最后一条消息内容',
    `message_count`      int          DEFAULT NULL COMMENT '消息数量',
    `pinned`             tinyint(1) DEFAULT NULL COMMENT '是否置顶',
    `tenant_id`         bigint       DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会话信息';

-- ----------------------------
-- Table structure for ai_conversation_message
-- ----------------------------
DROP TABLE IF EXISTS `ai_conversation_message`;
CREATE TABLE `ai_conversation_message`
(
    `id`                  bigint NOT NULL COMMENT 'ID',
    `create_by`           bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`         varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`         datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time`    datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`      bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name`    varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`             tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `conversation_id`     bigint       DEFAULT NULL COMMENT '会话ID，关联同一轮对话',
    `parent_message_id`   bigint       DEFAULT NULL COMMENT '父消息ID，用于构建消息树（如用户提问 → AI回复）',
    `user_id`             bigint       DEFAULT NULL COMMENT '用户ID',
    `tenant_id`           bigint       DEFAULT NULL COMMENT '租户ID',
    `role`                varchar(50)  DEFAULT NULL COMMENT '消息角色：user / assistant / system',
    `raw_content`         longtext COMMENT '原始消息内容（用户输入或AI原始输出）',
    `prompt_content`      longtext COMMENT '处理后的输入（如拼接知识库、指令注入等），供LLM使用',
    `display_content`     longtext COMMENT '最终展示给用户的内容（经合规、脱敏、格式化处理）',
    `model_provider`      varchar(100) DEFAULT NULL COMMENT '模型提供商，如 openai, deepseek, qwen',
    `model_name`          varchar(100) DEFAULT NULL COMMENT '模型名称，如 gpt-4, deepseek-chat-r1',
    `prompt_tokens`       int          DEFAULT NULL COMMENT '输入Token数',
    `completion_tokens`   int          DEFAULT NULL COMMENT '输出Token数',
    `total_tokens`        int          DEFAULT NULL COMMENT '总消耗Token数',
    `response_latency_ms` bigint       DEFAULT NULL COMMENT '响应延迟（毫秒）',
    `thinking_content`    longtext COMMENT '思考内容（如Agent的Plan、CoT、Tool Call等）',
    `user_feedback`       int          DEFAULT NULL COMMENT '用户反馈：1-点赞 2-点踩 0-无反馈',
    `feedback_remark`     varchar(500) DEFAULT NULL COMMENT '反馈备注（用户填写的反馈原因）',
    `metadata`            json         DEFAULT NULL COMMENT '来源设备/IP/渠道，JSON格式，如 {"ip":"1.1.1.1","device":"mobile"}',
    `sequence_num`        int          DEFAULT NULL COMMENT '消息在会话中的顺序号，用于排序',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会话信息明细记录';

-- ----------------------------
-- Table structure for ai_kb_knowledge_base
-- ----------------------------
DROP TABLE IF EXISTS `ai_kb_knowledge_base`;
CREATE TABLE `ai_kb_knowledge_base`
(
    `id`                   bigint NOT NULL COMMENT 'ID',
    `create_by`            bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`          varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`          datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time`     datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`       bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name`     varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`              tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `name`                 varchar(255) DEFAULT NULL COMMENT '知识库名称',
    `description`          text COMMENT '知识库描述',
    `tenant_id`            varchar(255) DEFAULT NULL COMMENT '租户ID',
    `top_k`                int          DEFAULT NULL COMMENT '相似结果数量',
    `min_score` double DEFAULT NULL COMMENT '相似度分数阈值，仅返回分数高于此值的结果。[-1, 1]，一般 >0.5 表示有一定相关性',
    `ingest_max_overlap`   int          DEFAULT NULL COMMENT '文档切割时重叠数量(根据token计算)',
    `ingest_max_length`    int          DEFAULT NULL COMMENT '文档切割时最大长度(根据token计算)',
    `retrieve_max_results` int          DEFAULT NULL COMMENT '文档召回最大数量',
    `chat_model_id`        bigint       DEFAULT NULL COMMENT '聊天模型ID',
    `embedding_model_id`   bigint       DEFAULT NULL COMMENT '向量模型ID',
    `version`              int          DEFAULT NULL COMMENT '版本号',
    `metadata`             json         DEFAULT NULL COMMENT '元数据',
    `enable_graph`              tinyint(1) DEFAULT '1' COMMENT '是否启用图谱',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识库';
-- 知识库表添加字段
ALTER TABLE ai_kb_knowledge_base ADD COLUMN rerank_model_id BIGINT COMMENT '重排序模型ID';

-- ----------------------------
-- Table structure for ai_kb_knowledge_chunk
-- ----------------------------
DROP TABLE IF EXISTS `ai_kb_knowledge_chunk`;
CREATE TABLE `ai_kb_knowledge_chunk`
(
    `id`               bigint NOT NULL COMMENT 'ID',
    `create_by`        bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`      varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time` datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`   bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`          tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `kb_id`            bigint       DEFAULT NULL COMMENT '所属知识库ID',
    `item_id`          bigint       DEFAULT NULL COMMENT '关联的知识条目ID',
    `chunk_type`       varchar(50)  DEFAULT NULL COMMENT '分片类型（枚举）',
    `content`          longtext COMMENT '分片内容，用于embedding的文本',
    `content_hash`     varchar(255) DEFAULT NULL COMMENT '内容哈希值',
    `vector_ref`       varchar(255) DEFAULT NULL COMMENT '外部向量库引用（如 milvus:12345 / pgvector:67890）',
    `dimension`        int          DEFAULT NULL COMMENT '向量维度',
    `chunk_index`      int          DEFAULT NULL COMMENT '分片序号，对于文档分片，表示在原文中的顺序',
    `start_position`   int          DEFAULT NULL COMMENT '在原文中的起始位置',
    `end_position`     int          DEFAULT NULL COMMENT '在原文中的结束位置',
    `token_count`      int          DEFAULT NULL COMMENT 'Token数量',
    `metadata`         json         DEFAULT NULL COMMENT '分片元数据',
    `tenant_id`         bigint       DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识分片';

-- ----------------------------
-- Table structure for ai_kb_knowledge_item
-- ----------------------------
DROP TABLE IF EXISTS `ai_kb_knowledge_item`;
CREATE TABLE `ai_kb_knowledge_item`
(
    `id`               bigint NOT NULL COMMENT 'ID',
    `create_by`        bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`      varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time` datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`   bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`          tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `kb_id`            bigint       DEFAULT NULL COMMENT '所属知识库ID',
    `item_type`        varchar(50)  DEFAULT NULL COMMENT '知识条目类型（枚举）',
    `title`            varchar(255) DEFAULT NULL COMMENT '标题（文档标题或FAQ的展示文本）',
    `question`         text COMMENT '问题，仅当 type = QA_PAIR 有效',
    `answer`           text COMMENT '答案，仅当 type = QA_PAIR 有效',
    `content`          longtext COMMENT '原始内容（用于分片与向量化）',
    `content_type`     varchar(100) DEFAULT NULL COMMENT '内容类型（如 pdf、text、html），仅对文档类有效',
    `file_path`        varchar(500) DEFAULT NULL COMMENT '文件路径，仅对文档类有效',
    `file_size`        bigint       DEFAULT NULL COMMENT '文件大小，仅对文档类有效',
    `content_hash`     varchar(255) DEFAULT NULL COMMENT '内容哈希，用于去重与变更检测',
    `status`           varchar(50)  DEFAULT NULL COMMENT '处理状态（枚举）',
    `vectorized`       tinyint(1) DEFAULT NULL COMMENT '是否已向量化',
    `version`          int          DEFAULT NULL COMMENT '乐观锁版本',
    `metadata`         json         DEFAULT NULL COMMENT '扩展元数据（JSON）',
    `tenant_id`         bigint       DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='知识条目';

-- ----------------------------
-- Table structure for ai_kb_vector_metadata
-- ----------------------------
DROP TABLE IF EXISTS `ai_kb_vector_metadata`;
CREATE TABLE `ai_kb_vector_metadata`
(
    `id`               bigint NOT NULL COMMENT 'ID',
    `create_by`        bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`      varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time` datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`   bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`          tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `vector_id`        varchar(255) DEFAULT NULL COMMENT '向量ID（在向量数据库中的唯一标识）',
    `kb_id`            bigint       DEFAULT NULL COMMENT '所属知识库ID',
    `item_id`          bigint       DEFAULT NULL COMMENT '关联的知识条目ID',
    `chunk_id`         bigint       DEFAULT NULL COMMENT '关联的知识分片ID',
    `chunk_type`       varchar(50)  DEFAULT NULL COMMENT '分片类型',
    `dimension`        int          DEFAULT NULL COMMENT '向量维度',
    `store_type`       varchar(100) DEFAULT NULL COMMENT '向量存储类型（milvus、pgvector等）',
    `collection_name`  varchar(255) DEFAULT NULL COMMENT '向量数据库集合名称',
    `text_content`     longtext COMMENT '文本内容（用于检索时的显示）',
    `text_hash`        varchar(255) DEFAULT NULL COMMENT '文本哈希值',
    `similarity_score` double DEFAULT NULL COMMENT '相似度分数（用于缓存搜索结果）',
    `metadata`         json         DEFAULT NULL COMMENT '扩展元数据',
    `tenant_id`         bigint       DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='向量元数据';

-- ----------------------------
-- Table structure for ai_kb_vector_store
-- ----------------------------
DROP TABLE IF EXISTS `ai_kb_vector_store`;
CREATE TABLE `ai_kb_vector_store`
(
    `id`               bigint NOT NULL COMMENT 'ID',
    `create_by`        bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`      varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time` datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`   bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`          tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `kb_id`            varchar(255) DEFAULT NULL COMMENT '所属知识库ID',
    `text`             longtext COMMENT '文本内容',
    `vector`           longtext COMMENT '向量数据',
    `dimension`        int          DEFAULT NULL COMMENT '向量维度',
    `store_type`       varchar(100) DEFAULT NULL COMMENT '向量存储类型',
    `metadata`         json         DEFAULT NULL COMMENT '元数据',
    `tenant_id`         bigint       DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='向量存储';

-- ----------------------------
-- Table structure for ai_kb_vectorization_task
-- ----------------------------
DROP TABLE IF EXISTS `ai_kb_vectorization_task`;
CREATE TABLE `ai_kb_vectorization_task`
(
    `id`               bigint NOT NULL COMMENT 'ID',
    `create_by`        bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`      varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time` datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`   bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`          tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `task_id`          varchar(255) DEFAULT NULL COMMENT '任务ID',
    `kb_id`            bigint       DEFAULT NULL COMMENT '知识库ID',
    `item_id`          bigint       DEFAULT NULL COMMENT '知识条目ID',
    `task_type`        varchar(100) DEFAULT NULL COMMENT '任务类型 SINGLE: 单条文本向量化 BATCH: 批量文本向量化 DOCUMENT: 文档向量化 FAQ: FAQ向量化 STRUCTURED: 结构化数据向量化 KNOWLEDGE_ITEM: 知识条目向量化',
    `vectorized`       tinyint(1) DEFAULT NULL COMMENT '是否已向量化',
    `status`           varchar(50)  DEFAULT NULL COMMENT '任务状态',
    `progress`         int          DEFAULT NULL COMMENT '处理进度（百分比）',
    `vector_ids`       json         DEFAULT NULL COMMENT '结果向量ID列表（JSON格式）',
    `error_message`    text COMMENT '错误信息',
    `token_usage`      int          DEFAULT NULL COMMENT 'Token使用量',
    `tenant_id`         bigint       DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='向量化任务';

-- ----------------------------
-- Table structure for ai_mcp_server_config
-- ----------------------------
DROP TABLE IF EXISTS `ai_mcp_server_config`;
CREATE TABLE `ai_mcp_server_config`
(
    `id`               bigint NOT NULL COMMENT 'ID',
    `create_by`        bigint                                                         DEFAULT NULL COMMENT '创建人ID',
    `create_name`      varchar(255)                                                   DEFAULT NULL COMMENT '创建人名称',
    `create_time`      datetime                                                       DEFAULT NULL COMMENT '创建时间',
    `last_modify_time` datetime                                                       DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`   bigint                                                         DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name` varchar(255)                                                   DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`          tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `name`             varchar(100)                                                   DEFAULT NULL COMMENT '服务名称',
    `command`          varchar(100)                                                   DEFAULT NULL COMMENT 'STDIO命令',
    `url`              varchar(100)                                                   DEFAULT NULL COMMENT 'SSE URL',
    `env`              varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '环境变量(JSON)',
    `status`           tinyint                                                        DEFAULT NULL COMMENT '状态',
    `type`             varchar(10)                                                    DEFAULT NULL COMMENT '服务类型',
    `args`             varchar(255)                                                   DEFAULT NULL COMMENT '启动参数',
    `tenant_id`        bigint                                                         DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Mcp配置';

-- ----------------------------
-- Table structure for ai_model_config
-- ----------------------------
DROP TABLE IF EXISTS `ai_model_config`;
CREATE TABLE `ai_model_config`
(
    `id`               bigint NOT NULL COMMENT 'ID',
    `create_by`        bigint       DEFAULT NULL COMMENT '创建人ID',
    `create_name`      varchar(255) DEFAULT NULL COMMENT '创建人名称',
    `create_time`      datetime     DEFAULT NULL COMMENT '创建时间',
    `last_modify_time` datetime     DEFAULT NULL COMMENT '最后修改时间',
    `last_modify_by`   bigint       DEFAULT NULL COMMENT '最后修改人ID',
    `last_modify_name` varchar(255) DEFAULT NULL COMMENT '最后修改人名称',
    `deleted`          tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `provider`         varchar(100) DEFAULT NULL COMMENT '模型提供商',
    `model_type`       varchar(50)  DEFAULT NULL COMMENT '模型类型',
    `model_name`       varchar(255) DEFAULT NULL COMMENT '模型名称',
    `api_key`          varchar(500) DEFAULT NULL COMMENT 'API密钥',
    `base_url`         varchar(500) DEFAULT NULL COMMENT '基础URL',
    `variables`        json         DEFAULT NULL COMMENT '模型配置属性',
    `tenant_id`        bigint       DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='多模态模型配置';


-- 模型配置表添加重排序模型
INSERT INTO ai_model_config (provider, model_type, model_name, api_key, base_url)
VALUES ('JINA', 'RERANK', 'jina-reranker-v2-base-multilingual', 'your_jina_api_key', NULL);
SET
FOREIGN_KEY_CHECKS = 1;
