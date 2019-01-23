package com.eCommerce.searchElastic624.controller;

import com.eCommerce.searchElastic624.dto.ProductDTO;
import com.eCommerce.searchElastic624.dto.ProductDTOList;
import com.eCommerce.searchElastic624.dto.ProductDTOWithPrice;
import com.eCommerce.searchElastic624.model.Product;
import com.eCommerce.searchElastic624.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.eCommerce.searchElastic624.constants.Constants;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @CrossOrigin("*")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product productCreated  = productService.add(product);
        return new ResponseEntity<String>(productCreated.getProductId(),HttpStatus.CREATED);
    }

//    @CrossOrigin("*")
//    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
//    public ProductDTO findOneProduct(@RequestParam String productId){
//        Optional<Product> product = productService.findOne(productId);
//        System.out.println(product);
//        ProductDTO productDTO = new ProductDTO();
//        BeanUtils.copyProperties(product, productDTO);
//        return productDTO;
//    }

    @CrossOrigin("*")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ProductDTO> findAllProduct(){
        Iterable<Product> productList = productService.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product _product : productList){
            ProductDTO _productDTO = new ProductDTO();
            BeanUtils.copyProperties(_product, _productDTO);
            productDTOList.add(_productDTO);
        }
        return productDTOList;
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteProduct(@RequestParam String productId){
        productService.delete(productId);
        return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product productCreated = productService.add(product);
        return new ResponseEntity<String>(productCreated.getProductId(), HttpStatus.CREATED);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Collection<ProductDTOWithPrice> query(@RequestParam String queryText){
        System.out.println(queryText);
        Collection<Product> result = productService.fuzzyQuery(queryText);
        Collection<ProductDTOWithPrice> productDTOS = new ArrayList<>();
        for(Product product: result){
            ProductDTOWithPrice productDTO = new ProductDTOWithPrice();
            BeanUtils.copyProperties(product, productDTO);
            productDTOS.add(productDTO);
        }
        return productDTOS;
        //List<ProductDTOWithPrice> updatedList = fetchDataFromMerchant(productDTOS);
        //return updatedList;
    }

    public List<ProductDTOWithPrice> fetchDataFromMerchant(Collection<ProductDTOWithPrice> productDTOS){

        final String uri = Constants.MERCHANT_MICROSERVICE_BASE_URL + "productMerchants/getByProductList/";

        //final String uri = "http://demo2494511.mockable.io/testing";

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity requestEntity=new HttpEntity(productDTOS, headers);

        ResponseEntity<?> entityResponse = restTemplate.exchange(uri, HttpMethod.POST,requestEntity,List.class);
        List prods = (List) entityResponse.getBody();
        Iterator iterator= prods.iterator();
        List<ProductDTOWithPrice> productDTOS1 = new ArrayList<>();
        while (iterator.hasNext()) {
            ProductDTOWithPrice productDTO = mapper.convertValue(iterator.next(), ProductDTOWithPrice.class);
            productDTOS1.add(productDTO);
        }
        for(ProductDTOWithPrice dto: productDTOS1){
            System.out.println(dto.getLowestPrice());
        }
        return productDTOS1;
    }
}