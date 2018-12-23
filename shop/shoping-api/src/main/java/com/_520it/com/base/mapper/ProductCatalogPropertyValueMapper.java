package com._520it.com.base.mapper;

import com._520it.com.base.domain.ProductCatalogPropertyValue;
import java.util.List;

public interface ProductCatalogPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductCatalogPropertyValue record);

    ProductCatalogPropertyValue selectByPrimaryKey(Long id);

    List<ProductCatalogPropertyValue> selectAll();

    int updateByPrimaryKey(ProductCatalogPropertyValue record);

    List<ProductCatalogPropertyValue> getPropertyByProductId(Long productId);
}