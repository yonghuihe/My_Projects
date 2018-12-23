package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.StockIncomeBillItem;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IStockIncomeBillItemService {
	void save(StockIncomeBillItem o);

	void update(StockIncomeBillItem o);

	void delete(StockIncomeBillItem o);

	StockIncomeBillItem get(Long id);

	List<StockIncomeBillItem> list();

	PageResult query(QueryObject qo);
}
