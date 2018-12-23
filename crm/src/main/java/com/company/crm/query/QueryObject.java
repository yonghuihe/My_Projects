package com.company.crm.query;

import org.springframework.stereotype.Component;

@Component
public class QueryObject {
	/*
	 * 当前页
	 */
	private Integer page;
	/**
	 * 每页有多少条数据
	 */
	private Integer rows;

	public Integer getStart() {
		return (page - 1) * rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

}
