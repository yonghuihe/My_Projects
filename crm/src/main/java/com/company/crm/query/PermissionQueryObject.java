package com.company.crm.query;

import org.springframework.stereotype.Component;

@Component
public class PermissionQueryObject extends QueryObject {
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "PermissionQueryObject [keyword=" + keyword + "]";
	}

}
