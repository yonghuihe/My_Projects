package com._520it.com.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Product extends BaseDomain {
    private Date createdDate = new Date();
    private Date lastModifiedDate = new Date();
    private Integer version = 0;
    private Catalog catalog;
    private Brand brand;
    private String name;
    private String keyword;
    private String code;
    private String image;
    private BigDecimal marketPrice;
    private BigDecimal basePrice;
    private Long mods;
    private String impressions;

    public String getCatalogName(){
        if(this.catalog == null){
            return "";
        }
        return this.catalog.getName();
    }
    public String getBrandName(){
        if(this.brand == null){
            return "";
        }
        return this.brand.getChineseName();
    }
}