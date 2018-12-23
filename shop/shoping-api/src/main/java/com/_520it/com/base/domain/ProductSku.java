package com._520it.com.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter@Getter
public class ProductSku extends BaseDomain{
    private Product product;
    private String code;
    private BigDecimal price;
    private Long mods;

    private List<ProductSkuProperty> productSkuPropertyList;

}