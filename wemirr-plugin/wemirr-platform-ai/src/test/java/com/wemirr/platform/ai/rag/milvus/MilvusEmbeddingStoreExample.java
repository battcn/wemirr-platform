package com.wemirr.platform.ai.rag.milvus;

import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
import io.milvus.client.MilvusServiceClient;
import io.milvus.common.clientenum.ConsistencyLevelEnum;
import io.milvus.param.ConnectParam;
import io.milvus.param.IndexType;
import io.milvus.param.MetricType;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiao1
 * @since 2025-10
 */
public class MilvusEmbeddingStoreExample {

    public static void main(String[] args) throws InterruptedException {

            QwenEmbeddingModel embeddingModel = QwenEmbeddingModel.builder()
                    .apiKey("")
//                .modelName("qwen-max")
                    .build();

            MilvusServiceClient customMilvusClient = new MilvusServiceClient(
                    ConnectParam.newBuilder()
                            .withHost("localhost")
                            .withPort(19530)
                            .build()
            );
            MilvusEmbeddingStore embeddingStore = MilvusEmbeddingStore.builder()
                    .milvusClient(customMilvusClient)
                    .collectionName("example_collection")
                    // 设置向量的维度（即每个嵌入向量的长度）。必须与你使用的嵌入模型输出的维度完全一致。
                    .dimension(1536)
                    // 设置向量索引类型。索引用于加速向量相似性搜索。
                    // 常见类型：
                    // - FLAT: 精确搜索，无损但速度慢，适合小数据量（< 10万）测试使用。
                    // - IVF_FLAT / IVF_SQ8: 基于聚类的近似搜索，速度快，适合中等数据量。
                    // - HNSW: 高性能图索引，适合大数据量和高并发查询。
                    // 注意：创建索引后才能进行高效搜索，插入数据前无需手动建索引。
                    .indexType(IndexType.FLAT)
                    // 设置向量相似度度量方式。决定两个向量“有多像”。
                    // 常见类型：
                    // - MetricType.COSINE: 余弦相似度，推荐用于文本嵌入，值范围 [-1, 1]，越接近 1 越相似。
                    // - MetricType.L2: 欧几里得距离，值越小越相似。
                    // - MetricType.IP: 内积（点积），与余弦相似度类似，常用于归一化向量。
                    // 注意：必须在创建集合时指定，后续不能更改。
                    .metricType(MetricType.COSINE)
                    // 设置数据一致性级别，控制读操作能看到多少写入的数据。
                    // 常见级别：
                    // - STRONG: 强一致性，读取总是返回最新数据，但延迟较高。
                    // - EVENTUALLY: 最终一致性，写入后可能短暂延迟才能查到，但性能最好。
                    // - SESSION: 单一会话内一致性，适合用户会话场景。
                    // 推荐测试用 EVENTUALLY，生产根据需求调整。
                    .consistencyLevel(ConsistencyLevelEnum.EVENTUALLY)
                    // 是否在每次插入数据后自动刷新（flush），使数据立即可被搜索。
                    // - true: 插入后立即可查，适合小规模或演示场景。
                    // - false: 需手动调用 flush() 或等待 Milvus 自动刷新，性能更高。
                    // 注意：Milvus 默认不是实时可见，autoFlushOnInsert=true 可简化开发。
                    .autoFlushOnInsert(true)
                    // 指定向量集合中用于存储唯一 ID 的字段名。
                    // Milvus 会为每条向量数据生成一个主键（如 id），用于精确查找或删除。
                    // 该字段类型通常为 VARCHAR 或 INT64。
                    .idFieldName("id")
                    // 指定向量集合中用于存储原始文本内容的字段名。
                    // 当你插入 TextSegment 时，其文本内容会存入此字段，便于搜索后返回原文。
                    // 字段类型通常为 VARCHAR。
                    .textFieldName("text")
                    // 指定向量集合中用于存储元数据（metadata）的字段名。
                    // 元数据可以是 JSON 字符串，如 {"source": "doc1.pdf", "page": 10}，
                    // 支持后续基于元数据过滤搜索结果。
                    // 字段类型通常为 VARCHAR。// Text field name
                    .metadataFieldName("metadata")
                    // 指定向量集合中用于存储向量数据的字段名。
                    // 该字段存储的是 float 数组（即 embedding.vector()），是相似性搜索的核心。
                    // 字段类型为 FLOAT_VECTOR。
                    .vectorFieldName("vector")
                    .build();

            TextSegment segment1 = TextSegment.from("I like football.");
            Embedding embedding1 = embeddingModel.embed(segment1).content();
            embeddingStore.add(embedding1, segment1);

            TimeUnit.SECONDS.sleep(60);

            TextSegment segment2 = TextSegment.from("The weather is good today.");
            Embedding embedding2 = embeddingModel.embed(segment2).content();
            embeddingStore.add(embedding2, segment2);

            TimeUnit.SECONDS.sleep(60);

            String userQuery = "What is your favourite sport?";
            Embedding queryEmbedding = embeddingModel.embed(userQuery).content();

            EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                    .queryEmbedding(queryEmbedding)
                    .maxResults(2)  // top 2
                    // .minScore(0.5) // 可选：设置最小相似度阈值
                    .build();

            //------ 搜索 ---------- 等价于ContentRetriever
            EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
            //  获取结果列表
            List<EmbeddingMatch<TextSegment>> matches = searchResult.matches();
            // 输出结果
            System.out.println("\n 搜索结果:");
            for (int i = 0; i < matches.size(); i++) {
                    EmbeddingMatch<TextSegment> match = matches.get(i);
                    TextSegment textSegment = match.embedded();
                    double score = match.score(); // Cosine Similarity

                    System.out.printf("  %d. 文本: \"%s\" | 相似度得分: %.4f\n", i + 1, textSegment.text(), score);
            }


    }
}
