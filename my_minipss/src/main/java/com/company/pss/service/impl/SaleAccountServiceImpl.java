package com.company.pss.service.impl;

import java.math.RoundingMode;
import java.util.List;

import com.company.pss.dao.ISaleAccountDao;
import com.company.pss.domain.ProductStock;
import com.company.pss.domain.SaleAccount;
import com.company.pss.domain.StockOutcomeBill;
import com.company.pss.domain.StockOutcomeBillItem;
import com.company.pss.service.ISaleAccountService;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public class SaleAccountServiceImpl implements ISaleAccountService {

	private ISaleAccountDao saleAccountDao;

	public void setSaleAccountDao(ISaleAccountDao saleAccountDao) {
		this.saleAccountDao = saleAccountDao;
	}

	@Override
	public void save(SaleAccount o) {
		this.saleAccountDao.save(o);
	}

	@Override
	public void update(SaleAccount o) {
		this.saleAccountDao.update(o);
	}

	@Override
	public void delete(SaleAccount o) {
		this.saleAccountDao.delete(o);
	}

	@Override
	public SaleAccount get(Long id) {
		return this.saleAccountDao.get(id);
	}

	@Override
	public List<SaleAccount> list() {
		return this.saleAccountDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.saleAccountDao.query(qo);
	}

	@Override
	public void addSaleAccount(StockOutcomeBill bill, StockOutcomeBillItem item, ProductStock productStock) {
		SaleAccount sa = new SaleAccount();
		sa.setvDate(bill.getVdate());// 销售帐时间--出库单审核时间
		sa.setClient(bill.getClient());// 对应出库单客户
		sa.setSaleMan(bill.getInputUser());// 对应录入人
		sa.setProduct(item.getProduct());// 对应明细中的商品
		sa.setNumber(item.getNumber());// 对应明细中的个数
		sa.setSalePrice(item.getSalePrice());// 对应明细中的销售价
		sa.setCostPrice(productStock.getStockPrice());// 对应库存成本
		sa.setTotalSaleAmount(sa.getSalePrice().multiply(sa.getNumber().setScale(2, RoundingMode.HALF_UP)));
		sa.setTotalCostAmount(sa.getCostPrice().multiply(sa.getNumber().setScale(2, RoundingMode.HALF_UP)));
		this.saleAccountDao.save(sa);
	}

}
