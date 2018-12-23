package com.conpany.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.conpany.dao.IProductDao;
import com.conpany.dao.impl.ProductDaoImpl;
import com.conpany.domain.Product;
import com.conpany.domain.ProductDir;

public class ProductDaoImplTest {
	private IProductDao productDao;

	public ProductDaoImplTest() {
		productDao = new ProductDaoImpl();
	}

	@Test
	public void testSave() {
		Product product = new Product();
		product.setProductName("productName");
		product.setBrand("brand");
		product.setSupplier("supplier");
		product.setSalePrice(BigDecimal.valueOf(100));
		product.setCostPrice(BigDecimal.valueOf(150));
		product.setCutoff(0.3d);
		
		ProductDir dir = new ProductDir();
		dir.setId(2L);
		product.setDir(dir);
		productDao.save(product);
	}

	@Test
	public void testUpdate() {
		Product product = new Product();
		product.setId(23L);
		product.setProductName("productName1");
		product.setBrand("brand1");
		product.setSupplier("supplier1");
		product.setSalePrice(BigDecimal.valueOf(120));
		product.setCostPrice(BigDecimal.valueOf(130));
		product.setCutoff(0.31d);
		ProductDir dir = new ProductDir();
		dir.setId(21L);
		product.setDir(dir);
		productDao.update(product);
	}
	
	@Test
	public void testDelete() throws Exception {
		productDao.delete(23L);
	}
	
	@Test
	public void testGet() throws Exception {
		Product product = productDao.get(22L);
		System.out.println(product);
	}
	
	@Test
	public void testList() throws Exception {
		List<Product> products = productDao.list();
		for (Product product : products) {
			System.out.println(product);
		}
	}

}
