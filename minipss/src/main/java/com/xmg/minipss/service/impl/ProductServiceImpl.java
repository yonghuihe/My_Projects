package com.xmg.minipss.service.impl;

import java.util.List;

import com.xmg.minipss.dao.IProductDAO;
import com.xmg.minipss.domain.Product;
import com.xmg.minipss.service.IProductService;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public class ProductServiceImpl implements IProductService {

	private IProductDAO productDAO;

	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public void save(Product o) {
		this.productDAO.save(o);
	}

	@Override
	public void update(Product o) {
		this.productDAO.update(o);
	}

	@Override
	public void delete(Product o) {
		this.productDAO.delete(o);
	}

	@Override
	public Product get(Long id) {
		return this.productDAO.get(id);
	}

	@Override
	public List<Product> list() {
		return this.productDAO.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.productDAO.query(qo);
	}

}
