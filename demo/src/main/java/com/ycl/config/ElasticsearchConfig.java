//package com.ycl.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
//
///**
// * @author : YangChunLong
// * @date : Created in 2020/11/22 21:08
// * @description: es 配置
// * @modified By:
// * @version: :
// */
//@Configuration
//public class ElasticsearchConfig {
//    @Bean("elasticsearchTemplate")
//    public ElasticsearchRestTemplate elasticsearchTemplate (RestHighLevelClient restHighLevelClient
//            , ElasticsearchConverter elasticsearchConverter){
//        return new ElasticsearchRestTemplate(restHighLevelClient,elasticsearchConverter);
//    }
//}
