package com._520it.com.base.service;

import com._520it.com.base.domain.ProductImage;

import java.util.List;

/**
 * Created by dell on 2017/8/11.
 */
public interface IProductImageService {
    //获取图片相册
    List<ProductImage> getImageByProductId(Long productId);
}
