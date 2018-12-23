package com.conpany.dao.impl;

import java.util.List;

import com.conpany.dao.IProductDirDao;
import com.conpany.domain.ProductDir;
import com.conpany.handler.ProductDirResultSetHandler;
import com.conpany.template.JDBCTemplate;

public class ProductDirDaoImpl implements IProductDirDao {

	@Override
	public void save(ProductDir productDir) {
		String sql = "insert into ProductDir (`dirName`,`parent_id`) values (?,?)";
		Object[] params = {productDir.getDirName(),productDir.getParent_id()};
		JDBCTemplate.update(sql, params);
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from ProductDir where `id` = ?";
		Object[] params = {id};
		JDBCTemplate.update(sql, params);
	}

	@Override
	public void update(ProductDir productDir) {
		String sql = "update ProductDir set `dirName` = ?,`parent_id`=? where id = ?";
		Object[] params = {productDir.getDirName(),productDir.getParent_id(),productDir.getId()};
		JDBCTemplate.update(sql, params);
	}

	@Override
	public ProductDir get(Long id) {
		String sql = "select * from ProductDir where id = ?";
		Object[] params = {id};
		List<ProductDir> productDirs = JDBCTemplate.query(sql,new ProductDirResultSetHandler(), params);
		if (productDirs.size() == 1){
			return productDirs.get(0);
		}
		return null;
	}

	@Override
	public List<ProductDir> list() {
		String sql = "select * from ProductDir";
		List<ProductDir> query = JDBCTemplate.query(sql, new ProductDirResultSetHandler());
		return query;
	}

}
