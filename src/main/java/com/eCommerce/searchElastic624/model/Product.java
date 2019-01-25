package com.eCommerce.searchElastic624.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Collection;

@Document(indexName = "productid", type = "product")
public class Product {
    @Id
    private String productId;
    private Category category;
    private String productName;
    private String productImageUrl;
    private String productUsp;
    private String productDescription;
    private Collection<StaticAttribute> staticAttributeList;
    private int merchantCount;
    private double lowestPrice;
    private double highestPrice;
    private String lowestBy;
    private String highestBy;

    public Product() {
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

    public Collection<StaticAttribute> getStaticAttributeList() {
        return staticAttributeList;
    }

    public void setStaticAttributeList(Collection<StaticAttribute> staticAttributeList) {
        this.staticAttributeList = staticAttributeList;
    }

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

    public int getMerchantCount() {
        return merchantCount;
    }

    public void setMerchantCount(int merchantCount) {
        this.merchantCount = merchantCount;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public String getLowestBy() {
        return lowestBy;
    }

    public void setLowestBy(String lowestBy) {
        this.lowestBy = lowestBy;
    }

    public String getHighestBy() {
        return highestBy;
    }

    public void setHighestBy(String highestBy) {
        this.highestBy = highestBy;
    }
}
