package com.conpany.test;

import java.util.List;

import org.junit.Test;

import com.conpany.dao.IProductDirDao;
import com.conpany.dao.impl.ProductDirDaoImpl;
import com.conpany.domain.ProductDir;

public class ProductDirDaoImplTest {
	private IProductDirDao productDirDao;
	
	public ProductDirDaoImplTest(){
		productDirDao = new ProductDirDaoImpl();
	}

	@Test
	public void testSave() {
		ProductDir productDir = new ProductDir();
		productDir.setDirName("dirName");
		productDir.setParent_id(12L);
		productDirDao.save(productDir);
	}

	@Test
	public void testDelete() {
		productDirDao.delete(70L);
	}

	@Test
	public void testUpdate() {
		ProductDir productDir = new ProductDir();
		productDir.setId(70L);
		productDir.setDirName("dirName");
		productDir.setParent_id(1L);
		productDirDao.update(productDir);
	}

	@Test
	public void testGet() {
		ProductDir productDir = productDirDao.get(70L);
		System.out.println(productDir);
	}

	@Test
	public void testList() {
		List<ProductDir> list = productDirDao.list();
		for(ProductDir productDir : list){
			System.out.println(productDir);
		}
	}

}
