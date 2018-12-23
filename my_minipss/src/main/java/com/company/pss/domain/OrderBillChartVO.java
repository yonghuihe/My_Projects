package com.company.pss.domain;

import java.math.BigDecimal;

public class OrderBillChartVO {
	private Long id;
	/**
	 * 分组类型
	 */
	private String groupByType;
	/**
	 * 总数量
	 */
	private BigDecimal totalNumber;
	/**
	 * 总金额
	 */
	private BigDecimal totalAmount;

	public OrderBillChartVO() {
		super();
	}

	public OrderBillChartVO(String groupByType, BigDecimal totalNumber, BigDecimal totalAmount) {
		super();
		this.groupByType = groupByType;
		this.totalNumber = totalNumber;
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupByType() {
		return groupByType;
	}

	public void setGroupByType(String groupByType) {
		this.groupByType = groupByType;
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

}
