package com.xmg.api.qury;

import java.util.ArrayList;
import java.util.List;

public class PageResult  extends QueryObject{

	private List listData;
	private Integer totalCount;
	private Integer prevPage;
	private Integer nextPage;
	private Integer totalPage;
	
	public PageResult(){}
	
	public PageResult(List listData,Integer totalCount,
			Integer currentPage,Integer pageSize){
		this.listData = listData;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.totalPage = this.totalCount % this.pageSize == 0 ? 
				this.totalCount / this.pageSize : 
					this.totalCount / this.pageSize + 1;
		
		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? 
				this.currentPage + 1 : this.totalPage;
	}
	
	public static PageResult empty(Integer pageSize){
		return new PageResult(new ArrayList(),0,1,pageSize);
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
	public List getListData() {
		return listData;
	}
	public void setListData(List listData) {
		this.listData = listData;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(Integer prevPage) {
		this.prevPage = prevPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getTotalPage() {
		return totalPage == 0 ? 1 : totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
