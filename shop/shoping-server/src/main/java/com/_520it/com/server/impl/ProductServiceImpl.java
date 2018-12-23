package com._520it.com.server.impl;

import com._520it.com.base.domain.Product;
import com._520it.com.base.mapper.ProductMapper;
import com._520it.com.base.query.PageResult;
import com._520it.com.base.query.ProductQueryObject;
import com._520it.com.base.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResult query(ProductQueryObject qo) {
        Integer count = productMapper.queryForCount(qo);
        if (count == 0) {
            return PageResult.empty(qo.getPageSize());
        }
        List result = productMapper.queryForList(qo);
        return new PageResult(result,count,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Product record) {
        return 0;
    }

    @Override
    public Product selectByPrimaryKey(Long productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public List<Product> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        return 0;
    }
}
