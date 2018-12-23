package com.xmg.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.api.domain.ProductImage;
import com.xmg.api.service.IProductImageService;
import com.xmg.server.mapper.ProductImageMapper;
@Service("productImageService")
public class ProductImageServiceImpl implements IProductImageService{

	@Autowired
	ProductImageMapper productImageDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return productImageDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProductImage record) {
		// TODO Auto-generated method stub
		return productImageDao.insert(record);
	}

	@Override
	public ProductImage selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return productImageDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductImage> selectAll() {
		// TODO Auto-generated method stub
		return productImageDao.selectAll();
	}

	@Override
	public int updateByPrimaryKey(ProductImage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductImage> getImageByProductId(Long productId) {
		// TODO Auto-generated method stub
		return productImageDao.getImageByProductId(productId);
	}

}
