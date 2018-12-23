package com._520it.com.base.mapper;

import com._520it.com.base.domain.ProductSku;
import java.util.List;

public interface ProductSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSku record);

    ProductSku selectByPrimaryKey(Long id);

    List<ProductSku> selectAll();

    int updateByPrimaryKey(ProductSku record);

    List<ProductSku> getSkuByProductId(Long productId);
}