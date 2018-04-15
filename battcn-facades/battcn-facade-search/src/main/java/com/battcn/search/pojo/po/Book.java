package com.battcn.search.pojo.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

/**
 * book
 *
 * @author Levin
 * @since 2018/4/10 0010
 */
@Data
@Document(indexName = "book", type = "book", shards = 1, replicas = 0)
public class Book implements java.io.Serializable {

    private static final long serialVersionUID = -5067450789644236857L;

    @Id
    private String bookNo;
    private String bookType;
    @Field(searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String name;
    @Field(searchAnalyzer = "ik_word", analyzer = "ik_word")
    private String author;
    @Field(searchAnalyzer = "ik_word", analyzer = "ik_word")
    private String description;
    private String tags;
    private String cover;
    private Date createdTime;
    private Date lastModifiedTime;
    private Boolean status;
    private Boolean recommend;
}
