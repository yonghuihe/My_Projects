package com.xmg.minipss.service;

import java.util.List;

import com.xmg.minipss.domain.Product;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public interface IProductService {
	void save(Product o);

	void update(Product o);

	void delete(Product o);

	Product get(Long id);

	List<Product> list();

	PageResult query(QueryObject qo);
}
