package com.wemirr.platform.ai.core.provider.graph;

import dev.langchain4j.community.data.document.transformer.graph.LLMGraphTransformer;
import dev.langchain4j.model.chat.ChatModel;

import java.util.Arrays;
import java.util.List;


/**
 * @author xJh
 * @since 2025/12/12
 * 后续优化成元组格式解析，json太过消耗token
 }
 **/
public class GraphRagTransformerFactory {


    public static final String[] GRAPH_ENTITY_EXTRACTION_ENTITY_TYPES = {
            "ORGANIZATION", "PERSON", "LOCATION", "EVENT", "DATE"
    };


    /**
     * Microsoft GraphRAG 的核心提取逻辑，适配为 JSON 输出格式
     * 改进：输出格式包含实体描述(entity_description)，用于后续向量化
     */
    private static final String GRAPH_RAG_INSTRUCTIONS = """
        -Goal-
        Given a text document that is potentially relevant to this activity and a list of entity types, identify all entities of those types from the text and all relationships among the identified entities.
         
        -Steps-
        1. Identify all entities. For each identified entity, extract the following information:
        - entity_name: Name of the entity, capitalized
        - entity_type: One of the allowed types provided in the context.
        - entity_description: Comprehensive description of the entity's attributes and activities. THIS IS CRITICAL FOR SEMANTIC SEARCH.
         
        2. From the entities identified in step 1, identify all pairs of (source_entity, target_entity) that are *clearly related* to each other.
        For each pair of related entities, extract the following information:
        - source_entity: name of the source entity, as identified in step 1
        - target_entity: name of the target entity, as identified in step 1
        - source_description: the description of the source entity from step 1
        - target_description: the description of the target entity from step 1
        - relationship_description: explanation as to why you think the source entity and the target entity are related to each other
        - relationship_strength: a numeric score indicating strength of the relationship between the source entity and target entity
         
        3. OUTPUT FORMAT (CRITICAL):
        You must return the output as a strict JSON list of objects. Do NOT use tuple format.
        Map the extracted information to the following JSON structure for each relationship:
        {
            "head": "source_entity name",
            "head_type": "source_entity type",
            "head_description": "source_entity description (IMPORTANT: include this for semantic search)",
            "relation": "The 'relationship_description' string",
            "relation_strength": "The 'relationship_strength' numeric score (Integer)",
            "tail": "target_entity name",
            "tail_type": "target_entity type",
            "tail_description": "target_entity description (IMPORTANT: include this for semantic search)"
        }
        
        4. CONSTRAINTS:
        - The 'head_type' and 'tail_type' MUST be strictly chosen from the allowed entity types provided.
        - The 'head_description' and 'tail_description' MUST be comprehensive and meaningful for semantic search.
        - Do not output any markdown or text explanations outside the JSON array.
        """;

    /**
     * 包含实体描述的 JSON 格式示例，用于向量化语义检索
     */
    private static final String GRAPH_RAG_JSON_EXAMPLES = """
        Example 1:
        Text:
        OpenAI announced on March 14, 2023 that GPT-4 has been released. CEO Sam Altman stated at the press conference in San Francisco that this is a major milestone for artificial intelligence. Microsoft, as a strategic partner, has integrated GPT-4 into its Bing search engine and Office suite.
        Output:
        [
            {
                "head": "SAM ALTMAN",
                "head_type": "PERSON",
                "head_description": "CEO of OpenAI, technology entrepreneur who announced the release of GPT-4 at the San Francisco press conference",
                "relation": "Sam Altman is the CEO of OpenAI and announced the release of GPT-4",
                "relation_strength": 9,
                "tail": "OPENAI",
                "tail_type": "ORGANIZATION",
                "tail_description": "Artificial intelligence research company that developed GPT-4, a major AI milestone released on March 14, 2023"
            },
            {
                "head": "MICROSOFT",
                "head_type": "ORGANIZATION",
                "head_description": "Technology corporation and strategic partner of OpenAI, integrated GPT-4 into Bing search engine and Office suite",
                "relation": "Microsoft is a strategic partner of OpenAI and integrated GPT-4 into its products",
                "relation_strength": 8,
                "tail": "OPENAI",
                "tail_type": "ORGANIZATION",
                "tail_description": "Artificial intelligence research company that developed GPT-4, partnered with Microsoft"
            }
        ]
        
        Example 2:
        Text:
        2023年9月，阿里巴巴集团在杭州云栖大会上发布了通义千问2.0大模型。阿里云智能集团CEO张勇表示，通义千问将全面接入阿里巴巴旗下所有产品。同时，阿里巴巴宣布开源通义千问70亿参数模型，供开发者免费使用。
        Output:
        [
            {
                "head": "张勇",
                "head_type": "PERSON",
                "head_description": "阿里云智能集团CEO，负责发布通义千问2.0大模型，宣布通义千问全面接入阿里巴巴产品",
                "relation": "张勇是阿里云智能集团的CEO，负责发布通义千问2.0",
                "relation_strength": 9,
                "tail": "阿里云智能集团",
                "tail_type": "ORGANIZATION",
                "tail_description": "阿里巴巴旗下云计算子公司，负责通义千问大模型的研发和发布"
            },
            {
                "head": "阿里巴巴集团",
                "head_type": "ORGANIZATION",
                "head_description": "中国互联网科技巨头，在2023年9月杭州云栖大会发布通义千问2.0，并开源70亿参数模型",
                "relation": "阿里巴巴集团在杭州云栖大会上发布了通义千问2.0大模型",
                "relation_strength": 8,
                "tail": "杭州",
                "tail_type": "LOCATION",
                "tail_description": "中国浙江省省会城市，2023年云栖大会举办地"
            }
        ]
        
        Example 3:
        Text:
        李明，著名企业家，于2025年1月1日在北京成立了"创新科技公司"。公司的主营业务是人工智能解决方案，并在成立当月获得了王芳女士的千万级天使投资。
        Output:
        [
            {
                "head": "李明",
                "head_type": "PERSON",
                "head_description": "著名企业家，创新科技公司创始人，于2025年1月1日在北京创办公司",
                "relation": "李明是创新科技公司的创始人，于2025年1月1日成立了该公司",
                "relation_strength": 8,
                "tail": "创新科技公司",
                "tail_type": "ORGANIZATION",
                "tail_description": "人工智能解决方案公司，2025年1月在北京成立，获得千万级天使投资"
            },
            {
                "head": "王芳",
                "head_type": "PERSON",
                "head_description": "天使投资人，为创新科技公司提供千万级天使投资",
                "relation": "王芳女士为创新科技公司提供了千万级的天使投资",
                "relation_strength": 9,
                "tail": "创新科技公司",
                "tail_type": "ORGANIZATION",
                "tail_description": "人工智能解决方案公司，成立当月即获得王芳女士千万级天使投资"
            },
            {
                "head": "创新科技公司",
                "head_type": "ORGANIZATION",
                "head_description": "人工智能解决方案公司，由著名企业家李明于2025年1月在北京创办",
                "relation": "创新科技公司的注册地和成立地点是北京",
                "relation_strength": 6,
                "tail": "北京",
                "tail_type": "LOCATION",
                "tail_description": "中国首都，创新科技公司注册和成立地点"
            }
        ]
        """;

    /**
     * 创建配置好的 GraphTransformer
     * @param chatModel LangChain4j ChatModel 实例
     * @return 配置好的 transformer
     */
    public static LLMGraphTransformer create(ChatModel chatModel) {

        List<String> allowedNodes = Arrays.asList(GRAPH_ENTITY_EXTRACTION_ENTITY_TYPES);

        return LLMGraphTransformer.builder()
                .model(chatModel)
                // 这会自动生成 "The 'head_type' and 'tail、_type' must be one of..." 的系统提示
                .allowedNodes(allowedNodes)
                // 将 GraphRAG 的逻辑步骤注入到 System/User prompt 中
                .additionalInstructions(GRAPH_RAG_INSTRUCTIONS)
                // 传入转换后的 JSON 格式示例
                .examples(GRAPH_RAG_JSON_EXAMPLES)
                // 建议设置重试，因为 JSON 格式偶尔可能出错
                .maxAttempts(2)
                .build();
    }
}