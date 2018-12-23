package com.conpany.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conpany.domain.ProductDir;

/**
 * 商品分类结果集处理器
 * @author yonghui
 *
 */
public class ProductDirResultSetHandler implements IResultSetHandler<List<ProductDir>> {

	@Override
	public List<ProductDir> handler(ResultSet rs) throws SQLException {
		List<ProductDir> list = new ArrayList<ProductDir>();
		while(rs.next()){
			ProductDir productDir = new ProductDir();
			productDir.setId(rs.getLong("id"));
			productDir.setDirName(rs.getString("dirName"));
			productDir.setParent_id(rs.getLong("parent_id"));
			list.add(productDir);
		}
		return list;
	}
}
