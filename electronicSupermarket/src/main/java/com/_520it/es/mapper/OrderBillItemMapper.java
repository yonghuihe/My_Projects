package com._520it.es.mapper;

import com._520it.es.domain.OrderBillItem;
import com._520it.es.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderBillItemMapper {
    int deleteByPrimaryKey(Long id);
    int insert(OrderBillItem record);
    OrderBillItem selectByPrimaryKey(Long id);
    List<OrderBillItem> selectAll();
    int updateByPrimaryKey(OrderBillItem record);
	Long queryByCondictionCount(QueryObject qo);
	List<OrderBillItem> queryByConditionResult(QueryObject qo);
}