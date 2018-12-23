package com.company.pss.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.company.pss.dao.IStockOutcomeBillDao;
import com.company.pss.domain.ProductStock;
import com.company.pss.domain.StockOutcomeBill;
import com.company.pss.domain.StockOutcomeBillItem;
import com.company.pss.domain.UserContext;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IProductStockService;
import com.company.pss.service.ISaleAccountService;
import com.company.pss.service.IStockOutcomeBillService;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {

	private IStockOutcomeBillDao stockOutcomeBillDao;
	private IProductStockService productStockService;
	private ISaleAccountService saleAccountService;

	public void setStockOutcomeBillDao(IStockOutcomeBillDao stockOutcomeBillDao) {
		this.stockOutcomeBillDao = stockOutcomeBillDao;
	}

	public void setProductStockService(IProductStockService productStockService) {
		this.productStockService = productStockService;
	}

	public void setSaleAccountService(ISaleAccountService saleAccountService) {
		this.saleAccountService = saleAccountService;
	}

	@Override
	public void save(StockOutcomeBill bill) {
		// 设置制单人、制单时间、修改状态为"未审核"，遍历明细，维护出库单和出库单明细之间的关系，计算明细小计，库存数量，总库存金额
		bill.setInputUser(UserContext.getCurrent());
		bill.setInputTime(new Date());
		bill.setStatus(StockOutcomeBill.NOMAL);
		bill.setTotalNumber(BigDecimal.ZERO);
		bill.setTotalAmount(BigDecimal.ZERO);
		for (StockOutcomeBillItem item : bill.getStockOutcomeBillItem()) {
			item.setStockOutcomeBill(bill);
			item.setAmount(item.getSalePrice().multiply(item.getNumber()));
			bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
			bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
		}
		this.stockOutcomeBillDao.save(bill);
	}

	@Override
	public void update(StockOutcomeBill bill) {
		bill.setStatus(StockOutcomeBill.NOMAL);
		bill.setTotalNumber(BigDecimal.ZERO);
		bill.setTotalAmount(BigDecimal.ZERO);
		for (StockOutcomeBillItem item : bill.getStockOutcomeBillItem()) {
			item.setStockOutcomeBill(bill);
			item.setAmount(item.getSalePrice().multiply(item.getNumber()));
			bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
			bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
		}
		this.stockOutcomeBillDao.update(bill);
	}

	@Override
	public void delete(StockOutcomeBill o) {
		this.stockOutcomeBillDao.delete(o);
	}

	@Override
	public StockOutcomeBill get(Long id) {
		return this.stockOutcomeBillDao.get(id);
	}

	@Override
	public List<StockOutcomeBill> list() {
		return this.stockOutcomeBillDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.stockOutcomeBillDao.query(qo);
	}

	@Override
	public void audit(StockOutcomeBill stockOutcomeBill) {
		// 获取出库单
		StockOutcomeBill bill = this.stockOutcomeBillDao.get(stockOutcomeBill.getId());
		if (bill.getStatus() == StockOutcomeBill.NOMAL) {
			bill.setAuditor(UserContext.getCurrent());
			bill.setAuditTime(new Date());
			bill.setStatus(StockOutcomeBill.AUDIT);
			ProductStock productStock;
			// 遍历明细
			for (StockOutcomeBillItem item : bill.getStockOutcomeBillItem()) {
				// 处理库存
				productStock = productStockService.auditStockOutcomeBill(bill, item);
				// 处理销售帐
				saleAccountService.addSaleAccount(bill, item, productStock);
			}
		}
	}

}
