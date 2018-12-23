package com.xmg.api.service;

import com.xmg.api.domain.Product;
import com.xmg.api.qury.PageResult;
import com.xmg.api.qury.ProductQueryObject;
import com.xmg.api.vo.ProductVo;
import java.util.List;

public interface IProductService {

	int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);
    
    int queryCount(ProductQueryObject qo);
    
    PageResult query(ProductQueryObject qo);

    Product save(ProductVo vo);
}
