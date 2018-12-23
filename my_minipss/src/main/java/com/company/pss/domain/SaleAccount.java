package com.company.pss.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SaleAccount {
	private Long id;
	/**
	 * 销售日期
	 */
	private Date vDate;
	/**
	 * 销售商品
	 */
	private Product product;
	/**
	 * 销售数量
	 */
	private BigDecimal number;
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;
	/**
	 * 销售总价格
	 */
	private BigDecimal totalSaleAmount;
	/**
	 * 成本价
	 */
	private BigDecimal costPrice;
	/**
	 * 总成本价
	 */
	private BigDecimal totalCostAmount;
	/**
	 * 客户
	 */
	private Client client;
	/**
	 * 销售员
	 */
	private Employee saleMan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getTotalSaleAmount() {
		return totalSaleAmount;
	}

	public void setTotalSaleAmount(BigDecimal totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getTotalCostAmount() {
		return totalCostAmount;
	}

	public void setTotalCostAmount(BigDecimal totalCostAmount) {
		this.totalCostAmount = totalCostAmount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employee getSaleMan() {
		return saleMan;
	}

	public void setSaleMan(Employee saleMan) {
		this.saleMan = saleMan;
	}

}
