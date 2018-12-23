package com.company.pss.domain;

import java.math.BigDecimal;

public class ProductStock {

	private Long id;

	/**
	 * 仓库
	 */
	private Depot depot;
	/**
	 * 商品
	 */
	private Product product;
	/**
	 * 库存数量
	 */
	private BigDecimal stockNumber;
	/**
	 * 库存价格
	 */
	private BigDecimal stockPrice;
	/**
	 * 库存总价值
	 */
	private BigDecimal stockAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(BigDecimal stockNumber) {
		this.stockNumber = stockNumber;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public BigDecimal getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(BigDecimal stockAmount) {
		this.stockAmount = stockAmount;
	}

}
