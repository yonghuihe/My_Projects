package com.company.pss.query;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

public class ProductStockQueryObject extends QueryObject {

	private String keywords;
	private Long depotId = -1L;
	private Long brandId = -1L;
	private BigDecimal stockLimit;

	@Override
	protected void customerQuery() {
		if (StringUtils.hasLength(keywords)) {
			this.addCondition("(obj.product.name like ?)", "%"+keywords+"%");
		}
		if (depotId > -1) {
			this.addCondition("obj.depot.id = ?", depotId);
		}
		if (brandId > -1) {
			this.addCondition("obj.product.brand.id = ?", brandId);
		}
		if (stockLimit != null ) {
			this.addCondition("obj.stockNumber <= ?", stockLimit);
		}
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Long getDepotId() {
		return depotId;
	}

	public void setDepotId(Long depotId) {
		this.depotId = depotId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public BigDecimal getStockLimit() {
		return stockLimit;
	}

	public void setStockLimit(BigDecimal stockLimit) {
		this.stockLimit = stockLimit;
	}
	
	
}
