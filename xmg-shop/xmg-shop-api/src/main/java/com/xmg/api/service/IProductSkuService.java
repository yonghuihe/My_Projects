package com.xmg.api.service;

import java.util.List;

import com.xmg.api.domain.ProductSku;


public interface IProductSkuService {

	int deleteByPrimaryKey(Long id);

    int insert(ProductSku record);

    ProductSku selectByPrimaryKey(Long id);

    List<ProductSku> selectAll();

    int updateByPrimaryKey(ProductSku record);

	List<ProductSku> getSkuByProductId(Long productId);
}
