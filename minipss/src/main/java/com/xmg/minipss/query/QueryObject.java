package com.xmg.minipss.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class QueryObject {

	private Integer currentPage = 1;
	private Integer pageSize = 10;
	private List<String> conditions = new ArrayList<String>();
	private List<Object> params = new ArrayList<Object>();
	private boolean isInit = false;

	public void customerQuery() {

	}

	private void init() {
		if (!isInit) {
			customerQuery();
			isInit = true;
		}
	}

	public void addCondition(String condition, Object... params) {
		conditions.add(condition);
		this.params.addAll(Arrays.asList(params));
	}

	//返回拼装好的WHERE语句
	public String getQuery() {
		init();
		if (conditions.size() > 0) {
			StringBuilder where = new StringBuilder(100).append(" WHERE ")
					.append(StringUtils.join(conditions, " AND "));
			return where.toString();
		}
		return "";
	}

	public List<Object> getParams() {
		init();
		return this.params;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
