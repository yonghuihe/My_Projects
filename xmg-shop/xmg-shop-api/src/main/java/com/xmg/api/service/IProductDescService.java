package com.xmg.api.service;

import java.util.List;

import com.xmg.api.domain.ProductDesc;

public interface IProductDescService {

	int deleteByPrimaryKey(Long id);

    int insert(ProductDesc record);

    ProductDesc selectByPrimaryKey(Long id);

    List<ProductDesc> selectAll();

    int updateByPrimaryKey(ProductDesc record);

	ProductDesc getDescByProductId(Long productId);
}
