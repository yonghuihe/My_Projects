package com.xmg.api.service;

import java.util.List;

import com.xmg.api.domain.ProductCatalogPropertyValue;

public interface IProductCatalogPropertyValueService {

	List<ProductCatalogPropertyValue> getPropertyByProductId(Long productId);

	
}
