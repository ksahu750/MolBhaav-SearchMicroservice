package com.eCommerce.searchElastic624;

import com.eCommerce.searchElastic624.dto.ProductDTO;
import com.eCommerce.searchElastic624.model.Product;
import com.eCommerce.searchElastic624.repository.ProductRepository;
import com.eCommerce.searchElastic624.service.ProductService;
import com.eCommerce.searchElastic624.service.impl.ProductServiceImpl;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class ServicesTest {

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Mock
    ProductRepository productRepository;

    @Mock
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test_add(){
        Product product = new Product();
        product.setProductId("TEST");

        Mockito.when(productServiceImpl.add(product)).thenReturn(product);

        Product product1 = productServiceImpl.add(product);

        Assert.assertEquals(product, product1);
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    public void test_findOne(){
        Product product = new Product();
        product.setProductId("TEST");
        Optional<Product> optionalProduct = Optional.ofNullable(product);

        Mockito.when(productServiceImpl.findOne("TEST")).thenReturn(optionalProduct);

        Optional<Product> product1 = productServiceImpl.findOne("TEST");

        Assert.assertEquals("TEST", product1.get().getProductId());
        Mockito.verify(productRepository, Mockito.times(1)).findById("TEST");
    }

    @Test
    public void test_findAll(){
        Product product = new Product();
        product.setProductId("TEST");

        Product product1 = new Product();
        product.setProductId("TEST2");

        Product product2 = new Product();
        product.setProductId("TEST3");

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);
        products.add(product2);

        Mockito.when(productServiceImpl.findAll()).thenReturn(products);

        List<Product> products1 = (List<Product>) productServiceImpl.findAll();

        Assert.assertEquals(3, products1.size());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void test_delete(){
        productServiceImpl.delete("TEST");
        Mockito.verify(productRepository, Mockito.times(1)).deleteById("TEST");
    }

    @Test
    public void test_update(){
        Product product = new Product();
        product.setProductId("TEST");

        Mockito.when(productServiceImpl.add(product)).thenReturn(product);

        Product product1 = productServiceImpl.update(product);

        Assert.assertEquals(product, product1);
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

}
