package com.xmg.minipss.service.impl;

import java.util.List;

import com.xmg.minipss.dao.IBrandDAO;
import com.xmg.minipss.domain.Brand;
import com.xmg.minipss.service.IBrandService;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public class BrandServiceImpl implements IBrandService {

	private IBrandDAO brandDAO;

	public void setBrandDAO(IBrandDAO brandDAO) {
		this.brandDAO = brandDAO;
	}

	@Override
	public void save(Brand o) {
		this.brandDAO.save(o);
	}

	@Override
	public void update(Brand o) {
		this.brandDAO.update(o);
	}

	@Override
	public void delete(Brand o) {
		this.brandDAO.delete(o);
	}

	@Override
	public Brand get(Long id) {
		return this.brandDAO.get(id);
	}

	@Override
	public List<Brand> list() {
		return this.brandDAO.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.brandDAO.query(qo);
	}

}
