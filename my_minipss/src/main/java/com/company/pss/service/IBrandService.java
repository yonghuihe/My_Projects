package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.Brand;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IBrandService {
	void save(Brand o);

	void update(Brand o);

	void delete(Brand o);

	Brand get(Long id);

	List<Brand> list();

	PageResult query(QueryObject qo);
}
