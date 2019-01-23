package com.eCommerce.searchElastic624.service.impl;

import com.eCommerce.searchElastic624.model.Product;
import com.eCommerce.searchElastic624.repository.ProductRepository;
import com.eCommerce.searchElastic624.service.ProductService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findOne(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Collection<Product> query(String queryString){
        QueryBuilder query = QueryBuilders.boolQuery()
//                .should(
//                        QueryBuilders.queryStringQuery(queryString)
//                        .lenient(true)
//                        .field("productName")
//                        .field("productUsp")
//                        .field("productDescription")
//                        .field("staticAttributeList.attributeDescription"))
                .should(
                        QueryBuilders.queryStringQuery("*" + queryString + "*")
                                .lenient(true)
                                .field("productName")
                                .field("productUsp")
                                .field("productDescription")
                                .field("staticAttributeList.attributeDescription")
                );

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        return elasticsearchTemplate.queryForList(build, Product.class);
    }

    @Override
    public Collection<Product> fuzzyQuery(String queryText){
        QueryBuilder query = QueryBuilders.multiMatchQuery("*"+queryText+"*")
                .lenient(true)
                .field("productName")
                .field("productUsp")
                .field("productDescription")
                .field("staticAttributeList.attributeDescription")
                .fuzziness(2);

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        return elasticsearchTemplate.queryForList(build, Product.class);
    }
}
