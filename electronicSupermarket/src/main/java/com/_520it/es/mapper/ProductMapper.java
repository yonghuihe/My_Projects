package com._520it.es.mapper;

import com._520it.es.domain.Product;
import com._520it.es.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Product record);
    Product selectByPrimaryKey(Long id);
    List<Product> selectAll();
    int updateByPrimaryKey(Product record);
	Long queryByCondictionCount(QueryObject qo);
	List<Product> queryByConditionResult(QueryObject qo);
}