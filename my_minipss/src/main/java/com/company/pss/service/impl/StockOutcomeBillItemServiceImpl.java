package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.IStockOutcomeBillItemDao;
import com.company.pss.domain.StockOutcomeBillItem;
import com.company.pss.service.IStockOutcomeBillItemService;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class StockOutcomeBillItemServiceImpl implements IStockOutcomeBillItemService {

	private IStockOutcomeBillItemDao stockOutcomeBillItemDao;

	public void setStockOutcomeBillItemDao(IStockOutcomeBillItemDao stockOutcomeBillItemDao) {
		this.stockOutcomeBillItemDao = stockOutcomeBillItemDao;
	}

	@Override
	public void save(StockOutcomeBillItem o) {
		this.stockOutcomeBillItemDao.save(o);
	}

	@Override
	public void update(StockOutcomeBillItem o) {
		this.stockOutcomeBillItemDao.update(o);
	}

	@Override
	public void delete(StockOutcomeBillItem o) {
		this.stockOutcomeBillItemDao.delete(o);
	}

	@Override
	public StockOutcomeBillItem get(Long id) {
		return this.stockOutcomeBillItemDao.get(id);
	}

	@Override
	public List<StockOutcomeBillItem> list() {
		return this.stockOutcomeBillItemDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.stockOutcomeBillItemDao.query(qo);
	}

}
