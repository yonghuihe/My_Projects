package com.xmg.api.domain;

public class ProductDesc extends BaseDomain{

    private Long productId;

    private String details;

   

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}