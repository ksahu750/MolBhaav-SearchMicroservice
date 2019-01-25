package com.eCommerce.searchElastic624.controller;

import com.eCommerce.searchElastic624.dto.ProductDTO;
import com.eCommerce.searchElastic624.dto.ProductDTOForSearchUpdation;
import com.eCommerce.searchElastic624.model.Product;
import com.eCommerce.searchElastic624.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
        if(productDTO.getProductId() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product productCreated  = productService.add(product);
        return new ResponseEntity<String>(productCreated.getProductId(),HttpStatus.CREATED);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public ResponseEntity<Optional<Product>> findOne(@RequestParam String productId){
        if(productId == null)
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productService.findOne(productId), HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ProductDTO> findAllProduct(){
        Iterable<Product> productList = productService.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product _product : productList){
            ProductDTO _productDTOWithPrice = new ProductDTO();
            BeanUtils.copyProperties(_product, _productDTOWithPrice);
            productDTOList.add(_productDTOWithPrice);
        }
        return productDTOList;
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteProduct(@RequestParam String productId){
        if(productId == null)
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        productService.delete(productId);
        return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO){
        if(productDTO.getProductId() == null)
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product productCreated = productService.add(product);
        return new ResponseEntity<String>(productCreated.getProductId(), HttpStatus.CREATED);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<Collection<ProductDTO>> query(@RequestParam String queryText){
        if(queryText==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println(queryText);
        Collection<Product> result = productService.fuzzyQuery(queryText);
        if(result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Collection<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product: result){
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            productDTOS.add(productDTO);
        }
//        return productDTOS;
        //List<ProductDTOWithPrice> updatedList = fetchDataFromMerchant(productDTOS);
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/updateProductPricing", method = RequestMethod.POST)
    public ResponseEntity updateProductPricing(@RequestBody ProductDTOForSearchUpdation productDTOForSearchUpdation) {
        Optional<Product> optionalProduct = productService.findOne(productDTOForSearchUpdation.getProductId());
        Product product;
        if (!optionalProduct.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        product = optionalProduct.get();
        product.setHighestPrice(productDTOForSearchUpdation.getHighestPrice());
        product.setLowestPrice(productDTOForSearchUpdation.getLowestPrice());
        product.setMerchantCount(product.getMerchantCount()+productDTOForSearchUpdation.getOption());
        productService.update(product);
        return new ResponseEntity(HttpStatus.OK);
    }

//    public List<ProductDTOWithPrice> fetchDataFromMerchant(Collection<ProductDTOWithPrice> productDTOS){
//
//        final String uri = Constants.MERCHANT_MICROSERVICE_BASE_URL + "productMerchants/getCountByProductList/";
//
//        //final String uri = "http://demo2494511.mockable.io/testing";
//
//        RestTemplate restTemplate = new RestTemplate();
//        ObjectMapper mapper = new ObjectMapper();
//        HttpHeaders headers=new HttpHeaders();
//        headers.set("Content-Type", "application/json");
//        HttpEntity requestEntity=new HttpEntity(productDTOS, headers);
//
//        ResponseEntity<?> entityResponse = restTemplate.exchange(uri, HttpMethod.POST,requestEntity,List.class);
//
//        System.out.println(entityResponse.toString());
//        List prods = (List) entityResponse.getBody();
//        Iterator iterator= prods.iterator();
//        List<ProductDTOWithPrice> productDTOS1 = new ArrayList<>();
//        while (iterator.hasNext()) {
//            ProductDTOWithPrice productDTO = mapper.convertValue(iterator.next(), ProductDTOWithPrice.class);
//            productDTOS1.add(productDTO);
//        }
//        for(ProductDTOWithPrice dto: productDTOS1){
//            System.out.println(dto.getLowestPrice());
//        }
//        return productDTOS1;
//    }
}