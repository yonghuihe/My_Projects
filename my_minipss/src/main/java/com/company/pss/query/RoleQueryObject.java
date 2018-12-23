package com.company.pss.query;

import org.springframework.util.StringUtils;

public class RoleQueryObject extends QueryObject {

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	protected void customerQuery() {
		if (StringUtils.hasLength(keyword)) {
			this.addCondition("(obj.name LIKE ? or obj.sn LIKE ?)", "%" + keyword + "%","%" + keyword + "%");
		}
	}
}
