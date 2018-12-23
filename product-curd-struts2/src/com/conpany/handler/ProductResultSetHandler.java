package com.conpany.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conpany.domain.Product;
import com.conpany.domain.ProductDir;

/**
 * 结果集处理器
 * @author yonghui
 *
 */
public class ProductResultSetHandler implements IResultSetHandler<List<Product>> {

	@Override
	public List<Product> handler(ResultSet rs) throws SQLException {
		List<Product> list = new ArrayList<Product>();
		while (rs.next()){
			Product p = new Product();
			p.setId(rs.getLong("id"));
			p.setProductName(rs.getString("productName"));
			p.setBrand(rs.getString("brand"));
			p.setSupplier(rs.getString("supplier"));
			p.setSalePrice(rs.getBigDecimal("salePrice"));
			p.setCostPrice(rs.getBigDecimal("costPrice"));
			p.setCutoff(rs.getDouble("cutoff"));
			
			ProductDir dir = new ProductDir();
			dir.setId(rs.getLong("dir_id"));
			p.setDir(dir);
			list.add(p);
		}
		return list;
	}
}
