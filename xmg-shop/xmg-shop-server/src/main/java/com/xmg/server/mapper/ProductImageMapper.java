package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.ProductImage;

public interface ProductImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductImage record);

    ProductImage selectByPrimaryKey(Long id);

    List<ProductImage> selectAll();

    int updateByPrimaryKey(ProductImage record);

	List<ProductImage> getImageByProductId(Long productId);
}