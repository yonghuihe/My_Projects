package com._520it.es.service;
import java.util.List;
import com._520it.es.domain.Product;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;

public interface IProductService {
	int deleteByPrimaryKey(Long id);
    int insert(Product record);
    Product selectByPrimaryKey(Long id);
    List<Product> selectAll();
    int updateByPrimaryKey(Product record);
	PageResult queryByConditionPage(QueryObject qo);
}
