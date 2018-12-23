package com._520it.com.base.mapper;

import com._520it.com.base.domain.ProductImage;
import java.util.List;

public interface ProductImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductImage record);

    ProductImage selectByPrimaryKey(Long id);

    List<ProductImage> selectAll();

    int updateByPrimaryKey(ProductImage record);

    List<ProductImage> getImageByProductId(Long productId);
}