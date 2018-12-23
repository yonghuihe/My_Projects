package com._520it.com.base.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductCatalogPropertyValue extends BaseDomain{
    private Long productId;
    private String name;
    private String value;
}