package com.xmg.api.service;

import java.util.List;

import com.xmg.api.domain.Brand;


public interface IBrandService {
	 int deleteByPrimaryKey(Long id);

	    int insert(Brand record);

	    Brand selectByPrimaryKey(Long id);

	    List<Brand> selectAll();

	    int updateByPrimaryKey(Brand record);
}
