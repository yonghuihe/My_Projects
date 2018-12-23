package com.xmg.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.api.domain.ProductSku;
import com.xmg.api.service.IProductSkuService;
import com.xmg.server.mapper.ProductSkuMapper;

@Service("productSkuService")
public class ProductSkuServiceImpl implements IProductSkuService{

	@Autowired
	ProductSkuMapper productSkuDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return productSkuDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProductSku record) {
		// TODO Auto-generated method stub
		return productSkuDao.insert(record);
	}

	@Override
	public ProductSku selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return productSkuDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductSku> selectAll() {
		// TODO Auto-generated method stub
		return productSkuDao.selectAll();
	}

	@Override
	public int updateByPrimaryKey(ProductSku record) {
		// TODO Auto-generated method stub
		return productSkuDao.updateByPrimaryKey(record);
	}

	@Override
	public List<ProductSku> getSkuByProductId(Long productId) {
		// TODO Auto-generated method stub
		return productSkuDao.getSkuByProductId(productId);
	}

}
