package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.ProductStock;
import com.company.pss.domain.SaleAccount;
import com.company.pss.domain.StockOutcomeBill;
import com.company.pss.domain.StockOutcomeBillItem;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface ISaleAccountService {
	void save(SaleAccount o);

	void update(SaleAccount o);

	void delete(SaleAccount o);

	SaleAccount get(Long id);

	List<SaleAccount> list();

	PageResult query(QueryObject qo);

	void addSaleAccount(StockOutcomeBill bill, StockOutcomeBillItem item, ProductStock productStock);

}
