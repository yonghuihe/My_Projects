package com.conpany.dao;

import java.util.List;

import com.conpany.domain.ProductDir;

public interface IProductDirDao {
	void save(ProductDir productDir);

	void delete(Long id);

	void update(ProductDir productDir);

	ProductDir get(Long id);

	List<ProductDir> list();
}
