package com.company.pss.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.company.pss.dao.IOrderBillDao;
import com.company.pss.domain.OrderBill;
import com.company.pss.domain.OrderBillItem;
import com.company.pss.domain.UserContext;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IOrderBillService;

public class OrderBillServiceImpl implements IOrderBillService {

	private IOrderBillDao orderBillDao;

	public void setOrderBillDao(IOrderBillDao orderBillDao) {
		this.orderBillDao = orderBillDao;
	}

	public void save(OrderBill bill) {
		//手动维护制单人、制单时间、修改审核状态为未审核
		bill.setInputUser(UserContext.getCurrent());
		bill.setInputTime(new Date());
		bill.setStatus(OrderBill.NOMAL);
		//给总金额、总数量设置初始值
		bill.setTotalAmount(BigDecimal.ZERO);
		bill.setTotalNumber(BigDecimal.ZERO);
		//遍历订单明细，建立订单明细和订单之间的关系、明细金额小计、手动维护订单总数量、订单总金额
		for(OrderBillItem item : bill.getOrderBillItem()){
			item.setOrderBill(bill);
			item.setAmount(item.getCostPrice().multiply(item.getNumber().setScale(2, RoundingMode.HALF_UP)));
			bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
			bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
		}
		this.orderBillDao.save(bill);
	}

	public void update(OrderBill bill) {
		bill.setStatus(OrderBill.NOMAL);
		//给总金额、总数量设置初始值
		bill.setTotalAmount(BigDecimal.ZERO);
		bill.setTotalNumber(BigDecimal.ZERO);
		//遍历订单明细，建立订单明细和订单之间的关系、明细金额小计、手动维护订单总数量、订单总金额
		for(OrderBillItem item : bill.getOrderBillItem()){
			item.setOrderBill(bill);
			item.setAmount(item.getCostPrice().multiply(item.getNumber().setScale(2, RoundingMode.HALF_UP)));
			bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
			bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
		}
		this.orderBillDao.update(bill);
	}

	public void delete(OrderBill o) {
		this.orderBillDao.delete(o);
	}

	public OrderBill get(Long id) {
		return this.orderBillDao.get(id);
	}

	public List<OrderBill> list() {
		return this.orderBillDao.list();
	}

	public PageResult query(QueryObject qo) {
		return this.orderBillDao.query(qo);
	}

	public void audit(OrderBill orderBill) {
		OrderBill bill = this.orderBillDao.get(orderBill.getId());
		//判断是否已审核，如果是未审核，修改审核状态，设置审核人，审核时间
		if(bill.getStatus() == OrderBill.NOMAL){
			bill.setStatus(OrderBill.AUDIT);
			bill.setAuditor(UserContext.getCurrent());
			bill.setAuditTime(new Date());
		}
	}

}
