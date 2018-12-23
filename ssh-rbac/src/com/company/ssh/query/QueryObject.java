package com.company.ssh.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class QueryObject {
	private Integer currentPage = 1;
	private Integer pageSize = 10;

	/**
	 * 条件
	 */
	private List<String> conditions = new ArrayList<String>();
	/**
	 * 参数
	 */
	private List<Object> params = new ArrayList<Object>();
	private boolean isInit = false;

	public void addCondition(String condition, Object... params) {
		conditions.add(condition);
		this.params.addAll(Arrays.asList(params));
	}

	/**
	 * 返回封装好的WHERE语句
	 * 
	 * @return
	 */
	public String getQuery() {
		init();
		if (conditions.size() > 0) {
			StringBuilder where = new StringBuilder(100).append(" WHERE ")
					.append(StringUtils.join(conditions, " AND "));
			return where.toString();
		}
		return "";
	}

	/**
	 * 返回参数
	 * 
	 * @return
	 */
	public List<Object> getParams() {
		init();
		return params;
	}

	private void init() {
		if (!isInit) {
			customerQuery();
			isInit = true;
		}
	}

	protected void customerQuery() {

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
