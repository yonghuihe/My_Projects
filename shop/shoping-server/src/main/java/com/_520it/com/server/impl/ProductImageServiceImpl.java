package com._520it.com.server.impl;

import com._520it.com.base.domain.ProductImage;
import com._520it.com.base.mapper.ProductImageMapper;
import com._520it.com.base.service.IProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/8/11.
 */
@Service("productImageService")
public class ProductImageServiceImpl implements IProductImageService {
    @Autowired
    private ProductImageMapper productImageMapper;

    @Override
    public List<ProductImage> getImageByProductId(Long productId) {
        return productImageMapper.getImageByProductId(productId);
    }
}
