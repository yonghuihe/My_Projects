package com._520it.com.base.query;

import java.io.Serializable;

public class QueryObject  implements Serializable{

	protected Integer currentPage = 1;
	protected Integer pageSize = 5;
	
	public int getStart(){
		return (currentPage - 1) * pageSize;
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
