
-- 会话信息表
CREATE TABLE ai_conversation (
    id BIGINT PRIMARY KEY COMMENT 'ID',
    create_by BIGINT COMMENT '创建人ID',
    create_name VARCHAR(255) COMMENT '创建人名称',
    create_time DATETIME COMMENT '创建时间',
    last_modify_time DATETIME COMMENT '最后修改时间',
    last_modify_by BIGINT COMMENT '最后修改人ID',
    last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
    deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',
    
    title VARCHAR(255) COMMENT '会话名称',
    uuid VARCHAR(255) COMMENT '对话uuid',
    user_id BIGINT COMMENT '用户ID',
    knowledge_base_ids BIGINT COMMENT '关联的知识库ids',
    agent_id BIGINT COMMENT '智能体ID',
    type TINYINT COMMENT '对话类型：1-普通对话 2-通用智能体对话 3-平台智能体 4-知识库对话 5-图片生成',
    last_message TEXT COMMENT '最后一条消息内容',
    message_count INT COMMENT '消息数量',
    pinned BOOLEAN COMMENT '是否置顶'
) COMMENT '会话信息';

-- 会话信息明细记录表
CREATE TABLE ai_conversation_message (
    id BIGINT PRIMARY KEY COMMENT 'ID',
    create_by BIGINT COMMENT '创建人ID',
    create_name VARCHAR(255) COMMENT '创建人名称',
    create_time DATETIME COMMENT '创建时间',
    last_modify_time DATETIME COMMENT '最后修改时间',
    last_modify_by BIGINT COMMENT '最后修改人ID',
    last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
    deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',
    
    conversation_id BIGINT COMMENT '会话ID，关联同一轮对话',
    parent_message_id BIGINT COMMENT '父消息ID，用于构建消息树（如用户提问 → AI回复）',
    user_id BIGINT COMMENT '用户ID',
    tenant_id BIGINT COMMENT '租户ID',
    role VARCHAR(50) COMMENT '消息角色：user / assistant / system',
    raw_content LONGTEXT COMMENT '原始消息内容（用户输入或AI原始输出）',
    prompt_content LONGTEXT COMMENT '处理后的输入（如拼接知识库、指令注入等），供LLM使用',
    display_content LONGTEXT COMMENT '最终展示给用户的内容（经合规、脱敏、格式化处理）',
    model_provider VARCHAR(100) COMMENT '模型提供商，如 openai, deepseek, qwen',
    model_name VARCHAR(100) COMMENT '模型名称，如 gpt-4, deepseek-chat-r1',
    prompt_tokens INT COMMENT '输入Token数',
    completion_tokens INT COMMENT '输出Token数',
    total_tokens INT COMMENT '总消耗Token数',
    response_latency_ms BIGINT COMMENT '响应延迟（毫秒）',
    thinking_content LONGTEXT COMMENT '思考内容（如Agent的Plan、CoT、Tool Call等）',
    user_feedback INT COMMENT '用户反馈：1-点赞 2-点踩 0-无反馈',
    feedback_remark VARCHAR(500) COMMENT '反馈备注（用户填写的反馈原因）',
    metadata JSON COMMENT '来源设备/IP/渠道，JSON格式，如 {"ip":"1.1.1.1","device":"mobile"}',
    sequence_num INT COMMENT '消息在会话中的顺序号，用于排序'
) COMMENT '会话信息明细记录';

-- AI记忆消息持久化表
CREATE TABLE ai_chat_message_store (
    uid VARCHAR(255) PRIMARY KEY COMMENT '消息UID',
    message LONGTEXT COMMENT '消息内容'
) COMMENT 'AI记忆消息持久化';

-- 知识库表
CREATE TABLE ai_kb_knowledge_base (
    id BIGINT PRIMARY KEY COMMENT 'ID',
    create_by BIGINT COMMENT '创建人ID',
    create_name VARCHAR(255) COMMENT '创建人名称',
    create_time DATETIME COMMENT '创建时间',
    last_modify_time DATETIME COMMENT '最后修改时间',
    last_modify_by BIGINT COMMENT '最后修改人ID',
    last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
    deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',
    
    name VARCHAR(255) COMMENT '知识库名称',
    description TEXT COMMENT '知识库描述',
    tenant_id VARCHAR(255) COMMENT '租户ID',
    top_k INT COMMENT '相似结果数量',
    min_score DOUBLE COMMENT '相似度分数阈值，仅返回分数高于此值的结果。[-1, 1]，一般 >0.5 表示有一定相关性',
    ingest_max_overlap INT COMMENT '文档切割时重叠数量(根据token计算)',
    ingest_max_length INT COMMENT '文档切割时最大长度(根据token计算)',
    retrieve_max_results INT COMMENT '文档召回最大数量',
    chat_model_id BIGINT COMMENT '聊天模型ID',
    embedding_model_id BIGINT COMMENT '向量模型ID',
    version INT COMMENT '版本号',
    metadata JSON COMMENT '元数据'
) COMMENT '知识库';

-- 知识条目表
CREATE TABLE ai_kb_knowledge_item (
    id BIGINT PRIMARY KEY COMMENT 'ID',
    create_by BIGINT COMMENT '创建人ID',
    create_name VARCHAR(255) COMMENT '创建人名称',
    create_time DATETIME COMMENT '创建时间',
    last_modify_time DATETIME COMMENT '最后修改时间',
    last_modify_by BIGINT COMMENT '最后修改人ID',
    last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
    deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',
    
    kb_id BIGINT COMMENT '所属知识库ID',
    item_type VARCHAR(50) COMMENT '知识条目类型（枚举）',
    title VARCHAR(255) COMMENT '标题（文档标题或FAQ的展示文本）',
    question TEXT COMMENT '问题，仅当 type = QA_PAIR 有效',
    answer TEXT COMMENT '答案，仅当 type = QA_PAIR 有效',
    content LONGTEXT COMMENT '原始内容（用于分片与向量化）',
    content_type VARCHAR(100) COMMENT '内容类型（如 pdf、text、html），仅对文档类有效',
    file_path VARCHAR(500) COMMENT '文件路径，仅对文档类有效',
    file_size BIGINT COMMENT '文件大小，仅对文档类有效',
    content_hash VARCHAR(255) COMMENT '内容哈希，用于去重与变更检测',
    status VARCHAR(50) COMMENT '处理状态（枚举）',
    vectorized BOOLEAN COMMENT '是否已向量化',
    version INT COMMENT '乐观锁版本',
    metadata JSON COMMENT '扩展元数据（JSON）'
) COMMENT '知识条目';

-- 知识分片表
CREATE TABLE ai_kb_knowledge_chunk (
    id BIGINT PRIMARY KEY COMMENT 'ID',
    create_by BIGINT COMMENT '创建人ID',
    create_name VARCHAR(255) COMMENT '创建人名称',
    create_time DATETIME COMMENT '创建时间',
    last_modify_time DATETIME COMMENT '最后修改时间',
    last_modify_by BIGINT COMMENT '最后修改人ID',
    last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
    deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',
    
    kb_id BIGINT COMMENT '所属知识库ID',
    item_id BIGINT COMMENT '关联的知识条目ID',
    chunk_type VARCHAR(50) COMMENT '分片类型（枚举）',
    content LONGTEXT COMMENT '分片内容，用于embedding的文本',
    content_hash VARCHAR(255) COMMENT '内容哈希值',
    vector_ref VARCHAR(255) COMMENT '外部向量库引用（如 milvus:12345 / pgvector:67890）',
    dimension INT COMMENT '向量维度',
    chunk_index INT COMMENT '分片序号，对于文档分片，表示在原文中的顺序',
    start_position INT COMMENT '在原文中的起始位置',
    end_position INT COMMENT '在原文中的结束位置',
    token_count INT COMMENT 'Token数量',
    metadata JSON COMMENT '分片元数据'
) COMMENT '知识分片';

-- 向量元数据表
CREATE TABLE ai_kb_vector_metadata (
    id BIGINT PRIMARY KEY COMMENT 'ID',
    create_by BIGINT COMMENT '创建人ID',
    create_name VARCHAR(255) COMMENT '创建人名称',
    create_time DATETIME COMMENT '创建时间',
    last_modify_time DATETIME COMMENT '最后修改时间',
    last_modify_by BIGINT COMMENT '最后修改人ID',
    last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
    deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',
    
    vector_id VARCHAR(255) COMMENT '向量ID（在向量数据库中的唯一标识）',
    kb_id BIGINT COMMENT '所属知识库ID',
    item_id BIGINT COMMENT '关联的知识条目ID',
    chunk_id BIGINT COMMENT '关联的知识分片ID',
    chunk_type VARCHAR(50) COMMENT '分片类型',
    dimension INT COMMENT '向量维度',
    store_type VARCHAR(100) COMMENT '向量存储类型（milvus、pgvector等）',
    collection_name VARCHAR(255) COMMENT '向量数据库集合名称',
    text_content LONGTEXT COMMENT '文本内容（用于检索时的显示）',
    text_hash VARCHAR(255) COMMENT '文本哈希值',
    similarity_score DOUBLE COMMENT '相似度分数（用于缓存搜索结果）',
    metadata JSON COMMENT '扩展元数据'
) COMMENT '向量元数据';

-- 向量存储表
CREATE TABLE ai_kb_vector_store (
    id BIGINT PRIMARY KEY COMMENT 'ID',
    create_by BIGINT COMMENT '创建人ID',
    create_name VARCHAR(255) COMMENT '创建人名称',
    create_time DATETIME COMMENT '创建时间',
    last_modify_time DATETIME COMMENT '最后修改时间',
    last_modify_by BIGINT COMMENT '最后修改人ID',
    last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
    deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',
    
    kb_id VARCHAR(255) COMMENT '所属知识库ID',
    text LONGTEXT COMMENT '文本内容',
    vector LONGTEXT COMMENT '向量数据',
    dimension INT COMMENT '向量维度',
    store_type VARCHAR(100) COMMENT '向量存储类型',
    metadata JSON COMMENT '元数据'
) COMMENT '向量存储';

-- 向量化任务表
CREATE TABLE ai_kb_vectorization_task (
    id BIGINT PRIMARY KEY COMMENT 'ID',
    create_by BIGINT COMMENT '创建人ID',
    create_name VARCHAR(255) COMMENT '创建人名称',
    create_time DATETIME COMMENT '创建时间',
    last_modify_time DATETIME COMMENT '最后修改时间',
    last_modify_by BIGINT COMMENT '最后修改人ID',
    last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
    deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',
    
    task_id VARCHAR(255) COMMENT '任务ID',
    kb_id BIGINT COMMENT '知识库ID',
    item_id BIGINT COMMENT '知识条目ID',
    task_type VARCHAR(100) COMMENT '任务类型 SINGLE: 单条文本向量化 BATCH: 批量文本向量化 DOCUMENT: 文档向量化 FAQ: FAQ向量化 STRUCTURED: 结构化数据向量化 KNOWLEDGE_ITEM: 知识条目向量化',
    vectorized BOOLEAN COMMENT '是否已向量化',
    status VARCHAR(50) COMMENT '任务状态',
    progress INT COMMENT '处理进度（百分比）',
    vector_ids JSON COMMENT '结果向量ID列表（JSON格式）',
    error_message TEXT COMMENT '错误信息'
) COMMENT '向量化任务';

-- 多模态模型配置表
CREATE TABLE ai_model_config (
    id BIGINT PRIMARY KEY COMMENT 'ID',
    create_by BIGINT COMMENT '创建人ID',
    create_name VARCHAR(255) COMMENT '创建人名称',
    create_time DATETIME COMMENT '创建时间',
    last_modify_time DATETIME COMMENT '最后修改时间',
    last_modify_by BIGINT COMMENT '最后修改人ID',
    last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
    deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',
    
    provider VARCHAR(100) COMMENT '模型提供商',
    model_type VARCHAR(50) COMMENT '模型类型',
    model_name VARCHAR(255) COMMENT '模型名称',
    api_key VARCHAR(500) COMMENT 'API密钥',
    base_url VARCHAR(500) COMMENT '基础URL',
    variables JSON COMMENT '模型配置属性',
    tenant_id BIGINT COMMENT '租户ID'
) COMMENT '多模态模型配置';

-- 智能体配置
CREATE TABLE ai_chat_agent (
                                 id BIGINT PRIMARY KEY COMMENT 'ID',
                                 create_by BIGINT COMMENT '创建人ID',
                                 create_name VARCHAR(255) COMMENT '创建人名称',
                                 create_time DATETIME COMMENT '创建时间',
                                 last_modify_time DATETIME COMMENT '最后修改时间',
                                 last_modify_by BIGINT COMMENT '最后修改人ID',
                                 last_modify_name VARCHAR(255) COMMENT '最后修改人名称',
                                 deleted BOOLEAN DEFAULT FALSE COMMENT '逻辑删除',

                                 userId BIGINT  COMMENT 'userID',
                                 name VARCHAR(100) COMMENT '智能体名称',
                                 chat_model_id BIGINT COMMENT '绑定会话模型',
                                 description VARCHAR(255) COMMENT '智能体描述',
                                 ai_system_message VARCHAR(500) COMMENT '智能体角色预设',
                                 kb_id BIGINT  COMMENT '关联知识库id',
                                 tools  VARCHAR(500) COMMENT '工具配置',
                                 tenant_id BIGINT COMMENT '租户ID'
) COMMENT '智能体配置';
