package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.StockIncomeBill;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IStockIncomeBillService {
	void save(StockIncomeBill o);

	void update(StockIncomeBill o);

	void delete(StockIncomeBill o);

	StockIncomeBill get(Long id);

	List<StockIncomeBill> list();

	PageResult query(QueryObject qo);

	void audit(StockIncomeBill stockIncomeBill);
}
