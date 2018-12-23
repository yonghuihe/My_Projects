package com.company.crm.query;

import org.springframework.stereotype.Component;

@Component
public class EmployeeQueryObject extends QueryObject {
	private String keyword;
	private Long id;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EmployeeQueryObject [keyword=" + keyword + ", id=" + id + "]";
	}

}
