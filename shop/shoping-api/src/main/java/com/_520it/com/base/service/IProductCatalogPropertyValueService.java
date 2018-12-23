package com._520it.com.base.service;

import com._520it.com.base.domain.ProductCatalogPropertyValue;

import java.util.List;

/**
 * Created by dell on 2017/8/11.
 */
public interface IProductCatalogPropertyValueService {
    //获取商品属性
    List<ProductCatalogPropertyValue> getPropertyByProductId(Long productId);
}
