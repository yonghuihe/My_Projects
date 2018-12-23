package com._520it.es.service;
import java.util.List;
import com._520it.es.domain.OrderBillItem;
import com._520it.es.page.PageResult;
import com._520it.es.query.QueryObject;

public interface IOrderBillItemService {
	int deleteByPrimaryKey(Long id);
    int insert(OrderBillItem record);
    OrderBillItem selectByPrimaryKey(Long id);
    List<OrderBillItem> selectAll();
    int updateByPrimaryKey(OrderBillItem record);
	PageResult queryByConditionPage(QueryObject qo);
}
