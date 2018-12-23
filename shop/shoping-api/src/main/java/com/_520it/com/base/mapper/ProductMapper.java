package com._520it.com.base.mapper;

import com._520it.com.base.domain.Product;
import com._520it.com.base.query.ProductQueryObject;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long productId);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    int queryForCount(ProductQueryObject qo);

    List<Product> queryForList(ProductQueryObject qo);
}