package com._520it.com.base.service;

import com._520it.com.base.domain.ProductDesc;

/**
 * Created by dell on 2017/8/10.
 */
public interface IProductDescService {
    ProductDesc getDescByProductId(Long productId);
}
