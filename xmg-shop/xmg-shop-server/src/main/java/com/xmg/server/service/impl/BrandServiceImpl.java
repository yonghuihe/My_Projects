package com.xmg.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.api.domain.Brand;
import com.xmg.api.service.IBrandService;
import com.xmg.server.mapper.BrandMapper;

@Service("brandService")
public class BrandServiceImpl implements IBrandService{

	@Autowired
	BrandMapper brandDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Brand record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Brand selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Brand> selectAll() {
		// TODO Auto-generated method stub
		return brandDao.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Brand record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
