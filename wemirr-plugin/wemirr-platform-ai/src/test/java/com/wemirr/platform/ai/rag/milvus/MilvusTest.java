package com.wemirr.platform.ai.rag.milvus;

import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.DataType;
import io.milvus.grpc.MutationResult;
import io.milvus.param.ConnectParam;
import io.milvus.param.R;
import io.milvus.param.RpcStatus;
import io.milvus.param.collection.CollectionSchemaParam;
import io.milvus.param.collection.CreateCollectionParam;
import io.milvus.param.collection.FieldType;
import io.milvus.param.dml.InsertParam;
import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.vector.request.SearchReq;
import io.milvus.v2.service.vector.request.data.FloatVec;
import io.milvus.v2.service.vector.response.SearchResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiao1
 * @since 2025-10
 */
@Slf4j
public class MilvusTest {

    @Test
    public void testCreateCollection() {
        // 假定milvusClient已在@BeforeEach方法中初始化并连接
        MilvusServiceClient milvusClient = getMilvusClient(); // 请实现获取客户端的方法

        String collectionName = "test_collection";

        // 1. 定义字段模式
        // 主键字段
        FieldType idField = FieldType.newBuilder()
                .withName("id")
                .withDataType(DataType.Int64)
                .withPrimaryKey(true)
                .withAutoID(true)
                .build();
        // 向量字段
        FieldType vectorField = FieldType.newBuilder()
                .withName("vector")
                .withDataType(DataType.FloatVector)
                .withDimension(128) // 指定向量维度
                .build();

        CollectionSchemaParam schema = CollectionSchemaParam.newBuilder()
                .addFieldType(idField)
                .addFieldType(vectorField)
                .build();

        CreateCollectionParam createParam = CreateCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .withSchema(schema)
                .build();

        R<RpcStatus> response = milvusClient.createCollection(createParam);

        assertEquals(R.Status.Success.getCode(), response.getStatus().intValue());
        assertNull(response.getException()); // 确保没有抛出异常

    }

    @Test
    public void testInsertVectorData() {
        MilvusServiceClient milvusClient = getMilvusClient(); // 获取已连接的客户端
        String collectionName = "test_collection";

        // 1. 准备要插入的数据
        // 生成一个随机的128维向量作为测试数据
        List<Float> randomVector = generateRandomVector(128);
        List<InsertParam.Field> fields = Arrays.asList(
                new InsertParam.Field("vector", Arrays.asList(randomVector)) // 因为AutoID为true，所以不需要提供"id"字段
        );

        // 2. 构建插入参数
        InsertParam insertParam = InsertParam.newBuilder()
                .withCollectionName(collectionName)
                .withFields(fields)
                .build();

        // 3. 执行插入操作
        R<MutationResult> response = milvusClient.insert(insertParam);

        // 4. 验证插入操作结果
        assertEquals(R.Status.Success.getCode(), response.getStatus().intValue());

        MutationResult resultWrapper = response.getData();
        assertNotNull(resultWrapper);
        // 验证系统确实为我们生成了ID
        log.info("Inserted ID: " + resultWrapper.getIDs());
    }

    @Test
    public void testVectorSearch() {
        MilvusClientV2 client = new MilvusClientV2(ConnectConfig.builder()
                .uri("http://localhost:19530")
                .token("root:Milvus")
                .build());

        FloatVec queryVector = new FloatVec(new float[]{0.3580376395471989f, -0.6023495712049978f, 0.18414012509913835f, -0.26286205330961354f, 0.9029438446296592f});
        SearchReq searchReq = SearchReq.builder()
                .collectionName("test_collection")
                .data(Collections.singletonList(queryVector))
                .topK(3)
                .build();

        SearchResp searchResp = client.search(searchReq);

        List<List<SearchResp.SearchResult>> searchResults = searchResp.getSearchResults();
        for (List<SearchResp.SearchResult> results : searchResults) {
            System.out.println("TopK results:");
            for (SearchResp.SearchResult result : results) {
                System.out.println(result);
            }
        }
    }




    private List<Float> generateRandomVector(int dimension) {
        // 生成一个随机向量用于测试。
        Float[] vector = new Float[dimension];
        for (int i = 0; i < dimension; i++) {
            vector[i] = (float) Math.random();
        }
        return Arrays.asList(vector);
    }


    private MilvusServiceClient getMilvusClient() {
        ConnectParam connectParam = ConnectParam.newBuilder()
                .withHost("localhost")
                .withPort(19530)
                .build();
        return new MilvusServiceClient(connectParam);
    }



}
