package com.eCommerce.searchElastic624.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;


import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Component
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.eCommerce.searchElastic624.repository")
@ComponentScan(basePackages = {"com.eCommerce.searchElastic624"})
public class EsConfig {
    @Value("${elasticsearch.home:/Users/kunalsahu/Downloads/elasticsearch-6.4.3}")
    private String elasticsearchHome;

    @Value("${elasticsearch.cluster.name:searchElastic}")
    private String clusterName;

    @Bean
    public Client client() throws UnknownHostException {
        Settings elasticsearchSettings = Settings.builder()
//        .put("client.transport.sniff", true)
        .put("path.home", elasticsearchHome)
        .put("cluster.name", clusterName)
        .build();
        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;
    }
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }
//    private static final String CLUSTER_NAME = "75022f914efa47b598d178e4e5e471dd";
//    private static final String USERNAME = "elastic";
//    private static final String PASSWORD = "wshnCONA6Z6xD2yyQ4cOkBA8";
//
//    private static final String CLUSTER_URL = CLUSTER_NAME + ".us-east-1.aws.found.io";
//    private static final String CREDENTIALS = USERNAME + ":" + PASSWORD;
//
//    @Bean
//    public TransportClient elasticsearchSecuredClient() throws Exception {
//        //Settings.Builder settings = Settings.builder()
//        Settings settings = Settings.builder()
//            .put("client.transport.nodes_sampler_interval", "5s")
//            .put("client.transport.sniff", false)
//            .put("transport.tcp.compress", true)
//            .put("cluster.name", CLUSTER_NAME)
//            .put("xpack.security.transport.ssl.enabled", true)
//            .put("request.headers.X-Found-Cluster", CLUSTER_NAME)
//            .put("xpack.security.user", CREDENTIALS)
//            .build();
//
//              return new PreBuiltXPackTransportClient(settings)
//            .addTransportAddress(new TransportAddress(new InetSocketAddress(CLUSTER_URL, 9343)));
//    }
}