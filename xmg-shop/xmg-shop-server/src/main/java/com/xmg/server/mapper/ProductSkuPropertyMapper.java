package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.ProductSkuProperty;

public interface ProductSkuPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSkuProperty record);

    ProductSkuProperty selectByPrimaryKey(Long id);

    List<ProductSkuProperty> selectAll();

    int updateByPrimaryKey(ProductSkuProperty record);
}