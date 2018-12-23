package com._520it.com.server.impl;

import com._520it.com.base.domain.ProductDesc;
import com._520it.com.base.mapper.ProductDescMapper;
import com._520it.com.base.service.IProductDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2017/8/10.
 */
@Service("productDescService")
public class ProductDescServiceImpl implements IProductDescService {
    @Autowired
    private ProductDescMapper productDescMapper;

    public ProductDesc getDescByProductId(Long productId){
        return productDescMapper.selectByPrimaryKey(productId);
    }
}
