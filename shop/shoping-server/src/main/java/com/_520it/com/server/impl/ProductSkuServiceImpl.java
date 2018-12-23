package com._520it.com.server.impl;

import com._520it.com.base.domain.ProductSku;
import com._520it.com.base.mapper.ProductSkuMapper;
import com._520it.com.base.service.IProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/8/11.
 */
@Service("productSkuService")
public class ProductSkuServiceImpl implements IProductSkuService {
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public List<ProductSku> getSkuByProductId(Long productId) {
        return productSkuMapper.getSkuByProductId(productId);
    }
}
