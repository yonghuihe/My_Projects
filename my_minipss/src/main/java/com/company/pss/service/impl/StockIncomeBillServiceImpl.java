package com.company.pss.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.company.pss.dao.IStockIncomeBillDao;
import com.company.pss.domain.StockIncomeBill;
import com.company.pss.domain.StockIncomeBillItem;
import com.company.pss.domain.UserContext;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IProductStockService;
import com.company.pss.service.IStockIncomeBillService;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {

	private IStockIncomeBillDao stockIncomeBillDao;
	private IProductStockService productStockService;

	public void setStockIncomeBillDao(IStockIncomeBillDao stockIncomeBillDao) {
		this.stockIncomeBillDao = stockIncomeBillDao;
	}

	public void setProductStockService(IProductStockService productStockService) {
		this.productStockService = productStockService;
	}

	@Override
	public void save(StockIncomeBill bill) {
		// 手动维护制单人，制单时间，修改审核状态为"未审核"
		bill.setInputUser(UserContext.getCurrent());
		bill.setInputTime(new Date());
		bill.setStatus(StockIncomeBill.NOMAL);
		bill.setTotalNumber(BigDecimal.ZERO);
		bill.setTotalAmount(BigDecimal.ZERO);
		// 遍历集合，维护入库单和入库单明细之间的管理，计算明细金额小计，总入库数量，总入库金额
		for (StockIncomeBillItem item : bill.getStockIncomeBillItem()) {
			item.setStockIncomeBill(bill);
			item.setAmount(item.getNumber().multiply(item.getCostPrice().setScale(2, RoundingMode.HALF_UP)));
			bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
			bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
		}
		this.stockIncomeBillDao.save(bill);
	}

	@Override
	public void update(StockIncomeBill bill) {
		bill.setStatus(StockIncomeBill.NOMAL);
		bill.setTotalNumber(BigDecimal.ZERO);
		bill.setTotalAmount(BigDecimal.ZERO);
		// 遍历集合，维护入库单和入库单明细之间的管理，计算明细金额小计，总入库数量，总入库金额
		for (StockIncomeBillItem item : bill.getStockIncomeBillItem()) {
			item.setStockIncomeBill(bill);
			item.setAmount(item.getNumber().multiply(item.getCostPrice().setScale(2, RoundingMode.HALF_UP)));
			bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
			bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
		}
		this.stockIncomeBillDao.update(bill);
	}

	@Override
	public void delete(StockIncomeBill o) {
		this.stockIncomeBillDao.delete(o);
	}

	@Override
	public StockIncomeBill get(Long id) {
		return this.stockIncomeBillDao.get(id);
	}

	@Override
	public List<StockIncomeBill> list() {
		return this.stockIncomeBillDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.stockIncomeBillDao.query(qo);
	}

	@Override
	public void audit(StockIncomeBill stockIncomeBill) {
		// 获取入库单对象
		StockIncomeBill bill = this.stockIncomeBillDao.get(stockIncomeBill.getId());
		System.out.println(bill.getDepot());
		if (bill.getStatus() == StockIncomeBill.NOMAL) {
			// 设置审核状态、审核人、审核时间
			bill.setStatus(StockIncomeBill.AUDIT);
			bill.setAuditor(UserContext.getCurrent());
			bill.setAuditTime(new Date());
			productStockService.auditStockIncomeBill(bill);
		}
	}

}
