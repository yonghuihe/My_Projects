package com.company.pss.domain;

import java.math.BigDecimal;

/**
 * 订单明细
 * 
 * @author yonghui
 *
 */
public class OrderBillItem {
	
	private Long id;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 进价
	 */
	private BigDecimal costPrice;
	/**
	 * 数量
	 */
	private BigDecimal number;
	/**
	 * 金额
	 */
	private BigDecimal amount;

	/**
	 * 商品对象
	 */
	private Product product;
	/**
	 * 订单对象
	 */
	private OrderBill orderBill;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderBill getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(OrderBill orderBill) {
		this.orderBill = orderBill;
	}

}
