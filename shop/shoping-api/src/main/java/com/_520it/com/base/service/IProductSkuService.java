package com._520it.com.base.service;

import com._520it.com.base.domain.ProductSku;

import java.util.List;

/**
 * Created by dell on 2017/8/11.
 */
public interface IProductSkuService {
    //获取sku
    List<ProductSku> getSkuByProductId(Long productId);
}
