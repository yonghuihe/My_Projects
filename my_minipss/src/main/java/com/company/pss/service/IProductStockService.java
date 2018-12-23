package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.ProductStock;
import com.company.pss.domain.StockIncomeBill;
import com.company.pss.domain.StockOutcomeBill;
import com.company.pss.domain.StockOutcomeBillItem;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IProductStockService {
	void save(ProductStock o);

	void update(ProductStock o);

	void delete(ProductStock o);

	ProductStock get(Long id);

	List<ProductStock> list();

	PageResult query(QueryObject qo);
	
	void auditStockIncomeBill(StockIncomeBill bill);
	
	ProductStock auditStockOutcomeBill(StockOutcomeBill bill, StockOutcomeBillItem item);
}
