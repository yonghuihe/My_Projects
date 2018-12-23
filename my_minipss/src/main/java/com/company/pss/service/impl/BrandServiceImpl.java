package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.IBrandDao;
import com.company.pss.domain.Brand;
import com.company.pss.service.IBrandService;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class BrandServiceImpl implements IBrandService {

	private IBrandDao brandDao;

	public void setBrandDao(IBrandDao brandDao) {
		this.brandDao = brandDao;
	}

	public void save(Brand o) {
		this.brandDao.save(o);
	}

	public void update(Brand o) {
		this.brandDao.update(o);
	}

	public void delete(Brand o) {
		this.brandDao.delete(o);
	}

	public Brand get(Long id) {
		return this.brandDao.get(id);
	}

	public List<Brand> list() {
		return this.brandDao.list();
	}

	public PageResult query(QueryObject qo) {
		return this.brandDao.query(qo);
	}

}
