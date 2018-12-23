package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.ProductDesc;

public interface ProductDescMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDesc record);

    ProductDesc selectByPrimaryKey(Long id);

    List<ProductDesc> selectAll();

    int updateByPrimaryKey(ProductDesc record);

	ProductDesc getDescByProductId(Long id);
}