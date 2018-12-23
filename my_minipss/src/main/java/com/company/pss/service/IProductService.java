package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.Product;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IProductService {
	void save(Product o);

	void update(Product o);

	void delete(Product o);

	Product get(Long id);

	List<Product> list();

	PageResult query(QueryObject qo);
}
