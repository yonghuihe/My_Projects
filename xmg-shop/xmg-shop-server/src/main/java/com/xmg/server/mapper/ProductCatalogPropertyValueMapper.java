package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.ProductCatalogPropertyValue;

public interface ProductCatalogPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductCatalogPropertyValue record);

    ProductCatalogPropertyValue selectByPrimaryKey(Long id);

    List<ProductCatalogPropertyValue> selectAll();

    int updateByPrimaryKey(ProductCatalogPropertyValue record);

	List<ProductCatalogPropertyValue> getPropertyByProductId(Long productId);
}