package com.company.pss.domain;

import java.math.BigDecimal;

/**
 * 出库单明细
 * 
 * @author yonghui
 *
 */
public class StockOutcomeBillItem {
	
	private Long id;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 售价
	 */
	private BigDecimal salePrice;
	/**
	 * 数量
	 */
	private BigDecimal number;
	/**
	 * 明细小计
	 */
	private BigDecimal amount;

	/**
	 * 商品对象
	 */
	private Product product;
	/**
	 * 订单对象
	 */
	private StockOutcomeBill stockOutcomeBill;

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

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
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

	public StockOutcomeBill getStockOutcomeBill() {
		return stockOutcomeBill;
	}

	public void setStockOutcomeBill(StockOutcomeBill stockOutcomeBill) {
		this.stockOutcomeBill = stockOutcomeBill;
	}

}
