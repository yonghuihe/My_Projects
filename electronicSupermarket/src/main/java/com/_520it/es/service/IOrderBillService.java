package com._520it.es.service;
import java.util.List;
import com._520it.es.domain.OrderBill;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;

public interface IOrderBillService {
	int deleteByPrimaryKey(Long id);
    int insert(OrderBill record);
    OrderBill selectByPrimaryKey(Long id);
    List<OrderBill> selectAll();
    int updateByPrimaryKey(OrderBill record);
	PageResult queryByConditionPage(QueryObject qo);
}
