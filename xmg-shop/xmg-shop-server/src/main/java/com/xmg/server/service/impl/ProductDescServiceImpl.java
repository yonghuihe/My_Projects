package com.xmg.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.api.domain.ProductDesc;
import com.xmg.api.service.IProductDescService;
import com.xmg.server.mapper.ProductDescMapper;

@Service("productDescService")
public class ProductDescServiceImpl implements IProductDescService{

	@Autowired
	ProductDescMapper productDescDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ProductDesc record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProductDesc selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return productDescDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductDesc> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(ProductDesc record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProductDesc getDescByProductId(Long id) {
		// TODO Auto-generated method stub
		return productDescDao.getDescByProductId(id);
	}

}
