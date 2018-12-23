package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.StockOutcomeBillItem;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IStockOutcomeBillItemService {
	void save(StockOutcomeBillItem o);

	void update(StockOutcomeBillItem o);

	void delete(StockOutcomeBillItem o);

	StockOutcomeBillItem get(Long id);

	List<StockOutcomeBillItem> list();

	PageResult query(QueryObject qo);
}
