package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.ProductSku;

public interface ProductSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSku record);

    ProductSku selectByPrimaryKey(Long id);

    List<ProductSku> selectAll();

    int updateByPrimaryKey(ProductSku record);

	List<ProductSku> getSkuByProductId(Long productId);
}