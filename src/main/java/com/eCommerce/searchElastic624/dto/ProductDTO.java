package com.eCommerce.searchElastic624.dto;

import com.eCommerce.searchElastic624.model.Category;
import com.eCommerce.searchElastic624.model.StaticAttribute;

import java.util.List;

public class ProductDTO {

    private String productId;
    private String productName;
    private String productImageUrl;
    private String productDescription;
    private List<StaticAttribute> staticAttributeList;
    private Category category;
    private String productUsp;

    public String getProductUsp() {
        return productUsp;
    }

    public void setProductUsp(String productUsp) {
        this.productUsp = productUsp;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public List<StaticAttribute> getStaticAttributeList() {
        return staticAttributeList;
    }

    public void setStaticAttributeList(List<StaticAttribute> staticAttributeList) {
        this.staticAttributeList = staticAttributeList;
    }
}
