package com._520it.com.base.service;

import com._520it.com.base.domain.Product;
import com._520it.com.base.query.PageResult;
import com._520it.com.base.query.ProductQueryObject;

import java.util.List;
public interface IProductService {
    int deleteByPrimaryKey(Long id);
    int insert(Product record);
    Product selectByPrimaryKey(Long productId);
    List<Product> selectAll();
    int updateByPrimaryKey(Product record);
    PageResult query(ProductQueryObject qo);
}
