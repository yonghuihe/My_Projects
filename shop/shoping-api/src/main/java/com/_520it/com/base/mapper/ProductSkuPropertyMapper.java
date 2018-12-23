package com._520it.com.base.mapper;

import com._520it.com.base.domain.ProductSkuProperty;

import java.util.List;

public interface ProductSkuPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSkuProperty record);

    ProductSkuProperty selectByPrimaryKey(Long id);

    List<ProductSkuProperty> selectAll();

    int updateByPrimaryKey(ProductSkuProperty record);

}