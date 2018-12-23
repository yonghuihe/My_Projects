package com.conpany.domain;

import java.math.BigDecimal;

public class Product {
	private Long id;
	private String productName;
	private String supplier;
	private String brand;
	private BigDecimal salePrice;
	private BigDecimal costPrice;
	private Double cutoff;
	private ProductDir dir;
	
	public ProductDir getDir() {
		return dir;
	}

	public void setDir(ProductDir dir) {
		this.dir = dir;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public Double getCutoff() {
		return cutoff;
	}

	public void setCutoff(Double cutoff) {
		this.cutoff = cutoff;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", supplier=" + supplier + ", brand=" + brand
				+ ", salePrice=" + salePrice + ", costPrice=" + costPrice + ", cutoff=" + cutoff + ", dir=" + dir + "]";
	}

}
