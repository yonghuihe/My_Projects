package com.xmg.server.mapper;

import java.util.List;

import com.xmg.api.domain.Product;
import com.xmg.api.qury.ProductQueryObject;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);
    
    int queryCount(ProductQueryObject qo);
    
    List<Product> query(ProductQueryObject qo);

}