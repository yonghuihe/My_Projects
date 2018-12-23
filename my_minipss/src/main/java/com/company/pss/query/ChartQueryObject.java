package com.company.pss.query;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.company.pss.util.DateUtil;

public class ChartQueryObject extends QueryObject {

	private String groupType = "PRODUCT";
	private Date beginDate;
	private Date endDate;
	private String keywords;
	private Long supplierId = -1L;
	private Long brandId = -1L;

	@Override
	protected void customerQuery() {
		if (StringUtils.hasLength(keywords)) {
			this.addCondition("(item.product.name like ?)", "%" + keywords + "%");
		}
		if (beginDate != null) {
			this.addCondition("(item.orderBill.vdate >= ?)", DateUtil.beginDateTime(beginDate));
		}
		if (endDate != null) {
			this.addCondition("(item.orderBill.vdate <= ?)", DateUtil.endDateTime(endDate));
		}
		if (supplierId > -1) {
			this.addCondition("item.orderBill.supplier.id = ?", supplierId);
		}
		if (brandId > -1) {
			this.addCondition("item.product.brand.id = ?", brandId);
		}
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getGroupType() {
		return groupType;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

}
