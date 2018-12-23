package com.company.pss.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采购入库单
 * 
 * @author yonghui
 *
 */
public class StockIncomeBill {
	
	/**
	 * 0：表示"未审核" 1：表示"已审核"
	 */
	public static int NOMAL = 0;
	public static int AUDIT = 1;
	
	private Long id;

	/**
	 * 订单审核的状态
	 */
	private int status;
	/**
	 * 订单编号
	 */
	private String sn;
	/**
	 * 业务时间
	 */
	private Date vdate;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 录入时间
	 */
	private Date inputTime;
	/**
	 * 总采购金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 总入库数量
	 */
	private BigDecimal totalNumber;
	/**
	 * 制单人
	 */
	private Employee inputUser;
	/**
	 * 审核人
	 */
	private Employee auditor;
	/**
	 * 仓库
	 */
	private Depot depot;
	/**
	 * 采购入库单明细
	 */
	private List<StockIncomeBillItem> stockIncomeBillItem = new ArrayList<StockIncomeBillItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(BigDecimal totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Employee getInputUser() {
		return inputUser;
	}

	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}

	public Employee getAuditor() {
		return auditor;
	}

	public void setAuditor(Employee auditor) {
		this.auditor = auditor;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public List<StockIncomeBillItem> getStockIncomeBillItem() {
		return stockIncomeBillItem;
	}

	public void setStockIncomeBillItem(List<StockIncomeBillItem> stockIncomeBillItem) {
		this.stockIncomeBillItem = stockIncomeBillItem;
	}

}
