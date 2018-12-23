package com._520it.com.base.mapper;

import com._520it.com.base.domain.ProductDesc;

import java.util.List;

public interface ProductDescMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDesc record);

    ProductDesc selectByPrimaryKey(Long productId);

    List<ProductDesc> selectAll();

    int updateByPrimaryKey(ProductDesc record);
}