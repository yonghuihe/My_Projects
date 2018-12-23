package com.company.pss.query;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.company.pss.util.DateUtil;

public class SaleAccountChartQueryObject extends QueryObject {

	private String groupType = "PRODUCT";
	private Date beginDate;
	private Date endDate;
	private String keywords;
	private Long clientId = -1L;
	private Long brandId = -1L;

	@Override
	protected void customerQuery() {
		if (StringUtils.hasLength(keywords)) {
			this.addCondition("(sa.product.name like ?)", "%" + keywords + "%");
		}
		if (beginDate != null) {
			this.addCondition("(sa.vDate >= ?)", DateUtil.beginDateTime(beginDate));
		}
		if (endDate != null) {
			this.addCondition("(sa.vDate <= ?)", DateUtil.endDateTime(endDate));
		}
		if (clientId > -1) {
			this.addCondition("sa.client.id = ?", clientId);
		}
		if (brandId > -1) {
			this.addCondition("sa.product.brand.id = ?", brandId);
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

}
