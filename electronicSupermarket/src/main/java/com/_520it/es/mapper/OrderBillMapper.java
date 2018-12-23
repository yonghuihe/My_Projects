package com._520it.es.mapper;

import com._520it.es.domain.OrderBill;
import com._520it.es.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderBillMapper {
    int deleteByPrimaryKey(Long id);
    int insert(OrderBill record);
    OrderBill selectByPrimaryKey(Long id);
    List<OrderBill> selectAll();
    int updateByPrimaryKey(OrderBill record);
	Long queryByCondictionCount(QueryObject qo);
	List<OrderBill> queryByConditionResult(QueryObject qo);
}