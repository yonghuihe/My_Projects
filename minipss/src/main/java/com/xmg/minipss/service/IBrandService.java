package com.xmg.minipss.service;

import java.util.List;

import com.xmg.minipss.domain.Brand;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public interface IBrandService {
	void save(Brand o);

	void update(Brand o);

	void delete(Brand o);

	Brand get(Long id);

	List<Brand> list();

	PageResult query(QueryObject qo);
}
