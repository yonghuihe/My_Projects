package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.StockOutcomeBill;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IStockOutcomeBillService {
	void save(StockOutcomeBill o);

	void update(StockOutcomeBill o);

	void delete(StockOutcomeBill o);

	StockOutcomeBill get(Long id);

	List<StockOutcomeBill> list();

	PageResult query(QueryObject qo);

	void audit(StockOutcomeBill stockOutcomeBill);
}
