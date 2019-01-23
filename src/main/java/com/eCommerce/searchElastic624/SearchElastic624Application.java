package com.eCommerce.searchElastic624;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
public class SearchElastic624Application {

	public static void main(String[] args) {
		SpringApplication.run(SearchElastic624Application.class, args);
	}

}

