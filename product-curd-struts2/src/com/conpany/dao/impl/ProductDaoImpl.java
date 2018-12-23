package com.conpany.dao.impl;

import java.util.List;

import com.conpany.dao.IProductDao;
import com.conpany.domain.Product;
import com.conpany.handler.ProductResultSetHandler;
import com.conpany.template.JDBCTemplate;

public class ProductDaoImpl implements IProductDao {

	@Override
	public void save(Product product) {
		String sql = "insert into product (`productName`,`supplier`,`brand`,`salePrice`,`costPrice`,`cutoff`,`dir_id`) values (?,?,?,?,?,?,?)";
		Object[] params = {product.getProductName(),product.getSupplier(),product.getBrand(),product.getSalePrice(),product.getCostPrice(),product.getCutoff(),product.getDir().getId()};
		JDBCTemplate.update(sql, params);
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from product where `id` = ?";
		Object[] params = {id};
		JDBCTemplate.update(sql, params);
	}

	@Override
	public void update(Product product) {
		String sql = "update product set `productName` = ?,`supplier` = ?,`brand` = ?,`salePrice` = ?,`costPrice` =?,`cutoff`=?,`dir_id`=? where id = ?";
		Object[] params = {product.getProductName(),product.getSupplier(),product.getBrand(),product.getSalePrice(),product.getCostPrice(),product.getCutoff(),product.getDir().getId(),product.getId()};
		JDBCTemplate.update(sql, params);
	}

	@Override
	public Product get(Long id) {
		String sql = "select * from product where id = ?";
		Object[] params = {id};
		List<Product> products = JDBCTemplate.query(sql,new ProductResultSetHandler(), params);
		if (products.size() == 1){
			return products.get(0);
		}
		return null;
	}

	@Override
	public List<Product> list() {
		String sql = "select * from product";
		List<Product> query = JDBCTemplate.query(sql, new ProductResultSetHandler());
		return query;
	}

}
