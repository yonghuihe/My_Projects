package com._520it.com.server.impl;

import com._520it.com.base.domain.ProductCatalogPropertyValue;
import com._520it.com.base.mapper.ProductCatalogPropertyValueMapper;
import com._520it.com.base.service.IProductCatalogPropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/8/11.
 */
@Service("productCatalogPropertyValueService")
public class ProductCatalogPropertyValueServiceImpl implements IProductCatalogPropertyValueService {

    @Autowired
    private ProductCatalogPropertyValueMapper productCatalogPropertyValueMapper;
    @Override
    public List<ProductCatalogPropertyValue> getPropertyByProductId(Long productId) {
        return productCatalogPropertyValueMapper.getPropertyByProductId(productId);
    }
}
