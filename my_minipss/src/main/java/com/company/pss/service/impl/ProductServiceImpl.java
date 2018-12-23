package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.IProductDao;
import com.company.pss.domain.Product;
import com.company.pss.service.IProductService;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class ProductServiceImpl implements IProductService {

	private IProductDao productDao;

	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void save(Product o) {
		this.productDao.save(o);
	}

	@Override
	public void update(Product o) {
		this.productDao.update(o);
	}

	@Override
	public void delete(Product o) {
		this.productDao.delete(o);
	}

	@Override
	public Product get(Long id) {
		return this.productDao.get(id);
	}

	@Override
	public List<Product> list() {
		return this.productDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.productDao.query(qo);
	}

}
