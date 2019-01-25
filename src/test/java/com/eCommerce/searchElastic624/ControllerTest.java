package com.eCommerce.searchElastic624;

import com.eCommerce.searchElastic624.controller.ProductController;
import com.eCommerce.searchElastic624.dto.ProductDTO;
import com.eCommerce.searchElastic624.model.Product;
import com.eCommerce.searchElastic624.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerTest {
    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    @Test
    public void test_add(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId("A");
        ResponseEntity<String> res = new ResponseEntity<String>("A", HttpStatus.OK);

        Mockito.when(productController.addProduct(productDTO)).thenReturn(res);

        ResponseEntity<String> responseEntity = productController.addProduct(productDTO);

        Assert.assertEquals("A", responseEntity.getBody());
    }
}
