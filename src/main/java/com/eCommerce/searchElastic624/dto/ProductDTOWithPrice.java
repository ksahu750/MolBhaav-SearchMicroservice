package com.eCommerce.searchElastic624.dto;

import com.eCommerce.searchElastic624.model.StaticAttribute;

import java.util.Collection;

public class ProductDTOWithPrice {
    private String productId;
    private String productName;
    private String categoryId;
    private String productImageUrl;
    private String productUsp;
    private String productDescription;
    private Collection<StaticAttribute> staticAttributeList;
    private int merchantCount;
    private double lowestPrice;
    private double highestPrice;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductUsp() {
        return productUsp;
    }

    public void setProductUsp(String productUsp) {
        this.productUsp = productUsp;
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
}
