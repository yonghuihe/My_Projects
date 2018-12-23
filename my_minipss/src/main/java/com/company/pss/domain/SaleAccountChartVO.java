package com.company.pss.domain;

import java.math.BigDecimal;

public class SaleAccountChartVO {

	private Long id;
	/**
	 * 分组类型
	 */
	private String groupByName;
	/**
	 * 采购总数量
	 */
	private BigDecimal totalNumber;
	/**
	 * 采购总金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 毛利润
	 */
	private BigDecimal grossProfit;

	public SaleAccountChartVO() {
		super();
	}

	public SaleAccountChartVO(String groupByName, BigDecimal totalNumber, BigDecimal totalAmount,
			BigDecimal grossProfit) {
		super();
		this.groupByName = groupByName;
		this.totalNumber = totalNumber;
		this.totalAmount = totalAmount;
		this.grossProfit = grossProfit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupByName() {
		return groupByName;
	}

	public void setGroupByName(String groupByName) {
		this.groupByName = groupByName;
	}

	public BigDecimal getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(BigDecimal totalNumber) {
		this.totalNumber = totalNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

}
