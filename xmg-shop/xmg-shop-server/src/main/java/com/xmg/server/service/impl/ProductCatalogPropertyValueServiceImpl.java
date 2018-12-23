package com.xmg.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.api.domain.ProductCatalogPropertyValue;
import com.xmg.api.service.IProductCatalogPropertyValueService;
import com.xmg.server.mapper.ProductCatalogPropertyValueMapper;

@Service("productCatalogPropertyValueService")
public class ProductCatalogPropertyValueServiceImpl implements IProductCatalogPropertyValueService{

	@Autowired
	ProductCatalogPropertyValueMapper productCatalogPropertyValueDao;
	@Override
	public List<ProductCatalogPropertyValue> getPropertyByProductId(
			Long productId) {
		// TODO Auto-generated method stub
		return productCatalogPropertyValueDao.getPropertyByProductId(productId);
	}

}
