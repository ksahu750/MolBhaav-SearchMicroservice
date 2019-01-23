package com.eCommerce.searchElastic624.service;

import com.eCommerce.searchElastic624.model.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    Product add(Product product);
    Optional<Product> findOne(String productId);
    Iterable<Product> findAll();
    void delete(String productId);
    Product update(Product product);
    Collection<Product> query(String queryText);
    Collection<Product> fuzzyQuery(String queryText);
}
