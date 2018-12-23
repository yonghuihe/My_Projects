package com.conpany.dao;

import java.util.List;

import com.conpany.domain.Product;

public interface IProductDao {
	void save(Product product);
	
	void delete(Long id);
	
	void update(Product product);
	
	Product get(Long id);
	
	List<Product> list();
}
