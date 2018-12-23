package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.IStockIncomeBillItemDao;
import com.company.pss.domain.StockIncomeBillItem;
import com.company.pss.service.IStockIncomeBillItemService;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class StockIncomeBillItemServiceImpl implements IStockIncomeBillItemService {

	private IStockIncomeBillItemDao stockIncomeBillItemDao;

	public void setStockIncomeBillItemDao(IStockIncomeBillItemDao stockIncomeBillItemDao) {
		this.stockIncomeBillItemDao = stockIncomeBillItemDao;
	}

	@Override
	public void save(StockIncomeBillItem o) {
		this.stockIncomeBillItemDao.save(o);
	}

	@Override
	public void update(StockIncomeBillItem o) {
		this.stockIncomeBillItemDao.update(o);
	}

	@Override
	public void delete(StockIncomeBillItem o) {
		this.stockIncomeBillItemDao.delete(o);
	}

	@Override
	public StockIncomeBillItem get(Long id) {
		return this.stockIncomeBillItemDao.get(id);
	}

	@Override
	public List<StockIncomeBillItem> list() {
		return this.stockIncomeBillItemDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.stockIncomeBillItemDao.query(qo);
	}

}
