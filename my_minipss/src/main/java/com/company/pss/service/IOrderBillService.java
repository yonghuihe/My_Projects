package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.OrderBill;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IOrderBillService {
	void save(OrderBill o);

	void update(OrderBill o);

	void delete(OrderBill o);

	OrderBill get(Long id);

	List<OrderBill> list();

	PageResult query(QueryObject qo);

	void audit(OrderBill orderBill);
}
