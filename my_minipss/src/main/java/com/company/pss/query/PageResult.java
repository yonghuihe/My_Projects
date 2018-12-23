package com.company.pss.query;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
	/**
	 * 当前页
	 */
	private Integer currentPage;
	/**
	 * 页面容量
	 */
	private Integer pageSize;
	/**
	 * 总条数
	 */
	private Integer totalCount;
	/**
	 * 查询的结果集
	 */
	private List<?> result;

	public PageResult() {
	}

	public static PageResult empty(Integer pageSize) {
		return new PageResult(1, pageSize, 0, new ArrayList<>());
	}

	public PageResult(Integer currentPage, Integer pageSize, Integer totalCount, List<?> result) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.result = result;
	}

	/**
	 * 总页数:推荐这种 其中pageSize - 1 就是 totalCount/ pageSize的最大的余数
	 * 
	 * @return
	 */
	public Integer getTotalPage() {
		return Math.max((totalCount + pageSize - 1) / pageSize, 1);
	}

	/**
	 * 上一页
	 * 
	 * @return
	 */
	public Integer getPrev() {
		return Math.max((currentPage - 1), 1);
	}

	/**
	 * 下一页
	 * 
	 * @return
	 */
	public Integer getNext() {
		return Math.min((currentPage + 1), getTotalPage());
	}

	/**
	 * 总记录数
	 * 
	 * @return
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * 结果集
	 * 
	 * @return
	 */
	public List<?> getResult() {
		return result;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	@Override
	public String toString() {
		return "PageResult [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", result=" + result + "]";
	}
	
}
